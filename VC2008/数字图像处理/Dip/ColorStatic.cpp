// ColorStatic.cpp : implementation file
//

#include "stdafx.h"
#include "Dip.h"
#include "ColorStatic.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CColorStatic

CColorStatic::CColorStatic()
{
	m_nChannel = 0;
}

CColorStatic::~CColorStatic()
{
}


BEGIN_MESSAGE_MAP(CColorStatic, CStatic)
	//{{AFX_MSG_MAP(CColorStatic)
	ON_WM_PAINT()
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CColorStatic message handlers

void CColorStatic::OnPaint() 
{
	CPaintDC dc(this); // device context for painting
	
	CRect rect;
	GetWindowRect ( &rect );
	CPen	*pPen, *pOldPen;

	int		color;
	int		width = rect.Width();
	int		height = rect.Height();
	for ( int i = 0; i < width; i++ )
	{
		color = i * 255 / width;

		switch ( m_nChannel )
		{
		case 0:
			pPen = new CPen(PS_SOLID, 1, RGB(color,color,color));
			break;
		case 1:
			pPen = new CPen(PS_SOLID, 1,RGB(0,0,color));
			break;
		case 2:
			pPen = new CPen(PS_SOLID, 1,RGB(0,color,0));
			break;
		case 3:
			pPen = new CPen(PS_SOLID, 1,RGB(color,0,0));
			break;
		}
		pOldPen = dc.SelectObject ( pPen );
		dc.Rectangle ( i, 0, i+1, height-1 );
		dc.SelectObject ( pOldPen );

		delete pPen;
	}
}

void CColorStatic::SetChannel(int chn)
{
	m_nChannel = chn;
	Invalidate(FALSE);
}
