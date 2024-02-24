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
import static us.gov.io.types.blob_t.serid_t;                                                  // serid_t
import static us.gov.io.types.vector_hash;                                                     // vector_hash

@SuppressWarnings("serial")
public class account_t implements seriable {

    public account_t() {
        locking_program = app.locking_program_id;
    }

    public final cash_t get_value_oil() {
        return box.value;
    }

    public int num_coins() {
        if (box.t == null) {
            return 0;
        }
        return box.t.size();
    }

    final cash_t get_value(final hash_t token) {
        if (token.is_zero()) return box.value;
        if (box.t == null) return cash_t.zero_;
        return box.t.get_value(token);
    }

    public void dump(final String prefix, final hash_t addr, int detail, PrintStream os) {
        os.print(prefix + addr.encode() + ' ');
        if (detail > 1) {
            if (locking_program != app.locking_program_id) {
                os.print("(locking_program " + locking_program.value + ") ");
            }
        }
        os.print(CFG.UGAS + ' ' + box.value.value);
        if (detail == 0) {
            if (box.m != null || box.f != null || box.t != null) {
                os.println(" *");
            }
            else {
                os.println();
            }
            return;
        }
        if (box.m != null) {
            os.print("; " + box.m.size() + " kv records");
        }
        if (box.f != null) {
            os.print("; " + box.f.size() + " files. " + box.f.total_mib() + " MiB");
        }
        if (box.t != null) {
            os.print("; " + box.t.size() + " coins");
        }
        os.println();
        if (detail > 1) {
            String pfx = prefix + "    ";
            String pfx2 = pfx + "    ";
            if (box.t != null) {
                os.println(pfx + "Coins:");
                box.t.dump(pfx2, addr, os);
            }
            if (box.m != null) {
                os.println(pfx + "Key-value:");
                box.m.dump(pfx2, os);
            }
            if (box.f != null) {
                os.println(pfx + "Files:");
                box.f.dump(pfx2, os);
            }
        }
    }

    @Override public serid_t serial_id() { return serid_t.no_header; }

    @Override public int blob_size() {
        return blob_writer_t.blob_size(locking_program) + blob_writer_t.blob_size(box);
    }

    @Override public void to_blob(blob_writer_t writer) {
        writer.write(locking_program);
        box.to_blob(writer);
    }

    @Override public ko from_blob(blob_reader_t reader) {
        {
            ko r = reader.read(locking_program);
            if (ko.is_ko(r)) return r;
        }
        {
            ko r = box.from_blob(reader);
            if (ko.is_ko(r)) return r;
        }
        return ko.ok;
    }

    public int mint_type(final hash_t address) { //0 not a mint; 1 open mint; 2 sealed mint
        return box.mint_type(address);
    }

    public locking_program_t locking_program;
    public safe_deposit_box box = new safe_deposit_box();
}


