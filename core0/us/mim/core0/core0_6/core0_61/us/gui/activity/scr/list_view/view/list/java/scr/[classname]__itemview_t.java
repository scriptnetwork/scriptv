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
import android.view.View;                                                                      // View
import androidx.recyclerview.widget.RecyclerView;
import us.cash.R;
import us.cash.CFG;
import android.graphics.Bitmap;                                                                // Bitmap
import us.cash.##itemtype##;
##include##

public final class ##classname##__itemview_t extends list_view__itemview_t {

    private static void log(final String s) {                    //--strip
        CFG.log_scr("##classname##__itemview_t: " + s);          //--strip
    }                                                            //--strip

    public ##classname##__itemview_t(Context ctx, ##classname##__itemview__widgets w, final ##classname##__adapter_t adapter) {
        super(ctx, w, adapter);
        this.w = w;
        this.adapter = adapter;
    }

    @Override public void bind(int pos) {
        super.bind(pos);
        log("bind pos=" + pos); //--strip
        final ##itemtype## item = adapter.get_item(pos);
        //TODO: Customize here. example:
##itemview_bind_example_code##
    }

    ##classname##__itemview__widgets w = null;
    ##classname##__adapter_t adapter = null;
}

