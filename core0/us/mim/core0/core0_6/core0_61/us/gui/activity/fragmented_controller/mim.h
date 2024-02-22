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
#include "base/mim.h"
//#include "impl_/mim.h"
//#include "list/mim.h"

namespace mim::core0::core0_6::core0_61 {
namespace us::gui::activity::fragmented_controller {

    struct vertex_t: mim::template_vertex_t {
        using b = mim::template_vertex_t;

        vertex_t(): b("fragmented_controller", __FILE__, "us::gui::activity::fragmented_controller") {
            add(new base::vertex_t());
//            add(new impl_::vertex_t());
            codefiles.add_java();
        }

        using fragment_info_t = pair<const namespace_t*, const params_t*>;
        using fragment_infos_t = vector<fragment_info_t>;
        using split_fragment_infos_t = pair<fragment_infos_t, fragment_infos_t>;

        string on_tab_reselected__fn__java(const split_fragment_infos_t& fragments) const {
            if (fragments.second.empty()) return "";
            ostringstream os;
            os <<
R"#(        if (pos < num_static_tabs) return;
        popup_closetab();
)#";
            return os.str();
        }
             
        string get_fragment__java(const split_fragment_infos_t& fragments) const {
            ostringstream os;
            os << "    "
R"#(fragment get_fragment(int pos) {
        log("get_fragment " + pos); //--strip
        if (onlytab != -1) {
            assert pos == 0;
            switch(onlytab) {
)#";
            {
                int n{0};
                for (auto& i: fragments.first) {
                    auto& p = *i.second;
                    auto static__constructor_args = p.get("static__constructor_args").second;
                    os <<
R"#(                case )#" << n++ << ": return new " << i.first->codefiles.classname << '(' << static__constructor_args << R"#();
)#";
                }
            }
            os <<
R"#(                default: return null;
            }
        }
        if (pos < num_static_tabs) {
            switch(pos) {
)#";
            {
                int n{0};
                for (auto& i: fragments.first) {
                    auto& p = *i.second;
                    auto static__constructor_args = p.get("static__constructor_args").second;
                    os <<
R"#(                case )#" << n++ << ": return new " << i.first->codefiles.classname << '(' << static__constructor_args << R"#();
)#";
                }
            }
            os <<
R"#(                default: return null;
            }
        }
)#";
            if (!fragments.second.empty()) {
                int n{0};
                for (auto& i: fragments.second) {
                    os <<
R"#(        pos -= num_static_tabs;
        if (pos < dyn_tabs_)#" << n << R"#(.size()) {
            return new )#" << i.first->codefiles.classname << R"#((dyn_tabs_)#" << n << R"#(.get(pos));
        }
)#";
                    if (n < fragments.second.size() - 1) {
                        os <<
R"#(        pos -= dyn_tabs_)#" << n << R"#(.size();
)#";
                    }
                    ++n;
                }
            }
            os <<
