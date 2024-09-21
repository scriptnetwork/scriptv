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
#include "engine_t.h"
#include <iostream>
#include <map>
#include <iostream>
#include <sstream>
#include <cassert>
#include <algorithm>
#include <vector>

#include <filesystem>
#include <sys/stat.h>
#include <sys/types.h>

#include <us/gov/ko.h>
#include <us/gov/stacktrace.h>

#include "mim.h"
#define mimns //TODO remove dependencies in mim.h and delete macro
#include "core0/mim.h"

using namespace std;
using namespace us;
using namespace mim;
using c = mim::engine_t;

c* c::_instance{nullptr};

namespace {

    void test_splitns() {
        {
            auto r = engine_t::split_fqns("");
            assert(r.empty());
        }
        {
            auto r = engine_t::split_fqns("::");
            assert(r.size() == 1);
            assert(r.begin()->empty());
        }
        {
            auto r = engine_t::split_fqns("::a");
            assert(r.size() == 2);
            assert(r.begin()->empty());
            assert(*r.rbegin() == "a");
        }
        {
            auto r = engine_t::split_fqns("a");
            assert(r.size() == 1);
            assert(*r.begin() == "a");
        }
        {
            auto r = engine_t::split_fqns("a::");
            assert(r.size() == 1);
            assert(*r.begin() == "a");
        }
    }
}

c& c::instance() {
    if (_instance == nullptr) {
        _instance = new c(); //_instance
        test_splitns();
    }
    return *_instance;
}

void c::delete_instance() {
    delete _instance;
    _instance = nullptr;
}

c::engine_t() {
    cfg = srctool.load_vars("cfg");
}

c::~engine_t() {
    for (auto& i:owned) {
        delete i;
    }
}

void c::init(float maxz, const string& output_dir_) {
    features.max_zorder = maxz;
    home = "cores"; //core0
    output_dir = output_dir_;
    assert(!output_dir_.empty());
    assert(output_dir_.back() == '/'); //back=last character

    using x = mim::android_vertex_t;
    x::cfg_str28 = get_cfg("str28"); // "us.cash"; //Android app package name
    x::cfg_str32 = get_cfg("str32"); //"US Cash"; //Android app name
    x::package_ns = x::cfg_str28;
    x::package_dir = x::package_ns;
    replace(x::package_dir.begin(), x::package_dir.end(), '.', '/');
    cout << "mim::android_vertex_t::package_ns = " << x::package_ns << '\n';
    cout << "mim::android_vertex_t::package_dir = " << x::package_dir << '\n';
}

void c::list_layers(float maxz, ostream& os) const {
    mim::core0::vertex_t core0;
    core0.list_layers("", maxz, os);
}

void c::set_dryrun() {
    dryrun = srctool._dryrun = true;
}

void c::set_review() {
    review = true;
}

void c::systemx(const string& cmd) {
    if (dryrun) {
        cout << "[dryrun] skipped execution of: " << cmd << '\n';
        return;
    }
    cout << "Executing: " << cmd << '\n';
    system(cmd.c_str());
}

void c::systemx(const string& filename, const string& content) {
    if (dryrun) {
        cout << "[dryrun] skipped writting file: " << filename << '\n';
        return;
    }
    cout << "Executing: write file: " << filename << '\n';
    ofstream os(filename);
    os << content;
}

