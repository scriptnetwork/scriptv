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
import static us.gov.socket.types.*;                                                           // *
import static us.stdint.*;                                                                     // *
import us.ko;                                                                                  // ko
import static us.ko.ok;                                                                        // ok
import us.vcs;                                                                                 // vcs
import android.util.Log;

public final class CFG {
    public static final String namespace = "##str28##";
    public static final int mode = ##str43##;
    public static final String blob_id = "##str56##"; //Branded android app blob_id
    public static final String debug_sk = "##str47##";
    public static final String acra_connection_string = "##str48##";
    public static final String app_name = "##str32##";
    public static final String brand = vcs.brand;
    public static final int appstore_edition = ##str83##;
    public static final String app_bz_desc = "##str91##";
    public static final int foss = ##str92##;
    public static final String origin = "##str93##";
    public static final int default_view_bookmarks = ##str94##;
    public static final String default_wallet_connections = "##str95##";
    public static final String custodial_wallet_host = "##str96##";

    public static boolean sdk_logs = false;                                             //--strip

    public static class logs {                                                          //--strip
        static boolean b = true;                                                        //--strip
        public static boolean android = b;                                              //--strip
        public static boolean scr = b;                                                  //--strip

        public static void set(boolean b) {                                             //--strip
            android = scr = b;                                                          //--strip
        }                                                                               //--strip
    }                                                                                   //--strip

    public static void log_android(final String line) {                                 //--strip
        if (logs.android)                                                               //--strip
        Log.e(namespace, line);                                         //--strip
    }                                                                                   //--strip

    public static void log_scr(final String line) {                                     //--strip
        if (logs.scr)                                                                   //--strip
        Log.e(namespace + ".scr", line);                                         //--strip
//        System.out.println(namespace + ".scr: " + line);                                     //--strip
    }                                                                                   //--strip

}

