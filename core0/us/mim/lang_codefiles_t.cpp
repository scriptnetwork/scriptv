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
#include <set>
#include <sstream>
#include <cassert>
#include <vector>
#include <sstream>
#include <filesystem>
#include <thread>

#include <us/gov/ko.h>

#include "lang_codefiles_t.h"
#include "menu_vertex_t.h"
#include "menuspec_t.h"
#include "template_instance_vertex_t.h"

#include "engine_t.h"

using namespace std;
using namespace mim;
using mim::cout;
using us::ko;
using us::is_ko;

using c = mim::lang_codefiles_t;

token_resolution_t token_resolutions::unsolved{-1, "", ""};

c::lang_codefiles_t(namespace_t& vertex, const string& name): vertex(&vertex), name(name) {

}

c::lang_codefiles_t(const lang_codefiles_t& other) {
    files = other.files;
    includes = other.includes;
    name = other.name;
    subhome = other.subhome;
    vertex = nullptr;
    for (auto& i: other) {
        emplace(i.first, i.second->clone());
    }
}

c::~lang_codefiles_t() {
    for (auto& i: *this) {
        delete i.second;
    }
}

bool c::file_exists(const string& f) { //cfg0
    struct stat s;
    if (stat(f.c_str(), &s) == 0) return S_ISREG(s.st_mode);
    return false;
}

void c::trim(string& s) { //copied from cfg0.cpp
    if (s.empty()) return;
    char* b = s.data();
    char* e = b + s.size();
    while(b < e) {
        if (*b > ' ') break;
        ++b;
    }
    --e;
    while(b <= e) {
        if (*e > ' ') break;
        --e;
    }
    ++e;
    s = string(b, e-b);
}

void c::load_filenames_(const string& dir, ostream& os) {
    os << "load_filenames. files @ " << dir << '\n';
    {
        ind_t ind(cout, "  ");
        namespace fs = std::filesystem;
        //files.clear();
        if (!engine_t::instance().dir_exists(dir)) {
            return;
        }
        for (auto& p: fs::directory_iterator(dir)) {
            if (!is_regular_file(p.path())) continue;
            os << "verbatim file " << p.path().filename() << " " << fs::path(p).parent_path() << '\n';
            string fn = p.path().filename();
            if (fn.find(".ref") == (fn.size() - 4)) {
                cerr << "KO 66959 .ref file found. " << fn << '\n';
                cerr << "hint: clear ref files (used to launch meld):\nfind . -name \"*.ref\" -delete\n";
                assert(false);
                abort();
            }
            filedef_t filedef;
            filedef.path = fs::path(p).parent_path();
            filedef.mim_file = vertex->mim_file;
            add_file(fn, move(filedef));
        }
    }
}

void c::set_vertex(namespace_t& v) {
    vertex = &v;
    for (auto& i: *this) {
        i.second->set_vertex(v);
    }
}

void c::load_filenames_(ostream& os) {
    load_filenames_(home(), os);
}

pair<ko, pair<string, string>> c::split_fqn(string fqn) { //adapted from cfg0.cpp
    trim(fqn);
    size_t pos = fqn.find(' ');
    if (pos != string::npos) {
        auto r = "KO 90182 input contains spaces.";
        return make_pair(r, make_pair("", ""));
    }
    if (fqn.empty()) {
        auto r = "KO 90183 Empty input.";
        return make_pair(r, make_pair("", ""));
    }
    if (*fqn.begin() == '/') {
        auto r ="KO 90184 Absolute path.";
        return make_pair(r, make_pair("", ""));
    }
    char cstr[fqn.size() + 1];
    char* p = fqn.data();
    char* tp = cstr;
    *tp = *p;
    ++tp;
    ++p;
    pos = 0;
    size_t j = 1;
    for (size_t i = 1; i < fqn.size(); ++i, ++p) {
        if (*(tp - 1) == '/' && *p == '/') continue;
        *tp = *p;
        if (*tp == '/') pos = j;
        ++tp;
        ++j;
    }
    if (*(tp - 1) == '/' && pos > 0) --tp; /// remove last slash
    *tp = '\0';
    fqn = cstr;
    string path = fqn.substr(0, pos);
    if (path.empty()) path = "/";
    ++pos;
    if (pos >= fqn.size()) {
        return make_pair(ok, make_pair(path, ""));
    }
    string name = fqn.substr(pos);
    return make_pair(ok, make_pair(path, name));
}

string c::conf_target_dir() const {
    if (subhome.empty()) return vertex->conf_target_dir(name);
    return vertex->conf_target_dir(name) + '/' + subhome;
}

string c::kickoff_code_dir() const {
    string input = vertex->kickoff_code_dir();
    assert(!input.empty());
    string s = input + "/" + name;
    if (subhome.empty()) return s;
    return s + '/' + subhome;
}

string c::lang_home() const {
    assert(vertex != nullptr);
    return vertex->vertex_dir() + '/' + name;
}

string c::home() const {
    if (subhome.empty()) return lang_home();
    return lang_home() + '/' + subhome;
}

bool c::merge(lang_codefiles_t& other, int op) {
    cout << "=merge lang_codefiles=================== " << other.files.size() << " files\n";
    while (true) {
        if (other.files.empty()) break;
        auto o = other.files.begin();
        add_file(o->first, move(o->second));
        other.files.erase(o);
    }
    while (true) {
        if (other.includes.empty()) break;
        auto i = other.includes.begin();
        auto m = includes.find(*i);
        if (m != includes.end()) {
            includes.erase(m);
        }
        cout << "+I file " << *i << '\n';
        includes.emplace(*i);
        other.includes.erase(i);
    }
    vector<string> other_todel;
    for (auto& oi: other) {
        auto m = find(oi.first);
        if (m == end()) {
            auto& o = emplace(oi).first->second; //->second;
            o->set_vertex(*vertex);
            other_todel.emplace_back(oi.first);
        }
        else {
            auto r = m->second->merge(*oi.second, op);
            assert(r);
        }
    }
    for (auto& i: other_todel) {
        other.erase(i);
    }
    return true;
}

bool c::anylang() const {
    if (!files.empty()) return true;
    if (!includes.empty()) return true;
    return false;
}

void c::dump0(ostream& os) const {
    ind_t ind(os, "[" + name + '/' + subhome + "] ");
    if (!anylang()) {
        os << "no lang items.\n";
        return;
    }
    if (!includes.empty()) {
        os << "includes:\n";
        ind_t ind(cout, "  ");
        for (auto& i: includes) {
            os << "#" << i << '\n';
        }
    }
    else {
        os << "no includes.\n";
    }
    os << "files:\n";
    {
        ind_t ind(cout, "  ");
        files.dump(os);
    }
}

void c::dump(ostream& os) const {
    dump0(os);
    for (auto& i: *this) {
        i.second->dump(os);
    }
}

bool c::gen() {
    {
        ind_t ind(cout, " [" + subhome + "] ");
        //if (engine_t::instance().dryrun) cout << pfx << "WARNING: this is a dry run. No files are touched.\n";
        auto h = home();
        assert(*h.begin() != '?');
        {
            ostringstream cmd;
            cmd << "mkdir -p " << home();
            engine_t::instance().systemx(cmd.str());
        }
        cout << "load files\n";
        {
            ind_t ind(cout, "  ");
            load_filenames_(cout);
        }
    }
    cout << size() << " sub codefiles\n";
    for (auto& i: *this) {
        //cout << pfx << " sub codefiles [" << i.first << "]:\n";
        i.second->gen();
    }
    return true;
}

namespace {
    void fail(const string& msg) {
        cerr << msg << '\n';
        abort();
    }
}

