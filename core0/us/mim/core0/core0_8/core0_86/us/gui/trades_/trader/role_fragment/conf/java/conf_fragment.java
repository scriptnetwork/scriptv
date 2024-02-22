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
import us.gov.io.blob_reader_t;                                                                // blob_reader_t
import us.wallet.trader.bookmarks_t;                                                           // bookmarks_t
import android.os.Bundle;                                                                      // Bundle
import us.gov.socket.datagram;                                                                 // datagram
import us.wallet.trader.data_t;                                                                // data_t
import androidx.fragment.app.Fragment;                                                         // Fragment
import static us.gov.crypto.ripemd160.hash_t;                                                  // hash_t
import static us.gov.io.types.*;                                                               // *
import static us.stdint.*;                                                                     // *
import static us.tuple.*;                                                                      // *
import android.content.Intent;                                                                 // Intent
import static us.ko.is_ko;                                                                     // is_ko
import us.ko;                                                                                  // ko
import android.view.LayoutInflater;                                                            // LayoutInflater
import android.widget.LinearLayout;                                                            // LinearLayout
import com.google.android.material.button.MaterialButton;                                      // MaterialButton
import androidx.core.widget.NestedScrollView;                                                  // NestedScrollView
import us.pair;                                                                                // pair
import us.string;                                                                              // string
import android.widget.Toast;                                                                   // Toast
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View

public abstract class conf_fragment extends Fragment {

    private static void log(final String line) {             //--strip
        CFG.log_android("conf_fragment: " + line);           //--strip
    }                                                        //--strip

    public conf_fragment() {
    }

    public String init_cards() {
        return "";
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saved_state) {
        log("onCreateView"); //--strip
        View v = inflater.inflate(R.layout.conf_fragment, container, false);
        content = v.findViewById(R.id.conf_content);
        scroll =  v.findViewById(R.id.scroll);
        bz = (conf_protocol)getActivity();
        assert bz != null;
        a = (app) bz.getApplication();
        assert a != null;
        if (under_construction) {
            under_construction_view _under_construction_view = (under_construction_view) inflater.inflate(R.layout.under_construction, null);
            content.addView(_under_construction_view);
        }
        String cards = init_cards();
        return v;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        log("onDestroyView"); //--strip
        assert a != null;
    }

    public boolean refresh() {
        assert bz != null;
        app.assert_ui_thread();  //--strip
        bz.setmode_ready();
        return true;
    }

    public app a;
    public conf_protocol bz;
    public ViewGroup content = null;

    public int trade_state = 0;
    public NestedScrollView scroll;

    public boolean under_construction = false;
}

