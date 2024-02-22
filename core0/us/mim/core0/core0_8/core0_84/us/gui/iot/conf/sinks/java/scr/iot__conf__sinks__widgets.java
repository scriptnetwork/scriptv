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
//MIM        file: core0/core0_6/core0_61/us/gui/activity/listview_controller/java/scr/[classname]__widgets.java
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM  Params:
//MIM    'classname': 'iot__conf__sinks'
//MIM    'include': 'import static us.gov...'
//MIM  kickoff code hash: 2jEgpaVL3wF9PiKXDAbWcqZqfUWi (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
import android.content.Context;                                                                // Context
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import us.cash.R;
import us.cash.CFG;
import android.util.TypedValue;                                                                // TypedValue
//MIM begin token 'include'
import static us.gov.crypto.ripemd160.hash_t;
import us.wallet.wallet.timeseries_index_t;
//MIM end token 'include'

public class iot__conf__sinks__widgets extends iot__conf__sinks0__widgets  {

    private static void log(final String s) {                        //--strip
        us.cash.CFG.log_scr("iot__conf__sinks__widgets: " + s);            //--strip
    }                                                                //--strip

    public iot__conf__sinks__widgets(final list_view_t.itemclick_listener_t list_listener_) {
        super();
        listener = list_listener_;
    }

    @Override public ViewGroup create_tree(Context ctx) { //aka inflate
        assert papyrus == null;
        papyrus = new canvas_t(ctx, 10, 1); {
            empty_list = new text_view_t(ctx, 1); {
                empty_list.setText("Empty");
            }
            list = new list_view_t(ctx, 2, listener, empty_list);
            papyrus.addView(empty_list);
            papyrus.addView(list);
        }
        return papyrus;
    }

    public list_view_t list;
    private text_view_t empty_list;
    private list_view_t.itemclick_listener_t listener;

}

