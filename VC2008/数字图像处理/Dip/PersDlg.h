#if !defined(AFX_PERSDLG_H__0136000A_637D_4678_8710_A7B3DD969219__INCLUDED_)
#define AFX_PERSDLG_H__0136000A_637D_4678_8710_A7B3DD969219__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
// PersDlg.h : header file
//

#include "DipDialog.h"
#include "LineSlider.h"
/////////////////////////////////////////////////////////////////////////////
// CPersDlg dialog

class CPersDlg : public CDipDialog
{
// Construction
public:
	int Change();
	CPersDlg(CWnd* pParent = NULL);   // standard constructor

// Dialog Data
	//{{AFX_DATA(CPersDlg)
	enum { IDD = IDD_GEO_PERS };
	float	m_dDistance;
	float	m_dFocus;
	float	m_dPhai;
	float	m_dThita;
	//}}AFX_DATA


// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CPersDlg)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:
	CLineSlider m_Slider1, m_Slider2, m_Slider3, m_Slider4;
	// Generated message map functions
	//{{AFX_MSG(CPersDlg)
	virtual BOOL OnInitDialog();
	afx_msg void OnHScroll(UINT nSBCode, UINT nPos, CScrollBar* pScrollBar);
	afx_msg void OnChangeDistance();
	afx_msg void OnChangeThita();
	afx_msg void OnChangePhai();
	afx_msg void OnChangeFocus();
	afx_msg void OnPreview();
	afx_msg void OnReset();
	//}}AFX_MSG

	DECLARE_MESSAGE_MAP()
};

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_PERSDLG_H__0136000A_637D_4678_8710_A7B3DD969219__INCLUDED_)
