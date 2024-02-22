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
//MIM        file: core0/core0_6/core0_61/us/gui/activity/scr/list_view/view/list/java/scr/[classname]__adapter_t.java
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/scr/list_view/view/list/mim.h
//MIM  Params:
//MIM    'classname': 'device_endpoints__conf__list_view'
//MIM    'datatype': 'device_endpoints_t' @ core0/core0_7/us/gui/device_endpoints/mim.h
//MIM    'include': ''
//MIM    'item_nft_code': '' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM    'itemtype': 'device_endpoint_t' @ core0/core0_7/us/gui/device_endpoints/mim.h
//MIM  kickoff code hash: 4JWtDeMaFCuoMs1zzZzPA4RgYctG (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash.scr;
import android.content.Context;                                                                // Context
import java.util.ArrayList;
import us.cash.CFG;
import us.cash.R;
import us.cash.device_endpoints_t;
import us.cash.device_endpoint_t;

public final class device_endpoints__conf__list_view__adapter_t extends list_view__adapter_t {

    private static void log(final String s) {                         //--strip
        CFG.log_scr("device_endpoints__conf__list_view__adapter_t: " + s);                //--strip
    }                                                                 //--strip

    public device_endpoints__conf__list_view__adapter_t(device_endpoints__conf__list_view main_view_) {
        super();
        main_view = main_view_;
    }

    @Override final public us.cash.scr.list_view__itemview_t create_itemview(Context ctx) {
        return main_view.create_itemview(ctx);
    }

    @Override final public int get_item_count() {
        if (data == null) return 0;
        //log("get_item_count " + data.size()); //--strip
        return data.size();
    }

    public final device_endpoint_t get_item(int pos) {
        assert data != null; //--strip
        return data.get(pos);
    }

    public void set_data(device_endpoints_t data_) {
        log("set_data sz=" + (data_ != null ? data_.size() : 0)); //--strip
        //assert data_ != null;
        highlight_pos = -1;
        data = data_;
        notifyDataSetChanged();
    }

    public void set_conn_ico(device_endpoint_t item, final int led_state, final String msg) { //0 red 1 amber 2 green -1 unchanged
        log("set_conn_ico. dep"); //--strip
        int n = 0;
        for (device_endpoint_t i: data) {
            if (i == item) {
                set_conn_ico(n, led_state, msg);
                break;
            }
            ++n;
        }
    }

    public void set_conn_ico(int pos, final int led_state, final String msg) { //0 red 1 amber 2 green -1 unchanged
        log("set_conn_ico. pos " + pos + " led state " + led_state + " " + us.cash.scr.leds_t.color_name(led_state)); //--strip
        assert pos >= 0; //--strip
        //log("set_status_led"); //--strip
        status_index = pos;
        status_msg = msg;
        if (led_state == us.cash.scr.leds_t.led_off) {
            status_ico = R.raw.conn_ico_off;
        }
        else if (led_state == us.cash.scr.leds_t.led_red) {
            status_ico = R.raw.conn_ico_red;
        }
        else if (led_state == us.cash.scr.leds_t.led_amber) {
            status_ico = R.raw.conn_ico_amber;
        }
        else if (led_state == us.cash.scr.leds_t.led_green) {
            status_ico = R.raw.conn_ico_green;
        }
        else if (led_state == us.cash.scr.leds_t.led_blue) {
            status_ico = R.raw.conn_ico_blue;
        }
        else {
            status_ico = R.raw.conn_ico_off;
        }
        notifyItemChanged(pos);
    }

    device_endpoints__conf__list_view main_view;
    device_endpoints_t data = null;
    public int status_index = -1;
    public int status_ico = -1;
    public String status_msg = "";
}

