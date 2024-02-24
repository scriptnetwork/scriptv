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
//MIM    'classname': 'device_endpoints__conf'
//MIM    'include': ''
//MIM  kickoff code hash: 4MsmUURBHArgYs7WpHhoXNLYEM7L (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
import android.content.Context;                                                                // Context
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import us.cash.R;
import us.cash.CFG;
import android.util.TypedValue;                                                                // TypedValue
import static us.gov.crypto.ripemd160.hash_t;
import us.pair;
import us.string;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.Gravity;
import android.view.MotionEvent;

public class device_endpoints__conf__widgets extends device_endpoints__conf0__widgets  {

    private static void log(final String s) {                        //--strip
        us.cash.CFG.log_scr("device_endpoints__conf__widgets: " + s);            //--strip
    }                                                                //--strip

    public device_endpoints__conf__widgets(
            final list_view_t.itemclick_listener_t list_view__listener_,
            final device_endpoints__conf__list_view__itemview__widgets.listener_t itemview_listener_
        ) {
        super();
        list_view__listener = list_view__listener_;
        itemview_listener = itemview_listener_;
    }

    @Override public ViewGroup create_tree(Context ctx) { //aka inflate
        assert papyrus == null;
        papyrus = new canvas_t(ctx, 10, 1); {
            connimg = new ImageView(ctx); {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, us.cash.app.a.le.dp2px(150));
                params.gravity = Gravity.CENTER;
                connimg.setLayoutParams(params);
                //connimg.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                connimg.setImageResource(us.cash.app.a.le.resolve_resid(R.raw.conn_ico));
                //connimg.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
                connimg.setAdjustViewBounds(true);
                connimg.setScaleType(ImageView.ScaleType.FIT_CENTER);
                connimg.setOnTouchListener(new View.OnTouchListener() {
                    @Override public boolean onTouch(final View view, final MotionEvent motionEvent) {
                        connimg.requestLayout();
                        int compressed_pic_height = 100;
                        if (connimg.getLayoutParams().height == compressed_pic_height) {
                            connimg.getLayoutParams().height = pic_height;
                        }
                        else {
                            pic_height = connimg.getLayoutParams().height;
                            connimg.getLayoutParams().height = compressed_pic_height;
                        }
                        return true;
                    }
                });

            }
            list_view = new device_endpoints__conf__list_view(ctx, list_view__listener, itemview_listener); {
            }
            papyrus.addView(connimg, 0);
            papyrus.addView(list_view);
        }
        return papyrus;
    }

    public ImageView connimg;
    int pic_height = 0;
    public device_endpoints__conf__list_view list_view;

    list_view_t.itemclick_listener_t list_view__listener;
    device_endpoints__conf__list_view__itemview__widgets.listener_t itemview_listener;

}

