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
//MIM    'classname': 'trader__conf__role'
//MIM    'controller': 'fragment' @ core0/core0_8/core0_86/us/gui/trader/conf/role/mim.h
//MIM    'datatype_decl': '' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'get_menu_override': '@Override protected ...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'include': ''
//MIM    'load__worker': '@Override public ko ...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'menu_max_depth': '1' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'on_menu__handling': '' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'title': 'Role' @ core0/core0_8/core0_86/us/gui/trader/conf/role/mim.h
//MIM  kickoff code hash: 37F11QZi3edJduA3ZJht8icVVyu5 (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.os.Bundle;                                                                      // Bundle
import us.ko;
import static us.ko.*;
import android.os.Handler;
import us.pair;
import static us.stdint.*;
import android.content.Intent;                                                                 // Intent
import us.wallet.trader.protocol_selection_t;
import us.wallet.trader.data_t;
import static us.gov.crypto.ripemd160.hash_t;
import android.view.View;                                                                      // View

public class trader__conf__role extends fragment {

    private static void log(final String line) {           //--strip
        CFG.log_android("trader__conf__role: " + line);         //--strip
    }                                                      //--strip

    public trader__conf__role(protocol_selection_t protocol_selection) {
        Bundle args = new Bundle();
        args.putInt("protocol_selection__object_id", a.mem_set_object(protocol_selection));
        setArguments(args);
    }

    //--------------lifecycle-------------------------------------------------------
    @Override protected void controller__on_create(Bundle saved_state) {
        assert w == null; //--strip
        set_menu_max_depth(1);
        super.controller__on_create(saved_state);
        log("controller__on_create"); //--strip
        w = (us.cash.scr.trader__conf__role__widgets) super.w;
        assert w != null; //--strip
        trader__conf_ = (trader__conf) ac;
        Bundle args = getArguments();
        if (args != null) {
            {
                Integer id = args.getInt("protocol_selection__object_id", -1);
                log("protocol_selection__object_id " + id); //--strip
                protocol_selection = (protocol_selection_t) a.mem_get_object(id);
            }
        }
        if (protocol_selection == null) {
            ko r = new ko("KO 77829 Invalid protocol_selection");
            log(r.msg);
            toast(r.msg);
            ac.finish();
            return;
        }
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
        return "Role";
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
        w.logo_view.init(trader__conf_, new View.OnClickListener() {
            @Override public void onClick(View view) {
            }
        });
        data_t data = trader__conf_.get_data();
        w.logo_view.on_data(data);
        w.tip_view.on_data(data);
         
    }

    //MIM begin token 'get_menu_override'
    @Override protected us.cash.scr.menu_t get_menu() {
        if (get_menu_depth() < 1) return super.get_menu();
        assert get_menu_depth() == 1; //--strip
        return us.cash.scr.trader__conf__role__menu___main_t.get_instance(get_context());
    }
    //MIM end token 'get_menu_override'


    @Override public us.cash.scr.view__widgets create_widgets() {
        return new us.cash.scr.trader__conf__role__widgets();
    }

    us.cash.scr.trader__conf__role__widgets w = null;
    trader__conf trader__conf_;
    protocol_selection_t protocol_selection;

    public void on_data(final data_t data) {
        if (w == null) return;
        w.logo_view.on_data(data);
        w.tip_view.on_data(data);
    }

    @Override public void on_push(final hash_t target_tid, final uint16_t code, final byte[] payload) {
        log("on_push"); //--strip
        a.assert_worker_thread(); //--strip
        if (!trader__conf_.tid.equals(target_tid)) {
            return;
        }
        switch(code.value) {
            case us.wallet.trader.trader_protocol.push_logo: {
                log("a logo for me"); //--strip
                w.logo_view.on_logo__worker(payload);
                return;
            }
            default: {
                super.on_push(target_tid, code, payload);
            }
        }
    }
}

