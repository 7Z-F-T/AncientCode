#include <iostream>
using namespace std;

int main()
{
	int person[99];
	int n,s,m;
	int count=0;
	cin>>n>>s>>m;
	if(n<=0||s<=0||m<=0)
	{
        cout<<"Wrong input!";
		return 0;
	}
	int left=n;//number of person that's not been out
	for(int i=1;i<=n;i++)
		person[i]=1;//1:still present 0:out
    int i=s;
	while(left>1)
	{
		if(person[i]==1)
		{
			count++;
			if(count==m)
			{
				person[i]=0;
				cout<<i<<" is out"<<endl;
				left--;
				count=0;
			}
		}
		i++;
		if(i>n) i=i-n;
	}
	for(int i=1;i<=n;i++)
		if(person[i]==1)
		{
			cout<<"Left:"<<i;
			return 0;
		}

	return 0;
	
}