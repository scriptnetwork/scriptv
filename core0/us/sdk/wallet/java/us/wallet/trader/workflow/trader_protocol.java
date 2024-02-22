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
package us.wallet.trader.workflow;

public class trader_protocol extends us.wallet.trader.trader_protocol {

    public static final int push_begin = us.wallet.trader.trader_protocol.push_end;


    public static final int push_workflow_item = push_begin + 0;
    public static final int push_doc =  push_begin + 1;
    public static final int push_redirects =  push_begin + 2;

    public static final int push_end = push_begin + 3;
    public static final int push_r2r_begin = us.wallet.trader.trader_protocol.push_r2r_begin;

}

