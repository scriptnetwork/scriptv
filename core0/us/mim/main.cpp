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
#include "engine_t.h"
#include "namespace_t.h"
#include <us/gov/ko.h>

using namespace std;
using namespace us;

using namespace mim;

using maze_t = mim::engine_t;
maze_t& maze = engine_t::instance();

void help(ostream& os) {
    os << "MIM (Master Information Model)\n";
    os << "mim [options] <command>\n";
    os << "Ops commands:\n";
    os << "  * configure [dev|cbs]     Configure codebase for edition (debugmode) or for the Continuous Build System (cbs).\n";
    os << "  * clean_conf              Clean up produced files.\n";
    os << "  Options:\n";
    os << "     -maxz <float>            Include components up to given z-order.\n";
    os << "                               e.g. -maxz 0.7 includes layers core0.\n";
    os << "     -dryrun                  Doesn produce output files.\n";
    os << "     -v <flood_level>         Flood cout (0:none, 1:a bit, >1 more).\n";
    os << "     -review                  Review all kickoff vertexes.\n";
    os << '\n';
    os << "Dev commands:\n";
    os << "  * layers                   Show layers \n";
    os << "  * mk_vertex                Vertex crafting tool.\n";
}

float read_state() {
    float f = numeric_limits<float>::max();
    ifstream is("state");
    if (!is.good()) {
        return f;
        //r = "KO 55009 file state not found. I don know which featureset to delete.";
    }
    is >> f;
    return f;
}

ko mk_vertex_main(int p, int argc, char** argv) {
    return "KO 89989 not implemented.";
}

int main(int argc, char** argv) {
    using mim::cout;
    if (argc < 2) {
        help(cerr);
        cerr << "KO 60599 Num arguments.\n";
        return 1;
    }
    auto verbose_rdbuf = std::cout.rdbuf();
    ostream nullos(0);
    std::cout.rdbuf(nullos.rdbuf());
    float maxz = read_state();
    string outputdir = "../../";
    ko r = ok;
    int p = 0;
    while(true) {
        ++p;
        string cmd = argv[p];
        if (cmd == "-maxz") {
            ++p;
            if (p == argc) {
                r = "KO 88019 no args left.";
                break;
            }
            string f = argv[p];
            istringstream is(f);
            is >> maxz;
            continue;
        }
        if (cmd == "-dryrun") {
            maze.set_dryrun();
            continue;
        }
        if (cmd == "-review") {
            maze.set_review();
            continue;
        }
        if (cmd == "-v") {
            ++p;
            if (p == argc) {
                r = "KO 88019 no args left.";
                break;
            }
            string f = argv[p];
            istringstream is(f);
            int level;
            is >> level;
            if (level == 0) {
                std::cout.rdbuf(nullos.rdbuf());
            }
            else {
                std::cout.rdbuf(verbose_rdbuf);
            }
            continue;
        }
        else if (cmd == "layers") {
            maze.list_layers(maxz, std::cout);
            break;
        }
        else if (cmd == "mk_vertex") {
            r = mk_vertex_main(p, argc, argv);
            break;
        }
        
        else if (cmd == "configure") {
            while(true) {
                ++p;
                if (p == argc) {
                    r = "KO 88009 no args left.";
                    break;
                }
                string cfg = argv[p];
                if (cfg == "dev") {
                    cout << "configure for dev (symlinks to MIM files) Core0: Maxz: " << maxz << '\n';
                    maze.init(maxz, outputdir); //must end with /
                    maze.features.mode = engine_t::mode_dev;
                    maze.features.dev.gitignore = true;
                    r = maze.configure();
                    {
                        ofstream os("state");
                        os << maxz;
                    }
                }
                else if (cfg == "cbs") {
                    cout << "configure for cbs (copy MIM files) Core0: Maxz: " << maxz << '\n';
                    maze.init(maxz, outputdir); //must end with /
                    maze.features.mode = engine_t::mode_cbs;
                    r = maze.configure();
                    {
                        ofstream os("state");
                        os << maxz;
                    }
                }
                else {
                    r = "KO 78869 ";
                    break;
                }
                break;
            }
            break;
        }
        else if (cmd == "clean_conf") {
            if (maxz > 1e16) {
                cerr << "KO 68577 file 'state' does not exist. Specify maxz manually. See makefile.\n";
                abort();
            }
            std::cout << "cleaning configuration z: " << maxz << '\n';
            maze.init(maxz, outputdir); //must end with /
            r = maze.clean_conf();
            system("rm state");
            break;
        }
        else {
            r = "KO 78654 ";
            break;
        }
    }
    maze.delete_instance();
    if (is_ko(r)) {
        help(cerr);
        cerr << r << '\n';
        return 1;
    }
    return 0;
}

