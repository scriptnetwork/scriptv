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
#include "apitypes/mim.h"
#include "menu__main/mim.h"
//import <ranges>

namespace mim::core0::core0_6::core0_61 {
namespace us::gui::activity::controller {

struct vertex_t: mim::template_vertex_t {
    using b = mim::template_vertex_t;

    vertex_t(): b("controller", __FILE__, "us::gui::activity::controller") {
        codefiles.add_java();
        add(new menu__main::vertex_t());
        add(new apitypes::vertex_t());
    }

    void abrt(const namespace_t& instance, const string& e) const {
        ind_t ind(cerr, "KO 89799> ");
        instance.dump(cerr);
        cerr << "KO 89799 " << e << '\n';
        abort();
    }

    void customize_produced_vertex(namespace_t& instance) const override {
        {
            auto x = instance.params__get("controller").second;
            if (x.empty()) {
                abrt(instance, "param controller required");
            }
        }
        instance.codefiles.compute_classname();

        {
            auto x = instance.params__get("with_menu").second;
            if (x != "true" && x != "false") {
                    abrt(instance, "param with_menu required true or false");
            }
            if (x != "true") {
                instance.find_child("menu__main")->detach();
            }
        }
        auto apitypes = dynamic_cast<namespace_t*>(instance.find_child("apitypes"));
        assert(apitypes != nullptr);
        {
            bool use_apitypes{false};
            string apimethod = apitypes->params__get("api__method").second;
            if (apimethod.empty()) {
                auto v = apitypes->params__get("api__function").second;
                if (!v.empty()) {
                    assert(!apitypes->params__get("api__datatype").second.empty());
                    instance.codefiles.compute_classname();
                    auto datatype0 = instance.codefiles.classname + "__datatype_t";
                    auto datatype = apitypes->params__get("datatype").second;
                    if (datatype.empty() || datatype == datatype0) {
                        use_apitypes = true;
                        instance.params.underride__empty("datatype", datatype0);
                    }
                }
                else {
                    assert(apitypes->params__get("api__datatype").second.empty());
                    //assert(!apitypes->params__get("datatype").second.empty());
                }
            }
            else {
                assert(apimethod == "hmi");
                instance.codefiles.compute_classname();
                auto datatype0 = instance.codefiles.classname + "__datatype_t";
                auto datatype = apitypes->params__get("datatype").second;
                if (datatype.empty() || datatype == datatype0) {
                    use_apitypes = true;
                    instance.params.underride__empty("datatype", datatype0);
                }
            }
            if (!use_apitypes) {
                cout << "removing apitypes\n";
                instance.remove_child("apitypes");
            }
        }
    }

    string load__hmi__java(const namespace_t& instance) const {
        string hmicmd = instance.params__get("hmi__command").second;
        string pushcode = instance.params__get("hmi__push_code").second;
        string datatype = instance.params__get("datatype").second;
        assert(!hmicmd.empty());
        assert(!pushcode.empty());
        assert(!datatype.empty());
        assert(instance.params__get("api__function").second.empty());
        //assert(instance.params__get("api__datatype").second.empty());

        auto data_identifier = instance.params__get("data_identifier").second;
        if (data_identifier.empty()) data_identifier = "data";
        ostringstream os;
        os <<
"    @Override public void load() {\n" \
"        log(\"load\"); //--strip\n" \
"        a.assert_ui_thread(); //--strip\n" \
"        a.hmi().command_trade(tr.tid, \"" << hmicmd << "\");\n" \
"    }\n";


        return os.str();
    }

    string load__worker__java(const namespace_t& instance) const {
        string apimethod = instance.params__get("api__method").second;
        if (apimethod == "hmi") {
            return load__hmi__java(instance);
        }
        assert(apimethod.empty());
        auto datatype = instance.params__get("datatype").second;
        bool check_backend{false};
        string apidatatype = instance.params__get("api__datatype").second;
        if (!apidatatype.empty()) {
            check_backend = true;
        }
        auto data_identifier = instance.params__get("data_identifier").second;
        if (data_identifier.empty()) data_identifier = "data";
        ostringstream os;
        os <<
    "    @Override public ko load__worker() {\n" \
    "        log(\"load__worker\"); //--strip\n" \
    "        a.assert_worker_thread(); //--strip\n";
        if (!datatype.empty()) {
            if (check_backend) {
                os <<
    "        {\n" \
    "            ko r = a.backend_ready();\n" \
    "            if (is_ko(r)) {\n" \
    "                " << data_identifier << " = null;\n" \
    "                return r;\n" \
    "            }\n" \
    "        }\n";
            }
            os <<
    "        pair<ko, " << datatype << "> r = fetch_" << data_identifier << "();\n" \
    "        if (is_ko(r.first)) {\n" \
    "            " << data_identifier << " = null;\n" \
    "            return r.first;\n" \
    "        }\n" \
    "        " << data_identifier << " = r.second;\n" \
    "        assert " << data_identifier << " != null; //--strip\n";
        }
        os <<
    "        return ok;\n" \
    "    }\n";
        return os.str();
    }

