#include <iostream>

template<class T>
struct BinTreeNode
{
	T data;
	BinTreeNode<T>* leftChild;
    BinTreeNode<T>* rightChild;
	BinTreeNode():leftChild(NULL),rightChild(NULL){}
	BinTreeNode(T x, BinTreeNode<T>* l=NULL, BinTreeNode<T>* r=NULL):data(x),leftChild(l),rightChild(r){}
};

template<class T>
class BinaryTree
{
public:
	BinaryTree(BinTreeNode<T>* r=NULL):root(r){}
	BinaryTree(T value):RefValue(value),root(NULL){}
	bool isEmpty() {return root==NULL?true:false;}
	BinTreeNode<T>* LeftChild(BinTreeNode<T>* current){return (current!=NULL)?current->leftChild:NULL;}
    BinTreeNode<T>* RightChild(BinTreeNode<T>* current){return (current!=NULL)?current->rightChild:NULL;}
	BinTreeNode<T>* getRoot(){return root;}
protected:
	BinTreeNode<T>* root;
	T RefValue;//数据输入停止标志
};