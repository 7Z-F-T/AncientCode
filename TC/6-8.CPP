#include "stdio.h"
#include "string.h"
void main()
{
int shushu(char a[],int *b);
char str[50];
int words=0,i,chang;
gets(str);
chang=strlen(str);
words=shushu(str,&chang);
printf("The number of words are:");
printf("%d\n",words+1);
}

int shushu(char a[],int *b)
{
int i,c=0;
for(i=0;i<=*b;i++)
   if(a[i]==32) c++;
return c;
}


