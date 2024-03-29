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
//MIM        file: core0/core0_6/core0_61/us/gui/activity/listview_controller/java/[classname].java
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM  Params:
//MIM    'classname': 'iot__conf__sinks'
//MIM    'conf_button__click_handler': 'void on_confitem_but...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM    'data_identifier': 'sinks' @ core0/core0_8/core0_84/us/gui/iot/conf/sinks/mim.h
//MIM    'datatype': 'iot__conf__sinks0__datatype_t' @ core0/core0_8/core0_84/us/gui/iot/conf/sinks/mim.h
//MIM    'highlight_nft__def': 'String highlight_nft...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM    'include': 'import static us.gov...'
//MIM    'item_object_id': 'app.data_sink__object_id' @ core0/core0_8/core0_84/us/gui/iot/conf/sinks/mim.h
//MIM    'item_title': 'data sink' @ core0/core0_8/core0_84/us/gui/iot/conf/sinks/mim.h
//MIM    'itemico': 'R.raw.timeseries' @ core0/core0_8/core0_84/us/gui/iot/conf/sinks/mim.h
//MIM    'itemtype': 'iot__conf__sinks0__itemtype_t' @ core0/core0_8/core0_84/us/gui/iot/conf/sinks/mim.h
//MIM    'nft_support__bind': 'adapter.highlight_nf...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM    'popups': 'public void popup_me...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM  kickoff code hash: QfURRtVbJw3y9TEksv98yaMtoGz (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.os.Bundle;                                                                      // Bundle
import android.view.View;                                                                      // View
import android.view.LayoutInflater;                                                            // LayoutInflater
import android.view.ViewGroup;                                                                 // ViewGroup
import android.content.Context;
import us.ko;
import static us.stdint.*;
import us.pair;
import static us.ko.*;
import android.os.Handler;
import java.util.ArrayList;
import androidx.appcompat.app.AlertDialog;                                                     // AlertDialog
import android.content.DialogInterface;                                                        // DialogInterface
import android.content.Intent;                                                                 // Intent

//MIM begin token 'include'
import static us.gov.crypto.ripemd160.hash_t;
import us.wallet.wallet.timeseries_index_t;
//MIM end token 'include'

public class iot__conf__sinks extends iot__conf__sinks0 {

    private static void log(final String line) {           //--strip
        CFG.log_android("iot__conf__sinks: " + line);         //--strip
    }                                                      //--strip

    public iot__conf__sinks() {
        super();
        log("iot__conf__sinks constructor"); //--strip
    }

