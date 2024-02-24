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
//MIM    'classname': 'business__conf'
//MIM    'create_tree': '@Override public Vie...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'include': 'import us.wallet.tra...'
//MIM  kickoff code hash: 2kEAYJfLmomAFxAhYEW2gRBQg5Zq (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
import android.content.Context;                                                                // Context
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import us.cash.R;
import us.cash.CFG;
import android.util.TypedValue;                                                                // TypedValue
//MIM begin token 'include'
import us.wallet.trader.bootstrap.protocols_t;
import us.wallet.trader.protocol_selection_t;
//MIM end token 'include'

public class business__conf__widgets extends view__widgets {

    private static void log(final String s) {                        //--strip
        us.cash.CFG.log_scr("business__conf__widgets: " + s);            //--strip
    }                                                                //--strip

    public business__conf__widgets() {
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
        papyrus = new canvas_t(ctx, 10, 1); {
            {
                canvas_t prot = new canvas_t(ctx, 2, 0); { //H
                    text_view_t label = new text_view_t(ctx, 0); {
                        label.set_text("Protocol:");
                    }
                    protocol_value = new text_view_t(ctx, 0); {
                    }
                    prot.addView(label);
                    prot.addView(protocol_value);
                }
                papyrus.addView(prot);
            }
            {
                canvas_t role = new canvas_t(ctx, 2, 0); { //H
                    text_view_t label = new text_view_t(ctx, 0); {
                        label.set_text("Role:");
                    }
                    role_value = new text_view_t(ctx, 0); {
                    }
                    role.addView(label);
                    role.addView(role_value);
                }
                papyrus.addView(role);
            }
        }
    }


    public scroll_view_t scroll = null;
    public canvas_t papyrus = null;
    //MIM end token 'create_tree'

    public text_view_t protocol_value;
    public text_view_t role_value;
}

