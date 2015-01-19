#include <windows.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <math.h>





//全局变量

HDC hdc,hdcmem;
HWND hwnd;
HBITMAP hbmNum1,hbmNum2,hbmNum3,hbmNum4,hbmNum5,hbmNum6,hbmNum7,hbmNum8,hbmNum9,hbmNum0,
         hbmNum00,hbmNumpoint;
HBITMAP hbmjia,hbmjian,hbmcheng,hbmchu;
HBITMAP hbmdengyu,hbmAC,hbmOFF,hbm2nd;
HBITMAP hbmSin,hbmCos,hbmTan,hbmln,hbmLog,hbmxfang;
HBITMAP hbmDEG,hbmRAD;
HBITMAP hbmMjia,hbmMjian,hbmMRC;
HBRUSH hbrush;
HPEN hpen;
HFONT hfont,hfont2,hfont3;
char Dis[10]={'a','a','a','a','a','a','a','a','a','a'};//显示到屏幕上的字符数组
char DisOpr[5];//在屏幕上显示的运算符
char *pchar;
char *stop="a";
int decimal,sign;
double xx;
double Num[10]={0,0,0,0,0,0,0,0,0,0};//存储计算数字用数组
int NumPos=0;//Num数组操作位置
int    Opr[9]={0,0,0,0,0,0,0,0,0};//存储操作符用数组
int OprPos=0;//Opr操作位置
int nDisLength=0;//Dis显示长度兼操作位置
int nDisOprLength=0;//操作符号显示长度
long startpostion=300;//显示初始位置，随后摁一个数字变化一次


double Result;
BOOL useDis=TRUE;
BOOL usepchar=FALSE;
BOOL DisplayOpr=FALSE;
BOOL Finished=FALSE;
BOOL FinishedOneOperation=FALSE;
BOOL useDEG=TRUE;
BOOL use2nd=FALSE;
double M=0;//寄存器变量



LRESULT CALLBACK WndProc(HWND,UINT,WPARAM,LPARAM); 	
double Operate(double Num[],int Opr[]);
void ClearDis();
void ClearNum();
void ClearOpr();
char  *Correctpchar(char *pchar);









int WINAPI WinMain(HINSTANCE	hInstance,HINSTANCE hPrevInst,LPSTR lpszCmdLine,int	nCmdShow)
{
	HWND hwnd ;
	MSG Msg ;
	WNDCLASS wndclass ;
	char lpszClassName[] = "main";		
	char lpszTitle[]= "科学计算器——期中大作业 结52 侯杰 2005010130";		

	
	wndclass.style = CS_HREDRAW|CS_VREDRAW|CS_DBLCLKS;					
	wndclass.lpfnWndProc = WndProc ;	
	wndclass.cbClsExtra	= 0 ;			
	wndclass.cbWndExtra	= 0 ;			
	wndclass.hInstance = hInstance ;	
	wndclass.hIcon = LoadIcon( NULL, IDI_APPLICATION) ;
	wndclass.hCursor = LoadCursor( NULL,IDC_ARROW);
	wndclass.hbrBackground =(HBRUSH) GetStockObject(WHITE_BRUSH); 
	wndclass.lpszMenuName ="NULL" ;			
	wndclass.lpszClassName = lpszClassName;	



	if( !RegisterClass( &wndclass))		
	{
		MessageBeep(0) ;
		return FALSE ;
	}

	hwnd=CreateWindow(
		lpszClassName,	
		lpszTitle,			
		WS_DLGFRAME,	
		CW_USEDEFAULT,
		CW_USEDEFAULT,			
		650,
		590,			
		NULL,					
		NULL,					
		hInstance,				
		NULL) ;					
 
	//位图句柄
	hbmNum1=LoadBitmap(hInstance,"Num1");
	hbmNum2=LoadBitmap(hInstance,"Num2");
	hbmNum3=LoadBitmap(hInstance,"Num3");
	hbmNum4=LoadBitmap(hInstance,"Num4");
	hbmNum5=LoadBitmap(hInstance,"Num5");
	hbmNum6=LoadBitmap(hInstance,"Num6");
	hbmNum7=LoadBitmap(hInstance,"Num7");
	hbmNum8=LoadBitmap(hInstance,"Num8");
	hbmNum9=LoadBitmap(hInstance,"Num9");
	hbmNum0=LoadBitmap(hInstance,"Num0");
	hbmNum00=LoadBitmap(hInstance,"Num00");
	hbmNumpoint=LoadBitmap(hInstance,"Numpoint");
	hbmjia=LoadBitmap(hInstance,"jia");
	hbmjian=LoadBitmap(hInstance,"jian");
	hbmcheng=LoadBitmap(hInstance,"cheng");
	hbmchu=LoadBitmap(hInstance,"chu");
	hbmdengyu=LoadBitmap(hInstance,"dengyu");
	hbmAC=LoadBitmap(hInstance,"AC");
	hbmOFF=LoadBitmap(hInstance,"OFF");
	hbm2nd=LoadBitmap(hInstance,"2nd");
	hbmSin=LoadBitmap(hInstance,"Sin");
	hbmCos=LoadBitmap(hInstance,"Cos");
	hbmTan=LoadBitmap(hInstance,"Tan");
	hbmln=LoadBitmap(hInstance,"ln");
	hbmLog=LoadBitmap(hInstance,"Log");
	hbmxfang=LoadBitmap(hInstance,"xfang");
	
	hbmDEG=LoadBitmap(hInstance,"DEG");
	hbmRAD=LoadBitmap(hInstance,"RAD");

	hbmMjia=LoadBitmap(hInstance,"Mjia");
	hbmMjian=LoadBitmap(hInstance,"Mjian");
	hbmMRC=LoadBitmap(hInstance,"MRC");
	
	
	
	
	ShowWindow( hwnd, nCmdShow); 			
	UpdateWindow(hwnd); 					
	while( GetMessage(&Msg, NULL, 0, 0))	
	{
		TranslateMessage( &Msg) ;
		DispatchMessage( &Msg) ;
	}
	return Msg.wParam;					
}

