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
		cout<<"���������ʽ����ӦΪterm.exe filename"<<endl;
		return -1;
	}

	char* file=argv[1];
	ifstream fin(file);
	if(!fin)
	{
		cout<<"�ļ���ʧ�ܣ�"<<endl;
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
	cout<<"��������Ϊ"<<count<<endl;
	cout<<"�ֱ������µ���"<<StoreWord<<endl;
}
		


