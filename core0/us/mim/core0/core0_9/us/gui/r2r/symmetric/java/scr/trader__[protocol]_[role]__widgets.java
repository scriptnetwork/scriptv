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
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import us.cash.R;
import us.cash.CFG;
import android.util.TypedValue;                                                                // TypedValue
import android.view.Gravity;

public class trader__##protocol##_##role##__widgets extends trader__conf__role__widgets {

    private static void log(final String s) {                        //--strip
        us.cash.CFG.log_scr("trader__##protocol##_##role##__widgets: " + s);            //--strip
    }                                                                //--strip

    public trader__##protocol##_##role##__widgets(View.OnClickListener search_listener_) {
        super();
        search_listener = search_listener_;
    }

    @Override protected void create_papyrus(Context ctx) {
        super.create_papyrus(ctx); //logo + tip + workflow+ redirects


        canvas_t c1 = new canvas_t(ctx, 13, 0); {
            text_view_t lbl = new text_view_t(ctx, 7); {
                lbl.setText("Send coins:");
            }
            c1.addView(lbl);
        }
        canvas_t c2 = new canvas_t(ctx, 13, 0); {
            text_view_t lbl = new text_view_t(ctx, 7); {
                lbl.setText("Coin:");
            }
            text_view_t coin = new text_view_t(ctx, 5); {
                coin.setText("");
                coin.setGravity(Gravity.END);
            }
            button_t search = new button_t(ctx, R.raw.search, search_listener); {
            }
            c1.addView(lbl);
            c1.addView(recipient);
            c1.addView(search);
        }
        canvas_t c3 = new canvas_t(ctx, 13, 0); {
            text_view_t lbl = new text_view_t(ctx, 7); {
                lbl.setText("Amount:");
            }
            edit_text_t amount = new edit_text_t(ctx, 5); {
                amount.setText("");
                amount.setGravity(Gravity.END);
            }
        }
        papyrus.addView(c1);
        papyrus.addView(c2);
        papyrus.addView(c3);
    }

    View.OnClickListener search_listener;

}

