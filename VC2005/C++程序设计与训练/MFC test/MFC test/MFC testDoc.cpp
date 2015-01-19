// MFC testDoc.cpp : CMFCtestDoc 类的实现
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


// CMFCtestDoc 构造/析构

CMFCtestDoc::CMFCtestDoc()
{
	// TODO: 在此添加一次性构造代码

}

CMFCtestDoc::~CMFCtestDoc()
{
}

BOOL CMFCtestDoc::OnNewDocument()
{
	if (!CDocument::OnNewDocument())
		return FALSE;

	// TODO: 在此添加重新初始化代码
	// (SDI 文档将重用该文档)

	return TRUE;
}




// CMFCtestDoc 序列化

void CMFCtestDoc::Serialize(CArchive& ar)
{
	if (ar.IsStoring())
	{
		// TODO: 在此添加存储代码
	}
	else
	{
		// TODO: 在此添加加载代码
	}
}


// CMFCtestDoc 诊断

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


// CMFCtestDoc 命令
