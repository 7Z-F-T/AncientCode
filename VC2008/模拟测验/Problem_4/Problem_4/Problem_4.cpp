#include <iostream>
#include <string>
#include <vector>
using namespace std;

bool isHuiWen(string str)
{
	for(int i=0;i<str.size();i++)
		if(str[i]!=str[str.size()-i-1])
			return false;
	return true;
}

int main()
{
		
	int N;
	cin>>N;
	vector<string> vec(N);
    string temp;
	int count=0;


	for(int i=0;i<N;i++)
	{
		cin>>vec[i];
	}

	//start
	for(int i=0;i<N;i++)
	{
		if(isHuiWen(vec[i])==true)
		    count++;
	}

	
    cout<<count;
	

	


	return 0;
}