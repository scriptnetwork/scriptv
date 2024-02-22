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
import us.wallet.trader.protocol_selection_t;
import java.util.TreeMap;

public static class r2r_libs_t extends TreeMap<protocol_selection_t, r2r_lib_t> {

    public r2r_libs_t() {
    }

    public void register_library(final protocol_selection_t protocol_selection, final r2r_lib_t r2r_lib) {
        put(protocol_selection, r2r_lib);
    }

    public trader__conf__role get_trader__conf__role(protocol_selection_t protocol_selection) {
        r2r_lib_t lib = get(protocol_selection);
        if (lib == null) {
            return null;
        }
        return lib.get_trader__conf__role();            
    }
    //boolean need_download = true;
    //if (need_download) {
    //    return new trader__conf__role__download();
    //}
    //return null;

    r2r_lib_metainfo_t get_metainfo(final protocol_selection_t protocol_selection) {
        r2r_lib_t lib = get(protocol_selection);
        if (lib == null) {
            return null;
        }
        return lib.get_metainfo();            
    }

}
    
