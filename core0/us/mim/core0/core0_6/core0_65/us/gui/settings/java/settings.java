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
//MIM  ******************************************************************************
//MIM  EDITABLE FILE. Changes on this file will NOT be overwritten by MIM.
//MIM  The first version of this file was produced by MIM. You can edit it afterwards.
//MIM
//MIM  Source:
//MIM        file: core0/core0_6/core0_61/us/gui/activity/controller/java/[classname].java
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM  Params:
//MIM    'apifetch': '' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'class_qualifier': '' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'classname': 'settings'
//MIM    'controller': 'activity' @ core0/core0_6/core0_65/us/gui/settings/mim.h
//MIM    'datatype_decl': '' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'get_menu_override': '@Override protected ...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'include': ''
//MIM    'load__worker': '@Override public ko ...' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'menu_max_depth': '1' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'on_menu__handling': '' @ core0/core0_6/core0_61/us/gui/activity/controller/mim.h
//MIM    'title': 'Settings' @ core0/core0_6/core0_65/us/gui/settings/mim.h
//MIM  kickoff code hash: 3mHz71TfVZXbuyb2GBUYRTGKWBbM (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.os.Bundle;                                                                      // Bundle
import us.ko;
import static us.ko.*;
import android.os.Handler;
import us.pair;
import android.content.Intent;                                                                 // Intent

import android.widget.AdapterView;
import java.util.Locale;                                                                       // Locale
import android.content.res.Resources;                                                          // Resources
import android.content.res.Configuration;                                                      // Configuration
import android.widget.ArrayAdapter;                                                            // ArrayAdapter
import android.widget.TextView;                                                                // TextView
import android.view.Gravity;
import android.graphics.Color;                                                                 // Color
import android.view.View;
import android.view.ViewGroup;

public final class settings extends activity {

    private static void log(final String line) {           //--strip
        CFG.log_android("settings: " + line);         //--strip
    }                                                      //--strip

    public settings() {
        super();
        log("settings constructor"); //--strip
    }

    //--------------lifecycle-------------------------------------------------------
    @Override protected void controller__on_create(Bundle saved_state) {
        assert w == null; //--strip
        set_menu_max_depth(1);
        super.controller__on_create(saved_state);
        log("controller__on_create"); //--strip
        w = (us.cash.scr.settings__widgets) super.w;
        assert w != null; //--strip
        load(); //follows on_ready
    }

    @Override protected void controller__on_pause() {
        log("controller__on_pause"); //--strip
        super.controller__on_pause();
    }

    @Override protected void controller__on_resume() {
        super.controller__on_resume();
        log("controller__on_resume"); //--strip
    }

    @Override protected void controller__on_destroy() {
        log("controller__on_destroy"); //--strip
        w = null;
        super.controller__on_destroy();
    }
    //-/------------lifecycle-------------------------------------------------------

    @Override public String get_title() {
        return "Settings";
    }


    //MIM begin token 'load__worker'
    @Override public ko load__worker() {
        log("load__worker"); //--strip
        a.assert_worker_thread(); //--strip
        return ok;
    }
    //MIM end token 'load__worker'

    public void on_ready(final ko load_result) {
        log("on_ready"); //--strip
        a.assert_ui_thread(); //--strip
        bind();
    }

    //MIM begin token 'get_menu_override'
    @Override protected us.cash.scr.menu_t get_menu() {
        if (get_menu_depth() < 1) return super.get_menu();
        assert get_menu_depth() == 1; //--strip
        return us.cash.scr.settings__menu___main_t.get_instance(get_context());
    }
    //MIM end token 'get_menu_override'


    @Override public us.cash.scr.view__widgets create_widgets() {
        return new us.cash.scr.settings__widgets();
    }