//窗口函数
LRESULT CALLBACK WndProc(HWND hwnd,UINT message,WPARAM  wParam,LPARAM lParam)
{
	PAINTSTRUCT ps;
	WORD x,y;
	TEXTMETRIC tm;
	int i;
	static long CharWidth,CharHeight;//待用..
	char *pDEG="DEG";
	char *pRAD="RAD";
	char *p2nd="2ndf";
	char *pM="M";
	char *parcSin="arcSin";
	char *parcCos="arcCos";
	char *parcTan="arcTan";
	char *pe="e";
	char *p10="10";
	char *px="x";
	char *pSqrt="Sqrt";
	char *pTitle="科学计算器";
	x=LOWORD(lParam);
	y=HIWORD(lParam);
	
	
	
	
	switch(message)
	{
    case WM_CREATE:
		hdc=GetDC(hwnd);
		
		hdcmem=CreateCompatibleDC(hdc);

		GetTextMetrics(hdc,&tm);
		CharWidth=tm.tmAveCharWidth ;
		CharHeight=tm.tmHeight ;

		hfont=CreateFont(40,0,0,0,FW_HEAVY,0,0,0,GB2312_CHARSET,OUT_DEFAULT_PRECIS,OUT_DEFAULT_PRECIS,
			DEFAULT_QUALITY,DEFAULT_PITCH|FF_DONTCARE,"Times New Roman");
		hfont2=CreateFont(13,0,0,0,FW_NORMAL,1,0,0,GB2312_CHARSET,OUT_DEFAULT_PRECIS,OUT_DEFAULT_PRECIS,
			DEFAULT_QUALITY,DEFAULT_PITCH|FF_DONTCARE,"Italic");
		hfont3=CreateFont(40,0,0,0,FW_HEAVY,1,0,0,GB2312_CHARSET,OUT_DEFAULT_PRECIS,OUT_DEFAULT_PRECIS,
			DEFAULT_QUALITY,DEFAULT_PITCH|FF_DONTCARE,"华文行楷");
		
		
		
		
		
		ReleaseDC(hwnd,hdc);
		break;
		
	

	case WM_PAINT:
		hdc=GetDC(hwnd);

	    SetTextColor(hdc,RGB(0,0,0));
		//显示各种状态显示符
		if(useDEG==TRUE)
			TextOut(hdc,315,85,pDEG,3);
		else
			TextOut(hdc,315,85,pRAD,3);
		if(use2nd==TRUE)
			TextOut(hdc,315,110,p2nd,3);
		if(M!=0)
			TextOut(hdc,325,135,pM,1);
		
		
		SelectObject(hdc,hfont);
	
		
     	if(useDis==TRUE)
			TextOut(hdc,startpostion,95,Dis,nDisLength);
		if(usepchar==TRUE)
			TextOut(hdc,90,95,pchar,10);
		if(DisplayOpr==TRUE)
			TextOut(hdc,60,95,DisOpr,nDisOprLength);
        //显示第二功能标签
		SelectObject(hdc,hfont2);
		SetTextColor(hdc,RGB(0,0,255));
		TextOut(hdc,390,175,parcSin,6);
		TextOut(hdc,390,245,parcCos,6);
		TextOut(hdc,390,315,parcTan,6);
		TextOut(hdc,480,175,pe,1);
		TextOut(hdc,480,245,p10,2);
		TextOut(hdc,488,170,px,1);
		TextOut(hdc,496,240,px,1);
		TextOut(hdc,475,315,pSqrt,4);

		SelectObject(hdc,hfont3);
		TextOut(hdc,400,20,pTitle,10);
		
		
		
		
	
		


		
		hbrush=(HBRUSH)GetStockObject(NULL_BRUSH);
		hpen=(HPEN)GetStockObject(BLACK_PEN);
		SelectObject(hdc,hbrush);
		SelectObject(hdc,hpen);
		//画输出小窗口
		Rectangle(hdc,50,80,350,160);
		
		//画按钮
		SelectObject(hdcmem,hbmNum7);
		BitBlt(hdc,50,190,60 ,50 ,hdcmem,0,0,SRCCOPY);
		SelectObject(hdcmem,hbmNum8);
		BitBlt(hdc,130,190,60 ,50 ,hdcmem,0,0,SRCCOPY);
		SelectObject(hdcmem,hbmNum9);
		BitBlt(hdc,210,190,60 ,50 ,hdcmem,0,0,SRCCOPY);
		SelectObject(hdcmem,hbmNum4);
		BitBlt(hdc,50,260,60 ,50 ,hdcmem,0,0,SRCCOPY);
		SelectObject(hdcmem,hbmNum5);
		BitBlt(hdc,130,260,60 ,50 ,hdcmem,0,0,SRCCOPY);
		SelectObject(hdcmem,hbmNum6);
		BitBlt(hdc,210,260,60 ,50 ,hdcmem,0,0,SRCCOPY);
		SelectObject(hdcmem,hbmNum1);
		BitBlt(hdc,50,330,60 ,50 ,hdcmem,0,0,SRCCOPY);
		SelectObject(hdcmem,hbmNum2);
		BitBlt(hdc,130,330,60,50 ,hdcmem,0,0,SRCCOPY);
		SelectObject(hdcmem,hbmNum3);
		BitBlt(hdc,210,330,60 ,50 ,hdcmem,0,0,SRCCOPY);
		SelectObject(hdcmem,hbmNum0);
		BitBlt(hdc,50,400,60 ,50 ,hdcmem,0,0,SRCCOPY);
		SelectObject(hdcmem,hbmNum00);
		BitBlt(hdc,130,400,60 ,50 ,hdcmem,0,0,SRCCOPY);
		SelectObject(hdcmem,hbmNumpoint);
		BitBlt(hdc,210,400,60 ,50 ,hdcmem,0,0,SRCCOPY);
		
		
		SelectObject(hdcmem,hbmjia);
		BitBlt(hdc,290,190,60 ,50 ,hdcmem,0,0,SRCCOPY);
		SelectObject(hdcmem,hbmjian);
		BitBlt(hdc,290,260,60 ,50 ,hdcmem,0,0,SRCCOPY);
		SelectObject(hdcmem,hbmcheng);
		BitBlt(hdc,290,330,60 ,50 ,hdcmem,0,0,SRCCOPY);
		SelectObject(hdcmem,hbmchu);
		BitBlt(hdc,290,400,60 ,50 ,hdcmem,0,0,SRCCOPY);

		SelectObject(hdcmem,hbmdengyu);
		BitBlt(hdc,50,470,300,50 ,hdcmem,0,0,SRCCOPY);
		SelectObject(hdcmem,hbmAC);
		BitBlt(hdc,380,470,60,50 ,hdcmem,0,0,SRCCOPY);
		SelectObject(hdcmem,hbmOFF);
		BitBlt(hdc,470,470,60,50 ,hdcmem,0,0,SRCCOPY);
		SelectObject(hdcmem,hbmSin);
		BitBlt(hdc,380,190,60,50 ,hdcmem,0,0,SRCCOPY);
		SelectObject(hdcmem,hbmCos);
		BitBlt(hdc,380,260,60,50 ,hdcmem,0,0,SRCCOPY);
		SelectObject(hdcmem,hbmTan);
		BitBlt(hdc,380,330,60,50 ,hdcmem,0,0,SRCCOPY);
		SelectObject(hdcmem,hbmln);
		BitBlt(hdc,460,190,60,50 ,hdcmem,0,0,SRCCOPY);
		SelectObject(hdcmem,hbmLog);
		BitBlt(hdc,460,260,60,50 ,hdcmem,0,0,SRCCOPY);
		SelectObject(hdcmem,hbmxfang);
		BitBlt(hdc,460,330,60,50 ,hdcmem,0,0,SRCCOPY);
		

		SelectObject(hdcmem,hbm2nd);
		BitBlt(hdc,460,110,60,50 ,hdcmem,0,0,SRCCOPY);

		SelectObject(hdcmem,hbmMjia);
		BitBlt(hdc,540,190,60,50 ,hdcmem,0,0,SRCCOPY);
		SelectObject(hdcmem,hbmMjian);
		BitBlt(hdc,540,260,60,50 ,hdcmem,0,0,SRCCOPY);
		SelectObject(hdcmem,hbmMRC);
		BitBlt(hdc,540,330,60,50 ,hdcmem,0,0,SRCCOPY);
		
		
		
		
		
		
		
		if(useDEG==TRUE)
		{
		SelectObject(hdcmem,hbmRAD);
		BitBlt(hdc,380,110,60,50 ,hdcmem,0,0,SRCCOPY);
		}
        else		
		{
 		SelectObject(hdcmem,hbmDEG);
		BitBlt(hdc,380,110,60,50 ,hdcmem,0,0,SRCCOPY);
		}
		
		
		ReleaseDC(hwnd,hdc);
		
		
		
		
	case WM_LBUTTONDOWN:
		//判断按钮操作

		//Num7
		if((x>=50&&x<=110)&&(y>=190&&y<=240))
		{
			hdc=BeginPaint(hwnd,&ps);
			SelectObject(hdcmem,hbmNum7);
			BitBlt(hdc,52,192,60 ,50 ,hdcmem,0,0,SRCCOPY);
		
		

			if(Finished==TRUE)
			{
			Finished=FALSE;
			
			ClearDis();
			nDisLength=0;
			
			startpostion=300;
			useDis=TRUE;
			}

			if(usepchar==TRUE)
			{
				usepchar=FALSE;
				useDis=TRUE;
			}

			
			

            Dis[nDisLength]='7';//向数组赋值
			if(nDisLength<=9)
				nDisLength++;     //修改当前数组添加位置，和起始显示坐标
			if(startpostion>=111)
			startpostion-=21;
			DisplayOpr=FALSE;


			
			
			
			
			EndPaint(hwnd,&ps);			
			
		}
		//Num4
		else if((x>=50&&x<=110)&&(y>=260&&y<=330))
		{	hdc=BeginPaint(hwnd,&ps);
		    SelectObject(hdcmem,hbmNum4);
		    BitBlt(hdc,52,262,60 ,50 ,hdcmem,0,0,SRCCOPY);
			if(Finished==TRUE)
			{
				Finished=FALSE;
				
				ClearDis();
				nDisLength=0;
				
				startpostion=300;
				useDis=TRUE;
			}
			

			
            Dis[nDisLength]='4';
			if(nDisLength<=9)
				nDisLength++;
			if(startpostion>=111)
			startpostion-=21;

			if(usepchar==TRUE)
			{
				usepchar=FALSE;
				useDis=TRUE;
			}
			
			DisplayOpr=FALSE;
			
			EndPaint(hwnd,&ps);					
		}
		//Num1
		else if((x>=50&&x<=110)&&(y>=330&&y<=380))
		{
			hdc=BeginPaint(hwnd,&ps);
			SelectObject(hdcmem,hbmNum1);
			BitBlt(hdc,52,332,60 ,50 ,hdcmem,0,0,SRCCOPY);
			FinishedOneOperation=FALSE;
			
			if(Finished==TRUE)
			{
				Finished=FALSE;
				
				ClearDis();
				nDisLength=0;
				
				startpostion=300;
				useDis=TRUE;
			}
			
			
            Dis[nDisLength]='1';
			if(nDisLength<=9)
				nDisLength++;
			if(startpostion>=111)
			startpostion-=21;

			if(usepchar==TRUE)
			{
				usepchar=FALSE;
				useDis=TRUE;
			}
			
			DisplayOpr=FALSE;
			
			EndPaint(hwnd,&ps);					
			
		}
		//Num0
		else if((x>=50&&x<=110)&&(y>=400&&y<=450))
		{
			hdc=BeginPaint(hwnd,&ps);
			SelectObject(hdcmem,hbmNum0);
		BitBlt(hdc,52,402,60 ,50 ,hdcmem,0,0,SRCCOPY);
		if(Finished==TRUE)
		{
			Finished=FALSE;
			
			ClearDis();
			nDisLength=0;
			
			startpostion=300;
			useDis=TRUE;
		}
		
		
		Dis[nDisLength]='0';
			if(nDisLength<=9)
				nDisLength++;
			if(startpostion>=111)
			startpostion-=21;

			if(usepchar==TRUE)
			{
				usepchar=FALSE;
				useDis=TRUE;
			}
			DisplayOpr=FALSE;
			
		EndPaint(hwnd,&ps);					
		
		}
		//Num8
		else if((x>=130&&x<=190)&&(y>=190&&y<=240))
		{
			hdc=BeginPaint(hwnd,&ps);
			SelectObject(hdcmem,hbmNum8);
			BitBlt(hdc,132,192,60 ,50 ,hdcmem,0,0,SRCCOPY);
			if(Finished==TRUE)
			{
				Finished=FALSE;
				
				ClearDis();
				nDisLength=0;
				
				startpostion=300;
				useDis=TRUE;
			}
			
			
            Dis[nDisLength]='8';
			if(nDisLength<=9)
				nDisLength++;
			if(startpostion>=111)
			startpostion-=21;
			
			if(usepchar==TRUE)
			{
				usepchar=FALSE;
				useDis=TRUE;
			}
			DisplayOpr=FALSE;
			
			EndPaint(hwnd,&ps);					
		}
		//Num5
		else if((x>=130&&x<=190)&&(y>=260&&y<=330))
		{	hdc=BeginPaint(hwnd,&ps);
		SelectObject(hdcmem,hbmNum5);
		BitBlt(hdc,132,262,60 ,50 ,hdcmem,0,0,SRCCOPY);
		if(Finished==TRUE)
		{
			Finished=FALSE;
			
			ClearDis();
			nDisLength=0;
			
			startpostion=300;
			useDis=TRUE;
		}
		
		
		Dis[nDisLength]='5';
			if(nDisLength<=9)
				nDisLength++;
			if(startpostion>=111)
			startpostion-=21;
		
			if(usepchar==TRUE)
			{
				usepchar=FALSE;
				useDis=TRUE;
			}
			DisplayOpr=FALSE;
			
		EndPaint(hwnd,&ps);					
		}
		//Num2
		else if((x>=130&&x<=190)&&(y>=330&&y<=380))
		{
			hdc=BeginPaint(hwnd,&ps);
			SelectObject(hdcmem,hbmNum2);
			BitBlt(hdc,132,332,60 ,50 ,hdcmem,0,0,SRCCOPY);
			if(Finished==TRUE)
			{
				Finished=FALSE;
				
				ClearDis();
				nDisLength=0;
				
				startpostion=300;
				useDis=TRUE;
			}
			
			
            Dis[nDisLength]='2';
			if(nDisLength<=9)
				nDisLength++;
			if(startpostion>=111)
			startpostion-=21;
			
			if(usepchar==TRUE)
			{
				usepchar=FALSE;
				useDis=TRUE;
			}
			DisplayOpr=FALSE;
			
			EndPaint(hwnd,&ps);					
			
		}
		//Num00
		else if((x>=130&&x<=190)&&(y>=400&&y<=450))
		{
			hdc=BeginPaint(hwnd,&ps);
			SelectObject(hdcmem,hbmNum00);
			BitBlt(hdc,132,402,60 ,50 ,hdcmem,0,0,SRCCOPY);
			if(Finished==TRUE)
			{
				Finished=FALSE;
				
				ClearDis();
				nDisLength=0;
				
				startpostion=300;
				useDis=TRUE;
			}
			
			
            Dis[nDisLength]='0';
			if(nDisLength<=9)
			{	nDisLength++;
			Dis[nDisLength]='0';
			if(startpostion>=111)
				startpostion-=21;}
			if(nDisLength<=9)
				nDisLength++;
			if(startpostion>=111)
			startpostion-=21;
			
			if(usepchar==TRUE)
			{
				usepchar=FALSE;
				useDis=TRUE;
			}
			DisplayOpr=FALSE;
			
			EndPaint(hwnd,&ps);					
			
		}
		//Num9
		else if((x>=210&&x<=270)&&(y>=190&&y<=240))
		{
			hdc=BeginPaint(hwnd,&ps);
			SelectObject(hdcmem,hbmNum9);
			BitBlt(hdc,212,192,60 ,50 ,hdcmem,0,0,SRCCOPY);
			if(Finished==TRUE)
			{
				Finished=FALSE;
				
				ClearDis();
				nDisLength=0;
				
				startpostion=300;
				useDis=TRUE;
			}
			
			
            Dis[nDisLength]='9';
			if(nDisLength<=9)
				nDisLength++;
			if(startpostion>=111)
			startpostion-=21;
			
			if(usepchar==TRUE)
			{
				usepchar=FALSE;
				useDis=TRUE;
			}
			DisplayOpr=FALSE;
			
			EndPaint(hwnd,&ps);					
			
		}
		//Num6
		else if((x>=210&&x<=270)&&(y>=260&&y<=330))
		{	hdc=BeginPaint(hwnd,&ps);
		SelectObject(hdcmem,hbmNum6);
		BitBlt(hdc,212,262,60 ,50 ,hdcmem,0,0,SRCCOPY);
		if(Finished==TRUE)
		{
			Finished=FALSE;
			
			ClearDis();
			nDisLength=0;
			
			startpostion=300;
			useDis=TRUE;
		}
		
		
		Dis[nDisLength]='6';
			if(nDisLength<=9)
				nDisLength++;
			if(startpostion>=111)
			startpostion-=21;
		
			if(usepchar==TRUE)
			{
				usepchar=FALSE;
				useDis=TRUE;
			}
			DisplayOpr=FALSE;
			
		EndPaint(hwnd,&ps);					
		}
		//Num3
		else if((x>=210&&x<=270)&&(y>=330&&y<=380))
		{
			hdc=BeginPaint(hwnd,&ps);
			SelectObject(hdcmem,hbmNum3);
			BitBlt(hdc,212,332,60 ,50 ,hdcmem,0,0,SRCCOPY);
			if(Finished==TRUE)
			{
				Finished=FALSE;
				
				ClearDis();
				nDisLength=0;
				
				startpostion=300;
				useDis=TRUE;
			}
			
			
            Dis[nDisLength]='3';
			if(nDisLength<=9)
				nDisLength++;
			if(startpostion>=111)
			startpostion-=21;
			
			if(usepchar==TRUE)
			{
				usepchar=FALSE;
				useDis=TRUE;
			}
			DisplayOpr=FALSE;
			
			EndPaint(hwnd,&ps);					
			
		}
		//小数点
		else if((x>=210&&x<=270)&&(y>=400&&y<=450))
		{
			hdc=BeginPaint(hwnd,&ps);
			SelectObject(hdcmem,hbmNumpoint);
			BitBlt(hdc,212,402,60 ,50 ,hdcmem,0,0,SRCCOPY);
			if(Finished==TRUE)
			{
				Finished=FALSE;
				
				ClearDis();
				nDisLength=0;
				
				startpostion=300;
				useDis=TRUE;
			}
			
			
            Dis[nDisLength]='.';
			if(nDisLength<=9)
				nDisLength++;
			if(startpostion>=111)
			startpostion-=21;
			
			if(usepchar==TRUE)
			{
				usepchar=FALSE;
				useDis=TRUE;
			}
			DisplayOpr=FALSE;
			
			EndPaint(hwnd,&ps);	
		}
		
		// +
			else if((x>=290&&x<=350)&&(y>=190&&y<=240))
		{
			hdc=BeginPaint(hwnd,&ps);
			SelectObject(hdcmem,hbmjia);
			BitBlt(hdc,292,192,60 ,50 ,hdcmem,0,0,SRCCOPY);
			if(FinishedOneOperation==TRUE)
			{
				for(i=1;i<=9;i++)
					Num[i]=0;
				NumPos=0;
				for(i=1;i<=8;i++)
					Opr[i]=0;
				OprPos=0;
				FinishedOneOperation=FALSE;
				
			}

			xx=strtod(Dis,&stop);
			Num[NumPos]=xx;
			if(NumPos<=8)
				NumPos++;
			Opr[OprPos]=1;
			if(OprPos<=7)
				OprPos++;
            Finished=TRUE;
			useDis=FALSE;
			usepchar=FALSE;

			DisplayOpr=TRUE;
			DisOpr[0]='+';
			nDisOprLength=1;

		
			
			    
			

			EndPaint(hwnd,&ps);	
		}
		// - 
			else if((x>=290&&x<=350)&&(y>=260&&y<=330))
		{
		hdc=BeginPaint(hwnd,&ps);
		SelectObject(hdcmem,hbmjian);
		BitBlt(hdc,292,262,60 ,50 ,hdcmem,0,0,SRCCOPY);
		if(FinishedOneOperation==TRUE)
		{
			for(i=1;i<=9;i++)
				Num[i]=0;
			NumPos=0;
			for(i=1;i<=8;i++)
				Opr[i]=0;
			OprPos=0;
			FinishedOneOperation=FALSE;
			
			
		}
		

		xx=strtod(Dis,&stop);
		Num[NumPos]=xx;
		if(NumPos<=8)
			NumPos++;
		Opr[OprPos]=2;
		if(OprPos<=7)
			OprPos++;
		Finished=TRUE;
		useDis=FALSE;
		usepchar=FALSE;
		
		DisplayOpr=TRUE;
		DisOpr[0]='-';
		nDisOprLength=1;
		
		
		EndPaint(hwnd,&ps);					
		}
		// *
			else if((x>=290&&x<=350)&&(y>=330&&y<=380))
		{
			hdc=BeginPaint(hwnd,&ps);
			SelectObject(hdcmem,hbmcheng);
			BitBlt(hdc,292,332,60 ,50 ,hdcmem,0,0,SRCCOPY);
			if(FinishedOneOperation==TRUE)
			{
				for(i=1;i<=9;i++)
					Num[i]=0;
				NumPos=0;
				for(i=1;i<=8;i++)
					Opr[i]=0;
				OprPos=0;
				FinishedOneOperation=FALSE;
				
			}
			

			xx=strtod(Dis,&stop);
			Num[NumPos]=xx;
			if(NumPos<=8)
				NumPos++;
			Opr[OprPos]=3;
			if(OprPos<=7)
				OprPos++;
			useDis=FALSE;
			usepchar=FALSE;
			
            Finished=TRUE;
			DisplayOpr=TRUE;
			DisOpr[0]='*';
			nDisOprLength=1;
			
			
			EndPaint(hwnd,&ps);					
			
		}
		// /
		else if((x>=290&&x<=350)&&(y>=400&&y<=450))
		{
			hdc=BeginPaint(hwnd,&ps);
			SelectObject(hdcmem,hbmchu);
			BitBlt(hdc,292,402,60 ,50 ,hdcmem,0,0,SRCCOPY);
			if(FinishedOneOperation==TRUE)
			{
				for(i=1;i<=9;i++)
					Num[i]=0;
				NumPos=0;
				for(i=1;i<=8;i++)
					Opr[i]=0;
				OprPos=0;
				FinishedOneOperation=FALSE;
				
				
			}
			

			xx=strtod(Dis,&stop);
			Num[NumPos]=xx;
			if(NumPos<=8)
				NumPos++;
			Opr[OprPos]=4;
			if(OprPos<=7)
				OprPos++;
			useDis=FALSE;
			usepchar=FALSE;
			
            Finished=TRUE;
			DisplayOpr=TRUE;
			DisOpr[0]='/';
			nDisOprLength=1;
			
			
			
			EndPaint(hwnd,&ps);	
		}
		
		//= 
		else if((x>=50&&x<=350)&&(y>=470&&y<=520))
		{
			hdc=BeginPaint(hwnd,&ps);
			SelectObject(hdcmem,hbmdengyu);
			BitBlt(hdc,52,472,300 ,50 ,hdcmem,0,0,SRCCOPY);

			xx=strtod(Dis,&stop);
			Num[NumPos]=xx;

			ClearDis();
			
			
			
			Result=Operate(Num,Opr);
			pchar=_ecvt(Result,10,&decimal,&sign);

			pchar=Correctpchar(pchar);


			for(i=0;i<=9;i++)
			{
				Dis[i]=*(pchar+i);
			}

			


			
			
			
			
			useDis=FALSE;
			usepchar=TRUE;

			DisplayOpr=TRUE;
			DisOpr[0]='=';
			Finished=TRUE;

			FinishedOneOperation=TRUE;





			
		

			
				
			EndPaint(hwnd,&ps);
		}
			
			//AC
		else if((x>=380&&x<=440)&&(y>=470&&y<=520))
		{
				hdc=BeginPaint(hwnd,&ps);
				SelectObject(hdcmem,hbmAC);
				BitBlt(hdc,382,472,60 ,50 ,hdcmem,0,0,SRCCOPY);

			ClearNum();
			NumPos=0;
			ClearOpr();
			 OprPos=0;
			 ClearDis();
			 nDisLength=0;
			 startpostion=300;
			 nDisOprLength=0;

			  useDis=TRUE;
			  usepchar=FALSE;
			  DisplayOpr=FALSE;
			  Finished=FALSE;
			  EndPaint(hwnd,&ps);
		}
			//OFF
			else if((x>=470&&x<=530)&&(y>=470&&y<=520))
			{
				hdc=BeginPaint(hwnd,&ps);
				SelectObject(hdcmem,hbmOFF);
				BitBlt(hdc,472,472,60 ,50 ,hdcmem,0,0,SRCCOPY);

				PostQuitMessage(0);

				EndPaint(hwnd,&ps);
			}
		//DEG
			else if((x>=380&&x<=440)&&(y>=110&&y<=160))
			{
				hdc=BeginPaint(hwnd,&ps);
				
				switch(useDEG)
				{
				
				case TRUE:
				
				SelectObject(hdcmem,hbmRAD);
				BitBlt(hdc,382,112,60 ,50 ,hdcmem,0,0,SRCCOPY);
				
				useDEG=FALSE;
				EndPaint(hwnd,&ps);
				break;

				case FALSE:
				
				SelectObject(hdcmem,hbmDEG);
				BitBlt(hdc,382,112,60 ,50 ,hdcmem,0,0,SRCCOPY);
				useDEG=TRUE;
				EndPaint(hwnd,&ps);
				break;
				}
			
			
				
				
			}
			//Sin
			else if((x>=380&&x<=440)&&(y>=190&&y<=240))
			{				
				hdc=BeginPaint(hwnd,&ps);
				SelectObject(hdcmem,hbmSin);
				BitBlt(hdc,382,192,60 ,50 ,hdcmem,0,0,SRCCOPY);
				xx=strtod(Dis,&stop);
				Num[NumPos]=xx;
				
				ClearDis();
				
				
				if(useDEG==TRUE&&use2nd==FALSE)
					Num[0]=Num[0]/180*3.141592653589793;
				
				if(use2nd==TRUE)
					if(Num[0]>1||Num[0]<-1)
					{   
						MessageBox(hwnd,"输入范围超过-1～+1，请重新输入！","错误",MB_OK);
						ClearNum();
						NumPos=0;
						ClearOpr();
						OprPos=0;
						ClearDis();
						nDisLength=0;
						startpostion=300;
						nDisOprLength=0;
						use2nd=FALSE;
						break;
					}
					else
						Result=asin(Num[0]);
				else
					Result=sin(Num[0]);
				if(useDEG==TRUE&&use2nd==TRUE)
					Result=Result/3.141592653589793*180;
				
				pchar=_ecvt(Result,10,&decimal,&sign);
				
				pchar=Correctpchar(pchar);
				
				
				for(i=0;i<=9;i++)
				{
					Dis[i]=*(pchar+i);
				}
				
				useDis=FALSE;
				usepchar=TRUE;
				
				DisplayOpr=TRUE;
				DisOpr[0]='=';
				Finished=TRUE;
				
				FinishedOneOperation=TRUE;
				use2nd=FALSE;
				
				
				
				EndPaint(hwnd,&ps);
				
				
			}
			
			//Cos
			else if((x>=380&&x<=440)&&(y>=260&&y<=310))
			{
				hdc=BeginPaint(hwnd,&ps);
				SelectObject(hdcmem,hbmSin);
				BitBlt(hdc,382,192,60 ,50 ,hdcmem,0,0,SRCCOPY);
				xx=strtod(Dis,&stop);
				Num[NumPos]=xx;
				
				ClearDis();
				
				
				if(useDEG==TRUE&&use2nd==FALSE)
					Num[0]=Num[0]/180*3.141592653589793;
				
				if(use2nd==TRUE)
					if(Num[0]>1||Num[0]<-1)
					{   
						MessageBox(hwnd,"输入范围超过-1～+1，请重新输入！","错误",MB_OK);
						ClearNum();
						NumPos=0;
						ClearOpr();
						OprPos=0;
						ClearDis();
						nDisLength=0;
						startpostion=300;
						nDisOprLength=0;
						use2nd=FALSE;
						break;
					}
					else
					Result=acos(Num[0]);
				else
					Result=cos(Num[0]);
				if(useDEG==TRUE&&use2nd==TRUE)
					Result=Result/3.141592653589793*180;
				
				pchar=_ecvt(Result,10,&decimal,&sign);
				
				pchar=Correctpchar(pchar);
				
				
				for(i=0;i<=9;i++)
				{
					Dis[i]=*(pchar+i);
				}
				
				useDis=FALSE;
				usepchar=TRUE;
				
				DisplayOpr=TRUE;
				DisOpr[0]='=';
				Finished=TRUE;
				
				FinishedOneOperation=TRUE;
				use2nd=FALSE;
				
				
				
				EndPaint(hwnd,&ps);
				
				
			}

		//Tan
			else if((x>=380&&x<=440)&&(y>=330&&y<=380))
			{			
				
				hdc=BeginPaint(hwnd,&ps);
				SelectObject(hdcmem,hbmSin);
				BitBlt(hdc,382,192,60 ,50 ,hdcmem,0,0,SRCCOPY);
				xx=strtod(Dis,&stop);
				Num[NumPos]=xx;
				
				ClearDis();
				
				
				if(useDEG==TRUE&&use2nd==FALSE)
					Num[0]=Num[0]/180*3.141592653589793;
				
				if(use2nd==TRUE)
					Result=atan(Num[0]);
				else
					Result=tan(Num[0]);
				if(useDEG==TRUE&&use2nd==TRUE)
					Result=Result/3.141592653589793*180;
				
				pchar=_ecvt(Result,10,&decimal,&sign);
				
				pchar=Correctpchar(pchar);
				
				
				for(i=0;i<=9;i++)
				{
					Dis[i]=*(pchar+i);
				}
				
				useDis=FALSE;
				usepchar=TRUE;
				
				DisplayOpr=TRUE;
				DisOpr[0]='=';
				Finished=TRUE;
				
				FinishedOneOperation=TRUE;
				use2nd=FALSE;
				
				
				
				EndPaint(hwnd,&ps);
				
			}

			//ln和e的x次幂
			else if((x>=460&&x<=520)&&(y>=190&&y<=240))
			{			hdc=BeginPaint(hwnd,&ps);
            SelectObject(hdcmem,hbmln);
			BitBlt(hdc,462,192,60 ,50 ,hdcmem,0,0,SRCCOPY);

			xx=strtod(Dis,&stop);
			Num[NumPos]=xx;
			
			ClearDis();
			
			if(use2nd==TRUE)
				if(Num[0]<=0)
				{
				MessageBox(hwnd,"只有正数才有对数！","错误",MB_OK);
				ClearNum();
				NumPos=0;
				ClearOpr();
				OprPos=0;
				ClearDis();
				nDisLength=0;
				startpostion=300;
				nDisOprLength=0;
				use2nd=FALSE;
				break;
				}
				else
				Result=exp(Num[0]);
			else
				Result=log(Num[0]);
			pchar=_ecvt(Result,10,&decimal,&sign);
			
			pchar=Correctpchar(pchar);
			
			
			for(i=0;i<=9;i++)
			{
				Dis[i]=*(pchar+i);
			}
			
			useDis=FALSE;
			usepchar=TRUE;
			
			DisplayOpr=TRUE;
			DisOpr[0]='=';
			Finished=TRUE;
			
			FinishedOneOperation=TRUE;
			use2nd=FALSE;
			
			
			EndPaint(hwnd,&ps);
			}
			//Log和10的x次幂
			else if((x>=460&&x<=520)&&(y>=260&&y<=310))
			{
				hdc=BeginPaint(hwnd,&ps);
				SelectObject(hdcmem,hbmLog);
			BitBlt(hdc,462,262,60 ,50 ,hdcmem,0,0,SRCCOPY);

			xx=strtod(Dis,&stop);
			Num[NumPos]=xx;
			
			ClearDis();
			
			if(use2nd==TRUE)
				if(Num[0]<=0)
				{
				MessageBox(hwnd,"只有正数才有对数！","错误",MB_OK);
				ClearNum();
				NumPos=0;
				ClearOpr();
				OprPos=0;
				ClearDis();
				nDisLength=0;
				startpostion=300;
				nDisOprLength=0;
				use2nd=FALSE;
				break;
				}
				else
				Result=pow(10,Num[0]);
             else
				Result=log10(Num[0]);
			
			pchar=_ecvt(Result,10,&decimal,&sign);
			
			pchar=Correctpchar(pchar);
			
			
			for(i=0;i<=9;i++)
			{
				Dis[i]=*(pchar+i);
			}
			
			useDis=FALSE;
			usepchar=TRUE;
			
			DisplayOpr=TRUE;
			DisOpr[0]='=';
			Finished=TRUE;
			
			FinishedOneOperation=TRUE;
			use2nd=FALSE;
			
			
			EndPaint(hwnd,&ps);
			}
			//x平方及开方
			else if((x>=460&&x<=520)&&(y>=330&&y<=380))
			{
				hdc=BeginPaint(hwnd,&ps);
				SelectObject(hdcmem,hbmxfang);
				BitBlt(hdc,462,332,60 ,50 ,hdcmem,0,0,SRCCOPY);
				xx=strtod(Dis,&stop);
			Num[NumPos]=xx;
			
			ClearDis();
			
			if(use2nd==TRUE)
				if(Num[0]<=0)
				{   
				 	MessageBox(hwnd,"负数没有平方根！","错误",MB_OK);
				    ClearNum();
			    	NumPos=0;
				    ClearOpr();
			    	OprPos=0;
			    	ClearDis();
			    	nDisLength=0;
			    	startpostion=300;
			    	nDisOprLength=0;
					use2nd=FALSE;
			    	break;
				}
				else
				Result=sqrt(Num[0]);
			else
				Result=Num[0]*Num[0];

			pchar=_ecvt(Result,10,&decimal,&sign);
			
			pchar=Correctpchar(pchar);
			
			
			for(i=0;i<=9;i++)
			{
				Dis[i]=*(pchar+i);
			}
			
			useDis=FALSE;
			usepchar=TRUE;
			
			DisplayOpr=TRUE;
			DisOpr[0]='=';
			Finished=TRUE;
			
			FinishedOneOperation=TRUE;
			use2nd=FALSE;
			

			
			EndPaint(hwnd,&ps);
			}
			//使用第二功能
			else if((x>=460&&x<=520)&&(y>=110&&y<=160))
			{
				hdc=BeginPaint(hwnd,&ps);
				SelectObject(hdcmem,hbm2nd);
				BitBlt(hdc,462,112,60 ,50 ,hdcmem,0,0,SRCCOPY);
				if(use2nd==FALSE)
				use2nd=TRUE;
				else
					use2nd=FALSE;

				EndPaint(hwnd,&ps);
			}				
			//M+
			else if((x>=540&&x<=600)&&(y>=190&&y<=240))
			{
				hdc=BeginPaint(hwnd,&ps);
				SelectObject(hdcmem,hbmMjia);
				BitBlt(hdc,542,192,60 ,50 ,hdcmem,0,0,SRCCOPY);
				xx=strtod(Dis,&stop);
				M=M+xx;
				EndPaint(hwnd,&ps);
			}
		    //M-
			else if((x>=540&&x<=600)&&(y>=260&&y<=310))
			{
				hdc=BeginPaint(hwnd,&ps);
				SelectObject(hdcmem,hbmMjian);
				BitBlt(hdc,542,262,60 ,50 ,hdcmem,0,0,SRCCOPY);
				xx=strtod(Dis,&stop);
				M=M-xx;
				EndPaint(hwnd,&ps);
			}
			//MR和MC
			else if((x>=540&&x<=600)&&(y>=330&&y<=380))
			{
				hdc=BeginPaint(hwnd,&ps);
				SelectObject(hdcmem,hbmMRC);
				BitBlt(hdc,542,332,60 ,50 ,hdcmem,0,0,SRCCOPY);
				pchar=_ecvt(M,10,&decimal,&sign);
				
				pchar=Correctpchar(pchar);
				useDis=FALSE;
				usepchar=TRUE;
				
				for(i=0;i<=9;i++)
				{
					Dis[i]=*(pchar+i);
				}
				
				
				
				
				EndPaint(hwnd,&ps);
			}
			

				
				
				
				
				
				
			
				

				
			 
			 

				


				
		
		
			
		
		break;

	case WM_LBUTTONUP:
         InvalidateRect(hwnd,NULL,TRUE);
		
		  break;

    case WM_LBUTTONDBLCLK:
		if((x>=540&&x<=600)&&(y>=330&&y<=380))
			M=0;
		break;

   
     
		


	case WM_DESTROY:
		PostQuitMessage(0);		
		break;                      
		
	default:						
		return  DefWindowProc(hwnd,message,wParam,lParam);
}
	
	return (0);
}




