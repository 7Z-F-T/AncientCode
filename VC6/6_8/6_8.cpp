#include "windows.h"
#include "stdlib.h"

LRESULT CALLBACK WndProc(HWND hwnd,UINT message,WPARAM wParam,LPARAM lParam);
int WINAPI WinMain(HINSTANCE hInstance,HINSTANCE hPrevInstance,LPSTR lpszCmdLine,int nCmdShow)
{
	HWND hwnd;
	MSG Msg;

	char ClassName[]="6_8";
	char Title[]="6_8 ��52 ��� 2005010130";

	WNDCLASS wndclass;
	wndclass.style =CS_HREDRAW|CS_VREDRAW;
	wndclass.lpfnWndProc=WndProc;
	wndclass.cbClsExtra =0;
	wndclass.cbWndExtra =0;
	wndclass.hInstance =hInstance;
	wndclass.hIcon =LoadIcon(NULL,IDI_APPLICATION);
	wndclass.hCursor=LoadCursor(NULL,IDC_ARROW);
	wndclass.hbrBackground =(HBRUSH)GetStockObject(WHITE_BRUSH);
	wndclass.lpszMenuName =NULL;
	wndclass.lpszClassName ="6_8";
	if(!RegisterClass(&wndclass)) return FALSE;
	hwnd=CreateWindow(ClassName,Title,WS_OVERLAPPEDWINDOW,CW_USEDEFAULT,CW_USEDEFAULT,CW_USEDEFAULT,CW_USEDEFAULT,NULL,NULL,hInstance,NULL);
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
	hdc=GetDC(hwnd);
	PAINTSTRUCT ps;
	static POINTS points[1500];            //������staticʵ����̫��Ҫ�˰�!!!!  
   	static BOOL begin=FALSE;               //û��static���޷�����ÿ������ƶ����µ���Ϣ,    
	static int nPoints=0;                  //����ÿ���ƶ���궼���³�ʼ��������  
	int i=0,j=0;
	switch(message)
	{
	case WM_LBUTTONDOWN:
	    nPoints=0;
		begin=TRUE;
		InvalidateRect(hwnd,NULL,TRUE);     //��TRUE,������BeginPaintʱ�����һ�λ���ͼ��  
		break;
		
	case WM_MOUSEMOVE:
        if(begin==TRUE&&nPoints<1500)
		{SetPixel(hdc,LOWORD(lParam),HIWORD(lParam),RGB(0,0,0));//SetPixelֻ�ǽ�ĳ��Ⱦɫ,������
		points[nPoints]=MAKEPOINTS(lParam);   //MAKEPOINTS��POINTS�ṹ������ʹ�ÿ��Ժܷ���
		nPoints++;                            //�ػ�ȡ���λ����Ϣ
		}
		break;
	

	case WM_LBUTTONUP:
		begin=FALSE;
		InvalidateRect(hwnd,NULL,FALSE);      
		break;
		
	case WM_PAINT:
		hdc=BeginPaint(hwnd,&ps);
		for(i=0;i<=nPoints-1;i++)
			for(j=i+1;j<=nPoints-1;j++)
			{MoveToEx(hdc,points[i].x,points[i].y,NULL);
			   LineTo(hdc,points[j].x,points[j].y);
			}
			EndPaint(hwnd,&ps);
			
			
		break;
	
	case WM_DESTROY:
			PostQuitMessage(0);
			break;

	
	}
	return DefWindowProc(hwnd,message,wParam,lParam);
}


  




