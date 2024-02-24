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
//MIM    'apifetch': 'public pair<ko, devi...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'class_qualifier': '' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'classname': 'device_endpoint__conf'
//MIM    'controller': 'activity' @ core0/core0_7/us/gui/device_endpoints/conf/device_endpoint/conf/mim.h
//MIM    'datatype': 'device_endpoint_t' @ core0/core0_7/us/gui/device_endpoints/conf/device_endpoint/conf/mim.h
//MIM    'datatype_decl': 'protected ##datatype...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'get_menu_override': '@Override protected ...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'include': ''
//MIM    'load__worker': '@Override public ko ...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'menu_max_depth': '1' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'on_menu__handling': 'protected void on_me...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'title': 'Connection' @ core0/core0_7/us/gui/device_endpoints/conf/device_endpoint/conf/mim.h
//MIM  kickoff code hash: NoVGQKY1Ve4Yih3uwSZ5NPw8dea (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.os.Bundle;                                                                      // Bundle
import us.ko;
import static us.ko.*;
import android.os.Handler;
import us.pair;
import android.content.Intent;                                                                 // Intent
import android.view.View;                                                                      // View
import android.view.LayoutInflater;                                                            // LayoutInflater
import android.view.ViewGroup;                                                                 // ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle;                                           // ActionBarDrawerToggle
import androidx.core.app.ActivityCompat;                                                       // ActivityCompat
import android.widget.AdapterView;                                                             // AdapterView
import androidx.appcompat.app.AlertDialog;                                                     // AlertDialog
import android.widget.ArrayAdapter;                                                            // ArrayAdapter
import us.wallet.trader.bookmarks_t;                                                           // bookmarks_t
import us.wallet.trader.bookmark_t;                                                            // bookmark_t
import android.os.Build;                                                                       // Build
import android.graphics.Color;                                                                 // Color
import android.widget.CompoundButton;                                                          // CompoundButton
import android.content.res.Configuration;                                                      // Configuration
import androidx.core.content.ContextCompat;                                                    // ContextCompat
import android.content.Context;                                                                // Context
import us.gov.socket.datagram;                                                                 // datagram
import android.content.DialogInterface;                                                        // DialogInterface
import android.text.method.DigitsKeyListener;                                                  // DigitsKeyListener
import android.graphics.drawable.Drawable;                                                     // Drawable
import us.gov.crypto.ec;                                                                       // ec
import android.text.Editable;                                                                  // Editable
import android.widget.EditText;                                                                // EditText
import android.graphics.drawable.GradientDrawable;                                             // GradientDrawable
import androidx.core.view.GravityCompat;                                                       // GravityCompat
import static us.gov.crypto.ripemd160.hash_t;                                                  // hash_t
import static android.Manifest.permission.*;                                                   // *
import static us.gov.id.types.*;                                                               // *
import static us.gov.io.types.*;                                                               // *
import static us.gov.socket.types.*;                                                           // *
import static us.ko.*;                                                                         // *
import static us.stdint.*;                                                                     // *
import static us.tuple.*;                                                                      // *
import android.view.inputmethod.InputMethodManager;                                            // InputMethodManager
import android.text.InputType;                                                                 // InputType
import android.content.Intent;                                                                 // Intent
import java.io.IOException;                                                                    // IOException
import us.wallet.engine.ip4_endpoint_t;                                                        // ip4_endpoint_t
import org.json.JSONArray;                                                                     // JSONArray
import org.json.JSONException;                                                                 // JSONException
import org.json.JSONObject;                                                                    // JSONObject
import android.text.method.KeyListener;                                                        // KeyListener
import java.security.KeyPair;                                                                  // KeyPair
import us.ko;                                                                                  // ko
import androidx.appcompat.widget.LinearLayoutCompat;                                           // LinearLayoutCompat
import android.widget.LinearLayout;                                                            // LinearLayout
import java.util.Locale;                                                                       // Locale
import android.Manifest;                                                                       // Manifest
import java.util.Map;                                                                          // Map
import com.google.android.material.button.MaterialButton;                                      // MaterialButton
import android.view.Menu;                                                                      // Menu
import android.view.MenuItem;                                                                  // MenuItem
import android.view.View.OnFocusChangeListener;                                                // OnFocusChangeListener
import android.content.pm.PackageManager;                                                      // PackageManager
import us.pair;                                                                                // pair
import us.wallet.protocol;                                                                     // protocol
import android.widget.RelativeLayout;                                                          // RelativeLayout
import androidx.annotation.RequiresApi;                                                        // RequiresApi
import android.content.res.Resources;                                                          // Resources
import android.text.SpannableStringBuilder;                                                    // SpannableStringBuilder
import android.widget.Spinner;                                                                 // Spinner
import java.lang.StringBuilder;                                                                // StringBuilder
import android.annotation.SuppressLint;                                                        // SuppressLint
import android.widget.Switch;                                                                  // Switch
import com.google.android.material.textfield.TextInputEditText;                                // TextInputEditText
import com.google.android.material.textfield.TextInputLayout;                                  // TextInputLayout
import android.widget.TextView;                                                                // TextView
import android.text.TextWatcher;                                                               // TextWatcher
import java.util.Timer;                                                                        // Timer
import java.util.TimerTask;                                                                    // TimerTask
import android.widget.Toast;                                                                   // Toast
import android.media.ToneGenerator;                                                            // ToneGenerator
import android.util.TypedValue;                                                                // TypedValue
import us.gov.io.types.vector_tuple_hash_host_port;                                            // vector_tuple_hash_host_port
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import android.widget.ImageView;
import android.util.AttributeSet;                                                              // AttributeSet
import android.os.Handler;

