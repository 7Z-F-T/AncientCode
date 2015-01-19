// DlgDrawLine.cpp : implementation file
//

#include "stdafx.h"
#include "ImageProc.h"
#include "DlgDrawLine.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CDlgDrawLine dialog


CDlgDrawLine::CDlgDrawLine(CWnd* pParent /*=NULL*/)
	: CDialog(CDlgDrawLine::IDD, pParent)
{
	//{{AFX_DATA_INIT(CDlgDrawLine)
	m_LineX1 = 0;
	m_LineX2 = 0;
	m_LineY1 = 0;
	m_LineY2 = 0;
	m_LineG = 0;
	m_LineB = 0;
	m_LineR = 0;
	m_LineWidth = 0;
	m_OvalCx = 0;
	m_OvalCy = 0;
	m_OvalH = 0;
	m_OvalW = 0;
	m_Type = 0;
	m_RectX1 = 0;
	m_RectX2 = 0;
	m_RectY1 = 0;
	m_RectY2 = 0;
	//}}AFX_DATA_INIT
}


void CDlgDrawLine::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CDlgDrawLine)
	DDX_Text(pDX, IDC_LINE_X1, m_LineX1);
	DDV_MinMaxUInt(pDX, m_LineX1, 0, 1024);
	DDX_Text(pDX, IDC_LINE_X2, m_LineX2);
	DDV_MinMaxUInt(pDX, m_LineX2, 0, 1024);
	DDX_Text(pDX, IDC_LINE_Y1, m_LineY1);
	DDV_MinMaxUInt(pDX, m_LineY1, 0, 768);
	DDX_Text(pDX, IDC_LINE_Y2, m_LineY2);
	DDV_MinMaxUInt(pDX, m_LineY2, 0, 768);
	DDX_Text(pDX, IDC_LINEG, m_LineG);
	DDV_MinMaxUInt(pDX, m_LineG, 0, 255);
	DDX_Text(pDX, IDC_LINEB, m_LineB);
	DDV_MinMaxUInt(pDX, m_LineB, 0, 255);
	DDX_Text(pDX, IDC_LINER, m_LineR);
	DDX_Text(pDX, IDC_LINEW, m_LineWidth);
	DDV_MinMaxUInt(pDX, m_LineWidth, 1, 20);
	DDX_Text(pDX, IDC_OVAL_CX, m_OvalCx);
	DDV_MinMaxUInt(pDX, m_OvalCx, 0, 1024);
	DDX_Text(pDX, IDC_OVAL_CY, m_OvalCy);
	DDV_MinMaxUInt(pDX, m_OvalCy, 0, 768);
	DDX_Text(pDX, IDC_OVAL_H, m_OvalH);
	DDV_MinMaxUInt(pDX, m_OvalH, 0, 768);
	DDX_Text(pDX, IDC_OVAL_W, m_OvalW);
	DDV_MinMaxUInt(pDX, m_OvalW, 0, 1024);
	DDX_Radio(pDX, IDC_RADIO_LINE, m_Type);
	DDX_Text(pDX, IDC_RECT_X1, m_RectX1);
	DDV_MinMaxUInt(pDX, m_RectX1, 0, 1024);
	DDX_Text(pDX, IDC_RECT_X2, m_RectX2);
	DDV_MinMaxUInt(pDX, m_RectX2, 0, 1024);
	DDX_Text(pDX, IDC_RECT_Y1, m_RectY1);
	DDV_MinMaxUInt(pDX, m_RectY1, 0, 768);
	DDX_Text(pDX, IDC_RECT_Y2, m_RectY2);
	DDV_MinMaxUInt(pDX, m_RectY2, 0, 768);
	//}}AFX_DATA_MAP
}


BEGIN_MESSAGE_MAP(CDlgDrawLine, CDialog)
	//{{AFX_MSG_MAP(CDlgDrawLine)
		// NOTE: the ClassWizard will add message map macros here
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CDlgDrawLine message handlers
