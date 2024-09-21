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
package us;
import java.io.PrintStream;                                                                    // PrintStream

public class vcs {

//#include <us/vcs_git_java>
//------------------------------------------------------------__begin__------generated by configure, do not edit.
//content of file: <us/vcs_git_java>
public static final String src = "https://github.com/user/project";
public static final String devjob = "";
public static final String devjobtag = "";
public static final String brand = "us";
public static final String branch = "alpha-57";
public static final String codehash = "1b46ef32cb0aa22dd34070b9543613233b600f04";
public static final String cfghash = "18444094a5521f4e381d7b6e73c6bfc2b39eb867b5919221ee47172e696ebaf2";
public static final String hashname = "1b46ef.184440";
public static final String version_name = "us-alpha-57_1b46ef.184440";
public static final String build_date = "2024-09-20_16-56-14";
//-/----------------------------------------------------------___end___------generated by configure, do not edit.

    public static String apkfilename() {
        return brand + "-wallet_android_" + branch + '_' + hashname + ".apk";
    }

    public static String sdk_name() {
         return brand + "-wallet-sdk_java_" + branch;
    }

    public static void version(PrintStream os) {
        os.print(version_name + ' ' + codehash + ' ' + build_date);
    }

    public static String version() {
        return version_name + ' ' + codehash + ' ' + build_date;
    }

    public static String name_date() {
        return version_name + ' ' + build_date;
    }
}

