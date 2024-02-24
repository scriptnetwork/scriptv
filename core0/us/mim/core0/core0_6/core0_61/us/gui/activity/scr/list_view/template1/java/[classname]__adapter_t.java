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
import android.content.Context;                                                                // Context
import java.util.ArrayList;
##include##

public final class ##classname##__adapter_t extends list_view__adapter_t {

    private static void log(final String s) {                         //--strip
        CFG.log_scr("##classname##__adapter_t: " + s);                //--strip
    }                                                                 //--strip

    public ##classname##__adapter_t() {
        super();
    }

    @Override final public us.cash.scr.list_view__itemview_t create_itemview(Context ctx) {
        w = new us.cash.scr.##classname##__itemview__widgets();
        return new us.cash.scr.##classname##__itemview_t(ctx, w, this);
    }

    @Override final public int get_item_count() {
        if (data == null) return 0;
        //log("get_item_count " + data.size()); //--strip
        return data.size();
    }

    public final ##itemtype## get_item(int pos) {
        assert data != null; //--strip
        return data.get(pos);
    }

    public void set_data(##datatype## data_) {
        log("set_data sz=" + (data_ != null ? data_.size() : 0)); //--strip
        //assert data_ != null;
        highlight_pos = -1;
        data = data_;
        notifyDataSetChanged();
    }

    us.cash.scr.##classname##__itemview__widgets w = null;
    ##datatype## data = null;
}
