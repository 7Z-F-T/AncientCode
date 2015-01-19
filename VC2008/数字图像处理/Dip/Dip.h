// Dip.h : main header file for the DIP application
//

#if !defined(AFX_DIP_H__7C48023B_8383_4F74_8254_121055C3E2B6__INCLUDED_)
#define AFX_DIP_H__7C48023B_8383_4F74_8254_121055C3E2B6__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#ifndef __AFXWIN_H__
	#error include 'stdafx.h' before including this file for PCH
#endif

#include "resource.h"       // main symbols

/////////////////////////////////////////////////////////////////////////////
// CDipApp:
// See Dip.cpp for the implementation of this class
//

class CDipApp : public CWinApp
{
public:
	CDipApp();

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CDipApp)
	public:
	virtual BOOL InitInstance();
	//}}AFX_VIRTUAL

// Implementation
	//{{AFX_MSG(CDipApp)
	afx_msg void OnAppAbout();
		// NOTE - the ClassWizard will add and remove member functions here.
		//    DO NOT EDIT what you see in these blocks of generated code !
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};


/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_DIP_H__7C48023B_8383_4F74_8254_121055C3E2B6__INCLUDED_)
