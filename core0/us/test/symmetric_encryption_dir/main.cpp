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
#include "us/gov/crypto/symmetric_encryption.h"

#include <iostream>
#include <string>
#include "us/gov/crypto/ec.h"
#include "us/gov/crypto/base58.h"
#include <vector>
#include <array>

#define loglevel "test"
#define logclass "symm_enc_main"
#include <us/gov/logs.inc>


using namespace std;
using namespace us::gov::crypto;

//using namespace us::test;

using namespace std;
using namespace us::gov::crypto;

using us::ko;
using us::is_ko;
using us::is_ok;

int main ( int argc, char *argv[] )
{

    us::dbg::thread_logger::set_root_logdir("logs");
    log_pstart("generate_keys_main");
    log_start("", "main");

    if ( argc < 5 ) {
        cout<<"required arugments: <private key> <public key> <[encrypt|decrypt]> <message>" << endl;
        if(argc == 2)
            cout<<"provided private key: " << argv[1] << endl;
        if(argc == 3)
            cout<<"provided private key: " << argv[1] << "\n" << "provided public key: " << argv[2] << endl;
        if(argc == 4)
            cout<<"provided private key: " << argv[1] << "\n" << "provided public key: " << argv[2] << "\n" << "provided command: " << argv[3] << endl;
    }
    else {

        ec::keys k;
        k.priv=ec::keys::priv_t::from_b58(argv[1]);
        k.pub=ec::keys::pub_t::from_b58(argv[2]);

        string command(argv[3]);

        symmetric_encryption s_e;
        s_e.init(k.priv, k.pub);

            if (command == "encrypt") {
                string message_string(argv[4]);
                vector<unsigned char> message(message_string.begin(),message_string.end());
                vector<unsigned char> encrypted;
                ko r = s_e.encrypt(message, encrypted, 0);
                assert(is_ok(r));
                cout << b58::encode(encrypted) << endl;
            }
            if (command == "decrypt") {
                vector<unsigned char> message;
                b58::decode(argv[4], message);
                vector<unsigned char> decrypted;
                ko r = s_e.decrypt(message, decrypted);
                assert(is_ok(r));
                string decrypted_string(decrypted.begin(), decrypted.end());
                cout << decrypted_string << endl;
            }

    }
}

