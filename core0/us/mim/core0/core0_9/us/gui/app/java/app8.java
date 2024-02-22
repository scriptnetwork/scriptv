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
//MIM    mim file: core0/core0_9/us/gui/app/mim.h
//MIM  kickoff code hash: HNPWwTc2fznRDUey7NFwerhKsPP (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import static us.gov.crypto.ripemd160.hash_t;                                                  // hash_t
import android.content.Intent;                                                                 // Intent
import android.os.Handler;
import us.wallet.trader.qr_t;
import us.pair;
import static us.ko.*;
import static us.stdint.*;
import us.ko;
import us.wallet.trader.protocol_selection_t;

public class app8 extends app7 {

    private static void log(final String line) {                              //--strip
        CFG.log_android("app8: " + line);          //--strip
    }                                                                         //--strip

    public app8() {
        super();
    }

    @Override public void onCreate() {
        super.onCreate();
        r2r_libs.register_library(lib_trader__w2w_w_t.protocol_selection, new lib_trader__w2w_w_t());
    }


}
