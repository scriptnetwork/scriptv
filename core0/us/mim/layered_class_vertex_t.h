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

namespace mim {
    using namespace std;

    struct namespace_t;
    struct lang_codefiles_t;
    
    struct layered_class_vertex_t: android_vertex_t {
        using b = android_vertex_t;

        struct layerdef_t {
            string name;
            string mim_file;
            string kickoff_dir;
        };
        using layers_t = vector<layerdef_t>;

        layered_class_vertex_t(const string& name, const string& layer_name, const string& mim_file);
        layered_class_vertex_t(const string& name, const vector<string>& layer_names, const string& mim_file);
        layered_class_vertex_t(const layered_class_vertex_t&);
        vertex_t* clone() const override;

        bool merge(vertex_t* other_, int op) override;

        void customize_after_being_instantiated_by_a_template() override;
        bool pre_configure() override;

        void dump_(ostream&) const override;

        static string layerize(const string& name0, const string& layer_name);
        token_resolution_t resolve_token(const string& token) const override;

        string package() const;
        string layer_class_content(layers_t::const_iterator z) const;
        string layer_class_template() const;


    public:
        layers_t layers;
        string deepest_baseclass;
        string subhome;
    };

}

