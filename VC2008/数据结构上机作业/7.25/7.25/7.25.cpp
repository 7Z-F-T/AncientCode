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
	cout<<"�����������ݵĸ��������ݸ���ΪF(n)-1����n>2)"<<endl;
	cout<<"n=?";
	int n;
	int nData;//���ݸ���
	cin>>n;
	nData=F(n)-1;
	cout<<"���ݵĸ���Ϊ"<<nData<<endl;
	cout<<"������������"<<nData<<"����"<<endl;
    int* Data=new int[nData+1];//���±�1��ʼ�洢
	for(int i=1;i<=nData;i++)
		cin>>Data[i];
	//����ð�ݷ�
	for(int i=1;i<=nData-1;i++)
		for(int j=1;j<=nData-i;j++)
			if(Data[j]>Data[j+1])
			{
				int temp;
			    temp=Data[j+1];
				Data[j+1]=Data[j];
				Data[j]=temp;
			}
	cout<<"����������Ҫ��������ֵ:";
	int m;
	cin>>m;
	if(FSearch(Data,n-1,F(n-1),m)) cout<<"�ҵ���"<<endl;
	else cout<<"û�ҵ���"<<endl;

	return 0;

}