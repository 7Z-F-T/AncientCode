// InputNumbers.cpp : implementation file
//

#include "stdafx.h"
#include "期末大作业.h"
#include "InputNumbers.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CInputNumbers dialog


CInputNumbers::CInputNumbers(CWnd* pParent /*=NULL*/)
	: CDialog(CInputNumbers::IDD, pParent)
{
	//{{AFX_DATA_INIT(CInputNumbers)
	m_1 = 0.0f;
	m_2 = 0.0f;
	m_3 = 0.0f;
	m_4 = 0.0f;
	m_5 = 0.0f;
	//}}AFX_DATA_INIT
}


void CInputNumbers::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CInputNumbers)
	DDX_Text(pDX, IDC_EDIT1, m_1);
	DDX_Text(pDX, IDC_EDIT2, m_2);
	DDX_Text(pDX, IDC_EDIT3, m_3);
	DDX_Text(pDX, IDC_EDIT4, m_4);
	DDX_Text(pDX, IDC_EDIT5, m_5);
	//}}AFX_DATA_MAP
}


BEGIN_MESSAGE_MAP(CInputNumbers, CDialog)
	//{{AFX_MSG_MAP(CInputNumbers)
		// NOTE: the ClassWizard will add message map macros here
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CInputNumbers message handlers
