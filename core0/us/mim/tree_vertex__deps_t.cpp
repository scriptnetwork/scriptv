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
#include <fstream>
#include <cassert>
#include <vector>
#include <limits>
#include <filesystem>

#include <us/gov/ko.h>

#include "tree_vertex__deps_t.h"
#include "sdk_vertex_t.h"
#include "template_instance_vertex_t.h"
#include "template_vertex_t.h"
#include "namespace_t.h"
#include "root_vertex_t.h"
#include "engine_t.h"

using namespace std;
using namespace us;
using namespace mim;

using c = mim::tree_vertex__deps_t;

map<c*, vector<c*>>* c::init_childmap{nullptr};

c::tree_vertex__deps_t(const string& name, const string& mim_file): b(name, mim_file), grid("configure_deps") {
}

c::tree_vertex__deps_t(const c& other): b(other), grid(other.grid) {
    cout << "* " << other.name << " copy constructor \n";
    {
        auto& srcdeps = other.get_configure_deps();
        ind_t ind(cout, "  ");
        cout << srcdeps.in.size() << " children: ";
        for (auto& i: srcdeps.in) {
            cout << i->name << ' ';
        }
        cout << '\n';
        for (auto& i: srcdeps.in) {
            auto tv = dynamic_cast<c*>(i);
            if (tv == nullptr) {
                cerr << "KO 68868 non tree vertex " << tv->name << '\n';
                assert(false);
                abort();
                continue;
            }
            auto child0 = tv->clone();
            assert(child0 != nullptr);
            auto child = dynamic_cast<c*>(child0);
            assert(child != nullptr);
            add(child);
        }
    }
}

c::~tree_vertex__deps_t() {
}

vertex_t* c::clone() const {
    return new c(*this);
}

bool c::merge(vertex_t* other_, int op) {
    assert(z_order() <= engine_t::instance().features.max_zorder);
    if (!b::merge(other_, op)) {
        cout << "!base. " << name << endl;
        return false;
    }
    auto* other = static_cast<c*>(other_);
    if (other == nullptr) {
        cerr << "KO 55040 type mismatch. " << vertex_dir() << '\n';
        abort();
    }
    if (other_ == this) {
        cout << "self-merge" << endl;
        return false; //after base class (serial)
    }
    if (!other_->is_enabled()) {
        cout << "merge with disabled vertex" << endl;
        return false; //after base class (serial)
    }
    cout << "merging " << name << " <-- " << other->name << '\n';
    while (true) {
        auto& odeps = other->get_configure_deps();
        if (odeps.in.empty()) break;
        auto i = odeps.in.begin();
        auto ov = static_cast<c*>(*i);
        assert(ov != nullptr);
        ov->detach();
        add_(*ov, op);
        cout << "merged/moved child " << ov->name << endl;
    }
    assert(grid == other->grid);
    return true;
}

bool c::pre_configure() {
    assert(z_order() <= engine_t::instance().features.max_zorder);
    cout << "-< tree_vertex " << name << ".\n";
    ind_t ind(cout, "  ");
    if (!is_enabled()) {
        cout << "ignoring vertex " << name << " because it's disabled.\n";
        return false;
    }
    return true;
}

bool c::configure() {
    assert(z_order() <= engine_t::instance().features.max_zorder);
    cout << "-< tree_vertex " << name << ".\n";
    ind_t ind(cout, "  ");
    if (!is_enabled()) {
        cout << "ignoring vertex " << name << " because it's disabled.\n";
        return false;
    }
    return true;
}

visitpath_t c::traverse__children() {
    float maxz = engine_t::instance().features.max_zorder;
    visitpath_t visitpath;
    if (z_order() > maxz) {
        cout << "excluded vertex " << name << " with z-order " << z_order() << '\n';
        return visitpath;
    }
    visitpath.reserve(256);
    auto& deps = get_configure_deps();
    for (auto& child: deps.in) {
        auto o = static_cast<c*>(child);
        assert (o != nullptr);
        if (o->z_order() > maxz) {
            cout << "excluded vertex " << o->name << " with z-order " << o->z_order() << '\n';
            continue;
        }
        visitpath.push_back(o);
    }
    return visitpath;
}

const_visitpath_t c::traverse__children() const {
    float maxz = engine_t::instance().features.max_zorder;
    const_visitpath_t visitpath;
    if (z_order() > maxz) {
        cout << "excluded vertex " << name << " with z-order " << z_order() << '\n';
        return visitpath;
    }
    visitpath.reserve(256);
    auto& deps = get_configure_deps();
    for (auto& child: deps.in) {
        auto o = static_cast<const c*>(child);
        assert (o != nullptr);
        if (o->z_order() > maxz) {
            cout << "excluded vertex " << o->name << " with z-order " << o->z_order() << '\n';
            continue;
        }
        visitpath.push_back(o);
    }
    return visitpath;
}

c* c::find_child(const string& findname) {
    auto& deps = get_configure_deps();
    for (auto& child: deps.in) {
        if (child->name == findname) return dynamic_cast<c*>(child);
    }
    return nullptr;
}

const c* c::find_child(const string& findname) const {
    auto& deps = get_configure_deps();
    for (auto& child: deps.in) {
        if (child->name == findname) return dynamic_cast<const c*>(child);
    }
    return nullptr;
}

const c* c::find_vertex2(const string& path) const {
//cout << vertex_dir() << "============find_vertex2 " << path << endl;
    if (path.empty()) return this;
    auto i = path.find('/');
    if (i == 0) {
        return find_root()->find_vertex2(path.substr(1));
    }
    if (i == string::npos) {
        auto& deps = get_configure_deps();
        for (auto& child: deps.in) {
            if (child->name == path) return dynamic_cast<const c*>(child);
        }
        return nullptr;
    }
    string p1, p2;
    p1 = path.substr(0, i);
    p2 = path.substr(i + 1);
/*
static int iii=0;
    if (iii == 0) {
    cout << p1 << " - " << p2 << endl;
    abort();
    }
    ++iii;
*/
    if (p1 == ".") {
        return find_vertex2(p2);
    }
    if (p1 == "..") {
        return get_parent()->find_vertex2(p2);
    }
    assert (!p1.empty());
    assert (!p2.empty());
    auto& deps = get_configure_deps();
    for (auto& child: deps.in) {
        if (child->name == p1) return dynamic_cast<const c*>(child)->find_vertex2(p2);
    }
    return nullptr;
}


#include <deque>
visitpath_t c::traverse__breath_first() {
    //return traverse__depth_first();

    float maxz = engine_t::instance().features.max_zorder;
    visitpath_t visitpath;
    if (z_order() > maxz) {
        cout << "excluded vertex " << name << " with z-order " << z_order() << '\n';
        return visitpath;
    }
    visitpath.reserve(256);
    deque<c*> line;
    line.push_back(this);
    while (!line.empty()) {
        c* o = line.front();
        line.pop_front();
        visitpath.push_back(o);
        auto& deps = o->get_configure_deps();
        for (auto& child: deps.in) {
            auto o2 = static_cast<c*>(child);
            assert (o2 != nullptr);
            if (o2->z_order() > maxz) {
                cout << "excluded vertex " << o2->name << " with z-order " << o2->z_order() << '\n';
                continue;
            }
            line.push_back(dynamic_cast<c*>(child));
        }
    }
    return visitpath;
}

const_visitpath_t c::traverse__breath_first() const {
    //return traverse__depth_first();
    
    float maxz = engine_t::instance().features.max_zorder;
    const_visitpath_t visitpath;
    if (z_order() > maxz) {
        cout << "excluded vertex " << name << " with z-order " << z_order() << '\n';
        return visitpath;
    }
    visitpath.reserve(256);
    deque<const c*> line;
    line.push_back(this);
    while (!line.empty()) {
        const c* o = line.front();
        line.pop_front();
        visitpath.push_back(o);
        auto& deps = o->get_configure_deps();
        for (auto& child: deps.in) {
            auto o2 = static_cast<const c*>(child);
            assert (o2 != nullptr);
            if (o2->z_order() > maxz) {
                cout << "excluded vertex " << o2->name << " with z-order " << o2->z_order() << '\n';
                continue;
            }
            line.push_back(dynamic_cast<const c*>(child));
        }
    }
    return visitpath;
}

#include <stack>
visitpath_t c::traverse__depth_first() {
    float maxz = engine_t::instance().features.max_zorder;
    visitpath_t visitpath;
    if (z_order() > maxz) {
        cout << "excluded vertex " << name << " with z-order " << z_order() << '\n';
        return visitpath;
    }
    visitpath.reserve(256);
    stack<c*> line;
    line.push(this);
    while (!line.empty()) {
        c* o = line.top();
        line.pop();
        visitpath.push_back(o);
        auto& deps = o->get_configure_deps();
        for (auto& child: deps.in) {
            auto o2 = static_cast<c*>(child);
            assert (o2 != nullptr);
            if (o2->z_order() > maxz) {
                cout << "excluded vertex " << o2->name << " with z-order " << o2->z_order() << '\n';
                continue;
            }
            line.push(o2);
        }
    }
    return visitpath;
}

const_visitpath_t c::traverse__depth_first() const {
    float maxz = engine_t::instance().features.max_zorder;
    const_visitpath_t visitpath;
    if (z_order() > maxz) {
        cout << "excluded vertex " << name << " with z-order " << z_order() << '\n';
        return visitpath;
    }
    visitpath.reserve(256);
    stack<const c*> line;
    line.push(this);
    while (!line.empty()) {
        auto o = line.top();
        line.pop();
        visitpath.push_back(o);
        auto& deps = o->get_configure_deps();
        for (auto& child: deps.in) {
            auto o2 = static_cast<const c*>(child);
            assert (o2 != nullptr);
            if (o2->z_order() > maxz) {
                cout << "excluded vertex " << o2->name << " with z-order " << o2->z_order() << '\n';
                continue;
            }
            line.push(o2);
        }
    }
    return visitpath;
}

visitpath_t c::traverse__leaves() {
    visitpath_t visitpath;
    visitpath.reserve(256);
    stack<c*> line;
    set<c*> uniq_parent;
    line.push(this);
    while (!line.empty()) {
        c* o = line.top();
        line.pop();
        auto& deps = o->get_configure_deps();
        if (deps.in.empty()) { //leaf
            visitpath.push_back(o);
            while (true) {
                o = o->get_parent();
                if (o == nullptr) break;
                if (uniq_parent.find(o) != uniq_parent.end()) {
                    break;
                }
                visitpath.push_back(o);
                uniq_parent.emplace(o);
            }
            continue;
        }
        for (auto& child: deps.in) {
            auto o2 = static_cast<c*>(child);
            assert (o2 != nullptr);
            line.push(o2);
        }
    }
    return visitpath;
}

edges_t& c::get_configure_deps() {
    return get_connections(grid);
}

const edges_t& c::get_configure_deps() const {
    return get_connections(grid);
}

void c::dump_(ostream& os) const {
    {
        ind_t ind(os, "  ");
        os << "-< tree_vertex.\n";
    }
    auto& deps = get_configure_deps();
    {
        ind_t ind(os, "    deps> ");
        deps.dump(os);
    }
    b::dump_(os);
    {
        int n{0};
        for (auto& i: deps.in) {
            os << "  child #" << n++ << ": \n";
            static_cast<c*>(i)->dump(os);
        }
    }
}

string c::conf_target_dir0(const string& lang) const {
    auto p = get_parent();
    auto n = static_cast<const c*>(p);
    if (n == nullptr) {
        return "";
    }
    string path = n->conf_target_dir0(lang); // depth + 1, os);
    if (!is_target_dir()) { //equivalent to !is_namespace(); a vertex that is not a namespace; only namespace type vertexes contribute to a namespace/file tree
        return path;
    }
    if (!path.empty()) {
        path += '/';
    }
    return path + name;
}

string c::conf_target_dir(const string& lang) const {
    return conf_target_dir0(lang);
}

c* c::get_parent() {
    auto& deps = get_configure_deps();
    if (deps.out.empty()) return nullptr;
    assert(deps.out.size() == 1);
    return static_cast<c*>(*deps.out.begin());
}

