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
#include <us/gov/types.h>
#include <us/gov/config.h>
#include <us/gov/engine/daemon_t.h>
#include <us/gov/io/blob_reader_t.h>
#include <us/gov/io/blob_writer_t.h>

#include "app.h"
#include "t_t.h"
#include "f_t.h"
#include "m_t.h"
#include "types.h"
#include "account_t.h"

#define loglevel "gov/cash"
#define logclass "account_t"
#include <us/gov/logs.inc>

using namespace us::gov::cash;
using c = us::gov::cash::account_t;

c::account_t(): locking_program(app::locking_program_id), box(0) {
}

c::account_t(const locking_program_t& locking_program, const box_t& box): locking_program(locking_program), box(box) {
}

c::account_t(locking_program_t&& locking_program, box_t&& box): locking_program(move(locking_program)), box(move(box)) {
}

void c::on_destroyX(accounts_t& ledger) {
    box.on_destroy(ledger);
}

ko c::merge(const account_t& other) {
    return box.merge(other.box);
}

cash_t c::get_value_oil() const {
    return box.value;
}

cash_t c::get_valueX(const hash_t& token) const {
    if (token.is_zero()) return box.value;
    if (box.t == nullptr) return 0;
    return box.t->get_valueX(token);
}

cash_t c::input_eligible(const hash_t& token) const { //returns available token
    log("input_eligible");
    auto fee = box.maintenance_fee();
    if (box.value <= fee) {
        auto r = "KO 79832 Negligible dust.";
        log(r, box.value, fee, "=> 0");
        return 0;
    }
    if (token.is_zero()) {
        auto n = box.value - fee;
        if (box.f == nullptr && box.t == nullptr && box.m == nullptr) {
            log("light. gas", n);
            return n;
        }
        if (n < CFG_LOW_GAS_LEVEL) {
            log("under CFG_LOW_GAS_LEVEL gas. not eligible.", CFG_LOW_GAS_LEVEL);
            return 0; //accounts holding data are not taken away gas if they 're under CFG_LOW_GAS_LEVEL
        }
        log("eligible gas", n - CFG_LOW_GAS_LEVEL);
        return n - CFG_LOW_GAS_LEVEL;
    }
    if (box.t == nullptr) {
        log("t is null");
        return 0;
    }
    auto i = box.t->find(token);
    if (i == box.t->end()) {
        log("no token");
        return 0;
    }
    return i->second;
}

pair<ko, cash_t> c::input_eligible3(const hash_t& token) const { //returns available amount, even if ko code is not ok, amount returned is eligible for withdraw
    log("input_eligible3");
    auto fee = box.maintenance_fee() + safe_deposit_box::burn_fee;
    if (box.value <= fee) {
        auto r = "KO 79833 Remaining amount is less than fees. (dust).";
        log(r, box.value, fee);
        return make_pair(r, 0);
    }
    if (token.is_zero()) {
        if (box.t != nullptr) {
            if (box.t->is_sealed()) {
                if (box.value < CFG_LOW_GAS_LEVEL) {
                    auto r = "KO 79834 Account with a sealed mint is below the minimum threshold of oil. CFG_LOW_GAS_LEVEL.";
                    log(r, box.value, fee, CFG_LOW_GAS_LEVEL);
                    return make_pair(r, 0); //accounts holding data are not taken away gas if they 're under CFG_LOW_GAS_LEVEL
                }
                auto r = "WA 79835 This address contains a sealed mint, predictable reserve is enforced limiting the amount of oil that can be withdrawn.";
                log(r, "Max gas withdraw", box.value - CFG_LOW_GAS_LEVEL);
                return make_pair(r, box.value - CFG_LOW_GAS_LEVEL);
            }
        }
        auto n = box.value - fee;
        log("allowed to pick from predictable reserve.", box.value, CFG_LOW_GAS_LEVEL, "returned gas", n);
        return make_pair(ok, n);
    }
    if (box.t == nullptr) {
        auto r = "KO 79382 No tokens here.";
        log(r, 0);
        return make_pair(r, 0);
    }
    auto i = box.t->find(token);
    if (i == box.t->end()) {
        auto r = "KO 79383 No token here.";
        log(r, 0);
        return make_pair(r, 0);
    }
    log(token, i->second);
    return make_pair(ok, i->second);
}

