// ShowResult.cpp : implementation file
//

#include "stdafx.h"
#include "期末大作业.h"
#include "ShowResult.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CShowResult dialog


CShowResult::CShowResult(CWnd* pParent /*=NULL*/)
	: CDialog(CShowResult::IDD, pParent)
{
	//{{AFX_DATA_INIT(CShowResult)
	m_show1 = 0.0f;
	m_show2 = 0.0f;
	m_show3 = 0.0f;
	m_show4 = 0.0f;
	m_show5 = 0.0f;
	m_show6 = 0.0f;
	//}}AFX_DATA_INIT
}


void CShowResult::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CShowResult)
	DDX_Text(pDX, IDC_EDIT1, m_show1);
	DDX_Text(pDX, IDC_EDIT2, m_show2);
	DDX_Text(pDX, IDC_EDIT3, m_show3);
	DDX_Text(pDX, IDC_EDIT4, m_show4);
	DDX_Text(pDX, IDC_EDIT5, m_show5);
	DDX_Text(pDX, IDC_EDIT6, m_show6);
	//}}AFX_DATA_MAP
}


BEGIN_MESSAGE_MAP(CShowResult, CDialog)
	//{{AFX_MSG_MAP(CShowResult)
		// NOTE: the ClassWizard will add message map macros here
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CShowResult message handlers
