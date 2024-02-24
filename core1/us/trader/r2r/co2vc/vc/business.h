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
#include <unordered_map>
#include <map>
#include <sstream>
#include <mutex>
#include <chrono>
#include <vector>
#include <fstream>

#include <us/gov/engine/signed_data.h>
#include <us/gov/socket/datagram.h>
#include <us/trader/r2r/co2vc/business.h>
#include <us/wallet/trader/trader_t.h>
#include <us/wallet/trader/workflow/doctype_processors_t.h>

#include "protocol.h"

namespace us::trader::r2r::co2vc::vc {

    struct business_t: us::trader::r2r::co2vc::business_t {
        using b = us::trader::r2r::co2vc::business_t;
        using hash_t = us::gov::crypto::ripemd160::value_type;
        using datagram = us::gov::socket::datagram;
        using protocol = us::trader::r2r::co2vc::vc::protocol;
        using doctype_processors_t = us::wallet::trader::workflow::doctype_processors_t;

    public:
        business_t();
        ~business_t() override;

    public:
        protocol_factory_id_t protocol_factory_id() const;
        void register_factories(protocol_factories_t&) override;

    public:
        ko init(const string& r2rhome, protocol_factories_t&) override;
        string homedir() const override;

        pair<ko, trader_protocol*> create_protocol() override;
        void list_protocols(ostream&) const override; //human format
        void published_protocols(protocols_t&, bool inverse) const override; //serialization format

    public:
        doctype_processors_t doctype_processors;

    };

}


