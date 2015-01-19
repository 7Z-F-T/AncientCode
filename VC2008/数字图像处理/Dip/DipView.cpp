// DipView.cpp : implementation of the CDipView class
//

#include "stdafx.h"
#include "Dip.h"

#include "DipDoc.h"
#include "DipView.h"
#include "DibBitOper.h"
#include "DibSpatialOper.h"
#include "DibAlgOper.h"
#include "DibGeoOper.h"
#include "DibMorOper.h"
#include "ThresholdDlg.h"
#include "LinearDlg.h"
#include "HistogramDlg.h"
#include "AffineDlg.h"
#include "PersDlg.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CDipView

IMPLEMENT_DYNCREATE(CDipView, CScrollView)

BEGIN_MESSAGE_MAP(CDipView, CScrollView)
	//{{AFX_MSG_MAP(CDipView)
	ON_COMMAND(ID_POINT_THRESHOLD, OnPointThreshold)
	ON_COMMAND(ID_EDIT_UNDO, OnEditUndo)
	ON_UPDATE_COMMAND_UI(ID_EDIT_UNDO, OnUpdateEditUndo)
	ON_COMMAND(ID_POINT_NEGATIVE, OnPointNegative)
	ON_COMMAND(ID_POINT_LINEAR, OnPointLinear)
	ON_COMMAND(ID_HISTOGRAM, OnHistogram)
	ON_COMMAND(ID_FIELD_AVERAGE, OnFieldAverage)
	ON_COMMAND(ID_FIELD_GAUSSIAN, OnFieldGaussian)
	ON_COMMAND(ID_FIELD_ROBERTS, OnFieldRoberts)
	ON_COMMAND(ID_FIELD_PREWITT, OnFieldPrewitt)
	ON_COMMAND(ID_FIELD_SOBEL, OnFieldSobel)
	ON_COMMAND(ID_FIELD_ISOBEL, OnFieldIsobel)
	ON_COMMAND(ID_FIELD_LAPLACIAN, OnFieldLaplacian)
	ON_COMMAND(ID_POINT_EQUALIZATION, OnPointEqualization)
	ON_COMMAND(ID_ALG_OFFSET, OnAlgOffset)
	ON_COMMAND(ID_ALG_SUBSTRACT, OnAlgSubstract)
	ON_COMMAND(ID_ALG_ADD, OnAlgAdd)
	ON_COMMAND(ID_GEO_VERTICAL, OnGeoVertical)
	ON_COMMAND(ID_GEO_HORIZON, OnGeoHorizon)
	ON_COMMAND(ID_MOR_EROTION, OnMorErotion)
	ON_COMMAND(ID_MOR_DILATION, OnMorDilation)
	ON_COMMAND(ID_MOR_OPEN, OnMorOpen)
	ON_COMMAND(ID_MOR_CLOSE, OnMorClose)
	ON_COMMAND(ID_MOR_EDGE, OnMorEdge)
	ON_COMMAND(ID_MOR_SKELECTON, OnMorSkelecton)
	ON_COMMAND(ID_GEO_AFFINE, OnGeoAffine)
	ON_COMMAND(ID_GEO_PERS, OnGeoPers)
	ON_COMMAND(ID_FIELD_SUSAN, OnFieldSusan)
	ON_COMMAND(IDC_MOR_THIN, OnMorThin)
	ON_UPDATE_COMMAND_UI(ID_FIELD_SUSAN, OnUpdateFieldSusan)
	ON_COMMAND(ID_FIELD_CANNY, OnFieldCanny)
	ON_UPDATE_COMMAND_UI(ID_FIELD_CANNY, OnUpdateFieldCanny)
	//}}AFX_MSG_MAP
	// Standard printing commands
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CDipView construction/destruction

CDipView::CDipView()
{
	// TODO: add construction code here

}

CDipView::~CDipView()
{
}

BOOL CDipView::PreCreateWindow(CREATESTRUCT& cs)
{
	// TODO: Modify the Window class or styles here by modifying
	//  the CREATESTRUCT cs

	return CScrollView::PreCreateWindow(cs);
}

/////////////////////////////////////////////////////////////////////////////
// CDipView drawing

