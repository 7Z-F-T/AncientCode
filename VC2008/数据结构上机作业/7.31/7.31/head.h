#include <iostream>
using namespace std;

template<class T1,class T2>
class kdTNode
{
private:
	T1 key1;
	T2 key2;
	kdTNode<T1,T2> *left, *right;
public:
	kdTNode(T1& t1, T2& t2, kdTNode<T1,T2>* l=NULL, kdTNode<T1,T2>* r=NULL);//构造函数
	kdTNode(const kdTNode<T1,T2>& tn);//拷贝构造函数
	T1 getKey1();//得到当前节点的Key1值
	T2 getKey2();//得到当前节点的Key2值
    kdTNode<T1,T2>* getLeftChild();//得到当前节点的左子女
	kdTNode<T1,T2>* getRightChild();//得到当前节点的右子女
};

template<class T1,class T2>
class kdTree
{
private:
	kdTNode<T1,T2>* root;
	bool Search(kdTNode<T1,T2>*& pr, kdTNode<T1,T2>*& pt, T1 val1, T2 val2,int layer);//按给定值搜索
public:
	kdTree();//构造函数
	kdTree(const kdTree<T1,T2>& t);//复制构造函数
	int getHeight();//求树高
	bool Search(kdTNode<T1,T2>*& pr, kdTNode<T1,T2>*& pt, T1 val1, T2 val2);//按给定值搜索
	kdTNode<T1,T2>* getLeftChild(kdTNode<T1,T2>* p);//查找p指向节点的左子女
	kdTNode<T1,T2>* getRightChild(kdTNode<T1,T2>* p);//查找p指向节点的右子女
	kdTNode<T1,T2>* getParent(kdTNode<T1,T2>* p);//查找p指向节点的父母
	bool Insert(T1 val1, T2 val2);//插入值为val1,val2的节点
	bool Delete(T1 val1, T2 val2);//删除值为val1,val2的节点
    kdTNode<T1,T2>* getRoot();//取得根节点


};

template<class T1,class T2>
bool kdTree<T1,T2>::Search(kdTNode<T1,T2>*& pr, kdTNode<T1,T2>*& pt, T1 val1, T2 val2)
{
	if(root==NULL)
	{
		pt=NULL;pr=NULL;return false;
	}
	else
	{
		pt=root;pr=NULL;
		if(Search(pr,pt,val1,val2,0)) return true;
		else return false;
	}
}

template<class T1,class T2>
bool kdTree<T1,T2>::Search(kdTNode<T1,T2>*& pr, kdTNode<T1,T2>*& pt, T1 val1, T2 val2,int layer)
{
	if(pt==NULL) return false;
	if(val1==pt->key1&&val2==pt->key2) return true;//找到
	else if(layer%2==0)//未找到，当前为偶数层
	{
		if(val1<pt->key1)
		{
			pr=pt;
			pt=pt->left;
			return Search(pr,pt,val1,val2,layer+1);
		}
		else
		{
			pr=pt;
			pt=pt->right;
			return Search(pr,pt,val1,val2,layer+1);
		}
	}
	else if(layer%2==1)//未找到，当前为奇数层
	{
		if(val2<pt->key2)
		{
			pr=pt;
			pt=pt->left;
			return Search(pr,pt,val1,val2,layer+1);
		}
		else 
		{
			pr=pt;
			pt=pt->right;
			return Search(pr,pt,val1,val2,layer+1);
		}
	}
}
