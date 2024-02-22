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
import us.gov.crypto.ripemd160.hash_t;                                                         // hash_t
import us.CFG;                                                                                 // CFG
import static us.stdint.*;                                                                     // *
import us.ko;                                                                                  // ko
import us.gov.io.seriable;                                                                     // seriable
import static us.gov.io.types.blob_t.serid_t;                                                  // serid_t
import static us.gov.io.types.vector_hash;                                                     // vector_hash

@SuppressWarnings("serial")
public class safe_deposit_box implements seriable {
    public static cash_t burn_fee = new cash_t(CFG.CRYPTOECONOMICS_BURN_FEE);

    @Override public serid_t serial_id() { return serid_t.no_header; }

    @Override public int blob_size() {
        int sz = blob_writer_t.blob_size(value) + 1;
        if (m != null) sz += m.blob_size();
        if (f != null) sz += f.blob_size();
        if (t != null) sz += t.blob_size();
        return sz;
    }

    @Override public void to_blob(blob_writer_t writer) {
        writer.write(value);
        uint8_t i = new uint8_t((short)0);
        if (m != null) i.value |= 1;
        if (f != null) i.value |= (1 << 1);
        if (t != null) i.value |= (1 << 2);
        writer.write(i);
        if (m != null) m.to_blob(writer);
        if (f != null) f.to_blob(writer);
        if (t != null) t.to_blob(writer);
    }

    @Override public ko from_blob(blob_reader_t reader) {
        {
            cash_t v = new cash_t();
            ko r = reader.read(v);
            if (ko.is_ko(r)) return r;
            value = v;
        }
        uint8_t i = new uint8_t();
        {
            ko r = reader.read(i);
            if (ko.is_ko(r)) return r;
        }
        {
            if ((i.value & 1) == 0) {
                if (m != null) {
                    m = null;
                }
            }
            else {
                if (m == null) m = new m_t();
                ko r = m.from_blob(reader);
                if (ko.is_ko(r)) return r;
            }
        }
        {
            if ((i.value & (1 << 1)) == 0) {
                if (f != null) {
                    f = null;
                }
            }
            else {
                if (f == null) f = new f_t();
                ko r = f.from_blob(reader);
                if (ko.is_ko(r)) return r;
            }
        }
        {
            if ((i.value & (1 << 2)) == 0) {
                if (t != null) {
                    t = null;
                }
            }
            else {
                if (t == null) t = new t_t();
                ko r = t.from_blob(reader);
                if (ko.is_ko(r)) return r;
            }
        }
        return ko.ok;
    }

    public int mint_type(final hash_t address) { //0 not a mint; 1 open mint; 2 sealed mint
        if (t == null) return 0;
        return t.mint_type(address);
    }

    public cash_t value = cash_t.zero_; //gas
    public m_t m = null;
    public f_t f = null;
    public t_t t = null;

}