R"#(        return null;
    }
)#";

            return os.str();
        }


        string from_intent__java() const {
            ostringstream os;
            os <<
        "    void from_intent(Intent i) {\n" \
        "        if (i == null) {\n" \
        "            log(\"from_intent null intent. \" + curtab + \" \" + mode); //--strip\n" \
        "            return;\n" \
        "        }\n" \
        "        Bundle bundle = i.getExtras();\n" \
        "        if (i.hasExtra(\"mode\")) {\n" \
        "            mode = bundle.getInt(\"mode\", 0);\n" \
        "        }\n" \
        "        if (i.hasExtra(\"mode1_header\")) {\n" \
        "            mode1_header = bundle.getString(\"mode1_header\", \"Select item...\");\n" \
        "        }\n" \
        "        if (i.hasExtra(\"curtab\")) {\n" \
        "            curtab = bundle.getInt(\"curtab\", 0);\n" \
        "        }\n" \
        "        if (i.hasExtra(\"onlytab\")) {\n" \
        "            onlytab = bundle.getInt(\"onlytab\", -1);\n" \
        "            curtab = 0;\n" \
        "        }\n" \
        "        log(\"from_intent \" + curtab + \" \" + mode); //--strip\n" \
        "    }\n";
            return os.str();
        }

        pair<string, string> get_tab_strings(const fragment_info_t& fi) const {
            auto title = fi.second->get("tabtitle").second;
            if (title.empty()) {
                title = fi.first->params__get("title").second;
                if (title.empty()) {
                    ostringstream os;
                    os << "Tab";
                    title = os.str();
                }
            }
            auto tabico = fi.second->get("tabico").second;
            if (tabico.empty()) {
                tabico = fi.first->params__get("tabico").second;
                if (tabico.empty()) {
                    tabico = "R.raw.gear";
                }
            }
            return make_pair(title, tabico);
        }

        string get_tab_args(const fragment_info_t& fi) const {
            auto ts = get_tab_strings(fi);
            ostringstream args;
            args << "\"" << ts.first << "\", " << ts.second; 
            return args.str();
        }
        
        string add_tabs__java(const split_fragment_infos_t& fragments) const {
            ostringstream os;
            os << "    "
R"#(void add_tabs() {
        log("add_tabs"); //--strip
        if (onlytab != -1) {
            switch(onlytab) {)#";
            {
                int n{0};
                for (auto& i: fragments.first) {
                    os << R"#(
                case )#" << n++ << R"#(:
                    add_tab(0, )#" << get_tab_args(i) << R"#();
                    break;)#";
                }
            }
            os << R"#(
            }
            return;
        }
)#";
            {
                int n{0};
                for (auto& i: fragments.first) {
                    os <<
"        add_tab(" << n++ << ", " << get_tab_args(i) << ");\n";
                }
            }
            {
                int n{0};
                for (auto& i: fragments.second) {
                    os <<
"        add_dyn_" << n << "_tabs();\n";
                }
            }
            os <<