hash_t c::extract_hash(const string& filename) {
    string buf;
    auto r = engine_t::read_text_file_(filename, buf);
    if (is_ko(r)) {
        cerr << "KO 81704 can't load file " << filename << '\n';
        assert(false);
        abort();
    }
    auto i = buf.find(namespace_t::kickoff_code_hash_token);
    if (i == string::npos) return hash_t::zero_;
    i += strlen(namespace_t::kickoff_code_hash_token);
    if (i >= buf.size()) fail("KO 51002");
    auto j = buf.find('\n', i);
    if (j == string::npos) fail("KO 51003");
    string value = buf.substr(i, j - i);
    //cout << "token: " << token << endl;
    if (value.empty()) fail("KO 51004");
    if (value.find('\n') != string::npos) fail("KO 51005");
    {
        auto i = value.find(' '); 
        if (i != string::npos) {
            value = value.substr(0, i);
        }
    }
    hash_t ans;
    ans = value;
    return ans;
}

void c::load_kickoff_files() {
    if (vertex->codefiles.kickoff_code == 0) return;
    auto kickoff_dir = kickoff_code_dir();
    ind_t ind(cout, "  ");
    cout << "loading customized kickoff files from " << kickoff_dir << '\n';
    {
        ind_t ind(cout, "  ");
        load_filenames_(kickoff_dir, cout);
    }
}

void c::write_kickoff_files(const params_t& p) {
    if (vertex->codefiles.kickoff_code == 0) return;
    cout << "converting remaining generated files into verbatim, and write them to disk for user customization.\n";
    for (auto& i: files) {
        if (i.second.is_verbatim()) {
            continue;
        }
        ind_t ind(cout, "  ");
        assert(i.second.is_generated());
        cout << "found generated file " << i.first << ": " << "converting generated file into verbatim (kickoff file).\n";
        cout << "  kickoff_dir: " << i.second.kickoff_dir << '\n';
        //save stuff
        auto generated_filedef = i.second; //used to write/rebase kickoff file
        if (generated_filedef.kickoff_dir.empty()) {
            generated_filedef.kickoff_dir = kickoff_code_dir();
        }
        assert(generated_filedef.hash.is_not_zero());
        assert(i.second.path.empty());
        cout << "convert to verbatim\n";
        i.second.path = generated_filedef.kickoff_dir;
        i.second.hash = hash_t::zero_;
        //i.second.mim_file = vertex->mim_file;
        i.second.content = "";
        i.second.derived_from = "";
        if (!engine_t::instance().clean_mode()) {
            cout << "write/rebase kickoff file\n";
            {
                ostringstream os;
                vertex->write_header(generated_filedef.derived_from, generated_filedef.mim_file, generated_filedef.hash, generated_filedef.tokens__resolved, os);
                os << generated_filedef.content;
                if (!engine_t::instance().write_kickoff_file(i.second.path, i.first, os.str())) {
                    fix_conflict__generated__verbatim(i.first, generated_filedef, i.second);
                }
            }
        }
        cout << "inflate kickoff file (in case it contains additional tokens added over the initial kickoff file)\n";
        gen_file(i.first, i.second, p);
    } 
}

void c::pre_configure() {
    cout << "pre_configure lang_codefiles " << name << ' ' << subhome << '\n';
    assert(vertex->codefiles.kickoff_code != -1);

    params_t p;
    {
        set<string> includeset;
        vertex->fill_includes(includeset);
        ostringstream sincludes;
        write_include_code(includeset, sincludes);
        p.emplace("include_lines", sincludes.str());
    }

    //load_kickoff_files();
    inflate(p);
    write_kickoff_files(p);
    
    for (auto& i: *this) {
        i.second->pre_configure();
    }
}

bool c::configure() {
    cout << "configure lang_codefiles\n";
    {
        auto b = vertex->codefiles.kickoff_code;
        vertex->codefiles.kickoff_code = 0;
        for (auto& i: files) {
            if (i.second.is_generated()) {
                ostringstream os;
                vertex->write_header(i.second.derived_from, vertex->mim_file, i.second.hash, i.second.tokens__resolved, os);
                os << i.second.content;
                i.second.content = os.str();
            }
            engine_t::instance().produce_file(i, conf_target_dir(), vertex->overwrite_mode());
        }
        vertex->codefiles.kickoff_code = b;
    }
    for (auto& i: *this) {
        i.second->configure();
    }
    return true;
}

