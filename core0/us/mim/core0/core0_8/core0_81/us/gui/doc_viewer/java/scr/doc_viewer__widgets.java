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
//MIM    'classname': 'doc_viewer'
//MIM    'create_tree': '@Override public Vie...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'include': ''
//MIM  kickoff code hash: 4YnzbbLdtQwrZ5NmUBSkQrHwueFw (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
import android.content.Context;                                                                // Context
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import us.cash.R;
import us.cash.CFG;
import android.util.TypedValue;                                                                // TypedValue
import android.text.InputType;

public class doc_viewer__widgets extends view__widgets {

    private static void log(final String s) {                        //--strip
        us.cash.CFG.log_scr("doc_viewer__widgets: " + s);            //--strip
    }                                                                //--strip

    public doc_viewer__widgets() {
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
    	assert papyrus == null; //--strip
        papyrus = new canvas_t(ctx, 6, 1); { //match_parent,match_parent,vertical
            title = new text_view_t(ctx, 1); {
                title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
            }

            content_ro = new text_view_t(ctx, 1); {
                content_ro.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
            }

            content_rw = new edit_text_t(ctx, 1); {
                content_rw.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
                content_rw.setHint("I certify ..."); // Set the hint text
                content_rw.setVerticalScrollBarEnabled(true); // Enable vertical scrollbars
                content_rw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE); // Set input type
                content_rw.setMaxLines(Integer.MAX_VALUE); // Set the maximum lines to Integer.MAX_VALUE (unlimited)
            }

            papyrus.addView(title);
            papyrus.addView(content_ro);
            papyrus.addView(content_rw);
        }
    }

    public scroll_view_t scroll = null;
    public canvas_t papyrus = null;
    //MIM end token 'create_tree'

    public text_view_t title;
    public text_view_t content_ro;
    public edit_text_t content_rw;

}

