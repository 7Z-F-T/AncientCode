class PriorityTable
{

	int** pTable;
	int  TableSize;
public:
	PriorityTable()
	{
		int i,j;
		pTable =NULL;
		TableSize = 7;
		
		
		pTable = new int* [TableSize];
		for( i = 0; i < TableSize; i++)
			pTable[i] = new int[TableSize];
		
		for( i = 0; i < TableSize; i++)
			for( j = 0; j<TableSize; j++)
				pTable[i][j] = 1;

		pTable[4][5] = pTable[6][6] = 0;
		pTable[0][2] = pTable[0][3] = pTable[0][4] = -1;
		pTable[1][2] = pTable[1][3] = pTable[1][4] = -1;
		pTable[2][4] = -1;
		pTable[3][4] = -1;
		for( j = 0; j < 5; j++)
			pTable[4][j] = -1;
		for( j = 0; j < 5; j++)
			pTable[6][j] = -1;
	
	}


	

	~PriorityTable()
	{
		
		for(int i=0; i< TableSize ; i++)
		{
			delete []pTable[i];
		}
	 	delete []pTable;
		
	}

	int CharToIndex(char ch)
	{
		switch (ch)
		{
		case '+':
				  return 0;
		case '-':
				  return 1;
		case '*':
				  return 2;
		case '/':
				  return 3;
		case '(':
				  return 4;
		case ')': 
				  return 5;
		case '#':
				  return 6;
		default:
				  return -1;
		}
	}

	int Compare(const char FirstOper,const char SecondOper) 
	{
		int FirstIndex  = CharToIndex(FirstOper);
		int SecondIndex = CharToIndex(SecondOper);
		if(FirstIndex != -1 && SecondIndex != -1 )
			return pTable[FirstIndex][SecondIndex];
		return -2;
	}

	
	int Compare(int x,int y) 
	{
		if( x>=0 && x<TableSize && y>=0 && y<TableSize)
			return pTable[x][y];
		return -2;
	}


};