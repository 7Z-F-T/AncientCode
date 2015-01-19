// MFC demoView.h : CMFCdemoView ��Ľӿ�
//


#pragma once

class CMFCdemoSet;

class CMFCdemoView : public COleDBRecordView
{
protected: // �������л�����
	CMFCdemoView();
	DECLARE_DYNCREATE(CMFCdemoView)

public:
	enum{ IDD = IDD_MFCDEMO_FORM };
	CMFCdemoSet* m_pSet;

// ����
public:
	CMFCdemoDoc* GetDocument() const;

// ����
public:

// ��д
public:
	virtual CRowset<>* OnGetRowset();
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);
protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV ֧��
	virtual void OnInitialUpdate(); // ������һ�ε���
	virtual BOOL OnPreparePrinting(CPrintInfo* pInfo);
	virtual void OnBeginPrinting(CDC* pDC, CPrintInfo* pInfo);
	virtual void OnEndPrinting(CDC* pDC, CPrintInfo* pInfo);

// ʵ��
public:
	virtual ~CMFCdemoView();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// ���ɵ���Ϣӳ�亯��
protected:
	DECLARE_MESSAGE_MAP()
};

#ifndef _DEBUG  // MFC demoView.cpp �еĵ��԰汾
inline CMFCdemoDoc* CMFCdemoView::GetDocument() const
   { return reinterpret_cast<CMFCdemoDoc*>(m_pDocument); }
#endif

