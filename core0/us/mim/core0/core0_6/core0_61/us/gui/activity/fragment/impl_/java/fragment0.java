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
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/fragment/impl_/mim.h
//MIM  kickoff code hash: 2RJCimNEYx2nFxeMVcbyncmNdic1 (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import us.gov.io.blob_reader_t;                                                                // blob_reader_t
import us.wallet.trader.bookmarks_t;                                                           // bookmarks_t
import android.os.Bundle;                                                                      // Bundle
import us.gov.socket.datagram;                                                                 // datagram
import us.wallet.trader.data_t;                                                                // data_t
import androidx.fragment.app.Fragment;                                                         // Fragment
import static us.gov.crypto.ripemd160.hash_t;                                                  // hash_t
import static us.gov.io.types.*;                                                               // *
import static us.stdint.*;                                                                     // *
import static us.tuple.*;                                                                      // *
import android.content.Intent;                                                                 // Intent
import static us.ko.is_ko;                                                                     // is_ko
import us.ko;                                                                                  // ko
import static us.ko.*;                                                                                  // ko
import android.view.LayoutInflater;                                                            // LayoutInflater
import us.pair;                                                                                // pair
import us.string;                                                                              // string
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import android.content.Context;                                                                // Context
import us.cash.scr.*;                                                                // Context

public abstract class fragment0 extends Fragment implements menu_t.listener {

    private static void log(final String line) {                              //--strip
        CFG.log_android("fragment0: " + line);          //--strip
    }                                                                         //--strip

    public fragment0() {
        super();
    }

    //--------------lifecycle-------------------------------------------------------
    protected void controller__on_create(Bundle saved_state) {
        log("controller__on_create"); //--strip
        assert w == null; //--strip
        log("creating widgets"); //--strip
        w = create_widgets();
        assert w != null; //--strip
        assert a != null;
        v = w.get_tree(ac);
        register_actions(v);
    }

    protected void controller__on_pause() {
        log("controller__on_pause"); //--strip
    }

    protected void controller__on_resume() {
        log("controller__on_resume"); //--strip
    }

    protected void controller__on_destroy() {
        log("controller__on_destroy"); //--strip
        a.actions.unregister_actions(v);
        w = null;
    }
    //-/------------lifecycle-------------------------------------------------------

    public abstract us.cash.scr.view__widgets create_widgets();
    public abstract String get_title();

    public void register_actions(View v) {
    }

    protected menu_t get_menu() {
        assert(get_menu_depth() == 0); //--strip
        return a.get_menu();
    }

    @Override public boolean on_menu(int resid) { //return true if captured, false if ignored
        log("on_menu " + resid); //--strip
        return false;
    }

    public void refresh() {
        log("refresh"); //--strip
        a.assert_ui_thread(); //--strip
    }

    public void refresh__worker() {
        log("refresh"); //--strip
        a.assert_worker_thread(); //--strip
        runOnUiThread(new Runnable() {
            @Override public void run() {
                refresh();
            }
        });
    }

    public static void toast(final String s) {
        log("toast. msg " + s); //--strip
        a.toast(s); //--strip
    }

    void toast__worker(final String msg) {
        log("toast__worker. msg " + msg); //--strip
        a.toast__worker(msg); //--strip
    }


    public void report_ko(final String msg) {
        log("report_ko " + msg); //--strip
        app.assert_ui_thread(); //--strip
        toast(msg);
    }

    public void report_ko__worker(final String msg) {
        log("report_ko__worker. " + msg); //--strip
        app.assert_worker_thread(); //--strip
        runOnUiThread(new Runnable() {
            @Override public void run() {
                report_ko(msg);
            }
        });
    }

    public void report_ko(final ko r) {
        log("report_ko " + r.msg); //--strip
        app.assert_ui_thread(); //--strip
        report_ko(r.msg);
    }

    public void report_ko__worker(final ko r) {
        log("report_ko__worker " + r.msg); //--strip
        app.assert_worker_thread(); //--strip
        report_ko__worker(r.msg);
    }


    public void set_busy__worker(boolean b) {
        log("set_busy__worker"); //--strip
        a.assert_worker_thread(); //--strip
        runOnUiThread(new Runnable() {
            @Override public void run() {
                set_busy(b);
            }
        });
    }

    public void on_busy() {
        log("on_busy"); //--strip
        a.assert_ui_thread(); //--strip
    }

    public void on_ready(final ko load_result) {
        log("on_ready"); //--strip
        a.assert_ui_thread(); //--strip
    }

    public void on_ready__worker(final ko load_result) {
        log("on_ready__worker"); //--strip
        a.assert_worker_thread(); //--strip
        runOnUiThread(new Runnable() {
            @Override public void run() {
                on_ready(load_result);
            }
        });
    }

    public void set_busy(boolean b) {
        log("set_busy"); //--strip
        a.assert_ui_thread(); //--strip
        if (b) {
            ++_busy;
            if (_busy == 1) {
                log("busy=1"); //--strip
                busy_result = ok;
                on_busy();
            }
            return;
        }
        --_busy;
        if (_busy == 0) {
            log("busy=0"); //--strip
            if (w != null) on_ready(busy_result); //if not destroyed
        }
    }

    public ko load__worker() {
        a.assert_worker_thread(); //--strip
        log("load"); //--strip
        ko r = new ko("KO 79688 Not implemented.");
        log(r.msg); //--strip
        return r;
    }

    public void load() {
        log("load"); //--strip
        a.assert_ui_thread(); //--strip
        set_busy(true);
        Thread t = new Thread(new Runnable() {
            @Override public void run() {
                ko r = load__worker();
                if (is_ko(r)) {
                    report_ko__worker(r);
                }
                if (is_ok(busy_result)) {
                    busy_result = r;
                }
                set_busy__worker(false);
            }
        });
        t.start();
    }

    void on_close() {
        ac.on_close();
    }

    public Context get_context() {
        return ac;
    }

    public void set_menu_max_depth(int max_depth) {
        ac.set_menu_max_depth(max_depth);
    }

    public int get_menu_depth() {
        return ac.get_menu_depth();
    }

    @Override final public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saved_state) {
        log("onCreateView"); //--strip
        ac = (activity) getActivity();
        assert ac != null;
        set_menu_max_depth(0);
        controller__on_create(saved_state);
        assert w != null; //--strip
        return v;
    }

    @Override final public void onPause() {
        log("onPause"); //--strip
        controller__on_pause();
        super.onPause();
    }

    @Override final public void onResume() {
        log("onResume"); //--strip
        super.onResume();
        controller__on_resume();
    }

    @Override final public void onDestroyView() {
        log("onDestroyView"); //--strip
        controller__on_destroy();
        super.onDestroyView();
    }

    void runOnUiThread(Runnable r) {
        ac.runOnUiThread(r);
    }

    public us.cash.scr.view__widgets w = null;

    int _busy = 0;
    ko busy_result = ok;
    View v;
    public activity ac;
    public static app a;

}
