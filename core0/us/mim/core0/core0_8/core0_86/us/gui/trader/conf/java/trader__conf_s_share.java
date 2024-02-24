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
//MIM    mim file: core0/core0_8/core0_86/us/gui/trader/conf/mim.h
//MIM  kickoff code hash: 3qBovaFXdei73Q8jnRQ7PYFqYePK (change this hash to force a review)
//MIM  ******************************************************************************
package us.cash;
import us.string;
import static us.gov.crypto.ripemd160.hash_t;
import static us.stdint.*;
import static us.gov.io.types.blob_t;
import us.gov.io.blob_reader_t;
import android.os.Bundle;                                                                      // Bundle
import static us.ko.*;
import us.ko;

public abstract class trader__conf_s_share extends trader__conf_s_personality {

    private static void log(final String line) {                              //--strip
        CFG.log_android("trader__conf_s_share: " + line);          //--strip
    }                                                                         //--strip

    public trader__conf_s_share() {
        super();
    }

    @Override protected void on_data() {
        log("on_data"); //--strip
        super.on_data();
    }

    @Override protected void on_data__children() {
        super.on_data__children();
    }

    @Override public void on_push(final hash_t target_tid, final uint16_t code, final byte[] payload) {
        log("on_push"); //--strip
        switch(code.value) {
            default: {
                super.on_push(target_tid, code, payload);
            }
            break;
        }
    }
}
