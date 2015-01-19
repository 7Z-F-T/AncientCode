// ForTest.cpp : Defines the entry point for the console application.
//



#define MaxWordNum 100
#define MaxWordLength 100

class WordAnalyse
{
protected:
	
	char Words[MaxWordNum][MaxWordLength];
	int  WordsAttribute[MaxWordNum];
	int CurrentWordNum;
	char* pCurrentLocation ;
	char* pLastLocation;
	bool bResult;
	bool WordType;  
	char Expression[100];
public:
	
	WordAnalyse(char* chExpression)
	{
		
		int i,j;
		for(i = 0; i<MaxWordNum ; i++)
			
		{
			for(j = 0; j<MaxWordLength ; j++)
				Words[i][j] = '\0';
			WordsAttribute[i] = 0;
			
		}
		CurrentWordNum = 0;
		pCurrentLocation = NULL;
		strcpy(Expression,"(");
		strcat(Expression,chExpression);
		pCurrentLocation = Expression+1;
		pLastLocation = Expression;
		bResult = WordsAnalyse();
		WordType = IsAllDouble();
	}

	
	int GetWordNum()
	{
		return CurrentWordNum;
	}
	
	char* GetWord(int index)
	{
		if(index>=CurrentWordNum)
			return NULL;
		else
			return Words[index];
	}

	int GetWordAttribute(int index)
	{
		if(index>=CurrentWordNum)
			return -100;
		return WordsAttribute[index];
	}

	bool GetResult()
	{
		return  bResult ;
	}

	bool TypeIsDouble()
	{
		return WordType;
	}






protected:

	bool IsAllDouble()
	{
		for(int i = 0;i<CurrentWordNum; i++)
		{
			if(WordsAttribute[i] == 1 || !strcmp(Words[i],"/"))
				return true;
		}
		return false;
	}

	bool WordsAnalyse()
	{
		
		while(*pCurrentLocation != '\0')
			if(!GenerateWord())
				return false;
			return true;
			
	}


	bool IsNumber(char ch)
	{
		if(ch >= '0' && ch <= '9')
			return true;
		return false;
	}


	bool GenerateWord()
	{
		
		if(*pCurrentLocation == ' ')
			do
			{
				pCurrentLocation++;
			}
			while(*pCurrentLocation == ' ');
			
			switch(*pCurrentLocation)
			{
			case '+':
			case '*':	
			case '/':	
			case '(':
			case ')':	
			case '#':
				Words[CurrentWordNum][0] = *pCurrentLocation;
				WordsAttribute[CurrentWordNum] = 2;
				CurrentWordNum++;
				pLastLocation = pCurrentLocation;
				pCurrentLocation++;
				
				return true;
			case '-':     
				if(*pLastLocation != '(')    // - 号为双目操作符
				{
					Words[CurrentWordNum][0] = *pCurrentLocation;
					WordsAttribute[CurrentWordNum] = 2;
					
				}
				else                             //－为单目操作符
				{
					Words[CurrentWordNum][0] = '0';
					WordsAttribute[CurrentWordNum] = 0;
					CurrentWordNum++;

					Words[CurrentWordNum][0] = '-';
					WordsAttribute[CurrentWordNum] = 2;
				
				}

				CurrentWordNum++;
				pLastLocation = pCurrentLocation;
				pCurrentLocation++;
				
				return true;
			
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				int i ;
				i = 0;
				do
				{
					Words[CurrentWordNum][i++] = *pCurrentLocation;
					pLastLocation = pCurrentLocation;
					pCurrentLocation++;
				}while(IsNumber(*pCurrentLocation));
				
				if(*pCurrentLocation == '.')
				{
					
					Words[CurrentWordNum][i++] = *pCurrentLocation;
					pCurrentLocation++;
					if(!IsNumber(*pCurrentLocation))
						return false;
					while(IsNumber(*pCurrentLocation))
					{
						Words[CurrentWordNum][i++] = *pCurrentLocation;
						pLastLocation = pCurrentLocation;
						pCurrentLocation++;
					}
					WordsAttribute[CurrentWordNum] = 1;
					CurrentWordNum ++;
					return true;
				}
				else
				{
					CurrentWordNum ++;
					return true;
				}
				
			default :
				return false;
				
			}
	
			return false;
	}
	

};


