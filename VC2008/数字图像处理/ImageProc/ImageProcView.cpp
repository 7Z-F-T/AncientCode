// ImageProcView.cpp : implementation of the CImageProcView class
//

#include "stdafx.h"
#include "ImageProc.h"

#include "ImageProcDoc.h"
#include "ImageProcView.h"
#include "DlgDrawLine.h"
#include "MainFrm.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CImageProcView

IMPLEMENT_DYNCREATE(CImageProcView, CScrollView)

BEGIN_MESSAGE_MAP(CImageProcView, CScrollView)
	//{{AFX_MSG_MAP(CImageProcView)
	ON_COMMAND(ID_DRAW, OnDrawLine)
	ON_WM_MOUSEMOVE()
	ON_COMMAND(ID_SAVE, OnSave)
	ON_UPDATE_COMMAND_UI(ID_SAVE, OnUpdateSave)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CImageProcView construction/destruction

CImageProcView::CImageProcView()
{
	// TODO: add construction code here
	mhBitmap=NULL;
	mhPalette=NULL;
	mpData=NULL;
	mnWidth=0;
	mnHeight=0;
	mnColorBits=0;
}

CImageProcView::~CImageProcView()
{
	if(mhBitmap!=NULL)
		DeleteObject(mhBitmap);

	if(mhPalette!=NULL)
		DeleteObject(mhPalette);

	if(mpData!=NULL)
		delete mpData;
}

BOOL CImageProcView::PreCreateWindow(CREATESTRUCT& cs)
{
	// TODO: Modify the Window class or styles here by modifying
	//  the CREATESTRUCT cs

	return CScrollView::PreCreateWindow(cs);
}

/////////////////////////////////////////////////////////////////////////////
// CImageProcView drawing

void CImageProcView::OnDraw(CDC* pDC)
{
	CImageProcDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	// TODO: add draw code for native data here
	HDC hDC,hMemDC;

	if(mhBitmap!=NULL){
		SetScrollSizes(MM_TEXT,CSize(mnWidth,mnHeight));
		hDC=pDC->GetSafeHdc( );
		hMemDC=::CreateCompatibleDC(hDC);
		::SelectObject(hMemDC, mhBitmap); 
		::BitBlt(hDC, 0,0,mnWidth,mnHeight,hMemDC, 0, 0, SRCCOPY);
		::DeleteDC(hMemDC);
	}
}

void CImageProcView::OnInitialUpdate()
{
	CScrollView::OnInitialUpdate();

	CSize sizeTotal;
	// TODO: calculate the total size of this view
	sizeTotal.cx = sizeTotal.cy = 100;
	SetScrollSizes(MM_TEXT, sizeTotal);
}

/////////////////////////////////////////////////////////////////////////////
// CImageProcView diagnostics

#ifdef _DEBUG
void CImageProcView::AssertValid() const
{
	CScrollView::AssertValid();
}

void CImageProcView::Dump(CDumpContext& dc) const
{
	CScrollView::Dump(dc);
}

CImageProcDoc* CImageProcView::GetDocument() // non-debug version is inline
{
	ASSERT(m_pDocument->IsKindOf(RUNTIME_CLASS(CImageProcDoc)));
	return (CImageProcDoc*)m_pDocument;
}
#endif //_DEBUG

