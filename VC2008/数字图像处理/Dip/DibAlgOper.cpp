// DibAlgOper.cpp: implementation of the CDibAlgOper class.
//
//////////////////////////////////////////////////////////////////////

#include "stdafx.h"
#include "Dip.h"
#include "DibAlgOper.h"

#ifdef _DEBUG
#undef THIS_FILE
static char THIS_FILE[]=__FILE__;
#define new DEBUG_NEW
#endif

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

CDibAlgOper::CDibAlgOper()
{

}

CDibAlgOper::~CDibAlgOper()
{

}

BOOL CDibAlgOper::Offset(CDib * pOldDib,CDib *& pNewDib)
{
	if(pNewDib == NULL) {
		pNewDib = new CDib();
	}
	pNewDib->CopyFromDib(pOldDib);
	int nW = pNewDib->Width();
	int nH = pNewDib->Height();
	int nValue;
	for(int i = 0; i < nW - 1; i++){
		for(int j = 0; j < nH - 1; j++){
			nValue = pNewDib->GetAt(i+1,j+1) - pOldDib->GetAt(i,j);//平移并相减
			pNewDib->SetAt(i,j,nValue/2 + 127);//调整至可视范围内
		}
	}
	for(int i = 0; i < nW; i++){
		pNewDib->SetAt(i,nH,255);
		pNewDib->SetAt(nW,i,255);
	}
	return TRUE;
}

BOOL CDibAlgOper::Substract(CDib *pSrcDib, CDib *pDestDib, CDib *&pNewDib)
{
	if(pNewDib == NULL) {
		pNewDib = new CDib();
	}
	pNewDib->CopyFromDib(pSrcDib);
	int nW = pNewDib->Width();
	int nH = pNewDib->Height();
	int nValue;
	for(int i = 0; i < nW - 1; i++){
		for(int j = 0; j < nH - 1; j++){
			nValue = pSrcDib->GetAt(i,j) - pDestDib->GetAt(i,j);//相减运算
			pNewDib->SetAt(i,j,(nValue+255)/2);//调整至可视范围
		}
	}
	return TRUE;
}

BOOL CDibAlgOper::Add(CDib *pSrcDib, CDib *pDestDib, CDib *&pNewDib)
{
	if(pNewDib == NULL) {
		pNewDib = new CDib();
	}
	pNewDib->CopyFromDib(pSrcDib);
	int nW = pNewDib->Width();
	int nH = pNewDib->Height();
	int nValue;
	for(int i = 0; i < nW - 1; i++){
		for(int j = 0; j < nH - 1; j++){
			nValue = pSrcDib->GetAt(i,j) + pDestDib->GetAt(i,j);//相加运算
			pNewDib->SetAt(i,j,nValue/2);//调整至可视范围
		}
	}
	return TRUE;
}
