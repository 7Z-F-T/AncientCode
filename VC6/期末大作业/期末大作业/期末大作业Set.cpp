// ��ĩ����ҵSet.cpp : implementation of the CMySet class
//

#include "stdafx.h"
#include "��ĩ����ҵ.h"
#include "��ĩ����ҵSet.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CMySet implementation

IMPLEMENT_DYNAMIC(CMySet, CRecordset)

CMySet::CMySet(CDatabase* pdb)
	: CRecordset(pdb)
{
	//{{AFX_FIELD_INIT(CMySet)
	m_column1 = 0;
	m_column2 = _T("");
	m_column3 = _T("");
	m_column4 = 0;
	m_column5 = _T("");
	m_nFields = 5;
	//}}AFX_FIELD_INIT
	m_nDefaultType = snapshot;
}

CString CMySet::GetDefaultConnect()
{
	return _T("ODBC;DSN=My_DB");
}

CString CMySet::GetDefaultSQL()
{
	return _T("[��1]");
}

void CMySet::DoFieldExchange(CFieldExchange* pFX)
{
	//{{AFX_FIELD_MAP(CMySet)
	pFX->SetFieldType(CFieldExchange::outputColumn);
	RFX_Long(pFX, _T("[���]"), m_column1);
	RFX_Text(pFX, _T("[����]"), m_column2);
	RFX_Text(pFX, _T("[�Ա�]"), m_column3);
	RFX_Long(pFX, _T("[����]"), m_column4);
	RFX_Text(pFX, _T("[����]"), m_column5);
	//}}AFX_FIELD_MAP
}

/////////////////////////////////////////////////////////////////////////////
// CMySet diagnostics

#ifdef _DEBUG
void CMySet::AssertValid() const
{
	CRecordset::AssertValid();
}

void CMySet::Dump(CDumpContext& dc) const
{
	CRecordset::Dump(dc);
}
#endif //_DEBUG




long CMySet::GetMaxID()
{
	MoveLast();		
	return m_column1; 

}
