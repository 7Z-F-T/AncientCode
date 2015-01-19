// 10_13.h : main header file for the 10_13 application
//

#if !defined(AFX_10_13_H__DB18C564_EB0C_44B8_8AE8_170661BA7DC3__INCLUDED_)
#define AFX_10_13_H__DB18C564_EB0C_44B8_8AE8_170661BA7DC3__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#ifndef __AFXWIN_H__
	#error include 'stdafx.h' before including this file for PCH
#endif

#include "resource.h"		// main symbols

/////////////////////////////////////////////////////////////////////////////
// CMy10_13App:
// See 10_13.cpp for the implementation of this class
//

class CMy10_13App : public CWinApp
{
public:
	CMy10_13App();

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CMy10_13App)
	public:
	virtual BOOL InitInstance();
	//}}AFX_VIRTUAL

// Implementation

	//{{AFX_MSG(CMy10_13App)
		// NOTE - the ClassWizard will add and remove member functions here.
		//    DO NOT EDIT what you see in these blocks of generated code !
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};


/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_10_13_H__DB18C564_EB0C_44B8_8AE8_170661BA7DC3__INCLUDED_)
