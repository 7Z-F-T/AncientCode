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
	TreeNode<T>* createTree(ifstream &fin);//ͨ���ļ�����������������
	void outputTree(ofstream &fout,TreeNode<T> *p);//�����Թ������ʽд���ļ���
	void indentedOutput(ofstream &fout, TreeNode<T> *p, int space);//������ʽ���
public:
	Tree(TreeNode<T>* r=NULL, TreeNode<T>* c=NULL):root(r),current(c){}
	TreeNode<T>* FirstChild();//������ָ��
	TreeNode<T>* NextSibling();//������ָ��
	void CreateTree(ifstream &fin);//ͨ���ļ�����������������
	void OutputTree(ofstream &fout);//�����Թ������ʽд���ļ���
	void IndentedOutput(ofstream &fout);//������ʽ���
	
};