bool c::has_children() const {
    auto& deps = get_configure_deps();
    return !deps.in.empty();
}

const c* c::get_parent() const {
    auto& deps = get_configure_deps();
    if (deps.out.empty()) return nullptr;
    assert(deps.out.size() == 1);
    return static_cast<const c*>(*deps.out.begin());
}

void c::add(c* child) {
    engine_t::instance().own(child);
    if (init_childmap == nullptr) {
        init_childmap = new map<c*,vector<c*>>();
    }
    auto i = init_childmap->find(this);
    if (i == init_childmap->end()) {
        i = init_childmap->emplace(this, vector<c*>()).first;
    }
    i->second.push_back(child);
}

void c::dump_childmap(ostream& os) {
    if (init_childmap == nullptr) return;
    os << "childmap:\n";
    ind_t ind(os, "  ");
    for (auto& i: *init_childmap) {
        os << i.first << ' ' << i.first->vertex_dir() << '\n';
        ind_t ind(os, "  ");
        for (auto& j: i.second) {
            os << j << ' ' << j->name << '\n';
        }            
    }
}

void c::init() {
    if (init_childmap == nullptr) return;
    init_();
    assert (init_childmap != nullptr);
    if (!init_childmap->empty()) {
        cerr << "at vertex " << vertex_dir() << '\n';
        cerr << "KO 78878 init_childmap not empty\n";
        dump_childmap(cerr);
        cerr << "KO 78878 init_childmap not empty\n";
        abort();        
    }
    assert(init_childmap->empty());
    delete init_childmap;
    init_childmap = nullptr;
}

void c::init_() {
    assert(!gened_);
    assert(!inited_);
    inited_ = true;
    ind_t ind(cout, '/' + name);
    {
        auto i = init_childmap->find(this);
        if (i == init_childmap->end()) {
            cout << "  this is a leaf vertex.\n";
            return;
        }
        auto ch = i->second;
        {
            ind_t ind(cout, "  ");
            init_childmap->erase(i);
            cout << "connecting children\n";
            {
                ind_t ind(cout, "  ");
                for (auto& x: ch) {
                    #ifndef NDEBUG
                    {
                        auto& cdeps = x->get_configure_deps();
                        assert(cdeps.out.size() == 0);
                        assert(cdeps.in.size() == 0);
                    }
                    #endif
                    add_(*x);
                }
            }
        }
        {
            for (auto& x: ch) {
                x->init_();
            }
        }
    }
}


bool c::gen() {
    assert(!gened_);
    assert(inited_);
    assert(z_order() <= engine_t::instance().features.max_zorder);
    gened_ = true;
    if (!is_enabled()) {
        cout << "configure vertex. Ignored " << name << " because it's disabled.\n";
        return false;
    }
    return true;
}

c& c::add_(c& child) {
    return add_(child, 1);
}

c& c::add_(c& child, int merge_op) {
    cout << "add child " << this << ' ' << vertex_dir() << " <- " << &child << ' ' << child.vertex_dir() << '\n';
    cout << "          [" << mim_file << "]<-[" << child.mim_file << "]\n";
    ind_t ind(cout, "  ");
    assert(child.get_parent() == nullptr);
    //linked list/ tree branch
    //           [root parent]                       [child parent]                        [leaf child]
    // <-out(0)--[root parent]--in(1)-->   <-out(1)--[child parent]--in(1)-->   <--out(1)--[leaf child]--in(0)-->
    {
        auto& cdeps = child.get_configure_deps();
        assert(cdeps.out.empty());
        cdeps.out.emplace(this);
    }
    #ifndef NDEBUG
    {
        auto& cdeps = child.get_configure_deps();
        assert(cdeps.out.size() == 1);
        assert(*cdeps.out.begin() == this);
    }
    #endif
    {
        auto& deps = get_configure_deps();
        //cout << "do I have a child named " << child.name << endl;
        auto v = deps.in.find_by_name(child.name);
        if (v == nullptr) {
            cout << "move child " << &child << ' ' << child.name << '\n';
            deps.in.emplace(&child);
        }
        else {
            cout << "merging child " << this << ' ' << name << " <- " << &child << ' ' << child.name << '\n';
            ind_t ind(cout, "  ");
            v->merge(&child, merge_op);
        }
    }
    child.on_attach(*this);
    return child;
}

