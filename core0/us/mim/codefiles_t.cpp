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
#include <thread>

#include <us/gov/ko.h>

#include "codefiles_t.h"
#include "namespace_t.h"
#include "engine_t.h"

using namespace std;
using namespace mim;
using namespace us;

using c = mim::codefiles_t;

c::codefiles_t(namespace_t& parent): vertex(parent) {
}
    
c::codefiles_t(namespace_t& parent, const c& other): vertex(parent), classname(other.classname) { //, filename_tokens(other.filename_tokens) {
    for (auto& i: other) {
        auto o = i.second->clone();
        assert(o != nullptr);
        o->set_vertex(vertex);
        emplace(i.first, o);
    }

    kickoff_code = other.kickoff_code;
    kickoff_code_dir = other.kickoff_code_dir;
//cerr << "classname " << classname << '\n';
//cerr << "other.classname " << other.classname << '\n';
//    assert(classname.empty());
//    assert(other.classname.empty());
    classname_alg = other.classname_alg;
}

c::~codefiles_t() {
    clear();
}

void c::clear() {
    for (auto& i: *this) {
        delete i.second;
    }
}

void c::remove_header(string& buf) {
    auto p = buf.rfind(namespace_t::prefix_header);
    if (p == string::npos) return;
    p = buf.find('\n', p);
    if (p == string::npos) return;
    buf = buf.substr(p + 1, buf.size() - p - 1);
}

void c::make_generated(const string& filename, filedef_t& filedef) const {
    assert(filedef.is_verbatim());
    auto fqn = filedef.path + '/' + filename;
    auto r = engine_t::read_text_file_(fqn, filedef.content);
    if (is_ko(r)) {
        cerr << "KO 89714 can't load file " << fqn << '\n';
        assert(false);
        abort();
    }
    remove_header(filedef.content);
    filedef.hash = vertex.hash_content(filedef.content); 
    filedef.path = "";
    filedef.derived_from = fqn;
}


bool c::merge(codefiles_t& other, int op) { //op:0= me has precedence; op:1=other has precedence
    cout << "merging codefiles " << vertex.name << "<-" << other.vertex.name << " classname " << classname << "<-" << other.classname << " sz " << size() << " o:" << other.size() << " op=" << op << '\n';
    {
        ind_t ind(cout, "-----me> ");
        dump(cout);
    }
    {
        ind_t ind(cout, "--other> ");
        other.dump(cout);
    }

    if (other.kickoff_code == 1) {
        kickoff_code = other.kickoff_code;
    }
    if (!other.kickoff_code_dir.empty()) {
        kickoff_code_dir = other.kickoff_code_dir;
    }
    if (classname_alg != other.classname_alg) {
        if (classname_alg == not_set) {
            classname_alg = other.classname_alg;
        }
        else if (other.classname_alg == not_set) {
        }
        else {
            cerr << "KO 40030 classname_alg mismatch. " << classname_alg_str[classname_alg] << " vs " << classname_alg_str[other.classname_alg] << '\n';
            assert(false);
            abort();
        }        
    }
    //assert(classname.empty());
    //assert(other.classname.empty());
    {
        ind_t ind(cout, "    ");
        while (true) {
            auto lang = other.begin();
            if (lang == other.end()) break;
            auto i = find(lang->first);
            if (i == end()) {
                cout << "moving " << lang->first << " files\n";
                auto& o = emplace(*lang).first->second;
                o->set_vertex(vertex);
            }
            else {
                cout << "merging " << lang->first << " files\n";
                if (!i->second->merge(*lang->second, op)) {
                    assert(false);
                    return false;
                }
                delete lang->second;
            }
            other.erase(lang);
        }
    }
    
    assert(other.empty());
    {
        ind_t ind(cout, "--merge> ");
        dump(cout);
    }
    return true;
}

