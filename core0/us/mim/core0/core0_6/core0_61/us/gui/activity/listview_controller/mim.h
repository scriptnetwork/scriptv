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
#include "list_view/mim.h"

namespace mim::core0::core0_6::core0_61 {
namespace us::gui::activity::listview_controller {

    struct vertex_t: mim::template_vertex_t {
        using b = mim::template_vertex_t;

        vertex_t(): b("listview_controller", __FILE__, "us::gui::activity::listview_controller") {
            add(new base::vertex_t());
            add(new list_view::vertex_t());
            codefiles.add_java();
        }

        void check_param__yes_no(namespace_t& instance, const string& param) const {
            auto p = instance.params__get(param).second;
            if (p == "yes") return;
            if (p == "no") return;
            cerr << "In " << instance.vertex_dir() << '\n';
            cerr << "KO 34453 param '" << param << "' should be either 'yes' or 'no'.\n";
            assert(false);
            abort();
        }

        void check_param__not_empty(namespace_t& instance, const string& param) const {
            auto p = instance.params__get(param).second;
            if (!p.empty()) return;
            cerr << "In " << instance.vertex_dir() << '\n';
            cerr << "KO 34454 param '" << param << "' should be not empty.\n";
            assert(false);
            abort();
        }

        void customize_controller(namespace_t& instance, namespace_t& base) const {
            auto apitypes = dynamic_cast<namespace_t*>(base.find_child("apitypes"));
            assert(apitypes != nullptr);
            {
                bool use_apitypes{false};

                string apimethod = apitypes->params__get("api__method").second;
                if (apimethod.empty()) {
                    auto v = apitypes->params__get("api__function").second;
                    if (!v.empty()) {
                        assert(!apitypes->params__get("api__datatype").second.empty());
                        assert(!apitypes->params__get("api__itemtype").second.empty());
                        base.codefiles.compute_classname();
                        auto datatype0 = base.codefiles.classname + "__datatype_t";
                        auto datatype = apitypes->params__get("datatype").second;
                        if (datatype.empty() || datatype == datatype0) {
                            use_apitypes = true;
                            instance.params.underride__empty("datatype", base.codefiles.classname + "__datatype_t");
                        }
                        auto itemtype0 = base.codefiles.classname + "__itemtype_t";
                        auto itemtype = apitypes->params__get("itemtype").second;
                        if (itemtype.empty() || itemtype == itemtype0) {
                            use_apitypes = true;
                            instance.params.underride__empty("itemtype", base.codefiles.classname + "__itemtype_t");
                        }
                    }
                    else {
                        assert(apitypes->params__get("api__datatype").second.empty());
                        assert(apitypes->params__get("api__itemtype").second.empty());
                        //assert(!apitypes->params__get("datatype").second.empty());
                        //assert(!apitypes->params__get("itemtype").second.empty());
                    }
                }
                else {
                    assert(apimethod == "hmi");
                    base.codefiles.compute_classname();
                    auto datatype0 = base.codefiles.classname + "__datatype_t";
                    auto datatype = apitypes->params__get("datatype").second;
                    if (datatype.empty() || datatype == datatype0) {
                        use_apitypes = true;
                        instance.params.underride__empty("datatype", base.codefiles.classname + "__datatype_t");
                    }
                    auto itemtype0 = base.codefiles.classname + "__itemtype_t";
                    auto itemtype = apitypes->params__get("itemtype").second;
                    if (itemtype.empty() || itemtype == itemtype0) {
                        use_apitypes = true;
                        instance.params.underride__empty("itemtype", base.codefiles.classname + "__itemtype_t");
                    }
                }
                if (!use_apitypes) {
                    cout << "removing apitypes\n";
                    instance.remove_child("apitypes");
                }
            }
        }

        void customize_produced_vertex(namespace_t& instance) const override {
            check_param__yes_no(instance, "item_button__conf__popup_menu");
            check_param__not_empty(instance, "item_title");

            auto base = dynamic_cast<namespace_t*>(instance.find_child("base"));
            assert(base != nullptr);
            customize_controller(instance, *base);
 
        }



        static constexpr auto itemview_bind_example_code{"itemview_bind_example_code"};

        string itemview_bind_example_code__api__java() const {
            ostringstream os;
            os <<
        "        Bitmap bmp = item.icon();\n" \
        "        if (bmp == null) {\n" \
        "             w.ico.setImageResource(us.cash.app.a.le.resolve_resid(##itemico##));\n" \
        "        }\n" \
        "        else {\n" \
        "            w.ico.setImageBitmap(bmp);\n" \
        "        }\n" \
        "        w.head.setText(item.head);\n" \
        "        w.tail.setText(item.tail);\n";
            return os.str();
        }

        string itemview_bind_example_code__java() const {
            ostringstream os;
            os <<
        "        w.ico.setImageResource(us.cash.app.a.le.resolve_resid(##itemico##));\n" \
        "        w.head.setText(\"head\");\n" \
        "        w.tail.setText(\"tail\");\n";
            return os.str();
        }

        string nft_support__itemtype__java(const string& nft_type) const {
            ostringstream os;
            os <<
        "    public " << nft_type << " nft() {\n" \
        "        return null;\n" \
        "    }\n";
            return os.str();
        }

