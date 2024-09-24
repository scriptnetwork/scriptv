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
#include <iostream>
#include <fstream>
#include <unistd.h>
#include <chrono>
#include <random>

#include <us/gov/config.h>
#include <us/gov/crypto/types.h>
#include <us/gov/types.h>
#include <us/gov/traders/wallet_address.h>
#include <us/gov/cash/accounts_t.h>
#include <us/gov/cash/account_t.h>
#include <us/gov/cash/t_t.h>
#include <us/gov/cash/f_t.h>
#include <us/gov/cash/ttx.h>
#include <us/gov/io/types.h>
#include <us/gov/io/seriable_vector.h>
#include <us/gov/dfs/fileattr_t.h>
#include <us/gov/engine/signed_data.h>
#include <us/gov/traders/app.h>

#include <us/wallet/wallet/algorithm.h>
#include <us/wallet/engine/wallet_connection_t.h>
#include <us/wallet/engine/ip4_endpoint_t.h>

#include "test_platform.h"

#define loglevel "test"
#define logclass "blobs"
#include "logs.inc"

#include "assert.inc"

namespace us::test {

using namespace std;
using namespace us;
using namespace us::gov;

struct blobs_t: us::test::test_platform {
    using b = us::test::test_platform;

    blobs_t(): rng(random_device{}()), b(cout) {
    }


    template<typename t>
    void test_blob_serializer(t v, int expected_sz) {
        cout << "blob_serializer " << +v << ' ' << " expected sz: " << expected_sz << endl;
        using blob_reader_t = us::gov::io::blob_reader_t;
        using blob_writer_t = us::gov::io::blob_writer_t;
        using blob_t = us::gov::io::blob_t;

        blob_t blob;
        blob_writer_t w(blob, blob_writer_t::blob_size(v));
        w.write(v);
        if (blob.size() != expected_sz) {
            cout << "blob.size() = " << blob.size() << endl;
        }
        assert(blob.size() == expected_sz);

        t v2;
        blob_reader_t r(blob);
        r.read(v2);
        if (v != v2) {
            cout << "read v = " << v2 << endl;
        }
        assert(v == v2);
    }

    void test_blob_serializer_sz(uint64_t v, int expected_sz) {
        cout << "blob_serializer " << +v << ' ' << " expected sz: " << expected_sz << endl;
        using blob_reader_t = us::gov::io::blob_reader_t;
        using blob_writer_t = us::gov::io::blob_writer_t;
        using blob_t = us::gov::io::blob_t;
        cout << "sizet_size(v)= " << blob_writer_t::sizet_size(v) << " expected " << expected_sz << endl;
        assert(blob_writer_t::sizet_size(v) == expected_sz);

        blob_t blob;
        blob_writer_t w(blob, blob_writer_t::sizet_size(v));
        w.write_sizet(v);
        if (blob.size() != expected_sz) {
            cout << "blob.size() = " << blob.size() << endl;
        }
        assert(blob.size() == expected_sz);

        uint64_t v2;
        blob_reader_t r(blob);
        r.read_sizet(v2);
        if (v != v2) {
            cout << "read v = " << v2 << endl;
        }
        assert(v == v2);
    }

    void test_blob_serializer(const hash_t& v) {
        using t = hash_t;
        int expected_sz = 20;
        cout << "blob_serializer " << v << ' ' << " expected sz: " << expected_sz << endl;
        using blob_reader_t = us::gov::io::blob_reader_t;
        using blob_writer_t = us::gov::io::blob_writer_t;
        using blob_t = us::gov::io::blob_t;

        blob_t blob;
        blob_writer_t w(blob, blob_writer_t::blob_size(v));
        w.write(v);
        if (blob.size() != expected_sz) {
            cout << "blob.size() = " << blob.size() << endl;
        }
        assert(blob.size() == expected_sz);

        t v2;
        blob_reader_t r(blob);
        r.read(v2);
        if (v != v2) {
            cout << "read v = " << v2 << endl;
        }
        assert(v == v2);
    }

    void test_blob_serializer2(const string& v, int expected_sz) {
        using t = string;
        cout << "blob_serializer " << v.size() << ' ' << " expected sz: " << expected_sz << endl;
        using blob_reader_t = us::gov::io::blob_reader_t;
        using blob_writer_t = us::gov::io::blob_writer_t;
        using blob_t = us::gov::io::blob_t;

        blob_t blob;
        blob_writer_t w(blob, blob_writer_t::blob_size(v));
        w.write(v);
        if (blob.size() != expected_sz) {
            cout << "blob.size() = " << blob.size() << endl;
        }
        assert(blob.size() == expected_sz);

        t v2;
        blob_reader_t r(blob);
        r.read(v2);
        if (v != v2) {
            cout << "read v = " << v2 << endl;
        }
        assert(v == v2);
    }

