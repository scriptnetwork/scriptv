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
//MIM    'api__datatype': 'timeseries_index_t' @ core0/core0_8/core0_84/us/gui/iot/conf/sinks/mim.h
//MIM    'classname': 'iot__conf__sinks0'
//MIM    'include': 'import static us.gov...'
//MIM  kickoff code hash: 2srpHE6EXSGbc1JarbDsZPhg8eEt (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import java.util.ArrayList;
//MIM begin token 'include'
import static us.gov.crypto.ripemd160.hash_t;
import us.wallet.wallet.timeseries_index_t;
//MIM end token 'include'

public class iot__conf__sinks0__datatype_t extends ArrayList<iot__conf__sinks0__itemtype_t> {

    private static void log(final String line) {                       //--strip
        CFG.log_android("iot__conf__sinks0__datatype_t: " + line);         //--strip
    }                                                                  //--strip

    public iot__conf__sinks0__datatype_t(final timeseries_index_t o) {
        log("constructor "); //--strip
        
        ensureCapacity(o.size());
        for (hash_t i: o) {
            add(new iot__conf__sinks0__itemtype_t("unnamed", i.encode())); //kickoff entry - delete-
        }
        //TODO: 
        //add(new iot__conf__sinks0__itemtype_t()); //kickoff entry - delete-
        //example apitype is a map
        //ensureCapacity(o.size());
        //for (Map.Entry<String, # #apiitemtype# #> entry : o.entrySet()) {
        //    add(new # #classname# #__datatype_t(entry.getKey(), entry.getValue()));
        //}
    }

    public void add(final hash_t addr) {
        add(new iot__conf__sinks0__itemtype_t("unnamed", addr.encode()));
    }
}

