#include "BinaryTree.h"
using namespace std;
int N;
int* t=NULL;

template<class T>
void CreateChildren(BinTreeNode<T>* parent,int n)//n为节点编号
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
		if(parent->leftChild!=NULL||parent->rightChild!=NULL)//若左，右子树至少存在一个不为空，就打印括号，否则就不打印括号了，这样一来不会出现"(,)"这样的输出
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
	//T[n]中存放的是完全二叉树中第n+1号节点的值
	BinaryTree<int> tree(new BinTreeNode<int>(t[0]));
	CreateChildren(tree.getRoot(),1);
    Output(tree.getRoot());
	return 0;
}