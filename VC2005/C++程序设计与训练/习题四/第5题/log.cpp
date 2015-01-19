#include "log.h"
#include <iostream>
using namespace std;


Print::Print(char* pName)
{
	if(Display){
	FuncName=pName;
	cout<<"Enter Function(Name:"<<FuncName<<")"<<endl;}
}
Print::~Print()
{
	if(Display){cout<<"Exit Function(Name:"<<FuncName<<")"<<endl;}
}

void Print::SetDisplay(bool dis)
{
	if(dis==true) Display=1;
	else Display=0;
}


