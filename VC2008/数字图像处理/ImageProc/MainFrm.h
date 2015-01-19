// MainFrm.h : interface of the CMainFrame class
//
/////////////////////////////////////////////////////////////////////////////

#if !defined(AFX_MAINFRM_H__DD7C2C6A_95B8_11D3_87D7_0050BAAD2602__INCLUDED_)
#define AFX_MAINFRM_H__DD7C2C6A_95B8_11D3_87D7_0050BAAD2602__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

class CMainFrame : public CMDIFrameWnd
{
	DECLARE_DYNAMIC(CMainFrame)
public:
	CStatusBar  m_wndStatusBar;
	CMainFrame();
	int mnWidth;
	int mnHeight;
	CString msFn;
	BYTE *m_R;
	BYTE *m_G;
	BYTE *m_B;
	BYTE *m_Gray;
	BOOL mbOrig;

// Attributes
public:

// Operations
public:
	void UpdateData(int w,int h,BYTE *R,BYTE *G,BYTE *B,BYTE *Gray);
// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CMainFrame)
	public:
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);
	//}}AFX_VIRTUAL

// Implementation
public:
	virtual ~CMainFrame();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:  // control bar embedded members
	CToolBar    m_wndToolBar;

// Generated message map functions
protected:
	//{{AFX_MSG(CMainFrame)
	afx_msg int OnCreate(LPCREATESTRUCT lpCreateStruct);
	afx_msg void OnOpen();
	afx_msg void OnShowb();
	afx_msg void OnUpdateShowb(CCmdUI* pCmdUI);
	afx_msg void OnShowg();
	afx_msg void OnUpdateShowg(CCmdUI* pCmdUI);
	afx_msg void OnShowgray();
	afx_msg void OnUpdateShowgray(CCmdUI* pCmdUI);
	afx_msg void OnShowr();
	afx_msg void OnUpdateShowr(CCmdUI* pCmdUI);
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_MAINFRM_H__DD7C2C6A_95B8_11D3_87D7_0050BAAD2602__INCLUDED_)
