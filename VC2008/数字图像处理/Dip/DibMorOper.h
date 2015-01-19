// DibMorOper.h: interface for the CDibMorOper class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_DIBMOROPER_H__7C8EB703_0DF6_4D80_86F0_237269E8169F__INCLUDED_)
#define AFX_DIBMOROPER_H__7C8EB703_0DF6_4D80_86F0_237269E8169F__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "DibOper.h"

class CDibMorOper : public CDibOper  
{
public:
	enum CMorphType {
		MORPH_DIAMOND,
		MORPH_SQUARE
	};

public:
	BOOL Thin(CDib *pOldDib, CDib *&pNewDib);
	BOOL Skelecton (CDib *pOldDib, CDib *&pNewDib, CMorphType type);
	BOOL Edge (CDib *pOldDib, CDib *&pNewDib, CMorphType type);
	BOOL Close (CDib *pOldDib, CDib *&pNewDib, CMorphType type);
	BOOL Open ( CDib *pOldDib, CDib *&pNewDib, CMorphType type );
	BOOL Dilation(CDib *pOldDib, CDib *&pNewDib, CMorphType type);
	BOOL Erotion ( CDib *pOldDib, CDib *&pNewDib, CDibMorOper::CMorphType type );
	CDibMorOper();
	virtual ~CDibMorOper();

protected:
	int m_nTempl[10][10];
	int m_nSize;
	void SelectTempl ( CDibMorOper::CMorphType type );
	BOOL DeletePoint(CDib* pDib,int DeleteTable[], int x, int y);//�ж��Ƿ�ɾ��ĳ�׵㣬����ɾ��
	void GetSurroundings (CDib* pDib, int x, int y, int surr[] );//�õ�ĳ����Χ8��������
	int CalcDeleteTableEntry( int surr[] );//����ϣ����
	void Substract(CDib * pSrcDib, CDib * pDestDib, CDib *& pNewDib);//������
	void Or(CDib * pSrcDib, CDib * pDestDib, CDib *& pNewDib);//������
	void MakeBlack(CDib*& pDib);//ʹ���е���
	BOOL IsBlack(CDib* pDib);//�ж��Ƿ����е㶼�Ǻڵ�
	void ToBinary(CDib*& pDib);//��ֵ��
};

#endif // !defined(AFX_DIBMOROPER_H__7C8EB703_0DF6_4D80_86F0_237269E8169F__INCLUDED_)
