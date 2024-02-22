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
//MIM    'classname': 'nodes__conf__r2r'
//MIM    'conf_button__click_handler': 'void on_confitem_but...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM    'data_identifier': 'bookmarks' @ core0/core0_8/core0_85/us/gui/nodes/conf/r2r/mim.h
//MIM    'datatype': 'nodes__conf__r2r0__datatype_t' @ core0/core0_8/core0_85/us/gui/nodes/conf/r2r/mim.h
//MIM    'highlight_nft__def': 'String highlight_nft...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM    'include': 'import static us.gov...'
//MIM    'item_object_id': 'app.wallet__node_bookmark__object_id' @ core0/core0_8/core0_85/us/gui/nodes/conf/r2r/mim.h
//MIM    'item_title': 'bookmark' @ core0/core0_8/core0_85/us/gui/nodes/conf/r2r/mim.h
//MIM    'itemico': 'R.raw.bookmark' @ core0/core0_8/core0_85/us/gui/nodes/conf/r2r/mim.h
//MIM    'itemtype': 'nodes__conf__r2r0__itemtype_t' @ core0/core0_8/core0_85/us/gui/nodes/conf/r2r/mim.h
//MIM    'nft_support__bind': 'adapter.highlight_nf...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM    'popups': 'public void popup_me...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM  kickoff code hash: vTyjKM4MBMRzmzxbwp95ZUFwXbV (change this hash to force a review)
//MIM  ******************************************************************************
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

//MIM begin token 'include'
import static us.gov.crypto.ripemd160.hash_t;
import us.wallet.trader.bootstrap.protocols_t;
//MIM end token 'include'
import us.wallet.trader.protocol_selection_t;

public class nodes__conf__r2r extends nodes__conf__r2r0 {

    private static void log(final String line) {           //--strip
        CFG.log_android("nodes__conf__r2r: " + line);         //--strip
    }                                                      //--strip

    public nodes__conf__r2r() {
        super();
        log("nodes__conf__r2r constructor"); //--strip
    }

    public nodes__conf__r2r(protocol_selection_t protocol_selection) {
        Bundle args = new Bundle();
        args.putString("protocol_selection", protocol_selection.to_string());
        setArguments(args);
    }

    //--------------lifecycle-------------------------------------------------------
    @Override protected void controller__on_create(Bundle saved_state) {  //create/resume order: 1-general class; 2-specialized class
        log("on_create"); //--strip
        adapter = create_adapter();
        assert adapter != null; //--strip
        super.controller__on_create(saved_state);
        w = (us.cash.scr.nodes__conf__r2r__widgets) super.w;
        assert w != null; //--strip
        Bundle args = getArguments();
        protocol_selection = new protocol_selection_t();
        if (args != null) {
            ko r = protocol_selection.from_string(args.getString("protocol_selection"));
            if (is_ko(r)) {
                toast("Invalid protocol_selection");
            }
        }
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

    public void bind(final nodes__conf__r2r0__datatype_t o) {
        log("bind"); //--strip
        //MIM begin token 'nft_support__bind'
        adapter.highlight_nft(highlight_nft, true);
        highlight_nft = null;
        //MIM end token 'nft_support__bind'
    }

    @Override public us.cash.scr.view__widgets create_widgets() {
        final nodes__conf__r2r self = this;
        return new us.cash.scr.nodes__conf__r2r__widgets(new us.cash.scr.list_view_t.itemclick_listener_t() {
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

    us.cash.scr.nodes__conf__r2r__itemview__widgets create_itemview__widgets() {
        final nodes__conf__r2r self = this;
        return new us.cash.scr.nodes__conf__r2r__itemview__widgets(new us.cash.scr.nodes__conf__r2r__itemview__widgets.listener_t() {
            @Override public void on_confitem_button_click(int position) {
                log("on_confitem_button_click " + position); //--strip
                self.on_confitem_button_click(position);
            }
        });
    }

    public us.cash.scr.list_view__itemview_t create_itemview(Context ctx) {
         return new us.cash.scr.nodes__conf__r2r__itemview_t(ctx, create_itemview__widgets(), adapter);
    }

    public nodes__conf__r2r__adapter_t create_adapter() {
        log("create_adapter");
        adapter = new nodes__conf__r2r__adapter_t(this);
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
        //nodes__conf__r2r0__itemtype_t item = adapter.get_item(pos);
    }

    boolean on_item__highlighted(int pos) {
        log("on_item__highlighted_item pos " + pos); //--strip
        //nodes__conf__r2r0__itemtype_t item = adapter.get_item(pos);
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
        adapter.set_data(bookmarks);
        bind(bookmarks);
    }

    //MIM begin token 'popups'
    public void popup_menu__conf(final int pos) {
        final nodes__conf__r2r0__itemtype_t item = adapter.get_item(pos);
        a.show_contextual_options_for(get_context(), R.raw.bookmark, "bookmark", app.wallet__node_bookmark__object_id, item.o);
    }
    //MIM end token 'popups'

    String highlight_nft = null; //MIM token 'highlight_nft__def'
    protected us.cash.scr.nodes__conf__r2r__widgets w = null;
    public nodes__conf__r2r__adapter_t adapter = null;
    protocol_selection_t protocol_selection;

}

