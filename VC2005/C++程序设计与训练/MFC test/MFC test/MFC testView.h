// MFC testView.h : CMFCtestView ��Ľӿ�
//


#pragma once


class CMFCtestView : public CListView
{
protected: // �������л�����
	CMFCtestView();
	DECLARE_DYNCREATE(CMFCtestView)

// ����
public:
	CMFCtestDoc* GetDocument() const;

// ����
public:

// ��д
public:
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);
protected:
	virtual void OnInitialUpdate(); // ������һ�ε���

// ʵ��
public:
	virtual ~CMFCtestView();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// ���ɵ���Ϣӳ�亯��
protected:
	afx_msg void OnStyleChanged(int nStyleType, LPSTYLESTRUCT lpStyleStruct);
	DECLARE_MESSAGE_MAP()
};

#ifndef _DEBUG  // MFC testView.cpp �еĵ��԰汾
inline CMFCtestDoc* CMFCtestView::GetDocument() const
   { return reinterpret_cast<CMFCtestDoc*>(m_pDocument); }
#endif

