#include <iostream>
using namespace std;

int main()
{
	int score[100];
	int num;//²ÃÅÐÊý
	int temp;
	cin>>num;
	for(int i=0;i<=num-1;i++)
	{
		cin>>score[i];
	}
	for(int j=1;j<=num-1;j++)
	{
		for(int i=0;i<=num-j-1;i++)
		{
			if(score[i]>score[i+1])
			{
				temp=score[i+1];
				score[i+1]=score[i];
				score[i]=temp;
			}

		}
	}
    cout<<score[num-2]<<" "<<score[1];

	return 0;

}