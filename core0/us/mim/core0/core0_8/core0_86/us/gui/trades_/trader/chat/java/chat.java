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
import java.util.ArrayList;                                                                    // ArrayList
import android.os.AsyncTask;                                                                   // AsyncTask
import us.gov.crypto.base58;                                                                   // base58
import us.gov.io.blob_reader_t;                                                                // blob_reader_t
import us.gov.io.blob_writer_t;                                                                // blob_writer_t
import android.os.Build;                                                                       // Build
import android.os.Bundle;                                                                      // Bundle
import us.gov.socket.datagram;                                                                 // datagram
import java.util.Date;                                                                         // Date
import android.widget.EditText;                                                                // EditText
import androidx.fragment.app.Fragment;                                                         // Fragment
import static us.gov.crypto.ripemd160.hash_t;                                                  // hash_t
import android.widget.ImageButton;                                                             // ImageButton
import static us.gov.io.types.*;                                                               // *
import static us.gov.socket.types.*;                                                           // *
import static us.ko.*;                                                                         // *
import static us.stdint.*;                                                                     // *
import static us.tuple.*;                                                                      // *
import static us.wallet.trader.chat.*;                                                         // *
import us.ko;                                                                                  // ko
import android.view.LayoutInflater;                                                            // LayoutInflater
import android.widget.LinearLayout;                                                            // LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager;                                       // LinearLayoutManager
import java.util.Map;                                                                          // Map
import us.pair;                                                                                // pair
import us.wallet.protocol;                                                                     // protocol
import androidx.recyclerview.widget.RecyclerView;                                              // RecyclerView
import java.util.concurrent.locks.ReentrantLock;                                               // ReentrantLock
import android.widget.RelativeLayout;                                                          // RelativeLayout
import androidx.annotation.RequiresApi;                                                        // RequiresApi
import android.annotation.SuppressLint;                                                        // SuppressLint
import android.widget.TextView;                                                                // TextView
import java.util.Timer;                                                                        // Timer
import java.util.TimerTask;                                                                    // TimerTask
import java.util.concurrent.TimeUnit;                                                          // TimeUnit
import java.util.TreeMap;                                                                      // TreeMap
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import android.content.Context;
import us.cash.scr.*;

public final class chat extends fragment {

    private static void log(final String line) {    //--strip
       CFG.log_android("chat: " + line);            //--strip
    }                                               //--strip

    public chat(Context ctx) {
        super(ctx, false);
        w = new chat__widgets();
    }

/*
        buttons.add_stock_button(R.raw.bnew, () -> {
            log("on_new"); //--strip
            on_click_new();
        });

        w.connimg.setOnTouchListener(new View.OnTouchListener() {
            @Override public boolean onTouch(final View view, final MotionEvent motionEvent) {
                connimg.requestLayout();
                int compressed_pic_height = 100;
                if (connimg.getLayoutParams().height == compressed_pic_height) {
                    connimg.getLayoutParams().height = pic_height;
                }
                else {
                    pic_height = connimg.getLayoutParams().height;
                    connimg.getLayoutParams().height = compressed_pic_height;
                }
                return true;
            }
        });
*/

    //-----------------------------------------
//    @Override public void set_listeners(scr.toolbar.buttons_t buttons) {
//        super.add_toolbar_buttons(buttons);
//    }

    @Override protected menu_t get_menu() {
        return super.get_menu();
    }

    @Override protected View get_content() {
        return w.create_tree(getContext());
    }
    //-----------------------------------------

    void set_handlers() {

        refreshbtn.setOnClickListener(v -> {
            log("refresh"); //--strip
            fetch();
        });


    }
/*

        <LinearLayout
            android:id="@+id/home_content"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <ImageView
                android:id="@+id/netactivity"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center_horizontal"
                app:srcCompat="@drawable/ic_drag" />

            <LinearLayout
                android:id="@+id/chat_header"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal"
                >
                    <LinearLayout
                    android:layout_width="wrap_content"
                    android:layout_height="match_parent"
                    android:paddingLeft="10dp"
                    android:orientation="vertical">

                    <TextView
                        android:id="@+id/endpoint_title"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_gravity="left"
                        android:textColor="@android:color/white"
                        android:text="endpoint"/>

                    <TextView
                        android:id="@+id/tx_title"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_gravity="left"
                        android:textColor="@android:color/white"
                        android:text="@string/transaction"/>

                </LinearLayout>

                    <LinearLayout
                        android:layout_width="match_parent"
                        android:layout_height="match_parent"
                        android:gravity="right"
                        android:orientation="horizontal">

                        <us.cash.button
                            android:id="@+id/refreshbtn"
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            android:layout_gravity="right"
                            app:custom_src="raw/refresh"
                            >
                            <include layout="@layout/button"/> 
                        </us.cash.button>

                    </LinearLayout>

            </LinearLayout>
            <View
                android:layout_width="match_parent"
                android:layout_height="1px"
                android:layout_marginHorizontal="10dp"
                android:background="@android:color/black"/>

            
            <androidx.constraintlayout.widget.ConstraintLayout
                xmlns:android="http://schemas.android.com/apk/res/android"
                xmlns:app="http://schemas.android.com/apk/res-auto"
                xmlns:tools="http://schemas.android.com/tools"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:background="#FFF"
                android:fitsSystemWindows="true"
                android:orientation="vertical"
                tools:context=".chat_initiator">


*/
    @SuppressLint("CutPasteId")
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log("OnCreate"); //--strip



//        v = inflater.inflate(R.layout.fragment_chat, container, false);
//        smessage = v.findViewById(R.id.edittext_chatbox);
//        sent = v.findViewById(R.id.button_chatbox_send);
        //refreshbtn= v.findViewById(R.id.refreshbtn);
//        message_recycler = v.findViewById(R.id.message_list);
//        chat_header = v.findViewById(R.id.chat_header);