void CDipView::OnDraw(CDC* pDC)
{
	CDipDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	CDib *pOut;

	if ( pDoc->m_nPreview )
		pOut = pDoc->m_pPreDib;
	else
		pOut = pDoc->m_pDib;

	pOut->ShowDib ( pDC, CRect ( 0, 0, 100, 100 ), FALSE );

}

void CDipView::OnInitialUpdate()
{
	CDipDoc* pDoc = GetDocument();

	CScrollView::OnInitialUpdate();

	CSize sizeTotal;
	// TODO: calculate the total size of this view
	sizeTotal.cx = pDoc->m_pDib->Width();
	sizeTotal.cy = pDoc->m_pDib->Height();

	SetScrollSizes(MM_TEXT, sizeTotal);
	ResizeParentToFit ();
}


/////////////////////////////////////////////////////////////////////////////
// CDipView diagnostics

#ifdef _DEBUG
void CDipView::AssertValid() const
{
	CScrollView::AssertValid();
}

void CDipView::Dump(CDumpContext& dc) const
{
	CScrollView::Dump(dc);
}

CDipDoc* CDipView::GetDocument() // non-debug version is inline
{
	ASSERT(m_pDocument->IsKindOf(RUNTIME_CLASS(CDipDoc)));
	return (CDipDoc*)m_pDocument;
}
#endif //_DEBUG

/////////////////////////////////////////////////////////////////////////////
// CDipView message handlers

void CDipView::OnPointThreshold() 
{
	CDipDoc *pDoc = GetDocument();	
	CThresholdDlg *dlg = new CThresholdDlg(this);
	
	dlg->pDoc = pDoc;
	if ( dlg->DoModal() == IDCANCEL )
	{
		pDoc->m_nPreview = 0;
		Invalidate();
		return;
	}
	
	dlg->Change();
	pDoc->SaveResult();

	delete dlg;
}

void CDipView::OnEditUndo() 
{
	CDipDoc *pDoc = GetDocument();
	delete pDoc->m_pDib;
	pDoc->m_pDib = pDoc->m_pOldDib;
	pDoc->m_pOldDib = NULL;
	pDoc->m_nCanUndo = 0;
	pDoc->m_nSteps--;
	if ( pDoc->m_nSteps == 0 )
		pDoc->SetModifiedFlag(FALSE);
	Invalidate(FALSE);
}

void CDipView::OnUpdateEditUndo(CCmdUI* pCmdUI) 
{
	CDipDoc *pDoc = GetDocument();

	pCmdUI->Enable( pDoc->m_nCanUndo );
}

void CDipView::OnPointNegative() 
{
	CDipDoc *pDoc = GetDocument();
	CDibBitOper Oper;

	Oper.Inverse ( pDoc->m_pDib, pDoc->m_pPreDib );
	pDoc->SaveResult();
	Invalidate(FALSE);
}

void CDipView::OnPointLinear() 
{
	CDipDoc *pDoc = GetDocument();	
	CLinearDlg *dlg = new CLinearDlg(this);
	
	dlg->pDoc = pDoc;
	if ( dlg->DoModal() == IDCANCEL )
	{
		pDoc->m_nPreview = 0;
		Invalidate();
		return;
	}

	dlg->Change();
	pDoc->SaveResult();
	delete dlg;
}

void CDipView::OnUpdate(CView* pSender, LPARAM lHint, CObject* pHint) 
{
	Invalidate(FALSE);
}

void CDipView::OnHistogram() 
{
	CDipDoc	*pDoc = GetDocument();

	CHistogramDlg	dlg;

	dlg.m_pDoc = pDoc;
	dlg.DoModal();
}

void CDipView::OnFieldAverage() 
{
	CDipDoc *pDoc = GetDocument();
	CDibSpatialOper Oper;

	Oper.Convolute ( pDoc->m_pDib, pDoc->m_pPreDib, CDibSpatialOper::CONVOLUTE_SMOOTH );
	pDoc->SaveResult();
	Invalidate(FALSE);
}

void CDipView::OnFieldGaussian() 
{
	CDipDoc *pDoc = GetDocument();
	CDibSpatialOper Oper;

	Oper.Convolute ( pDoc->m_pDib, pDoc->m_pPreDib, CDibSpatialOper::CONVOLUTE_GAUSSIAN );
	pDoc->SaveResult();
	Invalidate(FALSE);
}

