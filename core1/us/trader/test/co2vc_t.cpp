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
#include "co2vc_t.h"

#include <us/test/node.h>
#include <us/test/dispatcher_t.h>
#include <us/wallet/trader/trader_t.h>
#include <us/wallet/trader/data_t.h>
#include <us/wallet/trader/workflow/trader_protocol.h>

//#include <us/trader/workflow/consumer/docs.h>
//#include <us/trader/workflow/consumer/workflow_t.h>
//#include <us/trader/workflow/consumer/basket.h>

#include "node_co.h"
#include "node_vc.h"
#include "network.h"

#define loglevel "test"
#define logclass "co2vc_t"
#include <us/gov/logs.inc>
#include <us/test/assert.inc>

using c = us::test::co2vc_t;

void c::test_0(node& n1, node& n2) {
    curtest(n1, n2, "trade start", __FILE__, __LINE__);
    test_trade_start_dialog_a(n1, n2, trade_id, 0, 2, 2);
}

void c::test_1(node& n1, node& n2) {
    curtest(n1, n2, "start co2vc vc", __FILE__, __LINE__);
    test_trade_start_dialog_b_2(n1, n2, trade_id, 0, 2);
}

void c::test_3(node& n1, node& n2) {
    curtest(n1, n2, "wallet_cli list_trades()", __FILE__, __LINE__);

    string ans;
    auto r = n1.wallet_cli->rpc_daemon->get_peer().call_list_trades(ans);
    if (r != ok) {
        cout << r << endl;
        assert(false);
    }
    assert(!ans.empty());
    cout << ans << endl;
    wait(n1, n2);
}

void c::test_4(node& n1, node& n2) {
    curtest(n1, n2, "show data", __FILE__, __LINE__);

    n1.wallet_cli_dis->expected_code.emplace(us::wallet::trader::trader_t::push_data, 1); //103
    exec_cur_trade(n1, "show data");

    wait(n1, n2);

    Check_data(n1, "logo", "N");
}

void c::test_5(node& n1, node& n2) {
    curtest(n1, n2, "n2.wallet_cli->api().list_trades()", __FILE__, __LINE__);

    string ans;
    auto r = n2.wallet_cli->rpc_daemon->get_peer().call_list_trades(ans); //exec("trade list");
    assert(r == ok);
    cout << "list_trades:\n" << ans << endl;
    string stid;
    {
        istringstream is(ans);
        is >> stid;
    }
    us::gov::crypto::ripemd160::value_type tid;
    {
        istringstream is(stid);
        is >> tid;
    }
    cout << "tid: " << tid << " == " << n1.wallet_cli->cur << endl;
    assert(tid == n1.wallet_cli->cur);
}

void c::test_6(node& n1, node& n2) {
    curtest(n1, n2, "trade show data", __FILE__, __LINE__);

    n2.wallet_cli_dis->expected_code.emplace(us::wallet::trader::trader_t::push_data, 1); //103
    us::gov::crypto::ripemd160::value_type& tid = n1.wallet_cli->cur;
    ostringstream cmd;
    cmd << "trade " << tid << " show data";
    assert(n2.wallet_cli->exec(cmd.str()) == ok);

    wait(n1, n2);

    Check_data(n2, "protocol", "co2vc vc");
}

void c::test_7(node& n1, node& n2) {
    curtest(n1, n2, "request logo", __FILE__, __LINE__);

    n1.wallet_cli_dis->expected_code.emplace(us::gov::relay::pushman::push_ok, 1);
    n1.wallet_cli_dis->expected_code.emplace(us::wallet::trader::trader_t::push_data, 1);
    cout << "\nrequest logo" << endl;
    exec_cur_trade(n1, "request logo");

    wait(n1, n2);

    cout << "check data" << endl;
    Check_data(n1, "logo", "Y");
}

void c::test_8(node& n1, node& n2) {
    curtest(n1, n2, "show logo", __FILE__, __LINE__);

    n1.wallet_cli_dis->expected_code.emplace(us::wallet::trader::trader_protocol::push_logo, 1);
    cout << "\nshow logo" << endl;
    exec_cur_trade(n1, "show logo");

    wait(n1, n2);

    cout << "check data" << endl;
    Check_data(n1, "logo", "Y");
}

