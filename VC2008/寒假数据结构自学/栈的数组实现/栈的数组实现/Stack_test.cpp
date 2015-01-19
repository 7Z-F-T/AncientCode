#include "Stack.h"
#include <iostream>
using namespace std;

int main()
{
    stack a(3);
	cout<<"empty"<<a.isEmpty()<<endl;

	a.Push(1);
	a.Push(2);
	a.Push(3);
	a.Output();
	cout<<"full"<<a.isFull()<<endl;

    a.Pop();
	a.Output();

	cout<<"--a.top is"<<a.TopAndPop()<<endl;
	cout<<"--a.top is"<<a.Top()<<endl;
	cout<<"empty"<<a.isEmpty()<<endl;
	cout<<"full"<<a.isFull()<<endl;

	cout<<"Now Make Empty"<<endl;
	a.MakeEmpty();
	a.Output();





	return 0;
}