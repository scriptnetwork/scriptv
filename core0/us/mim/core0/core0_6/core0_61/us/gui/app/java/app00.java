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
//MIM    mim file: core0/core0_6/core0_61/us/gui/app/mim.h
//MIM  kickoff code hash: L73gfC1E8cN1UH5qnX5suSw45tQ (change this hash to force a review)
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
import us.cash.scr.*;
import android.media.projection.MediaProjection;
import android.graphics.Bitmap;
import android.media.Image;
import android.media.projection.MediaProjectionManager;
import android.media.ImageReader;
import android.graphics.PixelFormat;
import android.hardware.display.VirtualDisplay;
import android.hardware.display.DisplayManager;
import java.nio.ByteBuffer;
import android.media.projection.MediaProjection;
import android.os.Handler;
import androidx.core.view.GravityCompat;                                                       // GravityCompat
import java.util.Map;

public class app00 extends Application {

    private static void log(final String line) {                              //--strip
        CFG.log_android("app00: " + line);          //--strip
    }                                                                         //--strip

    public app00() {
        super();
        assert_ui_thread();                                                 //--strip
        log("----------------------APP CONSTRUCTOR-------------------- " + refcount);   //--strip
        log("Stack Trace at begining");                                     //--strip
        log(Arrays.toString(Thread.currentThread().getStackTrace()));       //--strip
        ++refcount;  //--strip
        if (refcount != 1) {  //--strip
            System.err.println("KO 50942 App instantiated multiple times"); //--strip
            System.exit(0);  //--strip
        } //--strip

CFG.sdk_logs = true; //--strip   TODO: disable
        log("SDK logs: " + CFG.sdk_logs);                                   //--strip
        us.CFG.logs.set(CFG.sdk_logs);                                      //--strip
        //assert a != null;
    }

    public static boolean is_ui_thread() {
        return Looper.getMainLooper().isCurrentThread();
    }

    public static void assert_ui_thread() {                                 //--strip
        if (!Looper.getMainLooper().isCurrentThread()) {                    //--strip
            log(Arrays.toString(Thread.currentThread().getStackTrace()));   //--strip
            log("ASSERT UI THREAD FAILED");                                 //--strip
            throw new AssertionError("not UI Thread");                      //--strip
        }                                                                   //--strip
    }                                                                       //--strip

    public static void assert_worker_thread() {                             //--strip
        if (Looper.getMainLooper().isCurrentThread()) {                     //--strip
            log(Arrays.toString(Thread.currentThread().getStackTrace()));   //--strip
            log("ASSERT WORKER THREAD FAILED");                             //--strip
            throw new AssertionError("UI Thread");                          //--strip
        }                                                                   //--strip
    }                                                                       //--strip

    @Override protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    static int refcount = 0; //--strip

    protected void init_static_refs(app a_) {
        a = a_;
        activity.a = fragment.a = us.cash.scr.toolbar_t.a = a;
    }

    void abort(String reason) {
        log("ABORT " + reason); //--strip
        exit_app();
        //android.os.Process.killProcess(android.os.Process.myPid());
        //System.exit(0);
    }

    AlertDialog.Builder new_dlg_builder(Context ctx) {
        log("new_dlg_builder"); //--strip
        return new AlertDialog.Builder(new ContextThemeWrapper(ctx, R.style.myDialog));
    }

    public boolean dark_mode(int setting) {
        switch (setting) {
            case 0:
                return true;
            case 1: {
                    int flags = getApplicationContext().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                    switch (flags) {
                        case Configuration.UI_MODE_NIGHT_YES:
                            return true;
                        case Configuration.UI_MODE_NIGHT_NO:
                            return false;
                        case Configuration.UI_MODE_NIGHT_UNDEFINED:
                            return false;
                    }
                }
                break;
            case 2:
                return false;
        }
        return false;
    }

    public boolean _dark_mode;
    public int setting__dark_mode = 1; //0:dark; 1:system; 2:light

    public void set_setting_dm(int v) {
        assert v == 0 || v == 1 || v == 2;
        if (setting__dark_mode == v) return;
        setting__dark_mode = v;
        boolean b = _dark_mode;
        _dark_mode = dark_mode(setting__dark_mode); 
        if (_dark_mode != b) {
            clear_cache();
        }
        configure_screen();
    }

