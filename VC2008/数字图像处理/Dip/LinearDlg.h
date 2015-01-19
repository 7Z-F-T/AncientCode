#if !defined(AFX_LINEARDLG_H__28E497E2_D28A_4E44_AFC9_81B8C1584B1F__INCLUDED_)
#define AFX_LINEARDLG_H__28E497E2_D28A_4E44_AFC9_81B8C1584B1F__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
// LinearDlg.h : header file
//

/////////////////////////////////////////////////////////////////////////////
// CLinearDlg dialog
#include "DipDialog.h"
#include "LineSlider.h"

class CLinearDlg : public CDipDialog
{
// Construction
public:
	int Change();
	CLinearDlg(CWnd* pParent = NULL);   // standard constructor

// Dialog Data
	//{{AFX_DATA(CLinearDlg)
	enum { IDD = IDD_POINT_LINEAR };
	int		m_nBright;
	float	m_fContrast;
	//}}AFX_DATA
	CLineSlider m_Slider1, m_Slider2;

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CLinearDlg)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:

	// Generated message map functions
	//{{AFX_MSG(CLinearDlg)
	virtual BOOL OnInitDialog();
	afx_msg void OnChangeContrast();
	afx_msg void OnChangeBrightness();
	afx_msg void OnPreview();
	afx_msg void OnHScroll(UINT nSBCode, UINT nPos, CScrollBar* pScrollBar);
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_LINEARDLG_H__28E497E2_D28A_4E44_AFC9_81B8C1584B1F__INCLUDED_)
