#include "head.h"

template<class T>
TreeNode<T>* Tree<T>::FirstChild()
{
	if(current!=NULL) return current->firstChild;
	else return NULL;
}

template<class T>
TreeNode<T>* Tree<T>::NextSibling()
{
	if(current!=NULL) return current->nextSibling;
	else return NULL;
}