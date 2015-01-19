// ExpressionCalculate.h : main header file for the EXPRESSIONCALCULATE application
//

#if !defined(AFX_EXPRESSIONCALCULATE_H__FE032F85_F636_4E31_8474_C4E43615BB26__INCLUDED_)
#define AFX_EXPRESSIONCALCULATE_H__FE032F85_F636_4E31_8474_C4E43615BB26__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#ifndef __AFXWIN_H__
	#error include 'stdafx.h' before including this file for PCH
#endif

#include "resource.h"		// main symbols

/////////////////////////////////////////////////////////////////////////////
// CExpressionCalculateApp:
// See ExpressionCalculate.cpp for the implementation of this class
//

class CExpressionCalculateApp : public CWinApp
{
public:
	CExpressionCalculateApp();

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CExpressionCalculateApp)
	public:
	virtual BOOL InitInstance();
	//}}AFX_VIRTUAL

// Implementation

	//{{AFX_MSG(CExpressionCalculateApp)
		// NOTE - the ClassWizard will add and remove member functions here.
		//    DO NOT EDIT what you see in these blocks of generated code !
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};


/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_EXPRESSIONCALCULATE_H__FE032F85_F636_4E31_8474_C4E43615BB26__INCLUDED_)
