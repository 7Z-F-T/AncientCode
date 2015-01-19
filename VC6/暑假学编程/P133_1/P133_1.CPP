#include <iostream.h>
double sin(double x);
double square(double x,int a);
double flat(int a);


double sin(double x)
{
	double e,sum=0;
	int a=1,j=1;
	e=0.00000001; //当某一项的绝对值小于此值时，停止计算
	do{
        sum=sum+j*square(x,a)/flat(a);
		j=-j;
		a=a+2;
 
	}while((square(x,a)/flat(a))>=e);
return sum;
}

double square(double x,int a)
{
	double sum=1;
	int i;
	for(i=1;i<=a;i++)
		sum=sum*x;
	return sum;

}

double flat(int a)
{
	double sum=1;
	if(a==1) sum=1;
	else sum=a*flat(a-1);
	return sum;

}

int main()
{
	double x;
	cout<<"计算sin(x)程序，请立即输入x，过时不候！"<<endl;
	cin>>x;
	cout<<"你好，sin("<<x<<")的结果为"<<sin(x)<<endl;
	cout<<"谢谢使用！侯杰 copyright 2007-07-16"<<endl;
    return 0;
}