#include "LinearList.h"
#include "SeqList.h"
#include "2.12.h"



int main()
{
	SeqList<int> A;
    SeqList<int> B;
	A.input(-1);
	B.input(-1);
	SeqList<int> C;
	C.Union(A,B);
	C.output();
}