"    }\n";
            int n{0};
            for (auto& i: fragments.second) {
                auto& p = *i.second;
                auto dyntab_nft_type = p.get("dyntab_nft_type").second;
                auto dyntab_nft__to_string_fn = p.get("dyntab_nft__to_string_fn").second;
                auto dyntab_nft__transform_fn = p.get("dyntab_nft__transform_fn").second;
                auto ts = get_tab_strings(i);
                ostringstream args;
                if (!dyntab_nft__transform_fn.empty()) {
                    args << dyntab_nft__transform_fn << "(i" << dyntab_nft__to_string_fn << "), " << ts.second; 
                }
                else {
                    args << "i" << dyntab_nft__to_string_fn << ", " << ts.second; 
                }
                os << R"#(
    void add_dyn_)#" << n << R"#(_tabs() {
        log("add_dyn_)#" << n << R"#(_tabs"); //--strip
        int pos = num_static_tabs;)#";
                for (int j = 0; j < n; ++j) {
                    os << R"#(
        pos += dyn_tabs_)#" << j << ".size();";
                }
                os << R"#(
        for ()#" << dyntab_nft_type << R"#( i: dyn_tabs_)#" << n << R"#() {
            add_tab(pos++, )#" << args.str() << R"#();
        }
    }
)#";
                ++n;
                
            }

            return os.str();
        }

        void customize_produced_vertex(namespace_t& instance) const override {
        }

        fragment_infos_t get_fragment_vertexes(const namespace_t& instance) const {
            fragment_infos_t fragments;
            for (auto& i: instance.template_instance_vertex->custom_params) {
                if (i.first.rfind("fragment", 0) != 0) continue;
                auto v = i.second.get("vertex_path");
                if (!v.first) {
                    cerr << "KO 78699 missing param vertex_path for entry " << i.first << '\n';
                    abort();
                }
                auto x = instance.find_vertex2(v.second);
                if (x == nullptr) {
                    cerr << "KO 78698 missing vertex " << v.second << '\n';
                    abort();
                }
                fragments.emplace_back(make_pair(dynamic_cast<const namespace_t*>(x), &i.second));
            }
            return fragments;
        }

        string nft_support__select__java(const string& nft_type) const {
            ostringstream os;
            os <<
        "    public void select(" << nft_type << " nft) {\n" \
        "        Intent data = new Intent();\n" \
        "        data.putExtra(\"nft\", nft);\n" \
        "        setResult(RESULT_OK, data);\n" \
        "        finish();\n" \
        "    }\n";
            return os.str();
        }

        split_fragment_infos_t split_fragment_infos(const fragment_infos_t& fragments) const {
            split_fragment_infos_t o;
            for (auto f: fragments) {
                auto& p = *f.second;
                auto tabtype = p.get("tabtype").second;
                assert(!tabtype.empty());
                if (tabtype == "static") {
                    o.first.push_back(f);
                }
                else if (tabtype == "dynamic") {
                    o.second.push_back(f);
                }
                else {
                    assert(false);
                    cerr << "KO 88947" << '\n';
                    abort();
                }
            }
            return o;
        }
   
        void dyntab_fun__addtab__java(const split_fragment_infos_t& fragments, ostream& os) const {
            //auto num_static = fragments.first.size();
            int i{0};
            for (auto f: fragments.second) {
                auto& p = *f.second;
                auto dyntab_nft_type = p.get("dyntab_nft_type").second;
                auto dyntab_nft__to_string_fn = p.get("dyntab_nft__to_string_fn").second;
                auto dyntab_nft__transform_fn = p.get("dyntab_nft__transform_fn").second;

                os << "    "
R"#(public void add_dyn_tab_)#" << i << "(" << dyntab_nft_type << R"#( o) {
        log("add_dyn_tab_)#" << i << R"#( " + o)#" << dyntab_nft__to_string_fn << R"#(); //--strip
        int p = -1;
        {
            int n = 0;
            for ()#" << dyntab_nft_type << " i: dyn_tabs_" << i << R"#() {
                if (i.equals(o)) {
                    p = n;
                }
                ++n;
            }
        }
        tabs_listener_disabled = true;
        int num_prev = num_static_tabs;
        if (p == -1) {
            dyn_tabs_)#" << i << R"#(.add(o);)#";
                for (int j = 0; j < i; ++j) {
                    os << "num_prev += dyn_tabs_" << j << ".size();";
                }
                auto strs = get_tab_strings(f);
                os << R"#(
            curtab = num_prev + dyn_tabs_)#" << i << R"#(.size() - 1;
            fragments.add(curtab, null);)#";
                if (!dyntab_nft__transform_fn.empty()) {
                    os << R"#(
            o = )#" << dyntab_nft__transform_fn << R"#((o);)#";
                }
                os << R"#(
            add_tab(curtab, o)#" << dyntab_nft__to_string_fn << ", " << strs.second << R"#();
        }
        else {)#";
            for (int j = 0; j < i; ++j) {
                os << R"#(
            num_prev += dyn_tabs_" << j << ".size();
)#";
            }
            os << R"#(
            curtab = num_prev + p;
        }
        w.tabs.getTabAt(curtab).select();
        tabs_listener_disabled = false;
        on_tab_selected(curtab);
    }
)#";
                ++i;
            }
        }
        
        void dyntab_fun__closetab__java(const split_fragment_infos_t& fragments, ostream& os) const {
            //auto num_static = fragments.first.size();
            os <<
        "    void close_tab() {\n" \
        "        if (curtab < num_static_tabs) return;\n";
            if (fragments.second.size() > 1) {
                os <<
        "        int relcurtab = curtab - num_static_tabs;\n" \
        "        while(true) {\n";
                auto i = 0;
                for (auto f: fragments.second) {
                    os <<
        "            if (dyn_tabs_" << i << ".remove2(relcurtab)) break;\n" \
        "            relcurtab -= dyn_tabs_" << i << ".size();\n";
                    ++i;
                }
                os <<
        "            break;\n" \
        "        }\n";
            }
            else if (fragments.second.size() == 1) {
                os <<
        "        dyn_tabs_0.remove2(curtab - num_static_tabs);\n";
            }
            os <<
        "        fragments.remove(curtab);\n" \
        "        --curtab;\n" \
        "        w.tabs.removeTabAt(curtab + 1);\n" \
        "    }\n";
        }

        void dyntab_fun__popup_closetab__java(const split_fragment_infos_t& fragments, ostream& os) const {
            os <<
        "    void popup_closetab() {\n" \
        "        String[] options = new String[]{\"Close tab\", a.getResources().getString(R.string.cancel)};\n" \
        "        AlertDialog.Builder dlg = a.new_dlg_builder(get_context());\n" \
        "        dlg.setTitle(\"Tab\").setItems(options, new DialogInterface.OnClickListener() {\n" \
        "            @Override public void onClick(DialogInterface dialog, int which) {\n" \
        "                switch (which) {\n" \
        "                    case 0:\n" \
        "                        close_tab();\n" \
        "                        break;\n" \
        "                }\n" \
        "            }\n" \
        "        }).setIcon(R.raw.gear).show();\n" \
        "    }\n";
        }

        string dyntab_fun__java(const split_fragment_infos_t& fragments) const {
            if (fragments.second.empty()) return "";
            ostringstream os;
            dyntab_fun__addtab__java(fragments, os);
            os << '\n';
            dyntab_fun__popup_closetab__java(fragments, os);
            os << '\n';
            dyntab_fun__closetab__java(fragments, os);
            return os.str();
        }

        string dyntab_decl__java(const split_fragment_infos_t& fragments) const {
            auto num_static = fragments.first.size();
            ostringstream os;
            os <<
R"#(    public final int num_static_tabs = )#" << num_static << R"#(;

)#";
            if (fragments.second.empty()) return os.str();
            os <<
