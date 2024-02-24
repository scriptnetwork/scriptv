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

#include <vector>
#include <string>
#include <iostream>
#include <sstream>
#include <map>
#include <set>
#include <unordered_map>
#include <unordered_set>
#include <queue>
#include <stack>
#include <limits>
#include <functional>
#include <algorithm>
#include <cassert>
#include <fstream>

namespace us::front {
using namespace std;

struct pairhash {
    public:
    template <typename T, typename U>
    std::size_t operator()(const std::pair<T, U> &x) const {
          //http://stackoverflow.com/questions/5889238/why-is-xor-the-default-way-to-combine-hashes
          //return std::hash<T>()(x.first) ^ std::hash<U>()(x.second);
          auto lhs = hash<T>()(x.first);
          auto rhs = hash<U>()(x.second);
          lhs ^= rhs + 0x9e3779b9 + (lhs << 6) + (lhs >> 2);
          return lhs;
    }
};


template<typename T> struct edge;
template<typename T>
struct vertex {
    vertex(T id_): id(id_) {
    }
    bool is_leaf() const { return e.empty(); }
    const edge<T>* get_edge_to(T to) const;
    set<edge<T>*> e;
    T id;
};

template<typename T>
struct edge {  //from, to
    edge(vertex<T>* f, const vertex<T>* t): from(f), to(t) {
        from->e.emplace(this);
    }
    ~edge() {
        from->e.erase(this);
    }
    const edge* get_reverse() const {
        return to->get_edge_to(from->id);
    }
    vertex<T>* from;
    const vertex<T>* to;
};

template<typename T>
const edge<T>* vertex<T>::get_edge_to(T to) const {
    for (const auto& i: e) {
        if (i->to->id == to) return i;
    }
    return 0;
}

template<typename T>
struct basic_graph {
    typedef T vtype;
    typedef pair<T,T> edid;
    enum type {
        directed, undirected
    };

    basic_graph(type t = directed): t(t) {  
    }

    basic_graph(const vector<string>& al, type t = directed): t(t) {  //v1 v2
        for (auto& l: al) {
            T v1;
            T v2;
            istringstream is(l);
            is >> v1;
            is >> v2;
            add(v1, v2);
        }
    }

    basic_graph(const vector<edid>& al, type t = directed): t(t) {  //v1 v2
        for (auto& l: al) {
            add(l.first, l.second);
        }
    }

    basic_graph(const basic_graph&) = delete;
    basic_graph(basic_graph&& other): V(move(other.V)), E(move(other.E)), t(other.t) {
    }

    template<class P>
    void make_dot(const P& pred, const function<bool(const vertex<T>&)>& is_leaf, const function<bool(const vertex<T>&)>& is_done, const function<string(const edge<T>&)>& label_edge, bool invert, ostream& os) const {
        os << "digraph {" << endl;
        os << "node [shape=oval,style=filled]\n";
        if (t == undirected) {
            os << "edge [arrowhead=none,arrowtail=none]\n";
        }
        else {
            os << "edge [arrowhead=normal,arrowtail=none]\n";
        }
        for (auto v: V) {
            string label=pred(v.second->id);
            os << "task" << v.second->id << "[label=<" << v.second->id;
            if (!label.empty()) {
                os << ": " << label ;
            }
            os << ">";
            if (is_done(*v.second)) {
                os << ",color=green";
            }
            else if (is_leaf(*v.second)) {
                os << ",color=red";
            }
            os << "];" << endl;
        }
        set<pair<T,T>> u;
        for (auto e: E) {
            if (t == undirected) {
                edid f=e.first;
                if (f.first>f.second) swap(f.first, f.second);
                if (u.find(f) != u.end()) {
                    continue;
                }
                u.emplace(f);
            }
            string label = label_edge(*e.second);
            if (invert)
                os << "task" << e.second->to->id << " -> task" << e.second->from->id;
            else
                os << "task" << e.second->from->id << " -> task" << e.second->to->id;
            int n = 0;
            ostringstream ost;
            ost << " [";
            if (!label.empty()) {
                ost << "label=<" << label << ">";
                ++n;
            }
            ost << "]";
            if (n>0)  os << ost.str();
            os << ";" << endl;
        }
        os << "}" << endl;
    }

