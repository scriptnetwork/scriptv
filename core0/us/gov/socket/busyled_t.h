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
#include <atomic>
#include "types.h"

namespace us::gov::socket {

    struct busyled_t {

        busyled_t();
        ~busyled_t();

        struct handler_t {
            virtual ~handler_t() {}
            virtual void on_busy() = 0;
            virtual void on_idle() = 0;
        };

        void set();
        void reset();
        void set_handler(handler_t*);

    private:
        handler_t* handler{nullptr};
        atomic<int> ref{0};
    };

}

