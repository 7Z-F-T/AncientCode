// MFC testDoc.cpp : CMFCtestDoc ���ʵ��
//

#include "stdafx.h"
#include "MFC test.h"

#include "MFC testDoc.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// CMFCtestDoc

IMPLEMENT_DYNCREATE(CMFCtestDoc, CDocument)

BEGIN_MESSAGE_MAP(CMFCtestDoc, CDocument)
END_MESSAGE_MAP()


// CMFCtestDoc ����/����

CMFCtestDoc::CMFCtestDoc()
{
	// TODO: �ڴ����һ���Թ������

}

CMFCtestDoc::~CMFCtestDoc()
{
}

BOOL CMFCtestDoc::OnNewDocument()
{
	if (!CDocument::OnNewDocument())
		return FALSE;

	// TODO: �ڴ�������³�ʼ������
	// (SDI �ĵ������ø��ĵ�)

	return TRUE;
}




// CMFCtestDoc ���л�

void CMFCtestDoc::Serialize(CArchive& ar)
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


// CMFCtestDoc ���

#ifdef _DEBUG
void CMFCtestDoc::AssertValid() const
{
	CDocument::AssertValid();
}

void CMFCtestDoc::Dump(CDumpContext& dc) const
{
	CDocument::Dump(dc);
}
#endif //_DEBUG


// CMFCtestDoc ����
