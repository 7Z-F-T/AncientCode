#include "stdio.h"
void main()
{
int n=0,i=0;
float sum=0;
scanf("%d",&n);
for(i=1;i<=n;i++)
   sum=sum+(i*i/(i+1.0));
printf("Sum is %f\n",sum);
}