/////////////////////////////////////////////////////////////////////////////
// CImageProcView message handlers
void CImageProcView::OnDrawLine() 
{
	// TODO: Add your command handler code here
	HPEN hpen;
	HBRUSH hbr;

	CDlgDrawLine DlgDrawLine;

	if(DlgDrawLine.DoModal()==IDOK){
		CDC *pDc=GetDC();
		COLORREF color=RGB(DlgDrawLine.m_LineR,DlgDrawLine.m_LineG,DlgDrawLine.m_LineB);
		hbr=(HBRUSH)::GetStockObject(HOLLOW_BRUSH);
		hpen=CreatePen(PS_SOLID,DlgDrawLine.m_LineWidth,color);
		::SelectObject(pDc->m_hDC,hbr);
		::SelectObject(pDc->m_hDC,hpen);
		DeleteObject(hpen);

		switch(DlgDrawLine.m_Type){
		case 0: //line
			pDc->MoveTo(DlgDrawLine.m_LineX1,DlgDrawLine.m_LineY1);
			pDc->LineTo(DlgDrawLine.m_LineX2,DlgDrawLine.m_LineY2);
			break;
		case 1: //rect
			pDc->Rectangle(DlgDrawLine.m_RectX1,DlgDrawLine.m_RectY1,DlgDrawLine.m_RectX2,DlgDrawLine.m_RectY2);
			break;
		case 2: //oval
			CRect rct=CRect(DlgDrawLine.m_OvalCx-DlgDrawLine.m_OvalH/2,DlgDrawLine.m_OvalCy-DlgDrawLine.m_OvalW/2,DlgDrawLine.m_OvalCx+DlgDrawLine.m_OvalH/2,DlgDrawLine.m_OvalCy+DlgDrawLine.m_OvalW/2);
			rct.NormalizeRect();
			pDc->Ellipse(&rct);
			break;
		}
		ReleaseDC(pDc);	
	}	
}

BOOL CImageProcView::LoadBitmap(CString csfn)
{
	CFile				cfRgb;
	char				msg[256];
	BITMAPFILEHEADER	bf;
	BITMAPINFOHEADER	bi;
	int					NumColors,Offset,LineBytes,ImgSize;
	BYTE				*R,*G,*B,*Gray;
	BYTE				*po,*pr,*pg,*pb,*pgray;
	BYTE				y,r,g,b,gray;
    LPRGBQUAD			lpRGB;
    LOGPALETTE			*pPal;
	int					i;

	if( !cfRgb.Open(csfn, CFile::modeRead)){
		wsprintf(msg,"error: can not open file %s",csfn);
		MessageBox(msg);
		return FALSE;
	}

	mnType=CHILD_ORIG;


	cfRgb.Read((LPSTR)&bf,sizeof(BITMAPFILEHEADER)); 
	cfRgb.Read((LPSTR)&bi,sizeof(BITMAPINFOHEADER));

	mnWidth=bi.biWidth;
	mnHeight=bi.biHeight;
	mnColorBits=bi.biBitCount;

	switch(bi.biBitCount){
		case 8:
            NumColors=256;
            break;
        case 24:
            NumColors=0;
            break;
        default:
			AfxMessageBox("Invalid color numbers!");
			cfRgb.Close();
            return FALSE; 
	}

	LineBytes=(DWORD)WIDTHBYTES(bi.biWidth*bi.biBitCount);
	Offset=sizeof(BITMAPINFOHEADER)+NumColors*sizeof(RGBQUAD);
	ImgSize=(DWORD)LineBytes*bi.biHeight;

	if(mpData!=NULL)
		delete mpData;

	mpData=new BYTE[ImgSize+Offset];
	R=new BYTE[bi.biWidth*bi.biHeight];
	G=new BYTE[bi.biWidth*bi.biHeight];
	B=new BYTE[bi.biWidth*bi.biHeight];
	Gray=new BYTE[bi.biWidth*bi.biHeight];

	cfRgb.Seek(sizeof(BITMAPFILEHEADER),CFile::begin);
	cfRgb.Read(mpData,ImgSize+Offset);
	cfRgb.Close();

	for(int row=0;row<bi.biHeight;row++){
		po=mpData+Offset+row*LineBytes;
		pr=R+row*bi.biWidth;
		pg=G+row*bi.biWidth;
		pb=B+row*bi.biWidth;
		pgray=Gray+row*bi.biWidth;
		for(int col=0;col<bi.biWidth;col++)
		{
			if(NumColors==256){
			    lpRGB = (LPRGBQUAD)((LPSTR)mpData + (DWORD)sizeof(BITMAPINFOHEADER));
				y=*po++;
				lpRGB+=y;
				r=lpRGB->rgbRed;
				g=lpRGB->rgbGreen;
				b=lpRGB->rgbBlue;
			}
			else{
				b=*po++;
				g=*po++;
				r=*po++;
			}
			gray=(BYTE)((int)r*299/1000+(int)g*587/1000+(int)b*114/1000);
			*pr++=r;
			*pg++=g;
			*pb++=b;
			*pgray++=gray;
		}
	}

	CMainFrame *pFrame=(CMainFrame*)AfxGetMainWnd( );
	pFrame->UpdateData(bi.biWidth,bi.biHeight,R,G,B,Gray);

    if(NumColors!=0)
	{                    
	    pPal =(LOGPALETTE *)(new BYTE[sizeof(LOGPALETTE) + NumColors* sizeof(PALETTEENTRY)]);
	    pPal->palNumEntries =(WORD) NumColors;
		pPal->palVersion    = 0x300;
	    lpRGB = (LPRGBQUAD)((LPSTR)mpData + (DWORD)sizeof(BITMAPINFOHEADER));
		for (i = 0; i < NumColors; i++) {
     		pPal->palPalEntry[i].peRed=lpRGB->rgbRed;
			pPal->palPalEntry[i].peGreen=lpRGB->rgbGreen;
			pPal->palPalEntry[i].peBlue=lpRGB->rgbBlue;
			pPal->palPalEntry[i].peFlags=(BYTE)0;
			lpRGB++;
		}
		mhPalette=CreatePalette(pPal);
		delete pPal;
	}

	if(mhBitmap!=NULL)
		DeleteObject(mhBitmap);

	if(mhPalette!=NULL)
		DeleteObject(mhPalette);


	CDC *pDc=GetDC();
	if(mhPalette){
        SelectPalette(pDc->m_hDC,mhPalette,FALSE);
		RealizePalette(pDc->m_hDC);
	}

	mhBitmap=CreateDIBitmap(pDc->m_hDC,(LPBITMAPINFOHEADER)mpData, (LONG)CBM_INIT,
				(LPSTR)mpData+Offset,
   				(LPBITMAPINFO)mpData, DIB_RGB_COLORS);
	ReleaseDC(pDc);	

	m_pDocument->SetTitle(csfn);
	
	return TRUE; 
}

