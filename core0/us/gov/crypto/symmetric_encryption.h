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
#include <string>
#include <stdio.h>
#include <crypto++/gcm.h>
#include <crypto++/aes.h>
#include <crypto++/osrng.h>

#include <us/gov/config.h>

#include "ec.h"

namespace us::gov::crypto {

    using namespace std;
    using CryptoPP::AutoSeededRandomPool;
    using CryptoPP::GCM;
    using CryptoPP::AES;

    struct symmetric_encryption {
        using keys = ec::keys;
        using byte = unsigned char;

        static constexpr size_t key_size = AES::DEFAULT_KEYLENGTH; //16;  // AES-128
        static constexpr size_t iv_size = 12; // Recommended for GCM
        static constexpr int tag_size = 16;   // Authentication tag size -MAC-

        ko init(const keys::priv_t&, const keys::pub_t&);
        ko encrypt(const vector<unsigned char>& cleartext_buf, vector<unsigned char>& ciphertext, size_t ciphertext_offset);

        ko decrypt(const unsigned char* p, size_t sz, vector<unsigned char>& dest);
        ko decrypt(const vector<unsigned char>& ciphertext, vector<unsigned char>& clear);

        AutoSeededRandomPool prng_;
        byte key_[key_size];
    };

}

