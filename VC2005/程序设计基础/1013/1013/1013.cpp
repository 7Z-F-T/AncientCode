#include <iostream>
using namespace std;
int GetMax(int x);
int GetMin(int x);

void main()
{
	int x;//����������λ����
	cout<<"������һ����λ����"<<endl;
	cin>>x;
	for(int i=1;i<=500;i++)
	{
		x=GetMax(x)-GetMin(x);
	}

	cout<<x<<endl;

}

int GetMax(int x)
{
	int a[4];//�ֱ��������������λ������ǧ���٣�ʮ����λ
	int i,j,t;
	a[0]=x/1000;
	a[1]=(x-a[0]*1000)/100;
	a[2]=(x-a[0]*1000-a[1]*100)/10;
	a[3]=(x-a[0]*1000-a[1]*100-a[2]*10);
	for(j=1;j<=3;j++)
	{
		for(i=0;i<=2;i++)
			if(a[i+1]>a[i])
			{
				t=a[i+1];
				a[i+1]=a[i];
				a[i]=t;
			}
	}
	x=1000*a[0]+100*a[1]+10*a[2]+a[3];
	return x;

}

int GetMin(int x)
{
	int a[4];//�ֱ��������������λ������ǧ���٣�ʮ����λ
	int i,j,t;
	a[0]=x/1000;
	a[1]=(x-a[0]*1000)/100;
	a[2]=(x-a[0]*1000-a[1]*100)/10;
	a[3]=(x-a[0]*1000-a[1]*100-a[2]*10);
	for(j=1;j<=3;j++)
	{
		for(i=0;i<=2;i++)
			if(a[i+1]<a[i])
			{
				t=a[i+1];
				a[i+1]=a[i];
				a[i]=t;
			}
	}
	x=1000*a[0]+100*a[1]+10*a[2]+a[3];
	return x;

}