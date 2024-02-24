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
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/scr/toolbar/mim.h
//MIM  kickoff code hash: nFoUvtBiNtKqAW1hkuD25u3sLqo (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
import android.app.Activity;                                                                   // Activity
import java.util.ArrayList;                                                                    // ArrayList
import android.util.AttributeSet;                                                              // AttributeSet
import us.gov.crypto.base58;                                                                   // base58
import android.os.Bundle;                                                                      // Bundle
import android.content.Context;                                                                // Context
import us.gov.socket.datagram;                                                                 // datagram
import us.wallet.trader.data_t;                                                                // data_t
import java.util.HashMap;                                                                      // HashMap
import static us.gov.crypto.ripemd160.hash_t;                                                  // hash_t
import android.widget.ImageView;                                                               // ImageView
import static us.stdint.*;                                                                     // *
import static us.tuple.*;                                                                      // *
import us.ko;                                                                                  // ko
import android.view.LayoutInflater;                                                            // LayoutInflater
import android.widget.LinearLayout;                                                            // LinearLayout
import java.util.Map;                                                                          // Map
import com.google.android.material.button.MaterialButton;                                      // MaterialButton
import com.google.android.material.card.MaterialCardView;                                      // MaterialCardView
import static us.ko.ok;                                                                        // ok
import us.pair;                                                                                // pair
import us.wallet.protocol;                                                                     // protocol
import us.gov.crypto.ripemd160;                                                                // ripemd160
import android.widget.TableLayout;                                                             // TableLayout
import android.widget.TextView;                                                                // TextView
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import androidx.appcompat.widget.Toolbar;
import java.util.Random;                                                                       // Random
import us.ko;                                                                                  // ko
import androidx.annotation.NonNull;                                                            // NonNull
import androidx.annotation.Nullable;                                                           // Nullable
import us.cash.R;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Space;
import us.cash.R;
import android.graphics.Typeface;
import java.util.Collections;

public class toolbar0_t extends LinearLayout implements dbg_mim_t {

    private static void log(final String line) {                              //--strip
        us.cash.CFG.log_scr("toolbar0_t: " + line);          //--strip
    }                                                                         //--strip

//    public toolbar0_t() {
//        super();
//    }

    @Override public String mim_vertex_path() {
        return "core0/core0_6/core0_61/us/gui/activity/scr/toolbar";
    }

    public static class buttons_t extends LinearLayout {

        private static void log(final String line) {             //--strip
            us.cash.CFG.log_scr("toolbar0.buttons_t: " + line);              //--strip
        }                                                        //--strip

        public buttons_t(Context ctx, toolbar0_t toolbar) {
            super(ctx);
            this.toolbar = toolbar;
            setLayoutParams(us.cash.layout_engine_t.layout_params_wrap);
            setOrientation(LinearLayout.HORIZONTAL);
        }

        public void configure(menu_t menu) {
            removeAllViews();
            menu_t.menuspec_t spec = menu.create_spec();
            for (menu_t.group_t g: spec) {
                Collections.reverse(g);
                for (menu_t.itemspec_t i: g) {
                    if (i.id == R.raw.burger) continue;
                    add_stock_button(i.id);
                }
            }
        }

        public void add_stock_button(final int resid) {
            button_t b = toolbar.stock.get(resid);
            if (b == null) {
                b = new button_t(getContext(), resid, new View.OnClickListener() {
                    @Override public void onClick(View view) {
                        toolbar.on_button(resid);
                    }
                });
                toolbar.stock.put(resid, b);
            }
            else {
                ViewGroup p = (ViewGroup) b.getParent();
                if (p != null) {
                    p.removeView(b);
                }
            }
            addView(b);
        }

        toolbar0_t toolbar;
    }

    public int burger_icon() {
        return R.raw.burger; //R.raw.menu;
    }    

    public void on_button(final int resid) {
        l.on_menu(resid);
    }
    
    public toolbar0_t(Context ctx, menu_t.listener l) {
        super(ctx);
        this.l = l;
        
        setLayoutParams(us.cash.app.a.le.layout_params_vwrap);
        setPadding(us.cash.app.a.le.border, 0, us.cash.app.a.le.border, 1);
        
        //menuico = findViewById(R.id.menuico);
        setGravity(Gravity.CENTER_VERTICAL);

        menuico = new button_t(getContext(), burger_icon(), new View.OnClickListener() {
            @Override public void onClick(View view) {
                log("on_click_menu"); //--strip
                a.toggle_drawer();
            }
        });
        addView(menuico); //#0

        canvas_title = new canvas_t(ctx, 8, 1); {
            canvas_title.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
            canvas_title.setPadding(us.cash.app.a.le.border, 0, us.cash.app.a.le.border, 0);
            title = new text_view_t(ctx, 0); {
                title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
                title.setTypeface(title.getTypeface(), Typeface.BOLD);
                title.setAllCaps(true);
                title.setClickable(true);
            }
            canvas_title.addView(title); //#1
        }
        addView(canvas_title);

        Space space = new Space(ctx); {
            space.setLayoutParams(new LinearLayout.LayoutParams(0, 0, 1));
        }
        addView(space); //#2

        buttons = new buttons_t(ctx, this);;
        addView(buttons); //#3
        //buttons.setVisibility(View.GONE);
    }

    public void configure(menu_t menu) {
        assert buttons != null; //--strip
//        assert getChildCount() >= 4; //--strip
//        assert getChildAt(3) == buttons; //--strip
        buttons.configure(menu);
        l = menu.l;
//        a.le.detach_from_parent(buttons);
    }

    public void set_title(@NonNull String title_) {
        title.setText(title_);
    }

    public void clear_cache() {
        log("VVZZX clear_cache. stock. menuico"); //--strip
        stock = new HashMap<>();
        menuico.setImageResource(us.cash.app.a.le.resolve_resid(burger_icon()));
       // buttons.clear_cache();
    }

    public button_t menuico;
    public canvas_t canvas_title;
    public text_view_t title;
    public buttons_t buttons = null;
    menu_t.listener l;

    public static us.cash.app a = null;
    public HashMap<Integer, button_t> stock = new HashMap<>();

}

