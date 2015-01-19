#include "head.h"
#pragma comment(lib, "MinMax3.lib")
#include <iostream>
using namespace std;

int main()
{
	int a,b,c;
	cout<<"请输入三个整数（以空格符分隔）"<<endl;
	cin>>a>>b>>c;
	cout<<"最大的数为"<<max(a,b,c)<<endl;
	cout<<"最小的数为"<<min(a,b,c)<<endl;
    return 0;
}