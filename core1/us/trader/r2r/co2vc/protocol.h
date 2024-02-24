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

#include <us/wallet/trader/trader_t.h>
#include <us/wallet/trader/workflow/trader_protocol.h>
#include <us/wallet/wallet/local_api.h>
#include <us/wallet/trader/chat_t.h>
#include <us/wallet/trader/workflow/business.h>

#include <us/trader/workflow/organization/docs.h>
#include <us/trader/workflow/organization/workflow_t.h>

#include "types.h"

namespace us::trader::r2r::co2vc {

    struct protocol: us::wallet::trader::workflow::trader_protocol {
        using b = us::wallet::trader::workflow::trader_protocol;
        using business_t = us::wallet::trader::workflow::business_t;

        static constexpr const char* name{"co2vc"};

    public:
        protocol(business_t&);
        ~protocol() override;

    public:
        size_t blob_size() const override;
        void to_blob(blob_writer_t&) const override;
        ko from_blob(blob_reader_t&) override;

    public:
        enum push_code_t { //communications node-HMI
            push_begin = trader_protocol::push_r2r_begin,
            push_end
        };

        enum service_t { //communications node-node
            svc_begin = trader_protocol::svc_r2r_begin,
            svc_end
        };

    public:
        void create_workflows(ch_t&) override;
    };

}


