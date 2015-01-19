#include "head.h"

template<class T>
bool BST<T>::Insert(T &el, BSTNode<T>* &p)
{
	if(p==NULL)
	{
		p=new BSTNode<T>(el);
		return true;
	}
	else if(el<p->key) Insert(el,p->left);
	else if(el>p->key) Insert(el,p->right);
	else p->count++;
	return true;
}

template<class T>
void BST<T>::Output(BSTNode<T> *p)
{
	if(p==NULL) 
	{
		cout<<"NULL";
		return;
	}
	else
	{
		cout<<p->key<<'*'<<p->count;;
		if(p->left!=NULL||p->right!=NULL)
		{
			cout<<'(';
			Output(p->left);
			cout<<',';
			Output(p->right);
			cout<<')';
		}
	}
}