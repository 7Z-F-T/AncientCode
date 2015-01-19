// MFC demoView.cpp : CMFCdemoView 类的实现
//

#include "stdafx.h"
#include "MFC demo.h"

#include "MFC demoSet.h"
#include "MFC demoDoc.h"
#include "MFC demoView.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// CMFCdemoView

IMPLEMENT_DYNCREATE(CMFCdemoView, COleDBRecordView)

BEGIN_MESSAGE_MAP(CMFCdemoView, COleDBRecordView)
	// 标准打印命令
	ON_COMMAND(ID_FILE_PRINT, &COleDBRecordView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_DIRECT, &COleDBRecordView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_PREVIEW, &COleDBRecordView::OnFilePrintPreview)
END_MESSAGE_MAP()

// CMFCdemoView 构造/析构

CMFCdemoView::CMFCdemoView()
	: COleDBRecordView(CMFCdemoView::IDD)
{
	m_pSet = NULL;
	// TODO: 在此处添加构造代码

}

CMFCdemoView::~CMFCdemoView()
{
}

void CMFCdemoView::DoDataExchange(CDataExchange* pDX)
{
	COleDBRecordView::DoDataExchange(pDX);
	// 可以插入 DDX_* 函数以及 SetDlgItem*/GetDlgItem* API 调用以将数据库链接到视图
	// 例如 ::SetDlgItemText(m_hWnd, IDC_MYCONTROL, m_pSet->m_MyColumn);
	// 有关更多信息，请参阅 MSDN 和 OLEDB 示例
}

BOOL CMFCdemoView::PreCreateWindow(CREATESTRUCT& cs)
{
	// TODO: 在此处通过修改
	//  CREATESTRUCT cs 来修改窗口类或样式

	return COleDBRecordView::PreCreateWindow(cs);
}

void CMFCdemoView::OnInitialUpdate()
{
	m_pSet = &GetDocument()->m_MFCdemoSet;
	{
		CWaitCursor wait;
		HRESULT hr = m_pSet->OpenAll();
		if (FAILED(hr))
		{
			// 未能打开记录集。如果此记录集是
			// 一个存储过程，则确保在调用
			// OpenAll() 方法之前已正确
			// 初始化所有的输入参数。

			AfxMessageBox(_T("未能打开记录集。"), MB_OK);
			// 因为在没有打开行集的情况下试图
			// 更改当前记录将导致故障，
			// 所以禁用“下一个”和“上一个”记录命令
			m_bOnFirstRecord = TRUE;
			m_bOnLastRecord = TRUE;
		}
		if( hr == DB_S_ENDOFROWSET )
		{
			// 行集是空的(不包含任何行)
			AfxMessageBox(_T("已打开记录集，但未返回任何行。"), MB_OK);
			// 禁用“下一个”和“上一个”记录命令
			m_bOnFirstRecord = TRUE;
			m_bOnLastRecord = TRUE;
		}
	}
	COleDBRecordView::OnInitialUpdate();

}


// CMFCdemoView 打印

BOOL CMFCdemoView::OnPreparePrinting(CPrintInfo* pInfo)
{
	// 默认准备
	return DoPreparePrinting(pInfo);
}

void CMFCdemoView::OnBeginPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
	// TODO: 添加额外的打印前进行的初始化过程
}

void CMFCdemoView::OnEndPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
	// TODO: 添加打印后进行的清除过程
}


// CMFCdemoView 诊断

#ifdef _DEBUG
void CMFCdemoView::AssertValid() const
{
	COleDBRecordView::AssertValid();
}

void CMFCdemoView::Dump(CDumpContext& dc) const
{
	COleDBRecordView::Dump(dc);
}

CMFCdemoDoc* CMFCdemoView::GetDocument() const // 非调试版本是内联的
{
	ASSERT(m_pDocument->IsKindOf(RUNTIME_CLASS(CMFCdemoDoc)));
	return (CMFCdemoDoc*)m_pDocument;
}
#endif //_DEBUG


// CMFCdemoView 数据库支持
CRowset<>* CMFCdemoView::OnGetRowset()
{
	return m_pSet->GetRowsetBase();
}



// CMFCdemoView 消息处理程序
