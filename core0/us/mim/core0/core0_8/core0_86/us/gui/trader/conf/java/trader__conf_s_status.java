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
//MIM  kickoff code hash: foDboaagq7JYVTwZASM7p4Tm15x (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import us.string;
import static us.gov.crypto.ripemd160.hash_t;
import static us.stdint.*;
import static us.gov.io.types.blob_t;
import us.gov.io.blob_reader_t;
import us.wallet.trader.data_t;
import android.os.Bundle;                                                                      // Bundle
import static us.ko.*;
import us.ko;

public abstract class trader__conf_s_status extends trader__conf__impl {

    private static void log(final String line) {                              //--strip
        CFG.log_android("trader__conf_s_status: " + line);          //--strip
    }                                                                         //--strip

    public trader__conf_s_status() {
        super();
    }

    //--------------lifecycle-------------------------------------------------------
    @Override protected void controller__on_create(Bundle saved_state) {
        log("on_create"); //--strip
        super.controller__on_create(saved_state);
    }

    @Override protected void controller__on_pause() {
        log("on_pause"); //--strip
        super.controller__on_pause();
    }

    @Override protected void controller__on_resume() {
        super.controller__on_resume();
        log("on_resume"); //--strip
        request_data();
    }

    @Override protected void controller__on_destroy() {
        log("on_destroy"); //--strip
        super.controller__on_destroy();
    }
    //-/------------lifecycle-------------------------------------------------------

    void set_parent_trade() {
        if (data == null) {
            parent_trade = hash_t.zero_;
            return;
        }
        String pt = data.find("parent_trade");
        if (pt == null) {
            parent_trade = hash_t.zero_;
            return;
        }
        parent_trade = new hash_t(pt);
    }

    @Override protected void on_data() {
        log("on_data"); //--strip
        app.assert_ui_thread(); //--strip
        set_parent_trade();
    }

    @Override protected void on_data__children() {
        if (curtab != 0) return;
        fragment f = fragments.get(curtab);
        trader__conf__status x = (trader__conf__status)f;
        x.on_data(data);
    }
    
    public synchronized data_t get_data() { return data; }
    public synchronized boolean has_data() { return data != null; }

    synchronized void setdata__worker(final data_t data0) {
        app.assert_worker_thread(); //--strip
        data = data0;
        runOnUiThread(new Runnable() {
            @Override public void run() {
                log("data has been renewed -> refresh"); //--strip
                on_data();
                on_data__children();
            }
        });
    }

    @Override public void on_push(final hash_t target_tid, final uint16_t code, final byte[] payload) {
        log("on_push"); //--strip
        switch(code.value) {
            case us.wallet.trader.trader_t.push_data: {
                log("data for me. "); //--strip
                string s = new string();
                {
                    ko r = blob_reader_t.parse(new blob_t(payload), s);
                    if (is_ko(r)) {
                        toast__worker(r.msg);
                        return;
                    }
                }
                data_t data = new data_t();
                ko r = data.from(s.value);
                if (is_ko(r)) {
                    toast__worker(r.msg);
                    return;
                }
                setdata__worker(data);
            }
            
            default: {
                super.on_push(target_tid, code, payload);
            }
            break;
        }
    }

    public void request_data() {
        command_trade("show data");
    }

    public void query_log() {
        command_trade("show log");
    }

    public void command_connect(boolean up) {
        command_trade(up ? "connect" : "disconnect");
    }

    private data_t data = null;
    public hash_t parent_trade = hash_t.zero_;
    
}
