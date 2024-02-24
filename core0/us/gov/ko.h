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
#include <iostream>
#include <string>
#include "likely.h"

namespace us {

    using namespace std;

    using ko_t = char;
    using ko = const ko_t*;
    static constexpr ko ok = nullptr;

    static inline bool is_ko(const string& s) {
        if (unlikely(s.size() < 3)) return false;
        return s[0] == 'K' && s[1] == 'O' && s[2] == ' ';
    }

    inline static bool is_ko(ko o) { return o != ok; }
    inline static bool is_ok(ko o) { return o == ok; }

}

