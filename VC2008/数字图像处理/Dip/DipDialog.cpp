// DipDialog.cpp : implementation file
//

#include "stdafx.h"
#include "Dip.h"
#include "DipDialog.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CDipDialog dialog


CDipDialog::CDipDialog(UINT nIDTemplate, CWnd* pParent /*=NULL*/)
	: CDialog( nIDTemplate, pParent)
{
	//{{AFX_DATA_INIT(CDipDialog)
		// NOTE: the ClassWizard will add member initialization here
	//}}AFX_DATA_INIT
	pDoc = NULL;
	m_bPreview = 1;
	m_bDlgReady = 0;
}


void CDipDialog::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CDipDialog)
		// NOTE: the ClassWizard will add DDX and DDV calls here
	//}}AFX_DATA_MAP
}


BEGIN_MESSAGE_MAP(CDipDialog, CDialog)
	//{{AFX_MSG_MAP(CDipDialog)
	ON_WM_DESTROY()
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CDipDialog message handlers

BOOL CDipDialog::OnInitDialog() 
{
	CDialog::OnInitDialog();
	
	CButton *pBtn = (CButton*) GetDlgItem ( IDC_PREVIEW );
	if ( pBtn != NULL )
		pBtn->SetCheck(1);

	pDoc->m_nPreview = 1;
	m_bDlgReady = 1;

	return TRUE;  // return TRUE unless you set the focus to a control
	              // EXCEPTION: OCX Property Pages should return FALSE
}

void CDipDialog::OnDestroy() 
{
	CDialog::OnDestroy();
	m_bDlgReady = 0;	
}
