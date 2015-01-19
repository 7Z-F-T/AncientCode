// 9_13Dlg.cpp : implementation file
//

#include "stdafx.h"
#include "9_13.h"
#include "9_13Dlg.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CAboutDlg dialog used for App About

class CAboutDlg : public CDialog
{
public:
	CAboutDlg();

// Dialog Data
	//{{AFX_DATA(CAboutDlg)
	enum { IDD = IDD_ABOUTBOX };
	//}}AFX_DATA

	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CAboutDlg)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:
	//{{AFX_MSG(CAboutDlg)
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

CAboutDlg::CAboutDlg() : CDialog(CAboutDlg::IDD)
{
	//{{AFX_DATA_INIT(CAboutDlg)
	//}}AFX_DATA_INIT
}

void CAboutDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CAboutDlg)
	//}}AFX_DATA_MAP
}

BEGIN_MESSAGE_MAP(CAboutDlg, CDialog)
	//{{AFX_MSG_MAP(CAboutDlg)
		// No message handlers
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CMy9_13Dlg dialog

CMy9_13Dlg::CMy9_13Dlg(CWnd* pParent /*=NULL*/)
	: CDialog(CMy9_13Dlg::IDD, pParent)
{
	//{{AFX_DATA_INIT(CMy9_13Dlg)
	//}}AFX_DATA_INIT
	// Note that LoadIcon does not require a subsequent DestroyIcon in Win32
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
}

void CMy9_13Dlg::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CMy9_13Dlg)
	DDX_Control(pDX, IDC_SCROLLBAR3, m_blue_scrollbar);
	DDX_Control(pDX, IDC_SCROLLBAR2, m_green_scrollbar);
	DDX_Control(pDX, IDC_SCROLLBAR1, m_red_scrollbar);
	DDX_Control(pDX, IDC_EDIT3, m_blue_edit);
	DDX_Control(pDX, IDC_EDIT2, m_green_edit);
	DDX_Control(pDX, IDC_EDIT1, m_red_edit);
	//}}AFX_DATA_MAP
}

BEGIN_MESSAGE_MAP(CMy9_13Dlg, CDialog)
	//{{AFX_MSG_MAP(CMy9_13Dlg)
	ON_WM_SYSCOMMAND()
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	ON_WM_HSCROLL()
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CMy9_13Dlg message handlers

BOOL CMy9_13Dlg::OnInitDialog()
{
	CDialog::OnInitDialog();

	// Add "About..." menu item to system menu.

	// IDM_ABOUTBOX must be in the system command range.
	ASSERT((IDM_ABOUTBOX & 0xFFF0) == IDM_ABOUTBOX);
	ASSERT(IDM_ABOUTBOX < 0xF000);

	CMenu* pSysMenu = GetSystemMenu(FALSE);
	if (pSysMenu != NULL)
	{
		CString strAboutMenu;
		strAboutMenu.LoadString(IDS_ABOUTBOX);
		if (!strAboutMenu.IsEmpty())
		{
			pSysMenu->AppendMenu(MF_SEPARATOR);
			pSysMenu->AppendMenu(MF_STRING, IDM_ABOUTBOX, strAboutMenu);
		}
	}

	// Set the icon for this dialog.  The framework does this automatically
	//  when the application's main window is not a dialog
	SetIcon(m_hIcon, TRUE);			// Set big icon
	SetIcon(m_hIcon, FALSE);		// Set small icon
	
	// TODO: Add extra initialization here
	m_red_scrollbar.SetScrollRange(0,255,FALSE);
	m_green_scrollbar.SetScrollRange(0,255,FALSE);
	m_blue_scrollbar.SetScrollRange(0,255,FALSE);
	m_red_scrollbar.SetScrollPos(0);
	m_green_scrollbar.SetScrollPos(0);
	m_blue_scrollbar.SetScrollPos(0);
	char sPos[10];
	itoa(m_red_scrollbar.GetScrollPos(),sPos,10);
	m_red_edit.SetWindowText(sPos);
	itoa(m_green_scrollbar.GetScrollPos(),sPos,10);
	m_green_edit.SetWindowText(sPos);
	itoa(m_blue_scrollbar.GetScrollPos(),sPos,10);
	m_blue_edit.SetWindowText(sPos);
	UpdateData(FALSE);
	
	


	return TRUE;  // return TRUE  unless you set the focus to a control
}

