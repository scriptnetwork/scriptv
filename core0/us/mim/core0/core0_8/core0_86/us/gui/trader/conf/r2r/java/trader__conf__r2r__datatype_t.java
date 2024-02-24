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
import java.util.ArrayList;
import us.wallet.trader.roles_t;
import us.wallet.trader.protocol_selection_t;
import us.pair;

public class trader__conf__r2r__datatype_t extends ArrayList<trader__conf__r2r__itemtype_t> {

    private static void log(final String line) {                       //--strip
        CFG.log_android("trader__conf__r2r__datatype_t: " + line);         //--strip
    }                                                                  //--strip

    public trader__conf__r2r__datatype_t(final roles_t o, final ArrayList<protocol_selection_t> running_ps) {
        log("constructor "); //--strip
        //TODO: 
        ensureCapacity(o.size());
        for (pair<String, String> i: o) {
            protocol_selection_t ps = new protocol_selection_t(i.first, i.second);
            boolean running = false;
            for (protocol_selection_t rps: running_ps) {
                if (rps.equals(ps)) {
                    running = true;
                    break;
                }
            }
            add(new trader__conf__r2r__itemtype_t(ps, running));
        }

 //       add(new iot__conf__sources0__itemtype_t("example", null)); //kickoff entry - delete-
        //example apitype is a map
        //ensureCapacity(o.size());
        //for (Map.Entry<String, # #apiitemtype# #> entry : o.entrySet()) {
        //    add(new # #classname# #__datatype_t(entry.getKey(), entry.getValue()));
        //}
    }

}

