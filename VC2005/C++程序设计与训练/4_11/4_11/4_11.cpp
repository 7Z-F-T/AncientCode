#include <iostream>
#include "listtmpl.h"


void List<T>::Initialize()  //tested ok
{
   head = NULL;
   size = 0;
}

void List::AddData(int num)//tested ok
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

bool List::GetData(int index,int &num)
{
	if (index < 0 || index >=size)
      return false;
   Node* pNode = head;
   for (int i = 0; i < index; i++)
      pNode = pNode->next;
   num = pNode->num;
   return true;
}

void List::DelData(int index) 
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

int List::FindData(int num)
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

void List::ClearData() 
{
   while (head != NULL)
   {
      Node* pNode = head;
      head = pNode->next;
      delete pNode;
   }
   size = 0;
}

int List::GetSize()
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

bool List::IsEmpty()
{
	if(head==NULL)
		return true;
	else
		return false;
}

void List::Copy(List &lst)
{
	Initialize();
    Node* pNode=lst.head;
	for(;pNode!=NULL;pNode=pNode->next)
		AddData(pNode->num);
}

void List::Append(List &lst)
{
    Node* pNode=lst.head;
	for(;pNode!=NULL;pNode=pNode->next)
		AddData(pNode->num);
}

void List::Reverse()
{
	List temp;
	int n;
	temp.Initialize();
	for(int i=size-1;i>=0;i--)
	{
		GetData(i,n);
		temp.AddData(n);
	}
	Copy(temp);

    

}
