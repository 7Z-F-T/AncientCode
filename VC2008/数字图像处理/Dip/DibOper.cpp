#include "stdafx.h"
#include "DibOper.h"

CDibOper::CDibOper()
{
	Init();
}
CDibOper::~CDibOper()
{
	Empty();
}
void CDibOper::Init()
{
	m_pOldDib = m_pNewDib = NULL;
}
void CDibOper::Empty()
{
}

BOOL CDibOper::CopyDib(CDib * pOldDib,CDib *& pNewDib,BOOL bSizeChanged,
	UINT newWidth, UINT newHeight)
{
	if(pNewDib == NULL)
		pNewDib = new CDib;
	pNewDib->CopyFromDib(pOldDib,bSizeChanged,newWidth,newHeight);
	return TRUE;
}