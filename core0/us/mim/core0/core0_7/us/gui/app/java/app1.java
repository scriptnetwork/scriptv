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
//MIM    mim file: core0/core0_7/us/gui/app/mim.h
//MIM  kickoff code hash: 22Xr2ULxfkte2rMvkXQD7nqj8hhq (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.app.Application;                                                                // Application
import java.util.ArrayList;                                                                    // ArrayList
import java.util.Arrays;                                                                       // Arrays
import android.media.AudioManager;                                                             // AudioManager
import java.net.Authenticator;                                                                 // Authenticator
import us.gov.io.blob_reader_t;                                                                // blob_reader_t
import static us.gov.io.types.blob_t;                                                          // blob_t
import java.io.BufferedInputStream;                                                            // BufferedInputStream
import java.io.BufferedReader;                                                                 // BufferedReader
import us.gov.socket.busyled_t;                                                                // busyled_t
import java.io.ByteArrayOutputStream;                                                          // ByteArrayOutputStream
import java.nio.charset.Charset;                                                               // Charset
import java.util.Collections;                                                                  // Collections
import java.util.Comparator;                                                                   // Comparator
import java.util.concurrent.locks.Condition;                                                   // Condition
import android.content.res.Configuration;                                                      // Configuration
import android.net.ConnectivityManager;                                                        // ConnectivityManager
import android.content.Context;                                                                // Context
import us.gov.socket.datagram;                                                                 // datagram
import java.util.Date;                                                                         // Date
import android.app.DialogFragment;                                                             // DialogFragment
import android.util.DisplayMetrics;                                                            // DisplayMetrics
import android.os.Environment;                                                                 // Environment
import java.io.File;                                                                           // File
import java.io.FileNotFoundException;                                                          // FileNotFoundException
import java.io.FileOutputStream;                                                               // FileOutputStream
import androidx.core.content.FileProvider;                                                     // FileProvider
import java.security.GeneralSecurityException;                                                 // GeneralSecurityException
import static us.gov.crypto.ripemd160.hash_t;                                                  // hash_t
import java.net.HttpURLConnection;                                                             // HttpURLConnection
import static us.gov.id.types.*;                                                               // *
import static us.gov.socket.types.*;                                                           // *
import static us.stdint.*;                                                                     // *
import static us.tuple.*;                                                                      // *
import java.io.InputStream;                                                                    // InputStream
import java.io.InputStreamReader;                                                              // InputStreamReader
import android.content.Intent;                                                                 // Intent
import java.io.IOException;                                                                    // IOException
import static us.ko.is_ko;                                                                     // is_ko
import org.json.JSONArray;                                                                     // JSONArray
import org.json.JSONException;                                                                 // JSONException
import org.json.JSONObject;                                                                    // JSONObject
import java.security.KeyPair;                                                                  // KeyPair
import us.ko;                                                                                  // ko
import java.util.Locale;                                                                       // Locale
import java.util.concurrent.locks.Lock;                                                        // Lock
import android.util.Log;                                                                       // Log
import android.os.Looper;                                                                      // Looper
import android.view.Menu;                                                                      // Menu
import android.view.MenuItem;                                                                  // MenuItem
import android.net.NetworkInfo;                                                                // NetworkInfo
import static us.ko.ok;                                                                        // ok
import java.io.OutputStream;                                                                   // OutputStream
import java.io.OutputStreamWriter;                                                             // OutputStreamWriter
import us.pair;                                                                                // pair
import java.net.PasswordAuthentication;                                                        // PasswordAuthentication
import android.preference.PreferenceManager;                                                   // PreferenceManager
import java.io.PrintStream;                                                                    // PrintStream
import us.wallet.protocol;                                                                     // protocol
import us.wallet.trader.qr_t;                                                                  // qr_t
import java.util.concurrent.locks.ReentrantLock;                                               // ReentrantLock
import android.content.res.Resources;                                                          // Resources
import android.content.SharedPreferences;                                                      // SharedPreferences
import android.os.StrictMode;                                                                  // StrictMode
import us.string;                                                                              // string
import android.net.wifi.SupplicantState;                                                       // SupplicantState
import android.telephony.TelephonyManager;                                                     // TelephonyManager
import java.util.Timer;                                                                        // Timer
import java.util.TimerTask;                                                                    // TimerTask
import android.widget.Toast;                                                                   // Toast
import android.media.ToneGenerator;                                                            // ToneGenerator
import java.util.TreeMap;                                                                      // TreeMap
import java.io.UnsupportedEncodingException;                                                   // UnsupportedEncodingException
import java.net.URL;                                                                           // URL
import android.net.wifi.WifiInfo;                                                              // WifiInfo
import android.net.wifi.WifiManager;                                                           // WifiManager
import android.view.View;                                                                      // View
import android.widget.TextView;                                                                // TextView
import androidx.appcompat.app.AlertDialog;                                                     // AlertDialog
import androidx.appcompat.view.ContextThemeWrapper;                                            // ContextThemeWrapper
import android.content.DialogInterface;                                                        // DialogInterface
import android.widget.LinearLayout;                                                            // LinearLayout
import android.view.ViewGroup;
import java.text.NumberFormat;                                                                 // NumberFormat
import android.media.projection.MediaProjection;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Handler;