ko c::configure() {
    if (dryrun) {
        cout << "WARNING: this is a dry run. No files are written as output.\n";
    }
    switch (features.mode) {
        case mode_dev: {
            if (features.dev.gitignore) {
                ostringstream cmd;
                cmd << "cat " << output_dir << "us/.gitignore | grep -v \"# --MIM\" > /tmp/gitign";
                systemx(cmd.str());
            }
            mim::core0::vertex_t core0;
            core0.gen_merge_configure(features.max_zorder);
            if (features.dev.gitignore) {
                ostringstream cmd;
                cmd << "mv /tmp/gitign " << output_dir << "us/.gitignore";
                systemx(cmd.str());
            }
        }
        break;
        case mode_cbs:
        case mode_clean: {
            mim::core0::vertex_t core0;
            core0.gen_merge_configure(features.max_zorder);
        }
    }
    if (skipped_code_review) {
        cout << "WARNING: flagged code in need of a review has been found but skipped in dryrun mode. Check output above. Run in write mode to review.\n"; 
    }
    cout << "***************************\n";
    cout << "****    Success!       ****\n" ;
    cout << "***************************\n";
    if (new_kickoff_files.empty()) return ok;
    if (dryrun) {
        cout << "[dryrun] The following files **would** ";
    }
    else {
        cout << "The following files ";
    }
    cout << "have been written in the MIM-tree for customization (kickoff-files): \n";
    ind_t ind(cout, "  ");
    int n{0};
    for (auto& i: new_kickoff_files) {
        cout << n++ << ": " << i << '\n';
    }
    for (auto& i: warnings) {
        cout << "Warning: " << i << '\n';
    }
    return ok;
}

#include "command.h"
const string& c::get_cfg(const string& key) const {
    static string empty;
    auto i = cfg.find(key);
    if (i == cfg.end()) return empty;
    return i->second;
}

ko c::system_command(const string& command, string& result) {
    cout << "exec " << command << endl;
    raymii::CommandResult ans = raymii::Command::exec(command);
    if (ans.exitstatus != 0) {
        auto r = "KO 82795 Command finished with error.";
        cerr << r << endl;
        return r;
    }
    result = ans.output;
    trim(result);
    return ok;
}


ko c::clean_conf() {
    features.mode = mode_clean;
    features.dev.gitignore = false;
    return configure();
}

void c::dump_files(const vector<string>& files) {
    for (auto& i: files) {
        cout << "file: " << i << '\n';
    }
}

bool c::file_exists(const string& f) { //cfg0
    struct stat s;
    if (stat(f.c_str(), &s) == 0) return S_ISREG(s.st_mode);
    return false;
}

