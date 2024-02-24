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
//MIM        file: core0/core0_9/us/gui/r2r/symmetric/java/trader__[protocol]_[role].java
//MIM    mim file: core0/core0_9/us/gui/r2r/symmetric/mim.h
//MIM  Params:
//MIM    'protocol': 'w2w' @ core0/core0_9/us/gui/r2r/w2w/mim.h
//MIM    'role': 'w' @ core0/core0_9/us/gui/r2r/w2w/mim.h
//MIM  kickoff code hash: 33hTnRVQnVUAXC6VjJ62f7MoLciX (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.os.Bundle;                                                                      // Bundle
import android.widget.Button;                                                                  // Button
import us.gov.socket.datagram;                                                                 // datagram
import us.wallet.trader.data_t;                                                                // data_t
import android.widget.EditText;                                                                // EditText
import java.util.HashMap;                                                                      // HashMap
import static us.gov.crypto.ripemd160.hash_t;                                                  // hash_t
import static us.gov.cash.types.*;                                                             // *
import static us.gov.crypto.types.*;                                                           // *
import static us.ko.*;                                                                         // *
import static us.stdint.*;                                                                     // *
import static us.tuple.*;                                                                      // *
import android.content.Intent;                                                                 // Intent
import us.ko;                                                                                  // ko
import android.view.LayoutInflater;                                                            // LayoutInflater
import android.widget.LinearLayout;                                                            // LinearLayout
import android.util.Log;                                                                       // Log
import us.pair;                                                                                // pair
import android.widget.ProgressBar;                                                             // ProgressBar
import us.wallet.protocol;                                                                     // protocol
import us.gov.io.shell_args;                                                                   // shell_args
import us.string;                                                                              // string
import android.widget.TextView;                                                                // TextView
import android.widget.Toast;                                                                   // Toast
import static us.gov.socket.types.ts_t;                                                        // ts_t
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View

public class trader__w2w_w extends trader__conf__role  {

    static void log(final String line) {             //--strip
        CFG.log_android("trader__w2w_w: " + line);    //--strip
    }                                                //--strip

    public trader__w2w_w() {
        super(lib_trader__w2w_w_t.protocol_selection);
    }

