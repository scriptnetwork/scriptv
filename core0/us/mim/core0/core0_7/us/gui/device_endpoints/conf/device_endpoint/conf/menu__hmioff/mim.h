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
namespace us::gui::device_endpoints::conf::device_endpoint::conf::menu__hmioff {

    struct vertex_t: menu_vertex_t {
        using b = menu_vertex_t;
        vertex_t(): b("menu__hmioff", __FILE__) {

            struct menuspec_t: b::menuspec_t {
                menuspec_t(): b::menuspec_t("_hmioff") {
                    header_icon = "R.raw.conn_ico_off";
                    header_title = "HMI";
                    header_subtitle = "Human-Machine Interface is OFF";

                    {
                        auto& g = find("usr");
                        g.add("R.raw.power", "Power-on HMI");
                        g.add("R.raw.power_pin", "Power-on HMI (with PIN)...");
                    }
                    {
                        auto& g = find("usr2");
                        g.add("R.raw.findip", "Find the IP Address of my node");
                    }
                    {
                        auto& g = find("usr3");
                        g.add("R.raw.seeds", "Network seeds");
                        g.add("R.raw.log", "Connection log...");
                    }
                    {
                        auto& g = find("nav");
                        g.add("R.raw.burger", "Next menu");
                        g.add("R.raw.exit", "Exit HMI Settings.");
                    }

                }
            };
            codefiles.add_java();
            add(menuspec_t());
        }

    };

}}

