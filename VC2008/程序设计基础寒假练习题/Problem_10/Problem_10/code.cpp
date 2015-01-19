#include <iostream>
#include <vector>
using namespace std;

int main()
{
	int N;
	cin>>N;
	int power=N*N*N;
	int sum=0;
	vector<int> num;

	for(int i=1;i<=power;)
	{
		sum=0;
        num.clear();
		for(int j=i;j<=power;)
		{
			if(sum>power) break;
			if(num.size()>N) break;
            num.push_back(j);
			sum+=j;
			if(sum==power)
			{
				if(num.size()==N)
				{
				    for(int k=0;k<num.size();k++)
					    cout<<num[k]<<" ";
				    return 0;
				}
			}
			j+=2;
		}
		i+=2;
	}

}