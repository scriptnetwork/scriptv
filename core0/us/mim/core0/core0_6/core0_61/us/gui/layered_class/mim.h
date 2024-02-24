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

namespace mim::core0::core0_6::core0_61 {
namespace us::gui::layered_class {

struct vertex_t: mim::template_vertex_t {
    using b = mim::template_vertex_t;

    vertex_t(): b("layered_class", __FILE__, "us::gui::layered_class") {
        codefiles.add_java();
    }

    params_t default_params() const override {
        return params_t {
            {"deepest_baseclass", "Object"},
        };
    }

    void rewrite_classname__instance(string& instance_classname) const {
    }

    static string layerize(const string& name0, int z) {
        string name = name0;
        string suff;
        if (name0.size() >= 2) {
            auto i = name0.rfind("_t");
            if (i == name0.size() - 2) {
                name = name0.substr(0, i);
                suff = "_t";
            }
        }
        ostringstream os;
        os << name << z << suff;
        return os.str();
    }

    void customize_instance(namespace_t& instance, params_srcs_t& params_srcs) const override {
        instance.codefiles.filename_tokens.emplace("layer_classname");

        string baseclass;
        if (z > 0) {
            baseclass = layerize(instance.codefiles.classname, z - 1);
        }
        else {
            baseclass = params_srcs.get("deepest_baseclass");
        }
        assert(!baseclass.empty());
        string layer_classname = layerize(instance.codefiles.classname, z);

        params_src_t params_src(mim_file, params_t{
            {"layer_classname", layer_classname},
            {"baseclass", baseclass},
        });
        params_srcs.priority_set(params_src);
    }

    void customize_instance__post_inflate(namespace_t& instance) const override {
    }

    int z{0};
};

}}