    template<typename t>
    void test_blob_serializer_64() {
        test_blob_serializer((t) 0, 8);
        test_blob_serializer((t) 1, 8);
        test_blob_serializer((t) -1, 8);
        test_blob_serializer((t) 120, 8);
        test_blob_serializer((t) -120, 8);
        test_blob_serializer((t) 121, 8);
        test_blob_serializer((t) -121, 8);
        test_blob_serializer((t) 122, 8);
        test_blob_serializer((t) -122, 8);
        test_blob_serializer((t) 123, 8);
        test_blob_serializer((t) -123, 8);
        test_blob_serializer((t) 124, 8);
        test_blob_serializer((t) -124, 8);
        test_blob_serializer((t) 125, 8);
        test_blob_serializer((t) -125, 8);
        test_blob_serializer((t) 126, 8);
        test_blob_serializer((t) -126, 8);
        test_blob_serializer((t) 127, 8);
        test_blob_serializer((t) -127, 8);
        test_blob_serializer((t) 128, 8);
        test_blob_serializer((t) -128, 8);
        test_blob_serializer((t) 32766, 8);
        test_blob_serializer((t) -32766, 8);
        test_blob_serializer((t) 32767, 8);
        test_blob_serializer((t) -32767, 8);
        test_blob_serializer((t) 32768, 8);
        test_blob_serializer((t) -32768, 8);
        test_blob_serializer((t) 32769, 8);
        test_blob_serializer((t) -32769, 8);
        test_blob_serializer((t) 2147483647, 8);
        test_blob_serializer((t) -2147483647, 8);
        test_blob_serializer((t) 2147483648, 8);
        test_blob_serializer((t) -2147483648, 8);
        test_blob_serializer((t) numeric_limits<t>::max(), 8);
    }

    void test_blob_serializer() {
        cout << "uint8_t" << endl;
        test_blob_serializer((uint8_t) 0, 1);
        test_blob_serializer((uint8_t) 1, 1);
        test_blob_serializer((uint8_t) 0xfc, 1);
        test_blob_serializer((uint8_t) 0xfd, 1);
        test_blob_serializer((uint8_t) 0xfe, 1);
        test_blob_serializer((uint8_t) 0xff, 1);

        cout << "uint16_t" << endl;
        test_blob_serializer((uint16_t) 0, 2);
        test_blob_serializer((uint16_t) 1, 2);
        test_blob_serializer((uint16_t) 0xfc, 2);
        test_blob_serializer((uint16_t) 0xfd, 2);
        test_blob_serializer((uint16_t) 0xfe, 2);
        test_blob_serializer((uint16_t) 0xff, 2);

        cout << "uint32_t" << endl;
        test_blob_serializer((uint32_t) 0, 4);
        test_blob_serializer((uint32_t) 1, 4);
        test_blob_serializer((uint32_t) 0xfc, 4);
        test_blob_serializer((uint32_t) 0xfd, 4);
        test_blob_serializer((uint32_t) 0xfe, 4);
        test_blob_serializer((uint32_t) 0xff, 4);


        cout << "blob_size" << endl;
        test_blob_serializer_sz(0, 1);
        test_blob_serializer_sz(1, 1);
        test_blob_serializer_sz(0xfc, 1);
        test_blob_serializer_sz(0xfd, 3);
        test_blob_serializer_sz(0xfe, 3);
        test_blob_serializer_sz(0xff, 3);
        test_blob_serializer_sz(0xffff, 3);
        test_blob_serializer_sz(0x10000, 5);
        test_blob_serializer_sz(0xfffffffe, 5);
        test_blob_serializer_sz(0xffffffff, 5);
        test_blob_serializer_sz(0x100000000, 9);
        test_blob_serializer_sz(0xffffffffffffffff, 9);

        cout << "int64_t" << endl;
        test_blob_serializer_64<int64_t>();
        test_blob_serializer_64<uint64_t>();

        //test_blob_serializer((int64_t) numeric_limits<int64_t>::min(), 9);
    /*
        for (int64_t i = 2147483638; i < numeric_limits<int64_t>::max(); i+=1) {
            test_blob_serializer((int64_t) i, 5);
            test_blob_serializer((int64_t) -i, 5);
        }
    */
    //    test_blob_serializer((int64_t) -128, 3);

        test_blob_serializer(hash_t(0));
        test_blob_serializer(hash_t(23445));
        test_blob_serializer2("", 1);
        test_blob_serializer2("a", 2);
        test_blob_serializer2("aa", 3);
        test_blob_serializer2(string(500, 'a'), 503);
        test_blob_serializer2(string(50000, 'a'), 50003);
        test_blob_serializer2(string(500000, 'a'), 500005);

    }

