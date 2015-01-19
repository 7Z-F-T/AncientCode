class List
{
private:
   struct Node;
   int size;
   Node* head;
public:
   void Initialize();
   void AddData(int num);
   bool GetData(int index,int &num);
   void DelData(int index);
   int FindData(int num);
   int GetSize();			
   bool IsEmpty();			
   void Copy(List &lst);	
   void Append(List &lst);	
   void Reverse();
   void ClearData();
};

