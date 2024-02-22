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
#include <string>
#include <map>
#include <vector>
#include <mutex>

#include <us/gov/cash/tx_t.h>
#include <us/gov/socket/types.h>
#include <us/gov/io/seriable_map.h>
#include <us/gov/io/seriable_vector.h>

#include "txlog_item_t.h"

namespace us::wallet::wallet {

//    struct protocol;
    struct local_api;

    struct index_item_t: us::gov::io::seriable {
        using gov_track_status_t = us::gov::engine::track_status_t;
        using gov_evt_status_t = us::gov::engine::evt_status_t;

        index_item_t() {}
        index_item_t(const string& label, gov_evt_status_t gov_evt_status, const string& gov_evt_status_info, wallet_evt_status_t wallet_evt_status): label(label), gov_evt_status(gov_evt_status), gov_evt_status_info(gov_evt_status_info), wallet_evt_status(wallet_evt_status) {}

    public: //serialization
        size_t blob_size() const override;
        void to_blob(blob_writer_t&) const override;
        ko from_blob(blob_reader_t&) override;

    public:
        void dump(const string& pfx, ostream&) const;

    public:
        string label;
        wallet_evt_status_t wallet_evt_status;
        gov_evt_status_t gov_evt_status;
        string gov_evt_status_info;
    };

    struct index_t: us::gov::io::seriable_vector<pair<track_t, index_item_t>> {
        void dump(const string& prefix, ostream&) const;
    };

    struct txlog_t: map<track_t, txlog_item_t> {
        using b = map<track_t, txlog_item_t>;
        using gov_track_status_t = us::gov::engine::track_status_t;

        txlog_t(): w(nullptr) {}
        txlog_t(local_api& w): w(&w) {}

        track_t register_transfer(const cash_t& amount, const hash_t& coin, const trade_id_t&, blob_t&);
        ko register_transfer(const blob_t&, const trade_id_t&, blob_t& response_blob);

        ko register_invoice(const blob_t&);
        ko register_tx(const blob_t&);

        ko pay_inv(track_t, blob_t&);

        ko cancel(const track_t& track);
        ko cancel(const track_t&, blob_t&);
        ko cancel(const blob_t&);

        void show(const trade_id_t&) const;
        index_t index() const;

        pair<ko, tx_t*> get_invoice(const track_t&) const; //returns a modifiable copy of the invoice that must be deleted by the caller

        void dump(const string& prefix, ostream&) const;

        void on_tx_tracking_status(const gov_track_status_t&);

    public:
        mutable mutex mx;
        local_api* w;
    };

}

