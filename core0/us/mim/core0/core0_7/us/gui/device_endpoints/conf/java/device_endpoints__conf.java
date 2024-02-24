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
//MIM        file: core0/core0_6/core0_61/us/gui/activity/listview_controller/java/[classname].java
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM  Params:
//MIM    'classname': 'device_endpoints__conf'
//MIM    'conf_button__click_handler': '@Override public voi...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM    'data_identifier': 'deps' @ core0/core0_7/us/gui/device_endpoints/mim.h
//MIM    'datatype': 'device_endpoints_t' @ core0/core0_7/us/gui/device_endpoints/mim.h
//MIM    'highlight_nft__def': '' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM    'include': ''
//MIM    'itemtype': 'device_endpoint_t' @ core0/core0_7/us/gui/device_endpoints/mim.h
//MIM    'nft_support__bind': '' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM    'popups': 'public void popup_me...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM  kickoff code hash: 2FhJHBFtq6y6bwMe3mLu1B64VbpV (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.os.Bundle;                                                                      // Bundle
import android.view.View;                                                                      // View
import android.view.LayoutInflater;                                                            // LayoutInflater
import android.view.ViewGroup;                                                                 // ViewGroup
import android.content.Context;
import us.ko;
import us.pair;
import static us.ko.*;
import android.os.Handler;
import java.util.ArrayList;
import androidx.appcompat.app.AlertDialog;                                                     // AlertDialog
import android.content.DialogInterface;                                                        // DialogInterface
import android.content.Intent;                                                                 // Intent
import us.gov.crypto.ripemd160.hash_t;                                                         // hash_t
import static us.gov.id.types.*;                                                               // *
import static us.gov.io.types.*;                                                               // *
import static us.gov.socket.types.*;                                                           // *
import static us.stdint.*;                                                                     // *
import us.stdint.*;                                                                            // *
import us.tuple.*;
import us.gov.crypto.base58;                                                                   // base58
import us.wallet.engine.wallet_connection_t;                                                   // wallet_connection_t
import us.wallet.engine.wallet_connections_t;                                                   // wallet_connection_t
import us.gov.io.blob_reader_t;                                                                // blob_reader_t
import java.util.Vector;