    virtual void dot(ostream& os) const {
        make_dot(
            [](T i) -> string { return ""; },
            [](const vertex<T>&v) -> bool { return v.is_leaf(); },
            [](const vertex<T>&) -> bool { return false; },
            [](const edge<T>&) -> string { return ""; },
            false, os);
    }

    vertex<T>* get_vertex(T iv) {
        auto i = V.find(iv);
        if (i == V.end()) {
            V.emplace(iv,new vertex<T>(iv));
            i = V.find(iv);
        }
        return i->second;
    }

    const vertex<T>* get_vertex(T iv) const {
        auto i = V.find(iv);
        if (i == V.end()) {
            return 0;
        }
        return i->second;
    }

    edid get_eid(T iv1, T iv2) const {
        return move(make_pair(iv1,iv2));
    }
    edid get_eid(const edge<T>& e) const {
        return move(make_pair(e.from->id,e.to->id));
    }
    edid get_erid(const edge<T>& e) const {
        return move(make_pair(e.to->id,e.from->id));
    }

    virtual edge<T>* add(T iv1, T iv2) {
        if (iv1 == iv2) return 0;
        auto v1 = get_vertex(iv1);
        auto v2 = get_vertex(iv2);
        edge<T>* e = new edge<T>(v1,v2);
        E.emplace(get_eid(iv1, iv2), e);
        if (t == undirected) {
            edge<T>* e = new edge<T>(v2, v1);
            E.emplace(get_eid(iv2, iv1), e);
        }
        return e;
    }

    virtual void erase(edge<T>* e) {
        auto eid = get_eid(*e);
        if (t == undirected) {
            auto i = E.find(get_erid(*e));
            if (i == E.end()) return;
            delete i->second;
            E.erase(i);
        }
        auto i = E.find(eid);
        if (i == E.end()) return;
        delete i->second;
        E.erase(i);
    }

    virtual ~basic_graph() {
        for (auto i: E) delete i.second;
        for (auto i: V) delete i.second;
    }

    struct visitor {
        virtual void start(const vertex<T>&){};
        virtual void visit(const edge<T>&){};
        virtual void visit(const vertex<T>&){};
        virtual void finished(){};
        bool finish{false};
    };
    void breath_first(T iv, visitor& visitor_) const {
        auto v = V.find(iv);
        if (v == V.end()) return;
        breath_first(*v->second, visitor_);
    }

    void breath_first(const vertex<T>& s, visitor& visitor_) const {
        visitor_.start(s);
        visitor_.visit(s);
        unordered_set<const edge<T>*> visited;
        queue<edge<T>*> q;
        for (auto e: s.e) {
            q.push(e);
        }
        while (!q.empty() && !visitor_.finish) {
            edge<T>* e = q.front(); q.pop();
            if (visited.find(e) == visited.end()) {
                for (auto ed: e->to->e) {
                    q.push(ed);
                }
                visitor_.visit(*e);
                visitor_.visit(*e->to);
                visited.emplace(e);
                if (t == undirected) {
                    const edge<T>* redge = e->get_reverse();
                    assert(redge != 0);
                    visited.emplace(redge);
                }
            }
        }
        visitor_.finished();
    }

    void depth_first(T iv, visitor& visitor_) const {
        auto v = V.find(iv);
        if (v == V.end()) return;
        depth_first(*v->second, visitor_);
    }

    void depth_first(const vertex<T>& s, visitor& visitor_) const {
        visitor_.start(s);
        visitor_.visit(s);
        unordered_set<const edge<T>*> visited;
        stack<edge<T>*> q;
        for (auto e: s.e) {
            q.push(e);
        }
        while(!q.empty() && !visitor_.finish) {
            edge<T>* e = q.top(); q.pop();
            if (visited.find(e) == visited.end()) {		
                for (auto ed:e->to->e) {
                    q.push(ed);
                }
                visitor_.visit(*e);
                visitor_.visit(*e->to);
                visited.emplace(e);
                if (t == undirected) {
                    const edge<T>* redge = e->get_reverse();
                    assert(redge != 0);
                    visited.emplace(redge);
                }
            }
        }
        visitor_.finished();
    }

