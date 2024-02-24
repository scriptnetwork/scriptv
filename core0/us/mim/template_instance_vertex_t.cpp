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
#include "template_instance_vertex_t.h"
#include "engine_t.h"
#include "template_vertex_t.h"
#include "tree_vertex__deps_t.h"

using namespace std;
using namespace mim;

using c = mim::template_instance_vertex_t;

c::template_instance_vertex_t(const string& name, const string& mim_file, const string& template_id, params_t&& params_): b(name, mim_file), template_id(template_id) {
//    params_srcs.emplace_back(params_src_t(mim_file, move(params)));
    params.add(params_);
    assert(!template_id.empty());
    codefiles.kickoff_code = 1;
}

c::template_instance_vertex_t(const c& other): b(other), template_id(other.template_id), templ_spec(other.templ_spec), custom_params(other.custom_params) {
    //codefiles.kickoff_code = other.codefiles.kickoff_code;
}

vertex_t* c::clone() const {
    return new c(*this);
}

bool c::merge(vertex_t* other_, int op) {
    if (!b::merge(other_, op)) return false;

    auto* other = dynamic_cast<c*>(other_);
    if (other == nullptr) {
        return true;
//        cerr << "KO 55042 type mismatch. " << vertex_dir() << '\n';
//        abort();
    }
    assert(template_id == other->template_id);
    return true;
}

void c::include_java(const string& dep) {
    codefiles.include_java(dep);
}

/*
namespace {

    void replace_string(string& buffer, const string& search, const string& replace) {
        size_t pos = 0;
        while ((pos = buffer.find(search, pos)) != std::string::npos) {
             buffer.replace(pos, search.length(), replace);
             pos += replace.length();
        }
    }

}
*/

pair<bool, string> c::params__get(const string& key) const {
    auto i = b::params__get(key);
    if (i.first) return i;
    assert(templ_spec != nullptr);
    return templ_spec->params__get(key);
}

/*
params_t c::get_all() const {
    params_t o = b::params__get_all();
    assert(templ_spec != nullptr);
    o.underride(templ_spec->params__get_all());
    return o;
}
*/

token_resolution_t c::resolve_token(const namespace_t& instance, const string& token) const {
    return templ_spec->resolve_token(instance, token);
}

void c::inflate(vector<namespace_t*>& allinstances) {
    cout << "inflate template " << template_id << " into vertex name " << name << '\n';
    ind_t ind(cout, "  ");
    assert(templ_spec == nullptr);
    //dump(cout);
    assert(!template_id.empty());
    auto i = engine_t::instance().templates.find(template_id);
    if (i == engine_t::instance().templates.end()) {
        cerr << "KO 89302 template not found: " << template_id << '\n';
        abort();
    }
    assert(i != engine_t::instance().templates.end());
    templ_spec = i->second;
    assert(templ_spec != nullptr);
    cout << "detach template_instance_vertex\n";
    auto parent =  dynamic_cast<namespace_t*>(get_parent());
    detach();
    auto x = templ_spec->create_instance(*parent, this);
/*
    templ_spec->customize_produced_vertex(*this, *x);
    x->customize_after_being_instantiated_by_a_template__all();
*/
    assert(x != nullptr);
    allinstances.emplace_back(x);
    cout << "inflate nested template instances\n";
    {
        ind_t ind(cout, "  ");
        x->inflate_templates(allinstances);
    }

    {
        ind_t ind(cout, "inflated vertex> ");
        x->dump(cout);
    }
}

void c::dump_(ostream& os) const {
    {
        ind_t ind(os, "  ");
        os << "<!> template instance vertex.";
        if (get_parent() == nullptr) os << " (detached)";
        os << '\n';
        {
            ind_t ind(os, "  ");
            os << "template_id: " << template_id << '\n';
        }
    }
    b::dump_(os);
    if (templ_spec != nullptr) {
        ind_t ind(os, "  ref <>: ");
        templ_spec->dump(os);
    }
}

bool c::gen() {
    cout << "<!> template_instance vertex >>>> " << name << " <<<<\n";
    ind_t ind(cout, "  ");
    return b::gen();
}

bool c::configure() {
    cerr << "KO 89799 this is a meta-vertex. cannot call configure.\n";
    assert(false);
    abort();
    return false;
}

void c::pre_configure(namespace_t&) const {
}

void c::rewrite_classname__instance(string&) const {
}

