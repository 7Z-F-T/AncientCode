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
	BOOL DeletePoint(CDib* pDib,int DeleteTable[], int x, int y);//判断是否删除某白点，是则删除
	void GetSurroundings (CDib* pDib, int x, int y, int surr[] );//得到某点周围8个点的情况
	int CalcDeleteTableEntry( int surr[] );//查表哈希函数
	void Substract(CDib * pSrcDib, CDib * pDestDib, CDib *& pNewDib);//减运算
	void Or(CDib * pSrcDib, CDib * pDestDib, CDib *& pNewDib);//或运算
	void MakeBlack(CDib*& pDib);//使所有点变黑
	BOOL IsBlack(CDib* pDib);//判断是否所有点都是黑点
	void ToBinary(CDib*& pDib);//二值化
};

#endif // !defined(AFX_DIBMOROPER_H__7C8EB703_0DF6_4D80_86F0_237269E8169F__INCLUDED_)
