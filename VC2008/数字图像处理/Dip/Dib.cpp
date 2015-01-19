#include "stdafx.h"
#include "windows.h"
#include "windowsx.h"
#include "Dib.h"
#include <wingdi.h>


CDib::CDib()
{
	Initialize();
}

CDib::CDib(const char* fileName)
{
	Initialize();
	LoadFromFile(fileName);		//读入位图并做初始化
}
//初始化
void CDib::Initialize()
{
	m_pBmInfo		= 0;
	m_pBmInfoHeader = 0;
	m_pBmBits		= 0;
	m_dwDibSize		= 0;
	m_nPaletteEntries = 0;
	m_pDib = NULL;
}

CDib::~CDib()
{
	//Empty();
}

//清空函数
void CDib::Empty()
{
	if(m_pBmInfo)
		GlobalFreePtr(m_pBmInfo);		//释放位图占用的内存
}

//从文件读入位图
BOOL CDib::LoadFromFile (LPCTSTR fileName){
	
	CFile file;
	if (!file.Open(fileName, CFile::modeRead))
		return (FALSE);

	DWORD dwDibSize;
	dwDibSize = file.GetLength() - sizeof(BITMAPFILEHEADER);

	unsigned char *pDib;
	pDib = new unsigned char[dwDibSize];
	if (pDib == NULL)
		return (FALSE);
	
	BITMAPFILEHEADER BFH;

	if (file.Read(&BFH, sizeof(BITMAPFILEHEADER)) != sizeof(BITMAPFILEHEADER) || 
		BFH.bfType != 'MB' || file.Read(pDib, dwDibSize) != dwDibSize){
		delete []pDib;
		return (FALSE);
	}

	if(m_pDib != NULL) 
		delete m_pDib;
	if(m_pBmBits != NULL) 
		delete m_pBmBits;
	
	m_pDib = pDib;
	m_dwDibSize = dwDibSize;

	m_pBmInfo = (BITMAPINFO*)pDib;
	m_pBmInfoHeader = (BITMAPINFOHEADER*)pDib;
	
	if(m_pBmInfoHeader->biBitCount > 8){
		MessageBox(NULL,"本系统暂不支持彩色图像处理功能。请打开灰度图片。","错误",MB_ICONSTOP);
		return FALSE;
	}

	m_nPaletteEntries = 1 << m_pBmInfoHeader->biBitCount;

	m_pBmBits = &pDib[sizeof(BITMAPINFOHEADER)+m_nPaletteEntries*sizeof(RGBQUAD)];

	
	return TRUE;
}
//把位图写入文件
BOOL CDib::SaveToFile(LPCTSTR fileName)
{
	if (m_pBmBits == NULL)
		return (FALSE);
	
	CFile file;
	if (!file.Open(fileName, CFile::modeCreate | CFile::modeWrite))
		return (FALSE);
	
	BITMAPFILEHEADER BFH;
	memset(&BFH, 0, sizeof(BITMAPFILEHEADER));
	BFH.bfType = 'MB';
	BFH.bfSize = sizeof(BITMAPFILEHEADER) + m_dwDibSize;
	BFH.bfOffBits = sizeof(BITMAPFILEHEADER) + 
		sizeof(BITMAPINFOHEADER) + m_nPaletteEntries * sizeof(RGBQUAD);
	file.Write(&BFH, sizeof(BITMAPFILEHEADER));
	file.Write(m_pDib, m_dwDibSize);

	return TRUE;
}

