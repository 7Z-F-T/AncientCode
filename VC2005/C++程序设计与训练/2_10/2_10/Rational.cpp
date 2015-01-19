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
	int min=(abs(tempnum)<=abs(tempden))?abs(tempnum):abs(tempden);  //Լ
	for(int i=1;i<=min;i++)                      //��
	{if(tempnum%i==0&&tempden%i==0) divisor=i;}  //��
    num=tempnum/divisor;                         //��
	den=tempden/divisor;
}
void Rational::Sub(Rational r)
{
	int tempden=den*r.den;
	int tempnum1=num*(tempden/den);
	int tempnum2=r.num*(tempden/r.den);
	int tempnum=tempnum1-tempnum2;
	int divisor=1;
	int min=(abs(tempnum)<=abs(tempden))?abs(tempnum):abs(tempden);  //Լ
	for(int i=1;i<=min;i++)                      //��
	{if(tempnum%i==0&&tempden%i==0) divisor=i;}  //��
    num=tempnum/divisor;                         //��
	den=tempden/divisor;
}
void Rational::Mul(Rational r)
{
	int tempden=den*r.den;
	int tempnum=num*r.num;
	int divisor=1;
	int min=(abs(tempnum)<=abs(tempden))?abs(tempnum):abs(tempden);  //Լ
	for(int i=1;i<=min;i++)                      //��
	{if(tempnum%i==0&&tempden%i==0) divisor=i;}  //��
	num=tempnum/divisor;                         //��
	den=tempden/divisor;
}
bool Rational::Div(Rational r)
{
	if(r.num==0) return false;
	else
	{int tempden=den*r.num;
	int tempnum=num*r.den;
	int divisor=1;
	int min=(abs(tempnum)<=abs(tempden))?abs(tempnum):abs(tempden);  //Լ
	for(int i=1;i<=min;i++)                      //��
	{if(tempnum%i==0&&tempden%i==0) divisor=i;}  //��
	num=tempnum/divisor;                         //��
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
	cout<<"�������һ�������ķ������ĸ���Կո�ָ�"<<endl;
	cin>>num>>den;
	if(r1.Assign(num,den)==false) {cout<<"��ĸ����Ϊ�㣡";return -1;}
	cout<<"������ڶ��������ķ������ĸ���Կո�ָ�"<<endl;
	cin>>num>>den;
	if(r2.Assign(num,den)==false) {cout<<"��ĸ����Ϊ�㣡";return -1;}
	cout<<"��ѡ����������"<<endl;
	cout<<"�ӷ�����1  ��������2  �˷�����3 ��������4 �ж��Ƿ���ȣ���5 \n���ڶ���������������һ�������ϣ���6"<<endl;

	cin>>choice;
	switch(choice)
	{
		case 1:
	cout<<"��������֮��Ϊ";
    r1.Add(r2);
	cout<<r1.GetDen()<<"��֮"<<r1.GetNum()<<endl;break;
		case 2:
	cout<<"��������֮��Ϊ";
    r1.Sub(r2);
	cout<<r1.GetDen()<<"��֮"<<r1.GetNum()<<endl;break;
		case 3:
    cout<<"��������֮��Ϊ";
    r1.Mul(r2);
	cout<<r1.GetDen()<<"��֮"<<r1.GetNum()<<endl;break;
		case 4:
	if(r1.Div(r2)==false) {cout<<"��������Ϊ�㣡";return -1;};
	cout<<"��������֮��Ϊ";
    cout<<r1.GetDen()<<"��֮"<<r1.GetNum()<<endl;break;
		case 5:
    if(r1.Equal(r2)==true)
		cout<<"�������"<<endl;
	else
		cout<<"���߲���"<<endl;
		case 6:
    r1.Copy(r2);
    cout<<"��һ������Ϊ"<<r1.GetDen()<<"��֮"<<r1.GetNum()<<endl;
    cout<<"�ڶ�������Ϊ"<<r2.GetDen()<<"��֮"<<r2.GetNum()<<endl;break;
	}
	return 0;
}






