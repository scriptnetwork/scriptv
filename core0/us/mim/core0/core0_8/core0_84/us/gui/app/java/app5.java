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
//MIM    mim file: core0/core0_8/core0_84/us/gui/app/mim.h
//MIM  kickoff code hash: Cdqc1uWJosdrgYVcwLQ7HJ2Nxai (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.content.Intent;
import android.os.Handler;

public class app5 extends app4 {

    private static void log(final String line) {                              //--strip
        CFG.log_android("app5: " + line);          //--strip
    }                                                                         //--strip

    public app5() {
        super();
    }

    public void launch_iot() {
        log("launch_iot"); //--strip
        Intent intent = new Intent(active_ac, iot__conf.class);
        add_flags_main_menu_task_launch(intent);
        main.startActivity(intent);
    }

    @Override public boolean on_menu(final int id) {
        log("on_menu"); //--strip
        if (id == R.raw.things) {
            new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        launch_iot();
                    }
                }, 100);
        }
        else {
            return super.on_menu(id);
        }
        return true;
    }

    public static final String source_device__disconnected__object_id = "source_device__disconnected";
    public static final String source_device__connected__object_id = "source_device__connected";
    public static final String data_sink__object_id = "data_sink__object_id";

    void register_actions__disconnected_device() {
    }
    
    void register_actions__connected_device() {
    }

    void register_actions__data_sink() {
        app4 self = this;
        actions.register_action(data_sink__object_id, new action_t("Copy data sink account address", new  action_runner_t() {
            @Override public void run(final Object nft) {
                String addr = (String) nft;
                self.set_clipboard(addr, "Data sink account address");
            }
        }));
        actions.register_action(data_sink__object_id, new action_t("Open data sink account address", new  action_runner_t() {
            @Override public void run(final Object nft) {
                String addr = (String) nft;
                self.open_account_address(addr);
            }
        }));    }
    
    @Override public void register_actions() {
        register_actions__connected_device();
        register_actions__disconnected_device();
        register_actions__data_sink();
        super.register_actions();
    }
 
}