//拷贝位图
BOOL CDib::CopyFromDib(CDib * pOldDib,BOOL bSizeChanged,
	UINT newWidth, UINT newHeight)
{
	if(!bSizeChanged){
		m_dwDibSize = pOldDib->GetDibSize();
		m_nPaletteEntries = pOldDib->GetPaletteEntries();
		if(m_pDib != NULL)
			delete [] m_pDib;
		m_pDib = new unsigned char[m_dwDibSize];
		memcpy(m_pDib, pOldDib->GetDibPtr(), m_dwDibSize);
		m_pBmBits = &m_pDib[sizeof(BITMAPINFOHEADER) + m_nPaletteEntries * sizeof(RGBQUAD)];
		m_pBmInfo = (BITMAPINFO*)m_pDib;
		m_pBmInfoHeader = (BITMAPINFOHEADER*)m_pDib;
	}
	else{
		BITMAPINFOHEADER* pH = pOldDib->GetInfoHeaderPtr();
		m_nPaletteEntries = pOldDib->GetPaletteEntries();
		m_dwDibSize = sizeof(BITMAPINFOHEADER) + m_nPaletteEntries*sizeof(RGBQUAD) + newWidth*newHeight*pH->biBitCount/sizeof(BYTE);
		if(m_pDib != NULL)
			delete [] m_pDib;
		m_pDib = new unsigned char[m_dwDibSize];
		memcpy(m_pDib, pOldDib->GetDibPtr(), sizeof(BITMAPINFOHEADER) + m_nPaletteEntries * sizeof(RGBQUAD));
		m_pBmBits = &m_pDib[sizeof(BITMAPINFOHEADER) + m_nPaletteEntries * sizeof(RGBQUAD)];
		m_pBmInfo = (BITMAPINFO*)m_pDib;
		m_pBmInfoHeader = (BITMAPINFOHEADER*)m_pDib;
		m_pBmInfoHeader->biWidth = newWidth;
		m_pBmInfoHeader->biHeight = newHeight;
		m_pBmInfoHeader->biSizeImage = ImageDataSize();
		memset(m_pBmBits,BYTE(0),ImageDataSize());
		
	}

	return TRUE;
}

//获得指定点的颜色值
int CDib::GetAt(int x,int y)
{
	if((m_pBmInfo==NULL)||(x<0)||(x>=Width())||(y<0)||(y>=Height()))
		return -1;
	return m_pBmBits [ y*WIDTHBYTES(Width()) + x ];
}

//设置指定点的颜色值
int CDib::SetAt(int x,int y,int value)
{
	if((m_pBmInfo==NULL)||(x<0)||(x>=Width())||(y<0)||(y>=Height())||(value>nMaxGray))
		return -1;
	m_pBmBits [ y*WIDTHBYTES(Width()) + x ] = value;
	return 0;
}
//返回中心点坐标
CPoint CDib::GetCentre(){
	return CPoint(Width()/2, Height()/2);
}
//获得指定点的颜色值(原点位于图片中央)
int CDib::Get2At(int x, int y){
	return GetAt(x + Width()/2, y + Height()/2);
}
//设置指定点的颜色值(原点位于图片中央)
int CDib::Set2At(int x, int y, int value){
	return SetAt(x + Width()/2, y + Height()/2,value);
}

//计算图像数据字节数
DWORD CDib::ImageDataSize()
{
	return GetLineSize()*Height();
}

//显示由CDib封装的位图，bStretch为是否拉伸
int CDib::ShowDib(CDC * pDC,CRect& rect,BOOL bStretch)
{	
	if (m_pBmBits == NULL)
		return (FALSE);
	int nX = rect.TopLeft().x;
	int nY = rect.TopLeft().y;
	int nWidth, nHeight;
	if(bStretch == TRUE){
		nWidth = rect.Width();
		nHeight = rect.Height();
	}else{
		nWidth = Width();
		nHeight = Height();
	}
	StretchDIBits(pDC->m_hDC, nX, nY, nWidth, nHeight, 0, 0, m_pBmInfoHeader->biWidth, m_pBmInfoHeader->biHeight, m_pBmBits, m_pBmInfo, BI_RGB, SRCCOPY);
	return 0;
}

int CDib::GetLineSize()
{
	int width = Width();
	int nbits = GetBitCount() / 8;
	width *= nbits;
	return WIDTHBYTES(width);
}
