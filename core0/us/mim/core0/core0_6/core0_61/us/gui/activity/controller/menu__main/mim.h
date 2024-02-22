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
#include <map>

namespace mim::core0::core0_6::core0_61 {
namespace us::gui::activity::controller::menu__main {

    struct vertex_t: menu_vertex_t {
        using b = menu_vertex_t;
        vertex_t(): b("menu__main", __FILE__) {
            struct menuspec_t: mim::menuspec_t {
                using b = mim::menuspec_t;
                menuspec_t(): b("_main") {
                    // +-------------+-------------------------------------------+
                    // +             |  header_title + header_title_resid        +
                    // + header_icon +-------------------------------------------+
                    // +             |  header_subtitle + header_subtitle_resid  +
                    // +-------------+-------------------------------------------+
                    // +  R.raw.burger   Next menu                               + <-group g0
                    // +  R.raw.exit     Exit activity                           +
                    // +  -----------------------------------------------------  +
                    // +                                                         + <-group g1
                    // +  ...                                                    +

                    header_icon = "R.raw.gear_150";
                    header_title = "Menu line 1 - "; //
                    header_title_resid = "R.string.app_name";
                    header_subtitle = "Menu Line 2 - ";
                    header_subtitle_resid = "R.string.brandbusiness";

                    {
                        auto& g = find("nav");
                        g.add("R.raw.burger", "Next menu");
                        g.add("R.raw.exit", "Exit activity");
                    }

                }
            };
            codefiles.add_java();
            add(menuspec_t());
        }

    };

}}

