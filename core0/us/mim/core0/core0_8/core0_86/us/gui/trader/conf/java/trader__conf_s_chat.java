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
//MIM  kickoff code hash: 3F7ZS63sdW4a2t7SvA6iw4NLpPXW (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import us.string;
import static us.gov.crypto.ripemd160.hash_t;
import static us.stdint.*;
import static us.gov.io.types.blob_t;
import us.gov.io.blob_reader_t;
import android.os.Bundle;                                                                      // Bundle
import java.util.Timer;
import java.util.TimerTask;
import static us.ko.*;
import us.ko;
import com.google.android.material.tabs.TabLayout;

public abstract class trader__conf_s_chat extends trader__conf_s_share {

    private static void log(final String line) {                              //--strip
        CFG.log_android("trader__conf_s_chat: " + line);          //--strip
    }                                                                         //--strip

    public trader__conf_s_chat() {
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
        blinker.stop_chat_blink();
        super.controller__on_destroy();
    }
    //-/------------lifecycle-------------------------------------------------------

    @Override protected void on_data() {
        log("on_data"); //--strip
        super.on_data();
    }

    @Override protected void on_data__children() {
        super.on_data__children();
    }

    @Override public void on_push(final hash_t target_tid, final uint16_t code, final byte[] payload) {
        log("on_push"); //--strip
        switch(code.value) {
            case us.wallet.trader.trader_t.push_chat:
                if (curtab != 4) {
                    blinker.start();
                }
                break;
                
            default: {
                super.on_push(target_tid, code, payload);
            }
            break;
        }
    }

    @Override void on_tab_selected(int pos) {
        log("on_tab_selected pos: " + pos); //--strip
        if (pos == 4) {
            blinker.stop_chat_blink();
        }
        super.on_tab_selected(pos);
    }

    class blinker_t {

        public void start() {
            if (chat_blink_task != null) return;
            chat_blink_task = new TimerTask() {
                @Override public void run() {
                    a.assert_worker_thread(); //--strip
                    runOnUiThread(new Runnable() {
                        @Override public void run() {
                            chat_blink();
                        }
                    });
                }
            };
            timer.scheduleAtFixedRate(chat_blink_task, 10, 500);
        }
        
        public void stop_chat_blink() {
            a.assert_ui_thread(); //--strip
            if (chat_blink_task == null) return;
            chat_blink_task.cancel();
            timer.purge();
            chat_blink_task = null;
            TabLayout.Tab tab = w.tabs.getTabAt(4);
            tab.setIcon(us.cash.app.a.le.resolve_resid(R.raw.chat));
            cur_chat_blink_ico = R.raw.chat;
        }

        private void chat_blink() {
            a.assert_ui_thread(); //--strip
            if (cur_chat_blink_ico == R.raw.chat) {
                cur_chat_blink_ico = R.raw.chat_blink;
            }
            else {
                cur_chat_blink_ico = R.raw.chat;
            }
            TabLayout.Tab tab = w.tabs.getTabAt(4);
            tab.setIcon(us.cash.app.a.le.resolve_resid(cur_chat_blink_ico));
        }

        private Timer timer = new Timer();
        private TimerTask chat_blink_task = null;
        private int cur_chat_blink_ico = R.raw.chat;

    }

    blinker_t blinker = new blinker_t();

}
