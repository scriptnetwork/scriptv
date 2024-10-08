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
#include <functional>
#include <random>

#include <us/gov/config.h>
#include <us/gov/auth/peer_t.h>
#include <us/gov/engine/daemon_t.h>

#include "app.h"
#include "wallet_address.h"
#include "types.h"
#include "local_delta.h"

#define loglevel "gov/traders"
#define logclass "local_delta"
#include <us/gov/logs.inc>

using namespace us::gov::traders;
using c = us::gov::traders::local_delta;

appid_t c::app_id() const {
    return traders::app::id();
}

c::local_delta() {
}

c::local_delta(const local_delta& other): online(other.online) {
}

c::~local_delta() {
}

void c::hash_data_to_sign(sigmsg_hasher_t& h) const {
    online.hash_data_to_sign(h);
}

void c::hash_data(hasher_t& h) const {
    online.hash_data(h);
}

void c::dump(const string& prefix, ostream& os) const {
    os << prefix << "traders online:\n";
    online.dump(prefix + "    ", os);
}

size_t c::blob_size() const {
    return blob_writer_t::blob_size(online);
}

void c::to_blob(blob_writer_t& writer) const {
    writer.write(online);
}

ko c::from_blob(blob_reader_t& reader) {
    {
        auto r = reader.read(online);
        if (is_ko(r)) return r;
    }
    return ok;
}

