#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int main(int argc,char *argv[])
{

	if(argc!=3)
	{
		cout<<"��������������ӦΪcount.exe filename string)";
		return -1;
	}

	string temp,str;//���ļ�����һ��һ������temp�������ң�strΪҪ���ҵ��ִ�
	int count=0;//�ҵ��ĸ���
	char* file=argv[1];
	str=argv[2];

	ifstream fin(file);
	if(!fin)
	{
		cout<<"�ļ���ʧ�ܣ�";
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
    cout<<str<<"���ֵĴ���Ϊ"<<count<<"��"<<endl;
}