    @Override public void onCreate() {
        super.onCreate();
        log("onCreate"); //--strip
        locale.set(new Locale(locale.get_lang(this), locale.get_country(this)));
        locale.update(this, getResources().getConfiguration());
        String lang = locale.get_lang(this);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        _dark_mode = dark_mode(setting__dark_mode); 
        log("_dark_mode " + _dark_mode); //--strip
        le = new layout_engine_t(getApplicationContext());
        register_actions();
    }

    int miss_configure_screen = 0;

    public void set_foreground_activity(activity0 ac, boolean up) {
        log("set_foreground_activity " + up + " " + ac); //--strip
        assert_ui_thread(); //--strip
        if (up) {
            if (active_ac != ac) {
                active_ac = ac;
                log("active_ac --> set " + active_ac + " ++++++++++++++++++++++++++++++++++++++++++++++++++++++"); //--strip
                if (miss_configure_screen > 0) {
                    log("missed redundant call to configure_screen"); //--strip
                    --miss_configure_screen;
                    return;
                }
                log("call configure_screen"); //--strip
                configure_screen();
            }
        }
        else {
            log("active_ac --> null XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"); //--strip
            //new Exception().printStackTrace();            
            active_ac = null;
        }
    }

    String r_(int id) {
        return getResources().getString(id);
    }

    public void run_test_seqs() {
        close_drawer(); //--strip
    }

    public void toast_msg(final String msg, int bg, int fg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
        View view = toast.getView();
        view.setBackgroundColor(bg);
        TextView text = view.findViewById(android.R.id.message);
        text.setTextColor(fg);
        toast.show();
    }

    public void toast(final String s) {
        app.assert_ui_thread(); //--strip
        log("Toast:" + s); //--strip
        Toast.makeText(getApplicationContext(), s, 6000).show();
    }

    public void toast__worker(final String msg) {
        log("toast__worker " + msg); //--strip
        assert_worker_thread();  //--strip
        if (active_ac == null) return;
//        if (active_ac == null) return;
        active_ac.runOnUiThread(new Runnable() {
            @Override public void run() {
                toast(msg);
            }
        });
    }

    static final int REQUEST_CODE_SCREENSHOT = 1929;

