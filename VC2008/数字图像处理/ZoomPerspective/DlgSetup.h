#if !defined(AFX_DLGSETUP_H__181DF13E_925D_451B_BE63_ED9BBBF852C9__INCLUDED_)
#define AFX_DLGSETUP_H__181DF13E_925D_451B_BE63_ED9BBBF852C9__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
// DlgSetup.h : header file
//

/////////////////////////////////////////////////////////////////////////////
// CDlgSetupPerspective dialog

class CDlgSetupPerspective : public CDialog
{
// Construction
public:
	CDlgSetupPerspective(CWnd* pParent = NULL);   // standard constructor

// Dialog Data
	//{{AFX_DATA(CDlgSetupPerspective)
	enum { IDD = IDD_DIALOG1 };
	double	m_fov;
	double	m_zNear;
	double	m_zFar;
	double	m_xeye;
	double	m_yeye;
	double	m_zeye;
	double	m_xcenter;
	double	m_ycenter;
	double	m_zcenter;
	//}}AFX_DATA


// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CDlgSetupPerspective)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:

	// Generated message map functions
	//{{AFX_MSG(CDlgSetupPerspective)
		// NOTE: the ClassWizard will add member functions here
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_DLGSETUP_H__181DF13E_925D_451B_BE63_ED9BBBF852C9__INCLUDED_)