    void test_ev_ser_wallet_address() {
        cout << "gov::io::blob" << endl;
        test_blob_serializer();

        cout << "test unsigned" << endl;
        {
            hash_t x(0);
            us::gov::traders::wallet_address ev(x, 123, 456);
            ev.pubkey.zero();
            blob_t blob;
    //        auto sz = ev.blob_size();
    //        cout << sz << endl;
    //        unsigned char buffer[sz];
            ev.write(blob);
            us::gov::traders::wallet_address ev2(hash_t(0), 0, 0);
            {
                assert(is_ok(ev2.read(blob)));
            }
    //        auto p = ev2.read_from(buffer, buffer+sz);
    //        assert(p == buffer + sz);
    //        unsigned char buffer2[sz];
            blob_t blob2;
            ev2.write(blob2);
            assert(blob.size() == blob2.size());
            assert(memcmp(blob.data(), blob2.data(), blob.size())==0);
            assert(ev2.pport == ev.pport);
            assert(ev2.net_addr == ev.net_addr);
            assert(ev2.eid == ev.eid);
//            assert(ev2.expires == ev.expires);
            cout << "A:" << ev.pubkey << " B:" <<  ev2.pubkey << endl;
            assert(ev2.pubkey == ev.pubkey);
            assert(ev2.signature == ev.signature);
            ostringstream os;
            cout << "verif: " << ev.verify(os) << endl;
            cout << os.str() << endl;
            assert(os.str() == "KO 02101 empty signature.\n");
            assert(!ev.verify(cout));
            assert(!ev2.verify(cout));
        }
        cout << "\ntest well formed" << endl;
        {
            hash_t x(47638);
            us::gov::traders::wallet_address ev(x, 6542, 5903);
            assert(ev.app == us::gov::traders::app::id());
            assert(ev.eid == 0);
//            ev.expires = ev.ts + 10000000068859;
            auto k = crypto::ec::keys::generate();
            ev.sign(k);
            cout << "ev pubkey=" << ev.pubkey << endl;
    //        auto sz = ev.ser_sz();
    //        cout << sz << endl;
            blob_t blob;
    //        unsigned char buffer[sz];
            ev.write(blob);
            us::gov::traders::wallet_address ev2(hash_t(0), 0, 0);
            assert(is_ok(ev2.read(blob)));
            blob_t blob2;
            ev2.write(blob2);
            for (int i = 0; i < blob.size(); ++i) {
                //cout << "pos=" << i << ' ' << (int)buffer[i] << ' ' << (int)buffer2[i] << '\n';
                assert(blob[i] == blob2[i]);
            }
            assert(memcmp(blob.data(), blob2.data(), blob.size())==0);
            assert(ev2.pport == ev.pport);
            assert(ev2.pport == 5903);
            assert(ev2.net_addr == ev.net_addr);
            assert(ev2.net_addr == 6542);
            assert(ev2.eid == ev.eid);
            assert(ev2.eid == 0);
            assert(ev2.app == ev.app);
            assert(ev2.app == us::gov::traders::app::id());
//            assert(ev2.expires == ev.expires);
//            assert(ev2.expires == ev.ts + 10000000068859);
//            assert(ev2.expires == ev2.ts + 10000000068859);
            assert(ev2.pubkey == ev.pubkey);
            cout << ev.pubkey << endl;
            cout << ev2.pubkey << endl;
            cout << k.pub << endl;
            assert(ev2.pubkey == k.pub);
            assert(ev2.pkh == x);
            assert(ev2.signature == ev.signature);
            assert(ev.verify(cout));
            assert(ev2.verify(cout));

    //        cout << "ser bin: " << sz << " bytes" << endl;
    //        ostringstream os;
    //        ev.to_stream(os);
    //        cout << "ser os: " << os.str().size() << " bytes" << endl;
        }

        cout << "\ntest datagram" << endl;
        {
            hash_t x(47638);
            us::gov::traders::wallet_address ev(x, 6542, 5903);
            assert(ev.app == us::gov::traders::app::id());
            assert(ev.eid == 0);
//            ev.expires = ev.ts + 10000000068859;
            auto k = crypto::ec::keys::generate();
            ev.sign(k);
            cout << "ev pubkey=" << ev.pubkey << endl;
    //        auto sz = ev.ser_sz();
    //        cout << sz << endl;
            auto d = ev.get_datagram(0, 6);
    //        unsigned char buffer[sz];
            us::gov::traders::wallet_address ev2(hash_t(0), 0, 0);
            {
                auto r = ev2.read(*d);
                if (is_ko(r)) cout << r << endl;
                assert(is_ok(r));
            }
            auto d2 = ev2.get_datagram(0, 6);
            assert(d->size() == d2->size());
            for (int i = 0; i < d->size(); ++i) {
                //cout << "pos=" << i << ' ' << (int)buffer[i] << ' ' << (int)buffer2[i] << '\n';
                assert((*d)[i] == (*d2)[i]);
            }
            assert(memcmp(d->data(), d2->data(), d->size())==0);
            assert(ev2.pport == ev.pport);
            assert(ev2.pport == 5903);
            assert(ev2.net_addr == ev.net_addr);
            assert(ev2.net_addr == 6542);
            assert(ev2.eid == ev.eid);
            assert(ev2.eid == 0);
            assert(ev2.app == ev.app);
            assert(ev2.app == us::gov::traders::app::id());
///            assert(ev2.expires == ev.expires);
//            assert(ev2.expires == ev.ts + 10000000068859);
//            assert(ev2.expires == ev2.ts + 10000000068859);
            assert(ev2.pubkey == ev.pubkey);
            cout << ev.pubkey << endl;
            cout << ev2.pubkey << endl;
            cout << k.pub << endl;
            assert(ev2.pubkey == k.pub);
            assert(ev2.pkh == x);
            assert(ev2.signature == ev.signature);
            assert(ev.verify(cout));
            assert(ev2.verify(cout));
            delete d;
            delete d2;
    //        cout << "ser bin: " << sz << " bytes" << endl;
    //        ostringstream os;
    //        ev.to_stream(os);
    //        cout << "ser os: " << os.str().size() << " bytes" << endl;
        }
    }

