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
namespace mim::core0::core0_8::core0_86 {
namespace us::gui::trader::conf::r2r {

    struct vertex_t: template_instance_vertex_t {
        using b = template_instance_vertex_t;

        vertex_t(): b("r2r", __FILE__, "us::gui::activity::listview_controller",
            params_t{
                {"tabico", "R.raw.r2rtab"},
                {"controller", "fragment"},
                {"api__method", "hmi"},
                {"hmi__command", "show roles"},
                {"hmi__push_code", "us.wallet.trader.trader_t.push_roles"},
                {"api__function", ""},
                {"api__datatype", ""},
                {"api__itemtype", ""},
                {"datatype", "trader__conf__r2r__datatype_t"},
                {"itemtype", "trader__conf__r2r__itemtype_t"},
                {"itemico", "R.raw.role"},
                {"title", "Roles"},
                {"item_title", "r2r"},
                {"with_menu", "true"},
                {"nft_support", "yes"},
                {"nft_type", "String"},
                {"data_identifier", "roles"},
                {"item_button__conf__popup_menu", "yes"},
                {"item_object_id", "app.protocol_selection__object_id"},

            }) {
            codefiles.add_java();

            include_java("static us.gov.crypto.ripemd160.hash_t");
            include_java("us.wallet.trader.roles_t");
//            include_java("us.wallet.trader.protocol_selection_t");
            include_java("us.pair");

  //          add(new base::vertex_t());

        }

        vertex_t(const vertex_t& other): b(other) {}
        vertex_t* clone() const override { return new vertex_t(*this); }

    };

}}
