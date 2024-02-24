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
import com.google.android.material.button.MaterialButton;                                      // MaterialButton
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
import android.os.Handler;
import java.util.TimerTask;
import java.util.Timer;

public class fragment_w2w extends role_fragment  {

    static void log(final String line) {             //--strip
        CFG.log_android("fragment_w2w: " + line);    //--strip
    }                                                //--strip

    public fragment_w2w() {
        super(false);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        if (v == null) return null;
        layout = (LinearLayout) inflater.inflate(R.layout.fragment_w2w, null);
        f_content.addView(layout);
        transfer = layout.findViewById(R.id.transfer);
        amount = layout.findViewById(R.id.amount);
        selcoin = layout.findViewById(R.id.selcoin);
        select_coin = layout.findViewById(R.id.select_coin);
        recipient = layout.findViewById(R.id.recipient);
        recipient.setText(tr.getendpoint());
        txlog = (txlog_view) inflater.inflate(R.layout.txlog_view, null);
        f_content.addView(txlog);
        txlog.init(this, tr);
        transfer.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                on_transfer();
            }
        });
        select_coin.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                on_select_coin();
            }
        });
        return v;
   }

    @Override public void onDestroyView() {
        super.onDestroyView();
        log("onDestroyView"); //--strip
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

    @Override public void refresh() {
        super.refresh();
        assert tr != null;
        data_t data = tr.get_data();
        if (data == null) {
            tr.setmode_wrong("KO 6053");
            return;
        }
        if (selected_coin == null) {
            selcoin.setText("-");
        }
        else {
            selcoin.setText("" + coin_name() + " [MAX " + selected_coin_max.value + "]");
        }
        if (txlog.entries == null || txlog.entries.isEmpty()) {
            a.hmi().command_trade(tid, "show txlog");
        }
        return;
    }

    String coin_name() {
        if (selected_coin == null) return "";
        if (selected_coin.is_zero()) {
            return us.CFG.UGAS;
        }
        return selected_coin.encode();
    }

    void on_transfer() {
        log("on_transfer"); //--strip
        app.assert_ui_thread(); //--strip
        final String am = amount.getText().toString();
//        final String co = selcoin.getText().toString();
        if (selected_coin == null) {
            on_select_coin();
            return;
        }
        shell_args args = new shell_args(am);
        {
            cash_t amount = args.next_cash(new cash_t(0));
            if (amount.value <= 0) {
                tr.toast("Enter Amount greater than 0");
                return;
            }
            String cmd = "transfer " + amount.value + " " + coin_name();
            a.hmi().command_trade(tid, cmd);
            tr.toast("Commanded '" + cmd + "'to remote wallet...");
        }
        amount.setText("");
    }

    void on_select_coin() {
        log("on_select_coin"); //--strip
        app.assert_ui_thread();  //--strip
        Intent intent = new Intent(getActivity().getApplicationContext(), coins.class);
        intent.putExtra("mode", 1);
        startActivityForResult(intent, SELECTCOIN_RESULT);
    }

/*
    void on_selcoin(final hash_t coin, final cash_t maxvalue) {
        app.assert_ui_thread();  //--strip
        tr.toast("Selected Coin: " + n + "; max " + maxvalue.value);
    }
*/

    public void onActivityResult(int request_code, int result_code, Intent data) {
        app.assert_ui_thread();  //--strip
        log("onActivityResult request_code:" + request_code + " (" + String.format("%X", request_code) + ") result_code:" + result_code); //--strip
        if (result_code == activity.RESULT_CANCELED) {
            return;
        }
        if (request_code != SELECTCOIN_RESULT) {
            return;
        }
        if (!data.hasExtra("selcoin")) {
            return;
        }
        log("Processing selcoin result"); //--strip
        final byte[] x = data.getByteArrayExtra("selcoin");
        final long x2 = data.getLongExtra("maxcoins", 0);
        selected_coin = new hash_t(x);
        selected_coin_max = new cash_t(x2);
       // Use a Handler to update the TextView on the UI thread
        new Timer().schedule(
            new TimerTask() {
                @Override public void run() {
                    tr.runOnUiThread(new Runnable() {
                        @Override public void run() {
                            if (selected_coin == null) {
                                selcoin.setText("-");
                            }
                            else {
                                selcoin.setText("" + coin_name() + " [MAX " + selected_coin_max.value + "]");
                            }
                        }
                    });
                }
            }, 1000
        );
    }

    static final int SELECTCOIN_RESULT = 11;

    public hash_t selected_coin = null;
    public cash_t selected_coin_max = null;
    String txid = null;
    private MaterialButton transfer;
    private TextView recipient;
    private EditText amount;
    private TextView selcoin;
    private MaterialButton select_coin;
    private LinearLayout layout;
    txlog_view txlog;
}

