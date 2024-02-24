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
#include "vertex_t.h"

using namespace std;
using namespace mim;

using c = mim::vertex_t;

c::vertex_t(const string& name, const string& mim_file): name(name), mim_file(mim_file) {
}

c::vertex_t(const c& other): name(other.name), merge__force_same_name(other.merge__force_same_name), mim_file(other.mim_file), merge_info(other.merge_info) {
    assert(empty()); //after cloning, the new vertex is not connected to any parent nor has any children.
}

c::~vertex_t() {
}

c* c::clone() const {
    return new c(*this);
}

bool c::merge(vertex_t* other, int op) {
    //assert(op == 1);
    assert(other != nullptr);
    if (!is_enabled()) {
        cout << "merge into a disabled vertex" << endl;
        return false; //after base class (serial)
    }
    if (merge__force_same_name) {
        if (other->name != name) {
            cerr << "other->name " << other->name << ' ' << name << '\n';
            abort();
        }
    }
    switch (op) {
        case 0:
            merge_info.emplace_back_(other->merge_info);
            if (!other->mim_file.empty()) {
                merge_info.emplace_back(merge_info_item_t(other->mim_file));
            }
            break;
        case 1:
            other->merge_info.emplace_back_(merge_info);
            if (!mim_file.empty()) {
                other->merge_info.emplace_back(merge_info_item_t(mim_file));
            }
            merge_info = other->merge_info;
            other->merge_info.clear();
            mim_file = other->mim_file;
            break;
    }
    return true;
}

void c::merge_info_t::emplace_back_(const merge_info_t& other) {
    reserve(size() + other.size());
    for (auto i = other.begin(); i != other.end(); ++i) {
        emplace_back(*i);
    }
}

edges_t& c::get_connections(const string& netname) {
    assert(!netname.empty());
    c& self = *this;
    auto i = self.find(netname);
    if (i == self.end()) {
        i = self.emplace(netname, edges_t()).first;
    }
    return i->second;
}

const edges_t& c::get_connections(const string& netname) const {
    assert(!netname.empty());
    c& self = const_cast<c&>(*this);
    return self.get_connections(netname);
}

string c::mim_dir() const {
    auto i = mim_file.find("/mim.h");
    if (i == string::npos) {
        cerr << "KO 79568\n";
        assert(false);
        abort();
    }
    return mim_file.substr(0, i);
}

void c::dump_mim_files(ostream& os) const {
    os << "mim_file: " << mim_file << '\n';
    os << "merge history. " << merge_info.size() << " entries";
    if (!merge_info.empty()) {
        int n{0};
        os << ": (oldest first)\n";
        ind_t ind(os, "  ");
        for (auto& i: merge_info) {
            os << n++ << ": " << i.src_mim_file << '\n';
        }
    }
    else {
        os << ".\n";
    }
}

void c::dump(ostream& os) const {
    ind_t ind(os, '/' + name);
    dump_(os);
}

void c::dump_(ostream& os) const {
    {
        ind_t ind(os, "  ");
        os << "-* vertex_t.\n";
        {
            ind_t ind(os, "  ");
            dump_mim_files(os);
        }
    }
}

void c::assert_(bool f) const {
    if (!f) {
        //TODO: print backtrace
        cerr << "assert triggered. run in debug mode. make debug -j8\n"; 
        assert(false); //in release builds it doesnt stop here
        abort();
    }
}
/*
const string& c::ind_() {
    static const string base_indent;
    if (pfx_.empty()) return base_indent;
    return pfx_.top();
}

void c::ind_push(const string& pfx) {
    pfx_.push(ind_() + pfx);
}

void c::ind_pop() {
    pfx_.pop();
}
*/

