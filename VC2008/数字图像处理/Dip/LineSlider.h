#if !defined(AFX_LINESLIDER_H__2FBB9A68_AB25_431F_9FDC_D00C7F7F0AC2__INCLUDED_)
#define AFX_LINESLIDER_H__2FBB9A68_AB25_431F_9FDC_D00C7F7F0AC2__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
// LineSlider.h : header file
//

/////////////////////////////////////////////////////////////////////////////
// CLineSlider window

class CLineSlider : public CSliderCtrl
{
// Construction
public:
	CLineSlider();

// Attributes
public:

// Operations
public:

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CLineSlider)
	//}}AFX_VIRTUAL

// Implementation
public:
	void SetPos(int nPos);
	virtual ~CLineSlider();

	// Generated message map functions
protected:
	BOOL	m_nMouseDown;
	//{{AFX_MSG(CLineSlider)
	afx_msg void OnPaint();
	afx_msg void OnLButtonDown(UINT nFlags, CPoint point);
	afx_msg void OnMouseMove(UINT nFlags, CPoint point);
	afx_msg void OnLButtonUp(UINT nFlags, CPoint point);
	//}}AFX_MSG

	DECLARE_MESSAGE_MAP()
};

/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_LINESLIDER_H__2FBB9A68_AB25_431F_9FDC_D00C7F7F0AC2__INCLUDED_)
