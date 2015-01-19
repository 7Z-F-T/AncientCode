// DibMorOper.cpp: implementation of the CDibMorOper class.
//
//////////////////////////////////////////////////////////////////////

#include "stdafx.h"
#include "Dip.h"
#include "DibMorOper.h"
#include "DibAlgOper.h"
#include "DibBitOper.h"

#ifdef _DEBUG
#undef THIS_FILE
static char THIS_FILE[]=__FILE__;
#define new DEBUG_NEW
#endif

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

CDibMorOper::CDibMorOper()
{

}

CDibMorOper::~CDibMorOper()
{

}


BOOL CDibMorOper::Erotion(CDib *pOldDib, CDib *&pNewDib, CMorphType type)
{
	//这里的腐蚀是“黑吃白”
	if(pNewDib == NULL) {
		pNewDib = new CDib();
	}
	pNewDib->CopyFromDib(pOldDib);
	if(pNewDib->GetInfoHeaderPtr()->biClrUsed != 0){
		MessageBox(NULL,"本功能只对二值图像有效！\n此图像非二值图像，即将转化成二值图像后继续","提示",MB_ICONINFORMATION);
		ToBinary(pNewDib);
	}
	int nW = pNewDib->Width();
	int nH = pNewDib->Height();
	type = MORPH_SQUARE;

	SelectTempl(type);
	for(int i = 1; i < nW - 1; i++){
		for(int j = 1; j < nH - 1; j++){
			for(int ii = -m_nSize/2; ii <= m_nSize/2; ii++){
				for(int jj = -m_nSize/2; jj <= m_nSize/2; jj++){
					if(m_nTempl[ii+m_nSize/2][jj+m_nSize/2] == 1 && pOldDib->GetAt(i+ii,j+jj) == 0)
						pNewDib->SetAt(i,j,0);
				}
			}
		}
	}
	return TRUE;
}

void CDibMorOper::SelectTempl(CDibMorOper::CMorphType type)
{
	switch ( type )
	{
	case MORPH_DIAMOND:
		m_nSize = 3;
		m_nTempl[0][0] = 0;m_nTempl[0][1] = 1;m_nTempl[0][2] = 0;
		m_nTempl[1][0] = 1;m_nTempl[1][1] = 1;m_nTempl[1][2] = 1;
		m_nTempl[2][0] = 0;m_nTempl[2][1] = 1;m_nTempl[2][2] = 0;
		break;
	case MORPH_SQUARE:
		m_nSize = 3;
		m_nTempl[0][0] = 1;m_nTempl[0][1] = 1;m_nTempl[0][2] = 1;
		m_nTempl[1][0] = 1;m_nTempl[1][1] = 1;m_nTempl[1][2] = 1;
		m_nTempl[2][0] = 1;m_nTempl[2][1] = 1;m_nTempl[2][2] = 1;
		break;
	}
}

BOOL CDibMorOper::Dilation(CDib *pOldDib, CDib *&pNewDib, CMorphType type)
{
	//这里的膨胀是“白吃黑”
	if(pNewDib == NULL) {
		pNewDib = new CDib();
	}
	pNewDib->CopyFromDib(pOldDib);
	if(pNewDib->GetInfoHeaderPtr()->biClrUsed != 0){
		MessageBox(NULL,"本功能只对二值图像有效！\n此图像非二值图像，即将转化成二值图像后继续","提示",MB_ICONINFORMATION);
		ToBinary(pNewDib);
	}
	int nW = pNewDib->Width();
	int nH = pNewDib->Height();

	SelectTempl(type);
	for(int i = 1; i < nW - 1; i++){
		for(int j = 1; j < nH - 1; j++){
			for(int ii = -m_nSize/2; ii <= m_nSize/2; ii++){
				for(int jj = -m_nSize/2; jj <= m_nSize/2; jj++){
					if(m_nTempl[ii+m_nSize/2][jj+m_nSize/2] == 1 && pOldDib->GetAt(i+ii,j+jj) == 255)
						pNewDib->SetAt(i,j,255);
				}
			}
		}
	}
	return TRUE;
}

BOOL CDibMorOper::Open(CDib *pOldDib, CDib *&pNewDib, CMorphType type)
{
	CDib *pTempDib = NULL;
	Erotion(pOldDib,pTempDib,type);
	Dilation(pTempDib,pNewDib,type);
	return TRUE;	
}

