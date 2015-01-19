#include <iostream>
#include <string>
using namespace std;

int mode=1;//1:加法 2:减法 0:结束
int sum;//到目前为止的计算结果
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