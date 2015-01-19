#include "stdio.h"
#include "math.h"
#include "conio.h"
#define pi 3.141593
void main()
{
clrscr();
int x,s=0;
float y;
for(x=-180;x<=180;x+=10)
   {
   s=s+1;
   if((s-1)%10==0)  getchar();
   y=sin((pi*x/180.0));
   printf("%d",x);
   printf("--------");
   printf("%f\n",y);}
}

