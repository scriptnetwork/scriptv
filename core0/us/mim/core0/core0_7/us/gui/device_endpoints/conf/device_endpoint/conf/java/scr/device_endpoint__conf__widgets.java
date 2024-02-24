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
//MIM        file: core0/core0_6/core0_61/us/gui/activity/controller/java/scr/[classname]__widgets.java
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM  Params:
//MIM    'classname': 'device_endpoint__conf'
//MIM    'create_tree': '@Override public Vie...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'include': ''
//MIM  kickoff code hash: K13RmfkBYKPw9fwwtEjS4d2jNKW (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
import android.content.Context;                                                                // Context
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import us.cash.R;
import us.cash.CFG;
import android.util.TypedValue;                                                                // TypedValue
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
import android.util.AttributeSet;                                                              // AttributeSet
import us.cash.R;                                                              // AttributeSet
import android.view.Gravity;
import android.graphics.Typeface;

public class device_endpoint__conf__widgets extends view__widgets {

    private static void log(final String s) {                        //--strip
        us.cash.CFG.log_scr("device_endpoint__conf__widgets: " + s);            //--strip
    }                                                                //--strip

    public device_endpoint__conf__widgets(View.OnClickListener save_name__listener_, View.OnClickListener read_ssid__listener_, View.OnClickListener save_ssid__listener_, View.OnClickListener connection_status__listener_, View.OnClickListener save_subhome__listener_, View.OnClickListener connect_btn__listener_) {
        save_name__listener = save_name__listener_;
        read_ssid__listener = read_ssid__listener_;
        save_ssid__listener = save_ssid__listener_;
        connection_status__listener = connection_status__listener_;
        save_subhome__listener = save_subhome__listener_;
        connect_btn__listener = connect_btn__listener_;
    }

    //MIM begin token 'create_tree'
    @Override public ViewGroup create_tree(Context ctx) {
        create_papyrus(ctx);
        assert papyrus != null; //--strip
        scroll = new scroll_view_t(ctx);
        scroll.addView(papyrus);
        return scroll;
    }

    private void create_papyrus(Context ctx) {
        papyrus = new canvas_t(ctx);
                            //android:fillViewport="true"
                            //android:gravity="bottom|center"
        papyrus.addView(get_content__name(ctx));
        papyrus.addView(get_content__wifi(ctx));
        papyrus.addView(get_content__subhome(ctx));
        papyrus.addView(get_content__device(ctx));
        papyrus.addView(get_content__device2(ctx));
        papyrus.addView(get_content__endpoint_inputbox(ctx));
        papyrus.addView(get_content__power(ctx));
        papyrus.addView(get_content__status(ctx));
        papyrus.addView(get_content__online(ctx));
    }

    View get_content__name(Context ctx) {
        canvas_t hcanvas = new canvas_t(ctx, 1, 0); {
            TextInputLayout input = new TextInputLayout(ctx); {
                input.setLayoutParams(us.cash.app.a.le.layout_params_hexpand_vwrap);
                input.setHint("Name of this connection");
                input.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
                name = new TextInputEditText(ctx); {
                    name.setLayoutParams(us.cash.app.a.le.layout_params_vwrap);
                    name.setText("My_wallet");
                    name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                }
                input.addView(name);
            }
            save_name = new button_t(ctx, R.raw.enter, save_name__listener);
            hcanvas.addView(input);
            hcanvas.addView(save_name);
        }
        return hcanvas;
    }

    View get_content__wifi(Context ctx) {
        canvas_t hcanvas = new canvas_t(ctx, 2, 0); {
            TextInputLayout input = new TextInputLayout(ctx); {
                input.setLayoutParams(us.cash.app.a.le.layout_params_hexpand_vwrap);
                input.setHint("Optional: Tie to WiFi network (Enter SSID)");
                input.setHintAnimationEnabled(true);
                input.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
                ssid = new TextInputEditText(ctx); {
                    ssid.setLayoutParams(us.cash.app.a.le.layout_params_vwrap);
                    ssid.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                }
                input.addView(ssid);
            }
            read_ssid = new button_t(ctx, R.raw.read_ssid, read_ssid__listener);
            save_ssid = new button_t(ctx, R.raw.enter, save_ssid__listener);
            hcanvas.addView(input);
            hcanvas.addView(read_ssid);
            hcanvas.addView(save_ssid);
        }
        return hcanvas;
    }

