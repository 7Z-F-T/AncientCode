#include <iostream>
#include <string>
#include <fstream>
#include <string.h>
#include <stdio.h>
using namespace std;

int main(int argc,char* argv[])
{
	if(argc!=2)
	{
		cout<<"命令输入格式错误，应为term.exe filename"<<endl;
		return -1;
	}

	char* file=argv[1];
	ifstream fin(file);
	if(!fin)
	{
		cout<<"文件打开失败！"<<endl;
		return -1;
	}
    string StoreWord;
	char temp[50];
	char seps[]=" \t\n,.1234567890";
	int count=0;
	char* Word;
	

	while(fin>>temp)
	{	
	  Word=strtok(temp,seps);
	  while(Word!=NULL)
	   {
		if(StoreWord.find(Word)==string::npos)
		    {StoreWord.append(Word);StoreWord.append(" ");count++;}
        Word=strtok(NULL,seps);
	   }
	}
	cout<<"单词总数为"<<count<<endl;
	cout<<"分别是如下单词"<<StoreWord<<endl;
}
		


