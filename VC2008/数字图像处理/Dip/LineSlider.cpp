// LineSlider.cpp : implementation file
//

#include "stdafx.h"
#include "Dip.h"
#include "LineSlider.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CLineSlider

CLineSlider::CLineSlider()
{
	m_nMouseDown = 0;
}

CLineSlider::~CLineSlider()
{
}


BEGIN_MESSAGE_MAP(CLineSlider, CSliderCtrl)
	//{{AFX_MSG_MAP(CLineSlider)
	ON_WM_PAINT()
	ON_WM_LBUTTONDOWN()
	ON_WM_MOUSEMOVE()
	ON_WM_LBUTTONUP()
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CLineSlider message handlers

void CLineSlider::OnPaint() 
{
	CPaintDC dc(this); // device context for painting
	
	CRect rect;
	GetWindowRect( &rect );
	dc.MoveTo ( 12, rect.Height()/2 );
	dc.LineTo ( rect.Width() - 13, rect.Height()/2 );
	int nPos = GetPos();
	int low, upper;
	GetRange(low, upper);
	int test = (nPos-low)*(rect.Width()-25)/(upper-low)+12;
	dc.Rectangle ( test - 5, rect.Height()/2, test+5, 
		rect.Height()/2+10 );
	// Do not call CSliderCtrl::OnPaint() for painting messages
}

void CLineSlider::OnLButtonDown(UINT nFlags, CPoint point) 
{
	CSliderCtrl::OnLButtonDown(nFlags, point);
	m_nMouseDown = 1;
	Invalidate();
}

void CLineSlider::OnMouseMove(UINT nFlags, CPoint point) 
{
	CSliderCtrl::OnMouseMove(nFlags, point);
	if ( m_nMouseDown )
		Invalidate();
}

void CLineSlider::OnLButtonUp(UINT nFlags, CPoint point) 
{
	m_nMouseDown = 0;	
	CSliderCtrl::OnLButtonUp(nFlags, point);
	Invalidate();
}

void CLineSlider::SetPos(int nPos)
{
	CSliderCtrl::SetPos(nPos);
	Invalidate();
}
