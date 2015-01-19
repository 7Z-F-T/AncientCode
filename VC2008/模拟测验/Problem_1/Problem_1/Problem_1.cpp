#include <iostream>
#include <iomanip>
using namespace std;

int main()
{
	float m;
	float w;
	cin>>m>>w;

	cout<<setiosflags(ios::fixed);
	cout<<setprecision(1);

	if(w<=20)
		cout<<m;
	else if(w>20&&w<=40)
		cout<<m+(w-20)/4+m/2;
	else if(w>40&&w<=60)
		cout<<m+(w-40)/3+5+m/2;
    else if(w>60&&w<=80)
		cout<<m+(w-20)/2+10+m/2;
	else if(w>80)
		cout<<"TOOHEAVY";

	return 0;
}