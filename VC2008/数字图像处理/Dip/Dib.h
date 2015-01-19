#ifndef _DIB_H
#define _DIB_H

const	int nMaxGray=255;
inline	int WIDTHBYTES(int i)			{ return (i%4)? (i-i%4+4) : i ;	}
inline	int LOC(int i,int j,int line)	{ return i+j*line;				}

//用于显示BMP的CDib类
class CDib: public CObject
{
public:
	int GetLineSize();
	int GetBitCount () { return m_pBmInfoHeader->biBitCount; }
	CDib();
	CDib(const char* fileName);				//以文件名为参数的构造函数
	~CDib();
	
	BOOL CopyFromDib(CDib * pOldDib,BOOL sizeChanged=FALSE,
		UINT newWidth=0, UINT newHeight=0);	//拷贝位图

	BOOL LoadFromFile(LPCTSTR fileName);	//从文件读入位图
	BOOL SaveToFile(LPCTSTR fileName);		//把位图写入文件

	int		GetAt(int x,int y);				//获得指定点的颜色值
	int		Get2At(int x,int y);			//获得指定点的颜色值(原点位于图片中央)
	int		SetAt(int x,int y,int value);	//设置指定点的颜色值
	int		Set2At(int x,int y,int value);	//设置指定点的颜色值(原点位于图片中央)

	//获得位图的宽度
	int		Width()	{ return m_pBmInfoHeader->biWidth; }
	//int		Width()	{ return m_pBIH->biWidth; }
	//获得位图的高度
	int		Height() { return m_pBmInfoHeader->biHeight; }
	//int		Height() { return m_pBIH->biHeight; }
	//获得位图的实际大小
	DWORD	ImageSize()	{ return m_pBmInfoHeader->biSizeImage; }
	//获得位图使用的颜色数
	DWORD	UsedColors(){ return (DWORD) m_pBmInfoHeader->biClrUsed; }
	DWORD		ImageDataSize();			//计算图像数据字节数
	
	//获得信息头指针
	LPBITMAPINFOHEADER	GetInfoHeaderPtr()	{ return m_pBmInfoHeader; }
	//获得信息指针
	LPBITMAPINFO		GetInfoPtr()		{ return m_pBmInfo;	}
	//获得点数组的指针
	BYTE *				GetBmBitsPtr()		{ return m_pBmBits;	}
	//返回Dib文件内容大小
	DWORD				GetDibSize()		{ return m_dwDibSize; }
	//返回调色板数目
	int					GetPaletteEntries()	{ return m_nPaletteEntries; }
	//返回Dib格式数据头指针
	unsigned char*		GetDibPtr()			{ return m_pDib; }
	//返回中心点坐标
	CPoint				GetCentre();

	//显示由CDib封装的位图
	int	ShowDib(CDC * pDC,CRect& rect,BOOL bStretch=FALSE);

private:
	void		Initialize();				//初始化函数
	void		Empty();					//清空函数

private:
	BITMAPINFO *		m_pBmInfo;			//位图信息指针
	BITMAPINFOHEADER *	m_pBmInfoHeader;	//位图信息头指针
	BYTE *				m_pBmBits;			//位图点数组
	DWORD				m_dwDibSize;		//Dib文件内容大小
	int					m_nPaletteEntries;	//调色板数目
	unsigned char*		m_pDib;				//Dib格式数据头指针

};

#endif