void CMy9_13Dlg::OnSysCommand(UINT nID, LPARAM lParam)
{
	if ((nID & 0xFFF0) == IDM_ABOUTBOX)
	{
		CAboutDlg dlgAbout;
		dlgAbout.DoModal();
	}
	else
	{
		CDialog::OnSysCommand(nID, lParam);
	}
}

// If you add a minimize button to your dialog, you will need the code below
//  to draw the icon.  For MFC applications using the document/view model,
//  this is automatically done for you by the framework.

void CMy9_13Dlg::OnPaint() 
{
	if (IsIconic())
	{
		CPaintDC dc(this); // device context for painting

		SendMessage(WM_ICONERASEBKGND, (WPARAM) dc.GetSafeHdc(), 0);

		// Center icon in client rectangle
		int cxIcon = GetSystemMetrics(SM_CXICON);
		int cyIcon = GetSystemMetrics(SM_CYICON);
		CRect rect;
		GetClientRect(&rect);
		int x = (rect.Width() - cxIcon + 1) / 2;
		int y = (rect.Height() - cyIcon + 1) / 2;

		// Draw the icon
		dc.DrawIcon(x, y, m_hIcon);
	}
	else
	{
		CDialog::OnPaint();
	}
	CDC *pDC=GetDC();
	CBrush cbrush;
	CPen cpen;
	cbrush.CreateSolidBrush(RGB(0,0,0));
    cpen.CreatePen(PS_SOLID,1,RGB(0,0,0));
	pDC->SelectObject(&cbrush);
	pDC->SelectObject(&cpen);
	pDC->Ellipse(70,10,400,130);
	ReleaseDC(pDC);
	
}

// The system calls this to obtain the cursor to display while the user drags
//  the minimized window.
HCURSOR CMy9_13Dlg::OnQueryDragIcon()
{
	return (HCURSOR) m_hIcon;
}

