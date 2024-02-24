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
//MIM    'api__itemtype': 'data_sources_index__item_t' @ core0/core0_8/core0_84/us/gui/iot/conf/sources/mim.h
//MIM    'classname': 'iot__conf__sources0'
//MIM    'include': 'import us.wallet.eng...'
//MIM    'itemtype__nft_support_code': 'public String nft() ...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM  kickoff code hash: RaXd2VMVHG8wZdYj1Eds7JCa8gA (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.graphics.BitmapFactory;                                                         // BitmapFactory
import android.graphics.Bitmap;                                                                // Bitmap
//MIM begin token 'include'
import us.wallet.engine.data_sources_index__item_t;
import us.wallet.engine.data_sources_index_t;
//MIM end token 'include'

public class iot__conf__sources0__itemtype_t {
    
    public iot__conf__sources0__itemtype_t(final data_sources_index__item_t o_) {
        o = o_;
        head = o.name.value;
        if (o.address.is_zero()) {
            tail = "Disconnected";
            icores = us.cash.app.a.le.resolve_resid(R.raw.streams);
            connected = false;
        }
        else {
            tail = "Connected to address: " + o.address.encode();
            icores = us.cash.app.a.le.resolve_resid(R.raw.streams_green);
            connected = true;
        }

    }

//    public Bitmap icon() {
//        return null;
/*
        //Example code for building icon from data found in data_sources_index__item_t
        if (ico != null) {
            return ico;
        }
        //if (o.ico.length == 0) return null;
        Bitmap bmp = BitmapFactory.decodeByteArray(o.ico, 0, o.ico.length);
        ico = Bitmap.createScaledBitmap(bmp, 28, 28, false);
        return ico;
*/
//    }

    //MIM begin token 'itemtype__nft_support_code'
    public String nft() {
        return null;
    }
    //MIM end token 'itemtype__nft_support_code'

    public boolean connected;
    public String head;
    public String tail;
    public int icores;
    public data_sources_index__item_t o;
//    Bitmap ico = null;
//    data_sources_index__item_t o;

}


