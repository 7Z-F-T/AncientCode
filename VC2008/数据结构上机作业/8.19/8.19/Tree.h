#ifndef TREE_H
#define TREE_H

#include<iostream>
using namespace std;

template<class T,class E>
struct TreeNode
{
	T data;
	TreeNode<T,E> *firstChild,*nextSibling;
	TreeNode(T value=0,TreeNode<T,E> *l=NULL,TreeNode<T,E> *r=NULL)
	{data = value;firstChild=l;nextSibling=r;}
};

template<class T,class E>
class Tree
{
public:
	Tree(TreeNode<T,E>* p = NULL):root(p),current(p)	{}
	bool Root ();					//置根结点为当前结点
	bool IsEmpty ()	{ return root == NULL; }
	bool FirstChild ();					//将当前结点的第一个子女置为当前结点
	bool NextSibling ();					//将当前结点的下一个兄弟置为当前结点
	bool Parent ();					//将当前结点的双亲置为当前结点
	bool Find (T value);					//搜索含value的结点, 使之成为当前结点
	void preOrder(void (*visit)(TreeNode<T,E> *t));		//先根次序遍历
	void postOrder(void (*visit)(TreeNode<T,E> *t));		//后根次序遍历
	void levelOrder(void (*visit)(TreeNode<T,E> *t));		//广度优先遍历
protected:
	TreeNode<T,E> *root,*current;
	int Find (TreeNode<T,E> *p, T value);			//在以p为根的树中搜索value
	void RemovesubTree (TreeNode<T,E> *p);		//删除以p为根的子树
	bool FindParent (TreeNode<T,E> *sub,TreeNode<T,E> *tar);	//在根为*sub的树中找*tar的双亲, 并置为当前结点
};

template <class T,class E>
bool Tree<T,E>::Root ()
{
	current=root;
	return (current != NULL);		//Indicate whether tree is empty
}

template<class T,class E>
bool Tree<T,E>::FirstChild()
{
	if (current && current->firstChild)		{current = current->firstChild;return true;}
	current = NULL;return false;
}

template<class T,class E>
bool Tree<T,E>::NextSibling()
{
	if (current && current->nextSibling)		{current = current->nextSibling;return true;}
	current = NULL;return false;
}

template<class T,class E>
bool Tree<T,E>::Parent()
{
	current=FindParent(root,current);
	return (current != NULL);
}

template<class T,class E>
bool Tree<T,E>::FindParent(TreeNode<T,E> *sub,TreeNode<T,E> *tar)
{
	if(sub == NULL)		{current=NULL;return false;}
	TreeNode<T,E> *q=sub->firstChild;
	while(q!=NULL && q!=tar)
	{
		if(FindParent(q,tar))		return true;
		else			q=q->nextSibling;
	}
	if(q!=NULL && q == tar)	{current=q;return true;}
	else			{current=NULL;return false;}
}
	
template<class T,class E>
void Tree<T,E>::preOrder(void (*visit)(TreeNode<T,E> *t))
{
	if(current == NULL || IsEmpty())	return;
	visit(current);
	TreeNode<T,E> *p=current;			//For temporary storage,inline reservation
	current=current->firstChild;
	while(current != NULL)
	{
		preOrder(visit);
		current=current->nextSibling;
	}
	current = p;
}

template<class T,class E>
void Tree<T,E>::postOrder(void (*visit)(TreeNode<T,E> *t))
{
	if(current == NULL || IsEmpty())	return;
	TreeNode<T,E> *p=current;
	current=current->firstChild;
	while(current != NULL)
	{
		postOrder(visit);
		current=current->nextSibling;
	}
	current = p;
	visit(current);
}

template<class T,class E>
void Tree<T,E>::levelOrder(void (*visit)(TreeNode<T,E> *t))
{
	if(current == NULL || IsEmpty())	return;
	LinkedQueue<TreeNode<T,E>* > Q;
	TreeNode<T,E> *p=current;
	Q.Enqueue(current);
	while(!Q.IsEmpty())
	{
		Q.DeQueue(current);
		visit(current);
		current = current->firstChild;
		while(current != NULL)
		{
			Q.DeQueue(current);
			current = current ->nextSibling;
		}
	}
	current = p;
}

#endif