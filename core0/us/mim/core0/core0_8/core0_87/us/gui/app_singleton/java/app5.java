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
import us.cash.scr.*;
import android.media.projection.MediaProjection;
import android.graphics.Bitmap;
import android.media.Image;

public class app5 extends app4 {

    private static void log(final String line) {           //--strip
        CFG.log_android("app5: " + line);                  //--strip
    }                                                      //--strip

    public app5() {
        super();
    }

    static interface i18n_t {
        enum sid {
            unknown,
            app, pres, ehr, aireq, aires, send, new_, cat, inv, pay, rcpt
        }
        String resolve(sid s);
    }

    static class i18n_en implements i18n_t {
        @Override
        public String resolve(sid s) {
            switch (s) {
                case app: return "Appointment";
                case pres: return "Prescription";
                case ehr: return "Electronic Health Record (EHR)";
                case aireq: return "A.I. Service referral";
                case aires: return "A.I. Service results";
                case send: return "Send";
                case new_: return "New";
                case cat: return "Catalogue";
                case inv: return "Invoice";
                case pay: return "Payment";
                case rcpt: return "Receipt";
            }
            return "unknown";
        }
    };

    static class i18n_es implements i18n_t {
        @Override public String resolve(sid s) {
            switch(s) {
                case app: return "Cita";
                case pres: return "Receta";
                case ehr: return "Historia medica (EHR)";
                case aireq: return "Volante servicio I.A";
                case aires: return "Resultados servicio I.A";
                case send: return "Enviar";
                case new_: return "Nuevo";
                case cat: return "Catalogo";
                case inv: return "Factura";
                case pay: return "Pago";
                case rcpt: return "Recibo";
            }
            return "desconocido";
        }
    };

    static i18n_t.sid i18n_sid(String s) {
        if (s.equals("app")) return i18n_t.sid.app;
        if (s.equals("pres")) return i18n_t.sid.pres;
        if (s.equals("ehr")) return i18n_t.sid.ehr;
        if (s.equals("aireq")) return i18n_t.sid.aireq;
        if (s.equals("aires")) return i18n_t.sid.aires;
        if (s.equals("send")) return i18n_t.sid.send;
        if (s.equals("new")) return i18n_t.sid.new_;
        if (s.equals("cat")) return i18n_t.sid.cat;
        if (s.equals("inv")) return i18n_t.sid.inv;
        if (s.equals("pay")) return i18n_t.sid.pay;
        if (s.equals("rcpt")) return i18n_t.sid.rcpt;
        return i18n_t.sid.unknown;
    }

    @Override public void onCreate() {
        super.onCreate();
        log("onCreate"); //--strip
        String lang = locale.get_lang(this);
        if (lang != null && lang.equals("es")) {
            i18n = new i18n_es();
        }
        else {
            i18n = new i18n_en();
        }
    }

    @Override protected us.cash.scr.app_singleton__menu___app1_online_t new_online_menu() {
        return us.cash.scr.app_singleton__menu___app5_online_t.get_instance(getApplicationContext());
    }

    public void beep_chat() {
        log("beep chat"); //--strip
        tone.startTone(ToneGenerator.TONE_CDMA_NETWORK_CALLWAITING, 150);
    }

    public void use_personality(final hash_t tid, final String personality) {
        assert_ui_thread();  //--strip
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                hmi().command_trade(tid, "use_personality " + personality);
            }
        });
        thread.start();
    }

    public void start_protocol(final hash_t tid, final String procol) {
        log("start_protocol " + tid.encode() + " " + procol); //--strip
        Thread thread = new Thread(new Runnable() {
            @Override public void run() {
                log("starting protocol " + procol); //--strip
                hmi().command_trade(tid, "start " + procol);
            }
        });
        thread.start();
    }

    public pair<ko, hash_t> new_trade(final hash_t parent_trade, final String datasubdir, final qr_t qr) {
        assert_ui_thread();  //--strip
        log("new_trade " + qr.to_string()); //--strip
        pair<ko, hash_t> r = hmi().new_trade(parent_trade, datasubdir, qr);
        log("invoked API new_trade"); //--strip
        if (is_ko(r.first)) {
            toast(hmi().rewrite(r.first));
        }
        else {
            toast(r_(R.string.newtrade) + "\n" + qr.to_string());
        }
        return r;
    }

    public void go_trade__worker(final hash_t tid) {
        assert_worker_thread();  //--strip
        log("go_trade__worker " + tid.encode()); //--strip
        runOnUiThread(new Runnable() {
            @Override public void run() {
                a.go_trade(tid);
            }
        });
    }

    public void go_trade(final hash_t tid) {
        assert_ui_thread();  //--strip
        log("go_trade " + tid.encode());  //--strip
        if (main == null) {
            log("KO 77968 main is null"); //--strip
            return;
        }
        Intent intent = new Intent(active_ac, trader.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("tid", tid.value);
        main.startActivity(intent);
    }

    @Override public void on_push(hash_t target_tid, uint16_t code, byte[] payload) {
        log("on_push " + target_tid.encode() + " code " + code.value + " payload BIN sz: " + payload.length + " bytes"); //--strip
        switch(code.value) {
            case us.wallet.trader.trader_t.push_trade: {
                log("a new trade for me"); //--strip
                go_trade__worker(target_tid);
                return;
            }
            case us.wallet.trader.trader_t.push_chat: {
                runOnUiThread(new Runnable() {
                    @Override public void run() {
                        call_human(1, target_tid.encode());
                    }
                });
                break;
            }
            default: {
                super.on_push(target_tid, code, payload);
            }
        }
    }

    public void world() {
        log("menu world"); //--strip
        Intent intent = new Intent(active_ac, nodes.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    void doscan(boolean frommainmenu) {
        Intent intent = new Intent(this, scan.class);
        intent.putExtra("what", 0);
//        if (frommainmenu) {
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
//        }
//        startActivityForResult(intent, SCAN_REQUESTCODE);
        startActivity(intent);
    }
//    static final int SCAN_REQUESTCODE = 3720;

    void go_qr() {
        Intent intent = new Intent(active_ac, qr_activity.class);
        startActivity(intent);
    }

    public void go_trades() {
        log("go_trades"); //--strip
        Intent intent = new Intent(active_ac, trades.class);
        startActivity(intent);
    }

    @Override public boolean on_menu(final int id) {
        log("on_menu"); //--strip
        if (id == R.raw.find) {
            new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        world();
                    }
                }, 100);
        }
        else if (id == R.raw.active) {
            new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        go_trades();
                    }
                }, 100);
        }
        else if (id == R.raw.qrico) {
            new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        go_qr();
                    }
                }, 100);
        }
        else {
            return super.on_menu(id);
        }
        active_ac.close_drawer();
        return true;
    }

    public i18n_t i18n;

}

