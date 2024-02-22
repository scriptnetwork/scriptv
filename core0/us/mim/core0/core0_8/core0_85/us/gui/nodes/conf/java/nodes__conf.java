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
//MIM        file: core0/core0_6/core0_61/us/gui/activity/fragmented_controller/java/[classname].java
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/fragmented_controller/mim.h
//MIM  Params:
//MIM    'add_tabs': 'void add_tabs() {...' @ core0/core0_6/core0_61/us/gui/activity/fragmented_controller/mim.h
//MIM    'classname': 'nodes__conf'
//MIM    'dyntab_decl': 'public final int num...' @ core0/core0_6/core0_61/us/gui/activity/fragmented_controller/mim.h
//MIM    'dyntab_fun': 'public void add_dyn_...' @ core0/core0_6/core0_61/us/gui/activity/fragmented_controller/mim.h
//MIM    'from_intent': 'void from_intent(Int...' @ core0/core0_6/core0_61/us/gui/activity/fragmented_controller/mim.h
//MIM    'get_fragment': 'fragment get_fragmen...' @ core0/core0_6/core0_61/us/gui/activity/fragmented_controller/mim.h
//MIM    'include': 'import us.wallet.tra...'
//MIM    'nft_support__select': 'public void select(S...' @ core0/core0_6/core0_61/us/gui/activity/fragmented_controller/mim.h
//MIM    'on_tab_reselected__fn': 'if (pos < num_static...' @ core0/core0_6/core0_61/us/gui/activity/fragmented_controller/mim.h
//MIM  kickoff code hash: 2sCkzqQBPxq5HHCMFUENMFfrD6Te (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.os.Bundle;                                                                      // Bundle
import android.view.View;                                                                      // View
import android.view.ViewGroup;                                                                 // ViewGroup
import us.ko;
import static us.ko.*;
import android.os.Handler;
import static us.gov.crypto.ripemd160.hash_t;
import java.util.ArrayList;
import androidx.appcompat.app.AlertDialog;                                                     // AlertDialog
import android.content.DialogInterface;                                                        // DialogInterface
import us.wallet.trader.bootstrap.protocols_t; //MIM token 'include'

import android.content.Intent;
import static us.stdint.*;
import com.google.android.material.tabs.TabLayout;
import java.util.concurrent.locks.ReentrantLock;                                               // ReentrantLock
import java.util.concurrent.locks.Lock;                                                        // Lock
import us.wallet.trader.protocol_selection_t;

public class nodes__conf extends nodes__conf__base {

    private static void log(final String line) {           //--strip
        CFG.log_android("nodes__conf: " + line);         //--strip
    }                                                      //--strip

    public nodes__conf() {
        super();
        log("nodes__conf constructor"); //--strip
    }

