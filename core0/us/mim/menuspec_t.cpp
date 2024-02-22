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
#include "menuspec_t.h"
#include "engine_t.h"

using namespace std;
using namespace mim;

using c = mim::menuspec_t;

string c::class_name(const string& nameprefix) const {
    ostringstream os;
    os << nameprefix << "__menu__" << name_ << "_t";
    return os.str();
}

namespace {

    string escape(const string& s) {
        if (s == "-") return "";
        return s;
    }

}

bool c::merge(c&& other, int op) {
    cout << "merge menuspec " << name_ << " <- " << other.name_ << '\n';
    ind_t ind(cout, "  ");
//    reserve(size() + other.size());
    vector<group_t> newgroups;
    while(!other.empty()) {
        auto j = other.begin();
        bool f = false;
        for (auto& i: *this) {
            if (i.name == j->name) {
                cout << "merging group " << i.name << " " << i.size() << " " << j->size() << '\n';
                i.insert(i.end(), j->begin(), j->end());
                f = true;
                break;   
            }
        }
        if (!f) {
            cout << "import group " << j->name << " " << j->size() << '\n';
            newgroups.emplace_back(move(*j));
        }
        other.erase(j);        
    }
    insert(begin(), newgroups.begin(), newgroups.end());
    assert(other.empty());
//    other.clear();
    assert(name_ == other.name_);
    if (!other.basespec.empty()) basespec = other.basespec;
    if (!other.header_icon.empty()) header_icon = other.header_icon;
    if (!other.header_title.empty()) header_title = escape(other.header_title);
    if (!other.header_subtitle.empty()) header_subtitle = escape(other.header_subtitle);
    if (!other.header_title_resid.empty()) header_title_resid = escape(other.header_title_resid);
    if (!other.header_subtitle_resid.empty()) header_subtitle_resid = escape(other.header_subtitle_resid);

cout << "AFTER MERGE" << endl;
dump(cout);
    return true;
}

string c::resolve_src__header_title() const {
    if (header_title_resid.empty()) {
        return string("\"") + header_title + "\"";
    }
    if (header_title.empty()) {
        return string("ctx.getString(") + header_title_resid + ")";
    }
    return string("\"") + header_title + "\" + " + string("ctx.getString(") + header_title_resid + ")";
}

string c::resolve_src__header_subtitle() const {
    if (header_subtitle_resid.empty()) {
        return string("\"") + header_subtitle + "\"";
    }
    if (header_subtitle.empty()) {
        return string("ctx.getString(") + header_subtitle_resid + ")";
    }
    return string("\"") + header_subtitle + "\" + " + string("ctx.getString(") + header_subtitle_resid + ")";
}

group_t& c::find(const string& name) {
    cout << "Menuspec " << name_ << ' ' << this << ". Find group '" << name << "' in [";
    for (auto& i: *this) {
        cout << i.name << ", ";
    }
    cout  << "]. ";
    int pos = 0;
    for (auto& i: *this) {
        if (i.name == name) {
            cout  << "Found at pos " << pos << '\n';
            return i;
        }
        ++pos;
    }
    cout  << "Added at pos " << pos << '\n';
    return emplace_back(group_t(name));
}

void c::dump_g(ostream& os) const {
    for (auto& i: *this) {
        os << "group " << i.name << '\n';
        ind_t ind(os, "  ");
        for (auto& j: i) {
            os << j.id << ' ' << j.title << '\n';
        }
    }
}

void c::dump(ostream& os) const {
    os << "basespec: " << basespec << '\n';
    os << "name: " << name_ << '\n';
    os << "title: " << resolve_src__header_title() << '\n';
    os << "  title: " << header_title << '\n';
    os << "  title_res_id: " << header_title_resid << '\n';
    os << "subtitle: " << resolve_src__header_subtitle() << '\n';
    os << "  subtitle: " << header_subtitle << '\n';
    os << "  subtitle_res_id: " << header_subtitle_resid << '\n';
    os << "icon: " << header_icon << '\n';
    os << size() << " groups:\n";
    {
        ind_t ind(os, "  ");
        dump_g(os);
    }
}

