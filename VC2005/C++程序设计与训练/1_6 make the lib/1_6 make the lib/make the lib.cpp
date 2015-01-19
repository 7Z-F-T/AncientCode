#include "head.h"
int max(int a,int b,int c)
{
	int temp;
	if(a<b)
	{
		temp=b;
		b=a;
		a=temp;
	}
	if(b<c)
	{
		temp=c;
		c=b;
		b=temp;
	}
	if(a<b)
	{
		temp=b;
		b=a;
		a=temp;
	}
return a;
}

int min(int a,int b,int c)
{
	int temp;
	if(a<b)
	{
		temp=b;
		b=a;
		a=temp;
	}
	if(b<c)
	{
		temp=c;
		c=b;
		b=temp;
	}
	if(a<b)
	{
		temp=b;
		b=a;
		a=temp;
	}
return c;
}