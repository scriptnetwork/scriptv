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
#include <us/gov/config.h>
#include <us/gov/ko.h>

#include "data_sources_index_t.h"

#define loglevel "wallet/engine"
#define logclass "data_sources_index_t"
#include <us/gov/logs.inc>

using namespace std;
using namespace us;
using namespace us::wallet::engine;
using c = us::wallet::engine::data_sources_index_t;

void c::dump(ostream& os) const {
    //string pfx = "    ";
    for (auto& i: *this) {
        os << i.name << ' ' << i.address << '\n';
    }
}

void data_sources_index__item_t::dump(ostream& os) const {
    os << name << ' ' << address << '\n';
}

size_t data_sources_index__item_t::blob_size() const {
    auto sz = blob_writer_t::blob_size(name) + blob_writer_t::blob_size(address);
    log("blob_size", sz);
    return sz;
}

void data_sources_index__item_t::to_blob(blob_writer_t& writer) const {
    log("to_blob", "cur", (uint64_t)(writer.cur - writer.blob.data()));
    writer.write(name);
    writer.write(address);
}

ko data_sources_index__item_t::from_blob(blob_reader_t& reader) {
    log("from_blob", "cur", (uint64_t)(reader.cur - reader.blob.data()));
    {
        auto r = reader.read(name);
        if (is_ko(r)) {
            return r;
        }
    }
    {
        auto r = reader.read(address);
        if (is_ko(r)) {
            return r;
        }
    }
    return ok;
}
