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
//MIM    'class_qualifier': '' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'classname': 'business__conf'
//MIM    'controller': 'activity' @ core0/core0_8/core0_82/us/gui/businesses/conf/business/conf/mim.h
//MIM    'datatype': 'protocol_selection_t' @ core0/core0_8/core0_82/us/gui/businesses/conf/business/conf/mim.h
//MIM    'datatype_decl': 'protected ##datatype...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'get_menu_override': '@Override protected ...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'include': 'import us.wallet.tra...'
//MIM    'load__worker': '@Override public ko ...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'menu_max_depth': '1' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'on_menu__handling': '' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'title': 'Business' @ core0/core0_8/core0_82/us/gui/businesses/conf/business/conf/mim.h
//MIM  kickoff code hash: 2tTA5KFMH57dBHq6ykLGeheDdU7w (change this hash to force a review)
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

public class business__conf extends activity {

    private static void log(final String line) {           //--strip
        CFG.log_android("business__conf: " + line);         //--strip
    }                                                      //--strip

    public business__conf() {
        super();
        log("business__conf constructor"); //--strip
    }

    //--------------lifecycle-------------------------------------------------------
    @Override protected void controller__on_create(Bundle saved_state) {
        assert w == null; //--strip
        set_menu_max_depth(1);
        super.controller__on_create(saved_state);
        log("controller__on_create"); //--strip
        w = (us.cash.scr.business__conf__widgets) super.w;
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
        return "Business";
    }

    //MIM begin token 'apifetch'
    public pair<ko, protocol_selection_t> fetch_bz() {
        log("fetch_bz"); //--strip
        Intent i = getIntent();
        if (i == null) {
            ko r = new ko("KO 20199 Intent");
            log(r.msg);
            return new pair(r, null);
        }
        if (!i.hasExtra("object_id")) {
            ko r = new ko("KO 77369 Missing object id");
            log(r.msg);
            return new pair(r, null);
        }
        Integer id = i.getExtras().getInt("object_id", -1);
        log("object_id " + id); //--strip
        protocol_selection_t o = (protocol_selection_t) a.mem_get_object(id);
        if (o == null) {
            ko r = new ko("KO 77829 Invalid object_id");
            log(r.msg);
            return new pair(r, null);
        }
        return new pair(ok, o);
    }
    //MIM end token 'apifetch'

    //MIM begin token 'load__worker'
    @Override public ko load__worker() {
        log("load__worker"); //--strip
        a.assert_worker_thread(); //--strip
        pair<ko, protocol_selection_t> r = fetch_bz();
        if (is_ko(r.first)) {
            bz = null;
            return r.first;
        }
        bz = r.second;
        assert bz != null; //--strip
        return ok;
    }
    //MIM end token 'load__worker'

    public void on_ready(final ko load_result) {
        log("on_ready"); //--strip
        a.assert_ui_thread(); //--strip
        if (is_ko(load_result)) {
            toast(load_result.msg);
            finish();
            return;
        }
        w.protocol_value.setText(bz.first);
        w.role_value.setText(bz.second);
        set_title("Business " + bz.to_string());
        m.header.tail.setText(bz.to_string());
    }

    us.cash.scr.menu_t m;

    //MIM begin token 'get_menu_override'
    @Override protected us.cash.scr.menu_t get_menu() {
        if (get_menu_depth() < 1) return super.get_menu();
        assert get_menu_depth() == 1; //--strip
        m = us.cash.scr.business__conf__menu___main_t.get_instance(get_context());
        return m;
    }
    //MIM end token 'get_menu_override'


    @Override public us.cash.scr.view__widgets create_widgets() {
        return new us.cash.scr.business__conf__widgets();
    }

    us.cash.scr.business__conf__widgets w = null;
    protected protocol_selection_t bz = null; //MIM token 'datatype_decl'
}

