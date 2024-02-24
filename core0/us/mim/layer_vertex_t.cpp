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
#include "layer_vertex_t.h"
#include "engine_t.h"


using namespace std;
using namespace mim;

using c = mim::layer_vertex_t;

c::layer_vertex_t(const string& name, const string& mim_file): b(name, mim_file) {

}


c::layer_vertex_t(const c& other): b(other) {
}

vertex_t* c::clone() const {
    cerr << "clone layer not impl" << endl;
    assert(false);
    abort();
    //return new c(*this);
}

bool c::merge(vertex_t* other_, int op) {
    if (!b::merge(other_, op)) {
        cout << "!! base. " << name << endl;
        return false;
    }
    auto* other = dynamic_cast<c*>(other_);
    if (other == nullptr) {
        cerr << "KO 55041 type mismatch. " << vertex_dir() << '\n';
        abort();
    }
    return true;
}

void c::vertex_dir(const string& sep, ostream& os) const {
    auto p = get_parent();
    if (p == nullptr) {
        os << name;
        return;
    }
    p->vertex_dir(sep, os);
    os << sep << name;
}

string c::conf_target_dir(const string& lang) const {
    return "";
}

void c::gen_merge_configure(float maxz) {
    cout << "All layers (not cut by z-order):" << '\n';
    {
        ind_t ind(cout, "MIM> ");
        dump(cout);
    }
    cout << "gen_merge_configure. layer z-order " << z_order() << " max: " << maxz << '\n';
    if (z_order() > maxz) return;
    cout << "gen. " << name << ". z-order: " << z_order() << '\n';
    {
        ind_t ind(cout, "MIM-gen: ");
        traverse_gen();
    }
    assert(gened_);
    cout << "merge. " << name << ". z-order: " << z_order() << '\n';
    root_vertex_t* m;
    {
        ind_t ind(cout, "MIM-merge-layers: ");
        m = merge(1);
    }
    if (m == nullptr) {
        cerr << "KO 85764 merge produced null tree. Check layer max z-order (-maxz)\n";
        return;
    }
    {
        ind_t ind(cout, "MIM-merged-layers> ");
        m->dump(cout);
    }
    cout << "configure merged vertex. z-order: " << m->z_order() << '\n';
    m->gened_ = true; //skip gen pass for 
    {
        ind_t ind(cout, "MIM-conf: ");
        m->traverse_configure();
    }
    {
        ind_t ind(cout, "output> ");
        m->dump(cout);
    }
    delete m;
    cout << "output_dir: " << engine_t::instance().output_dir << '\n';
}

//void c::merge_travel(root_vertex_t* src, root_vertex_t* dst, int op) {
//}

void c::list_layers(const string& pfx, float maxz, ostream& os) const {
    if (z_order() > maxz) return;
    os << pfx << "+: " << name << ' ' << " z:" << z_order() << '\n';
    auto& deps = get_configure_deps();
    for (auto& child: deps.in) {
        root_vertex_t* root;
        auto layer = dynamic_cast<c*>(child);
        if (layer != nullptr) {
            layer->list_layers(pfx + "  ", maxz, os);
        }
        else {
            auto root = dynamic_cast<root_vertex_t*>(child);
            assert (root != nullptr);
            if (root->z_order() <= maxz) {
                os << pfx << "  *: " << root->name << ' ' << " z:" << root->z_order() << '\n';
            }
        }
    }
}

root_vertex_t* c::merge(int op) {
    auto maxz = engine_t::instance().features.max_zorder;
    assert(z_order() <= maxz);
    auto m = new merged_root_vertex_t(name, z_order()); //"core0"
    auto& deps = get_configure_deps();
    map<float, vertex_t*> zbuf;
    for (auto& child: deps.in) {
        auto layer = dynamic_cast<c*>(child);
        if (layer != nullptr) {
            if (layer->z_order() <= maxz) {
                zbuf.emplace(layer->z_order(), layer);
            }
        }
        auto root = dynamic_cast<root_vertex_t*>(child);
        if (root != nullptr) {
            if (root->z_order() <= maxz) {
                zbuf.emplace(root->z_order(), root);
            }
        }
    }
    cout << "name=" << name << " zbuf of " << zbuf.size() << " vertexes:\n";
    int mcount = 0;
    string ms = "";
    for (auto& child: zbuf) {
        root_vertex_t* root;
        auto layer = dynamic_cast<c*>(child.second);
        if (layer != nullptr) {
            root = layer->merge(op); //new vertex
            cout << "returning from " << layer->name << " back to " << name << '\n';
            {
                ostringstream os;
                os << ms << (ms.empty() ? "" : "+") << layer->z_order();
                ms = os.str();
            }
        }
        else {
            root = dynamic_cast<root_vertex_t*>(child.second);
            {
                ostringstream os;
                os << ms << (ms.empty() ? "" : "+") << root->z_order();
                ms = os.str();
            }
        }
        if (root == nullptr) {
            cout << "root->name " << "not included\n";
            continue;
        }
        cout << "Merging " << m->name << " (z-order " << m->z_order() << ") + " << child.second->name << " (z-order " << child.first << ")\n";
        {
            ostringstream os;
            os << "dst" << mcount << "> ";
            ind_t ind(cout, os.str());
            m->dump(cout);
        }
        {
            ostringstream os;
            os << "+src" << mcount << "> ";
            ind_t ind(cout, os.str());
            root->dump(cout);
        }
        cout << "-------merge-------\n";
        {
            ind_t ind(cout, "  ");
            m->merge(root, op);

            //merge_travel(root, m, op); 
        }
        cout << "-----/-merge-------\n";
        if (layer != nullptr) {
            delete root; //no longer needed
        }
        ++mcount;
        {
            ostringstream os;
            os << m->z_order() << '=' << ms << "> ";
            ind_t ind(cout, os.str());
            m->dump(cout);
        }
    }
    if (mcount == 0) {
        cout << "returning null\n";
        delete m;
        m = nullptr;
    }
    cout << "end merge\n";
    return m;
}

void c::dump_(ostream& os) const {
    {
        ind_t ind(os, "  ");
        os << "|| layer vertex.\n";
        {
            ind_t ind(os, "  ");
            os << "title: " << title << '\n';
            os << "z_order: " << z_order() << '\n';
        }
    }
    b::dump_(os);
}

