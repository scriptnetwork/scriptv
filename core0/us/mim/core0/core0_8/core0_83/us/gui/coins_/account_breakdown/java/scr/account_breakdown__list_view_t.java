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
package us.cash.scr;
import android.content.Context;

public class account_breakdown__list_view_t extends account_breakdown0__list_view_t {

    public static interface itemclick_listener_t extends account_breakdown0__list_view_t.itemclick_listener_t {
        //void on_poweron(int position, boolean ison);
        //void on_conf(int position);
        //void on_menu(int position);
        //void on_trash(int position);
    }

    public account_breakdown__list_view_t(Context ctx, final itemclick_listener_t listener_) {
        super(ctx, listener_);
	listener = listener_;
    }

    itemclick_listener_t listener = null;

}

