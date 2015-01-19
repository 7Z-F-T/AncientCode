#include <iostream>
#include <string>
using namespace std;

int main()
{
	string str;
	cin>>str;
	int length=str.size();
	int n=str.size()/2;
	int i;
	for(i=0;i<n;i++)
		if(str[i]!=str[length-1-i]) break;
	if(i==n) cout<<"Yes it is";
	else cout<<"No it isn't";

	return 0;
}