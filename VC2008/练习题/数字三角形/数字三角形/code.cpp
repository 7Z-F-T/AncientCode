#include <iostream>
using namespace std;

bool finished=false;
int movetype=0;//0:向下 1:向右 2:向左上
int currenti;
int currentj;
int count=0;
int n;
int data[20][20];
int sum=0;


void move0()
{
	while(1)
	{
		count++;
		data[currenti][currentj]=count;
		if(count==sum)
		{
			finished=true;
			return;
		}
		if(currenti+1==n)
		{
			movetype=1;
			currentj++;
			return;
		}
		else if(data[currenti+1][currentj]!=0)
		{
			movetype=1;
			currentj++;
			return;
		}
		else
		{
			currenti++;
		}
	}

}

void move1()
{
	while(1)
	{
		count++;
		data[currenti][currentj]=count;
		if(count==sum)
		{
			finished=true;
			return;
		}
		if(currentj+1==n)
		{
			movetype=2;
			currenti--;
			currentj--;
			return;
		}
		else if(data[currenti][currentj+1]!=0)
		{
			movetype=2;
			currenti--;
			currentj--;
			return;
		}
		else
		{
			currentj++;
		}
	}

}
void move2()
{
	while(1)
	{
		count++;
		data[currenti][currentj]=count;
		if(count==sum)
		{
			finished=true;
			return;
		}
        if(data[currenti-1][currentj-1]!=0)
		{
			movetype=0;
			currenti++;
			return;
		}
		else
		{
			currenti--;
			currentj--;
		}
	}

}
int main()
{

	cin>>n;
	for(int i=0;i<n;i++)
		for(int j=0;j<n;j++)
			data[i][j]=0;
	currenti=0;
	currentj=0;
	
	for(int i=1;i<=n;i++)
		sum+=i;

	while(finished==false)
	{
		if(movetype==0)
			move0();
		else if(movetype==1)
			move1();
		else if(movetype==2)
			move2();
	}

	for(int i=0;i<n;i++)
	{
		for(int j=0;j<n;j++)
		{
			if(data[i][j]!=0)
				cout<<data[i][j]<<" ";
		}
		cout<<endl;
	}

	return 0;

}