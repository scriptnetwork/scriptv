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
import android.widget.LinearLayout;                                                                // TextView
import android.widget.Toast;                                                                   // Toast
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import android.widget.Button;
import us.wallet.wallet.timeseries_index_t;
import androidx.appcompat.app.AppCompatActivity;                                               // AppCompatActivity
import static us.ko.*;
import android.view.ViewGroup.LayoutParams;                                                    // LayoutParams
import us.gov.io.types.blob_t.serid_t;
import us.gov.io.blob_reader_t;
import us.gov.io.blob_writer_t;
import android.view.Gravity;
import static us.gov.io.types.blob_t;
import android.widget.Button;

//Front-end for Candidate role.
public class fragment_cdt2hh_cdt extends role_fragment  {

    static void log(final String line) {                      //--strip
        CFG.log_android("fragment_cdt2hh_cdt: " + line);    //--strip
    }                                                         //--strip


    public static class job_t implements us.gov.io.seriable {

        public job_t() {
        }

        @Override public serid_t serial_id() { return serid_t.no_header; }

        @Override public int blob_size() {
            int sz =
                blob_writer_t.blob_size(title) +
                blob_writer_t.blob_size(company) +
                blob_writer_t.blob_size(url);
            return sz;
        }

        @Override public void to_blob(blob_writer_t writer) {
            writer.write(title);
            writer.write(company);
            writer.write(url);
        }

        @Override public ko from_blob(blob_reader_t reader) {
            {
                string s = new string();
                ko r = reader.read(s);
                if (is_ko(r)) return r;
                title = s.value;
            }
            {
                string s = new string();
                ko r = reader.read(s);
                if (is_ko(r)) return r;
                company = s.value;
            }
            {
                string s = new string();
                ko r = reader.read(s);
                if (is_ko(r)) return r;
                url = s.value;
            }
            return ok;
        }

        public String title;
        public String company;
        public String url;

    }

    public static class jobs_t extends ArrayList<job_t> implements us.gov.io.seriable {

        public jobs_t() {}

        @Override public serid_t serial_id() { return serid_t.no_header; }

        @Override public int blob_size() {
            int sz = blob_writer_t.sizet_size(size());
            for (job_t entry : this) {
                sz += blob_writer_t.blob_size(entry);
            }
            return sz;
        }

        @Override public void to_blob(blob_writer_t writer) {
            writer.write_sizet(size());
            for (job_t entry : this) {
                writer.write(entry);
            }
        }

        @Override public ko from_blob(blob_reader_t reader) {
            log("from_blob"); //--strip
            uint64_t sz = new uint64_t();
            {
                ko r = reader.read_sizet(sz);
                if (is_ko(r)) return r;
            }
            clear();
            log("from_blob " + sz.value + " entries"); //--strip
            for (long i = 0; i < sz.value; ++i) {
                job_t s = new job_t();
                {
                    ko r = reader.read(s);
                    if (is_ko(r)) return r;
                    log("READ-chat-from-blob " + s); //--strip
                }
                add(s);
            }
            return ok;
        }
    }


    public fragment_cdt2hh_cdt() {
        under_construction = false;
    }


    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saved_state) {
        View v = super.onCreateView(inflater, container, saved_state);

        log("onCreateView"); //--strip
        {
            lbl = new TextView(getContext()); {
                lbl.setText("Welcome!");
                lbl.setGravity(Gravity.CENTER);
                lbl.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
                //lbl.setVisibility(View.GONE);
                lbl.setTextColor(Color.parseColor("#00FF00"));
                lbl.setBackgroundColor(Color.parseColor("#000000"));
            }
            content.addView(lbl);
        }
        {
            Button fetch = new Button(getContext());
            fetch.setText("Fetch jobs!");

            fetch.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View view) {
                    fetch_jobs();
                }
            });

            content.addView(fetch);
        }        
        {
            jobslo = new LinearLayout(getContext()); {
                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layout.setMargins(8,8,8,8);
                jobslo.setLayoutParams(layout);
                jobslo.setOrientation(LinearLayout.VERTICAL);
            }
            content.addView(jobslo);
        }
        //dispatchid = a.hmi().dispatcher.connect_sink(this);
        return v;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        //a.hmi().dispatcher.disconnect_sink(dispatchid);
    }

    void on_feed__worker(final byte[] payload) {
        blob_reader_t reader = new blob_reader_t(new blob_t(payload));
        jobs = new jobs_t();
        reader.read(jobs);
        tr.runOnUiThread(new Thread(new Runnable() {
            public void run() {
                refresh();
            }
        }));        
    }

    void fetch_jobs() {
        log("fetch jobs"); //--strip
        a.hmi().command_trade(tr.tid, "request_jobs");
    }

    public class us_trader_r2r_cdt2hh {
        public static final int push_begin = us.wallet.trader.trader_protocol.push_r2r_begin;
        public static final int push_jobs = push_begin + 1;
        public static final int push_end = push_begin + 2;
   }

    @Override public void on_push(final hash_t target_tid, final uint16_t code, byte[] payload) {
        super.on_push(target_tid, code, payload);
        log("on_push " + code.value); //--strip

        if (!target_tid.equals(tid)) {
            log("not for me"); //--strip
            return;
        }
        switch(code.value) {
            case us_trader_r2r_cdt2hh.push_jobs: {
                log("feed arrived"); //--strip
                on_feed__worker(payload);
                return;
            }
        }
    }

    @Override public boolean refresh() {
        boolean b = super.refresh();
        jobslo.removeAllViews();
        for (job_t job: jobs) {
            LinearLayout joblo = new LinearLayout(getContext()); {
                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layout.setMargins(8,8,8,8);
                joblo.setLayoutParams(layout);
                joblo.setOrientation(LinearLayout.VERTICAL);
            }
            {
                TextView lbl = new TextView(getContext());
                lbl.setText("Job title: " + job.title);
                lbl.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
                joblo.addView(lbl);
            }
            {
                TextView lbl = new TextView(getContext());
                lbl.setText("Company: " + job.title);
                lbl.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
                joblo.addView(lbl);
            }
            {
                TextView lbl = new TextView(getContext());
                lbl.setText("Apply");
                lbl.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
                lbl.setTextColor(Color.parseColor("#00FF00"));
                lbl.setBackgroundColor(Color.parseColor("#000000"));
                joblo.addView(lbl);
            }
            jobslo.addView(joblo);
        }        
        return b;
    }
    jobs_t jobs = new jobs_t();
    
    TextView lbl;
    LinearLayout jobslo;
    int dispatchid;
}

