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
//MIM    'classname': 'iot__conf__sources'
//MIM    'conf_button__click_handler': 'void on_confitem_but...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM    'data_identifier': 'sources' @ core0/core0_8/core0_84/us/gui/iot/conf/sources/mim.h
//MIM    'datatype': 'iot__conf__sources0__datatype_t' @ core0/core0_8/core0_84/us/gui/iot/conf/sources/mim.h
//MIM    'highlight_nft__def': 'String highlight_nft...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM    'include': 'import us.wallet.eng...'
//MIM    'item_object_id': 'app.source_device__connected__object_id' @ core0/core0_8/core0_84/us/gui/iot/conf/sources/mim.h
//MIM    'item_title': 'data source' @ core0/core0_8/core0_84/us/gui/iot/conf/sources/mim.h
//MIM    'itemico': 'R.raw.streams' @ core0/core0_8/core0_84/us/gui/iot/conf/sources/mim.h
//MIM    'itemtype': 'iot__conf__sources0__itemtype_t' @ core0/core0_8/core0_84/us/gui/iot/conf/sources/mim.h
//MIM    'nft_support__bind': 'adapter.highlight_nf...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM    'popups': 'public void popup_me...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM  kickoff code hash: 2ziPdARdWzePBFjuSwLBJJnkLoGY (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.os.Bundle;                                                                      // Bundle
import android.view.View;                                                                      // View
import android.view.LayoutInflater;                                                            // LayoutInflater
import android.view.ViewGroup;                                                                 // ViewGroup
import android.content.Context;
import us.ko;
import us.pair;
import us.string;
import static us.ko.*;
import android.os.Handler;
import java.util.ArrayList;
import androidx.appcompat.app.AlertDialog;                                                     // AlertDialog
import android.content.DialogInterface;                                                        // DialogInterface
import android.content.Intent;                                                                 // Intent
import static us.gov.crypto.ripemd160.hash_t;

//MIM begin token 'include'
import us.wallet.engine.data_sources_index__item_t;
import us.wallet.engine.data_sources_index_t;
//MIM end token 'include'

public class iot__conf__sources extends iot__conf__sources0 {

    private static void log(final String line) {           //--strip
        CFG.log_android("iot__conf__sources: " + line);         //--strip
    }                                                      //--strip

    public iot__conf__sources() {
        super();
        log("iot__conf__sources constructor"); //--strip
    }

