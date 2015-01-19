#include <iostream>
using namespace std;

int main()
{
    int k,n;
	int result;//ษฬ
	int mod;//ำเสý
	cin>>k>>n;
	mod=1%k;
	for(int i=1;i<=n;i++)
	{
        mod=mod*10;
		result=mod/k;
		mod=mod%k;
	}
	cout<<result;



	return 0;
}