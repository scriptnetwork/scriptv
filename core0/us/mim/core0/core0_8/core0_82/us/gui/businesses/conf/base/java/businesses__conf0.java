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
//MIM    'apifetch': 'public pair<ko, prot...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'class_qualifier': 'abstract ' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'classname': 'businesses__conf0'
//MIM    'controller': 'activity' @ core0/core0_8/core0_82/us/gui/businesses/mim.h
//MIM    'datatype': 'protocols_t' @ core0/core0_8/core0_82/us/gui/businesses/mim.h
//MIM    'datatype_decl': 'protected ##datatype...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'get_menu_override': '@Override protected ...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'include': 'import us.wallet.tra...'
//MIM    'load__worker': '@Override public ko ...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'menu_max_depth': '1' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'on_menu__handling': '' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'title': 'Businesses' @ core0/core0_8/core0_82/us/gui/businesses/mim.h
//MIM  kickoff code hash: 4ZnwuWhTFnjTeCXW9uXMZfMy3tQJ (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.os.Bundle;                                                                      // Bundle
import us.ko;
import static us.ko.*;
import android.os.Handler;
import us.pair;
import android.content.Intent;                                                                 // Intent
//MIM begin token 'include'
import us.wallet.trader.bootstrap.protocols_t;
import us.wallet.trader.protocol_selection_t;
//MIM end token 'include'

public abstract class businesses__conf0 extends activity {

    private static void log(final String line) {           //--strip
        CFG.log_android("businesses__conf0: " + line);         //--strip
    }                                                      //--strip

    public businesses__conf0() {
        super();
        log("businesses__conf0 constructor"); //--strip
    }

    //--------------lifecycle-------------------------------------------------------
    @Override protected void controller__on_create(Bundle saved_state) {
        assert w == null; //--strip
        set_menu_max_depth(1);
        super.controller__on_create(saved_state);
        log("controller__on_create"); //--strip
        w = (us.cash.scr.businesses__conf0__widgets) super.w;
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
        return "Businesses";
    }

    //MIM begin token 'apifetch'
    public pair<ko, protocols_t> fetch_bzs() {
        log("fetch_bzs"); //--strip
        a.assert_worker_thread(); //--strip
        protocols_t o = new protocols_t();
        ko r = a.hmi().rpc_peer.call_list_businesses(o);
        if (is_ko(r)) {
            return new pair<ko, protocols_t>(r, null);
        }
        return new pair<ko, protocols_t>(ok, o);
    }
    //MIM end token 'apifetch'

    //MIM begin token 'load__worker'
    @Override public ko load__worker() {
        log("load__worker"); //--strip
        a.assert_worker_thread(); //--strip
        {
            ko r = a.backend_ready();
            if (is_ko(r)) {
                bzs = null;
                return r;
            }
        }
        pair<ko, protocols_t> r = fetch_bzs();
        if (is_ko(r.first)) {
            bzs = null;
            return r.first;
        }
        bzs = r.second;
        assert bzs != null; //--strip
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
        return us.cash.scr.businesses__conf0__menu___main_t.get_instance(get_context());
    }
    //MIM end token 'get_menu_override'


    @Override public us.cash.scr.view__widgets create_widgets() {
        return new us.cash.scr.businesses__conf0__widgets();
    }

    us.cash.scr.businesses__conf0__widgets w = null;
    protected protocols_t bzs = null; //MIM token 'datatype_decl'
}

