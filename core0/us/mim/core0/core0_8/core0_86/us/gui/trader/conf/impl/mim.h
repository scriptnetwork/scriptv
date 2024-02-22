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
namespace mim::core0::core0_8::core0_86 {
namespace us::gui::trader::conf::impl {

    struct vertex_t: template_instance_vertex_t {
        using b = template_instance_vertex_t;

        vertex_t(): b("impl", __FILE__, "us::gui::activity::fragmented_controller",
            params_t{
                {"controller", "activity"},
                {"scroll", "no"},
                {"title", "Trader"},
                {"with_menu", "false"},
                {"nft_support", "yes"},
                {"nft_type", "String"},
                {"api__function", ""},
                {"api__datatype", ""},
                {"datatype", ""},
                {"data_identifier", ""},
            }) {
            codefiles.add_java();

            custom_params.emplace("fragment1", params_t{
                    {"vertex_path", "../status"},
                    {"tabtype", "static"},
                });
            custom_params.emplace("fragment2", params_t{
                    {"vertex_path", "../personality"},
                    {"tabtype", "static"},
                    {"tabtitle", "Me"},
                });
            custom_params.emplace("fragment3", params_t{
                    {"vertex_path", "../personality"},
                    {"tabtype", "static"},
                    {"tabtitle", "Peer"},
                    {"tabico", "R.raw.personality_peer"},
                });
            custom_params.emplace("fragment4", params_t{
                    {"vertex_path", "../share"},
                    {"tabtype", "static"},
                });
            custom_params.emplace("fragment5", params_t{
                    {"vertex_path", "../chat"},
                    {"tabtype", "static"},
                });
            custom_params.emplace("fragment6", params_t{
                    {"vertex_path", "../r2r"},
                    {"tabtype", "static"},
                });
            custom_params.emplace("fragment7", params_t{
                    {"vertex_path", "../role"},
                    {"tabtype", "dynamic"},
                    {"dyntab_nft_type", "protocol_selection_t"},
                    {"dyntab_nft__to_string_fn", ".to_string()"},

                });

        }

        vertex_t(const vertex_t& other): b(other) {}
        vertex_t* clone() const override { return new vertex_t(*this); }


    };

}}
