#include "head.h"
#include <iostream>
using namespace std;
int main()
{

	IntLib x;
	
	int n;

	cout<<"����a��ֵ";
	cin>>n;
	x.SetA(n);
	cout<<"����b��ֵ";
	cin>>n;
	x.SetB(n);
	cout<<"����c��ֵ";
	cin>>n;
	x.SetC(n);

	cout<<"a,b,cֵ�ֱ�Ϊ\n"<<x.GetA()<<" "<<x.GetB()<<" "<<x.GetC()<<endl;

	return 0;
}