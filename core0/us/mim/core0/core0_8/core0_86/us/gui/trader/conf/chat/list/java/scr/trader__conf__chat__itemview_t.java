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
//MIM    'classname': 'trader__conf__chat'
//MIM    'include': 'import static us.wal...'
//MIM    'itemico': 'R.raw.r2rtab' @ core0/core0_8/core0_86/us/gui/trader/conf/chat/mim.h
//MIM    'itemtype': 'trader__conf__chat0__itemtype_t' @ core0/core0_8/core0_86/us/gui/trader/conf/chat/mim.h
//MIM    'itemview_bind_example_code': 'w.ico.setImageResour...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM  kickoff code hash: 2XWUHe68AbjJcjLVjNoD86eKb3VL (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
import android.content.Context;                                                                // Context
import android.view.View;                                                                      // View
import androidx.recyclerview.widget.RecyclerView;
import us.cash.R;
import us.cash.CFG;
import android.graphics.Bitmap;                                                                // Bitmap
//MIM begin token 'include'
import static us.wallet.trader.chat.chat_entry;
import static us.wallet.trader.chat.chat_t;
//MIM end token 'include'
import us.cash.trader__conf__chat0__itemtype_t;
import android.view.Gravity;

public final class trader__conf__chat__itemview_t extends list_view__itemview_t {

    private static void log(final String s) {                    //--strip
        CFG.log_scr("trader__conf__chat__itemview_t: " + s);          //--strip
    }                                                            //--strip

    public trader__conf__chat__itemview_t(Context ctx, trader__conf__chat__itemview__widgets w, final us.cash.trader__conf__chat__adapter_t adapter) {
        super(ctx, w, adapter);
        this.w = w;
        this.adapter = adapter;
    }

    @Override public void bind(int pos) {
        super.bind(pos);
        log("bind pos=" + pos); //--strip
        final trader__conf__chat0__itemtype_t item = adapter.get_item(pos);
        //TODO: Customize here. example:
        //MIM begin token 'itemview_bind_example_code'
        w.head.setText(item.head);
        w.tail.setText(item.tail);
        if (item.o.me) {
            w.left.setPadding(0, 0, 0, 0);
            w.ico.setImageResource(us.cash.app.a.le.resolve_resid(R.raw.personality));
        }
        else {
            w.left.setPadding(100, 0, 0, 0);
            w.ico.setImageResource(us.cash.app.a.le.resolve_resid(R.raw.personality_peer));
        }
        //MIM end token 'itemview_bind_example_code'
    }

    trader__conf__chat__itemview__widgets w = null;
    us.cash.trader__conf__chat__adapter_t adapter = null;
}

