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
import android.util.TypedValue;                                                                // TypedValue
import us.cash.R;
import us.cash.CFG;

public class account_breakdown__itemview__widgets extends account_breakdown0__itemview__widgets {

    private static void log(final String s) {                             //--strip
        CFG.log_scr("account_breakdown__itemview__widgets: " + s);            //--strip
    }                                                                     //--strip

    @Override public ViewGroup create_tree(Context ctx) { //aka inflate
        ViewGroup r = super.create_tree(ctx);
        //    label = new text_view_t(ctx, 1); {
        //    label.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
	//papyrus.addView(label);
        return r;
    }

    //public text_view_t label;
}

