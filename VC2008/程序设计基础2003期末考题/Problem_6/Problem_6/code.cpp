#include <iostream>
using namespace std;

struct pos
{
	int x;
	int y;
};

int day[64][8][8];//day[i][j][k]��ʾ��i��������x=j,y=k�����ʱ��������
pos beginpos[64];//beginpos[i]��ʾ��i�������ʼ����
int n;//����nƥ��
int objectday=0;
int i,j,k;//i:��ı�š�j:x���ꡣk:y����
bool OKjump=true;//true��ʾ�ɹ���չ



void jump(int x,int y)
{
	if(x>=0&&x<8&&y>=0&&y<8)
		if(day[i][x][y]==-1)
		{
			day[i][x][y]=objectday+1;
			OKjump=true;
		}
		else {}
	else {}
}
int main()
{

	//��������
	cin>>n;
	for(i=0;i<n;i++)
		cin>>beginpos[i].x>>beginpos[i].y;
	//��ʼ��day
	for(i=0;i<n;i++)
		for(int j=0;j<8;j++)
			for(int k=0;k<8;k++)
				day[i][j][k]=-1;
	for(i=0;i<n;i++)
		day[i][beginpos[i].x][beginpos[i].y]=0;

	//��ʼ��day[i][j][k]��ֵ
	i=0,j=0,k=0;

	while(OKjump==true)
	{
		OKjump=false;
		for(i=0;i<n;i++)
			for(j=0;j<8;j++)
				for(k=0;k<8;k++)
				{
					if(day[i][j][k]==objectday)
					{
						jump(j+1,k+2);
						jump(j+2,k+1);
						jump(j+1,k-2);
						jump(j+2,k-1);
						jump(j-1,k+2);
						jump(j-2,k+1);
						jump(j-1,k-2);
						jump(j-2,k-1);
					}
				}
				objectday++;
	}
	
	int needday[8][8];//ÿ�������Ե����������������ֵ����Ϊ�˸��������������
	int minday=99999;//���и����������������Сֵ�����Ž⣩
	int temp=0;
	for(j=0;j<8;j++)
		for(k=0;k<8;k++)
		{
			temp=0;
			for(i=0;i<n;i++)
			{
				if(day[i][j][k]>temp)
					temp=day[i][j][k];
			}
			needday[j][k]=temp;
			if(needday[j][k]<minday) minday=needday[j][k];
		}
		for(j=0;j<8;j++)
			for(k=0;k<8;k++)
			{
				if(needday[j][k]==minday)
				{
					cout<<j<<" "<<k<<endl;
					return 0;
					//������ĿҪ�����ж���⣬ֻ������е�һ�鼴�ɡ�������������һ�����˳�����
				}
			}


			return 0;


}