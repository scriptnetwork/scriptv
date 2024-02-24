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
package us.gov.relay;
import us.gov.io.blob_reader_t;                                                                // blob_reader_t
import us.gov.io.blob_writer_t;                                                                // blob_writer_t
import us.gov.socket.datagram;                                                                 // datagram
import static us.gov.crypto.ripemd160.hash_t;                                                  // hash_t
import static us.gov.io.types.*;                                                               // *
import static us.gov.socket.types.*;                                                           // *
import static us.stdint.*;                                                                     // *
import us.ko;                                                                                  // ko
import static us.ko.ok;                                                                        // ok
import us.gov.protocol;                                                                        // protocol
import java.security.PublicKey;                                                                // PublicKey
import us.gov.crypto.sha256;                                                                   // sha256

public interface dto {

    //#include <us/api/generated/gov/java/relay/cllr_dto-hdr>
    //------------------------------------------------------------__begin__------generated by configure, do not edit.
    //content of file: <us/api/generated/gov/java/relay/cllr_dto-hdr>
    // relay - master file: us/api/data/gov/relay


    /// push - IN
    public static final class push_in_t extends blob_writer_t.writable {
        //source: coder_java.cpp::gen_dto_in_hdr (1)

        public push_in_t(final hash_t tid, final uint16_t code, final blob_t blob) {
            this.tid = tid;
            this.code = code;
            this.blob = blob;
        }

        @Override public int blob_size() {
            return blob_writer_t.blob_size(tid) +
                blob_writer_t.blob_size(code) +
                blob_writer_t.blob_size(blob);
        }

        @Override public void to_blob(blob_writer_t writer) {
            writer.write(tid);
            writer.write(code);
            writer.write(blob);
        }

        public datagram get_datagram(final channel_t channel, final seq_t seq) {
            return super.get_datagram(channel, new svc_t(protocol.relay_push), seq);
        }

        public static datagram get_datagram(final channel_t channel, final seq_t seq, final hash_t tid, final uint16_t code, final blob_t blob) {
            push_in_t o = new push_in_t(tid, code, blob);
            return o.get_datagram(channel, seq);
        }

        final hash_t tid;
        final uint16_t code;
        final blob_t blob;
    }
    //-/----------------------------------------------------------___end___------generated by configure, do not edit.

    //#include <us/api/generated/gov/java/relay/hdlr_dto-hdr>
    //------------------------------------------------------------__begin__------generated by configure, do not edit.
    //content of file: <us/api/generated/gov/java/relay/hdlr_dto-hdr>
    // relay - master file: us/api/data/gov/relay


    public static final class push_in_dst_t extends blob_reader_t.readable {
        //source: coder_java.cpp::gen_dto_in_hdr (2)

        public push_in_dst_t() {}


        @Override public ko from_blob(blob_reader_t reader) {
            {
                ko r = reader.read(tid);
                if (ko.is_ko(r)) return r;
            }
            {
                ko r = reader.read(code);
                if (ko.is_ko(r)) return r;
            }
            {
                ko r = reader.read(blob);
                if (ko.is_ko(r)) return r;
            }
            return ok;
        }

        public hash_t tid = new hash_t();
        public uint16_t code = new uint16_t();
        public blob_t blob = new blob_t();
    }
    //-/----------------------------------------------------------___end___------generated by configure, do not edit.

}

