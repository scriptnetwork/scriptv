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
//MIM  ******************************************************************************
//MIM  EDITABLE FILE. Changes on this file will NOT be overwritten by MIM.
//MIM  The first version of this file was produced by MIM. You can edit it afterwards.
//MIM
//MIM  Source:
//MIM        file: core0/core0_6/core0_61/us/gui/activity/scr/list_view/view/list/java/scr/[classname]__itemview__widgets.java
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/scr/list_view/view/list/mim.h
//MIM  Params:
//MIM    'classname': 'device_endpoints__conf__list_view'
//MIM    'include': ''
//MIM    'itemico': 'R.raw.gear' @ core0/core0_7/us/gui/device_endpoints/mim.h
//MIM  kickoff code hash: kKPdSZJeFRQVtVmHJFZpcuNaQgE (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
import android.content.Context;                                                                // Context
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;
import android.util.TypedValue;                                                                // TypedValue
import us.cash.R;
import us.cash.CFG;
import android.view.Gravity;
import android.widget.ImageView;
import android.view.ViewGroup.LayoutParams;                                                    // LayoutParams
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import static us.gov.crypto.ripemd160.hash_t;
import us.pair;
import us.string;
import androidx.appcompat.app.ActionBarDrawerToggle;                                           // ActionBarDrawerToggle
import androidx.core.app.ActivityCompat;                                                       // ActivityCompat
import android.widget.AdapterView;                                                             // AdapterView
import androidx.appcompat.app.AlertDialog;                                                     // AlertDialog
import android.widget.ArrayAdapter;                                                            // ArrayAdapter
import us.wallet.trader.bookmarks_t;                                                           // bookmarks_t
import us.wallet.trader.bookmark_t;                                                            // bookmark_t
import android.os.Build;                                                                       // Build
import android.os.Bundle;                                                                      // Bundle
import android.graphics.Color;                                                                 // Color
import android.widget.CompoundButton;                                                          // CompoundButton
import android.content.res.Configuration;                                                      // Configuration
import androidx.core.content.ContextCompat;                                                    // ContextCompat
import android.content.Context;                                                                // Context
import us.gov.socket.datagram;                                                                 // datagram
import android.content.DialogInterface;                                                        // DialogInterface
import android.text.method.DigitsKeyListener;                                                  // DigitsKeyListener
import android.graphics.drawable.Drawable;                                                     // Drawable
import us.gov.crypto.ec;                                                                       // ec
import android.text.Editable;                                                                  // Editable
import android.widget.EditText;                                                                // EditText
import android.graphics.drawable.GradientDrawable;                                             // GradientDrawable
import androidx.core.view.GravityCompat;                                                       // GravityCompat
import static us.gov.crypto.ripemd160.hash_t;                                                  // hash_t
import static android.Manifest.permission.*;                                                   // *
import static us.gov.id.types.*;                                                               // *
import static us.gov.io.types.*;                                                               // *
import static us.gov.socket.types.*;                                                           // *
import static us.ko.*;                                                                         // *
import static us.stdint.*;                                                                     // *
import static us.tuple.*;                                                                      // *
import android.view.inputmethod.InputMethodManager;                                            // InputMethodManager
import android.text.InputType;                                                                 // InputType
import android.content.Intent;                                                                 // Intent
import java.io.IOException;                                                                    // IOException
import us.wallet.engine.ip4_endpoint_t;                                                        // ip4_endpoint_t
import org.json.JSONArray;                                                                     // JSONArray
import org.json.JSONException;                                                                 // JSONException
import org.json.JSONObject;                                                                    // JSONObject
import android.text.method.KeyListener;                                                        // KeyListener
import java.security.KeyPair;                                                                  // KeyPair
import us.ko;                                                                                  // ko
import android.view.LayoutInflater;                                                            // LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat;                                           // LinearLayoutCompat
import android.widget.LinearLayout;                                                            // LinearLayout
import java.util.Locale;                                                                       // Locale
import android.Manifest;                                                                       // Manifest
import java.util.Map;                                                                          // Map
import com.google.android.material.button.MaterialButton;                                      // MaterialButton
import android.view.Menu;                                                                      // Menu
import android.view.MenuItem;                                                                  // MenuItem
import android.view.View.OnFocusChangeListener;                                                // OnFocusChangeListener
import android.content.pm.PackageManager;                                                      // PackageManager
import us.pair;                                                                                // pair
import us.wallet.protocol;                                                                     // protocol
import android.widget.RelativeLayout;                                                          // RelativeLayout
import androidx.annotation.RequiresApi;                                                        // RequiresApi
import android.content.res.Resources;                                                          // Resources
import android.text.SpannableStringBuilder;                                                    // SpannableStringBuilder
import android.widget.Spinner;                                                                 // Spinner
import android.widget.Space;                                                                 // Spinner
import java.lang.StringBuilder;                                                                // StringBuilder
import android.annotation.SuppressLint;                                                        // SuppressLint
import android.widget.Switch;                                                                  // Switch
import com.google.android.material.textfield.TextInputEditText;                                // TextInputEditText
import com.google.android.material.textfield.TextInputLayout;                                  // TextInputLayout
import android.widget.TextView;                                                                // TextView
import android.text.TextWatcher;                                                               // TextWatcher
import java.util.Timer;                                                                        // Timer
import java.util.TimerTask;                                                                    // TimerTask
import android.widget.Toast;                                                                   // Toast
import android.media.ToneGenerator;                                                            // ToneGenerator
import android.util.TypedValue;                                                                // TypedValue
import us.gov.io.types.vector_tuple_hash_host_port;                                            // vector_tuple_hash_host_port
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import android.widget.ImageView;
import android.view.Gravity;

