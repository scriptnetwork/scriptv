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
package us.cash;
import android.os.Bundle;                                                                      // Bundle
import android.view.View;                                                                      // View
import android.view.LayoutInflater;                                                            // LayoutInflater
import android.view.ViewGroup;                                                                 // ViewGroup
import android.content.Context;
import us.ko;
import us.pair;
import static us.ko.*;
import android.os.Handler;
import java.util.ArrayList;
import androidx.appcompat.app.AlertDialog;                                                     // AlertDialog
import android.content.DialogInterface;                                                        // DialogInterface
import android.content.Intent;                                                                 // Intent

##include##

public class ##classname## extends ##classname##0
         implements us.cash.scr.list_view_t.itemclick_listener_t,
                     us.cash.scr.##classname##__list_view__itemview__widgets.listener_t {

    private static void log(final String line) {           //--strip
        CFG.log_android("##classname##: " + line);         //--strip
    }                                                      //--strip

    public ##classname##() {
        super();
        log("##classname## constructor"); //--strip
    }

    //--------------lifecycle-------------------------------------------------------
    @Override protected void controller__on_create(Bundle saved_state) {  //create/resume order: 1-general class; 2-specialized class
        log("on_create"); //--strip
        super.controller__on_create(saved_state);
        w = (us.cash.scr.##classname##__widgets) super.w;
        assert w != null; //--strip
    }

    @Override protected void controller__on_pause() { //destroy/pause order: 1-specialized class; 2-general class
        log("controller__on_pause"); //--strip
        //custom code here
        super.controller__on_pause();
    }

    @Override protected void controller__on_resume() { //create/resume order: 1-general class; 2-specialized class
        super.controller__on_resume();
        log("controller__on_resume"); //--strip
        w.list_view.notify_dataset_changed();
    }

    @Override protected void controller__on_destroy() { //destroy/pause order: 1-specialized class; 2-general class
        log("controller__on_destroy"); //--strip
        w = null;
        super.controller__on_destroy();
    }
    //-/------------lifecycle-------------------------------------------------------

    public void bind(final ##datatype## o) {
        log("bind"); //--strip
##nft_support__bind##
    }

    @Override public us.cash.scr.view__widgets create_widgets() {
        final ##classname## self = this;
        return new us.cash.scr.##classname##__widgets(this, this);
    }

    //    +-----+------------------------------+-------+
    //    | <========================================> |
    //    | <===+=============click============+=====> +
    //    | <========================================> |
    //    +-----+--------------------------------------+
    @Override public void on_item__click(View v, final int pos) {
        log("on_item__click pos=" + pos); //--strip
        //w.list_view.toggle_sel(pos);
    }

    @Override public void on_item__long_click(View v, final int pos) {
        log("on_item__long_click pos " + pos); //--strip
        //TODO: do something
        //##itemtype## item = w.list_view.get_item(pos);
    }

    @Override public boolean on_item__highlighted(int pos) {
        log("on_item__highlighted_item pos " + pos); //--strip
        //##itemtype## item = w.list_view.get_item(pos);
        return false; //true: leaves it highlighted; false: changes to not highlighted
    }

    //    +-----+------------------------------+-------+
    //    | ico | head                         | btn <-+--- click
    //    |     |------------------------------+-------+
    //    |     | tail                                 |
    //    +-----+--------------------------------------+
##conf_button__click_handler##

    @Override public void on_ready(final ko load_result) {
        log("on_ready"); //--strip
        a.assert_ui_thread(); //--strip
        w.list_view.set_data(##data_identifier##);
        bind(##data_identifier##);
    }

##popups##

##highlight_nft__def##
    protected us.cash.scr.##classname##__widgets w = null;

}

