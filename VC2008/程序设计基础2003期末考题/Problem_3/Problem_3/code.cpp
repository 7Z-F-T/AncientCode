#include <iostream>
#include <math.h>
#include <vector>
using namespace std;

bool isPrime(int a)
{
	if(a==1) return false;
	for(int i=2;i<=sqrt((float)a);i++)
		if(a%i==0) return false;
	return true;
}

int main()
{
	//cout<<isPrime(43);
	int n;
	cin>>n;
	int tempcount=0;
	int count=0;
	vector<int> result;
	for(int j=pow(10,(float)n-1),i;j<pow(10,(float)n);j++)
	{	
		i=j;
		tempcount=0;
		//if(!isPrime(i)) continue;
		while(i>0)
		{
			if(isPrime(i)) 
			{
				tempcount++;
				i=i/10;
			}
			else break;
		}
		if(tempcount==n) 
		{
			result.push_back(j);
		}
		
	}
	cout<<result.size()<<endl;
	for(int i=0;i<result.size();i++)
		cout<<result[i]<<endl;

	return 0;
}