void CDipView::OnFieldRoberts() 
{
	CDipDoc *pDoc = GetDocument();
	CDibSpatialOper Oper;

	Oper.EdgeDetect ( pDoc->m_pDib, pDoc->m_pPreDib, CDibSpatialOper::CONVOLUTE_ROBERTS );
	pDoc->SaveResult();
	Invalidate(FALSE);
}

void CDipView::OnFieldPrewitt() 
{
	CDipDoc *pDoc = GetDocument();
	CDibSpatialOper Oper;

	Oper.EdgeDetect ( pDoc->m_pDib, pDoc->m_pPreDib, CDibSpatialOper::CONVOLUTE_PREWITT );
	pDoc->SaveResult();
	Invalidate(FALSE);
}

void CDipView::OnFieldSobel() 
{
	CDipDoc *pDoc = GetDocument();
	CDibSpatialOper Oper;

	Oper.EdgeDetect ( pDoc->m_pDib, pDoc->m_pPreDib, CDibSpatialOper::CONVOLUTE_SOBEL );
	pDoc->SaveResult();
	Invalidate(FALSE);
}

void CDipView::OnFieldIsobel() 
{
	CDipDoc *pDoc = GetDocument();
	CDibSpatialOper Oper;

	Oper.EdgeDetect ( pDoc->m_pDib, pDoc->m_pPreDib, CDibSpatialOper::CONVOLUTE_ISOBEL );
	pDoc->SaveResult();
	Invalidate(FALSE);
}

void CDipView::OnFieldLaplacian() 
{
	CDipDoc *pDoc = GetDocument();
	CDibSpatialOper Oper;

	Oper.Convolute ( pDoc->m_pDib, pDoc->m_pPreDib, CDibSpatialOper::CONVOLUTE_LAPLACIAN );
	pDoc->SaveResult();
	Invalidate(FALSE);
}

void CDipView::OnPointEqualization() 
{
	CDipDoc *pDoc = GetDocument();
	CDibBitOper Oper;

	Oper.Equalization ( pDoc->m_pDib, pDoc->m_pPreDib);
	pDoc->SaveResult();
	Invalidate(FALSE);
}

void CDipView::OnAlgOffset() 
{
	CDipDoc *pDoc = GetDocument();
	CDibAlgOper Oper;

	Oper.Offset ( pDoc->m_pDib, pDoc->m_pPreDib);
	pDoc->SaveResult();
	Invalidate(FALSE);
}

void CDipView::OnAlgSubstract() 
{
	CDipDoc *pDoc = GetDocument();
	CDib dest;
	CDibAlgOper Oper;

	CFileDialog fileDlg ( TRUE, "Bitmap", "*.bmp" );
	if ( fileDlg.DoModal() == IDCANCEL )
		return;
	dest.LoadFromFile ( fileDlg.GetPathName() );
	if ( !Oper.Substract ( pDoc->m_pDib, &dest, pDoc->m_pPreDib) )
	{
		AfxMessageBox ( "两个文件不能相减!" );
		return;
	}
	pDoc->SaveResult();
	Invalidate(FALSE);
}

void CDipView::OnAlgAdd() 
{
	CDipDoc *pDoc = GetDocument();
	CDib dest;
	CDibAlgOper Oper;

	CFileDialog fileDlg ( TRUE, "Bitmap", "*.bmp" );
	if ( fileDlg.DoModal() == IDCANCEL )
		return;
	dest.LoadFromFile ( fileDlg.GetPathName() );
	if ( !Oper.Add ( pDoc->m_pDib, &dest, pDoc->m_pPreDib) )
	{
		AfxMessageBox ( "两个文件不能相加!" );
		return;
	}
	pDoc->SaveResult();
	Invalidate(FALSE);
}

void CDipView::OnGeoVertical() 
{
	CDipDoc *pDoc = GetDocument();
	CDibGeoOper Oper;

	Oper.Vertical ( pDoc->m_pDib, pDoc->m_pPreDib);
	pDoc->SaveResult();
	Invalidate(FALSE);
}

void CDipView::OnGeoHorizon() 
{
	CDipDoc *pDoc = GetDocument();
	CDibGeoOper Oper;

	Oper.Horizon ( pDoc->m_pDib, pDoc->m_pPreDib);
	pDoc->SaveResult();
	Invalidate(FALSE);
}

