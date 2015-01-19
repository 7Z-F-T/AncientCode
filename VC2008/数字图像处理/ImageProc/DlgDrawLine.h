#if !defined(AFX_DLGDRAWLINE_H__DD7C2C7F_95B8_11D3_87D7_0050BAAD2602__INCLUDED_)
#define AFX_DLGDRAWLINE_H__DD7C2C7F_95B8_11D3_87D7_0050BAAD2602__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
// DlgDrawLine.h : header file
//

/////////////////////////////////////////////////////////////////////////////
// CDlgDrawLine dialog

class CDlgDrawLine : public CDialog
{
// Construction
public:
	CDlgDrawLine(CWnd* pParent = NULL);   // standard constructor

// Dialog Data
	//{{AFX_DATA(CDlgDrawLine)
	enum { IDD = IDD_DRAWLINE };
	UINT	m_LineX1;
	UINT	m_LineX2;
	UINT	m_LineY1;
	UINT	m_LineY2;
	UINT	m_LineG;
	UINT	m_LineB;
	UINT	m_LineR;
	UINT	m_LineWidth;
	UINT	m_OvalCx;
	UINT	m_OvalCy;
	UINT	m_OvalH;
	UINT	m_OvalW;
	int		m_Type;
	UINT	m_RectX1;
	UINT	m_RectX2;
	UINT	m_RectY1;
	UINT	m_RectY2;
	//}}AFX_DATA


// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CDlgDrawLine)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:

	// Generated message map functions
	//{{AFX_MSG(CDlgDrawLine)
		// NOTE: the ClassWizard will add member functions here
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_DLGDRAWLINE_H__DD7C2C7F_95B8_11D3_87D7_0050BAAD2602__INCLUDED_)
