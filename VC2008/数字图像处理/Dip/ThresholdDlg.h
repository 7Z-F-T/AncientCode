#if !defined(AFX_THRESHOLDDLG_H__FEB8507E_7D63_41D1_9524_FA3D9670461F__INCLUDED_)
#define AFX_THRESHOLDDLG_H__FEB8507E_7D63_41D1_9524_FA3D9670461F__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
// ThresholdDlg.h : header file
//

/////////////////////////////////////////////////////////////////////////////
// CThresholdDlg dialog

#include "DipDialog.h"
#include "LineSlider.h"

class CThresholdDlg : public CDipDialog
{
// Construction
public:
	int Change ();
	CThresholdDlg(CWnd* pParent = NULL);   // standard constructor

// Dialog Data
	//{{AFX_DATA(CThresholdDlg)
	enum { IDD = IDD_POINT_THRESHOLD };
	int		m_nThreshold;
	//}}AFX_DATA
	CLineSlider m_Slider;

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CThresholdDlg)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:

	// Generated message map functions
	//{{AFX_MSG(CThresholdDlg)
	virtual BOOL OnInitDialog();
	afx_msg void OnChangeThreshold();
	afx_msg void OnPreview();
	afx_msg void OnHScroll(UINT nSBCode, UINT nPos, CScrollBar* pScrollBar);
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_THRESHOLDDLG_H__FEB8507E_7D63_41D1_9524_FA3D9670461F__INCLUDED_)
