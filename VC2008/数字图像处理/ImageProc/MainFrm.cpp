// MainFrm.cpp : implementation of the CMainFrame class
//

#include "stdafx.h"
#include "ImageProc.h"

#include "MainFrm.h"
#include "ChildFrm.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CMainFrame

IMPLEMENT_DYNAMIC(CMainFrame, CMDIFrameWnd)

BEGIN_MESSAGE_MAP(CMainFrame, CMDIFrameWnd)
	//{{AFX_MSG_MAP(CMainFrame)
	ON_WM_CREATE()
	ON_COMMAND(ID_OPEN, OnOpen)
	ON_COMMAND(ID_SHOWB, OnShowb)
	ON_UPDATE_COMMAND_UI(ID_SHOWB, OnUpdateShowb)
	ON_COMMAND(ID_SHOWG, OnShowg)
	ON_UPDATE_COMMAND_UI(ID_SHOWG, OnUpdateShowg)
	ON_COMMAND(ID_SHOWGRAY, OnShowgray)
	ON_UPDATE_COMMAND_UI(ID_SHOWGRAY, OnUpdateShowgray)
	ON_COMMAND(ID_SHOWR, OnShowr)
	ON_UPDATE_COMMAND_UI(ID_SHOWR, OnUpdateShowr)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

static UINT indicators[] =
{
	ID_SEPARATOR,           // status line indicator
	//ID_INDICATOR_CAPS,
	//ID_INDICATOR_NUM,
	//ID_INDICATOR_SCRL,
    IDS_STATUS_WIDTH,
    IDS_STATUS_HEIGHT,
    IDS_STATUS_ROW,
    IDS_STATUS_COL,
    IDS_STATUS_R,
    IDS_STATUS_G,
    IDS_STATUS_B,
    IDS_STATUS_GRAY
};

/////////////////////////////////////////////////////////////////////////////
// CMainFrame construction/destruction

CMainFrame::CMainFrame()
{
	// TODO: add member initialization code here
	mnWidth=0;
	mnHeight=0;
	m_R=NULL;
	m_G=NULL;
	m_B=NULL;
	m_Gray=NULL;
	mbOrig=FALSE;
}

CMainFrame::~CMainFrame()
{
	if(m_R!=NULL)
		delete m_R;
	if(m_G!=NULL)
		delete m_G;
	if(m_B!=NULL)
		delete m_B;
	if(m_Gray!=NULL)
		delete m_Gray;
}

int CMainFrame::OnCreate(LPCREATESTRUCT lpCreateStruct)
{
	if (CMDIFrameWnd::OnCreate(lpCreateStruct) == -1)
		return -1;
	
	if (!m_wndToolBar.CreateEx(this, TBSTYLE_FLAT, WS_CHILD | WS_VISIBLE | CBRS_TOP
		| CBRS_GRIPPER | CBRS_TOOLTIPS | CBRS_FLYBY | CBRS_SIZE_DYNAMIC) ||
		!m_wndToolBar.LoadToolBar(IDR_MAINFRAME))
	{
		TRACE0("Failed to create toolbar\n");
		return -1;      // fail to create
	}

	if (!m_wndStatusBar.Create(this) ||
		!m_wndStatusBar.SetIndicators(indicators,
		  sizeof(indicators)/sizeof(UINT)))
	{
		TRACE0("Failed to create status bar\n");
		return -1;      // fail to create
	}

	// TODO: Delete these three lines if you don't want the toolbar to
	//  be dockable
	m_wndToolBar.EnableDocking(CBRS_ALIGN_ANY);
	EnableDocking(CBRS_ALIGN_ANY);
	DockControlBar(&m_wndToolBar);

	return 0;
}

BOOL CMainFrame::PreCreateWindow(CREATESTRUCT& cs)
{
	if( !CMDIFrameWnd::PreCreateWindow(cs) )
		return FALSE;
	// TODO: Modify the Window class or styles here by modifying
	//  the CREATESTRUCT cs

	cs.style = WS_OVERLAPPED | WS_CAPTION | FWS_ADDTOTITLE
		| WS_THICKFRAME | WS_SYSMENU | WS_MINIMIZEBOX | WS_MAXIMIZEBOX | WS_MAXIMIZE;

	return TRUE;
}

/////////////////////////////////////////////////////////////////////////////
// CMainFrame diagnostics

#ifdef _DEBUG
void CMainFrame::AssertValid() const
{
	CMDIFrameWnd::AssertValid();
}

