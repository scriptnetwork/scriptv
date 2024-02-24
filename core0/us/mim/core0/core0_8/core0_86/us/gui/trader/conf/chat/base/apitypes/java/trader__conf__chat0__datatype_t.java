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
//MIM    'api__datatype': 'chat_t' @ core0/core0_8/core0_86/us/gui/trader/conf/chat/mim.h
//MIM    'classname': 'trader__conf__chat0'
//MIM    'include': 'import static us.wal...'
//MIM  kickoff code hash: 3UJ7HjxiS3ftKZH1cguTAtfhDXwz (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import java.util.ArrayList;
//MIM begin token 'include'
import static us.wallet.trader.chat.chat_entry;
import static us.wallet.trader.chat.chat_t;
//MIM end token 'include'
import java.util.Map;
import static us.gov.socket.types.ts_t;

public class trader__conf__chat0__datatype_t extends ArrayList<trader__conf__chat0__itemtype_t> {

    private static void log(final String line) {                       //--strip
        CFG.log_android("trader__conf__chat0__datatype_t: " + line);         //--strip
    }                                                                  //--strip

    public trader__conf__chat0__datatype_t(final chat_t o) {
        log("constructor "); //--strip
        //TODO: 
//        add(new trader__conf__chat0__itemtype_t("example", null)); //kickoff entry - delete-
        //example apitype is a map
        ensureCapacity(o.size());
        for (Map.Entry<ts_t, chat_entry> entry : o.entrySet()) {
            add(new trader__conf__chat0__itemtype_t(entry.getKey(), entry.getValue()));
        }
    }

}

