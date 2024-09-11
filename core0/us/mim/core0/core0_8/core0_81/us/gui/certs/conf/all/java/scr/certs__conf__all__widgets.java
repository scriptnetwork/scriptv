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
//MIM    'classname': 'certs__conf__all'
//MIM    'include': 'import java.util.Arr...'
//MIM  kickoff code hash: 3hBFU7fdC2ZLYABDL8cX4kMxeAj1 (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
import android.content.Context;                                                                // Context
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import us.cash.R;
import us.cash.CFG;
import android.util.TypedValue;                                                                // TypedValue
//MIM begin token 'include'
import java.util.ArrayList;
import static us.gov.crypto.ripemd160.hash_t;
import us.pair;
import us.string;
//MIM end token 'include'

public class certs__conf__all__widgets extends certs__conf__all0__widgets  {

    private static void log(final String s) {                        //--strip
        us.cash.CFG.log_scr("certs__conf__all__widgets: " + s);            //--strip
    }                                                                //--strip

    public certs__conf__all__widgets(
            final list_view_t.itemclick_listener_t list_view__listener_,
            final certs__conf__all__list_view__itemview__widgets.listener_t itemview_listener_
        ) {
        super();
        list_view__listener = list_view__listener_;
        itemview_listener = itemview_listener_;
    }

    @Override public ViewGroup create_tree(Context ctx) { //aka inflate
        assert papyrus == null;
        papyrus = new certs__conf__all__list_view(ctx, list_view__listener, itemview_listener);
        return papyrus;
    }

    public certs__conf__all__list_view list_view;

    list_view_t.itemclick_listener_t list_view__listener;
    certs__conf__all__list_view__itemview__widgets.listener_t itemview_listener;

}

