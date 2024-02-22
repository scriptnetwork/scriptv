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
import us.cash.account_t; //MIM token 'include'
import us.wallet.wallet.timeseries_entry_index_t;
import static us.stdint.*;
import android.widget.ImageView;
import us.wallet.trader.data_t;
import us.cash.trader__conf;
import android.view.View;                                                                      // View
import android.graphics.BitmapFactory;                                                         // BitmapFactory
import android.graphics.Bitmap;                                                                // Bitmap

public class tip_view_t extends canvas_t {

    private static void log(final String line) {           //--strip
        us.cash.CFG.log_scr("tip_view_t: " + line);         //--strip
    }                                                      //--strip

    public tip_view_t(Context ctx) {
        super(ctx, 7, 1); 
        state_c = new canvas_t(ctx, 13, 0); {
            button_t icon = new button_t(ctx, R.raw.state, null); {
            }
            state = new text_view_t(ctx, 7); {
            }
            state_c.addView(icon);
            state_c.addView(state);
        }
        tip_c = new canvas_t(ctx, 13, 0); {
            button_t icon = new button_t(ctx, R.raw.tip, null); {
            }
            tip = new text_view_t(ctx, 7); {
            }
            tip_c.addView(icon);
            tip_c.addView(tip);
        }
        addView(state_c);
        addView(tip_c);
    }

    public void on_data(final data_t data) {
        us.cash.app.assert_ui_thread(); //--strip
        if (data == null) {
            setVisibility(View.GONE);
            return;            
        }
        setVisibility(View.VISIBLE);
        {
            String st = data.find("trade_state");
            if (st == null || st.isEmpty()) {
                state_c.setVisibility(View.GONE);    
            }
            else {
                state.setText(st);
                state_c.setVisibility(View.VISIBLE);    
            }
        }
        {
            String hint = data.find("user_hint");
            if (hint == null || hint.isEmpty()) {
                tip_c.setVisibility(View.GONE);    
            }
            else {
                tip.setText(hint);
                tip_c.setVisibility(View.VISIBLE);    
            }
        }
    }

    canvas_t state_c; 
    canvas_t tip_c; 

    public text_view_t tip;
    public text_view_t state;
}

