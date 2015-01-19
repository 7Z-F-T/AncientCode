# Microsoft Developer Studio Generated NMAKE File, Based on ZoomPerspective.dsp
!IF "$(CFG)" == ""
CFG=ZoomPerspective - Win32 Debug
!MESSAGE No configuration specified. Defaulting to ZoomPerspective - Win32 Debug.
!ENDIF 

!IF "$(CFG)" != "ZoomPerspective - Win32 Release" && "$(CFG)" != "ZoomPerspective - Win32 Debug"
!MESSAGE Invalid configuration "$(CFG)" specified.
!MESSAGE You can specify a configuration when running NMAKE
!MESSAGE by defining the macro CFG on the command line. For example:
!MESSAGE 
!MESSAGE NMAKE /f "ZoomPerspective.mak" CFG="ZoomPerspective - Win32 Debug"
!MESSAGE 
!MESSAGE Possible choices for configuration are:
!MESSAGE 
!MESSAGE "ZoomPerspective - Win32 Release" (based on "Win32 (x86) Application")
!MESSAGE "ZoomPerspective - Win32 Debug" (based on "Win32 (x86) Application")
!MESSAGE 
!ERROR An invalid configuration is specified.
!ENDIF 

!IF "$(OS)" == "Windows_NT"
NULL=
!ELSE 
NULL=nul
!ENDIF 

CPP=cl.exe
MTL=midl.exe
RSC=rc.exe

!IF  "$(CFG)" == "ZoomPerspective - Win32 Release"

OUTDIR=.\Release
INTDIR=.\Release
# Begin Custom Macros
OutDir=.\Release
# End Custom Macros

ALL : "$(OUTDIR)\ZoomPerspective.exe"


CLEAN :
	-@erase "$(INTDIR)\ChildFrm.obj"
	-@erase "$(INTDIR)\DlgSetup.obj"
	-@erase "$(INTDIR)\MainFrm.obj"
	-@erase "$(INTDIR)\OGL.OBJ"
	-@erase "$(INTDIR)\StdAfx.obj"
	-@erase "$(INTDIR)\TEXTURE.OBJ"
	-@erase "$(INTDIR)\vc60.idb"
	-@erase "$(INTDIR)\View.obj"
	-@erase "$(INTDIR)\ZoomPerspective.obj"
	-@erase "$(INTDIR)\ZoomPerspective.pch"
	-@erase "$(INTDIR)\ZoomPerspective.res"
	-@erase "$(INTDIR)\ZoomPerspectiveDoc.obj"
	-@erase "$(OUTDIR)\ZoomPerspective.exe"

"$(OUTDIR)" :
    if not exist "$(OUTDIR)/$(NULL)" mkdir "$(OUTDIR)"

CPP_PROJ=/nologo /MD /W3 /GX /O2 /D "WIN32" /D "NDEBUG" /D "_WINDOWS" /D "_AFXDLL" /D "_MBCS" /Fp"$(INTDIR)\ZoomPerspective.pch" /Yu"stdafx.h" /Fo"$(INTDIR)\\" /Fd"$(INTDIR)\\" /FD /c 
MTL_PROJ=/nologo /D "NDEBUG" /mktyplib203 /win32 
RSC_PROJ=/l 0x409 /fo"$(INTDIR)\ZoomPerspective.res" /d "NDEBUG" /d "_AFXDLL" 
BSC32=bscmake.exe
BSC32_FLAGS=/nologo /o"$(OUTDIR)\ZoomPerspective.bsc" 
BSC32_SBRS= \
	
