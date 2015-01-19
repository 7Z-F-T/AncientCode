// View.h : interface of the CZoomPerspectiveView class
//
/////////////////////////////////////////////////////////////////////////////

#if !defined(AFX_VIEW_H__626A24E3_E203_48C4_A0A9_F2137306057A__INCLUDED_)
#define AFX_VIEW_H__626A24E3_E203_48C4_A0A9_F2137306057A__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "ogl.h"
#include "texture.h"

class CZoomPerspectiveView : public CView
{
protected: // create from serialization only
	CZoomPerspectiveView();
	DECLARE_DYNCREATE(CZoomPerspectiveView)

// Attributes
public:
	CZoomPerspectiveDoc* GetDocument();

// Operations
public:

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CZoomPerspectiveView)
	public:
	virtual void OnDraw(CDC* pDC);  // overridden to draw this view
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);
	protected:
	virtual BOOL OnPreparePrinting(CPrintInfo* pInfo);
	virtual void OnBeginPrinting(CDC* pDC, CPrintInfo* pInfo);
	virtual void OnEndPrinting(CDC* pDC, CPrintInfo* pInfo);
	//}}AFX_VIRTUAL

// Implementation
public:
	virtual ~CZoomPerspectiveView();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// Generated message map functions
protected:
	//{{AFX_MSG(CZoomPerspectiveView)
	afx_msg int OnCreate(LPCREATESTRUCT lpCreateStruct);
	afx_msg void OnDestroy();
	afx_msg BOOL OnEraseBkgnd(CDC* pDC);
	afx_msg void OnLButtonDown(UINT nFlags, CPoint point);
	afx_msg void OnLButtonUp(UINT nFlags, CPoint point);
	afx_msg void OnMouseMove(UINT nFlags, CPoint point);
	afx_msg void OnRButtonDown(UINT nFlags, CPoint point);
	afx_msg void OnZoomAll();
	afx_msg void OnPerspectiveParameters();
	afx_msg void OnUpdatePerspectiveParameters(CCmdUI* pCmdUI);
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
protected:
   COGL  m_ogl       ;
   //
private:
   //
   // full rectangle :
   //
   UINT           m_list;
   void           CreateDisplayList();
   //
protected:
   //
   // Selection...
   //
   void		OnDrawSelection(CDC* pDC);
   CRect	   m_rcSelect  ;
   bool		m_bZooming  ;
   CTexture m_Texture   ;
};

#ifndef _DEBUG  // debug version in View.cpp
inline CZoomPerspectiveDoc* CZoomPerspectiveView::GetDocument()
   { return (CZoomPerspectiveDoc*)m_pDocument; }
#endif

/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_VIEW_H__626A24E3_E203_48C4_A0A9_F2137306057A__INCLUDED_)
