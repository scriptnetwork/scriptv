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
#include "params_t.h"
#include "vertex_t.h"
#include "engine_t.h"
#include <us/gov/ko.h>
#include <sstream>
#include <cassert>

using namespace std;
using namespace mim;
using namespace us;

using c = mim::params_t;

void c::dump(ostream& os) const {
    for (auto& i: *this) {
        os << "'" << i.first << "': '" << i.second << "'\n";
    }
}

void c::underride__notdef(const string& key, const string& value) {
    auto i = find(key);
    if (i == end()) {
        add(key, value);
    }
}

void c::underride__empty(const string& key, const string& value) {
    auto i = find(key);
    if (i == end()) {
        add(key, value);
        return;
    }
    if (i->second.empty()) {
        i->second = value;
    }
}

void c::underride__notdef(const c& other) {
    for (auto& i: other) {
        underride__notdef(i.first, i.second);
    }
}

void c::underride__empty(const c& other) {
    for (auto& i: other) {
        underride__empty(i.first, i.second);
    }
}

pair<bool, string> c::get(const string& key) const {
    auto i = find(key);
    if (i == end()) {
        return make_pair(false, "");
    }
    return make_pair(true, i->second);
}


void c::add(const string& k, const string& v) {
    auto i = find(k);
    if (i == end()) {
        emplace(k, v);
        return;
    }
    i->second = v;
}

void c::add(const c& other) {
    for (auto& i: other) {
        add(i.first, i.second);
    }
}

bool c::load(const string& fqn) {
    cout << "loading params from " << fqn << '\n';
    ind_t ind(cout, "  ");
    string buf;
    auto r = engine_t::read_text_file_(fqn, buf);
    if (is_ko(r)) {
        cout << "KO 44003\n" << fqn << endl;
        return false;
    }
    size_t i0{0};
    while (true) {
        if (i0 >= buf.size()) break;
        auto i = buf.find('\n', i0);
        if (i == string::npos) {
            i = buf.size();
        }
        auto m = buf.find_first_of("#\n", i0);
        //line without comment [i0,m]
        auto p = buf.find_first_of("=\n", i0);
        if (p != string::npos) {
            auto param = buf.substr(i0, p - i0);
            engine_t::trim(param);
            if (!param.empty()) {
                ++p;
                if (p != buf.size()) {
                    auto value = buf.substr(p, m - p);
                    engine_t::trim(value);
                    if (!value.empty()) {
                        if (value[0] == '"' && value[value.size() - 1] == '"') {
                            value = value.substr(1, value.size() - 2);
                        }
                    }
                    cout << "param: " << param << " value: " << value << endl;
                    add(param, value);
                }
            }
        }
        i0 = i + 1;
    }
    return true;
}

void c::merge(params_t& other, int op) {
    for (auto& i: other) {
        add(i.first, i.second);
    }
    other.clear();
}

