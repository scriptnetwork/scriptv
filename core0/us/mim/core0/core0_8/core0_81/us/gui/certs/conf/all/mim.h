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
#include "base/mim.h"

namespace mim::core0::core0_8::core0_81 {
namespace us::gui::certs::conf::all {

    struct vertex_t: template_instance_vertex_t {
        using b = template_instance_vertex_t;

        vertex_t(): b("all", __FILE__, "us::gui::activity::listview_controller",
            params_t{
                {"controller", "fragment"},
                //{"datatype", "ArrayList<pair<hash_t, string>>"},
                //{"itemtype", "pair<hash_t, string>"},
                {"datatype", ""},
                {"itemtype", ""},
                {"itemico", "R.raw.cert"},
                {"tabico", "R.raw.fairluckico"},
                {"item_name", "cert"},
                {"api__function", "cert_list"},
                {"api__datatype", "us.wallet.trader.cert.cert_index_t"},
                {"api__itemtype", "String"},
                {"data_identifier", "data"},
                {"title", "All"},
                {"item_title", "cert"},
                {"with_menu", "true"},
                {"item_button__conf__popup_menu", "no"},
//                {"nft_support", "yes"},
//                {"nft_type", "String"},
            }) {
            add(new base::vertex_t());
            codefiles.add_java();
            include_java("java.util.ArrayList");
            include_java("us.pair");
            include_java("us.string");
            include_java("java.util.ArrayList");
            include_java("static us.gov.crypto.ripemd160.hash_t");
        }

        vertex_t(const vertex_t& other): b(other) {}
        vertex_t* clone() const override { return new vertex_t(*this); }

    };

}}