c& c::remove(c& child) {
    //linked list/ tree branch
    //           [root parent]                       [child parent]                        [leaf child]
    // <-out(0)--[root parent]--in(1)-->   <-out(1)--[child parent]--in(1)-->   <--out(1)--[leaf child]--in(0)-->
    {
        auto& deps = get_configure_deps();
        auto i = deps.in.find(&child);
        assert (i != deps.in.end());
        deps.in.erase(i);
    }
    {
        auto& childdeps = child.get_configure_deps();
        auto i = childdeps.out.find(this);
        assert (i != childdeps.out.end());
        childdeps.out.erase(i);
        child.on_detach();
    }
   return child;
}


void c::on_detach() {
}

void c::on_attach(const tree_vertex__deps_t&) {
}

void c::detach() {
    auto p = get_parent();
    if (p == nullptr) return;
    p->remove(*this);
    auto& deps = get_configure_deps();
    auto p2 = get_parent();
    assert (p2 == nullptr);
}

void c::remove_child(const string& name) {
    auto& deps = get_configure_deps();
    for (auto& child0: deps.in) {
        auto child = dynamic_cast<c*>(child0);
        assert(child != nullptr);
        if (name == child->name) {
            remove(*child);
            break;
        }
    }
}

void c::vertex_dir(const string& sep, ostream& os) const {
    auto p = get_parent();
    if (p == nullptr) {
        os << "?/" << name;
        return;
    }
    p->vertex_dir(sep, os);
    os << sep << name;
}

void c::vertex_dir(ostream& os) const {
    return vertex_dir("/", os);
}

string c::vertex_dir() const {
    ostringstream os;
    vertex_dir(os);
    return os.str();
}


const c* c::find_vertex_in(vector<string>& rpath) const {
    if (rpath.empty()) {
        return this; ////us + empty path = us
    }
    auto& findname = *rpath.rbegin();
    auto& deps = get_configure_deps();
    for (auto& child0: deps.in) {
        auto child = dynamic_cast<const c*>(child0);
        assert(child != nullptr);
        if (findname == child->name) {
            auto copy_rpath = rpath;
            rpath.erase(rpath.end() - 1);
            auto v = child->find_vertex_in(rpath);
            if (v != nullptr) { //found
                return v;
            }
            rpath = copy_rpath;
        }
    }
    return nullptr; //not found;
}

const root_vertex_t* c::root_vertex() const {
    const c* x = this;
    while(true) {
        auto y = dynamic_cast<const root_vertex_t*>(x);
        if (y != nullptr) return y;
        x = x->get_parent();
        if (x == nullptr) break;
    }
    assert(false);
    return nullptr;
}

const c* c::find_root() const {
    auto p = get_parent();
    if (p == nullptr) return this;
    return p->find_root();
}

#include <algorithm>
const c* c::find_vertex(const string& vpath) const {
    vector<string> reverse_path = engine_t::split_fqns(vpath);
    if (reverse_path.empty()) {
        return this; ////us + empty path = us
    }
    reverse(reverse_path.begin(), reverse_path.end()); //thatś why it is called reverse_path
    if (reverse_path.rbegin()->empty()) { //empty entry -> absolute path -> send over to root
        reverse_path.erase(reverse_path.end() - 1);
        auto root = find_root();
        return root->find_vertex_in(reverse_path);
    }
    //non-empty entry -> relative path. one of our children should be named path->rbegin()
    return find_vertex_in(reverse_path);
}

void c::traverse_gen() {
    assert(z_order() <= engine_t::instance().features.max_zorder);
    auto visitpath = traverse__breath_first();
    cout << "traverse_gen. " << name << ". visitpath of " << visitpath.size() << " vertexes\n";
    {
        ind_t ind(cout, "  ");
        for (auto& i: visitpath) {
            i->gen();
        }
    }
}

