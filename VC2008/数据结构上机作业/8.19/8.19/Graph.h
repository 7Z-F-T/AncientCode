#ifndef GRAPH_H
#define GRAPH_H

#include<iostream>
#include<limits>
using namespace std;

const int DefaultVertices = 30;
const int maxWeight = INT_MAX;

template<class T,class E>
class Graph
{
protected:
	int maxVertices;
	int curEdges;
	int curVertices;
	virtual int getVertexPos(const T vertex) =0;
public:
	bool GraphEmpty() const {return curEdges == 0;}
	int NumberOfVertices() const {return curVertices;}
	int NumberOfEdges() const {return curEdges;}
	virtual T getValue(int i) =0;
	virtual E getWeight(int v1,int v) =0;
	virtual int getFirstNeighbor(int v) =0;
	virtual int getNextNeighbor (int v, int w) =0;
      //取邻接顶点 w 的下一邻接顶点
   	virtual bool insertVertex (const T vertex) =0;
      //插入一个顶点vertex
   	virtual bool insertEdge (int v1, int v2, const E cost) =0;
 	  //插入边(v1,v2), 权为cost
   	virtual bool removeVertex (int v) =0;	
      //删去顶点 v 和所有与相关联边
   	virtual bool removeEdge (int v1, int v2) =0;	
      //在图中删去边(v1,v2)
};

#endif