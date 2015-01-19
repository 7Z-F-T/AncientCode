template<class T> 
void SeqList<T>::Union(SeqList<T> &A, SeqList<T> &B)
{
	int i,j;
	reSize(A.maxSize+B.maxSize);
	//copy all the elements of A 
	for(i=0;i<=A.last;i++)
		Insert(0,A.data[i]);
	//copy all the elements of B as long as there's no repetition
	
	for(i=0;i<=B.last;i++)
	{
		for(j=0;j<=A.last;j++)
			if(B.data[i]==A.data[j]) break;
		if(j>A.last)
			Insert(0,B.data[i]);
	}
	//sort
	for(j=1;j<=Size()-1;j++)
		for(i=0;i<=last-j;i++)
			if(data[i]>data[i+1])
			{
				T temp;
				temp=data[i+1];
				data[i+1]=data[i];
				data[i]=temp;
			}
}

