// View.cpp : implementation of the CZoomPerspectiveView class
//

#include "stdafx.h"
#include "ZoomPerspective.h"

#include "ZoomPerspectiveDoc.h"
#include "View.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CZoomPerspectiveView

IMPLEMENT_DYNCREATE(CZoomPerspectiveView, CView)

BEGIN_MESSAGE_MAP(CZoomPerspectiveView, CView)
	//{{AFX_MSG_MAP(CZoomPerspectiveView)
	ON_WM_CREATE()
	ON_WM_DESTROY()
	ON_WM_ERASEBKGND()
	ON_WM_LBUTTONDOWN()
	ON_WM_LBUTTONUP()
	ON_WM_MOUSEMOVE()
	ON_WM_RBUTTONDOWN()
	ON_COMMAND(ID_ZOOM_ALL, OnZoomAll)
	ON_COMMAND(ID_PERSPECTIVE_PARAMETERS, OnPerspectiveParameters)
	ON_UPDATE_COMMAND_UI(ID_PERSPECTIVE_PARAMETERS, OnUpdatePerspectiveParameters)
	//}}AFX_MSG_MAP
	// Standard printing commands
	ON_COMMAND(ID_FILE_PRINT, CView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_DIRECT, CView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_PREVIEW, CView::OnFilePrintPreview)
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CZoomPerspectiveView construction/destruction

CZoomPerspectiveView::CZoomPerspectiveView()
{
      m_bZooming  = false;
	   m_list      = 0;
      //
      m_ogl.SetPerspectiveView(90.0,1.0,10000.0);
}

CZoomPerspectiveView::~CZoomPerspectiveView()
{
}

BOOL CZoomPerspectiveView::PreCreateWindow(CREATESTRUCT& cs)
{
      m_ogl.PreCreateWindow(cs);
	   return CView::PreCreateWindow(cs);
}

/////////////////////////////////////////////////////////////////////////////
// CZoomPerspectiveView drawing

void CZoomPerspectiveView::OnDraw(CDC* pDC)
{
	CZoomPerspectiveDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
   //
   if( !pDC->IsPrinting() ){
      //
      // printing next step...
      //
      CRect rcClient;
      GetClientRect(rcClient);
      rcClient.NormalizeRect();
      //
      m_ogl.OnPreRenderScene(pDC,rcClient);
         //
         // draw the object...
         //
         if( !m_list ) CreateDisplayList();
	      ::glCallList(m_list);
         //
      m_ogl.OnPostRenderScene(pDC,rcClient);
   }
}

/////////////////////////////////////////////////////////////////////////////
// CZoomPerspectiveView printing

BOOL CZoomPerspectiveView::OnPreparePrinting(CPrintInfo* pInfo)
{
	// default preparation
	return DoPreparePrinting(pInfo);
}

void CZoomPerspectiveView::OnBeginPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
	// TODO: add extra initialization before printing
}

void CZoomPerspectiveView::OnEndPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
	// TODO: add cleanup after printing
}

/////////////////////////////////////////////////////////////////////////////
// CZoomPerspectiveView diagnostics

#ifdef _DEBUG
void CZoomPerspectiveView::AssertValid() const
{
	CView::AssertValid();
}

void CZoomPerspectiveView::Dump(CDumpContext& dc) const
{
	CView::Dump(dc);
}

CZoomPerspectiveDoc* CZoomPerspectiveView::GetDocument() // non-debug version is inline
{
	ASSERT(m_pDocument->IsKindOf(RUNTIME_CLASS(CZoomPerspectiveDoc)));
	return (CZoomPerspectiveDoc*)m_pDocument;
}
#endif //_DEBUG

/////////////////////////////////////////////////////////////////////////////
// CZoomPerspectiveView message handlers

int CZoomPerspectiveView::OnCreate(LPCREATESTRUCT lpCreateStruct) 
{
	if (CView::OnCreate(lpCreateStruct) == -1)
		return -1;
   m_ogl.OnCreate(this);
	return 0;
}

void CZoomPerspectiveView::OnDestroy() 
{
	CView::OnDestroy();
   m_ogl.OnDestroy();
}

