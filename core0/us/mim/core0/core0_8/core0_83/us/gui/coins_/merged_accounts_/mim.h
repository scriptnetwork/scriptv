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

namespace mim::core0::core0_8::core0_83 {
namespace us::gui::coins::merged_accounts {

    struct vertex_t: template_instance_vertex_t {
        using b = template_instance_vertex_t;
        vertex_t(): b("merged_accounts", "us::gui::activity::fragment::listview_fragment::template1",
            map<string, string>{
                {"datatype", "ArrayList<item_t>"},
                {"itemtype", "item_t"},
                {"title", "balances"},
                {"resiconmenu150", "R.raw.businesses_150"},
                {"MIM file", __FILE__}
            }) {
//#### = 
//##menutitle1## = "Businesses"
//##menutitle2## = ""
//Exit activity -> Exit businesses 
//                    g0.add(R.raw.bnew, "item1");
//                    g0.add(R.raw.log, "item2");
//              =>
        }

        vertex_t(const vertex_t& other): b(other) {}
        vertex_t* clone() const override { return new vertex_t(*this); }

    };
}}