public class app1 extends app0 implements datagram_dispatcher_t.handler_t {

    private static void log(final String line) {                              //--strip
        CFG.log_android("app1: " + line);          //--strip
    }                                                                         //--strip

    public app1() {
        super();
    }

    @Override protected void init_static_refs(app a_) {
        super.init_static_refs(a_);
        sw_updates_t.a = a;
    }

    @Override public void onCreate() {
        super.onCreate();
        log("onCreate"); //--strip

        log("Init crypto"); //--strip
        us.gov.crypto.ec.create_instance();

        log("boot test"); //--strip
        tests(); //--strip

        ko r = create_device_endpoints();
        if (is_ko(r)) {
            log("device_endpoints failure"); //--strip
            abort("device_endpoints failure " + r.msg);
            return;
        }
        assert device_endpoints != null;
        assert !device_endpoints.isEmpty();
        //assert device_endpoints.cur != null;

        device_endpoints.datagram_dispatcher.connect_sink(this);
        tone = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
        log("end onCreate"); //--strip
    }

    void test_hmi() {
        if (!hmi_t.test()) {
            abort("failed hmi tests");
            return;
        }
        log("hmi test succeed"); //--strip
    }

    void tests() {                                  //--strip
        test_hmi();                                 //--strip
        log("boot test succeed");                   //--strip
    }                                               //--strip

    public ko create_device_endpoints() { // returns the index to poweron, -1 for none
        log("create_device_endpoints"); //--strip
        assert device_endpoints == null; //--strip
        try {
            device_endpoints = new device_endpoints_t(a);
            return device_endpoints.init();
        }
        catch (Exception e) {
            log("could not create device_endpoints." + e.getMessage()); //--strip
            device_endpoints = null;
        }
        ko r = new ko("KO 43929 failed to setup device endpoints.");
        log(r.msg); // --strip
        return r;
    }

    public void set_datagram_listener(datagram_dispatcher_t.handler_t sink, boolean up) {
        log("set_datagram_listener " + up); //--strip
        assert_ui_thread(); //--strip
        if (up) {
            device_endpoints.datagram_dispatcher.connect_sink(sink);
        }
        else {
            device_endpoints.datagram_dispatcher.disconnect_sink(sink);
        }
    }
 
    @Override public void activity_on_create(activity0 ac) {
        super.activity_on_create(ac);
        log("set_datagram_listener " + this); //--strip
        set_datagram_listener((activity)ac, true);
    }

    @Override public void activity_on_destroy(activity0 ac) {
        log("set_datagram_listener " + this); //--strip
        set_datagram_listener((activity) ac, false);
        super.activity_on_destroy(ac);
    }

    @Override public boolean on_main_resume() { //true if it launches an activity
        assert_ui_thread(); //--strip
        if (!poweron_boot) return false;
        poweron_boot = false;
        int reqpwr = device_endpoints.req_pwr();
        if (reqpwr == -1) {
            launch_device_endpoints__conf();
            return true;
        }
        log("powering ON pos " + reqpwr); //--strip
        log(" endpoint: " + device_endpoints.get(reqpwr).ip4_endpoint.to_string()); //--strip
        HMI_power_on(reqpwr, new pin_t(0));
        return false;
    }