BOOL CDibMorOper::Close(CDib *pOldDib, CDib *&pNewDib, CMorphType type)
{
	CDib *pTempDib = NULL;
	Dilation(pOldDib,pTempDib,type);
	Erotion(pTempDib,pNewDib,type);
	return TRUE;
}

BOOL CDibMorOper::Edge(CDib *pOldDib, CDib *&pNewDib, CMorphType type)
{
	CDib* pTempDib = NULL;
	Erotion(pOldDib,pTempDib,type);
	Substract(pOldDib,pTempDib,pNewDib);
	return TRUE;
}



BOOL CDibMorOper::Skelecton(CDib *pOldDib, CDib *&pNewDib, CMorphType type)
{
	SetCursor(LoadCursor(0, IDC_WAIT));
	
	CDib * pTempDib1 = NULL;
	CDib * pTempDib2 = NULL;
	CDib * pTempDib3 = NULL;
	CDib * pTempDib4 = NULL;
	if(pNewDib == NULL)
		pNewDib = new CDib();
	pNewDib->CopyFromDib(pOldDib);
	MakeBlack(pNewDib);

	
	Open(pOldDib,pTempDib1,type);
	Substract(pOldDib,pTempDib1,pNewDib);

	pTempDib1->CopyFromDib(pOldDib);
	pTempDib1->GetInfoHeaderPtr()->biClrUsed = 0;
	BOOL bFinish = FALSE;
	while(!bFinish){
		Erotion(pTempDib1,pTempDib2,type);
		Open(pTempDib2,pTempDib3,type);
		Substract(pTempDib2,pTempDib3,pTempDib4);
		Or(pNewDib,pTempDib4,pNewDib);
		if(IsBlack(pTempDib2))
			bFinish = TRUE;
		pTempDib1->CopyFromDib(pTempDib2);
	}
	SetCursor(LoadCursor(0, IDC_ARROW));
	return TRUE;
}

BOOL CDibMorOper::Thin(CDib *pOldDib, CDib *&pNewDib)
{
	SetCursor(LoadCursor(0, IDC_WAIT));
	
	// Add your Thinning Algorithm here
	if(pNewDib == NULL) {
		pNewDib = new CDib();
	}
	pNewDib->CopyFromDib(pOldDib);
	if(pNewDib->GetInfoHeaderPtr()->biClrUsed != 0){
		MessageBox(NULL,"本功能只对二值图像有效！\n此图像非二值图像，即将转化成二值图像后继续","提示",MB_ICONINFORMATION);
		ToBinary(pNewDib);
	}
	int nW = pNewDib->Width();
	int nH = pNewDib->Height();
	int DeleteTable[256]= {//查表来确定某点是否可删
		0,0,1,1,0,0,1,1,
		1,1,0,1,1,1,0,1,
		1,1,0,0,1,1,1,1,
		0,0,0,0,0,0,0,1,

		0,0,1,1,0,0,1,1,
		1,1,0,1,1,1,0,1,
		1,1,0,0,1,1,1,1,
		0,0,0,0,0,0,0,1,

		1,1,0,0,1,1,0,0,
		0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,

		1,1,0,0,1,1,0,0,
		1,1,0,1,1,1,0,1,
		0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,

		0,0,1,1,0,0,1,1,
		1,1,0,1,1,1,0,1,
		1,1,0,0,1,1,1,1,
		0,0,0,0,0,0,0,1,

		0,0,1,1,0,0,1,1,
		1,1,0,1,1,1,0,1,
		1,1,0,0,1,1,1,1,
		0,0,0,0,0,0,0,0,

		1,1,0,0,1,1,0,0,
		0,0,0,0,0,0,0,0,
		1,1,0,0,1,1,1,1,
		0,0,0,0,0,0,0,0,

		1,1,0,0,1,1,0,0,
		1,1,0,1,1,1,0,0,
		1,1,0,0,1,1,1,0,
		1,1,0,0,1,0,0,0
	};
	int i,j;
	BOOL bModified = TRUE;
	BOOL bDeleted = FALSE;
	while(bModified){//搜索所有白点，判断是否可删，可删则删除
		bModified = FALSE;
		for(i = 1; i < nH-1; i++){
			j = 1;
			while(j < nW-1){
				if(pNewDib->GetAt(j,i) == 255){
					if(pNewDib->GetAt(j-1,i)==0||pNewDib->GetAt(j+1,i)==0){
						bDeleted = DeletePoint(pNewDib,DeleteTable,j,i);
						if(bDeleted == TRUE){
							bModified = TRUE;
							j++;
						}
					}
				}
				j++;
			}
		}
		for(i = 1; i < nW-1; i++){
			j = 1;
			while(j < nH-1){
				if(pNewDib->GetAt(i,j) == 255){
					if(pNewDib->GetAt(i,j+1)==0||pNewDib->GetAt(i,j-1)==0){
						bDeleted = DeletePoint(pNewDib,DeleteTable,i,j);
						if(bDeleted == TRUE){
							bModified = TRUE;
							j++;
						}
					}
				}
				j++;
			}
		}
	}

	SetCursor(LoadCursor(0, IDC_ARROW));
	return TRUE;
}

