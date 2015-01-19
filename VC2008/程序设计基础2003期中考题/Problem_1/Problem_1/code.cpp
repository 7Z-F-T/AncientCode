#include <iostream>
using namespace std;

int main()
{
	int num[4];
	cin>>num[0]>>num[1]>>num[2]>>num[3];//store a,b,c,d
	//find max of a,b,c,d
	int max=0;
	for(int i=0;i<4;i++)
	{
        if(num[i]>max) max=num[i];
	}
	//开始向上枚举/测试
	for(int i=max;;i++)
	{
		if(i%num[0]==2&&i%num[1]==2&&i%num[2]==2&&i%num[3]==2)
		{
			cout<<i;
			break;
		}
	}

	return 0;

}