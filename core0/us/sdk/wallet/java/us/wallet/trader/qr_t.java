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
import java.io.ByteArrayInputStream;                                                           // ByteArrayInputStream
import java.io.ByteArrayOutputStream;                                                          // ByteArrayOutputStream
import us.CFG;                                                                                 // CFG
import us.stdint.*;                                                                            // *
import java.io.InputStream;                                                                    // InputStream
import static us.ko.is_ko;                                                                     // is_ko
import us.ko;                                                                                  // ko
import static us.ko.ok;                                                                        // ok
import java.io.OutputStream;                                                                   // OutputStream
import us.pair;                                                                                // pair
import java.io.PrintStream;                                                                    // PrintStream
import java.util.Scanner;                                                                      // Scanner
import static us.gov.io.types.blob_t.serid_t;                                                  // serid_t

///<orma transpile src="c++" id="us::wallet::trader::qr_t"/>
public class qr_t implements us.gov.io.seriable {

    static void log(final String line) {                         //--strip
       CFG.log_wallet_trader("qr_t: " + line);                   //--strip
    }                                                            //--strip

    public qr_t() {
        endpoint = new endpoint_t();
        protocol_selection = new protocol_selection_t();
    }

    public qr_t(endpoint_t ep, protocol_selection_t p) {
        endpoint = ep;
        protocol_selection = p;
    }

    public qr_t(endpoint_t ep) {
        endpoint = ep;
        protocol_selection = new protocol_selection_t();
    }

    public qr_t(qr_t other) {
        endpoint = new endpoint_t(other.endpoint);
        protocol_selection = new protocol_selection_t(other.protocol_selection);
    }

    static serid_t serid = new serid_t((short)'Q');
    @Override public serid_t serial_id() { return serid; }

    @Override public int blob_size() {
        return endpoint.blob_size() + protocol_selection.blob_size();
    }

    @Override public void to_blob(blob_writer_t writer) {
        log("writing qr"); //--strip
        endpoint.to_blob(writer);
        protocol_selection.to_blob(writer);
    }

    @Override public ko from_blob(blob_reader_t reader) {
        log("reading qr"); //--strip
        {
            ko r = endpoint.from_blob(reader);
            if (is_ko(r)) {
                return r;
            }
        }
        {
            ko r = protocol_selection.from_blob(reader);
            if (is_ko(r)) {
                return r;
            }
        }
        return ok;
    }

    public ko from_stream(InputStream is) {
        Scanner sc = new Scanner(is);
        return from_scanner(sc);
    }

     public ko from_scanner(Scanner sc) {
        log("QR1"); //--strip
        ko r = endpoint.from_scanner(sc);
        if (is_ko(r)) {
            return r;
        }
        return protocol_selection.from_scanner(sc);
    }

    public void to_streamX(PrintStream os) {
        endpoint.to_streamX(os);
        protocol_selection.to_streamX(os);
    }

    public String to_string() {
        return endpoint.to_string() + protocol_selection.to_string();
    }

    public ko from_string(String s) {
        ByteArrayInputStream is = new ByteArrayInputStream(s.getBytes());
        return from_stream(is);
    }

    public endpoint_t endpoint;
    public protocol_selection_t protocol_selection;

};

