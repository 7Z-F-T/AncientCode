#include <iostream>
#include <string>
using namespace std;

int mode=1;//1:�ӷ� 2:���� 0:����
int sum;//��ĿǰΪֹ�ļ�����
int temp=0;
string str;
string input;

void calc()
{
	if(mode==1)
	{
		sum=sum+atoi(str.c_str());
	}
	else if(mode==2)
	{
		sum=sum-atoi(str.c_str());
	}
}

int main()
{

	cin>>input;

	string::iterator it=input.begin();
	for(;it<input.end();it++)
	{
		if(*it>='0'&&*it<='9')
			str.push_back(*it);
		else if(*it=='+')
		{
			calc();
			mode=1;
			str.clear();
		}
		else if(*it=='-')
		{
			calc();
			mode=2;
			str.clear();
		}
		else if(*it=='=')
		{
			calc();
			mode=0;
			str.clear();
		}

	}
	cout<<sum;




	return 0;
}