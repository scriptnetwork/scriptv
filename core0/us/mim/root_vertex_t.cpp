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
#include <sys/stat.h>
#include <sys/types.h>
#include <iostream>
#include <map>
#include <sstream>
#include <cassert>
#include <vector>
#include <filesystem>
#include <us/gov/ko.h>
#include "root_vertex_t.h"
#include "layer_vertex_t.h"
#include "menu_vertex_t.h"

#include "engine_t.h"

using namespace std;
using namespace mim;

using c = mim::root_vertex_t;

c::root_vertex_t(const string& name, const string& mim_file): b(name, mim_file) {
    merge__force_same_name = false;
}

c::root_vertex_t(const c& other): b(other) {
}

vertex_t* c::clone() const {
    cerr << "clone root vertex not impl" << endl;
    assert(false);
    abort();
    //    return new c(*this);
}

bool c::merge(vertex_t* other_, int op) {
    if (!b::merge(other_, op)) {
        cout << "!! base. " << name << endl;
        return false;
    }
    auto* other = dynamic_cast<c*>(other_);
    if (other == nullptr) {
        cerr << "KO 55041 type mismatch. " << vertex_dir() << '\n';
        abort();
    }
    return true;
}

/*
void c::vertex_dir(const string& sep, ostream& os) const {
//    if (get_parent() != nullptr) {
//        cout << get_parent()->name << endl;
//    }
    //assert (get_parent() == nullptr);
    os << name;
}
*/

string c::conf_target_dir(const string& lang) const {
    //assert (get_parent() == nullptr);
    return "";
}

float c::z_order() const {  //overriden to provide a >=0 number if is not contained ina  layer vertex
    auto p = get_parent();
    if (p == nullptr) return -1;
    //if has a parent it needs to be a layer
    auto l = dynamic_cast<const layer_vertex_t*>(p);
    assert(l != nullptr);
    return l->z_order();
}

void c::collect_all_menus(vector<const menu_vertex_t*>& menus) const {
    auto& deps = get_configure_deps();
    for (auto& child: deps.in) {
        auto o = dynamic_cast<namespace_t*>(child);
        assert (o != nullptr);
        o->collect_all_menus(menus);
    }
}

void c::dump_(ostream& os) const {
    {
        ind_t ind(os, "  ");
        os << ".ϵ root vertex.\n";
    }
    b::dump_(os);
}

