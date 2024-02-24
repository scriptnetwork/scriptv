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

public final class account_breakdown__adapter_t extends account_breakdown0__adapter_t {

    private static void log(final String s) {                         //--strip
        CFG.log_scr("account_breakdown__adapter_t: " + s);                //--strip
    }                                                                 //--strip

    public account_breakdown__adapter_t() {
        super();
    }

    @Override public us.cash.scr.list_view__itemview_t create_item_view(Context ctx) {
        w = new us.cash.scr.account_breakdown__itemview__widgets();
        return new us.cash.scr.account_breakdown__itemview_t(ctx, w, this);
    }

    public us.cash.scr.account_breakdown__list_view_t get_list_view() {
        return (us.cash.scr.account_breakdown__list_view_t) list_view;
    }

    public us.cash.scr.account_breakdown__list_view_t.itemclick_listener_t get_listener() {
        return (us.cash.scr.account_breakdown__list_view_t.itemclick_listener_t) list_view.listener;
    }

    us.cash.scr.account_breakdown__itemview__widgets w = null;


}

