#ifndef STUDENT_H__
#define STUDENT_H__

#include <iostream>
#include <string>
using namespace std;

class Student {
  string id;		// ѧ��
  string name;		// ����
  int    mark;		// �ɼ�
  friend class SortByID;	// �������࣬���ڰ�ѧ������ĺ�������
  friend class SortByMark; 	// �������࣬���ڰ��ɼ�����ĺ�������
  friend class Print;		// �������࣬��������ĺ�������
  friend ostream& operator<<(ostream &out,const Student &x);
  friend istream& operator>>(istream &in,Student &x);

public:
  Student(){}
  Student(const string &strID, const string &strName,int nMark): 
	  id(strID), name(strName), mark(nMark) {}
  Student(const Student &st) : id(st.id), name(st.name),mark(st.mark) {}
  Student& operator = (const Student &st) 
  {
    if (this != &st) { id = st.id; name = st.name; mark = st.mark; }
    return *this;
  }
  
  
class SortByID { 	// ��ѧ�Ŵ�С��������
  public:
    bool operator() (const Student &s1, const Student &s2)
    { return s1.id < s2.id; }
  };

  class SortByMark { 	// ���ɼ��Ӹߵ�������
  public:
    bool operator() (const Student &s1, const Student &s2)
    { return s1.mark > s2.mark; }
  };


}; // end of class Student

ostream& operator<<(ostream &out,const Student &x)
{
out<<x.id<<'\t'<<x.name<<'\t'<<x.mark<<endl;
return out;
}
istream& operator>>(istream &in,Student &x)
{
in>>x.id>>x.name>>x.mark;
return in;
}


#endif // STUDENT_H__