namespace {
    void log(const string&) {}
}

void c::include(const string& dep) {
    includes.emplace(dep);
}


void c::fill_includes(set<string>& dest) const {
    cout << "fill_includes. lang_codefiles. " << name << '/' << subhome << " include lines=" << includes.size() << '\n'; 
    dest.insert(includes.begin(), includes.end());
    for (auto& i: *this) {
        i.second->fill_includes(dest);
    }
}

void c::fix_conflict__generated__verbatim(const string& filename, const filedef_t& generated, const filedef_t& verbatim) {
    cout << "fix_conflict__generated__verbatim\n";
    ind_t ind(cout, "  ");
    {
        ind_t ind(cout, "verbatim> ");
        verbatim.dump(cout);
    }
    {
        ind_t ind(cout, "generated> ");
        generated.dump(cout);
    }
    if (!generated.kickoff_dir.empty()) {
        assert(generated.kickoff_dir == verbatim.path);
    }
    //cout << "found verbatim file " << j->second << '/' << j->first << "
    auto kickoff_version_disk = extract_hash(verbatim.path + '/' + filename);
    auto& kickoff_version_mem = generated.hash;
    cout << "hashes. disk: " << kickoff_version_disk << " mem: " << kickoff_version_mem << '\n';
    cout << "vertex->codefiles.kickoff_code " << vertex->codefiles.kickoff_code << '\n';
    assert(kickoff_version_mem.is_not_zero());
    if (vertex->codefiles.kickoff_code == 0) {
        cout << "KO 91787 A file is overriding a generated file but vertex->codefiles.kickoff_code is not set in " << vertex->mim_file << '\n'; 
        assert(false);
        abort();
    }
    bool forcediff = engine_t::instance().review;
    if (kickoff_version_disk != kickoff_version_mem || kickoff_version_disk.is_zero() || forcediff) {
        if (engine_t::instance().dev_mode()) {
            auto ref = verbatim.path + '/' + filename + ".ref";
            {
                ofstream os(ref);
                vertex->write_header(generated.derived_from, generated.mim_file, kickoff_version_mem, generated.tokens__resolved, os);
                os << generated.content;
            }
            {
                ostringstream cmd;
                cmd << "chmod -w " << ref;
                system(cmd.str().c_str());
            }
            ostringstream meld;
            meld << "meld -L Ref " << ref << ' ' << verbatim.path << '/' << filename;
            cout << meld.str() << '\n';

            system(meld.str().c_str());
            system((string("rm ") + ref).c_str());

            kickoff_version_disk = extract_hash(verbatim.path + '/' + filename);
            cout << "hashes. disk: " << kickoff_version_disk << " mem: " << kickoff_version_mem << '\n';
            if (kickoff_version_disk != kickoff_version_mem || kickoff_version_disk.is_zero()) {
                cerr << "File has not been fixed (hash still differs). Aborting MIM.\n";
                abort();
            }
            this_thread::sleep_for(500ms);
        }
        else {
            if (engine_t::instance().cbs_mode()) {
                engine_t::instance().warnings.emplace_back(verbatim.path + '/' + filename + " needs review,");
            }
        }
    }
}

