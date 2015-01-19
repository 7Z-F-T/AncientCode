#include <iostream>
using namespace std;

int f(int n)
{
	if(n==1) return 1;
	if(n==2) return 3;
	else return f(n-1)+f(n-2);
}

int main()
{
	int input;
	cin>>input;
	cout<<f(input);
}
