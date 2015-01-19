#include "Student.h"
#include <string>
#include <vector>
#include <fstream>
#include <algorithm>
#include <iterator>
using namespace std;

int main() {
  ifstream fin("exam_result.txt");	// ����ɼ���
  ofstream fout1("by_id.txt");
  ofstream fout2("by_mark.txt");  
  vector<Student> Result;

  istream_iterator<Student> is(fin);
  istream_iterator<Student> eof;
  ostream_iterator<Student> os1(fout1);
  ostream_iterator<Student> os2(fout2);


  copy(is,eof,back_inserter(Result));
  fin.close();
  // ��ѧ������������ļ�by_id.txt
  sort(Result.begin(), Result.end(), Student::SortByID());
  copy(Result.begin(),Result.end(),os1);
  // ���ɼ�����������ļ�by_mark.txt
  sort(Result.begin(), Result.end(), Student::SortByMark());
  copy(Result.begin(),Result.end(),os2);
  return 0;
}
