#ifndef _DIB_BIT_OPERATION_H
#define _DIB_BIT_OPERATION_H

#include "DibOper.h"

class CDibBitOper : public CDibOper
{
public:
	BOOL CaculateHist ( CDib *pDib, int Data[4][257] );
	BOOL Equalization(CDib * pOldDib,CDib *& pNewDib);
	BOOL Inverse(CDib * pOldDib,CDib *& pNewDib);
	BOOL Linear (CDib * pOldDib,CDib *& pNewDib, float k, int b);
	BOOL BiValue(CDib * pOldDib,CDib *& pNewDib,BYTE threshold);
private:
	int levelBound(int n);
};


#endif