    using accounts_t = us::gov::cash::accounts_t;
    using account_t = us::gov::cash::account_t;

/*
/test_ev_ser_cash_accounts
A3
o>     1111111111111111HMYxF OIL 819; 1 files. 0 MiB; 1 coins
o>     11111111111111115NecGP OIL 889; 1 coins
test_ev_ser_cash_accounts
o>     1111111111111111HMYxF OIL 819; 1 files. 0 MiB; 1 coins
o>     11111111111111115NecGP OIL 889; 1 coins
-----------------------------------o
-----------------------------------  write o->blob
-----------------------------------o2
-----------------------------------  read o2 <- blob
-----------------------------------  write o2 -> blob2
-----------------------------------o3
-----------------------------------  read o3 <- blob2
-----------------------------------
blob1: 155
blob2: 125
blob1: 02000000000000000000000000000000000B080000023303000000000000060100000000000000000000000000000000FF0E0000488B00000867647367667364670100000000000000000000000000000000A6520300D9D40000000000000000000000000000000000000000000000AB17000004790300000000000004010000000000000000000000000000000004950300591900000000000000
blob2: 02000000000000000000000000000000000B080000023303000000000000060100000000000000000000000000000000FF0E0000488B00000867647367667364670100000000000000000000000000000000A6520300D9D4000000000000000000000000000000000000000000000000AB170000047903000000000000
Fail. vector sizes differ. Expected 125, got 155

===========================================

0x2dc566665a6d test ../../us/gov/logs.inc 76 -----------------------------------o 
0x2dc566666788 test ../../us/gov/logs.inc 76 -----------------------------------  write o->blob 
0x2dc566667426 gov/io writable.cpp 23 writable::write to blob 
0x2dc566669289 gov/io blob_writer_t.cpp 40 write at blob offset 0 
0x2dc566669aa9 gov/io blob_writer_t.cpp 167 write at blob offset 1 
0x2dc56666a293 gov/cash account_t.cpp 216 to_blob cur 21 
0x2dc56666a9f3 gov/io blob_writer_t.cpp 93 write at blob offset 21 
0x2dc56666b183 gov/cash safe_deposit_box.cpp 134 to_blob cur 22 
0x2dc56666b872 gov/io blob_writer_t.cpp 81 write at blob offset 22 
0x2dc56666bef5 gov/io blob_writer_t.cpp 93 write at blob offset 30 
0x2dc56666ceff gov/io blob_writer_t.cpp 40 write at blob offset 31 
0x2dc56666d64e gov/io blob_writer_t.cpp 167 write at blob offset 32 
0x2dc56666e029 gov/dfs fileattr_t.cpp 40 to_blob cur 52 
0x2dc56666e723 gov/io blob_writer_t.cpp 105 write at blob offset 52 
0x2dc56666f001 gov/io blob_writer_t.cpp 40 write at blob offset 56 
0x2dc56666f6e3 gov/io blob_writer_t.cpp 130 write at blob offset 57 
0x2dc56666febc gov/cash t_t.cpp 150 to_blob cur 65 
0x2dc5666705cf gov/io blob_writer_t.cpp 40 write at blob offset 65 
0x2dc566670d0a gov/io blob_writer_t.cpp 167 write at blob offset 66 
0x2dc566671408 gov/io blob_writer_t.cpp 81 write at blob offset 86 
0x2dc566671b05 gov/io blob_writer_t.cpp 93 write at blob offset 94 
0x2dc5666722b5 gov/io blob_writer_t.cpp 167 write at blob offset 95 
0x2dc5666729aa gov/cash account_t.cpp 216 to_blob cur 115 
0x2dc56667309b gov/io blob_writer_t.cpp 93 write at blob offset 115 
0x2dc566673790 gov/cash safe_deposit_box.cpp 134 to_blob cur 116 
0x2dc566673e5c gov/io blob_writer_t.cpp 81 write at blob offset 116 
0x2dc56667450d gov/io blob_writer_t.cpp 93 write at blob offset 124 
-------------------------------------------------------------------------------diff starts 
******************************************************************************
0x2dc566674b96 gov/cash t_t.cpp 150 to_blob cur 125 
0x2dc566675263 gov/io blob_writer_t.cpp 40 write at blob offset 125 
0x2dc56667595e gov/io blob_writer_t.cpp 167 write at blob offset 126 
0x2dc566676030 gov/io blob_writer_t.cpp 81 write at blob offset 146 
0x2dc56667673f gov/io blob_writer_t.cpp 93 write at blob offset 154 
0x2dc566676f10 test ../../us/gov/logs.inc 76 -----------------------------------o2 
0x2dc56667dcc8 test ../../us/gov/logs.inc 76 -----------------------------------  read o2 <- blob 
0x2dc56667f78d gov/io readable.cpp 20 readable::read from blob 155 serid 0 
0x2dc5666806ab gov/cash account_t.cpp 222 from_blob cur 21 
0x2dc56668107d gov/cash safe_deposit_box.cpp 147 from_blob cur 22 
0x2dc566682ca3 gov/dfs fileattr_t.cpp 46 from_blob cur 52 
0x2dc566684893 gov/cash t_t.cpp 156 from_blob cur 65 
0x2dc566687248 gov/cash t_t.cpp 162 reader.header.version 0 
0x2dc566687ab9 gov/cash t_t.cpp 164 old version. flags set to 0 
0x2dc5666893d4 gov/cash account_t.cpp 222 from_blob cur 114 
0x2dc566689c6d gov/cash safe_deposit_box.cpp 147 from_blob cur 115 
0x2dc56668aa14 test ../../us/gov/logs.inc 76 -----------------------------------  write o2 -> blob2 
0x2dc56668b84a gov/io writable.cpp 23 writable::write to blob 
0x2dc56668c8c2 gov/io blob_writer_t.cpp 40 write at blob offset 0 
0x2dc56668d1be gov/io blob_writer_t.cpp 167 write at blob offset 1 
0x2dc56668d910 gov/cash account_t.cpp 216 to_blob cur 21 
0x2dc56668e0a0 gov/io blob_writer_t.cpp 93 write at blob offset 21 
0x2dc56668e7c5 gov/cash safe_deposit_box.cpp 134 to_blob cur 22 
0x2dc56668eeb2 gov/io blob_writer_t.cpp 81 write at blob offset 22 
0x2dc56668f55a gov/io blob_writer_t.cpp 93 write at blob offset 30 
0x2dc56668fc6c gov/io blob_writer_t.cpp 40 write at blob offset 31 
0x2dc566690372 gov/io blob_writer_t.cpp 167 write at blob offset 32 
0x2dc566690aa0 gov/dfs fileattr_t.cpp 40 to_blob cur 52 
0x2dc566691164 gov/io blob_writer_t.cpp 105 write at blob offset 52 
0x2dc56669180c gov/io blob_writer_t.cpp 40 write at blob offset 56 
0x2dc566691e86 gov/io blob_writer_t.cpp 130 write at blob offset 57 
0x2dc566692610 gov/cash t_t.cpp 150 to_blob cur 65 
0x2dc566692d34 gov/io blob_writer_t.cpp 40 write at blob offset 65 
0x2dc56669342e gov/io blob_writer_t.cpp 167 write at blob offset 66 
0x2dc566693af6 gov/io blob_writer_t.cpp 81 write at blob offset 86 
0x2dc56669418d gov/io blob_writer_t.cpp 93 write at blob offset 94 
0x2dc5666948a9 gov/io blob_writer_t.cpp 167 write at blob offset 95 
0x2dc566694f2c gov/cash account_t.cpp 216 to_blob cur 115 
0x2dc5666955c5 gov/io blob_writer_t.cpp 93 write at blob offset 115 
0x2dc566695c72 gov/cash safe_deposit_box.cpp 134 to_blob cur 116 
0x2dc56669631b gov/io blob_writer_t.cpp 81 write at blob offset 116 
0x2dc56669697f gov/io blob_writer_t.cpp 93 write at blob offset 124 
------------------------------------------------------------------------------Unexpected stop
******************************************************************************

... missing stuff

0x2dc56669714f test ../../us/gov/logs.inc 76 -----------------------------------o3 
0x2dc566697e74 test ../../us/gov/logs.inc 76 -----------------------------------  read o3 <- blob2 
0x2dc566698ac1 gov/io readable.cpp 20 readable::read from blob 125 serid 0 
0x2dc56669940c gov/cash account_t.cpp 222 from_blob cur 21 
0x2dc566699b7c gov/cash safe_deposit_box.cpp 147 from_blob cur 22 
0x2dc56669a49a gov/dfs fileattr_t.cpp 46 from_blob cur 52 
0x2dc56669b25a gov/cash t_t.cpp 156 from_blob cur 65 
0x2dc56669bcd8 gov/cash t_t.cpp 162 reader.header.version 0 
0x2dc56669c416 gov/cash t_t.cpp 164 old version. flags set to 0 
0x2dc56669d622 gov/cash account_t.cpp 222 from_blob cur 114 
0x2dc56669dea4 gov/cash safe_deposit_box.cpp 147 from_blob cur 115 
0x2dc56669e9b0 test ../../us/gov/logs.inc 76 ----------------------------------- 


*/


