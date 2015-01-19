#include <iostream>
#include <math.h>
#include "Rational.h"
using namespace std;

int  Rational::GetNum()
{
	return(num);
}
void Rational::SetNum(int num)
{
	this->num=num;
}
int  Rational::GetDen()
{
	return(den);
}
bool Rational::SetDen(int den)
{
	if(den==0)
		return false;
	else
	{this->den=den;return true;}
}
bool Rational::Assign(int num, int den)
{
	if(den==0)
		return false;
	else
	{
		this->num=num;
		this->den=den;
		return true;
	}
}
void Rational::Copy(Rational r)
{
	num=r.num;
	den=r.den;
}
void Rational::Add(Rational r)
{
	int tempden=den*r.den;
	int tempnum1=num*(tempden/den);
	int tempnum2=r.num*(tempden/r.den);
	int tempnum=tempnum1+tempnum2;
	int divisor=1;
	int min=(abs(tempnum)<=abs(tempden))?abs(tempnum):abs(tempden);  //约
	for(int i=1;i<=min;i++)                      //分
	{if(tempnum%i==0&&tempden%i==0) divisor=i;}  //过
    num=tempnum/divisor;                         //程
	den=tempden/divisor;
}
void Rational::Sub(Rational r)
{
	int tempden=den*r.den;
	int tempnum1=num*(tempden/den);
	int tempnum2=r.num*(tempden/r.den);
	int tempnum=tempnum1-tempnum2;
	int divisor=1;
	int min=(abs(tempnum)<=abs(tempden))?abs(tempnum):abs(tempden);  //约
	for(int i=1;i<=min;i++)                      //分
	{if(tempnum%i==0&&tempden%i==0) divisor=i;}  //过
    num=tempnum/divisor;                         //程
	den=tempden/divisor;
}
void Rational::Mul(Rational r)
{
	int tempden=den*r.den;
	int tempnum=num*r.num;
	int divisor=1;
	int min=(abs(tempnum)<=abs(tempden))?abs(tempnum):abs(tempden);  //约
	for(int i=1;i<=min;i++)                      //分
	{if(tempnum%i==0&&tempden%i==0) divisor=i;}  //过
	num=tempnum/divisor;                         //程
	den=tempden/divisor;
}
bool Rational::Div(Rational r)
{
	if(r.num==0) return false;
	else
	{int tempden=den*r.num;
	int tempnum=num*r.den;
	int divisor=1;
	int min=(abs(tempnum)<=abs(tempden))?abs(tempnum):abs(tempden);  //约
	for(int i=1;i<=min;i++)                      //分
	{if(tempnum%i==0&&tempden%i==0) divisor=i;}  //过
	num=tempnum/divisor;                         //程
	den=tempden/divisor;
	return true;}
}
bool Rational::Equal(Rational r)
{
	int tempden=den*r.den;
	int tempnum1=num*(tempden/den);
	int tempnum2=r.num*(tempden/r.den);
	if(tempnum2==tempnum1) return true;
	else return false;
}

int main()
{
	Rational r1,r2;
	int num,den,choice;
	cout<<"请输入第一个分数的分子与分母，以空格分隔"<<endl;
	cin>>num>>den;
	if(r1.Assign(num,den)==false) {cout<<"分母不能为零！";return -1;}
	cout<<"请输入第二个分数的分子与分母，以空格分隔"<<endl;
	cin>>num>>den;
	if(r2.Assign(num,den)==false) {cout<<"分母不能为零！";return -1;}
	cout<<"请选择运算类型"<<endl;
	cout<<"加法－－1  减法－－2  乘法－－3 除法－－4 判断是否相等－－5 \n将第二个分数拷贝到第一个分数上－－6"<<endl;

	cin>>choice;
	switch(choice)
	{
		case 1:
	cout<<"两个分数之和为";
    r1.Add(r2);
	cout<<r1.GetDen()<<"分之"<<r1.GetNum()<<endl;break;
		case 2:
	cout<<"两个分数之差为";
    r1.Sub(r2);
	cout<<r1.GetDen()<<"分之"<<r1.GetNum()<<endl;break;
		case 3:
    cout<<"两个分数之积为";
    r1.Mul(r2);
	cout<<r1.GetDen()<<"分之"<<r1.GetNum()<<endl;break;
		case 4:
	if(r1.Div(r2)==false) {cout<<"除数不能为零！";return -1;};
	cout<<"两个分数之商为";
    cout<<r1.GetDen()<<"分之"<<r1.GetNum()<<endl;break;
		case 5:
    if(r1.Equal(r2)==true)
		cout<<"两者相等"<<endl;
	else
		cout<<"两者不等"<<endl;
		case 6:
    r1.Copy(r2);
    cout<<"第一个分数为"<<r1.GetDen()<<"分之"<<r1.GetNum()<<endl;
    cout<<"第二个分数为"<<r2.GetDen()<<"分之"<<r2.GetNum()<<endl;break;
	}
	return 0;
}






