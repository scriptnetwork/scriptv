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
// -----------------------------------
// ---- DO NOT EDIT ------------------
// -----------------------------------
// -- file generated from             
// --   poc_spec/co2vc/dynamics
// -- by                              
// --   make.sh
// -----------------------------------


#include "business.h"
#include <fstream>

#include <us/gov/io/shell_args.h>
#include <us/gov/io/cfg0.h>

#define loglevel "us::trader::r2r::co2vc/co"
#define logclass "business_t"
#include <us/gov/logs.inc>

using namespace us::trader::r2r::co2vc;
using c = us::trader::r2r::co2vc::co::business_t;

c::business_t() {
    name = "co (co2vc)";
}

c::~business_t() {
    log("destroyed co2vc co", home);
}

ko c::init(const string& r2rhome, protocol_factories_t& f) {
    auto r = b::init(r2rhome, f);
    if (is_ko(r)) {
        return r;
    }
    log("co2vc co init. homebase", home);
    assert(!home.empty());
    {
        auto r = doctype_processors.load(home + "/doctype_processors");
        if (is_ko(r)) {
            log("document processor nodes list is empty.");
        }
    }
    log("co2vc co initiated @", home);
    return ok;
}

c::protocol_factory_id_t c::protocol_factory_id() const {
    return protocol_factory_id_t(c::protocol::name, "co");
}

void c::register_factories(protocol_factories_t& protocol_factories) {
    struct my_protocol_factory_t: protocol_factory_t {

        my_protocol_factory_t(c* bz): bz(bz) {}

        pair<ko, value_type*> create() override {
            auto a = new business_t::protocol(*bz);
            log("created protocol", protocol::name, "instance at", a);
            return make_pair(ok, a);
        }

        c* bz;
    };
    protocol_factories.register_factory(protocol_factory_id(), new my_protocol_factory_t(this));
    assert(protocol_factories.find(protocol_factory_id()) != protocol_factories.end());
}

string c::homedir() const {
    ostringstream os;
    os << r2rhome << '/' << protocol::name << "/co";
    log("homedir", os.str());
    return os.str();
}

pair<us::ko, us::wallet::trader::trader_protocol*> c::create_protocol() {
    auto a = new c::protocol(*this);
    log("protocol instance at", a, 3);
    return make_pair(ok, a);
}

void c::list_protocols(ostream& os) const {
    os << c::protocol::name << " co\n";
}

void c::published_protocols(protocols_t& protocols, bool inverse) const {
    protocols.emplace_back(c::protocol::name, inverse ? "vc" : "co");
}