    void test_ev_ser_cash_accounts(accounts_t& o) {
        tee("test_ev_ser_cash_accounts");
        o.dump("o> ", 1, cout);
        tee("-----------------------------------o");
        tee("-----------------------------------  write o->blob");
        blob_t blob;
        o.write(blob);

        tee("-----------------------------------o2");
        accounts_t o2;
        tee("-----------------------------------  read o2 <- blob");
        assert(is_ok(o2.read(blob)));

        tee("-----------------------------------  write o2 -> blob2");
        blob_t blob2;
        o2.write(blob2);
        tee("-----------------------------------o3");
        accounts_t o3;
        tee("-----------------------------------  read o3 <- blob2");
        assert(is_ok(o3.read(blob2)));
        tee("-----------------------------------");
        assert(o.size() == o3.size());
        cout << "blob1: " << blob.size() << '\n';
        cout << "blob2: " << blob2.size()  << '\n';
        cout << "blob1: " << us::gov::crypto::b58::to_hex(blob) << endl;
        cout << "blob2: " << us::gov::crypto::b58::to_hex(blob2) << endl;
        

        check(blob, blob2);
        cout << blob.size() << endl;
        tee("/test_ev_ser_cash_accounts");
    }

    void test_ev_ser_cash_accounts() {
        tee("A1");
        {
            accounts_t o;
            test_ev_ser_cash_accounts(o);
        }
        tee("A2");
        {
            accounts_t o;
            {
                account_t a;
                a.locking_program = 4;
                a.box.value = 889;
                {
                    using t_t = us::gov::cash::t_t;
                    a.box.t = new t_t();
                }
                o.emplace(hash_t(6059), a);
            }
            test_ev_ser_cash_accounts(o);
        }
        tee("A3");
        {
            accounts_t o;
            {
                account_t a;
                a.locking_program = 4;
                a.box.value = 889;
                {
                    using t_t = us::gov::cash::t_t;
                    a.box.t = new t_t();
                    a.box.t->emplace(hash_t(234756), 6489);
                }
                o.emplace(hash_t(6059), a);
            }
            {
                account_t a;
                a.locking_program = 2;
                a.box.value = 819;
                {
                    using t_t = us::gov::cash::t_t;
                    using f_t = us::gov::cash::f_t;
                    a.box.t = new t_t();
                    a.box.t->emplace(hash_t(217766), 54489);
                    a.box.f = new f_t();
                    a.box.f->emplace(hash_t(3839), us::gov::dfs::fileattr_t("gdsgfsdg", 35656));
                }
                o.emplace(hash_t(2059), a);
            }
            o.dump("o> ", 1, cout);
            test_ev_ser_cash_accounts(o);
        }
    }

