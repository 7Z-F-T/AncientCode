#include <iostream.h>

int main()
{
	int factory[5];//5个工厂,值为名次
	bool comments[5];//5个工厂的评论正确与否
	int count1=0,count2=0;
	int i,j,k,l,m,n;

	//初始化
	for(n=0;n<=4;n++)
	   factory[n]=0;
    for(n=0;n<=4;n++)
	   comments[n]=false;

	//开始枚举
	for(i=1;i<=5;i++)
	{
		factory[0]=i;
		for(j=1;j<=5;j++)
		{
			if(j==i) continue;
			factory[1]=j;
			for(k=1;k<=5;k++)
			{
				if(k==i||k==j) continue;
				factory[2]=k;
				for(l=1;l<=5;l++)
				{
					if(l==i||l==j||l==k) continue;
					factory[3]=l;
					for(m=1;m<=5;m++)
					{
						if(m==i||m==j||m==k||m==l) continue;
						factory[4]=m;

					      //判断众厂评论是否正确,5条有2条正确
						  if(factory[4]==1) {comments[0]=true; count1++;}
						  if(factory[1]==2) {comments[1]=true; count1++;}
						  if(factory[0]==5) {comments[2]=true; count1++;}
						  if(!(factory[2]==1)) {comments[3]=true; count1++;}
						  if(factory[3]==1) {comments[4]=true; count1++;}

						//判断是否正确评论出自排名领先二厂
						  for(n=0;n<=4;n++)
						{
							if(factory[n]==1&&comments[n]==true) count2++;
							if(factory[n]==2&&comments[n]==true) count2++;
						}

						//输出
						  if(count1==2&&count2==2&&(!(factory[4]==2))&&(!(factory[4]==3)))
						{
						    cout<<"A厂是第"<<factory[0]<<"名"<<endl;
						    cout<<"B厂是第"<<factory[1]<<"名"<<endl;
						    cout<<"C厂是第"<<factory[2]<<"名"<<endl;
						    cout<<"D厂是第"<<factory[3]<<"名"<<endl;
						    cout<<"E厂是第"<<factory[4]<<"名"<<endl;

						}
						//每次枚举后应重新清零
						count1=0;
						count2=0;
						for(n=0;n<=4;n++)
							comments[n]=false;
					}
				}
				
			}
		}
	}
	return 0;
}