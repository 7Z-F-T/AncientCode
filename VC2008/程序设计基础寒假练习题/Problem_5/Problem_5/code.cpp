#include <iostream>
using namespace std;
int num[10];//存储每一个数值
int select[10];//标识第i个数进了第几组
int selectFORout[10];
int sum[10];//标识每个组的S
int n;
int k;
int calculate();
int result=999999;

int calculate()
{
	int finalsum=0;
	for(int i=0;i<k;i++)
	{
		int S=0;
		for(int j=0;j<n;j++)
		{
			if(select[j]==i) S+=num[j];
		}
		sum[i]=S;
	}
	for(int i=0;i<k-1;i++)
		for(int j=i+1;j<k;j++)
			finalsum+=(sum[i]-sum[j])*(sum[i]-sum[j]);
	return finalsum;
}

void recursion(int i)
{
	if(i==n)//最后一个数也选完分组了
	{
		//开始检测是不是每个组里都有数
		int OKdivide=0;
		for(int l=0;l<k;l++)
			for(int m=0;m<n;m++)
			{
				if(select[m]==l) 
				{
					OKdivide++;
					break;
				}
			}

		if(OKdivide==k)
			if(calculate()<result)
			{
				result=calculate();
				for(int l=0;l<n;l++)
					selectFORout[l]=select[l];
			}
	}
	else
	{
	    for(int j=0;j<k;j++)
	    {
	    	select[i]=j;
    		recursion(i+1);
    	}
	}
}

int main()
{

	cin>>n;
	cin>>k;
	
	for(int i=0;i<n;i++)
		select[i]=-1;
	for(int i=0;i<n;i++)
		cin>>num[i];

	recursion(0);

	for(int i=0;i<n;i++)
		cout<<selectFORout[i]<<" ";
	cout<<"result:"<<result<<endl;

	return 0;

}