BOOL CDibMorOper::DeletePoint(CDib* pDib,int DeleteTable[], int x, int y){
	int nValues[8];
	int nValue;
	int temp;
	GetSurroundings(pDib,x,y,nValues);
	nValue = CalcDeleteTableEntry(nValues);
	temp = DeleteTable[nValue];
	if(temp == 1){//删除！
		pDib->SetAt(x,y,0);
		return TRUE;
	}
	else
		return FALSE;
}
void CDibMorOper::GetSurroundings(CDib * pDib, int x, int y, int surr[] ){
	surr[0] = pDib->GetAt(x-1, y+1);//左上点
	surr[1] = pDib->GetAt(x, y+1);//上点
	surr[2] = pDib->GetAt(x+1, y+1);//右上点
	surr[3] = pDib->GetAt(x-1, y);//左点
	surr[4] = pDib->GetAt(x+1, y);//右点
	surr[5] = pDib->GetAt(x-1, y-1);//左下点
	surr[6] = pDib->GetAt(x, y-1);//下点
	surr[7] = pDib->GetAt(x+1, y-1);//右下点
}

void CDibMorOper::Substract(CDib * pSrcDib, CDib * pDestDib, CDib *& pNewDib){
	if(pNewDib == NULL)
		pNewDib = new CDib();
	pNewDib->CopyFromDib(pSrcDib);
	int nW = pNewDib->Width();
	int nH = pNewDib->Height();
	int nValue;
	for(int i = 0; i < nW - 1; i++){
		for(int j = 0; j < nH - 1; j++){
			nValue = pSrcDib->GetAt(i,j);
			if(nValue == 255){
				nValue = nValue - pDestDib->GetAt(i,j);
				pNewDib->SetAt(i,j,nValue);
			}
		}
	}
}

void CDibMorOper::Or(CDib * pSrcDib, CDib * pDestDib, CDib *& pNewDib){
	if(pNewDib == NULL){
		pNewDib = new CDib();
		pNewDib->CopyFromDib(pSrcDib);
	}
	BOOL test1 = IsBlack(pSrcDib);
	BOOL test2 = IsBlack(pNewDib);
	int nW = pNewDib->Width();
	int nH = pNewDib->Height();
	int nValue;
	for(int i = 0; i < nW - 1; i++){
		for(int j = 0; j < nH - 1; j++){
			nValue = (pSrcDib->GetAt(i,j) + pDestDib->GetAt(i,j) > 0) ? 255 : 0;
			pNewDib->SetAt(i,j,nValue);
		}
	}
}


int CDibMorOper::CalcDeleteTableEntry(int surr[] ){
	int nValue = 0;
	for(int i = 0; i < 8; i++){
		nValue += ((255-surr[i])/255) << i;
	}
	return nValue;
}

void CDibMorOper::MakeBlack(CDib*& pDib){
	int nW = pDib->Width();
	int nH = pDib->Height();
	for(int i = 0; i < nW - 1; i++){
		for(int j = 0; j < nH - 1; j++){
			pDib->SetAt(i,j,0);
		}
	}
}

BOOL CDibMorOper::IsBlack(CDib * pDib){
	int nW = pDib->Width();
	int nH = pDib->Height();
	for(int i = 0; i < nW - 1; i++){
		for(int j = 0; j < nH - 1; j++){
			if(pDib->GetAt(i,j) > 0)
				return FALSE;
		}
	}
	return TRUE;
}

void CDibMorOper::ToBinary(CDib*& pDib){
	int nW = pDib->Width();
	int nH = pDib->Height();
	for(int i = 0; i < nW - 1; i++){
		for(int j = 0; j < nH - 1; j++){
			if(pDib->GetAt(i,j) > 127)
				pDib->SetAt(i,j,255);
			else
				pDib->SetAt(i,j,0);
		}
	}
	pDib->GetInfoHeaderPtr()->biClrUsed = 0;
}