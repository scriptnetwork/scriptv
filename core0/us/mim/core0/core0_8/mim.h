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
#include "core0_81/mim.h"
#include "core0_82/mim.h"
#include "core0_83/mim.h"
#include "core0_84/mim.h"
#include "core0_85/mim.h"
#include "core0_86/mim.h"
//#include "core0_87/mim.h"
#include <map>

namespace mim::core0::core0_8 {
    struct vertex_t: layer_vertex_t {
        using b = layer_vertex_t;
        vertex_t(): b("core0_8", __FILE__) {
            add(new core0_81::vertex_t());
            add(new core0_82::vertex_t());
            add(new core0_83::vertex_t());
            add(new core0_84::vertex_t());
            add(new core0_85::vertex_t());
            add(new core0_86::vertex_t());
            //add(new core0_87::vertex_t());
        }
        float z_order() const override { return 0.8; }
        string directive{"wallet"};
    };

}

