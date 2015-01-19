#include <iostream>
using namespace std;

const int MaxPower=2;//多项式次数0~10

int Max(int a,int b)
{
    if(a>=b) return a;
	return b;
}

typedef struct 
{
	int CoeffArray[MaxPower+1];
	int HighestPower;
}Polynomail;



typedef Polynomail* pPolynomail;

void Zero(pPolynomail p)//多项式置零
{
	p->HighestPower=0;
	for(int i=0;i<=MaxPower;i++)
		p->CoeffArray[i]=0;
}

void Add(pPolynomail p1,pPolynomail p2,pPolynomail p3)
{
	Zero(p3);
	p3->HighestPower=Max(p1->HighestPower,p2->HighestPower);
	for(int i=p3->HighestPower;i>=0;i--)
	p3->CoeffArray[i]+=p1->CoeffArray[i]+p2->CoeffArray[i];
}

int Multiply(pPolynomail p1,pPolynomail p2,pPolynomail p3)
{
    Zero(p3);
	p3->HighestPower=p2->HighestPower+p1->HighestPower;
	if(p3->HighestPower>MaxPower) 
	{
		cout<<"Too Big!"<<endl;
		return -1;
	}
	int i,j;
	for(i=0;i<=MaxPower;i++)
		for(j=0;j<=MaxPower;j++)
			p3->CoeffArray[i+j]+=p1->CoeffArray[i]*p2->CoeffArray[j];
}

int main()
{
	pPolynomail poly1=new Polynomail;
	pPolynomail poly2=new Polynomail;
	Zero(poly1);
	Zero(poly2);
	for(int i=MaxPower;i>=0;i--)
	{
		cout<<"poly1(Power"<<i<<")=?  ";
		cin>>poly1->CoeffArray[i];
	}
	for(int i=MaxPower;i>=0;i--)
	{
		cout<<"poly2(Power"<<i<<")=?  ";
		cin>>poly2->CoeffArray[i];
	}
	int i=MaxPower;
	while(i>=0)
	{
		if(poly1->CoeffArray[i]>0) {poly1->HighestPower=i;break;}
		i--;
	}	
	i=MaxPower;
	while(i>=0)
	{
		if(poly2->CoeffArray[i]>0) {poly2->HighestPower=i;break;}
		i--;
	}	

	pPolynomail poly3=new Polynomail;
	pPolynomail poly4=new Polynomail;
	Add(poly1,poly2,poly3);
	Multiply(poly1,poly2,poly4);



}