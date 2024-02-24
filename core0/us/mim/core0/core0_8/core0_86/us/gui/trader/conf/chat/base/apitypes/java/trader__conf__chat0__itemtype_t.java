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
//MIM        file: core0/core0_6/core0_61/us/gui/activity/listview_controller/base/apitypes/java/[classname]__itemtype_t.java
//MIM    mim file: core0/core0_6/core0_61/us/gui/activity/listview_controller/base/apitypes/mim.h
//MIM  Params:
//MIM    'api__itemtype': 'chat_entry' @ core0/core0_8/core0_86/us/gui/trader/conf/chat/mim.h
//MIM    'classname': 'trader__conf__chat0'
//MIM    'include': 'import static us.wal...'
//MIM    'itemtype__nft_support_code': '' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM  kickoff code hash: xKDeauJjnGQ6PCx8hJPike1tFzr (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.graphics.BitmapFactory;                                                         // BitmapFactory
import android.graphics.Bitmap;                                                                // Bitmap
//MIM begin token 'include'
import static us.wallet.trader.chat.chat_entry;
import static us.wallet.trader.chat.chat_t;
//MIM end token 'include'
import static us.gov.socket.types.ts_t;
import java.text.SimpleDateFormat;
import java.util.Date;

public class trader__conf__chat0__itemtype_t {
    
    public trader__conf__chat0__itemtype_t(final ts_t ts_, final chat_entry o_) {
        o = o_;
        ts = ts_;
        head = o.to_string();
        tail = sdf.format(new Date(ts_.value / 1000000));
    }

    public Bitmap icon() {
        return null;
/*
        //Example code for building icon from data found in chat_entry
        if (ico != null) {
            return ico;
        }
        //if (o.ico.length == 0) return null;
        Bitmap bmp = BitmapFactory.decodeByteArray(o.ico, 0, o.ico.length);
        ico = Bitmap.createScaledBitmap(bmp, 28, 28, false);
        return ico;
*/
    }

    ts_t ts;
    public String head;
    public String tail;
    Bitmap ico = null;
    public chat_entry o;
    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

}
