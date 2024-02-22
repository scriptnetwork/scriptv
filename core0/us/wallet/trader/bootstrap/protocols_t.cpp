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
#include "protocols_t.h"
#include <functional>
#include <us/wallet/trader/types.h>

#define loglevel "wallet/trader/bootstrap"
#define logclass "protocols_t"
#include <us/gov/logs.inc>

using namespace us::wallet::trader;
using c = us::wallet::trader::bootstrap::protocols_t;

c c::filter_common(protocols_t a, protocols_t b) {
    log("filter_common");
    function f = [](const item& a, const item& b) -> bool {
        if (a.first != b.first) return a.first < b.first;
        return a.second < b.second;
    };
    protocols_t ans;
    if (a.empty() || b.empty()) {
        log("one is empty", a.size(), b.size());
        return ans;
    }
    sort(a.begin(), a.end(), f);
    sort(b.begin(), b.end(), f);
    protocols_t::const_iterator i = a.cbegin();
    protocols_t::const_iterator j = b.cbegin();
    while (true) {
        //if (i == a.end() || i == b.end() || j == a.end() || j == b.end()) break;
        if (i == a.end() || i == b.end() || j == a.end() || j == b.end()) break;
        log("i: ", i->first, i->second, "j:", j->first, j->second);
        if (i->first == j->first && i->second == j->second) {
            log("adding i");
            ans.emplace_back(*i);
            ++i;
            ++j;
            continue;
        }
        if (!f(*i, *j)) {
            log("swap");
            std::swap(i, j);
        }
        while (f(*i, *j)) {
            ++i;
            if (i == a.end() || i == b.end()) break;
        }
    }
    log("return ", ans.size());
    return ans;
}

void c::uniq() {
    set<protocol_selection_t> x;
    for (auto &i: *this) {
        x.emplace(i);
    }
    clear();
    reserve(x.size());
    for (auto& i: x) {
        emplace_back(i);
    }
}

void c::dump(ostream& os) const {
    for (auto& i: *this) {
        os << i.first << ' ' << i.second << '\n';
    }
}

void c::dump(const string& prefix, ostream& os) const {
    for (auto& i: *this) {
        os << prefix << i.first << ' ' << i.second << '\n';
    }
}

