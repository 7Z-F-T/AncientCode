#include <iostream>
#include <fstream>
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
	TreeNode<T> *root, *current;
	TreeNode<T>* createTree(ifstream &fin);//通过文件读入广义表来创建树
	void outputTree(ofstream &fout,TreeNode<T> *p);//将树以广义表形式写入文件中
	void indentedOutput(ofstream &fout, TreeNode<T> *p, int space);//缩格形式输出
public:
	Tree(TreeNode<T>* r=NULL, TreeNode<T>* c=NULL):root(r),current(c){}
	TreeNode<T>* FirstChild();//返回其指针
	TreeNode<T>* NextSibling();//返回其指针
	void CreateTree(ifstream &fin);//通过文件读入广义表来创建树
	void OutputTree(ofstream &fout);//将树以广义表形式写入文件中
	void IndentedOutput(ofstream &fout);//缩格形式输出
	
};
