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
package us.wallet.engine;
import java.util.ArrayList;                                                                    // ArrayList
import us.gov.crypto.base58;                                                                   // base58
import us.gov.io.blob_reader_t;                                                                // blob_reader_t
import us.gov.io.types.blob_t;                                                                 // blob_t
import us.gov.io.blob_writer_t;                                                                // blob_writer_t
import us.CFG;                                                                                 // CFG
import java.util.Date;                                                                         // Date
import us.gov.crypto.ec;                                                                       // ec
import static us.gov.crypto.types.*;                                                           // *
import static us.gov.id.types.*;                                                               // *
import static us.gov.socket.types.*;                                                           // *
import us.stdint.*;                                                                            // *
import static us.ko.is_ko;                                                                     // is_ko
import java.security.KeyPair;                                                                  // KeyPair
import us.ko;                                                                                  // ko
import static us.ko.ok;                                                                        // ok
import java.io.PrintStream;                                                                    // PrintStream
import static us.gov.io.types.blob_t.serid_t;                                                  // serid_t
import us.string;                                                                              // string
import java.util.TreeMap;
import java.util.Set;
import java.util.Map;
import us.wallet.trader.protocol_selection_t;                                                                // blob_writer_t
import us.wallet.trader.bookmarks_t;                                                                // blob_writer_t
import us.gov.crypto.ripemd160.hash_t;

@SuppressWarnings("serial")
public class data_sources_index__item_t implements us.gov.io.seriable {

    private static void log(final String line) {                                 //--strip
        CFG.log_wallet_trader("data_sources_index__item_t: " + line);                      //--strip
    }                                                                            //--strip

    public void dump(PrintStream os) {
        os.println(to_string());
    }

    public String to_string() {
        return name.value + " " + address.encode();
    }

    @Override public serid_t serial_id() { return serid_t.no_header; }

    @Override public int blob_size() {
        return blob_writer_t.blob_size(name) + blob_writer_t.blob_size(address);
    }

    @Override public void to_blob(blob_writer_t writer) {
        log("to_blob "); //--strip
        writer.write(name);
        writer.write(address);
    }

    @Override public ko from_blob(blob_reader_t reader) {
        log("from_blob"); //--strip
        try {
            {
                log("TTE loading name"); //--strip
                ko r = reader.read(name);
                if (is_ko(r)) {
                    log(r.msg); //--strip
                    return r;
                }
            }
            {
                log("TTE loading address"); //--strip
                ko r = reader.read(address);
                if (is_ko(r)) {
                    log(r.msg); //--strip
                    return r;
                }
            }
        }
        catch (Exception e) {
            return new ko("KO 89282 TTE Invalid read. " + e.getMessage());
        }
        log("TTE loaded ok item."); //--strip
        return ok;
    }

    public string name = new string("");
    public hash_t address = new hash_t(0);
}

