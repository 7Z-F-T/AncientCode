#ifndef STACK_H
#define STACK_H
typedef int ElementType;
class stack;

const int EmptyTOS=-1;
const int MinStackSize=1;

class stack
{
	int Capacity;
	int TopOfStack;
	ElementType* array;
public:
    stack(int MaxElements);
	~stack();
	int isEmpty();
	int isFull();
	void MakeEmpty();
	void Push(ElementType x);
	void Pop();
	ElementType Top();
	ElementType TopAndPop();
	void Output();
};


#endif
