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
import android.content.Context;                                                                // Context
import android.widget.LinearLayout;
import android.view.ViewGroup;                                                                 // ViewGroup
import android.util.TypedValue;                                                                // TypedValue
import us.cash.R;
import us.cash.CFG;
import android.view.Gravity;
import android.widget.ImageView;
import android.view.ViewGroup.LayoutParams;                                                    // LayoutParams
import us.gov.cash.account_t; //MIM token 'include'
import us.cash.coins__view__adapter_t;
import us.cash.tokens_t;

public class coins_view_t extends canvas_t {

    private static void log(final String line) {           //--strip
        us.cash.CFG.log_scr("coins_view_t: " + line);         //--strip
    }                                                      //--strip

    public coins_view_t(Context ctx, final list_view_t.itemclick_listener_t listener) { //, final coins_view_t.itemclick_listener_t coins_listener) {
        super(ctx, 10, 1);

        text_view_t coins_lbl = new text_view_t(ctx, 1); {
            coins_lbl.setText("Coins:");
        }
        empty_list = new text_view_t(ctx, 1); {
            empty_list.setText("Empty");
        }
        list = new list_view_t(ctx, 2, listener, empty_list);
        addView(coins_lbl);
        addView(empty_list);
        addView(list);
        list.setAdapter(adapter);
    }

    public void set_tokens(final tokens_t tokens) {
        adapter.set_data(tokens);
    }

    public list_view_t list;
    private text_view_t empty_list;
    private list_view_t.itemclick_listener_t listener;
    
    public coins__view__adapter_t adapter = new coins__view__adapter_t();

}

