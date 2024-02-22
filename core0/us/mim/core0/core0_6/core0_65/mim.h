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
#include "us/mim.h"
namespace mim::core0::core0_6::core0_65 {

    struct vertex_t: root_vertex_t {
        using b = root_vertex_t;
        vertex_t(): b("core0_65", __FILE__) {
            add(new us::vertex_t());
        }

        float z_order() const override { return 0.65; }

        //string layer_id{"front"};
        //string layer_name{"Front door aka user interface. minimal."};
        string directive{"minimum GUI. Front door. User Interface. minimal. featureless. Multi target {android, ios, desktop{gtk}}. Only features related to the cli app/program itself, like visualization settings and language"};
    };

}

