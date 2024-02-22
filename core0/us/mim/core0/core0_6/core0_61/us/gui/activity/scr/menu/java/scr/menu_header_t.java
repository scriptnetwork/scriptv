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
import us.cash.R;
import androidx.core.content.ContextCompat;
import android.view.Gravity;
import android.widget.ImageView;
import android.util.TypedValue;


public final class menu_header_t extends LinearLayout implements dbg_mim_t {

    @Override public String mim_vertex_path() {
        return "core0/core0_6/us/gui/activity/scr/menu/header";
    }

    public menu_header_t(Context ctx, int icores, final String headline, final String tailline, View.OnClickListener listener) {
        super(ctx);
        assert ctx != null; //--strip
        setLayoutParams(us.cash.app.a.le.layout_params_mm);
        setOrientation(LinearLayout.HORIZONTAL);
        //parentLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //setBackgroundColor(ContextCompat.getColor(ctx, R.color.colorPrimary));
        //setGravity(Gravity.BOTTOM);
        setPadding(us.cash.app.a.le.border, us.cash.app.a.le.border, us.cash.app.a.le.border, us.cash.app.a.le.border);
        canvas_t left = new canvas_t(ctx, 4, 1); {
            button_t blogo = new button_t(ctx, icores, listener); {
                final int sz = us.cash.app.a.le.dp2px(90);
                LayoutParams layout = new LinearLayout.LayoutParams(sz, sz);
                layout.setMargins(0, 0, us.cash.app.a.le.dp2px(8), 0);
                blogo.setLayoutParams(layout);
                blogo.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
            left.addView(blogo);
        }
        canvas_t right = new canvas_t(ctx, 1, 1); {
            head = new text_view_t(ctx, 1); {
                head.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                head.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
                head.setText(headline);
                //head.setAllCaps(true);
                //appname.setTextAppearance(ctx, us.cash.R.style.Base_TextAppearance_AppCompat_Medium_Inverse);
            }
            tail = new text_view_t(ctx, 1); {
                tail.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                tail.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
                tail.setText(tailline);
                //tail.setAllCaps(true);
                //appsub.setTextAppearance(ctx, us.cash.R.style.Base_TextAppearance_AppCompat_Small_Inverse);
            }
            right.addView(head);
            right.addView(tail);
        }
        addView(left);
        addView(right);
    }

    public text_view_t head;
    public text_view_t tail;
}

