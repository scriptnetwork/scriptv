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
#include "base/mim.h"

namespace mim::core0::core0_8::core0_84 {
namespace us::gui::iot::conf::sources {

    struct vertex_t: template_instance_vertex_t {
        using b = template_instance_vertex_t;

        vertex_t(): b("sources", __FILE__, "us::gui::activity::listview_controller",
            params_t{
                {"tabico", "R.raw.streams"},
                {"controller", "fragment"},
                {"api__function", "data_sources"},
                {"api__datatype", "data_sources_index_t"},
                {"api__itemtype", "data_sources_index__item_t"},
                {"itemico", "R.raw.streams"},
                {"title", "Source devices"},
                {"item_title", "data source"},
                {"with_menu", "true"},
                {"nft_support", "yes"},
                {"nft_type", "String"},
                {"data_identifier", "sources"},
                {"item_button__conf__popup_menu", "yes"},
                {"item_object_id", "app.source_device__connected__object_id"},
            }) {
            codefiles.add_java();

            include_java("us.wallet.engine.data_sources_index_t");
            include_java("us.wallet.engine.data_sources_index__item_t");
            add(new base::vertex_t());

        }

        vertex_t(const vertex_t& other): b(other) {}
        vertex_t* clone() const override { return new vertex_t(*this); }

    };

}}
