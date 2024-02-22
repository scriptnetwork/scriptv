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
#include <set>
#include "lang_codefiles_t.h"

namespace mim {
    using namespace std;
    struct namespace_t;

    struct java_files_t: lang_codefiles_t {
        using b = lang_codefiles_t;
        java_files_t(namespace_t& vertex, const string& name);
        java_files_t(const java_files_t&);
        lang_codefiles_t* clone() const override;
        void write_include_code(const set<string>& includes, ostream&) const override;
    };

}