    int vertexes_size() const { return V.size(); }
    int edges_size() const { return t==directed?E.size():E.size()/2; }

    bool cycle(const edge<T>& start) const {
        struct cycle_visitor: visitor {
            cycle_visitor(const edge<T>& start): start(start) {
            }
            virtual void visit(const edge<T>& e) override {
                if (e.to->id == start.from->id) {
                    cycle = true;
                    visitor::finish = true;
                }
            }
            const edge<T>& start;
            bool cycle{false};
        };
        cycle_visitor v(start);
        depth_first(*start.from, v);
        return v.cycle;
    }

    virtual void reverse() {
        for (auto& v:V) {
            v.second->e.clear();
        }
        for (auto& e:E) {
            swap(e.second->from,const_cast<vertex<T>*&>(e.second->to));
            V.find(e.second->from->id)->second->e.emplace(e.second);
        }
    }

    unordered_map<T,vertex<T>*> V;
    unordered_map<edid,edge<T>*, pairhash> E;
    type t;
};

/// scalar - most common best path optimization vector
template<typename T> 
struct scalar {
    T value{0};
    scalar() { }
    scalar(const T& t): value(t) { }
    scalar(T&& t): value(move(t)) { }
    scalar(const string& s) {
        istringstream is(s);
        is >> value;
    }
    bool operator < (const scalar& other) const {
        return value < other.value;
    }
    scalar& operator += (const scalar& other) {
        value += other.value;
        return *this;
    }
    scalar& operator -= (const scalar& other) {
        value -= other.value;
        return *this;
    }
    scalar operator + (const scalar& other) const {
        scalar v(*this);
        v += other;
        return move(v);
    }
    scalar operator - (const scalar& other) const {
        scalar v(*this);
        v -= other;
        return move(v);
    }
};

template<typename T>
istream& operator >> (istream& is, scalar<T>& v) {
    is >> v.value;
    return is;
}
template<typename T>
ostream& operator << (ostream& os, const scalar<T>& v) {
    os << v.value;
    return os;
}

template<typename T = int, typename Edata = scalar<int>>
struct graph: basic_graph<T> {
        typedef basic_graph<T> base;
        using type=typename base::type;
        using edid=typename base::edid;
        using vtype=typename base::vtype;

    graph(type t = base::directed): base(t) {
    }
    graph(const vector<edid>& al, type t = base::directed): base(al, t) {  //v1 v2
    }

    graph(const vector<string>& al_values, type t = base::directed): base(t) {  //v1 v2
        for (auto& l: al_values) {
            vtype v1;
            vtype v2;
            istringstream is(l);
            is >> v1;
            is >> v2;
            Edata d;
            is >> d;
            add(v1, v2, move(d));
        }
    }

    graph(const graph&) = delete;
    graph(graph&& other): ed(move(other.ed)), base(move(other)) {
    }

    virtual ~graph() {
    }

    virtual void dot(ostream& os) const {
        base::make_dot(
        [](int i) -> string { return ""; },
        [](const vertex<vtype>&v) -> bool { return v.is_leaf(); },
        [](const vertex<vtype>&) -> bool { return false; },
        [&](const edge<vtype>& e) -> string {
            ostringstream os;
            os << ed.find(get_edid(e))->second;
            return os.str();
        },
        false,
        os);
    }

    edid get_edid(const edge<vtype>& e) const {
        return get_edid(e.from->id, e.to->id);
    }

    edid get_edid(vtype iv1, vtype iv2) const {
        if (base::t == base::undirected && iv1>iv2) {
            return get_edid(iv2, iv1);
        }
        return move(make_pair(iv1, iv2));
    }

    virtual edge<vtype>* add(vtype iv1, vtype iv2) {
        base::add(iv1, iv2);
    }

