#include <iostream>
using namespace std;
int cell[100];//0:δ�������� 1:�ѷ�������
int n;
int count=0;

bool test()
{
	for(int i=0;i<n;i++)
		if(cell[i]==0) return false;
	return true;
}
void trythis(int i)
{
	if(i==2*n-1)//�������һ������
	{
		if(test()) 
			count++;
		return;
	}
	else//δ�������һ������
	{
		if(cell[i]==1) trythis(i+1);
		else//�˴�δ��������
		{
			//�Ժ��
			if((i+1)%n!=0&&cell[i+1]==0)
			{
				cell[i]=1;
				cell[i+1]=1;
				trythis(i+1);
				cell[i]=0;
				cell[i+1]=0;
			}
			if(i+n<=2*n-1&&cell[i+n]==0)
			{
				cell[i]=1;
				cell[i+n]=1;
				trythis(i+1);
				cell[i]=0;
				cell[i+n]=0;
			}
		}
	}
}

int main()
{
	cin>>n;
	for(int i=0;i<n;i++)
		cell[n]=0;
	trythis(0);
	cout<<count;
}