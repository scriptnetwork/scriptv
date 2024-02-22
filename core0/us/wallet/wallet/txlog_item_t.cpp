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
#include "txlog_item_t.h"

#include <us/gov/ko.h>

#include "algorithm.h"

#define loglevel "wallet/wallet"
#define logclass "txlog_item_t"
#include <us/gov/logs.inc>

using namespace std;
using namespace us::wallet::wallet;
using c = us::wallet::wallet::txlog_item_t;

c::txlog_item_t(t1_t* t1, bool initiator, const trade_id_t& trade_id): t1(t1), gov_evt_status(us::gov::engine::evt_unknown), trade_id(trade_id), initiator(initiator) {

    using affected_t = us::wallet::wallet::algorithm::affected_t;
    affected_t a;
    if (initiator) {
        a.add_pay(t1->amount, t1->coin);
        wallet_evt_status = wevt_wait_rcpt_info;
    }
    else {
        a.add_charge(t1->amount, t1->coin);
        wallet_evt_status = wevt_wait_signature;
    }
    io_summary = a.to_string("");
}

void c::set_inv(tx_t* tx) {
    inv.reset(tx);
    wallet_evt_status = wevt_wait_signature;
    //track_status_t(tx->ts, us::gov::engine::evt_wait_rcpt_info, "Waiting for peer info.", tder->id
}

void c::set_tx(tx_t* tx) {
    pay.reset(tx);
    //wallet_track_status = wts_wait_signature;
    //track_status_t(tx->ts, us::gov::engine::evt_wait_rcpt_info, "Waiting for peer info.", tder->id
}

void c::on_tx_tracking_status(const gov_track_status_t& status, set<trade_id_t>& notify) {
    log("on_tx_tracking_status 1");
    auto* tx = pay.get();
    if (tx == nullptr) return;
    if (tx->ts < status.from) return;
    if (tx->ts >= status.to) return;
    gov_evt_status = status.st;
    gov_evt_status_info = status.info;
    notify.emplace(trade_id);
}

string c::title() const {
    if (t1.get() == nullptr) {
        return "Unknown transaction";
    }
    ostringstream os;
    if (initiator) {
        os << "send ";
    }
    else {
        os << "recv ";
    }
    t1->dump(os);
    return os.str();
}

t1_t::t1_t(const cash_t& amount, const hash_t& coin): amount(amount), coin(coin) {

}

size_t t1_t::blob_size() const {
    return blob_writer_t::blob_size(amount) + blob_writer_t::blob_size(coin);
}

void t1_t::to_blob(blob_writer_t& writer) const {
    writer.write(amount);
    writer.write(coin);
}

ko t1_t::from_blob(blob_reader_t& reader) {
    {
        auto r = reader.read(amount);
        if (is_ko(r)) return r;
    }
    {
        auto r = reader.read(coin);
        if (is_ko(r)) return r;
    }
    return ok;
}

void t1_t::dump(ostream& os) const {
    os << amount << ' ';
    if (coin.is_zero()) {
        os << LGAS;
    }
    else {
        os << coin;
    }
}