void CMy9_13Dlg::OnHScroll(UINT nSBCode, UINT nPos, CScrollBar* pScrollBar) 
{
	// TODO: Add your message handler code here and/or call default
	
	int iRedNowPos,iGreenNowPos,iBlueNowPos;
	char sPos[10];
	iRedNowPos=m_red_scrollbar.GetScrollPos();
	iGreenNowPos=m_green_scrollbar.GetScrollPos();
	iBlueNowPos=m_blue_scrollbar.GetScrollPos();
	if(pScrollBar==&m_red_scrollbar)
	{
		switch(nSBCode)
		{
		case SB_THUMBTRACK:
			m_red_scrollbar.SetScrollPos(nPos);
			iRedNowPos=nPos;
			itoa(iRedNowPos,sPos,10);
			m_red_edit.SetWindowText(sPos);
			break;

		case SB_LINELEFT:
			iRedNowPos=m_red_scrollbar.GetScrollPos();
			iRedNowPos=iRedNowPos-1;
			if(iRedNowPos<0)
				iRedNowPos=0;
			m_red_scrollbar.SetScrollPos(iRedNowPos);
			itoa(iRedNowPos,sPos,10);
			m_red_edit.SetWindowText(sPos);
			break;

		case SB_LINERIGHT:
			iRedNowPos=m_red_scrollbar.GetScrollPos();
			iRedNowPos=iRedNowPos+1;
			if(iRedNowPos>255)
				iRedNowPos=255;
			m_red_scrollbar.SetScrollPos(iRedNowPos);
			itoa(iRedNowPos,sPos,10);
			m_red_edit.SetWindowText(sPos);
			break;

		case SB_PAGELEFT:
			iRedNowPos=m_red_scrollbar.GetScrollPos();
			iRedNowPos=iRedNowPos-10;
			if(iRedNowPos<0)
				iRedNowPos=0;
			m_red_scrollbar.SetScrollPos(iRedNowPos);
			itoa(iRedNowPos,sPos,10);
			m_red_edit.SetWindowText(sPos);
			break;

		case SB_PAGERIGHT:
			iRedNowPos=m_red_scrollbar.GetScrollPos();
			iRedNowPos=iRedNowPos+10;
			if(iRedNowPos>255)
				iRedNowPos=255;
			m_red_scrollbar.SetScrollPos(iRedNowPos);
			itoa(iRedNowPos,sPos,10);
			m_red_edit.SetWindowText(sPos);
			break;
			
			


			
		}
	}
	if(pScrollBar==&m_green_scrollbar)
	{
		switch(nSBCode)
		{
		case SB_THUMBTRACK:
			m_green_scrollbar.SetScrollPos(nPos);
			iGreenNowPos=nPos;
			itoa(iGreenNowPos,sPos,10);
			m_green_edit.SetWindowText(sPos);
			break;
			
		case SB_LINELEFT:
			iGreenNowPos=m_green_scrollbar.GetScrollPos();
			iGreenNowPos=iGreenNowPos-1;
			if(iGreenNowPos<0)
				iGreenNowPos=0;
			m_green_scrollbar.SetScrollPos(iGreenNowPos);
			itoa(iGreenNowPos,sPos,10);
			m_green_edit.SetWindowText(sPos);
			break;
			
		case SB_LINERIGHT:
			iGreenNowPos=m_green_scrollbar.GetScrollPos();
			iGreenNowPos=iGreenNowPos+1;
			if(iGreenNowPos>255)
				iGreenNowPos=255;
			m_green_scrollbar.SetScrollPos(iGreenNowPos);
			itoa(iGreenNowPos,sPos,10);
			m_green_edit.SetWindowText(sPos);
			break;
			
		case SB_PAGELEFT:
			iGreenNowPos=m_green_scrollbar.GetScrollPos();
			iGreenNowPos=iGreenNowPos-10;
			if(iGreenNowPos<0)
				iGreenNowPos=0;
			m_green_scrollbar.SetScrollPos(iGreenNowPos);
			itoa(iGreenNowPos,sPos,10);
			m_green_edit.SetWindowText(sPos);
			break;
			
		case SB_PAGERIGHT:
			iGreenNowPos=m_green_scrollbar.GetScrollPos();
			iGreenNowPos=iGreenNowPos+10;
			if(iGreenNowPos>255)
				iGreenNowPos=255;
			m_green_scrollbar.SetScrollPos(iGreenNowPos);
			itoa(iGreenNowPos,sPos,10);
			m_green_edit.SetWindowText(sPos);
			break;
			
			
			
			
			
		}
	}
	if(pScrollBar==&m_blue_scrollbar)
	{
		switch(nSBCode)
		{
		case SB_THUMBTRACK:
			m_blue_scrollbar.SetScrollPos(nPos);
			iBlueNowPos=nPos;
			itoa(iBlueNowPos,sPos,10);
			m_blue_edit.SetWindowText(sPos);
			break;
			
		case SB_LINELEFT:
			iBlueNowPos=m_blue_scrollbar.GetScrollPos();
			iBlueNowPos=iBlueNowPos-1;
			if(iBlueNowPos<0)
				iBlueNowPos=0;
			m_blue_scrollbar.SetScrollPos(iBlueNowPos);
			itoa(iBlueNowPos,sPos,10);
			m_blue_edit.SetWindowText(sPos);
			break;
			
		case SB_LINERIGHT:
			iBlueNowPos=m_blue_scrollbar.GetScrollPos();
			iBlueNowPos=iBlueNowPos+1;
			if(iBlueNowPos>255)
				iBlueNowPos=255;
			m_blue_scrollbar.SetScrollPos(iBlueNowPos);
			itoa(iBlueNowPos,sPos,10);
			m_blue_edit.SetWindowText(sPos);
			break;
			
		case SB_PAGELEFT:
			iBlueNowPos=m_blue_scrollbar.GetScrollPos();
			iBlueNowPos=iBlueNowPos-10;
			if(iBlueNowPos<0)
				iBlueNowPos=0;
			m_blue_scrollbar.SetScrollPos(iBlueNowPos);
			itoa(iBlueNowPos,sPos,10);
			m_blue_edit.SetWindowText(sPos);
			break;
			
		case SB_PAGERIGHT:
			iBlueNowPos=m_blue_scrollbar.GetScrollPos();
			iBlueNowPos=iBlueNowPos+10;
			if(iBlueNowPos>255)
				iBlueNowPos=255;
			m_blue_scrollbar.SetScrollPos(iBlueNowPos);
			itoa(iBlueNowPos,sPos,10);
			m_blue_edit.SetWindowText(sPos);
			break;
			
			

			
			
			
		}
	}


	CDC *pDC=GetDC();
	CBrush cbrush;
	CPen cpen;
	cbrush.CreateSolidBrush(RGB(iRedNowPos,iGreenNowPos,iBlueNowPos));
    cpen.CreatePen(PS_SOLID,1,RGB(iRedNowPos,iGreenNowPos,iBlueNowPos));
	pDC->SelectObject(&cbrush);
	pDC->SelectObject(&cpen);
	pDC->Ellipse(70,10,400,130);
	ReleaseDC(pDC);
}
