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
package us.gov.cash;
import us.gov.io.blob_reader_t;                                                                // blob_reader_t
import us.gov.io.blob_writer_t;                                                                // blob_writer_t
import static us.gov.cash.types.cash_t;                                                        // cash_t
import us.CFG;                                                                                 // CFG
import us.gov.crypto.ripemd160.hash_t;                                                         // hash_t
import static us.stdint.*;                                                                     // *
import us.ko;                                                                                  // ko
import java.util.Map;                                                                          // Map
import java.io.PrintStream;                                                                    // PrintStream
import us.gov.io.seriable;                                                                     // seriable
import us.gov.io.seriable_map__hash_cash;                                                      // seriable_map__hash_cash
import static us.gov.io.types.blob_t.serid_t;                                                  // serid_t

@SuppressWarnings("serial")
public class t_t extends seriable_map__hash_cash {

    static final String msg_sealed = ". Sealed.";

    public t_t() {
    }

    public t_t(final t_t other) {
        for (Map.Entry<hash_t, cash_t> i: other.entrySet()) {
            put(i.getKey(), i.getValue());
        }
        flags.value = other.flags.value;
    }

    public final cash_t get_value(final hash_t coin) {
        cash_t i = get(coin);
        if (i == null) {
            return cash_t.zero_;  //non existent mint
        }
        return i;
    }

    public void dump(final String prefix, final hash_t addr, PrintStream os) {
        for (Map.Entry<hash_t, cash_t> i: entrySet()) {
            os.print(prefix + "coin " + i.getKey().encode() + ' ' + i.getValue().value);
            if (addr.equals(i.getKey())) {
                os.print(". Mint");
                if (is_sealed()) {
                    os.print(msg_sealed);
                }
            }
            os.println();
        }
    }

    final uint8_t get_flags() { return flags; }
    boolean is_sealed() { return flags.value == 1; }
    boolean is_unsealed() { return flags.value == 0; }

    public int mint_type(final hash_t address) { //0 not a mint; 1 open mint; 2 sealed mint
        if (get(address) == null) return 0;
        if (is_unsealed()) return 1;
        return 2;
    }

    @Override public serid_t serial_id() { return serid_t.no_header; }

    @Override public int blob_size() {
        return super.blob_size() + blob_writer_t.blob_size(flags);
    }

    @Override public void to_blob(blob_writer_t writer) {
        super.to_blob(writer);
        writer.write(flags);
    }

    @Override public ko from_blob(blob_reader_t reader) {
        {
            ko r = super.from_blob(reader);
            if (ko.is_ko(r)) return r;
        }
        {
            ko r = reader.read(flags);
            if (ko.is_ko(r)) return r;
        }
        return ko.ok;
    }

    uint8_t flags = new uint8_t((short)0);
};


