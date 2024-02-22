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
#include "conf/mim.h"
//#include <source_location>

namespace mim::core0::core0_7 {
namespace us::gui::device_endpoints {

    struct vertex_t: template_instance_vertex_t {
        using b = template_instance_vertex_t;

        vertex_t(): b("device_endpoints", __FILE__, "us::gui::activity::listconf",
            params_t{
                {"controller", "activity"},
                {"scroll", "no"},
                {"api__function", ""},
                {"api__datatype", ""},
                {"api__itemtype", ""},
                {"title", "Connections"},
                {"item_title", "connection"},
                {"datatype", "device_endpoints_t"},
                {"make_itemconf", "yes"},
                {"itemtype", "device_endpoint_t"},
                {"item_name", "device_endpoint"}, //vertex 'item' name
                {"item_name__append_to_parent", "no"}, //classname not prefixed by parent classname
                {"itemico", "R.raw.gear"},
                {"data_identifier", "deps"},
                {"with_menu", "true"},
                {"nft_support", "no"},
                {"item_button__conf__popup_menu", "yes"},
            }) {
            custom_params.emplace("item__conf", params_t{
                    {"controller", "activity"},
                    {"scroll", "yes"},
                    {"title", "Connection"},
                    {"api__function", ""},
                    {"api__datatype", ""},
                    {"data_identifier", "dep"},
                    {"with_menu", "false"},
                    {"is_abstract", "no"},
                });

            codefiles.add_java();
            codefiles.classname_alg = codefiles_t::vertex_name;
            add(new conf::vertex_t());
        }

        vertex_t(const vertex_t& other): b(other) {}
        vertex_t* clone() const override { return new vertex_t(*this); }

    };

}}


