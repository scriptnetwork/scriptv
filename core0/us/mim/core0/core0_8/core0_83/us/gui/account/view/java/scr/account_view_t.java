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

public class account_view_t extends canvas_t {

    private static void log(final String line) {           //--strip
        us.cash.CFG.log_scr("account_view_t: " + line);         //--strip
    }                                                      //--strip

    public account_view_t(Context ctx, list_view_t.itemclick_listener_t coins_listener_) { //, final coins_view_t.itemclick_listener_t coins_listener) {
        super(ctx, 7, 1);
        coins_listener = coins_listener_;
        canvas_t top = new canvas_t(ctx, 7, 1); {
            locking_program_canvas = new canvas_t(ctx, 7, 1); { //7=vwrap 1=V
                canvas_t tit = new canvas_t(ctx, 7, 0); { //7=vwrap 0=H
                    button_t blogo = new button_t(ctx, R.raw.key, null); {
                        //final int sz = us.cash.app.a.le.dp2px(25);
                        //LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(28, 28);
                        //layout.setMargins(0, 0, us.cash.app.a.le.dp2px(8), 0);
                        
                       // blogo.setLayoutParams(layout);
                        //blogo.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    }
                    text_view_t title = new text_view_t(ctx, 0); { //0-wrap
                        title.setText(" Locking program:");
//                            title.setTypeface(null, Typeface.BOLD);
                    }
                    locking_program = new text_view_t(ctx, 5); { //5-hexpand_vwrap
                        locking_program.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
                        locking_program.setText("P2PKH");
                    }
                    tit.addView(blogo);
                    tit.addView(title);
                    tit.addView(locking_program);
                }
//                    text_view_t space = new text_view_t(ctx, 0);
                locking_program_canvas.addView(tit);
//                  locking_program_canvas.addView(space);
            }
            canvas_t system_coin_canvas = new canvas_t(ctx, 7, 1); {
                
                canvas_t tit = new canvas_t(ctx, 7, 0); {
                    button_t blogo = new button_t(ctx, R.raw.token, null); {
                        //final int sz = us.cash.app.a.le.dp2px(25);
                        //LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(28, 28);
                        //layout.setMargins(0, 0, us.cash.app.a.le.dp2px(8), 0);
                        
                        //blogo.setLayoutParams(layout);
                        //blogo.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    }
                    text_view_t title = new text_view_t(ctx, 0); { //0-wrap
                        title.setText(" System coin (" +  us.CFG.UGAS + "):");
                        //title.setTypeface(null, Typeface.BOLD);
                    }
                    system_coin = new text_view_t(ctx, 5); { //5 fill
                        system_coin.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
                    }
                    tit.addView(blogo);
                    tit.addView(title);
                    tit.addView(system_coin);
                }
                system_coin_canvas.addView(tit);
            }
            //top.addView(left);
            top.addView(locking_program_canvas);
            top.addView(system_coin_canvas);
        }
        coins = new coins_view_t(ctx, coins_listener); {
            int border = us.cash.app.a.le.border;
            coins.setPadding(border, border, border, border);
        }
        addView(top);
        addView(coins);
    }

    public void set_account(final account_t acc) {
        locking_program.setText(acc.locking_program);
        system_coin.setText(acc.oil);
        coins.set_tokens(acc.coins);
        set_timeseries(null);
    }

    public void set_timeseries(final timeseries_entry_index_t o) {
        if (o == null) {
            if (timeseries != null) {
                removeView(timeseries);
            }
            timeseries = null;
            return;
        }
        if (timeseries == null) {
            timeseries = new timeseries_view_t(getContext(), timeseries_listener); {
                int border = us.cash.app.a.le.border;
                timeseries.setPadding(border, border, border, border);
            }
            addView(timeseries);
        }
        timeseries.set_timeseries(o);
    }
    
    public canvas_t locking_program_canvas;
    public text_view_t locking_program;
    public text_view_t system_coin;
    public coins_view_t coins;
    public timeseries_view_t timeseries;
    list_view_t.itemclick_listener_t coins_listener = null; 
    list_view_t.itemclick_listener_t timeseries_listener = null; 
}

