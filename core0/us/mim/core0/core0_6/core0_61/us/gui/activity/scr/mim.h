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
#include "view__widgets/mim.h"
#include "button/mim.h"
#include "menu/mim.h"
#include "toolbar/mim.h"
#include "edit_text/mim.h"
#include "text_view/mim.h"
#include "canvas/mim.h"
#include "screen/mim.h"
#include "scroll_view/mim.h"
#include "tabs/mim.h"
#include "frame/mim.h"
#include "drawer/mim.h"
#include "spinner/mim.h"
#include "list_view/mim.h"

namespace mim::core0::core0_6::core0_61 {
namespace us::gui::activity::scr {

    struct vertex_t: android_vertex_t {
        using b = android_vertex_t;
        vertex_t(): b("scr", __FILE__) {
            add(new view__widgets::vertex_t());
            add(new button::vertex_t());
            add(new menu::vertex_t());
            add(new toolbar::vertex_t());
            add(new edit_text::vertex_t());
            add(new text_view::vertex_t());
            add(new canvas::vertex_t());
            add(new screen::vertex_t());
            add(new scroll_view::vertex_t());
            add(new tabs::vertex_t());
            add(new frame::vertex_t());
            add(new drawer::vertex_t());
            add(new spinner::vertex_t());
            add(new list_view::vertex_t());
            codefiles.add_java();
            codefiles.classname_alg = codefiles_t::vertex_name;
        }
    };

}}
