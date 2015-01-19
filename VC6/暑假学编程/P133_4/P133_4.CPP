#include <iostream.h>;
#include <math.h>;
double f(double x);

int main()
{
	double a,b;//上下限
	double h,c=0;//中间变量
	int m=2000;//区间分割块数
	int i;//循环变量
	cout<<"本程序用来计算f(x)=cosx+x的积分值"<<endl;
	cout<<"请输入积分下限"<<endl;
	cin>>a;
	cout<<"请输入积分上限"<<endl;
	cin>>b;

	h=(b-a)/m;
    for(i=1;i<=(m-1);i++)
		c=c+f(a+i*h);

    cout<<"计算的结果为"<<endl;
	cout<<((f(a)+f(b))/2+c)*h;
	return 0;

}

double f(double x)
{
	return(cos(x)+x);
}