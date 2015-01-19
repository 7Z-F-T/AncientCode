#include "head.h"
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
	
	int nMark=atoi(argv[2]);
    vector<Student> Result;
	while(fin>>id>>name>>mark)
		Result.push_back(Student(id,name,mark));
	for_each(Result.begin(),Result.end(),FilterDisp(nMark));
	
	return 0;
}
