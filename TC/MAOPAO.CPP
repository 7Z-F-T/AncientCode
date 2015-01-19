#include <graphics.h>
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <dos.h>

void swap56(char *p[]);
void swap45(char *p[]);
void swap34(char *p[]);
void swap23(char *p[]);
void swap12(char *p[]);

void main()
{
int driver=DETECT;
int mode;
initgraph(&driver,&mode,"e:\\tc3\\bgi");
cleardevice();

int i,j,k,x;
int a[6];
char *p[6];
printf("Input 6 numbers please\n");
for(i=0;i<=5;i++) scanf("%d",&a[i]);
printf("\n");

 for(k=0;k<=5;k++)
     sprintf(p[k],"%d",a[k]);


for(i=1;i<=5;i++)
for(j=0;j<=4;j++)
{
if(a[j]>a[j+1])
   {
    switch(j)
    {
      case 0:
      swap12(p);
      break;

      case 1:
      swap23(p);
      break;

       case 2:
      swap34(p);
      break;

       case 3:
      swap45(p);
      break;

       case 4:
      swap56(p);
      break;
      }
    x=a[j];
    a[j]=a[j+1];
    a[j+1]=x;
    for(k=0;k<=5;k++)
     sprintf(p[k],"%d",a[k]);
   }
}

for(i=0;i<=5;i++)
printf("%d\n",a[i]);


for(i=0;i<=5;i++)
     sprintf(p[i],"%d",a[i]);


getch();
}

void swap56(char *p[])
{
int i;
settextjustify(CENTER_TEXT,CENTER_TEXT);
settextstyle(0,0,2);
circle(120,240,20);
circle(200,240,20);
circle(280,240,20);
circle(360,240,20);
outtextxy(120,240,p[0]);
outtextxy(200,240,p[1]);
outtextxy(280,240,p[2]);
outtextxy(360,240,p[3]);
for(i=0;i<=79;i++)
  {
    setcolor(WHITE);
    circle(440+i,240,20);
    circle(520-i,240,20);
    outtextxy(440+i,240,p[4]);
    outtextxy(520-i,240,p[5]);
    delay(15);
    setcolor(BLACK);
    circle(440+i,240,20);
    circle(520-i,240,20);
    outtextxy(440+i,240,p[4]);
    outtextxy(520-i,240,p[5]);
  }
setcolor(WHITE);
circle(440,240,20);
circle(520,240,20);
outtextxy(440,240,p[5]);
outtextxy(520,240,p[4]);
}
void swap45(char *p[])
{
int i;
settextjustify(CENTER_TEXT,CENTER_TEXT);
settextstyle(0,0,2);
circle(120,240,20);
circle(200,240,20);
circle(280,240,20);
circle(520,240,20);
outtextxy(120,240,p[0]);
outtextxy(200,240,p[1]);
outtextxy(280,240,p[2]);
outtextxy(520,240,p[5]);
for(i=0;i<=79;i++)
  {
    setcolor(WHITE);
    circle(360+i,240,20);
    circle(440-i,240,20);
    outtextxy(360+i,240,p[3]);
    outtextxy(440-i,240,p[4]);
    delay(15);
    setcolor(BLACK);
    circle(360+i,240,20);
    circle(440-i,240,20);
    outtextxy(360+i,240,p[3]);
    outtextxy(440-i,240,p[4]);
  }
setcolor(WHITE);
circle(360,240,20);
circle(440,240,20);
outtextxy(360,240,p[4]);
outtextxy(440,240,p[3]);
}
void swap34(char *p[])
{
int i;
settextjustify(CENTER_TEXT,CENTER_TEXT);
settextstyle(0,0,2);
circle(120,240,20);
circle(200,240,20);
circle(440,240,20);
circle(520,240,20);
outtextxy(120,240,p[0]);
outtextxy(200,240,p[1]);
outtextxy(440,240,p[4]);
outtextxy(520,240,p[5]);
for(i=0;i<=79;i++)
  {
    setcolor(WHITE);
    circle(280+i,240,20);
    circle(360-i,240,20);
    outtextxy(280+i,240,p[2]);
    outtextxy(360-i,240,p[3]);
    delay(15);
    setcolor(BLACK);
    circle(280+i,240,20);
    circle(360-i,240,20);
    outtextxy(280+i,240,p[2]);
    outtextxy(360-i,240,p[3]);
  }
setcolor(WHITE);
circle(280,240,20);
circle(360,240,20);
outtextxy(280,240,p[3]);
outtextxy(360,240,p[2]);
}
void swap23(char *p[])
{
int i;
settextjustify(CENTER_TEXT,CENTER_TEXT);
settextstyle(0,0,2);
circle(120,240,20);
circle(360,240,20);
circle(440,240,20);
circle(520,240,20);
outtextxy(120,240,p[0]);
outtextxy(360,240,p[3]);
outtextxy(440,240,p[4]);
outtextxy(520,240,p[5]);
for(i=0;i<=79;i++)
  {
    setcolor(WHITE);
    circle(200+i,240,20);
    circle(280-i,240,20);
    outtextxy(200+i,240,p[1]);
    outtextxy(280-i,240,p[2]);
    delay(15);
    setcolor(BLACK);
    circle(200+i,240,20);
    circle(280-i,240,20);
    outtextxy(200+i,240,p[1]);
    outtextxy(280-i,240,p[2]);
  }
setcolor(WHITE);
circle(200,240,20);
circle(280,240,20);
outtextxy(200,240,p[2]);
outtextxy(280,240,p[1]);
}
void swap12(char *p[])
{
int i;
settextjustify(CENTER_TEXT,CENTER_TEXT);
settextstyle(0,0,2);
circle(280,240,20);
circle(360,240,20);
circle(440,240,20);
circle(520,240,20);
outtextxy(280,240,p[2]);
outtextxy(360,240,p[3]);
outtextxy(440,240,p[4]);
outtextxy(520,240,p[5]);
for(i=0;i<=79;i++)
  {
    setcolor(WHITE);
    circle(120+i,240,20);
    circle(200-i,240,20);
    outtextxy(120+i,240,p[0]);
    outtextxy(200-i,240,p[1]);
    delay(15);
    setcolor(BLACK);
    circle(120+i,240,20);
    circle(200-i,240,20);
    outtextxy(120+i,240,p[0]);
    outtextxy(200-i,240,p[1]);
  }
setcolor(WHITE);
circle(120,240,20);
circle(200,240,20);
outtextxy(120,240,p[1]);
outtextxy(200,240,p[0]);
}
