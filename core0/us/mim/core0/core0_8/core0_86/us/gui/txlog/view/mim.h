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
namespace us::gui::txlog::view {

    struct vertex_t: template_instance_vertex_t {
        using b = template_instance_vertex_t;

        vertex_t(): b("view", __FILE__, "us::gui::activity::scr::list_view::view",
            params_t{
                {"datatype", "txlog__view__datatype_t"},
                {"itemtype", "txlog__view__itemtype_t"},
                {"itemico", "R.raw.tx_in"},
                {"item_title", "transaction"},
                {"nft_support", "yes"},
                {"nft_type", "String"},
                {"data_identifier", "txs"},
                {"item_button__conf__popup_menu", "yes"},
                {"item_object_id", "app.tx__object_id"},

            }) {
            codefiles.add_java();

        }

        vertex_t(const vertex_t& other): b(other) {}
        vertex_t* clone() const override { return new vertex_t(*this); }

    };
    
}}

