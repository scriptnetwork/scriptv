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
#include <iostream>

#include <us/gov/io/seriable.h>
#include <us/gov/crypto/ec.h>
#include <us/gov/crypto/ripemd160.h>

namespace us::gov::dfs {

    using namespace std;

    struct fileattr_t: io::seriable {

        fileattr_t();
        fileattr_t(const string& path, uint32_t sz);
        fileattr_t(const fileattr_t& other): path(other.path), sz(other.sz) {}

        void hash_data_to_sign(crypto::ec::sigmsg_hasher_t&) const;
        void hash_data(crypto::ripemd160&) const;
        void dump(ostream&) const;

    public:
        size_t blob_size() const override;
        void to_blob(blob_writer_t&) const override;
        ko from_blob(blob_reader_t&) override;

    public:
        uint32_t sz;
        string path;

    };

}

