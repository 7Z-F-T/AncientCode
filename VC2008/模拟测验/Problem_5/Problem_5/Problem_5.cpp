#include <iostream>
#include <vector>
using namespace std;

int N;
int M;
int LAMPleft[2];//LAMPleft[0]:red LAMPleft  LAMPleft[1]:yellow LAMPleft
vector<int> list;


void put(int n)
{
	if(n==N+M)//此时不需再放了
		{
			for(int j=0;j<list.size();j++)
				cout<<list[j];
			cout<<endl;
		}

	else 
		for(int i=0;i<=1;i++)
	{
		
		if(LAMPleft[i]>0)
		{
			list[n]=i;
			LAMPleft[i]--;
			put(n+1);
			LAMPleft[i]++;//回溯
		}
	}
}




int main()
{

	cin>>N>>M;
	LAMPleft[0]=N;
	LAMPleft[1]=M;
	list.resize(N+M);

	put(0);




	return 0;
}