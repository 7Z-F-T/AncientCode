FUNCTION _Stack_New : 
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
    _T8 = VTBL <_Stack> [ _T6 _T8 ]
    *(_T6 + 0) = _T8 [ _T6 ]
END BY RETURN, result = _T6

FUNCTION _Stack.Init : 
BASIC BLOCK 0 : 
  Def     = [ _T9 _T10 _T11 ]
  liveUse = [ ]
  liveIn  = [ _T0 ]
  liveOut = [ _T0 _T9 ]
    _T9 = 100 [ _T0 _T9 ]
    _T10 = 0 [ _T0 _T9 _T10 ]
    _T11 = (_T9 < _T10) [ _T0 _T9 _T11 ]
END BY JZERO, if _T11 = 
    0 : goto 2; 1 : goto 1
BASIC BLOCK 1 : 
  Def     = [ _T12 ]
  liveUse = [ ]
  liveIn  = [ _T0 _T9 ]
  liveOut = [ _T0 _T9 ]
    _T12 = "Decaf runtime error: Cannot create negative-sized array\n" [ _T0 _T9 _T12 ]
    parm _T12 [ _T0 _T9 ]
    call _PrintString [ _T0 _T9 ]
    call _Halt [ _T0 _T9 ]
END BY JUMP, goto 2
BASIC BLOCK 2 : 
  Def     = [ _T13 _T14 _T15 _T16 _T17 ]
  liveUse = [ _T9 ]
  liveIn  = [ _T0 _T9 ]
  liveOut = [ _T0 _T13 _T15 _T16 _T17 ]
    _T13 = 4 [ _T0 _T9 _T13 ]
    _T14 = (_T13 * _T9) [ _T0 _T9 _T13 _T14 ]
    _T15 = (_T13 + _T14) [ _T0 _T9 _T13 _T15 ]
    parm _T15 [ _T0 _T9 _T13 _T15 ]
    _T16 =  call _Alloc [ _T0 _T9 _T13 _T15 _T16 ]
    *(_T16 + 0) = _T9 [ _T0 _T13 _T15 _T16 ]
    _T17 = 0 [ _T0 _T13 _T15 _T16 _T17 ]
    _T16 = (_T16 + _T15) [ _T0 _T13 _T15 _T16 _T17 ]
END BY JUMP, goto 3
BASIC BLOCK 3 : 
  Def     = [ _T15 ]
  liveUse = [ _T13 _T15 ]
  liveIn  = [ _T0 _T13 _T15 _T16 _T17 ]
  liveOut = [ _T0 _T13 _T15 _T16 _T17 ]
    _T15 = (_T15 - _T13) [ _T0 _T13 _T15 _T16 _T17 ]
END BY JZERO, if _T15 = 
    0 : goto 5; 1 : goto 4
BASIC BLOCK 4 : 
  Def     = [ _T16 ]
  liveUse = [ _T13 _T16 _T17 ]
  liveIn  = [ _T0 _T13 _T15 _T16 _T17 ]
  liveOut = [ _T0 _T13 _T15 _T16 _T17 ]
    _T16 = (_T16 - _T13) [ _T0 _T13 _T15 _T16 _T17 ]
    *(_T16 + 0) = _T17 [ _T0 _T13 _T15 _T16 _T17 ]
END BY JUMP, goto 3
BASIC BLOCK 5 : 
  Def     = [ _T18 _T19 _T20 _T21 ]
  liveUse = [ _T0 _T16 ]
  liveIn  = [ _T0 _T16 ]
  liveOut = [ ]
    *(_T0 + 8) = _T16 [ _T0 ]
    _T18 = 0 [ _T0 _T18 ]
    *(_T0 + 4) = _T18 [ _T0 ]
    _T19 = 3 [ _T0 _T19 ]
    parm _T0 [ _T0 _T19 ]
    parm _T19 [ _T0 ]
    _T20 = *(_T0 + 0) [ _T20 ]
    _T21 = *(_T20 + 4) [ _T21 ]
    call _T21 [ ]
END BY RETURN, void result

FUNCTION _Stack.Push : 
BASIC BLOCK 0 : 
  Def     = [ _T22 _T23 _T24 _T25 ]
  liveUse = [ _T1 ]
  liveIn  = [ _T1 _T2 ]
  liveOut = [ _T1 _T2 _T22 _T23 ]
    _T22 = *(_T1 + 8) [ _T1 _T2 _T22 ]
    _T23 = *(_T1 + 4) [ _T1 _T2 _T22 _T23 ]
    _T24 = *(_T22 - 4) [ _T1 _T2 _T22 _T23 _T24 ]
    _T25 = (_T23 < _T24) [ _T1 _T2 _T22 _T23 _T25 ]