void c::test_9(node& n1, node& n2) {
    curtest(n1, n2, "show data", __FILE__, __LINE__);

    n1.wallet_cli_dis->expected_code.emplace(us::wallet::trader::trader_t::push_data, 1);
    n2.wallet_cli_dis->expected_code.emplace(us::wallet::trader::trader_t::push_data, 1);
    exec_cur_trade(n1, "show data");
    exec_cur_trade(n2, "show data");

    wait(n1, n2);

    //Check_data(n1, "local__wf_cat", "N");
    //Check_data(n2, "local__wf_cat", "Y");
}

void c::test_end(node& n1, node& n2) {
    curtest(n1, n2, "disconnect", __FILE__, __LINE__);

    n1.wallet_cli_dis->expected_code.emplace(us::wallet::trader::trader_t::push_data, 1);
    n1.wallet_cli_dis->expected_code.check.emplace(us::wallet::trader::trader_t::push_data,
        [&](const hash_t& tid, uint16_t code, const vector<uint8_t>& blob) {
            string payload;
            assert(is_ok(blob_reader_t::parse(blob, payload)));
            assert(tid == n1.wallet_cli->cur);
            assert(tid == trade_id);
            Check_s_contains(payload, "state offline");
        }
    );

    n2.wallet_cli_dis->expected_code.emplace(us::gov::relay::pushman::push_ok, 1);
    n2.wallet_cli_dis->expected_code.check.emplace(us::gov::relay::pushman::push_ok,
        [&](const hash_t& tid, uint16_t code, const vector<uint8_t>& blob) {
            string payload;
            assert(is_ok(blob_reader_t::parse(blob, payload)));
            assert(tid == n1.wallet_cli->cur);
            assert(tid == trade_id);
            Check_s_contains(payload, "Going offline.");
        }
    );

    n2.wallet_cli_dis->expected_code.emplace(us::wallet::trader::trader_t::push_data, 1);
    n2.wallet_cli_dis->expected_code.check.emplace(us::wallet::trader::trader_t::push_data,
        [&](const hash_t& tid, uint16_t code, const vector<uint8_t>& blob) {
            string payload;
            assert(is_ok(blob_reader_t::parse(blob, payload)));
            assert(tid == n1.wallet_cli->cur);
            assert(tid == trade_id);
            Check_s_contains(payload, "state offline");
        }
    );

    exec_cur_trade(n2, "disconnect");
    wait(n1, n2);
}

void c::test(node& n1, node& n2) {
    using hash_t = us::gov::crypto::ripemd160::value_type;

    cout << "start trade " << endl;
    cout << "node1: ";
    n1.gov_cli->exec("id");
    cout << "node2: ";
    n2.gov_cli->exec("id");
    cout << "n2 node wallet port: " << n2.wport << endl;
    cout << "n1 current trade id " << n1.wallet_cli->cur << endl;
    cout << "n2 current trade id " << n2.wallet_cli->cur << endl;
    assert(n1.wallet_cli->cur.is_zero());
    assert(n2.wallet_cli->cur.is_zero());

    //auto reward_coin = dynamic_cast<node_ask&>(ask).reward_coin;
    //auto addr = dynamic_cast<node_ask&>(ask).addr;
    //assert(reward_coin.is_not_zero());
    //assert(addr.is_not_zero());

    test_0(n1, n2); // initiate trade
    test_1(n1, n2);

    test_3(n1, n2);
    test_4(n1, n2);
    test_5(n1, n2);
    test_6(n1, n2);
    test_7(n1, n2);
    test_8(n1, n2);
    test_9(n1, n2);

    test_end(n1, n2);
}

void c::run() {
    auto& n1 = *n.find("co")->second;
    auto& n2 = *n.find("vc")->second;
    test_r2r_cfg(n1, n2, [&](node& n1, node& n2) { test(n1, n2); }, trade_id);
}


