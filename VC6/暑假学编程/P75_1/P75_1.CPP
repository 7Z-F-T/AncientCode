//////////////////////////////////////////////////////////////////////////
//这里用到一个小技巧.你想让用户输入数组大小,但你却要固定数组大小.因为定义数组大小时不可用变量.
//所以你可以先定义一个很大的数组,让用户输入的不是数组的大小,而是数组中需要操作的数的多少(剩下的数组变量不进行操作)
//这样可以模仿动态创建数组的效果

#include<iostream>
using namespace std;

int main()
{
	const MAXLAMP=1000;
	int lamp[MAXLAMP],i,k,n;
    cout<<"请输入要操作的灯的个数"<<endl;//最上方说的方法
	cin>>n;

	//第一个人把灯全关
	for(i=1;i<=n;i++)
		lamp[i]=0;

	//第二个人,开偶数灯
	k=2;
	while(k<=n)
	{lamp[k]=1;
	k=k+2;
	}

	//第三到最后一个人
	for(i=3;i<=n;i++)
	{
		k=i;
		while(k<=n)
		{
			lamp[k]=!lamp[k];
			k=k+i;
		}
	}

	//输出
	cout<<"这帮人折腾后,还亮着的灯编号为"<<endl;
	for(i=1;i<=n;i++)
		if(lamp[i]==1) cout<<i<<endl;
	
	return 0;
}