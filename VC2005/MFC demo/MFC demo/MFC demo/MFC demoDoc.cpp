// MFC demoDoc.cpp : CMFCdemoDoc ���ʵ��
//

#include "stdafx.h"
#include "MFC demo.h"

#include "MFC demoSet.h"
#include "MFC demoDoc.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// CMFCdemoDoc

IMPLEMENT_DYNCREATE(CMFCdemoDoc, CDocument)

BEGIN_MESSAGE_MAP(CMFCdemoDoc, CDocument)
END_MESSAGE_MAP()


// CMFCdemoDoc ����/����

CMFCdemoDoc::CMFCdemoDoc()
{
	// TODO: �ڴ����һ���Թ������

}

CMFCdemoDoc::~CMFCdemoDoc()
{
}

BOOL CMFCdemoDoc::OnNewDocument()
{
	if (!CDocument::OnNewDocument())
		return FALSE;

	// TODO: �ڴ�������³�ʼ������
	// (SDI �ĵ������ø��ĵ�)

	return TRUE;
}




// CMFCdemoDoc ���л�

void CMFCdemoDoc::Serialize(CArchive& ar)
{
	if (ar.IsStoring())
	{
		// TODO: �ڴ���Ӵ洢����
	}
	else
	{
		// TODO: �ڴ���Ӽ��ش���
	}
}


// CMFCdemoDoc ���

#ifdef _DEBUG
void CMFCdemoDoc::AssertValid() const
{
	CDocument::AssertValid();
}

void CMFCdemoDoc::Dump(CDumpContext& dc) const
{
	CDocument::Dump(dc);
}
#endif //_DEBUG


// CMFCdemoDoc ����
