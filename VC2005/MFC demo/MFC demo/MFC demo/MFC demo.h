// MFC demo.h : MFC demo Ӧ�ó������ͷ�ļ�
//
#pragma once

#ifndef __AFXWIN_H__
	#error "�ڰ������ļ�֮ǰ������stdafx.h�������� PCH �ļ�"
#endif

#include "resource.h"       // ������


// CMFCdemoApp:
// �йش����ʵ�֣������ MFC demo.cpp
//

class CMFCdemoApp : public CWinApp
{
public:
	CMFCdemoApp();


// ��д
public:
	virtual BOOL InitInstance();

// ʵ��
	afx_msg void OnAppAbout();
	DECLARE_MESSAGE_MAP()
};

extern CMFCdemoApp theApp;