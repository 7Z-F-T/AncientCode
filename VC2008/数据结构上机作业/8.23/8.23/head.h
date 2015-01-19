#ifndef HEAD_H
#define HEAD_H
#include <iostream>
#include <vector>
#include <fstream>
#include <string>
#include <algorithm>
using namespace std;

class edge
{
public:
	int head;
	int tail;
	int valve;
	edge(int h,int t,int v):head(h),tail(t),valve(v){}
	edge(){}

};

class sortedge//º¯Êý¶ÔÏó
{
public:
	bool operator()(const edge &x1,const edge &x2)
	{
		return x1.valve>x2.valve;
	}
};

bool isLoop(vector<edge> &);

#endif