    //--------------lifecycle-------------------------------------------------------
    @Override protected void controller__on_create(Bundle saved_state) {  //create/resume order: 1-general class; 2-specialized class
        log("on_create"); //--strip
        adapter = create_adapter();
        assert adapter != null; //--strip
        super.controller__on_create(saved_state);
        w = (us.cash.scr.iot__conf__sinks__widgets) super.w;
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
        if (w.list.getAdapter() == null) {
            w.list.setAdapter(adapter);
        }
        else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override protected void controller__on_destroy() { //destroy/pause order: 1-specialized class; 2-general class
        log("controller__on_destroy"); //--strip
        adapter = null;
        w = null;
        super.controller__on_destroy();
    }
    //-/------------lifecycle-------------------------------------------------------

    public void bind(final iot__conf__sinks0__datatype_t o) {
        log("bind"); //--strip
        //MIM begin token 'nft_support__bind'
        adapter.highlight_nft(highlight_nft, true);
        highlight_nft = null;
        //MIM end token 'nft_support__bind'
    }

    @Override public us.cash.scr.view__widgets create_widgets() {
        final iot__conf__sinks self = this;
        return new us.cash.scr.iot__conf__sinks__widgets(new us.cash.scr.list_view_t.itemclick_listener_t() {
            @Override public void on_item_click(View view, int position) {
                self.on_item__click(position);
            }
            @Override public void on_long_item_click(View view, int position) {
                self.on_item__long_click(position);
            }
            @Override public boolean on_highlighted_item(int pos) {
                return self.on_item__highlighted(pos);
            }
        });
    }

    us.cash.scr.iot__conf__sinks__itemview__widgets create_itemview__widgets() {
        final iot__conf__sinks self = this;
        return new us.cash.scr.iot__conf__sinks__itemview__widgets(new us.cash.scr.iot__conf__sinks__itemview__widgets.listener_t() {
            @Override public void on_confitem_button_click(int position) {
                log("on_confitem_button_click " + position); //--strip
                self.on_confitem_button_click(position);
            }
        });
    }

    public us.cash.scr.list_view__itemview_t create_itemview(Context ctx) {
         return new us.cash.scr.iot__conf__sinks__itemview_t(ctx, create_itemview__widgets(), adapter);
    }

    public iot__conf__sinks__adapter_t create_adapter() {
        log("create_adapter");
        adapter = new iot__conf__sinks__adapter_t(this);
        log("create_adapter returns = " + adapter);
        return adapter;
    }

    //    +-----+------------------------------+-------+
    //    | <========================================> |
    //    | <===+=============click============+=====> +
    //    | <========================================> |
    //    +-----+--------------------------------------+
    void on_item__click(final int pos) {
        log("on_item__click pos=" + pos); //--strip
//        fragment.container_t parent = (fragment.container_t)ac;
        if (((iot__conf)ac).get_mode() == 1) {
            adapter.toggle_sel(pos);
        }
    }

    void on_item__long_click(final int pos) {
        log("on_item__long_click pos " + pos); //--strip
        //TODO: do something
        //iot__conf__sinks0__itemtype_t item = adapter.get_item(pos);
    }

    boolean on_item__highlighted(int pos) { //true: leaves it highlighted; false: changes to not highlighted  
        log("on_item__highlighted_item pos " + pos); //--strip
        iot__conf__sinks0__itemtype_t item = adapter.get_item(pos);
        ((iot__conf)ac).select(item.nft());
        return true; //true: leaves it highlighted; false: changes to not highlighted

    }

    //    +-----+------------------------------+-------+
    //    | ico | head                         | btn <-+--- click
    //    |     |------------------------------+-------+
    //    |     | tail                                 |
    //    +-----+--------------------------------------+
    //MIM begin token 'conf_button__click_handler'
    void on_confitem_button_click(int pos) {
        log("on_confitem_button_click pos " + pos); //--strip
        popup_menu__conf(pos);
    }
    //MIM end token 'conf_button__click_handler'

    @Override public void on_ready(final ko load_result) {
        log("on_ready"); //--strip
        a.assert_ui_thread(); //--strip
        assert adapter != null;
        adapter.set_data(sinks);
        bind(sinks);
    }

    //MIM begin token 'popups'
    public void popup_menu__conf(final int pos) {
        final iot__conf__sinks0__itemtype_t item = adapter.get_item(pos);
        a.show_contextual_options_for(get_context(), R.raw.timeseries, "data sink", app.data_sink__object_id, item.tail);
    }
    //MIM end token 'popups'

    @Override protected void on_menu__main__bnew() { //New Address
        hash_t o = new hash_t();
        ko r = a.hmi().rpc_peer.call_timeseries_new1(new uint8_t((short)1), o);
        if (is_ko(r)) {
            toast(r.msg);
            return;
        }
        //toast("New Addr: " + o.encode());
        if (adapter.getItemCount() == 0) {
            load();
        }
        else {
            sinks.add(o);
            adapter.notifyItemInserted(adapter.getItemCount() - 1);
            adapter.scrolldown_flash();
        }
    }
    
    
    String highlight_nft = null; //MIM token 'highlight_nft__def'
    protected us.cash.scr.iot__conf__sinks__widgets w = null;
    public iot__conf__sinks__adapter_t adapter = null;

}

