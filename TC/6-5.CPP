#include "stdio.h"
#include "conio.h"
int i,s;
void main()
{
clrscr();
float doit(float x[]);
float result;
float a[500];
printf("DO NOT INPUT ZERO!!!\n");
   printf("You can input zero to end inputing!\n");
for(i=1;i<=500;i++)
   {
   printf("x");
   printf("%d",i);
   printf("=");
   scanf("%f",&a[i-1]);
   if(a[i-1]==0)
   {s=i-1;break;} /*s is the amount of data*/
   }
result=doit(a);
printf("Result is:");
printf("%f", result);
}

float doit(float x[])
{
float y=0,z;
for(i=0;i<=s-1;i++)
   y+=1/x[i];
z=s/y;
return z;
}
