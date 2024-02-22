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
#include "core0_1/mim.h"
#include "core0_5/mim.h"
#include "core0_6/mim.h"
#include "core0_7/mim.h"
#include "core0_8/mim.h"
#include "core0_9/mim.h"

namespace mim::core0 {

    struct vertex_t: layer_vertex_t {
        using b = layer_vertex_t;
        vertex_t(): b("core0", __FILE__) {
            add(new core0_1::vertex_t());
            add(new core0_5::vertex_t());
            add(new core0_6::vertex_t());
            add(new core0_7::vertex_t());
            add(new core0_8::vertex_t());
            add(new core0_9::vertex_t());
            init(); //call once from top vertex
        }

        float z_order() const override { return 0; }
        string directive{"mass consumption - service to humanity, whales, dogs and insects in the symbiotic mutual service sense"};
    };

}