    //--------------lifecycle-------------------------------------------------------
    @Override protected void controller__on_create(Bundle saved_state) {  //create/resume order: 1-general class; 2-specialized class
        log("on_create"); //--strip
        adapter = create_adapter();
        assert adapter != null; //--strip
        super.controller__on_create(saved_state);
        w = (us.cash.scr.iot__conf__sources__widgets) super.w;
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

    public void bind(final iot__conf__sources0__datatype_t o) {
        log("bind"); //--strip
        //MIM begin token 'nft_support__bind'
        adapter.highlight_nft(highlight_nft, true);
        highlight_nft = null;
        //MIM end token 'nft_support__bind'
    }

    @Override public us.cash.scr.view__widgets create_widgets() {
        final iot__conf__sources self = this;
        return new us.cash.scr.iot__conf__sources__widgets(new us.cash.scr.list_view_t.itemclick_listener_t() {
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

    us.cash.scr.iot__conf__sources__itemview__widgets create_itemview__widgets() {
        final iot__conf__sources self = this;
        return new us.cash.scr.iot__conf__sources__itemview__widgets(new us.cash.scr.iot__conf__sources__itemview__widgets.listener_t() {
            @Override public void on_confitem_button_click(int position) {
                log("on_confitem_button_click " + position); //--strip
                self.on_confitem_button_click(position);
            }
        });
    }

    public us.cash.scr.list_view__itemview_t create_itemview(Context ctx) {
         return new us.cash.scr.iot__conf__sources__itemview_t(ctx, create_itemview__widgets(), adapter);
    }

    public iot__conf__sources__adapter_t create_adapter() {
        log("create_adapter");
        adapter = new iot__conf__sources__adapter_t(this);
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
        //adapter.toggle_sel(pos);
    }

    void on_item__long_click(final int pos) {
        log("on_item__long_click pos " + pos); //--strip
        //TODO: do something
        //iot__conf__sources0__itemtype_t item = adapter.get_item(pos);
    }

    boolean on_item__highlighted(int pos) {
        log("on_item__highlighted_item pos " + pos); //--strip
        //iot__conf__sources0__itemtype_t item = adapter.get_item(pos);
        return false; //true: leaves it highlighted; false: changes to not highlighted
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
        adapter.set_data(sources);
        bind(sources);
    }

    //MIM begin token 'popups'
    public void popup_menu__conf(final int pos) {
        final iot__conf__sources0__itemtype_t item = adapter.get_item(pos);
        if (item.connected) {
            a.show_contextual_options_for(get_context(), R.raw.streams_green, "Connected device " + item.nft(), app.source_device__connected__object_id, item);
        }
        else {
            a.show_contextual_options_for(get_context(), R.raw.streams, "Disconnected device " + item.nft(), app.source_device__disconnected__object_id, item);
        }
    }
    //MIM end token 'popups'

    void feed_wearable(final iot__conf__sources0__itemtype_t item) {
        toast("KO 40340 Health watch is not accessible.");
    }

    void feed_human(final iot__conf__sources0__itemtype_t item) {
        select_cert(item);
    }
 
    void feed(final iot__conf__sources0__itemtype_t item) {
        if (item.head.equals("wearable. health watch.")) {
            feed_wearable(item);
        }
        else if (item.head.equals("human")) {
            feed_human(item);
        }
        else {
            toast("KO 40399 input driver is unavailable.");
        }
    }

    static final int SELECTTS_RESULT = 12;
    static final int SELECTCERT_RESULT = 13;


    void connect_source(final hash_t addr) {
//        toast("connect " + cur.name.value + " " + addr.encode());
        string result = new string();
        string name = new string(tmp_item.head);
        ko r = a.hmi().rpc_peer.call_data_source_connect(new us.wallet.cli.rpc_peer_t.data_source_connect_in_t(name, addr), result);
        if (is_ko(r)) {
            log(r.msg); //--strip
            toast(r.msg);
            return;
        }
        if (!result.value.isEmpty()) {
            toast(result.value);
        }
        load();
//        toast("API called");
        //reload(true);
    }

    void disconnect_source(final iot__conf__sources0__itemtype_t item) {
        tmp_item = item;
        connect_source(new hash_t(0));
        tmp_item = null;
    }

    void feed_local_cert(final hash_t nft) {
        string result = new string();
        ko r = a.hmi().rpc_peer.call_timeseries_add_local_cert(new us.wallet.cli.rpc_peer_t.timeseries_add_local_cert_in_t(tmp_item.o.address, nft), result);
        if (is_ko(r)) {
            log(r.msg); //--strip
            toast(r.msg);
            return;
        }
        toast(result.value);
    }
    
    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        log("onActivityResult " + requestCode + " " + resultCode); //--strip

        if (resultCode != activity.RESULT_OK) {
            log("cancelled"); //--strip
            tmp_item = null;
            if (requestCode == SELECTTS_RESULT) {
                toast("Sink address selection has been cancelled by user.");
            }
            return;
        }
        if (requestCode == SELECTTS_RESULT) {
//            String addr = data.getData().toString();
            if (data.hasExtra("nft")) {
                String nft = data.getExtras().getString("nft");
                connect_source(new hash_t(nft));
            }

//            String nft = bundle.getExtra("nft"); //.toString();
//intent.putExtra(
        }
        if (requestCode == SELECTCERT_RESULT) {
//            String addr = data.getData().toString();
            if (data.hasExtra("nft")) {
                String nft = data.getExtras().getString("nft");
                toast("feeding cert " + nft);
                feed_local_cert(new hash_t(nft));
            }

//            String nft = bundle.getExtra("nft"); //.toString();
//intent.putExtra(
        }

        
        /*
        if (requestCode == CERT_VIEWER_RESULT) {
            hash_t nft = (hash_t) data.getSerializableExtra("nft");
            if (nft == null) {
                toast("Null nft");
                return;
            }
//            toast("Got certificate " + nft.encode());
            feed_local_cert(nft);
            return;
        }
        */
        tmp_item = null;
    }

    public void select_sink_address(final iot__conf__sources0__itemtype_t item) {
        log("launch_iot"); //--strip
        tmp_item = item;
        Intent intent = new Intent(ac, iot__conf.class);
        intent.putExtra("mode", 1);
        intent.putExtra("onlytab", 1);
        intent.putExtra("mode1_header", "Select sink address to connect to data source " + item.head + "...");
        //intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); //single instance
        startActivityForResult(intent, SELECTTS_RESULT);
    }

    public void select_cert(final iot__conf__sources0__itemtype_t item) {
        log("launch_iot"); //--strip
        tmp_item = item;
        Intent intent = new Intent(ac, certs__conf.class);
        intent.putExtra("mode", 1);
        //intent.putExtra("onlytab", 1);
        intent.putExtra("mode1_header", "Select cert to feed to address " + item.o.address.encode() + "...");
        //intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); //single instance
        startActivityForResult(intent, SELECTCERT_RESULT);
        
    }

    void register_actions__disconnected_device(View v) {
        iot__conf__sources self = this;
        a.actions.register_action(a.source_device__disconnected__object_id, new app.action_t(v, "Connect to wallet address", new app.action_runner_t() {
            @Override public void run(final Object nft) {
                final iot__conf__sources0__itemtype_t item = (iot__conf__sources0__itemtype_t) nft;
                self.select_sink_address(item);
            }
        }));
    }
    
    void register_actions__connected_device(View v) {
        iot__conf__sources self = this;
        a.actions.register_action(a.source_device__connected__object_id, new app.action_t(v, "Feed certificate", new app.action_runner_t() {
            @Override public void run(final Object nft) {
                final iot__conf__sources0__itemtype_t item = (iot__conf__sources0__itemtype_t) nft;
                self.feed(item);
            }
        }));
        a.actions.register_action(a.source_device__connected__object_id, new app.action_t(v, "Open address", new app.action_runner_t() {
            @Override public void run(final Object nft) {
                final iot__conf__sources0__itemtype_t item = (iot__conf__sources0__itemtype_t) nft;
                //open_address(item.o.address);
                self.toast("KO 44004 Not implemented");
            }
        }));
        a.actions.register_action(a.source_device__connected__object_id, new app.action_t(v, "Disconnect from wallet address", new app.action_runner_t() {
            @Override public void run(final Object nft) {
                final iot__conf__sources0__itemtype_t item = (iot__conf__sources0__itemtype_t) nft;
                self.disconnect_source(item);
            }
        }));
    }

    @Override public void register_actions(View v) {
        register_actions__connected_device(v);
        register_actions__disconnected_device(v);
        super.register_actions(v);
    }

    iot__conf__sources0__itemtype_t tmp_item = null;

    String highlight_nft = null; //MIM token 'highlight_nft__def'
    protected us.cash.scr.iot__conf__sources__widgets w = null;
    public iot__conf__sources__adapter_t adapter = null;

}

