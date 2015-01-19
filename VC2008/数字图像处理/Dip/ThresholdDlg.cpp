// ThresholdDlg.cpp : implementation file
//

#include "stdafx.h"
#include "Dip.h"
#include "DibBitOper.h"
#include "ThresholdDlg.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CThresholdDlg dialog


CThresholdDlg::CThresholdDlg(CWnd* pParent /*=NULL*/)
: CDipDialog( CThresholdDlg::IDD, pParent)
{
	//{{AFX_DATA_INIT(CThresholdDlg)
	m_nThreshold = 128;
	//}}AFX_DATA_INIT
}


void CThresholdDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CThresholdDlg)
	DDX_Text(pDX, IDC_THRESHOLD, m_nThreshold);
	DDV_MinMaxInt(pDX, m_nThreshold, 0, 255);
	DDX_Check(pDX, IDC_PREVIEW, m_bPreview);
	//}}AFX_DATA_MAP
}


BEGIN_MESSAGE_MAP(CThresholdDlg, CDipDialog)
	//{{AFX_MSG_MAP(CThresholdDlg)
	ON_EN_CHANGE(IDC_THRESHOLD, OnChangeThreshold)
	ON_BN_CLICKED(IDC_PREVIEW, OnPreview)
	ON_WM_HSCROLL()
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CThresholdDlg message handlers

BOOL CThresholdDlg::OnInitDialog() 
{
	CDipDialog::OnInitDialog();

	m_Slider.SubclassDlgItem ( IDC_SLIDER, this );
	m_Slider.SetRange(0, 255);
	m_Slider.SetPos(128);
	Change();
	return TRUE;  // return TRUE unless you set the focus to a control
	              // EXCEPTION: OCX Property Pages should return FALSE
}

void CThresholdDlg::OnChangeThreshold() 
{
	if ( m_bPreview )
		Change();
}

int CThresholdDlg::Change()
{
	CDibBitOper Oper;
	if ( m_bDlgReady )
		UpdateData();
	Oper.BiValue ( pDoc->m_pDib, pDoc->m_pPreDib, m_nThreshold );
	pDoc->UpdateAllViews(NULL);
	return 0;
}

void CThresholdDlg::OnPreview() 
{
	m_bPreview = !m_bPreview;
	pDoc->m_nPreview = m_bPreview;
	pDoc->UpdateAllViews(NULL);
}

void CThresholdDlg::OnHScroll(UINT nSBCode, UINT nPos, CScrollBar* pScrollBar) 
{
	CString strValue;
	int Pos;

    if (pScrollBar->GetDlgCtrlID() == IDC_SLIDER )
	{
		Pos = m_Slider.GetPos();
		strValue.Format("%d", Pos );
		SetDlgItemText ( IDC_THRESHOLD, strValue );
	}		
	CDipDialog::OnHScroll(nSBCode, nPos, pScrollBar);
}
