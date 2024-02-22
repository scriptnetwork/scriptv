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
import android.os.Bundle;                                                                      // Bundle
import android.view.View;                                                                      // View
import android.view.LayoutInflater;                                                            // LayoutInflater
import android.view.ViewGroup;                                                                 // ViewGroup
import us.ko;
##include##

public abstract class ##classname##0 extends listview_activity {

    private static void log(final String line) {           //--strip
        CFG.log_android("##classname##0: " + line);         //--strip
    }                                                      //--strip

    public ##classname##0() {
        super();
        log("##classname##0 constructor"); //--strip
        set_menu_max_depth(1); //0-app
    }

##get_menu_override##

    public abstract ##datatype## fetch_data();
    public abstract void bind(final ##datatype## o);

    @Override protected void onCreate(Bundle savedInstanceState) {
        log("onCreate"); //--strip
        super.onCreate(savedInstanceState);
        adapter = (##classname##0__adapter_t) super.adapter;
        w = (us.cash.scr.##classname##0__widgets) super.w;
        assert adapter != null;
        assert w != null;
        log("adapter is " + adapter); //--strip
        set_menu_max_depth(1); //0-app
        load();
    }

    @Override protected void onDestroy() {
        log("onDestroy"); //--strip
        w = null;
        adapter = null;
        data = null;
        super.onDestroy();
    }

    @Override public void load__worker() {
        log("load__worker"); //--strip
        a.assert_worker_thread(); //--strip
        data = fetch_data();
        log("data is " + data); //--strip
        if (data != null) {
            log("data size " + data.size()); //--strip
        }
        log("data size - null"); //--strip
    }

    @Override public void on_ready() {
        super.on_ready();
        a.assert_ui_thread(); //--strip
        assert adapter != null;
        adapter.set_data(data);
        bind(data);
    }

    public void item_click(final int pos) {
        log("item_click pos=" + pos); //--strip
        adapter.toggle_sel(pos);
        //##itemtype## item = adapter.get_item(pos);
        //TODO: do something with the list item that has just been selected
    }

    public void item_long_click(final int pos) {
        ##itemtype## item = adapter.get_item(pos);
        //TODO: do something
    }

    public boolean on_highlighted_item(int pos) {
        log("on_highlighted_item pos " + pos); //--strip
        return true;
    }

    us.cash.scr.##classname##0__widgets w = null;
    ##classname##0__adapter_t adapter = null;
    ##datatype## data = null;

}

