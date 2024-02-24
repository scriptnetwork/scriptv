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
#include <us/gov/config.h>
#include <us/gov/ko.h>

#include "timeseries_entry_index_t.h"

#define loglevel "wallet/wallet"
#define logclass "timeseries_entry_index_t"
#include <us/gov/logs.inc>

using namespace std;
using namespace us;
using namespace us::wallet::wallet;
using c = us::wallet::wallet::timeseries_entry_index_t;

void c::dump(ostream& os) const {
    for (auto& i: *this) {
        os << i << '\n';
    }
}


