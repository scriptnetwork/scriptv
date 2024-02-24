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
#include "impl/mim.h"
#include "status/mim.h"
#include "personality/mim.h"
#include "share/mim.h"
#include "chat/mim.h"
#include "r2r/mim.h"
#include "role/mim.h"


namespace mim::core0::core0_8::core0_86 {
namespace us::gui::trader::conf {

    struct vertex_t: layered_class_vertex_t {
        using b = layered_class_vertex_t;

        vertex_t(): b("conf", vector<string>{
                    "_s_status",
                    "_s_personality",
                    "_s_share",
                    "_s_chat",
                    "_s_r2r",
                    "_s_role",
                    }, __FILE__) {

            deepest_baseclass = "trader__conf__impl";
            subhome = "";
            codefiles.add_java();
//            codefiles.classname_alg = codefiles_t::vertex_name;
//            add(new menu__main::vertex_t());
            add(new impl::vertex_t());

            add(new status::vertex_t());
            add(new personality::vertex_t());
            add(new share::vertex_t());
            add(new chat::vertex_t());
            add(new r2r::vertex_t());
            add(new role::vertex_t());

        }

        vertex_t(const vertex_t& other): b(other) {}
        vertex_t* clone() const override { return new vertex_t(*this); }

    };

}}

