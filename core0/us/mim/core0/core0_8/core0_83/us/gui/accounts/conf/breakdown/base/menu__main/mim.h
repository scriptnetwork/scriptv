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
namespace mim::core0::core0_8::core0_83 {
namespace us::gui::accounts::conf::breakdown::base::menu__main {

    struct vertex_t: menu_vertex_t {
        using b = menu_vertex_t;

        vertex_t(): b("menu__main", __FILE__) {
            struct menuspec_t: b::menuspec_t {
                menuspec_t(): b::menuspec_t("_main") {
                    header_icon = "R.raw.account_150";
                    header_title = "Addresses";
                    header_subtitle = "All coins";
                    header_title_resid = "-";
                    header_subtitle_resid = "-";

                    {
                        auto& g = find("usr");
                        g.add("R.raw.bnew", "New address");
                    }
/*
                    group_t g0; {
                        g0.add("R.raw.bnew", "New address");
                    }
                    group_t g1; {
                        g1.add("R.raw.burger", "Next menu");
                        g1.add("R.raw.exit", "Exit accounts");
                    }
                    add(move(g0));
                    add(move(g1));
*/
                }
            };
            codefiles.add_java();
            add(menuspec_t());
        }

    };
}}

