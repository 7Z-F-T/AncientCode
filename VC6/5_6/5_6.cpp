#include "windows.h"
#include "stdlib.h"
#include "string.h"
#include "stdafx.h
LRESULT CALLBACK WndProc(HWND,UINT,WPARAM,LPARAM);
int WINAPI WinMain(HINSTANCE hInstance,HINSTANCE hPrevInstance,LPSTR lpszCmdLine,int nCmdShow)
{
	HWND hwnd;
	MSG Msg;
	WNDCLASS wndclass;
	wndclass.style =0;
	wndclass.lpfnWndProc=WndProc;
	wndclass.cbClsExtra =0;
	wndclass.cbWndExtra =0;
	wndclass.hInstance =hInstance;
	wndclass.hIcon =LoadIcon(NULL,IDI_APPLICATION);
	wndclass.hCursor=LoadCursor(NULL,IDC_ARROW);
	wndclass.hbrBackground =(HBRUSH)GetStockObject(WHITE_BRUSH);
	wndclass.lpszMenuName =NULL;
	wndclass.lpszClassName ="5_6";
	RegisterClass(&wndclass);
	hwnd=CreateWindow("5_6","5_6 结52 侯杰 2005010130",WS_OVERLAPPEDWINDOW,CW_USEDEFAULT,CW_USEDEFAULT,CW_USEDEFAULT,CW_USEDEFAULT,NULL,NULL,hInstance,NULL);
	ShowWindow(hwnd,nCmdShow);
	UpdateWindow(hwnd);
	while(GetMessage(&Msg,NULL,0,0))
	{
		TranslateMessage(&Msg);
		DispatchMessage(&Msg);
	}
	return Msg.wParam ;
}

LRESULT CALLBACK WndProc(HWND hwnd,UINT message,WPARAM wParam,LPARAM lParam)
{
	HDC hdc;
	HFONT hF;
	PAINTSTRUCT ps;
	TEXTMETRIC tm;
	char str[]="黄维通老师我们永远支持你！！！";
	char chFontname[10];
	static int x=700,y=0;
	static int s=0;
	
	switch(message)
	{
	case WM_CREATE:
		    SetTimer(hwnd,1,30,NULL);
		    break;

	case WM_TIMER:
		    InvalidateRect(hwnd,NULL,1);

	case WM_PAINT:
			
	    hdc=BeginPaint(hwnd,&ps);
		if(s==0)
		{SetTextColor(hdc,RGB(255,0,0));
		strcpy(chFontname,"宋体");
		}
		else if(s==1)
		{SetTextColor(hdc,RGB(0,255,0));
		strcpy(chFontname,"楷体_GB2312");
		}
		else if(s==2)
		{SetTextColor(hdc,RGB(255,255,0));
		strcpy(chFontname,"仿宋_GB2312");
		}
		else if(s==3)
		{SetTextColor(hdc,RGB(0,0,255));
		strcpy(chFontname,"黑体");
		}
		
		hF=CreateFont(40,0,0,0,400,0,0,0,GB2312_CHARSET,OUT_DEFAULT_PRECIS,CLIP_DEFAULT_PRECIS,DEFAULT_QUALITY,DEFAULT_PITCH|FF_DONTCARE,chFontname);
			
		
			SelectObject(hdc,hF);
			GetTextMetrics(hdc,&tm);
			TextOut(hdc,x,y,str,strlen(str));
			EndPaint(hwnd,&ps);
			DeleteObject(hF);
			x=x-5;
			if(x==(-strlen(str)*20)) {s=s+1;x=700;}
			if(s==4) s=0;
			break;


	case WM_DESTROY:
			PostQuitMessage(0);
			break;

	default:return DefWindowProc(hwnd,message,wParam,lParam);
	}
	return 0;
}


  




