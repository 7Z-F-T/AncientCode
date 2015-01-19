#include <string>
#include <iostream>
using namespace std;

int main()
{
	int n;
	string str;
	cout<<"请输入连续的字符串"<<endl;
	cin>>str;
	n=(int)str.length();
	for(int i=0;i<n;i++)
		if(str[i]>=97&&str[i]<=122)
			str[i]=str[i]-32;
	cout<<str<<"\n小写字母已改为大写";
	return 0;
}