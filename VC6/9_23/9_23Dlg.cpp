// 9_23Dlg.cpp : implementation file
//

#include "stdafx.h"
#include "9_23.h"
#include "9_23Dlg.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CMy9_23Dlg dialog

CMy9_23Dlg::CMy9_23Dlg(CWnd* pParent /*=NULL*/)
	: CDialog(CMy9_23Dlg::IDD, pParent)
{
	//{{AFX_DATA_INIT(CMy9_23Dlg)
	m_edit = 0.0;
	//}}AFX_DATA_INIT
	// Note that LoadIcon does not require a subsequent DestroyIcon in Win32
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
}

void CMy9_23Dlg::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CMy9_23Dlg)
	DDX_Text(pDX, IDC_EDIT, m_edit);
	//}}AFX_DATA_MAP
}

BEGIN_MESSAGE_MAP(CMy9_23Dlg, CDialog)
	//{{AFX_MSG_MAP(CMy9_23Dlg)
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	ON_WM_TIMER()
	ON_WM_HSCROLL()
	ON_WM_VSCROLL()
	ON_EN_CHANGE(IDC_EDIT, OnChangeEdit)
	ON_NOTIFY(LVN_ITEMCHANGED, IDC_LIST, OnItemchangedList)
	ON_NOTIFY(TVN_SELCHANGED, IDC_TREE, OnSelchangedTree)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CMy9_23Dlg message handlers

BOOL CMy9_23Dlg::OnInitDialog()
{
	CDialog::OnInitDialog();

	// Set the icon for this dialog.  The framework does this automatically
	//  when the application's main window is not a dialog
	SetIcon(m_hIcon, TRUE);			// Set big icon
	SetIcon(m_hIcon, FALSE);		// Set small icon
	
	// TODO: Add extra initialization here
	CProgressCtrl *pProg=(CProgressCtrl*)GetDlgItem(IDC_PROGRESS);
	pProg->SetRange(0,100);
    pProg->SetPos(0);
	SetTimer(1000,30,NULL);
	CString strText1;
	CSliderCtrl *pSlide1=(CSliderCtrl*)GetDlgItem(IDC_SLIDER1);
	pSlide1->SetRange(1,100);
	pSlide1->SetPos(50);
	strText1.Format("%d",pSlide1->GetPos());
	SetDlgItemText(IDC_STATIC1,strText1);

	CString strText2;
	CSliderCtrl *pSlide2=(CSliderCtrl*)GetDlgItem(IDC_SLIDER2);
	pSlide2->SetRange(1,5);
	pSlide2->SetPos(3);
	strText2.Format("%d.0",pSlide2->GetPos());
	SetDlgItemText(IDC_STATIC2,strText2);

	CString strValue;
	CSpinButtonCtrl *pSpin=(CSpinButtonCtrl*)GetDlgItem(IDC_SPIN);
	pSpin->SetRange(0,100);
	pSpin->SetPos(50);
	pSpin->SetBuddy(GetDlgItem(IDC_EDIT));
	CEdit *pEdit=(CEdit*)GetDlgItem(IDC_EDIT);
	pEdit->SetWindowText("5.0");

	HICON hicon[8];
	int n;
	m_imagelist.Create(16,16,0,8,8);
	hicon[0]=AfxGetApp()->LoadIcon(IDI_BLACK);
	hicon[1]=AfxGetApp()->LoadIcon(IDI_BLUE);
	hicon[2]=AfxGetApp()->LoadIcon(IDI_GREEN);
	hicon[3]=AfxGetApp()->LoadIcon(IDI_PINK);
	hicon[4]=AfxGetApp()->LoadIcon(IDI_RED);
	hicon[5]=AfxGetApp()->LoadIcon(IDI_YELLOW);
	for(n=0;n<8;n++)
		m_imagelist.Add(hicon[n]);

	static char *color[]={"black","blue","green","pink","red","yellow"};
	
	CListCtrl *pList=(CListCtrl*)GetDlgItem(IDC_LIST);
	pList->SetImageList(&m_imagelist,LVSIL_NORMAL);
	for(n=0;n<8;n++)
		pList->InsertItem(n,color[n],n);
	pList->SetBkColor(RGB(255,255,255));
	pList->SetTextColor(RGB(0,0,0));

	CTreeCtrl *pTree=(CTreeCtrl*)GetDlgItem(IDC_TREE);
	pTree->SetImageList(&m_imagelist,TVSIL_NORMAL);
	TV_INSERTSTRUCT tvinsert;
	tvinsert.hParent =NULL;
	tvinsert.item .mask=TVIF_IMAGE|TVIF_SELECTEDIMAGE|TVIF_TEXT;
	tvinsert.item.hItem =NULL;
	tvinsert.item.state =0;
	tvinsert.item.stateMask=0;
	tvinsert.item.cchTextMax =6;
	tvinsert.item.iSelectedImage =1;
	tvinsert.item.cChildren =0;
	tvinsert.item .lParam =0;
	tvinsert.item.iImage =2;
	tvinsert.item .pszText ="人类";
	HTREEITEM hHuman=pTree->InsertItem(&tvinsert);
	tvinsert.item.pszText ="昆虫";
	HTREEITEM hWorms=pTree->InsertItem(&tvinsert);
	tvinsert.hParent =hHuman;
	tvinsert.item .iImage =3;
	tvinsert.item .pszText ="好人";
	pTree->InsertItem(&tvinsert);
	tvinsert.item .pszText ="坏人";
	pTree->InsertItem(&tvinsert);
	tvinsert.hParent =hWorms;
	tvinsert.item .iImage =4;
	tvinsert.item .pszText ="益虫";
	pTree->InsertItem(&tvinsert);
	tvinsert.item .pszText ="害虫";
	pTree->InsertItem(&tvinsert);
	

	
	
	


	   

	
	
	
	
	
	return TRUE;  // return TRUE  unless you set the focus to a control
}

