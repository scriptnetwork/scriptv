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
##include##

public class ##classname##0__widgets extends view__widgets {

    private static void log(final String s) {                   //--strip
        CFG.log_scr("##classname##__widgets: " + s);            //--strip
    }                                                           //--strip

    public ##classname##__widgets(final ##classname##__list_view_t.itemclick_listener_t list_listener_) {
        this.list_listener = list_listener_;
    }

    public ##classname##__list_view_t create_list(Context ctx) {
	    return new ##classname##__list_view_t(ctx, list_listener);
    }

    @Override public ViewGroup create_tree(Context ctx) { //aka inflate
        assert canvas == null;
        canvas_t canvas = new canvas_t(ctx, 10, 1); {
            list = create_list(ctx);
            canvas.addView(list);
        }
        return canvas;
    }

    public ##classname##__list_view_t.itemclick_listener_t list_listener = null;

}

