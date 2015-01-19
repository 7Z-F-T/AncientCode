#include "stdio.h"

double Operate(double Num[],int Opr[])
{
	int i,j;
	
	while(Opr[0]!=0)
	{
		
		for(i=0;i<=8;i++)
		{
			if(Opr[i]==4)
			{
				Num[i]=Num[i]/Num[i+1];
				Num[i+1]=-1;
				Opr[i]=-1;
				for(i=0;i<=9;i++)
				{
					if(Num[i]==-1)
					{
						for(j=i;j<=9;j++)
						{
							Num[j]=Num[j+1];
						}
						
					}
				}
				for(i=0;i<=8;i++)
				{
					if(Opr[i]==-1)
					{
						for(j=i;j<=8;j++)
						{
							Opr[j]=Opr[j+1];
						}
					}
				}
				
			}

		}
		for(i=0;i<=8;i++)
		{	
			if(Opr[i]==3)
			{
				Num[i]=Num[i]*Num[i+1];
				Num[i+1]=-1;
				Opr[i]=-1;
				for(i=0;i<=9;i++)
				{
					if(Num[i]==-1)
					{
						for(j=i;j<=9;j++)
						{
							Num[j]=Num[j+1];
						}
						
					}
				}
				for(i=0;i<=8;i++)
				{
					if(Opr[i]==-1)
					{
						for(j=i;j<=8;j++)
						{
							Opr[j]=Opr[j+1];
						}
					}
				}
				
			}
		}
		for(i=0;i<=8;i++)
		{
			if(Opr[i]==2)
			{
				Num[i]=Num[i]-Num[i+1];
				Num[i+1]=-1;
				Opr[i]=-1;
				for(i=0;i<=9;i++)
				{
					if(Num[i]==-1)
					{
						for(j=i;j<=9;j++)
						{
							Num[j]=Num[j+1];
						}
						
					}
				}
				for(i=0;i<=8;i++)
				{
					if(Opr[i]==-1)
					{
						for(j=i;j<=8;j++)
						{
							Opr[j]=Opr[j+1];
						}
					}
				}
				
			}
		}
		for(i=0;i<=8;i++)
		{
			if(Opr[i]==1)
			{
				Num[i]=Num[i]+Num[i+1];
				Num[i+1]=-1;
				Opr[i]=-1;
				for(i=0;i<=9;i++)
				{
					if(Num[i]==-1)
					{
						for(j=i;j<=9;j++)
						{
							Num[j]=Num[j+1];
						}
						
					}
				}
				for(i=0;i<=8;i++)
				{
					if(Opr[i]==-1)
					{
						for(j=i;j<=8;j++)
						{
							Opr[j]=Opr[j+1];
						}
					}
				}
				
			}
				
		}
	}
	return Num[0];
}
	
void main()
{
	double result;
	double Num[10]={1,2,3,5};
	int Opr[9]={1,4,2};
	result=Operate(Num,Opr);
	printf("%f",result);	
}