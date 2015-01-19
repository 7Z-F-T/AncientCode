#include "stack.h"
#include "CompareTable.h"
#include "SyntaxAnalyse.h"



class  CalExpression
{
public:
	CalExpression(char* Expression);
	int GetValueType();
	int GetIntResult();
	double GetDoubleResult();
	bool GetResult();
	int Calculate();
protected:
	int Operate(char Operator,int Fir,int Sec);
	double Operate(char Operator,double Fir,double Sec);
protected:

	PriorityTable  MyTable;
	WordAnalyse MyWords;
	SyntaxAnalyse MySyntax;
protected:
	bool bResult;
	int nResult;
	double dResult;
	int ValueType;
};

CalExpression::CalExpression(char* Expression):MyWords(Expression)
{
	bResult = false;
	nResult = 0;
	dResult = 0;
	ValueType = -1;
}

bool CalExpression::GetResult()
{
	return bResult;
}


int CalExpression::GetValueType()
{
	return ValueType;
}


int CalExpression::Operate(char Operator,int Fir,int Sec)
{
	switch (Operator)
	{
	case '+':
		return Fir+Sec;
	case '-':
		return Fir - Sec;
	case '*':
		return Fir * Sec;
	case '/':
		return Fir/Sec;
	}
	return 0;
}

double CalExpression::Operate(char Operator,double Fir,double Sec)
{
	switch (Operator)
	{
	case '+':
		return Fir+Sec;
	case '-':
		return Fir - Sec;
	case '*':
		return Fir * Sec;
	case '/':
		return Fir/Sec;
	}
	return 0;
}

int CalExpression::GetIntResult()
{
	return nResult;
}

double CalExpression::GetDoubleResult()
{
	return dResult;
}

int CalExpression::Calculate()
{
	bResult = false;
	nResult = 0;
	dResult = 0;
	ValueType = -1;

	if(!MySyntax.Analyse(&MyWords))
		return ValueType = -1;
	if(MyWords.TypeIsDouble())
	{
		CStack<char> CharStack;
		CStack<double>  ValueStack;

		CharStack.Push('#');
		
		int WordIndex = 0;
		
		char top;
		CharStack.GetTop(top);
		while(!(strcmp(MyWords.GetWord(WordIndex),"#") == 0 && top == '#'))
		{
			if(MyWords.GetWordAttribute(WordIndex) != 2)
			{
				double Value = atof(MyWords.GetWord(WordIndex));
				ValueStack.Push(Value);
				WordIndex ++;
				
			}
			else
			{
				switch (MyTable.Compare(top,(MyWords.GetWord(WordIndex))[0]))
				{
				case -1:
					CharStack.Push((MyWords.GetWord(WordIndex))[0]);
					WordIndex ++;
					break;
				case 0:
					CharStack.Pop(top);
					WordIndex ++;
					break;
				case 1:
					{
						double nFirNum ,nSecNum, nResult;
						char Operator;
						ValueStack.Pop(nSecNum);
						ValueStack.Pop(nFirNum);
						CharStack.Pop(Operator);
						nResult = Operate(Operator,nFirNum,nSecNum);
						ValueStack.Push(nResult);
						break;
					}
				default	:
					break;
				}
			}
			
			
			CharStack.GetTop(top);
		}

		ValueStack.GetTop(dResult );
		ValueType = 1;
		return 1;
	}
	else
	{
		CStack<char> CharStack;
		CStack<int>  ValueStack;
		
		CharStack.Push('#');
		
		int WordIndex = 0;
		
		char top;
		CharStack.GetTop(top);
		while(!(strcmp(MyWords.GetWord(WordIndex),"#") == 0 && top == '#'))
		{
			if(MyWords.GetWordAttribute(WordIndex) != 2)
			{
				int Value = atoi(MyWords.GetWord(WordIndex));
				ValueStack.Push(Value);
				WordIndex ++;
				
			}
			else
			{
				switch (MyTable.Compare(top,(MyWords.GetWord(WordIndex))[0]))
				{
				case -1:
					CharStack.Push((MyWords.GetWord(WordIndex))[0]);
					WordIndex ++;
					break;
				case 0:
					CharStack.Pop(top);
					WordIndex ++;
					break;
				case 1:
					{
						int nFirNum ,nSecNum,nResult;
						char Operator;
						ValueStack.Pop(nSecNum);
						ValueStack.Pop(nFirNum);
						CharStack.Pop(Operator);
						nResult = Operate(Operator,nFirNum,nSecNum);
						ValueStack.Push(nResult);
						break;
					}
				default	:
					break;
				}
			}
			
			
			CharStack.GetTop(top);
		}

		ValueStack.GetTop(nResult );
		ValueType = 0;
		return 0;

	}
}

