// MFC testDoc.h : CMFCtestDoc ��Ľӿ�
//


#pragma once


class CMFCtestDoc : public CDocument
{
protected: // �������л�����
	CMFCtestDoc();
	DECLARE_DYNCREATE(CMFCtestDoc)

// ����
public:

// ����
public:

// ��д
public:
	virtual BOOL OnNewDocument();
	virtual void Serialize(CArchive& ar);

// ʵ��
public:
	virtual ~CMFCtestDoc();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// ���ɵ���Ϣӳ�亯��
protected:
	DECLARE_MESSAGE_MAP()
};


