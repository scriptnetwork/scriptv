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
import java.util.ArrayList;
##include##

public class ##classname##__datatype_t extends ArrayList<##classname##__itemtype_t> {

    private static void log(final String line) {                       //--strip
        CFG.log_android("##classname##__datatype_t: " + line);         //--strip
    }                                                                  //--strip

    public ##classname##__datatype_t(final ##api__datatype## o) {
        log("constructor "); //--strip
        //TODO: 
        add(new ##classname##__itemtype_t("example", null)); //kickoff entry - delete-
        //example apitype is a map
        //ensureCapacity(o.size());
        //for (Map.Entry<String, # #apiitemtype# #> entry : o.entrySet()) {
        //    add(new # #classname# #__datatype_t(entry.getKey(), entry.getValue()));
        //}
    }

}

