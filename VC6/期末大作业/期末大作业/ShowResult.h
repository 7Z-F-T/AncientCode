#if !defined(AFX_SHOWRESULT_H__7C782A6E_B838_482B_AECE_56EDB6BB052F__INCLUDED_)
#define AFX_SHOWRESULT_H__7C782A6E_B838_482B_AECE_56EDB6BB052F__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
// ShowResult.h : header file
//

/////////////////////////////////////////////////////////////////////////////
// CShowResult dialog

class CShowResult : public CDialog
{
// Construction
public:
	CShowResult(CWnd* pParent = NULL);   // standard constructor

// Dialog Data
	//{{AFX_DATA(CShowResult)
	enum { IDD = IDD_DIALOG5 };
	float	m_show1;
	float	m_show2;
	float	m_show3;
	float	m_show4;
	float	m_show5;
	float	m_show6;
	//}}AFX_DATA


// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CShowResult)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:

	// Generated message map functions
	//{{AFX_MSG(CShowResult)
		// NOTE: the ClassWizard will add member functions here
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_SHOWRESULT_H__7C782A6E_B838_482B_AECE_56EDB6BB052F__INCLUDED_)
