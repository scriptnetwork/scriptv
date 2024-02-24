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
#include <sys/stat.h>
#include <sys/types.h>
#include <iostream>
#include <map>
#include <sstream>
#include <cassert>
#include <vector>
#include <filesystem>

#include <us/gov/ko.h>

#include "namespace_t.h"
#include "menu_vertex_t.h"
#include "engine_t.h"
#include "template_instance_vertex_t.h"
#include "template_vertex_t.h"


using namespace std;
using namespace mim;
using namespace us;

using c = mim::namespace_t;

c::namespace_t(const string& name, const string& mim_file): b(name, mim_file), codefiles(*this) {
}

c::namespace_t(const c& other): b(other), codefiles(*this, other.codefiles), template_instance_vertex(other.template_instance_vertex), params(other.params) {
    /*
    auto& srcdeps = other.get_configure_deps();
    for (auto& i: srcdeps.in) {
        auto tv = dynamic_cast<tree_vertex__deps_t*>(i);
        if (tv == nullptr) {
            cerr << "KO 68868 non tree vertex " << tv->name << '\n';
            assert(false);
            abort();
            continue;
        }
        auto child0 = tv->clone();
        assert(child0 != nullptr);
        auto child = dynamic_cast<tree_vertex__deps_t*>(child0);
        assert(child != nullptr);
        add(*child);
        
        //cout << "cloned " << tv->name << endl;
    }
    */
}

vertex_t* c::clone() const {
    return new c(*this);
}

bool c::merge(vertex_t* other_, int op) { //debug version in gitba04e4c099ba542c679c42338d658c6b192b1bc1
    auto* other = dynamic_cast<c*>(other_);
    if (other == nullptr) {
        cerr << "KO 55041 type mismatch. " << vertex_dir() << '\n';
        abort();
    }

    //auto* ti = dynamic_cast<template_instance_vertex_t*>(this);
    //if (ti != nullptr) {
        //auto p = get_parent();

        //cerr << "YYYYYYYYYYYYYYYYYYYXXXXXXX " << name << ' ' << p->name << endl;
//        if (name == "conf" && p->name != "device_endpoints") {        
        //abort();
//        }
    //}
if (name == "base") {
    cerr << vertex_dir() << endl;
    int n=0;
}

if (other->template_instance_vertex != nullptr) {
    cerr << template_instance_vertex << endl;
    cerr << other->template_instance_vertex << endl;
    cerr << name << endl;
    abort();
}

    auto* oti = dynamic_cast<template_instance_vertex_t*>(other_);
    if (oti != nullptr) {
        cerr << "XXXXXXXXXXXXXXXXXXXXXXXXXXX " << oti->name << endl;
        abort();
    }
    cout << "merge op=" << op << '\n';
    {
        ind_t ind(cout, "-----me> ");
        dump(cout);
    }
    {
        ind_t ind(cout, "--other> ");
        other->dump(cout);
    }
    if (!b::merge(other_, op)) return false;
    auto r = codefiles.merge(other->codefiles, op);
    {
        ind_t ind(cout, "-merged> ");
        dump(cout);
    }
    params.merge(other->params, op);
    return r;
}

lang_codefiles_t* c::create_lang_codefiles(const string& lang) {
    lang_codefiles_t* o = nullptr;
    if (lang == "c++") {
        o = new cpp_files_t(*this, lang);
    }
    if (lang == "java") {
        o = new java_files_t(*this, lang);
    }
    assert(o != nullptr);
    return o;
}

void c::on_detach() {
    b::on_detach();
}

void c::on_attach(const tree_vertex__deps_t& parent) {
    b::on_attach(parent);
}

string c::get_namespace() const {
    auto p = get_parent();
    if (p == nullptr) {
        return name;
    }
    auto pns = dynamic_cast<const namespace_t*>(p);
    if (pns == nullptr) {
        return name;
    }
    return p->get_namespace() + "::" + name;
}

void c::dump_(ostream& os) const {
    {
        ind_t ind(os, "  ");
        os << ":: namespace vertex.\n";
    }
    {
        ind_t ind(os, "    ");
        os << "mim_file: " << mim_file << '\n';
        os << "namespace: " << get_namespace() << '\n';
        os << "params:\n";
        {
            ind_t ind(os, "  ");
            params.dump(os);
        }
        os << "codefiles:\n";
        {
            ind_t ind(os, "  ");
            codefiles.dump(os);
        }
    }
    b::dump_(os);
    {
        ind_t ind(os, "    ");
        if (template_instance_vertex != nullptr) {
            ind_t ind(os, "ref <!>: ");
            template_instance_vertex->dump(os);
        }
    }
}

