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
//MIM    'api__itemtype': 'String' @ core0/core0_8/core0_81/us/gui/certs/conf/all/mim.h
//MIM    'classname': 'certs__conf__all0'
//MIM    'include': 'import java.util.Arr...'
//MIM    'itemtype__nft_support_code': 'public String nft() ...' @ core0/core0_6/core0_61/us/gui/activity/listview_controller/mim.h
//MIM  kickoff code hash: H7H6cxba6ZMHVxY1WBusdfe5qYM (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import android.graphics.BitmapFactory;                                                         // BitmapFactory
import android.graphics.Bitmap;                                                                // Bitmap
//MIM begin token 'include'
import java.util.ArrayList;
import static us.gov.crypto.ripemd160.hash_t;
import us.pair;
import us.string;
//MIM end token 'include'

public class certs__conf__all0__itemtype_t {
    
    public certs__conf__all0__itemtype_t(final String id_, final String o_) {
        id = id_;
        head = o_;
    }

    public Bitmap icon() {
        return null;
    }

    //MIM begin token 'itemtype__nft_support_code'
    public String nft() {
        return id;
    }
    //MIM end token 'itemtype__nft_support_code'

    public String id;
    public String head;
    Bitmap ico = null;

}
