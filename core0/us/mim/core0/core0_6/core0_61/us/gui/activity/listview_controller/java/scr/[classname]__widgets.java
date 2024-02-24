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

public class ##classname##__widgets extends ##classname##0__widgets  {

    private static void log(final String s) {                        //--strip
        us.cash.CFG.log_scr("##classname##__widgets: " + s);            //--strip
    }                                                                //--strip

    public ##classname##__widgets(
            final list_view_t.itemclick_listener_t list_view__listener_,
            final ##classname##__list_view__itemview__widgets.listener_t itemview_listener_
        ) {
        super();
        list_view__listener = list_view__listener_;
        itemview_listener = itemview_listener_;
    }

    @Override public ViewGroup create_tree(Context ctx) { //aka inflate
        assert papyrus == null;
        papyrus = new ##classname##__list_view(ctx, list_view__listener, itemview_listener);
        return papyrus;
    }

    public ##classname##__list_view list_view;

    list_view_t.itemclick_listener_t list_view__listener;
    ##classname##__list_view__itemview__widgets.listener_t itemview_listener;

}