void CDipView::OnMorErotion() 
{
	CDipDoc *pDoc = GetDocument();
	CDibMorOper Oper;

	Oper.Erotion ( pDoc->m_pDib, pDoc->m_pPreDib, CDibMorOper::MORPH_DIAMOND);
	pDoc->SaveResult();
	Invalidate(FALSE);
}

void CDipView::OnMorDilation() 
{
	CDipDoc *pDoc = GetDocument();
	CDibMorOper Oper;

	Oper.Dilation ( pDoc->m_pDib, pDoc->m_pPreDib, CDibMorOper::MORPH_DIAMOND);
	pDoc->SaveResult();
	Invalidate(FALSE);
}

void CDipView::OnMorOpen() 
{
	CDipDoc *pDoc = GetDocument();
	CDibMorOper Oper;

	Oper.Open ( pDoc->m_pDib, pDoc->m_pPreDib, CDibMorOper::MORPH_DIAMOND );

	pDoc->SaveResult();
	Invalidate(FALSE);
}

void CDipView::OnMorClose() 
{
	CDipDoc *pDoc = GetDocument();
	CDibMorOper Oper;

	Oper.Close ( pDoc->m_pDib, pDoc->m_pPreDib, CDibMorOper::MORPH_DIAMOND);

	pDoc->SaveResult();
	Invalidate(FALSE);
}

void CDipView::OnMorEdge() 
{
	CDipDoc *pDoc = GetDocument();
	CDibMorOper Oper;

	Oper.Edge ( pDoc->m_pDib, pDoc->m_pPreDib, CDibMorOper::MORPH_DIAMOND );

	pDoc->SaveResult();
	Invalidate(FALSE);
}

void CDipView::OnMorSkelecton() 
{
	CDipDoc *pDoc = GetDocument();
	CDibMorOper Oper;

	Oper.Skelecton ( pDoc->m_pDib, pDoc->m_pPreDib, CDibMorOper::MORPH_DIAMOND );

	pDoc->SaveResult();
	Invalidate(FALSE);
}

void CDipView::OnGeoAffine() 
{
	CDipDoc *pDoc = GetDocument();	
	CAffineDlg *dlg = new CAffineDlg(this);
	
	dlg->pDoc = pDoc;
	if ( dlg->DoModal() == IDCANCEL )
	{
		pDoc->m_nPreview = 0;
		Invalidate();
		return;
	}

	dlg->Change();
	pDoc->SaveResult();
	delete dlg;
}

void CDipView::OnGeoPers() 
{
	CDipDoc *pDoc = GetDocument();	
	CPersDlg *dlg = new CPersDlg(this);
	
	dlg->pDoc = pDoc;
	if ( dlg->DoModal() == IDCANCEL )
	{
		pDoc->m_nPreview = 0;
		Invalidate();
		return;
	}

	dlg->Change();
	pDoc->SaveResult();
	delete dlg;
}

void CDipView::OnFieldSusan() 
{
	CDipDoc *pDoc = GetDocument();
	CDibSpatialOper Oper;

	Oper.Susan ( pDoc->m_pDib, pDoc->m_pPreDib);
	pDoc->SaveResult();
	Invalidate(FALSE);
}

void CDipView::OnMorThin() 
{
	CDipDoc *pDoc = GetDocument();
	CDibMorOper Oper;
	CDib *pTemp = new CDib;
	Oper.CopyDib( pDoc->m_pDib, pTemp );
	Oper.Thin ( pTemp, pDoc->m_pPreDib);
	delete pTemp;
	pDoc->SaveResult();
	Invalidate(FALSE);
}

void CDipView::OnUpdateFieldSusan(CCmdUI* pCmdUI) 
{
	if ( GetDocument()->m_pDib->GetBitCount() == 8 )
		pCmdUI->Enable(TRUE);
	else
		pCmdUI->Enable(FALSE);
}

void CDipView::OnFieldCanny() 
{
	CDipDoc *pDoc = GetDocument();
	CDibSpatialOper Oper;

	Oper.Canny ( pDoc->m_pDib, pDoc->m_pPreDib);
	pDoc->SaveResult();
	Invalidate(FALSE);
}

void CDipView::OnUpdateFieldCanny(CCmdUI* pCmdUI) 
{
	if ( GetDocument()->m_pDib->GetBitCount() == 8 )
		pCmdUI->Enable(TRUE);
	else
		pCmdUI->Enable(FALSE);
}
