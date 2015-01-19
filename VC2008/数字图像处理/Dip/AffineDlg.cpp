// AffineDlg.cpp : implementation file
//

#include "stdafx.h"
#include "Dip.h"
#include "AffineDlg.h"
#include "DibGeoOper.h"
#include "LineSlider.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CAffineDlg dialog


CAffineDlg::CAffineDlg(CWnd* pParent /*=NULL*/)
	: CDipDialog(CAffineDlg::IDD, pParent)
{
	//{{AFX_DATA_INIT(CAffineDlg)
	m_dRotate = 0.0;
	m_dDistort = 0.0;
	m_dTrans = 1.0;
	m_dScale = 1.0;
	//}}AFX_DATA_INIT
}


void CAffineDlg::DoDataExchange(CDataExchange* pDX)
{
	CDipDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CAffineDlg)
	DDX_Text(pDX, IDC_ROTATE, m_dRotate);
	DDX_Text(pDX, IDC_DISTORT, m_dDistort);
	DDX_Text(pDX, IDC_TRANSFORM, m_dTrans);
	DDX_Text(pDX, IDC_SCALE, m_dScale);
	//}}AFX_DATA_MAP
}


BEGIN_MESSAGE_MAP(CAffineDlg, CDipDialog)
	//{{AFX_MSG_MAP(CAffineDlg)
	ON_EN_CHANGE(IDC_DISTORT, OnChangeDistort)
	ON_EN_CHANGE(IDC_SCALE, OnChangeScale)
	ON_EN_CHANGE(IDC_ROTATE, OnChangeRotate)
	ON_EN_CHANGE(IDC_TRANSFORM, OnChangeTransform)
	ON_BN_CLICKED(IDC_PREVIEW, OnPreview)
	ON_WM_HSCROLL()
	ON_BN_CLICKED(IDC_RESET, OnReset)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CAffineDlg message handlers

int CAffineDlg::Change()
{
	SetCursor(LoadCursor(0, IDC_WAIT) );
	CDibGeoOper Oper;
	if ( !m_bDlgReady )
		return 1;
	UpdateData();
	Oper.Affine( pDoc->m_pDib, pDoc->m_pPreDib, m_dScale, 
		m_dTrans, m_dDistort, (m_dRotate) * (float)3.1415926 / 180 );
	pDoc->UpdateAllViews(FALSE);
	SetCursor(LoadCursor(0, IDC_ARROW) );
	return 1;
}

BOOL CAffineDlg::OnInitDialog() 
{
	CDipDialog::OnInitDialog();
	
	m_slider1.SubclassDlgItem ( IDC_SLIDER1, this );
	m_slider2.SubclassDlgItem ( IDC_SLIDER2, this );
	m_slider3.SubclassDlgItem ( IDC_SLIDER3, this );
	m_slider4.SubclassDlgItem ( IDC_SLIDER4, this );
	m_slider1.SetRange(1, 400);
	m_slider1.SetPos(100);
	SetDlgItemText(IDC_SCALE, "1.00" );
	m_slider2.SetRange(1, 400);
	m_slider2.SetPos(100);
	SetDlgItemText(IDC_TRANSFORM, "1.00" );
	m_slider3.SetRange(0, 200);
	m_slider3.SetPos(100);
	SetDlgItemText(IDC_DISTORT, "0.00" );
	m_slider4.SetRange(0, 360);
	m_slider4.SetPos(180);
	return TRUE;  // return TRUE unless you set the focus to a control
	              // EXCEPTION: OCX Property Pages should return FALSE
}

void CAffineDlg::OnChangeDistort() 
{
	if ( m_bPreview )
		Change();
}

void CAffineDlg::OnChangeScale() 
{
	if ( m_bPreview )
		Change();
}

void CAffineDlg::OnChangeRotate() 
{
	if ( m_bPreview )
		Change();
}

void CAffineDlg::OnChangeTransform() 
{
	if ( m_bPreview )
		Change();
}

void CAffineDlg::OnPreview() 
{
	m_bPreview = !m_bPreview;
	pDoc->m_nPreview = m_bPreview;
	pDoc->UpdateAllViews(NULL);
}

void CAffineDlg::OnHScroll(UINT nSBCode, UINT nPos, CScrollBar* pScrollBar) 
{
	CString strValue;
	int Pos;

    switch (pScrollBar->GetDlgCtrlID())
	{
	case IDC_SLIDER1: 
		Pos = m_slider1.GetPos();
		strValue.Format("%3.2f", (float) Pos / 100);
		SetDlgItemText ( IDC_SCALE, strValue );
		break;
	case IDC_SLIDER2:
		Pos = m_slider2.GetPos();
		strValue.Format("%3.2f", (float) Pos / 100);
		SetDlgItemText ( IDC_TRANSFORM, strValue );
		break;
	case IDC_SLIDER3:
		Pos = m_slider3.GetPos();
		strValue.Format("%3.2f", (float)Pos/100 - 1);
		SetDlgItemText ( IDC_DISTORT, strValue );
		break;
	case IDC_SLIDER4:
		Pos = m_slider4.GetPos();
		strValue.Format("%d", Pos - 180 );
		SetDlgItemText ( IDC_ROTATE, strValue );
		break;
	}
	
	CDipDialog::OnHScroll(nSBCode, nPos, pScrollBar);
}

void CAffineDlg::OnReset() 
{
	m_slider1.SetPos(100);
	SetDlgItemText(IDC_SCALE, "1.00" );

	m_slider2.SetPos(100);
	SetDlgItemText(IDC_TRANSFORM, "1.00" );

	m_slider3.SetPos(100);
	SetDlgItemText(IDC_DISTORT, "0.00" );

	m_slider4.SetPos(180);
	SetDlgItemText(IDC_ROTATE, "0" );
}
