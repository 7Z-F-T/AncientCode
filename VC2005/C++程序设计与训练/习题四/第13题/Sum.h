#ifndef SUM_H
#define SUM_H


template <class Type> 
Type Sum(int n, Type (*fun)(int))
{
	Type sum=0;

	for(int i=1;i<=n;i++)
		sum=sum+fun(i);
	return sum;
}
#endif