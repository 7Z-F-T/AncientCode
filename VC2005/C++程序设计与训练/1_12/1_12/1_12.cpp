#include <string>
#include <iostream>
using namespace std;

int main()
{
	int n;
	string str;
	cout<<"�������������ַ���"<<endl;
	cin>>str;
	n=(int)str.length();
	for(int i=0;i<n;i++)
		if(str[i]>=97&&str[i]<=122)
			str[i]=str[i]-32;
	cout<<str<<"\nСд��ĸ�Ѹ�Ϊ��д";
	return 0;
}