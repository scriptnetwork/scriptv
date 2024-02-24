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
#include <dlfcn.h>
#include <filesystem>

#include <us/gov/config.h>
#include <us/gov/io/cfg0.h>
#include <us/wallet/engine/daemon_t.h>

#include <us/wallet/trader/r2r/w2w/business.h>

#include "business.h"
#include "trades_t.h"
#include "types.h"
#include "libs_t.h"

#define loglevel "wallet/trader"
#define logclass "libs_t"
#include <us/gov/logs.inc>

using namespace us::wallet::trader;
using c = us::wallet::trader::libs_t;
using protocols_t = us::wallet::trader::bootstrap::protocols_t;

//----------------------lib0 - core0 businesses
lib0_t::lib0_t() {
}

lib0_t::~lib0_t() {
    assert(empty());
}

void lib0_t::cleanup() {
    set<business_t*> bzs = *this;
    for (auto& i: bzs) {
        delete_business(i);
    }
    assert(empty());
}

ko lib0_t::delete_business(business_t* bz) {
    log("lib0_t::delete_business", bz);
    auto i = find(bz);
    if (i == end()) {
        auto r = "KO 78699 tried to delete business not created by this factory.";
        log(r);
        return r;
    }
    erase(i);
    return ok;
}

//----------------------lib1 - core0 businesses
namespace us::wallet::trader {

    struct lib1_t final: lib0_t {
        using b = lib0_t;

        lib1_t(function<business_t*()> metabz);
        ~lib1_t() override;

        void cleanup() override;

    public:
        pair<ko, business_t*> create_business() override;
        ko delete_business(business_t*) override;

    private:
        ko good() const override;

    private:
        function<business_t*()> metabz;
    };
}

using c1 = us::wallet::trader::lib1_t;

c1::lib1_t(function<business_t*()> metabz): metabz(metabz) {
}

c1::~lib1_t() {
    log("c1::~destructor");
}

void c1::cleanup() {
    log("c1::cleanup");
    b::cleanup();
}

ko c1::good() const {
    return ok;
}

pair<ko, business_t*> c1::create_business() {
    log("lib1_t::create_business");
    auto o = metabz();
    if (o == nullptr) {
        auto r = "KO 85592 Cannot create business from main exec.";
        log(r);
        return make_pair(r, nullptr);
    }
    emplace(o);
    return make_pair(ok, o);
}

ko c1::delete_business(business_t* bz) {
    log("c1::delete_business", bz);
    auto r = b::delete_business(bz);
    if (is_ko(r)) {
        return r;
    }
    delete bz;
    return ok;
}

//----------------------lib - plugin businesses
namespace us::wallet::trader {

    struct lib_t final: lib0_t {
        using b = lib0_t;

        typedef us::wallet::trader::business_t* business_create_t(int ifversion);
        typedef void business_destroy_t(us::wallet::trader::business_t*);

    public:
        lib_t(const string& libfilename);
        lib_t(const lib_t&) = delete;
        ~lib_t() override;

        void cleanup() override;

    public:
        pair<ko, business_t*> create_business() override;
        ko delete_business(business_t*) override;

    private:
        ko good() const override;

    private:
        void* plugin{nullptr};
        business_create_t* create_bz{nullptr};
        business_destroy_t* destroy_bz{nullptr};
    };

}

lib_t::lib_t(const string& filename) { //constructor 2
    log("lib_t constructor 2", filename);
    plugin = ::dlopen(filename.c_str(), RTLD_LAZY);
    if (!plugin) {
        log("Cannot open library", ::dlerror());
        return;
    }
    ::dlerror();
    {
        create_bz = (business_create_t*) dlsym(plugin, "uswallet_business_create");
        const char *dlsym_error = ::dlerror();
        if (dlsym_error) {
            log("Cannot load symbol 'uswallet_business_create': ", dlsym_error);
            ::dlclose(plugin);
            plugin = nullptr;
            create_bz = nullptr;
            destroy_bz = nullptr;
            return;
        }
    }
    {
        destroy_bz = (business_destroy_t*) ::dlsym(plugin, "uswallet_business_destroy");
        const char* dlsym_error = ::dlerror();
        if (dlsym_error) {
            log("Cannot load symbol uswallet_business_destroy", dlsym_error);
            ::dlclose(plugin);
            plugin = nullptr;
            create_bz = nullptr;
            destroy_bz = nullptr;
            return;
        }
    }
    assert(create_bz != nullptr);
    assert(destroy_bz != nullptr);
    assert(plugin != nullptr);
}

lib_t::~lib_t() {
    log("lib_t::~lib_t");
    assert(create_bz == nullptr);
    assert(destroy_bz == nullptr);
    assert(plugin == nullptr);
}

ko lib_t::good() const {
    if (plugin != nullptr && create_bz != nullptr && destroy_bz != nullptr) {
        return ok;
    }
    auto r = "KO 84039 lib is not in good state.";
    log(r, plugin != nullptr, create_bz != nullptr, destroy_bz);
    return r;
}

ko lib_t::delete_business(business_t* bz) {
    log("lib_t::delete_business", bz);
    auto r = b::delete_business(bz);
    if (is_ko(r)) {
        return r;
    }
    {
        auto r = good();
        if (is_ko(r)) {
            assert(false);
            return r;
        }
    }
    log("calling destroy_bz");
    if (bz != nullptr) {
        destroy_bz(bz);
        log("returned from destroy_bz");
    }
    return ok;
}

