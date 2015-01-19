#include <iostream>
#include <vector>
#include <math.h>
using namespace std;

vector<int> PrimeNum;

bool isPrimeNum(int n)
{
	float k=sqrt((float)n);
	for(int i=2;i<=k;i++)
		if(n%i==0) return false;
	return true;

}

void findPrimeNum(int B)//find all prime numbers that <=B
{
	for(int i=2;i<=B;i++)
		if(isPrimeNum(i))
			PrimeNum.push_back(i);
}

bool Satisfied(int n)//判断n是否是B光滑数
{
	for(int i=0;i<PrimeNum.size();i++)
	{
			if(n%PrimeNum[i]==0) 
			{
				n=n/PrimeNum[i];
				i=-1;
			}
			else 
			{
				if(n==1) return true;
				else if(i==PrimeNum.size()-1) return false;
			}
	}
}
int main()
{	
	int n,m,B;
	int count=0;
	cin>>n>>m>>B;
	findPrimeNum(B);
	
	for(int i=n;i<=m;i++)
	{
	    if(Satisfied(i))
			count++;	
	}

	cout<<count;

	return 0;
}