#include "SafeString.h"
#include <iostream>
#include <string.h>
using namespace std;

SafeString::SafeString()
{
	a=new char[1];
	*a='\0';
}
SafeString::SafeString(char *string)
{
	size=(int)strlen(string);
	a=new char[size+1];
	strcpy(a,string);
}

SafeString::~SafeString()
{
	delete a;
}
void SafeString::Show()
{
	cout<<a;
}
int SafeString::Length()
{
	return size;
}
void SafeString::Assign(char * string)
{
	delete [] a;
	size=(int)strlen(string);
	a=new char[size+1];
	strcpy(a,string);
}
void SafeString::Append(char * string)
{
    size=size+strlen(string);
	char* b=new char[size+1];
	strcpy(b,a);
	strcat(b,string);
	Assign(b);
	delete [] b;
}
int SafeString::Find(char ch, int begin)
{
	int position=0;
    char* p=a;
    p=p+begin;
	while(*p!='\0')
	{
		if(*p==ch)
		return position;
		else
		{p++;position++;}
	}
	position=-1;
	return position;
}
void SafeString::Set(char ch, int index)
{
	char* p=a;
	p=p+index;
	*p=ch;
}
char SafeString::Get(int index)
{
	char* p=a;
	p=p+index;
	return(*p);
}



int main()
{
	SafeString str("gay");
    str.Show();
	cout<<"字符长度为"<<str.Length()<<endl;

	cout<<"现在重新赋值为lesbian"<<endl;
	str.Assign("lesbian");
	str.Show();
	cout<<"字符长度为"<<str.Length()<<endl;

	cout<<"现在在后面连接gay"<<endl;
	str.Append("gay");
	str.Show();
	cout<<"字符长度为"<<str.Length()<<endl;

	cout<<"现在寻找字母i,从头开始"<<endl;
	int pos=str.Find('i',0);
	if(pos==-1)
	cout<<"没找到!"<<endl;
	else
	cout<<"字母i的下标为"<<pos<<endl;


	cout<<"现在寻找字母x,从头开始"<<endl;
	pos=str.Find('x',0);
	if(pos==-1)
	cout<<"没找到!"<<endl;
	else
	cout<<"字母x的下标为"<<pos<<endl;

	cout<<"现在设置下标为3的字符为@"<<endl;
	str.Set('@',3);
	str.Show();
	cout<<"字符长度为"<<str.Length()<<endl;

	cout<<"现在取下标为5的字符"<<endl;
	cout<<str.Get(5)<<endl;

	return 0;
}