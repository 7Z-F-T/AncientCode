// HistogramDlg.cpp : implementation file
//

#include "stdafx.h"
#include "Dip.h"
#include "HistogramDlg.h"
#include "HistStatic.h"
#include "ColorStatic.h"
#include "DibBitOper.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CHistogramDlg dialog


CHistogramDlg::CHistogramDlg(CWnd* pParent /*=NULL*/)
	: CDialog(CHistogramDlg::IDD, pParent)
{
	//{{AFX_DATA_INIT(CHistogramDlg)
	//}}AFX_DATA_INIT
}


void CHistogramDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CHistogramDlg)
	//}}AFX_DATA_MAP
}


BEGIN_MESSAGE_MAP(CHistogramDlg, CDialog)
	//{{AFX_MSG_MAP(CHistogramDlg)
	ON_CBN_SELCHANGE(IDC_CHANNEL, OnSelchangeChannel)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CHistogramDlg message handlers

BOOL CHistogramDlg::OnInitDialog() 
{
	CDialog::OnInitDialog();

	SetDlgItemText(IDC_GREYLEVEL, "");
	SetDlgItemText(IDC_GREYCOUNT, "");
	m_HistView.SubclassDlgItem(IDC_HISTVIEW, this);
	m_HistView.SetCursor( ::LoadCursor(NULL, IDC_CROSS) );
	m_ColorList.SubclassDlgItem(IDC_COLORLIST, this);

	CRect rect;
	m_HistView.GetWindowRect ( &rect );
	m_HistView.m_nHeight = rect.Height();
	m_HistView.m_nWidth = rect.Width();
/*	
	BYTE *pData = m_pDoc->m_pDib->GetBmBitsPtr();
	int nbits = m_pDoc->m_pDib->GetInfoHeaderPtr()->biBitCount;
	int nSize = m_pDoc->m_pDib->ImageDataSize();
	int i, j;

	for ( i = 0; i < nSize; i++ )
		m_HistView.m_dData[0][(BYTE)pData[i]]++;

	int max[4] = {0,0,0,0};
	if ( nbits == 8 )
	{
		for ( j = 1; j < 4; j++ )
			for ( i = 0; i < 256; i++ )
				m_HistView.m_dData[j][i] = m_HistView.m_dData[0][i];
	}
	else
	{
		for ( i = 0; i < nSize; i+=3 )
		{
			m_HistView.m_dData[1][pData[i]]++;
			m_HistView.m_dData[2][pData[i+1]]++;
			m_HistView.m_dData[3][pData[i+2]]++;
		}
	}
	for ( j = 0; j < 4; j++ )
		for ( i = 0; i < 256; i++ )
			if ( max[j] < m_HistView.m_dData[j][i] )
				max[j] = m_HistView.m_dData[j][i];

	for ( j = 0; j < 4; j++ )
	{
		for ( i = 0; i < 256; i++ )
			m_HistView.m_dData[j][i] = m_HistView.m_dData[j][i] * 
				m_HistView.m_nHeight / max[j];
		m_HistView.m_dData[j][256] = max[j];
	}*/
	CDibBitOper oper;
	oper.CaculateHist ( m_pDoc->m_pDib, m_HistView.m_dData );
	/*
	for ( int j = 0; j < 4; j++ )
	{
		for ( int i = 0; i < 256; i++ )
			m_HistView.m_dData[j][i] = m_HistView.m_dData[j][i] * 
				m_HistView.m_nHeight / m_HistView.m_dData[j][256];
	}
	*/
	for ( int i = 0; i < 256; i++ )
		m_HistView.m_dData[0][i] = m_HistView.m_dData[0][i] * 
		m_HistView.m_nHeight / m_HistView.m_dData[0][256];



	return TRUE;  // return TRUE unless you set the focus to a control
	              // EXCEPTION: OCX Property Pages should return FALSE
}

void CHistogramDlg::OnSelchangeChannel() 
{
	CComboBox *pCombo = ( CComboBox*) GetDlgItem ( IDC_CHANNEL );
	m_ColorList.SetChannel ( pCombo->GetCurSel() );
	m_HistView.SetChannel ( pCombo->GetCurSel() );
	m_HistView.Invalidate();
}