void c::add_file(const string& filename, filedef_t&& new_filedef) {
    cout << "+ add_file " << filename << '\n';
    ind_t ind(cout, "  ");
    {
        ind_t ind(cout, "new_filedef> ");
        new_filedef.dump(cout);
    }
    auto i = files.find(filename);
    if (i == files.end()) {
        cout << "new file.\n";
//        assert(!filedef.mim_file.empty());
        if (new_filedef.is_generated()) {
            assert(new_filedef.hash.is_not_zero());
            assert(new_filedef.path.empty());
            assert(!new_filedef.content.empty());
        }
        else {
            assert(new_filedef.hash.is_zero());
            assert(!new_filedef.path.empty());
            assert(new_filedef.content.empty());
        }
        files.emplace(filename, move(new_filedef));
        return;
    }
    cout << "found existing file with same name:\n";
    filedef_t& existing_filedef = i->second;
    if (existing_filedef.is_verbatim()) {
        if (new_filedef.is_verbatim()) {
            cout << "verbatim->verbatim\n";
            cout << "Existing File: " << filename << ' '; existing_filedef.dump(cout);
            cout << "New File: " << filename << ' '; new_filedef.dump(cout);
            cout << "Existing file is dismissed, overriden with the newer file.\n";
            files.erase(i);
            files.emplace(filename, move(new_filedef));
            return;               
        }
        assert(new_filedef.is_generated());
        cout << "generated->verbatim. adding generated file " << new_filedef.path << '/' << filename << " after a verbatim one. Merging into a verbatim file \n";
        cout << "Existing File: " << i->first << ' '; existing_filedef.dump(cout);
        cout << "New File: " << filename << ' '; new_filedef.dump(cout);
        //fix_conflict__generated__verbatim(filename, new_filedef, existing_filedef);
//        new_filedef.mim_file = existing_filedef.mim_file;
//        new_filedef.mim_file = existing_filedef.mim_file;
        files.erase(i);
        files.emplace(filename, move(new_filedef));
        return;               
    }
    assert(existing_filedef.is_generated());
    if (new_filedef.is_verbatim()) {
        cout << "verbatim->generated. adding verbatim file " << new_filedef.path << '/' << filename << " after a generated one. Merging into a verbatim file.\n";
        cout << "Existing File: " << filename << ' '; existing_filedef.dump(cout);
        cout << "New File: " << filename << ' '; new_filedef.dump(cout);
        //fix_conflict__generated__verbatim(filename, existing_filedef, new_filedef);
        files.erase(i);
        files.emplace(filename, move(new_filedef));
        return;
    }
    assert(new_filedef.is_generated());
    cout << "generated->generated\n";
    cout << "Existing File: " << filename << ' '; existing_filedef.dump(cout);
    cout << "New File: " << filename << ' '; new_filedef.dump(cout);
    cout << "KO 22920 TODO: Resolve\n";
    abort();               
}

void c::find_tokens(const string& buf, const string& sepl, const string& sepr, tokens_t& o) const {
    int i = 0;
    while (true) {
        i = buf.find(sepl, i);
        if (i == string::npos) {
            break;
        }
        i += sepl.size();
        if (i >= buf.size()) fail("KO 54002");
        auto j = buf.find(sepr, i);
        if (j == string::npos) {
            cerr << buf.substr(i) << endl;
            fail("KO 54003");
        }
        //cout << "i: " << i << " j:" << j << endl;

        string token = buf.substr(i, j - i);
        //cout << "token: " << token << endl;
        if (token.empty()) fail("KO 54004");
        if (token.find('\n') != string::npos) fail("KO 54005");
        if (token.find(' ') != string::npos) fail("KO 54006");
        o.emplace(token);
        i = j + sepr.size();
    }
}

namespace {
    string indspc_(const string& s) {
        auto p = s.find_first_not_of(" ");
        if (p == string::npos) return "";
        if (p == 0) return "";
        return string(p, ' ');
    }

    int multiline(const string& s) {
        auto p = s.find('\n');
        if (p == string::npos) return 0;
        auto p2 = s.find('\n', p + 1);
        if (p2 == string::npos) return 1;
        return 2;
    }

    string payload_wrap(const string& tokenname, const string& payload) {
        if (payload.empty()) return "";
        ostringstream os;
        int n = multiline(payload);
        switch (n) {
            case 0:
                os << payload << " //MIM token '" << tokenname << "'\n";
                break;
            case 1:
                assert(payload[payload.size() - 1] == '\n');
                os << payload.substr(0, payload.size() - 1)  << " //MIM token '" << tokenname << "'\n";
                break;
            case 2:
                auto indspc = indspc_(payload);
                os << indspc << "//MIM begin token '" << tokenname << "'\n";
                os << payload;
                assert(payload[payload.size() - 1] == '\n');
                os << indspc << "//MIM end token '" << tokenname << "'\n";
                break;
        }
        return os.str();
    }    
}

