#include "MyContainer.h"
#include <iostream>
using namespace std;

struct MyContainer::Node
{
	int num;
	Node* next;
};


class MyContainer::Iterator
{
public:
	Node* iter;
	Iterator(){iter=NULL;}
	Iterator(Node* const &x){iter=x;}
	Iterator(Node* &x){iter=x;}
	Iterator(const Iterator &x){iter=x.iter;}
	Iterator& operator=(const Iterator &x)//重载Iterator的=
	{
		iter=x.iter;
		return *this;
	}
	bool operator==(const Iterator &x) const//重载Iterator的==
	{
		return iter==x.iter;
	}
	bool operator!=(const Iterator &x) const//重载Iterator的!=
	{
		return iter!=x.iter;
	}
	Iterator& operator++()//重载Iterator的前缀++
	{
		iter=iter->next;
		return *this;
	}
	Iterator operator++(int)//重载Iterator的后缀++
	{
		Iterator before(iter);
		iter=iter->next;
		return before;
	}
	int& operator*() const//重载Iterator的*
	{
		return iter->num;
	}
	Iterator& operator+(int n)//重载Iterator的+
	{
		for(int i=1;i<=n;i++)
			++(*this);
		return *this;
	}

};

MyContainer::MyContainer(const MyContainer &x)//拷贝构造函数
{
	Initialize();
	Iterator it=x.head;
	for(int i=0;i<x.size;i++)
	{
		this->AddData(*it);
		it++;
	}
}

void MyContainer::Initialize()  
{
   head = NULL;
   size = 0;
}

void MyContainer::AddData(int num)
{
	if (size == 0)
   {
      head = new Node;
      head->num = num;
      head->next = NULL;
   }
   else
   {
      Node* pNode = head;
      for (int i = 0; i < size - 1; i++) 
	 pNode = pNode->next;
      pNode->next = new Node;
      pNode->next->num = num;
      pNode->next->next = NULL;
   }
   size++;
}

bool MyContainer::GetData (int index,int &num) const
{
	if (index < 0 || index >=size)
      return false;
   Node* pNode = head;
   for (int i = 0; i < index; i++)
      pNode = pNode->next;
   num = pNode->num;
   return true;
}

void MyContainer::DelData(int index) 
{
   if (index < 0 || index >= size)
      return;
   Node* pNode =head;
   if (index == 0) 
   {
      head = head->next;
      delete pNode;
   }
   else
   {
      for (int i = 0; i < index -1; i++) 
	      pNode = pNode->next;
      Node* temp = pNode->next;
      pNode->next = temp->next;
      delete temp;
   }
   size--;
}

int MyContainer::FindData(int num) const
{
   Node* pNode = head;
   int index = 0;
   while (pNode != NULL)
      if (pNode->num == num)
         return index;
      else
      {
	  pNode = pNode->next;
	  index++;
      }
   return -1;
}

void MyContainer::ClearData() 
{
   while (head != NULL)
   {
      Node* pNode = head;
      head = pNode->next;
      delete pNode;
   }
   size = 0;
}

int MyContainer::GetSize() const
{
	int n=0;
    if(head==NULL)
		return 0;
	else
	{
		Node* pNode=head;
		while(pNode->next!=NULL)
		{pNode=pNode->next;n++;}
		return(n+1);
	}
}

bool MyContainer::IsEmpty() const
{
	if(head==NULL)
		return true;
	else
		return false;
}

void MyContainer::Copy(MyContainer &cont)
{
	Initialize();
    Node* pNode=cont.head;
	for(;pNode!=NULL;pNode=pNode->next)
		AddData(pNode->num);
}

void MyContainer::Append(MyContainer &cont)
{
    Node* pNode=cont.head;
	for(;pNode!=NULL;pNode=pNode->next)
		AddData(pNode->num);
}

void MyContainer::Reverse()
{
	MyContainer temp;
	int n;
	temp.Initialize();
	for(int i=size-1;i>=0;i--)
	{
		GetData(i,n);
		temp.AddData(n);
	}
	Copy(temp);
}
MyContainer::Iterator MyContainer::begin() const
{
	return Iterator(head);

}
MyContainer::Iterator MyContainer::end() const
{ 
	Node* pNode=head;
	while(pNode!=NULL)
		pNode=pNode->next;
	return Iterator(pNode);
}
void MyContainer::push_back(int n)
{
	this->AddData(n);
}
void MyContainer::pop_back()
{
	this->DelData(size-1);
}
void MyContainer::show()
{
	MyContainer::Iterator pa=begin();
	MyContainer::Iterator pb=end();
	for(;pa!=pb;pa++)
		cout<<*pa<<" ";
}

bool MyContainer::operator!=(const MyContainer &x)
{
	if(this->size!=x.size) return true;
	else
	{
		MyContainer::Iterator iter1(head);
	    MyContainer::Iterator iter2(x.head);
		while(iter1!=this->end())
		{
			int n1=*iter1;
			int n2=*iter2;
			if(n1!=n2) return true;
			else {iter1++;iter2++;}
		}
	}
	return false;
}	
bool MyContainer::operator==(const MyContainer &x) 
{
	if(this->size!=x.size) return false;
	else
	{
		MyContainer::Iterator iter1(head);
	    MyContainer::Iterator iter2(x.head);
		while(iter1!=this->end())
		{
			int n1=*iter1;
			int n2=*iter2;
			if(n1!=n2) return false;
			else {iter1++;iter2++;}
		}
	}
	return true;
}
const int& MyContainer::operator[](int index) const
{
	return (*(this->begin()+index));
}
int& MyContainer::operator[](int index)
{
    return (*(this->begin()+index));
}
MyContainer& MyContainer::operator=(MyContainer &x)
{
	Copy(x);
	return *this;
}







int main()
{
	MyContainer my;
	my.AddData(1);
	my.AddData(2);
	my.AddData(3);
	my.AddData(4);
	cout<<"my"<<endl;
	my.show();
	cout<<"size="<<my.GetSize();
	if(my.IsEmpty()==true)
		cout<<"empty";
	else
	    cout<<"not empty"<<endl;


	MyContainer his=my;
	cout<<"his"<<endl;
    his.show();
    cout<<"size="<<his.GetSize();
	if(his.IsEmpty()==true)
		cout<<"empty";
	else
	    cout<<"not empty"<<endl;


	MyContainer her;
	her=my;
	cout<<"her"<<endl;
    her.show();
    cout<<"size="<<her.GetSize();
	if(her.IsEmpty()==true)
		cout<<"empty";
	else
	    cout<<"not empty"<<endl;

 

	cout<<"my[1]="<<my[1]<<endl;
	cout<<"his[2]="<<his[2]<<endl;
	cout<<"her[3]="<<her[3]<<endl;
 

	return 0;
}

