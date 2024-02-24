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
//MIM    mim file: core0/core0_7/us/gui/activity/fragment/impl_/mim.h
//MIM  kickoff code hash: BMiYkeweJkhNShPg8N1jVuMRaZK (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import static us.gov.crypto.ripemd160.hash_t;                                                  // hash_t
import us.ko;                                                                                  // ko
import static us.ko.*;
import static us.ko.is_ko;                                                                     // is_ko
import static us.stdint.*;                                                                     // *
import android.os.Bundle;                                                                      // Bundle


public abstract class fragment1 extends fragment0 implements datagram_dispatcher_t.handler_t {

    private static void log(final String line) {                              //--strip
        CFG.log_android("fragment1: " + line);          //--strip
    }                                                                         //--strip

    public fragment1() {
        super();
    }

    //--------------lifecycle-------------------------------------------------------
    @Override protected void controller__on_create(Bundle saved_state) {
        super.controller__on_create(saved_state);
        log("controller__on_create"); //--strip
        a.set_datagram_listener(this, true);
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
        a.set_datagram_listener(this, false);
        super.controller__on_destroy();
    }
    //-/------------lifecycle-------------------------------------------------------

    @Override public void report_ko(final ko r) {
        log("report_ko " + r.msg); //--strip
        app.assert_ui_thread(); //--strip
        final String msg = a.has_hmi() ? a.hmi().rewrite(r) : r.msg;
        super.report_ko(msg);
    }

    @Override public void report_ko__worker(final ko r) {
        log("report_ko__worker " + r.msg); //--strip
        app.assert_worker_thread(); //--strip
        final String msg = a.has_hmi() ? a.hmi().rewrite(r) : r.msg;
        super.report_ko__worker(msg);
    }

    @Override public void on_push(final hash_t target_tid, final uint16_t code, final byte[] payload) {
        log("on_push"); //--strip
    }

}