bool c::expand__line_generator(string& buf, const string& tokenname, const string& token, const set<string>& names) const {
    auto i = buf.find(token);
    if (i == string::npos) return false;
    auto j = i + token.size();
    auto i0 = i;
    while (true) {
        if (i0 == 0) break;
        if (buf[i0 - 1] == '\n') break;
        --i0;
    }
    auto j1 = j;
    while (true) {
        if (j1 == buf.size()) break;
        if (buf[j1] == '\n') {
            ++j1;
            break;
        }
        ++j1;
    }
    string prefix = buf.substr(i0, i - i0);
    string suffix = buf.substr(j, j1 - j);
    ostringstream os;
    for (auto& i: names) {
        os << prefix << i << suffix << '\n';
    }
    buf.replace(i0, j1 - i0, payload_wrap(tokenname, os.str()));
    return true;
}

bool c::expand__line_subst(string& buf, const string& tokenname, const string& token, const string& payload) const {
    auto i = buf.find(token);
    if (i == string::npos) return false;
    auto j = i + token.size();
    auto i0 = i;
    while (true) {
        if (i0 == 0) break;
        if (buf[i0 - 1] == '\n') break;
        --i0;
    }
    auto j1 = j;
    while (true) {
        if (j1 == buf.size()) break;
        if (buf[j1] == '\n') {
            ++j1;
            break;
        }
        ++j1;
    }
    buf.replace(i0, j1 - i0, payload_wrap(tokenname, payload));
    return true;
}

bool c::expand__token_subst(string& buf, const string& tokenname, const string& token, const string& payload) const {
    auto i = buf.find(token);
    if (i == string::npos) return false;
    auto j = i + token.size();
    buf.replace(i, j - i, payload);
    return true;
}

bool c::expand_content(string& buf, const string& tokenname, const string& token, const token_resolution_t& resolution) const {
    switch (resolution.algo) {
        case 0:
            return expand__token_subst(buf, tokenname, token, resolution.payload);
            break;
        case 1:
            return expand__line_subst(buf, tokenname, token, resolution.payload);
            break;
        case 2:
            return expand__line_generator(buf, tokenname, token, resolution.payload2);
            break;
        default:
            assert(false);
            return false;
    }
}

void c::expand_content(string& buf, const string& sepl, const string& sepr, const filedef_t::tokens_resolved_t& tokens_resolved) const {
    for (auto& i: tokens_resolved) {
        string token = sepl + i.first + sepr;
        while (expand_content(buf, i.first, token, i.second)) {}
    }
}

void c::resolve_tokens(const tokens_t& used, tokens_resolved_t& resolved, const params_t p) const {
    if (used.empty()) return;
    if (used.find("delete") != used.end()) {
        resolved.emplace("delete", token_resolution_t{1, "", ""});
    }
    if (used.find("classname") != used.end()) {
        string classname = vertex->codefiles.classname;
        if (classname.empty()) {
            classname = vertex->name;
        }
        resolved.emplace("classname", token_resolution_t{0, classname, ""});
    }
    if (used.find("include") != used.end()) {
        string classname = vertex->codefiles.classname;
        if (classname.empty()) {
            classname = vertex->name;
        }
        resolved.emplace("include", token_resolution_t{1, p.find("include_lines")->second, ""});
    }
    vertex->resolve_tokens(used, resolved);
}

int c::check_resolution(const tokens_t& used, const tokens_resolved_t& resolved) const {
    int fail{0};
    for (auto& token: used) {
        auto x = resolved.find(token);
        if (x == resolved.end()) {
            cerr << "KO 30191 token has not been resolved. " << token << '\n';
            ++fail;
        }
    }
    return fail;
}

