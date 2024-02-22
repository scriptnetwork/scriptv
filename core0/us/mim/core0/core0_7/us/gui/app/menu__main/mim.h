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
#include <map>
namespace mim::core0::core0_7 {
namespace us::gui::app::menu__main {

    struct vertex_t: menu_vertex_t {
        using b = menu_vertex_t;
        vertex_t(): b("menu__main", __FILE__) {
            struct menuspec1_t: mim::menuspec_t {
                using b = mim::menuspec_t;
                menuspec1_t(): b("_main") {
                    {
                        auto& g = find("cfg");
                        g.add("R.raw.connection", "Manage connections");
                    }
                }
            };

            struct menuspec2_t: mim::menuspec_t {
                using b = mim::menuspec_t;
                menuspec2_t(): b("_online", "_main") {
                    {
                        auto& g = find("cfg");
                        g.add("R.raw.pill", "Software updates");
                    }
                }
            };

            codefiles.add_java();
            add(menuspec1_t());
            add(menuspec2_t());
        }

    };

}}

