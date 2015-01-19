#include <vector>
#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

int main()
{
	vector<string> strings(10);
	cout<<"ÇëÊäÈëÊ®¸ö×Ö·û´®"<<endl;
	for(int i=0;i<10;i++)
		cin>>strings[i];
	vector<string>::iterator iter1=strings.begin();
    vector<string>::iterator iter2=strings.end();
	sort(iter1,iter2);
	for(int i=0;i<10;i++)
		cout<<strings[i]<<" ";
	return 0;
}