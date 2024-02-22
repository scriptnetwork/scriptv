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
namespace us::gui::trader::conf::status {


    struct vertex_t: template_instance_vertex_t {
        using b = template_instance_vertex_t;

        vertex_t(): b("status", __FILE__, "us::gui::activity::controller",
            params_t{
                {"tabico", "R.raw.gear"},
                {"controller", "fragment"},
                {"scroll", "yes"},
                {"api__function", ""},
                {"api__datatype", ""},
                {"datatype", ""},
                {"data_identifier", ""},
                {"title", "Status"},
                {"with_menu", "true"},
                {"nft_support", "no"},

            }) {
            codefiles.add_java();
            //include_java("us.wallet.trader.bookmarks_t");
            //include_java("us.wallet.trader.bookmark_t");

            //add(new base::vertex_t());

        }

        vertex_t(const vertex_t& other): b(other) {}
        vertex_t* clone() const override { return new vertex_t(*this); }

    };

}}
