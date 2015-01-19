#include <iostream.h>;

int odd(int x)
{
	return (x*3+1);
}

int even(int x)
{
	return (x/2);
}

int main()
{
	int x;
	cout<<"请输入一个自然数"<<endl;
	cin>>x;
	cout<<"最后几次的每步运算结果为"<<endl;
	for(int i=1;i<=500;i++)
	{
		if((x%2)==0) x=even(x);
		else x=odd(x);
		if(i>=490) cout<<x<<endl;
	}
    return 0;
}