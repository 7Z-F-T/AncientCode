#include <iostream>
using namespace std;

int deletedMonkey[100];//存储被踢掉的猴子编号
int number=0;
int inputed_deletedMonkey[100];

struct monkey
{
	int num;
	monkey *next;

};
monkey *head,*tail;

void createMonkey(int n)//建立n只猴子
{
	int i;
	monkey *p,*q;
	p=new monkey;
	p->num=1;
	p->next=NULL;
	head=p;
	q=p;
	for(i=2;i<=n;i++)
	{
		p=new monkey;
		p->num=i;
		p->next=NULL;
		q->next=p;
		q=p;
	}
	tail=q;
	q->next=head;
}

void selectMonkey(int m,int startpos)//数到m就踢猴子
{
	int x=0;
	monkey *p,*q;
	q=tail;
	for(int i=1;i<startpos;i++)
	{
		
		p=q->next;
		q=p;

	}
	do 
	{
        p=q->next;	
		x=x+1;
		if(x%m==0)
		{
			deletedMonkey[number]=p->num;number++;
			q->next=p->next;
			delete p;
			p=NULL;
		}
		else q=p;
	} while(q!=q->next);
	deletedMonkey[number]=q->num;
	head=q;
}

int main()
{
	int count=0;

	int n,m,startpos;//n为猴子数，m为要数的数
	head=NULL;
	cin>>n;
	for(int i=0;i<n;i++)
	{
        cin>>inputed_deletedMonkey[i];
	}
	
	for(startpos=1;startpos<=n;startpos++)
	{
		for(m=1;m<=n;m++)
	    {
			createMonkey(n);
		    selectMonkey(m,startpos);
			for(int i=0;i<n;i++)
			{
				if(deletedMonkey[i]!=inputed_deletedMonkey[i]) break;
				else if(i==n-1) 
				{
					count++;
					cout<<m<<" "<<startpos;
					return 0;
				}
			      
			}
			for(int i=0;i<n;i++)
			{
				deletedMonkey[i]=0;
				number=0;
			}
		}
	}
    
	if(count==0) cout<<-1;

	delete head;

	return 0;
}
