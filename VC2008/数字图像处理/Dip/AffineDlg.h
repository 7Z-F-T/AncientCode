#if !defined(AFX_AFFINEDLG_H__5EAF826B_C92B_40F4_9680_4AFEF9182FFE__INCLUDED_)
#define AFX_AFFINEDLG_H__5EAF826B_C92B_40F4_9680_4AFEF9182FFE__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
// AffineDlg.h : header file
//

#include "DipDialog.h"
#include "LineSlider.h"

/////////////////////////////////////////////////////////////////////////////
// CAffineDlg dialog

class CAffineDlg : public CDipDialog
{
// Construction
public:
	int Change();
	CAffineDlg(CWnd* pParent = NULL);   // standard constructor

// Dialog Data
	//{{AFX_DATA(CAffineDlg)
	enum { IDD = IDD_GEO_AFFINE };
	float	m_dRotate;
	float	m_dDistort;
	float	m_dTrans;
	float	m_dScale;
	//}}AFX_DATA

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CAffineDlg)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:
	CLineSlider m_slider1, m_slider2, m_slider3, m_slider4;
	// Generated message map functions
	//{{AFX_MSG(CAffineDlg)
	virtual BOOL OnInitDialog();
	afx_msg void OnChangeDistort();
	afx_msg void OnChangeScale();
	afx_msg void OnChangeRotate();
	afx_msg void OnChangeTransform();
	afx_msg void OnPreview();
	afx_msg void OnHScroll(UINT nSBCode, UINT nPos, CScrollBar* pScrollBar);
	afx_msg void OnReset();
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_AFFINEDLG_H__5EAF826B_C92B_40F4_9680_4AFEF9182FFE__INCLUDED_)
