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
//MIM    'api__datatype': 'trades_index_t' @ core0/core0_8/core0_86/us/gui/trades/conf/active/mim.h
//MIM    'classname': 'trades__conf__active0'
//MIM    'include': 'import us.gov.crypto...'
//MIM  kickoff code hash: ubJvCasjCbAhgHbUSx8exEufscz (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import java.util.ArrayList;
//MIM begin token 'include'
import us.gov.crypto.ripemd160.hash_t;
import us.wallet.trader.trader_brief_t;
import us.wallet.trader.trades_index_t;
//MIM end token 'include'

public class trades__conf__active0__datatype_t extends ArrayList<trades__conf__active0__itemtype_t> {

    private static void log(final String line) {                       //--strip
        CFG.log_android("trades__conf__active0__datatype_t: " + line);         //--strip
    }                                                                  //--strip

    public trades__conf__active0__datatype_t(final trades_index_t o) {
        log("constructor "); //--strip
        ensureCapacity(o.size());
        for (trader_brief_t i: o) {
            add(new trades__conf__active0__itemtype_t(i));
        }
    }

}

