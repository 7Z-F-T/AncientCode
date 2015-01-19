#include "Stack.h"
#include <iostream>
using namespace std;

stack::stack(int MaxElements)
{
	if(MaxElements<MinStackSize)
	{
		cout<<"Stack size is too small!"<<endl;
	}
	else 
	array=new ElementType[MaxElements];
	Capacity=MaxElements;
	MakeEmpty();
}
void stack::MakeEmpty()
{
	TopOfStack=EmptyTOS;
}
int stack::isEmpty()
{
	return TopOfStack==EmptyTOS;
}
void stack::Push(ElementType x)
{
	if(isFull())
		cout<<"Stack is full!"<<endl;
	else 
	{
		array[++TopOfStack]=x;
	}
}
int stack::isFull()
{
	return TopOfStack==Capacity-1;
}
void stack::Pop()
{
	if(isEmpty())
		cout<<"Stack is empty!"<<endl;
	else
		TopOfStack--;
}
ElementType stack::Top()
{
	if(isEmpty())
	{
		cout<<"Stack is empty!"<<endl;
		return NULL;
	}
	else
		return array[TopOfStack];
}
ElementType stack::TopAndPop()
{
	if(isEmpty())
	{
		cout<<"Stack is empty!"<<endl;
		return NULL;
	}
	else
		return array[TopOfStack--];
}
void stack::Output()
{
	if(TopOfStack==EmptyTOS)
		cout<<"Stack is empty!"<<endl;
	else
	for(int i=0;i<=TopOfStack;i++)
		cout<<array[i]<<endl;
}
stack::~stack()
{
	delete [] array;
}