BOOL CZoomPerspectiveView::OnEraseBkgnd(CDC* pDC) 
{
   return ( m_ogl.OnEraseBkgnd(pDC) ? TRUE : CView::OnEraseBkgnd(pDC) );
}
//
/////////// This code is dirty but for select a rectangle ... OK !
//
class CSelectionDC : public CDC
{
private:
   int      m_iOldROP;
   CPen     m_Pen;
   CPen*    m_pPenOld;
   CBrush*  m_pBrushOld;
   HWND     m_hWnd;

private:
   void Init()
      {
         m_Pen.CreatePen(PS_SOLID,0,RGB(255,255,255));
         m_pPenOld   = SelectObject(&m_Pen);
         m_pBrushOld = (CBrush*)SelectStockObject(NULL_BRUSH);
         m_iOldROP   = SetROP2(R2_XORPEN);
      }

public:
   CSelectionDC(const CDC &cdc)
      {
         m_hWnd = NULL;
	      if( !Attach(cdc.m_hDC) )   AfxThrowResourceException();
         Init();
      }

    CSelectionDC(CWnd *pWnd)
      {
	      if( !Attach(::GetDC(m_hWnd = pWnd->GetSafeHwnd())) )  AfxThrowResourceException();
         Init();
      }

   virtual ~CSelectionDC()
      {
         SetROP2(m_iOldROP);
         SelectObject(m_pBrushOld);
         SelectObject(m_pPenOld);
         if( m_hWnd ){
            ::ReleaseDC(m_hWnd,Detach());
         }else{
	         Detach();
         }
      }
};

void CZoomPerspectiveView::OnLButtonDown(UINT nFlags, CPoint point) 
{
      if( !m_bZooming ){
         CRect rcClient;
         GetClientRect(&rcClient);
         MapWindowPoints(NULL,(LPPOINT)&rcClient,2);
         ClipCursor(&rcClient);
         SetCapture();
         m_bZooming = TRUE;
         m_rcSelect.SetRect(point.x,point.y,point.x,point.y);
         //
         CSelectionDC   dc(this);
         OnDrawSelection(&dc);
      }
	   CView::OnLButtonDown(nFlags, point);
}

void CZoomPerspectiveView::OnLButtonUp(UINT nFlags, CPoint point) 
{
      if( m_bZooming ){
         CSelectionDC   dc(this);
         OnDrawSelection(&dc);
         //
         ClipCursor(NULL);
         ReleaseCapture();
         m_bZooming  = FALSE;
         //
         CRect rcClient;
         GetClientRect(&rcClient);
         rcClient.NormalizeRect();
         m_rcSelect.NormalizeRect();
         //
         m_ogl.Zoom(rcClient,m_rcSelect);
         //
         InvalidateRect( NULL,TRUE );
      }
	   CView::OnLButtonUp(nFlags, point);
}

void CZoomPerspectiveView::OnMouseMove(UINT nFlags, CPoint point) 
{
      if( m_bZooming ){
         CSelectionDC   dc(this);
         //
         OnDrawSelection(&dc);
         m_rcSelect.right  = point.x;
         m_rcSelect.bottom = point.y;
         OnDrawSelection(&dc);
      }
	   CView::OnMouseMove(nFlags, point);
}

void CZoomPerspectiveView::OnRButtonDown(UINT nFlags, CPoint point) 
{
      if( m_bZooming ){
         CSelectionDC   dc(this);
         OnDrawSelection(&dc);
         ClipCursor(NULL);
         ReleaseCapture();
         m_bZooming  = FALSE;
      }
	   CView::OnRButtonDown(nFlags, point);
}

void CZoomPerspectiveView::OnDrawSelection(CDC* pDC)
{
	   pDC->MoveTo(m_rcSelect.left ,m_rcSelect.bottom);
	   pDC->LineTo(m_rcSelect.right,m_rcSelect.bottom);
	   pDC->LineTo(m_rcSelect.right,m_rcSelect.top   );
	   pDC->LineTo(m_rcSelect.left ,m_rcSelect.top   );
	   pDC->LineTo(m_rcSelect.left ,m_rcSelect.bottom);
}

void CZoomPerspectiveView::OnZoomAll() 
{
      m_ogl.ZoomAll();
      InvalidateRect(NULL,TRUE);
}

void CZoomPerspectiveView::OnPerspectiveParameters() 
{
      if( m_ogl.SetupPerspectiveParameters() == IDOK )
         InvalidateRect(NULL,TRUE);
}

