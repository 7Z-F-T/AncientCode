// MoveTo.cpp : implementation file
//

#include "stdafx.h"
#include "期末大作业.h"
#include "MoveTo.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CMoveTo dialog


CMoveTo::CMoveTo(CWnd* pParent /*=NULL*/)
	: CDialog(CMoveTo::IDD, pParent)
{
	//{{AFX_DATA_INIT(CMoveTo)
	m_RecordID = 0;
	//}}AFX_DATA_INIT
}


void CMoveTo::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CMoveTo)
	DDX_Text(pDX, IDC_EDIT1, m_RecordID);
	//}}AFX_DATA_MAP
}


BEGIN_MESSAGE_MAP(CMoveTo, CDialog)
	//{{AFX_MSG_MAP(CMoveTo)
		// NOTE: the ClassWizard will add message map macros here
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CMoveTo message handlers
