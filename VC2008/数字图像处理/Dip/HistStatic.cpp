// HistStatic.cpp : implementation file
//

#include "stdafx.h"
#include "Dip.h"
#include "HistStatic.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CHistStatic

CHistStatic::CHistStatic()
{
	for ( int i = 0; i < 4; i++ )
		for ( int j = 0; j < 256; j++ )
			m_dData[i][j] = 0;
	m_nChannel = 0;
}

CHistStatic::~CHistStatic()
{
}


BEGIN_MESSAGE_MAP(CHistStatic, CWnd)
	//{{AFX_MSG_MAP(CHistStatic)
	ON_WM_PAINT()
	ON_WM_MOUSEMOVE()
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CHistStatic message handlers

void CHistStatic::OnPaint() 
{
	CPaintDC dc(this); // device context for painting
	int x;
	dc.Rectangle ( 0, 0, m_nWidth, m_nHeight );
	for ( int i = 0; i < 256; i++ )
	{	
		x = i * (m_nWidth-1) / 255;
		dc.Rectangle ( CRect ( x, m_nHeight - m_dData[m_nChannel][i],
			x+1, m_nHeight ) );
	}
}

void CHistStatic::SetChannel(int chn)
{
	m_nChannel = chn;
	Invalidate();
}

void CHistStatic::OnMouseMove(UINT nFlags, CPoint point) 
{
	int x = point.x * 255 / m_nWidth;
	int count = m_dData[m_nChannel][x] * m_dData[m_nChannel][256] / m_nHeight;
	GetParent()->SetDlgItemInt(IDC_GREYLEVEL, x );
	GetParent()->SetDlgItemInt(IDC_GREYCOUNT, count);

	CStatic::OnMouseMove(nFlags, point);
}
