#ifndef LINEARLIST
#define LINEARLIST

#include <iostream>
#include <stdlib.h>
using namespace std;

//���Ա���
template<class T>
class LinearList
{
public:
	LinearList(){}
	~LinearList(){}
	virtual int Size()=0;//���ر�ǿ����ݵĳ���
	virtual T getData(int i) =0;//���ر��i������
	virtual void setData(int i,T x) =0;//���õ�i������
	virtual bool Insert(int i,T x) =0;//�ڵ�i�����ݺ������
	virtual bool Remove(int i) =0;//ɾ����i������
	virtual void input(T endTag) =0;
	virtual void output() =0;

};
#endif