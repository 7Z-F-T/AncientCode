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
	//c=5*a; c.show(); //����ʹ��ȫ�ֺ�������*�ſ�ʵ�ִ˾����
	complex d=c;
	d.show();
	complex e;
	e=c;
	e.show();

	return 0;
}