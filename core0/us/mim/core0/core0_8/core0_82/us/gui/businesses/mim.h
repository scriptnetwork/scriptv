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

namespace mim::core0::core0_8::core0_82 {
namespace us::gui::businesses {

    struct vertex_t: template_instance_vertex_t {
        using b = template_instance_vertex_t;

        vertex_t(): b("businesses", __FILE__, "us::gui::activity::listconf",
            params_t{
                {"controller", "activity"},
                {"scroll", "no"},
                {"api__function", "list_businesses"},
                {"api__datatype", "protocols_t"},
                {"api__itemtype", "protocol_selection_t"},
                {"title", "Businesses"},
                {"item_title", "business"},
                {"datatype", "protocols_t"},
                {"itemtype", "protocol_selection_t"},
                {"item_name", "business"}, //vertex 'item' name
                {"item_name__append_to_parent", "no"}, //classname not prefixed by parent classname
                {"itemico", "R.raw.business"},
                {"data_identifier", "bzs"},
                {"with_menu", "true"},
                {"nft_support", "no"},
                {"item_button__conf__popup_menu", "yes"},
                {"item_object_id", "app.protocol_selection__object_id"},
            }) {
            custom_params.emplace("item__conf", params_t{
                    {"controller", "activity"},
                    {"scroll", "yes"},
                    {"api__function", ""},
                    {"api__datatype", ""},
//                    {"api__itemtype", ""},
                    {"title", "Business"},
                    {"datatype", "protocol_selection_t"},
                    {"with_menu", "true"},
                    {"data_identifier", "bz"},
                    {"is_abstract", "no"},
                });

            codefiles.add_java();
            codefiles.classname_alg = codefiles_t::vertex_name;
            include_java("us.wallet.trader.bootstrap.protocols_t");
            include_java("us.wallet.trader.protocol_selection_t");
            add(new conf::vertex_t());
        }

        vertex_t(const vertex_t& other): b(other) {}
        vertex_t* clone() const override { return new vertex_t(*this); }

    };

}}

