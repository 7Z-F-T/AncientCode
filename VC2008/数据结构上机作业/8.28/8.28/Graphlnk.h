#ifndef GRAPHLNK_H
#define GRAPHLNK_H

#include"Graph.h"

template<class T,class E>
struct Edge
{
	int dest;				//The other vertix's position
	E cost;
	Edge<T,E>* link;
	Edge(int num = -1,E weight = maxWeight):dest(num),cost(weight),link(NULL)	{}
	bool operator != (Edge<T,E>& R) const	{return dest != R.dest;}
	bool operator == (Edge<T,E>& R) const	{return dest == R.dest;}
};

template<class T,class E>
struct Vertex
{
	T data;					//The ID of the vertex
	Edge<T,E>* adj;			//The head pointer of the adjacency list
};

template<class T,class E>
class Graphlnk:public Graph<T,E>
{
public:
	friend istream& operator >> (istream& in,Graphlnk<T,E>& G)	{G.f_input(in);return in;}
	friend ostream& operator << (ostream& out,Graphlnk<T,E>& G)	{G.f_output(out);return out;}
	Graphlnk(int sz = DefaultVertices);
	~Graphlnk();
	T getValue(int i)	{return (i >= 0 && i < curVertices) ? NodeTable[i].data : 0;}
	E getWeight(int v1, int v2);
	bool insertVertex(const T vertex);
	bool removeVertex(int v);
	bool insertEdge(int v1, int v2, const E cost);
	bool removeEdge(int v1, int v2);
	int getFirstNeighbor(int v);
	int getNextNeighbor(int v, int w);
private:
	Vertex<T,E> *NodeTable;					//Store each head pointer of linklist
	int getVertexPos(const T vertex);
	void f_input(istream& in);
	void f_output(ostream& out);
};

template<class T,class E>
int Graphlnk<T,E>::getVertexPos(const T vertex)
{
	for(int i=0;i<curVertices;i++)
		if(NodeTable[i].data == vertex)		return i;
	return -1;
}

template<class T,class E>
Graphlnk<T,E>::Graphlnk(int sz = DefaultVertices)
{
	maxVertices = sz;
	curVertices = curEdges = 0;
	NodeTable = new Vertex<T,E> [sz];
	if(NodeTable == NULL)
	{
		cerr<<"Allocation Error!"<<endl;
		exit(1);
	}
}

template<class T,class E>
Graphlnk<T,E>::~Graphlnk()
{
	for(int i=0;i<curVertices;i++)
	{
		Edge<T,E>* p=NodeTable[i].adj;
		while(p != NULL)
		{
			NodeTable[i].adj = p->link;
			delete p;
			p = NodeTable[i].adj;
		}
	}
	delete [] NodeTable;
}

template<class T,class E>
E Graphlnk<T,E>::getWeight(int v1, int v2)
{
	if(v1 != -1 && v2 != -1)
	{
		Edge<T,E>* p=NodeTable[v1].adj;
		while(p != NULL)
		{
			if(p->dest == v2)
				return p->cost;
			p=p->link;
		}
	}
	return maxWeight;
}

template<class T,class E>
int Graphlnk<T,E>::getFirstNeighbor(int v)
{
	if(v != -1)
	{
		Edge<T,E>* p = NodeTable[v].adj;
		if(p != NULL)	return p->dest;
	}
	return -1;
}

template <class T,class E>
int Graphlnk<T,E>::getNextNeighbor(int v, int w)		//The return value may not be linked with the vertex w??
{
	if (v != -1)
	{
		Edge<T,E> *p = NodeTable[v].adj;
		while (p != NULL && p->dest != w)	p = p->link;
		if (p != NULL && p->link != NULL)	return p->link->dest;
	}
	return -1;
}

template<class T,class E>
bool Graphlnk<T,E>::insertVertex(const T vertex)
{
	if(curVertices == maxVertices)
	{
		cerr<<"The table of vertics has full!"<<endl;
		return false;
	}
	NodeTable[curVertices].data = vertex;
	NodeTable[curVertices].adj = NULL;
	curVertices++;
	return true;
}

