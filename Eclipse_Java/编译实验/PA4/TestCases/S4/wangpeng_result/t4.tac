FUNCTION _Main_New : 
BASIC BLOCK 0 : 
  Def     = [ _T3 _T4 _T5 _T6 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T3 = 12 [ _T3 ]
    parm _T3 [ ]
    _T4 =  call _Alloc [ _T4 ]
    _T5 = 0 [ _T4 _T5 ]
    *(_T4 + 4) = _T5 [ _T4 _T5 ]
    *(_T4 + 8) = _T5 [ _T4 ]
    _T6 = VTBL <_Main> [ _T4 _T6 ]
    *(_T4 + 0) = _T6 [ _T4 ]
END BY RETURN, result = _T4

FUNCTION _Main.tester : 
BASIC BLOCK 0 : 
  Def     = [ _T7 _T8 _T9 ]
  liveUse = [ ]
  liveIn  = [ _T0 _T1 ]
  liveOut = [ _T0 _T1 _T7 ]
    _T7 = 1 [ _T0 _T1 _T7 ]
    _T8 = 0 [ _T0 _T1 _T7 _T8 ]
    _T9 = (_T7 < _T8) [ _T0 _T1 _T7 _T9 ]
END BY JZERO, if _T9 = 
    0 : goto 2; 1 : goto 1
BASIC BLOCK 1 : 
  Def     = [ _T10 ]
  liveUse = [ ]
  liveIn  = [ _T0 _T1 _T7 ]
  liveOut = [ _T0 _T1 _T7 ]
    _T10 = "Decaf runtime error: Cannot create negative-sized array\n" [ _T0 _T1 _T7 _T10 ]
    parm _T10 [ _T0 _T1 _T7 ]
    call _PrintString [ _T0 _T1 _T7 ]
    call _Halt [ _T0 _T1 _T7 ]
END BY JUMP, goto 2
BASIC BLOCK 2 : 
  Def     = [ _T11 _T12 _T13 _T14 _T15 ]
  liveUse = [ _T7 ]
  liveIn  = [ _T0 _T1 _T7 ]
  liveOut = [ _T0 _T1 _T11 _T13 _T14 _T15 ]
    _T11 = 4 [ _T0 _T1 _T7 _T11 ]
    _T12 = (_T11 * _T7) [ _T0 _T1 _T7 _T11 _T12 ]
    _T13 = (_T11 + _T12) [ _T0 _T1 _T7 _T11 _T13 ]
    parm _T13 [ _T0 _T1 _T7 _T11 _T13 ]
    _T14 =  call _Alloc [ _T0 _T1 _T7 _T11 _T13 _T14 ]
    *(_T14 + 0) = _T7 [ _T0 _T1 _T11 _T13 _T14 ]
    _T15 = 0 [ _T0 _T1 _T11 _T13 _T14 _T15 ]
    _T14 = (_T14 + _T13) [ _T0 _T1 _T11 _T13 _T14 _T15 ]
END BY JUMP, goto 3
BASIC BLOCK 3 : 
  Def     = [ _T13 ]
  liveUse = [ _T11 _T13 ]
  liveIn  = [ _T0 _T1 _T11 _T13 _T14 _T15 ]
  liveOut = [ _T0 _T1 _T11 _T13 _T14 _T15 ]
    _T13 = (_T13 - _T11) [ _T0 _T1 _T11 _T13 _T14 _T15 ]
END BY JZERO, if _T13 = 
    0 : goto 5; 1 : goto 4
BASIC BLOCK 4 : 
  Def     = [ _T14 ]
  liveUse = [ _T11 _T14 _T15 ]
  liveIn  = [ _T0 _T1 _T11 _T13 _T14 _T15 ]
  liveOut = [ _T0 _T1 _T11 _T13 _T14 _T15 ]
    _T14 = (_T14 - _T11) [ _T0 _T1 _T11 _T13 _T14 _T15 ]
    *(_T14 + 0) = _T15 [ _T0 _T1 _T11 _T13 _T14 _T15 ]
END BY JUMP, goto 3
BASIC BLOCK 5 : 
  Def     = [ _T16 _T17 ]
  liveUse = [ _T0 _T1 _T14 ]
  liveIn  = [ _T0 _T1 _T14 ]
  liveOut = [ _T1 ]
    *(_T0 + 8) = _T14 [ _T1 ]
    _T16 = 0 [ _T1 _T16 ]
    _T17 = (_T1 < _T16) [ _T1 _T17 ]
END BY JZERO, if _T17 = 
    0 : goto 7; 1 : goto 6
BASIC BLOCK 6 : 
  Def     = [ _T18 ]
  liveUse = [ ]
  liveIn  = [ _T1 ]
  liveOut = [ _T1 ]
    _T18 = "Decaf runtime error: Cannot create negative-sized array\n" [ _T1 _T18 ]
    parm _T18 [ _T1 ]
    call _PrintString [ _T1 ]
    call _Halt [ _T1 ]
END BY JUMP, goto 7
BASIC BLOCK 7 : 
  Def     = [ _T19 _T20 _T21 _T22 _T23 ]
  liveUse = [ _T1 ]
  liveIn  = [ _T1 ]
  liveOut = [ _T19 _T21 _T22 _T23 ]
    _T19 = 4 [ _T1 _T19 ]
    _T20 = (_T19 * _T1) [ _T1 _T19 _T20 ]
    _T21 = (_T19 + _T20) [ _T1 _T19 _T21 ]
    parm _T21 [ _T1 _T19 _T21 ]
    _T22 =  call _Alloc [ _T1 _T19 _T21 _T22 ]
    *(_T22 + 0) = _T1 [ _T19 _T21 _T22 ]
    _T23 = 0 [ _T19 _T21 _T22 _T23 ]
    _T22 = (_T22 + _T21) [ _T19 _T21 _T22 _T23 ]
END BY JUMP, goto 8
BASIC BLOCK 8 : 
  Def     = [ _T21 ]
  liveUse = [ _T19 _T21 ]
  liveIn  = [ _T19 _T21 _T22 _T23 ]
  liveOut = [ _T19 _T21 _T22 _T23 ]
    _T21 = (_T21 - _T19) [ _T19 _T21 _T22 _T23 ]
END BY JZERO, if _T21 = 
    0 : goto 10; 1 : goto 9
BASIC BLOCK 9 : 
  Def     = [ _T22 ]
  liveUse = [ _T19 _T22 _T23 ]
  liveIn  = [ _T19 _T21 _T22 _T23 ]
  liveOut = [ _T19 _T21 _T22 _T23 ]
    _T22 = (_T22 - _T19) [ _T19 _T21 _T22 _T23 ]
    *(_T22 + 0) = _T23 [ _T19 _T21 _T22 _T23 ]
END BY JUMP, goto 8
BASIC BLOCK 10 : 
  Def     = [ ]
  liveUse = [ _T22 ]
  liveIn  = [ _T22 ]
  liveOut = [ ]
END BY RETURN, result = _T22

FUNCTION _Main.start : 
BASIC BLOCK 0 : 
  Def     = [ _T24 _T27 ]
  liveUse = [ ]
  liveIn  = [ _T2 _T26 ]
  liveOut = [ _T2 _T24 _T26 ]
    _T27 = 1 [ _T2 _T26 _T27 ]
    _T24 = _T27 [ _T2 _T24 _T26 ]
END BY JUMP, goto 1
BASIC BLOCK 1 : 
  Def     = [ _T28 _T29 ]
  liveUse = [ _T24 ]
  liveIn  = [ _T2 _T24 _T26 ]
  liveOut = [ _T2 _T24 _T26 ]
    _T28 = 5 [ _T2 _T24 _T26 _T28 ]
    _T29 = (_T24 < _T28) [ _T2 _T24 _T26 _T29 ]
END BY JZERO, if _T29 = 
    0 : goto 5; 1 : goto 2
BASIC BLOCK 2 : 
  Def     = [ _T30 _T31 _T32 _T33 ]
  liveUse = [ _T24 ]
  liveIn  = [ _T2 _T24 _T26 ]
  liveOut = [ _T2 _T24 _T26 ]
    _T30 = 2 [ _T2 _T24 _T26 _T30 ]
    _T31 = (_T24 % _T30) [ _T2 _T24 _T26 _T31 ]
    _T32 = 0 [ _T2 _T24 _T26 _T31 _T32 ]
    _T33 = (_T31 == _T32) [ _T2 _T24 _T26 _T33 ]
END BY JZERO, if _T33 = 
    0 : goto 4; 1 : goto 3
BASIC BLOCK 3 : 
  Def     = [ _T26 _T34 _T35 _T36 ]
  liveUse = [ _T2 _T24 ]
  liveIn  = [ _T2 _T24 ]
  liveOut = [ _T26 ]
    parm _T2 [ _T2 _T24 ]
    parm _T24 [ _T2 ]
    _T34 = *(_T2 + 0) [ _T34 ]
    _T35 = *(_T34 + 0) [ _T35 ]
    _T36 =  call _T35 [ _T36 ]
    _T26 = _T36 [ _T26 ]
END BY JUMP, goto 5
BASIC BLOCK 4 : 
  Def     = [ _T24 _T37 _T38 _T39 _T40 ]
  liveUse = [ _T24 ]
  liveIn  = [ _T2 _T24 _T26 ]
  liveOut = [ _T2 _T24 _T26 ]
    _T37 = "Loop " [ _T2 _T24 _T26 _T37 ]
    parm _T37 [ _T2 _T24 _T26 ]
    call _PrintString [ _T2 _T24 _T26 ]
    parm _T24 [ _T2 _T24 _T26 ]
    call _PrintInt [ _T2 _T24 _T26 ]
    _T38 = "\n" [ _T2 _T24 _T26 _T38 ]
    parm _T38 [ _T2 _T24 _T26 ]
    call _PrintString [ _T2 _T24 _T26 ]
    _T39 = 1 [ _T2 _T24 _T26 _T39 ]
    _T40 = (_T24 + _T39) [ _T2 _T26 _T40 ]
    _T24 = _T40 [ _T2 _T24 _T26 ]
END BY JUMP, goto 1
BASIC BLOCK 5 : 
  Def     = [ _T41 _T42 _T43 ]
  liveUse = [ _T26 ]
  liveIn  = [ _T26 ]
  liveOut = [ _T26 _T41 ]
    _T41 = 0 [ _T26 _T41 ]
    _T42 = *(_T26 - 4) [ _T26 _T41 _T42 ]
    _T43 = (_T41 < _T42) [ _T26 _T41 _T43 ]
END BY JZERO, if _T43 = 
    0 : goto 7; 1 : goto 6
BASIC BLOCK 6 : 
  Def     = [ _T44 _T45 ]
  liveUse = [ _T41 ]
  liveIn  = [ _T26 _T41 ]
  liveOut = [ _T26 _T41 ]
    _T44 = 0 [ _T26 _T41 _T44 ]
    _T45 = (_T41 < _T44) [ _T26 _T41 _T45 ]
END BY JZERO, if _T45 = 
    0 : goto 8; 1 : goto 7
BASIC BLOCK 7 : 
  Def     = [ _T46 ]
  liveUse = [ ]
  liveIn  = [ _T26 _T41 ]
  liveOut = [ _T26 _T41 ]
    _T46 = "Decaf runtime error: Array subscript out of bounds\n" [ _T26 _T41 _T46 ]
    parm _T46 [ _T26 _T41 ]
    call _PrintString [ _T26 _T41 ]
    call _Halt [ _T26 _T41 ]
END BY JUMP, goto 8
BASIC BLOCK 8 : 
  Def     = [ _T47 _T48 _T49 _T50 _T51 _T52 _T53 ]
  liveUse = [ _T26 _T41 ]
  liveIn  = [ _T26 _T41 ]
  liveOut = [ _T26 _T51 ]
    _T47 = 0 [ _T26 _T41 _T47 ]
    _T48 = 4 [ _T26 _T41 _T47 _T48 ]
    _T49 = (_T41 * _T48) [ _T26 _T47 _T49 ]
    _T50 = (_T26 + _T49) [ _T26 _T47 _T50 ]
    *(_T50 + 0) = _T47 [ _T26 ]
    _T51 = 0 [ _T26 _T51 ]
    _T52 = *(_T26 - 4) [ _T26 _T51 _T52 ]
    _T53 = (_T51 < _T52) [ _T26 _T51 _T53 ]
END BY JZERO, if _T53 = 
    0 : goto 10; 1 : goto 9
BASIC BLOCK 9 : 
  Def     = [ _T54 _T55 ]
  liveUse = [ _T51 ]
  liveIn  = [ _T26 _T51 ]
  liveOut = [ _T26 _T51 ]
    _T54 = 0 [ _T26 _T51 _T54 ]
    _T55 = (_T51 < _T54) [ _T26 _T51 _T55 ]
END BY JZERO, if _T55 = 
    0 : goto 11; 1 : goto 10
BASIC BLOCK 10 : 
  Def     = [ _T56 ]
  liveUse = [ ]
  liveIn  = [ _T26 _T51 ]
  liveOut = [ _T26 _T51 ]
    _T56 = "Decaf runtime error: Array subscript out of bounds\n" [ _T26 _T51 _T56 ]
    parm _T56 [ _T26 _T51 ]
    call _PrintString [ _T26 _T51 ]
    call _Halt [ _T26 _T51 ]
END BY JUMP, goto 11
BASIC BLOCK 11 : 
  Def     = [ _T57 _T58 _T59 _T60 _T61 _T62 ]
  liveUse = [ _T26 _T51 ]
  liveIn  = [ _T26 _T51 ]
  liveOut = [ _T26 _T60 ]
    _T57 = 4 [ _T26 _T51 _T57 ]
    _T58 = (_T51 * _T57) [ _T26 _T58 ]
    _T59 = (_T26 + _T58) [ _T26 _T59 ]
    _T60 = *(_T59 + 0) [ _T26 _T60 ]
    _T61 = *(_T26 - 4) [ _T26 _T60 _T61 ]
    _T62 = (_T60 < _T61) [ _T26 _T60 _T62 ]
END BY JZERO, if _T62 = 
    0 : goto 13; 1 : goto 12
BASIC BLOCK 12 : 
  Def     = [ _T63 _T64 ]
  liveUse = [ _T60 ]
  liveIn  = [ _T26 _T60 ]
  liveOut = [ _T26 _T60 ]
    _T63 = 0 [ _T26 _T60 _T63 ]
    _T64 = (_T60 < _T63) [ _T26 _T60 _T64 ]
END BY JZERO, if _T64 = 
    0 : goto 14; 1 : goto 13
BASIC BLOCK 13 : 
  Def     = [ _T65 ]
  liveUse = [ ]
  liveIn  = [ _T26 _T60 ]
  liveOut = [ _T26 _T60 ]
    _T65 = "Decaf runtime error: Array subscript out of bounds\n" [ _T26 _T60 _T65 ]
    parm _T65 [ _T26 _T60 ]
    call _PrintString [ _T26 _T60 ]
    call _Halt [ _T26 _T60 ]
END BY JUMP, goto 14
BASIC BLOCK 14 : 
  Def     = [ _T66 _T67 _T68 _T69 _T70 _T71 _T72 ]
  liveUse = [ _T26 _T60 ]
  liveIn  = [ _T26 _T60 ]
  liveOut = [ ]
    _T66 = 4 [ _T26 _T60 _T66 ]
    _T67 = (_T60 * _T66) [ _T26 _T67 ]
    _T68 = (_T26 + _T67) [ _T26 _T68 ]
    _T69 = *(_T68 + 0) [ _T26 _T69 ]
    parm _T69 [ _T26 ]
    call _PrintInt [ _T26 ]
    _T70 = "\n" [ _T26 _T70 ]
    parm _T70 [ _T26 ]
    call _PrintString [ _T26 ]
    _T71 = *(_T26 - 4) [ _T71 ]
    parm _T71 [ ]
    call _PrintInt [ ]
    _T72 = "\n" [ _T72 ]
    parm _T72 [ ]
    call _PrintString [ ]
END BY RETURN, void result

FUNCTION main : 
BASIC BLOCK 0 : 
  Def     = [ _T73 _T74 _T75 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T73 =  call _Main_New [ _T73 ]
    parm _T73 [ _T73 ]
    _T74 = *(_T73 + 0) [ _T74 ]
    _T75 = *(_T74 + 4) [ _T75 ]
    call _T75 [ ]
END BY RETURN, void result

