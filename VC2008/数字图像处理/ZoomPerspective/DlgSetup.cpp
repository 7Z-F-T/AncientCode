// DlgSetup.cpp : implementation file
//

#include "stdafx.h"
#include "ZoomPerspective.h"
#include "DlgSetup.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CDlgSetupPerspective dialog


CDlgSetupPerspective::CDlgSetupPerspective(CWnd* pParent /*=NULL*/)
	: CDialog(CDlgSetupPerspective::IDD, pParent)
{
	//{{AFX_DATA_INIT(CDlgSetupPerspective)
	m_fov = 0.0;
	m_zNear = 0.0;
	m_zFar = 0.0;
	m_xeye = 0.0;
	m_yeye = 0.0;
	m_zeye = 0.0;
	m_xcenter = 0.0;
	m_ycenter = 0.0;
	m_zcenter = 0.0;
	//}}AFX_DATA_INIT
}


void CDlgSetupPerspective::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CDlgSetupPerspective)
	DDX_Text(pDX, IDC_EDIT1, m_fov);
	DDX_Text(pDX, IDC_EDIT2, m_zNear);
	DDX_Text(pDX, IDC_EDIT3, m_zFar);
	DDX_Text(pDX, IDC_EDIT4, m_xeye);
	DDX_Text(pDX, IDC_EDIT5, m_yeye);
	DDX_Text(pDX, IDC_EDIT6, m_zeye);
	DDX_Text(pDX, IDC_EDIT7, m_xcenter);
	DDX_Text(pDX, IDC_EDIT8, m_ycenter);
	DDX_Text(pDX, IDC_EDIT9, m_zcenter);
	//}}AFX_DATA_MAP
}


BEGIN_MESSAGE_MAP(CDlgSetupPerspective, CDialog)
	//{{AFX_MSG_MAP(CDlgSetupPerspective)
		// NOTE: the ClassWizard will add message map macros here
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CDlgSetupPerspective message handlers
