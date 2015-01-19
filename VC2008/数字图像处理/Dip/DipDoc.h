// DipDoc.h : interface of the CDipDoc class
//
/////////////////////////////////////////////////////////////////////////////

#if !defined(AFX_DIPDOC_H__C9C253A9_69D5_478B_99F1_06558CE6F661__INCLUDED_)
#define AFX_DIPDOC_H__C9C253A9_69D5_478B_99F1_06558CE6F661__INCLUDED_

#include "Dib.h"	// Added by ClassView
#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000


class CDipDoc : public CDocument
{
protected: // create from serialization only
	CDipDoc();
	DECLARE_DYNCREATE(CDipDoc)

// Attributes
public:

// Operations
public:

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CDipDoc)
	public:
	virtual BOOL OnNewDocument();
	virtual void Serialize(CArchive& ar);
	virtual BOOL OnOpenDocument(LPCTSTR lpszPathName);
	virtual BOOL OnSaveDocument(LPCTSTR lpszPathName);
	virtual void OnCloseDocument();
	//}}AFX_VIRTUAL

// Implementation
public:
	void SaveResult();
	CDib *m_pDib, *m_pPreDib, *m_pOldDib;
	int m_nPreview, m_nCanUndo, m_nSteps;

	virtual ~CDipDoc();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// Generated message map functions
protected:
	//{{AFX_MSG(CDipDoc)
		// NOTE - the ClassWizard will add and remove member functions here.
		//    DO NOT EDIT what you see in these blocks of generated code !
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_DIPDOC_H__C9C253A9_69D5_478B_99F1_06558CE6F661__INCLUDED_)
