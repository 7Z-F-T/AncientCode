// LinearDlg.cpp : implementation file
//

#include "stdafx.h"
#include "Dip.h"
#include "LinearDlg.h"
#include "DibBitOper.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CLinearDlg dialog


CLinearDlg::CLinearDlg(CWnd* pParent /*=NULL*/)
	: CDipDialog(CLinearDlg::IDD, pParent)
{
	//{{AFX_DATA_INIT(CLinearDlg)
	m_nBright = 0;
	m_fContrast = 1.0f;
	//}}AFX_DATA_INIT
}


void CLinearDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CLinearDlg)
	DDX_Text(pDX, IDC_BRIGHTNESS, m_nBright);
	DDV_MinMaxInt(pDX, m_nBright, -128, 127);
	DDX_Text(pDX, IDC_CONTRAST, m_fContrast);
	DDV_MinMaxFloat(pDX, m_fContrast, 0.f, 100.f);
	//}}AFX_DATA_MAP
}


BEGIN_MESSAGE_MAP(CLinearDlg, CDipDialog)
	//{{AFX_MSG_MAP(CLinearDlg)
	ON_EN_CHANGE(IDC_CONTRAST, OnChangeContrast)
	ON_EN_CHANGE(IDC_BRIGHTNESS, OnChangeBrightness)
	ON_BN_CLICKED(IDC_PREVIEW, OnPreview)
	ON_WM_HSCROLL()
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CLinearDlg message handlers

BOOL CLinearDlg::OnInitDialog() 
{
	CDipDialog::OnInitDialog();
	
	m_Slider1.SubclassDlgItem ( IDC_SLIDER1, this );
	m_Slider1.SetRange(0, 255);
	m_Slider1.SetPos(128);
	m_Slider2.SubclassDlgItem ( IDC_SLIDER2, this );
	m_Slider2.SetRange(0, 200);
	m_Slider2.SetPos(100);

	m_fContrast = 1;
	UpdateData(FALSE);
	
	return TRUE;  // return TRUE unless you set the focus to a control
	              // EXCEPTION: OCX Property Pages should return FALSE
}

int CLinearDlg::Change()
{
	CDibBitOper Oper;
	if ( m_bDlgReady )
		UpdateData();
	Oper.Linear( pDoc->m_pDib, pDoc->m_pPreDib, m_fContrast, m_nBright );
	pDoc->UpdateAllViews(NULL);
	return 0;
}

void CLinearDlg::OnChangeContrast() 
{
	if ( m_bPreview )
		Change();
}

void CLinearDlg::OnChangeBrightness() 
{
	if ( m_bPreview )
		Change();
}

void CLinearDlg::OnPreview() 
{
	m_bPreview = !m_bPreview;
	pDoc->m_nPreview = m_bPreview;
	pDoc->UpdateAllViews(NULL);
}

void CLinearDlg::OnHScroll(UINT nSBCode, UINT nPos, CScrollBar* pScrollBar) 
{
	CString strValue;
	int Pos;

    switch (pScrollBar->GetDlgCtrlID())
	{
	case IDC_SLIDER1:
		Pos = m_Slider1.GetPos() - 128;
		strValue.Format("%d", Pos );
		SetDlgItemText ( IDC_BRIGHTNESS, strValue );
		break;

	case IDC_SLIDER2:
		Pos = m_Slider2.GetPos();
		strValue.Format("%2.2f", (float)Pos / 100);
		SetDlgItemText ( IDC_CONTRAST, strValue );
		break;
	}		

	CDipDialog::OnHScroll(nSBCode, nPos, pScrollBar);
}