    public void on_hmi(final hmi_t src, final ko status) {
        assert_ui_thread();  //--strip
        if (!is_ko(status)) return;
        if (src.manual_mode) return;
        if (active_ac != null) {
            if (active_ac instanceof device_endpoints__conf) return;
            if (active_ac instanceof device_endpoint__conf) return;
            close_drawer();
        }
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                launch_device_endpoints__conf();
            }
        }, 1500);
    }

    public void on_hmi__worker(final hmi_t src, final ko status) {
        assert_worker_thread();  //--strip
        runOnUiThread(new Runnable() {
            @Override public void run() {
                on_hmi(src, status);
            }
        });
    }

    public void on_hmi_status__worker() {
        assert_worker_thread();  //--strip
        configure_screen__worker();
    }

    @Override public void on_push(hash_t target_tid, uint16_t code, byte[] payload) {
        log("on_push " + target_tid.encode() + " code " + code.value + " payload BIN sz: " + payload.length + " bytes"); //--strip
        switch(code.value) {
            case us.gov.relay.pushman.push_ko: {
                string s = new string();
                blob_reader_t rder = new blob_reader_t(new blob_t(payload));
                ko r = rder.read(s);
                if (is_ko(r)) {
                    toast__worker("KO decode payload trade " + target_tid.encode() + ": " + r.msg);
                    return;
                }
                toast__worker("KO trade " + target_tid.encode() + ": " + s.value);
                return;
            }
            case us.gov.relay.pushman.push_ok: {
                string s = new string();
                blob_reader_t rder = new blob_reader_t(new blob_t(payload));
                ko r = rder.read(s);
                if (is_ko(r)) {
                    toast__worker("OK. later got " + r.msg);
                    return;
                }
                return;
            }
        }
    }

    //===============================================Power OFF

    public void HMI_power_off__worker(boolean save_) {
        HMI_power_off__worker(device_endpoints.cur_index.value, save_);
    }

    public void HMI_power_off__worker(final int pos, boolean save_) {
        assert_worker_thread();  //--strip
        log("HMI_power_off__worker"); //--strip
        ko r = device_endpoints.poweroff(pos, save_);
        if (is_ko(r)) {
            toast__worker(r.msg);
            return;
        }
        log("set leds off"); //--strip
        a.le.screen.toolbar.set_leds_off();
    }

    public void HMI_power_off__worker(device_endpoint_t o, boolean save_) {
        assert_worker_thread();  //--strip
        log("HMI_power_off__worker"); //--strip
        ko r = device_endpoints.poweroff(o, save_);
        if (is_ko(r)) {
            toast__worker(r.msg);
            return;
        }
        log("set leds off"); //--strip
        if (device_endpoints.is_cur(o)) {
            a.le.screen.toolbar.set_leds_off();
        }
    }

    public void HMI_power_off(final int pos, final boolean save_) {
        a.assert_ui_thread(); //--strip
        Thread thread = new Thread(new Runnable() {
            @Override public void run() {
                HMI_power_off__worker(pos, save_);
            }
        });
        thread.start();
    }

    public void HMI_power_off(device_endpoint_t o, final boolean save_) {
        a.assert_ui_thread(); //--strip
        Thread thread = new Thread(new Runnable() {
            @Override public void run() {
                HMI_power_off__worker(o, save_);
            }
        });
        thread.start();
    }

    public void HMI_power_off(final boolean save_) {
        a.assert_ui_thread(); //--strip
        Thread thread = new Thread(new Runnable() {
            @Override public void run() {
                HMI_power_off__worker(save_);
            }
        });
        thread.start();
    }

    //===============================================Power ON

    public void HMI_power_on__worker(pin_t pin) {
        HMI_power_on__worker(device_endpoints.cur_index.value, pin);
    }

    public void HMI_power_on__worker(int pos, final pin_t pin) {
        assert_worker_thread();  //--strip
        if (has_hmi()) {
            return;
        }
        ko r = device_endpoints.poweron(pos, pin);
        if (is_ko(r)) {
            toast__worker(r.msg);
        }
    }

    public void HMI_power_on__worker(device_endpoint_t o, final pin_t pin) {
        assert_worker_thread();  //--strip
        if (has_hmi()) {
            return;
        }
        ko r = device_endpoints.poweron(o, pin);
        if (is_ko(r)) {
            toast__worker(r.msg);
        }
    }

    public void HMI_power_on(final int pos, final pin_t pin) {
        assert_ui_thread();  //--strip
        Thread thread = new Thread(new Runnable() {
            @Override public void run() {
                HMI_power_on__worker(pos, pin);
            }
        });
        thread.start();
    }

    public void HMI_power_on(device_endpoint_t o, final pin_t pin) {
        assert_ui_thread();  //--strip
        Thread thread = new Thread(new Runnable() {
            @Override public void run() {
                HMI_power_on__worker(o, pin);
            }
        });
        thread.start();
    }

    public void HMI_power_on(final pin_t pin) {
        assert_ui_thread();  //--strip
        Thread thread = new Thread(new Runnable() {
            @Override public void run() {
                HMI_power_on__worker(pin);
            }
        });
        thread.start();
    }

    public void toggle_poweron() {
        assert_ui_thread(); //--strip
        if (!has_hmi()) {
            HMI_power_on(new pin_t(0));
            return;
        }
        HMI_power_off(true);
    }

    //=============================================== / Power

    public boolean is_HMI_poweron() {
        if (!has_hmi()) return false;
        return true;
    }

    public void hmi__add_listener(device_endpoints_t.monitor_handler_t listener) {
        device_endpoints.hmi__add_listener(listener);
    }

    public void hmi__remove_listener(device_endpoints_t.monitor_handler_t listener) {
        device_endpoints.hmi__remove_listener(listener);
    }

    public hmi_t hmi() {
        log("get hmi " + device_endpoints.cur); //--strip
        assert device_endpoints.cur != null; //--strip
        log("get hmi " + device_endpoints.cur.hmi); //--strip
        assert device_endpoints.cur.hmi != null;  //--strip
        return device_endpoints.cur.hmi;
    }

    public boolean has_hmi() {
        if (device_endpoints.cur == null) return false; //--strip
        if (device_endpoints.cur.hmi == null) return false;
        if (device_endpoints.cur.hmi.rpc_peer == null) return false;
        if (!device_endpoints.cur.hmi.is_online) return false;
        return true;
    }

    public String net_identifier() {
        ConnectivityManager cm;
        NetworkInfo info = null;
        try {
            cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            info = cm.getActiveNetworkInfo();
        }
        catch (Exception e) {
            return ""; //e.printStackTrace();
        }
        if (info == null) {
            return "";
        }
        if (!info.isConnected()) {
            return "";
        }
        if (info.getType() == ConnectivityManager.TYPE_WIFI) {
            WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo;
            wifiInfo = wifiManager.getConnectionInfo();
            if (wifiInfo.getSupplicantState() == SupplicantState.COMPLETED) {
                String ssid = wifiInfo.getSSID();
                ssid = ssid.replaceAll("\"", "");
                if (!ssid.equals("<unknown ssid>")) {
                    return ssid;
                }
            }
            return "wifi";
        }
        if (info.getType() == ConnectivityManager.TYPE_MOBILE) { 
            switch (info.getSubtype()) {
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN:
                case TelephonyManager.NETWORK_TYPE_GSM:
                     return "2G";
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                case TelephonyManager.NETWORK_TYPE_TD_SCDMA:
                    return "3G";
                case TelephonyManager.NETWORK_TYPE_LTE:
                case TelephonyManager.NETWORK_TYPE_IWLAN:
                case 19:
                    return "4G";
                case TelephonyManager.NETWORK_TYPE_NR:
                    return "5G";
                default:
                    return "mobile";
            }
        }
        return "unknown";
    }

    @Override public void run_test_seqs() {  //--strip
        super.run_test_seqs();              //--strip
    }                                       //--strip

    public void launch_device_endpoints__conf() {
        assert_ui_thread(); //--strip
        if (active_ac == null) {
            log("KO 22019"); //--strip
            return;
        }
        assert_ui_thread(); //--strip
        log("launching device_endpoints__conf..."); //--strip
        Intent intent = new Intent(getApplicationContext(), device_endpoints__conf.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); //single instance
        main.startActivity(intent);
    }

    public void launch_connection_settings() {
        assert_ui_thread(); //--strip
        go_conf(device_endpoints.cur);
    }

