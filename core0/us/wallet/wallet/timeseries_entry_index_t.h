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

#include <us/gov/config.h>
#include <us/gov/io/seriable_vector.h>
#include <us/gov/io/blob_reader_t.h>

namespace us::wallet::wallet {

    struct timeseries_entry_index_t: us::gov::io::seriable_vector<uint64_t> {
        using serid_t = us::gov::io::blob_reader_t::serid_t;

        void dump(ostream&) const;

        void add(uint64_t&& a) {
            emplace_back(move(a));
        }

        //serid_t serial_id() const override { return 'T'; }
    };

}

