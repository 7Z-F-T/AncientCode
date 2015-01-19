#include <iostream>
using namespace std;

int main()
{
	int n;
	int factorial=1;
	cin>>n;
	for(int i=1;i<=n;i++)
		factorial=factorial*i;

	for(;;)
	{
		if(factorial%10==0) factorial=factorial/10;
		else break;
	}

	cout<<factorial%10;

	return 0;
}