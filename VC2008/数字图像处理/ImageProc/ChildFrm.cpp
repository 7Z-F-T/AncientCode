// ChildFrm.cpp : implementation of the CChildFrame class
//

#include "stdafx.h"
#include "ImageProc.h"

#include "ChildFrm.h"
#include "ImageProcView.h"
#include "MainFrm.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CChildFrame

IMPLEMENT_DYNCREATE(CChildFrame, CMDIChildWnd)

BEGIN_MESSAGE_MAP(CChildFrame, CMDIChildWnd)
	//{{AFX_MSG_MAP(CChildFrame)
	ON_WM_CLOSE()
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CChildFrame construction/destruction

CChildFrame::CChildFrame()
{
	// TODO: add member initialization code here
}

CChildFrame::~CChildFrame()
{
}

BOOL CChildFrame::PreCreateWindow(CREATESTRUCT& cs)
{
	// TODO: Modify the Window class or styles here by modifying
	//  the CREATESTRUCT cs

	if( !CMDIChildWnd::PreCreateWindow(cs) )
		return FALSE;

	return TRUE;
}

/////////////////////////////////////////////////////////////////////////////
// CChildFrame diagnostics

#ifdef _DEBUG
void CChildFrame::AssertValid() const
{
	CMDIChildWnd::AssertValid();
}

void CChildFrame::Dump(CDumpContext& dc) const
{
	CMDIChildWnd::Dump(dc);
}

#endif //_DEBUG

/////////////////////////////////////////////////////////////////////////////
// CChildFrame message handlers
BOOL CChildFrame::LoadBitmap(CString csfn)
{
	BOOL rtn;
	CImageProcView *pView=(CImageProcView *)GetActiveView();

	rtn=pView->LoadBitmap(csfn);
	
	if(rtn){
		mnType=CHILD_ORIG;
		InvalidateRect(NULL,TRUE);
		UpdateWindow();
	}
	
	return rtn; 
}

BOOL CChildFrame::ShowBand(int ntype,int w,int h,BYTE *pData)
{
	BOOL rtn;
	CImageProcView *pView=(CImageProcView *)GetActiveView();

	rtn=pView->ShowBand(ntype,w,h,pData);
	
	if(rtn){
		mnType=ntype;
		InvalidateRect(NULL,TRUE);
		UpdateWindow();
	}
	
	return rtn; 
}

void CChildFrame::OnClose() 
{
	// TODO: Add your message handler code here and/or call default
	if(mnType==CHILD_ORIG){
		CMainFrame *pFrame=(CMainFrame*)AfxGetMainWnd( );
		pFrame->mbOrig=FALSE;
	}
	CMDIChildWnd::OnClose();
}

