#include <iostream>
using namespace std;
int cell[100];//0:未放置棋子 1:已放置棋子
int n;
int count=0;

bool test()
{
	for(int i=0;i<n;i++)
		if(cell[i]==0) return false;
	return true;
}
void trythis(int i)
{
	if(i==2*n-1)//到达最后一个格子
	{
		if(test()) 
			count++;
		return;
	}
	else//未到达最后一个格子
	{
		if(cell[i]==1) trythis(i+1);
		else//此处未放置棋子
		{
			//试横放
			if((i+1)%n!=0&&cell[i+1]==0)
			{
				cell[i]=1;
				cell[i+1]=1;
				trythis(i+1);
				cell[i]=0;
				cell[i+1]=0;
			}
			if(i+n<=2*n-1&&cell[i+n]==0)
			{
				cell[i]=1;
				cell[i+n]=1;
				trythis(i+1);
				cell[i]=0;
				cell[i+n]=0;
			}
		}
	}
}

int main()
{
	cin>>n;
	for(int i=0;i<n;i++)
		cell[n]=0;
	trythis(0);
	cout<<count;
}