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
#include <us/wallet/trader/trader_t.h>
#include <us/wallet/trader/business.h>
#include <us/wallet/trader/params_t.h>
#include <us/wallet/trader/workflow/item_t.h>
#include <us/trader/r2r/co2vc/protocol.h>
#include <us/trader/r2r/co2vc/types.h>

namespace us::trader::r2r::co2vc::co {

    struct business_t;

    struct protocol: co2vc::protocol {
        using b = co2vc::protocol;
        using business_t = us::trader::r2r::co2vc::co::business_t;
        using item_t = us::wallet::trader::workflow::item_t;

    public:
        protocol(business_t&);
        ~protocol() override;

    public:
        size_t blob_size() const override;
        void to_blob(blob_writer_t&) const override;
        ko from_blob(blob_reader_t&) override;
        factory_id_t factory_id() const override;

    public:
        void init_workflows(ch_t&) override;

    public:
        void dump(ostream&) const override;
        void list_trades_bit(ostream&) const override;
        const char* rolestr() const override;
        const char* peer_rolestr() const override;
        const char* get_name() const override;
        chat_t::entry AI_chat(const chat_t&, peer_t&) override;
        ko on_remote_(params_t*, ch_t&) override;
        uint32_t trade_state_() const;
        void judge(const string& lang) override;
        void on_file_updated(const string& path, const string& name, ch_t&) override;

    };

}


