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
//MIM  kickoff code hash: 361GSo9oXU9EMaR53W3z5dhN1EUt (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import us.string;
import static us.gov.crypto.ripemd160.hash_t;
import static us.stdint.*;
import static us.gov.io.types.blob_t;
import us.gov.io.blob_reader_t;
import android.os.Bundle;                                                                      // Bundle
import java.util.ArrayList;
import us.wallet.trader.protocol_selection_t;
import static us.ko.*;
import us.ko;
import us.wallet.trader.data_t;

public abstract class trader__conf_s_r2r extends trader__conf_s_chat {

    private static void log(final String line) {                              //--strip
        CFG.log_android("trader__conf_s_r2r: " + line);          //--strip
    }                                                                         //--strip

    public trader__conf_s_r2r() {
        super();
    }

    @Override protected void on_data() {
        log("on_data"); //--strip
        super.on_data();
    }

    @Override protected void on_data__children() {
        if (curtab != 5) {
            super.on_data__children();
            return;
        }        
        fragment f = fragments.get(curtab);
        trader__conf__r2r x = (trader__conf__r2r)f;
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

    public ArrayList<protocol_selection_t> roles_from_data() {
        ArrayList<protocol_selection_t> o = new ArrayList<protocol_selection_t>();
        data_t data = get_data();
        if (data == null) {
            return o;
        }
        String r = data.find("protocol");
        if (r == null) {
            return o;
        }
        log("'protocol': " + r); //--strip
        if (r.equals("not set")) {
            return o;
        }
        protocol_selection_t protocol_selection = new protocol_selection_t();
        if (is_ko(protocol_selection.from_string(r))) {
            log("KO 55094 Invalid protocol_selection " + r); //--strip
            return o;
        }
        o.add(protocol_selection);
        return o;
    }

    public void start_stop_protocol(protocol_selection_t protocol_selection, boolean is_stop) {
        if (is_stop) {
            command_trade("end");
        }
        else {
            command_trade("start " + protocol_selection.to_string());
        }   
        set_timeout();
    }

}
