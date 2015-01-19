// ExpressionCalculateDlg.h : header file
//

#if !defined(AFX_EXPRESSIONCALCULATEDLG_H__A01AC2F5_9A40_444D_892E_609C56A90700__INCLUDED_)
#define AFX_EXPRESSIONCALCULATEDLG_H__A01AC2F5_9A40_444D_892E_609C56A90700__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

/////////////////////////////////////////////////////////////////////////////
// CExpressionCalculateDlg dialog

class CExpressionCalculateDlg : public CDialog
{
// Construction
public:
	CExpressionCalculateDlg(CWnd* pParent = NULL);	// standard constructor

// Dialog Data
	//{{AFX_DATA(CExpressionCalculateDlg)
	enum { IDD = IDD_EXPRESSIONCALCULATE_DIALOG };
	CString	m_strExpression;
	CString	m_strResult;
	//}}AFX_DATA

	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CExpressionCalculateDlg)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);	// DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:
	HICON m_hIcon;

	// Generated message map functions
	//{{AFX_MSG(CExpressionCalculateDlg)
	virtual BOOL OnInitDialog();
	afx_msg void OnSysCommand(UINT nID, LPARAM lParam);
	afx_msg void OnPaint();
	afx_msg HCURSOR OnQueryDragIcon();
	afx_msg void OnButton1();
	afx_msg void OnButton2();
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_EXPRESSIONCALCULATEDLG_H__A01AC2F5_9A40_444D_892E_609C56A90700__INCLUDED_)
