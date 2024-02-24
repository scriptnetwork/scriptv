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
import android.view.Menu;                                                                      // Menu
import android.view.MenuItem;                                                                  // MenuItem
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
import com.google.android.material.navigation.NavigationView;                                  // NavigationView
import android.view.Menu;                                                                      // Menu
import android.view.MenuItem;
import android.widget.TextView;
import android.animation.LayoutTransition;
import android.view.Gravity;
import androidx.core.view.GravityCompat;
// |-drawer
//   |-content
//   |-menu
public class drawer_t extends DrawerLayout implements dbg_mim_t {

    @Override public String mim_vertex_path() {
        return "core0/core0_6/us/gui/activity/scr/drawer";
    }

    private static void log(final String line) {         //--strip
        us.cash.CFG.log_scr("drawer_t: " + line);             //--strip
    }                                                    //--strip

    public drawer_t(Context ctx, View content_) {
        super(ctx);
        log("XXR drawer_t constructor " + this); //--strip
        DrawerLayout.LayoutParams params2 = new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params2.gravity = Gravity.NO_GRAVITY;
        setLayoutParams(params2);
        content = content_;
        us.cash.app.a.le.detach_from_parent(content);
        addView(content);
    }

    public void clear_cache() {
    }

    public void configure(menu_t.listener listener, menu_t menu_) {
        log("XXR configure started with childcount " + getChildCount() + " " + this); //--strip
        if (menu_ == menu) {
            log("XXR same menu " + getChildCount()); //--strip
            if (getChildCount() == 2) {
                log("configure ended with childcount " + getChildCount() + " " + this); //--strip
                menu.set_listener(listener);
                return;
            }
        }
        boolean o = false;
        if (getChildCount() > 1) {
            log("XXR remove view 1 "); //--strip
            o = is_open();
            removeViewAt(1);
        }
        menu = menu_;
        assert menu != null; //--strip
        menu.set_listener(listener);

        log("XXR menu " + menu + " ==> " + menu_); //--strip
        us.cash.app.a.le.detach_from_parent(menu);
        DrawerLayout.LayoutParams params1 = new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params1.gravity = GravityCompat.START;
        assert menu != null;
        addView(menu, params1);

        int widthMeasureSpec = MeasureSpec.makeMeasureSpec(getResources().getDisplayMetrics().widthPixels, MeasureSpec.EXACTLY);
        int heightMeasureSpec = MeasureSpec.makeMeasureSpec(getResources().getDisplayMetrics().heightPixels, MeasureSpec.EXACTLY);
        measure(widthMeasureSpec, heightMeasureSpec);
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
        log("XXR configure ended with childcount " + getChildCount() + " " + this); //--strip
        if (o) {
            open();
        }
    }

    public void open() {
        if (is_open()) return;
        openDrawer(GravityCompat.START);
    }

    public boolean is_open() {
        return isDrawerOpen(GravityCompat.START);
    }

    public void toggle() {
        if (is_open()) {
            close();
        }
        else {
            open();
        }
    }

    public void close() {
        closeDrawer(GravityCompat.START);
    }

    public View content;
    public menu_t menu;
}