    edge<vtype>* add(int iv1, int iv2, const Edata& d) {
        edge<vtype>* e=base::add(iv1, iv2);
        ed.emplace(get_edid(iv1, iv2), d);
        return e;
    }
    edge<vtype>* add(vtype iv1, vtype iv2, Edata&& d) {
        edge<vtype>* e = base::add(iv1, iv2);
        ed.emplace(get_edid(iv1, iv2), move(d));
        return e;
    }
    vector<edid> sort_edges_no_descendent() const { // O(E log E)
        vector<edid> v;
        v.reserve(ed.size());
        for (auto& i:ed) {
            v.push_back(i.first);
        }
        sort(v.begin(), v.end(), [&](const edid& e1,const edid& e2) -> bool {
            return ed.find(e1)->second < ed.find(e2)->second;
        });
        return move(v);
    }

    virtual void erase(edge<vtype>* e) {
        auto i = ed.find(get_edid(*e));
        if (i != ed.end()) ed.erase(i);
        base::erase(e);
    }

    graph mst_prim() const { // minimum_spanning_tree - greedy. 
        //http://www.geeksforgeeks.org/greedy-algorithms-set-5-prims-minimum-spanning-tree-mst-2/

        typedef multimap<Edata,const edge<vtype>*> master_t;
        typedef map<const vertex<vtype>*,typename master_t::const_iterator> index_t;

        master_t master;
        index_t index;

        graph g(base::t);
        if (base::V.empty()) return move(g);

        const vertex<vtype>* v=base::V.cbegin()->second; //pick vertex with minimum key value

        while(true) {

            for (auto& e: v->e) {
                if (g.V.find(e->to->id) != g.V.end()) continue; // already added
                auto i = index.find(e->to);
                if (i == index.end()) {
                    auto j = master.emplace(get_edata(*e), e);
                    index.emplace(e->to, j);
                }
                else {
                    master.erase(i->second);
                    auto j = master.emplace(get_edata(*e), e);
                    i->second = j;
                }
            }
            if (master.empty()) break;
            const edge<vtype>* e = master.cbegin()->second; //pick vertex with minimum key value
            index.erase(e->to);
            master.erase(master.begin());
            v = e->to;
            g.add(e->from->id, e->to->id, get_edata(*e));
        } 
        return move(g);
    }

    graph mst_kruskal() const { // minimum_spanning_tree - greedy. 
        //http://www.geeksforgeeks.org/greedy-algorithms-set-2-kruskals-minimum-spanning-tree-mst/
        graph r(base::t);
        vector<edid> edges = sort_edges_no_descendent();
        for (const auto& e: edges) {
            edge<vtype>* ne = r.add(e.first,e.second);
            if (r.cycle(*ne)) {
                r.erase(ne);
            }
            else {
                r.ed.emplace(e,get_edata(e)); //add the weight
                if (r.edges_size() == base::vertexes_size() - 1) break;
            }
        }
        return move(r);
    }

    Edata sum_data() const {
        struct vis: base::visitor {
            vis(const graph& g): g(g) {}
            Edata d;
            const graph& g;
            virtual void visit(const edge<vtype>& e) {
                d += g.get_edata(e);
            }
        };
        vis v(*this);
        base::depth_first(*base::V.cbegin()->second, v);
        return v.d;
    }

    struct spt {
        struct vd {
            vd() {}
            vd(vd&& other): src(other.src), cost(move(other.cost)) {}
            vd(int src, const Edata&d): src(src), cost(d) { }
            vtype src{-1};
            Edata cost;
        };
        struct vertex_data: unordered_map<vtype, vd> {
            typedef unordered_map<vtype, vd> base;
            vertex_data(){}
            vertex_data(vertex_data&&other): base(move(other)) {}
            void dump(ostream& os) const {
                os << "vertex_data" << endl;
                for (auto& i: *this) {
                    os << " > " << i.first << " " << i.second.cost.value << '\n';
                }
            }
            void relax(const edge<vtype>& e) {
            }

            graph get_sp(vtype to) const {
                graph r;
                auto i = this->find(to);
                if (i == this->end()) return move(r);
                vtype cur = to;
                vtype prev = i->second.src;
                while(prev != -1) {
                    auto p = this->find(prev);
                    assert(p != this->end());
                    r.add(prev, cur, i->second.cost-p->second.cost);
                    i = p;
                    cur = prev;
                    prev = i->second.src;
                } 
                return move(r);
            }
        };
    };

