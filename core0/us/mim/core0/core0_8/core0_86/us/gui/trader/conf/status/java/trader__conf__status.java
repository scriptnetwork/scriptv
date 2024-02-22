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
//MIM        file: core0/core0_6/core0_61/us/gui/activity/controller/java/[classname].java
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM  Params:
//MIM    'apifetch': '' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'class_qualifier': '' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'classname': 'trader__conf__status'
//MIM    'controller': 'fragment' @ core0/core0_8/core0_86/us/gui/trader/conf/status/mim.h
//MIM    'datatype_decl': '' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'get_menu_override': '@Override protected ...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'include': ''
//MIM    'load__worker': '@Override public ko ...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'menu_max_depth': '1' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'on_menu__handling': '' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'title': 'Status' @ core0/core0_8/core0_86/us/gui/trader/conf/status/mim.h
//MIM  kickoff code hash: 3G2RAbpzDRFc6yWQK9XL3WW7xNuv (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.os.Bundle;                                                                      // Bundle
import us.ko;
import static us.ko.*;
import android.os.Handler;
import us.pair;
import us.string;
import android.content.Intent;                                                                 // Intent
import static us.gov.crypto.ripemd160.hash_t;
import static us.stdint.*;
import android.view.View;                                                                      // View
import static us.gov.io.types.blob_t;
import us.gov.io.blob_reader_t;
import us.wallet.trader.data_t;
import android.widget.CompoundButton;                                                          // CompoundButton
import java.util.Timer;
import java.time.format.DateTimeFormatter;
import java.util.TimerTask;

public class trader__conf__status extends fragment {

    private static void log(final String line) {           //--strip
        CFG.log_android("trader__conf__status: " + line);         //--strip
    }                                                      //--strip

    public trader__conf__status() {
        super();
        log("trader__conf__status constructor"); //--strip
    }

    //--------------lifecycle-------------------------------------------------------
    @Override protected void controller__on_create(Bundle saved_state) {
        assert w == null; //--strip
        set_menu_max_depth(1);
        super.controller__on_create(saved_state);
        log("controller__on_create"); //--strip
        w = (us.cash.scr.trader__conf__status__widgets) super.w;
        assert w != null; //--strip
        tr = (trader__conf)ac;
        load(); //follows on_ready
    }

    @Override protected void controller__on_pause() {
        log("controller__on_pause"); //--strip
        stop_timer_ui_update();
        super.controller__on_pause();
    }

    @Override protected void controller__on_resume() {
        super.controller__on_resume();
        log("controller__on_resume"); //--strip
        start_timer_ui_update();
    }

    @Override protected void controller__on_destroy() {
        log("controller__on_destroy"); //--strip
        w = null;
        super.controller__on_destroy();
    }
    //-/------------lifecycle-------------------------------------------------------

    @Override public String get_title() {
        return "Status";
    }


    //MIM begin token 'load__worker'
    @Override public ko load__worker() {
        log("load__worker"); //--strip
        a.assert_worker_thread(); //--strip
        return ok;
    }
    //MIM end token 'load__worker'

    public void on_ready(final ko load_result) {
        log("on_ready"); //--strip
        a.assert_ui_thread(); //--strip
        w.tid.setText(tr.tid.encode());
        refresh_from_data();
    }

    //MIM begin token 'get_menu_override'
    @Override protected us.cash.scr.menu_t get_menu() {
        if (get_menu_depth() < 1) return super.get_menu();
        assert get_menu_depth() == 1; //--strip
        return us.cash.scr.trader__conf__status__menu___main_t.get_instance(get_context());
    }
    //MIM end token 'get_menu_override'


    @Override public us.cash.scr.view__widgets create_widgets() {
        return new us.cash.scr.trader__conf__status__widgets(
            new CompoundButton.OnCheckedChangeListener() {
                @Override public void onCheckedChanged(CompoundButton buttonView, boolean is_checked) {
                    if (widget_handlers__disabled) return;
                    tr.command_connect(is_checked);
                }
            },
            new View.OnClickListener() {
                @Override public void onClick(View view) {
                    if (widget_handlers__disabled) return;
                    if (w.data.getText().toString().isEmpty()) {
                        tr.request_data();
                    }
                    else {
                        w.data.setText("");
                    }
                }
            },
            new View.OnClickListener() {
                @Override public void onClick(View view) {
                    if (widget_handlers__disabled) return;
                    if (w.log.getText().toString().isEmpty()) {
                        tr.query_log();
                    }
                    else {
                        w.log.setText("");
                    }
                }
            });
    }

