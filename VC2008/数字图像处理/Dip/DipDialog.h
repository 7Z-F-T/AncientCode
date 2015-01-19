#if !defined(AFX_DIPDIALOG_H__29CEADF4_0A36_409B_BD30_63FC9F1916A8__INCLUDED_)
#define AFX_DIPDIALOG_H__29CEADF4_0A36_409B_BD30_63FC9F1916A8__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
// DipDialog.h : header file
//

/////////////////////////////////////////////////////////////////////////////
// CDipDialog dialog

#include "DipDoc.h"

class CDipDialog : public CDialog
{
// Construction
public:
	CDipDialog(UINT nIDTemplate, CWnd* pParent = NULL);   // standard constructor
	virtual int Change()=0;

// Dialog Data
	//{{AFX_DATA(CDipDialog)
//	enum { IDD = IDR_MAINFRAME };
		// NOTE: the ClassWizard will add data members here
	//}}AFX_DATA

	CDipDoc *pDoc;
	BOOL	m_bPreview;

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CDipDialog)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:
	BOOL m_bDlgReady;

	// Generated message map functions
	//{{AFX_MSG(CDipDialog)
	virtual BOOL OnInitDialog();
	afx_msg void OnDestroy();
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_DIPDIALOG_H__29CEADF4_0A36_409B_BD30_63FC9F1916A8__INCLUDED_)
