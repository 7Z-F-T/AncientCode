#ifndef LINEARLIST
#define LINEARLIST

#include <iostream>
#include <stdlib.h>
using namespace std;

//线性表类
template<class T>
class LinearList
{
public:
	LinearList(){}
	~LinearList(){}
	virtual int Size()=0;//返回表非空数据的长度
	virtual T getData(int i) =0;//返回表第i个数据
	virtual void setData(int i,T x) =0;//设置第i个数据
	virtual bool Insert(int i,T x) =0;//在第i个数据后面插入
	virtual bool Remove(int i) =0;//删除第i个数据
	virtual void input(T endTag) =0;
	virtual void output() =0;

};
#endif