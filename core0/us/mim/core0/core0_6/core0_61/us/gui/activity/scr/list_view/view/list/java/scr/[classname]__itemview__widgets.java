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
##include##

public class ##classname##__itemview__widgets extends list_view__itemview__widgets {

    private static void log(final String s) {                             //--strip
        CFG.log_scr("##classname##__itemview__widgets: " + s);            //--strip
    }                                                                     //--strip


    public static interface listener_t {
        //TODO: customize functions
        void on_confitem_button_click(int pos);
    }

    public ##classname##__itemview__widgets(listener_t listener_) {
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
                ico = new button_t(ctx, ##itemico##, null); {
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
