; CLW file contains information for the MFC ClassWizard

[General Info]
Version=1
LastClass=CMyView
LastTemplate=CDialog
NewFileInclude1=#include "stdafx.h"
NewFileInclude2=#include "期末大作业.h"
LastPage=0

ClassCount=11
Class1=CMyApp
Class2=CMyDoc
Class3=CMyView
Class4=CMainFrame
Class6=CAboutDlg

ResourceCount=10
Resource1=IDR_MAINFRAME
Resource2=IDD_DIALOG3
Class5=CMySet
Resource3=IDD_MY_FORM
Class7=CMoveTo
Resource4=IDD_DIALOG2
Class8=CSort
Resource5=IDR_PLAY
Class9=CSearch
Resource6=IDR_MENU_POP
Class10=CInputNumbers
Resource7=IDD_DIALOG1
Class11=CShowResult
Resource8=IDD_DIALOG4
Resource9=IDD_ABOUTBOX
Resource10=IDD_DIALOG5

[CLS:CMyApp]
Type=0
HeaderFile=期末大作业.h
ImplementationFile=期末大作业.cpp
Filter=N
LastObject=CMyApp

[CLS:CMyDoc]
Type=0
HeaderFile=期末大作业Doc.h
ImplementationFile=期末大作业Doc.cpp
Filter=N
LastObject=CMyDoc

[CLS:CMyView]
Type=0
HeaderFile=期末大作业View.h
ImplementationFile=期末大作业View.cpp
Filter=D
LastObject=ID_OPER_OPENV
BaseClass=CRecordView
VirtualFilter=RVWC


[CLS:CMySet]
Type=0
HeaderFile=期末大作业Set.h
ImplementationFile=期末大作业Set.cpp
Filter=N
LastObject=CMySet

[DB:CMySet]
DB=1
DBType=ODBC
ColumnCount=5
Column1=[编号], 4, 4
Column2=[姓名], 12, 100
Column3=[性别], 12, 100
Column4=[年龄], 4, 4
Column5=[部门], 12, 100


[CLS:CMainFrame]
Type=0
HeaderFile=MainFrm.h
ImplementationFile=MainFrm.cpp
Filter=T
LastObject=CMainFrame
BaseClass=CFrameWnd
VirtualFilter=fWC




[CLS:CAboutDlg]
Type=0
HeaderFile=期末大作业.cpp
ImplementationFile=期末大作业.cpp
Filter=D

[DLG:IDD_ABOUTBOX]
Type=1
Class=CAboutDlg
ControlCount=6
Control1=IDC_STATIC,static,1342177283
Control2=IDC_STATIC,static,1342308480
Control3=IDC_STATIC,static,1342308352
Control4=IDOK,button,1342373889
Control5=IDC_STATIC,static,1342308352
Control6=IDC_STATIC,static,1342308352

[MNU:IDR_MAINFRAME]
Type=1
Class=CMainFrame
Command1=ID_APP_EXIT
Command2=ID_RECORD_FIRST
Command3=ID_RECORD_PREV
Command4=ID_RECORD_NEXT
Command5=ID_RECORD_LAST
Command6=ID_RECORD_ADD
Command7=ID_RECORD_DELETE
Command8=ID_RECORD_UPDATE
Command9=ID_RECORD_MOVETO
Command10=ID_RECORD_SORT
Command11=ID_RECORD_SEARCH
Command12=ID_CALCULATE
Command13=ID_PAINT
Command14=ID_BIGGER
Command15=ID_SMALLER
Command16=ID_OPER_OPENV
Command17=ID_OPER_PLAYV
Command18=ID_OPER_PAUSE
Command19=ID_OPER_STOPV
Command20=ID_VIEW_TOOLBAR
Command21=ID_VIEW_STATUS_BAR
Command22=ID_APP_ABOUT
CommandCount=22

[ACL:IDR_MAINFRAME]
Type=1
Class=CMainFrame
Command1=ID_EDIT_UNDO
Command2=ID_EDIT_CUT
Command3=ID_EDIT_COPY
Command4=ID_EDIT_PASTE
Command5=ID_EDIT_UNDO
Command6=ID_EDIT_CUT
Command7=ID_EDIT_COPY
Command8=ID_EDIT_PASTE
Command9=ID_NEXT_PANE
Command10=ID_PREV_PANE
CommandCount=10

[DLG:IDD_MY_FORM]
Type=1
Class=CMyView
ControlCount=17
Control1=IDC_STATIC,static,1342308352
Control2=IDC_STATIC,static,1342308352
Control3=IDC_STATIC,static,1342308352
Control4=IDC_STATIC,static,1342308352
Control5=IDC_STATIC,static,1342308352
Control6=IDC_EDIT1,edit,1350631552
Control7=IDC_EDIT2,edit,1350631552
Control8=IDC_EDIT3,edit,1350631552
Control9=IDC_EDIT4,edit,1350631552
Control10=IDC_EDIT5,edit,1350631552
Control11=IDC_OCX1,{6BF52A52-394A-11D3-B153-00C04F79FAA6},1342242816
Control12=ID_OPER_OPENV,button,1342242816
Control13=ID_OPER_PLAYV,button,1342242816
Control14=ID_OPER_PAUSE,button,1342242816
Control15=IDC_SLIDER1,msctls_trackbar32,1342242842
Control16=IDC_STATIC,static,1342308352
Control17=ID_OPER_STOPV,button,1342242816

