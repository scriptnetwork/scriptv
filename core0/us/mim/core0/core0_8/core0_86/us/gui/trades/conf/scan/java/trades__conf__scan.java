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
//MIM    'apifetch': '' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'class_qualifier': '' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'classname': 'trades__conf__scan'
//MIM    'controller': 'fragment' @ core0/core0_8/core0_86/us/gui/trades/conf/scan/mim.h
//MIM    'datatype_decl': '' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'get_menu_override': '@Override protected ...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'include': ''
//MIM    'load__worker': '@Override public ko ...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'menu_max_depth': '1' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'on_menu__handling': 'protected void on_me...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'title': 'Scan QR' @ core0/core0_8/core0_86/us/gui/trades/conf/scan/mim.h
//MIM  kickoff code hash: 2SJAFWAjNqtW6jxNkbpUbeNt3pjW (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.os.Bundle;                                                                      // Bundle
import us.ko;
import static us.ko.*;
import android.os.Handler;
import us.pair;
import android.content.Intent;                                                                 // Intent
import android.Manifest;                                                                       // Manifest
import androidx.core.content.ContextCompat;                                                    // ContextCompat
import android.content.pm.PackageManager;                                                      // PackageManager
import androidx.core.app.ActivityCompat;                                                       // ActivityCompat
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import java.util.List;
import com.google.zxing.ResultPoint;
import android.view.View;                                                                      // View
import android.media.ToneGenerator;                                                            // ToneGenerator
import us.wallet.trader.qr_t;
import us.gov.crypto.ripemd160.hash_t;

public class trades__conf__scan extends fragment {

    private static void log(final String line) {           //--strip
        CFG.log_android("trades__conf__scan: " + line);         //--strip
    }                                                      //--strip

    public trades__conf__scan() {
        super();
        log("trades__conf__scan constructor"); //--strip
    }

    //--------------lifecycle-------------------------------------------------------
    @Override protected void controller__on_create(Bundle saved_state) {
        assert w == null; //--strip
        set_menu_max_depth(1);
        super.controller__on_create(saved_state);
        log("controller__on_create"); //--strip
        w = (us.cash.scr.trades__conf__scan__widgets) super.w;
        assert w != null; //--strip
        load(); //follows on_ready
    }

    @Override protected void controller__on_pause() {
        log("controller__on_pause"); //--strip
        w.scanner.pauseAndWait();
        super.controller__on_pause();
    }

    @Override protected void controller__on_resume() {
        super.controller__on_resume();
        log("controller__on_resume"); //--strip
        if (ContextCompat.checkSelfPermission(ac, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ac, new String[] {Manifest.permission.CAMERA}, 50);
        }
        log("Starting camera"); //--strip
        w.scanner.resume();
    }

    @Override protected void controller__on_destroy() {
        log("controller__on_destroy"); //--strip
        w = null;
        super.controller__on_destroy();
    }
    //-/------------lifecycle-------------------------------------------------------

    @Override public String get_title() {
        return "Scan QR";
    }


    //MIM begin token 'load__worker'
    @Override public ko load__worker() {
        log("load__worker"); //--strip
        a.assert_worker_thread(); //--strip
        return ok;
    }
    //MIM end token 'load__worker'

    public void on_ready(final ko load_result) {
        log("on_ready"); //--strip
        a.assert_ui_thread(); //--strip
        w.scanner.decodeSingle(
            new BarcodeCallback() {
                @Override public void barcodeResult(BarcodeResult result) {
                    if(result.getText() == null || result.getText().equals(last_text)) {
                        return; // Prevent duplicate scans
                    }
                    //submit(result.getText());
                    w.qr_paste.setText(result.getText());
                    a.tone.startTone(ToneGenerator.TONE_CDMA_PIP, 150);
                }

                @Override public void possibleResultPoints(List<ResultPoint> resultPoints) {
                }
            }
        );
    }

    //MIM begin token 'get_menu_override'
    @Override protected us.cash.scr.menu_t get_menu() {
        if (get_menu_depth() < 1) return super.get_menu();
        assert get_menu_depth() == 1; //--strip
        return us.cash.scr.trades__conf__scan__menu___main_t.get_instance(get_context());
    }
    //MIM end token 'get_menu_override'

    //MIM begin token 'on_menu__handling'
    protected void on_menu__main__torch() { //Toggle torch
        log("on_menu__main__torch"); //--strip
        toggle_torch();
    }


    @Override public boolean on_menu(int id) {
        log("on_menu"); //--strip
        if (id == R.raw.torch) { //Toggle torch
            new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        on_menu__main__torch();
                    }
                }, 100);
        }
        else {
            return super.on_menu(id);
        }
        return true;
    }
    //MIM end token 'on_menu__handling'

    @Override public us.cash.scr.view__widgets create_widgets() {
        final trades__conf__scan self = this;
        return new us.cash.scr.trades__conf__scan__widgets(
            new View.OnClickListener() {
                @Override public void onClick(View view) {
                    on_qr_paste();
                }
            }
        );
    }

    @Override public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: { // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    ac.setResult(ac.RESULT_CANCELED);
                    ac.finish();
                }
                return;
            }
        }
    }

    void on_qr_paste() {
        String ep = w.qr_paste.getText().toString().trim();
        if (ep.isEmpty()) {
            toast("type or paste an endpoint.");
            return;
        }
        ac.hide_keyboard();
        submit(ep);
    }

    private boolean hasFlash() {
        return ac.getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    boolean torch = false;
    public void toggle_torch() {
        if (torch) {
            w.scanner.setTorchOff();
        }
        else {
            w.scanner.setTorchOn();
        }
        torch = !torch;
    }

    void submit(final String text) {
        log("submit " + text); //--strip

        qr_t go_qr = new qr_t();
        {
            ko r = go_qr.from_string(text);
            if (ko.is_ko(r)) {
                a.tone.startTone(ToneGenerator.TONE_CDMA_INTERCEPT, 150);
                toast(r.msg);
                return;
            }
        }
        log("qr: " + go_qr.to_string()); //--strip
        {
            String datasubdir = "";
            hash_t parent = new hash_t(0);
            pair<ko, hash_t> r = a.new_trade(parent, datasubdir, go_qr); ///<orma flow dst="us::wallet::trader::traders_t::initiate"/>
            if (ko.is_ko(r.first)) {
                a.tone.startTone(ToneGenerator.TONE_CDMA_INTERCEPT, 150);
                toast(a.hmi().rewrite(r.first));
                return;
            }
        }
        w.scanner.pauseAndWait();
        ((trades__conf)ac).select_tab__trades();
            
/*
        Intent data = new Intent();
        data.putExtra("go_qr", text);
        if (what == 0) {
            data.putExtra("go_qr", text);
        }
        else {
            data.putExtra("selection", text);
        }
        ac.setResult(ac.RESULT_OK, data);
        ac.finish();
*/
    }

    us.cash.scr.trades__conf__scan__widgets w = null;

    String last_text = "";
//    private BarcodeCallback callback;

    static final int MY_PERMISSIONS_REQUEST_CAMERA = 1873;

    //boolean cont = false;
    //int what = 0;
//    hash_t tid;
}

