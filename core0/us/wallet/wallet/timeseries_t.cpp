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
#include <us/gov/io/cfg0.h>

#include "timeseries_t.h"
#include <us/gov/engine/evidence.h>

#define loglevel "wallet/wallet"
#define logclass "timeseries_t"
#include <us/gov/logs.inc>

using namespace std;
using namespace us;
using namespace us::wallet::wallet;
using c = us::wallet::wallet::timeseries_t;
using cb = us::wallet::wallet::timeseries_entry_t;
using ccrt = us::wallet::wallet::timeseries_entry__cert_t;
using cbin = us::wallet::wallet::timeseries_entry__bin_t;


ccrt::timeseries_entry__cert_t(cert_t* cert): cert(cert) {
}

ccrt::~timeseries_entry__cert_t() {
    delete cert;
}

cb* ccrt::load(const string& home, ts_t ts) {
    ostringstream filename;
    filename << home << '/' << ts;
    cert_t* cert = new cert_t();
    auto r = cert->load(filename.str());
    if (is_ok(r)) {
        return new ccrt(cert);
        
    }
    delete cert;
    return nullptr;
}

cb* cbin::load(const string& home, ts_t ts) {
    ostringstream filename;
    filename << home << '/' << ts;
    vector<uint8_t> v;
    auto r = us::gov::io::read_file_(filename.str(), v);
    if (is_ko(r)) {
        return nullptr;
    }
    return new cbin(move(v));
}

void ccrt::append(ostream& os) const {
    cert->write_pretty(os);
}

cbin::timeseries_entry__bin_t(vector<uint8_t>&& payload): payload(payload) {
}


void cbin::append(ostream& os) const {
    os << "--------------RAW CONTENT----------------\n";
    for (uint8_t byte: payload) {
        if (byte < 32) {
            os << "[Binary: " << payload.size() << " bytes]";
            return;
        }
    }
    os << string(payload.begin(), payload.end());
    os << "-/------------RAW CONTENT----------------\n";
}

cb* cb::load(const string& home, ts_t ts) {
    {
        auto r = ccrt::load(home, ts);
        if (r != nullptr) return r;
    }
    {
        auto r = cbin::load(home, ts);
        if (r != nullptr) return r;
    }
    return nullptr;
}

c::timeseries_t(const string& home): home(home) {
}

c::~timeseries_t() {
    for (auto& i: *this) {
        delete i.second;
    }
}

ko c::load(ts_t ts_from, ts_t ts_to) {
    log("load", ts_from, ts_to);
    ostringstream os;
    os << "cd " << home << "; find . -maxdepth 1 -type f | grep -v params | sed \"s~^\\./\\(.*\\)~\\1~\"";
    string raw;
    auto r = us::gov::io::system_command(os.str(), raw);
    if (is_ko(r)) {
        return r;
    }
    istringstream is(raw);
    log("parsing raw", raw);
    while (is.good()) {
        string line;
        getline(is, line);
        log("getline ", line);
        if (line.empty()) continue;
        uint64_t x{0};
        istringstream is(line);
        is >> x;
        log("read entry tx", x);
        if (!is.fail()) {
            if (ts_from > 0 && x < ts_from) continue;
            if (ts_to > 0 && x >= ts_to) continue;
            log("cb::load", x);
            auto o = cb::load(home, x);
            if (o != nullptr) {
                log("emplace", x);
                emplace(x, o);
            }
        }
    }
    return ok;
}    

namespace {
    string tsstr(uint64_t x) {
        if (x == 0) {
            return "N/A";
        }
        return us::gov::engine::evidence::formatts(x);
    }
}

string c::payload() const {
    ostringstream os;
    if (empty()) {
        os << "No entries.\n";
        return os.str();
    }
    os << size() << " entries.\n";
    ts_t ts_from{0};     
    ts_t ts_to{0};
    if (size() == 1) {
        ts_from =
        ts_to = begin()->first;
    }
    else {
        ts_from = begin()->first;
        ts_to = rbegin()->first;
    }
    os << "oldest: " << ts_from << " (" << tsstr(ts_from) << ")\n";
    os << "newest: " << ts_to << " (" << tsstr(ts_to) << ")\n";
    os << '\n';
    for (auto i: *this) {
        os << "* entry unix time: " << i.first << '\n';
        os << "        greg time: " << tsstr(i.first) << '\n';
        i.second->append(os);
    }
    return os.str();
}

