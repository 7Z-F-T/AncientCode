#include <iostream>
#include <string.h>
using namespace std;

int main()
{
	char a[100];
	char b[100];
	int count=0;
	int na,nb;//string length
	cin>>a;
	cin>>b;
	na=strlen(a);
	nb=strlen(b);
	for(int i=0,j=0,k=0;i<na;)
	{
		for(int j=0;j<nb;j++)
		{
			if(a[j+i]==b[j]) count++;
		}
		if(count==nb)
		{
			count=0;
			for(k=0;k<nb;k++)
			{
				for(j=i;j<na-1;j++)
					a[j]=a[j+1];
			}
            a[na-k]='\0';
        }
		else {i++;count=0;}
	
	}

	if(strlen(a)!=0) cout<<a;
	else cout<<endl;

}