    string datatype_decl__java(const namespace_t& instance) const {
        auto datatype = instance.params__get("datatype").second;
        if (datatype.empty()) return "";
        auto data_identifier = instance.params__get("data_identifier").second;
        if (data_identifier.empty()) data_identifier = "data";
        ostringstream os;
        os <<
    "    protected ##datatype## " << data_identifier << " = null;\n";
        return os.str();
    }

    string code_fetch_data__local(const namespace_t& instance, bool isabstract) const {
        string datatype = instance.params__get("datatype").second;
        if (datatype.empty()) {
            return "";
        }
        auto data_identifier = instance.params__get("data_identifier").second;
        if (data_identifier.empty()) data_identifier = "data";
        ostringstream os;
        if (isabstract) {
            os <<
    "    public abstract pair<ko, " << datatype << "> fetch_" << data_identifier << "();\n";
        }
        else {
            os <<
    "    public pair<ko, " << datatype << "> fetch_" << data_identifier << "() {\n" \
    "        log(\"fetch_" << data_identifier << "\"); //--strip\n" \
    "        Intent i = getIntent();\n" \
    "        if (i == null) {\n" \
    "            ko r = new ko(\"KO 20199 Intent\");\n" \
    "            log(r.msg); //--strip\n" \
    "            return new pair(r, null);\n" \
    "        }\n" \
    "        if (!i.hasExtra(\"object_id\")) {\n" \
    "            ko r = new ko(\"KO 77369 Missing object id\");\n" \
    "            log(r.msg); //--strip\n" \
    "            return new pair(r, null);\n" \
    "        }\n" \
    "        Integer id = i.getExtras().getInt(\"object_id\", -1);\n" \
    "        log(\"object_id \" + id); //--strip\n" \
    "        " << datatype << " o = (" << datatype << ") a.mem_get_object(id);\n" \
    "        if (o == null) {\n" \
    "            ko r = new ko(\"KO 77829 Invalid object_id\");\n" \
    "            log(r.msg); //--strip\n" \
    "            return new pair(r, null);\n" \
    "        }\n" \
    "        return new pair(ok, o);\n" \
    "    }\n";
        }
        return os.str();
    }

    string code_fetch_data__api(const namespace_t& instance, const string& apifunction, bool isabstract) const {
        string datatype = instance.params__get("datatype").second;
        string apidatatype = instance.params__get("api__datatype").second;
        if (apidatatype.empty()) {
            apidatatype = datatype;
        }
        string retobj;
        if (apidatatype != datatype) {
            ostringstream os;
            os << "new " << datatype << "(o)";
            retobj = os.str();
        }
        else {
            retobj = "o";
        }
        auto data_identifier = instance.params__get("data_identifier").second;
        if (data_identifier.empty()) data_identifier = "data";
        ostringstream os;
        os <<
"    public pair<ko, " << datatype << "> fetch_" << data_identifier << "() {\n" \
"        log(\"fetch_" << data_identifier << "\"); //--strip\n" \
"        a.assert_worker_thread(); //--strip\n" \
"        " << apidatatype << " o = new " << apidatatype << "();\n" \
"        ko r = a.hmi().rpc_peer.call_" << apifunction << "(o);\n" \
"        if (is_ko(r)) {\n" \
"            return new pair<ko, " << datatype << ">(r, null);\n" \
"        }\n" \
"        return new pair<ko, " << datatype << ">(ok, " << retobj << ");\n" \
"    }\n";
        return os.str();
    }

    using menuspecs_t = vector<pair<const menuspec_t*, const menu_vertex_t*>>;

    string get_menu_override__java(const menuspecs_t& s) const {
        if (s.empty()) return "";
        ostringstream os;
        os <<
    "    @Override protected us.cash.scr.menu_t get_menu() {\n" \
    "        if (get_menu_depth() < 1) return super.get_menu();\n" \
    "        assert get_menu_depth() == 1; //--strip\n";
        if (s.size() > 1) {
                os <<
    "        // TODO: decide which instance must be returned\n";
        }
        int n{0};
        for (auto& i: s) {
            if (n == 0) {
                os <<
    "        return us.cash.scr." << i.second->spec_classname(*i.first) << ".get_instance(get_context());\n";
            }
            else {
                os <<
    "        // Alternative instance:\n" \
    "        // return us.cash.scr." << i.second->spec_classname(*i.first) << ".get_instance(get_context());\n";
            }
        }
        if (s.empty()) {
            os <<
    "        return null;\n";
        }
        os <<
    "    }\n";
        return os.str();
    }


