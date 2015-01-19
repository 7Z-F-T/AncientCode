#ifndef _DIB_OPERATOR
#define _DIB_OPERATOR

#ifndef _DIB_CONSTANTS
#define _DIB_CONSTANTS
const double PI=3.1415926535897;
#endif

#include "Dib.h"
#include <math.h>

class CDibOper
{
public:
	CDibOper();
	~CDibOper();
	BOOL CopyDib(CDib * pOldDib,CDib *& pNewDib,BOOL bSizeChanged=FALSE,
		UINT newWidth=0, UINT newHeight=0);
public:
protected:
	void Init();
	void Empty();
protected:
	CDib * m_pOldDib, * m_pNewDib;
	BOOL m_bUseSame;
};
#endif