[TB:IDR_MAINFRAME]
Type=1
Class=CMainFrame
Command1=ID_RECORD_FIRST
Command2=ID_RECORD_PREV
Command3=ID_RECORD_NEXT
Command4=ID_RECORD_LAST
Command5=ID_RECORD_ADD
Command6=ID_RECORD_DELETE
Command7=ID_RECORD_UPDATE
Command8=ID_RECORD_MOVETO
Command9=ID_RECORD_SORT
Command10=ID_RECORD_SEARCH
Command11=ID_CALCULATE
Command12=ID_APP_ABOUT
CommandCount=12

[DLG:IDD_DIALOG1]
Type=1
Class=CMoveTo
ControlCount=5
Control1=IDOK,button,1342242817
Control2=IDCANCEL,button,1342242816
Control3=IDC_STATIC,static,1342308352
Control4=IDC_STATIC,static,1342308352
Control5=IDC_EDIT1,edit,1350631552

[CLS:CMoveTo]
Type=0
HeaderFile=MoveTo.h
ImplementationFile=MoveTo.cpp
BaseClass=CDialog
Filter=D
VirtualFilter=dWC
LastObject=CMoveTo

[DLG:IDD_DIALOG2]
Type=1
Class=CSort
ControlCount=6
Control1=IDOK,button,1342242817
Control2=IDCANCEL,button,1342242816
Control3=IDC_STATIC,static,1342308352
Control4=IDC_EDIT1,edit,1350631552
Control5=IDC_STATIC,static,1342308352
Control6=IDC_STATIC,static,1342308352

[CLS:CSort]
Type=0
HeaderFile=Sort.h
ImplementationFile=Sort.cpp
BaseClass=CDialog
Filter=D
VirtualFilter=dWC
LastObject=CSort

[DLG:IDD_DIALOG3]
Type=1
Class=CSearch
ControlCount=8
Control1=IDOK,button,1342242817
Control2=IDCANCEL,button,1342242816
Control3=IDC_STATIC,static,1342308352
Control4=IDC_EDIT1,edit,1350631552
Control5=IDC_STATIC,static,1342308352
Control6=IDC_STATIC,static,1342308352
Control7=IDC_EDIT2,edit,1350631552
Control8=IDC_STATIC,static,1342308352

[CLS:CSearch]
Type=0
HeaderFile=Search.h
ImplementationFile=Search.cpp
BaseClass=CDialog
Filter=D
VirtualFilter=dWC
LastObject=CSearch

[DLG:IDD_DIALOG4]
Type=1
Class=CInputNumbers
ControlCount=8
Control1=IDOK,button,1342242817
Control2=IDCANCEL,button,1342242816
Control3=IDC_STATIC,static,1342308352
Control4=IDC_EDIT1,edit,1350631552
Control5=IDC_EDIT2,edit,1350631552
Control6=IDC_EDIT3,edit,1350631552
Control7=IDC_EDIT4,edit,1350631552
Control8=IDC_EDIT5,edit,1350631552

[CLS:CInputNumbers]
Type=0
HeaderFile=InputNumbers.h
ImplementationFile=InputNumbers.cpp
BaseClass=CDialog
Filter=D
VirtualFilter=dWC

[DLG:IDD_DIALOG5]
Type=1
Class=CShowResult
ControlCount=10
Control1=IDOK,button,1342242817
Control2=IDCANCEL,button,1342242816
Control3=IDC_STATIC,static,1342308352
Control4=IDC_EDIT1,edit,1484849280
Control5=IDC_EDIT2,edit,1484849280
Control6=IDC_EDIT3,edit,1484849280
Control7=IDC_EDIT4,edit,1484849280
Control8=IDC_EDIT5,edit,1484849280
Control9=IDC_STATIC,static,1342308352
Control10=IDC_EDIT6,edit,1484849280

[CLS:CShowResult]
Type=0
HeaderFile=ShowResult.h
ImplementationFile=ShowResult.cpp
BaseClass=CDialog
Filter=D
VirtualFilter=dWC
LastObject=IDC_EDIT1

[MNU:IDR_MENU_POP]
Type=1
Class=?
Command1=ID_PAINT
Command2=ID_BIGGER
Command3=ID_SMALLER
CommandCount=3

[TB:IDR_PLAY]
Type=1
Class=?
Command1=ID_OPER_OPENV
Command2=ID_OPER_PLAYV
Command3=ID_OPER_PAUSE
Command4=ID_OPER_STOPV
CommandCount=4

