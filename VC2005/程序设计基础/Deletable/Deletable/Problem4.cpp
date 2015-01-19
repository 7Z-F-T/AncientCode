#include <iostream>
#include <stdlib.h>
using namespace std;

int main()
{
	double letterIN[26];
	double letterTRAN[26];
	memset(letterIN,0,2);
	memset(letterTRAN,0,26);
	int k;
	int selected_k=-1;
	double fangcha=99999;
	double tempfangcha;
	char pass[10000];
    for(int i=0;i<26;i++)
	{
		cin>>letterIN[i];
	}

	for(k=0;k<26;k++)
	{
		tempfangcha=0;
		memset(letterTRAN,0,26);
		for(int i=0;i<26;i++)
		{
			letterTRAN[(i+k)%26]=letterIN[i];
		}
		for(int i=0;i<26;i++)
		{
			tempfangcha=tempfangcha+(letterIN[i]-letterTRAN[i])*(letterIN[i]-letterTRAN[i]);
		}
		if(tempfangcha<fangcha) {fangcha=tempfangcha;selected_k=k;}
	}
  
	gets(pass);
	gets(pass);
	for(int i=0;i<strlen(pass)-1;i++)
	{
		if(pass[i]>='A'&&pass[i]<='Z') cout<<char(('A'+(pass[i]-'A'+selected_k)%26));
		else if(pass[i]>='a'&&pass[i]<='z') cout<<char(('a'+(pass[i]-'a'+selected_k)%26));
		else cout<<pass[i];
	}







}