END BY JZERO, if _T25 = 
    0 : goto 2; 1 : goto 1
BASIC BLOCK 1 : 
  Def     = [ _T26 _T27 ]
  liveUse = [ _T23 ]
  liveIn  = [ _T1 _T2 _T22 _T23 ]
  liveOut = [ _T1 _T2 _T22 _T23 ]
    _T26 = 0 [ _T1 _T2 _T22 _T23 _T26 ]
    _T27 = (_T23 < _T26) [ _T1 _T2 _T22 _T23 _T27 ]
END BY JZERO, if _T27 = 
    0 : goto 3; 1 : goto 2
BASIC BLOCK 2 : 
  Def     = [ _T28 ]
  liveUse = [ ]
  liveIn  = [ _T1 _T2 _T22 _T23 ]
  liveOut = [ _T1 _T2 _T22 _T23 ]
    _T28 = "Decaf runtime error: Array subscript out of bounds\n" [ _T1 _T2 _T22 _T23 _T28 ]
    parm _T28 [ _T1 _T2 _T22 _T23 ]
    call _PrintString [ _T1 _T2 _T22 _T23 ]
    call _Halt [ _T1 _T2 _T22 _T23 ]
END BY JUMP, goto 3
BASIC BLOCK 3 : 
  Def     = [ _T29 _T30 _T31 _T32 _T33 _T34 ]
  liveUse = [ _T1 _T2 _T22 _T23 ]
  liveIn  = [ _T1 _T2 _T22 _T23 ]
  liveOut = [ ]
    _T29 = 4 [ _T1 _T2 _T22 _T23 _T29 ]
    _T30 = (_T23 * _T29) [ _T1 _T2 _T22 _T30 ]
    _T31 = (_T22 + _T30) [ _T1 _T2 _T31 ]
    *(_T31 + 0) = _T2 [ _T1 ]
    _T32 = *(_T1 + 4) [ _T1 _T32 ]
    _T33 = 1 [ _T1 _T32 _T33 ]
    _T34 = (_T32 + _T33) [ _T1 _T34 ]
    *(_T1 + 4) = _T34 [ ]
END BY RETURN, void result

FUNCTION _Stack.Pop : 
BASIC BLOCK 0 : 
  Def     = [ _T36 _T37 _T38 _T39 _T40 _T41 ]
  liveUse = [ _T3 ]
  liveIn  = [ _T3 ]
  liveOut = [ _T3 _T36 _T39 ]
    _T36 = *(_T3 + 8) [ _T3 _T36 ]
    _T37 = *(_T3 + 4) [ _T3 _T36 _T37 ]
    _T38 = 1 [ _T3 _T36 _T37 _T38 ]
    _T39 = (_T37 - _T38) [ _T3 _T36 _T39 ]
    _T40 = *(_T36 - 4) [ _T3 _T36 _T39 _T40 ]
    _T41 = (_T39 < _T40) [ _T3 _T36 _T39 _T41 ]
END BY JZERO, if _T41 = 
    0 : goto 2; 1 : goto 1
BASIC BLOCK 1 : 
  Def     = [ _T42 _T43 ]
  liveUse = [ _T39 ]
  liveIn  = [ _T3 _T36 _T39 ]
  liveOut = [ _T3 _T36 _T39 ]
    _T42 = 0 [ _T3 _T36 _T39 _T42 ]
    _T43 = (_T39 < _T42) [ _T3 _T36 _T39 _T43 ]
END BY JZERO, if _T43 = 
    0 : goto 3; 1 : goto 2
BASIC BLOCK 2 : 
  Def     = [ _T44 ]
  liveUse = [ ]
  liveIn  = [ _T3 _T36 _T39 ]
  liveOut = [ _T3 _T36 _T39 ]
    _T44 = "Decaf runtime error: Array subscript out of bounds\n" [ _T3 _T36 _T39 _T44 ]
    parm _T44 [ _T3 _T36 _T39 ]
    call _PrintString [ _T3 _T36 _T39 ]
    call _Halt [ _T3 _T36 _T39 ]
END BY JUMP, goto 3
BASIC BLOCK 3 : 
  Def     = [ _T35 _T45 _T46 _T47 _T48 _T49 _T50 _T51 ]
  liveUse = [ _T3 _T36 _T39 ]
  liveIn  = [ _T3 _T36 _T39 ]
  liveOut = [ ]
    _T45 = 4 [ _T3 _T36 _T39 _T45 ]
    _T46 = (_T39 * _T45) [ _T3 _T36 _T46 ]
    _T47 = (_T36 + _T46) [ _T3 _T47 ]
    _T48 = *(_T47 + 0) [ _T3 _T48 ]
    _T35 = _T48 [ _T3 _T35 ]
    _T49 = *(_T3 + 4) [ _T3 _T35 _T49 ]
    _T50 = 1 [ _T3 _T35 _T49 _T50 ]
    _T51 = (_T49 - _T50) [ _T3 _T35 _T51 ]
    *(_T3 + 4) = _T51 [ _T35 ]
