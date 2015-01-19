#include "Expr.h"


int main()
{
	Expr t = Expr('*', Expr('-', 5), Expr('+', 3, 4));
	cout<<t<<"="<<t.eval()<<endl;
	t=Expr('*',t,t);
	cout<<t<<"="<<t.eval()<<endl;


	return 0;
}