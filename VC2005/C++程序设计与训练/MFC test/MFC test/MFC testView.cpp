// MFC testView.cpp : CMFCtestView ���ʵ��
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

// CMFCtestView ����/����

CMFCtestView::CMFCtestView()
{
	// TODO: �ڴ˴���ӹ������

}

CMFCtestView::~CMFCtestView()
{
}

BOOL CMFCtestView::PreCreateWindow(CREATESTRUCT& cs)
{
	// TODO: �ڴ˴�ͨ���޸�
	//  CREATESTRUCT cs ���޸Ĵ��������ʽ

	return CListView::PreCreateWindow(cs);
}

void CMFCtestView::OnInitialUpdate()
{
	CListView::OnInitialUpdate();


	// TODO: ���� GetListCtrl() ֱ�ӷ��� ListView ���б�ؼ���
	//  �Ӷ������������ ListView��
}


// CMFCtestView ���

#ifdef _DEBUG
void CMFCtestView::AssertValid() const
{
	CListView::AssertValid();
}

void CMFCtestView::Dump(CDumpContext& dc) const
{
	CListView::Dump(dc);
}

CMFCtestDoc* CMFCtestView::GetDocument() const // �ǵ��԰汾��������
{
	ASSERT(m_pDocument->IsKindOf(RUNTIME_CLASS(CMFCtestDoc)));
	return (CMFCtestDoc*)m_pDocument;
}
#endif //_DEBUG


// CMFCtestView ��Ϣ�������
void CMFCtestView::OnStyleChanged(int nStyleType, LPSTYLESTRUCT lpStyleStruct)
{
	//TODO: ��Ӵ�������Ӧ�û��Դ�����ͼ��ʽ�ĸ���	
	CListView::OnStyleChanged(nStyleType,lpStyleStruct);	
}
