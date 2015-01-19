#include "Sum.h"
#include <iostream>
using namespace std;

int fun1(int n)
{
	return n;
}

double fun2(int n)
{
	return n/10.0;
}


int main()
{
	
	cout<<Sum<int>(100,fun1)<<endl;
	cout<<Sum<double>(100,fun2)<<endl;
	return 0;
}