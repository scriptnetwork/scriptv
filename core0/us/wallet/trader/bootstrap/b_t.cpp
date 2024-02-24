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
#include "b_t.h"
#include <us/gov/crypto/base58.h>
#include <us/gov/ko.h>
#include <cassert>
#include <sstream>

#define loglevel "wallet/trader/bootstrap"
#define logclass "b_t"
#include <us/gov/logs.inc>

using namespace std;
using namespace us;
using namespace us::wallet::trader::bootstrap;

b1_t::b1_t() {
}

b1_t::b1_t(const protocol_selection_t& protocol_selection, const params_t& params): protocol_selection(protocol_selection), params(params) {
}

size_t b1_t::blob_size() const {
    return blob_writer_t::blob_size(protocol_selection) +
        blob_writer_t::blob_size(params);
}

void b1_t::to_blob(blob_writer_t& writer) const {
    writer.write(protocol_selection);
    writer.write(params);
}

ko b1_t::from_blob(blob_reader_t& reader) {
    {
        auto r = reader.read(protocol_selection);
        if (is_ko(r)) return r;
    }
    {
        auto r = reader.read(params);
        if (is_ko(r)) return r;
    }
    return ok;
}

//=_===================================================

b2_t::b2_t() {
}

b2_t::b2_t(personality_proof_t&& personality_proof, params_t&& params): params(move(params)), personality_proof(move(personality_proof)) {
}

b2_t::b2_t(personality_proof_t&& personality_proof): personality_proof(move(personality_proof)) {
}

size_t b2_t::blob_size() const {
    return blob_writer_t::blob_size(params) +
        blob_writer_t::blob_size(personality_proof);
}

void b2_t::to_blob(blob_writer_t& writer) const {
    writer.write(params);
    writer.write(personality_proof);
}

ko b2_t::from_blob(blob_reader_t& reader) {
    {
        auto r = reader.read(params);
        if (is_ko(r)) return r;
    }
    {
        auto r = reader.read(personality_proof);
        if (is_ko(r)) return r;
    }
    return ok;
}
//=_===================================================

b3_t::b3_t() {
}

b3_t::b3_t(params_t&& params): params(move(params)) {
}

size_t b3_t::blob_size() const {
    return blob_writer_t::blob_size(params);
}

void b3_t::to_blob(blob_writer_t& writer) const {
    writer.write(params);
}

ko b3_t::from_blob(blob_reader_t& reader) {
    {
        auto r = reader.read(params);
        if (is_ko(r)) return r;
    }
    return ok;
}

