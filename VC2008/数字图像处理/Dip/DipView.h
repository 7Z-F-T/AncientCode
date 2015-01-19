// DipView.h : interface of the CDipView class
//
/////////////////////////////////////////////////////////////////////////////

#if !defined(AFX_DIPVIEW_H__325A73A3_CF6E_4736_8DCC_3CC1DF269589__INCLUDED_)
#define AFX_DIPVIEW_H__325A73A3_CF6E_4736_8DCC_3CC1DF269589__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000


class CDipView : public CScrollView
{
protected: // create from serialization only
	CDipView();
	DECLARE_DYNCREATE(CDipView)

// Attributes
public:
	CDipDoc* GetDocument();

// Operations
public:

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CDipView)
	public:
	virtual void OnDraw(CDC* pDC);  // overridden to draw this view
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);
	protected:
	virtual void OnInitialUpdate(); // called first time after construct
	virtual void OnUpdate(CView* pSender, LPARAM lHint, CObject* pHint);
	//}}AFX_VIRTUAL

// Implementation
public:
	virtual ~CDipView();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// Generated message map functions
protected:
	//{{AFX_MSG(CDipView)
	afx_msg void OnPointThreshold();
	afx_msg void OnEditUndo();
	afx_msg void OnUpdateEditUndo(CCmdUI* pCmdUI);
	afx_msg void OnPointNegative();
	afx_msg void OnPointLinear();
	afx_msg void OnHistogram();
	afx_msg void OnFieldAverage();
	afx_msg void OnFieldGaussian();
	afx_msg void OnFieldRoberts();
	afx_msg void OnFieldPrewitt();
	afx_msg void OnFieldSobel();
	afx_msg void OnFieldIsobel();
	afx_msg void OnFieldLaplacian();
	afx_msg void OnPointEqualization();
	afx_msg void OnAlgOffset();
	afx_msg void OnAlgSubstract();
	afx_msg void OnAlgAdd();
	afx_msg void OnGeoVertical();
	afx_msg void OnGeoHorizon();
	afx_msg void OnMorErotion();
	afx_msg void OnMorDilation();
	afx_msg void OnMorOpen();
	afx_msg void OnMorClose();
	afx_msg void OnMorEdge();
	afx_msg void OnMorSkelecton();
	afx_msg void OnGeoAffine();
	afx_msg void OnGeoPers();
	afx_msg void OnFieldSusan();
	afx_msg void OnMorThin();
	afx_msg void OnUpdateFieldSusan(CCmdUI* pCmdUI);
	afx_msg void OnFieldCanny();
	afx_msg void OnUpdateFieldCanny(CCmdUI* pCmdUI);
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

#ifndef _DEBUG  // debug version in DipView.cpp
inline CDipDoc* CDipView::GetDocument()
   { return (CDipDoc*)m_pDocument; }
#endif

/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_DIPVIEW_H__325A73A3_CF6E_4736_8DCC_3CC1DF269589__INCLUDED_)
