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
import us.gov.cash.account_t;
import us.gov.cash.accounts_t;


public final class account_breakdown extends account_breakdown0 {

    private static void log(final String line) {           //--strip
        CFG.log_android("account_breakdown: " + line);         //--strip
    }                                                      //--strip



    public account_breakdown() {
        super();
        log("account_breakdown constructor"); //--strip
    }

    @Override public accounts_t fetch_data() {
        log("fetch_data"); //--strip
        a.assert_worker_thread(); //--strip
        //TODO:feed data to the view here
        return null;
    }

    @Override public boolean on_menu(int id) {
        log("on_menu"); //--strip
        if (id == R.raw.bnew) {
            //on_click_new();
        }
        //else if (id == R.raw.log) {
        //    String i = a.device_endpoints.cur.techinfo();
        //    a.new_dlg_builder().setTitle("HMI Info:").setMessage(i).show();
        //    return true;
        //}
        else {
            return super.on_menu(id);
        }
        ac.close_drawer();
	return false;
    }

   @Override public us.cash.scr.listview_fragment__widgets create_widgets() {
        assert w == null; //--strip
        final account_breakdown self = this;
        w = new us.cash.scr.account_breakdown__widgets(new us.cash.scr.account_breakdown__list_view_t.itemclick_listener_t() {

            @Override public void on_item_click(View view, int position) {
                log("on_item_click" + position); //--strip
                self.item_click(position);
            }

            @Override public void on_long_item_click(View view, int position) {
                log("on_long_item_click" + position); //--strip
                self.item_long_click(position);
            }

            @Override public boolean on_highlighted_item(int pos) {
                log("on_highlighted_item pos " + pos); //--strip
                return self.on_highlighted_item(pos);
            }

        });
        return w;
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saved_state) {
        View v = super.onCreateView(inflater, container, saved_state);
        log("onCreateView"); //--strip
	//w.connimg.setOnTouchListener(...
        return v;
    }

    @Override public void onResume() {
        log("onResume"); //--strip
        super.onResume();
    }

    @Override public void item_click(final int pos) {
        log("item_click pos=" + pos); //--strip
        super.item_click(pos);
    }

    @Override public us.cash.scr.list_view__adapter_t create_adapter() {
        log("create_adapter");
        adapter = new account_breakdown__adapter_t();
        log("create_adapter returns = " + adapter);
        return adapter;
    }

    us.cash.scr.account_breakdown__widgets w = null;
    account_breakdown__adapter_t adapter = null;
}

