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
import java.io.PrintStream;
    // |-screen
    // | |-toolbar
    // | |-drawer
    // |   |-content
    // |   |-menu

public class screen_t extends LinearLayout implements dbg_mim_t {

    private static void log(final String line) {              //--strip
        us.cash.CFG.log_scr("screen_t: " + line);             //--strip
    }                                                         //--strip

    @Override public String mim_vertex_path() {
        return "core0/core0_6/us/gui/activity/scr/screen";
    }
    
    public screen_t(us.cash.activity0 ac) {
        super(ac);
        assert ac != null;  //--strip
        setPadding(0, 0, 0, 0);
        setLayoutParams(us.cash.layout_engine_t.layout_params_mm);
        setOrientation(LinearLayout.VERTICAL);
    }

    public void clear_cache() {
        removeAllViews();
        drawer = null;
        toolbar = null;
    }


    public void configure(us.cash.activity0 ac) {
        //new Exception("").printStackTrace(); //--strip
        log("VVZZX screen configure"); //--strip
        
        drawer_t drawer_ = ac.get_drawer();
        assert drawer_ != null; //--strip

        if (getChildCount() == 2) {
            if (drawer_ != drawer) {
                removeViewAt(1);
                drawer = drawer_;
                us.cash.app.a.le.detach_from_parent(drawer);
                log("XXP using drawer " + drawer); //--strip
                addView(drawer);
            }
        }
        else if (getChildCount() == 0) {
            assert drawer == null; //--strip
            drawer = drawer_;
            us.cash.app.a.le.detach_from_parent(drawer);

            assert toolbar == null;  //--strip
            toolbar = new toolbar_t(ac, drawer.menu.l);
            addView(toolbar); //#0

            log("XXP using drawer " + drawer); //--strip
            addView(drawer);
        }
        else {
            assert false; //--strip
        }
        drawer.menu.set_listener(ac);
        toolbar.configure(drawer.menu);
    }

    public void dump(PrintStream os) {
        os.println("MIM screen dump:");
        
    }

    public toolbar_t toolbar = null;
    public drawer_t drawer = null;

}

