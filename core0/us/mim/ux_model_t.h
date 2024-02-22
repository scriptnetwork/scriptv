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

namespace us::front {


struct ux_model_t {

    struct vertex {
    };

    struct fragment: vertex {
            struct certs_all: fragment {
            }
    };


    struct activity: vertex {
        struct fragmented_activity: virtual activity, vector<fragment> {
             
        };
            struct certs: fragmented_activity {
            }

        struct scroll_activity: virtual activity {
        };

            struct fragmented_scroll_activity: fragmented_activity, scroll_activity {
                 
            };

            struct device_endpoint__conf: scroll_activity {
            };

            device_endpoints__conf

    };

};

}
/*
ux_model_t() {
    //vertex1, vertex2, edge_data

    vector<string> al = {
    "0 1 4",
    "0 7 8",
    "1 7 11",
    "1 2 8",
    "7 8 7",
    "2 8 2",
    "7 6 1",
    "6 8 6",
    "2 5 4",
    "6 5 2",
    "2 3 7",
    "3 5 14",
    "3 4 9",
    "5 4 10"};

    graph<> g(al, graph<>::directed);

    {
    cout << "written dot file: graph.dot" << endl;
    ofstream f("graph.dot");
    g.dot(f);
    }

    cout << g.vertexes_size() << " vertexes, " << g.edges_size() << " edges " << endl;

    {
        cout << "Kruskal" << endl;

        auto v=g.sort_edges_no_descendent();
        cout << "Weight Src Dst" << endl;
        for (auto& i:v) {
            cout << g.get_edata(i).value << " " << i.first << " " << i.second << endl;
        }

        auto mst=g.mst_kruskal();

        {
        cout << "written dot file: graph_mst_kruskal.dot" << endl;
        ofstream f("graph_mst_kruskal.dot");
        mst.dot(f);
        }

        cout << "sum: " << g.sum_data().value << endl;

    }
    {
        cout << "Prim" << endl;

        auto mst=g.mst_prim();
        {
        cout << "written dot file: graph_mst_prim.dot" << endl;
        ofstream f("graph_mst_prim.dot");
        mst.dot(f);
        }

        cout << "sum: " << g.sum_data().value << endl;
    }
    {
        cout << "Dijkstra Shortest Path Tree from 0" << endl;
        auto spt=g.spt_dijkstra(0);
        {
        cout << "written dot file: graph_spt_dijkstra.dot" << endl;
        ofstream f("graph_spt_dijkstra.dot");
        spt.first.dot(f);
        spt.second.dump(cout);
        }

        cout << "Dijkstra Shortest Path - from 0 to 6" << endl;
        auto sp_to6=spt.second.get_sp(6);
        {
        cout << "written dot file: graph_spt_dijkstra_0to6.dot" << endl;
        ofstream f("graph_spt_dijkstra_0to6.dot");
        sp_to6.dot(f);
        }
        

    }
    {
        cout << "Dijkstra Shortest Path Tree from 0 to 8" << endl;
        auto spt=g.spt_dijkstra(0,8);
        spt.second.dump(cout);
        auto sp_to8=spt.second.get_sp(8);
        {
        cout << "written dot file: graph_spt_dijkstra_0to8.dot" << endl;
        ofstream f("graph_spt_dijkstra_0to8.dot");
        sp_to8.dot(f);
        }

    }
}


};

}
*/
