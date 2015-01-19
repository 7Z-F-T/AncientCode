#include "Complex.h"





int main()
{
	
	Complex a(1.0,2.0);
	Complex b(3.0,4.0);
	Complex c(5.0,0.0);
	Complex d(7.0,0.0);

	cout<<ComplexFormat(1)<<a<<endl<<ComplexFormat(2)<<b<<endl;
	cout<<ComplexFormat(3)<<c<<endl<<ComplexFormat(4)<<d<<endl;

	return 0;
}