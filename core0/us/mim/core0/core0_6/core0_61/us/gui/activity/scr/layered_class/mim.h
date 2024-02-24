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
#pragma once

namespace mim::core0::core0_6::core0_61 {
namespace us::gui::activity::scr::layered_class {

    struct vertex_t: mim::core0::core0_6::core0_61::us::gui::layered_class::vertex_t {
        using b = mim::core0::core0_6::core0_61::us::gui::layered_class::vertex_t;

        vertex_t(): b("layered_class", __FILE__, "us::gui::activity::scr::layered_class") {
            codefiles.add_java();
        }

        params_t default_params() const override {
            return params_t {
                {"deepest_baseclass", "LinearLayout"},
            };
        }

    };

}}