    using signed_data0 = us::gov::engine::signed_data0;

    void test_signed_data(signed_data0& o) {
        blob_t blob;
        o.write(blob);
        signed_data0 o2;
        assert(is_ok(o2.read(blob)));
        blob_t blob2;
        o2.write(blob2);
        signed_data0 o3;
        assert(is_ok(o3.read(blob2)));
        assert(o.pubkey == o3.pubkey);
        assert(o.signature == o3.signature);
        check(blob, blob2);
        cout << blob.size() << endl;;
    }

    void test_signed_data() {
        tee("test_signed_data");
        {
            signed_data0 o;
            test_signed_data(o);
        }
        auto k = us::gov::crypto::ec::keys::generate();
        {
            signed_data0 o;
            {
                o.pubkey = k.pub;
            }
            test_signed_data(o);
        }
        {
            signed_data0 o;
            {
                o.pubkey = k.pub;
                o.signature.data[16] = 'A';
            }
            test_signed_data(o);
        }
        {
            string sigder = "381yXYmWWMTYPsDYLYmk6cNKa5tEWVrnHmRs9HH5dJ4NdGmC9si9Ds1KG2aSLTa6hzeedVnsrUywcopXH3Nde7ELUSexR2QX";
            auto sig = us::gov::crypto::ec::instance.sig_from_der(sigder);
            auto der = us::gov::crypto::ec::instance.sig_encode_der_b58(sig);
            assert(sigder == der);

            signed_data0 o;
            {
                o.pubkey = k.pub;
                o.signature = sig;
            }
            test_signed_data(o);
        }
    }

    void test_bin_ser() {
        test_ev_ser_wallet_address();
        test_ev_ser_cash_accounts();
        test_signed_data();
    }

    void test_datagram_parse(int mode_) {
        //mode = mode_;
        /*
        cout << "test_datagram_parse. mode=" << mode << endl;
        {
          cout << "X dgram parse. payload=v{hu4u2}" << endl;
          vector<tuple<hash_t, uint32_t, uint16_t>> i_in = create_instance<vector<tuple<hash_t, uint32_t, uint16_t>>>();
          us::gov::socket::datagram d(1, 0,    i_in);
          vector<tuple<hash_t, uint32_t, uint16_t>> i_out;
          assert(d.parse(i_out));
          check_x(i_in, i_out);
        }
        */
        //#include <us/api/apitool_generated__c++__datagram_tests_impl>
    }

