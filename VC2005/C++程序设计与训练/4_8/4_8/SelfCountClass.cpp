#include <iostream>
#include "SelfCountClass.h"
using namespace std;

void apple()
{
	SelfCountClass c;
	cout<<c.GetObjectCount();
}

int main()
{
	SelfCountClass a;
	SelfCountClass b;
	cout<<a.GetObjectCount();
	apple();
	cout<<a.GetObjectCount();


	return 0;
}

