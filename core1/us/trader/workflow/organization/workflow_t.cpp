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
#include "workflow_t.h"
#include "docs.h"

#define loglevel "trader/workflow/organization"
#define logclass "workflow_t"
#include <us/gov/logs.inc>

using namespace us::trader::workflow::organization;
using c = us::trader::workflow::organization::workflow_t;

c::workflow_t() {
}

c::~workflow_t() {
}

c::bitem* c::enable_cert(bool b, ch_t& ch) {
    return b ? add<cert_t, cert_factory_id>(ch) : remove<cert_t>(ch);
}

c::bitem* c::enable_appointment(bool b, ch_t& ch) {
    return b ? add<appointment_t, 1>(ch) : remove<appointment_t>(ch);
}


c::bitem* c::enable_reference(bool b, ch_t& ch) {
    return b ? add<reference_t, 2>(ch) : remove<reference_t>(ch);
}


c::bitem* c::enable_precontract(bool b, ch_t& ch) {
    return b ? add<precontract_t, precontract_factory_id>(ch) : remove<precontract_t>(ch);
}

c::bitem* c::enable_contract(bool b, ch_t& ch) {
    return b ? add<contract_t, contract_factory_id>(ch) : remove<contract_t>(ch);
}

namespace {

    using namespace std;
    using namespace us;

    template<typename a, c::item_factory_id_t item_factory_id>
    struct my_item_factory_t: c::item_factory_t {
        pair<ko, value_type*> create() const override {
            return make_pair(ok, new item_t<a, item_factory_id>());
        }
    };

}

void c::exec_help(const string& prefix, ostream& os) {
    cert_t::exec_help(prefix + cert_traits::name + ' ', os);
    appointment_t::exec_help(prefix + appointment_traits::name + ' ', os);
    reference_t::exec_help(prefix + reference_traits::name + ' ', os);
    precontract_t::exec_help(prefix + precontract_traits::name + ' ', os);
    contract_t::exec_help(prefix + contract_traits::name + ' ', os);
}

us::ko c::exec(istream& is, wallet::wallet::local_api& w) {
    string cmd;
    is >> cmd;
    log("exec", cmd);
    if (cmd == cert_traits::name) {
        return cert_t::exec(is, w);
    }
    if (cmd == appointment_traits::name) {
        return appointment_t::exec(is, w);
    }
    if (cmd == precontract_traits::name) {
        return precontract_t::exec(is, w);
    }
    if (cmd == reference_traits::name) {
        return reference_t::exec(is, w);
    }
    if (cmd == contract_traits::name) {
        return contract_t::exec(is, w);
    }
    auto r = "KO 44093 exec interface is not enabled for this doctype.";
    log(r);
    return r;
}


void c::register_factories(item_factories_t& item_factories) const {
    item_factories.register_factory(cert_factory_id, new my_item_factory_t<cert_t, cert_factory_id>());
    item_factories.register_factory(appointment_factory_id, new my_item_factory_t<appointment_t, appointment_factory_id>());
    item_factories.register_factory(reference_factory_id, new my_item_factory_t<reference_t, reference_factory_id>());
    item_factories.register_factory(precontract_factory_id, new my_item_factory_t<precontract_t, precontract_factory_id>());
    item_factories.register_factory(contract_factory_id, new my_item_factory_t<contract_t, contract_factory_id>());
}

