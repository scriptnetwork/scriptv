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
import us.gov.dfs.fileattr_t;                                                                  // fileattr_t
import us.gov.crypto.ripemd160.hash_t;                                                         // hash_t
import static us.stdint.*;                                                                     // *
import us.ko;                                                                                  // ko
import java.util.Map;                                                                          // Map
import java.io.PrintStream;                                                                    // PrintStream
import us.gov.io.seriable;                                                                     // seriable
import us.gov.io.seriable_map__hash_fileattr;                                                  // seriable_map__hash_fileattr
import static us.gov.io.types.blob_t.serid_t;                                                  // serid_t

@SuppressWarnings("serial")
public class f_t extends seriable_map__hash_fileattr {

    public f_t() {
    }

    public f_t(final f_t other) {
        for (Map.Entry<hash_t, fileattr_t> i: other.entrySet()) {
            put(i.getKey(), i.getValue());
        }
    }

    public uint32_t total_mib() {
        uint32_t n = new uint32_t(0);
        for (Map.Entry<hash_t, fileattr_t> i: entrySet()) {
            n.value += i.getValue().sz.value;
        }
        return new uint32_t(n.value / (1024 * 1024));
    }


    public void dump(final String prefix, PrintStream os) {
        for (Map.Entry<hash_t, fileattr_t> i: entrySet()) {
            os.print(prefix + i.getKey() + ' ');
            i.getValue().dump(os);
        }
    }

};


