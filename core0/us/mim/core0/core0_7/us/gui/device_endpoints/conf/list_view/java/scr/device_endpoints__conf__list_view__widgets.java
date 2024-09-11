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
//MIM        file: core0/core0_6/core0_61/us/gui/activity/scr/list_view/view/java/scr/[classname]__widgets.java
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/scr/list_view/view/mim.h
//MIM  Params:
//MIM    'classname': 'device_endpoints__conf__list_view'
//MIM    'include': ''
//MIM  kickoff code hash: uAd9AFV65RH8un99fevD21adLjD (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
import android.content.Context;                                                                // Context
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import us.cash.R;
import us.cash.CFG;
import android.util.TypedValue;                                                                // TypedValue

public class device_endpoints__conf__list_view__widgets {

    private static void log(final String s) {                        //--strip
        us.cash.CFG.log_scr("device_endpoints__conf__list_view__widgets: " + s);            //--strip
    }                                                                //--strip

    public device_endpoints__conf__list_view__widgets(Context ctx, final list_view_t.itemclick_listener_t list_listener_, final device_endpoints__conf__list_view__itemview__widgets.listener_t itemview_listener_) {
        super();
        itemview_listener = itemview_listener_;
        papyrus = new canvas_t(ctx, 10, canvas_t.V); {
            empty_list = new text_view_t(ctx, 1); {
                empty_list.setText("Empty");
            }
            list = new list_view_t(ctx, 2, list_listener_, empty_list);
            papyrus.addView(empty_list);
            papyrus.addView(list);
        }
    }

    public canvas_t papyrus;
    public list_view_t list;
    public text_view_t empty_list;
    public device_endpoints__conf__list_view__itemview__widgets.listener_t itemview_listener;

}

