// MFC demoDoc.h : CMFCdemoDoc ��Ľӿ�
//


#pragma once
#include "MFC demoSet.h"


class CMFCdemoDoc : public CDocument
{
protected: // �������л�����
	CMFCdemoDoc();
	DECLARE_DYNCREATE(CMFCdemoDoc)

// ����
public:
	CMFCdemoSet m_MFCdemoSet;

// ����
public:

// ��д
public:
	virtual BOOL OnNewDocument();
	virtual void Serialize(CArchive& ar);

// ʵ��
public:
	virtual ~CMFCdemoDoc();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// ���ɵ���Ϣӳ�亯��
protected:
	DECLARE_MESSAGE_MAP()
};