    void bind_lang() {
        Resources res = getResources();
        Configuration conf = res.getConfiguration();
        lang = locale.get_lang(this);
        country = locale.get_country(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 0) {
            @Override public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = createTextView();
                textView.setText(getItem(position));
                return textView;
            }
            @Override public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = createTextView();
                textView.setText(getItem(position));
                return textView;
            }
            private TextView createTextView() {
                TextView textView = new TextView(getContext());
                textView.setLayoutParams(new ViewGroup.LayoutParams(
//                        ViewGroup.LayoutParams.WRAP_CONTENT,
//                        ViewGroup.LayoutParams.WRAP_CONTENT));
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                textView.setGravity(Gravity.CENTER);
                //textView.setTextColor(Color.BLACK);
                //textView.setBackgroundColor(Color.WHITE);
                textView.setPadding(10, 10, 10, 10);
                return textView;
            }
        };
        adapter.add("en_GB");
        adapter.add("es_ES");
        w.lang_dropdown.setAdapter(adapter);
        if (lang != null && lang.equals("es")) {
            w.lang_dropdown.setSelection(1);
        }
        else {
            w.lang_dropdown.setSelection(0);
        }
        set_handlers_lang();    

    }

    void bind_dm() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 0) {
            @Override public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = createTextView();
                textView.setText(getItem(position));
                return textView;
            }
            @Override public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = createTextView();
                textView.setText(getItem(position));
                return textView;
            }
            private TextView createTextView() {
                TextView textView = new TextView(getContext());
                textView.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                textView.setGravity(Gravity.CENTER);
                //textView.setTextColor(Color.BLACK);
                //textView.setBackgroundColor(Color.WHITE);
                textView.setPadding(10, 10, 10, 10);
                return textView;
            }
        };
        //adapter.add("Dark");
        adapter.add("System default");
        //adapter.add("Light");
        w.dm_dropdown.setAdapter(adapter);
//        w.dm_dropdown.setSelection(us.cash.app.a.setting__dark_mode);
        w.dm_dropdown.setSelection(0);
        set_handlers_dm();    
    }

    void bind_expertise() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 0) {
            @Override public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = createTextView();
                textView.setText(getItem(position));
                return textView;
            }
            @Override public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = createTextView();
                textView.setText(getItem(position));
                return textView;
            }
            private TextView createTextView() {
                TextView textView = new TextView(getContext());
                textView.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                textView.setGravity(Gravity.CENTER);
                //textView.setTextColor(Color.BLACK);
                //textView.setBackgroundColor(Color.WHITE);
                textView.setPadding(10, 10, 10, 10);
                return textView;
            }
        };
        adapter.add("Beginner");
        adapter.add("Advanced");
        //adapter.add("Light");
        w.expertise_dropdown.setAdapter(adapter);
//        w.dm_dropdown.setSelection(us.cash.app.a.setting__dark_mode);
        w.expertise_dropdown.setSelection(1);
        set_handlers_expertise();    
    }

    void bind() {
        bind_lang();
        bind_dm();
        bind_expertise();
    }
    
    void set_handlers_lang() {
        log("set button handlers"); //--strip
        w.lang_dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selected_string = w.lang_dropdown.getSelectedItem().toString();
                String[] x = selected_string.split("_");
                assert x.length == 2;
                String l = x[0];
                String c = x[1];
                if (lang != null && !lang.equals(l)) {
                    locale.set_lang(getApplicationContext(), l);
                    locale.set_country(getApplicationContext(), c);
                    locale.set(new Locale(l, c));
                    toast(getString(R.string.changelangnext));
                }
            }

            @Override public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    void set_handlers_dm() {
        w.dm_dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                String selected_string = w.dm_dropdown.getSelectedItem().toString();
                //us.cash.app.a.set_setting_dm(position); 
            }

            @Override public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

    }

    void set_handlers_expertise() {
        w.expertise_dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                String selected_string = w.expertise_dropdown.getSelectedItem().toString();
                //us.cash.app.a.set_setting_expertise(position); 
            }

            @Override public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

    }

    us.cash.scr.settings__widgets w = null;

    String lang;
    String country;
}

