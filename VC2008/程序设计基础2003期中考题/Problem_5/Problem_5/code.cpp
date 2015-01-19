#include <iostream>
using namespace std;

bool isHuZhi(int i,int j)
{
	int min;
	if(i<j) min=i;
	else min=j;
	for(int x=2;x<=min;x++)
		if(i%x==0&&j%x==0)
			return false;
	return true;
}
bool isLiangLiangHuZhi(int i,int j,int k,int l)
{
    if(isHuZhi(i,j)&&isHuZhi(i,k)&&isHuZhi(i,l)&&isHuZhi(j,k)&&isHuZhi(j,l)&&isHuZhi(k,l))
		return true;
	else return false;
}
int main()
{
	int a,b,c,d;
	int n;
	int L;
	int count=0;
	cin>>n>>a>>b>>c>>d>>L;

	for(int i=1;i<=n;i++)
		for(int j=1;j<=n;j++)
			for(int k=1;k<=n;k++)
				for(int l=1;l<=n;l++)
				{
					if(isLiangLiangHuZhi(i,j,k,l)&&a*i+b*j+c*k+d*l==L)
						count++;
				}
	cout<<count;
	return 0;
}