#include <iostream>
using namespace std;
int* data=NULL;
int n,r;

void output()
{
	for(int i=0;i<r;i++)
		cout<<data[i]<<" ";
	cout<<endl;
}

void recursion(int k, int start)
{
	for(int i=start;i<=n;i++)
	{
		data[k]=i;
		if(k==r-1) output();
		else recursion(k+1,i+1);
	}
		
}

int main()
{
	cout<<"Please input n"<<endl;
	cin>>n;
	cout<<"Please input r"<<endl;
	cin>>r;
	data=new int[r];
	for(int i=0;i<r;i++)
		data[r]=0;

	recursion(0,1);

}