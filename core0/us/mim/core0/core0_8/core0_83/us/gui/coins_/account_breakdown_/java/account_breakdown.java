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
import android.widget.AbsListView;                                                             // AbsListView
import androidx.appcompat.app.ActionBarDrawerToggle;                                           // ActionBarDrawerToggle
import androidx.appcompat.app.ActionBar;                                                       // ActionBar
import android.app.Activity;                                                                   // Activity
import androidx.appcompat.app.AlertDialog;                                                     // AlertDialog
import android.widget.ArrayAdapter;                                                            // ArrayAdapter
import java.util.ArrayList;                                                                    // ArrayList
import android.graphics.BitmapFactory;                                                         // BitmapFactory
import android.graphics.Bitmap;                                                                // Bitmap
import android.os.Build;                                                                       // Build
import android.os.Bundle;                                                                      // Bundle
import android.content.Context;                                                                // Context
import us.gov.socket.datagram;                                                                 // datagram
import java.text.DecimalFormat;                                                                // DecimalFormat
import android.content.DialogInterface;                                                        // DialogInterface
import androidx.drawerlayout.widget.DrawerLayout;                                              // DrawerLayout
import android.widget.FrameLayout;                                                             // FrameLayout
import java.util.HashMap;                                                                      // HashMap
import us.gov.crypto.ripemd160.hash_t;                                                         // hash_t
import android.widget.ImageButton;                                                             // ImageButton
import android.widget.ImageView;                                                               // ImageView
import static us.ko.*;                                                                         // *
import static us.stdint.*;                                                                     // *
import java.io.InputStream;                                                                    // InputStream
import us.ko;                                                                                  // ko
import android.view.LayoutInflater;                                                            // LayoutInflater
import android.widget.LinearLayout;                                                            // LinearLayout
import android.widget.ListView;                                                                // ListView
import android.view.ViewTreeObserver;
import android.view.Menu;                                                                      // Menu
import android.view.MenuItem;                                                                  // MenuItem
import com.google.android.material.navigation.NavigationView;                                  // NavigationView
import java.text.NumberFormat;                                                                 // NumberFormat
import static us.ko.ok;                                                                        // ok
import us.pair;                                                                                // pair
import android.widget.ProgressBar;                                                             // ProgressBar
import us.wallet.protocol;                                                                     // protocol
import android.widget.RelativeLayout;                                                          // RelativeLayout
import androidx.annotation.RequiresApi;                                                        // RequiresApi
import us.string;                                                                              // string
import android.widget.TextView;                                                                // TextView
import android.widget.Toast;                                                                   // Toast
import androidx.appcompat.widget.Toolbar;                                                      // Toolbar
import android.view.ViewGroup;                                                                 // ViewGroup
import android.view.View;                                                                      // View
import us.gov.cash.account_t;
import us.gov.cash.accounts_t;
import java.util.Map;                                                                          // Map
import static us.gov.cash.types.cash_t;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import androidx.recyclerview.widget.DividerItemDecoration;
import android.graphics.Color;
import android.widget.LinearLayout;

//##layer_fqn## = mim::core0::core0_8::core0_83
//##classname## = account_breakdown
//##datatype## = ArrayList<item_t>
//##itemtype## = item_t
//##title## = "balances"
//##resiconmenu150## = R.raw.businesses_150
//##menutitle1## = "Businesses"
//##menutitle2## = ""
//Exit activity -> Exit businesses 
//                    g0.add(R.raw.bnew, "item1");
//                    g0.add(R.raw.log, "item2");
//              =>

public final class account_breakdown extends listview_fragment {

    private static void log(final String line) {           //--strip
        CFG.log_android("account_breakdown: " + line);         //--strip
    }                                                      //--strip

    static class item_t {
        public item_t(final String token, final String amount) {
            this.token = token;
            this.balance = amount;
        }
        public String token;
        public String balance;
    }
    
    public merged_accounts() {
        super();
        log("merged_accounts constructor"); //--strip
    }

    @Override protected us.cash.scr.menu_t get_menu() {
        return us.cash.scr.merged_accounts__widgets.menu__modal_t.get_instance(this);
    }

    @Override protected View get_content() {
        return w.create_tree(this);
    }

    @Override public String get_title() {
        return "balances";
    }

