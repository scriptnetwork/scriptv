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
package us.cash.scr;
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
import android.content.Context;                                                                // Context

public final class chat__widgets extends view__widgets {

    private static void log(final String s) {                 //--strip
        CFG.log_scr("chat__widgets: " + s);                   //--strip
    }                                                         //--strip

    public chat__widgets() {
    }
/*
        new hLinearLayout(ctx);
                android:layout_width="0dp"
                android:layout_height="wrap_content"

                        <EditText
                            android:id="@+id/edittext_chatbox"
                            android:layout_width="0dp"
                            android:layout_height="match_parent"
                            android:background="@null"
                            android:hint="@string/enter_message"
                            android:maxLines="6"
                            android:padding="7dp"
                            android:textCursorDrawable="@null"

                        <ImageButton
                            android:id="@+id/file_send"
                            android:layout_width="wrap_content"
                            android:layout_height="match_parent"
                            android:layout_gravity="center"
                            android:background="@drawable/file_send_shape"
                            android:padding="5dp"
                            android:visibility="invisible"
                            android:src="@drawable/ic_attach_file_black_24dp"


         new button_t
                android:id="@+id/button_chatbox_send"
                android:layout_width="48dp"
                android:layout_height="match_parent"
                android:layout_gravity="center"
                android:background="?android:selectableItemBackgroundBorderless"
                android:clickable="true"
                android:focusable="true"
                android:src="@drawable/ic_send_24dp"
*/

    @Override public View create_tree(Context ctx) { //aka inflate
        return new canvas_t(getContext());
        canvas_t canvas = new canvas_t(ctx, 0); {
            list = new list_view_t(ctx, 1);
            canvas_t input = new canvas_t(ctx, 0, 0); {
                capture = new edit_text_t(ctx); {
                    capture.setHint(R.string.enter_message);
                    capture.setMaxLines(6);
                    capture.setPadding(a.le.border, a.le.border, a.le.border, a.le.border);
                }
                send = new button_t(ctx, R.drawable.ic_send_24dp, null);
                input.addView(capture);
                input.addView(send);
            }
            canvas.addView(list);
            canvas.addView(input);
        }
        return canvas;
    }

    list_view_t list; //RecyclerView message_recycler;
    canvas_t input;
    edit_text_t capture; //EditText smessage;
    button_t send;
}

