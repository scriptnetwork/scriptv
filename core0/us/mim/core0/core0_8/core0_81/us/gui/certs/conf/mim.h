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
#include "all/mim.h"

namespace mim::core0::core0_8::core0_81 {
namespace us::gui::certs::conf {

    struct vertex_t: template_instance_vertex_t {
        using b = template_instance_vertex_t;

        vertex_t(): b("conf", __FILE__, "us::gui::activity::fragmented_controller",
            params_t{
                {"controller", "activity"},
                {"title", "Certificates"},
                {"with_menu", "false"},
                {"nft_support", "yes"},
                {"nft_type", "String"},

            }) {
            codefiles.add_java();

            custom_params.emplace("fragment1", params_t{
                    {"vertex_path", "all"},
                    {"tabtype", "static"},
                    {"tabtitle", "All"},
                    {"static__constructor_args", "0"},
                });

            custom_params.emplace("fragment2", params_t{
                    {"vertex_path", "all"},
                    {"tabtype", "static"},
                    {"tabtitle", "SoA"},
                    {"static__constructor_args", "1"},
                });

            add(new all::vertex_t());

        }

        vertex_t(const vertex_t& other): b(other) {}
        vertex_t* clone() const override { return new vertex_t(*this); }


    };

}}

