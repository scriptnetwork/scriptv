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
//MIM    'classname': 'certs__conf__all'
//MIM    'conf_button__click_handler': 'void on_confitem_but...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM    'data_identifier': 'data' @ core0/core0_8/core0_81/us/gui/certs/conf/all/mim.h
//MIM    'datatype': 'certs__conf__all0__datatype_t' @ core0/core0_8/core0_81/us/gui/certs/conf/all/mim.h
//MIM    'highlight_nft__def': 'String highlight_nft...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM    'include': 'import java.util.Arr...'
//MIM    'itemtype': 'certs__conf__all0__itemtype_t' @ core0/core0_8/core0_81/us/gui/certs/conf/all/mim.h
//MIM    'nft_support__bind': 'adapter.highlight_nf...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM    'popups': '' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM  kickoff code hash: JCBLpY44wgqgqJKZuK7cCNLXGLp (change this hash to force a review)
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
import java.util.ArrayList;
import static us.gov.crypto.ripemd160.hash_t;
import us.pair;
import us.string;
//MIM end token 'include'
import static us.stdint.*;
import android.content.Intent;                                                                 // Intent
import us.ko;
import static us.ko.is_ko;
import static us.gov.io.types.blob_t;
import android.content.DialogInterface;                                                        // DialogInterface
import android.os.Bundle;
import static us.gov.crypto.ripemd160.hash_t;

public final class certs__conf__all extends certs__conf__all0 {

    private static void log(final String line) {           //--strip
        CFG.log_android("certs__conf__all: " + line);         //--strip
    }                                                      //--strip

    public certs__conf__all(int issuer_) { //0-all; 1-soa
        Bundle args = new Bundle();
        args.putInt("issuer", issuer);
        setArguments(args);
    }

