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
//MIM    'classname': 'certs__conf__all'
//MIM    'datatype': 'certs__conf__all0__datatype_t' @ core0/core0_8/core0_81/us/gui/certs/conf/all/mim.h
//MIM    'include': 'import java.util.Arr...'
//MIM    'item_nft_code': 'public String get_nf...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM    'itemtype': 'certs__conf__all0__itemtype_t' @ core0/core0_8/core0_81/us/gui/certs/conf/all/mim.h
//MIM  kickoff code hash: 2W9Zzonamf8Ub3DTy9S9diuGofTx (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.content.Context;                                                                // Context
import java.util.ArrayList;
import static us.gov.crypto.ripemd160.hash_t;
//MIM begin token 'include'
import java.util.ArrayList;
import static us.gov.crypto.ripemd160.hash_t;
import us.pair;
import us.string;
//MIM end token 'include'

public final class certs__conf__all__adapter_t extends list_view__adapter_t {

    private static void log(final String s) {                         //--strip
        CFG.log_scr("certs__conf__all__adapter_t: " + s);                //--strip
    }                                                                 //--strip

    public certs__conf__all__adapter_t(certs__conf__all main_view_) {
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

    public final certs__conf__all0__itemtype_t get_item(int pos) {
        assert data != null; //--strip
        return data.get(pos);
    }

    public void set_data(certs__conf__all0__datatype_t data_) {
        log("set_data sz=" + (data_ != null ? data_.size() : 0)); //--strip
        //assert data_ != null;
        highlight_pos = -1;
        data = data_;
        notifyDataSetChanged();
    }

    //MIM begin token 'item_nft_code'
    public String get_nft(final certs__conf__all0__itemtype_t i) {
        return i.nft();
    }

    public int find_nft__pos(final String nft) {
        if (data == null) return -1;
        if (nft == null) return -1;
        int p = -1;
        for (certs__conf__all0__itemtype_t i: data) {
            ++p;
            String inft = get_nft(i);
            if (inft == null) continue;
            if (inft.equals(nft)) {
                break;
            }
        }
        return p;
    }

    public certs__conf__all0__itemtype_t find_nft__item(final String nft) {
        if (data == null) return null;
        if (nft == null) return null;
        for (certs__conf__all0__itemtype_t i: data) {
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

    certs__conf__all main_view;
    certs__conf__all0__datatype_t data = null;
}

