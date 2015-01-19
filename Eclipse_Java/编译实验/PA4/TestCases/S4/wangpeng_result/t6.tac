FUNCTION _Main_New : 
BASIC BLOCK 0 : 
  Def     = [ _T3 _T4 _T5 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T3 = 4 [ _T3 ]
    parm _T3 [ ]
    _T4 =  call _Alloc [ _T4 ]
    _T5 = VTBL <_Main> [ _T4 _T5 ]
    *(_T4 + 0) = _T5 [ _T4 ]
END BY RETURN, result = _T4

FUNCTION _Main.Binky : 
BASIC BLOCK 0 : 
  Def     = [ _T6 _T7 _T8 ]
  liveUse = [ _T2 ]
  liveIn  = [ _T1 _T2 ]
  liveOut = [ _T1 _T2 _T6 ]
    _T6 = 0 [ _T1 _T2 _T6 ]
    _T7 = *(_T2 - 4) [ _T1 _T2 _T6 _T7 ]
    _T8 = (_T6 < _T7) [ _T1 _T2 _T6 _T8 ]
END BY JZERO, if _T8 = 
    0 : goto 2; 1 : goto 1
BASIC BLOCK 1 : 
  Def     = [ _T9 _T10 ]
  liveUse = [ _T6 ]
  liveIn  = [ _T1 _T2 _T6 ]
  liveOut = [ _T1 _T2 _T6 ]
    _T9 = 0 [ _T1 _T2 _T6 _T9 ]
    _T10 = (_T6 < _T9) [ _T1 _T2 _T6 _T10 ]
END BY JZERO, if _T10 = 
    0 : goto 3; 1 : goto 2
BASIC BLOCK 2 : 
  Def     = [ _T11 ]
  liveUse = [ ]
  liveIn  = [ _T1 _T2 _T6 ]
  liveOut = [ _T1 _T2 _T6 ]
    _T11 = "Decaf runtime error: Array subscript out of bounds\n" [ _T1 _T2 _T6 _T11 ]
    parm _T11 [ _T1 _T2 _T6 ]
    call _PrintString [ _T1 _T2 _T6 ]
    call _Halt [ _T1 _T2 _T6 ]
END BY JUMP, goto 3
BASIC BLOCK 3 : 
  Def     = [ _T12 _T13 _T14 _T15 _T16 _T17 ]
  liveUse = [ _T1 _T2 _T6 ]
  liveIn  = [ _T1 _T2 _T6 ]
  liveOut = [ _T1 _T15 ]
    _T12 = 4 [ _T1 _T2 _T6 _T12 ]
    _T13 = (_T6 * _T12) [ _T1 _T2 _T13 ]
    _T14 = (_T2 + _T13) [ _T1 _T14 ]
    _T15 = *(_T14 + 0) [ _T1 _T15 ]
    _T16 = *(_T1 - 4) [ _T1 _T15 _T16 ]
    _T17 = (_T15 < _T16) [ _T1 _T15 _T17 ]
END BY JZERO, if _T17 = 
    0 : goto 5; 1 : goto 4
BASIC BLOCK 4 : 
  Def     = [ _T18 _T19 ]
  liveUse = [ _T15 ]
  liveIn  = [ _T1 _T15 ]
  liveOut = [ _T1 _T15 ]
    _T18 = 0 [ _T1 _T15 _T18 ]
    _T19 = (_T15 < _T18) [ _T1 _T15 _T19 ]
END BY JZERO, if _T19 = 
    0 : goto 6; 1 : goto 5
BASIC BLOCK 5 : 
  Def     = [ _T20 ]
  liveUse = [ ]
  liveIn  = [ _T1 _T15 ]
  liveOut = [ _T1 _T15 ]
    _T20 = "Decaf runtime error: Array subscript out of bounds\n" [ _T1 _T15 _T20 ]
    parm _T20 [ _T1 _T15 ]
    call _PrintString [ _T1 _T15 ]
    call _Halt [ _T1 _T15 ]
END BY JUMP, goto 6
BASIC BLOCK 6 : 
  Def     = [ _T21 _T22 _T23 _T24 ]
  liveUse = [ _T1 _T15 ]
  liveIn  = [ _T1 _T15 ]
  liveOut = [ ]
    _T21 = 4 [ _T1 _T15 _T21 ]
    _T22 = (_T15 * _T21) [ _T1 _T22 ]
    _T23 = (_T1 + _T22) [ _T23 ]
    _T24 = *(_T23 + 0) [ _T24 ]
END BY RETURN, result = _T24

FUNCTION main : 
BASIC BLOCK 0 : 
  Def     = [ _T27 _T28 _T29 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ _T27 ]
    _T27 = 5 [ _T27 ]
    _T28 = 0 [ _T27 _T28 ]
    _T29 = (_T27 < _T28) [ _T27 _T29 ]
END BY JZERO, if _T29 = 
    0 : goto 2; 1 : goto 1
BASIC BLOCK 1 : 
  Def     = [ _T30 ]
  liveUse = [ ]
  liveIn  = [ _T27 ]
  liveOut = [ _T27 ]
    _T30 = "Decaf runtime error: Cannot create negative-sized array\n" [ _T27 _T30 ]
    parm _T30 [ _T27 ]
    call _PrintString [ _T27 ]
    call _Halt [ _T27 ]
END BY JUMP, goto 2
BASIC BLOCK 2 : 
  Def     = [ _T31 _T32 _T33 _T34 _T35 ]
  liveUse = [ _T27 ]
  liveIn  = [ _T27 ]
  liveOut = [ _T31 _T33 _T34 _T35 ]
    _T31 = 4 [ _T27 _T31 ]
    _T32 = (_T31 * _T27) [ _T27 _T31 _T32 ]
    _T33 = (_T31 + _T32) [ _T27 _T31 _T33 ]
    parm _T33 [ _T27 _T31 _T33 ]
    _T34 =  call _Alloc [ _T27 _T31 _T33 _T34 ]
    *(_T34 + 0) = _T27 [ _T31 _T33 _T34 ]
    _T35 = 0 [ _T31 _T33 _T34 _T35 ]
    _T34 = (_T34 + _T33) [ _T31 _T33 _T34 _T35 ]
END BY JUMP, goto 3
BASIC BLOCK 3 : 
  Def     = [ _T33 ]
  liveUse = [ _T31 _T33 ]
  liveIn  = [ _T31 _T33 _T34 _T35 ]
  liveOut = [ _T31 _T33 _T34 _T35 ]
    _T33 = (_T33 - _T31) [ _T31 _T33 _T34 _T35 ]
END BY JZERO, if _T33 = 
    0 : goto 5; 1 : goto 4
BASIC BLOCK 4 : 
  Def     = [ _T34 ]
  liveUse = [ _T31 _T34 _T35 ]
  liveIn  = [ _T31 _T33 _T34 _T35 ]
  liveOut = [ _T31 _T33 _T34 _T35 ]
    _T34 = (_T34 - _T31) [ _T31 _T33 _T34 _T35 ]
    *(_T34 + 0) = _T35 [ _T31 _T33 _T34 _T35 ]
END BY JUMP, goto 3
BASIC BLOCK 5 : 
  Def     = [ _T26 _T36 _T37 _T38 ]
  liveUse = [ _T34 ]
  liveIn  = [ _T34 ]
  liveOut = [ _T26 _T36 ]
    _T26 = _T34 [ _T26 ]
    _T36 = 0 [ _T26 _T36 ]
    _T37 = *(_T26 - 4) [ _T26 _T36 _T37 ]
    _T38 = (_T36 < _T37) [ _T26 _T36 _T38 ]
END BY JZERO, if _T38 = 
    0 : goto 7; 1 : goto 6
BASIC BLOCK 6 : 
  Def     = [ _T39 _T40 ]
  liveUse = [ _T36 ]
  liveIn  = [ _T26 _T36 ]
  liveOut = [ _T26 _T36 ]
    _T39 = 0 [ _T26 _T36 _T39 ]
    _T40 = (_T36 < _T39) [ _T26 _T36 _T40 ]
END BY JZERO, if _T40 = 
    0 : goto 8; 1 : goto 7
BASIC BLOCK 7 : 
  Def     = [ _T41 ]
  liveUse = [ ]
  liveIn  = [ _T26 _T36 ]
  liveOut = [ _T26 _T36 ]
    _T41 = "Decaf runtime error: Array subscript out of bounds\n" [ _T26 _T36 _T41 ]
    parm _T41 [ _T26 _T36 ]
    call _PrintString [ _T26 _T36 ]
    call _Halt [ _T26 _T36 ]
END BY JUMP, goto 8
BASIC BLOCK 8 : 
  Def     = [ _T42 _T43 _T44 ]
  liveUse = [ ]
  liveIn  = [ _T26 _T36 ]
  liveOut = [ _T26 _T36 _T42 ]
    _T42 = 12 [ _T26 _T36 _T42 ]
    _T43 = 0 [ _T26 _T36 _T42 _T43 ]
    _T44 = (_T42 < _T43) [ _T26 _T36 _T42 _T44 ]
END BY JZERO, if _T44 = 
    0 : goto 10; 1 : goto 9
BASIC BLOCK 9 : 
  Def     = [ _T45 ]
  liveUse = [ ]
  liveIn  = [ _T26 _T36 _T42 ]
  liveOut = [ _T26 _T36 _T42 ]
    _T45 = "Decaf runtime error: Cannot create negative-sized array\n" [ _T26 _T36 _T42 _T45 ]
    parm _T45 [ _T26 _T36 _T42 ]
    call _PrintString [ _T26 _T36 _T42 ]
    call _Halt [ _T26 _T36 _T42 ]
END BY JUMP, goto 10
BASIC BLOCK 10 : 
  Def     = [ _T46 _T47 _T48 _T49 _T50 ]
  liveUse = [ _T42 ]
  liveIn  = [ _T26 _T36 _T42 ]
  liveOut = [ _T26 _T36 _T46 _T48 _T49 _T50 ]
    _T46 = 4 [ _T26 _T36 _T42 _T46 ]
    _T47 = (_T46 * _T42) [ _T26 _T36 _T42 _T46 _T47 ]
    _T48 = (_T46 + _T47) [ _T26 _T36 _T42 _T46 _T48 ]
    parm _T48 [ _T26 _T36 _T42 _T46 _T48 ]
    _T49 =  call _Alloc [ _T26 _T36 _T42 _T46 _T48 _T49 ]
    *(_T49 + 0) = _T42 [ _T26 _T36 _T46 _T48 _T49 ]
    _T50 = 0 [ _T26 _T36 _T46 _T48 _T49 _T50 ]
    _T49 = (_T49 + _T48) [ _T26 _T36 _T46 _T48 _T49 _T50 ]
END BY JUMP, goto 11
BASIC BLOCK 11 : 
  Def     = [ _T48 ]
  liveUse = [ _T46 _T48 ]
  liveIn  = [ _T26 _T36 _T46 _T48 _T49 _T50 ]
  liveOut = [ _T26 _T36 _T46 _T48 _T49 _T50 ]
    _T48 = (_T48 - _T46) [ _T26 _T36 _T46 _T48 _T49 _T50 ]
END BY JZERO, if _T48 = 
    0 : goto 13; 1 : goto 12
BASIC BLOCK 12 : 
  Def     = [ _T49 ]
  liveUse = [ _T46 _T49 _T50 ]
  liveIn  = [ _T26 _T36 _T46 _T48 _T49 _T50 ]
  liveOut = [ _T26 _T36 _T46 _T48 _T49 _T50 ]
    _T49 = (_T49 - _T46) [ _T26 _T36 _T46 _T48 _T49 _T50 ]
    *(_T49 + 0) = _T50 [ _T26 _T36 _T46 _T48 _T49 _T50 ]
END BY JUMP, goto 11
BASIC BLOCK 13 : 
  Def     = [ _T51 _T52 _T53 _T54 _T55 _T56 ]
  liveUse = [ _T26 _T36 _T49 ]
  liveIn  = [ _T26 _T36 _T49 ]
  liveOut = [ _T26 _T54 ]
    _T51 = 4 [ _T26 _T36 _T49 _T51 ]
    _T52 = (_T36 * _T51) [ _T26 _T49 _T52 ]
    _T53 = (_T26 + _T52) [ _T26 _T49 _T53 ]
    *(_T53 + 0) = _T49 [ _T26 ]
    _T54 = 10 [ _T26 _T54 ]
    _T55 = 0 [ _T26 _T54 _T55 ]
    _T56 = (_T54 < _T55) [ _T26 _T54 _T56 ]
END BY JZERO, if _T56 = 
    0 : goto 15; 1 : goto 14
BASIC BLOCK 14 : 
  Def     = [ _T57 ]
  liveUse = [ ]
  liveIn  = [ _T26 _T54 ]
  liveOut = [ _T26 _T54 ]
    _T57 = "Decaf runtime error: Cannot create negative-sized array\n" [ _T26 _T54 _T57 ]
    parm _T57 [ _T26 _T54 ]
    call _PrintString [ _T26 _T54 ]
    call _Halt [ _T26 _T54 ]
END BY JUMP, goto 15
BASIC BLOCK 15 : 
  Def     = [ _T58 _T59 _T60 _T61 _T62 ]
  liveUse = [ _T54 ]
  liveIn  = [ _T26 _T54 ]
  liveOut = [ _T26 _T58 _T60 _T61 _T62 ]
    _T58 = 4 [ _T26 _T54 _T58 ]
    _T59 = (_T58 * _T54) [ _T26 _T54 _T58 _T59 ]
    _T60 = (_T58 + _T59) [ _T26 _T54 _T58 _T60 ]
    parm _T60 [ _T26 _T54 _T58 _T60 ]
    _T61 =  call _Alloc [ _T26 _T54 _T58 _T60 _T61 ]
    *(_T61 + 0) = _T54 [ _T26 _T58 _T60 _T61 ]
    _T62 = 0 [ _T26 _T58 _T60 _T61 _T62 ]
    _T61 = (_T61 + _T60) [ _T26 _T58 _T60 _T61 _T62 ]
END BY JUMP, goto 16
BASIC BLOCK 16 : 
  Def     = [ _T60 ]
  liveUse = [ _T58 _T60 ]
  liveIn  = [ _T26 _T58 _T60 _T61 _T62 ]
  liveOut = [ _T26 _T58 _T60 _T61 _T62 ]
    _T60 = (_T60 - _T58) [ _T26 _T58 _T60 _T61 _T62 ]
END BY JZERO, if _T60 = 
    0 : goto 18; 1 : goto 17
BASIC BLOCK 17 : 
  Def     = [ _T61 ]
  liveUse = [ _T58 _T61 _T62 ]
  liveIn  = [ _T26 _T58 _T60 _T61 _T62 ]
  liveOut = [ _T26 _T58 _T60 _T61 _T62 ]
    _T61 = (_T61 - _T58) [ _T26 _T58 _T60 _T61 _T62 ]
    *(_T61 + 0) = _T62 [ _T26 _T58 _T60 _T61 _T62 ]
END BY JUMP, goto 16
BASIC BLOCK 18 : 
  Def     = [ _T25 _T63 _T64 _T65 ]
  liveUse = [ _T61 ]
  liveIn  = [ _T26 _T61 ]
  liveOut = [ _T25 _T26 _T63 ]
    _T25 = _T61 [ _T25 _T26 ]
    _T63 = 0 [ _T25 _T26 _T63 ]
    _T64 = *(_T25 - 4) [ _T25 _T26 _T63 _T64 ]
    _T65 = (_T63 < _T64) [ _T25 _T26 _T63 _T65 ]
END BY JZERO, if _T65 = 
    0 : goto 20; 1 : goto 19
BASIC BLOCK 19 : 
  Def     = [ _T66 _T67 ]
  liveUse = [ _T63 ]
  liveIn  = [ _T25 _T26 _T63 ]
  liveOut = [ _T25 _T26 _T63 ]
    _T66 = 0 [ _T25 _T26 _T63 _T66 ]
    _T67 = (_T63 < _T66) [ _T25 _T26 _T63 _T67 ]
END BY JZERO, if _T67 = 
    0 : goto 21; 1 : goto 20
BASIC BLOCK 20 : 
  Def     = [ _T68 ]
  liveUse = [ ]
  liveIn  = [ _T25 _T26 _T63 ]
  liveOut = [ _T25 _T26 _T63 ]
    _T68 = "Decaf runtime error: Array subscript out of bounds\n" [ _T25 _T26 _T63 _T68 ]
    parm _T68 [ _T25 _T26 _T63 ]
    call _PrintString [ _T25 _T26 _T63 ]
    call _Halt [ _T25 _T26 _T63 ]
END BY JUMP, goto 21
BASIC BLOCK 21 : 
  Def     = [ _T69 _T70 _T71 _T72 _T73 _T74 _T75 _T76 _T77 _T78 _T79 _T80 _T81 _T82 _T83 ]
  liveUse = [ _T25 _T26 _T63 ]
  liveIn  = [ _T25 _T26 _T63 ]
  liveOut = [ _T25 _T26 _T81 ]
    _T69 = 4 [ _T25 _T26 _T63 _T69 ]
    _T70 = 5 [ _T25 _T26 _T63 _T69 _T70 ]
    _T71 = 3 [ _T25 _T26 _T63 _T69 _T70 _T71 ]
    _T72 = (_T70 * _T71) [ _T25 _T26 _T63 _T69 _T72 ]
    _T73 = 4 [ _T25 _T26 _T63 _T69 _T72 _T73 ]
    _T74 = (_T72 / _T73) [ _T25 _T26 _T63 _T69 _T74 ]
    _T75 = 2 [ _T25 _T26 _T63 _T69 _T74 _T75 ]
    _T76 = (_T74 % _T75) [ _T25 _T26 _T63 _T69 _T76 ]
    _T77 = (_T69 + _T76) [ _T25 _T26 _T63 _T77 ]
    _T78 = 4 [ _T25 _T26 _T63 _T77 _T78 ]
    _T79 = (_T63 * _T78) [ _T25 _T26 _T77 _T79 ]
    _T80 = (_T25 + _T79) [ _T25 _T26 _T77 _T80 ]
    *(_T80 + 0) = _T77 [ _T25 _T26 ]
    _T81 = 0 [ _T25 _T26 _T81 ]
    _T82 = *(_T26 - 4) [ _T25 _T26 _T81 _T82 ]
    _T83 = (_T81 < _T82) [ _T25 _T26 _T81 _T83 ]
END BY JZERO, if _T83 = 
    0 : goto 23; 1 : goto 22
BASIC BLOCK 22 : 
  Def     = [ _T84 _T85 ]
  liveUse = [ _T81 ]
  liveIn  = [ _T25 _T26 _T81 ]
  liveOut = [ _T25 _T26 _T81 ]
    _T84 = 0 [ _T25 _T26 _T81 _T84 ]
    _T85 = (_T81 < _T84) [ _T25 _T26 _T81 _T85 ]
END BY JZERO, if _T85 = 
    0 : goto 24; 1 : goto 23
BASIC BLOCK 23 : 
  Def     = [ _T86 ]
  liveUse = [ ]
  liveIn  = [ _T25 _T26 _T81 ]
  liveOut = [ _T25 _T26 _T81 ]
    _T86 = "Decaf runtime error: Array subscript out of bounds\n" [ _T25 _T26 _T81 _T86 ]
    parm _T86 [ _T25 _T26 _T81 ]
    call _PrintString [ _T25 _T26 _T81 ]
    call _Halt [ _T25 _T26 _T81 ]
END BY JUMP, goto 24
BASIC BLOCK 24 : 
  Def     = [ _T87 _T88 _T89 _T90 _T91 _T92 _T93 ]
  liveUse = [ _T25 _T26 _T81 ]
  liveIn  = [ _T25 _T26 _T81 ]
  liveOut = [ _T25 _T26 _T90 _T91 ]
    _T87 = 4 [ _T25 _T26 _T81 _T87 ]
    _T88 = (_T81 * _T87) [ _T25 _T26 _T88 ]
    _T89 = (_T26 + _T88) [ _T25 _T26 _T89 ]
    _T90 = *(_T89 + 0) [ _T25 _T26 _T90 ]
    _T91 = 0 [ _T25 _T26 _T90 _T91 ]
    _T92 = *(_T25 - 4) [ _T25 _T26 _T90 _T91 _T92 ]
    _T93 = (_T91 < _T92) [ _T25 _T26 _T90 _T91 _T93 ]
END BY JZERO, if _T93 = 
    0 : goto 26; 1 : goto 25
BASIC BLOCK 25 : 
  Def     = [ _T94 _T95 ]
  liveUse = [ _T91 ]
  liveIn  = [ _T25 _T26 _T90 _T91 ]
  liveOut = [ _T25 _T26 _T90 _T91 ]
    _T94 = 0 [ _T25 _T26 _T90 _T91 _T94 ]
    _T95 = (_T91 < _T94) [ _T25 _T26 _T90 _T91 _T95 ]
END BY JZERO, if _T95 = 
    0 : goto 27; 1 : goto 26
BASIC BLOCK 26 : 
  Def     = [ _T96 ]
  liveUse = [ ]
  liveIn  = [ _T25 _T26 _T90 _T91 ]
  liveOut = [ _T25 _T26 _T90 _T91 ]
    _T96 = "Decaf runtime error: Array subscript out of bounds\n" [ _T25 _T26 _T90 _T91 _T96 ]
    parm _T96 [ _T25 _T26 _T90 _T91 ]
    call _PrintString [ _T25 _T26 _T90 _T91 ]
    call _Halt [ _T25 _T26 _T90 _T91 ]
END BY JUMP, goto 27
BASIC BLOCK 27 : 
  Def     = [ _T97 _T98 _T99 _T100 _T101 _T102 ]
  liveUse = [ _T25 _T90 _T91 ]
  liveIn  = [ _T25 _T26 _T90 _T91 ]
  liveOut = [ _T25 _T26 _T90 _T100 ]
    _T97 = 4 [ _T25 _T26 _T90 _T91 _T97 ]
    _T98 = (_T91 * _T97) [ _T25 _T26 _T90 _T98 ]
    _T99 = (_T25 + _T98) [ _T25 _T26 _T90 _T99 ]
    _T100 = *(_T99 + 0) [ _T25 _T26 _T90 _T100 ]
    _T101 = *(_T90 - 4) [ _T25 _T26 _T90 _T100 _T101 ]
    _T102 = (_T100 < _T101) [ _T25 _T26 _T90 _T100 _T102 ]
END BY JZERO, if _T102 = 
    0 : goto 29; 1 : goto 28
BASIC BLOCK 28 : 
  Def     = [ _T103 _T104 ]
  liveUse = [ _T100 ]
  liveIn  = [ _T25 _T26 _T90 _T100 ]
  liveOut = [ _T25 _T26 _T90 _T100 ]
    _T103 = 0 [ _T25 _T26 _T90 _T100 _T103 ]
    _T104 = (_T100 < _T103) [ _T25 _T26 _T90 _T100 _T104 ]
END BY JZERO, if _T104 = 
    0 : goto 30; 1 : goto 29
BASIC BLOCK 29 : 
  Def     = [ _T105 ]
  liveUse = [ ]
  liveIn  = [ _T25 _T26 _T90 _T100 ]
  liveOut = [ _T25 _T26 _T90 _T100 ]
    _T105 = "Decaf runtime error: Array subscript out of bounds\n" [ _T25 _T26 _T90 _T100 _T105 ]
    parm _T105 [ _T25 _T26 _T90 _T100 ]
    call _PrintString [ _T25 _T26 _T90 _T100 ]
    call _Halt [ _T25 _T26 _T90 _T100 ]
END BY JUMP, goto 30
BASIC BLOCK 30 : 
  Def     = [ _T106 _T107 _T108 _T109 _T110 _T111 _T112 ]
  liveUse = [ _T25 _T90 _T100 ]
  liveIn  = [ _T25 _T26 _T90 _T100 ]
  liveOut = [ _T25 _T26 _T110 ]
    _T106 = 55 [ _T25 _T26 _T90 _T100 _T106 ]
    _T107 = 4 [ _T25 _T26 _T90 _T100 _T106 _T107 ]
    _T108 = (_T100 * _T107) [ _T25 _T26 _T90 _T106 _T108 ]
    _T109 = (_T90 + _T108) [ _T25 _T26 _T106 _T109 ]
    *(_T109 + 0) = _T106 [ _T25 _T26 ]
    _T110 = 0 [ _T25 _T26 _T110 ]
    _T111 = *(_T25 - 4) [ _T25 _T26 _T110 _T111 ]
    _T112 = (_T110 < _T111) [ _T25 _T26 _T110 _T112 ]
END BY JZERO, if _T112 = 
    0 : goto 32; 1 : goto 31
BASIC BLOCK 31 : 
  Def     = [ _T113 _T114 ]
  liveUse = [ _T110 ]
  liveIn  = [ _T25 _T26 _T110 ]
  liveOut = [ _T25 _T26 _T110 ]
    _T113 = 0 [ _T25 _T26 _T110 _T113 ]
    _T114 = (_T110 < _T113) [ _T25 _T26 _T110 _T114 ]
END BY JZERO, if _T114 = 
    0 : goto 33; 1 : goto 32
BASIC BLOCK 32 : 
  Def     = [ _T115 ]
  liveUse = [ ]
  liveIn  = [ _T25 _T26 _T110 ]
  liveOut = [ _T25 _T26 _T110 ]
    _T115 = "Decaf runtime error: Array subscript out of bounds\n" [ _T25 _T26 _T110 _T115 ]
    parm _T115 [ _T25 _T26 _T110 ]
    call _PrintString [ _T25 _T26 _T110 ]
    call _Halt [ _T25 _T26 _T110 ]
END BY JUMP, goto 33
BASIC BLOCK 33 : 
  Def     = [ _T116 _T117 _T118 _T119 _T120 _T121 _T122 _T123 _T124 _T125 ]
  liveUse = [ _T25 _T26 _T110 ]
  liveIn  = [ _T25 _T26 _T110 ]
  liveOut = [ _T25 _T26 _T121 _T122 _T123 ]
    _T116 = 4 [ _T25 _T26 _T110 _T116 ]
    _T117 = (_T110 * _T116) [ _T25 _T26 _T117 ]
    _T118 = (_T25 + _T117) [ _T25 _T26 _T118 ]
    _T119 = *(_T118 + 0) [ _T25 _T26 _T119 ]
    parm _T119 [ _T25 _T26 ]
    call _PrintInt [ _T25 _T26 ]
    _T120 = " " [ _T25 _T26 _T120 ]
    parm _T120 [ _T25 _T26 ]
    call _PrintString [ _T25 _T26 ]
    _T121 = 2 [ _T25 _T26 _T121 ]
    _T122 = 100 [ _T25 _T26 _T121 _T122 ]
    _T123 = 0 [ _T25 _T26 _T121 _T122 _T123 ]
    _T124 = *(_T26 - 4) [ _T25 _T26 _T121 _T122 _T123 _T124 ]
    _T125 = (_T123 < _T124) [ _T25 _T26 _T121 _T122 _T123 _T125 ]
END BY JZERO, if _T125 = 
    0 : goto 35; 1 : goto 34
BASIC BLOCK 34 : 
  Def     = [ _T126 _T127 ]
  liveUse = [ _T123 ]
  liveIn  = [ _T25 _T26 _T121 _T122 _T123 ]
  liveOut = [ _T25 _T26 _T121 _T122 _T123 ]
    _T126 = 0 [ _T25 _T26 _T121 _T122 _T123 _T126 ]
    _T127 = (_T123 < _T126) [ _T25 _T26 _T121 _T122 _T123 _T127 ]
END BY JZERO, if _T127 = 
    0 : goto 36; 1 : goto 35
BASIC BLOCK 35 : 
  Def     = [ _T128 ]
  liveUse = [ ]
  liveIn  = [ _T25 _T26 _T121 _T122 _T123 ]
  liveOut = [ _T25 _T26 _T121 _T122 _T123 ]
    _T128 = "Decaf runtime error: Array subscript out of bounds\n" [ _T25 _T26 _T121 _T122 _T123 _T128 ]
    parm _T128 [ _T25 _T26 _T121 _T122 _T123 ]
    call _PrintString [ _T25 _T26 _T121 _T122 _T123 ]
    call _Halt [ _T25 _T26 _T121 _T122 _T123 ]
END BY JUMP, goto 36
BASIC BLOCK 36 : 
  Def     = [ _T129 _T130 _T131 _T132 _T133 _T134 ]
  liveUse = [ _T25 _T26 _T121 _T122 _T123 ]
  liveIn  = [ _T25 _T26 _T121 _T122 _T123 ]
  liveOut = [ ]
    _T129 = 4 [ _T25 _T26 _T121 _T122 _T123 _T129 ]
    _T130 = (_T123 * _T129) [ _T25 _T26 _T121 _T122 _T130 ]
    _T131 = (_T26 + _T130) [ _T25 _T121 _T122 _T131 ]
    _T132 = *(_T131 + 0) [ _T25 _T121 _T122 _T132 ]
    parm _T122 [ _T25 _T121 _T132 ]
    parm _T132 [ _T25 _T121 ]
    parm _T25 [ _T121 ]
    _T133 =  call _Main.Binky [ _T121 _T133 ]
    _T134 = (_T121 * _T133) [ _T134 ]
    parm _T134 [ ]
    call _PrintInt [ ]
END BY RETURN, void result