bool c::gen() {
    if (!b::gen()) return false;
    cout << ":: namespace vertex >>>> " << name << " <<<<\n";
    ind_t ind(cout, "  ");
    {
        ind_t ind(cout, "codefiles> ");
        return codefiles.gen();
    }
}

void c::set_kickoff_code_dir() {
    codefiles.kickoff_code_dir = mim_dir();
    cout << "set_kickoff_code_dir " << codefiles.kickoff_code_dir << ". vertex " << vertex_dir() << '\n';
}

string c::kickoff_code_dir() const {
    ind_t ind(cerr, "  ");
    if (!codefiles.kickoff_code_dir.empty()) {
        return codefiles.kickoff_code_dir;
    }
    auto p = get_parent();
    if (p == nullptr) {
        return "";
    }
    auto s = p->kickoff_code_dir();
    if (s.empty()) {
        return "";
    }
    auto r = s + '/' + name;
    return r;
}

bool c::pre_configure() {
    if (!b::pre_configure()) return false;
    cout << ":: namespace vertex >>>> " << name << " <<<<\n";
    ind_t ind(cout, "  ");
    cout << "codefiles:\n";
    {
        ind_t ind(cout, "  ");
        codefiles.pre_configure(); //kickoff files review is here
        if (template_instance_vertex != nullptr) {
            template_instance_vertex->pre_configure(*this); //kickoff files may be patched here, turning generated
        }
    }
    return true;
}

bool c::configure() {
    if (!b::configure()) return false;
    cout << ":: namespace vertex >>>> " << name << " <<<<\n";
    ind_t ind(cout, "  ");
    cout << "codefiles:\n";
    {
        ind_t ind(cout, "  ");
        if (!codefiles.configure()) {
            return false;
        }
    }
    return true;
}

void c::fill_includes(set<string>& dest) const {
    cout << "fill_includes.  " << vertex_dir() << " sz=" << dest.size() << '\n';
    {
        ind_t ind(cout, "  ");
        auto p = get_parent();
        if (p != nullptr) {
            auto ns = dynamic_cast<const c*>(p);
            if (ns != nullptr) {
                ns->fill_includes(dest);
            }
        }
    }
    ind_t ind(cout, " >");
    codefiles.fill_includes(dest);
    if (template_instance_vertex != nullptr) {
        template_instance_vertex->fill_includes(dest);
    }
}

void c::collect_all_menus(vector<const menu_vertex_t*>& menus) const {
    cout << "collect_menus from vertex " << vertex_dir() << '\n';
    {
        ind_t ind(cout, "  ");
        auto visitpath = traverse__depth_first();
        cout << "traverse. " << name << ". visitpath of " << visitpath.size() << " vertexes\n";
        for (auto& i: visitpath) {
            auto m = dynamic_cast<const menu_vertex_t*>(i);
            if (m == nullptr) continue;
            menus.emplace_back(m);
        }
     }
}

void c::collect_menus(vector<const menu_vertex_t*>& menus) const {
    cout << "collect_menus from vertex " << vertex_dir() << '\n';
    auto visitpath = traverse__children();
    cout << "traverse. " << name << ". visitpath of " << visitpath.size() << " vertexes\n";
    for (auto& i: visitpath) {
        auto m = dynamic_cast<const menu_vertex_t*>(i);
        if (m == nullptr) continue;
        cout << "  found menu " << m->name << '\n';
        menus.emplace_back(m);
    }
}

hash_t c::hash_content(const string& content0) const { //produce a hash based on manual (changing salt produces review of all vertexes), file header (in case headers are changed over time), and content.
    ostringstream os;
    os << "salt-4582975648";
    write_header("", "", hash_t::zero_, tokens_resolved_t(), os);
    os << content0;
    return hasher_t::digest(os.str());
}

void c::write_info_all(const string& derived_from, const string& src_mim_file, const tokens_resolved_t& tokens_resolved, ostream& os) const {
    os << "\n";
    os << "Source:\n";
    {
        ind_t ind(os, "  ");
        if (!derived_from.empty()) {
            os << "    file: " << derived_from << '\n';
        }
        os << "mim file: " << src_mim_file << '\n';
    }
/*
    os << "Vertex:\n";
    {
        ind_t ind(os, "  ");
        os << "mim file: " << mim_file << '\n';
//        dump_mim_files(os);
    }
*/
/*
    os << "Auto params:\n";
    {
        ind_t ind(os, "  ");
        os << "'classname': '" << codefiles.classname << "'\n";
    }
*/
    if (!tokens_resolved.empty()) {
        os << "Params:" << '\n';
        ind_t ind(os, "  ");
        tokens_resolved.dump(os);
    }

/*
    params_t params = params__get_all();
    if (!params.empty()) {
        os << "Params:" << '\n';
        ind_t ind(os, "  ");
        params.dump(os);
    }

    /*
    const auto& params_srcs = vertex->params__collect_srcs();
    if (!params_srcs.empty()) {
        os << "Params:" << '\n';
        ind_t ind(os, "  ");
        params_srcs.dump(os);
    }
    */
}

