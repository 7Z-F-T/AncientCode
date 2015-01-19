#include "head.h"
#include <iostream>
using namespace std;
int main()
{

	IntLib x;
	
	int n;

	cout<<"输入a的值";
	cin>>n;
	x.SetA(n);
	cout<<"输入b的值";
	cin>>n;
	x.SetB(n);
	cout<<"输入c的值";
	cin>>n;
	x.SetC(n);

	cout<<"a,b,c值分别为\n"<<x.GetA()<<" "<<x.GetB()<<" "<<x.GetC()<<endl;

	return 0;
}