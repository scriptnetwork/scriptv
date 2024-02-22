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
#include <us/gov/engine/evidence.h>

namespace us::gov::engine::auth {

    struct node_address_tx final: engine::evidence {
        using b = engine::evidence;

        static constexpr eid_t eid{0};

        node_address_tx();
        node_address_tx(const hash_t& pkh, uint32_t net_addr, uint16_t pport);
        void write_pretty_en(ostream&) const override;
        void write_pretty_es(ostream&) const override;
        string name() const override { return "auth::app::node_address"; }
        void dump(const string& prefix, ostream&) const;
        uint64_t uniq() const override { return 0; }

    public:
        size_t blob_size() const override;
        void to_blob(blob_writer_t&) const override;
        ko from_blob(blob_reader_t&) override;

    public:
        hash_t pkh;
        uint32_t net_addr;
        uint16_t pport;
    };

}

