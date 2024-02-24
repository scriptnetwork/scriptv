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
//MIM    mim file: core0/core0_8/core0_82/us/gui/app/mim.h
//MIM  kickoff code hash: 2jUAxVrfAmKpSgMbgsJ8WBoTuD1y (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.view.View;                                                                      // View
import android.content.Intent;                                                                 // Intent

import us.wallet.trader.protocol_selection_t;
import us.wallet.trader.bootstrap.protocols_t;
import android.os.Handler;
import android.content.Intent;                                                                 // Intent

public class app3 extends app2 {

    private static void log(final String line) {                              //--strip
        CFG.log_android("app3: " + line);          //--strip
    }                                                                         //--strip

    public app3() {
        super();
    }

    public void go_businesses() {
        log("go_businesses"); //--strip
        Intent intent = new Intent(getApplicationContext(), businesses__conf.class);
        add_flags_main_menu_task_launch(intent);
        main.startActivity(intent);
    }

    public void launch_business__conf(final protocol_selection_t o) {
        a.assert_ui_thread(); //--strip
        log("launching conf..."); //--strip
        Intent intent = new Intent(this, business__conf.class);
        add_flags_main_menu_task_launch(intent);
        intent.putExtra("object_id", mem_set_object(o));
        main.startActivity(intent);
    }

    @Override public boolean on_menu(final int id) {
        log("on_menu"); //--strip
        if (id == R.raw.businesses) {
            new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        go_businesses();
                    }
                }, 100);
        }
        else {
            return super.on_menu(id);
        }
        return true;
    }

    public static final String protocol_selection__object_id = "protocol_selection";

    @Override public void register_actions() {
        super.register_actions();
        app3 self = this;
        actions.register_action(protocol_selection__object_id, new action_t("Configure business", new  action_runner_t() {
            @Override public void run(final Object nft) {
                protocol_selection_t protocol_selection = (protocol_selection_t) nft;
                self.launch_business__conf(protocol_selection);
            }
        }));
    }

}
