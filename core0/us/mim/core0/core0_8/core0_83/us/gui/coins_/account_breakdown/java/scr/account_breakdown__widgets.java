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
import us.gov.cash.account_t;
import us.gov.cash.accounts_t;


public class account_breakdown__widgets extends account_breakdown0__widgets {

    private static void log(final String s) {                        //--strip
        us.cash.CFG.log_scr("account_breakdown__widgets: " + s);            //--strip
    }                                                                //--strip

    public account_breakdown__widgets(final account_breakdown__list_view_t.itemclick_listener_t list_listener_) {
        super(list_listener_);
	listener = list_listener_;
    }

    @Override public account_breakdown0__list_view_t create_list(Context ctx) {
	return new account_breakdown__list_view_t(ctx, listener);
    }

    @Override public ViewGroup create_tree(Context ctx) { //aka inflate
        ViewGroup r = super.create_tree(ctx);
        //connimg = new ImageView(ctx); {
        //    connimg.setScaleType(ImageView.ScaleType.FIT_CENTER);
        //}
        //canvas.addView(connimg, 0);
        //text_view_t text_view = new text_view_t(ctx, 0); {
        //    text_view.setText("Connections");
        //    text_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        //}
        //canvas.addView(text_view);
        return r;
    }

    //public ImageView connimg;
    account_breakdown__list_view_t.itemclick_listener_t listener = null;

}