    @Override public us.cash.scr.list_view__adapter_t create_adapter() {
        final merged_accounts self = this;
        adapter = new merged_accounts__adapter_t(data, new merged_accounts__adapter_t.itemclick_listener_t() {
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
        w = new us.cash.scr.merged_accounts__widgets();
        return w;
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
        else {
            super.on_menu(id);
            return;
        }
        close_drawer();
    }

    void item_click(final int pos) {
        item_t item = adapter.get_item(pos);
        //TODO: do something with the list item that has just been selected
    }

    ArrayList<item_t> convert(final account_t account) {
        ArrayList<item_t> coins = new ArrayList<item_t>();
        int sz = 1 + account.num_coins();
        if (sz < min_entries) {
            sz = min_entries;
        }
        coins_.ensureCapacity(sz);
        coins_.add(new item_t(us.CFG.UGAS, app.formatter.format(account.box.value.value)));
        for (Map.Entry<hash_t, cash_t> entry : account.box.t.entrySet()) {
            coins.add(new item_t(entry.getKey().encode(), app.formatter.format(entry.getValue().value)));
        }
        if (coins.size() < min_entries) {
            for (int i = coins.size(); i < min_entries; ++i) {
                coins.add(new item_t("-", "-"));
            }
        }
        return coins;
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
        account_t account = new account_t();
        ko r = a.hmi().rpc_peer.call_merge_accounts(account);
        if (is_ko(r)) {
            report_ko__worker(r);
            return;
        }
        account_ = account;
        data = convert(account_);
    }

    @Override void on_ready() {
        a.assert_ui_thread(); //--strip
        adapter.set_data(data);
    }

    us.cash.scr.merged_accounts__widgets w;
    merged_accounts__adapter_t adapter;
    ArrayList<item_t> data;
    account_t account_;

}

/*
public final class account_breakdown extends fragment {

    private static void log(final String line) {                 //--strip
        CFG.log_android("account_breakdown: " + line);             //--strip
    }                                                            //--strip

    static class inner_item_t {

        public inner_item_t(String token, String amount) {
            this.token = token;
            this.balance = amount;
        }

        public String token;
        public String balance;
    }

    static class inner_view_holder extends RecyclerView.ViewHolder {

        public static interface item_click_listener {
            void on_coin_click(int position);
        }

        public inner_view_holder(View item_view, final item_click_listener listener, final inner_adapter adapter_) {
            super(item_view);
            listener_ = listener;
            layout = item_view.findViewById(R.id.layout);
            coin = item_view.findViewById(R.id.coin);
            balance = item_view.findViewById(R.id.balance);
            if (listener != null) {
/ *
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View view) {
                        int position = getAdapterPosition();
                        //adapter_.toggle_selection(position);
                        if (position != RecyclerView.NO_POSITION) {
                            listener_.on_coin_click(position);
                        }
                    }
                });
* /

            }
        }

        void bind(final inner_item_t item, final int coin_position, boolean selected) {
            log("inner bind " + item.token + " " + selected); //--strip
            coin.setText(item.token);
            balance.setText(item.balance);
            if (selected) {
                layout.setBackgroundColor(coins.selcolor);
            }
            else {
                if ((coin_position & 1) == 0) {
                    layout.setBackgroundColor(coins.odditycolor);
                }
                else {
                    layout.setBackgroundColor(Color.WHITE);
                }
            }
        }

        item_click_listener listener_ = null;
        LinearLayout layout;
        TextView coin;
        TextView balance;
    }

    static class inner_adapter extends RecyclerView.Adapter<inner_view_holder> {

        public inner_adapter() {
            items = null;
        }

        public void set_items(final ArrayList<inner_item_t> items) {
            this.items = items;
            selected = RecyclerView.NO_POSITION;
            notifyDataSetChanged();
            log("inner: notifyDataSetChanged " + getItemCount()); //--strip
        }

        public void set_item_click_listener(inner_view_holder.item_click_listener listener) {
            this.listener = listener;
        }

        @Override public inner_view_holder onCreateViewHolder(ViewGroup parent, int view_type) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.balance_item, parent, false);
            return new inner_view_holder(view, listener, this);
        }

        @Override public void onBindViewHolder(inner_view_holder holder, int position) {
            inner_item_t item = items.get(position);
            holder.bind(item, position, position == selected);
        }

        @Override public int getItemCount() {
            if (items == null) return 0;
            return items.size();
        }

/ *
        public void toggle_selection(int pos) {
            if (pos >= account_.box.t.size()) {
                if (selected != RecyclerView.NO_POSITION) {
                    int sel = selected;
                    selected = RecyclerView.NO_POSITION;
                    notifyItemChanged(sel);
                }
                return;
            }
            if (selected != pos) {
                if (selected != RecyclerView.NO_POSITION) {
                    int sel = selected;
                    selected = RecyclerView.NO_POSITION;
                    notifyItemChanged(sel);
                }
                selected = pos;
                if (selected != RecyclerView.NO_POSITION) {
                    notifyItemChanged(selected);
                }
//                if (pos != RecyclerView.NO_POSITION) {
//                    if (!msgtapagain) {
//                        toast("Tap again on the highlighted address to select it.");
//                        msgtapagain = true;
//                    }
//                }
            }
        }
* /
/ *
        public pair<hash_t, cash_t> get_selected_coin() {
            if (selected == RecyclerView.NO_POSITION) {
                return null;
            }
            Map.Entry<hash_t, cash_t> entry = account_.box.t.entrySet().stream().skip(selected).findFirst().get();
            return new pair<hash_t, cash_t>(entry.getKey(), entry.getValue());
        }
* /
        ArrayList<inner_item_t> items = null;
        inner_view_holder.item_click_listener listener = null;
        int selected = RecyclerView.NO_POSITION;
        boolean msgtapagain = false;
    }

    static class item_t {

        public item_t() {
            this.address0 = hash_t.zero_;
            this.address = "-";
            this.gas = "-";
            this.mint_type = 0;
        }

        public item_t(hash_t address, final account_t account) {
            this.address0 = address;
            this.address = address.encode();
            this.gas = app.formatter.format(account.box.value.value);
            this.mint_type = account.mint_type(address);
            if (account == null) return;
            if (account.box.t == null) return;
            this.subitems = new ArrayList<inner_item_t>();
            int sz = account.num_coins();
            boolean dummy_content = true;              //--strip
            if (dummy_content) {                        //--strip
                if (sz < 20) {                          //--strip
                    sz = 20;                            //--strip
                }                                       //--strip
            }                                           //--strip
            this.subitems.ensureCapacity(sz);
            for (Map.Entry<hash_t, cash_t> entry : account.box.t.entrySet()) {
                this.subitems.add(new inner_item_t(entry.getKey().encode(), app.formatter.format(entry.getValue().value)));
            }
            if (dummy_content) {                                            //--strip
                if (this.subitems.size() < 20) {                            //--strip
                    for (int i = this.subitems.size(); i < 20; ++i) {       //--strip
                        this.subitems.add(new inner_item_t("-", "-"));      //--strip
                    }                                                       //--strip
                }                                                           //--strip
            }                                                               //--strip
        }

        public String snum_items() {
            if (subitems == null) return "-";
            return "" + subitems.size();
        }

        public int num_items() {
            if (subitems == null) return 0;
            return subitems.size();
        }

        public hash_t address0;
        public String address;
        public String gas;
        int mint_type;
        ArrayList<inner_item_t> subitems = null;
    }

    static class view_holder extends RecyclerView.ViewHolder {

        public static interface item_click_listener {
            void on_address_click(int address_position);
            void on_coin_click(int address_position, int coin_position);
        }

        public view_holder(View item_view, final item_click_listener listener, final adapter adapter_) {
            super(item_view);
            listener_ = listener;
            address = item_view.findViewById(R.id.address);
            gaslbl = item_view.findViewById(R.id.gaslbl);
            gas = item_view.findViewById(R.id.gas);
            layout_t_t = item_view.findViewById(R.id.t_t);
            minttype_layout = item_view.findViewById(R.id.minttype_layout);
            numcoins = item_view.findViewById(R.id.numcoins);
            coins_ = item_view.findViewById(R.id.inner_recycler_view);
            mintico = item_view.findViewById(R.id.mintico);
            minttype = item_view.findViewById(R.id.minttype);
            
            layout = item_view.findViewById(R.id.layout);
            if (listener != null) {
/ *
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View view) {
                        super.onClick(view);
                        int position = getAdapterPosition();
                        adapter_.toggle_selection(position);
                        if (position != RecyclerView.NO_POSITION) {
                            listener_.on_address_click(position);
                        }
                    }
                });
* /
            }
        }

        void bind(final item_t item, final int address_position, boolean selected) {
            log("bind " + item.address + " " + selected); //--strip
            address.setText(item.address);
            gaslbl.setText(us.CFG.UGAS);
            gas.setText(item.gas);
            if (item.num_items() > 0) {
                if (item.mint_type != 0) {
                    minttype_layout.setVisibility(View.VISIBLE);
                    switch (item.mint_type) {
                        case 1:
                            mintico.setImageResource(R.raw.activemint);
                            minttype.setText("This mint is active.\nInflationary coin.");
                            break;
                        case 2:
                            mintico.setImageResource(R.raw.sealedmint);
                            minttype.setText("This mint is sealed.\nDeflationary coin.");
                            break;
                    }
                }
                else {
                    minttype_layout.setVisibility(View.GONE);
                }

                numcoins.setText(item.snum_items());
                if (coins_.getLayoutManager() == null) {
                    log("New layout manager pos " + address_position); //--strip
                    coins_.setLayoutManager(new LinearLayoutManager(a));
                    coins_.addItemDecoration(new DividerItemDecoration(a, LinearLayoutManager.VERTICAL));
                    log("New inner_adapter_ " + address_position); //--strip
                    inner_adapter_ = new inner_adapter();
                    final view_holder self = this;
                    inner_adapter_.set_item_click_listener(new inner_view_holder.item_click_listener() {
                        @Override public void on_coin_click(int coin_position) {
                            self.listener_.on_coin_click(address_position, coin_position);
                        }
                    });
                    coins_.setAdapter(inner_adapter_);
                }
                inner_adapter_.set_items(item.subitems);
                final int maxHeight = 400;
                coins_.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override public void onGlobalLayout() {
                        coins_.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        int contentHeight = 0;
                        for (int i = 0; i < coins_.getChildCount(); i++) {
                            View child = coins_.getChildAt(i);
                            contentHeight += child.getMeasuredHeight();
                        }
                        // Set the height of the inner RecyclerView
                        ViewGroup.LayoutParams layoutParams = coins_.getLayoutParams();
                        layoutParams.height = contentHeight > maxHeight ? maxHeight : contentHeight;
                        coins_.setLayoutParams(layoutParams);

                        log("layoutParams.height " + layoutParams.height);
                    }
                });
                layout_t_t.setVisibility(View.VISIBLE);
            }
            else {
                layout_t_t.setVisibility(View.GONE);
            }

            if (selected) {
                layout.setBackgroundColor(coins.selcolor);
            }
            else {
                if ((address_position & 1) == 0) {
                    layout.setBackgroundColor(coins.odditycolor);
                }
                else {
                    layout.setBackgroundColor(Color.WHITE);
                }
            }
        }
        inner_adapter inner_adapter_ = null;

        item_click_listener listener_ = null;
        LinearLayout layout;
        LinearLayout layout_t_t;
        LinearLayout minttype_layout;
        TextView address;
        TextView gaslbl;
        TextView gas;
        TextView numcoins;
        ImageView mintico;
        TextView minttype;
        RecyclerView coins_;
    }

    static class adapter extends RecyclerView.Adapter<view_holder> {

        public adapter() {
            items = new ArrayList<item_t>();
        }

        public void set_item_click_listener(view_holder.item_click_listener listener) {
            this.listener = listener;
        }

        public void set_accounts(final accounts_t accounts) {
            accounts_ = accounts;
            items = new ArrayList<item_t>();
            int sz = accounts.size();
            if (sz < min_entries) {
                sz = min_entries;
            }
            items.ensureCapacity(sz);
            for (Map.Entry<hash_t, account_t> entry : accounts_.entrySet()) {
                hash_t address = entry.getKey();
                account_t account = entry.getValue();
                items.add(new item_t(address, account));
            }
            if (items.size() < min_entries) {
                for (int i = items.size(); i < min_entries; ++i) {
                    items.add(new item_t());
                }
            }
            selected = RecyclerView.NO_POSITION;
            notifyDataSetChanged();
        }

        @Override public view_holder onCreateViewHolder(ViewGroup parent, int view_type) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item, parent, false);
            return new view_holder(view, listener, this);
        }

        @Override public void onBindViewHolder(view_holder holder, int address_position) {
            item_t item = items.get(address_position);
            holder.bind(item, address_position, address_position == selected);
        }

        @Override public int getItemCount() {
            return items.size();
        }

        public void toggle_selection(int pos) {
            if (pos >= accounts_.size()) {
                if (selected != RecyclerView.NO_POSITION) {
                    int sel = selected;
                    selected = RecyclerView.NO_POSITION;
                    notifyItemChanged(sel);
                }
                return;
            }
            if (selected != pos) {
                if (selected != RecyclerView.NO_POSITION) {
                    int sel = selected;
                    selected = RecyclerView.NO_POSITION;
                    notifyItemChanged(sel);
                }
                selected = pos;
                if (selected != RecyclerView.NO_POSITION) {
                    notifyItemChanged(selected);
                }
//                if (pos != RecyclerView.NO_POSITION) {
//                    if (!msgtapagain) {
//                        toast("Tap again on the highlighted address to select it.");
//                        msgtapagain = true;
//                    }
//                }
            }
        }

        public hash_t get_selected_address() {
            if (selected == RecyclerView.NO_POSITION) {
                return null;
            }
            Map.Entry<hash_t, account_t> entry = accounts_.entrySet().stream().skip(selected).findFirst().get();
            return entry.getKey();
        }

        static int min_entries = 50;
        ArrayList<item_t> items;
        view_holder.item_click_listener listener = null;
        accounts_t accounts_ = null;
        int selected = RecyclerView.NO_POSITION;
        boolean msgtapagain = false;
    }

    public account_breakdown() {
        super(true);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saved_state) {
        log("onCreateView"); //--strip
        View v = super.onCreateView(inflater, container, saved_state);
        LinearLayout o = (LinearLayout) inflater.inflate(R.layout.account_breakdown, null);
        f_content.addView(o);

        rv = o.findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(ac));
        rv.addItemDecoration(new DividerItemDecoration(ac, LinearLayoutManager.VERTICAL));

        parent = (coins) ac;
        adapter_ = new adapter();
        final account_breakdown self = this;
        if (parent.selcoin_mode()) {
            adapter_.set_item_click_listener(new view_holder.item_click_listener() {

                @Override public void on_address_click(int address_position) {
                    self.on_address_click(address_position);
                }

                @Override public void on_coin_click(final int address_position, final int coin_position) {
                    self.on_coin_click(address_position, coin_position);
                }
            });
        }
        rv.setAdapter(adapter_);
        return v;
    }

    void on_address_click(int address_position) {
        toast("on_address_click " + address_position); //--strip
/ *
        hash_t address = adapter_.get_selected_address();
        if (address == null) {
            clicked_address = null;
            return;
        }
        if (clicked_address != null) {
            if (address.equals(clicked_address)) {
                choose_address(address);
                return;
            }
        }
        clicked_address = address;
* /
    }

    void on_coin_click(int address_position, int coin_position) {
        toast("on_coin_click " + address_position + " " + coin_position); //--strip
/ *
        hash_t address = adapter_.get_selected_address();
        if (address == null) {
            clicked_address = null;
            return;
        }
        if (clicked_address != null) {
            if (address.equals(clicked_address)) {
                choose_address(address);
                return;
            }
        }
        clicked_address = address;
* /
    }

    void choose_address(final hash_t address) {
/ *
        Intent data = new Intent();
        data.putExtra("seladdr", addre.value);
        data.putExtra("maxcoins", (long) max.value);
        ac.setResult(ac.RESULT_OK, data);
        ac.finish();
* /
    }

    @Override public void onResume() {
        log("onResume"); //--strip
        super.onResume();
        if (!a.has_hmi()) {
            ac.finish();
            return;
        }
        load_accounts();
    }

    @Override public void on_click_refresh() {
        log("on_click_refresh"); //--strip
        super.on_click_refresh();
        load_accounts();
    }

    @Override void on_busy() {
        a.assert_ui_thread(); //--strip
    }

    @Override void on_ready() {
        a.assert_ui_thread(); //--strip
        clicked_address = null;
        adapter_.set_accounts(accounts_);
    }

    void load_accounts__worker() {
        a.assert_worker_thread(); //--strip
        log("load_accounts__worker"); //--strip
        if (!a.has_hmi() || a.hmi().rpc_peer == null) {
            ko r = new ko("KO 70699 HMI is off."); //--strip
            log(r.msg); //--strip
            return;
        }
        accounts_t accounts = new accounts_t();
        ko r = a.hmi().rpc_peer.call_accounts(accounts);
        if (is_ko(r)) {
            report_ko__worker(r);
            return;
        }
        accounts_ = accounts;
    }

    void load_accounts() {
        a.assert_ui_thread(); //--strip
        log("load accounts"); //--strip
        set_busy(true);
        Thread t = new Thread(new Runnable() {
            @Override public void run() {
                load_accounts__worker();
                set_busy__worker(false);
            }
        });
        t.start();
    }

    public void on_click_new() {
        log("on_click_new"); //--strip
        toast("on_new address");
    }

    outer_recycler_view rv;
    adapter adapter_;
    accounts_t accounts_;
    hash_t clicked_address = null; 
    coins parent;
}
*/