/*
    public View.OnClickListener get_click_listener() {
        return new View.OnClickListener() {
            @Override public void onClick(View v) {
                String[] options = {"Go to connection settings...", "Go to connections...", "Test leds.", (a.is_HMI_poweron() ? "Stop HMI" : "Start HMI"), a.getResources().getString(R.string.cancel)};
                new_dlg_builder().setTitle("Quick access to...").setItems(options, new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        switch(which) {
                            case 0:
                                a.launch_connection_settings();
                                break;
                            case 1:
                                a.launch_device_endpoints__conf();
                                break;
                            case 2:
                                a.le.screen.toolbar.led_test();
                                break;
                            case 3:
                                a.toggle_poweron();
                                break;
                        }
                    }
                }).setIcon(R.drawable.ic_world).show();
            }
        };
    }
*/

    public device_endpoint_t mem_get_device_endpoint_t(Integer id) {
        if (id == null) return null;
        if (!a.device_endpoints.is_valid_index(id)) {
            return null;
        }
        return device_endpoints.get(id);
    }

    public void go_conf(final device_endpoint_t o) {
        assert_ui_thread(); //--strip
        log("launching connection settings..."); //--strip
        Intent intent = new Intent(getApplicationContext(), device_endpoint__conf.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); //single instance
        intent.putExtra("object_id", mem_set_object(o));
        main.startActivity(intent);
    }