void c::write_info(const string& derived_from, const string& mim_file, const tokens_resolved_t& tokens_resolved, ostream& os) const {
    os << "!! WARNING !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n";
    os << "Do not edit this file. Your changes would be overwritten on the next MIM run.\n";
    os << "This is a file generated by MIM from a template.\n";
    write_info_all(derived_from, mim_file, tokens_resolved, os);
    os << "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n";
}

void c::write_info_kickoff(const string& derived_from, const string& mim_file, const hash_t& hash, const tokens_resolved_t& tokens_resolved, ostream& os) const {
    os << "******************************************************************************\n";
    os << "EDITABLE FILE. Changes on this file will NOT be overwritten by MIM.\n";
    os << "The first version of this file was produced by MIM. You can edit it afterwards.\n";
    write_info_all(derived_from, mim_file, tokens_resolved, os);
    os << kickoff_code_hash_token << hash << " (change this hash to force a review)\n";
    os << "******************************************************************************\n";
}

void c::write_header(const string& derived_from, const string& mim_file, const hash_t& hash, const tokens_resolved_t& tokens_resolved, ostream& os) const {
    ind_t ind(os, prefix_header);
    if (codefiles.kickoff_code == 0) {
        write_info(derived_from, mim_file, tokens_resolved, os);
    }
    else {
        write_info_kickoff(derived_from, mim_file, hash, tokens_resolved, os);
    }
}

void c::rewrite_classname(string& classname) const {
    if (template_instance_vertex == nullptr) return;
    template_instance_vertex->rewrite_classname__instance(classname);
}

bool c::params__load(const string& filename) {
    return params.load(mim_dir() + '/' + filename);
}

pair<bool, string> c::params__get(const string& key) const {
    cout << "params__get '" << key << "' from:" << vertex_dir() << '\n';
    ind_t ind(cout, "  ");
    auto i = params.get(key);
    if (i.first) {
        cout << "returning '" << i.second << "' from:" << vertex_dir() << '\n';
        return i;
    }
    auto p = get_parent();
    if (p != nullptr) {
        auto x = p->params__get(key);
        if (x.first) return x;
    }
    if (template_instance_vertex == nullptr) return make_pair(false, "");
    return template_instance_vertex->params__get(key);
}

/*
params_t c::params__get_all() const {
    params_t o = params;
    auto p = get_parent();
    if (p != nullptr) {
        o.underride(p->params__get_all());
    }
    if (template_instance_vertex == nullptr) {
        return o;
    }
    o.underride(template_instance_vertex->params__get_all());
    return o;    
}
*/

token_resolution_t c::resolve_token(const string& token) const {
    auto i = params.get(token);
    if (i.first) {
        return token_resolution_t{0, i.second, mim_file};
    }
    auto p = get_parent();
    if (p != nullptr) {
        auto x = p->resolve_token(token);
        if (x.algo != -1) return x;
    }
    if (template_instance_vertex != nullptr) {
        return template_instance_vertex->resolve_token(*this, token);
    }
    return token_resolution_t{-1, "", ""};
}

void c::resolve_tokens(const filedef_t::tokens_t& used, filedef_t::tokens_resolved_t& resolved) const {
    for (auto& token: used) {
        auto r = resolved.find(token);
        if (r != resolved.end()) continue;
        auto x = resolve_token(token);
        if (x.algo == -1) continue;
        resolved.emplace(token, move(x));
    }
}

/*
#include <deque>

void c::set_classname(const string& classname) {
    cout << "set_classname " << classname << endl;
    deque<pair<c*, string>> line;
    line.push_back(make_pair(this, classname));
    while (!line.empty()) {
        auto o = line.front();
        line.pop_front();

        o.first->codefiles.classname = o.second;
        
        auto& deps = o.first->get_configure_deps();
        for (auto& child: deps.in) {
            auto o2 = dynamic_cast<c*>(child);
            if (o2 == nullptr) continue;
            string child_classname;
            if (o.second.empty()) {
                child_classname = o2->name;
            }
            else {
                if (o2->codefiles.same_classname) {
                    child_classname = o.second;
                }
                else {
                    child_classname = o.second + "__" + o2->name;
                }
            }
            line.push_back(make_pair(o2, child_classname));
        }
    }
}
*/

