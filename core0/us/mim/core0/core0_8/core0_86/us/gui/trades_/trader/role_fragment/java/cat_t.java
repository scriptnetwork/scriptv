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
package us.cash;
import us.gov.crypto.base58;                                                                   // base58
import us.gov.io.blob_reader_t;                                                                // blob_reader_t
import us.gov.io.blob_writer_t;                                                                // blob_writer_t
import us.wallet.trader.data_t;                                                                // data_t
import java.util.HashMap;                                                                      // HashMap
import static us.gov.crypto.ripemd160.hash_t;                                                  // hash_t
import static us.gov.crypto.types.*;                                                           // *
import static us.ko.*;                                                                         // *
import static us.stdint.*;                                                                     // *
import us.stdint.*;                                                                            // *
import us.ko;                                                                                  // ko
import java.util.Map;                                                                          // Map
import us.pair;                                                                                // pair
import java.io.PrintStream;                                                                    // PrintStream
import static us.gov.io.types.blob_t.serid_t;                                                  // serid_t
import us.string;                                                                              // string

@SuppressWarnings("serial")
public class cat_t extends HashMap<hash_t, basket_item_t> implements us.gov.io.seriable {

    public cat_t() {
    }

    @Override public serid_t serial_id() { return serid_t.no_header; }

    @Override public int blob_size() {
        int sz = blob_writer_t.sizet_size(size());
        for (Map.Entry<hash_t, basket_item_t> entry: entrySet()) {
            sz += blob_writer_t.blob_size(entry.getKey());
            sz += blob_writer_t.blob_size(entry.getValue());
        }
        return sz;
    }

    @Override public void to_blob(blob_writer_t writer) {
        writer.write_sizet(size());
        for (Map.Entry<hash_t, basket_item_t> entry: entrySet()) {
            writer.write(entry.getKey());
            writer.write(entry.getValue());
        }
    }

    @Override public ko from_blob(blob_reader_t reader) {
        uint64_t sz = new uint64_t();
        {
            ko r = reader.read_sizet(sz);
            if (is_ko(r)) return r;
        }
        clear();
        for (long i = 0; i < sz.value; ++i) {
            hash_t key = new hash_t();
            {
                ko r = reader.read(key);
                if (is_ko(r)) return r;
            }
            basket_item_t value = new basket_item_t();
            {
                ko r = reader.read(value);
                if (is_ko(r)) return r;
            }
            put(key, value);
        }
        return ok;
    }

}

