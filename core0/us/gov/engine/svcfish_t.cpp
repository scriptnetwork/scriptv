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
#include <us/gov/config.h>

#include "svcfish_t.h"

#define loglevel "gov/engine"
#define logclass "svcfish_t"
#include "logs.inc"

using namespace us::gov::engine;
using namespace us;

using c = us::gov::engine::svcfish_t;

svc_t c::from_prev(svc_t svc) const {
    auto i = db01.find(svc);
    if (i == db01.end()) {
        return svc;
    }
    return i->second;
}

us::svc_t c::to_prev(svc_t svc) const {
    auto i = db10.find(svc);
    if (i == db10.end()) {
        return svc;
    }
    return i->second;
}

