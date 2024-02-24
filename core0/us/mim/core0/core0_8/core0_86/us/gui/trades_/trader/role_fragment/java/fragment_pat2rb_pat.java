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
import android.view.LayoutInflater;                                                            // LayoutInflater
import us.pair;                                                                                // pair
import us.string;                                                                              // string
import android.widget.Toast;
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import android.os.Bundle;                                                                      // Bundle
import android.widget.Button;
import static us.gov.crypto.ripemd160.hash_t;                                                  // hash_t
import static us.stdint.*;                                                                     // *
import com.google.android.material.button.MaterialButton;                                      // MaterialButton
import android.widget.TextView;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;                                                            // LayoutInflater
import us.pair;                                                                                // pair
import us.string;                                                                              // string
import android.widget.Toast;
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import android.os.Bundle;                                                                      // Bundle
import android.widget.Button;
import java.util.ArrayList;                                                                    // ArrayList
import android.os.Bundle;                                                                      // Bundle
import android.graphics.Color;                                                                 // Color
import us.gov.socket.datagram;                                                                 // datagram
import us.wallet.trader.data_t;                                                                // data_t
import static android.graphics.BitmapFactory.decodeResource;                                   // decodeResource
import android.app.Fragment;                                                                   // Fragment
import static us.gov.crypto.ripemd160.hash_t;                                                  // hash_t
import static us.stdint.*;                                                                     // *
import static us.tuple.*;                                                                      // *
import android.content.Intent;                                                                 // Intent
import us.ko;                                                                                  // ko
import android.view.LayoutInflater;                                                            // LayoutInflater
import static us.ko.ok;                                                                        // ok
import us.pair;                                                                                // pair
import android.widget.TextView;                                                                // TextView
import android.widget.Toast;                                                                   // Toast
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import android.widget.Button;
import us.wallet.wallet.timeseries_index_t;
import androidx.appcompat.app.AppCompatActivity;                                               // AppCompatActivity
import static us.ko.*;

public class fragment_pat2rb_pat extends role_fragment  {

    static void log(final String line) {                      //--strip
        CFG.log_android("fragment_pat2rb_pat: " + line);    //--strip
    }                                                         //--strip

    public fragment_pat2rb_pat() {
        under_construction = false;
    }


    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saved_state) {
        View v = super.onCreateView(inflater, container, saved_state);

        log("onCreateView"); //--strip

        lbl = new TextView(getContext());
        content.addView(lbl);
        lbl.setText("You are subscribed!. Thanks so much.");
        lbl.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        lbl.setVisibility(View.GONE);
        lbl.setTextColor(Color.parseColor("#00FF00"));
        lbl.setBackgroundColor(Color.parseColor("#000000"));
        return v;
    }

    void on_precontract(final byte[] payload) {
        precontract = payload;
        //if (cat != null) {
        //    select_items();
        //}
        //tr.toast("precontract arrived");
    }

    void fetch_precontract() {
        a.hmi().command_trade(tr.tid, "show precontract");
    }

    void request_precontract() {
        a.hmi().command_trade(tr.tid, "request precontract");
    }

    @Override public String init_cards() {
        log("init_cards"); //--strip
        String cards = "precontract contract ehr";
        return cards;
    }


    void subscribe() {
        select_timeseries();
        
//                a.hmi().command_trade(tid, "subscribe");
    }


    @Override public void on_push(final hash_t target_tid, final uint16_t code, byte[] payload) {
        super.on_push(target_tid, code, payload);
        if (!target_tid.equals(tid)) {
            log("not for me"); //--strip
            return;
        }
        switch(code.value) {
            case us.wallet.trader.workflow.trader_protocol.push_workflow_item: {
                log("a workflow_item for me"); //--strip
                getActivity().runOnUiThread(new Runnable() {
                    @Override public void run() {
                        on_precontract(payload);
                    }
                });
                return;
            }
        }
    }

    void send_ehr() {
        a.hmi().command_trade(tr.tid, "send ehr ");
    }
    

    @Override public boolean refresh() {
        boolean b = super.refresh();
        //_workflow_view.add_view_after("cat", _basket_view);
        _workflow_view.set_action("precontract", "Subscribe", R.drawable.ic_pay_48dp, new View.OnClickListener() {
                @Override public void onClick(View view) {
                    subscribe();
                }
            });
        _workflow_view.set_action("ehr", "Send", R.drawable.ic_send_48dp, new View.OnClickListener() {
                @Override public void onClick(View view) {
                    send_ehr();
                }
            });
        lbl.setVisibility(View.GONE);
        if (trade_state == 1) {
            request_precontract();
//            _workflow_view.set_visible("precontract", false);
//            _workflow_view.set_visible("contract", false);
//            _workflow_view.set_visible("ehr", false);
        }
        else if (trade_state == 3) {
//            _workflow_view.set_visible("precontract", true);
//            _workflow_view.set_visible("contract", false);
//            _workflow_view.set_visible("ehr", false);
        }
        else if (trade_state == 5) {
            _workflow_view.set_visible("precontract", false);
//            _workflow_view.set_visible("contract", true);
//            _workflow_view.set_visible("ehr", true);
            lbl.setVisibility(View.VISIBLE);
            add_archive_button();
        }
        return b;
    }


    static final int SELECTTS_RESULT = 12;

    public void onActivityResult(int request_code, int result_code, final Intent data) {
        if (request_code == SELECTTS_RESULT) {
            if (result_code == AppCompatActivity.RESULT_CANCELED) {
                return;
            }
            if (result_code != AppCompatActivity.RESULT_OK) {
                log("not ok"); //--strip
                return;
            }
            String addr = data.getData().toString();
            hash_t h = new hash_t(addr);
            a.hmi().command_trade(tr.tid, "subscribe " + h.encode());
            scroll_down();
        }
    }
        

    void launchselectdialogue(String[] sourceshit) {
        Intent intent = new Intent(tr, selitems.class);
        intent.putExtra("title", "select timeseries address");
        intent.putExtra("sourceshit", sourceshit);
        startActivityForResult(intent, SELECTTS_RESULT);
    }

    timeseries_index_t timeseries_index = null;
    void select_timeseries() {
        if (timeseries_index == null) {
            timeseries_index = new timeseries_index_t();
            ko r = a.hmi().rpc_peer.call_timeseries_list1(timeseries_index);
            if (is_ko(r)) {
                log(r.msg); //--strip
                return;
            }
        }
        ArrayList<String> x = new ArrayList<String>();
        for (hash_t i: timeseries_index) {
            x.add(i.encode());
        }
        String[] array = x.toArray(new String[x.size()]);

        launchselectdialogue(array);
        
    }


    byte[] precontract = null;
    TextView lbl;
}

