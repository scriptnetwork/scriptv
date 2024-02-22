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

#include "sdk_vertex_t.h"

using namespace std;
using namespace mim;

using c = mim::sdk_vertex_t;

c::sdk_vertex_t(const string& name, const string& mim_file): b(name, mim_file) {
    codefiles.classname_alg = codefiles_t::vertex_name;
}

c::sdk_vertex_t(const c& other): b(other) {
}

vertex_t* c::clone() const {
    return new c(*this);
}

bool c::merge(vertex_t* other_, int op) {
    if (!b::merge(other_, op)) return false;
    auto* other = dynamic_cast<c*>(other_);
    if (other == nullptr) {
        cerr << "KO 59043 type mismatch. " << vertex_dir() << '\n';
        abort();
    }
    return true;
}

string c::conf_target_dir(const string& lang) const {
    return "us/sdk/wallet/" + lang + "/" + conf_target_dir0(lang);
}

void c::dump_(ostream& os) const {
    {
        ind_t ind(os, "  ");
        os << "__ sdk vertex.\n";
    }
    b::dump_(os);
}


