#include <iostream>
using namespace std;
const int maxInt=0x7fffffff;
int arraySize;
int* A;

int factorial(int k)
{
	int s=1;
	int i=1;
	while(i<=k)
     	s=s*i++;
	if(s>maxInt) {cerr<<"TOO LARGE!";exit(1);}
    return s;
}
int PowerOf2(int k)
{
	int s=1;
	for(int i=1;i<=k;i++)
		s=2*s;
	if(s>maxInt) {cerr<<"TOO LARGE!";exit(1);}
    return s;
}

int main()
{
	cout<<"Please input arraySize"<<endl;
	cin>>arraySize;
	A=new int[arraySize];

	for(int i=0;i<arraySize;i++)
	{
		int result=factorial(i+1)*PowerOf2(i+1);
		if(result>maxInt||result<0) {cerr<<"TOO LARGE!";exit(1);}
		A[i]=result;
	}
	for(int i=0;i<arraySize;i++)
		cout<<i+1<<"!*2^"<<i+1<<"="<<A[i]<<endl;
}