public class device_endpoints__conf__list_view__itemview__widgets extends list_view__itemview__widgets {

    private static void log(final String s) {                             //--strip
        CFG.log_scr("device_endpoints__conf__list_view__itemview__widgets: " + s);            //--strip
    }                                                                     //--strip


    public static interface listener_t {
        void on_poweron__changed(int pos, boolean ison);
        void on_conf__click(int pos);
        void on_menu__click(int pos);
        void on_trash__click(int pos);
    }

    public device_endpoints__conf__list_view__itemview__widgets(listener_t listener_) {
        listener = listener_;
    }

    //    +-----+------------------------------+-----+
    //    | ico | head                         | btn |
    //    |     |------------------------------+-----+
    //    |     | tail                               |
    //    +-----+------------------------------------+
    @Override public ViewGroup create_tree(Context ctx) {
        super.create_tree(ctx); {
            //papyrus.setBackgroundColor(Color.parseColor("#ff0000"));
            //android:background="#FFFFFF"
            label = new text_view_t(ctx, 1); {
                label.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
            }
            ssid = new text_view_t(ctx, 1); {
                ssid.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            }
            address = new text_view_t(ctx, 1); {
                address.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            }
            status = new text_view_t(ctx, 1); {
                status.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            }
            canvas_t pan = new canvas_t(ctx, 1, 0); { //android:layout_width="match_parent" android:layout_height="wrap_content" android:orientation="horizontal"
                pan.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL); //android:gravity="right|center_vertical"
                int bottom_margin = us.cash.app.a.le.dp2px(10);
                pan.setPadding(0, 0, 0, bottom_margin); // Set bottom margin using padding; android:layout_marginBottom="10dp"

                conn_ico = new button_t(ctx, R.raw.conn_ico_off, null); { //<ImageView android:id="@+id/conn_ico" android:layout_width="50dp" android:layout_height="50dp" android:gravity="left|center_vertical" android:src="@raw/conn_ico_off"/>
//                    int w = us.cash.a.le.dp2px(50);
//                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(w, w);
//                    layoutParams.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
//                    conn_ico.setLayoutParams(layoutParams);
//                    conn_ico.setScaleType(ImageView.ScaleType.CENTER);
//                    conn_ico.setImageResource();
                }

                Space space = new Space(ctx); { //<Space android:layout_width="0dp" android:layout_height="0dp" android:layout_weight="1" />
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, 0);
                    layoutParams.weight = 1;
                    space.setLayoutParams(layoutParams);
                }

                poweron = new Switch(ctx); { //<Switch android:id="@+id/poweron" android:layout_width="wrap_content" android:layout_height="wrap_content" android:layout_marginRight="16dp" android:text="Power" android:theme="@style/switch_theme" android:gravity="right|center_vertical" />
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
                    poweron.setLayoutParams(params);
                    poweron.setText("Power");
                    int right_margin = us.cash.app.a.le.dp2px(16);
                    poweron.setPadding(0, 0, right_margin, 0); // Set right margin using padding
                    poweron.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override public void onCheckedChanged(CompoundButton buttonView, boolean is_checked) {
                            log("on_poweron " + is_checked); //--strip
                            if (!enable_listener) return;
                            if (listener == null) return;
                            listener.on_poweron__changed(list_view__itemview.getBindingAdapterPosition(), is_checked);
                        }
                    });
                }

                conf = new button_t(ctx, R.raw.gear, new View.OnClickListener() {
                        @Override public void onClick(View v) {
                            log("on_conf"); //--strip
                            if (listener == null) return;
                            listener.on_conf__click(list_view__itemview.getBindingAdapterPosition());
                        }
                    }); {
                    int right_margin = us.cash.app.a.le.border;
                    conf.setPadding(0, 0, right_margin, 0); // Set right margin using padding
                }

                menu = new button_t(ctx, R.raw.menu, new View.OnClickListener() {
                        @Override public void onClick(View v) {
                            log("on_menu"); //--strip
                            if (listener == null) return;
                            listener.on_menu__click(list_view__itemview.getBindingAdapterPosition());
                        }
                    }); { 
                }

                trash = new button_t(ctx, R.raw.trash, new View.OnClickListener() {
                        @Override public void onClick(View v) {
                            log("on_trash"); //--strip
                            if (listener == null) return;
                            listener.on_trash__click(list_view__itemview.getBindingAdapterPosition());
                        }
                    }); {
                    int left_margin = us.cash.app.a.le.dp2px(16);
                    trash.setPadding(left_margin, 0, 0, 0); // Set right margin using padding
                }
                pan.addView(conn_ico);
                pan.addView(space);
                pan.addView(poweron);
                pan.addView(conf);
                pan.addView(menu);
                pan.addView(trash);
            }
            
            papyrus.addView(label);
            papyrus.addView(ssid);
            papyrus.addView(address);
            papyrus.addView(status);
            papyrus.addView(pan);
        }
        return papyrus;
    }

    public text_view_t label;
    public text_view_t ssid;
    public text_view_t address;
    public text_view_t status;
    public button_t conn_ico;
    public Switch poweron;
    public button_t conf;
    public button_t menu;
    public button_t trash;

    listener_t listener;

    public boolean enable_listener = true;
}

