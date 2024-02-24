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
//MIM    mim file: core0/core0_8/core0_85/us/gui/app/mim.h
//MIM  kickoff code hash: 4ZByQyD4sU4CYiCSbUrUPztVvCVb (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import static us.gov.crypto.ripemd160.hash_t;                                                  // hash_t
import android.content.Intent;                                                                 // Intent
import android.os.Handler;
import us.wallet.trader.bookmarks_t;                                                           // bookmarks_t
import us.wallet.trader.bookmark_t;                                                            // bookmark_t
import us.wallet.trader.endpoint_t;                                                            // endpoint_t

public class app6 extends app5 {

    private static void log(final String line) {                              //--strip
        CFG.log_android("app6: " + line);          //--strip
    }                                                                         //--strip

    public app6() {
        super();
    }

    public void launch_nodes() {
        log("launch_nodes"); //--strip
        Intent intent = new Intent(active_ac, nodes__conf.class);
        add_flags_main_menu_task_launch(intent);
        main.startActivity(intent);
    }

    @Override public boolean on_menu(final int id) {
        log("on_menu"); //--strip
        if (id == R.raw.search) {
            new Handler().postDelayed(new Runnable() {
                @Override public void run() {
                    launch_nodes();
                }
            }, 100);
        }
        else {
            return super.on_menu(id);
        }
        return true;
    }

    public void select_node(final hash_t pkh, final int action) {
        assert_worker_thread();  //--strip
        log("select_node not implemented " + action); //--strip
    }

    public void select_node__worker(final hash_t pkh, final int action) {
        assert_worker_thread();  //--strip
        log("select_node__worker " + pkh.encode() + " action " + action); //--strip
        runOnUiThread(new Runnable() {
            @Override public void run() {
                a.select_node(pkh, action);
            }
        });
    }

    public static final String wallet__node_address__object_id = "wallet__node_address";
    public static final String wallet__node_bookmark__object_id = "wallet__node_bookmark";

    @Override public void register_actions() {
        super.register_actions();
        app6 self = this;
        actions.register_action(wallet__node_address__object_id, new action_t("Copy QR", new  action_runner_t() {
            @Override public void run(final Object nft) {
                String addr = (String) nft;
                endpoint_t ep = new endpoint_t(hmi().p.channel, new hash_t(addr));
                bookmark_t bm = new bookmark_t(ep);
                self.set_clipboard(bm.qr.to_string(), "remote wallet QR");
            }
        }));
        actions.register_action(wallet__node_bookmark__object_id, new action_t("Copy QR", new  action_runner_t() {
            @Override public void run(final Object nft) {
                bookmark_t bm = (bookmark_t) nft;
                self.set_clipboard(bm.qr.to_string(), "remote wallet QR");
            }
        }));
    }

}
