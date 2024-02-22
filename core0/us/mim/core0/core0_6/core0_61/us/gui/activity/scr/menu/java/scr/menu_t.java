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
import androidx.core.content.ContextCompat;
import us.cash.R;
import android.graphics.drawable.Drawable;
import androidx.appcompat.view.ContextThemeWrapper;
import java.util.ArrayList;
import java.util.Collections;

public abstract class menu_t extends NavigationView implements dbg_mim_t {

    @Override public String mim_vertex_path() {
        return "core0/core0_6/us/gui/activity/scr/menu";
    }

    private static void log(final String line) {                 //--strip
        us.cash.CFG.log_scr("menu_t: " + line);                  //--strip
    }                                                            //--strip

    public interface listener {
        boolean on_menu(int resid); //return false if ignored
    }

    public static class itemspec_t {
        int id;
        String title;
    }

    public static class group_t extends ArrayList<itemspec_t> {

        public group_t() {
            name = "";
        }

        public group_t(String name_) {
            name = name_;
        }

        public void add(int id, final String title) {
            itemspec_t o = new itemspec_t();
            o.id = id;
            o.title = title;
            add(o);
        }
/*
        public void add(int pos, int id, final String title) {
            itemspec_t o = new itemspec_t();
            o.id = id;
            o.title = title;
            add(pos, o);
        }
*/
        String name;
    }

    public static class menuspec_t extends ArrayList<group_t> {

        public group_t find(final String name) {
            for (group_t g: this) {
                if (g.name.equals(name)) return g;
            }
            group_t g = new group_t(name);
            add(g);
            return g;
        }

    }

    public abstract menuspec_t create_spec();

    public menu_t(Context ctx, menu_header_t header_) {
        super(new ContextThemeWrapper(ctx, R.style.AppTheme));
        init(ctx, header_, create_spec());
    }

    void init(Context ctx, menu_header_t header_, menuspec_t spec) {
        setLayoutParams(us.cash.layout_engine_t.layout_params_wrap);
        setItemIconTintList(null);
        header = header_;
        log("reuse_header " + header); //--strip
        addHeaderView(header);
        int gi = 0;
        for (group_t g: spec) {
            ArrayList<itemspec_t> rg = new ArrayList<itemspec_t>(g);
            Collections.reverse(rg);
            for (itemspec_t i: rg) {
                add_menu_item(gi, i.id, i.title);
            }
            ++gi;
        }
    }

    private void add_menu_item(int gid, int id_icores, final String title) {
        Menu menu = getMenu();
        MenuItem item = menu.add(gid, id_icores, Menu.NONE, title);
        item.setIcon(us.cash.app.a.le.resolve_resid(id_icores));
        item.setIconTintList(null);
    }

    public void set_listener(listener l_) {
        log("set_listener " + l_); //--strip
        l = l_;
        setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                log("onNavigationItemSelected "); //--strip
                boolean processed = l.on_menu(item.getItemId());
                if (!processed) {
                    us.cash.app.a.on_menu(item.getItemId());
                }
                assert us.cash.app.a.active_ac != null; //--strip
                us.cash.app.a.close_drawer();
                return true;
            }
        });
    }

    public listener l;
    public menu_header_t header = null;
    protected menuspec_t menuspec;
}

