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
#include "android_vertex_t.h"
#include "menuspec_t.h"

namespace mim {
    using namespace std;

    struct namespace_t;

    struct menu_vertex_t: android_vertex_t {
        using b = android_vertex_t;
        using menuspec_t = mim::menuspec_t;

        menu_vertex_t(const string& name, const string& mim_file);

        menu_vertex_t(const menu_vertex_t&);
        vertex_t* clone() const override;

        void add(menuspec_t&&);
        using b::add;

        void dump_(ostream&) const override;
        string spec_classname(const menuspec_t&) const;

        void pre_configure(const menuspec_t&);

        bool pre_configure() override;
        bool configure() override;
        bool gen() override;
        bool merge(vertex_t* other, int op) override;

    public:
        map<string, menuspec_t> menus;
        const menu_vertex_t* base{nullptr};
    };

}

