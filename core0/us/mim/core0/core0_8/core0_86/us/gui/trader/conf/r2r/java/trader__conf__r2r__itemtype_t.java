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
import us.wallet.trader.protocol_selection_t;

public class trader__conf__r2r__itemtype_t {
    
    public trader__conf__r2r__itemtype_t(final protocol_selection_t protocol_selection_, boolean running_) {
        protocol_selection = protocol_selection_;
        running = running_;
        app.r2r_lib_metainfo_t metainfo = app.a.r2r_libs.get_metainfo(protocol_selection);
        if (metainfo == null) {
            head = protocol_selection.to_string();
            tail = "";
        }
        else {
            head = metainfo.headline + " (" + protocol_selection.to_string() + ")";
            tail = metainfo.description;
        }
    }
    
    public String nft() {
        return protocol_selection.to_string();
    }

    public boolean running;
    public String head;
    public String tail;
    public protocol_selection_t protocol_selection;
}


