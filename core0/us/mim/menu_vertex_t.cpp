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

#include "menu_vertex_t.h"
#include "engine_t.h"

using namespace std;
using namespace mim;

using c = mim::menu_vertex_t;

c::menu_vertex_t(const string& name, const string& mim_file): b(name, mim_file) {
}

c::menu_vertex_t(const c& other): b(other), menus(other.menus), base(other.base) {
}

vertex_t* c::clone() const {
    return new c(*this);
}

bool c::merge(vertex_t* other_, int op) {
    if (!b::merge(other_, op)) return false;
    auto* other = dynamic_cast<c*>(other_);
    if (other == nullptr) {
        return true;
    }
    cout << "merging menu. vertex name  " << name << "<-" << other->name << '\n';
    while(true) {
        if (other->menus.empty()) break;
        ind_t ind(cout, "  ");
        auto i = other->menus.begin();
        auto s = menus.find(i->second.name_);
        if (s == menus.end()) {
            cout << "importing menuspec " << i->second.name_ << '\n';
            menus.emplace(move(*i));
        }
        else {
            s->second.merge(move(i->second), op);
            //assert(false);
        }
        other->menus.erase(i);
    }
    return true;
}

void c::add(menuspec_t&& spec) {
    cout << "Menu " << vertex_dir() << '\n';
    auto n = spec.name_;
    if (n.empty()) {
        cerr << "KO 10275 @vertex " << name << " " << vertex_dir() << '\n';
        assert(false);
        abort();
    }
    assert(menus.find(n) == menus.end());
    menus.emplace(n, move(spec));
}

string c::spec_classname(const menuspec_t& spec) const {
    string parent_classname;
    {
        auto p = get_parent();
        assert(p != nullptr);
        const_cast<namespace_t*>(p)->codefiles.compute_classname();
        parent_classname = p->codefiles.classname;
        if (parent_classname.empty()) {
            cerr << "KO 50499\n";
            abort();
        }
//        if (parent_classname.empty()) {
//            parent_classname = p->name;
//        }
    }
    return spec.class_name(parent_classname);
}

