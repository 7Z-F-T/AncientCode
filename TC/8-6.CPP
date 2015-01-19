#include "stdio.h"
#include "string.h"
#include "conio.h"
#define N 20
void main()
{
clrscr();
int compare(char a[],char b[]);
char str1[N];
char str2[N];
int result;
printf("Please input string 1:\n");
gets(str1);
printf("Please input string 2:\n");
gets(str2);
result=compare(str1,str2);
printf("Result is %d\n",result);
getch();
}

int compare(char a[],char b[])
{
int i,r=0;
for(i=0;i<20;i++)
  if((a[i]=='\0')&&(b[i]=='\0')) break;
  else if(a[i]!=b[i])
    {r=a[i]-b[i];break;}
return(r);
}

