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

public class lib_trader__w2w_w_t implements r2r_lib_t {

    public static final protocol_selection_t protocol_selection = new protocol_selection_t("w2w", "w");


    @Override public trader__conf__role get_trader__conf__role() {
        return new trader__w2w_w();
    }

    @Override public r2r_lib_metainfo_t get_metainfo() {
        r2r_lib_metainfo_t o = new r2r_lib_metainfo_t();
        o.headline = "Bank";
        o.description = "symmetric inter-wallet protocol for banking functions";
        return o;
    }
    
}

