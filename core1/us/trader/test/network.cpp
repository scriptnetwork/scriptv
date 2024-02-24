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
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>
#include <set>

#include <us/gov/engine/daemon_t.h>
#include <us/gov/engine/db_t.h>

#include <us/test/dispatcher_t.h>

#include <us/trader/workflow/consumer/docs.h>

#include "node_ask.h"
#include "node_bid.h"

#include "node_vc.h"
#include "node_co.h"

#include "main.h"
#include "network.h"

#define loglevel "trader/test"
#define logclass "network"
#include <us/gov/logs.inc>
#include <us/test/assert.inc>

using namespace std;
using namespace us;
using c = us::test::network_c1;
using us::ko;
using hash_t = us::gov::crypto::ripemd160::value_type;

c::network_c1(const string& homedir, const string& logdir, const string& vardir, const string& stage1dir, ostream& os): b(homedir, logdir, vardir, stage1dir, os) {
    add_node("ask", new node_ask("ask", homedir, logdir, vardir, 22172, 22173));
    add_node("bid", new node_bid("bid", homedir, logdir, vardir, 22272, 22273));

    add_node("vc", new node_vc("vc", homedir, logdir, vardir, 22372, 22373));
    add_node("co", new node_co("co", homedir, logdir, vardir, 22472, 22473));
}

c::~network_c1() {
}

void c::stage1_configure() {
    auto ask = dynamic_cast<node_ask*>(find("ask")->second);
    auto bid = dynamic_cast<node_bid*>(find("bid")->second);
    cout << "configuring nodes" << endl;
    ask->create_shop("bid2ask/ask");
    {
        cout << "transfer some coins to bid node" << endl;
        transfer_wait(*ask, *bid, ask->recv_coin, 1000000);
    }
    bid->create_bid("bid2ask/bid");

    auto vc = dynamic_cast<node_vc*>(find("vc")->second);
    auto co = dynamic_cast<node_co*>(find("co")->second);
    cout << "configuring nodes" << endl;
    vc->create_vc("co2vc/vc");
    co->create_co("co2vc/co");
}

void c::stage1_ff_configure() {
    auto ask = dynamic_cast<node_ask*>(find("ask")->second);
    auto bid = dynamic_cast<node_bid*>(find("bid")->second);
    assert(ask->load_data("bid2ask/ask"));
    assert(bid->load_data("bid2ask/bid"));

    auto vc = dynamic_cast<node_vc*>(find("vc")->second);
    auto co = dynamic_cast<node_co*>(find("co")->second);
    assert(vc->load_data("co2vc/vc"));
    assert(co->load_data("co2vc/co"));
}

