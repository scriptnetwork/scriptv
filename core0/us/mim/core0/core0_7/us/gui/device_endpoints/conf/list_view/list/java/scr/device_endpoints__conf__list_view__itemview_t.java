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
//MIM        file: core0/core0_6/core0_61/us/gui/activity/scr/list_view/view/list/java/scr/[classname]__itemview_t.java
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/scr/list_view/view/list/mim.h
//MIM  Params:
//MIM    'classname': 'device_endpoints__conf__list_view'
//MIM    'include': ''
//MIM    'itemico': 'R.raw.gear' @ core0/core0_7/us/gui/device_endpoints/mim.h
//MIM    'itemtype': 'device_endpoint_t' @ core0/core0_7/us/gui/device_endpoints/mim.h
//MIM    'itemview_bind_example_code': 'w.ico.setImageResour...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM  kickoff code hash: Cb6XTaW3GjZ53QVkUosnCJYAh26 (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
import android.content.Context;                                                                // Context
import android.view.View;                                                                      // View
import androidx.recyclerview.widget.RecyclerView;
import us.cash.R;
import us.cash.CFG;
import android.graphics.Bitmap;                                                                // Bitmap
import us.cash.device_endpoint_t;
import us.pair;
import us.string;
import android.graphics.Color;                                                                 // Color
import static us.gov.crypto.ripemd160.hash_t;

public final class device_endpoints__conf__list_view__itemview_t extends list_view__itemview_t {

    private static void log(final String s) {                    //--strip
        CFG.log_scr("device_endpoints__conf__list_view__itemview_t: " + s);          //--strip
    }                                                            //--strip

    public device_endpoints__conf__list_view__itemview_t(Context ctx, device_endpoints__conf__list_view__itemview__widgets w, final device_endpoints__conf__list_view__adapter_t adapter) {
        super(ctx, w, adapter);
        this.w = w;
        this.adapter = adapter;
    }

    @Override public void bind(int pos) {
        super.bind(pos);
        log("bind pos=" + pos); //--strip
        final us.cash.device_endpoint_t item = adapter.get_item(pos);
        int ico = -1;
        String msg = null;
        if (pos == adapter.status_index) {
            ico = adapter.status_ico;
            msg = adapter.status_msg;
        }
        //boolean enabled_listener = enable_listener;
        w.enable_listener = false;
        String caption = item.get_title();
        String subhome = item.get_subhome();
        String addr_ = item.get_address();
        String ssid_ = item.get_ssid();
        log("hmi==null. XXII. " + (item.hmi == null ? "NULL" : "NOT NULL")); //--strip
        if (item.hmi == null) {
            w.poweron.setChecked(false);
        }
        else {
            w.poweron.setChecked(true);
        }
        String hdr = "status: ";
        if (ico != -1) {
            w.conn_ico.setImageResource(us.cash.app.a.le.resolve_resid(ico));
            w.status.setText(hdr + msg);
            //status.setVisibility(View.VISIBLE);
        }
        else {
            w.conn_ico.setImageResource(us.cash.app.a.le.resolve_resid(R.raw.conn_ico_off));
            w.status.setText(hdr + " Off.");
            //status.setVisibility(View.GONE);
        }
        w.label.setText(caption);
        if (item.has_addr()) {
            if (subhome.isEmpty()) {
                w.address.setText("Privacy wallet: " + addr_);
                w.address.setTextColor(darkgreen);
            }
            else {
                w.address.setText("Custodial wallet: " + addr_ + "." + subhome);
                w.address.setTextColor(orange);
            }
           w.address.setVisibility(View.VISIBLE);
        }
        else {
            w.address.setVisibility(View.GONE);
        }
        if (!ssid_.isEmpty()) {
            w.ssid.setText("tied to wifi SSID: " + ssid_);
            w.ssid.setVisibility(View.VISIBLE);
        }
        else {
            w.ssid.setVisibility(View.GONE);
        }
        if (item.is_powered_on()) {
            w.trash.setVisibility(View.INVISIBLE);
        }
        else {
            w.trash.setVisibility(View.VISIBLE);
        }
        w.enable_listener = true;
    }

    static int darkgreen = Color.parseColor("#009900");
    static int orange = Color.parseColor("#ffa500");

    device_endpoints__conf__list_view__itemview__widgets w = null;
    device_endpoints__conf__list_view__adapter_t adapter = null;
}

