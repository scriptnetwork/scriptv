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
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import android.graphics.Color;                                                                 // Color
import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class list_view_t extends RecyclerView implements dbg_mim_t {

    private static void log(final String line) {           //--strip
        us.cash.CFG.log_scr("list_view_t: " + line);       //--strip
    }                                                      //--strip

    public static interface itemclick_listener_t {
        public void on_item__click(View view, int position);
        public void on_item__long_click(View view, int position);
        boolean on_item__highlighted(int pos); //return true: selection remains; false: the item is deselected.
    }

    static class my_listener_t implements RecyclerView.OnItemTouchListener {

        public my_listener_t(Context context, final RecyclerView recyclerView, itemclick_listener_t listener_) {
            listener = listener_;
            gesture_detector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && listener != null) {
                        listener.on_item__long_click(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View child = view.findChildViewUnder(e.getX(), e.getY());
            if (child != null && listener != null && gesture_detector.onTouchEvent(e)) {
                listener.on_item__click(child, view.getChildAdapterPosition(child));
                return false;
            }
            return false;
        }

        @Override public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        }

        @Override public void onRequestDisallowInterceptTouchEvent (boolean disallowIntercept) {
        }

        private itemclick_listener_t listener;
        GestureDetector gesture_detector;
    }

    public list_view_t(Context ctx, int mode, final itemclick_listener_t listener_, View empty_view_) {
        super(ctx);
        listener = listener_;
        empty_view = empty_view_;
        if (mode == 0) {
            setLayoutParams(us.cash.app.a.le.layout_params_vwrap);
            //setId(R.id.message_list);
            LinearLayoutManager layout_manager = new LinearLayoutManager(ctx);
            layout_manager.setOrientation(LinearLayoutManager.VERTICAL);
            setLayoutManager(layout_manager);
        }
        else if (mode == 1) { //chat
            LinearLayoutManager layout_manager = new LinearLayoutManager(ctx);
            layout_manager.setStackFromEnd(true);
            //layout_manager.setSmoothScrollbarEnabled(true);
            setLayoutManager(layout_manager);
            //list_view.setItemViewCacheSize(20);
            //list_view.setDrawingCacheEnabled(true);
            //list_view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
            //list_view.setHasFixedSize(true);
        }
        else if (mode == 2) { //connections, certs_all
            LinearLayoutManager layout_manager = new LinearLayoutManager(ctx);
            addItemDecoration(new DividerItemDecoration(ctx, LinearLayoutManager.VERTICAL));
            setLayoutManager(layout_manager);
            setPadding(0, 0, 0, 0);
        }
        else if (mode == 3) { //selitems
            setLayoutParams(us.cash.app.a.le.layout_params_mm);
            LinearLayoutManager layout_manager = new LinearLayoutManager(ctx);
            addItemDecoration(new DividerItemDecoration(ctx, LinearLayoutManager.VERTICAL));
            setLayoutManager(layout_manager);
        }
        else if (mode == 4) {
            setLayoutParams(us.cash.app.a.le.layout_params_mm);
            LinearLayoutManager layout_manager = new LinearLayoutManager(ctx);
            layout_manager.setStackFromEnd(true);
            setLayoutManager(layout_manager);
            setHasFixedSize(true);
        }
        addOnItemTouchListener(new my_listener_t(ctx, this, listener));
    }

   @Override public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if (empty_view == null) return;
        if (adapter != null) {
            adapter.registerAdapterDataObserver(data_observer);
        }
        data_observer.onChanged();
    }

    private AdapterDataObserver data_observer = new AdapterDataObserver() {
        @Override public void onChanged() {
            Adapter<?> adapter =  getAdapter();
            if (adapter == null) return;
            if (adapter.getItemCount() == 0) {
                empty_view.setVisibility(View.VISIBLE);
                list_view_t.this.setVisibility(View.GONE);
            }
            else {
                empty_view.setVisibility(View.GONE);
                list_view_t.this.setVisibility(View.VISIBLE);
            }
        }
    };

    @Override public String mim_vertex_path() {
        return "core0/core0_6/core0_61/us/gui/activity/scr/list_view";
    }

    public itemclick_listener_t listener = null;
    static View empty_view = null;
}

