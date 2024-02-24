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
#include "activity/mim.h"
#include "device_endpoints/mim.h"
#include "whmi/mim.h"
#include "context/mim.h"

namespace mim::core0::core0_7 {
namespace us::gui {

    struct vertex_t: namespace_t {
        using b = namespace_t;
        vertex_t(): b("gui", __FILE__) {
            add(new context::vertex_t());
            add(new activity::vertex_t());
            add(new app::vertex_t());
            add(new device_endpoints::vertex_t());
            add(new whmi::vertex_t());
        }

    };

}}

