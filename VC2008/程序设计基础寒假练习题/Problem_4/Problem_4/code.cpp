#include <iostream>
#include <vector>
using namespace std;

int DayNeed[30];
int DayProducePrice[30];
int DayStorePrice[30];
int N;
int DayProduce[30];
struct Record
{
	int NeedNeedFromTodayToFinalday;
	int YesterdayLeft;
	int i;
};
Record SelectRecord[30][999][999];
//Record** SelectRecord=new Record[9][9];

//考虑第N-n号天（从0开始记），P(n,NeedFromTodayToFinalday,YesterdayLeft)为从当天至最后一天共n天所需费用的最小值
int P(int n,int NeedFromTodayToFinalday,int YesterdayLeft)
{
    int min=9999999;
	int sum=0;
	int i;
	if(n==1)//最后一天了
	{
	    min=DayProducePrice[N-1]*(DayNeed[N-1]-YesterdayLeft);//最后一天不要多产
		SelectRecord[N-1][NeedFromTodayToFinalday][YesterdayLeft].i=DayNeed[N-1]-YesterdayLeft;
        SelectRecord[N-1][NeedFromTodayToFinalday][YesterdayLeft].NeedNeedFromTodayToFinalday=NeedFromTodayToFinalday;
		SelectRecord[N-1][NeedFromTodayToFinalday][YesterdayLeft].YesterdayLeft=YesterdayLeft;
	}
	    
	else
	{
	if(DayNeed[N-n]<YesterdayLeft) i=0;
	else i=DayNeed[N-n]-YesterdayLeft;
	for(;i<=NeedFromTodayToFinalday;i++)//i为当天产量
	{
        sum=DayProducePrice[N-n]*i+DayStorePrice[N-n]*(i+YesterdayLeft-DayNeed[N-n])+P(n-1,NeedFromTodayToFinalday-i,i+YesterdayLeft-DayNeed[N-n]);
		if(sum<min) 
		{
			min=sum;
			SelectRecord[N-n][NeedFromTodayToFinalday][YesterdayLeft].i=i;
			SelectRecord[N-n][NeedFromTodayToFinalday][YesterdayLeft].NeedNeedFromTodayToFinalday=NeedFromTodayToFinalday;
			SelectRecord[N-n][NeedFromTodayToFinalday][YesterdayLeft].YesterdayLeft=YesterdayLeft;

		}
	
	}
	}
    
	return min;
}
void OutputResult(int a,int NeedFromTodayToFinalday,int YesterdayLeft)
{
	for(int j=0;j<999;j++)
		for(int k=0;k<999;k++)
			if(SelectRecord[a][j][k].NeedNeedFromTodayToFinalday==NeedFromTodayToFinalday&&SelectRecord[a][j][k].YesterdayLeft==YesterdayLeft)
			{
				cout<<SelectRecord[a][j][k].i<<" ";
				if(a<N-1)
				    OutputResult(a+1,SelectRecord[a][j][k].NeedNeedFromTodayToFinalday-SelectRecord[a][j][k].i,SelectRecord[a][j][k].i+SelectRecord[a][j][k].YesterdayLeft-DayNeed[a]);
			}

}

int main()
{
	cin>>N;
	for(int i=0;i<N;i++)
		cin>>DayNeed[i];
	for(int i=0;i<N;i++)
		cin>>DayProducePrice[i];
	for(int i=0;i<N;i++)
		cin>>DayStorePrice[i];

	int YesterdayLeft=0;
	int NeedFromTodayToFinalday=0;

	for(int i=0;i<N;i++)
		NeedFromTodayToFinalday+=DayNeed[i];
	YesterdayLeft=0;
	for(int i=0;i<N;i++)
		DayProduce[i]=0;

	for(int i=0;i<N;i++)
		for(int j=0;j<999;j++)
			for(int k=0;k<999;k++)
			{
				SelectRecord[i][j][k].NeedNeedFromTodayToFinalday=-1;
				SelectRecord[i][j][k].YesterdayLeft=-1;
				SelectRecord[i][j][k].i=-1;
			}

	P(N,NeedFromTodayToFinalday,YesterdayLeft);


	for(int j=0;j<999;j++)
		for(int k=0;k<999;k++)
			if(SelectRecord[0][j][k].i!=-1)
			{
				cout<<SelectRecord[0][j][k].i<<" ";
				OutputResult(1,SelectRecord[0][j][k].NeedNeedFromTodayToFinalday-SelectRecord[0][j][k].i,SelectRecord[0][j][k].i+SelectRecord[0][j][k].YesterdayLeft-DayNeed[0]);
				return 0;
			}


	return 0;


}