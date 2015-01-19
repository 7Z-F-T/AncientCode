
#include "WordAnalyse.h"



class SyntaxAnalyse
{
	bool bResult;
	int WordIndex;
public:
	SyntaxAnalyse() {bResult = true;  WordIndex = 0;}
	bool Analyse(WordAnalyse* pWords);
protected:
	void E(WordAnalyse* pWords);
	void E_(WordAnalyse* pWords);
//	void N(WordAnalyse* pWords);
};

bool SyntaxAnalyse::Analyse(WordAnalyse* pWords)
{
	if(pWords->GetResult() == false)
		return false;
	E(pWords);
	if( WordIndex < pWords->GetWordNum() - 1)
	{
			bResult = true;
			WordIndex = 0;
			return false;
	}
	bool tem = bResult;
	bResult = true;
	WordIndex = 0;
	return tem;

}

void SyntaxAnalyse::E(WordAnalyse* pWords)
{
	
	if(strcmp(pWords->GetWord(WordIndex),"(") == 0)
	{
		WordIndex++;
		E(pWords);
		if(strcmp(pWords->GetWord(WordIndex),")") == 0)
		{
			WordIndex++;
			E_(pWords);
		}
		else
		{
			bResult = false;
			return;
		}
		
	}
	else
		if(pWords->GetWordAttribute(WordIndex) != 2)
		{
			WordIndex++;
			E_(pWords);
		}
		else
		{
			bResult = false;
			return;
		}
}


void SyntaxAnalyse::E_(WordAnalyse* pWords)
{
	//char tem[100] = "\0";
//	strcpy(tem,Words.GetWord(WordIndex));
//	char Operator = tem[0];
	if(strcmp(pWords->GetWord(WordIndex),"+") == 0 ||
		strcmp(pWords->GetWord(WordIndex),"-") == 0 ||
		strcmp(pWords->GetWord(WordIndex),"*") == 0 ||
		strcmp(pWords->GetWord(WordIndex),"/") == 0 
		)
	{
		WordIndex++;
		E(pWords);
		E_(pWords);
	}
	else
		return;
	
}
