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
//MIM        file: core0/core0_6/core0_61/us/gui/activity/fragmented_controller/java/scr/[classname]__widgets.java
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/fragmented_controller/mim.h
//MIM  Params:
//MIM    'classname': 'trader__conf__impl'
//MIM    'include': ''
//MIM  kickoff code hash: 3u7Msj9RfBYaJqH6NH9r1PDMGMFA (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
import android.content.Context;                                                                // Context
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import us.cash.R;
import us.cash.CFG;
import android.util.TypedValue;                                                                // TypedValue
import android.view.Gravity;
import android.graphics.Typeface;
import com.google.android.material.tabs.TabLayout;
import android.graphics.Color;

public class trader__conf__impl__widgets extends trader__conf__impl__base__widgets {

    private static void log(final String s) {                        //--strip
        us.cash.CFG.log_scr("trader__conf__impl__widgets: " + s);            //--strip
    }                                                                //--strip

    public trader__conf__impl__widgets() {
        super();
    }

    @Override public ViewGroup create_tree(Context ctx) { //aka inflate
        papyrus = new canvas_t(ctx, 11, 1); {
            error_msg = new text_view_t(ctx, 0); {
                error_msg.setVisibility(View.GONE);
                error_msg.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
                error_msg.setGravity(Gravity.CENTER_HORIZONTAL);
                error_msg.setTypeface(null, Typeface.BOLD);
                error_msg.setTextColor(Color.RED);
            }
            header = new text_view_t(ctx, 1); {
                header.setVisibility(View.GONE);
                header.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                header.setGravity(Gravity.CENTER_HORIZONTAL);
                header.setTypeface(null, Typeface.BOLD);
            }
            tabs = new tabs_t(ctx); {
                tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
            }
            frame = new frame_t(ctx, 1); {
            }
            papyrus.addView(error_msg);
            papyrus.addView(header);
            papyrus.addView(tabs);
            papyrus.addView(frame);
        }
        return papyrus;
    }

    public void show_header(final String text) {
        header.setText(text);
        header.setVisibility(View.VISIBLE);
        showing_header = true;
    }

    public void set_error(String msg) {
        if (msg == null) {
            error_msg.setVisibility(View.GONE);
            if (showing_header) {
                header.setVisibility(View.VISIBLE);
            }
            else {
                header.setVisibility(View.GONE);
            }
            tabs.setVisibility(View.VISIBLE);
            frame.setVisibility(View.VISIBLE);
            return;
        }
        error_msg.setVisibility(View.VISIBLE);
        error_msg.setText(msg);
        header.setVisibility(View.GONE);
        tabs.setVisibility(View.GONE);
        frame.setVisibility(View.GONE);
    }
    
    boolean showing_header = false;
    private text_view_t error_msg;
    private text_view_t header;
    public tabs_t tabs;
    public frame_t frame;

}

