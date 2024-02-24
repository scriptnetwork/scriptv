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
#include <cstdint>
#include <string>
#include <iostream>
#include "types.h"

namespace us::gov::engine {

    struct daemon_t;

    struct shell {
        static constexpr int max_type_errors{3};
        shell(daemon_t&);

    public:
        void help(ostream&) const;
        ko command(const string& cmd, ostream&);
        ko command(istream&, ostream&);
        ko command(istream&, const string& cmd, ostream&);
        bool test_node(const string& n, ostream&);

    public:
        daemon_t& d;
        int level{7};
        int cur_app{-1};
        string memory_cmd_lvl;
    };

}

