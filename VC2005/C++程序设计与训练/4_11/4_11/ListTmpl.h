#ifndef LISTTMPL_H
#define LISTTMPL_H
#include <iostream>

template<class T>
struct Node
{
   T num;
   Node* next;
};

template <class T>
class List
{
private:
   int size;
   Node<T>  *head;
public:
   void Initialize();
   void AddData(T num);
   bool GetData (int index,T &num) const ;
   void DelData(int index);
   int FindData (int num) const ;
   int  GetSize() const;			
   bool IsEmpty() const;			
   void Copy(List &lst);	
   void Append(List &lst);	
   void Reverse();
   void ClearData();
};


template<class T>
void List<T>::Initialize()  //tested ok
{
   head = NULL;
   size = 0;
}
template<class T>
void List<T>::AddData(T num)//tested ok
{
	if (size == 0)
   {
      head = new Node<T>;
      head->num = num;
      head->next = NULL;
   }
   else
   {
      Node<T>* pNode = head;
      for (int i = 0; i < size - 1; i++) 
	 pNode = pNode->next;
      pNode->next = new Node<T>;
      pNode->next->num = num;
      pNode->next->next = NULL;
   }
   size++;
}
template<class T>
bool  List<T>::GetData(int index,T &num) const
{
	if (index < 0 || index >=size)
      return false;
   Node<T>* pNode = head;
   for (int i = 0; i < index; i++)
      pNode = pNode->next;
   num = pNode->num;
   return true;
}
template<class T>
void List<T>::DelData(int index) 
{
   if (index < 0 || index >= size)
      return;
   Node<T>* pNode =head;
   if (index == 0) 
   {
      head = head->next;
      delete pNode;
   }
   else
   {
      for (int i = 0; i < index -1; i++) 
	      pNode = pNode->next;
      Node<T>* temp = pNode->next;
      pNode->next = temp->next;
      delete temp;
   }
   size--;
}
template<class T>
int List<T>::FindData(int num) const
{
   Node<T>* pNode = head;
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
template<class T>
void List<T>::ClearData() 
{
   while (head != NULL)
   {
      Node<T>* pNode = head;
      head = pNode->next;
      delete pNode;
   }
   size = 0;
}
template<class T>
int List<T>::GetSize() const
{
	int n=0;
	if(head==NULL)
		return 0;
	else
	{
		Node<T>* pNode=head;
		while(pNode->next!=NULL)
		{pNode=pNode->next;n++;}
		return(n+1);
	}
}
template<class T>
bool List<T>::IsEmpty() const 
{
	if(head==NULL)
		return true;
	else
		return false;
}
template<class T>
void List<T>::Copy(List<T> &lst)
{
	Initialize();
    Node<T>* pNode=lst.head;
	for(;pNode!=NULL;pNode=pNode->next)
		AddData(pNode->num);
}
template<class T>
void List<T>::Append(List<T> &lst)
{
    Node<T>* pNode=lst.head;
	for(;pNode!=NULL;pNode=pNode->next)
		AddData(pNode->num);
}
template<class T>
void List<T>::Reverse()
{
	List<T> temp;
	T n;
	temp.Initialize();
	for(int i=size-1;i>=0;i--)
	{
		GetData(i,n);
		temp.AddData(n);
	}
	Copy(temp);

    

}

#endif
