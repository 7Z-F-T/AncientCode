// PersDlg.cpp : implementation file
//

#include "stdafx.h"
#include "Dip.h"
#include "PersDlg.h"
#include "DibGeoOper.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CPersDlg dialog


CPersDlg::CPersDlg(CWnd* pParent /*=NULL*/)
	: CDipDialog(CPersDlg::IDD, pParent)
{
	//{{AFX_DATA_INIT(CPersDlg)
	m_dDistance = 1.0;
	m_dFocus = 1.0;
	m_dPhai = 0;
	m_dThita = 0.0;
	//}}AFX_DATA_INIT
}


void CPersDlg::DoDataExchange(CDataExchange* pDX)
{
	CDipDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CPersDlg)
	DDX_Text(pDX, IDC_DISTANCE, m_dDistance);
	DDX_Text(pDX, IDC_FOCUS, m_dFocus);
	DDX_Text(pDX, IDC_PHAI, m_dPhai);
	DDX_Text(pDX, IDC_THITA, m_dThita);
	//}}AFX_DATA_MAP
}


BEGIN_MESSAGE_MAP(CPersDlg, CDipDialog)
	//{{AFX_MSG_MAP(CPersDlg)
	ON_WM_HSCROLL()
	ON_EN_CHANGE(IDC_DISTANCE, OnChangeDistance)
	ON_EN_CHANGE(IDC_THITA, OnChangeThita)
	ON_EN_CHANGE(IDC_PHAI, OnChangePhai)
	ON_EN_CHANGE(IDC_FOCUS, OnChangeFocus)
	ON_BN_CLICKED(IDC_PREVIEW, OnPreview)
	ON_BN_CLICKED(IDC_RESET, OnReset)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CPersDlg message handlers

BOOL CPersDlg::OnInitDialog() 
{
	CDipDialog::OnInitDialog();

	m_Slider1.SubclassDlgItem ( IDC_SLIDER1, this );
	m_Slider2.SubclassDlgItem ( IDC_SLIDER2, this );
	m_Slider3.SubclassDlgItem ( IDC_SLIDER3, this );
	m_Slider4.SubclassDlgItem ( IDC_SLIDER4, this );
	m_Slider1.SetRange(1, 1600);
	m_Slider1.SetPos(100);
	SetDlgItemText(IDC_DISTANCE, "1.00" );
	m_Slider2.SetRange(0, 180);
	m_Slider2.SetPos(90);
	SetDlgItemText(IDC_THITA, "0" );
	m_Slider3.SetRange(0, 360);
	m_Slider3.SetPos(180);
	SetDlgItemText(IDC_PHAI, "0" );
	m_Slider4.SetRange(1, 1600);
	m_Slider4.SetPos(100);
	SetDlgItemText(IDC_FOCUS, "1.00" );
	
	return TRUE;  // return TRUE unless you set the focus to a control
	              // EXCEPTION: OCX Property Pages should return FALSE
}

int CPersDlg::Change()
{
	SetCursor(LoadCursor(0, IDC_WAIT) );
	CDibGeoOper Oper;
	if ( !m_bDlgReady )
		return 1;
	UpdateData();
	Oper.Perspective( pDoc->m_pDib, pDoc->m_pPreDib, m_dDistance, 
		(m_dThita)*(float)3.1415926/180/300, (m_dPhai)*(float)3.1415926/180/300, m_dFocus );
	pDoc->UpdateAllViews(NULL);
	SetCursor(LoadCursor(0, IDC_ARROW) );
	return 1;
}

void CPersDlg::OnHScroll(UINT nSBCode, UINT nPos, CScrollBar* pScrollBar) 
{
	CString strValue;
	int Pos;

    switch (pScrollBar->GetDlgCtrlID())
	{
	case IDC_SLIDER1: 
		Pos = m_Slider1.GetPos();
		strValue.Format("%2.2f", (float) Pos / 100);
		SetDlgItemText ( IDC_DISTANCE, strValue );
		break;
	case IDC_SLIDER2:
		Pos = m_Slider2.GetPos();
		strValue.Format("%d", Pos - 90);
		SetDlgItemText ( IDC_THITA, strValue );
		break;
	case IDC_SLIDER3:
		Pos = m_Slider3.GetPos();
		strValue.Format("%d", Pos - 180 );
		SetDlgItemText ( IDC_PHAI, strValue );
		break;
	case IDC_SLIDER4:
		Pos = m_Slider4.GetPos();
		strValue.Format("%2.2f", (float)Pos / 100 );
		SetDlgItemText ( IDC_FOCUS, strValue );
		break;
	}
	
	CDipDialog::OnHScroll(nSBCode, nPos, pScrollBar);
}

void CPersDlg::OnChangeDistance() 
{
	if ( m_bPreview )
		Change();
}

void CPersDlg::OnChangeThita() 
{
	if ( m_bPreview )
		Change();
}

void CPersDlg::OnChangePhai() 
{
	if ( m_bPreview )
		Change();
}

void CPersDlg::OnChangeFocus() 
{
	if ( m_bPreview )
		Change();
}

void CPersDlg::OnPreview() 
{
	m_bPreview = !m_bPreview;
	pDoc->m_nPreview = m_bPreview;
	pDoc->UpdateAllViews(NULL);
}

void CPersDlg::OnReset() 
{
	m_Slider1.SetPos(100);
	SetDlgItemText(IDC_DISTANCE, "1.00" );

	m_Slider2.SetPos(90);
	SetDlgItemText(IDC_THITA, "0" );

	m_Slider3.SetPos(180);
	SetDlgItemText(IDC_PHAI, "0" );

	m_Slider4.SetPos(100);
	SetDlgItemText(IDC_FOCUS, "1.00" );
}
