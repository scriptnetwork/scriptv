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

public abstract class i18n_t implements i18n_if {

    static i18n_if.sid i18n_sid(final String s) {
        if (s.equals("app")) return i18n_t.sid.app;
        if (s.equals("pres")) return i18n_t.sid.pres;
        if (s.equals("ehr")) return i18n_t.sid.ehr;
        if (s.equals("aireq")) return i18n_t.sid.aireq;
        if (s.equals("aires")) return i18n_t.sid.aires;
        if (s.equals("send")) return i18n_t.sid.send;
        if (s.equals("new")) return i18n_t.sid.new_;
        if (s.equals("cat")) return i18n_t.sid.cat;
        if (s.equals("inv")) return i18n_t.sid.inv;
        if (s.equals("pay")) return i18n_t.sid.pay;
        if (s.equals("rcpt")) return i18n_t.sid.rcpt;
        return i18n_if.sid.unknown;
    }

}

