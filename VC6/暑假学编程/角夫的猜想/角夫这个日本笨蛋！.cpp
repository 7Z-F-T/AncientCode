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
	cout<<"������һ����Ȼ��"<<endl;
	cin>>x;
	cout<<"��󼸴ε�ÿ��������Ϊ"<<endl;
	for(int i=1;i<=500;i++)
	{
		if((x%2)==0) x=even(x);
		else x=odd(x);
		if(i>=490) cout<<x<<endl;
	}
    return 0;
}