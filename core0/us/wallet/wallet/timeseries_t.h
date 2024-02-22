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
#include <functional>
#include <map>

#include <us/gov/config.h>
#include <us/gov/io/blob_reader_t.h>
#include <us/wallet/trader/cert/cert_t.h>

namespace us::wallet::wallet {

    using namespace std;

    struct timeseries_entry_t {
        virtual ~timeseries_entry_t() {}

        static timeseries_entry_t* load(const string& home, ts_t ts);

        virtual void append(ostream&) const = 0;
    };

    struct timeseries_entry__bin_t: timeseries_entry_t {
        timeseries_entry__bin_t(vector<uint8_t>&&);
        ~timeseries_entry__bin_t() override {}
        static timeseries_entry_t* load(const string& home, ts_t ts);

        void append(ostream&) const override;
        vector<uint8_t> payload;
    };

    struct timeseries_entry__cert_t: timeseries_entry_t {
        using cert_t = us::wallet::trader::cert::cert_t;
        timeseries_entry__cert_t(cert_t*);
        ~timeseries_entry__cert_t() override;
        static timeseries_entry_t* load(const string& home, ts_t ts);

        void append(ostream&) const override;
        cert_t* cert;
    };

    struct timeseries_t final: map<ts_t, timeseries_entry_t*> {

        timeseries_t(const string& home);
        ~timeseries_t();

        string payload() const;

        ko load(ts_t ts_from, ts_t ts_to);
        string home;
    };

}

