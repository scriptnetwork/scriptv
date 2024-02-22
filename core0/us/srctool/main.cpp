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
#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <sstream>
#include <algorithm>
#include <set>
#include <map>
#include <cstring>
#include <cassert>
#include <filesystem>
#include <functional>
#include "srctool_t.h"

using namespace std;

void help(ostream& os) {
    os << "srctool [options] -a <header_file> <file>        Adds header\n";
    os << "srctool [options] -i <file>                      Removes header\n";
    os << "srctool [options] --patch <pfx> <envfile> <file> Replaces in file all tokens defined in env. tokens are in the form ##<pfx>token##, env in the form token=\"replacement\" \n";
    os << "Options:\n";
    os << "  --ext <extension>                              Treat file as if it had the given extension.\n";
    os << "  --dir                                          <file> is a directory, apply recursively.\n";
    os << "  --patch                                        Tokens mode. all other options ignored.\n";
    os << "  --dryrun                                       Write to srdout instead of rewriting files.\n";
    os << "    extension: sh                                File shall have bangshe on 1st line.\n";
}

int main(int argc, char** argv) {
    if (argc < 2) {
        help(cerr);
        return 1;
    }
    mim::srctool_t o;
    int p = 1;
    while(true) {
        string arg = argv[p++];
        if (arg == "--ext") {
            if (argc <= p) {
                help(cerr);
                return 1;
            }
            o._ext = argv[p++];
            if (o._recursive) {
                cerr << "KO 76998 --ext and --dir cannot be used together.\n";
                return 1;
            }
            if (o._ext == "sh") o._ext = "script";
            if (argc <= p) {
                help(cerr);
                return 1;
            }
            continue;
        }
        if (arg == "--dir") {
            if (argc <= p) {
                help(cerr);
                return 1;
            }
            if (!o._ext.empty()) {
                cerr << "KO 76997 --ext and --dir cannot be used together.\n";
                return 1;
            }
            o._recursive = true;
            continue;
        }
        if (arg == "--patch") {
            if (argc <= p) {
                help(cerr);
                return 1;
            }
            string prefix = argv[p++];
            if (argc <= p) {
                help(cerr);
                return 1;
            }
            string envfile = argv[p++];
            if (argc <= p) {
                help(cerr);
                return 1;
            }
            string ofile = argv[p++];
            auto r = o.patch(prefix, envfile, ofile);
            return r;
        }
        if (arg == "--dryrun") {
            o._dryrun = true;
            continue;
        }
        if (arg == "-i") {
            if (argc < p) {
                help(cerr);
                return 1;
            }
            if (o._recursive) {
                return o.remove_header_dir(argv[p]);
            }
            else {
                return o.remove_header(argv[p]);
            }
        }
        if (arg == "-a") {
            if (argc < p + 1) {
                help(cerr);
                return 1;
            }
            if (o._recursive) {
                return o.add_header_dir(argv[p], argv[p + 1]);
            }
            else {
                return o.add_header(argv[p], argv[p + 1]);
            }
        }
        break;
    }

    help(cout);
    cerr << "KO 76094 invalid input\n";
    return 1;
}

