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
import android.view.ViewGroup;                                                                 // ViewGroup
import us.ko;
import static us.ko.*;
import android.os.Handler;
import static us.gov.crypto.ripemd160.hash_t;
import java.util.ArrayList;
import androidx.appcompat.app.AlertDialog;                                                     // AlertDialog
import android.content.DialogInterface;                                                        // DialogInterface
##include##

import android.content.Intent;
import static us.stdint.*;
import com.google.android.material.tabs.TabLayout;

public class ##classname## extends ##classname##__base {

    private static void log(final String line) {           //--strip
        CFG.log_android("##classname##: " + line);         //--strip
    }                                                      //--strip

    public ##classname##() {
        super();
        log("##classname## constructor"); //--strip
    }

    //--------------lifecycle-------------------------------------------------------
    @Override protected void controller__on_create(Bundle saved_state) {
        assert w == null; //--strip
        //on_create__call_app = false; //we need to create tabs before configuring screen
        super.controller__on_create(saved_state);
        log("on_create"); //--strip
        w = (us.cash.scr.##classname##__widgets) super.w;
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

##nft_support__select##

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
        return new us.cash.scr.##classname##__widgets();
    }

##add_tabs##

##get_fragment##

##from_intent##

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
##on_tab_reselected__fn##
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

    ##dyntab_fun##
    
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
    us.cash.scr.##classname##__widgets w = null;

    ##dyntab_decl##
}

