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

//##classname## = businesses
//##datatype## = ArrayList<protocol_selection_t>
//##itemtype## = protocol_selection_t
//##title## = "businesses"
//##resiconmenu150## = R.raw.businesses_150
//##menutitle1## = "Businesses"
//##menutitle2## = ""
//Exit activity -> Exit businesses 
//                    g0.add(R.raw.bnew, "item1");
//                    g0.add(R.raw.log, "item2");
//              =>

public final class ##classname## extends listview_fragment {

    private static void log(final String line) {           //--strip
        CFG.log_android("##classname##: " + line);         //--strip
    }                                                      //--strip

    public ##classname##() {
        super();
        log("##classname## constructor"); //--strip
    }

    @Override protected us.cash.scr.menu_t get_menu() {
        return us.cash.scr.##classname##__widgets.menu__modal_t.get_instance(this);
    }

    @Override protected View get_content() {
        return w.create_tree(this);
    }

    @Override public String get_title() {
        return "title";
    }

    @Override public us.cash.scr.list_view__adapter_t create_adapter() {
        final ##classname## self = this;
        adapter = new ##classname##__adapter_t(data, new ##classname##__adapter_t.itemclick_listener_t() {
                @Override public void on_papyrus(int position) {
                    log("on_papyrus" + position); //--strip
                    
                }
                @Override public boolean on_highlighted_item(int position) {
                    log("on_highlighted_item" + position); //--strip
                    self.item_click(position);
                    return true;
                }
            }
        );
        return adapter;
    }

    @Override public us.cash.scr.listview_activity__widgets create_widgets() {
        w = new us.cash.scr.##classname##__widgets();
        return w;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        log("onCreate"); //--strip
        super.onCreate(savedInstanceState);
        load();
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saved_state) {
        log("onCreateView"); //--strip
        View v = super.onCreateView(inflater, container, saved_state);
        load();
        return v;
    }

    @Override public void onResume() {
        log("onResume"); //--strip
        super.onResume();
    }

    @Override public void onPause() {
        log("onPause"); //--strip
        super.onPause();
    }

    @Override public void on_menu(int id) {
        log("on_menu"); //--strip
        if (id == R.raw.bnew) {
        }
        else if (id == R.raw.log) {
        }
        else {
            super.on_menu(id);
            return;
        }
        close_drawer();
    }

    void item_click(final int pos) {
        ##itemtype## item = adapter.get_item(pos);
        //TODO: do something with the list item that has just been selected
    }

    @Override void load__worker() {
        log("load__worker"); //--strip
        a.assert_worker_thread(); //--strip
        if (!a.has_hmi() || a.hmi().rpc_peer == null) {
            ko r = new ko("KO 70699 HMI is not on"); //--strip
            log(r.msg); //--strip
            report_ko__worker(r);
            return;
        }
        //TODO: adjust call & assign data
        /*
        cert_index_t cert_index = new cert_index_t();
        uint8_t mode = new uint8_t(soa ? (byte)1 : (byte)0);
        log("hmi().rpc_peer.call_cert_list"); //--strip
        ko r = a.hmi().rpc_peer.call_cert_list(mode, cert_index);
        if (is_ko(r)) {
            report_ko__worker(r);
            return;
        }
        data = cert_index.to_vector();
        */
    }

    @Override void on_ready() {
        a.assert_ui_thread(); //--strip
        adapter.set_data(data);
    }

    us.cash.scr.##classname##__widgets w;
    ##classname##__adapter_t adapter;
    ##datatype## data;

}

