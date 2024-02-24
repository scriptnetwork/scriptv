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

public class fragment_pat2slt_pat extends role_fragment  {

    public enum level_t {
        GP,
        specialist,
        human
    }

    public enum speciality_t {
        unknown,
        dermatology,
        cardiology
    }

    static void log(final String line) {                    //--strip
       CFG.log_android("fragment_pat2slt_pat: " + line);    //--strip
    }                                                       //--strip

    void init_level() {
        data_t data = tr.get_data();
        if (data == null) return;
        String slevel = data.find("remote__level");
        if (slevel != null) {
            if (slevel.equals("0")) {
                level = level_t.GP;
            }
            else if (slevel.equals("1")) {
                level = level_t.specialist;
            }
            else if (slevel.equals("2")) {
                level = level_t.human;
            }
            else {
                level = level_t.GP;
            }
        }
        else {
            level = level_t.GP;
        }

        String sspeciality = data.find("remote__speciality");
        if (sspeciality != null) {
            if (sspeciality.equals("1")) {
                speciality = speciality_t.dermatology;
            }
            else if (sspeciality.equals("2")) {
                speciality = speciality_t.cardiology;
            }
            else {
                speciality = speciality_t.unknown;
            }
        }
        else {
            speciality = speciality_t.unknown;
        }
    }

    @Override public String init_cards() {
        log("init_cards"); //--strip
        init_level();
        String cards = "";
        if (is_GP()) {
            log("is GP. opening chat"); //--strip
            cards = "app";
            tr.openchat(null);
        }
        else if (is_specialist()) {
            if (speciality == speciality_t.dermatology) {
                cards = "app aireq aires pres";
            }
            else if (speciality == speciality_t.cardiology) {
                cards = "app ehr";
            }
        }
        return cards;
    }

    public boolean is_GP() {
        return level == level_t.GP;
    }

    public boolean is_specialist() {
        return level == level_t.specialist;
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saved_state) {
        View v = super.onCreateView(inflater, container, saved_state);
        log("onCreateView"); //--strip
        if (level == level_t.specialist && speciality == speciality_t.cardiology) {
            create_ehr = new Button(getContext());
            create_ehr.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View view) {
                    on_click();
                }
            });
            content.addView(create_ehr);
        }
        return v;
    }
    @Override public void onResume() {
        super.onResume();
        log("onResume"); //--strip
        if (level == level_t.specialist && speciality == speciality_t.cardiology) {
            data_t data = tr.get_data();
            if (data == null) {
                create_ehr.setVisibility(View.GONE);
                return;                
            }
            String have_ehr = data.find("local__wf_ehr");
            if (have_ehr == null) have_ehr = "N";
            if (!have_ehr.equals("Y")) {
                create_ehr.setText("create EHR from timeseries");
            }
            else {
                create_ehr.setText("create another EHR from timeseries");
            }
            create_ehr.setVisibility(View.VISIBLE);
        }
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
            a.hmi().command_trade(tid, "create_ehr_from_timeseries_diff " + h.encode());
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

    
    void on_click() {
        select_timeseries();
    }

    Button create_ehr;
    private level_t level = level_t.GP;
    private speciality_t speciality = speciality_t.unknown;

}

