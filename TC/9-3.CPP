#include "stdio.h"
void main()
{
int a[80],*p,i,n,result,c,s;
printf("Please input the number of persons:\n");
scanf("%d",&n);
p=a;
for(i=0;i<n;i++)
*(p+i)=(i+1);
i=0,c=0,s=0;
while(s<n-1)
{
if(*(p+i)!=0) c++;
if(c==3)
  {c=0;*(p+i)=0;s++;}
i++;
if(i==n) i=0;
;
}
for(i=0;i<n;i++)
if(*(p+i)!=0) {result=*(p+i);break;}
printf("The man left is number %d\n",result);
}


