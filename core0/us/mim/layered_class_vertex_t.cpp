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

#include "layered_class_vertex_t.h"
#include "menu_vertex_t.h"

using namespace std;
using namespace mim;

using c = mim::layered_class_vertex_t;

c::layered_class_vertex_t(const string& name, const string& layer_name, const string& mim_file): b(name, mim_file) {
    layers.emplace_back(layerdef_t{layer_name, mim_file, mim_dir()});
    codefiles.kickoff_code = 1;

}

c::layered_class_vertex_t(const string& name, const vector<string>& layer_names, const string& mim_file): b(name, mim_file) {
    for (auto& i: layer_names) {
        layers.emplace_back(layerdef_t{i, mim_file, mim_dir()});
    }
    codefiles.kickoff_code = 1;

}

c::layered_class_vertex_t(const c& other): b(other), deepest_baseclass(other.deepest_baseclass), subhome(other.subhome), layers(other.layers) {
}

vertex_t* c::clone() const {
    return new c(*this);
}

void c::customize_after_being_instantiated_by_a_template() {
    assert(layers.size() == 1);
    layers.begin()->kickoff_dir = kickoff_code_dir();
}

string c::layerize(const string& name0, const string& layer_name) {
    string name = name0;
    string suff;
    if (name0.size() >= 2) {
        auto i = name0.rfind("_t");
        if (i == name0.size() - 2) {
            name = name0.substr(0, i);
            suff = "_t";
        }
    }
    ostringstream os;
    os << name << layer_name << suff;
    return os.str();
}

bool c::merge(vertex_t* other_, int op) {
    if (!b::merge(other_, op)) return false;
    auto* other = dynamic_cast<c*>(other_);
    if (other == nullptr) {
        cerr << "KO 55042 type mismatch. " << vertex_dir() << '\n';
        abort();
    }
    layers.reserve(layers.size() + other->layers.size());
    for (auto& i: other->layers) {
        layers.emplace_back(i);
    }
    other->layers.clear();
    if (subhome != other->subhome) {
        if (!other->subhome.empty()) { //if empty it bcs it is just not specified
            cerr << "subhomes mismatch. " << subhome << " vs " << other->subhome << '\n';
        }
    }
    assert(other->deepest_baseclass.empty());
    return true;
}

string c::package() const {
    if (subhome.empty()) return package_ns;
    return package_ns + "." + subhome;
}

string c::layer_class_content(layers_t::const_iterator z) const {
    assert(!layers.empty());
    string classname;
    string extends;
    {
        string baseclass;
        if (z == layers.end()) {
            baseclass = layerize(codefiles.classname, layers.rbegin()->name);
            classname = codefiles.classname;
         }
        else if (z == layers.begin()) {
            assert(!deepest_baseclass.empty());
            baseclass = deepest_baseclass;
            classname = layerize(codefiles.classname, z->name);
        }
        else {
            auto z0 = z;
            --z0;
            baseclass = layerize(codefiles.classname, z0->name);
            classname = layerize(codefiles.classname, z->name);
        }
        if (!baseclass.empty()) {
            extends = " extends " + baseclass;
        }
    }

    ostringstream os;
    os <<
        "package " << package() << ";\n" \
        "\n" \
        "public " << (z == layers.end() ? "final " : "") << "class " << classname << extends << " {\n" \
        "\n" \
        "    private static void log(final String line) {                              //--strip\n" \
        "        " << (subhome.empty() ? subhome : package_ns + ".") << "CFG.log_" << (subhome.empty() ? "android" : subhome) << "(\"" << classname << ": \" + line);          //--strip\n" \
        "    }                                                                         //--strip\n" \
        "\n" \
        "    public " << classname << "() {\n" \
        "        super();\n" \
        "    }\n" \
        "}\n";
    return os.str();
}

token_resolution_t c::resolve_token(const string& token) const {
    auto r = b::resolve_token(token);
    if (r.solved()) return r;
    return token_resolutions::unsolved;
}

bool c::pre_configure() {
    cout << "|<| layered class vertex >>>> " << name << " <<<<\n";
    {
        ind_t ind(cout, "  ");
        codefiles.compute_classname();
        assert(!codefiles.classname.empty());
        lang_codefiles_t* o = codefiles.find_lang_codefiles("java", subhome);
        string kickoff_subd = "/java";
        if (!subhome.empty()) {
            kickoff_subd += string("/") + subhome;
        }
        cout << "wrapper class\n";
        {
            ind_t ind(cout, "  ");
            filedef_t filedef;
            filedef.content = layer_class_content(layers.end());
            filedef.derived_from = "";
            filedef.mim_file = layers.rbegin()->mim_file;
            filedef.kickoff_dir = layers.rbegin()->kickoff_dir + kickoff_subd;
            filedef.hash = hash_content(filedef.content);
            o->add_file(codefiles.classname + ".java", move(filedef));
        }

        for (auto z = layers.cbegin(); z != layers.cend(); ++z) {
            auto cn = layerize(codefiles.classname, z->name);
            cout << "mezzanine class " << cn << '\n';
            ind_t ind(cout, "  ");
            filedef_t filedef;
            //filedef.path = z->kickoff_dir;
            filedef.content = layer_class_content(z);
            filedef.mim_file = z->mim_file;
            filedef.kickoff_dir = z->kickoff_dir + kickoff_subd;
            filedef.derived_from = "";
            filedef.hash = hash_content(filedef.content);
            o->add_file(cn + ".java", move(filedef));
        }
    }
    return b::pre_configure();
}

void c::dump_(ostream& os) const {
    {
        ind_t ind(os, "  ");
        os << "|<| layered-class vertex.\n";
        {
            ind_t ind(os, "  ");
            os << "layers (name, kickoff_dir):\n";
            {
                ind_t ind(os, "  ");
                for (auto& i: layers) {
                    cout << '\'' << i.name << "' " << i.kickoff_dir << '\n';
                }
            }
            os << "subhome: " << subhome << '\n';
            os << "deepest_baseclass: " << deepest_baseclass << '\n';
        }
    }
    b::dump_(os);
}

