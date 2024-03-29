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
//MIM        file: core0/core0_6/core0_61/us/gui/activity/listview_controller/list/java/scr/[classname]__itemview__widgets.java
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/listview_controller/list/mim.h
//MIM  Params:
//MIM    'classname': 'nodes__conf__bookmarks'
//MIM    'include': 'import static us.gov...'
//MIM    'itemico': 'R.raw.bookmark' @ core0/core0_8/core0_85/us/gui/nodes/conf/bookmarks/mim.h
//MIM  kickoff code hash: 3yHmsxNRGXzCCawytcGeN4fcLyH (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
import android.content.Context;                                                                // Context
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;
import android.util.TypedValue;                                                                // TypedValue
import us.cash.R;
import us.cash.CFG;
import android.view.Gravity;
import android.widget.ImageView;
import android.view.ViewGroup.LayoutParams;                                                    // LayoutParams
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
//MIM begin token 'include'
import static us.gov.crypto.ripemd160.hash_t;
import us.wallet.trader.bootstrap.protocols_t;
//MIM end token 'include'

public class nodes__conf__bookmarks__itemview__widgets extends list_view__itemview__widgets {

    private static void log(final String s) {                             //--strip
        CFG.log_scr("nodes__conf__bookmarks__itemview__widgets: " + s);            //--strip
    }                                                                     //--strip


    public static interface listener_t {
        //TODO: customize functions
        void on_confitem_button_click(int pos);
    }

    public nodes__conf__bookmarks__itemview__widgets(listener_t listener_) {
        listener = listener_;
    }

    //    +-----+------------------------------+-----+
    //    | ico | head                         | btn |
    //    |     |------------------------------+-----+
    //    |     | tail                               |
    //    +-----+------------------------------------+
    @Override public ViewGroup create_tree(Context ctx) {
        super.create_tree(ctx); {
            canvas_t left = new canvas_t(ctx, 12, 1); {
                ico = new button_t(ctx, R.raw.bookmark, null); {
                    final int sz = us.cash.app.a.le.dp2px(25);
                    LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(sz, sz);
                    layout.setMargins(0, 0, us.cash.app.a.le.dp2px(8), 0);
                    ico.setLayoutParams(layout);
                    ico.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
                left.addView(ico);
            }
            canvas_t right = new canvas_t(ctx, 13, 1); {
                canvas_t rt = new canvas_t(ctx, 13, 0); {
                    head = new text_view_t(ctx, 5); {
                        head.setTextSize(TypedValue.COMPLEX_UNIT_DIP, us.cash.app.a.le.lists_text_size_header);
                    }
                    button_t confitem_button = new button_t(ctx, R.raw.gear, new View.OnClickListener() {
                        @Override public void onClick(View v) {
                            log("on_confitem_button_click"); //--strip
                            if (listener == null) return;
                            listener.on_confitem_button_click(list_view__itemview.getBindingAdapterPosition());
                        }
                    }); {
                    }
                    rt.addView(head);
                    rt.addView(confitem_button);
                }
                tail = new text_view_t(ctx, 1); {
                    tail.setTextSize(TypedValue.COMPLEX_UNIT_DIP, us.cash.app.a.le.lists_text_size);
                }
                right.addView(rt);
                right.addView(tail);
            }
            papyrus.addView(left);
            papyrus.addView(right);

        }
        return papyrus;
    }

    public button_t ico;
    public text_view_t head;
    public text_view_t tail;

    listener_t listener;
}
