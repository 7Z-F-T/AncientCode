#ifndef MYCONTAINER_H
#define MYCONTAINER_H

class MyContainer
{
private:
   struct Node;
   int size;
   Node* head;
   class Iterator;
public:
   MyContainer() {Initialize();}
   MyContainer(const MyContainer &);
   void Initialize();
   void AddData(int num);
   bool GetData (int index,int &num) const;
   void DelData(int index);
   int FindData (int num) const;
   int GetSize()  const;			
   bool IsEmpty() const;			
   void Copy(MyContainer &cont);	
   void Append(MyContainer &cont);	
   void Reverse();
   void ClearData();
   Iterator begin() const;
   Iterator end() const;
   void push_back(int);
   void pop_back();
   void show();
   MyContainer& operator=(MyContainer &);
   const int& operator[](int index) const;
   int& operator[](int index);
   bool operator==(const MyContainer &x) ;
   bool operator!=(const MyContainer &x) ;
};



#endif