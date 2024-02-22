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
//MIM    'classname': 'coins__view'
//MIM    'datatype': 'tokens_t' @ core0/core0_8/core0_83/us/gui/coins/view/mim.h
//MIM    'include': ''
//MIM    'itemtype': 'token_t' @ core0/core0_8/core0_83/us/gui/coins/view/mim.h
//MIM  kickoff code hash: 4JV45s8YT51k41r579o6uUp1Dhd4 (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.content.Context;                                                                // Context
import java.util.ArrayList;

public final class coins__view__adapter_t extends list_view__adapter_t {

    private static void log(final String s) {                         //--strip
        CFG.log_scr("coins__view__adapter_t: " + s);                //--strip
    }                                                                 //--strip

    public coins__view__adapter_t() {
        super();
    }

    @Override final public us.cash.scr.list_view__itemview_t create_itemview(Context ctx) {
        w = new us.cash.scr.coins__view__itemview__widgets();
        return new us.cash.scr.coins__view__itemview_t(ctx, w, this);
    }

    @Override final public int get_item_count() {
        if (data == null) return 0;
        //log("get_item_count " + data.size()); //--strip
        return data.size();
    }

    public final token_t get_item(int pos) {
        assert data != null; //--strip
        return data.get(pos);
    }

    public void set_data(tokens_t data_) {
        log("set_data sz=" + (data_ != null ? data_.size() : 0)); //--strip
        //assert data_ != null;
        highlight_pos = -1;
        data = data_;
        notifyDataSetChanged();
    }

    us.cash.scr.coins__view__itemview__widgets w = null;
    tokens_t data = null;
}