public final class device_endpoint__conf extends activity implements device_endpoints_t.monitor_handler_t {

    private static void log(final String line) {           //--strip
        CFG.log_android("device_endpoint__conf: " + line);         //--strip
    }                                                      //--strip

    public device_endpoint__conf() {
        super();
        log("device_endpoint__conf constructor"); //--strip
    }

    //--------------lifecycle-------------------------------------------------------
    @Override protected void controller__on_create(Bundle saved_state) {
        assert w == null; //--strip
        set_menu_max_depth(1);
        super.controller__on_create(saved_state);
        log("controller__on_create"); //--strip
        w = (us.cash.scr.device_endpoint__conf__widgets) super.w;
        assert w != null; //--strip
        load(); //follows on_ready
    }

    @Override protected void controller__on_pause() {
        log("controller__on_pause"); //--strip
        update_hmi__set_manual_mode(false);
        disconnect_leds();
        dep.parent.hmi__remove_listener(this);
        super.controller__on_pause();
    }

    @Override protected void controller__on_resume() {
        super.controller__on_resume();
        log("controller__on_resume"); //--strip
        a.device_endpoints.hmi__add_listener(this);
        if (dep == a.device_endpoints.cur) {
            connect_leds();
        }
        update_hmi__set_manual_mode(true);
        if (a.device_endpoints.find_index(dep) == -1) {
            log("device_endpoint doesn't exist anymore (has been deleted), closing conf activity"); //--strip
            finish();
            return;
        }
    }

    @Override protected void controller__on_destroy() {
        log("controller__on_destroy"); //--strip
        w = null;
        update_hmi__set_manual_mode(false);
        super.controller__on_destroy();
    }
    //-/------------lifecycle-------------------------------------------------------

    @Override public String get_title() {
        return "Connection";
    }

    //MIM begin token 'apifetch'
    public pair<ko, device_endpoint_t> fetch_dep() {
        log("fetch_dep"); //--strip
        Intent i = getIntent();
        if (i == null) {
            ko r = new ko("KO 20199 Intent");
            log(r.msg); //--strip
            return new pair(r, null);
        }
        if (!i.hasExtra("object_id")) {
            ko r = new ko("KO 77369 Missing object id");
            log(r.msg); //--strip
            return new pair(r, null);
        }
        Integer id = i.getExtras().getInt("object_id", -1);
        log("object_id " + id); //--strip
        device_endpoint_t o = (device_endpoint_t) a.mem_get_object(id);
        if (o == null) {
            ko r = new ko("KO 77829 Invalid object_id");
            log(r.msg); //--strip
            return new pair(r, null);
        }
        return new pair(ok, o);
    }
    //MIM end token 'apifetch'

    //MIM begin token 'load__worker'
    @Override public ko load__worker() {
        log("load__worker"); //--strip
        a.assert_worker_thread(); //--strip
        pair<ko, device_endpoint_t> r = fetch_dep();
        if (is_ko(r.first)) {
            dep = null;
            return r.first;
        }
        dep = r.second;
        assert dep != null; //--strip
        return ok;
    }
    //MIM end token 'load__worker'

    public void on_ready(final ko load_result) {
        log("on_ready"); //--strip
        a.assert_ui_thread(); //--strip
        if (is_ko(load_result)) {
            log("on_ready FAIL"); //--strip
            toast(load_result.msg);
            finish();
            return;
        }
        log("on_ready OK"); //--strip
        assert dep != null;
        final ip4_endpoint_t ep = dep.ip4_endpoint;
        log("ip4_endpoint " + (ep == null ? "Null" : ep.to_string())); //--strip
        if (ep != null) {
            log("Set UI Texts"); //--strip
            w.addr.setText(ep.shost.value);
            w.port.setText("" + ep.port.value);
            w.channel.setText("" + ep.channel.value);
        }
        set_handlers();
        refresh();
    }

    //MIM begin token 'get_menu_override'
    @Override protected us.cash.scr.menu_t get_menu() {
        if (get_menu_depth() < 1) return super.get_menu();
        assert get_menu_depth() == 1; //--strip
        if (dep != null && dep.hmi != null && dep.hmi.is_online) {
            log("returning menu for hmi=ON"); //--strip
            return us.cash.scr.device_endpoint__conf__menu___hmion_t.get_instance(this);
        }
        log("returning menu for hmi=OFF"); //--strip
        return us.cash.scr.device_endpoint__conf__menu___hmioff_t.get_instance(this);
    }
    //MIM end token 'get_menu_override'

