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
//MIM        file: core0/core0_6/core0_61/us/gui/activity/scr/list_view/template1/java/scr/[classname]__itemview_t.java
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/scr/list_view/template1/mim.h
//MIM  Params:
//MIM    'classname': 'timeseries__view'
//MIM    'include': 'import us.stdint.uin...'
//MIM    'itemico': 'R.raw.timeseries_entry' @ core0/core0_8/core0_83/us/gui/timeseries/view/mim.h
//MIM    'itemtype': 'uint64_t' @ core0/core0_8/core0_83/us/gui/timeseries/view/mim.h
//MIM  kickoff code hash: 2VFJXikMmGzu55p4rCcMxYxiBJ8D (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
import android.content.Context;                                                                // Context
import android.view.View;                                                                      // View
import androidx.recyclerview.widget.RecyclerView;
import us.cash.R;
import us.cash.CFG;
//MIM begin token 'include'
import us.stdint.uint64_t;
import us.wallet.wallet.timeseries_entry_index_t;
//MIM end token 'include'

public final class timeseries__view__itemview_t extends list_view__itemview_t {

    private static void log(final String s) {                    //--strip
        CFG.log_scr("timeseries__view__itemview_t: " + s);          //--strip
    }                                                            //--strip

    public timeseries__view__itemview_t(Context ctx, timeseries__view__itemview__widgets w, final us.cash.timeseries__view__adapter_t adapter) {
        super(ctx, w, adapter);
        this.w = w;
        this.adapter = adapter;
    }

    @Override public void bind(int pos) {
        super.bind(pos);
        log("bind pos=" + pos); //--strip
        final uint64_t item = adapter.get_item(pos);
        //TODO: Customize here. example:
        //w.ico.setImageResource(us.cash.app.a.le.resolve_resid(R.raw.timeseries_entry));
        w.head.setText("" + item.value);
        w.tail.setText("" + item.value);
    }

    timeseries__view__itemview__widgets w;
    us.cash.timeseries__view__adapter_t adapter;
}

