#include <iostream>
using namespace std;

int FindMax(int* A,int left)
{
	if(left==1)
		return *A;
	else
	{
	    int MaxBehind=FindMax(A+1,left-1);
	    if(*A>MaxBehind)
		    return *A;
	    else return MaxBehind;
	}
}

int Sum(int* A,int left)
{
	if(left==1) return *A;
	else return *A+Sum(A+1,left-1);
}

float Average(int* A,int left)
{
	if(left==1) return *A;
	else return (*A+Average(A+1,left-1)*(left-1))/(float)left;
}
int main()
{
	int n;
	cout<<"Please input the n of A[n]"<<endl;
	cin>>n;
	cout<<"Please input the elements of A[n]"<<endl;
	int* A=new int[n];
	for(int i=0;i<n;i++)
		cin>>A[i];

	cout<<"Maximum Number:"<<FindMax(A,n)<<endl;
	cout<<"Sum:"<<Sum(A,n)<<endl;
	cout<<"Average Value:"<<Average(A,n)<<endl;


}