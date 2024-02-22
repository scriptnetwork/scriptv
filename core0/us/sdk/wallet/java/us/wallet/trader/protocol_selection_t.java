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
import us.CFG;                                                                                 // CFG
import java.lang.Comparable;                                                                   // Comparable
import us.stdint.*;                                                                            // *
import java.io.InputStream;                                                                    // InputStream
import static us.ko.is_ko;                                                                     // is_ko
import us.ko;                                                                                  // ko
import java.util.NoSuchElementException;                                                       // NoSuchElementException
import static us.ko.ok;                                                                        // ok
import java.io.OutputStream;                                                                   // OutputStream
import us.pair;                                                                                // pair
import java.io.PrintStream;                                                                    // PrintStream
import java.util.Scanner;                                                                      // Scanner
import static us.gov.io.types.blob_t.serid_t;                                                  // serid_t
import java.nio.charset.StandardCharsets;                                                      // StandardCharsets

public class protocol_selection_t extends pair<String, String> implements us.gov.io.seriable, Comparable<protocol_selection_t> {

    static void log(final String line) {                         //--strip
       CFG.log_wallet_trader("protocol_selection_t: " + line);   //--strip
    }                                                            //--strip

    public protocol_selection_t() {
        this.first = "";
        this.second = "";
    }

    public protocol_selection_t(String p, String r) {
        this.first = p;
        this.second = r;
    }

    public protocol_selection_t(protocol_selection_t other) {
        this.first = new String(other.first);
        this.second = new String(other.second);
    }

    @Override public int compareTo(protocol_selection_t other) {
        int x = this.first.compareTo(other.first);
        if (x != 0) {
            return x;
        }
        return this.second.compareTo(other.second);
    }

    public boolean equals(protocol_selection_t other) {
        String a = first == null ? "" : first;
        String b = second == null ? "" : second;
        String oa = other.first == null ? "" : other.first;
        String ob = other.second == null ? "" : other.second;
        if (!a.equals(oa)) return false;
        return b.equals(b);
    }

    static void to_streamX(String o, PrintStream os) {
        if (o.isEmpty()) {
            os.print("- ");
        }
        else {
            os.print(o + ' ');
        }
    }

    public void to_streamX(PrintStream os) {
        to_streamX(first, os);
        to_streamX(second, os);
    }

    static pair<ko, String> from_scanner_string(Scanner sc) {
        pair<ko, String> r = new pair<ko, String>();
        try {
            r.second = sc.next();
            if (r.second.equals("-")) r.second = "";
            r.first = ok;
        }
        catch (NoSuchElementException e) {
            log("String has ended. Returning empty string."); //--strip
            r.second = "";
            r.first = ok;
        }
        catch (Exception e) {
            r.second = null;
            r.first = new ko("KO 65904 " + e.getMessage());
        }
        return r;
    }

    public ko from_string(String pr) {
        InputStream is = new ByteArrayInputStream(pr.getBytes(StandardCharsets.UTF_8));
        return from_stream(is);
    }

    public String to_string() {
        return first + ' ' + second;
    }

    public String to_string2() {
        return first + '-' + second;
    }

    public ko from_stream(InputStream is) {
        Scanner sc = new Scanner(is);
        return from_scanner(sc);
    }

    public ko from_scanner(Scanner sc) {
        {
            pair<ko, String> r = from_scanner_string(sc);
            if (is_ko(r.first)) {
                return r.first;
            }
            first = r.second;
        }
        {
            pair<ko, String> r = from_scanner_string(sc);
            if (is_ko(r.first)) {
                return r.first;
            }
            second = r.second;
        }
        return ok;
    }

    @Override public serid_t serial_id() { return serid_t.no_header; }

    @Override public int blob_size() {
        return blob_writer_t.blob_size(first) + blob_writer_t.blob_size(second);
    }

    @Override public void to_blob(blob_writer_t writer) {
        writer.write(first);
        writer.write(second);
    }

    @Override public ko from_blob(blob_reader_t reader) {
        {
            pair<ko, String> r = reader.read_string();
            if (is_ko(r.first)) {
                return r.first;
            }
            first = r.second;
        }
        {
            pair<ko, String> r = reader.read_string();
            if (is_ko(r.first)) {
                return r.first;
            }
            second = r.second;
        }
        return ok;
    }

    public boolean is_symmetric() {
        String[] r = first.split("2");
        if (r.length != 2) return false;
        if (r[0].equals(r[1])) return true;
        return false;
    }

}

