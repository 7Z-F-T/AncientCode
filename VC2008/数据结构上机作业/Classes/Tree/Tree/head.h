#include <iostream>
using namespace std;

template<class T>
struct TreeNode
{
	T data;
	TreeNode<T> *firstChild, *nextSibling;
	TreeNode(T value=0,TreeNode<T> *fc=NULL, TreeNode<T> *ns=NULL):data(value),firstChild(fc),nextSibling(ns){}

};

template<class T>
class Tree
{
private:
	TreeNode *root, *current;
public:
	TreeNode<T>* FirstChild();//返回其指针
	TreeNode<T>* NextSibling();//返回其指针
	
};
