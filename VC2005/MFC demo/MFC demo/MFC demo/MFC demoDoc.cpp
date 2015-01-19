// MFC demoDoc.cpp : CMFCdemoDoc 类的实现
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


// CMFCdemoDoc 构造/析构

CMFCdemoDoc::CMFCdemoDoc()
{
	// TODO: 在此添加一次性构造代码

}

CMFCdemoDoc::~CMFCdemoDoc()
{
}

BOOL CMFCdemoDoc::OnNewDocument()
{
	if (!CDocument::OnNewDocument())
		return FALSE;

	// TODO: 在此添加重新初始化代码
	// (SDI 文档将重用该文档)

	return TRUE;
}




// CMFCdemoDoc 序列化

void CMFCdemoDoc::Serialize(CArchive& ar)
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


// CMFCdemoDoc 诊断

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


// CMFCdemoDoc 命令
