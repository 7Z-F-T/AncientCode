#include "stdio.h"
#include "stdlib.h"
#include "conio.h"
void main()
{
clrscr();
FILE *fp1,*fp2;
char f1[]="d:\\file1.dat",f2[]="d:\\file2.dat";
char ch;
if((fp1=fopen(f1,"w"))==NULL)
printf("Can't create file!");
while(ch!='\n') {ch=getchar();fputc(ch,fp1);}
printf("File has been created!\n");
fclose(fp1);

if((fp1=fopen(f1,"r"))==NULL)
printf("Can't open file!");
if((fp2=fopen(f2,"w"))==NULL)
printf("Can't create file!");
while(!feof(fp1)) fputc(fgetc(fp1),fp2);
printf("File has been copied!\n");
fclose(fp1);
fclose(fp2);
}



