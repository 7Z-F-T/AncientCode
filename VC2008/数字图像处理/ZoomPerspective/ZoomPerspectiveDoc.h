// ZoomPerspectiveDoc.h : interface of the CZoomPerspectiveDoc class
//
/////////////////////////////////////////////////////////////////////////////

#if !defined(AFX_ZOOMPERSPECTIVEDOC_H__86FA204D_421D_4E8E_A2DA_713577BC7F0F__INCLUDED_)
#define AFX_ZOOMPERSPECTIVEDOC_H__86FA204D_421D_4E8E_A2DA_713577BC7F0F__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000


class CZoomPerspectiveDoc : public CDocument
{
protected: // create from serialization only
	CZoomPerspectiveDoc();
	DECLARE_DYNCREATE(CZoomPerspectiveDoc)

// Attributes
public:

// Operations
public:

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CZoomPerspectiveDoc)
	public:
	virtual BOOL OnNewDocument();
	virtual void Serialize(CArchive& ar);
	//}}AFX_VIRTUAL

// Implementation
public:
	virtual ~CZoomPerspectiveDoc();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// Generated message map functions
protected:
	//{{AFX_MSG(CZoomPerspectiveDoc)
		// NOTE - the ClassWizard will add and remove member functions here.
		//    DO NOT EDIT what you see in these blocks of generated code !
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_ZOOMPERSPECTIVEDOC_H__86FA204D_421D_4E8E_A2DA_713577BC7F0F__INCLUDED_)
