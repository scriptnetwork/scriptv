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

#include "menu__main/mim.h"

namespace mim::core0::core0_8::core0_83 {
namespace us::gui::coins::merged_accounts {

    struct vertex_t: template_instance_vertex_t {
        using b = template_instance_vertex_t;

        vertex_t(): b("merged_accounts", "us::gui::activity::fragment::listview_fragment::template1",
            map<string, string>{
                {"MIM file", __FILE__},
                {"datatype", "accounts_t"},
                {"itemtype", "account_t"},
                {"title", ""},
            }) {
            codefiles.add_java();
            codefiles._mim_srcdir = "core0/core0_8/core0_83/us/gui/coins/merged_accounts";
            codefiles.kickoff_code_diff = true;
            //include_java("java.util.ArrayList");
            //include_java("us.pair");
            //include_java("us.string");
            //include_java("java.util.ArrayList");
            //include_java("static us.gov.crypto.ripemd160.hash_t");
            include_java("us.gov.cash.account_t");
            include_java("us.gov.cash.accounts_t");
            add(new menu__main::vertex_t());

        }

        vertex_t(const vertex_t& other): b(other) {}
        vertex_t* clone() const override { return new vertex_t(*this); }

    };
}}
