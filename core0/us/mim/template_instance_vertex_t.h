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

#include "android_vertex_t.h"
#include <map>

namespace mim {
    using namespace std;

    struct namespace_t;
    struct template_vertex_t;

    struct template_instance_vertex_t: android_vertex_t {
        using b = android_vertex_t;

        template_instance_vertex_t(const string& name, const string& mim_file, const string& template_id, params_t&& params);
        //template_instance_vertex_t(const string& name, const string& template_id, string srcfile); //use only under a template_vertex
        template_instance_vertex_t(const template_instance_vertex_t&);
        vertex_t* clone() const override;

        void include_java(const string&);

//        void inflate(const string& pfx, const params_srcs_t& base_params, const ancestor_data_t&, track_data_t&);
        void inflate(vector<namespace_t*>& allinstances);
        //static void traverse_inflate(const string& pfx, namespace_t&, const map<string, string>& params);
        void dump_(ostream&) const override;

        void replace_with_template_instance();
        bool merge(vertex_t* other, int op) override;

        bool configure() override;
        bool gen() override;

        virtual void pre_configure(namespace_t& instance) const;
        //bool needs_review() const override;

        virtual void rewrite_classname__instance(string& instance_classname) const;
                //string classname() const override;

        pair<bool, string> params__get(const string& key) const override;
        //params_t params__get_all() const override;

        virtual token_resolution_t resolve_token(const namespace_t& instance, const string& token) const;

    public:
        //params_t params;
        string template_id;
        //bool in_template{false};
//        params_srcs_t params_srcs;
       // params_t params;
        //int depth{0};
        map<string, params_t> custom_params;
        const template_vertex_t* templ_spec{nullptr};
        b* produced_vertex{nullptr};
    };
}

