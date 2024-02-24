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
// Template spec MIM node: mim/core0/core0_7/us/gui/activity/fragmented_activity/template1/java/[classname].java
// Template instance params:
//   MIM file: "core0/core0_8/core0_86/us/gui/qr/conf/mim.h"
//   classname: "qr__conf"
//   title: "QR"
// ***************************************
package us.cash;
import android.os.Bundle;                                                                      // Bundle
import android.view.View;                                                                      // View
import android.view.LayoutInflater;                                                            // LayoutInflater
import android.view.ViewGroup;                                                                 // ViewGroup
import us.ko;
import static us.ko.*;
import android.os.Handler;



public final class qr__conf extends fragmented_activity {

    private static void log(final String line) {           //--strip
        CFG.log_android("qr__conf: " + line);         //--strip
    }                                                      //--strip

    public qr__conf() {
        super();
        log("qr__conf constructor"); //--strip
        set_menu_max_depth(1); //0-app
    }

    @Override public String get_title() {
        return "QR";
    }

    @Override public us.cash.scr.view__widgets create_widgets() {
        return new us.cash.scr.qr__conf__widgets();
    }

    @Override protected void add_tabs() {
        log("add_tabs"); //--strip
        add_tab("My QRs", R.raw.qr);
        add_tab("Scan QR", R.raw.qr_scan);
    }

    @Override protected fragment get_fragment(int pos) {
        log("get_fragment " + pos); //--strip
        switch(pos) {
            case 0: return new qr__conf__show();
            case 1: return new qr__conf__scan();
            default: return null;
        }
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        assert w == null; //--strip
        super.onCreate(savedInstanceState);
        log("onCreate"); //--strip
        w = (us.cash.scr.qr__conf__widgets) super.w;
        assert w != null;
        //set_menu_max_depth(1); //0-app
    }

    @Override protected void onResume() {
        super.onResume();
        log("onResume"); //--strip
    }

    @Override protected void onDestroy() {
        log("onDestroy"); //--strip
        w = null;
        super.onDestroy();
    }

    @Override public void on_tab(int pos) {
        log("on_tab"); //--strip
    }

    us.cash.scr.qr__conf__widgets w = null;
}


