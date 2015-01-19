#if !defined(AFX_MOVETO_H__612532D9_F3E7_4A9B_B530_48AF5FC6D673__INCLUDED_)
#define AFX_MOVETO_H__612532D9_F3E7_4A9B_B530_48AF5FC6D673__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
// MoveTo.h : header file
//

/////////////////////////////////////////////////////////////////////////////
// CMoveTo dialog

class CMoveTo : public CDialog
{
// Construction
public:
	CMoveTo(CWnd* pParent = NULL);   // standard constructor

// Dialog Data
	//{{AFX_DATA(CMoveTo)
	enum { IDD = IDD_DIALOG1 };
	long	m_RecordID;
	//}}AFX_DATA


// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CMoveTo)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:

	// Generated message map functions
	//{{AFX_MSG(CMoveTo)
		// NOTE: the ClassWizard will add member functions here
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_MOVETO_H__612532D9_F3E7_4A9B_B530_48AF5FC6D673__INCLUDED_)