R"#(    public static class dyntabs_t<T> extends ArrayList<T> {
    
        public boolean remove2(int i) {
            if (i >= size()) return false;
            super.remove(i);
            return true;
        }

    }

)#";
            auto i = 0;
            for (auto f: fragments.second) {
                auto& p = *f.second;
                auto dyntab_nft_type = p.get("dyntab_nft_type").second;
                assert(!dyntab_nft_type.empty());
                os <<
R"#(    public dyntabs_t<)#" << dyntab_nft_type << "> dyn_tabs_" << i++ << " = new dyntabs_t<" << dyntab_nft_type << R"#(>();
)#";
            }
            return os.str();
        }

        token_resolution_t resolve_token(const namespace_t& instance, const string& token) const override {
            if (token == "get_fragment" || token == "add_tabs" || token == "dyntab_decl" || token=="dyntab_fun" || token == "on_tab_reselected__fn") {
                auto fragments = get_fragment_vertexes(instance);
                if (fragments.empty()) {
                    cerr << "KO 61143 At least one fragment child vertex must be defined\n";
                    abort();
                }
                auto sf = split_fragment_infos(fragments);
                if (token == "get_fragment") {
                    return token_resolution_t(1, get_fragment__java(sf), mim_file);
                }
                if (token == "add_tabs") {
                    return token_resolution_t(1, add_tabs__java(sf), mim_file);
                }
                if (token == "dyntab_fun") {
                    return token_resolution_t(1, dyntab_fun__java(sf), mim_file);
                }
                if (token == "dyntab_decl") {
                    return token_resolution_t(1, dyntab_decl__java(sf), mim_file);
                }
                if (token == "on_tab_reselected__fn") {
                    return token_resolution_t(1, on_tab_reselected__fn__java(sf), mim_file);
                }
            }
            if (token == "from_intent") {
                return token_resolution_t(1, from_intent__java(), mim_file);
            }
            static set<string> nft_support_tokens{
                "nft_support__select",
                };
            if (nft_support_tokens.find(token) != nft_support_tokens.end()) {
                string nft_support = instance.params__get("nft_support").second;
                if (nft_support != "yes") {
                    return token_resolution_t(1, "", mim_file);
                }
                string nft_type = instance.params__get("nft_type").second;
                if (nft_type.empty()) {
                    nft_type = "String";
                }
                if (token == "nft_support__select") {
                    return token_resolution_t(1, nft_support__select__java(nft_type), mim_file);
                }
            }
           
            return token_resolutions::unsolved;
        }

    };

}}

