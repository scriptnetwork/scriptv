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
#include "java_files_t.h"
#include "cpp_files_t.h"
#include "codefiles_t.h"
#include "params_t.h"

namespace mim {
    using namespace std;

    struct namespace_t;
    struct menu_vertex_t;
    struct template_instance_vertex_t;

    struct namespace_t: tree_vertex__deps_t {
        using b = tree_vertex__deps_t;
        static constexpr auto kickoff_code_hash_token{"kickoff code hash: "};
        static constexpr auto prefix_header{"//MIM  "};
    public:
        namespace_t(const string& name, const string& mim_file);
        namespace_t(const namespace_t&);
        vertex_t* clone() const override;

    public:
        bool pre_configure() override;
        bool configure() override;
        bool gen() override;


        void on_attach(const b&) override;
        void on_detach() override;

        void dump_(ostream&) const override;
        void collect_menus(vector<const menu_vertex_t*>&) const;
        void collect_all_menus(vector<const menu_vertex_t*>& menus) const;

        const namespace_t* get_parent() const { return dynamic_cast<const namespace_t*>(b::get_parent()); }
        namespace_t* get_parent() { return dynamic_cast<namespace_t*>(b::get_parent()); }
        //void set_classname(const string& classname);
        //void inflate(const namespace_t& parent, params_srcs_t& params_srcs);

//        void review(vector<string>& changed_mim_files) const;
        string get_namespace() const;

        string kickoff_code_dir() const;
        void set_kickoff_code_dir();

        hash_t hash_content(const string& content0) const;

        using tokens_resolved_t = filedef_t::tokens_resolved_t;

        void write_info(const string& src, const string& mim_file, const tokens_resolved_t&, ostream&) const;
        void write_info_kickoff(const string& src, const string& mim_file, const hash_t&, const tokens_resolved_t&, ostream&) const;
        void write_info_all(const string& src, const string& mim_file, const tokens_resolved_t&, ostream&) const;
        void write_header(const string& src, const string& mim_file, const hash_t&, const tokens_resolved_t&, ostream&) const;

    public:
        bool has_impl() const { return codefiles.has_java() || codefiles.has_cpp(); }
        bool merge(vertex_t* other, int op) override;
        //virtual void clone_template(const namespace_t& src, const params_t&);

        virtual lang_codefiles_t* create_lang_codefiles(const string& lang);

        void fill_includes(set<string>&) const;

        //void review_info(const string& pfx, ostream&) const;

        //virtual string classname() const;
//        bool needs_review() const;
//        virtual bool needs_review() const;
//        void set_need_review() { need_review = true; reviewed = false; } 

        virtual void rewrite_classname(string&) const;
//    protected:
//        bool need_review{false};
//        mutable bool reviewed{false};

        bool params__load(const string& filename);
        virtual pair<bool, string> params__get(const string& key) const;
//        virtual params_t params__get_all() const;

        virtual token_resolution_t resolve_token(const string& token) const;
        void resolve_tokens(const filedef_t::tokens_t& tokens_used, filedef_t::tokens_resolved_t& tokens_resolved) const;
        
    public:
        codefiles_t codefiles;
        template_instance_vertex_t* template_instance_vertex{nullptr};
        params_t params;
    };

}

