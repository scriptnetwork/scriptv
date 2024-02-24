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
#include "data_sources_t.h"

#include <us/gov/io/cfg1.h>

#include <us/wallet/engine/daemon_t.h>
#include <us/wallet/wallet/local_api.h>
#include <us/wallet/trader/businesses_t.h>

#include "types.h"

#define loglevel "wallet/engine"
#define logclass "data_sources_t"
#include <us/gov/logs.inc>

using namespace us::wallet::engine;
using c = us::wallet::engine::data_sources_t;

void data_source_t::dump(const string& pfx, ostream&) const {
}


c::data_sources_t(daemon_t& daemon): daemon(daemon) {
    emplace("human", new human_data_source_t());
    emplace("wearable. health watch.", new health_watch_data_source_t());
}

c::~data_sources_t() {
    {
        lock_guard<mutex> lock(mx);
        for (auto i: *this) {
            delete i.second;
        }
    }
}

ko c::get_index(data_sources_index_t& ans) {
    assert(ans.empty());
    ans.reserve(size());
    for (auto& i: *this) {
        data_sources_index__item_t o;
        o.name = i.first;
        o.address = i.second->address;
        ans.emplace_back(move(o));
    }
    return ok;
}

ko c::connect(const string& name, const hash_t& address) {
    lock_guard<mutex> lock(mx);
    auto i = find(name);
    if (i == end()) {
        auto r = "KO 79654 data source not found";
        log(r);
        return r;
    }
    i->second->connect(address);
    return ok;
}

data_source_t* c::get_data_source(const string& name) {
    log("get_data_source", name);
    lock_guard<mutex> lock(mx);
    auto i = find(name);
    if (likely(i != end())) {
        log("data_source_t instance is", i->second, "name=", name);
        return i->second;
    }
    return nullptr;
}

void c::dump(ostream& os) const {
    lock_guard<mutex> lock(mx);
    for (auto i: *this) {
        os << "name: /" << i.first << '\n';
        i.second->dump("  ", os);
    }
}

