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
namespace us::gui::trader::conf::chat {


    struct vertex_t: template_instance_vertex_t {
        using b = template_instance_vertex_t;

        vertex_t(): b("chat", __FILE__, "us::gui::activity::listview_controller",
            params_t{
                {"tabico", "R.raw.chat"},
                {"controller", "fragment"},
                {"api__method", "hmi"},
                {"hmi__command", "show chat"},
                {"hmi__push_code", "us.wallet.trader.trader_t.push_chat"},
                {"api__function", ""},
                {"api__datatype", "chat_t"},
                {"api__itemtype", "chat_entry"},
                {"datatype", ""},
                {"itemtype", ""},
                {"itemico", "R.raw.r2rtab"},
                {"title", "Chat"},
                {"item_title", "message"},
                {"with_menu", "true"},
                {"nft_support", "no"},
                {"nft_type", ""},
                {"data_identifier", "chat"},
                {"item_button__conf__popup_menu", "yes"},
            }) {
            codefiles.add_java();
            include_java("static us.wallet.trader.chat.chat_entry");
            include_java("static us.wallet.trader.chat.chat_t");
            //include_java("us.wallet.trader.bookmark_t");

            //add(new base::vertex_t());

        }

        vertex_t(const vertex_t& other): b(other) {}
        vertex_t* clone() const override { return new vertex_t(*this); }

    };

}}
