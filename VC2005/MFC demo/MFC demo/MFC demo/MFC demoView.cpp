// MFC demoView.cpp : CMFCdemoView ���ʵ��
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
	// ��׼��ӡ����
	ON_COMMAND(ID_FILE_PRINT, &COleDBRecordView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_DIRECT, &COleDBRecordView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_PREVIEW, &COleDBRecordView::OnFilePrintPreview)
END_MESSAGE_MAP()

// CMFCdemoView ����/����

CMFCdemoView::CMFCdemoView()
	: COleDBRecordView(CMFCdemoView::IDD)
{
	m_pSet = NULL;
	// TODO: �ڴ˴���ӹ������

}

CMFCdemoView::~CMFCdemoView()
{
}

void CMFCdemoView::DoDataExchange(CDataExchange* pDX)
{
	COleDBRecordView::DoDataExchange(pDX);
	// ���Բ��� DDX_* �����Լ� SetDlgItem*/GetDlgItem* API �����Խ����ݿ����ӵ���ͼ
	// ���� ::SetDlgItemText(m_hWnd, IDC_MYCONTROL, m_pSet->m_MyColumn);
	// �йظ�����Ϣ������� MSDN �� OLEDB ʾ��
}

BOOL CMFCdemoView::PreCreateWindow(CREATESTRUCT& cs)
{
	// TODO: �ڴ˴�ͨ���޸�
	//  CREATESTRUCT cs ���޸Ĵ��������ʽ

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
			// δ�ܴ򿪼�¼��������˼�¼����
			// һ���洢���̣���ȷ���ڵ���
			// OpenAll() ����֮ǰ����ȷ
			// ��ʼ�����е����������

			AfxMessageBox(_T("δ�ܴ򿪼�¼����"), MB_OK);
			// ��Ϊ��û�д��м����������ͼ
			// ���ĵ�ǰ��¼�����¹��ϣ�
			// ���Խ��á���һ�����͡���һ������¼����
			m_bOnFirstRecord = TRUE;
			m_bOnLastRecord = TRUE;
		}
		if( hr == DB_S_ENDOFROWSET )
		{
			// �м��ǿյ�(�������κ���)
			AfxMessageBox(_T("�Ѵ򿪼�¼������δ�����κ��С�"), MB_OK);
			// ���á���һ�����͡���һ������¼����
			m_bOnFirstRecord = TRUE;
			m_bOnLastRecord = TRUE;
		}
	}
	COleDBRecordView::OnInitialUpdate();

}


// CMFCdemoView ��ӡ

BOOL CMFCdemoView::OnPreparePrinting(CPrintInfo* pInfo)
{
	// Ĭ��׼��
	return DoPreparePrinting(pInfo);
}

void CMFCdemoView::OnBeginPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
	// TODO: ��Ӷ���Ĵ�ӡǰ���еĳ�ʼ������
}

void CMFCdemoView::OnEndPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
	// TODO: ��Ӵ�ӡ����е��������
}


// CMFCdemoView ���

#ifdef _DEBUG
void CMFCdemoView::AssertValid() const
{
	COleDBRecordView::AssertValid();
}

void CMFCdemoView::Dump(CDumpContext& dc) const
{
	COleDBRecordView::Dump(dc);
}

CMFCdemoDoc* CMFCdemoView::GetDocument() const // �ǵ��԰汾��������
{
	ASSERT(m_pDocument->IsKindOf(RUNTIME_CLASS(CMFCdemoDoc)));
	return (CMFCdemoDoc*)m_pDocument;
}
#endif //_DEBUG


// CMFCdemoView ���ݿ�֧��
CRowset<>* CMFCdemoView::OnGetRowset()
{
	return m_pSet->GetRowsetBase();
}



// CMFCdemoView ��Ϣ�������
