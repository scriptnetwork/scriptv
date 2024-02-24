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
#include <iostream>
#include "android_vertex_t.h"

namespace mim {
    using namespace std;

    struct itemspec_t {
        string id;
        string title;
        
        string relid() const {
            if (id.substr(0, 6) == "R.raw.") {
                return id.substr(6);
            }
            return id;
        }
    };

    struct group_t: vector<itemspec_t> {
 
        group_t(const string& name): name(name) {
            assert(!name.empty());
        }

        void add(const string& id, const string& title) {
            itemspec_t o;
            o.id = id;
            o.title = title;
            emplace_back(move(o));
        }

        string name;
    };

    struct menuspec_t: vector<group_t> {

        menuspec_t(const string& name): name_(name) {
        }

        menuspec_t(const string& name, const string& basespec): name_(name), basespec(basespec) {
        }

        group_t& find(const string& name);

        bool merge(menuspec_t&& other, int op);
        string resolve_src__header_title() const;
        string resolve_src__header_subtitle() const;

        string class_name(const string& nameprefix) const;

        void dump(ostream& os) const;
        void dump_g(ostream& os) const;

        string name_;
        string basespec;
        string header_icon;
        string header_title;
        string header_subtitle;
        string header_title_resid;
        string header_subtitle_resid;
    };
}

