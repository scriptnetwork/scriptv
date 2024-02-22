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
import android.graphics.BitmapFactory;                                                         // BitmapFactory
import android.graphics.Bitmap;                                                                // Bitmap
##include##

public class ##classname##__itemtype_t {
    
    public ##classname##__itemtype_t(final String id, final ##api__itemtype## o_) {
        o = o_;
        head = "head";
        tail = "tail";
    }

    public Bitmap icon() {
        return null;
/*
        //Example code for building icon from data found in ##api__itemtype##
        if (ico != null) {
            return ico;
        }
        //if (o.ico.length == 0) return null;
        Bitmap bmp = BitmapFactory.decodeByteArray(o.ico, 0, o.ico.length);
        ico = Bitmap.createScaledBitmap(bmp, 28, 28, false);
        return ico;
*/
    }

##itemtype__nft_support_code##

    public String head;
    public String tail;
    Bitmap ico = null;
    ##api__itemtype## o;

}