    View get_content__subhome(Context ctx) {
        canvas_t hcanvas = new canvas_t(ctx, 2, 0); {
            TextInputLayout input = new TextInputLayout(ctx); {
                input.setLayoutParams(us.cash.app.a.le.layout_params_hexpand_vwrap);
                input.setHint("Custodial wallet (or 'new', or empty for non-custodial)");
                input.setHintAnimationEnabled(true);
                input.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
                subhome = new TextInputEditText(ctx); {
                    subhome.setLayoutParams(us.cash.app.a.le.layout_params_vwrap);
                    subhome.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                }
                input.addView(subhome);
            }
            save_subhome = new button_t(ctx, R.raw.enter, save_subhome__listener); {
            }
            hcanvas.addView(input);
            hcanvas.addView(save_subhome);
        }
        return hcanvas;
    }

    View get_content__device(Context ctx) {
        canvas_t hcanvas = new canvas_t(ctx, 2, 0); {
            TextInputLayout input = new TextInputLayout(ctx); {
                input.setLayoutParams(us.cash.app.a.le.layout_params_hexpand_vwrap);
                input.setHint(R.string.thisdevice);
                input.setHintAnimationEnabled(true);
                input.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
                input.setStartIconDrawable(ContextCompat.getDrawable(ctx, R.drawable.ic_devices));
    //            input.setMargins(0, us.cash.app.a.le.border, 0, 0); ////left, top, right, bottom
                //input.setPadding(0, us.cash.app.a.le.border, 0, 0); ////left, top, right, bottom
                devicepk = new TextInputEditText(ctx); {
                    devicepk.setLayoutParams(us.cash.app.a.le.layout_params_vwrap);
                    devicepk.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                    devicepk.setMaxLines(2);
                    devicepk.setLines(2);
                    devicepk.setGravity(Gravity.START);
                    //devicepk.setInputType(InputType.TYPE_CLASS_TEXT);
                    devicepk.setInputType(InputType.TYPE_NULL);
                    devicepk.setTextIsSelectable(true);
                    devicepk.setKeyListener(null);
                }
                input.addView(devicepk);
            }
            hcanvas.addView(input);
        }
        return hcanvas;
    }

    View get_content__device2(Context ctx) {
        canvas_t hcanvas = new canvas_t(ctx, 2, 0); {
            TextInputLayout input = new TextInputLayout(ctx); {
                input.setLayoutParams(us.cash.app.a.le.layout_params_hexpand_vwrap);
                input.setHint("This device Address");
                input.setHintAnimationEnabled(true);
                input.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
                input.setStartIconDrawable(ContextCompat.getDrawable(ctx, R.drawable.ic_devices));
    //            input.setMargins(0, us.cash.app.a.le.border, 0, 0); ////left, top, right, bottom
                //input.setPadding(0, us.cash.app.a.le.border, 0, 0); ////left, top, right, bottom
                devicepkh = new TextInputEditText(ctx); {
                    devicepkh.setLayoutParams(us.cash.app.a.le.layout_params_vwrap);
                    devicepkh.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                    devicepkh.setMaxLines(1);
                    devicepkh.setLines(1);
                    devicepkh.setGravity(Gravity.START);
                    //devicepk.setInputType(InputType.TYPE_CLASS_TEXT);
                    devicepkh.setInputType(InputType.TYPE_NULL);
                    devicepkh.setTextIsSelectable(true);
                    devicepkh.setKeyListener(null);
                }
                input.addView(devicepkh);
            }
            hcanvas.addView(input);
        }
        return hcanvas;
    }

