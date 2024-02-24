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
import com.google.android.material.button.MaterialButton;                                      // MaterialButton
import com.google.android.material.card.MaterialCardView;                                      // MaterialCardView
import android.widget.TextView;                                                                // TextView
import android.widget.Toast;                                                                   // Toast
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import android.os.Bundle;                                                                      // Bundle
import android.widget.Button;                                                                  // Button

public class fragment_co2vc_co extends role_fragment  {

    static void log(final String line) {                       //--strip
        CFG.log_android("fragment_co2vc_co: " + line);    //--strip
    }                                                          //--strip

    public fragment_co2vc_co() {
        under_construction = true;
    }

    @Override public String init_cards() {
        log("init_cards"); //--strip
        String cards = "";
        return cards;
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saved_state) {
        log("onCreateView"); //--strip
        View v = super.onCreateView(inflater, container, saved_state);
        _cockpit_view = (cockpit_view) inflater.inflate(R.layout.cockpit_co2vc_co, null);
        f_content.addView(_cockpit_view);
        return v;
    }

    cockpit_view _cockpit_view;
}
