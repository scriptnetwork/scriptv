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
#include "business.h"

#include <fstream>

#include <us/gov/io/shell_args.h>
#include <us/gov/io/cfg0.h>

#include <us/wallet/engine/daemon_t.h>
#include <us/wallet/trader/trader_t.h>

#define loglevel "wallet/trader/w2w"
#define logclass "business"
#include <us/gov/logs.inc>

using namespace us::wallet::trader;
using c = us::wallet::trader::r2r::w2w::business_t;

c::business_t() {
    log("constructor bank bz");
}

c::~business_t() {
    log("destroyed bank bz", home);
}

c::protocol_factory_id_t c::protocol_factory_id() const {
    return protocol_factory_id_t(c::protocol::name, "w");
}

void c::register_factories(protocol_factories_t& protocol_factories) {
    struct my_protocol_factory_t: protocol_factory_t {

        my_protocol_factory_t(c* bz): bz(bz) {}

        pair<ko, value_type*> create() override {
            auto a = new business_t::protocol(*bz);
            log("created protocol", protocol::name, "instance at", a, 1);
            return make_pair(ok, a);
        }

        c* bz;
    };
    protocol_factories.register_factory(protocol_factory_id(), new my_protocol_factory_t(this));
    assert(protocol_factories.find(protocol_factory_id()) != protocol_factories.end());
}

ko c::init(const string& r2rhome, protocol_factories_t& f) {
    name = "bank";
    auto r = b::init(r2rhome, f);
    if (is_ko(r)) {
        return r;
    }
    return ok;
}

string c::homedir() const {
    ostringstream os;
    os << r2rhome << '/' << protocol::name;
    log("homedir", os.str());
    return os.str();
}

std::pair<ko, us::wallet::trader::trader_protocol*> c::create_protocol() {
    log("protocol instatiation");
    auto p = new business_t::protocol(*this);
    log("protocol instance at", p, 1);
    log("created protocol", c::protocol::name);
    return make_pair(ok, p);
}

void c::list_protocols(ostream& os) const {
    os << c::protocol::name << " w\n";
}

bool c::invert(protocol_selection_t& i) const {  //symmetric role
    if (i.first != protocol::name) return false;
    if (i.second == "w") return true;
    return false;
}

void c::published_protocols(protocols_t& protocols, bool inverse) const { //<orma flow src="us::wallet::trader::businesses_t::published_protocols"/>
    protocols.emplace_back(protocol_selection_t(c::protocol::name, "w"));
}

void c::exec_help(const string& prefix , ostream& os) const {
    protocol::exec_help(prefix, os);
}

ko c::exec(istream& is, wallet::local_api& w) {
    return protocol::exec(is, w);
}

