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
#include "app/mim.h"
#include "trades/mim.h"
#include "trader/mim.h"
#include "role/mim.h"
#include "txlog/mim.h"
#include "i18n/mim.h"
#include "r2rlibs/mim.h"

namespace mim::core0::core0_8::core0_86 {
namespace us::gui {

    struct vertex_t: namespace_t {
        using b = namespace_t;
        vertex_t(): b("gui", __FILE__) {
            add(new app::vertex_t());
            add(new trades::vertex_t());
            add(new trader::vertex_t());
            add(new role::vertex_t());
            add(new txlog::vertex_t());
            add(new i18n::vertex_t());
            add(new r2rlibs::vertex_t());
        }
    };

}}
