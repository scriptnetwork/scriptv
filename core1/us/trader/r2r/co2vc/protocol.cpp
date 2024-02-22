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


#include "protocol.h"
#include <us/wallet/trader/workflow/workflows_t.h>

#define loglevel "trader/r2r/co2vc"
#define logclass "protocol"
#include <us/gov/logs.inc>

using namespace us::trader::r2r::co2vc;
using c = us::trader::r2r::co2vc::protocol;

c::protocol(business_t& bz): b(bz) {
}

c::~protocol() {
}

void c::create_workflows(ch_t& ch) {
}

size_t c::blob_size() const {
    size_t sz = b::blob_size();
    log("blob_size", sz);
    return sz;
}

void c::to_blob(blob_writer_t& writer) const {
    log("to_blob", "cur", (uint64_t)(writer.cur - writer.blob.data()));
    b::to_blob(writer);
}

ko c::from_blob(blob_reader_t& reader) {
    log("from_blob", "cur", (uint64_t)(reader.cur - reader.blob.data()));
    {
        auto r = b::from_blob(reader);
        if (is_ko(r)) {
            return r;
        }
    }
    return ok;
}


