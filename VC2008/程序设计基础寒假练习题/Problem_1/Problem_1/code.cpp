#include <iostream>
#include <cmath>
#include <iomanip>
using namespace std;

int main()
{
	double a,b,c;
	double pi=3.1415;
	cin>>a>>b>>c;
	double r=a*b*sqrt(1-(a*a+b*b-c*c)/(2*a*b)*(a*a+b*b-c*c)/(2*a*b))/(a+b+c);
	cout<<setiosflags(ios::fixed);
	cout<<setprecision(2);
	cout<<pi*r*r;

	return 0;

}