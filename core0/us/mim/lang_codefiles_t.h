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
#include <cassert>
#include <iostream>
#include <map>
#include <set>
#include <vector>
#include <us/gov/ko.h>
#include <us/gov/crypto/ripemd160.h>
#include "params_t.h"

namespace mim {
    using namespace std;
    using us::ko;
    using us::ok;
    using hasher_t = us::gov::crypto::ripemd160;
    using hash_t = hasher_t::value_type;
    struct namespace_t;
    struct menu_vertex_t;
    struct menuspec_t;

    using filename_t = string;
    using fspath_t = string;

    struct token_resolution_t {

        token_resolution_t(int algo, const string& payload, const string& mim_file): algo(algo), payload(payload), mim_file(mim_file) {} 
        token_resolution_t(set<string>&& payload, const string& mim_file): algo(2), payload2(payload), mim_file(mim_file) {} 

        int algo; //algorithm: -1; unsolved; 0: token_subst; 1: line_subst; 2: line_generator__set
        string payload;
        set<string> payload2;
        string mim_file;

        bool solved() const { return algo != -1; }
        string algostr() const;
        void dump(ostream&) const;
    };    

    struct token_resolutions {
        static token_resolution_t unsolved;
    };
    
    struct filedef_t {
    public:
        bool is_generated() const { return !content.empty(); }
        bool is_verbatim() const { return content.empty(); }
        void dump(ostream&) const;

        fspath_t path;
        hash_t hash;
        string content;
        string derived_from;
        string kickoff_dir;
        string mim_file;
    public:
        struct tokens_resolved_t: map<string, token_resolution_t> {
            void dump(ostream&) const;
        };  

        struct  tokens_t: set<string> {
            void dump(ostream&) const;
        };
        
        tokens_t tokens__used; 
        tokens_resolved_t tokens__resolved; 

    };

    struct files_t: map<filename_t, filedef_t> { //name, path
        using b = map<filename_t, filedef_t>;
        using b::map;
        
        void dump(ostream&) const;

    };

    struct lang_codefiles_t: map<string, lang_codefiles_t*> { //subhome

        //static constexpr auto kickoff_suffix{"__kickoff"};
        using tokens_t = filedef_t::tokens_t;
        using tokens_resolved_t = filedef_t::tokens_resolved_t;

        lang_codefiles_t(namespace_t& vertex, const string& name);
        lang_codefiles_t(const lang_codefiles_t& other);
        virtual ~lang_codefiles_t();

        virtual lang_codefiles_t* clone() const = 0;

        string home() const;

        virtual bool gen();
        virtual void pre_configure();
        virtual bool configure();
        virtual bool merge(lang_codefiles_t& other_, int op);

        //virtual void on_attach_vertex(const namespace_t& parent_vertex);
        //virtual void on_detach_vertex();

        virtual void dump(ostream&) const;
        void dump0(ostream&) const;

        string conf_target_dir() const;
        string kickoff_code_dir() const;

        hash_t extract_hash(const string& filename);

        void set_vertex(namespace_t&);

        bool file_exists(const string&);
        void trim(string&);
        
        pair<ko, pair<string, string>> split_fqn(string fqn);

        void load_filenames_(ostream& os);
        void load_filenames_(const string& dir, ostream& os);
        string lang_home() const;

        void inflate(const params_t&);
        void gen_files(const params_t&);
        void gen_file(pair<string, filedef_t>& entry, const params_t&);
        void gen_file(const string& filename, filedef_t& filedef, const params_t&);

        void include(const string& dep);
        void add_file(const string&, filedef_t&&);

        virtual void write_include_code(const set<string>& includes, ostream&) const = 0;

        void fix_conflict__generated__verbatim(const string& filename, const filedef_t& generated, const filedef_t& verbatim);

        vector<const menu_vertex_t*> collect_menus() const;
        void fill_includes(set<string>& dest) const;
        bool anylang() const;

        void find_tokens(const string& buf, const string& sepl, const string& sepr, tokens_t& o) const;

        //void expand_content(string& buf, const string& classname, const string& includes) const;
        //void expand_filename(string& filename, const string& classname) const;

//        using menuspecs_t = vector<pair<const menuspec_t*, const menu_vertex_t*>>;
//        void expand__classname(string& buf, const string& classname, set<string>& tokens_left) const;
//        void expand__params(string& buf, set<string>& tokens_left) const;
//        set<string> expand__user_tokens(string& buf) const;


        bool expand__line_generator(string& buf, const string& tokenname, const string& token, const set<string>& names) const;
        bool expand__line_subst(string& buf, const string& tokenname, const string& token, const string& payload) const;
        bool expand__token_subst(string& buf, const string& tokenname, const string& token, const string& payload) const;
        bool expand_content(string& buf, const string& tokenname, const string& token, const token_resolution_t&) const;
        void expand_content(string& buf, const string& sepl, const string& sepr, const filedef_t::tokens_resolved_t&) const;


        void resolve_tokens(const tokens_t& used, tokens_resolved_t& resolved, const params_t p) const;

        int check_resolution(const tokens_t& used, const tokens_resolved_t& resolved) const;
        void load_kickoff_files();
        void write_kickoff_files(const params_t&);

    public:
        files_t files;
        //generated_files_t generated_files;
        set<string> includes;
        string name;
        string subhome;

        namespace_t* vertex;
    };


}

