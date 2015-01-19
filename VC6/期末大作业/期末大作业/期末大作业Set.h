// 期末大作业Set.h : interface of the CMySet class
//
/////////////////////////////////////////////////////////////////////////////

#if !defined(AFX_SET_H__FE73C110_2081_46BC_9102_2A69B07FD6F2__INCLUDED_)
#define AFX_SET_H__FE73C110_2081_46BC_9102_2A69B07FD6F2__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

class CMySet : public CRecordset
{
public:
	long GetMaxID();
	CMySet(CDatabase* pDatabase = NULL);
	DECLARE_DYNAMIC(CMySet)

// Field/Param Data
	//{{AFX_FIELD(CMySet, CRecordset)
	long	m_column1;
	CString	m_column2;
	CString	m_column3;
	long	m_column4;
	CString	m_column5;
	//}}AFX_FIELD

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CMySet)
	public:
	virtual CString GetDefaultConnect();	// Default connection string
	virtual CString GetDefaultSQL(); 	// default SQL for Recordset
	virtual void DoFieldExchange(CFieldExchange* pFX);	// RFX support
	//}}AFX_VIRTUAL

// Implementation
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

};

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_SET_H__FE73C110_2081_46BC_9102_2A69B07FD6F2__INCLUDED_)

