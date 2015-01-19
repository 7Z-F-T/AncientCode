#ifndef COMPLEX_H
#define COMPLEX_H
#include <iostream>
using namespace std;

class ComplexFormat
{
public:
	int x;
	ComplexFormat(int n):x(n){}
};

class Complex
{
	float real;
	float imag;
	static int format;

public:

	Complex():real(0),imag(0){}
	Complex(float re,float im=0):real(re),imag(im){}
	Complex(const Complex &x):real(x.real),imag(x.imag) {}//¿½±´¹¹Ôìº¯Êý
	Complex& operator=(const Complex &x)
	{
		real=x.real;
		imag=x.imag;
		return *this;
	}
	static void SetFormat(int n) {Complex::format=n;}
	friend class ComplexFormat;
	friend Complex operator*(const Complex&,const Complex&);
    friend ostream& operator<<(ostream &out,Complex &b);
	friend ostream& operator<<(ostream &out,ComplexFormat &a);


	void show() const
    {
	   cout<<real<<","<<imag<<endl;
    }
};

Complex operator*(const Complex &x1,const Complex &x2)
{
	return Complex(x1.real*x2.real-x1.imag*x2.imag,x2.real*x1.imag+x1.real*x2.imag);
}
ostream& operator<<(ostream &out,ComplexFormat &a)
{
	Complex::SetFormat(a.x);
	return out;
}
ostream& operator<<(ostream &out,Complex &b)
{
	switch(b.format)
	{
	case 1:out<<"Re:"<<b.real<<" Im:"<<b.imag;break;
	case 2:out<<"("<<b.real<<","<<b.imag<<")";break;
	case 3:out<<b.real<<"+j"<<b.imag;break;
	case 4:
		{
		    if(b.real==0&&b.imag==0) break;
			else if(b.real==0) {out<<"j"<<b.imag;break;}
			else if(b.imag==0) {out<<b.real;break;}
			else {out<<b.real<<"+j"<<b.imag;break;}
		}
	}
	return out;

	
}
int Complex::format=1;





#endif