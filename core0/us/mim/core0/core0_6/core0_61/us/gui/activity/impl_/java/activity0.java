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
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/impl_/mim.h
//MIM  kickoff code hash: 43JX6ACJxNFLyg1weEVUGwio6V1q (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import androidx.appcompat.app.AlertDialog;                                                     // AlertDialog
import androidx.appcompat.app.AppCompatActivity;                                               // AppCompatActivity
import android.os.Bundle;                                                                      // Bundle
import java.util.concurrent.locks.Condition;                                                   // Condition
import android.content.Context;                                                                // Context
import android.content.DialogInterface;                                                        // DialogInterface
import androidx.drawerlayout.widget.DrawerLayout;                                              // DrawerLayout
import androidx.core.view.GravityCompat;                                                       // GravityCompat
import us.gov.crypto.ripemd160.hash_t;                                                         // hash_t
import static us.gov.id.types.*;                                                               // *
import static us.gov.socket.types.*;                                                           // *
import android.content.Intent;                                                                 // Intent
import static us.ko.is_ko;                                                                     // is_ko
import us.ko;                                                                                  // ko
import static us.ko.*;                                                                                  // ko
import android.view.LayoutInflater;                                                            // LayoutInflater
import java.util.Locale;                                                                       // Locale
import java.util.concurrent.locks.Lock;                                                        // Lock
import androidx.annotation.NonNull;                                                            // NonNull
import androidx.annotation.Nullable;                                                           // Nullable
import android.content.pm.PackageManager;                                                      // PackageManager
import us.wallet.trader.qr_t;                                                                  // qr_t
import java.util.concurrent.locks.ReentrantLock;                                               // ReentrantLock
import android.widget.RelativeLayout;                                                          // RelativeLayout
import android.provider.Settings;                                                              // Settings
import java.util.Timer;                                                                        // Timer
import java.util.TimerTask;                                                                    // TimerTask
import android.net.Uri;                                                                        // Uri
import android.view.View;                                                                      // View
import android.view.Window;                                                                    // Window
import android.graphics.Bitmap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import android.os.Environment;
import androidx.appcompat.app.ActionBarDrawerToggle;                                           // ActionBarDrawerToggle
import android.view.ViewGroup;
import static us.stdint.*;
import com.google.android.material.navigation.NavigationView;                                  // NavigationView
import android.view.Menu;                                                                      // Menu
import android.view.MenuItem;                                                                  // MenuItem
import us.cash.scr.*;                                                                  // MenuItem
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.content.res.Configuration;

public abstract class activity0 extends AppCompatActivity implements menu_t.listener {

    private static void log(final String line) {                              //--strip
        CFG.log_android("activity0: " + line);          //--strip
    }                                                                         //--strip

    public activity0() {
        super();
        locale.update(this);
    }

    //--------------lifecycle-------------------------------------------------------
    protected void controller__on_create(Bundle saved_state) {
        log("controller__on_create"); //--strip
        assert w == null; //--strip
        log("creating widgets"); //--strip
        w = create_widgets();
        assert w != null; //--strip
        assert a != null;
        w.get_tree(this);
    }

    protected void controller__on_pause() {
        log("controller__on_pause"); //--strip
    }

    protected void controller__on_resume() {
        log("controller__on_resume"); //--strip
    }

    protected void controller__on_destroy() {
        log("controller__on_destroy"); //--strip
        w = null;
    }
    //-/------------lifecycle-------------------------------------------------------

    public abstract us.cash.scr.view__widgets create_widgets();
    public abstract String get_title();

    protected menu_t get_menu() {
        assert(get_menu_depth() == 0); //--strip
        return a.get_menu();
    }

    @Override public boolean on_menu(int resid) { //return true if captured, false if ignored
        log("on_menu " + resid); //--strip
        return a.on_menu(resid);
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

    public abstract ko load__worker();

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
        finish();
    }

    public Context get_context() {
        return this;
    }

    public void set_menu_max_depth(int max_depth) {
        menu_depth_max = max_depth;
        menu_depth = menu_depth_max;
    }

    public int get_menu_depth() {
        return menu_depth;
    }

    public boolean dark_mode() {
        return us.cash.app.a._dark_mode;
    }

