 #include "stdio.h"
 #include "graphics.h"
 #include "conio.h"
 void main()
 {
 void drawline(float a1,float b1,float a2,float b2);
 int graphdriver=DETECT;
 int graphmode,i;
 initgraph(&graphdriver,&graphmode,"c:\\tc3\\bgi");
 float x[]={0.0,0.4,0.8,1.2,1.6,2.0,2.4,2.8,3.2,3.6};
 float y[]={0.67,0.68,0.71,1.86,1.86,1.04,1.30,1.81,1.46,1.86};
 for(i=0;i<9;i++)
 drawline(x[i],y[i],x[i+1],y[i+1]);
 getch();
 }
 void drawline(float a1,float b1,float a2,float b2)
 {
 line(100*a1,400-100*b1,100*a2,400-100*b2);
 }


