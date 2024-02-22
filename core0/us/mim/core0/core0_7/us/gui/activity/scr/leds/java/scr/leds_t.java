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
package us.cash.scr;
import android.graphics.drawable.ColorDrawable;                                                // ColorDrawable
import android.graphics.Color;                                                                 // Color
import android.widget.ImageView;                                                               // ImageView
import android.view.View;                                                                      // View
import android.widget.LinearLayout;
import android.content.Context;
import android.util.AttributeSet;                                                              // AttributeSet
import us.ko;                                                                                  // ko
import us.cash.device_endpoint_t;
import android.view.Gravity;

public class leds_t extends LinearLayout implements us.cash.device_endpoints_t.monitor_handler_t {

    static void log(final String line) {          //--strip
        us.cash.CFG.log_scr("leds_t: " + line);       //--strip
    }                                             //--strip

    static int outer_margin = 1;
    //static int space_between_st_and_rxtx = 1;


    static LinearLayout.LayoutParams led_layout = null;

    public leds_t(Context context, boolean vertical, int zoom, boolean key) {
        super(context);
        String sl[] = {"Line Status", "Receive (Rx)", "Transmit (Tx)"};
        int number = sl.length;
        log("Constructor leds"); //--strip
        led = new View[number];
        led0 = new View[number];
        setBackgroundColor(Color.parseColor("#606060"));
        int n = outer_margin * zoom;
        setPadding(n, n, n, n);
        assert vertical == false;
        {
            setLayoutParams(us.cash.app.a.le.layout_params_wrap);
            setOrientation(LinearLayout.HORIZONTAL);
            setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            istatus = 0;
            itx = 2;
            irx = 1;
        }
        /*
        {
            View ledv0;
            canvas_t ledv = new canvas_t(context, 9, 1); {
                {
                    LayoutParams layout = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
                    //layout.setMargins(0, 0, space_between_st_and_rxtx * zoom, 0);
                    layout.setMargins(0, 0, 0, 0);
                    ledv.setLayoutParams(layout);
                    ledv.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                }
                ledv0 = new View(context); {
                    LayoutParams layout = new LinearLayout.LayoutParams(zoom * 30, zoom * 14);
                    ledv0.setLayoutParams(layout);
                }
                ledv.addView(ledv0);
                if (key) {
                    text_view_t skey = new text_view_t(context, 1); {
                        skey.setText(sl[0]);
                        //android:layout_marginTop="3px"
                        skey.setBackgroundColor(Color.parseColor("#606060")); //--Color.WHITE);
                        skey.setTextColor(led_off);
                        skey.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                    }
                    ledv.addView(skey);
                }
            }
            addView(ledv);
            led0[0] = ledv0;
            led[0] = null;
        }
        */
        for (int i = 0; i < number; ++i) {
            {
                View ledv0;
                canvas_t ledv = new canvas_t(context, 9, 1); { //vertical
                    {
                        LayoutParams layout = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
                        layout.setMargins(i == 0 ? 0 : n, 0, 0, 0);
                        ledv.setLayoutParams(layout);
                        ledv.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                    }
                    ledv0 = new View(context); {
                        LayoutParams layout = new LinearLayout.LayoutParams(zoom * 8, zoom * 4);
                        ledv0.setLayoutParams(layout);
                    }
                    ledv.addView(ledv0);
                    if (key) {
                        text_view_t skey = new text_view_t(context, 1); {
                            skey.setText(sl[i]);
//                            skey.setBackgroundColor(Color.WHITE);
                            skey.setTextSize(zoom);
                            skey.setBackgroundColor(Color.parseColor("#606060")); //--Color.WHITE);
                            skey.setTextColor(led_off);
                            skey.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                            //android:layout_marginTop="3px"
                        }
                        ledv.addView(skey);
                    }
                }
                addView(ledv);
                led0[i] = ledv0;
                led[i] = null;
            }
        }
    }

    public static String color_name(final int col) {
        if (col == led_off) return "off";
        if (col == led_red) return "red";
        if (col == led_amber) return "amber";
        if (col == led_green) return "green";
        if (col == led_blue) return "blue";
        return "unk";
    }

    public boolean set_visibility(final boolean b) {
        us.cash.app.a.assert_ui_thread(); //--strip
        setVisibility(b ? View.VISIBLE : View.INVISIBLE);
        if (led[0] == null) { //they are off
            if (b) { //req on
                for (int i = 0; i < led0.length; ++i) {
                    led[i] = led0[i];
                }
                return true; //changed
            }
        }
        else { //they are on
            if (!b) { //req off
                for (int i = 0; i < led0.length; ++i) {
                    led[i] = null;
                }
                return true; //changed
            }
        }
        return false; //no changes
    }

    @Override public void on_hmi__worker(final device_endpoint_t src, final ko status) {
    }

    @Override public void on_status(final device_endpoint_t src, int led_status, final String msg) {
        us.cash.app.a.assert_worker_thread(); //--strip
        set_led(istatus, led_status);
    }

    @Override public void confirmed_subhome(final device_endpoint_t src, final String subhome) {
        us.cash.app.a.assert_worker_thread(); //--strip
    }

    @Override public void on_send(boolean busy) {
        us.cash.app.a.assert_worker_thread(); //--strip
        set_led(itx, busy ? leds_t.led_green : leds_t.led_off);
    }

    @Override public void on_recv(boolean busy) {
        us.cash.app.a.assert_worker_thread(); //--strip
        set_led(irx, busy ? leds_t.led_green : leds_t.led_off);
    }

    boolean is_invisible() {
        return led[0] == null;
    }

    public boolean get_visibility() { //true = visible
        return led[0] != null;
    }

    public void set_all_off__ui() {
        us.cash.app.a.assert_ui_thread(); //--strip
        for (int i = 0; i < led.length; ++i) {
            if (led[i] == null) continue;
            led[i].setBackgroundColor(led_off);
        }
    }

    public void set_all_off() {
        us.cash.app.a.assert_worker_thread(); //--strip
        if (is_invisible()) return;
        post(new Runnable() {
            @Override public void run() {
                set_all_off__ui();
            }
        });
    }

    public void set_led(final int i, final int color) { // -1 off
        us.cash.app.a.assert_worker_thread(); //--strip
        if (led[i] == null) return;
        post(new Runnable() {
            @Override public void run() {
                if (led[i] == null) return;
                log("led[" + i + "].setBackgroundColor"); //--strip
                led[i].setBackgroundColor(color);
            }
        });
    }

    public void set_led__ui(final int i, final int color) { // -1 off
        us.cash.app.a.assert_ui_thread(); //--strip
        if (led[i] == null) return;
        led[i].setBackgroundColor(color);
    }

    public static final int led_off = Color.parseColor("#a0a0a0");
    public static final int led_red = Color.RED;
    public static final int led_amber = Color.parseColor("#FFBF00");
    public static final int led_green = Color.GREEN;
    public static final int led_blue = Color.CYAN;
    View led[];
    View led0[];
    int istatus;
    int itx;
    int irx;
}
