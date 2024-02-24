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
#pragma once
#include <unordered_map>
#include <mutex>
#include <string>

#include <us/gov/config.h>

#include "types.h"
#include "data_sources_index_t.h"

namespace us::wallet::engine {

    struct daemon_t;
    struct peer_t;


    struct data_source_t  {
        data_source_t() {
            address.zero();
        }
        data_source_t(const hash_t& address): address(address) {}
        virtual ~data_source_t() {}
        virtual void dump(const string& pfx, ostream&) const;

        void connect(const hash_t& addr) {
            address = addr;
        }
        hash_t address;
    };


    struct human_data_source_t: data_source_t  {
    };

    struct health_watch_data_source_t: data_source_t  {
    };

    struct data_sources_t final: unordered_map<string, data_source_t*> {
        using datagram = us::gov::socket::datagram;

    public:
        data_sources_t(daemon_t&);
        ~data_sources_t();

    public:
        data_source_t* get_data_source(const string& name); //returns soft pointer

    public:
        void dump(ostream&) const;


        ko get_index(data_sources_index_t& ans);
        ko connect(const string& name, const hash_t& address);

    public:
        daemon_t& daemon; //daemon mode, trading enabled

        #if CFG_LOGS == 1
            string logdir;
        #endif

        data_source_t* human{nullptr};

    private:
        mutable mutex mx;
    };

}

