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
#include "rpc_peer_t.h"

#define loglevel "gov/dfs"
#define logclass "rpc_peer_t"
#include "logs.inc"
#include "dto.inc"

using namespace us::gov::dfs;
using c = us::gov::dfs::rpc_peer_t;

c::rpc_peer_t(rpc_daemon_t& rpc_daemon): b(rpc_daemon) {
}

#ifdef has_us_gov_dfs_api
    #include <us/api/generated/gov/c++/dfs/cllr_rpc-impl>
#endif

