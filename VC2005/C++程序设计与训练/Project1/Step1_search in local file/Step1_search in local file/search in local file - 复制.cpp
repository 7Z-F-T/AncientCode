////////////////////////////////////////////////////////
//p[i]��*pΪ�洢���ַ�����string����iΪ���±�
//q[i][j]��*qΪ�洢���ʵ�string����iΪ���±꣬jΪ�����±�

#include "head.h"


int main(int argc,char* argv[])
{
	if(argc!=2) {cout<<"�����ʽ����";return -1;}
	char* filename=argv[1];
	ifstream fin(filename);
	if(!fin)
	{cout<<"�ļ���ʧ�ܣ�";return -1;}



	int line=GetLinesAmount(filename);//ȡ����
    string* p=new string[line];//
    
	int i=0;                         //���ж��ļ����ڴ�
	getline(fin,p[0],'\n');
	while(p[i].size()!=0)
	{
		i++;
		getline(fin,p[i],'\n');
	}


	/*for(i=0;p[i].size()!=0;i++)  //��������ļ�
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