void c::gen_file(const string& filename, filedef_t& filedef, const params_t& p) {
    cout << "gen_file " << filename << '\n';
    assert(filename.find('[') == string::npos);
    assert(vertex->codefiles.kickoff_code == 1);
    ind_t ind(cout, "  ");
    {
        ind_t ind(cout, "--before> ");
        filedef.dump(cout);
    }
    {
        ind_t ind(cout, "    ");

        string fqn;
        string buf;
        if (filedef.is_verbatim()) {
            assert(!filedef.path.empty());
            fqn = filedef.path + '/' + filename;
            auto r = engine_t::read_text_file_(fqn, buf);
            if (is_ko(r)) {
                cerr << "KO 89704 can't load file " << fqn << '\n';
                assert(false);
                abort();
            }
            vertex->codefiles.remove_header(buf);
        }
        else {
            buf = filedef.content;
            assert(filedef.path.empty());
            fqn = filedef.kickoff_dir + '/' + filename;
        }
        string buf0 = buf;
        assert(!fqn.empty());
        while(true) {
            auto b0 = buf;
            find_tokens(buf, "##", "##", filedef.tokens__used);
            resolve_tokens(filedef.tokens__used, filedef.tokens__resolved, p);
            expand_content(buf, "##", "##", filedef.tokens__resolved);
            if (buf == b0) break;
        }
        auto fail = check_resolution(filedef.tokens__used, filedef.tokens__resolved); //are all tokens resolved?
        if (fail > 0) {
            cerr << "!!!!!!!!!!!!!!!!!!!!!!!!!!!\n";
            vertex->write_info_all(fqn, filedef.mim_file, filedef.tokens__resolved, cerr);
            cerr << fail << " undefined parameters:\n";
            cerr << "!!!!!!!!!!!!!!!!!!!!!!!!!!!\n";
            abort();
        }
        if (buf == buf0) {
            cout << "Content has not changed in this file.\n";
            return;
        }
        filedef.path = "";
        filedef.hash = vertex->hash_content(buf); 
        filedef.content = buf;
        filedef.derived_from = fqn;
    }
    {
        ind_t ind(cout, "--after> ");
        filedef.dump(cout);
    }
    return;
}

void c::gen_file(pair<string, filedef_t>& entry, const params_t& p) {
    cout << "gen_file " << entry.first << '\n';
    ind_t ind(cout, "  ");
    {
        ind_t ind(cout, "--before> ");
        cout << "filename: " << entry.first << '\n';
        entry.second.dump(cout);
    }
    {
        ind_t ind(cout, "    ");

        string fqn;
        string buf;
        if (entry.second.is_verbatim()) {
            assert(!entry.second.path.empty());
            fqn = entry.second.path + '/' + entry.first;
            auto r = engine_t::read_text_file_(fqn, buf);
            if (is_ko(r)) {
                cerr << "KO 89704 can't load file " << fqn << '\n';
                assert(false);
                abort();
            }
            vertex->codefiles.remove_header(buf);
        }
        else {
            buf = entry.second.content;
            assert(entry.second.path.empty());
            fqn = entry.second.kickoff_dir + '/' + entry.first;
        }
        string filename = entry.first;
        string buf0 = buf;
        if (vertex->codefiles.kickoff_code == 1) {
            assert(!fqn.empty());
        }
        assert(entry.second.tokens__resolved.empty());
        while(true) {
            auto b0 = buf;
            find_tokens(buf, "##", "##", entry.second.tokens__used);
            resolve_tokens(entry.second.tokens__used, entry.second.tokens__resolved, p);
            expand_content(buf, "##", "##", entry.second.tokens__resolved);
            if (buf == b0) break;
        }
        find_tokens(filename, "[", "]", entry.second.tokens__used);
        expand_content(filename, "[", "]", entry.second.tokens__resolved);
        auto fail = check_resolution(entry.second.tokens__used, entry.second.tokens__resolved); //are all tokens resolved?
        if (fail > 0) {
            cerr << "!!!!!!!!!!!!!!!!!!!!!!!!!!!\n";
            vertex->write_info_all(fqn, vertex->mim_file, entry.second.tokens__resolved, cerr);
            cerr << fail << " undefined parameters:\n";
            cerr << "!!!!!!!!!!!!!!!!!!!!!!!!!!!\n";
            abort();
        }
        if (buf == buf0 && filename == entry.first) {
            cout << "Nothing has changed in this file.\n";
            return;
        }
        entry.first = filename;
        entry.second.path = "";
        entry.second.hash = vertex->hash_content(buf); 
        entry.second.content = buf;
        entry.second.derived_from = fqn;
    }
    {
        ind_t ind(cout, "--after> ");
        cout << "filename: " << entry.first << '\n';
        entry.second.dump(cout);
    }
    return;
}