vector<template_vertex_t*> c::find_templates() {
    float maxz = engine_t::instance().features.max_zorder;
    vector<template_vertex_t*> visitpath;
    if (z_order() > maxz) {
        cout << "excluded vertex " << name << " with z-order " << z_order() << '\n';
        return visitpath;
    }
    visitpath.reserve(256);
    stack<c*> line;
    line.push(this);
    while (!line.empty()) {
        c* o = line.top();
        line.pop();
        auto x = dynamic_cast<template_vertex_t*>(o);
        if (x != nullptr) {
            visitpath.push_back(x);
            continue; //skip template_instance_children
        }
        auto& deps = o->get_configure_deps();
        for (auto& child: deps.in) {
            auto o2 = static_cast<c*>(child);
            assert (o2 != nullptr);
            if (o2->z_order() > maxz) {
                cout << "excluded vertex " << o2->name << " with z-order " << o2->z_order() << '\n';
                continue;
            }
            line.push(o2);
        }
    }
    return visitpath;
}

vector<template_instance_vertex_t*> c::find_template_instances() {
    float maxz = engine_t::instance().features.max_zorder;
    vector<template_instance_vertex_t*> visitpath;
    if (z_order() > maxz) {
        cout << "excluded vertex " << name << " with z-order " << z_order() << '\n';
        return visitpath;
    }
    visitpath.reserve(256);
    stack<c*> line;
    line.push(this);
    while (!line.empty()) {
        c* o = line.top();
        line.pop();
        auto x = dynamic_cast<template_instance_vertex_t*>(o);
        if (x != nullptr) {
            visitpath.push_back(x);
            continue; //skip template_instance_children
        }
        auto& deps = o->get_configure_deps();
        for (auto& child: deps.in) {
            auto o2 = static_cast<c*>(child);
            assert (o2 != nullptr);
            if (o2->z_order() > maxz) {
                cout << "excluded vertex " << o2->name << " with z-order " << o2->z_order() << '\n';
                continue;
            }
            line.push(o2);
        }
    }
    return visitpath;
}

void c::underride_classname_alg(codefiles_t::classname_alg_t alg) {
    auto visitpath = traverse__breath_first();
    cout << "underride_classname_alg. " << name << ". visitpath of " << visitpath.size() << " vertexes\n";
    {
        ind_t ind(cout, "  ");
        for (auto& i: visitpath) {
            auto o = dynamic_cast<namespace_t*>(i);
            if (o == nullptr) continue;
            if (o->codefiles.classname_alg != codefiles_t::not_set) continue;
            o->codefiles.classname_alg = alg;
        }
    }
}

void c::touch_topmost_templates() {
    auto instances = find_template_instances();
    for (auto& i: instances) {
        i->set_kickoff_code_dir();
    }
}

void c::inflate_templates(vector<namespace_t*>& allinstances) {
    auto instances = find_template_instances();
    int n{0};
    cout << "Begin of: Inflate_templates. " << vertex_dir() << ". num instances: " << instances.size() << '\n';
    for (auto& i: instances) {
        cout << "#" << n++ << ": template_instance " << i->vertex_dir() << '\n';
    }
    n = 0;
    for (auto& i: instances) {
        cout << "#" << n++ << " inflate_templates " << i->vertex_dir() << '\n';
        ind_t ind(cout, i->name + "> ");        
        i->inflate(allinstances);
        i->customize_after_being_instantiated_by_a_template__all();

    }
    cout << "End of: Inflate_templates. " << vertex_dir() << ".\n";
}

void c::configure_templates() {
    vector<template_vertex_t*> templates = find_templates();
    cout << "configuring " << templates.size() << " template vertexes\n";
    for (auto& i: templates) {
        cout << "configuring template vertex " << i->name << "\n";
        i->configure();
    }
    #ifndef NDEBUG
    {
        vector<template_vertex_t*> templates = find_templates();
        assert(templates.empty());
    }
    #endif
}

