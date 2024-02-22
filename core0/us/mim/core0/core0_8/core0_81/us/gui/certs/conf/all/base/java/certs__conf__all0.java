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
//MIM    'apifetch': 'public pair<ko, cert...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'class_qualifier': 'abstract ' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'classname': 'certs__conf__all0'
//MIM    'controller': 'fragment' @ core0/core0_8/core0_81/us/gui/certs/conf/all/mim.h
//MIM    'datatype': 'certs__conf__all0__datatype_t' @ core0/core0_8/core0_81/us/gui/certs/conf/all/base/mim.h
//MIM    'datatype_decl': 'protected ##datatype...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'get_menu_override': '@Override protected ...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'include': 'import java.util.Arr...'
//MIM    'load__worker': '@Override public ko ...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'menu_max_depth': '1' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'on_menu__handling': 'protected abstract v...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'title': 'All' @ core0/core0_8/core0_81/us/gui/certs/conf/all/mim.h
//MIM  kickoff code hash: 2q3fzMomYhVqKxAunqrnfz1ihzLs (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.os.Bundle;                                                                      // Bundle
import us.ko;
import static us.ko.*;
import android.os.Handler;
import us.pair;
import android.content.Intent;                                                                 // Intent
//MIM begin token 'include'
import java.util.ArrayList;
import static us.gov.crypto.ripemd160.hash_t;
import us.pair;
import us.string;
//MIM end token 'include'
import static us.stdint.*;

public abstract class certs__conf__all0 extends fragment {

    private static void log(final String line) {           //--strip
        CFG.log_android("certs__conf__all0: " + line);         //--strip
    }                                                      //--strip

    public certs__conf__all0() {
        super();
        log("certs__conf__all0 constructor"); //--strip
    }

    //--------------lifecycle-------------------------------------------------------
    @Override protected void controller__on_create(Bundle saved_state) {
        assert w == null; //--strip
        set_menu_max_depth(1);
        super.controller__on_create(saved_state);
        log("controller__on_create"); //--strip
        w = (us.cash.scr.certs__conf__all0__widgets) super.w;
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
        uint8_t issuer = issuerb();
        if (issuer.value == 0) {
            return "All";
        }
        return "SoA";
    }

    //MIM begin token 'apifetch'
    public pair<ko, certs__conf__all0__datatype_t> fetch_data() {
        log("fetch_data"); //--strip
        a.assert_worker_thread(); //--strip
        us.wallet.trader.cert.cert_index_t o = new us.wallet.trader.cert.cert_index_t();
        ko r = a.hmi().rpc_peer.call_cert_list(issuerb(), o);
        if (is_ko(r)) {
            return new pair<ko, certs__conf__all0__datatype_t>(r, null);
        }
        return new pair<ko, certs__conf__all0__datatype_t>(ok, new certs__conf__all0__datatype_t(o));
    }
    //MIM end token 'apifetch'

    //MIM begin token 'load__worker'
    @Override public ko load__worker() {
        log("load__worker"); //--strip
        a.assert_worker_thread(); //--strip
        {
            ko r = a.backend_ready();
            if (is_ko(r)) {
                data = null;
                return r;
            }
        }
        pair<ko, certs__conf__all0__datatype_t> r = fetch_data();
        if (is_ko(r.first)) {
            data = null;
            return r.first;
        }
        data = r.second;
        assert data != null; //--strip
        return ok;
    }
    //MIM end token 'load__worker'

    public void on_ready(final ko load_result) {
        log("on_ready"); //--strip
        a.assert_ui_thread(); //--strip
        //TODO: init w
    }

    //MIM begin token 'get_menu_override'
    @Override protected us.cash.scr.menu_t get_menu() {
        if (get_menu_depth() < 1) return super.get_menu();
        assert get_menu_depth() == 1; //--strip
        return us.cash.scr.certs__conf__all0__menu___main_t.get_instance(get_context());
    }
    //MIM end token 'get_menu_override'

    //MIM begin token 'on_menu__handling'
    protected abstract void on_menu__main__bnew(); //New certificate

    @Override public boolean on_menu(int id) {
        log("on_menu"); //--strip
        if (id == R.raw.bnew) { //New certificate
            new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        on_menu__main__bnew();
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
        return new us.cash.scr.certs__conf__all0__widgets();
    }

    protected abstract uint8_t issuerb();
    protected abstract void on_click_new();

    us.cash.scr.certs__conf__all0__widgets w = null;
    protected certs__conf__all0__datatype_t data = null; //MIM token 'datatype_decl'
}

