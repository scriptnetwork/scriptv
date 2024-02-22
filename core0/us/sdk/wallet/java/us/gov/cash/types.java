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
import static us.stdint.*;                                                                     // *
import us.ko;                                                                                  // ko
import us.pair;                                                                                // pair
import static us.gov.socket.types.ts_t;                                                        // ts_t

public class types {

    public static class cash_t extends int64_t {
        public static final cash_t zero_ = new cash_t(0);

        public cash_t() { super(); }
        public cash_t(long v) { super(v); }

    }

    public static class track_t extends ts_t {

        public track_t() { super(); }
        public track_t(long v) { super(v); }

    }

    public static class locking_program_t extends uint8_t {

        public locking_program_t() { super(); }
        public locking_program_t(short v) { super(v); }

    }

}

