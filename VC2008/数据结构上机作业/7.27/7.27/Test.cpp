#include "BST.cpp"

int main()
{
	BST<int> t;
	int input;
	cout<<"���������ݣ����ͣ�����-1��������"<<endl;
	cin>>input;
	while(input!=-1)
	{
		t.Insert(input);
		cin>>input;
	}
	t.Output();
}