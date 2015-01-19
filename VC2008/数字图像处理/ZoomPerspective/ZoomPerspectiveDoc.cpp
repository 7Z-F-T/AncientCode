// ZoomPerspectiveDoc.cpp : implementation of the CZoomPerspectiveDoc class
//

#include "stdafx.h"
#include "ZoomPerspective.h"

#include "ZoomPerspectiveDoc.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CZoomPerspectiveDoc

IMPLEMENT_DYNCREATE(CZoomPerspectiveDoc, CDocument)

BEGIN_MESSAGE_MAP(CZoomPerspectiveDoc, CDocument)
	//{{AFX_MSG_MAP(CZoomPerspectiveDoc)
		// NOTE - the ClassWizard will add and remove mapping macros here.
		//    DO NOT EDIT what you see in these blocks of generated code!
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CZoomPerspectiveDoc construction/destruction

CZoomPerspectiveDoc::CZoomPerspectiveDoc()
{
	// TODO: add one-time construction code here

}

CZoomPerspectiveDoc::~CZoomPerspectiveDoc()
{
}

BOOL CZoomPerspectiveDoc::OnNewDocument()
{
	if (!CDocument::OnNewDocument())
		return FALSE;

	// TODO: add reinitialization code here
	// (SDI documents will reuse this document)

	return TRUE;
}



/////////////////////////////////////////////////////////////////////////////
// CZoomPerspectiveDoc serialization

void CZoomPerspectiveDoc::Serialize(CArchive& ar)
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
// CZoomPerspectiveDoc diagnostics

#ifdef _DEBUG
void CZoomPerspectiveDoc::AssertValid() const
{
	CDocument::AssertValid();
}

void CZoomPerspectiveDoc::Dump(CDumpContext& dc) const
{
	CDocument::Dump(dc);
}
#endif //_DEBUG

/////////////////////////////////////////////////////////////////////////////
// CZoomPerspectiveDoc commands