void CZoomPerspectiveView::OnUpdatePerspectiveParameters(CCmdUI* pCmdUI) 
{
      pCmdUI->Enable( TRUE );
}
//
/////////////////////////
//
// this is a dummy function. In the real word replace this function
// by your drawing code...
//
// This code draw a textured cube ....
//
static   float xSize=10.0f;
static   float ySize=10.0f;
static   float zSize=10.0f;
//
// this code draw a cube with size 'size' around origin...
//
static   float m[8][3]={      { -1.0f,-1.0f,-1.0f },
                     {  1.0f,-1.0f,-1.0f },
                     {  1.0f, 1.0f,-1.0f },
                     { -1.0f, 1.0f,-1.0f },
                     { -1.0f,-1.0f, 1.0f },
                     {  1.0f,-1.0f, 1.0f },
                     {  1.0f, 1.0f, 1.0f },
                     { -1.0f, 1.0f, 1.0f }};
//
static   float normal[6][3]={ {  0.0f, 0.0f,-1.0f },
                     {  0.0f, 0.0f, 1.0f },
                     {  0.0f,-1.0f, 0.0f },
                     {  0.0f, 1.0f, 0.0f },
                     { -1.0f, 0.0f, 0.0f },
                     {  1.0f, 0.0f, 0.0f }};
//
static   int   faces[6][4]={  { 3, 2, 1, 0 },   // -z
                     { 4, 5, 6, 7 },   // +z
                     { 0, 1, 5, 4 },   // -y
                     { 3, 7, 6, 2 },   // +y
                     { 0, 4, 7, 3 },   // -x
                     { 1, 2, 6, 5 }};  // +x
//
static   int   side[12][2] ={ { 0, 1 },
                     { 1, 2 },
                     { 2, 3 },
                     { 3, 0 },
                     { 4, 5 },
                     { 5, 6 },
                     { 6, 7 },
                     { 7, 0 },
                     { 0, 4 },
                     { 1, 5 },
                     { 2, 6 },
                     { 3, 7 }};

void  CZoomPerspectiveView::CreateDisplayList()
{
      if( m_list ) return;
      //
#ifdef _ONLY_COLORS_
      //
      m_list   = 1;
	   ::glNewList(m_list,GL_COMPILE);
      //
      ::glShadeModel(GL_SMOOTH);
         //
	      ::glBegin(GL_QUADS);
         //
         int   r[]={ 255,   0,   0,  255 }; 
         int   g[]={   0, 255,   0,  255 }; 
         int   b[]={   0,   0, 255,    0 };

		   for( int i=0 , j , k ; i < 6 ; i++ ){
		         ::glNormal3d(normal[i][0],normal[i][1],normal[i][2]);
               for( j=0 ; j < 4 ; j++ ){
				      k=faces[i][j];
                  ::glColor3ub(r[j],g[j],b[j]);
                  ::glVertex3d( m[k][0] * xSize,m[k][1] * ySize,m[k][2] * zSize);
               }
		   }
	      ::glEnd();
	   ::glEndList();
#else
      m_Texture.ReadFromResource(IDR_TEXTURE,_T("TEXTURE"));
      m_Texture.BGRtoRGB();
      //
      m_list   = 1;
	   ::glNewList(m_list,GL_COMPILE);
      //
      ::glShadeModel(GL_SMOOTH);
      //
         ::glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
         ::glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
//       ::glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//       ::glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
         ::glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
         ::glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
         //
         ::glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_DECAL);
         ::glEnable(GL_TEXTURE_2D);
         ::glShadeModel(GL_SMOOTH);

	      if( m_Texture.GetData() != NULL)
		      ::glTexImage2D(   GL_TEXTURE_2D,0,3,
		                        m_Texture.GetWidth(),
								      m_Texture.GetHeight(),0,
								      GL_RGB,GL_UNSIGNED_BYTE,
								      m_Texture.GetData());
         //
	      ::glBegin(GL_QUADS);
         //
         float xT[]={   0.0f, 1.0f, 1.0f, 0.0f };
         float yT[]={   0.0f, 0.0f, 1.0f, 1.0f };
         //
		   for( int i=0 , j , k ; i < 6 ; i++ ){
		         ::glNormal3d(normal[i][0],normal[i][1],normal[i][2]);
               for( j=0 ; j < 4 ; j++ ){
				      k=faces[i][j];
                  glTexCoord2f(xT[j],yT[j]);
                  ::glVertex3d( m[k][0] * xSize,m[k][1] * ySize,m[k][2] * zSize);
               }
		   }
	      ::glEnd();
      //
	   ::glDisable(GL_TEXTURE_2D);
      //
	   ::glEndList();
#endif
}


