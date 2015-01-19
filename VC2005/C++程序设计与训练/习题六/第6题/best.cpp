#include "Student.h"
#include <stdlib.h>
#include <fstream>
#include <vector>

int main(int argc,char* argv[])
{
	string id,name;
	int mark;
	if(argc!=3) {cout<<"命令输入错误！"<<endl;return -1;}
    char* filename=argv[1];
	char* strMark=argv[2];
	fstream fin(filename);
	if(!fin) {cout<<"文件打开错误！"<<endl;return -1;}
	
	int amount=atoi(argv[2]);
    vector<Student> Result;
	while(fin>>id>>name>>mark)
		Result.push_back(Student(id,name,mark));
   sort(Result.begin(),Result.end(),SortByMark);
   sort(Result.begin(),Result.end(),ThenSortByID);
   vector<Student>::iterator iter=Result.begin();
   for(int i=0;i<amount;i++)
   {
	   if(amount>Result.size())
	   {
		   cout<<"没有这么多数据啊！"<<endl;
		   return -1;
	   }
       iter->Show();
	   iter++;
   }

   if(iter==Result.end()) return 0;
   while(iter->GetMark()==(--iter)->GetMark())
   {
	   ++iter;
	   iter->Show();
	   iter++;
   }

	   
	return 0;
}
