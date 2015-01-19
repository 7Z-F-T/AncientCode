// 9_13Dlg.h : header file
//

#if !defined(AFX_9_13DLG_H__A72C05B7_9594_4CF0_84BF_C69DAEE2EAA2__INCLUDED_)
#define AFX_9_13DLG_H__A72C05B7_9594_4CF0_84BF_C69DAEE2EAA2__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

/////////////////////////////////////////////////////////////////////////////
// CMy9_13Dlg dialog

class CMy9_13Dlg : public CDialog
{
// Construction
public:
	CMy9_13Dlg(CWnd* pParent = NULL);	// standard constructor

// Dialog Data
	//{{AFX_DATA(CMy9_13Dlg)
	enum { IDD = IDD_MY9_13_DIALOG };
	CScrollBar	m_blue_scrollbar;
	CScrollBar	m_green_scrollbar;
	CScrollBar	m_red_scrollbar;
	CEdit	m_blue_edit;
	CEdit	m_green_edit;
	CEdit	m_red_edit;
	//}}AFX_DATA

	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CMy9_13Dlg)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);	// DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:
	HICON m_hIcon;

	// Generated message map functions
	//{{AFX_MSG(CMy9_13Dlg)
	virtual BOOL OnInitDialog();
	afx_msg void OnSysCommand(UINT nID, LPARAM lParam);
	afx_msg void OnPaint();
	afx_msg HCURSOR OnQueryDragIcon();
	afx_msg void OnHScroll(UINT nSBCode, UINT nPos, CScrollBar* pScrollBar);
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_9_13DLG_H__A72C05B7_9594_4CF0_84BF_C69DAEE2EAA2__INCLUDED_)
