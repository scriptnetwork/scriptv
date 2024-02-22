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
//MIM    mim file: core0/core0_7/us/gui/activity/impl_/mim.h
//MIM  kickoff code hash: WKMB11JbvD3bW4z9xLs1JyK5X8K (change this hash to force a review)
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
import us.ko;                                                                                  // ko
import android.view.LayoutInflater;                                                            // LayoutInflater
import android.view.ViewGroup.LayoutParams;                                                    // LayoutParams
import android.widget.LinearLayout;                                                            // LinearLayout
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


public abstract class activity1 extends activity0 implements datagram_dispatcher_t.handler_t {

    private static void log(final String line) {                              //--strip
        CFG.log_android("activity1: " + line);          //--strip
    }                                                                         //--strip

    public activity1() {
        super();
    }

    //--------------lifecycle-------------------------------------------------------
    @Override protected void controller__on_create(Bundle saved_state) {
        super.controller__on_create(saved_state);
        log("controller__on_create"); //--strip
    }

    @Override protected void controller__on_pause() {
        log("controller__on_pause"); //--strip
        super.controller__on_pause();
    }

    @Override protected void controller__on_resume() {
        super.controller__on_resume();
        log("controller__on_resume"); //--strip
    }

    @Override protected void controller__on_destroy() {
        log("controller__on_destroy"); //--strip
        super.controller__on_destroy();
    }
    //-/------------lifecycle-------------------------------------------------------

    void debug_asserts() {                               //--strip
        assert a.device_endpoints.need_save == false;    //--strip
    }                                                    //--strip

    @Override public void on_push(final hash_t target_tid, final uint16_t code, final byte[] payload) {
        log("on_push"); //--strip
    }

    public void stop_hmi(final boolean save_) {
        a.HMI_power_off(a.device_endpoints.cur_index.value, save_);
    }

    public void start_hmi(int conf_index) {
        a.HMI_power_on(conf_index, new pin_t(0));
    }

    //@Override public void on_conf() {
    //    a.launch_device_endpoints__conf();
    //}

    @Override public void report_ko(final ko r) {
        app.assert_ui_thread(); //--strip
        final String msg = a.has_hmi() ? a.hmi().rewrite(r) : r.msg;
        report_ko(msg);
    }

    @Override public void report_ko__worker(final ko r) {
        app.assert_worker_thread(); //--strip
        final String msg = a.has_hmi() ? a.hmi().rewrite(r) : r.msg;
        report_ko__worker(msg);
    }

    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        log("onActivityResult " + requestCode + " " + resultCode); //--strip
        if (requestCode == INSTALL_PACKAGES_REQUESTCODE) {
            if (resultCode == RESULT_CANCELED) {
                toast(a.r_(R.string.installationcacnelledbyuser));
                return;
            }
            if (resultCode != RESULT_OK) {
                log("not ok"); //--strip
                return;
            }
            if (a.has_hmi()) { // && a.hmi().sw_updates != null) {
                if (a.getPackageManager().canRequestPackageInstalls()) {
                    if (a.hmi().sw_updates != null) {
                        a.hmi().sw_updates.do_inst_local(this);
                    }
                }
            }
            else {
                log("sw updates not available"); //--strip
            }
            return;
        }
        a.onActivityResult(requestCode, resultCode, data);
    }

}