    //MIM begin token 'on_menu__handling'
    protected void on_menu__hmioff__findip() { //Find the IP Address of my node
        log("on_menu__hmioff__findip"); //--strip
        find_ip_address();
    }

    protected void on_menu__hmioff__hmion__log() { //Connection log...
        log("on_menu__hmioff__hmion__log"); //--strip
        connection_log();
    }

    protected void on_menu__hmion__pill() { //Software updates...
        log("on_menu__hmion__pill"); //--strip
        dep.hmi.sw_updates.show_ui(device_endpoint__conf.this);
    }

    protected void on_menu__hmioff__power() { //Power-on HMI
        log("on_menu__hmioff__power"); //--strip
        try_poweron(false); //false = don't ask for pin
    }

    protected void on_menu__hmioff__power_pin() { //Power-on HMI (with PIN)...
        log("on_menu__hmioff__power_pin"); //--strip
        try_poweron(true); //true = ask for pin
    }

    protected void on_menu__hmion__poweroff() { //Power-off HMI
        log("on_menu__hmion__poweroff"); //--strip
        on_user_req_poweroff();
    }

    protected void on_menu__hmioff__hmion__seeds() { //Network seeds
        log("on_menu__hmioff__hmion__seeds"); //--strip
        show_seeds();
    }


