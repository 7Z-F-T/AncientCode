; CLW file contains information for the MFC ClassWizard

[General Info]
Version=1
LastClass=CMainFrame
LastTemplate=CDialog
NewFileInclude1=#include "stdafx.h"
NewFileInclude2=#include "ImageProc.h"
LastPage=0

ClassCount=7
Class1=CImageProcApp
Class2=CImageProcDoc
Class3=CImageProcView
Class4=CMainFrame

ResourceCount=4
Resource1=IDR_IMAGEPTYPE
Resource2=IDD_DRAWLINE
Class5=CChildFrame
Class6=CAboutDlg
Resource3=IDD_ABOUTBOX
Class7=CDlgDrawLine
Resource4=IDR_MAINFRAME

[CLS:CImageProcApp]
Type=0
HeaderFile=ImageProc.h
ImplementationFile=ImageProc.cpp
Filter=N

[CLS:CImageProcDoc]
Type=0
HeaderFile=ImageProcDoc.h
ImplementationFile=ImageProcDoc.cpp
Filter=N

[CLS:CImageProcView]
Type=0
HeaderFile=ImageProcView.h
ImplementationFile=ImageProcView.cpp
Filter=C
BaseClass=CScrollView
VirtualFilter=VWC
LastObject=ID_SAVE


[CLS:CMainFrame]
Type=0
HeaderFile=MainFrm.h
ImplementationFile=MainFrm.cpp
Filter=T
LastObject=ID_BUTTON32788
BaseClass=CMDIFrameWnd
VirtualFilter=fWC


[CLS:CChildFrame]
Type=0
HeaderFile=ChildFrm.h
ImplementationFile=ChildFrm.cpp
Filter=M
BaseClass=CMDIChildWnd
VirtualFilter=mfWC
LastObject=CChildFrame


[CLS:CAboutDlg]
Type=0
HeaderFile=ImageProc.cpp
ImplementationFile=ImageProc.cpp
Filter=D

[DLG:IDD_ABOUTBOX]
Type=1
Class=CAboutDlg
ControlCount=4
Control1=IDC_STATIC,static,1342177283
Control2=IDC_STATIC,static,1342308480
Control3=IDC_STATIC,static,1342308352
Control4=IDOK,button,1342373889

[MNU:IDR_MAINFRAME]
Type=1
Class=CMainFrame
Command1=ID_FILE_NEW
Command2=ID_OPEN
Command3=ID_FILE_MRU_FILE1
Command4=ID_APP_EXIT
Command5=ID_VIEW_TOOLBAR
Command6=ID_VIEW_STATUS_BAR
Command7=ID_APP_ABOUT
CommandCount=7

[TB:IDR_MAINFRAME]
Type=1
Class=CMainFrame
Command1=ID_FILE_NEW
Command2=ID_OPEN
Command3=ID_SAVE
Command4=ID_EDIT_CUT
Command5=ID_EDIT_COPY
Command6=ID_EDIT_PASTE
Command7=ID_FILE_PRINT
Command8=ID_APP_ABOUT
Command9=ID_SHOWR
Command10=ID_SHOWG
Command11=ID_SHOWB
Command12=ID_SHOWGRAY
Command13=ID_DRAW
CommandCount=13

[MNU:IDR_IMAGEPTYPE]
Type=1
Class=CImageProcView
Command1=ID_FILE_NEW
Command2=ID_OPEN
Command3=ID_FILE_CLOSE
Command4=ID_SAVE
Command5=ID_SAVE_AS
Command6=ID_FILE_MRU_FILE1
Command7=ID_APP_EXIT
Command8=ID_EDIT_UNDO
Command9=ID_EDIT_CUT
Command10=ID_EDIT_COPY
Command11=ID_EDIT_PASTE
Command12=ID_VIEW_TOOLBAR
Command13=ID_VIEW_STATUS_BAR
Command14=ID_SHOWR
Command15=ID_SHOWG
Command16=ID_SHOWB
Command17=ID_SHOWGRAY
Command18=ID_WINDOW_NEW
Command19=ID_WINDOW_CASCADE
Command20=ID_WINDOW_TILE_HORZ
Command21=ID_WINDOW_ARRANGE
Command22=ID_APP_ABOUT
Command23=ID_DRAW
CommandCount=23

[ACL:IDR_MAINFRAME]
Type=1
Class=CMainFrame
Command1=ID_FILE_NEW
Command2=ID_FILE_OPEN
Command3=ID_FILE_SAVE
Command4=ID_EDIT_UNDO
Command5=ID_EDIT_CUT
Command6=ID_EDIT_COPY
Command7=ID_EDIT_PASTE
Command8=ID_EDIT_UNDO
Command9=ID_EDIT_CUT
Command10=ID_EDIT_COPY
Command11=ID_EDIT_PASTE
Command12=ID_NEXT_PANE
Command13=ID_PREV_PANE
CommandCount=13

[DLG:IDD_DRAWLINE]
Type=1
Class=CDlgDrawLine
ControlCount=40
Control1=IDC_RADIO_LINE,button,1342373897
Control2=IDC_LINE_X1,edit,1350631552
Control3=IDC_LINE_Y1,edit,1350631552
Control4=IDC_LINE_X2,edit,1350631552
Control5=IDC_LINE_Y2,edit,1350631552
Control6=IDC_RADIO_RECT,button,1342177289
Control7=IDC_RECT_X1,edit,1350631552
Control8=IDC_RECT_Y1,edit,1350631552
Control9=IDC_RECT_X2,edit,1350631552
Control10=IDC_RECT_Y2,edit,1350631552
Control11=IDC_RADIO_OVAL,button,1342177289
Control12=IDC_OVAL_CX,edit,1350631552
Control13=IDC_OVAL_CY,edit,1350631552
Control14=IDC_OVAL_W,edit,1350631552
Control15=IDC_OVAL_H,edit,1350631552
Control16=IDC_LINEW,edit,1350631552
Control17=IDC_LINER,edit,1350631552
Control18=IDC_LINEG,edit,1350631552
Control19=IDC_LINEB,edit,1350631552
Control20=IDOK,button,1342242817
Control21=IDCANCEL,button,1342242816
Control22=IDC_STATIC,button,1342177287
Control23=IDC_STATIC,button,1342177287
Control24=IDC_STATIC,static,1342308352
Control25=IDC_STATIC,static,1342308352
Control26=IDC_STATIC,static,1342308352
Control27=IDC_STATIC,static,1342308352
Control28=IDC_STATIC,static,1342308352
Control29=IDC_STATIC,static,1342308352
Control30=IDC_STATIC,static,1342308352
Control31=IDC_STATIC,static,1342308352
Control32=IDC_STATIC,static,1342308352
Control33=IDC_STATIC,static,1342308352
Control34=IDC_STATIC,static,1342308352
Control35=IDC_STATIC,static,1342308352
Control36=IDC_STATIC,static,1342308352
Control37=IDC_STATIC,static,1342308352
Control38=IDC_STATIC,static,1342308352
Control39=IDC_STATIC,static,1342308352
Control40=IDC_STATIC,static,1342308352

[CLS:CDlgDrawLine]
Type=0
HeaderFile=DlgDrawLine.h
ImplementationFile=DlgDrawLine.cpp
BaseClass=CDialog
Filter=D
LastObject=CDlgDrawLine
VirtualFilter=dWC

