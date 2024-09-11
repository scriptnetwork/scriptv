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
import us.cash.##itemtype##;
import us.cash.##datatype##;
import us.cash.CFG;

##include##

public class ##classname## extends canvas_t {

    private static void log(final String line) {           //--strip
        CFG.log_scr("##classname##: " + line);         //--strip
    }                                                      //--strip

    public ##classname##(Context ctx, final list_view_t.itemclick_listener_t listener, ##classname##__itemview__widgets.listener_t itemview_listener_) {
        super(ctx, 10, canvas_t.V);
        log("##classname## constructor"); //--strip
        w = new ##classname##__widgets(ctx, listener, itemview_listener_);
        addView(w.empty_list);
        addView(w.list);
        adapter = create_adapter();
        w.list.setAdapter(adapter);
    }

    public ##classname##__itemview__widgets create_itemview__widgets() {
        return new us.cash.scr.##classname##__itemview__widgets(w.itemview_listener);
    }

    public us.cash.scr.list_view__itemview_t create_itemview(Context ctx) {
         return new us.cash.scr.##classname##__itemview_t(ctx, create_itemview__widgets(), adapter);
    }

    public ##classname##__adapter_t create_adapter() {
        log("create_adapter"); //--strip
        adapter = new ##classname##__adapter_t(this);
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
    
    public void set_data(##datatype## data) {
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

    public void set_conn_ico(##itemtype## item, final int led_state, final String msg) {
        adapter.set_conn_ico(item, led_state, msg);
    }
    
    public ##itemtype## get_item(int pos) {
        return adapter.get_item(pos);
    }

    public void toggle_sel(int pos) {
        adapter.toggle_sel(pos);
    }
    
##highlight_nft__def##

    protected us.cash.scr.##classname##__widgets w = null;
    public ##classname##__adapter_t adapter = null;
}

