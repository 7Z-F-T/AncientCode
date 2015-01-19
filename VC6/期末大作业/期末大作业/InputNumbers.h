#if !defined(AFX_INPUTNUMBERS_H__656B6073_B654_4AD2_BE20_741ABC81A9E1__INCLUDED_)
#define AFX_INPUTNUMBERS_H__656B6073_B654_4AD2_BE20_741ABC81A9E1__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
// InputNumbers.h : header file
//

/////////////////////////////////////////////////////////////////////////////
// CInputNumbers dialog

class CInputNumbers : public CDialog
{
// Construction
public:
	CInputNumbers(CWnd* pParent = NULL);   // standard constructor

// Dialog Data
	//{{AFX_DATA(CInputNumbers)
	enum { IDD = IDD_DIALOG4 };
	float	m_1;
	float	m_2;
	float	m_3;
	float	m_4;
	float	m_5;
	//}}AFX_DATA


// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CInputNumbers)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:

	// Generated message map functions
	//{{AFX_MSG(CInputNumbers)
		// NOTE: the ClassWizard will add member functions here
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_INPUTNUMBERS_H__656B6073_B654_4AD2_BE20_741ABC81A9E1__INCLUDED_)
