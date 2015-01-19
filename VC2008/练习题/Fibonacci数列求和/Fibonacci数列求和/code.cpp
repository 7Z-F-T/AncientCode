#include <iostream>
#include <vector>
using namespace std;

int f(int n)
{
	if(n==1) return 1;
	else if(n==2) return 1;
	else return f(n-1)+f(n-2);
	
}
int fsum(int n)
{
	int sum=0;
	for(int i=1;i<=n;i++)
		sum+=f(i);
	return sum;
}
int main()
{
	vector<int> input;
	vector<int> output;
	//cout<<f(4);

	int n;
	cin>>n;
	while(n!=0)
	{
		output.push_back(fsum(n));
		cin>>n;
	}
	for(int i=0;i<output.size();i++)
		cout<<output[i]<<endl;

	return 0;
}