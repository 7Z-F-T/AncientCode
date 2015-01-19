#include <iostream>
#include <vector>
using namespace std;
int N;
int sum=0;
vector<int> num;

void recursion(int start)
{
	for(int i=start;i<=N-sum;i++)
	{
		if(i==N) return;
		sum+=i;
		num.push_back(i);
		if(sum==N)
		{
			for(int j=0;j<num.size();j++)
				cout<<num[j]<<" ";
			cout<<endl;
			num.pop_back();
			sum-=i;
			return;
		}
		else recursion(i);
		num.pop_back();
		sum-=i;
	}
}
int main()
{
	cin>>N;
	recursion(1);

	return 0;
}