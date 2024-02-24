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
// -----------------------------------
// ---- DO NOT EDIT ------------------
// -----------------------------------
// -- file generated from             
// --   poc_spec/co2vc/dynamics
// -- by                              
// --   make.sh
// -----------------------------------


#include "business.h"
#include "protocol.h"

#define loglevel "trader/r2r/co2vc"
#define logclass "business_t"
#include <us/gov/logs.inc>

using namespace us::trader::r2r::co2vc;
using c = us::trader::r2r::co2vc::business_t;

c::business_t() {
}

c::~business_t() {
}

bool c::invert(protocol_selection_t& i) const {
    if (i.first != protocol::name) return false;
    if (i.second == "vc") {
        i.second = "co";
        return true;
    }
    if (i.second == "co") {
        i.second = "vc";
        return true;
    }
    return false;
}


