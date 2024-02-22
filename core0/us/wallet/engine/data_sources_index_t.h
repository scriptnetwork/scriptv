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
#include <map>
#include <string>
#include <mutex>
#include <functional>

#include <us/gov/config.h>
#include <us/gov/io/seriable_vector.h>
#include <us/gov/io/blob_reader_t.h>

namespace us::wallet::engine {

    struct data_sources_index__item_t: us::gov::io::seriable {
    public:
        void dump(ostream&) const;

    public: //serialization blob
        size_t blob_size() const override;
        void to_blob(blob_writer_t&) const override;
        ko from_blob(blob_reader_t&) override;

    public:
        string name;
        hash_t address;
    };


    struct data_sources_index_t: us::gov::io::seriable_vector<data_sources_index__item_t> {
        using serid_t = us::gov::io::blob_reader_t::serid_t;

        void dump(ostream&) const;

        serid_t serial_id() const override { return 'I'; }
    };

}