    using wallet_connection_t = us::wallet::engine::wallet_connection_t;
    using serid_t = us::gov::io::blob_reader_t::serid_t;
    using ip4_endpoint_t = us::wallet::engine::ip4_endpoint_t;
    using blob_writer_t = us::gov::io::blob_writer_t;
    using blob_reader_t = us::gov::io::blob_reader_t;
    using blob_header_t = us::gov::io::blob_writer_t::blob_header_t;

    struct wrapper_wallet_connection_t: wallet_connection_t {
        using b = wallet_connection_t;

        wrapper_wallet_connection_t(): wallet_connection_t("nm1", "subhome_1", ip4_endpoint_t((shost_t)"192.167.66.3", (port_t)18782, (channel_t)0)) {
            ts = 52;
            addr = "addr_1";
            ssid = "ssid_1";
        }

        size_t blob_size() const override {
            return b::blob_size();
        }

        void to_blob(blob_writer_t& w) const override {
            b::to_blob(w);
        }

        ko from_blob(blob_reader_t& reader) override {
            {
                auto r = b::from_blob(reader);
                if (is_ko(r)) return r;
            }
            return ok;
        }

    };

    struct container_wc_t: us::gov::io::seriable_vector<wrapper_wallet_connection_t> {
        using b = us::gov::io::seriable_vector<wrapper_wallet_connection_t>;

        using b::seriable_vector;

        serid_t serial_id() const override { return 'X'; }

    };

    string container_of_5_wrapper_wallet_connection_a50_curblob;

    void test_wallet_connection() {
        container_wc_t container_wc(5, wrapper_wallet_connection_t());
        blob_t blob;
        container_of_5_wrapper_wallet_connection_a50_curblob = container_wc.encode();        
        cout << "container_of_5_wrapper_wallet_connection_a50_curblob=" << container_of_5_wrapper_wallet_connection_a50_curblob << endl;
    }

    void wrapper_test_wallet_connection2() {
        string container_of_5_wrapper_wallet_connection_t_a50_blob = "3JxT8NUbu3u9xYXNVCwQi1kqTuCJyiAQBc8h3kTvBTKVz9w898JiC1HAyB4eHaZiYQnjzAGCpWmATosNReVAeKATNz4MDdGEKUFcVANCpnu1V8Y2dHKFApuzxnKqJweYJ758hrRijLxKNtPkaKuW2r9b4HDHAtHHerUTaKsHDCBpAozpZ43taHWvtC8kBcGgVwonmT8FhoXCaiEsWWQJWkvStCtarrEbYAL9Q6jyVUDnLw3hDRa2JoFVdpZvwfa7mn1sKBrDUvnEFTRWKa2j1rVrhgiAHToYNQsW9bLCdVoQywCfYRNchKu25eKV1puU8kexK1uuf5bcp5qH6VgVFQQEQikydSeamY4621DnwUZiCF";
/*
        //string container_of_5_wrapper_wallet_connection_t_a49_blob = "24HGz9KDSkRWJvUzGCe2fJkngCb8v7XfmMMjnLtWXds1YaL5RtMcjTxoCZCGQKwsvCt7YJKRBDTq3k9aYoXzRvqMx4aModzMpjTqZduRBVbFbh9vyNsTC9jVLtER2Rod1WExpYC4My4ikxGMvrz7ZTtwDgoNAh9eTnPR1P8uqGLvErrNEXgDSbzHMEhgDv19Ckd9myzgXuDmRXMBxbr4p8obcwKGMDPR5ALCcdBvgWehGPqvhFZmXYwtrtedJ7zaJ7HB3AXLMtS2Fi9ZbeBc5orcBFycV7275nNcV1h";
        cout << "container_of_5_wrapper_wallet_connection_t_a49_blob=" << container_of_5_wrapper_wallet_connection_t_a49_blob << endl;
        cout << "container_of_5_wrapper_wallet_connection_t_a50_blob=" << container_of_5_wrapper_wallet_connection_t_a50_blob << endl;
        assert(container_of_5_wrapper_wallet_connection_t_a50_blob == container_of_5_wrapper_wallet_connection_a50_curblob);
        container_wc_t cw;
        assert(is_ko(cw.read(container_of_5_wrapper_wallet_connection_t_a49_blob)));

        string a49h = blob_writer_t::add_header(blob_header_t{blob_reader_t::current_version - 1, 'X'}, container_of_5_wrapper_wallet_connection_t_a49_blob);
        cout << "a49h = {blob_reader_t::current_version - 1}'X' + container_of_5_wrapper_wallet_connection_t_a49_blob = " << a49h << endl;
        ko r = cw.read(a49h);
        if (is_ko(r)) {
            tee(r);
        }
        assert(is_ok(r));
        assert(cw.size() == 5);
*/
        container_wc_t cw;
        assert(is_ok(cw.read(container_of_5_wrapper_wallet_connection_t_a50_blob)));
        assert(cw.size() == 5);
    }

