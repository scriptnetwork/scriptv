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
import android.view.Gravity;

public class canvas_t extends LinearLayout implements dbg_mim_t {

    @Override public String mim_vertex_path() {
        return "core0/core0_6/us/gui/activity/scr/canvas";
    }
    
    public canvas_t(Context ctx) {
        super(ctx);
        LinearLayout.LayoutParams layout;
        layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layout.setMargins(us.cash.app.a.le.border, us.cash.app.a.le.border, us.cash.app.a.le.border, us.cash.app.a.le.border);
        setLayoutParams(layout);
        setOrientation(LinearLayout.VERTICAL);
        init();
    }

    public canvas_t(Context ctx, int margin) {
        super(ctx);
        LinearLayout.LayoutParams layout;
        if (margin != 0) {
            layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            margin = us.cash.app.a.le.dp2px(margin);
            layout.setMargins(margin, margin, margin, margin);
        }
        else {
            layout = us.cash.app.a.le.layout_params_mm;
        }
        setLayoutParams(layout);
        setOrientation(LinearLayout.VERTICAL);
        init();
    }

    static LinearLayout.LayoutParams layout_params_6 = null;
    static LinearLayout.LayoutParams layout_params_7 = null;
    //static LinearLayout.LayoutParams layout_params_8 = null;
    public static final int H = 0;
    public static final int V = 1;


    public canvas_t(Context ctx, int code, int intarg) { //expands horizontally. intarg: pair(0): H; odd(1): V
        super(ctx);
        if (code == 0) {
            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
            setLayoutParams(layout);
        }
        else if (code == 1) {
            setLayoutParams(us.cash.app.a.le.layout_params_vwrap);
        }
        else if (code == 2) {
            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setMargins(0, us.cash.app.a.le.border, 0, 0); ////left, top, right, bottom
            setLayoutParams(layout);
        }
        else if (code == 3) {
            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setMargins(0, 0, us.cash.app.a.le.border, 0); ////left, top, right, bottom
            setLayoutParams(layout);
        }
        else if (code == 4) {
            setLayoutParams(us.cash.app.a.le.layout_params_wrap);
        }
        else if (code == 5) {
            setLayoutParams(us.cash.app.a.le.layout_params_hwrap);
        }
        else if (code == 6) {
            if (layout_params_6 == null) {
                layout_params_6 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                layout_params_6.setMargins(us.cash.app.a.le.border, us.cash.app.a.le.border, us.cash.app.a.le.border, us.cash.app.a.le.border);
            }
            setLayoutParams(layout_params_6);
//            setLayoutParams(us.cash.app.a.le.layout_params_mm);
        }
        else if (code == 7) {
            setLayoutParams(us.cash.app.a.le.layout_params_vwrap);
/*
            if (layout_params_7 == null) {
                layout_params_7 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //layout_params_7.setMargins(us.cash.app.a.le.border, 0, us.cash.app.a.le.border, 0); //LTRB
            }
            setLayoutParams(layout_params_7);
//            setLayoutParams(us.cash.app.a.le.layout_params_mm);
*/
        }
        else if (code == 8) {
            setLayoutParams(us.cash.app.a.le.layout_params_wrap);
/*
            if (layout_params_8 == null) {
                layout_params_8 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //layout_params_8.setMargins(0, 0, 0, us.cash.app.a.le.border); //LTRB
            }
            setLayoutParams(layout_params_8);
//            setLayoutParams(us.cash.app.a.le.layout_params_mm);
*/
        }
        else if (code == 9) {
            //LayoutParams layout = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            //setLayoutParams(layout);
        }
        else if (code == 10) {
            LinearLayout.LayoutParams layout;
            layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layout.setMargins(us.cash.app.a.le.border, us.cash.app.a.le.border, us.cash.app.a.le.border, us.cash.app.a.le.border);
            layout.gravity = Gravity.CENTER;
            setLayoutParams(layout);
        }
        else if (code == 11) {
            setLayoutParams(us.cash.app.a.le.layout_params_mm);
        }
        else if (code == 12) {
            LinearLayout.LayoutParams layout;
            layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.gravity = Gravity.TOP;
            setLayoutParams(layout);
        }
        else if (code == 13) {
            LinearLayout.LayoutParams layout;
            layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.gravity = Gravity.TOP;
            setLayoutParams(layout);
        }
        else if (code == 14) {
            LinearLayout.LayoutParams layout;
            layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.gravity = Gravity.TOP;
            setLayoutParams(layout);
        }
        else if (code == 15) {
            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setMargins(us.cash.app.a.le.border, 0, 0, 0); ////left, top, right, bottom
            setLayoutParams(layout);
        }
        else if (code == 16) {
            setLayoutParams(us.cash.app.a.le.layout_params_hexpand_vwrap);
        }
        else if (code == 17) {
            setLayoutParams(us.cash.app.a.le.layout_params_hmatch_vexpand);
        }
        else if (code == 18) {
            setLayoutParams(us.cash.app.a.le.layout_params_hexpand_vwrap);
            setGravity(Gravity.END);  
        }
        else {               //--strip
            assert false;    //--strip
        }                    //--strip
        //HORIZONTAL: 0
        //VERTICAL: 1
        setOrientation((intarg & 1) == 0 ? LinearLayout.HORIZONTAL : LinearLayout.VERTICAL);
        init();
    }

    void init() {
        setClickable(true);
    }

    public void hide() {
        setVisibility(View.GONE);
    }
}