LINK32=link.exe
LINK32_FLAGS=opengl32.lib glu32.lib /nologo /subsystem:windows /incremental:no /pdb:"$(OUTDIR)\ZoomPerspective.pdb" /machine:I386 /out:"$(OUTDIR)\ZoomPerspective.exe" 
LINK32_OBJS= \
	"$(INTDIR)\ChildFrm.obj" \
	"$(INTDIR)\MainFrm.obj" \
	"$(INTDIR)\OGL.OBJ" \
	"$(INTDIR)\StdAfx.obj" \
	"$(INTDIR)\TEXTURE.OBJ" \
	"$(INTDIR)\View.obj" \
	"$(INTDIR)\ZoomPerspective.obj" \
	"$(INTDIR)\ZoomPerspectiveDoc.obj" \
	"$(INTDIR)\ZoomPerspective.res" \
	"$(INTDIR)\DlgSetup.obj"

"$(OUTDIR)\ZoomPerspective.exe" : "$(OUTDIR)" $(DEF_FILE) $(LINK32_OBJS)
    $(LINK32) @<<
  $(LINK32_FLAGS) $(LINK32_OBJS)
<<

!ELSEIF  "$(CFG)" == "ZoomPerspective - Win32 Debug"

OUTDIR=.\Debug
INTDIR=.\Debug
# Begin Custom Macros
OutDir=.\Debug
# End Custom Macros

ALL : "$(OUTDIR)\ZoomPerspective.exe"


CLEAN :
	-@erase "$(INTDIR)\ChildFrm.obj"
	-@erase "$(INTDIR)\DlgSetup.obj"
	-@erase "$(INTDIR)\MainFrm.obj"
	-@erase "$(INTDIR)\OGL.OBJ"
	-@erase "$(INTDIR)\StdAfx.obj"
	-@erase "$(INTDIR)\TEXTURE.OBJ"
	-@erase "$(INTDIR)\vc60.idb"
	-@erase "$(INTDIR)\vc60.pdb"
	-@erase "$(INTDIR)\View.obj"
	-@erase "$(INTDIR)\ZoomPerspective.obj"
	-@erase "$(INTDIR)\ZoomPerspective.pch"
	-@erase "$(INTDIR)\ZoomPerspective.res"
	-@erase "$(INTDIR)\ZoomPerspectiveDoc.obj"
	-@erase "$(OUTDIR)\ZoomPerspective.exe"
	-@erase "$(OUTDIR)\ZoomPerspective.ilk"
	-@erase "$(OUTDIR)\ZoomPerspective.pdb"

"$(OUTDIR)" :
    if not exist "$(OUTDIR)/$(NULL)" mkdir "$(OUTDIR)"

CPP_PROJ=/nologo /MDd /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_WINDOWS" /D "_AFXDLL" /D "_MBCS" /Fp"$(INTDIR)\ZoomPerspective.pch" /Yu"stdafx.h" /Fo"$(INTDIR)\\" /Fd"$(INTDIR)\\" /FD /GZ /c 
MTL_PROJ=/nologo /D "_DEBUG" /mktyplib203 /win32 
RSC_PROJ=/l 0x409 /fo"$(INTDIR)\ZoomPerspective.res" /d "_DEBUG" /d "_AFXDLL" 
BSC32=bscmake.exe
BSC32_FLAGS=/nologo /o"$(OUTDIR)\ZoomPerspective.bsc" 
BSC32_SBRS= \
	
LINK32=link.exe
LINK32_FLAGS=opengl32.lib glu32.lib /nologo /subsystem:windows /incremental:yes /pdb:"$(OUTDIR)\ZoomPerspective.pdb" /debug /machine:I386 /out:"$(OUTDIR)\ZoomPerspective.exe" /pdbtype:sept 
LINK32_OBJS= \
	"$(INTDIR)\ChildFrm.obj" \
	"$(INTDIR)\MainFrm.obj" \
	"$(INTDIR)\OGL.OBJ" \
	"$(INTDIR)\StdAfx.obj" \
	"$(INTDIR)\TEXTURE.OBJ" \
	"$(INTDIR)\View.obj" \
	"$(INTDIR)\ZoomPerspective.obj" \
	"$(INTDIR)\ZoomPerspectiveDoc.obj" \
	"$(INTDIR)\ZoomPerspective.res" \
	"$(INTDIR)\DlgSetup.obj"

