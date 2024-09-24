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
#include "symmetric_encryption.h"

#include <iterator>

#include <us/gov/likely.h>

#include <secp256k1_ecdh.h>
#include <crypto++/cryptlib.h>
#include <crypto++/filters.h>

#define loglevel "gov/crypto"
#define logclass "symmetric_encryption"
#include "logs.inc"

using namespace us::gov::crypto;
using c = us::gov::crypto::symmetric_encryption;
using us::ko;
using CryptoPP::BufferedTransformation;
using CryptoPP::AuthenticatedSymmetricCipher;
using CryptoPP::Redirector;
using CryptoPP::StringSink;
using CryptoPP::StringSource;
using CryptoPP::ArraySink;
using CryptoPP::ArraySource;
using CryptoPP::AuthenticatedEncryptionFilter;
using CryptoPP::AuthenticatedDecryptionFilter;

ko c::init(const keys::priv_t& sk, const keys::pub_t& pub_other) {
    log("init. pub_other:", pub_other);
    ko r = ec::instance.generate_shared_key(key_, sizeof(key_), sk, pub_other);
    if (unlikely(is_ko(r))) {
        log(r);
        return r;
    }
    return ok;
}

ko c::encrypt(const vector<unsigned char>& cleartext, vector<unsigned char>& ciphertext, size_t ciphertext_offset) {
    log("encrypt block of", cleartext.size(), "bytes. dst_offset", ciphertext_offset);
    ciphertext.resize(ciphertext_offset + iv_size + cleartext.size() + tag_size); //when using authenticated encryption modes like GCM, the size of the encrypted data (ciphertext) minus the IV and tag should always equal the size of the cleartext
    auto iv = ciphertext.data() + ciphertext_offset;
    try {
        prng_.GenerateBlock(iv, iv_size);
        GCM<AES>::Encryption enc;
        enc.SetKeyWithIV(key_, key_size, iv, iv_size);
        ArraySink sink(iv + iv_size, cleartext.size() + tag_size);
        ArraySource(cleartext.data(), cleartext.size(), true, new AuthenticatedEncryptionFilter(enc, new Redirector(sink), false, tag_size));
        log("encrypted sz", ciphertext.size(), "bytes.");
    }
    catch(CryptoPP::InvalidArgument& e) {
        auto r = "KO 30921 Invalid argument in encryption.";
        log(r, e.what());
        return r;
    }
    return ok;
}

ko c::decrypt(const vector<unsigned char>& ciphertext, vector<unsigned char>& cleartext) {
    return decrypt(ciphertext.data(), ciphertext.size(), cleartext);
}

ko c::decrypt(const unsigned char* ciphertext, size_t ciphertext_sz, vector<unsigned char>& cleartext) {
    if (ciphertext_sz < (iv_size + tag_size)) [[unlikely]] {
        auto r = "KO 44031 Message too small to decrypt.";
        log(r);
        return r;
    }
    size_t msg_sz = ciphertext_sz - iv_size - tag_size;
    log("decrypt block of ", ciphertext_sz, " bytes. msg sz=", msg_sz, " + IV size ", iv_size);
    cleartext.resize(msg_sz);
    try {
        const unsigned char* iv = ciphertext;
        GCM<AES>::Decryption dec;
        dec.SetKeyWithIV(key_, key_size, iv, iv_size);
        AuthenticatedDecryptionFilter filter(dec, new ArraySink(cleartext.data(), cleartext.size()), AuthenticatedDecryptionFilter::DEFAULT_FLAGS, tag_size);
        const unsigned char* enc_data = ciphertext + iv_size;
        size_t enc_data_size = msg_sz + tag_size;
        ArraySource(enc_data, enc_data_size, true, new Redirector(filter));
    }
    catch (const CryptoPP::HashVerificationFilter::HashVerificationFailed& e) {
        ko r = "KO 44032 CryptoPP::HashVerificationFilter::HashVerificationFailed.";
        log(r, e.what());
        return r;
    }
    catch (const CryptoPP::InvalidArgument& e) {
        auto r = "KO 44033 Invalid decrypting argument.";
        log(r, e.what());
        return r;
    }
    catch (const CryptoPP::Exception& e) {
        auto r = "KO 44034 Decryption was unsuccessful.";
        log(r, e.what());
        return r;
    }
    return ok;
}

