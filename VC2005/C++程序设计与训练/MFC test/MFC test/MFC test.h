// MFC test.h : MFC test Ӧ�ó������ͷ�ļ�
//
#pragma once

#ifndef __AFXWIN_H__
	#error "�ڰ������ļ�֮ǰ������stdafx.h�������� PCH �ļ�"
#endif

#include "resource.h"       // ������


// CMFCtestApp:
// �йش����ʵ�֣������ MFC test.cpp
//

class CMFCtestApp : public CWinApp
{
public:
	CMFCtestApp();


// ��д
public:
	virtual BOOL InitInstance();

// ʵ��
	afx_msg void OnAppAbout();
	DECLARE_MESSAGE_MAP()
};

extern CMFCtestApp theApp;