void c::gen_files(const params_t& p) {
    files_t f = files;
    files.clear();
    while(!f.empty()) {
        auto i = f.begin();
        pair<string, filedef_t> d = *i;
        f.erase(i);
        gen_file(d, p);
        auto x = f.find(d.first); // if filename was a template, the final filename could already exist in f 
        if (x != f.end()) {
            //assert(x->second.is_verbatim()); //it can be a kickoff file; replace with the generated one; later it will be rebased to keep manual changes
            f.erase(x);
        }        
        add_file(d.first, move(d.second));
    }
}

void c::inflate(const params_t& p) {
    cout << "inflate subhome " << subhome << '\n';
    {
        ind_t ind(cout, "  [" + subhome + ']');
        {
            ind_t ind(cout, "----before inflate> ");
            dump0(cout);
        }
        {
            ind_t ind(cout, "      ");
            gen_files(p);
        }    
        {
            ind_t ind(cout, "----after inflate> ");
            dump0(cout);
        }
    }
}

void files_t::dump(ostream& os) const {
    int f{0};
    int g{0};
    for (auto& i: *this) {
        os << "* " << i.first << '\n';
        {
            ind_t ind(cout, "  ");
            i.second.dump(os);        
        }
        if (i.second.is_generated()) {
            ++g;
        }
        else {
            ++f;
        }
    }
    os << f << " verbatim files; " << g << " generated files.\n";
}

void filedef_t::dump(ostream& os) const {
    if (is_generated()) {
        os << "{gen } [" << content.size() << " bytes. hash: " << hash << "]\n";
    }
    else {
        os << "{disk} [path " << path << "]\n";
    }
    {
        ind_t ind(cout, "       ");
        if (!kickoff_dir.empty()) {
            os << "[kickoff_dir " << kickoff_dir << "]\n";
        }
        if (!mim_file.empty()) {
            os << "[mim_file " << mim_file << "]\n";
        }
    }
}

string token_resolution_t::algostr() const {
    switch(algo) {
        case -1: return "unsolved";
        case 0: return "token__subst";
        case 1: return "line__subst";
        case 2: return "line_gen__set";
    }
    return "error";
}

void token_resolution_t::dump(ostream& os) const {
    if (algo == 2) {
        if (payload2.empty()) {
            os << "''";
        }        
        else {
            os << "set: '";
            os << *payload2.begin();
            os << '\'';
            if (payload2.size() > 1) {
                os << "  [" << payload2.size() - 1 << " more]";
            }
        }
    }
    else {
        auto p = payload.find('\n');
        if (p != string::npos) {
            auto l = payload.substr(0, p);
            engine_t::trim(l);
            if (l.size() > 20) l = l.substr(0, 20);
            l += "...";
            os << '\'' << l << '\'';
        }
        else {
            os << '\'' << payload << '\'';
        }
    }
    if (!mim_file.empty()) {
        os << " @ " << mim_file; 
    }
    os << '\n';
}

void filedef_t::tokens_resolved_t::dump(ostream& os) const {
    for (auto& i: *this) {
        os << '\'' << i.first << "': ";
        i.second.dump(os);
    }
}

void filedef_t::tokens_t::dump(ostream& os) const {
    for (auto& i: *this) {
        os << i << '\n';
    }
}

