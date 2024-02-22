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
//MIM    'api__itemtype': 'hash_t' @ core0/core0_8/core0_84/us/gui/iot/conf/sinks/mim.h
//MIM    'classname': 'iot__conf__sinks0'
//MIM    'include': 'import static us.gov...'
//MIM    'itemtype__nft_support_code': 'public String nft() ...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM  kickoff code hash: 2etuhoVBNDrzgi9x9M7qVTQ3jHph (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.graphics.BitmapFactory;                                                         // BitmapFactory
import android.graphics.Bitmap;                                                                // Bitmap
//MIM begin token 'include'
import static us.gov.crypto.ripemd160.hash_t;
import us.wallet.wallet.timeseries_index_t;
//MIM end token 'include'

public class iot__conf__sinks0__itemtype_t {
    
    public iot__conf__sinks0__itemtype_t(final String head_, final String id) {
        head = head_;
        tail = id;
    }

    public Bitmap icon() {
        return null;
/*
        //Example code for building icon from data found in hash_t
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
        return tail;
    }
    //MIM end token 'itemtype__nft_support_code'

    public String head;
    public String tail;
    Bitmap ico = null;

}
