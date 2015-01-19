// DibGeoOper1.h: interface for the CDibGeoOper class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_DIBGEOOPER1_H__57AE94C8_3BBD_4C1D_9DB5_800C4A9E2AF3__INCLUDED_)
#define AFX_DIBGEOOPER1_H__57AE94C8_3BBD_4C1D_9DB5_800C4A9E2AF3__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "DibOper.h"

class CDibGeoOper : public CDibOper  
{
public:
	BOOL Perspective(CDib *pOldDib, CDib *&pNewDib, float d, float t, float p, float f);
	BOOL Affine ( CDib* pOldDib, CDib* &pNewDib, float s, float t, float u, float a);
	BOOL Horizon(CDib *pOldDib, CDib *&pNewDib);
	BOOL Vertical(CDib * pOldDib,CDib *& pNewDib);
	CDibGeoOper();
	virtual ~CDibGeoOper();
	

protected:
	CPoint CalcAffine(int x, int y, float s, float t, float u, float a);//�����任
	CPoint CalcAffineInverse(int x, int y, float s, float t, float u, float a);//�������任
	CPoint CalcPerspective(int x, int y, float d, float t, float p, float f);//��͸�ӱ任
	CPoint CalcPerspectiveInverse(int x, int y, float d, float t, float p, float f);//��͸����任
};

#endif // !defined(AFX_DIBGEOOPER1_H__57AE94C8_3BBD_4C1D_9DB5_800C4A9E2AF3__INCLUDED_)