    string on_menu__handling__java(const menuspecs_t& s, bool is_abstract) const {
        map<string, pair<string, itemspec_t>> items;
        for (auto& i: s) {
            for (auto& j: *i.first) {
                if (j.name == "nav") continue;
                for (auto& m: j) {
                    auto x = items.find(m.id);
                    if (x == items.end()) {
                        items.emplace(m.id, make_pair(i.first->name_, m));
                    }
                    else {
                        x->second.first += "_" + i.first->name_;
                    }
                }
            }
        }
        if (items.empty()) {
            return "";
        }
        ostringstream os;
        if (is_abstract) {
            for (auto& i: items) {
                os <<
    "    protected abstract void on_menu_" << i.second.first << "__" << i.second.second.relid() << "(); //" << i.second.second.title << "\n";
            }
        }
        else {
            for (auto& i: items) {
                os <<
    "    protected void on_menu_" << i.second.first << "__" << i.second.second.relid() << "() { //" << i.second.second.title << "\n" \
    "        log(\"on_menu_" << i.second.first << "__" << i.second.second.relid() << "\"); //--strip\n" \
    "        //TODO: handle menu here\n" \
    "    }\n" \
    "\n";
            }
        }
        os << '\n';
        os <<
    "    @Override public boolean on_menu(int id) {\n" \
    "        log(\"on_menu\"); //--strip\n";
        int n{0};
        for (auto& i: items) {
        os <<
    "        ";
            if (n == 0) {
                n = 1;
            }
            else {
        os << "else ";
            }
        os << "if (id == " << i.second.second.id << ") { //" << i.second.second.title << "\n" \
    "            new Handler().postDelayed(new Runnable() {\n" \
    "                    @Override public void run() {\n" \
    "                        on_menu_" << i.second.first << "__" << i.second.second.relid() << "();\n" \
    "                    }\n" \
    "                }, 100);\n" \
    "        }\n";
        }
        os <<
    "        else {\n" \
    "            return super.on_menu(id);\n" \
    "        }\n" \
    "        return true;\n" \
    "    }\n";
        return os.str();
    }

    string create_tree__java(bool has_scroll) const {
        ostringstream os;
        os <<
    "    @Override public ViewGroup create_tree(Context ctx) {\n" \
    "        create_papyrus(ctx);\n" \
    "        assert papyrus != null; //--strip\n";
        if (has_scroll) {
            os <<
    "        scroll = new scroll_view_t(ctx);\n" \
    "        scroll.addView(papyrus);\n" \
    "        return scroll;\n";
        }
        else {
            os <<
    "        return papyrus;\n";
        }
        os <<
    "    }\n\n" \
    "    private void create_papyrus(Context ctx) {\n" \
    "        papyrus = new canvas_t(ctx, 10, 1);\n" \
    "        //TODO: add widgets here\n" \
    "    }\n" \
    "\n\n";
        if (has_scroll) {
            os <<
    "    public scroll_view_t scroll = null;\n";
        }
        os <<
    "    public canvas_t papyrus = null;\n";
        return os.str();
    }

    string menu_max_depth__java(const menuspecs_t& s) const {
        int max_depth = s.empty() ? 0 : 1;
        ostringstream os;
        os << max_depth;
        return os.str();
    }

    token_resolution_t resolve_token(const namespace_t& instance, const string& token) const override {
        bool isabstract = instance.params__get("is_abstract").second == "yes";
        if (token == "apifetch") {
            //bool apif{false};
            string apimethod = instance.params__get("api__method").second;
            if (apimethod.empty()) {
                string apifunction = instance.params__get("api__function").second;
                if (apifunction.empty()) {
                    return token_resolution_t(1, code_fetch_data__local(instance, isabstract), mim_file);
                }
                else {
                    return token_resolution_t(1, code_fetch_data__api(instance, apifunction, isabstract), mim_file);
                }
            }
            else {
                assert(apimethod == "hmi");
                return token_resolution_t(1, "", mim_file);
            }
        }
        static set<string> menutokens{
            "get_menu_override",
            "menu_max_depth",
            "on_menu__handling",
        };
        if (menutokens.find(token) != menutokens.end()) {
            menuspecs_t s; { 
                vector<const menu_vertex_t*> m;
                instance.collect_menus(m);
                for (auto& i: m) {
                    for (auto& j: i->menus) {
                        s.push_back(make_pair(&j.second, i));
                    }
                }
            }
            sort(s.begin(), s.end(), [](const auto& lhs, const auto& rhs) -> bool {
                return lhs.first->name_ < rhs.first->name_;
            });
            //ranges::sort([](const auto& lhs, const auto& rhs) -> bool {
            //    return lhs.first->name_ < rhs.first->name_;
            //});
            if (token == "get_menu_override") {
                return token_resolution_t(1, get_menu_override__java(s), mim_file);
            }
            if (token == "menu_max_depth") {
                return token_resolution_t(0, menu_max_depth__java(s), mim_file);
            }
            if (token == "on_menu__handling") {
                return token_resolution_t(1, on_menu__handling__java(s, isabstract), mim_file);
            }
        }
        if (token == "datatype_decl") {
                return token_resolution_t(1, datatype_decl__java(instance), mim_file);
        }
        if (token == "load__worker") {
                return token_resolution_t(1, load__worker__java(instance), mim_file);
        }
        if (token == "class_qualifier") {
            string isabstract = instance.params__get("is_abstract").second;
            string value;
            if (isabstract == "yes") {
                value = "abstract ";
            }
            return token_resolution_t(0, value, mim_file);
        }
        if (token == "create_tree") {
            bool has_scroll = instance.params__get("scroll").second == "yes";
            return token_resolution_t(1, create_tree__java(has_scroll), mim_file);
        }
        return token_resolutions::unsolved;
    }

};

}}

