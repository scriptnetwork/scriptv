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
import us.cash.scr.list_view__adapter_t;
import us.cash.scr.##classname##__itemview__widgets;

public final class ##classname##__adapter_t extends list_view__adapter_t {

    private static void log(final String s) {                         //--strip
        CFG.log_scr("##classname##__adapter_t: " + s);                //--strip
    }                                                                 //--strip

    public static interface itemclick_listener_t extends list_view__adapter_t.itemclick_listener_t {
        //void on_whatever(int position);
    }

    public ##classname##__adapter_t(##datatype## data, itemclick_listener_t listener) {
        super(listener);
        this.data = data;
        this.listener = listener;
    }

    @Override public us.cash.scr.list_view__itemview_t create_item_view(Context ctx) {
        ##classname##__itemview__widgets w = new ##classname##__itemview__widgets();
        return new ##classname##__itemview_t(ctx, w, this);
    }

    @Override public int get_item_count() {
        return data.size();
    }

    public ##itemtype## get_item(int pos) {
        return data.get(pos);
    }

    ##datatype## data;
    public itemclick_listener_t listener;
}