    @Override public boolean on_menu(int id) {
        log("on_menu"); //--strip
        if (id == R.raw.findip) { //Find the IP Address of my node
            new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        on_menu__hmioff__findip();
                    }
                }, 100);
        }
        else if (id == R.raw.log) { //Connection log...
            new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        on_menu__hmioff__hmion__log();
                    }
                }, 100);
        }
        else if (id == R.raw.pill) { //Software updates...
            new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        on_menu__hmion__pill();
                    }
                }, 100);
        }
        else if (id == R.raw.power) { //Power-on HMI
            new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        on_menu__hmioff__power();
                    }
                }, 100);
        }
        else if (id == R.raw.power_pin) { //Power-on HMI (with PIN)...
            new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        on_menu__hmioff__power_pin();
                    }
                }, 100);
        }
        else if (id == R.raw.poweroff) { //Power-off HMI
            new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        on_menu__hmion__poweroff();
                    }
                }, 100);
        }
        else if (id == R.raw.seeds) { //Network seeds
            new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        on_menu__hmioff__hmion__seeds();
                    }
                }, 100);
        }
        else {
            return super.on_menu(id);
        }
        return true;
    }
    //MIM end token 'on_menu__handling'

    @Override public us.cash.scr.view__widgets create_widgets() {
        View.OnClickListener save_name__listener = new View.OnClickListener() {
            @Override public void onClick(View view) {
                dep.name_.value = w.name.getText().toString();
                a.device_endpoints.save();
                w.save_name.setVisibility(View.GONE);
            }
        };
        View.OnClickListener read_ssid__listener = new View.OnClickListener() {
            @Override public void onClick(View view) {
                ask_read_ssid();
            }
        };
        View.OnClickListener save_ssid__listener = new View.OnClickListener() {
            @Override public void onClick(View view) {
                dep.ssid.value = w.ssid.getText().toString();
                a.device_endpoints.save();
                w.save_ssid.setVisibility(View.GONE);
            }
        };
        View.OnClickListener connection_status__listener = new View.OnClickListener() {
           @Override public void onClick(View view) {
               state.toast_result();
           }
        };
        View.OnClickListener save_subhome__listener = new View.OnClickListener() {
           @Override public void onClick(View view) {
                String x = w.subhome.getText().toString();
                if (x.equals("[Non-Custodial]")) {
                    x = "";
                }
                dep.subhome.value = x;
                a.device_endpoints.save();
                w.save_subhome.setVisibility(View.GONE);
           }
        };
        View.OnClickListener connect_btn__listener = new View.OnClickListener() {
           @Override public void onClick(View view) {
                w.connect_btn.requestFocus();
                hide_keyboard();
                try_poweron(true); //true = ask for pin
           }
        };
        return new us.cash.scr.device_endpoint__conf__widgets(save_name__listener, read_ssid__listener, save_ssid__listener, connection_status__listener, save_subhome__listener, connect_btn__listener);
    }

    @Override public void on_send(boolean busy) {}
    @Override public void on_recv(boolean busy) {}

    @Override public void on_hmi__worker(final device_endpoint_t src, final ko status) {
        app.assert_worker_thread(); //--strip
        if (src != dep) return;
        log("on_hmi__worker"); //--strip
        connect_leds__worker();
        if (resume__poweron0) { //old hmi has just powered off, start the new one
            resume__poweron0 = false;
            runOnUiThread(new Runnable() {
                public void run() {
                    do_test();
                }
            });
        }
        if (resume__poweron) { //old hmi has just powered off, start the new one
            resume__poweron = false;
            if (status == hmi_t.KO_10001) { // I Disconnected
                log("on_hmi__worker status=" + status.msg); //--strip
                Thread thread = new Thread(new Runnable() {
                    @Override public void run () {
                        try {
                            Thread.sleep(100);
                        }
                        catch (Exception e) {
                        }
                        test_task2();
                    }
                });
                thread.start();
            }
        }
        if (status == ok) {
            update_hmi__set_manual_mode(true);
        }

        runOnUiThread(new Runnable() {
            @Override public void run() {
                log("HMI event: refresh"); //--strip
                refresh();
            }
        });

    }

    @Override public void on_status(final device_endpoint_t src, int led_status, final String msg) {
        a.assert_worker_thread(); //--strip
        if (src != dep) return;
        assert w != null; //--strip
        final device_endpoint__conf self = this;
        runOnUiThread(new Runnable() {
            @Override public void run() {
                //self.w.leds.set_led__ui(0, led_status);
                self.refresh_subhome_widgets();
                self.refresh_online();
                if (msg != null) {
                    self.w.connection_status.setText(msg);
                }
            }
        });
    }

    @Override public void confirmed_subhome(final device_endpoint_t src, final String subhome__backend_choice) {
        a.assert_worker_thread(); //--strip
        log("confirmed_subhome " + subhome__backend_choice); //--strip
        if (src != dep) return;
        dep.set_subhome(subhome__backend_choice);
        final device_endpoint__conf self = this;
        runOnUiThread(new Runnable() {
            @Override public void run() {
                self.refresh_subhome_widgets();
                self.refresh_online();
            }
        });
    }

    void connect_leds() {
        if (leds_connected) return;
        a.device_endpoints.hmi__add_listener(w.leds);
        leds_connected = true;
    }

    void connect_leds__worker() {
        if (leds_connected) return;
        a.device_endpoints.hmi__add_listener__worker(w.leds);
        leds_connected = true;
    }

    void disconnect_leds() {
        if (!leds_connected) return;
        a.device_endpoints.hmi__remove_listener(w.leds);
        leds_connected = false;
    }

    static class state_t {
        public ip4_endpoint_t ip4_endpoint = null;
        public pin_t pin;
        public String subhome = "";
        public boolean success = false;
        public boolean called = false;
        public boolean lookingup = false;
        public boolean testing = false;
        public ko test_result = new ko("Not tested");
        public String advice = null;

        public boolean start_lookup() {
            if (lookingup) return false;
            if (testing) return false;
            lookingup = true;
            return true;
        }

        public void start_testing(ip4_endpoint_t ep, pin_t pin_, String subhome_) {
            log("start_testing " + (ep == null ? "NULL EP" : ep.to_string())); //--strip
            ip4_endpoint = ep;
            pin = pin_;
            subhome = subhome_;
            testing = true;
            called = true;
            success = false;
            test_result = new ko("Test not finished yet.");
            advice = null;
        }

        public void end_testing(ko err, String advice_) {
            assert testing == true;
            log("end_testing " + (err == ok ? "OK" : err.msg) + " "); //--strip
            testing = false;
            success = err == ok;
            test_result = err;
            advice = advice_;
            toast_result();
        }

        public void end_testing(ko report) {
            log("end_testing " + ko.as_string(report)); //--strip
            if (report != ok) {
                if (report == us.gov.socket.client.KO_83912) {
                    end_testing(report, "Node is unreachable. Please double check your inputs and consider if they are compatible with your WIFI/GSM configuration.");
                }
                else if (report == us.gov.id.peer_t.KO_6017) {
                    end_testing(report, "New devices must be paired first.");
                }
                else if (report == hmi_t.KO_10000) {
                    end_testing(report, "New devices must be paired first.");
                }
                else if (report == hmi_t.KO_61210) {
                    end_testing(report, "Oops this shouldn't happen. Handshake error. ");
                }
                else {
                    end_testing(report, "Oops this shouldn't happen.");
                }
            }
            else {
                end_testing(ok, "");
            }
        }

        public void toast_result() {
            if (!success) {
                Toast.makeText(a, test_result.msg + '\n' + advice, Toast.LENGTH_LONG).show();
            }
        }
    }


    void on_close() {
        finish();
    }


    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    }

    private static String[] PERMISSIONS_WIFI_SSID = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private static final int REQUEST_PERMISSION_WIFI_SSID = 1832;


    public void ask_permission_continue() {
        log("request Permission"); //--strip
        ActivityCompat.requestPermissions(this, PERMISSIONS_WIFI_SSID, REQUEST_PERMISSION_WIFI_SSID);
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        log("checkSelfPermission returned " + permission + " (granted=" + PackageManager.PERMISSION_GRANTED + ")"); //--strip
        String msg = (permission == PackageManager.PERMISSION_GRANTED) ? "Thanks for granting access to current wifi SSID." : "Permissions for reading the SSID has been denied.";
        toast(msg);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            us.cash.app.a.tone.startTone(ToneGenerator.TONE_CDMA_NETWORK_BUSY, 200);
        }
        else {
            set_ssid2();
        }
    }

    private void ask_permission() {
        log("ask_permission"); //--strip
        AlertDialog.Builder dlg = a.new_dlg_builder(get_context());
        dlg.setTitle("FINE_LOCATION permissions.")
            .setMessage("This app collects location data to enable dynamic parameter selection even when the app is closed or not in use. The app maintains a user-defined list of connections and uses the network id (SSID) to select the most appropriate one based on this value. This data is never transmitted nor stored in public storage. Please grant access to your WIFI network name (SSID) in the following dialogue in order to enable this feature...")
            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        log("user clicked ok"); //--strip
                        ask_permission_continue();
                    }
                })
            .create();
        dlg.show();
    }

    public ko verify_ssid_permissions() {        // Check if we have write permission
        a.assert_ui_thread(); //--strip
        log("verify_ssid_permissions"); //--strip
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permission == PackageManager.PERMISSION_GRANTED) {
            log("Permission already granted"); //--strip
            return ok;
        }
        return new ko("KO 43097 Permission denied");
    }

    void set_ssid2() {
        a.assert_ui_thread(); //--strip
        ko r = verify_ssid_permissions();
        if (is_ko(r)) {
            toast(r.msg);
            return;
        }
        ssid_verified = a.net_identifier();
        log("ssid_verified " + ssid_verified); //--strip
        w.ssid.setText(ssid_verified);
    }

    void set_ssid() {
        a.assert_ui_thread(); //--strip
        if (is_ko(verify_ssid_permissions())) {
            log("asking for permissions"); //--strip
            ask_permission();
            return;
        }
        set_ssid2();
    }

    void update_hmi__set_manual_mode(boolean set_manual) { //Disables the autoconnect logic while configuring the interface.
        log("update_hmi__set_manual_mode " + set_manual); //--strip
        if (dep == null) {
            log("dep is null"); //--strip
            return;
        }
        dep.set_manual_mode(set_manual);
    }

    void show_connection_log() {
        if (dep.hmi == null) return;
        AlertDialog.Builder dlg = a.new_dlg_builder(get_context());
        dlg.setTitle("Connection log:").setMessage(dep.hmi.get_msg_log()).show();
    }

    void copy_connection_log() {
        if (dep.hmi == null) return;
        us.cash.app.a.set_clipboard(dep.hmi.get_msg_log(), "status_log");
    }

    public void techinfo() {
        String i = dep.techinfo();
        a.new_dlg_builder(get_context()).setTitle("HMI Info:").setMessage(i).show();
    }

    void connection_log() {
        if (dep.hmi == null) return;
        String[] options = {"Connection log. Show.", "Connection log. Copy.", "Connection log. Clear.", "HMI info.", a.getResources().getString(R.string.cancel)};
        a.new_dlg_builder(get_context()).setTitle("Connection log:")
            .setItems(options, new DialogInterface.OnClickListener() {
                @Override public void onClick(DialogInterface dialog, int which) {
                    switch(which) {
                        case 0:
                            show_connection_log();
                            break;
                        case 1:
                            copy_connection_log();
                            break;
                        case 2:
                            if (dep.hmi != null) {
                                dep.hmi.clear_msg_log();
                                toast("Connection log cleared.");
                            }
                            else {
                                toast("HMI is off.");
                            }
                            break;
                        case 3:
                            techinfo();
                            break;
                        case 4:
                            break;
                    }
                }
            }).show();
    }

    void show_seeds() {
        channel_t channel = channel_from_widgets();
        if (channel == null) {
            toast("KO 65880 Invalid value for channel. Please fill the channel box above (e.g. 0)");
            return;
        }
        us.gov.io.types.vector_tuple_hash_host_port seeds = dep.get_seeds(channel);
        if (seeds == null) {
            a.new_dlg_builder(get_context()).setTitle("Opps").setMessage("--empty--").show();
            return;
        }
        StringBuilder b = new StringBuilder(seeds.size() * 50);
        for (tuple3<hash_t, host_t, port_t> i: seeds) {
            b.append(i.item0.encode());
            b.append(' ');
            b.append(us.gov.socket.client.endpoint(i.item1, i.item2));
            b.append('\n');
        }
        a.new_dlg_builder(get_context()).setTitle("Some known nodes in channel " + channel.value).setMessage(b.toString()).show();
    }

    void find_ip_address() {
        log("looking up IP address..."); //--strip
        final device_endpoint__conf self = this;
        Thread t = new Thread(new Runnable() {
            @Override public void run () {
                pair<ko, ip4_endpoint_t> r = dep.lookup_ip(new hmi_t.gov_status_handler_t() {
                    @Override public void on_status(final int led, final String msg) {
                        if (msg != null) {
                            self.runOnUiThread(new Runnable() {
                                @Override public void run() {
                                    self.w.leds.set_led__ui(0, us.cash.scr.leds_t.led_blue);
                                    self.w.connection_status.setText(msg);
                                }
                            });
                        }
                    }
                });
                if (is_ko(r.first)) {
                    self.runOnUiThread(new Runnable() {
                        @Override public void run() {
                            //self.w.leds.set_led__ui(0, led_status);
                            //self.w.connection_status.setText(r.first.msg);
                            self.toast(r.first.msg);
                        }
                    });
                    return;
                }
                self.runOnUiThread(new Runnable() {
                    @Override public void run() {
                        //self.w.leds.set_led__ui(0, led_status);
                        self.w.connection_status.setText(r.second.to_string());
                        self.toast(r.second.to_string());
                    }
                });
                a.device_endpoints.report_status__worker();
            }
        });
        t.start();
    }

    private void show_PIN_dialog() {
        log("show_PIN_dialog"); //--strip
        final us.cash.scr.edit_text_t input = new us.cash.scr.edit_text_t(a);
        input.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override public void onFocusChange(View v, boolean hasFocus) {
                input.post(new Runnable() {
                    @Override public void run() {
                        InputMethodManager inputMethodManager= (InputMethodManager) device_endpoint__conf.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.showSoftInput(input, InputMethodManager.SHOW_IMPLICIT);
                    }
                });
            }
        });
        input.requestFocus();
        input.setSingleLine();
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        a.new_dlg_builder(get_context()).setTitle("Connecting to remote wallet.")
            .setMessage("Authentication for new devices. If you have a PIN number type it, or leave the field empty for sending default pin 00000. PINS are one-shot. Already authenticated devices don't need to enter any PIN.")
            .setView(input)
            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override public void onClick(DialogInterface dialog, int which) {
                    String v = String.valueOf(input.getText());
                    pin_t pin;
                    try {
                        pin = new pin_t(Integer.parseInt(v));
                    }
                    catch(Exception e) {
                        log("KO 30291 Exception " + e.getMessage()); //--strip
                        pin = new pin_t(0);
                    }
                    log("collected PIN " + pin.value); //--strip
                    do_test1(pin);
                }
            }).create().show();
    }

    channel_t channel_from_widgets() {
        channel_t chan;
        try {
            chan = new channel_t(Integer.parseInt(w.channel.getText().toString()));
        }
        catch(NumberFormatException e) {
            return null;
        }
        return chan;
    }

    ip4_endpoint_t ip4_endpoint_from_widgets() {
        final String address = w.addr.getText().toString();
        port_t tcpport;
        channel_t chan;
        try {
            tcpport = new port_t(Integer.parseInt(w.port.getText().toString()));
            chan = new channel_t(Integer.parseInt(w.channel.getText().toString()));
        }
        catch(NumberFormatException e) {
            return null;
        }
        return new ip4_endpoint_t(new shost_t(address), tcpport, chan);
    }

    void ask_read_ssid() {
        final device_endpoint_t curdep = a.device_endpoints.cur;
        final String curname = curdep.get_title();
        final device_endpoint__conf o = this;

        AlertDialog.Builder builder = a.new_dlg_builder(get_context());
        builder.setTitle("Fill with current SSID?");
        builder.setMessage("This configuration will be used only when connecting via this interface.");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                set_ssid();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    void ask_stop_current() {
        final device_endpoint_t curdep = a.device_endpoints.cur;
        final String curname = curdep.get_title();
        final device_endpoint__conf o = this;

        AlertDialog.Builder builder = a.new_dlg_builder(get_context());
        builder.setTitle("Stop current connection?");
        builder.setMessage("Only one simultaneous connection is possible. Do you want to stop the current one ('" + curname + "') before proceed?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                o.resume__poweron0 = true;
                o.a.HMI_power_off(a.device_endpoints.cur_index.value, false);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                refresh();
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    void on_user_req_poweron() {
        a.assert_ui_thread(); //--strip
        log("on_user_req_poweron"); //--strip
        ip4_endpoint_t ip4_endpoint = ip4_endpoint_from_widgets();
        ko ans = dep.set_ip4_endpoint(ip4_endpoint);
        try_poweron(false); //false = don't ask for pin
    }

    void on_user_req_poweroff() {
        a.assert_ui_thread(); //--strip
        log("on_user_req_poweroff"); //--strip
        disconnect_leds();
        w.leds.set_all_off__ui();
        a.HMI_power_off(dep, true);
    }

    void test_task() {
        log("test_task " + state.ip4_endpoint.to_string()); //--strip
        a.assert_worker_thread(); //--strip
        on_status(null, us.cash.scr.leds_t.led_amber, "Trying " + (state.ip4_endpoint != null ? state.ip4_endpoint.to_string() : ""));
        if (state.ip4_endpoint == null) {
            log("wrong ip4_endpoint from widgets"); //--strip
            test_result__worker(new ko("KO 60954 Wrong input."));
            return;
        }
        log("ip4_endpoint from state " + state.ip4_endpoint.to_string() + " PIN > 0 " + (state.pin.value > 0 ? "yes" : "no"));  //--strip
        if (dep.hmi != null) {
            log("Stopping current HMI"); //--strip
            resume__poweron = true;
            a.HMI_power_off__worker(dep, false);
            return;
        }
        test_task2();
    }

    void test_task2() {
        a.assert_worker_thread(); //--strip
        String st = "Connecting to " + state.ip4_endpoint.to_string() + " using PIN > 0 " + (state.pin.value > 0 ? "yes" : "no") + " subhome " + state.subhome;
        log(st); //--strip
        on_status(null, us.cash.scr.leds_t.led_amber, st);
        a.assert_worker_thread(); //--strip
        assert dep.hmi == null;
        dep.set_ip4_endpoint(state.ip4_endpoint);
        dep.set_subhome(state.subhome);
        a.HMI_power_on__worker(dep, state.pin);
    }

    void try_poweron(final boolean ask_pin) {
        askpin = ask_pin;
        if (a.has_hmi()) {
            if (!a.device_endpoints.is_cur(dep)) {
                ask_stop_current();
                return;
            }
        }
        connect_leds();
        do_test();
    }

    void do_test() {
        a.assert_ui_thread(); //--strip
        if (askpin) {
            show_PIN_dialog();
            return;
        }
        do_test1(new pin_t(0));
    }

    void do_test1(final pin_t pin) {
        a.assert_ui_thread(); //--strip
        log ("do_test " + pin.value); //--strip
        ip4_endpoint_t ep = ip4_endpoint_from_widgets();
        log ("ip4_endpoint_t from widgets " + ep.to_string()); //--strip
        String sbhome = w.subhome.getText().toString();
        if (sbhome.equals("[Non-Custodial]")) {
            sbhome = "";
        }
        log("do_test " + pin.value + " subhome '" + sbhome + "'"); //--strip
        state.start_testing(ep, pin, sbhome);
        //refresh();
        Thread thread = new Thread(new Runnable() {
            @Override public void run () {
                test_task();
            }
        });
        thread.start();
    }

    String r_(int id) {
        return getResources().getString(id);
    }

    boolean isok(final String report) {
        log("isok"); //--strip
        if (report.equals("?")) {
            log("DEPRECATED: still using '?'. not ok. "); //--strip
            return false;
        }
        if (report.startsWith("KO")) { //TODO: Deprecated control
            log("DEPRECATED report.startsWith"); //--strip
            log(report); //--strip
            return false;
        }
        log("is ok!"); //--strip
        return true;
    }

    void test_result_ui(final ko report) {
        log("test_result_ui"); //--strip
        a.assert_ui_thread(); //--strip
        state.end_testing(report);
        if (report == ok) {
            log("========saving ip4_ep " + state.ip4_endpoint.to_string()); //--strip
            dep.ip4_endpoint = state.ip4_endpoint;
            a.device_endpoints.save();
        }
        refresh();
    }

    void test_result__worker(final ko report) {
        a.assert_worker_thread();  //--strip
        runOnUiThread(new Runnable() {
            public void run() {
                test_result_ui(report);
            }
        });
    }

    String get_pubkey() {
        log("get_pubkey"); //--strip
        if (dep == null) return "";
        if (dep.cfg == null) return "";
        if (dep.cfg.keys == null) return "";
        return ec.instance.to_b58(dep.cfg.keys.getPublic());
    }

    String get_pubkeyh() {
        log("get_pubkey"); //--strip
        if (dep == null) return "";
        if (dep.cfg == null) return "";
        if (dep.cfg.keys == null) return "";
        return ec.instance.to_encoded_address(dep.cfg.keys.getPublic());
    }

    void refresh_mode_widgets() {
        log("refresh_mode_widgets. custodial_info"); //--strip
        assert dep.hmi != null; //--strip
        String subhome = dep.subhome.value;
        if (subhome.isEmpty()) {
            w.mode.setText(R.string.mode_non_custodial);
            w.mode.setTextColor(darkgreen);
        }
        else {
            String mcs = r_(R.string.mode_custodial);
            log("mcs=" + mcs); //--strip
            w.mode.setText(mcs + "\n(subhome " + subhome + ")");
            w.mode.setTextColor(orange);
        }
//        w.mode.setVisibility(View.VISIBLE);
    }

    void refresh_node_address_n_lookupip() {
        log("refresh_node_address_n_lookupip " + dep.hmi); //--strip
        //boolean lookupip_enable = true;
        assert dep.hmi != null; //--strip
        hash_t wallet_address = dep.hmi.wallet_address;
        if (wallet_address == null || wallet_address.is_zero()) {
            assert false; //--strip
            return;
            //lookupip_enable = false;
        }
        log("wallet_address " + wallet_address.encode()); //--strip
        String saddr = dep.hmi.wallet_address.encode();
        if (!dep.subhome.value.isEmpty()) {
            saddr += "." + dep.subhome.value;
        }
        w.nodepkh.setText(saddr);
//        w.npkh.setVisibility(View.VISIBLE);
        //if (state.lookingup || state.testing) {
            //lookupip_enable = false;
        //}
    }

    void set_handlers() {
        log("set_handlers"); //--strip
        w.name.addTextChangedListener(new TextWatcher() {
            @Override public void afterTextChanged(Editable s) { w.save_name.setVisibility(View.VISIBLE); }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        w.ssid.addTextChangedListener(new TextWatcher() {
            @Override public void afterTextChanged(Editable s) { w.save_ssid.setVisibility(View.VISIBLE); }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        subhome_key_listener = w.subhome.getKeyListener();
        w.poweron.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (disable_poweron_listener) {
                    log("power switch not actionated by human"); //--strip
                    return;
                }
                log("human touched power switch"); //--strip
                if (isChecked) {
                    on_user_req_poweron();
                }
                else {
                    on_user_req_poweroff();
                }
            }
        });
    }

    void lookup() {
        app.assert_worker_thread(); //--strip
        if (dep.hmi == null) return;
        log("lookup ip"); //--strip
        pair<ko, ip4_endpoint_t> r = dep.hmi.lookup_ip();
    }

    void refresh_online() {
        if (dep.hmi == null) {
            w.online.setVisibility(View.GONE);
            log("gone"); //--strip
            return;
        }
        hash_t wallet_address = dep.hmi.wallet_address;
        if (wallet_address == null || wallet_address.is_zero()) {
            w.online.setVisibility(View.GONE);
            log("gone2"); //--strip
            return;
            //lookupip_enable = false;
        }
        refresh_node_address_n_lookupip();
        refresh_connect_button();
        refresh_mode_widgets();
        w.online.setVisibility(View.VISIBLE);
    }

    void refresh_device_widgets() {
        log("devicepk"); //--strip
        w.devicepk.setText(get_pubkey());
        w.devicepkh.setText(get_pubkeyh());
    }

    void refresh_connect_button() {
        log("refresh_connect_button " + state.lookingup + " " + state.testing); //--strip
        assert dep.hmi != null; //--strip
        ip4_endpoint_t ep = dep.ip4_endpoint;
        log("ip4_endpoint " + (ep == null ? "Null" : ep.to_string())); //--strip
        if (ep != null) {
            w.current_endpoint.setText("Current: " + ep.to_string());
        }
        w.connect_btn.setEnabled(true);
//        w.connect_btn.setVisibility(View.VISIBLE);
//        w.current_endpoint.setVisibility(View.VISIBLE);
    }

    void refresh_ssid_widgets() {
        w.read_ssid.setVisibility(View.VISIBLE);
        w.save_ssid.setVisibility(View.GONE);
        String curnet = ssid_verified;
        log("curnet " + curnet); //--strip
        if (w.ssid.getText().equals(curnet)) {
            w.read_ssid.setVisibility(View.GONE);
        }
        else {
            w.read_ssid.setVisibility(View.VISIBLE);
        }
        String uissid = w.ssid.getText().toString();
        log("ssid.getText()='" + uissid + "'"); //--strip
        if (uissid.isEmpty()) {
            log("ssid.setText()->'" + dep.ssid.value + "'"); //--strip
            w.ssid.setText(dep.ssid.value);
            w.save_ssid.setVisibility(View.GONE);
        }
    }

    void refresh_subhome_widgets() {
        String subhometxt = w.subhome.getText().toString();
        log("subhome.getText() = '" + subhometxt + "'"); //--strip

        w.subhome.removeTextChangedListener(subhome_TextWatcher);
        if (dep.subhome.value.isEmpty()) {
            w.subhome.setText("[Non-Custodial]");
            w.subhome.setTextColor(darkgreen);
        }
        else {
            w.subhome.setText(dep.subhome.value);
            w.subhome.setTextColor(orange);
        }
        w.save_subhome.setVisibility(View.GONE);
        w.subhome.setTextIsSelectable(true);
        if (dep.hmi == null) { //only allow taking input in power-off mode.
            if (subhome_key_listener != null) { //restore original keylistener and allow input
                w.subhome.setKeyListener(subhome_key_listener);
                w.subhome.addTextChangedListener(subhome_TextWatcher);
            }
        }
        else {
            w.subhome.setKeyListener(null);
        }
    }

    void refresh_poweron() {
        log("refresh_poweron dep.hmi=" + (dep.hmi != null ? "ON" : "OFF")); //--strip
        disable_poweron_listener = true;
        if (dep.hmi == null) {
            w.poweron.setChecked(false);
        }
        else {
            w.poweron.setChecked(true);
        }
        disable_poweron_listener = false;
    }

    public void refresh() {
        log("refresh"); //--strip
        app.assert_ui_thread();  //--strip
        w.name.setText(dep.name_.value);
        w.save_name.setVisibility(View.GONE);
        refresh_ssid_widgets();
        refresh_subhome_widgets();
        refresh_device_widgets();
        refresh_poweron();
        //refresh_status();
        refresh_online();
//        super.refresh();
//        app.a.refresh_menu();
        log("end refresh"); //--strip
    }



    static int darkgreen = Color.parseColor("#009900");
    static int orange = Color.parseColor("#ffa500");

    TextWatcher subhome_TextWatcher = new TextWatcher() {
            @Override public void afterTextChanged(Editable s) { w.save_subhome.setVisibility(View.VISIBLE); }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    boolean askpin = false;
    boolean resume__poweron0 = false;
    boolean resume__poweron = false;
    String ssid_verified = null;
    boolean disable_poweron_listener = false;
    state_t state = new state_t();
    KeyListener subhome_key_listener = null;
    boolean leds_connected = false;

    us.cash.scr.device_endpoint__conf__widgets w = null;
    protected device_endpoint_t dep = null; //MIM token 'datatype_decl'
}