// If you add a minimize button to your dialog, you will need the code below
//  to draw the icon.  For MFC applications using the document/view model,
//  this is automatically done for you by the framework.

void CMy9_23Dlg::OnPaint() 
{
	if (IsIconic())
	{
		CPaintDC dc(this); // device context for painting

		SendMessage(WM_ICONERASEBKGND, (WPARAM) dc.GetSafeHdc(), 0);

		// Center icon in client rectangle
		int cxIcon = GetSystemMetrics(SM_CXICON);
		int cyIcon = GetSystemMetrics(SM_CYICON);
		CRect rect;
		GetClientRect(&rect);
		int x = (rect.Width() - cxIcon + 1) / 2;
		int y = (rect.Height() - cyIcon + 1) / 2;

		// Draw the icon
		dc.DrawIcon(x, y, m_hIcon);
	}
	else
	{
		CDialog::OnPaint();
	}
}

// The system calls this to obtain the cursor to display while the user drags
//  the minimized window.
HCURSOR CMy9_23Dlg::OnQueryDragIcon()
{
	return (HCURSOR) m_hIcon;
}

void CMy9_23Dlg::OnTimer(UINT nIDEvent) 
{
	// TODO: Add your message handler code here and/or call default
	CProgressCtrl *pProg=(CProgressCtrl*)GetDlgItem(IDC_PROGRESS);
	if(nIDEvent==1000)
		pProg->SetPos(pProg->GetPos()+1);
	if(pProg->GetPos()>=100)
		pProg->SetPos(0);
	CDialog::OnTimer(nIDEvent);

}

void CMy9_23Dlg::OnHScroll(UINT nSBCode, UINT nPos, CScrollBar* pScrollBar) 
{
	CSliderCtrl *pSlide1=(CSliderCtrl*)GetDlgItem(IDC_SLIDER1);
	CString strText1;
	strText1.Format("%d",pSlide1->GetPos());
	SetDlgItemText(IDC_STATIC1,strText1);

	CSliderCtrl *pSlide2=(CSliderCtrl*)GetDlgItem(IDC_SLIDER2);
	CString strText2;
	strText2.Format("%d.0",pSlide2->GetPos()*3+1);
	SetDlgItemText(IDC_STATIC2,strText2);
	

	CDialog::OnHScroll(nSBCode, nPos, pScrollBar);
}

void CMy9_23Dlg::OnVScroll(UINT nSBCode, UINT nPos, CScrollBar* pScrollBar) 
{
   if(pScrollBar->GetDlgCtrlID()==IDC_SPIN)
      
   { 
      CString strValue;
	  strValue.Format("%-8.1f",(double)nPos/10.0);
	  CSpinButtonCtrl *pSpin=(CSpinButtonCtrl*)GetDlgItem(IDC_SPIN);
	  pSpin->GetBuddy()->SetWindowText(strValue);
   }  
	  
   
  	
	CDialog::OnVScroll(nSBCode, nPos, pScrollBar);
}

void CMy9_23Dlg::OnChangeEdit() 
{
	// TODO: If this is a RICHEDIT control, the control will not
	// send this notification unless you override the CDialog::OnInitDialog()
	// function and call CRichEditCtrl().SetEventMask()
	// with the ENM_CHANGE flag ORed into the mask.
	
	// TODO: Add your control notification handler code here
	UpdateData(TRUE);
	CSpinButtonCtrl *pSpin=(CSpinButtonCtrl*)GetDlgItem(IDC_SPIN);
	pSpin->SetPos(m_edit*10);
	
	
}

void CMy9_23Dlg::OnItemchangedList(NMHDR* pNMHDR, LRESULT* pResult) 
{
	NM_LISTVIEW* pNMListView = (NM_LISTVIEW*)pNMHDR;
	CListCtrl *pList=(CListCtrl*)GetDlgItem(IDC_LIST);
    int nSelected=pNMListView->iItem ;
	if(nSelected>=0)
	{
		CString strItem=pList->GetItemText(nSelected,0);
		SetDlgItemText(IDC_STATIC_LIST,strItem);
	}
	*pResult = 0;
}

void CMy9_23Dlg::OnSelchangedTree(NMHDR* pNMHDR, LRESULT* pResult) 
{
	NM_TREEVIEW* pNMTreeView = (NM_TREEVIEW*)pNMHDR;
	CTreeCtrl *pTree=(CTreeCtrl*)GetDlgItem(IDC_TREE);
    HTREEITEM hSelected=pNMTreeView->itemNew.hItem;
	if(hSelected!=NULL)
		
	{
		char text[30];
		TV_ITEM item;
	    item.mask=TVIF_HANDLE|TVIF_TEXT;
	    item.hItem =hSelected;
	    item.pszText=text;
		item.cchTextMax =30;
		VERIFY(pTree->GetItem(&item));
		SetDlgItemText(IDC_STATIC_TREE,item.pszText);
	}

	*pResult = 0;
}
