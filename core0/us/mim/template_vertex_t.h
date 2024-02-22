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

#include <vector>
#include "android_vertex_t.h"

namespace mim {
    using namespace std;

    struct template_instance_vertex_t;
    struct codefiles_t;

    struct template_vertex_t: android_vertex_t {
        using b = android_vertex_t;
        template_vertex_t(const string& name, const string& mim_file, const string& id, params_t&& default_params);
        template_vertex_t(const string& name, const string& mim_file, const string& id);
        template_vertex_t(const template_vertex_t&);
        vertex_t* clone() const override;

        namespace_t* create_instance(namespace_t& parent, template_instance_vertex_t*) const;
        void dump_(ostream&) const override;

//        virtual void customize_produced_vertex(const template_instance_vertex_t&, namespace_t& instance) const;
        virtual void customize_produced_vertex(namespace_t& instance) const;
        virtual void rewrite_classname__instance(namespace_t& instance_classname) const {}

        //virtual params_t default_params() const;

        bool configure() override;
        bool gen() override;

        virtual token_resolution_t resolve_token(const namespace_t& instance, const string& token) const;

        //void customize_produced_vertexes() const;
    public:
        string id;
        //params_t params;

        mutable vector<template_instance_vertex_t*> instances;
    };
}

