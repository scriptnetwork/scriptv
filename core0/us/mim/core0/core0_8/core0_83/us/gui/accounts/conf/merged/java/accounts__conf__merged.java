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
//MIM    'apifetch': 'public pair<ko, acco...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'class_qualifier': '' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'classname': 'accounts__conf__merged'
//MIM    'controller': 'fragment' @ core0/core0_8/core0_83/us/gui/accounts/conf/merged/mim.h
//MIM    'datatype': 'account_t' @ core0/core0_8/core0_83/us/gui/accounts/conf/merged/mim.h
//MIM    'datatype_decl': 'protected ##datatype...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'get_menu_override': '@Override protected ...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'include': ''
//MIM    'load__worker': '@Override public ko ...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'menu_max_depth': '1' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'on_menu__handling': '' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'title': 'Balance' @ core0/core0_8/core0_83/us/gui/accounts/conf/merged/mim.h
//MIM  kickoff code hash: K5JszJf8VCiYYmwpXhcdttppwjU (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.os.Bundle;                                                                      // Bundle
import us.ko;
import static us.ko.*;
import android.os.Handler;
import us.pair;
import android.content.Intent;                                                                 // Intent
import android.view.View;

public class accounts__conf__merged extends fragment {

    private static void log(final String line) {           //--strip
        CFG.log_android("accounts__conf__merged: " + line);         //--strip
    }                                                      //--strip

    public accounts__conf__merged() {
        super();
        log("accounts__conf__merged constructor"); //--strip
    }

    //--------------lifecycle-------------------------------------------------------
    @Override protected void controller__on_create(Bundle saved_state) {
        assert w == null; //--strip
        set_menu_max_depth(1);
        super.controller__on_create(saved_state);
        log("controller__on_create"); //--strip
        w = (us.cash.scr.accounts__conf__merged__widgets) super.w;
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
        return "Balance";
    }

    //MIM begin token 'apifetch'
    public pair<ko, account_t> fetch_acc() {
        log("fetch_acc"); //--strip
        a.assert_worker_thread(); //--strip
        us.gov.cash.account_t o = new us.gov.cash.account_t();
        ko r = a.hmi().rpc_peer.call_merge_accounts(o);
        if (is_ko(r)) {
            return new pair<ko, account_t>(r, null);
        }
        return new pair<ko, account_t>(ok, new account_t(null, o));
    }
    //MIM end token 'apifetch'

    //MIM begin token 'load__worker'
    @Override public ko load__worker() {
        log("load__worker"); //--strip
        a.assert_worker_thread(); //--strip
        {
            ko r = a.backend_ready();
            if (is_ko(r)) {
                acc = null;
                return r;
            }
        }
        pair<ko, account_t> r = fetch_acc();
        if (is_ko(r.first)) {
            acc = null;
            return r.first;
        }
        acc = r.second;
        assert acc != null; //--strip
        return ok;
    }
    //MIM end token 'load__worker'

    public void on_ready(final ko load_result) {
        log("on_ready"); //--strip
        a.assert_ui_thread(); //--strip
        w.account_view.set_account(acc);
        w.account_view.locking_program_canvas.setVisibility(View.GONE);

    }

    //MIM begin token 'get_menu_override'
    @Override protected us.cash.scr.menu_t get_menu() {
        if (get_menu_depth() < 1) return super.get_menu();
        assert get_menu_depth() == 1; //--strip
        return us.cash.scr.accounts__conf__merged__menu___main_t.get_instance(get_context());
    }
    //MIM end token 'get_menu_override'


    @Override public us.cash.scr.view__widgets create_widgets() {
        return new us.cash.scr.accounts__conf__merged__widgets(null);
    }

    us.cash.scr.accounts__conf__merged__widgets w = null;
    protected account_t acc = null; //MIM token 'datatype_decl'
}