void c::dump(ostream& os) const {
    os << "classname: " << classname << '\n';
    os << "classname algorithm: " << classname_alg_str[classname_alg] << '\n';
    os << "kickoff_code: " << kickoff_code << (kickoff_code == 0 ? " [final code]" : " [customized code in kickoff_code_dir]") << '\n';
    os << "kickoff_code_dir: " << kickoff_code_dir << '\n';
    if (empty()) {
        os << "No subhomes.\n";
        return;
    }
    for (auto& i: *this) {
        i.second->dump(os);
    }
}

void c::add_java() {
    add("java");
}

void c::add_cpp() {
    add("c++");
}

void c::include_java(const string& dep) {
    if (!has_java()) {
        cerr << "KO 60598 java is not set up.\n";
        assert(false);
        abort();
    }
    find("java")->second->include(dep);
}

bool c::has_java() const {
    return find("java") != end();
}

bool c::has_cpp() const {
    return find("c++") != end();
}

lang_codefiles_t* c::find_lang_codefiles(const string& lang, const string& subhome) {
    auto i = find(lang);
    assert(i != end());
    if (subhome.empty()) return i->second;
    auto j = i->second->find(subhome);
    assert(j != i->second->end());
    return j->second;
}

void c::add(const string& lang) {
    auto r = emplace(lang, vertex.create_lang_codefiles(lang)).second;
    assert(r);
}

/*
void c::on_detach_vertex() {
    for (auto& i: *this) {
        i.second->on_detach_vertex();
    }
}

void c::on_attach_vertex(const namespace_t& parent_vertex) {
    //cout << "on_attach_vertex\n";
    for (auto& i: *this) {
        i.second->on_attach_vertex(parent_vertex);
    }
}
*/

bool c::gen() {
    for (auto& i: *this) {
        {
            ind_t ind(cout, " " + i.first + "> ");
            if (!i.second->gen()) {
                cerr << "gen failed\n";
                abort();
                return false;
            }
        }
        ind_t ind(cout, "dump> ");
        i.second->dump(cout);
    }
    return true;
}

void c::pre_configure() {
    cout << "pre_configure codefiles. sz " << size() << '\n';
    compute_classname();
    for (auto& i: *this) {
        ind_t ind(cout, i.first + ' ');
        i.second->pre_configure(); 
        i.second->dump(cout);
    }
}

bool c::configure() {
    cout << "configure codefiles. sz " << size() << '\n';
    {
        for (auto& i: *this) {
            ind_t ind(cout, i.first + ' ');
            if (!i.second->configure()) return false; 
            i.second->dump(cout);
        }
    }
    return true;
}

void c::fill_includes(set<string>& dest) const {
    for (auto& i: *this) {
        i.second->fill_includes(dest);
    }
}

void c::compute_classname() {
    //assert(classname.empty());
    if (!classname.empty()) {
//cerr << "XXXVVFDCXXXXX " << classname << endl;
        return;
    }
    if (vertex.name=="conf") {
cerr << "XXXVVFDCXXXXX " << classname << endl;
    }
    //string prev_classname = classname;
    if (classname_alg == not_set) {
        classname_alg = append_to_parent; //vertex_name;
    }
    switch (classname_alg) {
        case vertex_name: {
                classname = vertex.name;
            }
            break;
        case same_as_parent:
        case append_to_parent: {
                auto p = vertex.get_parent();
                assert(p != nullptr);
                p->codefiles.compute_classname();
                assert(!p->codefiles.classname.empty());
                if (classname_alg == same_as_parent) {
                    classname = p->codefiles.classname;
                }
                else {
                    classname = p->codefiles.classname + "__" + vertex.name;
                }
            }
            break;
    }
    auto classname0 = classname;
    if (vertex.name == "base") {
        int bxreak=0;
    }
    vertex.rewrite_classname(classname);
    cout << vertex.vertex_dir() << " compute classname: " << classname << " alg: " << classname_alg_str[classname_alg];
    if (classname0 != classname) {
        cout << " (rewrite of " << classname0 << ')';
    }
    //if (!prev_classname.empty()) {
    //    assert(prev_classname == classname);
    //}
    cout << '\n';
}

