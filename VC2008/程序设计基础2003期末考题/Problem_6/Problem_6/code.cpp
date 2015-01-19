#include <iostream>
using namespace std;

struct pos
{
	int x;
	int y;
};

int day[64][8][8];//day[i][j][k]表示第i号马跳至x=j,y=k坐标格时所需天数
pos beginpos[64];//beginpos[i]表示第i号马的起始坐标
int n;//共有n匹马
int objectday=0;
int i,j,k;//i:马的标号。j:x坐标。k:y坐标
bool OKjump=true;//true表示成功扩展



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

	//输入数据
	cin>>n;
	for(i=0;i<n;i++)
		cin>>beginpos[i].x>>beginpos[i].y;
	//初始化day
	for(i=0;i<n;i++)
		for(int j=0;j<8;j++)
			for(int k=0;k<8;k++)
				day[i][j][k]=-1;
	for(i=0;i<n;i++)
		day[i][beginpos[i].x][beginpos[i].y]=0;

	//开始给day[i][j][k]赋值
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
	
	int needday[8][8];//每格各马各自到达所需天数的最大值（即为此格相会所需天数）
	int minday=99999;//所有格相会所需天数的最小值（最优解）
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
					//由于题目要求若有多组解，只输出其中的一组即可。所以这里输完一组后就退出程序
				}
			}


			return 0;


}