void CMainFrame::Dump(CDumpContext& dc) const
{
	CMDIFrameWnd::Dump(dc);
}

#endif //_DEBUG

/////////////////////////////////////////////////////////////////////////////
// CMainFrame message handlers

void CMainFrame::OnOpen() 
{
	// TODO: Add your command handler code here
	CString csfn;
	CChildFrame* pChildWnd;
	char	strfilter[]="Bitmap Images(*.bmp)|*.bmp|All Files(*.*)|*.*||";	

	CFileDialog DlgOpenFile(TRUE,NULL,NULL,OFN_HIDEREADONLY | OFN_OVERWRITEPROMPT|OFN_PATHMUSTEXIST,strfilter);
	DlgOpenFile.m_ofn.lpstrTitle="打开图象文件";
	if(DlgOpenFile.DoModal()!=IDOK)
		return;

	csfn=DlgOpenFile.GetFileName( );
	msFn=csfn;

	SendMessage(WM_COMMAND,ID_FILE_NEW,0);
	SendMessage(WM_COMMAND,ID_WINDOW_CASCADE,0);

	pChildWnd=(CChildFrame*)MDIGetActive();
	mbOrig=pChildWnd->LoadBitmap(csfn);
}

void CMainFrame::UpdateData(int w,int h,BYTE *R,BYTE *G,BYTE *B,BYTE *Gray)
{
	if(m_R!=NULL)
		delete m_R;
	if(m_G!=NULL)
		delete m_G;
	if(m_B!=NULL)
		delete m_B;
	if(m_Gray!=NULL)
		delete m_Gray;

	mnWidth=w;
	mnHeight=h;
	m_R=R;
	m_G=G;
	m_B=B;
	m_Gray=Gray;
}

void CMainFrame::OnShowr() 
{
	// TODO: Add your command handler code here
	CChildFrame* pChildWnd;
	SendMessage(WM_COMMAND,ID_FILE_NEW,0);
	SendMessage(WM_COMMAND,ID_WINDOW_CASCADE,0);

	pChildWnd=(CChildFrame*)MDIGetActive();
	mbOrig=pChildWnd->ShowBand(CHILD_R,mnWidth,mnHeight,m_R);
}

void CMainFrame::OnUpdateShowr(CCmdUI* pCmdUI) 
{
	// TODO: Add your command update UI handler code here
	pCmdUI->Enable(mbOrig);		
}

void CMainFrame::OnShowg() 
{
	// TODO: Add your command handler code here
	CChildFrame* pChildWnd;
	SendMessage(WM_COMMAND,ID_FILE_NEW,0);
	SendMessage(WM_COMMAND,ID_WINDOW_CASCADE,0);

	pChildWnd=(CChildFrame*)MDIGetActive();
	mbOrig=pChildWnd->ShowBand(CHILD_G,mnWidth,mnHeight,m_G);
}

void CMainFrame::OnUpdateShowg(CCmdUI* pCmdUI) 
{
	// TODO: Add your command update UI handler code here
	pCmdUI->Enable(mbOrig);	
}

void CMainFrame::OnShowb() 
{
	// TODO: Add your command handler code here
	CChildFrame* pChildWnd;
	SendMessage(WM_COMMAND,ID_FILE_NEW,0);
	SendMessage(WM_COMMAND,ID_WINDOW_CASCADE,0);

	pChildWnd=(CChildFrame*)MDIGetActive();
	mbOrig=pChildWnd->ShowBand(CHILD_B,mnWidth,mnHeight,m_B);
}

void CMainFrame::OnUpdateShowb(CCmdUI* pCmdUI) 
{
	// TODO: Add your command update UI handler code here
	pCmdUI->Enable(mbOrig);
	
}

void CMainFrame::OnShowgray() 
{
	// TODO: Add your command handler code here
	CChildFrame* pChildWnd;
	SendMessage(WM_COMMAND,ID_FILE_NEW,0);
	SendMessage(WM_COMMAND,ID_WINDOW_CASCADE,0);

	pChildWnd=(CChildFrame*)MDIGetActive();
	mbOrig=pChildWnd->ShowBand(CHILD_GRAY,mnWidth,mnHeight,m_Gray);
	
}

void CMainFrame::OnUpdateShowgray(CCmdUI* pCmdUI) 
{
	// TODO: Add your command update UI handler code here
	pCmdUI->Enable(mbOrig);
}

