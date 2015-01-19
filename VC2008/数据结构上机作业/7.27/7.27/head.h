#include <iostream>
using namespace std;

template<class T>
struct BSTNode
{
	T key;
	int count;
	BSTNode<T> *left, *right;
	BSTNode(T d, BSTNode<T>* l=NULL, BSTNode<T>* r=NULL):key(d),left(l),right(r),count(1){}

};

template<class T>
class BST
{
	BSTNode<T>* root;
	bool Insert(T& el, BSTNode<T>* &p);
	void Output(BSTNode<T> *p);
public:
	BST():root(NULL){}
	bool Insert(T& el) {return Insert(el,root);}
	void Output(){Output(root);}
};