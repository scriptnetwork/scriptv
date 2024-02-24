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
#include <vector>
#include <filesystem>

#include <us/gov/ko.h>

#include "android_vertex_t.h"
#include "android_java_files_t.h"

using namespace std;
using namespace mim;

using c = mim::android_vertex_t;

string c::package_ns = "us.cash";
string c::package_dir = "us/cash";

string c::cfg_str28 = "us.cash"; //Android app package name
string c::cfg_str32 = "US Cash"; //Android app name

c::android_vertex_t(const string& name, const string& mim_file): b(name, mim_file) {
}

c::android_vertex_t(const c& other): b(other) {
}

vertex_t* c::clone() const {
    return new c(*this);
}

string c::conf_target_dir(const string& lang) const {
    assert(lang == "java");
    return "us/android/app/src/main/java/" + package_dir;
}

lang_codefiles_t* c::create_lang_codefiles(const string& lang) {
    lang_codefiles_t* o = nullptr;
    if (lang == "java") {
        o = new android_java_files_t(*this, lang);
    }
    assert(o != nullptr);
    return o;
}

void c::dump_(ostream& os) const {
    {
        ind_t ind(os, "  ");
        os << "[] android vertex.\n";
    }
    b::dump_(os);
}

