// MFC testView.cpp : CMFCtestView 类的实现
//

#include "stdafx.h"
#include "MFC test.h"

#include "MFC testDoc.h"
#include "MFC testView.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// CMFCtestView

IMPLEMENT_DYNCREATE(CMFCtestView, CListView)

BEGIN_MESSAGE_MAP(CMFCtestView, CListView)
	ON_WM_STYLECHANGED()
END_MESSAGE_MAP()

// CMFCtestView 构造/析构

CMFCtestView::CMFCtestView()
{
	// TODO: 在此处添加构造代码

}

CMFCtestView::~CMFCtestView()
{
}

BOOL CMFCtestView::PreCreateWindow(CREATESTRUCT& cs)
{
	// TODO: 在此处通过修改
	//  CREATESTRUCT cs 来修改窗口类或样式

	return CListView::PreCreateWindow(cs);
}

void CMFCtestView::OnInitialUpdate()
{
	CListView::OnInitialUpdate();


	// TODO: 调用 GetListCtrl() 直接访问 ListView 的列表控件，
	//  从而可以用项填充 ListView。
}


// CMFCtestView 诊断

#ifdef _DEBUG
void CMFCtestView::AssertValid() const
{
	CListView::AssertValid();
}

void CMFCtestView::Dump(CDumpContext& dc) const
{
	CListView::Dump(dc);
}

CMFCtestDoc* CMFCtestView::GetDocument() const // 非调试版本是内联的
{
	ASSERT(m_pDocument->IsKindOf(RUNTIME_CLASS(CMFCtestDoc)));
	return (CMFCtestDoc*)m_pDocument;
}
#endif //_DEBUG


// CMFCtestView 消息处理程序
void CMFCtestView::OnStyleChanged(int nStyleType, LPSTYLESTRUCT lpStyleStruct)
{
	//TODO: 添加代码以响应用户对窗口视图样式的更改	
	CListView::OnStyleChanged(nStyleType,lpStyleStruct);	
}
