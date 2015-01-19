// ImageProc.h : main header file for the IMAGEPROC application
//

#if !defined(AFX_IMAGEPROC_H__DD7C2C66_95B8_11D3_87D7_0050BAAD2602__INCLUDED_)
#define AFX_IMAGEPROC_H__DD7C2C66_95B8_11D3_87D7_0050BAAD2602__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#ifndef __AFXWIN_H__
	#error include 'stdafx.h' before including this file for PCH
#endif

#include "resource.h"       // main symbols

/////////////////////////////////////////////////////////////////////////////
// CImageProcApp:
// See ImageProc.cpp for the implementation of this class
//

class CImageProcApp : public CWinApp
{
public:
	CImageProcApp();

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CImageProcApp)
	public:
	virtual BOOL InitInstance();
	//}}AFX_VIRTUAL

// Implementation
	//{{AFX_MSG(CImageProcApp)
	afx_msg void OnAppAbout();
		// NOTE - the ClassWizard will add and remove member functions here.
		//    DO NOT EDIT what you see in these blocks of generated code !
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};


/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_IMAGEPROC_H__DD7C2C66_95B8_11D3_87D7_0050BAAD2602__INCLUDED_)
