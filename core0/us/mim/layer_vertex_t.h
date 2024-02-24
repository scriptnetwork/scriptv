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
#include "tree_vertex__deps_t.h"
#include "root_vertex_t.h"

namespace mim {
    using namespace std;

    struct layer_vertex_t: tree_vertex__deps_t { 
        using b = tree_vertex__deps_t;

        struct merged_root_vertex_t: root_vertex_t {
            using b = root_vertex_t;

            merged_root_vertex_t(const string& name, float z): b(name, ""), z(z) {
            }

            float z_order() const override { return z; }

            float z;
        };

        layer_vertex_t(const string& name, const string& mim_file);
        layer_vertex_t(const layer_vertex_t&);
        vertex_t* clone() const override;
        bool merge(vertex_t* other, int op) override;

        void vertex_dir(const string& sep, ostream&) const override;
        using b::vertex_dir;
        string conf_target_dir(const string& lang) const override;
        using b::conf_target_dir;

        void list_layers(const string& pfx, float maxz, ostream&) const;

        void gen_merge_configure(float maxz);

        float z_order() const override { return -1; } //need to be overriden to provide a >=0 number
        void dump_(ostream&) const override;

    private:
        root_vertex_t* merge(int op);
        //void merge_travel(root_vertex_t* src, root_vertex_t* dst, int op);
        string title;
    };

}

