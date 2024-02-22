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
package us.cash;
import us.gov.cash.account_t;
import static us.gov.cash.types.cash_t;
//import static us.gov.crypto.ripemd160.hash_t;
//import static us.stdint.*;

public final class token_t {

    private static void log(final String line) {           //--strip
        CFG.log_android("token_t: " + line);         //--strip
    }                                                      //--strip

    public token_t(final String token, final String amount) {
        this.coin = token;
        this.balance = amount;
    }

    public String coin;
    public String balance;
}

