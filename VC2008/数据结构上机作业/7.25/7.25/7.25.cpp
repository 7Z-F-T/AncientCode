#include <iostream>
#include <cmath>
using namespace std;

int F(int n)
{
	double k=sqrt(double(5));
    return 1/k*(pow((1+k)/2,n)-pow((1-k)/2,n));
}

bool FSearch(int* data, int k, int Fk, int m)
{
	if(k==1||k==0) return false;
	else
	{
	if(m==data[Fk]) return true;
	else if(m<data[Fk]) FSearch(data,k-1,Fk-F(k)+F(k-1),m);
	else FSearch(data,k-2,Fk+F(k-2),m);
	}
}

int main()
{
	cout<<"请先输入数据的个数（数据个数为F(n)-1，且n>2)"<<endl;
	cout<<"n=?";
	int n;
	int nData;//数据个数
	cin>>n;
	nData=F(n)-1;
	cout<<"数据的个数为"<<nData<<endl;
	cout<<"现在请输入这"<<nData<<"个数"<<endl;
    int* Data=new int[nData+1];//从下标1开始存储
	for(int i=1;i<=nData;i++)
		cin>>Data[i];
	//排序，冒泡法
	for(int i=1;i<=nData-1;i++)
		for(int j=1;j<=nData-i;j++)
			if(Data[j]>Data[j+1])
			{
				int temp;
			    temp=Data[j+1];
				Data[j+1]=Data[j];
				Data[j]=temp;
			}
	cout<<"现在请输入要搜索的数值:";
	int m;
	cin>>m;
	if(FSearch(Data,n-1,F(n-1),m)) cout<<"找到！"<<endl;
	else cout<<"没找到！"<<endl;

	return 0;

}