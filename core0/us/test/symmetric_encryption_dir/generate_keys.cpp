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
#include <us/gov/crypto/symmetric_encryption.h>

#include <iostream>
#include <string>

#define loglevel "test"
#define logclass "symm_enc"
#include <us/gov/logs.inc>


#include <us/gov/crypto/ec.h>

using namespace std;
using namespace us::gov::crypto;

//using namespace us::test;

int main ()
{


    us::dbg::thread_logger::set_root_logdir("logs");
    log_pstart("generate_keys");
    log_start("", "main");


    ec::keys k = ec::keys::generate();
    std::cout << k.priv << " " << k.pub << endl;
    //std::cout << "4ACKBcXXhtGb3NtZzSfwgSs3GqCgCBY65juF2UVSJQR2 dPtUg631Hh7tL8t3wK6KHDwFfQzLmcHtkEAH5mSsANX3" << endl;
    //std::cout << "7RZ9FmWLCdfGZCzThU7DZKf2q8hyxtAzCXfjXEGVU8E1 R9s12KNiCKJG1ax5hYGoSHgLSMX3trHY26jmwbhUQFRQCgwR2gRtrAnJTR8NwmFkD4YMtHtXhe57KAd6nSjrYtMV" << endl;
    //std::cout << "9pKQDhcZsi9V1qVhaDDnqV7HyiatxUEwTiLjqtqD7ZR6 R9s12KNiCKJG1ax5hYGoSHgLSMX3trHY26jmwbhUQFRQCgwR2gRtrAnJTR8NwmFkD4YMtHtXhe57KAd6nSjrYtMV" << endl;
    //std::cout << "9pKQDhcZsi9V1qVhaDDnqV7HyiatxUEwTiLjqtqD7ZR6 or69BumA7ZALzHNKjuxDLtHithXo3BfzJ2VYg73uNizk" <<endl;
}

