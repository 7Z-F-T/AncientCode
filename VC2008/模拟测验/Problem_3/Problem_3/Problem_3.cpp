#include <iostream>
using namespace std;

int main()
{
    int fired[101];//0:fired 1:not fired
	int S[11];
	int T[11];
	int N,M;
	int count=0;
	cin>>N;
	cin>>M;

	for(int i=1;i<=N;i++)
		fired[i]=0;
	for(int i=1;i<=M;i++)
		cin>>S[i]>>T[i];
    //start firing
	for(int i=1;i<=M;i++)
	{
		for(int j=S[i];j<=N;)
		{
			if(fired[j]==0)
				fired[j]=1;
			j+=T[i];
		}
	}
	//how many left
	for(int i=1;i<=N;i++)
		if(fired[i]==0)
			count++;

	cout<<count;
	


	return 0;
}