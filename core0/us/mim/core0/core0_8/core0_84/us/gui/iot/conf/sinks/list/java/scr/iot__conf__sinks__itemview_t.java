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
//MIM    'classname': 'iot__conf__sinks'
//MIM    'include': 'import static us.gov...'
//MIM    'itemico': 'R.raw.timeseries' @ core0/core0_8/core0_84/us/gui/iot/conf/sinks/mim.h
//MIM    'itemtype': 'iot__conf__sinks0__itemtype_t' @ core0/core0_8/core0_84/us/gui/iot/conf/sinks/mim.h
//MIM    'itemview_bind_example_code': 'Bitmap bmp = item.ic...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM  kickoff code hash: JUys3XeKtw4HonfwrH6QqZWBLqW (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
import android.content.Context;                                                                // Context
import android.view.View;                                                                      // View
import androidx.recyclerview.widget.RecyclerView;
import us.cash.R;
import us.cash.CFG;
import android.graphics.Bitmap;                                                                // Bitmap
//MIM begin token 'include'
import static us.gov.crypto.ripemd160.hash_t;
import us.wallet.wallet.timeseries_index_t;
//MIM end token 'include'
import us.cash.iot__conf__sinks0__itemtype_t;

public final class iot__conf__sinks__itemview_t extends list_view__itemview_t {

    private static void log(final String s) {                    //--strip
        CFG.log_scr("iot__conf__sinks__itemview_t: " + s);          //--strip
    }                                                            //--strip

    public iot__conf__sinks__itemview_t(Context ctx, iot__conf__sinks__itemview__widgets w, final us.cash.iot__conf__sinks__adapter_t adapter) {
        super(ctx, w, adapter);
        this.w = w;
        this.adapter = adapter;
    }

    @Override public void bind(int pos) {
        super.bind(pos);
        log("bind pos=" + pos); //--strip
        final iot__conf__sinks0__itemtype_t item = adapter.get_item(pos);
        //TODO: Customize here. example:
        //MIM begin token 'itemview_bind_example_code'
        Bitmap bmp = item.icon();
        if (bmp == null) {
             w.ico.setImageResource(us.cash.app.a.le.resolve_resid(R.raw.timeseries));
        }
        else {
            w.ico.setImageBitmap(bmp);
        }
        w.head.setText(item.head);
        w.tail.setText(item.tail);
        //MIM end token 'itemview_bind_example_code'
    }

    iot__conf__sinks__itemview__widgets w = null;
    us.cash.iot__conf__sinks__adapter_t adapter = null;
}