        Bundle bundle =  getArguments();
        tid = new hash_t(bundle.getByteArray("tid"));
        String lbl = bundle.getString("lbl");
        //tx_title = v.findViewById(R.id.tx_title);
        //endpoint_title = v.findViewById(R.id.endpoint_title);
        //endpoint_title.setText(lbl == null ? "" : lbl);
        byte[] raw = bundle.getByteArray("raw");
        log("raw?" + (raw == null ? "N" : "Y")); //--strip
        //tx_title.setText("id " + tid.encode());
        set_handlers();
        message_array = new ArrayList<>();
        message_adapter = new chat_adapter(v.getContext(), message_array);
        message_adapter.setHasStableIds(true);
        w.capture.requestFocus();
        w.send.setOnClickListener(() -> {
            String txt = w.capture.getText().toString();
            if (txt.isEmpty()) {
                log("empty msg"); //--strip
                return;
            }
            app.assert_ui_thread(); //--strip
            log("MSG " + txt); //--strip
            a.hmi().command_trade(tid, "msg " + txt);
            w.capture.setText("");
        });

        set_payload(raw);
        return v;
    }

    @Override public void onDestroy() {
        super.onDestroy();
        log("onDestroy"); //--strip
    }

    @Override public void on_push(final hash_t target_tid, final uint16_t code, final byte[] payload) {
        log("on_push target_tid " + target_tid.encode() + " tid " + tid.encode()); //--strip
        super.on_push(target_tid, code, payload);
        if (!tid.equals(target_tid)) {
            log("not for me"); //--strip
            return;
        }
        switch(code.value) {
            case us.wallet.trader.trader_t.push_chat: {
                log("a chat for me. "); //--strip
                set_payload__worker(payload);
            }
            break;
        }
    }

    public void set_payload__worker(byte[] raw) {
        app.assert_worker_thread(); //--strip
        chat_t chat = new chat_t();
        blob_reader_t reader = new blob_reader_t(new blob_t(raw));
        ko r = chat.from_blob(reader);
        if (is_ko(r)) {
            log("chat corrupt. "); //--strip
            return;
        }
        log("received chat. lines " + chat.size()); //--strip
        update_shit(chat);
        paint__worker();
    }

    void paint__worker() {
        app.assert_worker_thread(); //--strip
        log("paint_chat_worker. scheduling for UI thread"); //--strip
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                paint();
            }
        });
    }

    public void set_payload(byte[] raw) {
        app.assert_ui_thread(); //--strip;
        if (raw == null) {
            log("fetch"); //--strip
            fetch();
        }
        else {
            log("raw copy available -> just paint"); //--strip
            chat_t chat = new chat_t();
            blob_reader_t reader = new blob_reader_t(new blob_t(raw));
            ko r = chat.from_blob(reader);
            if (is_ko(r)) {
                log("chat corrupt. 2."); //--strip
                return;
            }
            update_shit(chat);
            paint();
        }
    }

    void update_shit(final chat_t ch) {
        //ch.dump("chat> ", System.out);
        lock.lock();
        try {
            message_array.clear();
            for(Map.Entry<ts_t, us.wallet.trader.chat.chat_entry> entry : ch.entrySet()) {
                ts_t key = entry.getKey();
                us.wallet.trader.chat.chat_entry value = entry.getValue();
                Date d = new Date(TimeUnit.MILLISECONDS.convert(key.value, TimeUnit.NANOSECONDS));
                //chat_message cm = new chat_message();
                //cm.date = d;
                //cm.text = value.to_string();
                //log("TEXTHERE: " + cm.text); //--strip
                //cm.source = (value.me) ? chat_message.source_type.M : chat_message.source_type.P;
                //chat_messages.add(cm);

                message_t msg = new message_t(value.to_string(), value.me ? 0 : 1, d);
                message_array.add(msg);
            }
        }
        finally {
            lock.unlock();
        }
        log("last thing to do: updating Adapter"); //--strip
    }


    void paint() {
        app.assert_ui_thread(); //--strip
        log("paint chat"); //--strip
        list_view.setAdapter(message_adapter);
        boolean tracklast = true; //TODO scroll only if last item was visible
        if (tracklast) {
            TimerTask task = new TimerTask() {
                @Override public void run() {
                    if (message_adapter.getItemCount() > 0) {
                        list_view.smoothScrollToPosition(message_adapter.getItemCount() - 1);
                    }
                }
            };
            new Timer().schedule(task, 500);
        }
        //progressbarcontainer.setVisibility(View.GONE);
    }

    void fetch() {
        app.assert_ui_thread(); //--strip;
        //progressbarcontainer.setVisibility(View.VISIBLE);
        log("fetch - UI thread"); //--strip
        a.hmi().command_trade(tid, "show chat");
    }

    chat__widgets w;

//    RecyclerView message_recycler;
//    EditText smessage;
//    ImageButton sent;
    //TextView tx_title;
//    TextView endpoint_title;
    //button refreshbtn;
    private Boolean exit = false;
    private int REQUEST_CODE = 200;
    hash_t tid;
    //RelativeLayout progressbarcontainer;
    ReentrantLock lock = new ReentrantLock();
    ArrayList<message_t> message_array;
//    private ArrayList<chat_message> chat_messages = new ArrayList<chat_message>();
    chat_adapter message_adapter;
//    LinearLayout chat_header;
    View v;
}

