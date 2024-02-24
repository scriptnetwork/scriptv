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
//MIM    'api__itemtype': 'trader_brief_t' @ core0/core0_8/core0_86/us/gui/trades/conf/active/mim.h
//MIM    'classname': 'trades__conf__active0'
//MIM    'include': 'import us.gov.crypto...'
//MIM    'itemtype__nft_support_code': 'public String nft() ...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM  kickoff code hash: j3iuQQpSJxKM5oQQcW1yLXCmwxV (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.graphics.BitmapFactory;                                                         // BitmapFactory
import android.graphics.Bitmap;                                                                // Bitmap
//MIM begin token 'include'
import us.gov.crypto.ripemd160.hash_t;
import us.wallet.trader.trader_brief_t;
import us.wallet.trader.trades_index_t;
//MIM end token 'include'
import java.text.SimpleDateFormat;
import java.util.Date;

public class trades__conf__active0__itemtype_t {
    
    public trades__conf__active0__itemtype_t(final trader_brief_t o_) {
        o = o_;
        head = o.tid.encode();      
        ts_creation = sdf.format(new Date(o.ts_creation.value / 1000000));
        ts_updated = sdf.format(new Date(o.ts_activity.value / 1000000));
        qr = o.qr.to_string();
    }

    public Bitmap icon() {
        return null;
/*
        //Example code for building icon from data found in trader_brief_t
        if (ico != null) {
            return ico;
        }
        //if (o.ico.length == 0) return null;
        Bitmap bmp = BitmapFactory.decodeByteArray(o.ico, 0, o.ico.length);
        ico = Bitmap.createScaledBitmap(bmp, 28, 28, false);
        return ico;
*/
    }

    //MIM begin token 'itemtype__nft_support_code'
    public String nft() {
        return head;
    }
    //MIM end token 'itemtype__nft_support_code'

    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public String head;
    public String ts_creation;
    public String ts_updated;
    public String qr;
    Bitmap ico = null;
    trader_brief_t o;

}