public final class device_endpoints__conf extends device_endpoints__conf0
         implements us.cash.scr.list_view_t.itemclick_listener_t,
                    us.cash.scr.device_endpoints__conf__list_view__itemview__widgets.listener_t,
                    device_endpoints_t.monitor_handler_t {

    private static void log(final String line) {           //--strip
        CFG.log_android("device_endpoints__conf: " + line);         //--strip
    }                                                      //--strip

    public device_endpoints__conf() {
        super();
        log("device_endpoints__conf constructor"); //--strip
    }

    //--------------lifecycle-------------------------------------------------------
    @Override protected void controller__on_create(Bundle saved_state) {  //create/resume order: 1-general class; 2-specialized class
        log("on_create"); //--strip
        super.controller__on_create(saved_state);
        w = (us.cash.scr.device_endpoints__conf__widgets) super.w;
        assert w != null; //--strip
    }

    @Override protected void controller__on_pause() { //destroy/pause order: 1-specialized class; 2-general class
        log("controller__on_pause"); //--strip
        a.device_endpoints.hmi__remove_listener(this);
        super.controller__on_pause();
    }

    @Override protected void controller__on_resume() { //create/resume order: 1-general class; 2-specialized class
        super.controller__on_resume();
        log("controller__on_resume"); //--strip
        w.list_view.notify_dataset_changed();
        a.device_endpoints.hmi__add_listener(this);
    }

    @Override protected void controller__on_destroy() { //destroy/pause order: 1-specialized class; 2-general class
        log("controller__on_destroy"); //--strip
        w = null;
        super.controller__on_destroy();
    }
    //-/------------lifecycle-------------------------------------------------------

    @Override public pair<ko, device_endpoints_t> fetch_deps() { //MIM token 'apifetch'
        log("fetch_deps sz=" +  a.device_endpoints.size()); //--strip
        return new pair(ok, a.device_endpoints);
    }
    
    public void bind(final device_endpoints_t o) {
        log("bind"); //--strip
    }

    @Override public us.cash.scr.view__widgets create_widgets() {
        final device_endpoints__conf self = this;
        return new us.cash.scr.device_endpoints__conf__widgets(this, this);
    }

    //    +-----+------------------------------+-------+
    //    | <========================================> |
    //    | <===+=============click============+=====> +
    //    | <========================================> |
    //    +-----+--------------------------------------+
    @Override public void on_item__click(View v, final int pos) {
        log("on_item__click pos=" + pos); //--strip
        //w.list_view.toggle_sel(pos);
    }

    @Override public void on_item__long_click(View v, final int pos) {
        log("on_item__long_click pos " + pos); //--strip
        //TODO: do something
        //device_endpoint_t item = w.list_view.get_item(pos);
    }

    @Override public boolean on_item__highlighted(int pos) {
        log("on_item__highlighted_item pos " + pos); //--strip
        //device_endpoint_t item = w.list_view.get_item(pos);
        return false; //true: leaves it highlighted; false: changes to not highlighted
    }

    //    +-----+------------------------------+-------+
    //    | ico | head                         | btn <-+--- click
    //    |     |------------------------------+-------+
    //    |     | tail                                 |
    //    +-----+--------------------------------------+
    //MIM begin token 'conf_button__click_handler'
    @Override public void on_poweron__changed(int position, boolean ison) {
        log("on_poweron__changed " + position); //--strip
        if (ison) {
            on_user_req_poweron(position);
        }
        else {
            on_user_req_poweroff(position);
        }
    }

    @Override public void on_conf__click(int position) {
        log("on_conf__click " + position); //--strip
        device_endpoint_t item = w.list_view.get_item(position);
        a.go_conf(item);
    }

    @Override public void on_menu__click(int position) {
        log("on_menu__click " + position); //--strip
        popup_menu__conf(position);
    }

    @Override public void on_trash__click(int position) {
        log("on_trash__click " + position); //--strip
        delete_entry(position);
    }
    //MIM end token 'conf_button__click_handler'

/*
    public boolean list_view__scrolldown_flash() {
        return w.listview_w.scrolldown_flash();

    }

    public boolean flash(final int pos) {
        return w.listview_w.flash(pos);
    }
*/

    @Override public void on_ready(final ko load_result) {
        log("on_ready"); //--strip
        a.assert_ui_thread(); //--strip
        w.list_view.set_data(deps);
        bind(deps);
    }

    //MIM begin token 'popups'
    public void popup_menu__conf(final int pos) {
        final device_endpoint_t dep = a.device_endpoints.get(pos);
        final String name = dep.get_title();
        String onoff_hmi;
        if (dep.hmi == null) {
            onoff_hmi = "Turn ON";
        }
        else {
            onoff_hmi = "Turn OFF";
        }
        String[] options = new String[]{"Configure connection", onoff_hmi, "Delete connection", "Copy into a new connection", a.getResources().getString(R.string.cancel)};
        final device_endpoints__conf self = this;
        AlertDialog.Builder dlg = a.new_dlg_builder(get_context());
        dlg.setTitle(name).setItems(options, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        a.go_conf(dep);
                        break;

                    case 1:
                        if (dep.hmi != null) {
                            log("stop HMI"); //--strip
                            stop_hmi(true);
                        }
                        else {
                            log("start HMI"); //--strip
                            start_hmi(pos);
                        }
                        break;

                    case 2:
                        delete_entry(pos);
                        break;

                    case 3:
                        {
                            ko r = self.a.device_endpoints.copy_device_endpoint(pos);
                            if (is_ko(r)) {
                                toast(r.msg);
                                break;
                            }
                            w.list_view.notify_item_inserted();
                            refresh();
                        }
                        break;

                    case 5:
                }
            }
        }).setIcon(R.raw.gear).show();
    }
    //MIM end token 'popups'

    void redraw_ui(int pos) {
        w.list_view.notify_item_changed(pos);
    }

    void ask_stop_current(final int desired_position) {
        if (a.device_endpoints.cur == null) return;
        final device_endpoint_t curdep = a.device_endpoints.cur;
        final String curname = curdep.get_title();
        final device_endpoints__conf o = this;
        AlertDialog.Builder builder = a.new_dlg_builder(get_context());
        builder.setTitle("Stop current connection?");
        builder.setMessage("Only one simultaneous connection is possible. Do you want to stop the current one ('" + curname + "') before proceed?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                o.resume__poweron = desired_position;
                o.a.HMI_power_off(a.device_endpoints.cur_index.value, false);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                redraw_ui(desired_position);
                dialog.dismiss();
            }
        }).create().show();
    }

    void on_user_req_poweron(int desired_position) {
        a.assert_ui_thread(); //--strip
        log("on_user_req_poweron"); //--strip
        if (a.device_endpoints.cur != null && a.device_endpoints.cur.hmi != null) {
            ask_stop_current(desired_position);
            return;
        }
        a.HMI_power_on(desired_position, new pin_t(0));
    }

    void on_user_req_poweroff(int position) {
        a.assert_ui_thread(); //--strip
        log("on_user_req_poweroff"); //--strip
        a.HMI_power_off(position, true);
    }

    void ask_dep() {
        if (CFG.default_wallet_connections.isEmpty()) {
            new_connection(null);
            return;
        }
        blob_t blob = new blob_t(base58.decode(CFG.default_wallet_connections));
        if (blob.value == null) {
            log("default connection blob is null"); //--strip
            new_connection(null);
            return;
        }
        wallet_connections_t wallet_connections = new wallet_connections_t();
        blob_reader_t reader = new blob_reader_t(blob);
        ko r = reader.read(wallet_connections);
        if (is_ko(r)) {
            log(r.msg); //--strip
            new_connection(null);
            return;
        }
        log("adding default device_endpoints "); //--strip
        Vector<String> optionsv = new Vector<String>();
        optionsv.add("Cancel");
        optionsv.add("New Non-Custodial Wallet");

        final Vector<device_endpoint_t> v = new Vector<>();
        for (wallet_connection_t entry: wallet_connections) {
            device_endpoint_t dep = new device_endpoint_t(a.device_endpoints, entry);
            v.add(dep);
            optionsv.add(dep.get_title());
        }
        if (v.isEmpty()) {
            new_connection(null);
            return;
        }
        if (v.size() == 1) {
            new_connection(v.get(0));
            return;
        }
        String[] options = optionsv.toArray(new String[optionsv.size()]); //v.toArray(new String[v.size()]);
        AlertDialog.Builder dlg = a.new_dlg_builder(get_context());
        dlg.setTitle("Choose template for Custodial wallet").setItems(options, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (which == 0) {
                    toast("Cancelled");
                    return;
                }
                if (which == 1) {
                    new_connection(null);
                    toast("New connection to a non-custodial wallet.");
                    return;
                }
                which -= 2;
                if (which < v.size()) {
                    new_connection(new device_endpoint_t(a.device_endpoints, wallet_connections.get(which)));
                    toast("New connection to a custodial wallet.");
                    return;
                }
            }
        }).setIcon(R.drawable.ic_itemlist).show();
    }


    void delete_entry2(final int position) {
        ko r = a.device_endpoints.erase(position);
        if (is_ko(r)) {
            toast(r.msg);
            return;
        }
        w.list_view.notify_item_removed(position);
        refresh();
    }

    void delete_entry(final int position) {
        final device_endpoint_t dep = a.device_endpoints.get(position);
        if (dep.is_powered_on()) {
            toast("An active connection cannot be deleted.");
            return;
        }
        AlertDialog.Builder dlg = a.new_dlg_builder(get_context());
        dlg.setTitle("The connection settings for '" + dep.get_title() + "' will be deleted.")
            .setMessage("Confirm?")
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override public void onClick(DialogInterface dialog, int whichButton) {
                    delete_entry2(position);
                }})
             .setNegativeButton(android.R.string.no, null).show();
    }

    @Override protected void on_menu__main__bnew() {
        a.assert_ui_thread(); //--strip
        if (a.device_endpoints == null) {
            toast("device_endpoints failure");
            return;
        }
        ask_dep();
    }

    @Override protected void on_menu__main__log() {
        a.assert_ui_thread(); //--strip
        if (a.device_endpoints.cur == null) return;
        final String i = a.device_endpoints.cur.techinfo();
        a.new_dlg_builder(get_context()).setTitle("HMI Info:").setMessage(i).show();
    }

    void new_connection(device_endpoint_t dep) {
        if (a.device_endpoints == null) {
            toast("device_endpoints failure");
            return;
        }
        if (dep != null) {
            a.device_endpoints.add(dep);
        }
        else {
            ko r = a.device_endpoints.new_endpoint();
            if (is_ko(r)) {
                toast(r.msg);
            }
            //else {
            //    toast("Added a new wallet connection at the bottom of the list.");
            //}
        }
        w.list_view.notify_item_inserted();
        refresh();
//        scrolldown_flash();
    }

    void update_poweredon_item__worker() {
        a.assert_worker_thread(); //--strip
        final device_endpoints__conf self = this;
        final int pos = a.device_endpoints.cur_index.value;
        if (pos < 0) {            //--strip
            log("KO 70699 Receiving "); //--strip
            assert false;               //--strip
            return;                     //--strip
        }                               //--strip
        runOnUiThread(new Runnable() {
            @Override public void run() {
                self.w.list_view.notify_item_changed(pos);
            }
        });
        log("adapter.notifyItemChanged. XXII. pos=" + a.device_endpoints.cur_index.value); //--strip
    }

    @Override public void on_hmi__worker(final device_endpoint_t src, final ko status) {
        a.assert_worker_thread(); //--strip
        log("on_hmi__worker. XXII. " + (status == ok ? "ok" : status.msg)); //--strip
        if (resume__poweron != -1) { //old hmi has just powered off, start the new one
            log("resume poweron"); //--strip
            if (is_ko(status)) { //(status == hmi_t.KO_10001) { // I Disconnected
                log("on_hmi__worker status=" + status.msg); //--strip
                final int pos = resume__poweron;
                resume__poweron = -1;
                final device_endpoints__conf o = this;
                Thread thread = new Thread(new Runnable() {
                    @Override public void run () {
                        try {
                            Thread.sleep(100);
                        }
                        catch (Exception e) {
                        }
                        o.a.HMI_power_on__worker(pos, new pin_t(0));
                    }
                });
                thread.start();
            }
            else {
                log("on_hmi__worker status= OK"); //--strip
            }
        }
    }

    @Override public void on_status(final device_endpoint_t src, int led_status, final String msg) {
        a.assert_worker_thread(); //--strip
        log("ZZH6 on_status. XXII. " + us.cash.scr.leds_t.color_name(led_status) + " " + msg); //--strip
        final device_endpoints__conf self = this;
        runOnUiThread(new Runnable() {
            @Override public void run() {
                log("HMI status: " + msg); //--strip
                if (self.a.device_endpoints.cur_index.value == -1) {
                    self.w.list_view.set_conn_ico(src, led_status, msg);
                }
                else {
                    self.w.list_view.set_conn_ico(self.a.device_endpoints.cur_index.value, led_status, msg);
                }
            }
        });
    }

    @Override public void confirmed_subhome(final device_endpoint_t src, final String subhome) {
        a.assert_worker_thread(); //--strip
        log("confirmed_subhome " + subhome); //--strip
        update_poweredon_item__worker();
    }

    @Override public void on_send(boolean busy) {
        a.assert_worker_thread(); //--strip
    }

    @Override public void on_recv(boolean busy) {
        a.assert_worker_thread(); //--strip
    }

    protected us.cash.scr.device_endpoints__conf__widgets w = null;
    int resume__poweron = -1;

}

