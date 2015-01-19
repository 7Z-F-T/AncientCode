FUNCTION _Cow_New : 
BASIC BLOCK 0 : 
  Def     = [ _T4 _T5 _T6 _T7 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T4 = 12 [ _T4 ]
    parm _T4 [ ]
    _T5 =  call _Alloc [ _T5 ]
    _T6 = 0 [ _T5 _T6 ]
    *(_T5 + 4) = _T6 [ _T5 _T6 ]
    *(_T5 + 8) = _T6 [ _T5 ]
    _T7 = VTBL <_Cow> [ _T5 _T7 ]
    *(_T5 + 0) = _T7 [ _T5 ]
END BY RETURN, result = _T5

FUNCTION _Main_New : 
BASIC BLOCK 0 : 
  Def     = [ _T8 _T9 _T10 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T8 = 4 [ _T8 ]
    parm _T8 [ ]
    _T9 =  call _Alloc [ _T9 ]
    _T10 = VTBL <_Main> [ _T9 _T10 ]
    *(_T9 + 0) = _T10 [ _T9 ]
END BY RETURN, result = _T9

FUNCTION _Cow.Init : 
BASIC BLOCK 0 : 
  Def     = [ ]
  liveUse = [ _T0 _T1 _T2 ]
  liveIn  = [ _T0 _T1 _T2 ]
  liveOut = [ ]
    *(_T0 + 8) = _T1 [ _T0 _T2 ]
    *(_T0 + 4) = _T2 [ ]
END BY RETURN, void result

FUNCTION _Cow.Moo : 
BASIC BLOCK 0 : 
  Def     = [ _T11 _T12 _T13 _T14 ]
  liveUse = [ _T3 ]
  liveIn  = [ _T3 ]
  liveOut = [ ]
    _T11 = *(_T3 + 4) [ _T3 _T11 ]
    parm _T11 [ _T3 ]
    call _PrintInt [ _T3 ]
    _T12 = " " [ _T3 _T12 ]
    parm _T12 [ _T3 ]
    call _PrintString [ _T3 ]
    _T13 = *(_T3 + 8) [ _T13 ]
    parm _T13 [ ]
    call _PrintInt [ ]
    _T14 = "\n" [ _T14 ]
    parm _T14 [ ]
    call _PrintString [ ]
END BY RETURN, void result

FUNCTION main : 
BASIC BLOCK 0 : 
  Def     = [ _T15 _T16 _T17 _T18 _T19 _T20 _T21 _T22 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T16 =  call _Cow_New [ _T16 ]
    _T15 = _T16 [ _T15 ]
    _T17 = 100 [ _T15 _T17 ]
    _T18 = 122 [ _T15 _T17 _T18 ]
    parm _T15 [ _T15 _T17 _T18 ]
    parm _T17 [ _T15 _T18 ]
    parm _T18 [ _T15 ]
    _T19 = *(_T15 + 0) [ _T15 _T19 ]
    _T20 = *(_T19 + 0) [ _T15 _T20 ]
    call _T20 [ _T15 ]
    parm _T15 [ _T15 ]
    _T21 = *(_T15 + 0) [ _T21 ]
    _T22 = *(_T21 + 4) [ _T22 ]
    call _T22 [ ]
END BY RETURN, void result

