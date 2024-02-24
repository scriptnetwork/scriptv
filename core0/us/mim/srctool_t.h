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
#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <sstream>
#include <algorithm>
#include <set>
#include <map>
#include <cstring>
#include <cassert>
#include <filesystem>
#include <functional>


namespace mim {
    using namespace std;

    struct srctool_t {

        srctool_t();

        bool exclude(const string& f);
        string ext(const string& file);
        string get_pfx(string& bangshe, const string& file);
        bool read_header(const string& filename, const string& pfx, vector<unsigned char>& buf);
        bool read_file(const string& filename, vector<unsigned char>& buf);
        bool write_file(const string& filename, size_t from, const vector<unsigned char>& buf);
        bool fileinfo(const string& file, vector<uint8_t>& content, string& comment, size_t& hdr_start);
        const vector<uint8_t>& get_header(const string& hfile, const string& pfx);
        int add_header(const string& bangshe, const vector<uint8_t>& header, const string& file);
        int add_header(const string& hfile, const string& file);
        int remove_header(const string& file);
        vector<string> collect_files(const string& dir);
        int add_header_dir(const string& hfile, const string& dir);
        int remove_header_dir(const string& dir);
        bool is_white(const char* p);
        bool is_identifier(const char* p);
        void advance_to_identifier(const char*& p, const char* e);
        string extract_identifier(const char*& p, const char* e);
        void advance_after(const char*& p, const char* e, char f);
        pair<string, string> extract_key_value(const string& line);
        map<string, string> load_vars(const string& file);
        string replace_tokens0(const string varpfx, const string& line);
        string replace_tokens(const string varpfx, const string& line, function<string(const string&)> f);
        int patch(const string& prefix, const string& envfile, const string& file);
        int patch(const string& prefix, const map<string, string>& cfg, const string& file);

        string _ext;
        bool _recursive{false};
        bool _dryrun{false};
        
        set<string> excludeset;
        set<string> bashexts;
        set<string> ignoreexts;
        map<string, vector<uint8_t>> hdrs;
    };
}

