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
		cout<<"����������(1-5)"<<endl;
	    cin>>nType;
		if(nType==-1) break;
		else 
		{
		cout<<"����������"<<endl;
	    cin>>iNum;
		Sum=Sum+Add(nType,iNum);
		}
	}
	cout<<"��Ӫҵ��Ϊ"<<Sum<<endl;
	return 0;
}