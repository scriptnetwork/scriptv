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
import android.widget.AdapterView;                                                             // AdapterView
import androidx.appcompat.app.AlertDialog;                                                     // AlertDialog
import android.widget.ArrayAdapter;                                                            // ArrayAdapter
import java.util.ArrayList;                                                                    // ArrayList
import android.graphics.BitmapFactory;                                                         // BitmapFactory
import android.graphics.Bitmap;                                                                // Bitmap
import us.gov.io.blob_reader_t;                                                                // blob_reader_t
import us.wallet.trader.bookmarks_t;                                                           // bookmarks_t
import android.os.Bundle;                                                                      // Bundle
import android.widget.Button;                                                                  // Button
import java.util.Collections;                                                                  // Collections
import android.graphics.Color;                                                                 // Color
import android.widget.CompoundButton;                                                          // CompoundButton
import androidx.coordinatorlayout.widget.CoordinatorLayout;                                    // CoordinatorLayout
import us.wallet.trader.data_t;                                                                // data_t
import java.util.Date;                                                                         // Date
import static android.graphics.BitmapFactory.decodeResource;                                   // decodeResource
import android.content.DialogInterface;                                                        // DialogInterface
import android.graphics.drawable.Drawable;                                                     // Drawable
import androidx.fragment.app.FragmentActivity;                                                 // FragmentActivity
import androidx.fragment.app.Fragment;                                                         // Fragment
import static us.gov.crypto.ripemd160.hash_t;                                                  // hash_t
import android.widget.ImageView;                                                               // ImageView
import static us.gov.io.types.*;                                                               // *
import static us.ko.*;                                                                         // *
import static us.stdint.*;                                                                     // *
import static us.tuple.*;                                                                      // *
import android.content.Intent;                                                                 // Intent
import us.ko;                                                                                  // ko
import android.view.LayoutInflater;                                                            // LayoutInflater
import android.widget.LinearLayout;                                                            // LinearLayout
import com.google.android.material.button.MaterialButton;                                      // MaterialButton
import android.view.MotionEvent;                                                               // MotionEvent
import androidx.annotation.NonNull;                                                            // NonNull
import us.pair;                                                                                // pair
import android.widget.Spinner;                                                                 // Spinner
import android.widget.Switch;                                                                  // Switch
import android.widget.TextView;                                                                // TextView
import java.util.concurrent.TimeUnit;                                                          // TimeUnit
import android.widget.Toast;                                                                   // Toast
import android.net.Uri;                                                                        // Uri
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import us.wallet.trader.protocol_selection_t;                                                                  // qr_t
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class conf_fragment_bid2ask_ask extends conf_fragment {

    static void log(final String line) {                //--strip
       CFG.log_android("conf_fragment_bid2ask_ask: " + line);     //--strip
    }                                                   //--strip

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        View v = super.onCreateView(inflater, container, savedInstanceState);
        CoordinatorLayout w = (CoordinatorLayout) inflater.inflate(R.layout.conf_fragment_bid2ask_ask, null);
        content.addView(w);
        return v;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        log("onDestroyView"); //--strip
    }

    @Override public void onResume() {
        super.onResume();
        log("onResume"); //--strip
    }

    @Override public void onPause() {
        super.onPause();
        log("onPause"); //--strip
    }

    @Override public boolean refresh() {
        return super.refresh();
        //if (tr == null) {
        //    log("KO 86677 REFRESH: activity is null"); //--strip
        //    return;
        //}
/*
        data_t data = tr.get_data();
        if (data == null) {
            tr.setmode_wrong("KO 43029");
            return;
        }
        data_btn.setVisibility(View.VISIBLE);
        String status = data.find("state");
        if (status != null) {
            if (status.equals("online")) {
                online.setChecked(true);
            }
            else {
                online.setChecked(false);
            }
        }
        else {
            online.setChecked(false);
        }

        String age = data.find("online_age");
        if (age == null) {
            onlinetime.setText("");
        }
        else {
            onlinetime.setText(age);
        }

        String ep1 = data.find("remote_endpoint");
        if (ep1 != null) {
            endpoint.setText(ep1);
        }
        if (data == null) {
            state.setText("...");
        }
        else {
            state.setText(data.find("state"));
            String rsn = data.find("reason");
            if (rsn == null) {
                reason.setText("");
            }
            else {
                reason.setText(rsn);
            }
        }
        String sinitiator = data.find("initiator");
        if (sinitiator != null) {
            initiator = sinitiator.equals("Y");
        }
        String peerp = getResources().getString(R.string.anonymous);
        String myp = getResources().getString(R.string.anonymous);
        String pper = data.find("peer_personality");
        if (pper != null) {
            peerp = pper;
        }
        String mper = data.find("peer_moniker");
        if (mper != null) {
            peerp += "\n" + mper;
        }
        String myper = data.find("my_personality");
        if (myper != null) {
            myp = myper;
        }
        String myperm = data.find("my_moniker");
        if (myperm != null) {
            myp += "\n" + myperm;
        }
        if (initiator) {
            personality_initiator.setText(myp);
            personality_follower.setText(peerp);
        }
        else {
            personality_initiator.setText(peerp);
            personality_follower.setText(myp);
        }
        initiator_title.setText(getResources().getString(R.string.initiator) + " (" + (initiator ? getResources().getString(R.string.me) : getResources().getString(R.string.peer)) + ")");
        follower_title.setText(getResources().getString(R.string.follower) + " (" + (!initiator ? getResources().getString(R.string.me) : getResources().getString(R.string.peer)) + ")");
        if (tr.cur_protocol == null) {
            select_protocol_role.setText(getResources().getString(R.string.select_protocol_role));
        }
        else {
            select_protocol_role.setText(getResources().getString(R.string.protocolandrole) + " Cur: " + tr.cur_protocol);
        }
        refresh_peer_ico(data);
        tr.setmode_ready();
*/
    }

/*
    //private trader tr = (trader) getActivity();
    private app a;
    //private hash_t tid;
    private boolean initiator = false;
    private MaterialButton select_protocol_role;
    private Switch online;
    private TextView endpoint;
    private TextView onlinetime;
    private TextView state;
    private TextView reason;
    private TextView personality_initiator;
    private TextView initiator_title;
    private ImageView pic_initiator;
    private TextView personality_follower;
    private TextView follower_title;
    private ImageView pic_follower;
    private MaterialButton closetrade_btn;
    private MaterialButton create_bookmark_btn;
    private MaterialButton log_btn;
    private MaterialButton personality_exchange_btn;
    private MaterialButton data_btn;
    private MaterialButton reset;
    private MaterialButton reload;
    private MaterialButton certs;
    public Bitmap r_peer_ico = null;
    int tries = 0;
*/
    private LayoutInflater inflater;

}

