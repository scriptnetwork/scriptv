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
//MIM    'classname': 'trader__conf__chat'
//MIM    'conf_button__click_handler': 'void on_confitem_but...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM    'data_identifier': 'chat' @ core0/core0_8/core0_86/us/gui/trader/conf/chat/mim.h
//MIM    'datatype': 'trader__conf__chat0__datatype_t' @ core0/core0_8/core0_86/us/gui/trader/conf/chat/mim.h
//MIM    'highlight_nft__def': '' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM    'include': 'import static us.wal...'
//MIM    'itemtype': 'trader__conf__chat0__itemtype_t' @ core0/core0_8/core0_86/us/gui/trader/conf/chat/mim.h
//MIM    'nft_support__bind': '' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM    'popups': 'public void popup_me...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM  kickoff code hash: aNeXoxCoen7t422sDBLprG5swpD (change this hash to force a review)
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
import static us.wallet.trader.chat.chat_entry;
import static us.wallet.trader.chat.chat_t;
//MIM end token 'include'
import static us.gov.crypto.ripemd160.hash_t;
import static us.stdint.*;
import static us.gov.io.types.blob_t;

public class trader__conf__chat extends trader__conf__chat0 {

    private static void log(final String line) {           //--strip
        CFG.log_android("trader__conf__chat: " + line);         //--strip
    }                                                      //--strip

    public trader__conf__chat() {
        super();
        log("trader__conf__chat constructor"); //--strip
    }

    //--------------lifecycle-------------------------------------------------------
    @Override protected void controller__on_create(Bundle saved_state) {  //create/resume order: 1-general class; 2-specialized class
        log("on_create"); //--strip
        adapter = create_adapter();
        assert adapter != null; //--strip
        super.controller__on_create(saved_state);
        w = (us.cash.scr.trader__conf__chat__widgets) super.w;
        assert w != null; //--strip
        tr = (trader__conf)ac;
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

    public void bind(final trader__conf__chat0__datatype_t o) {
        log("bind"); //--strip
        adapter.scrolldown();
    }

    @Override public us.cash.scr.view__widgets create_widgets() {
        final trader__conf__chat self = this;
        return new us.cash.scr.trader__conf__chat__widgets(new us.cash.scr.list_view_t.itemclick_listener_t() {
            @Override public void on_item_click(View view, int position) {
                self.on_item__click(position);
            }
            @Override public void on_long_item_click(View view, int position) {
                self.on_item__long_click(position);
            }
            @Override public boolean on_highlighted_item(int pos) {
                return self.on_item__highlighted(pos);
            }
        },
        new View.OnClickListener() {
            @Override public void onClick(View v) {
                String msg = self.w.input.getText().toString().trim();
                send_message(msg);
                self.w.input.setText("");
                //self.ac.hide_keyboard();
            }
        });
    }

    us.cash.scr.trader__conf__chat__itemview__widgets create_itemview__widgets() {
        final trader__conf__chat self = this;
        return new us.cash.scr.trader__conf__chat__itemview__widgets(new us.cash.scr.trader__conf__chat__itemview__widgets.listener_t() {
            @Override public void on_confitem_button_click(int position) {
                log("on_confitem_button_click " + position); //--strip
                self.on_confitem_button_click(position);
            }
        });
    }

    public us.cash.scr.list_view__itemview_t create_itemview(Context ctx) {
         return new us.cash.scr.trader__conf__chat__itemview_t(ctx, create_itemview__widgets(), adapter);
    }

    public trader__conf__chat__adapter_t create_adapter() {
        log("create_adapter");
        adapter = new trader__conf__chat__adapter_t(this);
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
        //trader__conf__chat0__itemtype_t item = adapter.get_item(pos);
    }

    boolean on_item__highlighted(int pos) {
        log("on_item__highlighted_item pos " + pos); //--strip
        //trader__conf__chat0__itemtype_t item = adapter.get_item(pos);
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
        adapter.set_data(chat);
        bind(chat);
    }

    //MIM begin token 'popups'
    public void popup_menu__conf(final int pos) {
        final trader__conf__chat0__itemtype_t item = adapter.get_item(pos);
        toast("KO 40010 Not implemented.");
    }
    //MIM end token 'popups'

    @Override public void on_push(final hash_t target_tid, final uint16_t code, final byte[] payload) {
        log("on_push"); //--strip
        a.assert_worker_thread(); //--strip
        if (!tr.tid.equals(target_tid)) {
            return;
        }
        switch(code.value) {
            case us.wallet.trader.trader_t.push_chat: {
                log("a chat for me. "); //--strip
                chat_t apichat = new chat_t();
                ko r = us.gov.io.blob_reader_t.parse(new blob_t(payload), apichat);
                if (!is_ko(r)) {
                    chat = new trader__conf__chat0__datatype_t(apichat);
                }
                on_ready__worker(r);
            }
            break;
        }
    }

    void send_message(final String msg) {
        toast("send " + msg);
        if (msg.isEmpty()) {
            return;
        }
        String escaped = msg.replace("\n", "\\n");
        tr.command_trade("msg " + escaped);
    }

    protected us.cash.scr.trader__conf__chat__widgets w = null;
    public trader__conf__chat__adapter_t adapter = null;
    trader__conf tr;

}

