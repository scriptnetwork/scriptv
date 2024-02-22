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
#include <set>
#include <cassert>
#include <vector>
#include <functional>
#include <iostream>
#include "lang_codefiles_t.h"
#include "params_t.h"

namespace mim {
    using namespace std;

    struct namespace_t;

    struct codefiles_t final: map<string, lang_codefiles_t*> {

        codefiles_t(namespace_t&);
        codefiles_t(namespace_t& parent, const codefiles_t&);
        ~codefiles_t();

        void clear();

        void add_java();
        void add_cpp();
        void include_java(const string& dep);

        bool has_java() const;
        bool has_cpp() const;
        void add(const string& lang);
        bool merge(codefiles_t& other, int op);
        //bool merge2(const codefiles_t& other, int op) const;
        bool gen();
        void pre_configure();
        bool configure();
        //void update_mim_srcdir(const namespace_t& parent_vertex);

        //void on_attach_vertex(const namespace_t& parent);
        //void on_detach_vertex();
        void dump(ostream&) const;

        //void inflate();

        lang_codefiles_t* find_lang_codefiles(const string& lang, const string& subhome);
        void fill_includes(set<string>& dest) const;
        //void check_review() const;
        //bool review(int stage) const; //returns true if kickoff files were written
        void compute_classname();
        //string kickoff_code_dir() const;
        void make_generated(const string& filename, filedef_t& filedef) const;
        static void remove_header(string&);

    public:
        namespace_t& vertex;
        //string prefix_classname;
        int kickoff_code{0}; 
                             //0: generated files go straight to codebase without writing in the mim directories 
                             //1: generated files go to the mim directories, unless they already exist, 
        string kickoff_code_dir; 
        //bool kickoff_code_diff{false};
        //int diff_review_{-1};
        string classname;

        enum classname_alg_t {
            not_set,
            vertex_name,
            same_as_parent,
            append_to_parent,

            num_classname_alg
        };

        static constexpr const char* classname_alg_str[num_classname_alg] = {"not_set", "vertex_name", "same_as_parent", "append_to_parent"};

        classname_alg_t classname_alg{not_set};
        //bool _written_kickoff_code{false};
        //set<string> filename_tokens;
        //params_srcs_t inflate_params;
    };

}