    View get_content__endpoint_inputbox_body(Context ctx) {
        canvas_t hcanvas = new canvas_t(ctx, 2, 0); { //horizontal
            TextInputLayout input1 = new TextInputLayout(ctx); {
                input1.setLayoutParams(us.cash.app.a.le.layout_params_hexpand_vwrap);
                input1.setHint(R.string.ipaddress);
                input1.setHintAnimationEnabled(true);
                input1.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
//                input1.setMargins(0, 0, us.cash.app.a.le.border, 0); ////left, top, right, bottom
                input1.setPadding(0, 0, us.cash.app.a.le.border, 0); ////left, top, right, bottom
                addr = new TextInputEditText(ctx); {
                    addr.setLayoutParams(us.cash.app.a.le.layout_params_vwrap);
                    addr.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                    addr.setSingleLine();
                    addr.setGravity(Gravity.CENTER);
                    addr.setInputType(InputType.TYPE_CLASS_NUMBER);
                    addr.setKeyListener(DigitsKeyListener.getInstance("0123456789."));
                    addr.setSelectAllOnFocus(true);
                    addr.setText("192.168.0.2");
                    addr.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    //addr.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    //    @Override public void onFocusChange(View v, boolean hasFocus) {}
                    //});
                }
                input1.addView(addr);
            }
            TextInputLayout input2 = new TextInputLayout(ctx); {
                input2.setLayoutParams(us.cash.app.a.le.layout_params_hexpand_vwrap);
                input2.setHint(R.string.tcpport);
                input2.setHintAnimationEnabled(true);
                input2.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
//                input2.setMargins(0, 0, us.cash.app.a.le.border, 0); ////left, top, right, bottom
                input2.setPadding(0, 0, us.cash.app.a.le.border, 0); ////left, top, right, bottom
                port = new TextInputEditText(ctx); {
                    port.setLayoutParams(us.cash.app.a.le.layout_params_vwrap);
                    port.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                    port.setSingleLine();
                    port.setGravity(Gravity.CENTER);
                    port.setInputType(InputType.TYPE_CLASS_NUMBER);
                    port.setKeyListener(DigitsKeyListener.getInstance("0123456789."));
                    port.setSelectAllOnFocus(true);
                    port.setText("16673");
                    port.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    port.setMinWidth(us.cash.app.a.le.dp2px(80));

                    //addr.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    //    @Override public void onFocusChange(View v, boolean hasFocus) {}
                    //});
                }
                input2.addView(port);
            }
            TextInputLayout input3 = new TextInputLayout(ctx); {
                input3.setLayoutParams(us.cash.app.a.le.layout_params_hexpand_vwrap);
                input3.setHint(R.string.channel);
                input3.setHintAnimationEnabled(true);
                input3.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
                //input3.setMargins(0, 0, a.le.border, 0); ////left, top, right, bottom
                channel = new TextInputEditText(ctx); {
                    channel.setLayoutParams(us.cash.app.a.le.layout_params_vwrap);
                    channel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                    channel.setSingleLine();
                    channel.setGravity(Gravity.CENTER);
                    channel.setInputType(InputType.TYPE_CLASS_NUMBER);
                    channel.setKeyListener(DigitsKeyListener.getInstance("0123456789."));
                    channel.setSelectAllOnFocus(true);
                    channel.setText("0");
                    channel.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    channel.setMinWidth(us.cash.app.a.le.dp2px(80));

                    //addr.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    //    @Override public void onFocusChange(View v, boolean hasFocus) {}
                    //});
                }
                input3.addView(channel);
            }
            hcanvas.addView(input1);
            hcanvas.addView(input2);
            hcanvas.addView(input3);
        }
        return hcanvas;
    }

    View get_content__endpoint_inputbox(Context ctx) {
        canvas_t vcanvas = new canvas_t(ctx, 2, 1); { //vertical
            text_view_t label = new text_view_t(ctx, 1); {
                label.setText("Connection data");
                label.setGravity(Gravity.CENTER);
//              label.setPadding(dpToPx(16), 0, 0, 0);
            }
            vcanvas.addView(label);
            vcanvas.addView(get_content__endpoint_inputbox_body(ctx));
        }
        return vcanvas;
    }

