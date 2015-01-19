#if !defined(AFX_HISTSTATIC_H__8A790640_2DE4_4A2E_9AEE_6A5767662416__INCLUDED_)
#define AFX_HISTSTATIC_H__8A790640_2DE4_4A2E_9AEE_6A5767662416__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
// HistStatic.h : header file
//

/////////////////////////////////////////////////////////////////////////////
// CHistStatic window

class CHistStatic : public CStatic
{
// Construction
public:
	CHistStatic();

// Attributes
public:

// Operations
public:

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CHistStatic)
	protected:
	//}}AFX_VIRTUAL

// Implementation
public:
	void SetChannel( int chn );
	int m_nChannel;
	int m_dData[4][257];
	int m_nWidth, m_nHeight;
	virtual ~CHistStatic();

	// Generated message map functions
protected:
	//{{AFX_MSG(CHistStatic)
	afx_msg void OnPaint();
	afx_msg void OnMouseMove(UINT nFlags, CPoint point);
	//}}AFX_MSG

	DECLARE_MESSAGE_MAP()
};

/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_HISTSTATIC_H__8A790640_2DE4_4A2E_9AEE_6A5767662416__INCLUDED_)
