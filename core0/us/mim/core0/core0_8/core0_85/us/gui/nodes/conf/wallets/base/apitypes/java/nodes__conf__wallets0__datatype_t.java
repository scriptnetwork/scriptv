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
//MIM  ******************************************************************************
//MIM  EDITABLE FILE. Changes on this file will NOT be overwritten by MIM.
//MIM  The first version of this file was produced by MIM. You can edit it afterwards.
//MIM
//MIM  Source:
//MIM        file: core0/core0_6/core0_61/us/gui/activity/listview_controller/base/apitypes/java/[classname]__datatype_t.java
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/listview_controller/base/apitypes/mim.h
//MIM  Params:
//MIM    'api__datatype': 'vector_hash' @ core0/core0_8/core0_85/us/gui/nodes/conf/wallets/mim.h
//MIM    'classname': 'nodes__conf__wallets0'
//MIM    'include': 'import static us.gov...'
//MIM  kickoff code hash: 4P6jwuaP2oMtAw3DkqhLfuQcnEdv (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import java.util.ArrayList;
//MIM begin token 'include'
import static us.gov.crypto.ripemd160.hash_t;
import us.gov.io.types.vector_hash;
import us.wallet.trader.bootstrap.protocols_t;
//MIM end token 'include'

public class nodes__conf__wallets0__datatype_t extends ArrayList<nodes__conf__wallets0__itemtype_t> {

    private static void log(final String line) {                       //--strip
        CFG.log_android("nodes__conf__wallets0__datatype_t: " + line);         //--strip
    }                                                                  //--strip

    public nodes__conf__wallets0__datatype_t(final vector_hash o) {
        log("constructor "); //--strip
        ensureCapacity(o.size());
        for (hash_t entry: o) {
            add(new nodes__conf__wallets0__itemtype_t(entry));
        }
    }

}