bool c::dir_exists(const string& d) {
    struct stat s;
    if (stat(d.c_str(), &s) == 0) return S_ISDIR(s.st_mode);
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

vector<string> c::files(const string& home) { //copied from gov::engine::db_analyst
    namespace fs = std::filesystem;
    vector<string> r;
    for(auto& p: fs::directory_iterator(home)) {
        if (!is_regular_file(p.path())) continue;
        r.push_back(p.path().filename());
    }
    return r;
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

vector<string> c::split_fqns(string fqns) { //adapted from cfg0.cpp
    vector<string> ret;
    trim(fqns);
//    replace :: -> /
    string cur;
    while(true) {
        auto i = fqns.find("::");
        if (i == string::npos) {
            if (!fqns.empty()) {
                ret.emplace_back(fqns);
            }
            break;
        }
        if (i == 0) {
            fqns = fqns.substr(2);
            ret.emplace_back(cur);
            cur = "";
            continue;
        }
        cur = fqns.substr(0, i);
        fqns = fqns.substr(i);
    }
    return ret;
}

string c::target_filename(const string& file) {
    if (file.ends_with(".in")) {
        return file.substr(0, file.size() - 3);
    }
    return file;
}

void c::register_template(const string& key, const template_vertex_t& v) {
    cout << "register_template " << key << '\n';
    templates.emplace(key, &v);
}

/*
void c::customize_produced_vertexes() {
    for (auto& t: templates) {
        cout << "template name " << t.second->name << '\n';
        ind_t ind(cout, "  ");
        t.second->customize_produced_vertexes();
    }
}
*/

void c::own(vertex_t* v) {
    owned.emplace_back(v);
}

void c::produce_file(const pair<string, filedef_t>& filedef, const string& tgt, bool overwrite) {
    cout << "produce file " << filedef.first << ' '; filedef.second.dump(cout);
    ind_t ind(cout, "  ");

    cout << "output dir: " << string(output_dir) << tgt << " overwrite=" << overwrite << '\n';
    if (filedef.first.find('[') != string::npos) {
        print_stacktrace(cout);
        cerr << "KO 94995 produced template file: " << filedef.first << '\n';
        abort();
    }
    if (filedef.second.is_generated()) {
        produce_generated_file_(filedef.second.content, filedef.first, tgt, overwrite);
        return;
    }
    string file = target_filename(filedef.first);
    string src = target_filename(filedef.second.path);
    assert(!src.empty());
    switch (features.mode) {
        case mode_clean: { //clean
            ostringstream cmd;
            cmd << "rm -f " << output_dir << tgt << '/' << file;
            systemx(cmd.str());
            if (file != filedef.first) {
                ostringstream cmd;
                cmd << "rm -f " << src << '/' << file; //this src is generated
                systemx(cmd.str());
            }
        }
        break;
        case mode_dev: //config dev
        case mode_cbs: {
            //bool force_copy{false};
            if (file != filedef.first) {
                int r = srctool.patch("", cfg, src + '/' + file);
                assert(r == 0);
                //force_copy = true;
                if (features.mode == mode_dev) {
                    if (features.dev.gitignore) {
                        ostringstream cmd;
                        cmd << "echo \"/us/mim/" << src << '/' << file << " # --MIM\" >> /tmp/gitign";
                        systemx(cmd.str());
                    }
                }
            }
            else {
                file = filedef.first;
            }
            string destdir = string(output_dir) + tgt;
            {
                ostringstream cmd;
                cmd << "mkdir -p " << destdir;
                systemx(cmd.str());
            }
            string fn = destdir + '/' + file;
            /*
            if (fn.find('[') != string::npos) {
                print_stacktrace(cout);
                cerr << "KO 94995 produced template file " << fn << endl;
                abort();
            }
            */
            if (overwrite) {
                if (file_exists(fn)) {
                    ostringstream cmd;
                    cmd << "rm -f " << fn;
                    systemx(cmd.str());
                }
            }
            if (file_exists(fn)) {
                cout << "File " << fn << " already exists.\n";
            }
            else {
                ostringstream cmd;
                if (features.mode == mode_dev) {
                    cmd << "bin/mk_symlink " << src << ' ' << file << ' ' << fn;
                }
                else if (features.mode == mode_cbs) {
                    cmd << "cp " << src << '/' << file << ' ' << fn;
                }
                systemx(cmd.str());
            }
            if (features.mode == mode_dev) {
                if (features.dev.gitignore) {
                    ostringstream cmd;
                    cmd << "echo \"" << tgt << '/' << file << " # --MIM\" >> /tmp/gitign";
                    systemx(cmd.str());
                }
            }
        }
        break;
    }
}

bool c::mim_screen_dump_enabled() const {
    return (features.mode == mode_dev) && features.dev.mim_screen_dump;
}

bool c::dev_mode() const {
    return (features.mode == mode_dev);
}

bool c::cbs_mode() const {
    return (features.mode == mode_cbs);
}

bool c::clean_mode() const {
    return (features.mode == mode_clean);
}

bool c::write_kickoff_file(const string& path, const string& filename, const string& content) {
if (filename == "certs__conf0.java") {
    int stop = 1;
}
    assert(!clean_mode());
    auto fqn = path + '/' + filename; 
    if (file_exists(fqn)) {
        cout << "skipped writting kickoff file " << fqn << " because it already exists.\n";
        return false;        
    }
    if (dryrun) {
        cout << "[dryrun] skipped writting kickoff file " << fqn << '\n';
    }
    else {
        cout << "writting kickoff file " << fqn << '\n';
        {
            ostringstream cmd;
            cmd << "mkdir -p " << path;
            systemx(cmd.str());
        }

        ofstream os(fqn);
        os << content;
    }
    new_kickoff_files.push_back(fqn);
    return true;
}

void c::produce_generated_file_(const string& content, const string& file, const string& tgt, bool overwrite) {
    produce_generated_file_(content, file, tgt, output_dir, overwrite);
}

void c::produce_generated_file_(const string& content, const string& file, const string& tgt, const string& odir, bool overwrite) {
    cout << "produce_generated_file " << content.size() << " bytes: " << file << " -> " << odir << tgt << " overwrite=" << overwrite << '\n';
    switch (features.mode) {
        case mode_clean: { //clean
            ostringstream cmd;
            cmd << "rm -f " << odir << tgt << '/' << file;
            systemx(cmd.str());
        }
        break;
        case mode_dev: //config dev
        case mode_cbs: {
            string destdir = string(odir) + tgt;
            {
                ostringstream cmd;
                cmd << "mkdir -p " << destdir;
                systemx(cmd.str());
            }
            string fn = destdir + '/' + file;
            if (overwrite) {
                if (file_exists(fn)) {
                    ostringstream cmd;
                    cmd << "rm -f " << fn;
                    systemx(cmd.str());
                }
            }
            systemx(fn, content);
            if (features.mode == mode_dev) {
                if (features.dev.gitignore) {
                    ostringstream cmd;
                    cmd << "echo \"" << tgt << '/' << file << " # --MIM\" >> /tmp/gitign";
                    systemx(cmd.str());
                }
            }
        }
        break;
    }
}

ko c::read_text_file_(const string& filename, string& dest) {
    ifstream is(filename, ios::ate);
    if (is.fail()) {
        auto r = "KO 65998 File cannot be opened.";
        return r;
    }
    ifstream::pos_type pos = is.tellg();
    try {
        dest.resize(pos);
    }
    catch(exception e) {
        auto r = "KO 54098";
        return r;
    }
    is.seekg(0, ios::beg);
    is.read((char*)dest.data(), pos);
    if (is.fail()) {
        auto r = "KO 65997";
        return r;
    }
    return ok;
}

void c::delete_lines_with(string& buffer, const string& search) {
    size_t pos = 0;
    const char* p0 = buffer.data();
    while ((pos = buffer.find(search, pos)) != std::string::npos) {
        const char* p1 = p0 + pos;
        while (p1 > p0) {
            if (*(p1 - 1) == '\n') break;
            --p1;
        }
        const char* p2 = p0 + pos + search.length();
        while (*p2 != 0) {
            if (*(p2 - 1) == '\n') break;
            ++p2;
        }
        pos = p1 - p0;
        buffer.replace(pos, p2 - p1, "");
    }
}

void c::replace_string(string& buffer, const string& search, const string& replace) {
    size_t pos = 0;
    while ((pos = buffer.find(search, pos)) != std::string::npos) {
         buffer.replace(pos, search.length(), replace);
         pos += replace.length();
    }
}

/*
bool c::patch_mim_file(const string& file, const string& info) {
    if (file.empty()) {
        return false;
    }
    string mim_content;
    auto r = read_text_file_(file, mim_content);
    if (is_ko(r)) {
        cerr << "KO 60288 Unable to open file. " << file << '\n';
        assert(false);
        abort();
    }
    auto p = mim_content.find("need_review = true;");
    if (p == string::npos) return false;
    cout << "\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n";
    cout << "File: " << file << '\n';
    cout << info << '\n';
    cout << "Answering 'y' will set need_review flag to false." << ".\n";
    cout << "Reset review flag in MIM file? [y|n]: "; 
    cout.flush();
    string line;
    cin >> line;
    cout << "X/XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n\n";
    if (line == "Y" || line == "y") {
        replace_string(mim_content, "need_review = true;", "need_review = false;");
        ofstream os(file);
        os << mim_content;
        return true;
    }
    return false;
}

void c::review_templates(vector<string>& changed_mim_files) {
    for (auto& i: templates) {
        if (!i.second->needs_review()) continue;
        ostringstream os;
        os << "Template def:              [T]\n";
        os << "    mim_file: " << i.second->mim_file << '\n';
        os << "    id: " << i.first << '\n';
        os << "    need_review: " << i.second->needs_review() << '\n';
        if (patch_mim_file(i.second->mim_file, os.str())) {
            changed_mim_files.emplace_back(i.second->mim_file);
        }
    }
}
*/

