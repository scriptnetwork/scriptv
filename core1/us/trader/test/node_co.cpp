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
#include "node_co.h"
#include <us/wallet/trader/workflow/doctype_processors_t.h>

#define loglevel "trader/test"
#define logclass "node_co"
#include <us/gov/logs.inc>
#include <us/test/assert.inc>

using namespace std;

using c = us::test::node_co;

c::node_co(const string& id, const string& homedir, const string& logdir, const string& vardir, uint16_t gport, uint16_t wport): b(id, homedir, logdir, vardir, gport, wport) {
}

void c::update_plugins(const string& destdir) {
    b::update_plugins(destdir);
    tee("update_plugins");
    {
        ostringstream cmd;
        cmd << "cp ../../../../core1/us/trader/libtrader-co2vc-co.so " << destdir;
        system(cmd.str().c_str());
    }
}

bool c::load_data(const string& r2rhome) {
    if (!b::load_data(r2rhome)) {
        return false;
    }
    tee("load_data");
    return true;
}

string c::desc() const {
    return "coish";
}

vector<string> c::r2r_libs(bool filter_not_active) {
    return {"co2vc-co"};
}

//void c::create_co(const string& r2rhome, vector<node*> processors, bookmarks_t&& bookmarks) {
void c::create_co(const string& r2rhome) {
    b::create_node(r2rhome);
    cout << "creating co" << endl;
}

void c::banner(ostream& os) const {
     os << "A node running co called " << desc() << '\n';
}

