#include "LinearList.h"
#include "SeqList.h"
#include "LinkedList.h"


int main()
{
	/*List<char> list;
	list.inputRear('#');
	list.output();
	cout<<list.Size()<<endl;
	cout<<"insert X after 2-th elem"<<endl;
	list.Insert(2,'X');
	list.output();
    cout<<"Remove 4-th elem"<<endl;
	list.Remove(4);
	list.output();
	cout<<"get 5-th data"<<endl;
	cout<<list.getData(5)<<endl;
	cout<<"set 5-th data to Y"<<endl;
	list.setData(5,'Y');
	list.output();*/
	SeqList<int> a(10);
	a.input(0);
	a.reSize(20);
	a.output();


	return 0;
}