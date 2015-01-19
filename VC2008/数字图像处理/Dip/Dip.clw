; CLW file contains information for the MFC ClassWizard

[General Info]
Version=1
LastClass=CAffineDlg
LastTemplate=CDialog
NewFileInclude1=#include "stdafx.h"
NewFileInclude2=#include "dip.h"
LastPage=0

ClassCount=15
Class1=CAffineDlg
Class2=CChildFrame
Class3=CColorStatic
Class4=CDipApp
Class5=CAboutDlg
Class6=CDipDialog
Class7=CDipDoc
Class8=CDipView
Class9=CHistogramDlg
Class10=CHistStatic
Class11=CLinearDlg
Class12=CLineSlider
Class13=CMainFrame
Class14=CPersDlg
Class15=CThresholdDlg

ResourceCount=8
Resource1=IDD_POINT_THRESHOLD
Resource2=IDD_POINT_LINEAR
Resource3=IDD_GEO_AFFINE
Resource4=IDR_DIPTYPE
Resource5=IDD_HISTOGRAM
Resource6=IDD_ABOUTBOX
Resource7=IDD_GEO_PERS
Resource8=IDR_MAINFRAME

[CLS:CAffineDlg]
Type=0
BaseClass=CDipDialog
HeaderFile=AffineDlg.h
ImplementationFile=AffineDlg.cpp
LastObject=IDC_SLIDER4

[CLS:CChildFrame]
Type=0
BaseClass=CMDIChildWnd
HeaderFile=ChildFrm.h
ImplementationFile=ChildFrm.cpp

[CLS:CColorStatic]
Type=0
BaseClass=CStatic
HeaderFile=ColorStatic.h
ImplementationFile=ColorStatic.cpp

[CLS:CDipApp]
Type=0
BaseClass=CWinApp
HeaderFile=Dip.h
ImplementationFile=Dip.cpp

[CLS:CAboutDlg]
Type=0
BaseClass=CDialog
HeaderFile=Dip.cpp
ImplementationFile=Dip.cpp
LastObject=ID_FIELD_ISOBEL

[CLS:CDipDialog]
Type=0
BaseClass=CDialog
HeaderFile=DipDialog.h
ImplementationFile=DipDialog.cpp

[CLS:CDipDoc]
Type=0
BaseClass=CDocument
HeaderFile=DipDoc.h
ImplementationFile=DipDoc.cpp

[CLS:CDipView]
Type=0
BaseClass=CScrollView
HeaderFile=DipView.h
ImplementationFile=DipView.cpp

[CLS:CHistogramDlg]
Type=0
BaseClass=CDialog
HeaderFile=HistogramDlg.h
ImplementationFile=HistogramDlg.cpp

[CLS:CHistStatic]
Type=0
BaseClass=CWnd
HeaderFile=HistStatic.h
ImplementationFile=HistStatic.cpp

[CLS:CLinearDlg]
Type=0
BaseClass=CDipDialog
HeaderFile=LinearDlg.h
ImplementationFile=LinearDlg.cpp

[CLS:CLineSlider]
Type=0
BaseClass=CSliderCtrl
HeaderFile=LineSlider.h
ImplementationFile=LineSlider.cpp

[CLS:CMainFrame]
Type=0
BaseClass=CMDIFrameWnd
HeaderFile=MainFrm.h
ImplementationFile=MainFrm.cpp

[CLS:CPersDlg]
Type=0
BaseClass=CDipDialog
HeaderFile=PersDlg.h
ImplementationFile=PersDlg.cpp

[CLS:CThresholdDlg]
Type=0
BaseClass=CDipDialog
HeaderFile=ThresholdDlg.h
ImplementationFile=ThresholdDlg.cpp

[DLG:IDD_GEO_AFFINE]
Type=1
Class=CAffineDlg
ControlCount=16
Control1=IDOK,button,1342242817
Control2=IDCANCEL,button,1342242816
Control3=IDC_SCALE,edit,1350639744
Control4=IDC_SLIDER1,msctls_trackbar32,1342242840
Control5=IDC_TRANSFORM,edit,1350631552
Control6=IDC_SLIDER2,msctls_trackbar32,1342242841
Control7=IDC_DISTORT,edit,1350639744
Control8=IDC_SLIDER3,msctls_trackbar32,1342242841
Control9=IDC_ROTATE,edit,1350639744
Control10=IDC_SLIDER4,msctls_trackbar32,1342242841
Control11=IDC_PREVIEW,button,1342242819
Control12=IDC_STATIC,static,1342308352
Control13=IDC_STATIC,static,1342308352
Control14=IDC_STATIC,static,1342308352
Control15=IDC_STATIC,static,1342308352
Control16=IDC_RESET,button,1342242816

[DLG:IDD_ABOUTBOX]
Type=1
Class=CAboutDlg
ControlCount=4
Control1=IDC_STATIC,static,1342177283
Control2=IDC_STATIC,static,1342308480
Control3=IDC_STATIC,static,1342308352
Control4=IDOK,button,1342373889

[DLG:IDD_HISTOGRAM]
Type=1
Class=CHistogramDlg
ControlCount=9
Control1=IDC_HISTVIEW,static,1342177543
Control2=IDC_CHANNEL,combobox,1344339971
Control3=IDC_STATIC,static,1342308352
Control4=IDC_STATIC,button,1342177287
Control5=IDC_STATIC,static,1342308352
Control6=IDC_STATIC,static,1342308352
Control7=IDC_GREYLEVEL,static,1342308352
Control8=IDC_GREYCOUNT,static,1342308352
Control9=IDC_COLORLIST,static,1342177287