//加减乘除运算(带优先级判断)
double Operate(double Num[],int Opr[])
{
	int i,j;
	
	while(Opr[0]!=0)
	{
		
		for(i=0;i<=8;i++)
		{
			if(Opr[i]==4)
			{
				if(Num[i+1]==0)
				{
					MessageBox(hwnd,"除数不能为零！","错误",MB_OK);
					ClearNum();
					NumPos=0;
					ClearOpr();
					OprPos=0;
					ClearDis();
					nDisLength=0;
					startpostion=300;
					nDisOprLength=0;
					break;
				}
				Num[i]=Num[i]/Num[i+1];
				Num[i+1]=-0.0000000051533406;
				Opr[i]=-1;
				for(i=0;i<=9;i++)
				{
					if(Num[i]==-0.0000000051533406)
					{
						for(j=i;j<=9;j++)
						{
							Num[j]=Num[j+1];
						}
						
					}
				}
				for(i=0;i<=8;i++)
				{
					if(Opr[i]==-1)
					{
						for(j=i;j<=8;j++)
						{
							Opr[j]=Opr[j+1];
						}
					}
				}
				
			}

		}
		for(i=0;i<=8;i++)
		{	
			if(Opr[i]==3)
			{
				Num[i]=Num[i]*Num[i+1];
				Num[i+1]=-0.0000000051533406;
				Opr[i]=-1;
				for(i=0;i<=9;i++)
				{
					if(Num[i]==-0.0000000051533406)
					{
						for(j=i;j<=9;j++)
						{
							Num[j]=Num[j+1];
						}
						
					}
				}
				for(i=0;i<=8;i++)
				{
					if(Opr[i]==-1)
					{
						for(j=i;j<=8;j++)
						{
							Opr[j]=Opr[j+1];
						}
					}
				}
				
			}
		}
		for(i=0;i<=8;i++)
		{
			if(Opr[i]==2)
			{
				Num[i]=Num[i]-Num[i+1];
				Num[i+1]=-0.0000000051533406;
				Opr[i]=-1;
				for(i=0;i<=9;i++)
				{
					if(Num[i]==-0.0000000051533406)
					{
						for(j=i;j<=9;j++)
						{
							Num[j]=Num[j+1];
						}
						
					}
				}
				for(i=0;i<=8;i++)
				{
					if(Opr[i]==-1)
					{
						for(j=i;j<=8;j++)
						{
							Opr[j]=Opr[j+1];
						}
					}
				}
				
			}
		}
		for(i=0;i<=8;i++)
		{
			if(Opr[i]==1)
			{
				Num[i]=Num[i]+Num[i+1];
				Num[i+1]=-0.0000000051533406;
				Opr[i]=-1;
				for(i=0;i<=9;i++)
				{
					if(Num[i]==-0.0000000051533406)
					{
						for(j=i;j<=9;j++)
						{
							Num[j]=Num[j+1];
						}
						
					}
				}
				for(i=0;i<=8;i++)
				{
					if(Opr[i]==-1)
					{
						for(j=i;j<=8;j++)
						{
							Opr[j]=Opr[j+1];
						}
					}
				}
				
			}
				
		}
	}
	return Num[0];
}	
void ClearDis()
{
	int i;
	for(i=0;i<=9;i++)
		Dis[i]='a';
}
void ClearNum()
{
	int i;
	for(i=0;i<=9;i++)
		Num[i]=0;
}
void ClearOpr()
{
	int i;
	for(i=0;i<=8;i++)
	    Opr[i]=0;
}
//依据小数点位置和正负修正由double转来的string
char *Correctpchar(char *pchar)
{
	int i;
	//如果decimal为正(>=1)
	if(decimal>0)
	{
		for(i=9;i>=decimal+1;i--)
			*(pchar+i)=*(pchar+i-1);
		*(pchar+decimal)='.';
	}
	
	//如果decimal小于等于零
	if(decimal<=0)
	{
		
		for(i=9;i>=(-decimal+1);i--)
			*(pchar+i)=*(pchar+i-(-decimal+2));
		*(pchar)='0';
		*(pchar+1)='.';
		for(i=2;i<=(-decimal+1);i++)
			*(pchar+i)='0';
	}
	
	//如果计算结果为负
	if(sign==1)
	{
		for(i=9;i>=1;i--)
			*(pchar+i)=*(pchar+i-1);
		*(pchar)='-';
	}

	return pchar;
	
}
