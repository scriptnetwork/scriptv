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
//MIM        file: core0/core0_6/core0_61/us/gui/activity/listview_controller/list/java/[classname]__adapter_t.java
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/listview_controller/list/mim.h
//MIM  Params:
//MIM    'classname': 'trades__conf__active'
//MIM    'datatype': 'trades__conf__active0__datatype_t' @ core0/core0_8/core0_86/us/gui/trades/conf/active/mim.h
//MIM    'include': 'import us.gov.crypto...'
//MIM    'item_nft_code': 'public String get_nf...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM    'itemtype': 'trades__conf__active0__itemtype_t' @ core0/core0_8/core0_86/us/gui/trades/conf/active/mim.h
//MIM  kickoff code hash: 4PwsTE3cPBuZvvgfy5z1MM9foWyV (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.content.Context;                                                                // Context
import java.util.ArrayList;
import static us.gov.crypto.ripemd160.hash_t;
//MIM begin token 'include'
import us.gov.crypto.ripemd160.hash_t;
import us.wallet.trader.trader_brief_t;
import us.wallet.trader.trades_index_t;
//MIM end token 'include'

public final class trades__conf__active__adapter_t extends list_view__adapter_t {

    private static void log(final String s) {                         //--strip
        CFG.log_scr("trades__conf__active__adapter_t: " + s);                //--strip
    }                                                                 //--strip

    public trades__conf__active__adapter_t(trades__conf__active main_view_) {
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

    public final trades__conf__active0__itemtype_t get_item(int pos) {
        assert data != null; //--strip
        return data.get(pos);
    }

    public void set_data(trades__conf__active0__datatype_t data_) {
        log("set_data sz=" + (data_ != null ? data_.size() : 0)); //--strip
        //assert data_ != null;
        highlight_pos = -1;
        data = data_;
        notifyDataSetChanged();
    }

    //MIM begin token 'item_nft_code'
    public String get_nft(final trades__conf__active0__itemtype_t i) {
        return i.nft();
    }

    public int find_nft__pos(final String nft) {
        if (data == null) return -1;
        if (nft == null) return -1;
        int p = -1;
        for (trades__conf__active0__itemtype_t i: data) {
            ++p;
            String inft = get_nft(i);
            if (inft == null) continue;
            if (inft.equals(nft)) {
                break;
            }
        }
        return p;
    }

    public trades__conf__active0__itemtype_t find_nft__item(final String nft) {
        if (data == null) return null;
        if (nft == null) return null;
        for (trades__conf__active0__itemtype_t i: data) {
            String inft = get_nft(i);
            if (inft == null) continue;
            if (inft.equals(nft)) {
                return i;
            }
        }
        return null;
    }

    public void highlight_nft(final String nft, boolean with_flash) {
        int pos = find_nft__pos(nft);
        if (pos == -1) return;
        highlight(pos, true);
        if (with_flash) flash(pos);
    }
    //MIM end token 'item_nft_code'

    trades__conf__active main_view;
    trades__conf__active0__datatype_t data = null;
}

