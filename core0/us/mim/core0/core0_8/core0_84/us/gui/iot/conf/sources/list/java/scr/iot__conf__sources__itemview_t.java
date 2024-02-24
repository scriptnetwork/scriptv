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
//MIM        file: core0/core0_6/core0_61/us/gui/activity/listview_controller/list/java/scr/[classname]__itemview_t.java
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/listview_controller/list/mim.h
//MIM  Params:
//MIM    'classname': 'iot__conf__sources'
//MIM    'include': 'import us.wallet.eng...'
//MIM    'itemico': 'R.raw.streams' @ core0/core0_8/core0_84/us/gui/iot/conf/sources/mim.h
//MIM    'itemtype': 'iot__conf__sources0__itemtype_t' @ core0/core0_8/core0_84/us/gui/iot/conf/sources/mim.h
//MIM    'itemview_bind_example_code': 'Bitmap bmp = item.ic...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM  kickoff code hash: 2joR7VAKqGD96TCkDFv5FtorPN62 (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
import android.content.Context;                                                                // Context
import android.view.View;                                                                      // View
import androidx.recyclerview.widget.RecyclerView;
import us.cash.R;
import us.cash.CFG;
import android.graphics.Bitmap;                                                                // Bitmap
//MIM begin token 'include'
import us.wallet.engine.data_sources_index__item_t;
import us.wallet.engine.data_sources_index_t;
//MIM end token 'include'
import us.cash.iot__conf__sources0__itemtype_t;

public final class iot__conf__sources__itemview_t extends list_view__itemview_t {

    private static void log(final String s) {                    //--strip
        CFG.log_scr("iot__conf__sources__itemview_t: " + s);          //--strip
    }                                                            //--strip

    public iot__conf__sources__itemview_t(Context ctx, iot__conf__sources__itemview__widgets w, final us.cash.iot__conf__sources__adapter_t adapter) {
        super(ctx, w, adapter);
        this.w = w;
        this.adapter = adapter;
    }

    @Override public void bind(int pos) {
        super.bind(pos);
        log("bind pos=" + pos); //--strip
        final iot__conf__sources0__itemtype_t item = adapter.get_item(pos);
        w.head.setText(item.head);
        w.tail.setText(item.tail);
        w.ico.setImageResource(item.icores);
    }

    iot__conf__sources__itemview__widgets w = null;
    us.cash.iot__conf__sources__adapter_t adapter = null;
}