        string nft_support__java(const string& nft_type) const {
            ostringstream os;
            os <<
        "    public " << nft_type << " get_nft(final ##itemtype## i) {\n" \
        "        return i.nft();\n" \
        "    }\n" \
        "\n" \
        "    public int find_nft__pos(final " << nft_type << " nft) {\n" \
        "        if (data == null) return -1;\n" \
        "        if (nft == null) return -1;\n" \
        "        int p = -1;\n" \
        "        for (##itemtype## i: data) {\n" \
        "            ++p;\n" \
        "            " << nft_type << " inft = get_nft(i);\n" \
        "            if (inft == null) continue;\n" \
        "            if (inft.equals(nft)) {\n" \
        "                break;\n" \
        "            }\n" \
        "        }\n" \
        "        return p;\n" \
        "    }\n" \
        "\n" \
        "    public ##itemtype## find_nft__item(final " << nft_type << " nft) {\n" \
        "        if (data == null) return null;\n" \
        "        if (nft == null) return null;\n" \
        "        for (##itemtype## i: data) {\n" \
        "            " << nft_type << " inft = get_nft(i);\n" \
        "            if (inft == null) continue;\n" \
        "            if (inft.equals(nft)) {\n" \
        "                return i;\n" \
        "            }\n" \
        "        }\n" \
        "        return null;\n" \
        "    }\n" \
        "\n" \
        "    public void highlight_nft(final " << nft_type << " nft, boolean with_flash) {\n" \
        "        int pos = find_nft__pos(nft);\n" \
        "        if (pos == -1) return;\n" \
        "        highlight(pos, true);\n" \
        "        if (with_flash) flash(pos);\n" \
        "    }\n";
            return os.str();
        }


        string nft_support__bind__java(const string& nft_type) const {
            ostringstream os;
            os <<
        "        adapter.highlight_nft(highlight_nft, true);\n" \
        "        highlight_nft = null;\n";
            return os.str();
        }

        string nft_support__highlight_def__java(const string& nft_type) const {
            ostringstream os;
            os <<
        "    " << nft_type << " highlight_nft = null;\n";
            return os.str();
        }

        string create__on_confitem_button_click(const namespace_t& instance) const {
            bool has_confmenu = instance.params__get("item_button__conf__popup_menu").second == "yes";
            ostringstream os;
            os <<
        "    @Override public void list_view__on_confitem_button_click(int pos) {\n" \
        "        log(\"list_view__on_confitem_button_click pos \" + pos); //--strip\n";
            if (has_confmenu) {
                os <<
        "        popup_menu__conf(pos);\n";
            }
            else {
                os <<
        "        //final ##itemtype## item = adapter.get_item(pos);\n" \
        "        //a.launch_(item.o);\n";
            }
            os <<
        "    }\n";
            return os.str();
        }

        string create__popups(const namespace_t& instance) const {
            bool has_confmenu = instance.params__get("item_button__conf__popup_menu").second == "yes";
            if (!has_confmenu) return "";
            string item_title = instance.params__get("item_title").second;
            string item_object_id = instance.params__get("item_object_id").second;
            ostringstream os;
            os <<
R"#(    public void popup_menu__conf(final int pos) {
        final ##itemtype## item = adapter.get_item(pos);
)#";
            if (!item_object_id.empty()) {
                os <<
R"#(        a.show_contextual_options_for(get_context(), ##itemico##, "##item_title##", ##item_object_id##, item);
)#";
            }

            os <<
R"#(    }
)#";
            return os.str();
        }
/*
        "        String[] options = new String[]{\"Configure " << item_title << "\", a.getResources().getString(R.string.cancel)};\n" \
        "        final " << instance.codefiles.classname << " self = this;\n" \
        "        AlertDialog.Builder dlg = a.new_dlg_builder(self);\n" \
        "        dlg.setTitle(\"Configure " << item_title << "\").setItems(options, new DialogInterface.OnClickListener() {\n" \
        "            @Override public void onClick(DialogInterface dialog, int which) {\n" \
        "                switch (which) {\n" \
        "                    case 0:\n" \
        "                        self.toast(\"KO 40010 Not implemented.\");\n" \
        "                        break;\n" \
        "                }\n" \
        "            }\n" \
        "        }).setIcon(R.raw.gear).show();\n" \
        "    }\n";
            return os.str();
        }
*/
        token_resolution_t resolve_token(const namespace_t& instance, const string& token) const override {
            if (token == itemview_bind_example_code) {
                string apifunction = instance.params__get("api__function").second;
                if (apifunction.empty()) {
                    return token_resolution_t(1, itemview_bind_example_code__java(), mim_file);
                }
                else {
                    return token_resolution_t(1, itemview_bind_example_code__api__java(), mim_file);
                }
            }
            static set<string> nft_support_tokens{
                "item_nft_code",
                "itemtype__nft_support_code",
                "nft_support__bind",
                "highlight_nft__def",
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
                if (token == "item_nft_code") {
                    return token_resolution_t(1, nft_support__java(nft_type), mim_file);
                }
                if (token == "itemtype__nft_support_code") {
                    return token_resolution_t(1, nft_support__itemtype__java(nft_type), mim_file);
                }
                if (token == "nft_support__bind") {
                    return token_resolution_t(1, nft_support__bind__java(nft_type), mim_file);
                }
                if (token == "highlight_nft__def") {
                    return token_resolution_t(1, nft_support__highlight_def__java(nft_type), mim_file);
                }
            }
            if (token == "popups") {
                return token_resolution_t(1, create__popups(instance), mim_file);
            }
            if (token == "conf_button__click_handler") {
                return token_resolution_t(1, create__on_confitem_button_click(instance), mim_file);
            }
            
            return token_resolutions::unsolved;
        }

    };

}}

