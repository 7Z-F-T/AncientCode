#include "Add.h"
#include <iostream>
using namespace std;
int main()
{
	int nType;
    int iNum;
    double Sum=0;
    nType=1;
	while(nType!=-1)
	{
		cout<<"请输入类型(1-5)"<<endl;
	    cin>>nType;
		if(nType==-1) break;
		else 
		{
		cout<<"请输入数量"<<endl;
	    cin>>iNum;
		Sum=Sum+Add(nType,iNum);
		}
	}
	cout<<"总营业额为"<<Sum<<endl;
	return 0;
}