FUNCTION _Animal_New : 
BASIC BLOCK 0 : 
  Def     = [ _T5 _T6 _T7 _T8 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T5 = 12 [ _T5 ]
    parm _T5 [ ]
    _T6 =  call _Alloc [ _T6 ]
    _T7 = 0 [ _T6 _T7 ]
    *(_T6 + 4) = _T7 [ _T6 _T7 ]
    *(_T6 + 8) = _T7 [ _T6 ]
    _T8 = VTBL <_Animal> [ _T6 _T8 ]
    *(_T6 + 0) = _T8 [ _T6 ]
END BY RETURN, result = _T6

FUNCTION _Cow_New : 
BASIC BLOCK 0 : 
  Def     = [ _T14 _T15 _T16 _T17 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T14 = 16 [ _T14 ]
    parm _T14 [ ]
    _T15 =  call _Alloc [ _T15 ]
    _T16 = 0 [ _T15 _T16 ]
    *(_T15 + 4) = _T16 [ _T15 _T16 ]
    *(_T15 + 8) = _T16 [ _T15 _T16 ]
    *(_T15 + 12) = _T16 [ _T15 ]
    _T17 = VTBL <_Cow> [ _T15 _T17 ]
    *(_T15 + 0) = _T17 [ _T15 ]
END BY RETURN, result = _T15

FUNCTION _Main_New : 
BASIC BLOCK 0 : 
  Def     = [ _T18 _T19 _T20 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T18 = 4 [ _T18 ]
    parm _T18 [ ]
    _T19 =  call _Alloc [ _T19 ]
    _T20 = VTBL <_Main> [ _T19 _T20 ]
    *(_T19 + 0) = _T20 [ _T19 ]
END BY RETURN, result = _T19

FUNCTION _Animal.InitAnimal : 
BASIC BLOCK 0 : 
  Def     = [ ]
  liveUse = [ _T0 _T1 _T2 ]
  liveIn  = [ _T0 _T1 _T2 ]
  liveOut = [ ]
    *(_T0 + 4) = _T1 [ _T0 _T2 ]
    *(_T0 + 8) = _T2 [ ]
END BY RETURN, void result

FUNCTION _Animal.GetHeight : 
BASIC BLOCK 0 : 
  Def     = [ _T21 ]
  liveUse = [ _T3 ]
  liveIn  = [ _T3 ]
  liveOut = [ ]
    _T21 = *(_T3 + 4) [ _T21 ]
END BY RETURN, result = _T21

FUNCTION _Animal.GetMom : 
BASIC BLOCK 0 : 
  Def     = [ _T22 ]
  liveUse = [ _T4 ]
  liveIn  = [ _T4 ]
  liveOut = [ ]
    _T22 = *(_T4 + 8) [ _T22 ]
END BY RETURN, result = _T22

FUNCTION _Cow.InitCow : 
BASIC BLOCK 0 : 
  Def     = [ _T23 _T24 ]
  liveUse = [ _T9 _T10 _T11 _T12 ]
  liveIn  = [ _T9 _T10 _T11 _T12 ]
  liveOut = [ ]
    *(_T9 + 12) = _T12 [ _T9 _T10 _T11 ]
    parm _T9 [ _T9 _T10 _T11 ]
    parm _T10 [ _T9 _T11 ]
    parm _T11 [ _T9 ]
    _T23 = *(_T9 + 0) [ _T23 ]
    _T24 = *(_T23 + 0) [ _T24 ]
    call _T24 [ ]
END BY RETURN, void result

FUNCTION _Cow.IsSpottedCow : 
BASIC BLOCK 0 : 
  Def     = [ _T25 ]
  liveUse = [ _T13 ]
  liveIn  = [ _T13 ]
  liveOut = [ ]
    _T25 = *(_T13 + 12) [ _T25 ]
END BY RETURN, result = _T25

FUNCTION main : 
BASIC BLOCK 0 : 
  Def     = [ _T26 _T27 _T28 _T29 _T30 _T31 _T32 _T33 _T34 _T35 _T36 _T37 _T38 _T39 _T40 _T41 _T42 _T43 _T44 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T28 =  call _Cow_New [ _T28 ]
    _T26 = _T28 [ _T26 ]
    _T29 = 5 [ _T26 _T29 ]
    _T30 = 0 [ _T26 _T29 _T30 ]
    _T31 = 1 [ _T26 _T29 _T30 _T31 ]
    parm _T26 [ _T26 _T29 _T30 _T31 ]
    parm _T29 [ _T26 _T30 _T31 ]
    parm _T30 [ _T26 _T31 ]
    parm _T31 [ _T26 ]
    _T32 = *(_T26 + 0) [ _T26 _T32 ]
    _T33 = *(_T32 + 12) [ _T26 _T33 ]
    call _T33 [ _T26 ]
    _T27 = _T26 [ _T26 _T27 ]
    parm _T27 [ _T26 _T27 ]
    _T34 = *(_T27 + 0) [ _T26 _T27 _T34 ]
    _T35 = *(_T34 + 8) [ _T26 _T27 _T35 ]
    _T36 =  call _T35 [ _T26 _T27 ]
    _T37 = "spots: " [ _T26 _T27 _T37 ]
    parm _T37 [ _T26 _T27 ]
    call _PrintString [ _T26 _T27 ]
    parm _T26 [ _T26 _T27 ]
    _T38 = *(_T26 + 0) [ _T27 _T38 ]
    _T39 = *(_T38 + 16) [ _T27 _T39 ]
    _T40 =  call _T39 [ _T27 _T40 ]
    parm _T40 [ _T27 ]
    call _PrintBool [ _T27 ]
    _T41 = "    height: " [ _T27 _T41 ]
    parm _T41 [ _T27 ]
    call _PrintString [ _T27 ]
    parm _T27 [ _T27 ]
    _T42 = *(_T27 + 0) [ _T42 ]
    _T43 = *(_T42 + 4) [ _T43 ]
    _T44 =  call _T43 [ _T44 ]
    parm _T44 [ ]
    call _PrintInt [ ]
END BY RETURN, void result

