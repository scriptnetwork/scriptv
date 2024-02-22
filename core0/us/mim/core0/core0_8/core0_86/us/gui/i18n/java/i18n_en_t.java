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

public final class i18n_en_t implements i18n_t {
    @Override public String resolve(sid s) {
        switch (s) {
            case app: return "Appointment";
            case pres: return "Prescription";
            case ehr: return "Electronic Health Record (EHR)";
            case aireq: return "A.I. Service referral";
            case aires: return "A.I. Service results";
            case send: return "Send";
            case new_: return "New";
            case cat: return "Catalogue";
            case inv: return "Invoice";
            case pay: return "Payment";
            case rcpt: return "Receipt";
        }
        return "unknown";
    }

    
};

