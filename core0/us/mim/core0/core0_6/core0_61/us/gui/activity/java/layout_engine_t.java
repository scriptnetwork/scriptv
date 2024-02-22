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
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Stack;
import java.util.ArrayList;
import us.pair;
import static us.stdint.*;
import java.util.Collections;
import android.content.res.Configuration;

public class layout_engine_t {

    private static void log(final String line) {         //--strip
        CFG.log_scr("layout_engine_t: " + line);         //--strip
    }                                                    //--strip

    static int icount = 0; //--strip

    public layout_engine_t(Context ctx_) {
        ctx = ctx_;
        density = ctx.getResources().getDisplayMetrics().density;
//        border = dp2px(4);
        border = 8;
        border_button = border / 2;
        ++icount;                               //--strip
        if (icount != 1) {                      //--strip
            log("layout_engine_t singleton");   //--strip
            assert false;                       //--strip
        }                                       //--strip
    }

    public int dp2px(final int dp) {
        return Math.round(dp * density);
    }

    public LinearLayout create_view__LinearLayout_vwrap() {
        LinearLayout o = new LinearLayout(ctx);
        o.setLayoutParams(layout_params_vwrap);
        o.setOrientation(LinearLayout.VERTICAL);
        return o;
    }

    public TextView create_TextView_vwrap() {
        TextView o = new TextView(ctx);
        //o.setId(R.id.apptitle);
        o.setLayoutParams(layout_params_vwrap);
        return o;
    }

    public TextView create_TextView() {
        TextView o = new TextView(ctx);
        //o.setId(R.id.apptitle);
        o.setLayoutParams(layout_params_wrap);
        return o;
    }

    public void detach_from_parent(View child) {
        ViewGroup p = (ViewGroup) child.getParent();
        if (p != null) {
            p.removeView(child);
        }
    }

    ArrayList<pair<View, uint16_t>> traverse__depth_first() {
        ArrayList<pair<View, uint16_t>> visitpath = new ArrayList<pair<View, uint16_t>>();
        visitpath.ensureCapacity(256);
        Stack<pair<View, uint16_t>> line = new Stack<pair<View, uint16_t>>();
        line.push(new pair<View, uint16_t>(screen, new uint16_t(0)));
        while (!line.empty()) {
            pair<View, uint16_t> o = line.pop();
            visitpath.add(o);
            if (o.first instanceof ViewGroup) {
                ViewGroup g = (ViewGroup) o.first;
                for (int i = 0; i < g.getChildCount(); i++) {
                    final View child = g.getChildAt(i);
                    line.push(new pair<View, uint16_t>(child, new uint16_t(o.second.value + 1)));
                }
            }
        }
        return visitpath;
    }

    public String mim_dump() {                                                          //--strip
        try {                                                                           //--strip
            ByteArrayOutputStream bos = new ByteArrayOutputStream();                    //--strip
            PrintStream os = new PrintStream(bos);                                      //--strip
            ArrayList<pair<View, uint16_t>> vs = traverse__depth_first();               //--strip
            for (pair<View, uint16_t> v: vs) {                                          //--strip
                for (int i = 0; i < v.second.value; ++i) {                              //--strip
                    os.print("  ");                                                     //--strip
                }                                                                       //--strip
                if (v.first instanceof us.cash.scr.dbg_mim_t) {                         //--strip
                    os.println(((us.cash.scr.dbg_mim_t)v.first).mim_vertex_path());     //--strip
                }                                                                       //--strip
                else {                                                                  //--strip
                    os.println("unknown");                                              //--strip
                }                                                                       //--strip
            }                                                                           //--strip
            return bos.toString();                                                      //--strip
        }                                                                               //--strip
        catch(Exception e) {                                                            //--strip
            return "";                                                                  //--strip
        }                                                                               //--strip
    }                                                                                   //--strip

    public int resolve_resid(int resid) {
        //log("VVZZX resolve_resid = " + resid + " dm=" + us.cash.app.a._dark_mode); //--strip
        if (!us.cash.app.a._dark_mode) return resid;
        String nm = ctx.getResources().getResourceEntryName(resid);
        nm = nm + "__dm";
        int dmresid = ctx.getResources().getIdentifier(nm, "raw", ctx.getPackageName());
        if (dmresid != 0) {
            return dmresid;
        }
        log("WA 66859 Dark mode icon not found. Using LightMode version. " + nm); //--strip 
        return resid;
    }

    public void clear_cache() {
        if (screen == null) return;
//        screen = null;
        screen.clear_cache();
    }

    static int screen_icount = 0; //--strip

    public us.cash.scr.screen_t get_screen(activity0 ac) {
        if (screen == null) {
            log("configuring new screen"); //--strip
            screen = new us.cash.scr.screen_t(ac);
            ++screen_icount;                                //--strip
            if (screen_icount != 1) {                       //--strip
                log("screen singleton");                    //--strip
                assert false;                               //--strip
            }                                               //--strip
        }
        log("configuring existing screen"); //--strip
        detach_from_parent(screen);
        screen.configure(ac);
        return screen;
    }

    public static us.cash.scr.screen_t screen = null;
    public static final LayoutTransition layout_transition = new LayoutTransition();
    public static final LinearLayout.LayoutParams layout_params_mm = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    public static final LinearLayout.LayoutParams layout_params_wrap = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    public static final LinearLayout.LayoutParams layout_params_vwrap = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    public static final LinearLayout.LayoutParams layout_params_hwrap = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
    public static final LinearLayout.LayoutParams layout_params_hexpand_vwrap = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1); //TextInputLayout.LayoutParams

    public static final LinearLayout.LayoutParams layout_params_hmatch_vexpand = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
    public static final LinearLayout.LayoutParams layout_params_hexpand_vmatch = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
    public static final LinearLayout.LayoutParams layout_params_hwrap_vexpand = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0, 1);

    public static int lists_text_size = 16;
    public static int lists_text_size_header = 18;

    Context ctx;
    float density;
    public int border;
    public int border_button;
}
