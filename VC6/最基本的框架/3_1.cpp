#include <windows.h>
LRESULT CALLBACK WndProc(HWND,UINT,WPARAM,LPARAM); 	//���ں���˵��
//------------ ���³�ʼ��������----------------
int WINAPI WinMain(HINSTANCE	hInstance,HINSTANCE hPrevInst,LPSTR lpszCmdLine,int	nCmdShow)
{
		HWND hwnd ;
		MSG Msg ;
		WNDCLASS wndclass ;
		char lpszClassName[] = "����";			//��������
		char lpszTitle[]= "My_Windows";			//���ڱ�����

        //������Ķ���
		wndclass.style = 0;						//��������Ϊȱʡ����
		wndclass.lpfnWndProc = WndProc ;		//���ڴ�����ΪWndProc
		wndclass.cbClsExtra	= 0 ;				//����������չ
		wndclass.cbWndExtra	= 0 ;				//����ʵ������չ
		wndclass.hInstance = hInstance ;		//��ǰʵ�����
		wndclass.hIcon = LoadIcon( NULL, IDI_APPLICATION) ;
												//���ڵ���С��ͼ��Ϊȱʡͼ��
		wndclass.hCursor = LoadCursor( NULL,IDC_ARROW); //���ڲ��ü�ͷ���
		wndclass.hbrBackground =(HBRUSH) GetStockObject(WHITE_BRUSH); //���ڱ���Ϊ��ɫ
		wndclass.lpszMenuName = NULL ;			//�������޲˵�
		wndclass.lpszClassName = lpszClassName; 			//��������Ϊ"����ʾ��"

//--------------- ���½��д������ע�� -----------------
	    if( !RegisterClass( &wndclass))			//���ע��ʧ���򷢳���������
		{
			MessageBeep(0) ;
			return FALSE ;
		}

        //��������
		hwnd=CreateWindow(
						lpszClassName,		//��������
						lpszTitle,				//����ʵ���ı�����
						WS_OVERLAPPEDWINDOW,	//���ڵķ��
						CW_USEDEFAULT,
						CW_USEDEFAULT,			//�������Ͻ�����Ϊȱʡֵ
						CW_USEDEFAULT,
						CW_USEDEFAULT,			//���ڵĸߺͿ�Ϊȱʡֵ
						NULL,					//�˴����޸�����
						NULL,					//�˴��������˵�
						hInstance,				//�����˴��ڵ�Ӧ�ó���ĵ�ǰ���
						NULL) ;					//��ʹ�ø�ֵ

		
		ShowWindow( hwnd, nCmdShow); 			//��ʾ����
		UpdateWindow(hwnd); 					//�����û���
		while( GetMessage(&Msg, NULL, 0, 0))	//��Ϣѭ��
		{
			TranslateMessage( &Msg) ;
			DispatchMessage( &Msg) ;
		}
		return Msg.wParam;						//��Ϣѭ��������������ֹʱ����Ϣ����ϵͳ
}

//���ں���
LRESULT CALLBACK WndProc(HWND hwnd,UINT message,WPARAM  wParam,LPARAM lParam)
{
		switch(message)
{
		case WM_DESTROY:
			PostQuitMessage(0);					//����PostQuitMessage����WM_QUIT��Ϣ

		default:								//ȱʡʱ����ϵͳ��Ϣȱʡ������
			return  DefWindowProc(hwnd,message,wParam,lParam);
		}
		return (0);
}
