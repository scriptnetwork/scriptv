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
import us.cash.timeseries__view__adapter_t;
import us.cash.tokens_t;
import us.wallet.wallet.timeseries_entry_index_t;

public class timeseries_view_t extends canvas_t {

    private static void log(final String line) {           //--strip
        us.cash.CFG.log_scr("timeseries_view_t: " + line);         //--strip
    }                                                      //--strip

    public timeseries_view_t(Context ctx, final list_view_t.itemclick_listener_t listener) {
        super(ctx, 10, 1);

        text_view_t lbl = new text_view_t(ctx, 1); {
            lbl.setText("Timeseries:");
        }
        empty_list = new text_view_t(ctx, 1); {
            empty_list.setText("Empty");
        }
        list = new list_view_t(ctx, 2, listener, empty_list);
        addView(lbl);
        addView(empty_list);
        addView(list);
        list.setAdapter(adapter);
    }

    public void set_timeseries(final timeseries_entry_index_t entries) {
        adapter.set_data(entries);
    }

    public list_view_t list;
    private text_view_t empty_list;
    private list_view_t.itemclick_listener_t listener;
    
    public timeseries__view__adapter_t adapter = new timeseries__view__adapter_t();

}

