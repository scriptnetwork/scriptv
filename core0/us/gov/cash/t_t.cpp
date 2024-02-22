//===-                           S C R I P T  T.V.
//===-                           https://script.tv
//===-
//===-            Copyright (C) 2017-2024 Script Network
//===-            Copyright (C) 2017-2024 manicberet@gmail.com
//===-
//===-                      GNU GENERAL PUBLIC LICENSE
//===-                       Version 3, 29 June 2007
//===-
//===-    This program is free software: you can redistribute it and/or modify
//===-    it under the terms of the GPLv3 License as published by the Free
//===-    Software Foundation.
//===-
//===-    This program is distributed in the hope that it will be useful,
//===-    but WITHOUT ANY WARRANTY; without even the implied warranty of
//===-    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
//===-
//===-    You should have received a copy of the General Public License
//===-    along with this program, see LICENCE file.
//===-    see https://www.gnu.org/licenses
//===-
//===----------------------------------------------------------------------------
//===-
#include "accounts_t.h"
#include "types.h"
#include "t_t.h"

#define loglevel "gov/cash"
#define logclass "t_t"
#include <us/gov/logs.inc>

using namespace us::gov::cash;
using c = us::gov::cash::t_t;

const char* c::msg_sealed{". Sealed."};

c::t_t(): flags(0) {
}

c::t_t(const t_t& other) {
    for (auto& i: other) {
        emplace(i);
    }
    flags = other.flags;
}

void c::hash_data_to_sign(sigmsg_hasher_t& h) const {
    for (auto& i: *this) {
        h.write(i.first);
        h.write(i.second);
    }
    h.write(flags);
}

void c::hash_data(hasher_t& h) const {
    for (auto& i: *this) {
        h.write(i.first);
        h.write(i.second);
    }
    h.write(flags);
}

void c::on_destroy(accounts_t& ledger) {
    for(auto& i: *this) {
        auto j = ledger.find(i.first);
        if (j != ledger.end()) {
            j->second.box.add(i.first, i.second);
        }
    }
}

void c::merge(const c& other) {
    for (auto i = other.begin(); i != other.end(); ++i) {
        auto j = emplace(*i);
        if (!j.second) {
            j.first->second += i->second;
        }
    }
    if (other.is_sealed()) flags = 1; //if any is sealed, the result is sealed.
}

/*
1 address = 1 coin
t_t is the token container
*/

ko c::set_supply(const hash_t& token, const cash_t& supply) {
    log("set_supply.", token, supply);
    if (is_sealed()) {
        auto r = "KO 89785 Mint is sealed. Ignored attempt to set supply.";
        log(r);
        return r;
    }
    auto i = find(token);
    if (i == end()) {
        if (supply == -1) {
            emplace(token, 0);
            flags = 1;
            return ok;
        }
        if (supply <= 0) {
            auto r = "KO 89786 Supply must be positive integer.";
            log(r);
            return r;
        }
        emplace(token, supply);
        return ok;
    }
    log("cur value", i->second);
    if (supply == -1) { //seal mint. Makes current amount in circulation immutable, becoming a zero inflation coin.
        log("mint sealed.", token);
        flags = 1;
        return ok; // don't update the balance
    }
    if (supply == 0) {
        if (is_unsealed()) { //if sealed the item doesnt go away 
            log("delete entry");
            erase(i);
        }
        return ok;
    }
    if (supply < 0) {
        auto r = "KO 89787 Supply must be positive integer.";
        log(r);
        return r;
    }
    i->second = supply;
    return ok;
}

cash_t c::get_valueX(const hash_t& coin) const {
    auto i = find(coin);
    if (i == end()) {
        return 0;  //non existent mint
    }
    return i->second;
}

bool c::burn(const hash_t& addr, const hash_t& coin, const cash_t& amount) {
    assert(coin.is_not_zero());
    auto i = find(coin);
    if (i == end()) return false;
    if (i->second >= amount) {
        i->second -= amount;
        if (i->second == 0) {
            if (addr == coin && is_unsealed()) {
                erase(i);
            }
        }
        return true;
    }
    return false;
}

void c::dump(const string& prefix, const hash_t& addr, ostream& os) const {
    for (auto& i: *this) {
        os << prefix << "coin " << i.first << ' ' << i.second;
        if (addr == i.first) {
            os << ". Mint";
            if (is_sealed()) {
                os << msg_sealed;
            }
        }
        os << '\n';
    }
}

size_t c::blob_size() const {
    return b::blob_size() + blob_writer_t::blob_size(flags);
}

void c::to_blob(blob_writer_t& writer) const {
    log("to_blob", "cur", (uint64_t)(writer.cur - writer.blob.data()));
    b::to_blob(writer);
    writer.write(flags);
}

ko c::from_blob(blob_reader_t& reader) {
    log("from_blob", "cur", (uint64_t)(reader.cur - reader.blob.data()));
    auto r = b::from_blob(reader);
    if (is_ko(r)) {
        return r;
    }
    // <orma backwards_compatibility_hack version_from=0 version_to=11/>
    log("reader.header.version", +reader.header.version);
    if (reader.header.version < 11) {
        log("old version. flags set to 0");
        flags = 0; //unsealed
    }
    else {
        auto r = reader.read(flags);
        if (is_ko(r)) return r;
    }
    return ok;
}

