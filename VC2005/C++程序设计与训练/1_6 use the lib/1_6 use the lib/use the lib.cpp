#include "head.h"
#pragma comment(lib, "MinMax3.lib")
#include <iostream>
using namespace std;

int main()
{
	int a,b,c;
	cout<<"�����������������Կո���ָ���"<<endl;
	cin>>a>>b>>c;
	cout<<"������Ϊ"<<max(a,b,c)<<endl;
	cout<<"��С����Ϊ"<<min(a,b,c)<<endl;
    return 0;
}