    //--------------lifecycle-------------------------------------------------------
    @Override protected void controller__on_create(Bundle saved_state) {
        assert w == null; //--strip
        //on_create__call_app = false; //we need to create tabs before configuring screen
        lock.lock();
        super.controller__on_create(saved_state);
        log("on_create"); //--strip
        w = (us.cash.scr.nodes__conf__widgets) super.w;
        assert w != null; //--strip
        w.tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override public void onTabSelected(TabLayout.Tab tab) {
                if (tabs_listener_disabled) return;
                on_tab_selected(tab.getPosition());
            }

            @Override public void onTabUnselected(TabLayout.Tab tab) {
                log("tab unselected. position " + tab.getPosition()); //--strip
                if (tabs_listener_disabled) return;
            }

            @Override public void onTabReselected(TabLayout.Tab tab) {
                log("tab reselected. position " + tab.getPosition()); //--strip
                if (tabs_listener_disabled) return;
                on_tab_reselected(tab.getPosition());
            }
        });
        from_intent(getIntent());
        retab();
        if (mode == 1) {
            w.show_header(mode1_header);
        }
        lock.unlock();
    }

    @Override protected void controller__on_pause() {
        log("on_pause"); //--strip
        super.controller__on_pause();
    }

    @Override protected void controller__on_resume() {
        super.controller__on_resume();
        log("on_resume"); //--strip
    }

    @Override protected void controller__on_destroy() {
        log("on_destroy"); //--strip
        w = null;
        cur = null;
        fragments = null;
        super.controller__on_destroy();
    }
    //-/------------lifecycle-------------------------------------------------------

    //MIM begin token 'nft_support__select'
    public void select(String nft) {
        Intent data = new Intent();
        data.putExtra("nft", nft);
        setResult(RESULT_OK, data);
        finish();
    }
    //MIM end token 'nft_support__select'

    @Override protected us.cash.scr.menu_t get_menu() {
        log("get_menu. depth=" + get_menu_depth()); //--strip
        if (get_menu_depth() < 1) return super.get_menu();
        assert get_menu_depth() == 1;
        assert cur != null; //--strip
        if (cur.ac == null) cur.ac = this;
        us.cash.scr.menu_t f_menu = cur.get_menu();
        assert f_menu != null; //--strip
        return f_menu;
    }

    @Override public boolean on_menu(int id) {
        log("on_menu"); //--strip
        if (cur != null) {
            if (cur.on_menu(id)) {
                return true;
            }
        }
        return super.on_menu(id);
    }

    @Override public us.cash.scr.view__widgets create_widgets() {
        return new us.cash.scr.nodes__conf__widgets();
    }

    //MIM begin token 'add_tabs'
    void add_tabs() {
        log("add_tabs"); //--strip
        if (onlytab != -1) {
            switch(onlytab) {
                case 0:
                    add_tab(0, "Bookmarks", R.raw.bookmark);
                    break;
                case 1:
                    add_tab(0, "Wallets", R.raw.world);
                    break;
            }
            return;
        }
        add_tab(0, "Bookmarks", R.raw.bookmark);
        add_tab(1, "Wallets", R.raw.world);
        add_dyn_0_tabs();
    }

    void add_dyn_0_tabs() {
        log("add_dyn_0_tabs"); //--strip
        int pos = num_static_tabs;
        for (protocol_selection_t i: dyn_tabs_0) {
            add_tab(pos++, i.to_string(), R.raw.r2rtab);
        }
    }
    //MIM end token 'add_tabs'

    //MIM begin token 'get_fragment'
    fragment get_fragment(int pos) {
        log("get_fragment " + pos); //--strip
        if (onlytab != -1) {
            assert pos == 0;
            switch(onlytab) {
                case 0: return new nodes__conf__bookmarks();
                case 1: return new nodes__conf__wallets();
                default: return null;
            }
        }
        if (pos < num_static_tabs) {
            switch(pos) {
                case 0: return new nodes__conf__bookmarks();
                case 1: return new nodes__conf__wallets();
                default: return null;
            }
        }
        pos -= num_static_tabs;
        if (pos < dyn_tabs_0.size()) {
            return new nodes__conf__r2r(dyn_tabs_0.get(pos));
        }
        return null;
    }
    //MIM end token 'get_fragment'

    //MIM begin token 'from_intent'
    void from_intent(Intent i) {
        if (i == null) {
            log("from_intent null intent. " + curtab + " " + mode); //--strip
            return;
        }
        Bundle bundle = i.getExtras();
        if (i.hasExtra("mode")) {
            mode = bundle.getInt("mode", 0);
        }
        if (i.hasExtra("mode1_header")) {
            mode1_header = bundle.getString("mode1_header", "Select item...");
        }
        if (i.hasExtra("curtab")) {
            curtab = bundle.getInt("curtab", 0);
        }
        if (i.hasExtra("onlytab")) {
            onlytab = bundle.getInt("onlytab", -1);
            curtab = 0;
        }
        log("from_intent " + curtab + " " + mode); //--strip
    }
    //MIM end token 'from_intent'

    protected void add_tab(int pos, final String title, final int res_ico) {
        TabLayout.Tab tab = w.tabs.newTab().setText(title);
        tab.setIcon(us.cash.app.a.le.resolve_resid(res_ico));
        w.tabs.addTab(tab, pos);
    }

    void retab() {
        log("retab before " + w.tabs.getTabCount()); //--strip
        tabs_listener_disabled = true;
        while (w.tabs.getTabCount() > 0) {
            w.tabs.removeTabAt(w.tabs.getTabCount() - 1);
        }
        add_tabs();
        int count = w.tabs.getTabCount();
        log("retab after " + count + " selected pos: " + curtab); //--strip
        fragments = new ArrayList<fragment>(count);
        for (int i = 0; i < count; ++i) {
            fragments.add(null);
        }
        tabs_listener_disabled = true;
        w.tabs.getTabAt(curtab).select();
        tabs_listener_disabled = false;
        on_tab_selected(curtab);
    }

    void on_tab_reselected(int pos) {
        //MIM begin token 'on_tab_reselected__fn'
        if (pos < num_static_tabs) return;
        popup_closetab();
        //MIM end token 'on_tab_reselected__fn'
    }

    void on_tab_selected(int pos) {
        log("on_tab_selected pos: " + pos); //--strip
        if (fragments.get(pos) == null) {
            log("obtaining fragment for pos " + pos); //--strip
            fragment f = get_fragment(pos);
            if (f == null) {                        //--strip
                log("Null fragment");               //--strip
                assert false;                       //--strip
                return;                             //--strip
            }                                       //--strip
            fragments.set(pos, f);
        }
        curtab = pos;
        cur = fragments.get(curtab);
        assert cur != null; //--strip
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                getSupportFragmentManager().beginTransaction().replace(w.frame.getId(), cur).commitNowAllowingStateLoss();
                a.configure_screen();
                on_tab(curtab);
            }
        }, 1);
    }

    public void on_tab(int pos) {
        log("on_tab"); //--strip
    }

    //MIM begin token 'dyntab_fun'
    public void add_dyn_tab_0(protocol_selection_t o) {
        log("add_dyn_tab_0 " + o.to_string()); //--strip
        int p = -1;
        {
            int n = 0;
            for (protocol_selection_t i: dyn_tabs_0) {
                if (i.equals(o)) {
                    p = n;
                }
                ++n;
            }
        }
        tabs_listener_disabled = true;
        int num_prev = num_static_tabs;
        if (p == -1) {
            dyn_tabs_0.add(o);
            curtab = num_prev + dyn_tabs_0.size() - 1;
            fragments.add(curtab, null);
            add_tab(curtab, o.to_string(), R.raw.r2rtab);
        }
        else {
            curtab = num_prev + p;
        }
        w.tabs.getTabAt(curtab).select();
        tabs_listener_disabled = false;
        on_tab_selected(curtab);
    }

    void popup_closetab() {
        String[] options = new String[]{"Close tab", a.getResources().getString(R.string.cancel)};
        AlertDialog.Builder dlg = a.new_dlg_builder(get_context());
        dlg.setTitle("Tab").setItems(options, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        close_tab();
                        break;
                }
            }
        }).setIcon(R.raw.gear).show();
    }

    void close_tab() {
        if (curtab < num_static_tabs) return;
        dyn_tabs_0.remove2(curtab - num_static_tabs);
        fragments.remove(curtab);
        --curtab;
        w.tabs.removeTabAt(curtab + 1);
    }
    //MIM end token 'dyntab_fun'
    
    public int get_mode() {
        return mode;
    }

    public boolean mode__normal() { return mode == 0; }
    public boolean mode__pick() { return mode == 1; }

    String mode1_header = null;
    int mode = 0; //; 0 normal; 1 select item
    protected int onlytab = -1;
    protected int curtab = 0;
    protected fragment cur = null;

    ArrayList<fragment> fragments = null;
    boolean tabs_listener_disabled = false;
    us.cash.scr.nodes__conf__widgets w = null;

    //MIM begin token 'dyntab_decl'
    public final int num_static_tabs = 2;

    public static class dyntabs_t<T> extends ArrayList<T> {
    
        public boolean remove2(int i) {
            if (i >= size()) return false;
            super.remove(i);
            return true;
        }

    }

    public dyntabs_t<protocol_selection_t> dyn_tabs_0 = new dyntabs_t<protocol_selection_t>();
    //MIM end token 'dyntab_decl'

    public void on_ready(final ko load_result) { //r2r index is ready
        log("on_ready"); //--strip
        a.assert_ui_thread(); //--strip
        lock.lock();
        //all tabs done
        lock.unlock();
        toast("r2r_index.size=" + r2r_index.size());
    }
    Lock lock = new ReentrantLock();
 
}
