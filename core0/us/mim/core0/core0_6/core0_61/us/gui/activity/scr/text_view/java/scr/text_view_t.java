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
import android.widget.TextView;
import android.view.Gravity;
import android.util.TypedValue;                                                                // TypedValue

public class text_view_t extends TextView implements dbg_mim_t {

    @Override public String mim_vertex_path() {
        return "core0/core0_6/us/gui/activity/scr/text_view";
    }

    public text_view_t(Context ctx, int x) {
        super(ctx);
        if (x == 0) {
            setLayoutParams(us.cash.app.a.le.layout_params_wrap);
        }
        else if (x == 1) { //orma connections, cers_all, accounts
            setLayoutParams(us.cash.app.a.le.layout_params_vwrap);
        }
        else if (x == 2) { //connections
            setLayoutParams(us.cash.app.a.le.layout_params_hmatch_vexpand);
        }
        else if (x == 3) { //connection_settings 
            LinearLayout.LayoutParams layout = new  LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layout.setMargins(0, us.cash.app.a.le.border, 0, 0); ////left, top, right, bottom
            setLayoutParams(layout);
        }
        else if (x == 4) { //orma about
            setLayoutParams(us.cash.app.a.le.layout_params_vwrap);
            setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        }
        else if (x == 5) { //orma device_endpoints__conf , doc_viewer
            setLayoutParams(us.cash.app.a.le.layout_params_hexpand_vwrap);
        }
        else if (x == 6) { //orma active trades
            setLayoutParams(us.cash.app.a.le.layout_params_hexpand_vwrap);
            setGravity(Gravity.END);
        }
        else if (x == 7) { //orma active trades
            setLayoutParams(us.cash.app.a.le.layout_params_hexpand_vwrap);
            setGravity(Gravity.START);
        }
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, us.cash.app.a.le.lists_text_size);
    }

    public void set_text(final String txt) {
        setText(txt);
    }

    public void set_text(int resid) {
        setText(getContext().getString(resid));
    }

}

