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
#include "conf/mim.h"
//#include "apitypes/mim.h"

namespace mim::core0::core0_6::core0_61 {
namespace us::gui::activity::listconf {

struct vertex_t: mim::template_vertex_t {
    using b = mim::template_vertex_t;

    vertex_t(): b("listconf", __FILE__, "us::gui::activity::listconf") {
        codefiles.add_java();
        add(new conf::vertex_t());
//        add(new apitypes::vertex_t());
    }

    void customize_produced_vertex(namespace_t& instance) const override {
/*
        auto v = instance.params__get("api__function").second;
        if (v.empty()) {
            assert(instance.params__get("api__datatype").second.empty());
            assert(instance.params__get("api__itemtype").second.empty());
            //assert(!params_srcs.get("datatype").empty());
            //assert(!params_srcs.get("itemtype").empty());
            cout << "removing apitypes\n";
            instance.remove_child("apitypes");
        }
        else {
            assert(!instance.params__get("api__datatype").second.empty());
            assert(!instance.params__get("api__itemtype").second.empty());
            {
            lang_codefiles_t* lc = instance.codefiles.find_lang_codefiles("java", "scr");
            assert(lc != nullptr);
            lc->includes.emplace("us.cash." + instance.codefiles.classname + "__itemtype_t");
            }
        }
*/

        auto conf = dynamic_cast<namespace_t*>(instance.find_child("conf")); {
            auto make_itemconf = instance.params__get("make_itemconf").second;
            if (make_itemconf == "no") {
                instance.remove_child("item");
            }
            else {
                auto item = dynamic_cast<namespace_t*>(conf->find_child("item"));
                auto item_name__append_to_parent = instance.params__get("item_name__append_to_parent").second;
                if (item_name__append_to_parent != "yes") {
                    item->codefiles.classname_alg = codefiles_t::vertex_name;
                }
                {
                    auto item__conf = dynamic_cast<namespace_t*>(item->find_child("conf"));
                    auto cp = instance.template_instance_vertex->custom_params.find("item__conf");
                    if (cp != instance.template_instance_vertex->custom_params.end()) {
                        item__conf->params.add(cp->second);
                    }
                    auto v = item__conf->params__get("api__function").second;
                    if (!v.empty()) {
                        assert(!instance.params__get("api__datatype").second.empty());
                        auto apiitemtype = instance.params__get("api__itemtype").second;
                        item__conf->params.underride__empty("api__datatype", apiitemtype);
                    }
                    else {
                        assert(item__conf->params__get("api__datatype").second.empty());
                        //assert(item__conf->params__get("api__itemtype").second.empty());
                        //assert(!params_srcs.get("datatype").empty());
                        //assert(!params_srcs.get("itemtype").empty());
                        auto itemtype = instance.params__get("itemtype").second;
                        item__conf->params.underride__empty("datatype", itemtype);
                    }
                }
                auto rename = instance.params__get("item_name").second;
                if (rename != "item") {
                    item->detach();
                    item->name = rename;
                    auto n2 = conf->find_child(rename);
                    if (n2 != nullptr) {
                        n2->detach();
                        item->merge(n2, 1);
                    }
                    conf->add_(*item, 1);
                }
            }
        }
        //instance.dump(cerr);
        //abort();
      }

};

}}