    //--------------lifecycle-------------------------------------------------------
    @Override protected void controller__on_create(Bundle saved_state) {
        super.controller__on_create(saved_state);
        log("controller__on_create"); //--strip
        w = (us.cash.scr.trader__w2w_w__widgets) super.w;
        assert w != null; //--strip
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

    @Override public void on_ready(final ko load_result) {
        log("on_ready"); //--strip
        a.assert_ui_thread(); //--strip
        super.on_ready(load_result);
    }

    @Override public us.cash.scr.view__widgets create_widgets() {
        return new us.cash.scr.trader__w2w_w__widgets(new View.OnClickListener() {
            @Override public void onClick(View view) {
                select_coin();
            }
        }
        );
    }

    void select_coin() {
    }

===================================================

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        if (v == null) return null;
        layout = (LinearLayout) inflater.inflate(R.layout.fragment_w2w, null);
        content.addView(layout);
        transfer = layout.findViewById(R.id.transfer);
        amount = layout.findViewById(R.id.amount);
        coin = layout.findViewById(R.id.coin);
        select_coin = layout.findViewById(R.id.select_coin);
        recipient = layout.findViewById(R.id.recipient);
        recipient.setText(tr.getendpoint());
        txlog = (txlog_view) inflater.inflate(R.layout.txlog_view, null);
        content.addView(txlog);
        txlog.init(this, tr);
        transfer.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                on_transfer();
            }
        });
        coin.setText("");
        select_coin.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                on_select_coin();
            }
        });
        dispatchid = a.hmi().dispatcher.connect_sink(this);
        return v;
   }

    @Override public void onDestroyView() {
        super.onDestroyView();
        log("onDestroyView"); //--strip
        a.hmi().dispatcher.disconnect_sink(dispatchid);
    }

    @Override public void onPause() {
        super.onPause();
        log("onPause"); //--strip
    }

    @Override public void onResume() {
        super.onResume();
        log("onResume"); //--strip
        refresh();
    }

    @Override public void on_push(hash_t target_tid, uint16_t code, byte[] payload) {
        super.on_push(target_tid, code, payload);
        log("on_push"); //--strip
        if (!target_tid.equals(tid)) {
            log("not for me"); //--strip
            return;
        }
        switch(code.value) {
            case us.wallet.wallet.local_api.push_txlog:
                log("arrived us.wallet.wallet.local_api.push_txlog"); //--strip
                txlog.update(payload);
                break;
        }
    }

    @Override public boolean refresh() {
        if (!super.refresh()) return false;
        assert tr != null;
        data_t data = tr.get_data();
        if (data == null) {
            tr.setmode_wrong("KO 6053");
            return false;
        }
        if (txlog.entries == null || txlog.entries.isEmpty()) {
            a.hmi().command_trade(tid, "show txlog");
        }
        return true;
    }

    void on_transfer() {
        log("on_transfer"); //--strip
        app.assert_ui_thread(); //--strip
        final String am = amount.getText().toString();
        final String co = coin.getText().toString();
        if (co.isEmpty()) {
            on_select_coin();
//            Toast.makeText(getActivity().getApplicationContext(), "Select coin.", 6000).show();
            return;
        }
        shell_args args = new shell_args(am + " " + co);
        {
            cash_t amount = args.next_cash(new cash_t(0));
            hash_t coin = args.next_token();
            if (amount.value <= 0) {
                Toast.makeText(getActivity().getApplicationContext(), "Enter Amount greater than 0", 6000).show();
                return;
            }
            String cmd = "transfer " + amount.value + " " + coin.encode();
            a.hmi().command_trade(tid, cmd);
            Toast.makeText(getActivity().getApplicationContext(), "Commanded '" + cmd + "'to remote wallet...", 6000).show();
        }
        amount.setText("");
    }

    void on_select_coin() {
        log("on_select_coin"); //--strip
        app.assert_ui_thread();  //--strip
        Thread thread = new Thread(new Runnable() {
            @Override public void run() {
                log("call_balance"); //--strip
                final string ans = new string();
                final ko r = a.hmi().rpc_peer.call_balance(new uint16_t(0), ans);
                String x = null;
                if (is_ko(r)) {
                    x = a.hmi().rewrite(r);
                }
                final String y = x;
                getActivity().runOnUiThread(new Runnable() {
                    @Override public void run() {
                        log("process response"); //--strip
                        if (is_ko(r)) {
                            log(r.msg + " " + y); //--strip
                            Toast.makeText(getActivity().getApplicationContext(), y, 6000).show();
                            return;
                        }
                        final String[] ashit = ans.value.split("\\r?\\n");
                        launchselectdialogue(ashit);
                    }
                });
            }
        });
        thread.start();
    }

    void launchselectdialogue(String[] sourceshit) {
        Intent intent = new Intent(getActivity().getApplicationContext(), selitems.class);
        intent.putExtra("title", getResources().getString(R.string.selectcoin));
        intent.putExtra("sourceshit", sourceshit);
        getActivity().startActivityForResult(intent, SELECTCOIN_RESULT);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        app.assert_ui_thread();  //--strip
        if (requestCode == SELECTCOIN_RESULT) {
            String scanned = data.getData().toString();
            log("Selected coin " + scanned); //--strip
            String[] shit = scanned.split("\\s");
            selected_coin = shit[0];
            selected_coin_max = shit[1];
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    coin.setText(selected_coin + " [MAX " + selected_coin_max + "]");
                }
            });
        }
    }

    public String selected_coin = null;
    public String selected_coin_max = null;
    static final int SELECTCOIN_RESULT = 11;
    String txid = null;
    private MaterialButton transfer;
    private TextView recipient;
    private EditText amount;
    private TextView coin;
    private MaterialButton select_coin;
    int dispatchid;
    private LinearLayout layout;
    txlog_view txlog;
}