    void wrapper_test_wallet_connection() {
        string wallet_connection_t_a49_blob="XvkfoSNzekbT3FwZdtp99Mg1aqRhkJ3EmvvMm66pddv9aBHdonSAqfD";
        wallet_connection_t wc;
        assert(is_ko(wc.read(wallet_connection_t_a49_blob)));
    }

    void convert_fromv9(const string& blobb58_v9) {
        string a49h = blob_writer_t::add_header(blob_header_t{blob_reader_t::current_version - 1, 'X'}, blobb58_v9);
        cout << "--- convert-str95------------" << endl;
        cout << "input: " << blobb58_v9 << endl;
        cout << "a49h = {blob_reader_t::current_version - 1}'X' + blobb58_v9 = " << a49h << endl;

        struct wc_t: wallet_connection_t {
            serid_t serial_id() const override { return 'X'; }
        };

        wc_t wc;
        ko r = wc.read(a49h);
        if (is_ko(r)) {
            tee(r);
        }
        assert(is_ok(r));
        wc.subhome = "new";

        wallet_connection_t wc2(wc); //serialization with no header
        cout << "a50= " << wc2.encode() << endl;
        cout << "-/- convert------------------" << endl;
    }

    void test() {
        test_bin_ser();
        test_datagram_parse(0);
        test_datagram_parse(1);
        test_datagram_parse(2);
        test_wallet_connection();
        wrapper_test_wallet_connection();
        wrapper_test_wallet_connection2();
        /*
        convert_fromv9("Sk8adPorUZdLvsqufDQdnCp2r2KDABwmcKXwDYKJrmsQEEbSxe5jxmdUjz9BKLMoCetgS7my");
        convert_fromv9("6bJY8RxitpYoDA3AmpxpXhpWQvPqgYPP3XAGMWMez7aGWMzA4iiWs22xgfMQd76BEBEAwjm");
        convert_fromv9("4HuXw4HAXDiqGmyhVdDN5S1X4cQk4ejM98cXXQDrffYc3wScHDHBRHmzQQLdjwHpU87");
        */
    }

    mt19937_64 rng;
    int mode{0};

};

void test_sigcode0() {

    using sigcode_t = us::gov::cash::sigcode_t;
    sigcode_t sc;
    blob_t blob0;
    sc.write(blob0);
    cout << blob0.size() << endl;

    sigcode_t sc1;
    assert(is_ok(sc1.read(blob0)));
}

void test_locking_program_input() {

    using locking_program_input_t = us::gov::cash::locking_program_input_t;
    locking_program_input_t o;
    blob_t blob0;
    o.write(blob0);
    cout << blob0.size() << endl;

    locking_program_input_t o1;
    assert(is_ok(o1.read(blob0)));
}

void test_tx() {
    us::wallet::wallet::algorithm w("");
    auto a = w.new_address();


    {
    auto tx = w.set_supply(a, 5647);
    assert(is_ko(tx.first));
    cout << tx.first << endl;
    assert(string(tx.first) == "KO 32001 A funded account must exist");
    }
    w.data = new us::gov::cash::accounts_t();
    w.data->emplace(a, us::gov::cash::account_t());

    auto tx = w.set_supply(a, 5647);


    blob_t blob0;
    tx.second->write(blob0);

    cout << "blob size" << blob0.size() << endl;
    using evidence = us::gov::engine::evidence;
    pair<ko, evidence*> r = evidence::from_blob(blob0);
    {
        if (is_ko(r.first)) {
            cout << r.first << endl;
            assert(r.second == nullptr);
            assert(false);
        }
    }
    r.second->write_pretty_en(cout);
    delete r.second;
}

void test_ev_blob() {
    test_sigcode0();
    test_locking_program_input();
    test_tx();
}

#include <us/gov/relay/rpc_peer_t.h>

void test_push(const string& s) {
    tee("test push dgram write/read");
    datagram* d;
    hash_t tid(4673);
    {
        blob_t blob;
        {
        us::gov::io::blob_writer_t writer(blob, us::gov::io::blob_writer_t::blob_size(s));
        writer.write(s);
        }
        assert(blob.size() == s.size() + us::gov::io::blob_writer_t::sizet_size(s.size()));

        us::gov::relay::rpc_peer_t::push_in_t o(tid, 82, blob);
        d = o.get_datagram(0, 0);
    }

    us::gov::relay::rpc_peer_t::push_in_dst_t o_in;
    us::gov::io::blob_reader_t reader(*d);
    auto r = reader.read(o_in);
    if (is_ko(r)) {
        cout << r << '\n';
        assert(false);
    }
    assert(o_in.blob.size() == s.size() + us::gov::io::blob_writer_t::sizet_size(s.size()));
    assert(o_in.tid == tid);
    assert(o_in.code == 82);

    string s2;
    assert(is_ok(us::gov::io::blob_reader_t::parse(o_in.blob, s2)));
    assert(s2 == s);
    delete d;
}

void test_push() {
    test_push("");
    test_push("abracadabra-poo");
    test_push("POLLAFRITA");
}

void test_blobs() {
    {
        blobs_t blobs;
        blobs.test();
    }
    test_ev_blob();
    test_push();
}

}

