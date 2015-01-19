#ifndef HEAD_H
#define HEAD_H
#include <string>
#include <algorithm>
#include <iostream>
using namespace std;

class Student
{
	string id;
	string name;
	int mark;
	friend class FilterDisp;
public:
	Student(const string &id1,const string name1,const int mark1):
	  id(id1),name(name1),mark(mark1){}
};

class FilterDisp
{
	int val;
public:
	FilterDisp(int n):val(n){}
    void operator()(const Student &x)
	{
		if(x.mark<val) cout<<x.id<<" "<<x.name<<" "<<x.mark<<endl;
	}
};

#endif