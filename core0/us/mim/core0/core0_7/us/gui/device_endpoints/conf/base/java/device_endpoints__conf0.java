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
//MIM    'apifetch': 'public abstract pair...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'class_qualifier': 'abstract ' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'classname': 'device_endpoints__conf0'
//MIM    'controller': 'activity' @ core0/core0_7/us/gui/device_endpoints/mim.h
//MIM    'datatype': 'device_endpoints_t' @ core0/core0_7/us/gui/device_endpoints/mim.h
//MIM    'datatype_decl': 'protected ##datatype...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'get_menu_override': '@Override protected ...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'include': ''
//MIM    'load__worker': '@Override public ko ...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'menu_max_depth': '1' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'on_menu__handling': 'protected abstract v...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'title': 'Connections' @ core0/core0_7/us/gui/device_endpoints/mim.h
//MIM  kickoff code hash: 3KJ8pLeoj2ToZoxzsfrsJVCiT9sz (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.os.Bundle;                                                                      // Bundle
import us.ko;
import static us.ko.*;
import android.os.Handler;
import us.pair;
import android.content.Intent;                                                                 // Intent

public abstract class device_endpoints__conf0 extends activity {

    private static void log(final String line) {           //--strip
        CFG.log_android("device_endpoints__conf0: " + line);         //--strip
    }                                                      //--strip

    public device_endpoints__conf0() {
        super();
        log("device_endpoints__conf0 constructor"); //--strip
    }

    //--------------lifecycle-------------------------------------------------------
    @Override protected void controller__on_create(Bundle saved_state) {
        assert w == null; //--strip
        set_menu_max_depth(1);
        super.controller__on_create(saved_state);
        log("controller__on_create"); //--strip
        w = (us.cash.scr.device_endpoints__conf0__widgets) super.w;
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
        return "Connections";
    }

    public abstract pair<ko, device_endpoints_t> fetch_deps(); //MIM token 'apifetch'

    //MIM begin token 'load__worker'
    @Override public ko load__worker() {
        log("load__worker"); //--strip
        a.assert_worker_thread(); //--strip
        pair<ko, device_endpoints_t> r = fetch_deps();
        if (is_ko(r.first)) {
            deps = null;
            return r.first;
        }
        deps = r.second;
        assert deps != null; //--strip
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
        return us.cash.scr.device_endpoints__conf0__menu___main_t.get_instance(get_context());
    }
    //MIM end token 'get_menu_override'

    //MIM begin token 'on_menu__handling'
    protected abstract void on_menu__main__bnew(); //Create a new connection
    protected abstract void on_menu__main__log(); //Current HMI info...

    @Override public boolean on_menu(int id) {
        log("on_menu"); //--strip
        if (id == R.raw.bnew) { //Create a new connection
            new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        on_menu__main__bnew();
                    }
                }, 100);
        }
        else if (id == R.raw.log) { //Current HMI info...
            new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        on_menu__main__log();
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
        return new us.cash.scr.device_endpoints__conf0__widgets();
    }

    us.cash.scr.device_endpoints__conf0__widgets w = null;
    protected device_endpoints_t deps = null; //MIM token 'datatype_decl'
}

