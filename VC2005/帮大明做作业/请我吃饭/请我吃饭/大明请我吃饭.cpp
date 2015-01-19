#include <stdio.h>

int main()
{
	long int a;//待操作数字
	int s5,s4,s3,s2,s1;//分别存储每位数字
	int count;//数字位数
	scanf("%d",&a);
	if(a>=10000)
	{
		count=5;
		s5=a/10000;a-=10000*s5;
		s4=a/1000;a-=1000*s4;
		s3=a/100;a-=100*s3;
		s2=a/10;a-=10*s2;
		s1=a;
	}
	else if(a>=10000)
	{
		count=5;
		s5=a/10000;a-=10000*s5;
		s4=a/1000;a-=1000*s4;
		s3=a/100;a-=100*s3;
		s2=a/10;a-=10*s2;
		s1=a;
	}
	else if(a>=1000)
	{
		count=4;
		s4=a/1000;a-=1000*s4;
		s3=a/100;a-=100*s3;
		s2=a/10;a-=10*s2;
		s1=a;
	}
	else if(a>=100)
	{
		count=3;
		s3=a/100;a-=100*s3;
		s2=a/10;a-=10*s2;
		s1=a;
	}
	else if(a>=10)
	{
		count=2;
		s2=a/10;a-=10*s2;
		s1=a;
	}
	else 
	{
		count=1;
		s1=a;
	}

	printf("输入数字的位数为%d\n",count);
	printf("顺序输出:\n");
	switch(count)
	{
	case 5:printf("%d%d%d%d%d",s5,s4,s3,s2,s1);break;
	case 4:printf("%d%d%d%d",s4,s3,s2,s1);break;
	case 3:printf("%d%d%d",s3,s2,s1);break;
	case 2:printf("%d%d",s2,s1);break;
	case 1:printf("%d",s1);break;
	}
	printf("倒序输出:\n");
	switch(count)
	{
	case 5:printf("%d%d%d%d%d",s1,s2,s3,s4,s5);break;
	case 4:printf("%d%d%d%d",s1,s2,s3,s4);break;
	case 3:printf("%d%d%d",s1,s2,s3);break;
	case 2:printf("%d%d",s1,s2);break;
	case 1:printf("%d",s1);break;
	}


	return 1;
}


