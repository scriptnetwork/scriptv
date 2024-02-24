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
#include "menu__main/mim.h"

namespace mim::core0::core0_8::core0_83 {
namespace us::gui::accounts::conf::merged {

    struct vertex_t: template_instance_vertex_t {
        using b = template_instance_vertex_t;

        vertex_t(): b("merged", __FILE__, "us::gui::activity::controller",
            params_t{
                {"controller", "fragment"},
                {"scroll", "yes"},
                {"api__function", "merge_accounts"},
                {"api__datatype", "us.gov.cash.account_t"},
                {"datatype", "account_t"},
                {"data_identifier", "acc"},
                {"title", "Balance"},
                {"with_menu", "true"},
                {"tabico", "R.raw.wallet"},
//                {"nft_support", "yes"},
            }) {
//            add(new base::vertex_t());
            codefiles.add_java();
//            include_java("java.util.ArrayList");
//            include_java("us.pair");
            //include_java("us.gov.cash.account_t");
//            include_java("java.util.ArrayList");
            //include_java("static us.gov.crypto.ripemd160.hash_t");
            add(new menu__main::vertex_t());
        }

        vertex_t(const vertex_t& other): b(other) {}
        vertex_t* clone() const override { return new vertex_t(*this); }

    };

}}

