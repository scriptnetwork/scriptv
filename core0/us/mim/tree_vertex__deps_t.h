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
#include "tree_vertex_t.h"
#include "params_t.h"
#include "codefiles_t.h"

namespace mim {
    using namespace std;

    struct tree_vertex__deps_t;

    struct visitpath_t: vector<tree_vertex__deps_t*> {
    };

    struct const_visitpath_t: vector<const tree_vertex__deps_t*> {
    };

    struct template_vertex_t;
    struct template_instance_vertex_t;
    struct namespace_t;
    struct root_vertex_t;

    struct template_instance_vertex_t;
    struct template_vertex_t;

    struct tree_vertex__deps_t: tree_vertex_t {
        using b = tree_vertex_t;

        tree_vertex__deps_t(const string& name, const string& mim_file);
        tree_vertex__deps_t(const tree_vertex__deps_t&);
        ~tree_vertex__deps_t() override;
        vertex_t* clone() const override;
        bool merge(vertex_t* other, int op) override;

        edges_t& get_configure_deps();
        const edges_t& get_configure_deps() const;

        string conf_target_dir0(const string& lang) const;
        virtual string conf_target_dir(const string& lang) const;

        virtual bool is_target_dir() const { return true; }

        void add(tree_vertex__deps_t* child);
        tree_vertex__deps_t& add_(tree_vertex__deps_t& child, int merge_op);
        tree_vertex__deps_t& add_(tree_vertex__deps_t& child);
        tree_vertex__deps_t& remove(tree_vertex__deps_t& child);

        void remove_child(const string& name);

        void detach();

        virtual void on_attach(const tree_vertex__deps_t&);
        virtual void on_detach();
        //virtual void on_ancestor_attach(); //template instantiation

        void set_parent(tree_vertex__deps_t*);
        const tree_vertex__deps_t* get_parent() const;
        tree_vertex__deps_t* get_parent();
//        tree_vertex__deps_t& root();
//        const tree_vertex__deps_t& root() const;
        bool has_children() const;

        const tree_vertex__deps_t* find_vertex(const string& vpath) const;
        const tree_vertex__deps_t* find_vertex_in(vector<string>& rpath) const;
        const tree_vertex__deps_t* find_root() const;
        tree_vertex__deps_t* find_child(const string& findname);
        const tree_vertex__deps_t* find_child(const string& findname) const;
        const tree_vertex__deps_t* find_vertex2(const string& path) const; //supports "./" and "../"

//        virtual bool is_root() const { return false; }
        virtual float z_order() const;

        void traverse_gen();
        void traverse_configure();
        //void set_kickoff_code_dir();

        void inflate_templates(vector<namespace_t*>& allinstances);
        void configure_templates();
//        void pre_configure0_vertexes();
        void pre_configure_vertexes();
        void configure_vertexes();

        void init();
        void init_();
        virtual bool gen();
        virtual bool pre_configure();
        virtual bool configure();

        virtual void customize_after_being_instantiated_by_a_template();
        void customize_after_being_instantiated_by_a_template__all();

        int depth() const;
        virtual void vertex_dir(const string& sep, ostream&) const;
        void vertex_dir(ostream& os) const;
        string vertex_dir() const;

        void dump_(ostream&) const override;
//        void check_review(const track_data_t&);
        //void check_review();

        visitpath_t traverse__breath_first();
        const_visitpath_t traverse__breath_first() const;
        visitpath_t traverse__depth_first();
        const_visitpath_t traverse__depth_first() const;
        visitpath_t traverse__children();
        const_visitpath_t traverse__children() const;
        visitpath_t traverse__leaves();
        void print(visitpath_t& visitpath) const;
        
        vector<template_instance_vertex_t*> find_template_instances();
        vector<template_vertex_t*> find_templates();

        void touch_topmost_templates();
        const root_vertex_t* root_vertex() const;

        void underride_classname_alg(codefiles_t::classname_alg_t alg);

        static map<tree_vertex__deps_t*,vector<tree_vertex__deps_t*>>* init_childmap;
        static void dump_childmap(ostream&);

    public:
        bool gened_{false};
        bool inited_{false};
        
    private:
        string grid;
    
    };

}

