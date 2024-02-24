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
#include "menu__main/mim.h"

namespace mim::core0::core0_6::core0_61 {
namespace us::gui::app {

    struct vertex_t: layered_class_vertex_t {
        using b = layered_class_vertex_t;

        vertex_t(): b("app", "00", __FILE__) {

            deepest_baseclass = "Application";
            subhome = "";
            codefiles.add_java();
            codefiles.classname_alg = codefiles_t::vertex_name;
            add(new menu__main::vertex_t());
        }

        vertex_t(const vertex_t& other): b(other) {}
        vertex_t* clone() const override { return new vertex_t(*this); }

        token_resolution_t resolve_token(const string& token) const override {
            auto r = b::resolve_token(token);
            if (r.solved()) return r;
            if (token == "menu_classnames") {
                using menuspecs_t = vector<pair<const menuspec_t*, const menu_vertex_t*>>;
                vector<const menu_vertex_t*> m;
                auto r = root_vertex();
                assert(r != nullptr);
                r->collect_all_menus(m);
                menuspecs_t specs;
                for (auto& i: m) {
                    for (auto& j: i->menus) {
                        specs.push_back(make_pair(&j.second, i));
                    }
                }
                set<string> names;
                for (auto& s: specs) {
                    names.emplace(s.second->spec_classname(*s.first));
                }
                return token_resolution_t(move(names), mim_file);
            }
            return token_resolutions::unsolved;
        }

    };
}}

