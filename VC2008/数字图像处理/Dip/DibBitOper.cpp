#include "stdafx.h"
#include "DibBitOper.h"
#include <iostream>
using namespace std;

int CDibBitOper::levelBound(int n){
	if(n > 255)
		return 255;
	else if(n < 0)
		return 0;
	else
		return n;
}

BOOL CDibBitOper::Inverse(CDib * pOldDib,CDib *& pNewDib)
{
	if(pNewDib == NULL) {
		pNewDib = new CDib();
	}
	pNewDib->CopyFromDib(pOldDib);
	int nW = pNewDib->Width();
	int nH = pNewDib->Height();
	for(int i = 0; i < nW; i++){
		for(int j = 0; j < nH; j++){
			pNewDib->SetAt(i,j,255 - pNewDib->GetAt(i,j));//求反
		}
	}
	return TRUE;
}

BOOL CDibBitOper::BiValue(CDib * pOldDib,CDib *& pNewDib,BYTE threshold)
{
	if(pNewDib == NULL) {
		pNewDib = new CDib();	
	}
	pNewDib->CopyFromDib(pOldDib);
	int nW = pNewDib->Width();
	int nH = pNewDib->Height();
	for(int i = 0; i < nW; i++){
		for(int j = 0; j < nH; j++){
			if(pNewDib->GetAt(i,j)<threshold)//与阀值比较
				pNewDib->SetAt(i,j,0);
			else
				pNewDib->SetAt(i,j,255);
		}
	}
	return TRUE;
}

BOOL CDibBitOper::Linear(CDib * pOldDib,CDib *& pNewDib,float k, int b)
{
	if(pNewDib == NULL) {
		pNewDib = new CDib();
	}
	pNewDib->CopyFromDib(pOldDib);
	int nW = pNewDib->Width();
	int nH = pNewDib->Height();
	int nValue;
	for(int i = 0; i < nW; i++){
		for(int j = 0; j < nH; j++){
			//亮度调节
			nValue = pNewDib->GetAt(i,j) + b;
			nValue = levelBound(nValue);
			pNewDib->SetAt(i,j,nValue);
			//对比度调节
			int midLevel = 255/2;
			nValue = pNewDib->GetAt(i,j) - midLevel;
			nValue = midLevel + nValue * k;
			nValue = levelBound(nValue);
			pNewDib->SetAt(i,j,nValue);
		}
	}
	return TRUE;
}

BOOL CDibBitOper::Equalization(CDib *pOldDib, CDib *&pNewDib)
{
	if(pNewDib == NULL) {
		pNewDib = new CDib();
	}
	pNewDib->CopyFromDib(pOldDib);
	int Data[1][257];
	int DataStat[256];
	for(int i = 0; i < 257; i++)
		Data[0][i] = 0;
	for(int i = 0; i < 256; i++)
		DataStat[i] = 0;
	CaculateHist(pNewDib,Data);
	int nW = pNewDib->Width();
	int nH = pNewDib->Height();
	int nValue;
	int nPointSum = 0;
	//计算像素的概率分布函数
	for(int i = 0; i < 256; i++){
		nPointSum += Data[0][i];
		DataStat[i] = nPointSum;
	}
	//根据分布函数计算均衡化后新值
	for(int i = 0; i < nW; i++){
		for(int j = 0; j < nH; j++){
			nValue = pNewDib->GetAt(i,j);
			nValue = DataStat[nValue] * 255 / nPointSum ;
			pNewDib->SetAt(i,j,nValue);
		}
	}
	return TRUE;
}

BOOL CDibBitOper::CaculateHist(CDib *pDib, int Data[][257])
{
	int nW = pDib->Width();
	int nH = pDib->Height();
	int nValue;
	int nMostPoints = 0;//单个灰度值所对应的像素个数的最大值
	//计算每个灰度值下的像素个数
	for(int i = 0; i < nW; i++){
		for(int j = 0; j < nH; j++){
			nValue = pDib->GetAt(i,j);
			Data[0][nValue]++;
		}
	}
	//求单个灰度值所对应的像素个数的最大值
	for(int i = 0; i < 256; i++){
		if(Data[0][i] > nMostPoints)
			nMostPoints = Data[0][i];
	}
	//这个最大值放在数组的末尾
	Data[0][256] = nMostPoints;
	return TRUE;
}
