#include "BST.cpp"

int main()
{
	BST<int> t;
	int input;
	cout<<"请输入数据（整型），以-1做结束符"<<endl;
	cin>>input;
	while(input!=-1)
	{
		t.Insert(input);
		cin>>input;
	}
	t.Output();
}