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
//MIM    mim file: core0/core0_8/core0_83/us/gui/app/mim.h
//MIM  kickoff code hash: ZWNeBj3LkcpVXdRwc5aALQDhdqD (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.os.Handler;
import java.text.NumberFormat;                                                                 // NumberFormat
import android.content.Intent;                                                                 // Intent

public class app4 extends app3 {

    private static void log(final String line) {                              //--strip
        CFG.log_android("app4: " + line);          //--strip
    }                                                                         //--strip

    public app4() {
        super();
    }

    @Override public void onCreate() {
        super.onCreate();
        log("onCreate"); //--strip
        formatter = NumberFormat.getInstance(getResources().getConfiguration().locale);
        formatter.setMaximumFractionDigits(0);
        formatter.setMinimumFractionDigits(0);
        formatter.setGroupingUsed(true);
    }

    public void launch_position() {
        log("launch_position"); //--strip
        Intent intent = new Intent(active_ac, accounts__conf.class);
        add_flags_main_menu_task_launch(intent);
        intent.putExtra("mode", 0);
        main.startActivity(intent);
    }

    public void open_account_address(final String nft) {
        log("open_account_address"); //--strip
        Intent intent = new Intent(active_ac, accounts__conf.class);
        add_flags_main_menu_task_launch(intent);
        intent.putExtra("mode", 0);
        intent.putExtra("object_id", mem_set_object(nft));
        intent.putExtra("object_dyntab", 0);
        main.startActivity(intent);
    }

    @Override public boolean on_menu(final int id) {
        log("on_menu"); //--strip
        if (id == R.raw.wallet) {
            new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        launch_position();
                    }
                }, 100);
        }
        else {
            return super.on_menu(id);
        }
        return true;
    }

    public static final String wallet__account_address__object_id = "wallet__account_address";

    @Override public void register_actions() {
        super.register_actions();
        app4 self = this;
        actions.register_action(wallet__account_address__object_id, new action_t("Copy account address", new  action_runner_t() {
            @Override public void run(final Object nft) {
                String addr = (String) nft;
                self.set_clipboard(addr, "Account address");
            }
        }));

        actions.register_action(wallet__account_address__object_id, new action_t("Open account address (New task)", new  action_runner_t() {
            @Override public void run(final Object nft) {
                String addr = (String) nft;
                self.open_account_address(addr);
            }
        }));
    }

    public static NumberFormat formatter = null;

}