[DLG:IDD_POINT_LINEAR]
Type=1
Class=CLinearDlg
ControlCount=9
Control1=IDOK,button,1342242817
Control2=IDCANCEL,button,1342242816
Control3=IDC_STATIC,static,1342308352
Control4=IDC_BRIGHTNESS,edit,1350639744
Control5=IDC_PREVIEW,button,1342242819
Control6=IDC_STATIC,static,1342308352
Control7=IDC_CONTRAST,edit,1350631552
Control8=IDC_SLIDER2,msctls_trackbar32,1342242840
Control9=IDC_SLIDER1,msctls_trackbar32,1342242840

[DLG:IDD_GEO_PERS]
Type=1
Class=CPersDlg
ControlCount=16
Control1=IDOK,button,1342242817
Control2=IDCANCEL,button,1342242816
Control3=IDC_DISTANCE,edit,1350639744
Control4=IDC_SLIDER1,msctls_trackbar32,1342242840
Control5=IDC_THITA,edit,1350631552
Control6=IDC_SLIDER2,msctls_trackbar32,1342242841
Control7=IDC_PHAI,edit,1350639744
Control8=IDC_SLIDER3,msctls_trackbar32,1342242841
Control9=IDC_FOCUS,edit,1350639744
Control10=IDC_SLIDER4,msctls_trackbar32,1342242841
Control11=IDC_PREVIEW,button,1342242819
Control12=IDC_STATIC,static,1342308352
Control13=IDC_STATIC,static,1342308352
Control14=IDC_STATIC,static,1342308352
Control15=IDC_STATIC,static,1342308352
Control16=IDC_RESET,button,1342242816

[DLG:IDD_POINT_THRESHOLD]
Type=1
Class=CThresholdDlg
ControlCount=6
Control1=IDOK,button,1342242817
Control2=IDCANCEL,button,1342242816
Control3=IDC_STATIC,static,1342308352
Control4=IDC_THRESHOLD,edit,1350639744
Control5=IDC_PREVIEW,button,1342242819
Control6=IDC_SLIDER,msctls_trackbar32,1342242840

[TB:IDR_MAINFRAME]
Type=1
Class=?
Command1=ID_FILE_NEW
Command2=ID_FILE_OPEN
Command3=ID_FILE_SAVE
Command4=ID_APP_ABOUT
CommandCount=4

[MNU:IDR_MAINFRAME]
Type=1
Class=?
Command1=ID_FILE_NEW
Command2=ID_FILE_OPEN
Command3=ID_FILE_MRU_FILE1
Command4=ID_APP_EXIT
Command5=ID_VIEW_TOOLBAR
Command6=ID_VIEW_STATUS_BAR
Command7=ID_APP_ABOUT
CommandCount=7

[MNU:IDR_DIPTYPE]
Type=1
Class=?
Command1=ID_FILE_OPEN
Command2=ID_FILE_CLOSE
Command3=ID_FILE_SAVE
Command4=ID_FILE_SAVE_AS
Command5=ID_FILE_MRU_FILE1
Command6=ID_APP_EXIT
Command7=ID_EDIT_UNDO
Command8=ID_HISTOGRAM
Command9=ID_POINT_THRESHOLD
Command10=ID_POINT_NEGATIVE
Command11=ID_POINT_LINEAR
Command12=ID_POINT_EQUALIZATION
Command13=ID_ALG_OFFSET
Command14=ID_ALG_SUBSTRACT
Command15=ID_ALG_ADD
Command16=ID_GEO_HORIZON
Command17=ID_GEO_VERTICAL
Command18=ID_GEO_AFFINE
Command19=ID_GEO_PERS
Command20=ID_FIELD_AVERAGE
Command21=ID_FIELD_GAUSSIAN
Command22=ID_FIELD_ROBERTS
Command23=ID_FIELD_PREWITT
Command24=ID_FIELD_SOBEL
Command25=ID_FIELD_ISOBEL
Command26=ID_FIELD_LAPLACIAN
Command27=ID_FIELD_SUSAN
Command28=ID_FIELD_CANNY
Command29=ID_MOR_DILATION
Command30=ID_MOR_EROTION
Command31=ID_MOR_OPEN
Command32=ID_MOR_CLOSE
Command33=ID_MOR_EDGE
Command34=ID_MOR_SKELECTON
Command35=IDC_MOR_THIN
Command36=ID_WINDOW_NEW
Command37=ID_WINDOW_CASCADE
Command38=ID_WINDOW_TILE_HORZ
Command39=ID_WINDOW_ARRANGE
Command40=ID_APP_ABOUT
CommandCount=40

[ACL:IDR_MAINFRAME]
Type=1
Class=?
Command1=ID_FILE_NEW
Command2=ID_FILE_OPEN
Command3=ID_FILE_SAVE
Command4=ID_FILE_PRINT
Command5=ID_EDIT_UNDO
Command6=ID_EDIT_CUT
Command7=ID_EDIT_COPY
Command8=ID_EDIT_PASTE
Command9=ID_EDIT_UNDO
Command10=ID_EDIT_CUT
Command11=ID_EDIT_COPY
Command12=ID_EDIT_PASTE
Command13=ID_NEXT_PANE
Command14=ID_PREV_PANE
CommandCount=14

