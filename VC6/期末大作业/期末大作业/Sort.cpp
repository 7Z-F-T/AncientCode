// Sort.cpp : implementation file
//

#include "stdafx.h"
#include "期末大作业.h"
#include "Sort.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CSort dialog


CSort::CSort(CWnd* pParent /*=NULL*/)
	: CDialog(CSort::IDD, pParent)
{
	//{{AFX_DATA_INIT(CSort)
	m_Sort = _T("");
	//}}AFX_DATA_INIT
}


void CSort::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CSort)
	DDX_Text(pDX, IDC_EDIT1, m_Sort);
	//}}AFX_DATA_MAP
}


BEGIN_MESSAGE_MAP(CSort, CDialog)
	//{{AFX_MSG_MAP(CSort)
		// NOTE: the ClassWizard will add message map macros here
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CSort message handlers
