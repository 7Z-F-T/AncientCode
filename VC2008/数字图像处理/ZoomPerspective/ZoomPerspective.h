// ZoomPerspective.h : main header file for the ZOOMPERSPECTIVE application
//

#if !defined(AFX_ZOOMPERSPECTIVE_H__A39B0779_7B97_49BB_92F5_3310AB866D49__INCLUDED_)
#define AFX_ZOOMPERSPECTIVE_H__A39B0779_7B97_49BB_92F5_3310AB866D49__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#ifndef __AFXWIN_H__
	#error include 'stdafx.h' before including this file for PCH
#endif

#include "resource.h"       // main symbols

/////////////////////////////////////////////////////////////////////////////
// CZoomPerspectiveApp:
// See ZoomPerspective.cpp for the implementation of this class
//

class CZoomPerspectiveApp : public CWinApp
{
public:
	CZoomPerspectiveApp();

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CZoomPerspectiveApp)
	public:
	virtual BOOL InitInstance();
	//}}AFX_VIRTUAL

// Implementation
	//{{AFX_MSG(CZoomPerspectiveApp)
	afx_msg void OnAppAbout();
		// NOTE - the ClassWizard will add and remove member functions here.
		//    DO NOT EDIT what you see in these blocks of generated code !
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};


/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_ZOOMPERSPECTIVE_H__A39B0779_7B97_49BB_92F5_3310AB866D49__INCLUDED_)
