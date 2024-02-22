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
#pragma once

#include <string>
#include <map>
#include <cassert>
#include <vector>
#include <iostream>
#include "namespace_t.h"

namespace mim {
    using namespace std;

    struct menu_vertex_t;
    
    struct root_vertex_t: tree_vertex__deps_t { 
        using b = tree_vertex__deps_t;

        root_vertex_t(const string& name, const string& mim_file);
        root_vertex_t(const root_vertex_t&);
        vertex_t* clone() const override;
        bool merge(vertex_t* other, int op) override;

        string conf_target_dir(const string& lang) const override;
        using b::conf_target_dir;
        void dump_(ostream&) const override;

        void collect_all_menus(vector<const menu_vertex_t*>& menus) const;

        float z_order() const override;
    };

}

