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
#include "template_vertex_t.h"
#include "template_instance_vertex_t.h"
#include "tree_vertex__deps_t.h"
#include "namespace_t.h"
#include "engine_t.h"

using namespace std;
using namespace mim;

using c = mim::template_vertex_t;

c::template_vertex_t(const string& name, const string& mim_file, const string& id, params_t&& params_): b(name, mim_file), id(id) {
    //params.add(params_);
}

c::template_vertex_t(const string& name, const string& mim_file, const string& id): b(name, mim_file), id(id) {
}

c::template_vertex_t(const c& other): b(other), id(other.id), instances(instances) {
}

vertex_t* c::clone() const {
    assert(false);
    cout << "KO 78699" << endl;
    abort();
    return new c(*this);
}

bool c::gen() {
    return b::gen();
}

bool c::configure() {
    cout << "\nconfigure template vertex\n";
    assert(!id.empty());
    engine_t::instance().register_template(id, *this);
    detach();
    return true;
}

namespace_t* c::create_instance(namespace_t& parent, template_instance_vertex_t* instance_vertex) const {
    cout << "create_instance\n";
    ind_t ind(cout, "  ");
    assert(instance_vertex->get_parent() == nullptr); //template instance should be detached
    assert(instance_vertex->produced_vertex == nullptr);
    assert(tree_vertex__deps_t::init_childmap == nullptr);
    auto x = new b(*this);
    instance_vertex->produced_vertex = x;
    x->init();
    x->template_instance_vertex = instance_vertex;
    x->name = instance_vertex->name;
    parent.add_(*x);
    cout << "merge\n";
    {
        ind_t ind(cout, "  ");
        b instance_vertex_as_b(*instance_vertex);
        instance_vertex_as_b.init();
        x->merge(&instance_vertex_as_b, 1); //parent codefiles inherits codefiles from the templ_instance vertex
    }
/*
    customize_produced_vertex(*instance_vertex, *x);
    x->customize_after_being_instantiated_by_a_template__all();
*/
    engine_t::instance().own(x);
    instances.emplace_back(instance_vertex);
    return x;
}

void c::customize_produced_vertex(namespace_t& instance) const {
}

token_resolution_t c::resolve_token(const namespace_t& instance, const string& token) const {
    return token_resolutions::unsolved;
}

void c::dump_(ostream& os) const {
    {
        ind_t ind(os, "  ");
        os << "<> template def vertex.\n";
        {
            ind_t ind(os, "  ");
            os << "template_id: " << id << '\n';
        }
    }
    b::dump_(os);
    {
        ind_t ind(os, "    ");
        os << instances.size() << " instances\n";
        {
            ind_t ind(os, "  ");
            for (auto& i: instances) {
                os << i->vertex_dir() << '\n';
            }
        }    
    }
}

