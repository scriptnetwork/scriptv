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


#pragma once
#include <string>

#include <us/gov/io/factory.h>

#include <us/wallet/trader/workflow/business.h>
#include <us/wallet/trader/ch_t.h>
#include <us/wallet/trader/protocol_selection_t.h>
#include <us/wallet/wallet/local_api.h>
#include <us/wallet/trader/workflow/workflow_t.h>

#include "types.h"

#include "protocol.h"

namespace us::trader::r2r::co2vc {

    struct business_t: us::wallet::trader::workflow::business_t {
        using b = us::wallet::trader::workflow::business_t;
        using ch_t = us::wallet::trader::ch_t;
        using workflow_t = us::wallet::trader::workflow::workflow_t;

    public:
        business_t();
        ~business_t() override;

    public:

        bool invert(protocol_selection_t&) const override;

    };

}