BOOL CImageProcView::ShowBand(int ntype,int w,int h,BYTE *pData)
{
	CFile				cfHeader;
	char				msg[256];
	BITMAPFILEHEADER	bf;
	BITMAPINFOHEADER	bi;
	int					Offset,LineBytes,ImgSize;
	BYTE				*porig,*pthis;
	CString				title;
    LOGPALETTE			*pPal;
    LPRGBQUAD			lpRGB;
	int					i;

//	if( !cfHeader.Open("d:\\ImageProc\\gray.hdr",CFile::modeRead)){
	if( !cfHeader.Open(".\\gray.hdr",CFile::modeRead)){
		wsprintf(msg,"error: can not open file gray.hdr");
		MessageBox(msg);
		return FALSE;
	}

	mnType=ntype;
	
	CMainFrame *pFrame=(CMainFrame*)AfxGetMainWnd( );
	title=pFrame->msFn;
	switch(ntype){
	case CHILD_R:
		title+="-R分量";
		break;
	case CHILD_G:
		title+="-G分量";
		break;
	case CHILD_B:
		title+="-B分量";
		break;
	case CHILD_GRAY:
		title+="-灰度图";
		break;
	default:
		break;
	}

	cfHeader.Read((LPSTR)&bf,sizeof(BITMAPFILEHEADER)); 
	cfHeader.Read((LPSTR)&bi,sizeof(BITMAPINFOHEADER));

	mnWidth=w;
	mnHeight=h;
	mnColorBits=8;

	bi.biWidth=w;
	bi.biHeight=h;

	LineBytes=(DWORD)WIDTHBYTES(w*8);
	Offset=sizeof(BITMAPINFOHEADER)+256*sizeof(RGBQUAD);
	ImgSize=(DWORD)LineBytes*h;

	if(mpData!=NULL)
		delete mpData;

	mpData=new BYTE[ImgSize+Offset];

	memcpy(mpData,(BYTE *)&bi,sizeof(BITMAPINFOHEADER));
	cfHeader.Read(mpData+sizeof(BITMAPINFOHEADER),ImgSize+Offset-sizeof(BITMAPINFOHEADER));
	cfHeader.Close();

	for(int row=0;row<bi.biHeight;row++){
		porig=pData+row*w;
		pthis=mpData+Offset+row*LineBytes;
		memcpy(pthis,porig,w);
	}

    pPal =(LOGPALETTE *)(new BYTE[sizeof(LOGPALETTE) + 256* sizeof(PALETTEENTRY)]);
    pPal->palNumEntries =(WORD) 256;
	pPal->palVersion    = 0x300;
    lpRGB = (LPRGBQUAD)((LPSTR)mpData + (DWORD)sizeof(BITMAPINFOHEADER));
	for (i = 0; i < 256; i++) {
     		pPal->palPalEntry[i].peRed=i;
			pPal->palPalEntry[i].peGreen=i;
			pPal->palPalEntry[i].peBlue=i;
			pPal->palPalEntry[i].peFlags=(BYTE)0;
			lpRGB++;
		}
	mhPalette=CreatePalette(pPal);
	delete pPal;

	if(mhBitmap!=NULL)
		DeleteObject(mhBitmap);

	if(mhPalette!=NULL)
		DeleteObject(mhPalette);

	CDC *pDc=GetDC();
	if(mhPalette){
        SelectPalette(pDc->m_hDC,mhPalette,FALSE);
		RealizePalette(pDc->m_hDC);
	}

	mhBitmap=CreateDIBitmap(pDc->m_hDC,(LPBITMAPINFOHEADER)mpData, (LONG)CBM_INIT,
				(LPSTR)mpData+Offset,
   				(LPBITMAPINFO)mpData, DIB_RGB_COLORS);
	ReleaseDC(pDc);	

	m_pDocument->SetTitle(title);
	return TRUE; 
}

