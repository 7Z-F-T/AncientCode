#include "head.h"


int nLine;
vector<Line> Text;


bool GetReady(char* &filename)
{
	ifstream fin(filename);
	if(!fin)
	{cout<<"--Cannot open file！--";return false;}
	nLine=GetLinesAmount(filename);//取行数
	Text.resize(nLine);
	int i=0;                                     //按行读文件入内存
	getline(fin,Text[0].StringOfLine,'\n');
	while(Text[i].StringOfLine.size()!=0)
	{
		i++;
		getline(fin,Text[i].StringOfLine,'\n');
	}

	for(i=0;i<nLine;i++)
	{
		Text[i].GetWordAmount();
		Text[i].GetWordFromString();
	}
	return true;
}
int GetLinesAmount(const char* filename)
{
	int count=0;
    ifstream fintemp(filename);
	string temp;
	getline(fintemp,temp,char(0));
    int pos=0;
    while(temp.find("\n",pos)!=string::npos)
	    {
		    count++;
		    pos=(int)temp.find("\n",pos)+1;
		}
	
	return count+2;
}

void Line::GetWordAmount()
{   
	char seps[]=" \t\n,.1234567890?!`~@#$%^&*()_=+[{]}\\|'\"/<>";
	int count=0;
	char* Word;
	string temp=StringOfLine;
    Word=strtok(&(temp[0]),seps);
     while(Word!=NULL)
	   {
		count++;
        Word=strtok(NULL,seps);
	   }
	nWord=count;
}
void Line::GetSearchWordAmount()
{   
	char seps[]=" ";
	int count=0;
	char* Word;
	string temp=StringOfLine;
    Word=strtok(&(temp[0]),seps);
     while(Word!=NULL)
	   {
		count++;
        Word=strtok(NULL,seps);
	   }
	nWord=count;
}
void Line::GetWordFromString()
{
	WordOfLine.resize(nWord);
	int i=0;
	char* Word;
	char seps[]=" \t\n,.1234567890?!`~@#$%^&*()_=+[{]}\\|'\"/<>";
	string temp=StringOfLine;
	Word=strtok(&(temp[0]),seps);
    while(Word!=NULL)
	   {
		WordOfLine[i]=Word;
		i++;
        Word=strtok(NULL,seps);
	   }
}
void Line::GetSearchWordFromString()
{
	WordOfLine.resize(nWord);
	int i=0;
	char* Word;
	char seps[]=" ";
	string temp=StringOfLine;
	Word=strtok(&(temp[0]),seps);
    while(Word!=NULL)
	   {
		WordOfLine[i]=Word;
		i++;
        Word=strtok(NULL,seps);
	   }

	int nfront=-1,nback=-1;
	//以上当输入例如 "this is" 时会被拆成"this 和 is"，不符合要求。下面代码用来处理这种情况
	for(i=0;i<nWord;i++)
	{

		if(*WordOfLine[i].begin()=='\"'&&*(--WordOfLine[i].end())!='\"') nfront=i;
		else if(*WordOfLine[i].begin()!='\"'&&*(--WordOfLine[i].end())=='\"') nback=i;
		if(nfront!=-1&&nback!=-1)
		{
			WordOfLine[nfront]+=" ";
			for(int j=nfront+1;j<nback;j++)
		     	{WordOfLine[nfront]+=WordOfLine[j];
				WordOfLine[nfront]+=" ";}
			WordOfLine[nfront]+=WordOfLine[nback];
			WordOfLine.erase(WordOfLine.begin()+nfront+1,WordOfLine.begin()+nback+1);
			nWord=nWord-(nback-nfront);i-=nback-nfront;nfront=-1;nback=-1;
		}
	}
				
		
	    
}

void Line::ShowString()
{
	cout<<StringOfLine<<endl;
}

void Line::Initialize()
{
	Display=true;
	nWord=0;
	StringOfLine.clear();
	WordOfLine.clear();
}
Line Line::ChangeCap()
{
	string str=StringOfLine;
	for(string::iterator iter1=str.begin();iter1<str.end();iter1++)
		if((*iter1)>='A'&&(*iter1)<='Z') (*iter1)+=32;
	vector<string> vec=WordOfLine;
	for(vector<string>::iterator iter2=vec.begin();iter2<vec.end();iter2++)
		for(string::iterator iter3=(*iter2).begin();iter3<(*iter2).end();iter3++)
			if((*iter3)>='A'&&(*iter3)<='Z') (*iter3)+=32;
	return Line(nWord,str,vec);
}
void GeneralSearch(string &str)
{
	int count=0;
	Line temp;
	for(int i=0;i<nLine;i++)
	{
		temp=Text[i].ChangeCap();
		for(int j=0;j<Text[i].nWord;j++)
		{
			if(str==temp.WordOfLine[j]) count++;
		}
		if(count!=0&&Text[i].Display==true) Text[i].Display=true;
		else Text[i].Display=false;
		count=0;
	}
}

void StringSearch(string &str)
{
	str.erase(str.begin());
	str.erase(--str.end());
	Line temp;
	int pos;
	int count;
	for(int i=0;i<nLine;i++)
	{
		temp=Text[i].ChangeCap();
		pos=temp.StringOfLine.find(str);
		if(pos==string::npos) Text[i].Display=false;
		else if(pos>0)
		{if(temp.StringOfLine[pos-1]>='a'&&temp.StringOfLine[pos-1]<='z') Text[i].Display=false;}
		if(pos+str.size()<temp.StringOfLine.size())
		{if(temp.StringOfLine[pos+str.size()]>='a'&&temp.StringOfLine[pos+str.size()]<='z') Text[i].Display=false;}
		if(Text[i].Display==true)
			Text[i].Display=true; 
	
			
		
	}

}
void DeleteSearch(string &str)
{
	str.erase(str.begin());
	int count=0;
	Line temp;
	for(int i=0;i<nLine;i++)
	{
		temp=Text[i].ChangeCap();
		for(int j=0;j<Text[i].nWord;j++)
		{
			if(str==temp.WordOfLine[j]) count++;
		}
		if(count!=0&&Text[i].Display==true) Text[i].Display=false;
		count=0;
	}
}





