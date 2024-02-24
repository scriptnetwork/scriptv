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
//MIM    'classname': 'about'
//MIM    'create_tree': '@Override public Vie...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'include': ''
//MIM  kickoff code hash: 2RQRfoaR4j93tHACVhLoqt8TeR53 (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
import android.content.Context;                                                                // Context
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import us.cash.R;
import us.cash.CFG;
import android.util.TypedValue;                                                                // TypedValue
import android.graphics.Typeface;
import android.view.Gravity;

public class about__widgets extends view__widgets {

    private static void log(final String s) {                        //--strip
        us.cash.CFG.log_scr("about__widgets: " + s);            //--strip
    }                                                                //--strip

    public about__widgets() {
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
        papyrus = new canvas_t(ctx, 10, 1); { //7, 0); {
            appname = new text_view_t(ctx, 4); {
                //appname.set_text("");
                appname.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 64);
                appname.setAllCaps(true);
                appname.setTypeface(appname.getTypeface(), Typeface.BOLD);
            }
            appdescription = new text_view_t(ctx, 4); {
                //appname.set_text("");
                appdescription.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
            }
            text_view_t swv_title = new text_view_t(ctx, 4); {
                //appname.set_text("");
                swv_title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
                swv_title.setAllCaps(true);
                swv_title.setTypeface(swv_title.getTypeface(), Typeface.BOLD);
                swv_title.setText("Software version");
            }
            swv = new text_view_t(ctx, 4); {
                //appname.set_text("");
                swv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
            }
            swupdates_src = new text_view_t(ctx, 4); {
                //appname.set_text("");
                swupdates_src.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
            }
            build_from_src_header = new text_view_t(ctx, 4); {
                build_from_src_header.set_text(R.string.buildfromsources);
                build_from_src_header.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
                build_from_src_header.setAllCaps(true);
                build_from_src_header.setTypeface(build_from_src_header.getTypeface(), Typeface.BOLD);
            }
            src = new text_view_t(ctx, 4); {
                //src.set_text("");
                src.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
            }
            publisher_header = new text_view_t(ctx, 4); {
                publisher_header.set_text(R.string.publisher);
                //src.set_text("");
                publisher_header.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
                publisher_header.setAllCaps(true);
                publisher_header.setTypeface(publisher_header.getTypeface(), Typeface.BOLD);
                
            }
            publisher = new text_view_t(ctx, 4); {
                //publisher.set_text("");
                publisher.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
            }
            text_view_t android_info = new text_view_t(ctx, 4); {
                //appname.set_text("");
                android_info.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
                android_info.setAllCaps(true);
                android_info.setTypeface(android_info.getTypeface(), Typeface.BOLD);
                android_info.setText("Operating System info");
            }
            apiver = new text_view_t(ctx, 4); {
                //publisher.set_text("");
                apiver.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
            }
            text_view_t device_info_title = new text_view_t(ctx, 4); {
                //appname.set_text("");
                device_info_title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
                device_info_title.setAllCaps(true);
                device_info_title.setTypeface(device_info_title.getTypeface(), Typeface.BOLD);
                device_info_title.setText("Screen info");
            }
            device_info = new text_view_t(ctx, 4); {
                //appname.set_text("");
                device_info.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
            }
            papyrus.setGravity(Gravity.CENTER_HORIZONTAL);
            papyrus.addView(appname);
            papyrus.addView(appdescription);
            papyrus.addView(swv_title);
            papyrus.addView(swv);
            papyrus.addView(swupdates_src);
            papyrus.addView(build_from_src_header);
            papyrus.addView(src);
            papyrus.addView(publisher_header);
            papyrus.addView(publisher);
            papyrus.addView(android_info);
            papyrus.addView(apiver);
            papyrus.addView(device_info_title);
            papyrus.addView(device_info);
        }
    }


    public scroll_view_t scroll = null;
    public canvas_t papyrus = null;
    //MIM end token 'create_tree'

    public text_view_t appname;
    public text_view_t appdescription;
    public text_view_t swv;
    public text_view_t swupdates_src;
    public text_view_t build_from_src_header;
    public text_view_t src;
    public text_view_t publisher_header;
    public text_view_t publisher;
    public text_view_t apiver;
    public text_view_t device_info;
}

