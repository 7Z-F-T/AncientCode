#ifndef LINKEDLIST
#define LINKEDLIST

#include "LinearList.h"


///////////////////////////////////////////////
//��ͷ����������
///////////////////////////////////////////////

//����ڵ���
template<class T>
struct LinkNode
{
	T data;//�ڵ�����
	LinkNode<T>* link;
	LinkNode(LinkNode<T>* ptr=NULL){link=ptr;}//���캯��
	LinkNode(T item, LinkNode<T>* ptr=NULL){link=ptr;data=item;}//���캯��
};
//������
template<class T>
class List:public LinearList<T>
{
protected:
	LinkNode<T>* first;//ֻ���׵�ַ�����������С��ͨ��������ã����ﲻ�档
public:
	List(){first=new LinkNode<T>();}
	~List(){makeEmpty();}
	int Size();
	LinkNode<T>* Locate(int i);//���ص�i�����ĵ�ַ
	T getData(int i);
	void setData(int i,T x);
	bool Insert(int i, T x);
	bool Remove(int i);
	void input(T endTag);//ʵ����ִ�е���inputRear
	void inputRear(T endTag);//��ǰ�����
    void inputFront(T endTag);//��ǰ�����
	void output();
	void makeEmpty();
};

//Other implementions
template<class T>
void List<T>::makeEmpty()
{
	LinkNode<T>* p;
	while(first->link!=NULL)
	{
		p=first->link;
		first->link=p->link;
		delete p;
	}
}
template<class T>
int List<T>::Size()
{
	int n=0;
	LinkNode<T>* p=first->link;
	while(p!=NULL)
	{
        n++;
		p=p->link;
	}
	return n;
}
template<class T>
LinkNode<T>* List<T>::Locate(int i)
{
	if(i<0||i>Size()) return NULL;
	LinkNode<T>* p=first;
	for(int j=1;j<=i;j++)
		p=p->link;
	return p;

}
template<class T>
T List<T>::getData(int i)
{
	if(i<=0||i>Size()) return NULL;
	LinkNode<T>* p=Locate(i);
	return p->data;

}
template<class T>
void List<T>::setData(int i,T x)
{
	if(i<=0||i>Size()) return ;
	LinkNode<T>* p=Locate(i);
	p->data=x;

}

template<class T>
bool List<T>::Insert(int i, T x)
{
    if(i<0||i>Size()) return false;
	LinkNode<T>* p=Locate(i);
	LinkNode<T>* newNode=new LinkNode<T>(x);
	newNode->link=p->link;
	p->link=newNode;
	return true;

}
template<class T>
bool List<T>::Remove(int i)
{
    if(i<=0||i>Size()) return false;
	LinkNode<T>* p=Locate(i-1);//ע��p��ͷ��������i-1���������ɾ�ڵ�ǰ��Ľڵ�
	LinkNode<T>* del=p->link;
	p->link=del->link;
	delete del;
}
template <class T>
void List<T>::inputRear(T endTag)
{
	//�������Ҫ�������ҵ�last
	LinkNode<T>* last;
	LinkNode<T>* newNode;
	last=first;
	while(last->link!=NULL)//Ѱ�ң�ֱ��last->linkΪ�գ��Ͷ�λ�����ڵ�����
		last=last->link;
	T c;
	cin>>c;
	while(c!=endTag)
	{
		newNode=new LinkNode<T>(c);
		last->link=newNode;
		last=newNode;
		cin>>c;
	}
}
template <class T>
void List<T>::inputFront(T endTag)
{
	LinkNode<T>* newNode;
	T c;
	cin>>c;
	while(c!=endTag)
	{
		newNode=new LinkNode<T>(c);
		newNode->link=first->link;
		first->link=newNode;
		cin>>c;
	}
}
template <class T>
void List<T>::output()
{
	LinkNode<T>* p=first->link;
	while(p!=NULL)
	{
		cout<<p->data;
		p=p->link;
	}
	cout<<endl;
}
template <class T>
void List<T>::input(T endTag)
{
	inputRear(endTag);
}

#endif