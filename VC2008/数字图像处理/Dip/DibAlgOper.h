// DibAlgOper.h: interface for the CDibAlgOper class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_DIBALGOPER_H__2C07AB18_864D_4E08_B7AA_7BF3B02E00C4__INCLUDED_)
#define AFX_DIBALGOPER_H__2C07AB18_864D_4E08_B7AA_7BF3B02E00C4__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "DibOper.h"

class CDibAlgOper : public CDibOper  
{
public:
	BOOL Add(CDib *pSrcDib, CDib *pDestDib, CDib *&pNewDib);
	BOOL Substract ( CDib * pSrcDib, CDib *pDestDib, CDib *& pNewDib );
	BOOL Offset ( CDib * pOldDib,CDib *& pNewDib);
	CDibAlgOper();
	virtual ~CDibAlgOper();

};

#endif // !defined(AFX_DIBALGOPER_H__2C07AB18_864D_4E08_B7AA_7BF3B02E00C4__INCLUDED_)
