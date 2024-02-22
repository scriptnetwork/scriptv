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

public class ##classname##__itemview_t extends list_view__itemview_t {  //view_holder

    private static void log(final String s) {                    //--strip
        CFG.log_scr("##classname##__itemview_t: " + s);          //--strip
    }                                                            //--strip

    public ##classname##__itemview_t(Context ctx, ##classname##__itemview__widgets w, final us.cash.##classname##__adapter_t adapter) {
        super(w.create_tree(ctx), adapter);
        this.w = w;
        this.adapter = adapter;
        final ##classname##__itemview_t self = this;
        log("adding button listeners"); //--strip
        w.papyrus.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                log("conf.onClick"); //--strip
                int position = getAdapterPosition();
                if (position == RecyclerView.NO_POSITION) return;
                adapter.listener.on_papyrus(position);
            }
        });
    }

    @Override public View get_papyrus() {
        return w.papyrus;
    }

    @Override public void bind(int pos) {
        super.bind(pos);
        log("bind pos=" + pos); //--strip
        //TODO: fill widgets at w.*
    }

    ##classname##__itemview__widgets w;
    us.cash.##classname##__adapter_t adapter;
    
}

