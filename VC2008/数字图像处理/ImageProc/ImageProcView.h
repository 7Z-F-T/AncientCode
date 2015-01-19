// ImageProcView.h : interface of the CImageProcView class
//
/////////////////////////////////////////////////////////////////////////////

#if !defined(AFX_IMAGEPROCVIEW_H__DD7C2C70_95B8_11D3_87D7_0050BAAD2602__INCLUDED_)
#define AFX_IMAGEPROCVIEW_H__DD7C2C70_95B8_11D3_87D7_0050BAAD2602__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

class CImageProcDoc;

class CImageProcView : public CScrollView
{
protected: // create from serialization only
	CImageProcView();
	DECLARE_DYNCREATE(CImageProcView)

// Attributes
public:
	int mnWidth;
	int mnHeight;
	int mnType;
	int mnColorBits;
	HBITMAP mhBitmap;
	HPALETTE mhPalette;
	BYTE *mpData;

	CImageProcDoc* GetDocument();
	BOOL LoadBitmap(CString csfn);
	BOOL ShowBand(int ntype,int w,int h,BYTE *pData);

// Operations
public:

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CImageProcView)
	public:
	virtual void OnDraw(CDC* pDC);  // overridden to draw this view
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);
	protected:
	virtual void OnInitialUpdate(); // called first time after construct
	//}}AFX_VIRTUAL

// Implementation
public:
	virtual ~CImageProcView();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// Generated message map functions
protected:
	//{{AFX_MSG(CImageProcView)
	afx_msg void OnDrawLine();
	afx_msg void OnMouseMove(UINT nFlags, CPoint point);
	afx_msg void OnSave();
	afx_msg void OnUpdateSave(CCmdUI* pCmdUI);
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

#ifndef _DEBUG  // debug version in ImageProcView.cpp
inline CImageProcDoc* CImageProcView::GetDocument()
   { return (CImageProcDoc*)m_pDocument; }
#endif

/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_IMAGEPROCVIEW_H__DD7C2C70_95B8_11D3_87D7_0050BAAD2602__INCLUDED_)
