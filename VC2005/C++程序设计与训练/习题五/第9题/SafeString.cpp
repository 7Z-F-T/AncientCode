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
SafeString::SafeString(const SafeString &x)
{
	size=(int)strlen(x.a);
	a=new char[size+1];
	strcpy(a,x.a);
}


SafeString::~SafeString()
{
	delete a;
}
void SafeString::Show() const
{
	cout<<a<<endl;
}
int SafeString::Length() const
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
int SafeString::Find(char ch, int begin) const
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
char SafeString::Get(int index) const
{
	char* p=a;
	p=p+index;
	return(*p);
}
SafeString& SafeString::operator=(const SafeString &x)
{
	Assign(x.a);
	return *this;
}
SafeString& SafeString::operator=(char* p)
{
	Assign(p);
	return *this;
}
SafeString& SafeString::operator+=(const SafeString &x)
{
	Append(x.a);
	return *this;
}
SafeString& SafeString::operator+=(char *p)
{
	Append(p);
	return *this;
}

SafeString operator+(const SafeString &x1,const SafeString &x2)
{
	char* p=new char[strlen(x1.a)+strlen(x2.a)+1];
	strcpy(p,x1.a);
	strcat(p,x2.a);
	SafeString temp(p);
	delete [] p;
	return temp;
}




int main()
{
         
	SafeString str1, str2;                            cout<<"str1=";str1.Show();cout<<"str2=";str2.Show();
	str1="abc";
	str2=str1;                                        cout<<"str1=";str1.Show();cout<<"str2=";str2.Show();

	
	SafeString str3("str3"), str4("str4");            cout<<"str3=";str3.Show();cout<<"str4=";str4.Show();
	str3+="abc";
	str4+=str3;                                       cout<<"str3=";str3.Show();cout<<"str4=";str4.Show();


			
	SafeString str5("str5"),str6;    cout<<"str5=";str5.Show();cout<<"str6=";str6.Show();
	str6="abc_"+str5;                cout<<"str5=";str5.Show();cout<<"str6=";str6.Show();
	str6=str5+str6;                  cout<<"str5=";str5.Show();cout<<"str6=";str6.Show();
	str6=str6+"_def";                cout<<"str5=";str5.Show();cout<<"str6=";str6.Show();



	return 0;
}