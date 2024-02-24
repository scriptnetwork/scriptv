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
package us.wallet.trader;
import us.gov.io.blob_reader_t;                                                                // blob_reader_t
import us.gov.io.blob_writer_t;                                                                // blob_writer_t
import us.CFG;                                                                                 // CFG
import static us.gov.io.types.*;                                                               // *
import static us.ko.is_ko;                                                                     // is_ko
import us.ko;                                                                                  // ko
import static us.ko.ok;                                                                        // ok
import us.pair;                                                                                // pair
import us.string;                                                                                // pair
import java.io.PrintStream;                                                                    // PrintStream
import us.gov.io.seriable;                                                                     // seriable
import static us.gov.io.types.blob_t.serid_t;                                                  // serid_t
import static us.gov.socket.types.*;                                                           // *
import static us.gov.crypto.ripemd160.hash_t;
import java.util.ArrayList;                                                                    // ArrayList

@SuppressWarnings("serial")
public class trader_brief_t implements us.gov.io.seriable {

    static void log(final String line) {                               //--strip
       CFG.log_wallet_trader("trader_brief_t " + line);                //--strip
    }                                                                  //--strip

    @Override public serid_t serial_id() { return serid_t.no_header; }

    @Override public int blob_size() {
        int sz = blob_writer_t.blob_size(tid)
        + blob_writer_t.blob_size(ts_creation)
        + blob_writer_t.blob_size(ts_activity)
        + blob_writer_t.blob_size(subhome)
        + qr.blob_size();
        log("blob_size" + sz); //--strip
        return sz;
    }

    @Override public void to_blob(blob_writer_t writer) {
        log("to_blob"); //--strip
        writer.write(tid);
        writer.write(ts_creation);
        writer.write(ts_activity);
        writer.write(subhome);
        writer.write(qr);
    }

    @Override public ko from_blob(blob_reader_t reader) {
        {
            ko r = reader.read(tid);
            if (is_ko(r)) {
                return r;
            }
        }
        {
            ko r = reader.read(ts_creation);
            if (is_ko(r)) {
                return r;
            }
        }
        {
            ko r = reader.read(ts_activity);
            if (is_ko(r)) {
                return r;
            }
        }
        {
            ko r = reader.read(subhome);
            if (is_ko(r)) {
                return r;
            }
        }
        {
            ko r = qr.from_blob(reader);
            if (is_ko(r)) {
                return r;
            }
        }
        return ok;
    }

    public hash_t tid = new hash_t();
    public ts_t ts_creation = new ts_t(0);
    public ts_t ts_activity = new ts_t(0);
    public string subhome  = new string();
    public qr_t qr = new qr_t();
            
}

