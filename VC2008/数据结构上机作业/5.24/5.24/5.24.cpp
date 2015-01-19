#include "BinaryTree.h"
using namespace std;
int N;
int* t=NULL;

template<class T>
void CreateChildren(BinTreeNode<T>* parent,int n)//nΪ�ڵ���
{
	if(n*2<=N)
	    parent->leftChild=new BinTreeNode<T>(t[n*2-1]);
	else return;
	if(n*2+1<=N)
	    parent->rightChild=new BinTreeNode<T>(t[n*2]);
	else return;
	CreateChildren(parent->leftChild,n*2);
	CreateChildren(parent->rightChild,n*2+1);
}
template<class T>
void Output(BinTreeNode<T>* parent)
{
	if(parent!=NULL)
	{
        cout<<parent->data;
		if(parent->leftChild!=NULL||parent->rightChild!=NULL)//�������������ٴ���һ����Ϊ�գ��ʹ�ӡ���ţ�����Ͳ���ӡ�����ˣ�����һ���������"(,)"���������
		{
	        cout<<"(";
            Output(parent->leftChild);
            cout<<",";
            Output(parent->rightChild);
            cout<<")";
		}
	}
	else return;
}
int main()
{
	cin>>N;
	t=new int[N];
	for(int i=0;i<N;i++)
		cin>>t[i];
	//T[n]�д�ŵ�����ȫ�������е�n+1�Žڵ��ֵ
	BinaryTree<int> tree(new BinTreeNode<int>(t[0]));
	CreateChildren(tree.getRoot(),1);
    Output(tree.getRoot());
	return 0;
}