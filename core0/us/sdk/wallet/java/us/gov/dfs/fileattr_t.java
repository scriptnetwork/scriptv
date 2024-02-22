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
package us.gov.dfs;
import us.gov.io.blob_reader_t;                                                                // blob_reader_t
import us.gov.io.blob_writer_t;                                                                // blob_writer_t
import us.CFG;                                                                                 // CFG
import us.gov.crypto.ripemd160.hash_t;                                                         // hash_t
import static us.stdint.*;                                                                     // *
import us.ko;                                                                                  // ko
import java.io.PrintStream;                                                                    // PrintStream
import us.gov.io.seriable;                                                                     // seriable
import static us.gov.io.types.blob_t.serid_t;                                                  // serid_t
import us.string;                                                                              // string

@SuppressWarnings("serial")
public class fileattr_t implements seriable {

    public fileattr_t() {
        sz = new uint32_t(0);
        path = new string();
    }

    public fileattr_t(final string path_, final uint32_t sz_) {
        sz = sz_;
        path = new string(us.gov.io.cfg0.rewrite_path(path_.value));
    }

    public void dump(PrintStream os) {
        os.println(path.value + ' ' + sz.value + " bytes");
    }

    @Override public serid_t serial_id() { return serid_t.no_header; }

    @Override public int blob_size() {
        return blob_writer_t.blob_size(sz) + blob_writer_t.blob_size(path);
    }

    @Override public void to_blob(blob_writer_t writer) {
        writer.write(sz);
        writer.write(path);
    }

    @Override public ko from_blob(blob_reader_t reader) {
        {
            ko r = reader.read(sz);
            if (ko.is_ko(r)) return r;
        }
        {
            ko r = reader.read(path);
            if (ko.is_ko(r)) return r;
        }
        return ko.ok;
    }

    public uint32_t sz;
    public string path;
};

