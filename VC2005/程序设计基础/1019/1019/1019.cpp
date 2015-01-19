#include <iostream>
using namespace std;
void swap(int &a,int &b);
int GetIt(int a,int b);


int main()
{
	int a;
	int b;
	cin>>a>>b;
	if(a>b) swap(a,b);
	cout<<GetIt(a,b);


}

void swap(int &a,int &b)
{
	int temp;
	temp=b;
	b=a;
	a=temp;
};

int GetIt(int a,int b)
{
	if(a==0) return b;
	else
	{
		b=b%a;
	    swap(a,b);
	    GetIt(a,b);
	}
	
};