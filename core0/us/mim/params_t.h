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
#include <map>
#include <vector>
#include <iostream>

namespace mim {
    using namespace std;

    struct params_t: map<string, string> {
        using map<string, string>::map;

        bool load(const string& filename);
        void add(const string& k, const string& v);
        void add(const params_t&);
        pair<bool, string> get(const string& key) const;
        void underride__notdef(const string& key, const string& value);
        void underride__notdef(const params_t&);
        void underride__empty(const string& key, const string& value);
        void underride__empty(const params_t&);
        void merge(params_t& other, int op);


        void dump(ostream&) const;

    };

/*
    struct params_src_t {
        params_src_t();
        params_src_t(const string& mim_file, params_t&& params);
        params_src_t(const params_src_t& other);
        
        string mim_file;
        params_t params;
    };

    struct params_srcs_t: vector<params_src_t> {
        void priority_set(const params_srcs_t&);
        void priority_set(const params_src_t&);
        void low_priority_set(const params_srcs_t&);
        void low_priority_set(const params_src_t&);
        void low_priority_set(params_src_t&&);
        void dump(ostream&) const;
        string get(const string&) const;
        //string get_mim_files() const;
        const string& get_low_mim_file() const;
        params_t collect() const;
        int v{0};
    };
*/
        
}

