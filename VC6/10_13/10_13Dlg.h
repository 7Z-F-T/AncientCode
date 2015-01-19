// 10_13Dlg.h : header file
//

#if !defined(AFX_10_13DLG_H__0A261584_1CFE_4C51_99D3_87E4D190C859__INCLUDED_)
#define AFX_10_13DLG_H__0A261584_1CFE_4C51_99D3_87E4D190C859__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

/////////////////////////////////////////////////////////////////////////////
// CMy10_13Dlg dialog

class CMy10_13Dlg : public CDialog
{
// Construction
public:
	CMy10_13Dlg(CWnd* pParent = NULL);	// standard constructor

// Dialog Data
	//{{AFX_DATA(CMy10_13Dlg)
	enum { IDD = IDD_MY10_13_DIALOG };
	CString	m_edit1;
	CString	m_edit2;
	//}}AFX_DATA

	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CMy10_13Dlg)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);	// DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:
	HICON m_hIcon;

	// Generated message map functions
	//{{AFX_MSG(CMy10_13Dlg)
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

#endif // !defined(AFX_10_13DLG_H__0A261584_1CFE_4C51_99D3_87E4D190C859__INCLUDED_)
