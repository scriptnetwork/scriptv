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
//MIM    mim file: core0/core0_8/core0_86/us/gui/trader/conf/mim.h
//MIM  kickoff code hash: 4Y4D7Zvze4eDVFSn5iHjSy16LWwd (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import us.string;
import static us.gov.crypto.ripemd160.hash_t;
import static us.stdint.*;
import static us.gov.io.types.blob_t;
import us.gov.io.blob_reader_t;
import us.wallet.trader.protocol_selection_t;
import java.util.ArrayList;
import android.os.Bundle;                                                                      // Bundle
import us.pair;
import static us.ko.*;
import us.ko;
import android.os.Handler;
import us.wallet.trader.data_t;

public abstract class trader__conf_s_role extends trader__conf_s_r2r {

    private static void log(final String line) {                              //--strip
        CFG.log_android("trader__conf_s_role: " + line);          //--strip
    }                                                                         //--strip

    public trader__conf_s_role() {
        super();
    }

    public static class array_comp {
        public static pair<ArrayList<protocol_selection_t>, ArrayList<protocol_selection_t>> compareaa(ArrayList<protocol_selection_t> existing, ArrayList<protocol_selection_t> wanted) { //to_open , to_close
            log("existing/wanted-->2open/2close. existing: " + existing.size() + " wanted: " + wanted.size()); //--strip
            ArrayList<protocol_selection_t> to_open = new ArrayList<protocol_selection_t>();
            ArrayList<protocol_selection_t> to_close = new ArrayList<protocol_selection_t>();
            for (protocol_selection_t i: existing) {
                boolean found = false;
                for (protocol_selection_t j: wanted) {
                    if (i.equals(j)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    to_close.add(i);
                }
            }
            for (protocol_selection_t i: wanted) {
                log("wanted " + i.to_string()); //--strip
                boolean found = false;
                for (protocol_selection_t j: existing) {
                    log("  check existing " + j.to_string()); //--strip
                    if (i.equals(j)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    to_open.add(i);
                }
            }
            return new pair<ArrayList<protocol_selection_t>, ArrayList<protocol_selection_t>>(to_open, to_close);
        }
    }

    void set_role_tabs() {
        log("open_role_tabs"); //--strip
        ArrayList<protocol_selection_t> o = roles_from_data();
        pair<ArrayList<protocol_selection_t>, ArrayList<protocol_selection_t>> cmd = array_comp.compareaa(dyn_tabs_0, o); //(existing, wanted) ==> (to_open, to_close)
        for (protocol_selection_t p: cmd.second) { //to_close
            close_dyn_tab0(p);
        }
        boolean newdyntabs = false;
        for (protocol_selection_t p: cmd.first) { //to_open
            add_dyn_tab_0(p, false);
            newdyntabs = true;
        } 
        if (newdyntabs) {
            new Handler().postDelayed(new Runnable() {
                @Override public void run() {
                    w.tabs.scroll_right();
                }
            }, 1000);
        }
    }

    @Override protected void on_data() {
        log("on_data"); //--strip
        super.on_data();
        set_role_tabs();
    }

    @Override protected void on_data__children() {
        if (curtab < num_static_tabs) {
            super.on_data__children();
            return;
        }        
        fragment f = fragments.get(curtab);
        if (f == null) return;
        trader__conf__role x = (trader__conf__role)f;
        x.on_data(get_data());
    }

    @Override public void on_push(final hash_t target_tid, final uint16_t code, final byte[] payload) {
        log("on_push"); //--strip
        switch(code.value) {
            default: {
                super.on_push(target_tid, code, payload);
            }
            break;
        }
    }

    public void request_logo() {
        log("request_logo"); //--strip
        data_t data = get_data();
        if (data == null) {
            request_data();
            return;
        }
        String backend_has_item = data.find("logo");
        if (backend_has_item == null) {
            log("data doesn't contain the item"); //--strip
            return;
        }
        if (!backend_has_item.equals("Y")) {
            log("backend doesn't have the item"); //--strip
            command_trade("logo request");
            return;
        }
        command_trade("show logo");
    }

    @Override protected fragment new_role_frontend(final protocol_selection_t protocol_selection) {
        return a.r2r_libs.get_trader__conf__role(protocol_selection);
    }


}
