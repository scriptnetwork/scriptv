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
//MIM        file: core0/core0_6/core0_61/us/gui/activity/controller/java/scr/[classname]__widgets.java
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM  Params:
//MIM    'classname': 'trades__conf__myqr'
//MIM    'create_tree': '@Override public Vie...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'include': 'import us.wallet.tra...'
//MIM  kickoff code hash: 4BX35FCwUYiYc7cZvCj5PGrVmP3K (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
import android.content.Context;                                                                // Context
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import us.cash.R;
import us.cash.CFG;
import android.util.TypedValue;                                                                // TypedValue
//MIM begin token 'include'
import us.wallet.trader.bookmark_t;
import us.wallet.trader.bookmarks_t;
//MIM end token 'include'
import android.text.InputType;
import android.view.Gravity;
import android.widget.ImageView;
import android.view.ViewGroup.LayoutParams;                                                    // LayoutParams
import android.widget.LinearLayout;

public class trades__conf__myqr__widgets extends view__widgets {

    private static void log(final String s) {                        //--strip
        us.cash.CFG.log_scr("trades__conf__myqr__widgets: " + s);            //--strip
    }                                                                //--strip

    public trades__conf__myqr__widgets() {
        super();
    }

    //MIM begin token 'create_tree'
    @Override public ViewGroup create_tree(Context ctx) {
        create_papyrus(ctx);
        assert papyrus != null; //--strip
        scroll = new scroll_view_t(ctx);
        scroll.addView(papyrus);
        return scroll;
    }

    private void create_papyrus(Context ctx) {
        papyrus = new canvas_t(ctx, 7, 1); {
            page = new text_view_t(ctx, 1); { //vwrap
                //page.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
                //page.setHint("");
                page.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                page.setTextSize(20);
                page.setInputType(InputType.TYPE_NULL);
                page.setKeyListener(null);
            }
            canvas_t header = new canvas_t(ctx, 7, 0); { //H
                header.setGravity(Gravity.CENTER_HORIZONTAL);
                pic = new ImageView(ctx); {
                    pic.setLayoutParams(new ViewGroup.LayoutParams(100, 100));
                    pic.setScaleType(ImageView.ScaleType.FIT_XY);
                    pic.setImageResource(R.raw.loading_role_img);
                    //pic.setGravity(Gravity.TOP);  // Set gravity
                }
                qrlabel = new text_view_t(ctx, 1); { //vwrap
                    //page.setHint("");
                    qrlabel.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    qrlabel.setTextSize(30);
                    qrlabel.setTextIsSelectable(false);
                    qrlabel.setInputType(InputType.TYPE_NULL);
                    qrlabel.setKeyListener(null);
                }
                header.addView(pic);
                header.addView(qrlabel);
            }
            qrtext = new edit_text_t(ctx, 2); { //vwrap
                qrtext.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                qrtext.setTextSize(16);
                qrtext.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                qrtext.setLines(3);
                qrtext.setMinLines(2);
                qrtext.setMaxLines(4);
                qrtext.setVerticalScrollBarEnabled(true);
                qrtext.setHorizontallyScrolling(false);
                qrtext.setSelectAllOnFocus(true);            
                qrtext.setInputType(InputType.TYPE_NULL);
                qrtext.setTextIsSelectable(true);
                qrtext.setKeyListener(null);
            }
            text_view_t sep = new text_view_t(ctx, 1); { //vwrap
                sep.setText("------------------------------");
                sep.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                sep.setTextSize(16);
            }
            qrcode = new ImageView(ctx); {
                qrcode.setAdjustViewBounds(true);
                qrcode.setPadding(8, 0, 8, 0);
                //qrcode.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
            }
            text_view_t sep2 = new text_view_t(ctx, 1); { //vwrap
                sep2.setText("------------------------------");
                sep2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                sep2.setTextSize(16);
            }
            info = new text_view_t(ctx, 1); { //vwrap
                info.setText("This is your QR code. Other users can scan it to start a session with you.");
                info.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                info.setTextSize(16);
            }
            info2 = new text_view_t(ctx, 1); { //vwrap
                info.setText("<--> swipe for more QRs");
                info.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                info.setTextSize(12);
            }
            papyrus.addView(page);
            papyrus.addView(header);
            papyrus.addView(qrtext);
            papyrus.addView(sep);
            papyrus.addView(qrcode);
            papyrus.addView(sep2);
            papyrus.addView(info);
            papyrus.addView(info2);
        }
    }


    public scroll_view_t scroll = null;
    public canvas_t papyrus = null;
    //MIM end token 'create_tree'

    public text_view_t page;
    public ImageView pic;
    public text_view_t qrlabel;
    public edit_text_t qrtext;
    public ImageView qrcode;
    public text_view_t info;
    public text_view_t info2;

}

