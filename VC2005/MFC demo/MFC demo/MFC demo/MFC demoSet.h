// MFC demoSet.h: CMFCdemoSet 类的接口
//


#pragma once

// 代码生成在 2006年6月5日, 19:25

class CMFCdemoSetAccessor
{
public:
	TCHAR m_column0[51];
	TCHAR m_column1[51];
	TCHAR m_column2[51];
	TCHAR m_column3[51];
	TCHAR m_column4[51];

	// 以下向导生成的数据成员包含
	//列映射中相应字段的状态值。
	// 可以使用这些值保存数据库返回的 NULL 值或在编译器返回
	// 错误时保存错误信息。有关如何使用
	//这些字段的详细信息，
	// 请参见 Visual C++ 文档中的
	//“向导生成的访问器中的字段状态数据成员”。
	// 注意: 在设置/插入数据前必须初始化这些字段!

	DBSTATUS m_dwcolumn0Status;
	DBSTATUS m_dwcolumn1Status;
	DBSTATUS m_dwcolumn2Status;
	DBSTATUS m_dwcolumn3Status;
	DBSTATUS m_dwcolumn4Status;

	// 以下向导生成的数据成员包含
	//列映射中相应字段的长度值。
	// 注意: 对变长列，必须在设置/插入
	//       数据前初始化这些字段!

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
// 此连接字符串中可能包含明文密码和/或其他重要
// 信息。请在查看完此连接字符串并找到所有与安全
// 有关的问题后移除 #error。可能需要将此密码存
// 储为其他格式或使用其他的用户身份验证。
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

