#include <iostream>
using namespace std;

int main()
{
	int n;
	int m;
	int goods[51][101];
	int buyat[101];//buyat[i]存储i号产品应在哪个超市买
	cin>>n>>m;
	for(int i=1;i<=n;i++)
		for(int j=1;j<=m;j++)
			cin>>goods[i][j];
    //初始化buyat[]
	for(int i=1;i<=m;i++)
		buyat[i]=0;
	//开始挑
	for(int i=1;i<=m;i++)//i:产品标号
	{
		int min=9999999;
		for(int j=1;j<=n;j++)//j:商店标号
		{
			if(goods[j][i]<=min&&goods[j][i]!=0)
			{
				min=goods[j][i];
				buyat[i]=j;
			}
		}
	}
	//output
	for(int i=1;i<=m;i++)
		cout<<buyat[i];

	return 0;

	
}