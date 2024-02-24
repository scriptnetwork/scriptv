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

    struct namespace_t;
    struct lang_codefiles_t;
    
    struct android_vertex_t: namespace_t {
        using b = namespace_t;

        android_vertex_t(const string& name, const string& mim_file);
        android_vertex_t(const android_vertex_t&);
        vertex_t* clone() const override;

        string conf_target_dir(const string& lang) const override;
        using b::conf_target_dir;
        //bool merge(vertex_t* other, int op) override;
        lang_codefiles_t* create_lang_codefiles(const string& lang) override;
        void dump_(ostream&) const override;

    public:
        static string package_ns;
        static string package_dir;

        static string cfg_str28; //Android app package name
        static string cfg_str32; //Android app name

    };

}

