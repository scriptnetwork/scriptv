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
#include "java_files_t.h"

namespace mim {
    using namespace std;

    struct namespace_t;
    struct android_vertex_t;

    struct android_java_files_t final: java_files_t {
        using b = java_files_t;
        android_java_files_t(namespace_t& vertex, const string& name);
        android_java_files_t(const android_java_files_t&);
        ~android_java_files_t() override;
        lang_codefiles_t* clone() const override;
    };


}