    View get_content__power__switch(Context ctx) {
        canvas_t hcanvas = new canvas_t(ctx, 1, 0); { //horizontal
            //int padding = us.cash.app.a.le.dp2px(16);
            //hcanvas.setPadding(padding, padding, padding, padding);
            hcanvas.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
/*
            text_view_t label = new text_view_t(ctx, 0); {
                label.setText("POWER");
                label.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                label.setTypeface(null, Typeface.BOLD);
                label.setGravity(Gravity.CENTER);
//              label.setPadding(dpToPx(16), 0, 0, 0);
            }
*/
            poweron = new Switch(ctx); {
                poweron.setLayoutParams(us.cash.app.a.le.layout_params_wrap);
//                poweron.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));

                //poweron.setLayoutParams(new LinearLayout.LayoutParams(us.cash.app.a.le.dp2px(100), us.cash.app.a.le.dp2px(72)));
                poweron.setPadding(50, 50, 50, 50); // set padding in pixels
                poweron.setScaleX(2); // set scale X value
                poweron.setScaleY(2); // set scale Y value
                //poweron.setSwitchTextAppearance(ctx, R.style.switch_theme); // set switch theme
                poweron.setText("POWER"); // set empty text
            }
//            hcanvas.addView(label);
            hcanvas.addView(poweron);
//            hcanvas.requestLayout();

        }
        return hcanvas;
    }


    View get_content__power__leds(Context ctx) {
        canvas_t canvas = new canvas_t(ctx, 1, 0); {
            canvas.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
            leds = new leds_t(ctx, false, 8, true); {
                leds.set_visibility(true);
                leds.set_all_off__ui();
            }
            canvas.addView(leds);
        }
        return canvas;
    }

