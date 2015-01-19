// 期末大作业View.cpp : implementation of the CMyView class
//

#include "stdafx.h"
#include "期末大作业.h"

#include "期末大作业Set.h"
#include "期末大作业Doc.h"
#include "期末大作业View.h"
#include "MoveTo.h"
#include "Sort.h"
#include "Search.h"
#include "ShowResult.h"
#include "InputNumbers.h"

#include "wmpplayer4.h"
#include "wmpcontrols.h"
#include "wmpsettings.h"


float x=1;//放缩因子
PAINTSTRUCT ps;

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CMyView

IMPLEMENT_DYNCREATE(CMyView, CRecordView)

BEGIN_MESSAGE_MAP(CMyView, CRecordView)
	//{{AFX_MSG_MAP(CMyView)
	ON_COMMAND(ID_RECORD_ADD, OnRecordAdd)
	ON_COMMAND(ID_RECORD_DELETE, OnRecordDelete)
	ON_COMMAND(ID_RECORD_UPDATE, OnRecordUpdate)
	ON_COMMAND(ID_RECORD_MOVETO, OnRecordMoveto)
	ON_COMMAND(ID_RECORD_SORT, OnRecordSort)
	ON_COMMAND(ID_RECORD_SEARCH, OnRecordSearch)
	ON_COMMAND(ID_CALCULATE, OnCalculate)
	ON_COMMAND(ID_PAINT, OnPaint)
	ON_COMMAND(ID_BIGGER, OnBigger)
	ON_COMMAND(ID_SMALLER, OnSmaller)
	ON_WM_RBUTTONUP()
	ON_WM_CREATE()
	ON_COMMAND(ID_OPER_OPENV, OnOperOpenv)
	ON_COMMAND(ID_OPER_PLAYV, OnOperPlayv)
	ON_COMMAND(ID_OPER_STOPV, OnOperStopv)
	ON_WM_SIZE()
	ON_WM_SHOWWINDOW()
	ON_WM_VSCROLL()
	ON_COMMAND(ID_OPER_PAUSE, OnOperPause)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CMyView construction/destruction

CMyView::CMyView()
	: CRecordView(CMyView::IDD)
{
	//{{AFX_DATA_INIT(CMyView)
	m_pSet = NULL;
	IsPaused=FALSE;
	//}}AFX_DATA_INIT
	// TODO: add construction code here
	m_PopMenu.LoadMenu(IDR_MENU_POP);
	m_Video = new CWMPPlayer4;
	m_Music = new CWMPPlayer4;

}

CMyView::~CMyView()
{
	delete m_Video;
	delete m_Music;
}

void CMyView::DoDataExchange(CDataExchange* pDX)
{
	CRecordView::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CMyView)
	DDX_FieldText(pDX, IDC_EDIT2, m_pSet->m_column2, m_pSet);
	DDX_FieldText(pDX, IDC_EDIT3, m_pSet->m_column3, m_pSet);
	DDX_FieldText(pDX, IDC_EDIT5, m_pSet->m_column5, m_pSet);
	DDX_FieldText(pDX, IDC_EDIT1, m_pSet->m_column1, m_pSet);
	DDX_FieldText(pDX, IDC_EDIT4, m_pSet->m_column4, m_pSet);
	//}}AFX_DATA_MAP
}

BOOL CMyView::PreCreateWindow(CREATESTRUCT& cs)
{
	// TODO: Modify the Window class or styles here by modifying
	//  the CREATESTRUCT cs

	return CRecordView::PreCreateWindow(cs);
}

void CMyView::OnInitialUpdate()
{
	m_pSet = &GetDocument()->m_mySet;
	CRecordView::OnInitialUpdate();
	GetParentFrame()->RecalcLayout();
	ResizeParentToFit();

}

/////////////////////////////////////////////////////////////////////////////
// CMyView diagnostics

#ifdef _DEBUG
void CMyView::AssertValid() const
{
	CRecordView::AssertValid();
}

void CMyView::Dump(CDumpContext& dc) const
{
	CRecordView::Dump(dc);
}

CMyDoc* CMyView::GetDocument() // non-debug version is inline
{
	ASSERT(m_pDocument->IsKindOf(RUNTIME_CLASS(CMyDoc)));
	return (CMyDoc*)m_pDocument;
}
#endif //_DEBUG

/////////////////////////////////////////////////////////////////////////////
// CMyView database support
CRecordset* CMyView::OnGetRecordset()
{
	return m_pSet;
}


/////////////////////////////////////////////////////////////////////////////
// CMyView message handlers

void CMyView::OnRecordAdd() 
{
	// TODO: Add your command handler code here
	CRecordset * pSet=OnGetRecordset();
	if(pSet->CanUpdate() && !pSet->IsDeleted())
	{
		pSet->Edit();
		if(!UpdateData())
			return;
		pSet->Update();
	}
	
	long m_lNewID=m_pSet->GetMaxID()+1;	
	m_pSet->AddNew();					
	m_pSet->m_column1=m_lNewID;			
	m_pSet->Update();					
	m_pSet->Requery();					
	m_pSet->MoveLast();					
	UpdateData(FALSE);				
	
}

