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
#include <cassert>
#include <vector>
#include <streambuf>
#include <iostream>
#include "edges_t.h"

namespace mim {
    using namespace std;

    struct vertex_t: map<string, edges_t> { //DAG vertex
        using b = map<string, edges_t>;
        //vertex_t(const string& name);
        vertex_t(const string& name, const string& mim_file);
        vertex_t(const vertex_t&);
        virtual vertex_t* clone() const;
        virtual bool merge(vertex_t* other, int op);
        virtual ~vertex_t();
        virtual bool is_enabled() const { return true; }
        virtual bool overwrite_mode() const { return true; } //vertexes same-name file merge mode: true: other's file is used.; false: me file is used
        edges_t& get_connections(const string& netname);
        const edges_t& get_connections(const string& netname) const;
        void dump(ostream&) const;
        virtual void dump_(ostream& os) const;
        void assert_(bool) const;
        string mim_dir() const;

    public:
        void dump_mim_files(ostream& os) const;

        struct merge_info_item_t {
            merge_info_item_t(const string& src_mim_file): src_mim_file(src_mim_file) {
            }
            merge_info_item_t(const merge_info_item_t& other): src_mim_file(other.src_mim_file) {
            }
            string src_mim_file;
        };

        struct merge_info_t: vector<merge_info_item_t> {
            using b = vector<merge_info_item_t>;
            void emplace_back_(const merge_info_t& other);
            //using b::emplace_back;
        };
        
        merge_info_t merge_info;

        //static const string& ind_();
        //static void ind_push(const string&);
        //static void ind_pop();

    public:
        bool merge__force_same_name{true};
        string name;
        string mim_file;

        //static stack<string> pfx_;
    };

    struct ind_t: streambuf {
        using b = streambuf;
        ind_t(ostream& os_, const string& indent_): dest(os_.rdbuf()), os(&os_) {
            if (dest == nullptr) return;
            auto prev = dynamic_cast<ind_t*>(dest);
            if (prev != nullptr) {
                prev->enable = false;
                indent = prev->indent + indent_;
            }
            else {
                indent = indent_;
            }
            os->rdbuf(this);
        }
/*
        ind_t(ostream& os_): dest(os_.rdbuf()), os(&os_) {
            if (dest == nullptr) return;
            auto prev = dynamic_cast<ind_t*>(dest);
            if (prev != nullptr) {
                prev->enable = false;
                indent = prev->indent;
            }
            os->rdbuf(this);
        }
*/
        ~ind_t() override {
            if (dest == nullptr) return;
            auto prev = dynamic_cast<ind_t*>(dest);
            if (prev != nullptr) {
                prev->enable = true;
            }
            os->rdbuf(dest);
        }

        int overflow(int ch) override {
            if (enable) {
/*
                if (line_start && ch != '\n') {
                    dest->sputn(indent.data(), indent.size());
                }
                line_start = ch == '\n';
*/
                if (line_start) {
                    if (ch == '\n') { //indent has trailing spaces and this line has ended. Trimming.
                        size_t sz{0};
                        sz = indent.size();
                        while (sz > 0) {
                            if (indent[sz -1] != ' ') break;
                            --sz;
                        }
                        dest->sputn(indent.data(), sz);
                    }
                    else {
                        dest->sputn(indent.data(), indent.size());
                    }
                    line_start = false;
                }
                if (ch == '\n') line_start = true;

            }
            return dest->sputc(ch);
        }

        streambuf* dest;
        string indent;
        ostream* os;
        bool line_start{true};
        bool enable{true};
    };    
/*
    static std::ostream& operator << (std::ostream& os, const ind_t& ind) {
        os << vertex_t::ind_();
        return os;
    }
*/
}


