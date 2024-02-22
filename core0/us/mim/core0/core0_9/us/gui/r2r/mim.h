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
#include "w2w/mim.h"
#include "symmetric/mim.h"

namespace mim::core0::core0_9 {
namespace us::gui::r2r {

    struct vertex_t: android_vertex_t {
        using b = android_vertex_t;
        vertex_t(): b("r2r", __FILE__) {
            codefiles.add_java();
            codefiles.classname_alg = codefiles_t::vertex_name;
            
            add(new symmetric::vertex_t());
            add(new w2w::vertex_t());

        }
    };

}}
