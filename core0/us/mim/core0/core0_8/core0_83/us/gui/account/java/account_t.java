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
import static us.gov.cash.types.cash_t;
import java.util.Map;                                                                          // Map
import static us.gov.crypto.ripemd160.hash_t;

public final class account_t {

    private static void log(final String line) {           //--strip
        CFG.log_android("account_t: " + line);         //--strip
    }                                                      //--strip

    public account_t(final hash_t address, final us.gov.cash.account_t account) {
        if (address != null && address.is_not_zero()) {
            this.address = address.encode();
        }
        locking_program = "P2PKH";
        int sz = account.num_coins();
        log("coins sz = " + sz); //--strip
        coins.ensureCapacity(sz);
        if (sz > 0) {
            assert account.box.t != null; //--strip
            for (Map.Entry<hash_t, cash_t> entry : account.box.t.entrySet()) {
                coins.add(new token_t(entry.getKey().encode(), app.formatter.format(entry.getValue().value)));
            }
        }
        oil = app.a.formatter.format(account.get_value_oil().value);
        //if (sz == 0) { //--strip
        //    coins.add(new token_t("debug_coin", "0"));  //--strip
        //}  //--strip
        log("Account " + this.address + " " + coins.size() + " coin entries"); //--strip
    }

    public int size() {
        return coins.size();
    }

    public token_t get(int pos) {
        return coins.get(pos);
    }

    public String get_address() {
        if (address == null) return "";
        return address;
    }

    public static String get_shortened_address(final String address) {
        if (address == null) return "";
        return address.substring(0, 4) + ".." + address.substring(address.length() - 4, address.length());
    }

    public String get_shortened_address() {
        return get_shortened_address(address);
    }

    public String nft() {
        if (address == null) return "";
        return address;
    }

    public String address = null;     
    public String locking_program;     
    public String oil;
        
    public tokens_t coins = new tokens_t();
}