    View get_content__power(Context ctx) {
        canvas_t hcanvas = new canvas_t(ctx, 2, 1); { //vertical
            hcanvas.setPadding(0, 0, 0, 0);
            View hcanvas1 = get_content__power__switch(ctx);
            View hcanvas2 = get_content__power__leds(ctx);
            current_endpoint = new text_view_t(ctx, 3); {
                current_endpoint.setText("");
                current_endpoint.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
            hcanvas.addView(hcanvas1);
            hcanvas.addView(hcanvas2);
            hcanvas.addView(current_endpoint);
        }
        return hcanvas;
    }

    View get_content__status(Context ctx) {
        canvas_t layoutst = new canvas_t(ctx, 1, 1); { //vertical
//            layoutst.setPadding(0, 0, 0, 0);

            TextInputLayout cst = new TextInputLayout(ctx); {
//                cst.setLayoutParams(us.cash.app.a.le.layout_params_hexpand_vwrap);
                cst.setLayoutParams(us.cash.app.a.le.layout_params_vwrap);
                cst.setHint(R.string.connection);
                cst.setHintAnimationEnabled(true);
                cst.setStartIconDrawable(R.drawable.conn);
                //cst.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
                //cst.setPadding(0, 0, 0, 0);
                //cst.gravity = Gravity.LEFT;
                connection_status = new TextInputEditText(ctx); {
                    connection_status.setLayoutParams(us.cash.app.a.le.layout_params_vwrap);
                    connection_status.setTextIsSelectable(true);
                    connection_status.setKeyListener(null);
                    connection_status.setOnClickListener(connection_status__listener);
                    connection_status.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                    //connection_status.setPadding(0, 0, 0, 0);
                }
                cst.addView(connection_status);
            }
         /*   
            
            
            TextInputLayout input3 = new TextInputLayout(ctx); {
                input3.setLayoutParams(us.cash.app.a.le.layout_params_hexpand_vwrap);
                input3.setHint(R.string.tcpport);
                input3.setHintAnimationEnabled(true);
                input3.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
                //input3.setMargins(0, 0, a.le.border, 0); ////left, top, right, bottom
                channel = new TextInputEditText(ctx); {
                    channel.setLayoutParams(us.cash.app.a.le.layout_params_vwrap);
                    channel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                    channel.setSingleLine();
                    channel.setGravity(Gravity.CENTER);
                    channel.setInputType(InputType.TYPE_CLASS_NUMBER);
                    channel.setKeyListener(DigitsKeyListener.getInstance("0123456789."));
                    channel.setSelectAllOnFocus(true);
                    channel.setText("0");
                    channel.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    channel.setMinWidth(us.cash.app.a.le.dp2px(80));

                    //addr.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    //    @Override public void onFocusChange(View v, boolean hasFocus) {}
                    //});
                }
                input3.addView(channel);
            }            
            */
            layoutst.addView(cst);
            //layoutst.addView(input3);
            //layoutst.addView(npkh);
        }
        return layoutst;
    }

    View get_content__online(Context ctx) {
        online = new canvas_t(ctx, 1, 1); { //vertical
            TextInputLayout npkh = new TextInputLayout(ctx); {
                npkh.setLayoutParams(us.cash.app.a.le.layout_params_vwrap);
                npkh.setHint(R.string.nodehash);
                npkh.setHintAnimationEnabled(true);
                npkh.setStartIconDrawable(R.drawable.ic_node_idle_32);
                npkh.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
                npkh.setPadding(0, 0, 0, 0);
                //npkh.gravity = Gravity.LEFT;
                nodepkh = new TextInputEditText(ctx); {
                    nodepkh.setLayoutParams(us.cash.app.a.le.layout_params_vwrap);
                    nodepkh.setKeyListener(null);
                    nodepkh.setInputType(InputType.TYPE_NULL);
                    nodepkh.setTextIsSelectable(true);
                    nodepkh.setPadding(0, 0, 0, 0);
                }
                npkh.addView(nodepkh);
            }

            canvas_t pin = new canvas_t(ctx, 1, 0); {
                pin.setGravity(Gravity.CENTER);
                {
                    LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layout.setMargins(0, us.cash.app.a.le.border, 0, 0); ////left, top, right, bottom
                    connect_btn = new button_t(ctx, layout, R.raw.enterpin, connect_btn__listener);
                }
                text_view_t label = new text_view_t(ctx, 0); {
                    label.setText("Reconnect\nusing PIN");
                    label.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
    //              label.setPadding(dpToPx(16), 0, 0, 0);
                }
                pin.addView(label);
                pin.addView(connect_btn);
            }

            text_view_t modelbl = new text_view_t(ctx, 3); {
                modelbl.setGravity(Gravity.BOTTOM | Gravity.LEFT);
                modelbl.setText("Privacy info:");
                //mode.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
            mode = new text_view_t(ctx, 3); {
                mode.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
                //mode.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
            online.addView(npkh);
            online.addView(pin);
            online.addView(modelbl);
            online.addView(mode);
        }
        return online;
    }

    public scroll_view_t scroll = null;
    public canvas_t papyrus = null;
    //MIM end token 'create_tree'
    
    public TextInputEditText name;                  public button_t save_name;
    public TextInputEditText ssid;                  public button_t save_ssid; public button_t read_ssid;
    public TextInputEditText subhome;               public button_t save_subhome;
    public TextInputEditText devicepk;
    public TextInputEditText devicepkh;
    public TextInputEditText addr;                  public TextInputEditText port;            public TextInputEditText channel;
    public Switch poweron;
    public leds_t leds;
    public TextInputEditText connection_status;
    public canvas_t online;
        public TextInputEditText nodepkh;
        public text_view_t current_endpoint;
        public button_t connect_btn;
        public text_view_t mode;

    View.OnClickListener save_name__listener;
    View.OnClickListener read_ssid__listener;
    View.OnClickListener save_ssid__listener;
    View.OnClickListener connection_status__listener;
    View.OnClickListener save_subhome__listener;
    View.OnClickListener connect_btn__listener;
}

