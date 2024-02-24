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
// ***************************************
// EDITABLE FILE. Changes on this file will NOT be overwritten by MIM.
// The first version of this file was produced by MIM. You can edit it afterwards.
// Modifying review_serial in either MIM nodes (template spec or instance) will trigger a sync process (meld) on the next MIM run.
// Template spec MIM node: mim/core0/core0_7/us/gui/activity/fragmented_activity/template1/java/scr/[classname]__widgets.java
// Template instance params:
//   MIM file: "core0/core0_8/core0_86/us/gui/qr/conf/mim.h"
//   classname: "qr__conf"
//   title: "QR"
// ***************************************
package us.cash.scr;
import android.content.Context;                                                                // Context
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import us.cash.R;
import us.cash.CFG;
import android.util.TypedValue;                                                                // TypedValue


public class qr__conf__widgets extends fragmented_activity__widgets {

    private static void log(final String s) {                        //--strip
        us.cash.CFG.log_scr("qr__conf__widgets: " + s);            //--strip
    }                                                                //--strip

    public qr__conf__widgets() {
        super();
    }

    @Override public ViewGroup create_tree(Context ctx) { //aka inflate
        return super.create_tree(ctx);
    }

}

