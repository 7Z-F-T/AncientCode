// 期末大作业View.h : interface of the CMyView class
//
/////////////////////////////////////////////////////////////////////////////

#if !defined(AFX_VIEW_H__926D87B9_06DF_41D3_9458_9DE187FC079E__INCLUDED_)
#define AFX_VIEW_H__926D87B9_06DF_41D3_9458_9DE187FC079E__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

class CMySet;

class CWMPPlayer4; 	// 前示声明

class CMyView : public CRecordView
{
protected: // create from serialization only
	CMyView();
	DECLARE_DYNCREATE(CMyView)

public:
	//{{AFX_DATA(CMyView)
	enum { IDD = IDD_MY_FORM };
	CSliderCtrl	m_slider;
	CSpinButtonCtrl	m_spin;
	CMySet* m_pSet;
	CMenu m_PopMenu;
	CMenu* m_pPop;
	//}}AFX_DATA

// Attributes
public:
	CMyDoc* GetDocument();
	CWMPPlayer4		* m_Video;		// 用来播放视频
	CWMPPlayer4		* m_Music;		// 用来播放音频
	CString			m_strVideo;		// 视频文件名
	CString			m_strMusic;		// 音频文件名
	

// Operations
public:

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CMyView)
	public:
	virtual CRecordset* OnGetRecordset();
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV support
	virtual void OnInitialUpdate(); // called first time after construct
	//}}AFX_VIRTUAL

// Implementation
public:
	BOOL IsPaused;
	void InitSlider();
	void DoPaint();
	virtual ~CMyView();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// Generated message map functions
protected:
	//{{AFX_MSG(CMyView)
	afx_msg void OnRecordAdd();
	afx_msg void OnRecordDelete();
	afx_msg void OnRecordUpdate();
	afx_msg void OnRecordMoveto();
	afx_msg void OnRecordSort();
	afx_msg void OnRecordSearch();
	afx_msg void OnCalculate();
	afx_msg void OnPaint();
	afx_msg void OnBigger();
	afx_msg void OnSmaller();
	afx_msg void OnRButtonUp(UINT nFlags, CPoint point);
	afx_msg int OnCreate(LPCREATESTRUCT lpCreateStruct);
	afx_msg void OnOperOpenv();
	afx_msg void OnOperPlayv();
	afx_msg void OnOperStopv();
	afx_msg void OnSize(UINT nType, int cx, int cy);
	afx_msg void OnShowWindow(BOOL bShow, UINT nStatus);
	afx_msg void OnVScroll(UINT nSBCode, UINT nPos, CScrollBar* pScrollBar);
	afx_msg void OnOperPause();
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

#ifndef _DEBUG  // debug version in 期末大作业View.cpp
inline CMyDoc* CMyView::GetDocument()
   { return (CMyDoc*)m_pDocument; }
#endif

/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_VIEW_H__926D87B9_06DF_41D3_9458_9DE187FC079E__INCLUDED_)
