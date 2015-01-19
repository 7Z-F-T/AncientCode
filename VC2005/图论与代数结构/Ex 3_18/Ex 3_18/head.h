#ifndef HEAD_H
#define HEAD_H

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

class leaf
{
public:
	int number;
	int value;
	int valueBackup;
	bool isleaf;
	void output()
	{
		if(leftleaf==-1&&rightleaf==-1)
			cout<<"Node:"<<number<<"(leaf,value="<<valueBackup<<")"<<endl;
		else 
			cout<<"Node:"<<number<<"-->"<<leftleaf<<","<<rightleaf<<endl;;
	}
	int leftleaf;
	int rightleaf;
	//leaf(int num,int val):number(num),value(val){}
	leaf(bool is,int num,int val,int l=-1,int r=-1):isleaf(is),number(num),value(val),leftleaf(l),rightleaf(r){}
	
	friend class sortleaf;
	
};

class sortleaf
{
public:
	bool operator()(leaf &x1,leaf &x2)
	{
		return x1.value<x2.value;
	}
};

#endif