void c::pre_configure(const menuspec_t& spec) {
    cout << "configure menuspec " << spec.name_ << endl;
    lang_codefiles_t* o = codefiles.find_lang_codefiles("java", "scr");
    assert(o != nullptr);

    string classname = spec_classname(spec);
    ostringstream file;
    file << classname << ".java";
    ostringstream content;

    //spec.write_info(content);

    string src__header_icon;
    string src__header_title = "\"\"";
    string src__header_subtitle = "\"\"";
    {
        int n{0};
        vector<const menuspec_t*> v;
        const menuspec_t* s = &spec;
        while (s != nullptr) {
            v.emplace_back(s);
            if (s->basespec.empty()) {
                break;
            }
            auto i = menus.find(s->basespec);
            if (i == menus.end()) {
                cerr << "s->basespec: " << s->basespec << '\n';
                cerr << "KO 76832 basespec not found.\n";
                abort();
            }
            s = &i->second;
        }
        for (auto i = v.rbegin(); i != v.rend(); ++i) {
            {
                auto s = (*i)->header_icon;
                if (!s.empty()) {
                    src__header_icon = s;
                }
            }
            {
                auto s = (*i)->resolve_src__header_title();
                if (s != "\"\"") {
                    src__header_title = s;
                }
            }
            {
                auto s = (*i)->resolve_src__header_subtitle();
                if (s != "\"\"") {
                    src__header_subtitle = s;
                }
            }
        }
    }
//    bool inherit_from_baseclass = ; //spec.inherit_from_baseclass;
//    if (spec.basespec.empty()) {
//        inherit_from_baseclass = false;
//    }
    assert(!src__header_icon.empty());
    assert(!src__header_title.empty());
    assert(!src__header_subtitle.empty());
    auto package = params__get("str28").second;
    content <<
        "package " << package << ".scr;\n" \
        "import android.content.Context;\n" \
        "import android.view.View;\n" \
        "import " << package << ".R;\n" \
        "\n";

    if (!spec.basespec.empty()) {
        auto i = menus.find(spec.basespec);
        if (i == menus.end()) {
            cerr << "KO 76832 basespec not found\n";
            abort();
        }

        string baseclass = spec_classname(i->second);
        content <<
        "public class " << classname << " extends " << baseclass << " {\n" \
        "    public " << classname << "(Context ctx) {\n" \
        "        super(ctx);\n" \
        "    }\n" \
        "\n" \
        "    @Override public void append_spec(menuspec_t spec) {\n" \
        "        super.append_spec(spec);\n";
        {
            //int n{0};
            for (auto& g: spec) {
//                if (g.merge) {
                content <<
                "        {\n" \
                "            group_t g = spec.find(\"" << g.name << "\");\n";
                for (auto i = g.begin(); i != g.end(); ++i) {
                    content <<
                "            g.add(" << i->id << ", \"" << i->title << "\");\n";
                }
                content <<
                "        }\n";
/*
                }
                else {
                    if (g.name.empty()) {
                        content <<
                        "        group_t g" << n << " = new group_t(); {\n";
                    }
                    else {
                        content <<
                        "        group_t g" << n << " = new group_t(\"" << g.name << "\"); {\n";
                    }
                    for (auto& i: g) {
                        content <<
                    "            g" << n << ".add(" << i.id << ", \"" << i.title << "\");\n";
                    }
                    content <<
                    "        }\n" \
                    "        spec.add(0, g" << n << ");\n";
                }
                ++n;
*/
            }
        }
        content <<
            "    }\n" \
            "\n" \
            "    public static " << classname << " get_instance(Context ctx) {\n" \
            "        if (instance != null) return instance;\n" \
            "        instance = new " << classname << "(ctx);\n" \
            "        return instance;\n" \
            "    }\n" \
            "\n" \
            "    public static void clear_cache() {\n" \
            "        instance = null;\n" \
            "    }\n" \
            "\n" \
            "    static " << classname << " instance = null;\n" \
            "}\n";
    }
    else {
        content <<
            "public class " << classname << " extends menu_t {\n" \
            "    public " << classname << "(Context ctx) {\n" \
            "        super(ctx, new menu_header_t(ctx, " << src__header_icon << ", " << src__header_title << ", " << src__header_subtitle << ", new View.OnClickListener() {\n" \
            "            @Override public void onClick(View view) {\n" \
            "                //" << package << ".app.a.toast(us.CFG.COPYRIGHT_LINE);\n" \
            "            }\n" \
            "        }));\n" \
            "    }\n" \
            "\n" \
            "    public void append_spec(menuspec_t spec) {\n";

        {
            //int n{0};
            const menuspec_t* s = &spec;
            while (s != nullptr) {
                for (auto& g: *s) {
                    //if (g.name.empty()) {
                    //    content <<
                    //    "        group_t g" << n << " = new group_t(); {\n";
                    //}
                    //else {
                    content <<
                    "        {\n" \
                    "            group_t g = spec.find(\"" << g.name << "\");\n";
                    //}
                    for (auto i = g.begin(); i!= g.end(); ++i) {
                        content <<
                    "            g.add(" << i->id << ", \"" << i->title << "\");\n";
                    }
                    content <<
                    "        }\n";
//                    "        spec.add(g" << n << ");\n";
//                    ++n;
                }

                if (s->basespec.empty()) {
                    break;
                }
                //for (auto& i: menus) cout << i.first << endl;
                //cout << s->basespec << endl;
                auto i = menus.find(s->basespec);
                if (i == menus.end()) {
                    cerr << "KO 76832 basespec not found\n";
                    abort();
                }
                s = &i->second;
            }
        }
        if (engine_t::instance().mim_screen_dump_enabled()) {
            content <<
            "        {\n" \
            "            group_t g = find(\"dev\");\n" \
            "            g.add(R.raw.dev, \"MIM screen dump\");\n" \
            "        }\n";
//            "        spec.add(gdev);\n";
        }
        content <<
            "    }\n" \
            "\n" \
            "    @Override public menuspec_t create_spec() {\n" \
            "        menuspec_t spec = new menuspec_t();\n" \
            "        append_spec(spec);\n" \
            "        return spec;\n" \
            "    }\n" \
            "\n" \
            "    public static " << classname << " get_instance(Context ctx) {\n" \
            "        if (instance != null) return instance;\n" \
            "        instance = new " << classname << "(ctx);\n" \
            "        return instance;\n" \
            "    }\n" \
            "\n" \
            "    public static void clear_cache() {\n" \
            "        instance = null;\n" \
            "    }\n" \
            "\n" \
            "    static " << classname << " instance = null;\n" \
            "}\n";
    }

    
    filedef_t filedef;
    filedef.hash = hash_content(content.str());
    //cout << "hash " << file.str() << " " << filedef.hash << endl;
    filedef.derived_from = mim_file;
    filedef.content = content.str();
    filedef.mim_file = mim_file;
    o->add_file(file.str(), move(filedef));
}

bool c::gen() {
    return b::gen();
}

bool c::pre_configure() {
    for (auto& i: menus) {
        pre_configure(i.second);
    }
    return b::pre_configure();
}

bool c::configure() {
    return b::configure();
}

void c::dump_(ostream& os) const {
    os << "== menu vertex.\n";
    {
        ind_t ind(os, "  ");
        os << menus.size() << " menus:\n";
        {
            int n{0};
            for (auto& i: menus) {
                ostringstream pfx;
                pfx << "menu " << n++ << ": "; 
                ind_t ind(os, pfx.str());
                i.second.dump(os);
            }
        }
    }
    b::dump_(os);
}

