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
//MIM    mim file: core0/core0_8/core0_86/us/gui/app/mim.h
//MIM  kickoff code hash: qMdpZ4heS4h23mDop9D8oR56LRs (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import static us.gov.crypto.ripemd160.hash_t;                                                  // hash_t
import android.content.Intent;                                                                 // Intent
import android.os.Handler;
import us.wallet.trader.qr_t;
import us.pair;
import static us.ko.*;
import static us.stdint.*;
import us.ko;
import us.wallet.trader.bookmarks_t;                                                           // bookmarks_t
import us.wallet.trader.bookmark_t;                                                            // bookmark_t
import us.wallet.trader.endpoint_t;                                                            // endpoint_t
import us.wallet.trader.protocol_selection_t;

public class app7 extends app6 {

    private static void log(final String line) {                              //--strip
        CFG.log_android("app7: " + line);          //--strip
    }                                                                         //--strip

    public app7() {
        super();
    }

    @Override public void onCreate() {
        super.onCreate();
        log("onCreate"); //--strip
        String lang = locale.get_lang(this);
        if (lang != null && lang.equals("es")) {
            i18n = new i18n_es();
        }
        else {
            i18n = new i18n_en();
        }
    }

    public void go_trades() {
        log("go_trades"); //--strip
        Intent intent = new Intent(active_ac, trades__conf.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); //single instance
        main.startActivity(intent);
    }

    public void go_trade(final String nft) {
        assert_ui_thread();  //--strip
        log("go_trade " + nft);  //--strip
        Intent intent = new Intent(active_ac, trader__conf.class);
        add_flags_main_menu_task_launch(intent);
        intent.putExtra("nft", nft);
        main.startActivity(intent);
    }

    public void go_trade__worker(final hash_t tid) {
        assert_worker_thread();  //--strip
        log("go_trade__worker " + tid.encode()); //--strip
        runOnUiThread(new Runnable() {
            @Override public void run() {
                a.go_trade(tid.encode());
            }
        });
    }

    @Override public boolean on_menu(final int id) {
        log("on_menu"); //--strip
        if (id == R.raw.trade) {
            new Handler().postDelayed(new Runnable() {
                @Override public void run() {
                    go_trades();
                }
            }, 100);
        }
        else {
            return super.on_menu(id);
        }
        return true;
    }
/*
    @Override public boolean on_menu(final int id) {
        log("on_menu"); //--strip
        if (id == R.raw.qrico) {
            new Handler().postDelayed(new Runnable() {
                @Override public void run() {
                    go_qr();
                }
            }, 100);
        }
        else {
            return super.on_menu(id);
        }
        active_ac.close_drawer();
        return true;
    }
*/


    public pair<ko, hash_t> new_trade(final hash_t parent_trade, final String datasubdir, final qr_t qr) {
        assert_ui_thread();  //--strip
        log("new_trade " + qr.to_string()); //--strip
        pair<ko, hash_t> r = hmi().new_trade(parent_trade, datasubdir, qr);
        log("invoked API new_trade"); //--strip
        if (is_ko(r.first)) {
            toast(hmi().rewrite(r.first));
        }
        else {
            toast(r_(R.string.newtrade) + "\n" + qr.to_string());
        }
        return r;
    }

/*
    public void select_trade(final hash_t tid, final int action) {
        assert_worker_thread();  //--strip
        log("select_trade " + action); //--strip
        if (action == 1) {
            go_trade(tid);
            return;
        }
        select_trade2(tid, action);
    }

    public void select_trade__worker(final hash_t tid, final int action) {
        assert_worker_thread();  //--strip
        log("select_trade__worker " + tid.encode() + " action " + action); //--strip
        runOnUiThread(new Runnable() {
            @Override public void run() {
                a.select_trade(tid, action);
            }
        });
    }

    void go_qr(int mode) { //0 show; 1 scan
        log("go_qr"); //--strip
        Intent intent = new Intent(active_ac, qr__conf.class);
        int tab = mode == 0 ? 0 : 1;
        intent.putExtra("sel", tab);
        main.startActivity(intent);
    }
*/



    public void beep_chat() {
        log("beep chat"); //--strip
        //tone.startTone(ToneGenerator.TONE_CDMA_NETWORK_CALLWAITING, 150);
    }

    public void use_personality(final hash_t tid, final String personality) {
        assert_ui_thread();  //--strip
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                hmi().command_trade(tid, "use_personality " + personality);
            }
        });
        thread.start();
    }

/*
    public void start_protocol(final hash_t tid, final String procol) {
        log("start_protocol " + tid.encode() + " " + procol); //--strip
        Thread thread = new Thread(new Runnable() {
            @Override public void run() {
                log("starting protocol " + procol); //--strip
                hmi().command_trade(tid, "start " + procol);
            }
        });
        thread.start();
    }
*/



    void doscan(boolean frommainmenu) {
/*
        Intent intent = new Intent(this, scan.class);
        intent.putExtra("what", 0);
        startActivity(intent);
*/
    }

    @Override public void on_push(hash_t target_tid, uint16_t code, byte[] payload) {
        log("on_push " + target_tid.encode() + " code " + code.value + " payload BIN sz: " + payload.length + " bytes"); //--strip
        switch(code.value) {
            case us.wallet.trader.trader_t.push_trade: {
                log("a new trade for me"); //--strip
//                go_trade__worker(target_tid);
                return;
            }
            case us.wallet.trader.trader_t.push_chat: {
                runOnUiThread(new Runnable() {
                    @Override public void run() {
                        call_human(1, target_tid.encode());
                    }
                });
                break;
            }
            default: {
                super.on_push(target_tid, code, payload);
            }
        }
    }

    public static final String wallet__trade_address__object_id = "wallet__trade_address";
    public static final String tx__object_id = "tx";

    @Override public void register_actions() {
        app7 self = this;
        actions.register_action(wallet__node_address__object_id, new action_t("Start new trade", new  action_runner_t() {
            @Override public void run(final Object nft) {
                String addr = (String) nft;
                endpoint_t ep = new endpoint_t(hmi().p.channel, new hash_t(addr));
                bookmark_t bm = new bookmark_t(ep);
                new_trade(hash_t.zero_, "", bm.qr);
            }
        }));

        actions.register_action(wallet__node_bookmark__object_id, new action_t("Start new trade", new  action_runner_t() {
            @Override public void run(final Object nft) {
                bookmark_t bm = (bookmark_t) nft;
                new_trade(hash_t.zero_, "", bm.qr);
            }
        }));

        actions.register_action(wallet__trade_address__object_id, new action_t("Open trade", new  action_runner_t() {
            @Override public void run(final Object nft) {
                String tid = (String) nft;
                self.go_trade(tid);
            }
        }));

        super.register_actions();
    }
 
 
 
    void load_ui_classes() {
    }
 
    r2r_libs_t r2r_libs = new r2r_libs_t(); 
    public i18n_t i18n;

}
