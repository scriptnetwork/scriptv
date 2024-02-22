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
#include <sys/stat.h>
#include <sys/types.h>
#include <iostream>
#include <map>
#include <sstream>
#include <cassert>
#include <sstream>
#include <vector>
#include <filesystem>

#include <us/gov/ko.h>

#include "java_files_t.h"
#include "namespace_t.h"
#include "engine_t.h"

using namespace std;
using namespace mim;
using mim::cout;

using c = mim::java_files_t;

c::java_files_t(namespace_t& vertex, const string& name): b(vertex, name) {
}

c::java_files_t(const c& other): b(other) {

}

lang_codefiles_t* c::clone() const {
    return new c(*this);
}

void c::write_include_code(const set<string>& includes, ostream& os) const {
    for (auto& i: includes) {
        os << "import " << i << ";\n";
    }
}

