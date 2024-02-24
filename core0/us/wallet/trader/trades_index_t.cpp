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
#include "trades_index_t.h"

#define loglevel "wallet/trader"
#define logclass "trades_index_t"
#include <us/gov/logs.inc>

using namespace us::wallet::trader;
using d = us::wallet::trader::trader_brief_t;

size_t d::blob_size() const {
    auto sz = blob_writer_t::blob_size(tid)
    + blob_writer_t::blob_size(ts_creation)
    + blob_writer_t::blob_size(ts_activity)
    + blob_writer_t::blob_size(subhome)
    + blob_writer_t::blob_size(qr);
    log("blob_size", sz);
    return sz;
}

void d::to_blob(blob_writer_t& writer) const {
    log("to_blob", "cur", (uint64_t)(writer.cur - writer.blob.data()));
    writer.write(tid);
    writer.write(ts_creation);
    writer.write(ts_activity);
    writer.write(subhome);
    writer.write(qr);
}

ko d::from_blob(blob_reader_t& reader) {
    log("from_blob", "cur", (uint64_t)(reader.cur - reader.blob.data()));
    {
        auto r = reader.read(tid);
        if (is_ko(r)) {
            return r;
        }
    }
    {
        auto r = reader.read(ts_creation);
        if (is_ko(r)) {
            return r;
        }
    }
    {
        auto r = reader.read(ts_activity);
        if (is_ko(r)) {
            return r;
        }
    }
    {
        auto r = reader.read(subhome);
        if (is_ko(r)) {
            return r;
        }
    }
    {
        auto r = reader.read(qr);
        if (is_ko(r)) {
            return r;
        }
    }
    return ok;
}

/*
void c::dump(const string& pfx, ostream& os) const {
    lock_guard<mutex> lock(mx);
    for (auto& i: *this) {
        os << pfx << i.first << ' ' << i.second << '\n';
    }
}
*/