void c::print(visitpath_t& visitpath) const {
    cout << "visitpath of " << visitpath.size() << " vertexes\n";
    {
        for (auto& i: visitpath) {
            auto ns = dynamic_cast<const namespace_t*>(i);
            if (ns != nullptr) {
                cout << ns->codefiles.kickoff_code << ' ';
            }
            cout << string(i->depth() * 2, '.') << i->name << '\n';
        }    
    }
}

void c::customize_after_being_instantiated_by_a_template() {
}

void c::customize_after_being_instantiated_by_a_template__all() {
    cout << "customize_after_being_instantiated_by_a_template__all\n";
    visitpath_t visitpath;
    {
        ind_t ind(cout, "  ");
        visitpath = traverse__depth_first();
        cout << "[1=kickoff] ... name \n";
        print(visitpath);
    }
    for (auto& i: visitpath) {
        cout << i->vertex_dir() << ":\n";
        ind_t ind(cout, "  ");
        i->customize_after_being_instantiated_by_a_template();
    }
}

/*
void c::pre_configure0_vertexes() {
    visitpath_t visitpath;
    cout << "vertex processing order; depth_first: \n";
    {
        ind_t ind(cout, "  ");
        visitpath = traverse__depth_first();
        cout << "[1=kickoff] ... name \n";
        print(visitpath);
    }
    for (auto& i: visitpath) {
        auto instance = dynamic_cast<namespace_t*>(i);
        if (instance == nullptr) continue;
        //if (instance->codefiles.classname_alg == codefiles_t::not_set) {
        //    instance->codefiles.classname_alg = codefiles_t::vertex_name;
        //}
        cout << instance->vertex_dir() << ":\n";
        ind_t ind(cout, "  ");
        
        instance->codefiles.compute_classname();
    }
}
*/

void c::pre_configure_vertexes() {
    visitpath_t visitpath;
    cout << "vertex processing order; leaves_first: \n";
    {
        ind_t ind(cout, "  ");
        visitpath = traverse__leaves();
        cout << "[1=kickoff] ... name \n";
        print(visitpath);
    }
    for (auto& i: visitpath) {
//        ind_t ind(cout, string(i->depth() * 2, ' '));
        cout << i->vertex_dir() << ":\n";
        ind_t ind(cout, "  ");
        i->pre_configure();
    }
}

void c::configure_vertexes() {
    visitpath_t visitpath;
    cout << "depth_first: \n";
    {
        ind_t ind(cout, "  ");
        visitpath = traverse__depth_first();
        {
            ind_t ind(cout, "configure list> ");
            print(visitpath);
        }
    }
    cout << "configure stage:\n";
    for (auto& i: visitpath) {
        assert(dynamic_cast<template_instance_vertex_t*>(i) == nullptr); //all instances must have been converted to the base vertex class of its corresponding template
        assert(dynamic_cast<template_vertex_t*>(i) == nullptr); //all templates must have been detached from the tree
        ind_t ind(cout, string(i->depth() * 2, ' '));
        i->configure();
    }
}

int c::depth() const {
    auto p = get_parent();
    if (p == nullptr) return 0;
    return p->depth() + 1;
}


