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
      //ȡ�ڽӶ��� w ����һ�ڽӶ���
   	virtual bool insertVertex (const T vertex) =0;
      //����һ������vertex
   	virtual bool insertEdge (int v1, int v2, const E cost) =0;
 	  //�����(v1,v2), ȨΪcost
   	virtual bool removeVertex (int v) =0;	
      //ɾȥ���� v ���������������
   	virtual bool removeEdge (int v1, int v2) =0;	
      //��ͼ��ɾȥ��(v1,v2)
};

#endif