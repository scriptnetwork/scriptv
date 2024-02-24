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
//MIM        file: core0/core0_6/core0_61/us/gui/activity/scr/list_view/view/java/scr/[classname].java
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/scr/list_view/view/mim.h
//MIM  Params:
//MIM    'classname': 'device_endpoints__conf__list_view'
//MIM    'datatype': 'device_endpoints_t' @ core0/core0_7/us/gui/device_endpoints/mim.h
//MIM    'highlight_nft__def': '' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM    'include': ''
//MIM    'itemtype': 'device_endpoint_t' @ core0/core0_7/us/gui/device_endpoints/mim.h
//MIM  kickoff code hash: 4MHxDpfHMa6fbcV35UT9pK2hANn1 (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
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
import us.cash.device_endpoint_t;
import us.cash.device_endpoints_t;


public class device_endpoints__conf__list_view extends canvas_t {

    private static void log(final String line) {           //--strip
        CFG.log_scr("device_endpoints__conf__list_view: " + line);         //--strip
    }                                                      //--strip

    public device_endpoints__conf__list_view(Context ctx, final list_view_t.itemclick_listener_t listener, device_endpoints__conf__list_view__itemview__widgets.listener_t itemview_listener_) {
        super(ctx, 10, canvas_t.V);
        log("device_endpoints__conf__list_view constructor"); //--strip
        w = new device_endpoints__conf__list_view__widgets(ctx, listener, itemview_listener_);
        addView(w.empty_list);
        addView(w.list);
        adapter = create_adapter();
        w.list.setAdapter(adapter);
    }

    public device_endpoints__conf__list_view__itemview__widgets create_itemview__widgets() {
        return new us.cash.scr.device_endpoints__conf__list_view__itemview__widgets(w.itemview_listener);
    }

    public us.cash.scr.list_view__itemview_t create_itemview(Context ctx) {
         return new us.cash.scr.device_endpoints__conf__list_view__itemview_t(ctx, create_itemview__widgets(), adapter);
    }

    public device_endpoints__conf__list_view__adapter_t create_adapter() {
        log("create_adapter"); //--strip
        adapter = new device_endpoints__conf__list_view__adapter_t(this);
        log("create_adapter returns = " + adapter);  //--strip
        return adapter;
    }

    public void notify_dataset_changed() {
        assert w.list.getAdapter() != null; //--strip
        //if (w.list.getAdapter() == null) {
        //    w.list.setAdapter(adapter);
        //}
        //else {
            adapter.notifyDataSetChanged();
        //}
    }

    public void notify_item_inserted() {
        adapter.notifyItemInserted(adapter.getItemCount() -1);
        adapter.scrolldown_flash();
    }

    public void notify_item_changed(int pos) {
        adapter.notifyItemChanged(pos);
    }

    public void notify_item_removed(int pos) {
        adapter.notifyItemRemoved(pos);
    }
    
    public void set_data(device_endpoints_t data) {
        adapter.set_data(data);
        adapter.notifyDataSetChanged();
    }
    
    public void scrolldown_flash() {
        adapter.scrolldown_flash();
    } 

    public void flash(int pos) {
        adapter.flash(pos);
    } 

    public void set_conn_ico(int pos, final int led_state, final String msg) {
        adapter.set_conn_ico(pos, led_state, msg);
    }

    public void set_conn_ico(device_endpoint_t item, final int led_state, final String msg) {
        adapter.set_conn_ico(item, led_state, msg);
    }
    
    public device_endpoint_t get_item(int pos) {
        return adapter.get_item(pos);
    }

    public void toggle_sel(int pos) {
        adapter.toggle_sel(pos);
    }
    

    protected us.cash.scr.device_endpoints__conf__list_view__widgets w = null;
    public device_endpoints__conf__list_view__adapter_t adapter = null;
}

