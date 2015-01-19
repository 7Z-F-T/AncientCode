#if !defined(AFX_HISTOGRAMDLG_H__28D2F022_FCF3_4829_89F5_65C7005C54E6__INCLUDED_)
#define AFX_HISTOGRAMDLG_H__28D2F022_FCF3_4829_89F5_65C7005C54E6__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
// HistogramDlg.h : header file
//

/////////////////////////////////////////////////////////////////////////////
// CHistogramDlg dialog
#include "HistStatic.h"
#include "ColorStatic.h"
#include "DipDoc.h"

class CHistogramDlg : public CDialog
{
// Construction
public:
	CDipDoc * m_pDoc;
	CHistStatic m_HistView;
	CColorStatic m_ColorList;
	CHistogramDlg(CWnd* pParent = NULL);   // standard constructor

// Dialog Data
	//{{AFX_DATA(CHistogramDlg)
	enum { IDD = IDD_HISTOGRAM };
	//}}AFX_DATA


// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CHistogramDlg)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:

	// Generated message map functions
	//{{AFX_MSG(CHistogramDlg)
	virtual BOOL OnInitDialog();
	afx_msg void OnSelchangeChannel();
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_HISTOGRAMDLG_H__28D2F022_FCF3_4829_89F5_65C7005C54E6__INCLUDED_)
