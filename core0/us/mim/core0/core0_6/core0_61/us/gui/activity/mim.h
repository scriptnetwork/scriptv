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
#include "scr/mim.h"
//#include "scroll_activity/mim.h"
#include "impl_/mim.h"
#include "controller/mim.h"
#include "listview_controller/mim.h"
#include "listconf/mim.h"
#include "fragment/mim.h"
#include "fragmented_controller/mim.h"
//#include "tabsconf/mim.h"

namespace mim::core0::core0_6::core0_61 {
namespace us::gui::activity {

    struct vertex_t: android_vertex_t {
        using b = android_vertex_t;
        vertex_t(): b("activity", __FILE__) {
            add(new impl_::vertex_t());
            add(new fragment::vertex_t());
//            add(new scroll_activity::vertex_t());
            add(new scr::vertex_t());
            add(new controller::vertex_t());
            add(new listview_controller::vertex_t());
            add(new fragmented_controller::vertex_t());
            add(new listconf::vertex_t());
//            add(new tabsconf::vertex_t());
            codefiles.add_java();
            codefiles.classname_alg = codefiles_t::vertex_name;
        }

        vertex_t(const vertex_t& other): b(other) {}
        vertex_t* clone() const override { return new vertex_t(*this); }

    };

}}

