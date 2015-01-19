// MFC demoView.h : CMFCdemoView 类的接口
//


#pragma once

class CMFCdemoSet;

class CMFCdemoView : public COleDBRecordView
{
protected: // 仅从序列化创建
	CMFCdemoView();
	DECLARE_DYNCREATE(CMFCdemoView)

public:
	enum{ IDD = IDD_MFCDEMO_FORM };
	CMFCdemoSet* m_pSet;

// 属性
public:
	CMFCdemoDoc* GetDocument() const;

// 操作
public:

// 重写
public:
	virtual CRowset<>* OnGetRowset();
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);
protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持
	virtual void OnInitialUpdate(); // 构造后第一次调用
	virtual BOOL OnPreparePrinting(CPrintInfo* pInfo);
	virtual void OnBeginPrinting(CDC* pDC, CPrintInfo* pInfo);
	virtual void OnEndPrinting(CDC* pDC, CPrintInfo* pInfo);

// 实现
public:
	virtual ~CMFCdemoView();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// 生成的消息映射函数
protected:
	DECLARE_MESSAGE_MAP()
};

#ifndef _DEBUG  // MFC demoView.cpp 中的调试版本
inline CMFCdemoDoc* CMFCdemoView::GetDocument() const
   { return reinterpret_cast<CMFCdemoDoc*>(m_pDocument); }
#endif