//void c::check_review(const track_data_t& track_data) {
/*
void c::check_review() {
    cout << "check_review:\n";
    if (engine_t::instance().dryrun) {
        cout << "\n[dryrun] ==>>>>>>>>>>>>>>>>>>>>>>>>> Skipping code review.\n";
        engine_t::instance().skipped_code_review = true;
        return;
    } 
    vector<string> changedfiles;

    auto v = traverse__depth_first();
    for (auto& i: v) {
        auto o = dynamic_cast<namespace_t*>(i);
        if (o == nullptr) continue;
        o->review(changedfiles);
    }
    engine_t::instance().review_templates(changedfiles);
    if (!changedfiles.empty()) {
        cout << "The following files have been updated:\n";
        for (auto& i: changedfiles) {
            cout << "  " << i << '\n';
        }
        //system("make clean");
        //cout << "type !-1 to run MIM again\n";
        //exit(0);
    }
        //codefiles.check_review();
}
*/
/*
void c::set_kickoff_code_dir() {
    auto visitpath = traverse__breath_first();
    for (auto& i: visitpath) {
        auto v = dynamic_cast<namespace_t*>(i);
        if (v == nullptr) continue;
        auto p = i->get_parent();
        assert(p != nullptr);
        auto r = dynamic_cast<root_vertex_t*>(p);
        if (r != nullptr) {
            v->kickoff_code_dir = "core0/" + v->name;
        }
        else {
            auto pns = dynamic_cast<namespace_t*>(p);
            assert (pns != nullptr);
            assert (!pns->kickoff_code_dir.empty());
            v->kickoff_code_dir = pns->kickoff_code_dir + '/' + v->name;
        }
        //cout << "XXXXXXXXX " << v->vertex_dir() << " " << v->kickoff_code_dir << endl; 
    }
}
*/
void c::traverse_configure() {
    if (!gened_) {
        cerr << "KO 78699 vertex not gone through gen pass. " << name << '\n';
        assert(false);
        abort();
    }
    cout << "templates\n";
    {
        ind_t ind(cout, "[0] ");
        configure_templates();
    }
    cout << "touch_topmost_templates\n";
    {
        ind_t ind(cout, "[1] ");
        touch_topmost_templates();
    }
    cout << "inflate templates\n";
    {
        ind_t ind(cout, "[2] ");
        vector<namespace_t*> allinstances;
        inflate_templates(allinstances);
        for (auto& i: allinstances) {
            i->template_instance_vertex->templ_spec->customize_produced_vertex(/* *i->template_instance_vertex,*/ *i);
        }
    }
    /*
    cout << "pre-configure0\n";
    {
        ind_t ind(cout, "[3] ");
        pre_configure0_vertexes();
    }
    */
    cout << "pre-configure\n";
    {
        ind_t ind(cout, "[4] ");
        pre_configure_vertexes();
    }
    cout << "configure vertexes\n";
    {
        ind_t ind(cout, "[5] ");
        configure_vertexes();
    }
}

float c::z_order() const {
    auto p = get_parent();
    if (p == nullptr) { //detached vertex
        return 0;//give a inclusive z-order
    }
    auto z = p->z_order();
    assert(z >= 0);
    return z;
}

/*
void track_data_t::dump(const string& pfx, ostream& os) const {
    dump0(pfx, os);
    for (auto& i: child) {
        i.dump(pfx + "    ", os);
    }
}

void track_data_t::dump0(const string& pfx, ostream& os) const {
    os << pfx << "\n";
    if (template_vertex == nullptr) {
        os << "root\n";
        return;
    }
    os << pfx << "output instance:           O->[]\n";
    os << pfx << "    vertex_dir: " << instance->vertex_dir() << '\n';
    os << pfx << "    mim_srcdir: " << instance->codefiles._mim_srcdir << '\n';
    os << pfx << "Instance def:              []->[T]\n";
    os << pfx << "    mim_file: " << template_instance_vertex->mim_file << '\n';
    os << pfx << "    template id: " << template_instance_vertex->template_id << '\n';
    os << pfx << "    need_review: " << template_instance_vertex->needs_review() << '\n';
    os << pfx << "Template def:              [T]\n";
    os << pfx << "    mim_file: " << template_vertex->mim_file << '\n';
    os << pfx << "    id: " << template_vertex->id << '\n';
    os << pfx << "    need_review: " << template_vertex->need_review << '\n';
    os << '\n';
}

vector<const track_data_t*> track_data_t::traverse__depth_first() const {
    vector<const track_data_t*> visitpath;
    visitpath.reserve(256);
    stack<const track_data_t*> line;
    line.push(this);
    while (!line.empty()) {
        const track_data_t* o = line.top();
        line.pop();
        visitpath.push_back(o);
        for (auto& x: o->child) {
            line.push(&x);
        }
    }
    return visitpath;
}

bool track_data_t::needs_review() const {
    if (template_instance_vertex == nullptr) {
        return false;
    }
    return template_instance_vertex->needs_review();
}

bool track_data_t::template_flagged_review() const {
    if (template_vertex == nullptr) {
        return false;
    }
    return template_vertex->need_review;
}
*/

