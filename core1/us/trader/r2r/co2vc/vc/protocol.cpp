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


#include "protocol.h"
#include <fstream>
#include <unordered_map>

#include <us/gov/socket/datagram.h>
#include <us/gov/io/shell_args.h>
#include <us/gov/io/cfg0.h>
#include <us/gov/io/cfg0.h>

#include <us/wallet/trader/workflow/doctype_processors_t.h>

#include "business.h"

#define loglevel "trader/r2r/co2vc/vc"
#define logclass "protocol"
#include <us/gov/logs.inc>

using namespace us::trader::r2r::co2vc::vc;
using c = us::trader::r2r::co2vc::vc::protocol;
using us::ko;

const char* c::rolestr() const { return "vc"; }
const char* c::peer_rolestr() const { return "co"; }
const char* c::get_name() const { return name; };

c::protocol(business_t& bz): b(bz) {
    log("created protocol_co2vc_vc");
}

c::~protocol() {
    log("destroyed protocol_co2vc_vc");
}

void c::init_workflows(ch_t& ch) {
}

ko c::on_remote_(params_t* rp, ch_t& ch) {
    log("on_remote_params");
    auto r = b::on_remote_(rp, ch);
    if (is_ko(r)) {
        return r;
    }
    log("on_remote_params. returned", ch.to_string());
    return ok;
}

void c::on_file_updated(const string& path, const string& name, ch_t& ch) {
    log("on_file_updated", name);
    b::on_file_updated(path, name, ch);
}

void c::dump(ostream& os) const {
    os << "vc";
}

void c::list_trades_bit(ostream& os) const { //protocol role
    os << "co2vc vc" << ' ';
}

namespace { namespace i18n {

    using namespace std;

    struct r_en_t;
    struct r_es_t;

    struct r_t: unordered_map<uint32_t, const char*> {
        using b =  unordered_map<uint32_t, const char*>;

        using b::unordered_map;

        const char* resolve(uint32_t n) const {
            log("string-res. resolve", n);
            auto i = find(n);
            if (i == end()) return begin()->second;
            return i->second;
        }

        static r_t& resolver(const string& lang);
    };

    struct r_en_t: r_t {
        using b = r_t;

        r_en_t(): b({
            {0, "KO 30920 Use i18n package in Lower Layer."},
            {1, "Talk to VC."}, {2, "Chat with VC."},

        }) {
            //log("constructor 'en' string resources with", size(), "entries. Entry #4 is", resolve(4));
        }

    };

    struct r_es_t: r_t {
        using b = r_t;

        r_es_t(): b({
            {0, "KO 30920"},
            {1, "Talk to VC."}, {2, "Chat with VC."},
        }) {
            //log("constructor 'es' string resources with", size(), "entries. Entry #4 is", resolve(4));
        }

    };

    r_en_t r_en;
    r_es_t r_es;

    r_t& r_t::resolver(const string& lang) {
        if (lang == "es") return r_es;
        return r_en;
    }

}}

uint32_t c::trade_state_() const {
    log("trade_state_");
    return 1;
}

void c::judge(const string& lang) {
    auto st = trade_state_();
    if (st != _trade_state.first) {
        if (st == 0) {
            b::judge(lang);
            return;
        }
        auto t = i18n::r_t::resolver(lang);
        auto r = t.resolve(st);
        log("trade_state", st, r);
        _trade_state = make_pair(st, r);
        _user_hint = t.resolve(_trade_state.first + 1);
    }
}

c::chat_t::entry c::AI_chat(const chat_t& chat, peer_t&) {
    chat_t::entry response;
    if (chat.empty_me_()) {
        using us::wallet::trader::paragraphs;
        using us::wallet::trader::paragraph;
        response.emplace_back("Hi from our company!.");
    }
    return response;
}

size_t c::blob_size() const {
    size_t sz = b::blob_size();
    return sz;
}

void c::to_blob(blob_writer_t& writer) const {
    b::to_blob(writer);
}

c::factory_id_t c::factory_id() const {
    return protocol_selection_t("co2vc", "vc");
}

ko c::from_blob(blob_reader_t& reader) {
    {
        auto r = b::from_blob(reader);
        if (is_ko(r)) {
            return r;
        }
    }
    return ok;
}


