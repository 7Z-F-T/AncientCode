#include "log.h"
#include <iostream>
using namespace std;

Print::Print(char* pName)
{
	FuncName=pName;
	cout<<"Enter Fuction(Name:"<<FuncName<<")"<<endl;
}
Print::~Print()
{
	cout<<"Exit Function(Name:"<<FuncName<<")"<<endl;
}

