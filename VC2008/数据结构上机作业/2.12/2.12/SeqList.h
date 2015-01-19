#ifndef SEQLISTLIST
#define SEQLISTLIST

#include "LinearList.h"
const int defaultSize=100;

//˳�����
template<class T>
class SeqList:public LinearList<T>
{
protected:
	T* data;//��������׵�ַ
	int maxSize;//�������ݻ�
	int last;//���һ��Ԫ�ص��±�
public:
	SeqList(int sz=defaultSize);
	~SeqList() {delete[] data;}
	void reSize(int newSize);
	int Size() {return last+1;}
	T getData(int i);
	void setData(int i,T x);
	bool Insert(int i,T x);
	bool Remove(int i);
	void input(T endTag);
	void output();
	void Union(SeqList<T> &A, SeqList<T> &B);

};
//Other implementions
template<class T>
SeqList<T>::SeqList(int sz=defaultSize)
{
	if(sz>0)
	{
		maxSize=sz;
		last=-1;
		data=new T[maxSize];
		if(data==NULL)
		{
			cout<<"�ڴ�������"<<endl;
		}
	}
}
template<class T>
void SeqList<T>::reSize(int newSize)
{
	if(newSize<maxSize) {cout<<"Invalid Size!";return;}
	else
	{
		T* newarray=new T[newSize];
		T* source=data;
		T* destnation=newarray;
		for(int i=0;i<=last;i++)
		{
			*destnation=*source;
			destnation++;
			source++;
		}
		delete [] data;
		data=newarray;
		maxSize=newSize;
	}
}
template<class T>
T SeqList<T>::getData(int i)
{
	if(i>0&&i<=last+1)
	{
		return data[i-1];
	}
	else
	{
		cout<<"�������"<<endl;
		return -1;
	}
}
template<class T>
void SeqList<T>::setData(int i,T x)
{
	if(i>0&&i<=last+1)
	{
		data[i-1]=x;
	}
	else
		cout<<"�������"<<endl;
}
template<class T>
bool SeqList<T>::Insert(int i, T x)
{
	if(last==maxSize-1) return false;//������
	if(i<0||i>last+1) return false;//i������
	int j;
	for(j=last+1;j>=i+1;j--)
		data[j]=data[j-1];
	data[i]=x;
	last++;
	return true;
}
template<class T>
bool SeqList<T>::Remove(int i)
{
	if(last==-1) return false;//���ѿ�
	if(i<1||i>last+1) return false;//i������
	int j;
	for(j=i-1;j<=last-1;j++)
		data[j]=data[j+1];
	last--;
	return true;
}
template<class T>
void SeqList<T>::input(T endTag)
{
    T c;
	cin>>c;
	while(last<maxSize-1&&c!=endTag)
	{
		last++;
		data[last]=(T)c;
		cin>>c;
	}
}
template<class T>
void SeqList<T>::output()
{
	for(int i=0;i<=last;i++)
		cout<<data[i]<<" ";
	cout<<endl;
}

#endif
