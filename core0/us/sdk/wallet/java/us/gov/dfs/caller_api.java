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
package us.gov.dfs;
import static us.gov.crypto.ripemd160.hash_t;                                                  // hash_t
import static us.gov.io.types.*;                                                               // *
import static us.gov.socket.types.*;                                                           // *
import us.ko;                                                                                  // ko
import us.string;                                                                              // string

public interface caller_api extends dto {

    //#include <us/api/generated/gov/java/dfs/cllr_purevir>
    //------------------------------------------------------------__begin__------generated by configure, do not edit.
    //content of file: <us/api/generated/gov/java/dfs/cllr_purevir>
    // dfs - master file: us/api/data/gov/dfs

      ko call_file_request(final hash_t content_digest, bin_t content);
      ko call_file_request_response(final bin_t content);
    //-/----------------------------------------------------------___end___------generated by configure, do not edit.

}