template<class T,class E>
bool Graphlnk<T,E>::removeVertex(int v)
{
	if(v <0 || v>= maxVertices)	{cerr<<"Invaild vertex ID!"<<endl;return false;}
	if(curVertices == 1)
	{cerr<<"This Vertex is the last one vertex in the graph!"<<endl;return false;}
	Edge<T,E> *p,*s,*t;int k;
	while(NodeTable[v].adj != NULL)				//Delete all nodes int the vth adjancent list
	{
		p = NodeTable[v].adj;
		k = p->dest;
		s = NodeTable[k].adj;					//s will point to the symmetrical edge node
		t = NULL;
		while(s != NULL && s->dest != v)
		{
			t = s;s=s->link;
		}
		if(s != NULL)
		{
			if(t == NULL)	NodeTable[k].adj = s->link;		//s is the first item of the adjancent list(free of circulation)
			else			t->link = s->link;
			delete s;
		}
		NodeTable[v].adj = p->link;				//Delete the initial edge from the vth adjancent list
		delete p;
		curEdges--;
	}
	curVertices--;
	NodeTable[v].data = NodeTable[curVertices].data;		//Fill up
	NodeTable[v].adj = NodeTable[curVertices].adj;
	p = NodeTable[v].adj;
	while(p != NULL)
	{
		s = NodeTable[p->dest].adj;
		while(s != NULL)
		{
			if(s->dest == curVertices)		{s->dest = v;break;}
			s = s->link;
		}
	}
	return true;
}

template<class T,class E>
bool Graphlnk<T,E>::insertEdge(int v1, int v2, const E cost)
{
	if(v1 < 0 || v1 >= curVertices || v2 < 0 || v2 >= curVertices)
	{
		cerr<<"Invaild vertex ID!"<<endl;
		return false;
	}
	Edge<T,E>* p = NodeTable[v1].adj, *q = NULL;
	while (p != NULL)
	{
		if(p->dest == v2)
		{
			cerr<<"This edge has existed!"<<endl;
			return false;
		}
		p = p->link;
	}
	p = new Edge<T,E>(v2,cost);q = new Edge<T,E>(v1,cost);
	p->link = NodeTable[v1].adj;NodeTable[v1].adj = p;
	q->link = NodeTable[v2].adj;NodeTable[v2].adj = q;
	curEdges++;
	return true;
}

template<class T,class E>
bool Graphlnk<T,E>::removeEdge(int v1, int v2)
{
	if(v1 < 0 || v1 >= curVertices || v2 < 0 || v2 >= curVertices)
	{
		cerr<<"Invaild vertex ID!"<<endl;
		return false;
	}
	Edge<T,E>* p = NodeTable[v1].adj,*q = NULL;
	while(p != NULL && p->dest != v2)		{q=p;p=p->link;}
	if(p == NULL)	{cerr<<"The edge doesn't existed!"<<endl;return false;}
	q->link = p->link;delete p;
	p = NodeTable[v2].adj;
	while(p->dest != v2)					{q=p;p=p->link;}
	q->link = p->link;delete p;
	curEdges--;
	return true;
}

template<class T,class E>
void Graphlnk<T,E>::f_input(istream &in)
{
	int i,j,k,n,m;
	T e1,e2;
	E weight;
	cout<<"Please input the number of vertexes and edges:";
	in>>n>>m;
	cout<<"Please input the information (ID) of vertexes:"<<endl;
	for(i=0;i<n;i++)
	{
		in>>e1;
		insertVertex(e1);
	}
	cout<<"Please input the information (vertixes and weight) of edges:"<<endl;
	i=0;
	while(i<m)
	{
		in>>e1>>e2>>weight;
		j=getVertexPos(e1);k=getVertexPos(e2);
		if(j == -1 || k == -1 || j == k)
			cerr<<"One or more vertix error,enter aegin!"<<endl;
		else		{if(insertEdge(j,k,weight))			i++;}
	}
}

template<class T,class E>
void Graphlnk<T,E>::f_output(ostream& out)
{
	int i, j, n, m;
	E w;
	n = NumberOfVertices(); m = NumberOfEdges();
	out<<"The number of vertices is:"<<n<<"\nThe number of edges is:"<<m<<endl;
	out<<"These edges are:"<<endl;
	for(i=0;i<n;i++)
	{
		for(j=i+1;j<n;j++)
		{
			w = getWeight(i,j);
			if(w>0 && w<maxWeight)
				out<<'('<<getValue(i)<<','<<getValue(j)<<'<'<<w<<">)\t";
		}
	}
	out<<endl;
}

#endif