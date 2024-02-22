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
import static us.gov.crypto.ripemd160.hash_t;
import us.pair;
import java.util.ArrayList;
import java.util.Map;

public class accounts_t extends ArrayList<account_t> {

    static void log(final String line) {                  //--strip
       CFG.log_android("accounts_t: " + line);            //--strip
    }                                                     //--strip

    public accounts_t(final us.gov.cash.accounts_t x) {
        ensureCapacity(size());
        for (Map.Entry<hash_t, us.gov.cash.account_t> entry : x.entrySet()) {
            add(new account_t(entry.getKey(), entry.getValue()));
        }
    }

}

