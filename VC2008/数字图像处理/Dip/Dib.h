#ifndef _DIB_H
#define _DIB_H

const	int nMaxGray=255;
inline	int WIDTHBYTES(int i)			{ return (i%4)? (i-i%4+4) : i ;	}
inline	int LOC(int i,int j,int line)	{ return i+j*line;				}

//������ʾBMP��CDib��
class CDib: public CObject
{
public:
	int GetLineSize();
	int GetBitCount () { return m_pBmInfoHeader->biBitCount; }
	CDib();
	CDib(const char* fileName);				//���ļ���Ϊ�����Ĺ��캯��
	~CDib();
	
	BOOL CopyFromDib(CDib * pOldDib,BOOL sizeChanged=FALSE,
		UINT newWidth=0, UINT newHeight=0);	//����λͼ

	BOOL LoadFromFile(LPCTSTR fileName);	//���ļ�����λͼ
	BOOL SaveToFile(LPCTSTR fileName);		//��λͼд���ļ�

	int		GetAt(int x,int y);				//���ָ�������ɫֵ
	int		Get2At(int x,int y);			//���ָ�������ɫֵ(ԭ��λ��ͼƬ����)
	int		SetAt(int x,int y,int value);	//����ָ�������ɫֵ
	int		Set2At(int x,int y,int value);	//����ָ�������ɫֵ(ԭ��λ��ͼƬ����)

	//���λͼ�Ŀ��
	int		Width()	{ return m_pBmInfoHeader->biWidth; }
	//int		Width()	{ return m_pBIH->biWidth; }
	//���λͼ�ĸ߶�
	int		Height() { return m_pBmInfoHeader->biHeight; }
	//int		Height() { return m_pBIH->biHeight; }
	//���λͼ��ʵ�ʴ�С
	DWORD	ImageSize()	{ return m_pBmInfoHeader->biSizeImage; }
	//���λͼʹ�õ���ɫ��
	DWORD	UsedColors(){ return (DWORD) m_pBmInfoHeader->biClrUsed; }
	DWORD		ImageDataSize();			//����ͼ�������ֽ���
	
	//�����Ϣͷָ��
	LPBITMAPINFOHEADER	GetInfoHeaderPtr()	{ return m_pBmInfoHeader; }
	//�����Ϣָ��
	LPBITMAPINFO		GetInfoPtr()		{ return m_pBmInfo;	}
	//��õ������ָ��
	BYTE *				GetBmBitsPtr()		{ return m_pBmBits;	}
	//����Dib�ļ����ݴ�С
	DWORD				GetDibSize()		{ return m_dwDibSize; }
	//���ص�ɫ����Ŀ
	int					GetPaletteEntries()	{ return m_nPaletteEntries; }
	//����Dib��ʽ����ͷָ��
	unsigned char*		GetDibPtr()			{ return m_pDib; }
	//�������ĵ�����
	CPoint				GetCentre();

	//��ʾ��CDib��װ��λͼ
	int	ShowDib(CDC * pDC,CRect& rect,BOOL bStretch=FALSE);

private:
	void		Initialize();				//��ʼ������
	void		Empty();					//��պ���

private:
	BITMAPINFO *		m_pBmInfo;			//λͼ��Ϣָ��
	BITMAPINFOHEADER *	m_pBmInfoHeader;	//λͼ��Ϣͷָ��
	BYTE *				m_pBmBits;			//λͼ������
	DWORD				m_dwDibSize;		//Dib�ļ����ݴ�С
	int					m_nPaletteEntries;	//��ɫ����Ŀ
	unsigned char*		m_pDib;				//Dib��ʽ����ͷָ��

};

#endif