    pair<graph,typename spt::vertex_data> spt_Astar(const function<Edata(const vertex<vtype>*)>& heur, vtype start, vtype end=-1) const { //shortest_path_tree
        //http://www.geeksforgeeks.org/a-search-algorithm/

        typename spt::vertex_data vdm;

        auto cmp = [&vdm, &heur](const vertex<vtype>* v1, const vertex<vtype>* v2)-> bool {
            return (vdm.find(v2->id)->second.cost+heur(v2)) < (vdm.find(v1->id)->second.cost+heur(v1));
        }; 
        priority_queue<const vertex<vtype>*,vector<const vertex<vtype>*>, decltype(cmp)> q(cmp);

        const vertex<vtype>* v = base::get_vertex(start);
        vdm.emplace(v->id,typename spt::vd());
        q.emplace(v);
        while(!q.empty()) {
            const vertex<vtype>* v = q.top(); q.pop();
            if (v->id == end) break;
            const typename spt::vd& w0 = vdm.find(v->id)->second;
            for (auto& e:v->e) {
                auto i = vdm.find(e->to->id);
                if (i == vdm.end()) {
                    i = vdm.emplace(e->to->id, typename spt::vd(e->from->id, w0.cost + get_edata(*e))).first;
                    q.emplace(e->to);
                    continue;
                }
                auto cost = w0.cost + get_edata(*e);
                if (cost < i->second.cost)  {
                    i->second.cost = cost;
                    i->second.src = v->id;
                    q.emplace(e->to);  // I accept leaving the old entry buried in the heap, 
                }
            }
        }
        graph r(base::t);
        for (auto& i:vdm) {
            if (i.second.src == -1) continue;
            r.add(i.second.src, i.first, get_edata(get_edid(i.second.src, i.first)));
        }
        return move(pair<graph, typename spt::vertex_data>(move(r), move(vdm)));
    }

    pair<graph,typename spt::vertex_data> spt_dijkstra(vtype start, vtype end = -1) const { //shortest_path_tree
        //http://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/
        return move(spt_Astar([](const vertex<vtype>*) -> Edata {return Edata();}, start, end));
    }

/*
    pair<graph,typename spt::vertex_data> spt_bellman_ford(int start) const { //shortest_path_tree
        //http://www.geeksforgeeks.org/dynamic-programming-set-23-bellman-ford-algorithm/

        typename spt::vertex_data vdm;

        for (int i=0; i<V.size(); **i) {
            for (auto& e:E) {
                vdm.relax(*e);
            }
        }

        graph r(t);
        for (auto& i:vdm) {
            if (i.second.src==-1) continue;
            r.add(i.second.src,i.first,get_edata(get_edid(i.second.src,i.first)));
        }
        return move(pair<graph,typename spt::vertex_data>(move(r),move(vdm)));
    }
*/


    Edata defaultEdata;
    const Edata& get_edata(const edge<vtype>& e) const {
        auto i = ed.find(get_edid(e));
        if (i == ed.end()) return defaultEdata;
        return i->second;
    }
    const Edata& get_edata(const edid& e) const {
        auto i = ed.find(e);
        if (i == ed.end()) return defaultEdata;
        return i->second;
    }

    virtual void reverse() {
        base::reverse();
        if (base::t == base::undirected) return;
        unordered_map<edid, Edata, pairhash> m;
        for (auto& i: ed) {
            m.emplace(edid(i.first.second, i.first.first), move(i.second));
        }
        ed = m;
    }

