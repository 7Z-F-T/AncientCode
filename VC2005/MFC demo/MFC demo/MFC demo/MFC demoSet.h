// MFC demoSet.h: CMFCdemoSet ��Ľӿ�
//


#pragma once

// ���������� 2006��6��5��, 19:25

class CMFCdemoSetAccessor
{
public:
	TCHAR m_column0[51];
	TCHAR m_column1[51];
	TCHAR m_column2[51];
	TCHAR m_column3[51];
	TCHAR m_column4[51];

	// ���������ɵ����ݳ�Ա����
	//��ӳ������Ӧ�ֶε�״ֵ̬��
	// ����ʹ����Щֵ�������ݿⷵ�ص� NULL ֵ���ڱ���������
	// ����ʱ���������Ϣ���й����ʹ��
	//��Щ�ֶε���ϸ��Ϣ��
	// ��μ� Visual C++ �ĵ��е�
	//�������ɵķ������е��ֶ�״̬���ݳ�Ա����
	// ע��: ������/��������ǰ�����ʼ����Щ�ֶ�!

	DBSTATUS m_dwcolumn0Status;
	DBSTATUS m_dwcolumn1Status;
	DBSTATUS m_dwcolumn2Status;
	DBSTATUS m_dwcolumn3Status;
	DBSTATUS m_dwcolumn4Status;

	// ���������ɵ����ݳ�Ա����
	//��ӳ������Ӧ�ֶεĳ���ֵ��
	// ע��: �Ա䳤�У�����������/����
	//       ����ǰ��ʼ����Щ�ֶ�!

	DBLENGTH m_dwcolumn0Length;
	DBLENGTH m_dwcolumn1Length;
	DBLENGTH m_dwcolumn2Length;
	DBLENGTH m_dwcolumn3Length;
	DBLENGTH m_dwcolumn4Length;


	void GetRowsetProperties(CDBPropSet* pPropSet)
	{
		pPropSet->AddProperty(DBPROP_CANFETCHBACKWARDS, true, DBPROPOPTIONS_OPTIONAL);
		pPropSet->AddProperty(DBPROP_CANSCROLLBACKWARDS, true, DBPROPOPTIONS_OPTIONAL);
	}

	HRESULT OpenDataSource()
	{
		CDataSource _db;
		HRESULT hr;
#error Security Issue: The connection string may contain a password
// �������ַ����п��ܰ������������/��������Ҫ
// ��Ϣ�����ڲ鿴��������ַ������ҵ������밲ȫ
// �йص�������Ƴ� #error��������Ҫ���������
// ��Ϊ������ʽ��ʹ���������û������֤��
		hr = _db.OpenFromInitializationString(L"Provider=MSDASQL.1;Persist Security Info=False;Data Source=My_DB;Extended Properties=\"DSN=My_DB;DBQ=E:\\\x7f16\x7a0b\\VC6\\\x671f\x672b\x5927\x4f5c\x4e1a\\Access\x6570\x636e\x5e93\\mydb.mdb;DriverId=25;FIL=MS Access;MaxBufferSize=2048;PageTimeout=5;\"");
		if (FAILED(hr))
		{
#ifdef _DEBUG
			AtlTraceErrorRecords(hr);
#endif
			return hr;
		}
		return m_session.Open(_db);
	}

	void CloseDataSource()
	{
		m_session.Close();
	}

	operator const CSession&()
	{
		return m_session;
	}

	CSession m_session;

	BEGIN_COLUMN_MAP(CMFCdemoSetAccessor)
		COLUMN_ENTRY_LENGTH_STATUS(1, m_column0, m_dwcolumn0Length, m_dwcolumn0Status)
		COLUMN_ENTRY_LENGTH_STATUS(2, m_column1, m_dwcolumn1Length, m_dwcolumn1Status)
		COLUMN_ENTRY_LENGTH_STATUS(3, m_column2, m_dwcolumn2Length, m_dwcolumn2Status)
		COLUMN_ENTRY_LENGTH_STATUS(4, m_column3, m_dwcolumn3Length, m_dwcolumn3Status)
		COLUMN_ENTRY_LENGTH_STATUS(5, m_column4, m_dwcolumn4Length, m_dwcolumn4Status)
	END_COLUMN_MAP()
};

class CMFCdemoSet : public CTable<CAccessor<CMFCdemoSetAccessor> >
{
public:
	HRESULT OpenAll()
	{
		HRESULT hr;
		hr = OpenDataSource();
		if (FAILED(hr))
			return hr;
		__if_exists(GetRowsetProperties)
		{
			CDBPropSet propset(DBPROPSET_ROWSET);
			__if_exists(HasBookmark)
			{
				if( HasBookmark() )
					propset.AddProperty(DBPROP_IRowsetLocate, true);
			}
			GetRowsetProperties(&propset);
			return OpenRowset(&propset);
		}
		__if_not_exists(GetRowsetProperties)
		{
			__if_exists(HasBookmark)
			{
				if( HasBookmark() )
				{
					CDBPropSet propset(DBPROPSET_ROWSET);
					propset.AddProperty(DBPROP_IRowsetLocate, true);
					return OpenRowset(&propset);
				}
			}
		}
		return OpenRowset();
	}

	HRESULT OpenRowset(DBPROPSET *pPropSet = NULL)
	{
		HRESULT hr = Open(m_session, L"\x88681", pPropSet);
#ifdef _DEBUG
		if(FAILED(hr))
			AtlTraceErrorRecords(hr);
#endif
		return hr;
	}

	void CloseAll()
	{
		Close();
		CloseDataSource();
	}
};