    private void requestScreenshotPermission() {
        MediaProjectionManager mediaProjectionManager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);
        Intent intent = mediaProjectionManager.createScreenCaptureIntent();
        active_ac.startActivityForResult(intent, REQUEST_CODE_SCREENSHOT);
    }

    Bitmap capture_screenshot(MediaProjection mediaProjection) {
        // Get screen dimensions
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int screenWidth = metrics.widthPixels;
        int screenHeight = metrics.heightPixels;

        // Create an ImageReader to capture the screenshot
        ImageReader imageReader = ImageReader.newInstance(screenWidth, screenHeight, PixelFormat.RGBA_8888, 1);
        VirtualDisplay virtualDisplay = mediaProjection.createVirtualDisplay("Screenshot",
                screenWidth, screenHeight, metrics.densityDpi,
                DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                imageReader.getSurface(), null, null);

        // Capture the screenshot
        Image image = imageReader.acquireLatestImage();
        Bitmap screenshot = image_to_Bitmap(image);
        image.close();
        virtualDisplay.release();
        mediaProjection.stop();
        return screenshot;

        // Clean up resources
    }

    Bitmap image_to_Bitmap(Image image) {
        Image.Plane[] planes = image.getPlanes();
        ByteBuffer buffer = planes[0].getBuffer();
        int width = image.getWidth();
        int height = image.getHeight();
        int pixelStride = planes[0].getPixelStride();
        int rowStride = planes[0].getRowStride();
        int rowPadding = rowStride - pixelStride * width;
        Bitmap bitmap = Bitmap.createBitmap(width + rowPadding / pixelStride, height, Bitmap.Config.ARGB_8888);
        bitmap.copyPixelsFromBuffer(buffer);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        log("onActivityResult " + requestCode + " " + resultCode); //--strip
        if (requestCode == REQUEST_CODE_SCREENSHOT && resultCode == activity.RESULT_OK) {
//            MediaProjection mediaProjection = mediaProjectionManager.getMediaProjection(resultCode, data);
//            capture_creenshot(mediaProjection);
        }
    }

    public void set_clipboard(final String payload, final String headline) {
        assert_ui_thread(); //--strip
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(payload);
        }
        else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText(headline, payload);
            clipboard.setPrimaryClip(clip);
        }
        toast(headline + " copied to clipboard");
    }

    protected void exit_app() {
        if (active_ac != null) {
            active_ac.finishAffinity();
        }
        System.exit(0);
    }

    void check_dark_mode_() {
        boolean cur = dark_mode(setting__dark_mode);
        if (cur != _dark_mode) {
            _dark_mode = cur;
            log("VVZZX dark_mode changed to " + _dark_mode); //--strip
            clear_cache();
        }
    }

    public void activity_on_create(activity0 ac) {
        assert le != null;
        log("VVZZX activity_on_create " + ac); //--strip
        check_dark_mode_();
        set_foreground_activity(ac, true);
    }

    public void activity_on_pause(activity0 ac) {
        log("VVZZX activity_on_pause " + ac); //--strip
        set_foreground_activity(ac, false);
    }

    public void activity_on_resume(activity0 ac) {
        log("activity_on_resume " + ac); //--strip
        check_dark_mode_();
        set_foreground_activity(ac, true);
    }

    public void activity_on_destroy(activity0 ac) {
        log("activity_on_destroy " + ac); //--strip
    }

    public void configure_screen() { //meny & toolbar
        log("configure_screen"); //--strip
        if (active_ac == null) {
            log("missed configure_screen active_ac is null."); //--strip
            return;
        }
        active_ac.setContentView(le.get_screen(active_ac));
    }

    public void configure_screen__worker() {
        assert_worker_thread();  //--strip
        log("configure_screen__worker"); //--strip
        if (active_ac == null) { //one trace occuring this is when the app is in background (no activity active) and the network disconnects causin event on_hmi_status)
            log("missed configure_screen active_ac is null"); //--strip
            return;
        }
        active_ac.runOnUiThread(new Runnable() {
            @Override public void run() {
                configure_screen();
            }
        });
    }

    public void open_drawer() {
        log("opening drawer"); //--strip
        le.screen.drawer.open();
    }

    public boolean is_drawer_open() {
        return le.screen.drawer.is_open();
    }

    public void toggle_drawer() {
        log("toggle drawer"); //--strip
        le.screen.drawer.toggle();
    }

    public void close_drawer() {
        log("closing drawer"); //--strip
        le.screen.drawer.close();
    }

    public menu_t get_menu() {
        log("menuid menu__app"); //--strip
        return us.cash.scr.app__menu___main_t.get_instance(getApplicationContext());
    }

    boolean on_burger() {
        log("QAZAQ on_burger"); //--strip
        if (active_ac == null) {
            log("QAZAQ KO 20993"); //--strip
            assert false; //--strip
//            menu_restore = null;
            return false;
        }
        if (active_ac.on_burger()) {
            log("QAZAQ activity changed menu"); //--strip
            configure_screen();
            open_drawer();
            return true;
        }
        return false;
    }

    public boolean on_menu(final int id) {
        log("on_menu " + id); //--strip
        if (id == R.raw.burger) {
            if (on_burger()) {
                return true;
            }
        }
        else if (id == R.raw.close) {
            new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        active_ac.finish();
                    }
                }, 100);
        }
        else if (id == R.raw.exit) {
            new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        if (active_ac == null) {
                            exit_app();
                        }
                        else {
                            log("XXZV exit. active_ac.menu_depth=" + active_ac.menu_depth); //--strip
                            if (active_ac.menu_depth == 0) {
                                exit_app();
                            }
                            else {
                                active_ac.finish();
                            }
                        }
                    }
                }, 100);
        }
        else if (id == R.raw.dev) { //--strip
            mim_menu();             //--strip
        }                           //--strip
        else {
            return false;
        }
        return true;
    }

    void mim_menu() {                                                           //--strip
        assert active_ac != null;                                               //--strip
        String i = a.le.mim_dump();                                             //--strip
        new_dlg_builder(active_ac).setTitle("MIM screen dump:").setMessage(i).show();    //--strip
    }                                                                           //--strip


    // Object temporary storage for launching activities 
    /////////////////////////////////////////////////////////////////////////////////////////
    private TreeMap<Integer, Object> object_memory = new TreeMap<Integer, Object>();
    private Integer next_object = 0;

    public Integer mem_set_object(Object o) {
        Integer id = next_object++;
        object_memory.put(id, o);    
        return id;
    }

    public Object mem_get_object(Integer id) {
        if (id == null) return null;
        return object_memory.remove(id);
    }

    // Contextual Actions over objects 
    /////////////////////////////////////////////////////////////////////////////////////////
    public static interface action_runner_t {
        void run(final Object nft);
    }
    
    public static class action_t {
        public action_t(final String label_, final action_runner_t runner_) {
            view = null;
            label = label_;
            runner = runner_;
        }

        public action_t(View view_, final String label_, final action_runner_t runner_) {
            view = view_;
            label = label_;
            runner = runner_;
        }
        
        View view;
        String label;
        action_runner_t runner;
    }
    
    public static class actions_t extends ArrayList<action_t> {
    }

    public static class all_actions_t extends TreeMap<String, actions_t> { //e.g. wallet_node_address, wallet_account_address 

        public void register_action(String item_type, action_t action) {
            actions_t e = get(item_type);
            if (e == null) {
                e = new actions_t();
                e.add(action);
                put(item_type, e);
            }
            else {
                e.add(action);
            }
        }

        public void unregister_actions(String item_type, View v) {
            actions_t e = get(item_type);
            if (e == null) return;
            actions_t o = new actions_t();
            for (action_t i: e) {
                if (i.view != v) {
                    o.add(i);
                }
            }
            if (o.isEmpty()) {
                remove(item_type);
            }
            else {
                put(item_type, o);
            }
        }

        public void unregister_actions(View v) {
            TreeMap<String, actions_t> tmp = new TreeMap<String, actions_t>();
            for (Map.Entry<String, actions_t> entry : entrySet()){
                actions_t e = entry.getValue();
                actions_t o = new actions_t();
                for (action_t i: e) {
                    if (i.view != v) {
                        o.add(i);
                    }
                }
                if (!o.isEmpty()) {
                    tmp.put(entry.getKey(), o);
                }
            }
            clear();
            putAll(tmp);
        }

        public String dump() {
            String x = new String();
            for (Map.Entry<String, actions_t> entry : entrySet()){
                for (action_t i: entry.getValue()) {
                    x = x + entry.getKey() + " " + i.label + " " + (i.view == null ? "A" : "V") + "\n";
                }
            }
            return x;
        }
    }

    public void show_contextual_options_for(Context ctx, int icon, final String title, final String item_type, final Object nft) {

        final actions_t tactions = actions.get(item_type);
        if (tactions == null || tactions.isEmpty()) {
            toast("No actions are available for item type " + item_type);
            return;
        }
        ArrayList<String> oplist = new ArrayList<String>();
        for (action_t i: tactions) {
            oplist.add(i.label);
        }
        oplist.add(getResources().getString(R.string.cancel));
        final String[] options = oplist.toArray(new String[0]); //https://shipilev.net/blog/2016/arrays-wisdom-ancients/
//        final app self = this;
        AlertDialog.Builder dlg = new_dlg_builder(ctx);
        dlg.setTitle(title).setItems(options, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
//toast("which="+which+"     " + self.actions.size());
                if (which >= 0 && which < tactions.size()) {
                    tactions.get(which).runner.run(nft);
                } 
            }
        }).setIcon(icon).show();

    }

    public void register_actions() {
    }


    public all_actions_t actions = new all_actions_t();

    public int cache_version = 1;
    public void clear_cache() {
        log("clear_cache"); //--strip
        ++cache_version;
        us.cash.scr.##menu_classnames##.clear_cache();
        le.clear_cache();
    }

    public static app a;
    PrintStream cout = System.out;
    public activity0 active_ac = null;
    //Lock lock_active_ac = new ReentrantLock();
    public layout_engine_t le;


}