"$(OUTDIR)\ZoomPerspective.exe" : "$(OUTDIR)" $(DEF_FILE) $(LINK32_OBJS)
    $(LINK32) @<<
  $(LINK32_FLAGS) $(LINK32_OBJS)
<<

!ENDIF 

.c{$(INTDIR)}.obj::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

.cpp{$(INTDIR)}.obj::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

.cxx{$(INTDIR)}.obj::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

.c{$(INTDIR)}.sbr::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

.cpp{$(INTDIR)}.sbr::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<

.cxx{$(INTDIR)}.sbr::
   $(CPP) @<<
   $(CPP_PROJ) $< 
<<


!IF "$(NO_EXTERNAL_DEPS)" != "1"
!IF EXISTS("ZoomPerspective.dep")
!INCLUDE "ZoomPerspective.dep"
!ELSE 
!MESSAGE Warning: cannot find "ZoomPerspective.dep"
!ENDIF 
!ENDIF 


!IF "$(CFG)" == "ZoomPerspective - Win32 Release" || "$(CFG)" == "ZoomPerspective - Win32 Debug"
SOURCE=.\ChildFrm.cpp

"$(INTDIR)\ChildFrm.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\ZoomPerspective.pch"


SOURCE=.\DlgSetup.cpp

"$(INTDIR)\DlgSetup.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\ZoomPerspective.pch"


SOURCE=.\MainFrm.cpp

"$(INTDIR)\MainFrm.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\ZoomPerspective.pch"


SOURCE=.\OGL.CPP

"$(INTDIR)\OGL.OBJ" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\ZoomPerspective.pch"


SOURCE=.\StdAfx.cpp

!IF  "$(CFG)" == "ZoomPerspective - Win32 Release"

CPP_SWITCHES=/nologo /MD /W3 /GX /O2 /D "WIN32" /D "NDEBUG" /D "_WINDOWS" /D "_AFXDLL" /D "_MBCS" /Fp"$(INTDIR)\ZoomPerspective.pch" /Yc"stdafx.h" /Fo"$(INTDIR)\\" /Fd"$(INTDIR)\\" /FD /c 

"$(INTDIR)\StdAfx.obj"	"$(INTDIR)\ZoomPerspective.pch" : $(SOURCE) "$(INTDIR)"
	$(CPP) @<<
  $(CPP_SWITCHES) $(SOURCE)
<<


!ELSEIF  "$(CFG)" == "ZoomPerspective - Win32 Debug"

CPP_SWITCHES=/nologo /MDd /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_WINDOWS" /D "_AFXDLL" /D "_MBCS" /Fp"$(INTDIR)\ZoomPerspective.pch" /Yc"stdafx.h" /Fo"$(INTDIR)\\" /Fd"$(INTDIR)\\" /FD /GZ /c 

"$(INTDIR)\StdAfx.obj"	"$(INTDIR)\ZoomPerspective.pch" : $(SOURCE) "$(INTDIR)"
	$(CPP) @<<
  $(CPP_SWITCHES) $(SOURCE)
<<


!ENDIF 

SOURCE=.\TEXTURE.CPP

"$(INTDIR)\TEXTURE.OBJ" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\ZoomPerspective.pch"


SOURCE=.\View.cpp

"$(INTDIR)\View.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\ZoomPerspective.pch"


SOURCE=.\ZoomPerspective.cpp

"$(INTDIR)\ZoomPerspective.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\ZoomPerspective.pch"


SOURCE=.\ZoomPerspective.rc

"$(INTDIR)\ZoomPerspective.res" : $(SOURCE) "$(INTDIR)"
	$(RSC) $(RSC_PROJ) $(SOURCE)


SOURCE=.\ZoomPerspectiveDoc.cpp

"$(INTDIR)\ZoomPerspectiveDoc.obj" : $(SOURCE) "$(INTDIR)" "$(INTDIR)\ZoomPerspective.pch"



!ENDIF 