void lib_t::cleanup() {
    log("lib_t::cleanup");
    b::cleanup();
    if (plugin == nullptr) {
        return;
    }
    ::dlclose(plugin);
    create_bz = nullptr;
    destroy_bz = nullptr;
    plugin = nullptr;
}

pair<ko, business_t*> lib_t::create_business() {
    log("lib_t::creating business", business_t::interface_version);
    auto o = create_bz(business_t::interface_version);
    if (o == nullptr) {
        auto r = "KO 59982 Cannot create bz using shared library factory.";
        log(r);
        return make_pair(r, nullptr);
    }
    emplace(o);
    return make_pair(ok, o);
}

protocol_selection_t c::extract_protocol_selection(const string& fn) {
    log("extract_protocol_selection", fn);
    protocol_selection_t ans;
    assert(!ans.is_set());
    auto i = fn.find_first_of('-');
    if (unlikely(i != string::npos)) {
        ++i;
        while(true) {
            auto i0 = i;
            i = fn.find_first_of('-', i0);
            if (unlikely(i == string::npos)) {
                i = i0;
                break;
            }
            ans.first = ans.second;
            ans.second = fn.substr(i0, i - i0);
            ++i;
        }
        auto i0 = i;
        i = fn.find_first_of("-.", i0);
        if (unlikely(i == string::npos)) {
            log("KO 91027 missing extension.", fn);
            return protocol_selection_t();
        }
        ans.first = ans.second;
        ans.second = fn.substr(i0, i - i0);
        ++i;
    }
    us::gov::io::cfg0::trim(ans.first);
    us::gov::io::cfg0::trim(ans.second);
    if (ans.first.empty()) {
        log("KO 91028 protocol cannot be deduced from filename.", fn);
        return protocol_selection_t();
    }
    if (ans.second.empty()) {
        log("KO 91029 role cannot be deduced from filename.", fn);
        return protocol_selection_t();
    }
    assert(ans.is_set());
    return ans;
}

c::libs_t(trades_t& parent): parent(parent) {
    log("creating libs");
}

c::~libs_t() {
    log("destroying libs");
    for (auto& i: *this) {
        log("destroying lib", i.first.to_string2());
        i.second->cleanup();
        delete i.second;
    }
}

void c::load() {
    log("loading core0 plugins");
    load_bank_factory();
    log("loading plugins");
    load_plugins();
}

void c::load_bank_factory() {
    log("creating w2w");
    lib0_t* l = new lib1_t([]() -> business_t* {
        return new us::wallet::trader::r2r::w2w::business_t();
    });
    log("adding lib w2w-w at", l);
    auto r = emplace(protocol_selection_t(us::wallet::trader::r2r::w2w::business_t::protocol::name, "w"), l);
    assert(r.second);
    log("added lib w2w-w at", l);
}

void c::load_plugins() {
    namespace fs = std::filesystem;
    string plugindir = parent.daemon.home + "/trader/lib";
    us::gov::io::cfg0::ensure_dir(plugindir);
    for (auto& p: fs::directory_iterator(plugindir)) {
        if (!is_regular_file(p.path())) continue;
        string fn = p.path().filename().string();
        protocol_selection_t protocol_selection = extract_protocol_selection(fn);
        if (!protocol_selection.is_set()) {
            log("Ignoring lib", fn, "protocol_selection is unparseable.");
            continue;
        }
        if (find(protocol_selection) != end()) {
            auto r = "KO 86692 business factory already exists.";
            log(r, protocol_selection.to_string2());
            continue;
        }
        ostringstream os;
        os << plugindir << '/' << fn; //libustrader-pat2ai-ai.so
        lib0_t* l = new lib_t(os.str());
        {
            auto r = l->good();
            if (is_ko(r)) {
                log(os.str(), protocol_selection.to_string2());
                delete l;
                continue;
            }
        }
        log("adding lib", os.str(), "at", l);
        auto r = emplace(protocol_selection, l);
        assert(r.second);
        log("added lib", os.str(), "at", l);
    }
}

pair<ko, business_t*> c::create_business(const protocol_selection_t& protocol_selection) {
    log("libs_t::create_business", protocol_selection.to_string2());
    auto i = find(protocol_selection);
    if (i == end()) {
        auto r = "KO 75558 Business factory not found for the given protocol_selection.";
        log(r);
        return make_pair(r, nullptr); 
    }
    return i->second->create_business();
}

ko c::delete_business(business_t* bz) {
    log("delete_business", bz);
    if (bz == nullptr) return ok;
    log("delete_business", bz->protocol_selection());
    auto i = find(bz->protocol_selection());
    if (i == end()) {
        auto r = "KO 55835 bz not found.";
        log(r);
        return r;
    }
    log("calling lib delete_business", i->first.to_string2());
    return i->second->delete_business(bz);
}

protocols_t c::available_protocol_selections() const {
    protocols_t protocols;
    protocols.reserve(size());
    for (auto& i: *this) {
        protocols.emplace_back(i.first);
    }
    return protocols;    
}