    unordered_map<edid, Edata, pairhash> ed;
};

/*
struct Vdata {
	Vdata():score(numeric_limits<int>::max()) {}
	Vdata(int v):score(v) {}
	const vertex* get_dst_vertex() const { return from->to; }
	const vertex* get_src_vertex() const { return from->from; }
	int score;
	const edge* from{0};
	bool operator <(const Vdata& other) const { return score<other.score; }
	template<typename Edata>
	Vdata operator+(const Edata& e) const {
		Vdata r;
		r.score=score+e.value;
		return move(r);
	}	
};

/// best_path visitor
///
/// this visitor traverses the graph through an optimized route
template<typename Edata, typename Vdata=Vdata>
struct best_path:basic_graph::visitor {
	unordered_map<const edge*,Edata> values;
	unordered_map<const vertex*,Vdata> vd; //vertex data
	const basic_graph& g;
	const vertex* f{0};
	const vertex* t{0};
	struct result:vector<const vertex*> {
		void dump(const std::function <string (const vertex&)>& _t, ostream& os) const {
			for (auto i:*this) os << _t(*i) << endl;
		}
		void dump(ostream& os) const {
			for (auto i:*this) os << i->id << endl;
		}
		void reverse() {
			result r;
			r.reserve(size());
			for (auto i=rbegin(); i!=rend(); ++i) 
				r.push_back(*i);
			*this=r;
		}
	};
	result r;
	best_path(const basic_graph&g_): g(g_) { //adjacency list + values
	}
	best_path(const basic_graph&g_, const vector<string>& al_values): g(g_) { //adjacency list + values
		for (auto& l:al_values) {
			int v1=-1;
			int v2=-1;
			istringstream is(l);
			is >> v1;
			is >> v2;
//			ostringstream os;
//			os << v1 << " " << v2;
			auto ie=g.E.find(make_pair(v1,v2));
			if (ie==g.E.end()) {
				cerr << "edge not found " << v1 << " " << v2 << endl;
				continue;
			}
			string value;
			getline(is,value);
			Edata t(value);
			values.emplace(ie->second,move(t));
		}
	}
	~best_path() {
	}
		enum algorithm {
			breath_first, depth_first
		
		};

	result compute(int from, int to,algorithm a) {
		auto ifrom=g.V.find(from);
		if (ifrom==g.V.end()) {
			cerr << from << " node not found" << endl;
			return r;
		}
		else f=ifrom->second;

		auto ito=g.V.find(to);
		if (ito==g.V.end()) {
			cerr << to << " node not found" << endl;
			return r;
		}
		else t=ito->second;
		switch(a) {
			case breath_first:
				g.breath_first(*f,*this);
				break;
			case depth_first:
				g.depth_first(*f,*this);
				break;
			default: cerr << "unknown algorithm" << endl;
		}
		return r;
	}

	Vdata& relax(const edge& e) {
		//obtain edge associated data structure
		auto edi=values.find(&e);
		if (edi==values.end()) {
			values.emplace(&e,Edata());
			edi=values.find(&e);
		}
		const auto& ed=edi->second; ////edge associated data structure

		//obtain source vertex associated data structure
		auto ivdf=vd.find(e.from);
		if (ivdf==vd.end()) {
			vd.emplace(e.from,Vdata());
			ivdf=vd.find(e.from);
		}

		//obtain destination vertex associated data structure
		auto ivdt=vd.find(e.to);
		if (ivdt==vd.end()) {
			vd.emplace(e.to,Vdata());
			ivdt=vd.find(e.to);
		}
		const Vdata& df=ivdf->second; //source vertex associated data structure
		Vdata& dt=ivdt->second; //destination vertex associated data structure
		auto sum=df+ed;	// from-vertex accumulated cost plus edge cost
		if (sum<dt) {
			dt=sum;
			dt.from=&e; //annotate in the destination vertex data this source is the most optimal so far
		}
		return dt;
	}


	void start(const vertex& s) override {
		r.clear();
		vd.clear();
		vd.emplace(&s,Vdata(0)); //Score
	};
	
	void visit(const edge& e) override {
		relax(e);
	}

	void finished() override {
		auto icur=vd.find(t);
		if (icur==vd.end()) return; //no hay conexion con el nodo buscado
		Vdata*cur=&icur->second;
		vector<const vertex*> rp;
		while(cur->from!=0) {
			rp.push_back(cur->get_dst_vertex());
			const vertex* src=cur->get_src_vertex();
			if (src==0) break;
			cur=&vd.find(src)->second;
		}		
		//reverse it
		r.reserve(rp.size()+1);
		r.push_back(f);
		for (auto i=rp.rbegin(); i!=rp.rend(); ++i) {
			r.push_back(*i);
		}
	}


};
*/


}


