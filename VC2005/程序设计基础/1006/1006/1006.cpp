#include <iostream>
#include <vector>
using namespace std;
int num[20];//存整数
bool isUsed[20];//存标记
int select[20];//存选取的数
int n,k;
int primeCount=0;


int operate()
{
	int sum=0;
	for(int i=0;i<k;i++)
	{
		sum=sum+select[i];
	}
	int count=0;
	for(int i=1;i<=sum;i++)
	{
		if(sum%i==0) count++;
	}
	if(count==2) 
	{
		return 1;
	}
	return 0;

}

void find(int pos,int start)
{
	for(int i=start;i<n;i++)
	{
		if(isUsed[i]==0)
		{
             select[pos]=num[i];
			 isUsed[i]=1;
			 if(pos==k-1)
			 {
				 primeCount+=operate();
			 }
			 else find(pos+1,i+1);
		
		}
		isUsed[i]=0;//打扫干净再换新地方
		
	}
}



int main()
{
	
	for(int i=0;i<20;i++)
		isUsed[i]=0;//0代表没用过，1代表已用

	cin>>n>>k;
	for(int i=0;i<n;i++)
	{
		cin>>num[i];
	}

	find(0,0);
	cout<<primeCount;



	return 0;
}