/*            
    public void go_conf(final int index) {
        assert_ui_thread(); //--strip
        log("launching connection settings..."); //--strip
        Intent intent = new Intent(getApplicationContext(), device_endpoint__conf.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); //single instance
        Integer i = index;
        intent.putExtra("object_id", i);
        main.startActivity(intent);
    }
*/

    protected us.cash.scr.app__menu___online_t new_online_menu() {
        return us.cash.scr.app__menu___online_t.get_instance(getApplicationContext());
    }

    protected us.cash.scr.app__menu___main_t new_offline_menu() {
        return us.cash.scr.app__menu___main_t.get_instance(getApplicationContext());
    }

    @Override public us.cash.scr.menu_t get_menu() {
        log("get_menu"); //--strip
        if (has_hmi()) {
            if (hmi().is_online) {
                log("menuid menu_hmi_online"); //--strip
                return new_online_menu(); //us.cash.scr.app_singleton__menu___app0_online_t.get_instance(getApplicationContext());
            }
        }
        return new_offline_menu();
//       log("menuid menu_nohmi"); //--strip
//        return us.cash.scr.menu__nohmi_t.get_instance(getApplicationContext());
    }

    @Override public boolean on_menu(final int id) {
        log("on_menu"); //--strip
        if (id == R.raw.connection) {
            new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        launch_device_endpoints__conf();
                    }
                }, 100);
        }
        else if (id ==  R.raw.pill) {
            assert device_endpoints.cur != null;
            new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        device_endpoints.cur.sw_updates_ui(active_ac);
                    }
                }, 100);
        }
        else if (id ==  R.raw.amberled) {            //--strip   DEBUG feature
            a.le.screen.toolbar.led_test();     //--strip   DEBUG feature
        }                                       //--strip   DEBUG feature
        else {
            return super.on_menu(id);
        }
        return true;
    }

    @Override public ko backend_ready() {
        if (!a.has_hmi()) {
            ko r = new ko("KO 79125 HMI is not available");
            log(r.msg); //--strip
            return r;
        }
        return ok;
    }

    public static String KO_68874 = "KO 68874 Invalid endpoint.";
    public static String KO_89700 = "KO 89700 HMI is off.";

    device_endpoints_t device_endpoints = null;
    boolean poweron_boot = true;
    protected ToneGenerator tone;


}

