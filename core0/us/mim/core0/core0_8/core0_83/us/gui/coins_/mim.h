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
#include "merged_accounts/mim.h"
#include "account_breakdown/mim.h"

namespace mim::core0::core0_8::core0_83 {
namespace us::gui::coins {
    struct vertex_t: android_vertex_t {
        using b = android_vertex_t;
        vertex_t(): b("coins") {
            codefiles.add_java();
            add(_merged_accounts);
            add(_account_breakdown);
        }

        merged_accounts::vertex_t _merged_accounts;
        account_breakdown::vertex_t _account_breakdown;
    };
}}
