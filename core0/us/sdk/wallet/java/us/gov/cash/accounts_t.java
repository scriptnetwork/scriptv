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
import us.ko;                                                                                  // ko
import static us.gov.cash.types.locking_program_t;                                             // locking_program_t
import java.io.PrintStream;                                                                    // PrintStream
import us.gov.io.seriable;                                                                     // seriable
import us.gov.io.seriable_map__hash_account;
import java.util.Map;                                                                          // Map

@SuppressWarnings("serial")
public class accounts_t extends seriable_map__hash_account { // #<orma src="make.sh#gen_templates"/>

    public accounts_t() {
    }

    public void dump(final String prefix, int detail, PrintStream os) {
        if (detail > 1) {
            os.println(prefix + size() + " accounts:");
        }
        String pfx = prefix + "    ";
        for (Map.Entry<hash_t, account_t> i: entrySet()) {
            i.getValue().dump(pfx, i.getKey(), detail, os);
        }
    }

}

