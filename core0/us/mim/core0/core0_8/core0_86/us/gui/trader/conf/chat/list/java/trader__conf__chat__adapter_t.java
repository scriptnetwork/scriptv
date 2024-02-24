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
//MIM    'classname': 'trader__conf__chat'
//MIM    'datatype': 'trader__conf__chat0__datatype_t' @ core0/core0_8/core0_86/us/gui/trader/conf/chat/mim.h
//MIM    'include': 'import static us.wal...'
//MIM    'item_nft_code': '' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM    'itemtype': 'trader__conf__chat0__itemtype_t' @ core0/core0_8/core0_86/us/gui/trader/conf/chat/mim.h
//MIM  kickoff code hash: 3vERZ57FSQH2YPjtgK7hyzb6XZpb (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.content.Context;                                                                // Context
import java.util.ArrayList;
import static us.gov.crypto.ripemd160.hash_t;
//MIM begin token 'include'
import static us.wallet.trader.chat.chat_entry;
import static us.wallet.trader.chat.chat_t;
//MIM end token 'include'

public final class trader__conf__chat__adapter_t extends list_view__adapter_t {

    private static void log(final String s) {                         //--strip
        CFG.log_scr("trader__conf__chat__adapter_t: " + s);                //--strip
    }                                                                 //--strip

    public trader__conf__chat__adapter_t(trader__conf__chat main_view_) {
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

    public final trader__conf__chat0__itemtype_t get_item(int pos) {
        assert data != null; //--strip
        return data.get(pos);
    }

    public void set_data(trader__conf__chat0__datatype_t data_) {
        log("set_data sz=" + (data_ != null ? data_.size() : 0)); //--strip
        //assert data_ != null;
        highlight_pos = -1;
        data = data_;
        notifyDataSetChanged();
    }


    trader__conf__chat main_view;
    trader__conf__chat0__datatype_t data = null;
}

