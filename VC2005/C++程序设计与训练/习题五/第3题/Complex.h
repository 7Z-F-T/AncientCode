#ifndef COMPLEX_H
#define COMPLEX_H
#include <iostream>
using namespace std;

//����Ϊ����ʵ�����ط�ʽ����ѡ��һ�֣�����һ�ֵ����ע�͵�

//1.�ó�Ա����ʵ������*//
class complex
{
	float real;
	float imag;
public:
	complex():real(0),imag(0) {}
	complex(float re,float im=0):real(re),imag(im) {}
	complex(const complex &x):real(x.real),imag(x.imag) {}//�������캯��
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

//2.��ȫ�ֺ�������Ԫ����*//
/*class complex
{
	float real;
	float imag;
public:
	complex():real(0),imag(0) {}
	complex(float re,float im=0):real(re),imag(im) {}
	complex(const complex &x):real(x.real),imag(x.imag) {}//�������캯��
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