END BY RETURN, result = _T35

FUNCTION _Stack.NumElems : 
BASIC BLOCK 0 : 
  Def     = [ _T52 ]
  liveUse = [ _T4 ]
  liveIn  = [ _T4 ]
  liveOut = [ ]
    _T52 = *(_T4 + 4) [ _T52 ]
END BY RETURN, result = _T52

FUNCTION main : 
BASIC BLOCK 0 : 
  Def     = [ _T53 _T54 _T55 _T56 _T57 _T58 _T59 _T60 _T61 _T62 _T63 _T64 _T65 _T66 _T67 _T68 _T69 _T70 _T71 _T72 _T73 _T74 _T75 _T76 _T77 _T78 _T79 _T80 _T81 _T82 _T83 _T84 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T54 =  call _Stack_New [ _T54 ]
    _T53 = _T54 [ _T53 ]
    parm _T53 [ _T53 ]
    _T55 = *(_T53 + 0) [ _T53 _T55 ]
    _T56 = *(_T55 + 0) [ _T53 _T56 ]
    call _T56 [ _T53 ]
    _T57 = 3 [ _T53 _T57 ]
    parm _T53 [ _T53 _T57 ]
    parm _T57 [ _T53 ]
    _T58 = *(_T53 + 0) [ _T53 _T58 ]
    _T59 = *(_T58 + 4) [ _T53 _T59 ]
    call _T59 [ _T53 ]
    _T60 = 7 [ _T53 _T60 ]
    parm _T53 [ _T53 _T60 ]
    parm _T60 [ _T53 ]
    _T61 = *(_T53 + 0) [ _T53 _T61 ]
    _T62 = *(_T61 + 4) [ _T53 _T62 ]
    call _T62 [ _T53 ]
    _T63 = 4 [ _T53 _T63 ]
    parm _T53 [ _T53 _T63 ]
    parm _T63 [ _T53 ]
    _T64 = *(_T53 + 0) [ _T53 _T64 ]
    _T65 = *(_T64 + 4) [ _T53 _T65 ]
    call _T65 [ _T53 ]
    parm _T53 [ _T53 ]
    _T66 = *(_T53 + 0) [ _T53 _T66 ]
    _T67 = *(_T66 + 12) [ _T53 _T67 ]
    _T68 =  call _T67 [ _T53 _T68 ]
    parm _T68 [ _T53 ]
    call _PrintInt [ _T53 ]
    _T69 = " " [ _T53 _T69 ]
    parm _T69 [ _T53 ]
    call _PrintString [ _T53 ]
    parm _T53 [ _T53 ]
    _T70 = *(_T53 + 0) [ _T53 _T70 ]
    _T71 = *(_T70 + 8) [ _T53 _T71 ]
    _T72 =  call _T71 [ _T53 _T72 ]
    parm _T72 [ _T53 ]
    call _PrintInt [ _T53 ]
    _T73 = " " [ _T53 _T73 ]
    parm _T73 [ _T53 ]
    call _PrintString [ _T53 ]
    parm _T53 [ _T53 ]
    _T74 = *(_T53 + 0) [ _T53 _T74 ]
    _T75 = *(_T74 + 8) [ _T53 _T75 ]
    _T76 =  call _T75 [ _T53 _T76 ]
    parm _T76 [ _T53 ]
    call _PrintInt [ _T53 ]
    _T77 = " " [ _T53 _T77 ]
    parm _T77 [ _T53 ]
    call _PrintString [ _T53 ]
    parm _T53 [ _T53 ]
    _T78 = *(_T53 + 0) [ _T53 _T78 ]
    _T79 = *(_T78 + 8) [ _T53 _T79 ]
    _T80 =  call _T79 [ _T53 _T80 ]
    parm _T80 [ _T53 ]
    call _PrintInt [ _T53 ]
    _T81 = " " [ _T53 _T81 ]
    parm _T81 [ _T53 ]
    call _PrintString [ _T53 ]
    parm _T53 [ _T53 ]
    _T82 = *(_T53 + 0) [ _T82 ]
    _T83 = *(_T82 + 12) [ _T83 ]
    _T84 =  call _T83 [ _T84 ]
    parm _T84 [ ]
    call _PrintInt [ ]
END BY RETURN, void result

