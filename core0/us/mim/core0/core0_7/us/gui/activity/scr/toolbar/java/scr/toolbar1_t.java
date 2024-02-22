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
//MIM    mim file: core0/core0_7/us/gui/activity/scr/toolbar/mim.h
//MIM  kickoff code hash: 3gLQqbec4GufQ1kX2EinYqVocZM9 (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
import android.app.Activity;                                                                   // Activity
import java.util.ArrayList;                                                                    // ArrayList
import android.util.AttributeSet;                                                              // AttributeSet
import us.gov.crypto.base58;                                                                   // base58
import android.os.Bundle;                                                                      // Bundle
import android.content.Context;                                                                // Context
import us.gov.socket.datagram;                                                                 // datagram
import us.wallet.trader.data_t;                                                                // data_t
import java.util.HashMap;                                                                      // HashMap
import static us.gov.crypto.ripemd160.hash_t;                                                  // hash_t
import android.widget.ImageView;                                                               // ImageView
import static us.stdint.*;                                                                     // *
import static us.tuple.*;                                                                      // *
import us.ko;                                                                                  // ko
import android.view.LayoutInflater;                                                            // LayoutInflater
import android.widget.LinearLayout;                                                            // LinearLayout
import java.util.Map;                                                                          // Map
import com.google.android.material.button.MaterialButton;                                      // MaterialButton
import com.google.android.material.card.MaterialCardView;                                      // MaterialCardView
import static us.ko.ok;                                                                        // ok
import us.pair;                                                                                // pair
import us.wallet.protocol;                                                                     // protocol
import us.gov.crypto.ripemd160;                                                                // ripemd160
import android.widget.TableLayout;                                                             // TableLayout
import android.widget.TextView;                                                                // TextView
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import androidx.appcompat.widget.Toolbar;
import java.util.Random;                                                                       // Random
import us.ko;                                                                                  // ko
import androidx.annotation.NonNull;                                                            // NonNull
import androidx.annotation.Nullable;                                                           // Nullable

public class toolbar1_t extends toolbar0_t {

    private static void log(final String line) {                              //--strip
        us.cash.CFG.log_scr("toolbar1_t: " + line);          //--strip
    }                                                                         //--strip

    public toolbar1_t(Context ctx, menu_t.listener l) {
        super(ctx, l);
        canvas_t canvas = new canvas_t(ctx, 8, 1); {
            canvas.setPadding(0, 8, 0, 0);
            leds = new leds_t(ctx, false, 1, false);
            canvas.addView(leds); //#4
        }
        canvas_title.addView(canvas, 0);
        assert !leds.get_visibility(); //--strip
        set_leds_visibility(true);
        leds.set_all_off__ui();
        setVisibility(View.VISIBLE);
    }

    public void set_leds_off() {
        a.assert_worker_thread(); //--strip
        log("set_leds off"); //--strip
        if (leds != null) {
            leds.set_all_off();
        }
    }

    public void set_leds_off__ui() {
        a.assert_ui_thread(); //--strip
        log("set_leds off"); //--strip
        if (leds != null) {
            leds.set_all_off__ui();
        }
    }

    public void set_led(final int i, final int color) {
        a.assert_worker_thread(); //--strip
        log("set_walletd_led " + i + " " + leds_t.color_name(color)); //--strip
        if (leds == null) {
            return;
        }
        leds.set_led(i, color);
    }

    int rnd(int min, int max) {
        return new Random().nextInt(1 + min + (max - min));
    }

    public void led_test__worker() {
        a.assert_worker_thread(); //--strip
        log("led_test__worker "); //--strip
        boolean prev = leds.get_visibility();
//        prev = leds == null;
        set_leds_visibility__worker(true);
//        set_led(0, leds_t.led_blue);
//        return;
        try {
            set_led(0, leds_t.led_blue);
            set_led(1, leds_t.led_blue);
            set_led(2, leds_t.led_blue);
            Thread.sleep(rnd(2000, 2500));
            set_led(0, leds_t.led_red);
            set_led(1, leds_t.led_red);
            set_led(2, leds_t.led_red);
            Thread.sleep(2000);
            set_led(0, leds_t.led_amber);
            set_led(1, leds_t.led_amber);
            set_led(2, leds_t.led_amber);
            Thread.sleep(2000);
            set_led(0, leds_t.led_green);
            set_led(1, leds_t.led_green);
            set_led(2, leds_t.led_green);
            Thread.sleep(2000);
            set_led(0, leds_t.led_blue);
            set_led(1, leds_t.led_blue);
            set_led(2, leds_t.led_blue);
            Thread.sleep(2000);
            set_led(2, leds_t.led_red);
            for (int i = 0; i < 5; ++i) {
                int s = leds_t.led_green;
                set_led(0, s);
                set_led(1, s);
                Thread.sleep(200 + 5 * i);
                s = leds_t.led_off;
                set_led(0, s);
                set_led(1, s);
                Thread.sleep(100 - 5 * i);
            }
            set_led(2, leds_t.led_amber);
            for (int i = 5; i < 10; ++i) {
                set_led(0, leds_t.led_green);
                set_led(1, leds_t.led_off);
                Thread.sleep(200 + 5 * i);
                set_led(0, leds_t.led_off);
                set_led(1, leds_t.led_green);
                Thread.sleep(100 - 5 * i);
            }
            set_led(2, leds_t.led_green);
            for (int i = 10; i < 15; ++i) {
                set_led(0, leds_t.led_off);
                set_led(1, leds_t.led_off);
                Thread.sleep(200 + 5 * i);
                set_led(0, leds_t.led_green);
                set_led(1, leds_t.led_green);
                Thread.sleep(100 - 5 * i);
            }
            set_led(0, leds_t.led_off);
            set_led(1, leds_t.led_off);
            set_led(2, leds_t.led_off);
        }
        catch(Exception e) {
        }
        
        set_leds_visibility__worker(prev);
        if (!prev) return;
        if (!a.has_hmi()) return;
        log("report status"); //--strip
        a.hmi().report_status();
        
    }

    public void led_test() {
        a.assert_ui_thread();  //--strip
        Thread thread = new Thread(new Runnable() {
            @Override public void run() {
                led_test__worker();
            }
        });
        thread.start();
    }

    public void set_leds_visibility(final boolean b) {
        a.assert_ui_thread(); //--strip
        boolean i = leds.get_visibility();
        log("set_leds_visibility " + i + "=>" + b); //--strip
        if (i == b) return;
        if (leds.set_visibility(b)) {
            if (b) {
                log("+leds hmi_listener"); //--strip
                a.hmi__add_listener(leds);
            }
            else {
                log("-leds hmi_listener"); //--strip
                a.hmi__remove_listener(leds);
            }
        }
        //leds.setOnClickListener(b ? a.get_click_listener() : null);
    }

    public void set_leds_visibility__worker(final boolean b) {
        a.assert_worker_thread(); //--strip
        a.active_ac.runOnUiThread(new Runnable() {
            @Override public void run() {
                set_leds_visibility(b);
            }
        });
    }

    public leds_t leds;

}

