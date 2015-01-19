#ifndef COMPLEX_H
#define COMPLEX_H
#include <iostream>
using namespace std;

//以下为两种实现重载方式，可选择一种，把另一种的语句注释掉

//1.用成员函数实现重载*//
class complex
{
	float real;
	float imag;
public:
	complex():real(0),imag(0) {}
	complex(float re,float im=0):real(re),imag(im) {}
	complex(const complex &x):real(x.real),imag(x.imag) {}//拷贝构造函数
	complex& operator=(const complex &x)
	{
		real=x.real;
		imag=x.imag;
		return *this;
	}
	complex operator*(const complex &x)
	{
		return complex(real*x.real-imag*x.imag,x.real*imag+real*x.imag);
	}
	void show() const
    {
	   cout<<real<<","<<imag<<endl;
    }
};
/////////////////////////

//2.用全局函数加友元重载*//
/*class complex
{
	float real;
	float imag;
public:
	complex():real(0),imag(0) {}
	complex(float re,float im=0):real(re),imag(im) {}
	complex(const complex &x):real(x.real),imag(x.imag) {}//拷贝构造函数
	complex& operator=(const complex &x)
	{
		real=x.real;
		imag=x.imag;
		return *this;
	}

	friend complex operator*(const complex&,const complex&);
	void show() const
    {
	   cout<<real<<","<<imag<<endl;
    }
};

complex operator*(const complex &x1,const complex &x2)
{
	return complex(x1.real*x2.real-x1.imag*x2.imag,x2.real*x1.imag+x1.real*x2.imag);
}
*/
///////////////////////
#endif