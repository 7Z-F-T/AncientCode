#include "Complex.h"




int main()
{
	complex a(1.0,2.0);
	complex b(5.0,6.0);
	complex c;
	c=a*b;
	c.show();
	c=a*3;
	c.show();
	//c=5*a; c.show(); //必须使用全局函数重载*才可实现此句操作
	complex d=c;
	d.show();
	complex e;
	e=c;
	e.show();

	return 0;
}