void CImageProcView::OnMouseMove(UINT nFlags, CPoint point) 
{
	// TODO: Add your message handler code here and/or call default
	char				msg[256];
	BYTE				r,g,b,gray;
	BYTE				*p;
    LPRGBQUAD			lpRGB;
	int					Offset,LineBytes;
	int					f=1;
	
	CMainFrame *pFrame=(CMainFrame*)AfxGetMainWnd( );
	wsprintf(msg,"宽:%d",mnWidth);
	pFrame->m_wndStatusBar.SetPaneText(0+f,msg);
	wsprintf(msg,"高:%d",mnHeight);
	pFrame->m_wndStatusBar.SetPaneText(1+f,msg);
	wsprintf(msg,"行:%d",point.y);
	pFrame->m_wndStatusBar.SetPaneText(2+f,msg);
	wsprintf(msg,"列:%d",point.x);
	pFrame->m_wndStatusBar.SetPaneText(3+f,msg);
	if(point.x<=mnWidth&&point.y<=mnHeight){
		if(mnType==CHILD_ORIG){
			LineBytes=(DWORD)WIDTHBYTES(mnWidth*mnColorBits);
			if(mnColorBits==8){
				Offset=sizeof(BITMAPINFOHEADER)+256*sizeof(RGBQUAD);
				p=mpData+Offset+(mnHeight-point.y)*LineBytes+point.x;
				lpRGB = (LPRGBQUAD)((LPSTR)mpData + (DWORD)sizeof(BITMAPINFOHEADER));
				gray=*p++;
				lpRGB+=gray;
				r=lpRGB->rgbRed;
				g=lpRGB->rgbGreen;
				b=lpRGB->rgbBlue;
				gray=(BYTE)((int)r*299/1000+(int)g*587/1000+(int)b*114/1000);
			}
			else{
				Offset=sizeof(BITMAPINFOHEADER);
				p=mpData+Offset+point.y*LineBytes+point.x*3;
				b=*p++;
				g=*p++;
				r=*p++;
				gray=(BYTE)((int)r*299/1000+(int)g*587/1000+(int)b*114/1000);
			}
			wsprintf(msg,"R:%d",r);
			pFrame->m_wndStatusBar.SetPaneText(4+f,msg);
			wsprintf(msg,"G:%d",g);
			pFrame->m_wndStatusBar.SetPaneText(5+f,msg);
			wsprintf(msg,"B:%d",b);
			pFrame->m_wndStatusBar.SetPaneText(6+f,msg);
			wsprintf(msg,"Gray:%d",gray);
			pFrame->m_wndStatusBar.SetPaneText(7+f,msg);
		}
		else{
			LineBytes=(DWORD)WIDTHBYTES(mnWidth*mnColorBits);
			Offset=sizeof(BITMAPINFOHEADER)+256*sizeof(RGBQUAD);
			p=mpData+Offset+(mnHeight-point.y)*LineBytes+point.x;
			lpRGB = (LPRGBQUAD)((LPSTR)mpData + (DWORD)sizeof(BITMAPINFOHEADER));
			gray=*p++;
			wsprintf(msg,"R:0");
			pFrame->m_wndStatusBar.SetPaneText(4+f,msg);
			wsprintf(msg,"G:0");
			pFrame->m_wndStatusBar.SetPaneText(5+f,msg);
			wsprintf(msg,"B:0");
			pFrame->m_wndStatusBar.SetPaneText(6+f,msg);
			wsprintf(msg,"Gray:%d",gray);
			pFrame->m_wndStatusBar.SetPaneText(7+f,msg);
		}
	}
	else{
			wsprintf(msg,"R:0");
			pFrame->m_wndStatusBar.SetPaneText(4+f,msg);
			wsprintf(msg,"G:0");
			pFrame->m_wndStatusBar.SetPaneText(5+f,msg);
			wsprintf(msg,"B:0");
			pFrame->m_wndStatusBar.SetPaneText(6+f,msg);
			wsprintf(msg,"Gray:0");
			pFrame->m_wndStatusBar.SetPaneText(7+f,msg);
	}

	CScrollView::OnMouseMove(nFlags, point);
}

