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
import android.content.Context;                                                                // Context
import android.view.View;                                                                      // View
import androidx.recyclerview.widget.RecyclerView;
import us.cash.R;
import us.cash.CFG;
import us.gov.cash.account_t;
import us.gov.cash.accounts_t;


public final class account_breakdown__itemview_t extends account_breakdown0__itemview_t {  //view_holder

    private static void log(final String s) {                    //--strip
        CFG.log_scr("account_breakdown__itemview_t: " + s);          //--strip
    }                                                            //--strip

    public account_breakdown__itemview_t(Context ctx, account_breakdown__itemview__widgets w_, final us.cash.account_breakdown__adapter_t adapter_) {
        super(ctx, w_, adapter_);
        w = w_;
        adapter = adapter_;
    }

    @Override public void bind(int pos) {
        super.bind(pos);
        log("bind pos=" + pos); //--strip
        /*
        final us.cash.account_t itm = adapter.get_item(pos);
        w.status.setText(itm.get_title());
        */
    }

    account_breakdown__itemview__widgets w = null;
    us.cash.account_breakdown__adapter_t adapter = null;
}