void c::dump(const string& prefix, int detail, ostream& os) const {
    dump(prefix, hash_t::zero_, detail, os);
}

void c::dump(const string& prefix, const hash_t& addr, int detail, ostream& os) const {
    os << prefix;
    if (addr.is_not_zero()) {
        os << addr << ' ';
        if (detail > 1) {
            if (unlikely(locking_program != app::locking_program_id)) {
                os << "(locking_program " << +locking_program << ") ";
            }
        }
    }
    os << UGAS << ' ' << box.value;
    if (detail == 0) {
        if (box.m != nullptr || box.f != nullptr || box.t != nullptr) {
            os << " *\n";
        }
        else {
            os << '\n';
        }
        return;
    }
    if (unlikely(box.m != nullptr)) {
        os << "; " << box.m->size() << " kv records";
    }
    if (unlikely(box.f != nullptr)) {
        os << "; " << box.f->size() << " files. " << box.f->total_mib() << " MiB";
    }
    if (unlikely(box.t != nullptr)) {
        os << "; " << box.t->size() << " coins";
    }
    os << '\n';
    if (detail > 1) {
        string pfx = prefix + "    ";
        string pfx2 = pfx + "    ";
        if (box.t != nullptr) {
            os << pfx << "Coins:\n";
            box.t->dump(pfx2, addr, os);
        }
        if (box.m != nullptr) {
            os << pfx << "Key-value:\n";
            box.m->dump(pfx2, os);
        }
        if (box.f != nullptr) {
            os << pfx + "Files:\n";
            box.f->dump(pfx2, os);
        }
    }
}

void c::list_files(const string& path, ostream& os) const {
    if (box.f == nullptr) return;
    box.f->list_files(path, os);
}

hash_t c::file_hash(const string& path) const {
    if (box.f == nullptr) return hash_t::zero_;
    return box.f->file_hash(path);
}

void c::print_data(const hash_t& addr, ostream& os) const {
    os << "Account " << addr << '\n';
    os << "locking_program " << +locking_program << '\n';
    os << UGAS << ' ' << box.value << '\n';
    if (box.m != nullptr) {
        os << box.m->size() << " key-value records:\n";
        box.m->dump("  ", os);
    }
    if (box.f != nullptr) {
        os << box.f->size() << " files. Total size " << box.f->total_mib() << " MiB.\n";
        box.f->dump("  ", os);
    }
    if (unlikely(box.t != nullptr)) {
        os << box.t->size() << " coins:\n";
        box.t->dump("  ", addr, os);
    }
}

void c::hash_data_to_sign(sigmsg_hasher_t& h) const {
    h.write(locking_program);
    box.hash_data_to_sign(h);
}

void c::hash_data(hasher_t& h) const {
    h.write(locking_program);
    box.hash_data(h);
}

size_t c::blob_size() const {
    return blob_writer_t::blob_size(locking_program) + blob_writer_t::blob_size(box);
}

void c::to_blob(blob_writer_t& writer) const {
    log("to_blob", "cur", (uint64_t)(writer.cur - writer.blob.data()));
    writer.write(locking_program);
    writer.write(box);
}

ko c::from_blob(blob_reader_t& reader) {
    log("from_blob", "cur", (uint64_t)(reader.cur - reader.blob.data()));
    {
        auto r = reader.read(locking_program);
        if (is_ko(r)) return r;
    }
    {
        auto r = reader.read(box);
        if (is_ko(r)) return r;
    }
    return ok;
}

