#include "stdio.h"
#include "string.h"
#include "conio.h"
void main()
{
clrscr();
char a[80];
int i=0,length=0,space=0,letter=0,number=0;
gets(a);
length=strlen(a);
for(i=0;i<length;i++)
   {
   if(a[i]==' ')
    space+=1;
   else if(((a[i]>='A'&&a[i]<='Z')||(a[i]>='a'&&a[i]<='z')))
    letter+=1;
   else if((a[i]>='0')&&(a[i]<='9'))
    number+=1;
   }
printf("Legnth=%d\n",length);
printf("Spaces=%d\n",space);
printf("Letters=%d\n",letter);
printf("Numbers=%d\n",number);
}
