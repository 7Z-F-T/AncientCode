#include <iostream>
#include <string.h>
using namespace std;
bool SearchInB(char aa,char bb[],int nn);

int main()
{
	char a[100];
	char b[100];
	int na,nb;//string length
	cin>>a;
	cin>>b;
	na=strlen(a);
	nb=strlen(b);
	for(int i=0,j=0;i<na;)
	{
		if(SearchInB(a[i],b,nb)==true)
		{
			for(j=i;j<na-1;j++)
				a[j]=a[j+1];
			a[j]='\0';
			
		}
		else i++;
	}

	if(strlen(a)!=0) cout<<a;
	else cout<<endl;

}

bool SearchInB(char aa,char bb[],int nn)
{
	for(int i=0;i<nn;i++)
	{
		if(bb[i]==aa) return true;
	}
	return false;
}