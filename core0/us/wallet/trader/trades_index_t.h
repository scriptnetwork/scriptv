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

#include <us/gov/ko.h>
#include <us/gov/io/seriable_vector.h>
#include <us/gov/socket/types.h>

#include "qr_t.h"

namespace us::wallet::trader {

    struct trader_brief_t: us::gov::io::seriable {
        using b = us::gov::io::seriable;
        using ts_t = us::gov::socket::ts_t;

    public: //serialization blob
        size_t blob_size() const override;
        void to_blob(blob_writer_t&) const override;
        ko from_blob(blob_reader_t&) override;

    public:
        hash_t tid;
        ts_t ts_creation;
        ts_t ts_activity;
        string subhome;
        qr_t qr;
    };


    struct trades_index_t: us::gov::io::seriable_vector<trader_brief_t> {
        using b = us::gov::io::seriable_vector<trader_brief_t>;
        
    };

}