    @Override final protected void onCreate(@Nullable Bundle saved_state) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        set_menu_max_depth(0);
        controller__on_create(saved_state);
        super.onCreate(saved_state);
        log("onCreate0"); //--strip
//        if (on_create__call_app) a.activity_on_create(this);  //causes screen redraw
        a.activity_on_create(this);  //causes screen redraw
    }

    @Override final protected void onPause() {
        log("onPause"); //--strip
        controller__on_pause();
        super.onPause();
        a.activity_on_pause(this);
    }

    @Override final protected void onResume() {
        log("onResume"); //--strip
        super.onResume();
        controller__on_resume();
        setTitle(get_title());
        a.activity_on_resume(this);
    }

    @Override final protected void onDestroy() {
        log("onDestroy"); //--strip
        controller__on_destroy();
        super.onDestroy();
        a.activity_on_destroy(this);
    }

    public void set_title(String title) {
        setTitle(title);
    }
    
    @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case INSTALL_PACKAGES_REQUESTCODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                }
                else {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                    intent.setData(Uri.parse("package:" + getPackageName()));
                    startActivity(intent);
                }
                break;
        }
    }

    public File screenshot(String filename) {
        View view = getWindow().getDecorView().getRootView();
        Date date = new Date();
        CharSequence format = android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", date); // Here we are initialising the format of our image name
        try { // Initialising the directory of storage
            String dirpath = Environment.getExternalStorageDirectory() + "";
            File file = new File(dirpath);
            if (!file.exists()) {
                boolean mkdir = file.mkdir();
            }
            String path = dirpath + "/" + filename + "-" + format + ".png";
            view.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);
            File imageurl = new File(path);
            FileOutputStream outputStream = new FileOutputStream(imageurl);
            bitmap.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
            outputStream.flush();
            outputStream.close();
            return imageurl;
        }
        catch (FileNotFoundException io) {
            io.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void hide_keyboard() {
        try{
            View v = getCurrentFocus();
            if (v == null) {
                v = new View(this);
            }
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
        catch (Exception e) {
        }
    }

    public void show_keyboard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager == null) return;
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                v.requestFocus();
            }
        }, 100);
    }

//    public void on_conf() {
//        a.launch_language();
//    }

    void locale_init() {
        log("locale_init"); //--strip
        String lang = locale.get_lang(this);
        String country = locale.get_country(this);
        if (lang == null || country == null) {
            locale.set(new Locale("en", "GB"));
        }
        else {
            locale.set(new Locale(lang, country));
        }
    }

    public boolean on_burger() {
        log("on_burger. menu_depth " + menu_depth + " menu_depth_max " + menu_depth_max); //--strip
        if (menu_depth_max == 0) return false;
        if (menu_depth > 0) {
            --menu_depth;
        }
        else {
            menu_depth = menu_depth_max;
        }
        return true;
    }

    int cache_version = 0;

    public us.cash.scr.drawer_t get_drawer() {
        log("get_drawer"); //--strip
        if (cache_version != us.cash.app.a.cache_version) {
            drawer = null;
            cache_version = us.cash.app.a.cache_version;
        }
        if (drawer == null) {
            drawer = new us.cash.scr.drawer_t(this, w.get_tree(this));
        }
        drawer.configure(this, get_menu());
        return drawer;
    }

    @Override public void setTitle(CharSequence title) {
        super.setTitle(title);
        log("setTitle " + title.toString()); //--strip
        a.le.screen.toolbar.set_title(title.toString());
    }

    public void set_title2(@NonNull String title) {
        setTitle(title);
    }

    @Override public void onBackPressed() {
        if (a.is_drawer_open()) {
            a.close_drawer();
            return;
        }
        super.onBackPressed();
    }

    public us.cash.scr.view__widgets w = null;

    static final int INSTALL_PACKAGES_REQUESTCODE = 1791;

    int _busy = 0;
    ko busy_result = ok;
    int menu_depth = 0;
    int menu_depth_max = 0;
    //menu_t menu = null;
    drawer_t drawer = null;
    //boolean on_create__call_app = true;

    public static app a = null;
}

//tests to recognize new IPs
//if (!wifiManager.isWifiEnabled()) {
//    Toast.makeText(this, "WiFi is disabled ... We need to enable it", Toast.LENGTH_LONG).show();
//    wifiManager.setWifiEnabled(true);
//}
//scanWifiNetworks();
//set_menu(menuid());
//navigation.setNavigationItemSelectedListener(this);
//ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout, a.toolbar_, R.string.bookmarks, R.string.bookmarks);
//drawer_layout.addDrawerListener(toggle);
//toggle.syncState();
