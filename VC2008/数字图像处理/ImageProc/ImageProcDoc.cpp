// ImageProcDoc.cpp : implementation of the CImageProcDoc class
//

#include "stdafx.h"
#include "ImageProc.h"

#include "ImageProcDoc.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CImageProcDoc

IMPLEMENT_DYNCREATE(CImageProcDoc, CDocument)

BEGIN_MESSAGE_MAP(CImageProcDoc, CDocument)
	//{{AFX_MSG_MAP(CImageProcDoc)
		// NOTE - the ClassWizard will add and remove mapping macros here.
		//    DO NOT EDIT what you see in these blocks of generated code!
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CImageProcDoc construction/destruction

CImageProcDoc::CImageProcDoc()
{
	// TODO: add one-time construction code here

}

CImageProcDoc::~CImageProcDoc()
{
}

BOOL CImageProcDoc::OnNewDocument()
{
	if (!CDocument::OnNewDocument())
		return FALSE;

	// TODO: add reinitialization code here
	// (SDI documents will reuse this document)

	return TRUE;
}



/////////////////////////////////////////////////////////////////////////////
// CImageProcDoc serialization

void CImageProcDoc::Serialize(CArchive& ar)
{
	if (ar.IsStoring())
	{
		// TODO: add storing code here
	}
	else
	{
		// TODO: add loading code here
	}
}

/////////////////////////////////////////////////////////////////////////////
// CImageProcDoc diagnostics

#ifdef _DEBUG
void CImageProcDoc::AssertValid() const
{
	CDocument::AssertValid();
}

void CImageProcDoc::Dump(CDumpContext& dc) const
{
	CDocument::Dump(dc);
}
#endif //_DEBUG

/////////////////////////////////////////////////////////////////////////////
// CImageProcDoc commands
