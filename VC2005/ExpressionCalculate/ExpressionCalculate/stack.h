/**/
template<class T>
class CStack  
{
public:
	CStack()
	{
		Head = new Node;
		Head->Next = NULL;
	}


	void Push(T data)
	{
		Node* tem;
		tem = Head->Next;
		Head->Next = new Node;
		Head->Next->Next = tem;
		Head->Next->Data = data;
	}
	


	BOOL Pop(T& data)
	{
		if(Head->Next)
		{
			data = Head->Next->Data;
			Node* tem = Head->Next->Next;
			delete Head->Next;
			Head->Next = tem;
			return TRUE;
		}
		else
			return FALSE;
	}

	BOOL GetTop(T& data)
	{
		if(Head->Next)
		{
			data = Head->Next->Data;
			return TRUE;
		}
		return FALSE;
	}

	virtual ~CStack()
	{
		Node* pNode = Head;
		Node* tem;
		while(pNode)
		{
			tem = pNode->Next;
			delete pNode;
			pNode = tem;
		}
	}


protected:
	struct Node
	{
		T Data;
		Node* Next;
	};

	Node* Head;

};