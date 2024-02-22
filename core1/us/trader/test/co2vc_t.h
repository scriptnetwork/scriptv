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
#include <us/test/r2r_t.h>
#include "network.h"

namespace us::test {
    using namespace std;

    struct co2vc_t: r2r_t {
        using b = r2r_t;

        co2vc_t(network& n): b(n) {}

        void test_0(node&, node&);
        void test_1(node&, node&);
        void test_3(node&, node&);
        void test_4(node&, node&);
        void test_5(node&, node&);
        void test_6(node&, node&);
        void test_7(node&, node&);
        void test_8(node&, node&);
        void test_9(node&, node&);

        void test_end(node&, node&);

        void test(node&, node&);
        void run();

    };

}

