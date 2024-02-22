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
#include <us/gov/engine/app.h>
#include <us/gov/engine/merger.h>
#include <us/gov/peer/account_t.h>
#include <us/gov/peer/nodes_t.h>
#include <us/gov/types.h>

namespace us::gov::engine::auth {

    using account_t = peer::account_t;
    using nodes_t = peer::nodes_t;

    struct delta final: engine::app::delta {
        using b = engine::app::delta;

        delta() {}
        ~delta() override {}
        delta(const delta&) = delete;
        delta& operator = (const delta&) = delete;
        uint64_t merge(engine::app::local_delta*) override;
        void end_merge() override;

        struct merger: map<hash_t, engine::majority_merger<account_t>> {
            void merge(const nodes_t&);
            void end_merge(nodes_t&);
        };

    public:
        void dump(const string& prefix, ostream&) const override;

    public:
        size_t blob_size() const override;
        void to_blob(blob_writer_t&) const override;
        ko from_blob(blob_reader_t&) override;

    public:
        nodes_t online;
        merger online_merger;
    };

}

