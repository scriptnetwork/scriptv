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

namespace mim::core0::core0_6::core0_61 {
namespace us::gui::activity::scr::list_view::template1 {

    struct vertex_t: mim::template_vertex_t {
        using b = mim::template_vertex_t;

        vertex_t(): b("template1", __FILE__, "us::gui::activity::scr::list_view::template1",
            params_t {
                {"datatype", "ArrayList<String>"},
                {"itemtype", "String"},
                {"itemico", "R.raw.gear"},
            }) {
            codefiles.add_java();
        }

    };

}}

