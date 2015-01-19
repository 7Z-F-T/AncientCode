////////////////////////////////////////////////////////
//p[i]或*p为存储行字符串的string对象，i为行下标
//q[i][j]或*q为存储单词的string对象，i为行下标，j为单词下标

#include "head.h"


int main(int argc,char* argv[])
{
	if(argc!=2) {cout<<"命令格式错误！";return -1;}
	char* filename=argv[1];
	ifstream fin(filename);
	if(!fin)
	{cout<<"文件打开失败！";return -1;}



	int line=GetLinesAmount(filename);//取行数
    string* p=new string[line];//
    
	int i=0;                         //按行读文件入内存
	getline(fin,p[0],'\n');
	while(p[i].size()!=0)
	{
		i++;
		getline(fin,p[i],'\n');
	}


	/*for(i=0;p[i].size()!=0;i++)  //按行输出文件
	{cout<<p[i]<<endl;}*/









	return 0;
}

int GetLinesAmount(const char* filename)
{
	int count=0;
    ifstream fintemp(filename);
	string temp;
	getline(fintemp,temp,char(0));
    int pos=0;
    while(temp.find("\n",pos)!=string::npos)
	    {
		    count++;
		    pos=(int)temp.find("\n",pos)+1;
		}
	
	return count+1;
}

int GetWordsAmount(string str)
{   
	char seps[]=" \t\n,.1234567890";
	int count=0;
	char* Word;
    Word=strtok(&str[0],seps);
     while(Word!=NULL)
	   {
		count++;
        Word=strtok(NULL,seps);
	   }
	return count;
}