    //--------------lifecycle-------------------------------------------------------
    @Override protected void controller__on_create(Bundle saved_state) {  //create/resume order: 1-general class; 2-specialized class
        log("on_create"); //--strip
        adapter = create_adapter();
        assert adapter != null; //--strip
        super.controller__on_create(saved_state);
        w = (us.cash.scr.certs__conf__all__widgets) super.w;
        assert w != null; //--strip
        certs__conf_ = (certs__conf) ac;
        Bundle args = getArguments();
        if (args != null) {
            issuer = args.getInt("issuer");
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

    public void bind(final certs__conf__all0__datatype_t o) {
        log("bind"); //--strip
        //MIM begin token 'nft_support__bind'
        adapter.highlight_nft(highlight_nft, true);
        highlight_nft = null;
        //MIM end token 'nft_support__bind'
    }

    @Override public us.cash.scr.view__widgets create_widgets() {
        final certs__conf__all self = this;
        return new us.cash.scr.certs__conf__all__widgets(new us.cash.scr.list_view_t.itemclick_listener_t() {
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

    us.cash.scr.certs__conf__all__itemview__widgets create_itemview__widgets() {
        return new us.cash.scr.certs__conf__all__itemview__widgets();
    }

    public us.cash.scr.list_view__itemview_t create_itemview(Context ctx) {
         return new us.cash.scr.certs__conf__all__itemview_t(ctx, create_itemview__widgets(), adapter);
    }

    public certs__conf__all__adapter_t create_adapter() {
        log("create_adapter");
        adapter = new certs__conf__all__adapter_t(this);
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
        adapter.toggle_sel(pos);
    }

    void on_item__long_click(final int pos) {
        log("on_item__long_click pos " + pos); //--strip
        //TODO: do something
        //certs__conf__all0__itemtype_t item = adapter.get_item(pos);
    }

    boolean on_item__highlighted(int pos) {
        log("on_item__highlighted_item pos " + pos); //--strip
        certs__conf__all0__itemtype_t item = adapter.get_item(pos);
        if (certs__conf_.mode == 1) {
            String[] options = {"Select this certificate", "View certificate", a.getResources().getString(R.string.cancel)};
            //final certs i = certs.this;
            a.new_dlg_builder(get_context()).setTitle(item.nft())
                . setItems(options, new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        switch(which) {
                            case 0:
                                certs__conf_.select(item.nft());
                                break;
                            case 1:
                                open_cert(item.nft());
                                break;
                        }
                    }
                }).setIcon(a.le.resolve_resid(R.raw.world)).show();
            return false;
        }
        open_cert(item.nft());
        return true; //true: leaves it highlighted; false: changes to not highlighted
    }

    @Override public void on_ready(final ko load_result) {
        log("on_ready"); //--strip
        a.assert_ui_thread(); //--strip
        assert adapter != null;
        adapter.set_data(data);
        bind(data);
    }

    void reload(boolean force) {
        a.assert_ui_thread(); //--strip
        log("reload force=" + force); //--strip
        load();
    }

    @Override public void on_push(final hash_t target_tid, final uint16_t code, final byte[] payload) {
        log("on_push"); //--strip
        super.on_push(target_tid, code, payload);
        switch(code.value) {
            case us.wallet.trader.trader_t.push_cert: {
                log("recv cert "); //--strip
                hash_t nft = new hash_t();
                ko r = us.gov.io.blob_reader_t.parse(new blob_t(payload), nft);
                if (is_ko(r)) {
                    log(r.msg); //--strip
                    return;
                }
                toast__worker("Trade " + target_tid.encode() + " received cert: " + nft.encode());
                highlight_nft = nft.encode();
                Thread t = new Thread(new Runnable() {
                    @Override public void run() {
                        load__worker();
                    }
                });
                t.start(); //quickly return as we're in the pushman thread
            }
            break;
        }
    }

    @Override protected void on_menu__main__bnew() {
        on_click_new();
    }

    void open_cert(final String nft) {
        String title = "cert " + nft;
        String fname = "cert_" + nft;
        String fetch_content_cmd = "";
        String action_cmd = "";
        String action_caption = "";
        int mode = 0;
        launch_doc_viewer(title, fname, fetch_content_cmd, action_cmd, action_caption, mode, nft);
    }

    @Override protected void on_click_new() {
        log("on_click_new"); //--strip
        String title = "New cert";
        String fname = "cert";
        String fetch_content_cmd = "";
        String action_cmd = "tade cert create";
        String action_caption = "Go ahead and Type your new certificate:";
        int mode = 1;
        launch_doc_viewer(title, fname, fetch_content_cmd, action_cmd, action_caption, mode, null);
    }

    public void launch_doc_viewer(final String title, final String fname, final String fetch_content_cmd, final String action_cmd, final String action_caption, int mode, final String nft) {
        Intent intent = new Intent(ac, doc_viewer.class);
        intent.putExtra("tid", new hash_t(0));
        if (nft != null) {
            intent.putExtra("nft", nft);
        }
        intent.putExtra("title", title);
        intent.putExtra("fname", fname);
        intent.putExtra("fetch_content_cmd", fetch_content_cmd);
        intent.putExtra("action_cmd", action_cmd);
        intent.putExtra("action_caption", action_caption);
        intent.putExtra("mode", mode);
        startActivityForResult(intent, DOC_VIEWER_RESULT);
    }

    @Override public void onActivityResult(int request_code, int result_code, Intent data) {
        log("onActivityResult " + request_code + " " + result_code); //--strip
        super.onActivityResult(request_code, result_code, data);
        log("onActivityResult impl " + request_code + " " + result_code); //--strip
        if (request_code != DOC_VIEWER_RESULT) {
            log("not from doc_viewer"); //--strip
            return;
        }
        if (result_code != doc_viewer.RESULT_OK) {
            log("doc_viewer was cancelled"); //--strip
            return;
        }
        String cmd = data.getStringExtra("actioncommand");
        String msg = data.getStringExtra("msg");
        log("Xecuting action: " + cmd); //--strip
        if (msg.isEmpty()) {
            toast("Empty msg. Aborted: cert create." + msg);
            return;
        }
        set_busy(true);
        highlight_nft = "";
        hash_t highlight_nft_ = new hash_t();
        ko r = a.hmi().rpc_peer.call_cert_create(new string(msg), highlight_nft_);
        if (is_ko(r)) {
            set_busy(false);
            log(r.msg); //--strip
            report_ko(r);
            return;
        }
        highlight_nft = highlight_nft_.encode();
        toast("New cert: " + highlight_nft);
        set_busy(false);
        reload(true);
    }

    @Override protected uint8_t issuerb() {
        return new uint8_t(issuer == 1 ? (byte)1 : (byte)0);
    }
    
    static final int DOC_VIEWER_RESULT = 832;

    int issuer = 0; //0=all; 1=issued by me
    certs__conf certs__conf_;

    String highlight_nft = null; //MIM token 'highlight_nft__def'
    protected us.cash.scr.certs__conf__all__widgets w = null;
    public certs__conf__all__adapter_t adapter = null;

}