void CImageProcView::OnSave() 
{
	// TODO: Add your command handler code here
	CFile				cfSave;
	CString				csfn;
	BITMAPFILEHEADER	bf;
	int					Offset,LineBytes;
	char				msg[256];

	CMainFrame *pFrame=(CMainFrame*)AfxGetMainWnd( );
	csfn=pFrame->msFn;
	switch(mnType){
	case CHILD_R:
		csfn+="-R分量";
		break;
	case CHILD_G:
		csfn+="-G分量";
		break;
	case CHILD_B:
		csfn+="-B分量";
		break;
	case CHILD_GRAY:
		csfn+="-灰度图";
		break;
	default:
		break;
	}
	csfn+=".bmp";

	if( !cfSave.Open(csfn, CFile::modeCreate|CFile::modeWrite)){
		AfxMessageBox("Can't create file!");
		return;
	}

	LineBytes=(DWORD)WIDTHBYTES(mnWidth*mnColorBits);
	Offset=sizeof(BITMAPFILEHEADER)+sizeof(BITMAPINFOHEADER)+256*sizeof(RGBQUAD);

	bf.bfType=0x424d;
	bf.bfSize=Offset+LineBytes*mnHeight;
	bf.bfReserved1=0;
	bf.bfReserved2=0;
	bf.bfOffBits=Offset;

	cfSave.Write((BYTE *)&bf,sizeof(BITMAPFILEHEADER));
	cfSave.Write(mpData,sizeof(BITMAPINFOHEADER)+256*sizeof(RGBQUAD)+LineBytes*mnHeight);
	cfSave.Close();
	wsprintf(msg,"文件已被保存至%s",csfn);
	pFrame->m_wndStatusBar.SetPaneText(0,msg);
	m_pDocument->SetTitle(csfn);
}

void CImageProcView::OnUpdateSave(CCmdUI* pCmdUI) 
{
	// TODO: Add your command update UI handler code here
	pCmdUI->Enable(mnType!=CHILD_ORIG);	
}