void CMyView::OnRecordDelete() 
{
	// TODO: Add your command handler code here
	CRecordsetStatus m_cStatus;
	try{
		m_pSet->Delete();
	}
	catch(CDBException* m_pEx)
	{
		AfxMessageBox(m_pEx->m_strError);
		m_pEx->Delete();
		m_pSet->MoveFirst();
		UpdateData(FALSE);
		return;
	}
	m_pSet->GetStatus(m_cStatus);
	if(m_cStatus.m_lCurrentRecord==0)
		m_pSet->MoveFirst();
	else
		m_pSet->MoveNext();
	UpdateData(FALSE);
	
}

void CMyView::OnRecordUpdate() 
{
	// TODO: Add your command handler code here
	m_pSet->Edit();
	UpdateData(TRUE);
	if(m_pSet->CanUpdate())
		m_pSet->Update();
	
}



void CMyView::OnRecordMoveto() 
{
	// TODO: Add your command handler code here
	CMoveTo dlgMoveTo;		
	if(dlgMoveTo.DoModal()==IDOK)
	{
		CRecordset *pSet=OnGetRecordset();
		if(pSet->CanUpdate() && !pSet->IsDeleted())	
		{
			pSet->Edit();
			if(!UpdateData())
				return;
			pSet->Update();
		}
		pSet->SetAbsolutePosition(dlgMoveTo.m_RecordID);
		UpdateData(FALSE);	
	}
}

void CMyView::OnRecordSort() 
{
	// TODO: Add your command handler code here
	CSort dlg;
	if(dlg.DoModal()==IDOK)
	{
	UpdateData(TRUE);
	m_pSet->Close();
	m_pSet->m_strSort=dlg.m_Sort;
	m_pSet->Open();
	UpdateData(FALSE);
	}
}

void CMyView::OnRecordSearch() 
{
	// TODO: Add your command handler code here
	CSearch dlg;
	int result=dlg.DoModal();
	if(result==IDOK)
	{
		CString str=dlg.m_Filter+"='"+dlg.m_str+"'";
		m_pSet->Close();
		m_pSet->m_strFilter=str;
		m_pSet->Open();
		int recCount=m_pSet->GetRecordCount();
		if(recCount==0)
		{
			MessageBox("没有找到!");
			m_pSet->Close();
			m_pSet->m_strFilter;
			m_pSet->Open();
		}
		UpdateData(FALSE);
	}
}

void CMyView::OnCalculate() 
{
	// TODO: Add your command handler code here
	float sort[5];
	float sum,t;
	CInputNumbers dlgIn;
	CShowResult dlgOut;
	int i,j;
	if(dlgIn.DoModal()==IDOK)
	{
		UpdateData(TRUE);
		sort[0]=dlgIn.m_1;
		sort[1]=dlgIn.m_2;
		sort[2]=dlgIn.m_3;
		sort[3]=dlgIn.m_4;
		sort[4]=dlgIn.m_5;
		sum=dlgIn.m_1*dlgIn.m_1+dlgIn.m_2*dlgIn.m_2+dlgIn.m_3*dlgIn.m_3+dlgIn.m_4*dlgIn.m_4+dlgIn.m_5*dlgIn.m_5;
        for(j=0;j<4;j++)
			for(i=0;i<4-j;i++)
				if(sort[i]>sort[i+1])
				{
					t=sort[i];
					sort[i]=sort[i+1];
					sort[i+1]=t;
				}
		dlgOut.m_show1=sort[0];
		dlgOut.m_show2=sort[1];
		dlgOut.m_show3=sort[2];
		dlgOut.m_show4=sort[3];
		dlgOut.m_show5=sort[4];
		dlgOut.m_show6=sum;
		dlgOut.DoModal();
		
		
	}

		
	
}




void CMyView::DoPaint()
{
		

	Invalidate();
	CDC *pDC;
	pDC=GetDC();
	BeginPaint(&ps);
	CBitmap bitmap,brush;
	CDC dcMemory;
	bitmap.LoadBitmap(IDB_BITMAP1);
	BITMAP bmInfo;
    bitmap.GetObject(sizeof(bmInfo),&bmInfo);
	dcMemory.CreateCompatibleDC(pDC);
	dcMemory.SelectObject(&brush);
		dcMemory.SelectObject(&bitmap);
	pDC->StretchBlt(50,250,bmInfo.bmWidth*x,bmInfo.bmHeight*x,
		            &dcMemory,0,0,bmInfo.bmWidth,bmInfo.bmHeight,SRCCOPY);
	ReleaseDC(pDC);
	EndPaint(&ps);
	

}

void CMyView::OnPaint() 
{
	// TODO: Add your command handler code here
	DoPaint();
	
}