    @Override public void on_push(final hash_t target_tid, final uint16_t code, final byte[] payload) {
        if(!tr.tid.equals(target_tid)) return;
        switch(code.value) {
            case us.wallet.trader.trader_t.push_log: {
                log("log for me. "); //--strip
                string s = new string();
                ko r = blob_reader_t.parse(new blob_t(payload), s);
                if (is_ko(r)) {
                    toast__worker(r.msg);
                    return;
                }
                settext__worker(w.log, s.value);
            }
            break;
            default: {
                super.on_push(target_tid, code, payload);
            }
            break;
        }
    }

    void stop_timer_ui_update() {
        if (timer == null) return;
        timer.cancel();
        timer.purge();
        timer = null;
    }
    
    void start_timer_ui_update() {
        if (timer != null) return;
        timer = new Timer();
        timer.scheduleAtFixedRate(
            new TimerTask() {
                @Override public void run() {
                    update_ui_timer();
                }
        }
        , 100, 1000);
    }

    void update_ui_timer() {
        a.assert_worker_thread(); //--strip
        online_age.add_seconds(1);
        runOnUiThread(new Runnable() {
            @Override public void run() {
                w.online_time.setText("Online time: " + online_age.to_string());
            }
        });

    }

    void settext__worker(us.cash.scr.text_view_t widget, final String txt) {
        runOnUiThread(new Runnable() {
            @Override public void run() {
                String l = '\n' + txt.replace('\r','\n');
                //log("log: " + l); //--strip
                widget.setText(l);
            }
        });
    }

    public static class duration_t {

        public void parse(final String src) {
            String[] n = src.split(":");
            if (n.length != 3) return;
            h = Integer.parseInt(n[0], 10);
            m = Integer.parseInt(n[1], 10);
            s = Integer.parseInt(n[2], 10);
        }

        public String to_string() {
            return String.format("%02d", h) + ":" + String.format("%02d", m) + ":" + String.format("%02d", s);
        }

        public void add_seconds(int secs) {
            ++s;
            if (s >= 60) {
                s = 0;
                ++m;
                if (m >= 60) {
                    m = 0;
                    ++h;
                }
            }
        }

        int h = 0;
        int m = 0;
        int s = 0;

    }

    void refresh_from_data() {
        log("refresh_from_data"); //--strip
        data_t data = tr.get_data();
        if (data == null) return;
        widget_handlers__disabled = true;
        {
            String s = '\n' + data.to_string();
            w.data.setText(s);
        }
        {
            {
                String status = data.find("state");
                boolean cn = false;
                if (status != null) {
                    if (status.equals("online")) {
                        cn = true;
                    }
                }
                if (cn) {
                    w.connect.setChecked(true);
                    w.connect.setText("Connected");
                }
                else {
                    w.connect.setChecked(false);
                    w.connect.setText("Disconnected");
                }
                //w.papyrus.requestLayout();
            }
            {
                String age = data.find("online_age");
                if (age == null) {
                    w.cable.setImageResource(us.cash.app.a.le.resolve_resid(R.raw.disconnected_cable));
                    w.online_time.setText("Online time: --:--:--");
                    stop_timer_ui_update();
                }
                else {
                    w.cable.setImageResource(us.cash.app.a.le.resolve_resid(R.raw.connected_cable));
                    w.online_time.setText("Online time: " + age);
                    online_age.parse(age);
                    start_timer_ui_update();
                }
            }
        }
        widget_handlers__disabled = false;
    }

    public void on_data(final data_t data) {
        refresh_from_data();
    }
    
    us.cash.scr.trader__conf__status__widgets w = null;
    trader__conf tr;
    boolean widget_handlers__disabled = false;
    Timer timer = null;
    duration_t online_age = new duration_t();

}

