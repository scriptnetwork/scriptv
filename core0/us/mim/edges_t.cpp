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
#include "edges_t.h"
#include "vertex_t.h"

using namespace std;
using namespace mim;

using c = mim::edges_t;

c::edges_t() {
}

c::edges_t(const edges_t& other): in(other.in), out(other.out) {
}

void c::dump(ostream& os) const {
    os << "edges:\n";
    {    
        ind_t ind(os, "  ");
        os << "out (parent): " << out.size() << '\n';
        {
            ind_t ind(os, "  ");
            out.dump(os);
        }
        os << "in (children): " << in.size() << '\n';
        {
            ind_t ind(os, "  ");
            in.dump(os);
        }
    }
}

using d = mim::edges_t::directional_edges_t;

d::directional_edges_t() {
}

vertex_t* d::find_by_name(const string& name) {
    for (auto& i: *this) {
        if (i->name == name) return i;
    }
    return nullptr;
}

void d::dump(ostream& os) const {
    static int w = 8;
    if (empty()) return;
    int j = 0;
    for (auto& i: *this) {
        if (j > 0 && j % w == 0) {
            os << '\n';
        }
        os << i->name << ", ";
        ++j;
    }
    os << '\n';
}

