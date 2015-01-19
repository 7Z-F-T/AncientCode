#if !defined(AFX_SORT_H__FB6F9DC1_F296_4598_B310_1D014DD3CD89__INCLUDED_)
#define AFX_SORT_H__FB6F9DC1_F296_4598_B310_1D014DD3CD89__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
// Sort.h : header file
//

/////////////////////////////////////////////////////////////////////////////
// CSort dialog

class CSort : public CDialog
{
// Construction
public:
	CSort(CWnd* pParent = NULL);   // standard constructor

// Dialog Data
	//{{AFX_DATA(CSort)
	enum { IDD = IDD_DIALOG2 };
	CString	m_Sort;
	//}}AFX_DATA


// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CSort)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:

	// Generated message map functions
	//{{AFX_MSG(CSort)
		// NOTE: the ClassWizard will add member functions here
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_SORT_H__FB6F9DC1_F296_4598_B310_1D014DD3CD89__INCLUDED_)
