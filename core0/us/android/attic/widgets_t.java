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
//import android.app.Activity; 

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
//import androidx.appcompat.widget.Toolbar;                                                      // Toolbar
import android.net.Uri;                                                                        // Uri
import android.view.View;                                                                      // View
import android.view.Window;                                                                    // Window
import android.graphics.Bitmap;
//import androidx.core.app.ActivityCompat;
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

public class widgets_t {

    private static void log(final String line) {         //--strip
        CFG.log_android("widgets: " + line);             //--strip
    }                                                    //--strip

    public widgets_t(Context ctx) {
        density = ctx.getResources().getDisplayMetrics().density;
    }

    public int dp2px(final int dp) {
        return Math.round(dp * density);
    }

    public static int path_to_id__better_use_int(Context ctx, String path) {
        log("path_to_id " + path); //--strip
        path = path.replace("@", "res/");

        String[] parts = path.split("/");
        String pkg = ctx.getPackageName();
        String type = "";
        String name0 = "";
        int i = 0;
        switch (parts.length) {
            case 3:
                //pkg = parts[i++];
                i++;
            case 2:
                type = parts[i++];
                name0 = parts[i];
                break;
            default:
                ko r = new ko("KO 80955 Unable to parse resource");
                log(r.msg + " " + path); //--strip
                assert false; //--strip
                return -1;
        }
        log("resource " + "path '" + path + "'; name0 '" + name0 + "'; type '" + type + "'; pkg '" + pkg + "'"); //--strip 
        String name = name0;
        String ext = "";
        String[] namext = name0.split("\\.");
        log("split length " + namext.length); //--strip 
        if (namext.length == 2) {
            name = namext[0];
            ext = namext[1];
        }
        log("resource " + "; namext.length + " + namext.length + "; name0 '" + name0 + "' ext '" + ext + "'; path '" + path + "'; name '" + name + "'; type '" + type + "'; pkg '" + pkg + "'"); //--strip 
        int id = getContext().getResources().getIdentifier(name, type, pkg);
        log("path_to_id " + path + " returned " + id); //--strip
        return id;
    }

    MenuItem add_menu_item(Menu menu, int gid, int id, final String icores, final String title) {
        return menu.add(gid, id, Menu.NONE, title).setIcon(path_to_id(icores));
    }

    //NavigationView create_view__NavigationView(DrawerLayout drawer, ) {
/*
new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle menu item clicks here
                return true;
            }
        }
*/
       // return o;
    //}

/*
<androidx.drawerlayout.widget.DrawerLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    >
</androidx.drawerlayout.widget.DrawerLayout>
*/

/*
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="@color/colorPrimary"
    android:gravity="bottom"
    android:orientation="vertical"
    android:layout_marginTop="48dp"
    android:padding="8dp"
>

    <androidx.appcompat.widget.LinearLayoutCompat
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="horizontal">

        <androidx.appcompat.widget.AppCompatImageView
            android:layout_width="90dp"
            android:layout_height="90dp"
            android:layout_marginRight="8dp"
            android:layout_gravity="left"
            android:src="@raw/icoapp"/>

        <androidx.appcompat.widget.LinearLayoutCompat
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:gravity="center_vertical"
                android:textSize="20dp"
                android:text="@string/app_name"
                android:textAppearance="@style/Base.TextAppearance.AppCompat.Medium.Inverse" />

            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="@string/brandbusiness"
                android:textAppearance="@style/Base.TextAppearance.AppCompat.Small.Inverse" />

        </androidx.appcompat.widget.LinearLayoutCompat>
    </androidx.appcompat.widget.LinearLayoutCompat>
</LinearLayout>

// Create the parent LinearLayout
LinearLayout parentLayout = w.create_view__LinearLayout_vwrap();
//parentLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//parentLayout.setOrientation(LinearLayout.VERTICAL);
parentLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
parentLayout.setGravity(Gravity.BOTTOM);
parentLayout.setPadding(8, 8, 8, 8);

// Create the first LinearLayoutCompat child
LinearLayout childLayout1 = w.create_view__HLinearLayout();

// Create the ImageView

*/


    public LinearLayout create_view__HLinearLayout() {
        LinearLayout o = new LinearLayout(ctx);
        o.setLayoutParams(layout_params_mm);
        o.setOrientation(LinearLayout.HORIZONTAL);
        return o;
    }

    public LinearLayout create_view__LinearLayout() {
        LinearLayout o = new LinearLayout(ctx);
        o.setLayoutParams(layout_params_mm);
        o.setOrientation(LinearLayout.VERTICAL);
        return o;
    }

    public LinearLayout create_view__LinearLayout_vwrap() {
        LinearLayout o = new LinearLayout(ctx);
        o.setLayoutParams(layout_params_vwrap);
        o.setOrientation(LinearLayout.VERTICAL);
        return o;
    }

    public LinearLayout create_view__HLinearLayout_wrap() {
        LinearLayout o = new LinearLayout(ctx);
        o.setLayoutParams(layout_params_wrap);
        o.setOrientation(LinearLayout.HORIZONTAL);
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

    public LinearLayout create_screen(LinearLayout content) {
    }

    public static class drawer_t extends DrawerLayout {
        // |-drawer
        //   |-content
        //   |-nav
        public drawer_t(Context ctx, LinearLayout content, NavigationView nav) {
            super(ctx);
            setLayoutParams(layout_params_mm);
            //setFitsSystemWindows(true);
            addView(content);
            addView(nav);
        }

        public LinearLayout content;
        public NavigationView nav;
    }

    public static class screen_t extends LinearLayout {
        // |-screen
        // | |-toolbar
        // | |-drawer
        // |   |-content
        // |   |-nav
        public screen_t(Context ctx, drawer_t drawer_, LinearLayout buttons) {
            super(ctx);
            setLayoutParams(layout_params_mm);
            setOrientation(LinearLayout.VERTICAL);
            toolbar = new toolbar_t(ctx);
            addView(toolbar);
            drawer = drawer_; //new drawer_t(screen_content)
            addView(drawer);
        }

        public void configure(drawer_t drawer_new, LinearLayout buttons) {
            assert drawer != null; //--strip
            assert drawer_new != null;
            setLayoutTransition(null); // Suspend layout updates

            removeView(drawer);
            drawer = drawer_new;
            addView(drawer);
            toolbar.configure(buttons);

            setLayoutTransition(layout_transition);
        }


        toolbar_t toolbar;
        drawer_t drawer;

    }

    public static screen_t get_screen(Context ctx, LinearLayout content, NavigationView nav, LinearLayout buttons) {
        if (screen == null) { 
            screen = new screen_t(ctx, content, nav, buttons);
        }
        else {
            screen.configure(content, nav, buttons);
        }
        return screen;
    }
/*
    public void reuse_toolbar(LinearLayout new_parent) {
        if (header == null) { 
            header = create_view__toolbar();
        }
        ViewGroup old_parent = (ViewGroup) toolbar_.getParent();
        if (old_parent != null) {
            old_parent.removeView(header);
        }
        if (new_parent == null) {
            header = null;
            return;
        }
        new_parent.addView(header, 0);
    }
*/

//    public static LinearLayout screen_content = null;
//    public static LinearLayout screen = null;
//    public static toolbar header = null;

    public static screen_t screen = null;
    static final LayoutTransition layout_transition = new LayoutTransition();
    static final ViewGroup.LayoutParams layout_params_mm = new  ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    static final ViewGroup.LayoutParams layout_params_wrap = new  ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    static final ViewGroup.LayoutParams layout_params_vwrap = new  ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    Context ctx;
    float density;

}

