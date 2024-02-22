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

public abstract class list_view__adapter_t extends RecyclerView.Adapter<list_view__itemview_t> {

    private static void log(final String s) {                     //--strip
        us.cash.CFG.log_scr("list_view__adapter_t: " + s);        //--strip
    }                                                             //--strip

    public abstract list_view__itemview_t create_itemview(Context ctx);
    public abstract int get_item_count();

    @Override final public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        log("onAttachedToRecyclerView"); //--strip
        super.onAttachedToRecyclerView(recyclerView);
        list_view = (list_view_t) recyclerView;
    }

    @Override final public list_view__itemview_t onCreateViewHolder(ViewGroup parent, int view_type) {
        return create_itemview(parent.getContext());
    }

    @Override final public void onBindViewHolder(@NonNull list_view__itemview_t holder, int position) { //ViewHolder
        log("onBindViewHolder pos " + position); //--strip
        holder.bind(position);
    }

    @Override final public int getItemCount() {
        return get_item_count();
    }

    public void highlight(int pos, boolean b) {
        log("highlight " + pos + " " + b); //--strip
        if (b) {
            assert pos != -1; //--strip
            if (highlight_pos == pos) return;
            int prev = highlight_pos;
            highlight_pos = pos;
            notifyItemChanged(prev);
            notifyItemChanged(highlight_pos);
        }
        else {
            if (highlight_pos == -1) return;
            int prev = highlight_pos;
            highlight_pos = -1;
            notifyItemChanged(prev);
        }
    }

    void schel_highlight(final int pos, int t, final boolean b) {
        log("schel_highlight. in " + t + "ms goes " + b); //--strip
        list_view__adapter_t self = this;
        timer.schedule(new TimerTask() {
          @Override public void run() {
            us.cash.app.a.active_ac.runOnUiThread(new Runnable() {
                @Override public void run() {
                    self.highlight(pos, b);
                }
            });
          }
        }, t);
    }

    public boolean flash(final int pos) {
        log("flash pos " + pos + " highlight_pos=" + highlight_pos); //--strip
        us.cash.app.a.assert_ui_thread(); //--strip
        if (pos == -1) return false;
        int prev = highlight_pos;

//        list_view.scrollToPosition(pos);
        list_view.smoothScrollToPosition(pos);

        int t = 300;
        int dur = 100;
        for (int n = 0; n < 5; ++n) {
            schel_highlight(pos, t, true);
            t += dur;
            schel_highlight(pos, t, false);
            t += dur;
        }
        schel_highlight(pos, t, true);
        t += 400;
        schel_highlight(pos, t, false);
        if (prev != -1) {
            t += 200;
            schel_highlight(prev, t, true);
        }
        return true;
    }

    public boolean scrolldown_flash() {
        us.cash.app.a.assert_ui_thread(); //--strip
        final int pos = getItemCount() - 1;
        flash(pos);
        return true;
    }

    public boolean scrolldown() {
        us.cash.app.a.assert_ui_thread(); //--strip
        final int pos = getItemCount() - 1;
        if (pos == -1) return false;
        list_view.smoothScrollToPosition(pos);
        return true;
    }

    public void toggle_sel(int pos) {
        int prev = highlight_pos;
        highlight_pos = pos;
        if (prev == pos) {
            notifyItemChanged(prev);
            if (list_view.listener != null) {
                if (!list_view.listener.on_item__highlighted(pos)) {
                    highlight_pos = -1;
                }
            }
            else {
                highlight_pos = -1;
            }
            return;
        }
        if (prev != -1) {
            notifyItemChanged(prev);
        }
        if (pos != -1) {
            notifyItemChanged(pos);
            if (dbl_click_hint) {
                us.cash.app.a.toast(dbl_click_hint_msg);
                dbl_click_hint = false;
            }
        }
    }

    static Timer timer = new Timer();
    static String dbl_click_hint_msg = "Touch it again.";
    static boolean dbl_click_hint = true;

    public list_view_t list_view;
    public int highlight_pos = -1;
}

