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
#include "menu_modal/mim.h"
#include "menu_parent/mim.h"

namespace mim::core0::core0_8::core0_85 {
namespace us::gui::timeseries::things {

    struct vertex_t: template_instance_vertex_t {
        using b = template_instance_vertex_t;

        vertex_t(): b("things", "us::gui::listconf",
            map<string, string>{
                {"MIM file", __FILE__},
                {"datatype", "ArrayList<things_t>"},
                {"itemtype", "thing_t"},
                {"title", "IoT Thing"},
            }) {
            codefiles.add_java();
            add(new menu_parent::vertex_t());
            add(new menu_modal::vertex_t());

        }

        vertex_t(const vertex_t& other): b(other) {}
        vertex_t* clone() const override { return new vertex_t(*this); }

    };
}}
