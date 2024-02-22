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

#include <string>
#include <map>
#include <limits>
#include <vector>
#include <us/gov/ko.h>
#include "namespace_t.h"
#include "root_vertex_t.h"
#include "srctool_t.h"

namespace mim {
    using namespace std;
    using us::ko;
    struct template_vertex_t;

    struct engine_t final {

        enum mode_t {
            mode_clean,
            mode_dev,
            mode_cbs,
        };

        enum part_level_t {
            part_level__only_gov_backend,
            part_level__only_wallet_backend,
            part_level__full_backend,
            part_level__only_gov_frontend,
            part_level__only_wallet_frontend,
            part_level__full_frontend,
            part_level__full,
        };

        struct dev_t {
            bool gitignore{true};
            bool mim_screen_dump{false};
        };

        struct features_t final {
            mode_t mode = mode_dev; //0: clean; 1: dev(debug);  2: cbs(release)
            part_level_t part_level{part_level__full}; 
            dev_t dev;
            //featureset_t featureset;
            float max_zorder{numeric_limits<float>::max()};
        };

    private:

        engine_t();
        ~engine_t();

    public:
        void init(float maxz, const string& output_dir);

/*
        struct layers_t: vector<root_vertex_t*> {
            layers_t();
            ~layers_t();

            void gen_merge_configure();
            void dump(ostream&) const;
        private:
            root_vertex_t* merge(int op);
            void merge_travel(root_vertex_t* src, root_vertex_t* dst, int op);

        };
*/
//        layers_t create_layers();

        const string& get_cfg(const string& key) const;
        static ko system_command(const string& command, string& result);
        static string target_filename(const string& file);

        void dump_files(const vector<string>& files);
        static bool file_exists(const string&);
        static bool dir_exists(const string&);
        static void trim(string&);
        vector<string> files(const string& dir);
        pair<ko, pair<string, string>> split_fqn(string fqn);
        void produce_file(const pair<string, filedef_t>& filedef, const string& tgt, bool overwrite);
        void produce_generated_file_(const string& content, const string& file, const string& tgt, bool overwrite);
        void produce_generated_file_(const string& content, const string& file, const string& tgt, const string& odir, bool overwrite);
        ko configure();
        ko clean_conf();
        void list_layers(float maxz, ostream&) const;
        bool mim_screen_dump_enabled() const;

        void register_template(const string& key, const template_vertex_t&);
        //void customize_produced_vertexes();

        void own(vertex_t*);

        static ko read_text_file_(const string& filename, string& dest);
        static void delete_lines_with(string& buffer, const string& search);
        static void replace_string(string& buffer, const string& search, const string& replace);

        string output_dir;
        features_t features;

        void set_dryrun();
        void set_review();

        bool dev_mode() const;
        bool cbs_mode() const;
        bool clean_mode() const;

        string home;
        bool dryrun{false};
        bool review{false};

        void systemx(const string& cmd);
        void systemx(const string& filename, const string& content);

        srctool_t srctool;
        map<string, string> cfg;

        static engine_t& instance();
        static void delete_instance();
        static vector<string> split_fqns(string fqns);

        bool skipped_code_review{false};

        bool write_kickoff_file(const string& path, const string& filename, const string& content);

        vector<string> warnings;

    private:
        static engine_t* _instance;
        vector<vertex_t*> owned;
        vector<string> new_kickoff_files;

    public:
        map<string, const template_vertex_t*> templates;
   };

}
