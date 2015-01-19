#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int main(int argc,char *argv[])
{

	if(argc!=3)
	{
		cout<<"命令参数输入错误（应为count.exe filename string)";
		return -1;
	}

	string temp,str;//将文件内容一串一串读入temp中来查找，str为要查找的字串
	int count=0;//找到的个数
	char* file=argv[1];
	str=argv[2];

	ifstream fin(file);
	if(!fin)
	{
		cout<<"文件打开失败！";
		return -1;
	}
	int strSize=(int)str.size();
	int pos;
    while(fin>>temp)
	{
        pos=0;
	    while(temp.find(str,pos)!=string::npos)
	    {
		    count++;
		    pos=(int)temp.find(str,pos)+strSize;
		}
	}
    cout<<str<<"出现的次数为"<<count<<"次"<<endl;
}