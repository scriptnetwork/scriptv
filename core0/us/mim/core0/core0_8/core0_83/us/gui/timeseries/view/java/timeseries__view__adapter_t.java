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
//MIM        file: core0/core0_6/core0_61/us/gui/activity/scr/list_view/template1/java/[classname]__adapter_t.java
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/scr/list_view/template1/mim.h
//MIM  Params:
//MIM    'classname': 'timeseries__view'
//MIM    'datatype': 'timeseries_entry_index_t' @ core0/core0_8/core0_83/us/gui/timeseries/view/mim.h
//MIM    'include': 'import us.stdint.uin...'
//MIM    'itemtype': 'uint64_t' @ core0/core0_8/core0_83/us/gui/timeseries/view/mim.h
//MIM  kickoff code hash: j1PKUJ9cWrtyWvuMdUsxLu584Ny (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.content.Context;                                                                // Context
import java.util.ArrayList;
//MIM begin token 'include'
import us.stdint.uint64_t;
import us.wallet.wallet.timeseries_entry_index_t;
//MIM end token 'include'

public final class timeseries__view__adapter_t extends list_view__adapter_t {

    private static void log(final String s) {                         //--strip
        CFG.log_scr("timeseries__view__adapter_t: " + s);                //--strip
    }                                                                 //--strip

    public timeseries__view__adapter_t() {
        super();
    }

    @Override final public us.cash.scr.list_view__itemview_t create_itemview(Context ctx) {
        w = new us.cash.scr.timeseries__view__itemview__widgets();
        return new us.cash.scr.timeseries__view__itemview_t(ctx, w, this);
    }

    @Override final public int get_item_count() {
        if (data == null) return 0;
        //log("get_item_count " + data.size()); //--strip
        return data.size();
    }

    public final uint64_t get_item(int pos) {
        assert data != null; //--strip
        return data.get(pos);
    }

    public void set_data(timeseries_entry_index_t data_) {
        log("set_data sz=" + (data_ != null ? data_.size() : 0)); //--strip
        //assert data_ != null;
        highlight_pos = -1;
        data = data_;
        notifyDataSetChanged();
    }

    us.cash.scr.timeseries__view__itemview__widgets w = null;
    timeseries_entry_index_t data = null;
}
