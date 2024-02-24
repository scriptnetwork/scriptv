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
//MIM        file: core0/core0_6/core0_61/us/gui/activity/controller/java/[classname].java
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM  Params:
//MIM    'apifetch': 'public pair<ko, book...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'class_qualifier': '' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'classname': 'trades__conf__myqr'
//MIM    'controller': 'fragment' @ core0/core0_8/core0_86/us/gui/trades/conf/myqr/mim.h
//MIM    'datatype': 'bookmarks_t' @ core0/core0_8/core0_86/us/gui/trades/conf/myqr/mim.h
//MIM    'datatype_decl': 'protected ##datatype...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'get_menu_override': '@Override protected ...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'include': 'import us.wallet.tra...'
//MIM    'load__worker': '@Override public ko ...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'menu_max_depth': '1' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'on_menu__handling': '' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'title': 'My QRs' @ core0/core0_8/core0_86/us/gui/trades/conf/myqr/mim.h
//MIM  kickoff code hash: 2pQkodsH22Mpfg9Xat7iTiryn4sk (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.os.Bundle;                                                                      // Bundle
import us.ko;
import static us.ko.*;
import android.os.Handler;
import us.pair;
import android.content.Intent;                                                                 // Intent
//MIM begin token 'include'
import us.wallet.trader.bookmark_t;
import us.wallet.trader.bookmarks_t;
//MIM end token 'include'
import android.content.ClipboardManager;                                                       // ClipboardManager
import android.content.ClipData;                                                               // ClipData
import com.google.zxing.MultiFormatWriter;                                                     // MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder;                                          // BarcodeEncoder
import com.google.zxing.BarcodeFormat;                                                         // BarcodeFormat
import android.graphics.Bitmap;                                                                // Bitmap
import com.google.zxing.common.BitMatrix;                                                      // BitMatrix
import com.google.zxing.WriterException;                                                       // WriterException
import android.view.View;                                                                      // View
import us.pair;                                                                      // View
import android.content.Context;

public class trades__conf__myqr extends fragment {

    private static void log(final String line) {           //--strip
        CFG.log_android("trades__conf__myqr: " + line);         //--strip
    }                                                      //--strip

    public trades__conf__myqr() {
        super();
        log("trades__conf__myqr constructor"); //--strip
    }

    //--------------lifecycle-------------------------------------------------------
    @Override protected void controller__on_create(Bundle saved_state) {
        assert w == null; //--strip
        set_menu_max_depth(1);
        super.controller__on_create(saved_state);
        log("controller__on_create"); //--strip
        w = (us.cash.scr.trades__conf__myqr__widgets) super.w;
        assert w != null; //--strip
        load(); //follows on_ready
    }

    @Override protected void controller__on_pause() {
        log("controller__on_pause"); //--strip
        super.controller__on_pause();
    }

    @Override protected void controller__on_resume() {
        super.controller__on_resume();
        log("controller__on_resume"); //--strip
    }

    @Override protected void controller__on_destroy() {
        log("controller__on_destroy"); //--strip
        w = null;
        super.controller__on_destroy();
    }
    //-/------------lifecycle-------------------------------------------------------

    @Override public String get_title() {
        return "My QRs";
    }

    //MIM begin token 'apifetch'
    public pair<ko, bookmarks_t> fetch_bookmarks() {
        log("fetch_bookmarks"); //--strip
        a.assert_worker_thread(); //--strip
        bookmarks_t o = new bookmarks_t();
        ko r = a.hmi().rpc_peer.call_qr(o);
        if (is_ko(r)) {
            return new pair<ko, bookmarks_t>(r, null);
        }
        return new pair<ko, bookmarks_t>(ok, o);
    }
    //MIM end token 'apifetch'

    //MIM begin token 'load__worker'
    @Override public ko load__worker() {
        log("load__worker"); //--strip
        a.assert_worker_thread(); //--strip
        {
            ko r = a.backend_ready();
            if (is_ko(r)) {
                bookmarks = null;
                return r;
            }
        }
        pair<ko, bookmarks_t> r = fetch_bookmarks();
        if (is_ko(r.first)) {
            bookmarks = null;
            return r.first;
        }
        bookmarks = r.second;
        assert bookmarks != null; //--strip
        return ok;
    }
    //MIM end token 'load__worker'

    public void on_ready(final ko load_result) {
        log("on_ready"); //--strip
        a.assert_ui_thread(); //--strip
        index = 0;
        w.qrtext.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                copy_clip();
            }
        });
        w.qrcode.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                copy_clip();
            }
        });
        w.qrcode.setOnTouchListener(new swipe_touch__listener_t(get_context()) {
            @Override public void on_swipe_right() {
                --index;
                if (index < 0) index = bookmarks.size() - 1;
                refresh();
            }
            @Override public void on_swipe_left() {
                ++index;
                if (index >= bookmarks.size()) index = 0;
                refresh();
            }
        });
        refresh();
    }

    //MIM begin token 'get_menu_override'
    @Override protected us.cash.scr.menu_t get_menu() {
        if (get_menu_depth() < 1) return super.get_menu();
        assert get_menu_depth() == 1; //--strip
        return us.cash.scr.trades__conf__myqr__menu___main_t.get_instance(get_context());
    }
    //MIM end token 'get_menu_override'


    @Override public us.cash.scr.view__widgets create_widgets() {
        return new us.cash.scr.trades__conf__myqr__widgets();
    }

    void copy_clip() {
        ClipboardManager clipboard = (ClipboardManager) ac.getSystemService(Context.CLIPBOARD_SERVICE);
        String qr = bookmarks.get(bookmarks.keySet().toArray()[index]).qr.to_string();
        ClipData clip = ClipData.newPlainText("endpoint", qr);
        clipboard.setPrimaryClip(clip);
        toast(ac.getResources().getString(R.string.endpointcopied) + "\n" + qr);
    }

    @Override public void refresh() {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            bookmark_t b = bookmarks.get("" + bookmarks.keySet().toArray()[index]);
            String txt = b.qr.to_string();
            log("my-bookmark=" + txt); //--strip
            if (txt.isEmpty()) {
                w.qrtext.setText("Invalid");
                w.qrcode.setVisibility(View.INVISIBLE);
                w.qrtext.setVisibility(View.INVISIBLE);
            }
            else {
                BitMatrix bitMatrix = multiFormatWriter.encode(txt, BarcodeFormat.QR_CODE, 200, 200);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                w.qrcode.setImageBitmap(bitmap);
                w.qrtext.setText(txt);
                String pg = "" + (index + 1) + "/" + bookmarks.size();
                w.page.setText(pg);
                w.qrlabel.setText(b.label);
                w.qrcode.setVisibility(View.VISIBLE);
                w.qrtext.setVisibility(View.VISIBLE);
            }
        }
        catch (WriterException e) {
            toast(e.getMessage() + "    " + e.toString());
        }
    }

    us.cash.scr.trades__conf__myqr__widgets w = null;
    protected bookmarks_t bookmarks = null; //MIM token 'datatype_decl'
    int index = -1;
}

