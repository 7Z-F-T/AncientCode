#include "stdio.h"
void main()
{
int a,b,i,result=0;
scanf("%d,%d",&a,&b);
for(i=1;i<=a;i++)
   if(a%i==0&&b%i==0) result=i;
printf("result=%d\n",result);
}
