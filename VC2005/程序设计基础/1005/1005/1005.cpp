#include <iostream>
using namespace std;

int main()
{
	int a,b;
	int damnDay=0;
	int damnHours=0;
	int hours[7];
	for(int i=0;i<7;i++)
	{
        cin>>a>>b;
		hours[i]=a+b;
	}
	for(int i=0;i<7;i++)
	{
		if(hours[i]>damnHours)
		{
			damnHours=hours[i];
			damnDay=i+1;
		}
	}
	if(damnHours<=8) cout<<0;
	else cout<<damnDay;

	return 0;	
}