void CMyView::OnBigger() 
{
	// TODO: Add your command handler code here
	if(x<1.6)
	{
		x=x+0.2;
		CSliderCtrl* pslider=(CSliderCtrl*)GetDlgItem(IDC_SLIDER1);
		pslider->SetPos((x-1)*5+4);
	}		
	
     DoPaint();
}

void CMyView::OnSmaller() 
{
	// TODO: Add your command handler code here
	if(x>0.3)
	{
		x=x-0.2;
		CSliderCtrl* pslider=(CSliderCtrl*)GetDlgItem(IDC_SLIDER1);
		pslider->SetPos((x-1)*5+4);
	}
		
	DoPaint();
	
}


void CMyView::OnRButtonUp(UINT nFlags, CPoint point) 
{
	// TODO: Add your message handler code here and/or call default
	m_pPop=m_PopMenu.GetSubMenu(0);
	ClientToScreen(&point);
    m_pPop->TrackPopupMenu(TPM_LEFTALIGN,point.x,point.y,this);
	
	
	CRecordView::OnRButtonUp(nFlags, point);
}

int CMyView::OnCreate(LPCREATESTRUCT lpCreateStruct) 
{
	if (CRecordView::OnCreate(lpCreateStruct) == -1)
		return -1;


	
	// TODO: Add your specialized creation code here
	   
	HCURSOR hCursor=::LoadCursor(NULL, IDC_ARROW);
	
	
	m_Video->Create(AfxRegisterWndClass(CS_HREDRAW | CS_VREDRAW | CS_DBLCLKS | CS_PARENTDC, hCursor, 0, 0), 
		NULL, WS_VISIBLE|WS_CHILD, CRect(0,0,0,0), this, 0);
	m_Video->SetUiMode("none");

	m_Video->GetSettings().SetMute(TRUE);
	

	m_Music->Create(AfxRegisterWndClass(CS_HREDRAW | CS_VREDRAW | CS_DBLCLKS | CS_PARENTDC, hCursor, 0, 0), 
		NULL, WS_VISIBLE|WS_CHILD, CRect(0,0,0,0), this, 0);

	m_Music->ShowWindow(SW_HIDE);

	
	
	
	

		return TRUE;  // return TRUE unless you set the focus to a control
	
	
	return 0;
}

void CMyView::OnOperOpenv() 
{
	// TODO: Add your command handler code here

	m_Video->GetControls().stop();					
	static char BASED_CODE szFilter[] = "视频或音频文件 (*.avi;*.wav;*.mpg;*.mpeg;*.wav;*.mp3;*.wma)|*.avi;*.wav;*.mpg;*.mpeg;*.wav;*.mp3;*.wma|";
	CFileDialog	fileDlg(TRUE, NULL, NULL, OFN_HIDEREADONLY, szFilter);
	if(fileDlg.DoModal() == IDOK)
	{
		
		m_strVideo = fileDlg.GetPathName();
	}
	
}

void CMyView::OnOperPlayv() 
{
	// TODO: Add your command handler code here
	m_Video->GetControls().stop();						
	m_Video->GetSettings().SetAutoStart(TRUE);		
	m_Video->SetUrl(m_strVideo);					
	
}

void CMyView::OnOperStopv() 
{
	// TODO: Add your command handler code here
	m_Video->GetControls().stop();					
}

	



void CMyView::OnSize(UINT nType, int cx, int cy) 
{
	CRecordView::OnSize(nType, cx, cy);
	
	// TODO: Add your message handler code here
	CRect rect;
	GetClientRect(rect);
	rect.left+=400;
    rect.top+=100;
    rect.right-=25;
    rect.bottom-=100;
	m_Video->MoveWindow(rect);
}


void CMyView::InitSlider()
{
	CSliderCtrl* pslider=(CSliderCtrl*)GetDlgItem(IDC_SLIDER1);
	pslider->SetRange(0,7);
	pslider->SetPos(4);
}

void CMyView::OnShowWindow(BOOL bShow, UINT nStatus) 
{
	CRecordView::OnShowWindow(bShow, nStatus);
	
	// TODO: Add your message handler code here
	InitSlider();
	
}


void CMyView::OnVScroll(UINT nSBCode, UINT nPos, CScrollBar* pScrollBar) 
{
	// TODO: Add your message handler code here and/or call default
	CSliderCtrl* pslider=(CSliderCtrl*)GetDlgItem(IDC_SLIDER1);
	
	x=1+(pslider->GetPos()-4)/5.0;
	DoPaint();
		
	CRecordView::OnVScroll(nSBCode, nPos, pScrollBar);
}

void CMyView::OnOperPause() 
{
	// TODO: Add your command handler code here
	if(IsPaused==FALSE)
	{
		m_Video->GetControls().pause();
		IsPaused=TRUE;
		CButton* pbutton=(CButton*)GetDlgItem(ID_OPER_PAUSE);
		pbutton->SetWindowText("继续");
		
	}
	else
	{
		m_Video->GetControls().play();
		IsPaused=FALSE;
		CButton* pbutton=(CButton*)GetDlgItem(ID_OPER_PAUSE);
		pbutton->SetWindowText("暂停");
			
	}
}
