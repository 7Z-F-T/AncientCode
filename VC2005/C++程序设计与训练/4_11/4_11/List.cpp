#include "ListTmpl.h"
#include <iostream>
using namespace std;

int main()
{
    List<double> my,her,his;
	my.Initialize();
	my.AddData(1.1);
	my.AddData(2.2);
	my.AddData(3.3);
	my.AddData(4.4);
	his.Initialize();
	his.AddData(5.5);
	his.AddData(6.6);
	his.AddData(7.7);
	his.AddData(8.8);


    cout<<"my"<<endl;
	double a[4],b[4],c[8];
	for(int i=0;i<4;i++)
	{my.GetData(i,a[i]);
	cout<<a[i];}
	cout<<"size="<<my.GetSize();
	if(my.IsEmpty()==true)
		cout<<"empty";
	else
	    cout<<"not empty"<<endl;
	
	cout<<"her(the copy of my)"<<endl;
	her.Copy(my);
	for(int i=0;i<4;i++)
	{her.GetData(i,b[i]);
	cout<<b[i];}
	cout<<"size="<<her.GetSize();
    if(my.IsEmpty()==true)
		cout<<"empty";
	else
	    cout<<"not empty"<<endl;

    cout<<"my+his"<<endl;
	my.Append(his);
	for(int i=0;i<8;i++)
	{my.GetData(i,c[i]);
	cout<<c[i];}
	cout<<"size="<<my.GetSize();
    if(my.IsEmpty()==true)
		cout<<"empty";
	else
	    cout<<"not empty"<<endl;

	cout<<"reversed my"<<endl;
	my.Reverse();
    for(int i=0;i<8;i++)
	   {my.GetData(i,c[i]);
	cout<<c[i];}
	cout<<"size="<<my.GetSize();
    if(my.IsEmpty()==true)
		cout<<"empty";
	else
	    cout<<"not empty"<<endl;
	return 0;
}