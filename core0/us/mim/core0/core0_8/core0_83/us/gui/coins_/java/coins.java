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
import androidx.appcompat.app.ActionBarDrawerToggle;                                           // ActionBarDrawerToggle
import com.journeyapps.barcodescanner.BarcodeEncoder;                                          // BarcodeEncoder
import com.google.zxing.BarcodeFormat;                                                         // BarcodeFormat
import android.graphics.Bitmap;                                                                // Bitmap
import com.google.zxing.common.BitMatrix;                                                      // BitMatrix
import us.wallet.trader.bookmarks_t;                                                           // bookmarks_t
import us.wallet.trader.bookmark_t;                                                            // bookmark_t
import android.os.Bundle;                                                                      // Bundle
import android.content.ClipboardManager;                                                       // ClipboardManager
import android.content.ClipData;                                                               // ClipData
import android.content.Context;                                                                // Context
import android.widget.EditText;                                                                // EditText
import android.widget.LinearLayout;                                                                // EditText
import android.view.GestureDetector;                                                           // GestureDetector
import android.widget.ImageView;                                                               // ImageView
import android.text.InputType;                                                                 // InputType
import static us.ko.is_ko;                                                                     // is_ko
import us.ko;                                                                                  // ko
import android.view.Menu;                                                                      // Menu
import android.view.MenuItem;                                                                  // MenuItem
import android.view.MotionEvent;                                                               // MotionEvent
import com.google.zxing.MultiFormatWriter;                                                     // MultiFormatWriter
import com.google.android.material.navigation.NavigationView;                                  // NavigationView
import android.view.View.OnTouchListener;                                                      // OnTouchListener
import us.pair;                                                                                // pair
import android.widget.RelativeLayout;                                                          // RelativeLayout
import android.view.GestureDetector.SimpleOnGestureListener;                                   // SimpleOnGestureListener
import android.view.View;                                                                      // View
import com.google.zxing.WriterException;                                                       // WriterException
import android.widget.ImageView;                                                               // ImageView
import android.graphics.BitmapFactory;                                                         // BitmapFactory
import android.graphics.Bitmap;                                                                // Bitmap
import com.google.zxing.qrcode.QRCodeWriter;
import java.util.Map;
import com.google.zxing.EncodeHintType;
import java.util.HashMap;
import com.google.android.material.tabs.TabLayout; 
import java.text.NumberFormat;                                                                 // NumberFormat
import android.graphics.Color;
import androidx.annotation.NonNull;
import static us.gov.crypto.ripemd160.hash_t;
import android.content.Intent; 

public final class coins extends fragmented_activity {

    private static void log(final String line) {    //--strip
        CFG.log_android("coins: " + line);          //--strip
    }                                               //--strip

    @Override public String get_title() {
        if (selcoin_mode()) {
            return "Select coin...";
        }
        else {
            return "Coins";
        }
    }

    @Override public void on_tab(int pos) {
        super.on_tab(pos);
    }

    @Override protected void onCreate(Bundle saved_state) {
        log("OnCreate"); //--strip
        super.onCreate(saved_state);

        Bundle bundle = getIntent().getExtras();
        if (getIntent().hasExtra("mode")) {
            mode = bundle.getInt("mode", 0);
        }
        if (getIntent().hasExtra("tabpos")) {
            selected = bundle.getInt("tabpos", 0);
        }
    }

    @Override protected void onPause() {
        log("OnPause"); //--strip
        super.onPause();
    }

    @Override protected void onResume() {
        log("OnResume"); //--strip
        super.onResume();
    }

    @Override protected int add_tabs() {
        log("add_tabs"); //--strip
        add_tab("Merged accounts", R.raw.wallet);  //eg. R.raw.qrico
        add_tab("Account breakdown", R.raw.catalogue); //eg R.raw.qrscanico
        return selected;
    }

    @Override protected fragment get_fragment(int pos) {
        switch (pos) {
            case 0:
                return new merged_accounts();
            case 1:
                return new account_breakdown();
        }
        return null;
    }

    public boolean selcoin_mode() {
        return mode == 0;
    }

    public void select(final hash_t nft) {
        Intent data = new Intent();
        data.putExtra("nft", nft);
        setResult(RESULT_OK, data);
        finish();
    }


    int mode = 0; //; 0 normal; 1 selcoin
    int selected = 0;
}

/*
    @Override public void toolbar_init(toolbar toolbar_) {
        log("toolbar_init"); //--strip
        super.toolbar_init(toolbar_);
        a.toolbar_.enable_refresh(new View.OnClickListener() {
            @Override public void onClick(View view) {
                cur.on_click_refresh();
            }
        });
        a.toolbar_.enable_new(curtab == 0 ? null : new View.OnClickListener() {
            @Override public void onClick(View view) {
                cur.on_click_new();
            }
        });
    }
*/
/*
    @Override protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        log("save_state"); //--strip
        state.putInt("tabpos", tabpos);
    }

    @Override protected void onRestoreInstanceState(@NonNull Bundle saved_state) {
        super.onRestoreInstanceState(saved_state);
        restore_state(saved_state);
    }
*/

/*
    @Override public void refresh() {
        log("refresh"); //--strip
        a.assert_ui_thread(); //--strip
        if (!a.has_hmi()) {
            log("hmi is null"); //--strip
            finish();
            return;
        }
        super.refresh();
    }
*/

