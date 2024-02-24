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
//#include "base/mim.h"

namespace mim::core0::core0_8::core0_85 {
namespace us::gui::nodes::conf::r2r {

    struct vertex_t: template_instance_vertex_t {
        using b = template_instance_vertex_t;

        vertex_t(): b("r2r", __FILE__, "us::gui::activity::listview_controller",
            params_t{
                {"tabico", "R.raw.r2rtab"},
                {"controller", "fragment"},
                {"api__function", "bookmark_list"},
                {"api__datatype", "us.wallet.trader.bookmarks_t"},
                {"api__itemtype", "us.wallet.trader.bookmark_t"},
                {"datatype", ""},
                {"itemtype", ""},
                {"itemico", "R.raw.bookmark"},
                {"title", "Bookmarks"},
                {"item_title", "bookmark"},
                {"with_menu", "true"},
                {"nft_support", "yes"},
                {"nft_type", "String"},
                {"data_identifier", "bookmarks"},
                {"item_button__conf__popup_menu", "yes"},
                {"item_object_id", "app.wallet__node_bookmark__object_id"},

            }) {
            codefiles.add_java();

            //include_java("us.wallet.wallet.timeseries_index_t");
            include_java("static us.gov.crypto.ripemd160.hash_t");
  //          add(new base::vertex_t());

        }

        vertex_t(const vertex_t& other): b(other) {}
        vertex_t* clone() const override { return new vertex_t(*this); }

    };

}}
