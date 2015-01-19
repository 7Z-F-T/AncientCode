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
	friend bool SortByMark(const Student &x1,const Student &x2);
	friend bool ThenSortByID(const Student &x1,const Student &x2);
public:
	Student(const string &id1,const string name1,const int mark1):
	  id(id1),name(name1),mark(mark1){}
	  void Show() 
	  {
		cout<<id<<"\t"<<name<<"\t"<<mark<<endl;
	  }
	  int& GetMark() {return mark;}

};

bool SortByMark(const Student &x1,const Student &x2)
{
	return (x1.mark>x2.mark);
}
bool ThenSortByID(const Student &x1,const Student &x2)
{
	if(x1.mark!=x2.mark) return false;
	else if(x1.id<x2.id) return true;
}
#endif