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
	kdTNode(T1& t1, T2& t2, kdTNode<T1,T2>* l=NULL, kdTNode<T1,T2>* r=NULL);//���캯��
	kdTNode(const kdTNode<T1,T2>& tn);//�������캯��
	T1 getKey1();//�õ���ǰ�ڵ��Key1ֵ
	T2 getKey2();//�õ���ǰ�ڵ��Key2ֵ
    kdTNode<T1,T2>* getLeftChild();//�õ���ǰ�ڵ������Ů
	kdTNode<T1,T2>* getRightChild();//�õ���ǰ�ڵ������Ů
};

template<class T1,class T2>
class kdTree
{
private:
	kdTNode<T1,T2>* root;
	bool Search(kdTNode<T1,T2>*& pr, kdTNode<T1,T2>*& pt, T1 val1, T2 val2,int layer);//������ֵ����
public:
	kdTree();//���캯��
	kdTree(const kdTree<T1,T2>& t);//���ƹ��캯��
	int getHeight();//������
	bool Search(kdTNode<T1,T2>*& pr, kdTNode<T1,T2>*& pt, T1 val1, T2 val2);//������ֵ����
	kdTNode<T1,T2>* getLeftChild(kdTNode<T1,T2>* p);//����pָ��ڵ������Ů
	kdTNode<T1,T2>* getRightChild(kdTNode<T1,T2>* p);//����pָ��ڵ������Ů
	kdTNode<T1,T2>* getParent(kdTNode<T1,T2>* p);//����pָ��ڵ�ĸ�ĸ
	bool Insert(T1 val1, T2 val2);//����ֵΪval1,val2�Ľڵ�
	bool Delete(T1 val1, T2 val2);//ɾ��ֵΪval1,val2�Ľڵ�
    kdTNode<T1,T2>* getRoot();//ȡ�ø��ڵ�


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
	if(val1==pt->key1&&val2==pt->key2) return true;//�ҵ�
	else if(layer%2==0)//δ�ҵ�����ǰΪż����
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
	else if(layer%2==1)//δ�ҵ�����ǰΪ������
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
