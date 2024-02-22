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

#include <set>
#include <string>
#include <iostream>

namespace mim {
    using namespace std;

    struct vertex_t;

    struct edges_t final {
        struct directional_edges_t final: set<vertex_t*> {
            directional_edges_t();
            vertex_t* find_by_name(const string& name);
            void dump(ostream& os) const;
        };

        edges_t();
        edges_t(const edges_t&);
        void dump(ostream& os) const;

        directional_edges_t in;
        directional_edges_t out;
    };

}

