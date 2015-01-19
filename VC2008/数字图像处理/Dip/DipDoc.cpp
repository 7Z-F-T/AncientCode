// DipDoc.cpp : implementation of the CDipDoc class
//

#include "stdafx.h"
#include "Dip.h"

#include "DipDoc.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CDipDoc

IMPLEMENT_DYNCREATE(CDipDoc, CDocument)

BEGIN_MESSAGE_MAP(CDipDoc, CDocument)
	//{{AFX_MSG_MAP(CDipDoc)
		// NOTE - the ClassWizard will add and remove mapping macros here.
		//    DO NOT EDIT what you see in these blocks of generated code!
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CDipDoc construction/destruction

CDipDoc::CDipDoc()
{
	m_pDib = m_pOldDib = m_pPreDib = NULL;
	m_nPreview = 0;
	m_nCanUndo = 0;
	m_nSteps = 0;
}

CDipDoc::~CDipDoc()
{
}

BOOL CDipDoc::OnNewDocument()
{
	if (!CDocument::OnNewDocument())
		return FALSE;

	CFileDialog fileDlg ( TRUE, "Bitmap", "*.bmp" );
	if ( fileDlg.DoModal() == IDCANCEL )
		return FALSE;

	OnOpenDocument ( fileDlg.GetPathName() );
	return TRUE;
}



/////////////////////////////////////////////////////////////////////////////
// CDipDoc serialization

void CDipDoc::Serialize(CArchive& ar)
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
// CDipDoc diagnostics

#ifdef _DEBUG
void CDipDoc::AssertValid() const
{
	CDocument::AssertValid();
}

void CDipDoc::Dump(CDumpContext& dc) const
{
	CDocument::Dump(dc);
}
#endif //_DEBUG

/////////////////////////////////////////////////////////////////////////////
// CDipDoc commands

BOOL CDipDoc::OnOpenDocument(LPCTSTR lpszPathName) 
{
	if (!CDocument::OnOpenDocument(lpszPathName))
		return FALSE;
	m_pDib = new CDib ();
	if ( !m_pDib->LoadFromFile ( lpszPathName ) )
		return FALSE;
		
	return TRUE;
}

void CDipDoc::SaveResult()
{
	m_nPreview = 0;
	m_pOldDib = m_pDib;
	m_pDib = m_pPreDib;
	m_pPreDib = NULL;
	m_nCanUndo = 1;
	m_nSteps++;
	SetModifiedFlag();
}

BOOL CDipDoc::OnSaveDocument(LPCTSTR lpszPathName) 
{
	SetModifiedFlag(FALSE);
	return m_pDib->SaveToFile(lpszPathName);
}

void CDipDoc::OnCloseDocument() 
{
	delete m_pDib;
	delete m_pOldDib;
	delete m_pPreDib;	
	CDocument::OnCloseDocument();
}
