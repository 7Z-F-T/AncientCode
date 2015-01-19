FUNCTION _QueueItem_New : 
BASIC BLOCK 0 : 
  Def     = [ _T11 _T12 _T13 _T14 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T11 = 16 [ _T11 ]
    parm _T11 [ ]
    _T12 =  call _Alloc [ _T12 ]
    _T13 = 0 [ _T12 _T13 ]
    *(_T12 + 4) = _T13 [ _T12 _T13 ]
    *(_T12 + 8) = _T13 [ _T12 _T13 ]
    *(_T12 + 12) = _T13 [ _T12 ]
    _T14 = VTBL <_QueueItem> [ _T12 _T14 ]
    *(_T12 + 0) = _T14 [ _T12 ]
END BY RETURN, result = _T12

FUNCTION _Queue_New : 
BASIC BLOCK 0 : 
  Def     = [ _T19 _T20 _T21 _T22 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T19 = 12 [ _T19 ]
    parm _T19 [ ]
    _T20 =  call _Alloc [ _T20 ]
    _T21 = 0 [ _T20 _T21 ]
    *(_T20 + 4) = _T21 [ _T20 _T21 ]
    *(_T20 + 8) = _T21 [ _T20 ]
    _T22 = VTBL <_Queue> [ _T20 _T22 ]
    *(_T20 + 0) = _T22 [ _T20 ]
END BY RETURN, result = _T20

FUNCTION _Main_New : 
BASIC BLOCK 0 : 
  Def     = [ _T23 _T24 _T25 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T23 = 4 [ _T23 ]
    parm _T23 [ ]
    _T24 =  call _Alloc [ _T24 ]
    _T25 = VTBL <_Main> [ _T24 _T25 ]
    *(_T24 + 0) = _T25 [ _T24 ]
END BY RETURN, result = _T24

FUNCTION _QueueItem.Init : 
BASIC BLOCK 0 : 
  Def     = [ ]
  liveUse = [ _T0 _T1 _T2 _T3 ]
  liveIn  = [ _T0 _T1 _T2 _T3 ]
  liveOut = [ ]
    *(_T0 + 4) = _T1 [ _T0 _T2 _T3 ]
    *(_T0 + 8) = _T2 [ _T0 _T2 _T3 ]
    *(_T2 + 12) = _T0 [ _T0 _T3 ]
    *(_T0 + 12) = _T3 [ _T0 _T3 ]
    *(_T3 + 8) = _T0 [ ]
END BY RETURN, void result

FUNCTION _QueueItem.GetData : 
BASIC BLOCK 0 : 
  Def     = [ _T26 ]
  liveUse = [ _T4 ]
  liveIn  = [ _T4 ]
  liveOut = [ ]
    _T26 = *(_T4 + 4) [ _T26 ]
END BY RETURN, result = _T26

FUNCTION _QueueItem.GetNext : 
BASIC BLOCK 0 : 
  Def     = [ _T27 ]
  liveUse = [ _T5 ]
  liveIn  = [ _T5 ]
  liveOut = [ ]
    _T27 = *(_T5 + 8) [ _T27 ]
END BY RETURN, result = _T27

FUNCTION _QueueItem.GetPrev : 
BASIC BLOCK 0 : 
  Def     = [ _T28 ]
  liveUse = [ _T6 ]
  liveIn  = [ _T6 ]
  liveOut = [ ]
    _T28 = *(_T6 + 12) [ _T28 ]
END BY RETURN, result = _T28

FUNCTION _QueueItem.SetNext : 
BASIC BLOCK 0 : 
  Def     = [ ]
  liveUse = [ _T7 _T8 ]
  liveIn  = [ _T7 _T8 ]
  liveOut = [ ]
    *(_T7 + 8) = _T8 [ ]
END BY RETURN, void result

FUNCTION _QueueItem.SetPrev : 
BASIC BLOCK 0 : 
  Def     = [ ]
  liveUse = [ _T9 _T10 ]
  liveIn  = [ _T9 _T10 ]
  liveOut = [ ]
    *(_T9 + 12) = _T10 [ ]
END BY RETURN, void result

FUNCTION _Queue.Init : 
BASIC BLOCK 0 : 
  Def     = [ _T29 _T30 _T31 _T32 _T33 _T34 _T35 ]
  liveUse = [ _T15 ]
  liveIn  = [ _T15 ]
  liveOut = [ ]
    _T29 =  call _QueueItem_New [ _T15 _T29 ]
    *(_T15 + 8) = _T29 [ _T15 ]
    _T30 = *(_T15 + 8) [ _T15 _T30 ]
    _T31 = 0 [ _T15 _T30 _T31 ]
    _T32 = *(_T15 + 8) [ _T15 _T30 _T31 _T32 ]
    _T33 = *(_T15 + 8) [ _T30 _T31 _T32 _T33 ]
    parm _T30 [ _T30 _T31 _T32 _T33 ]
    parm _T31 [ _T30 _T32 _T33 ]
    parm _T32 [ _T30 _T33 ]
    parm _T33 [ _T30 ]
    _T34 = *(_T30 + 0) [ _T34 ]
    _T35 = *(_T34 + 0) [ _T35 ]
    call _T35 [ ]
END BY RETURN, void result

FUNCTION _Queue.EnQueue : 
BASIC BLOCK 0 : 
  Def     = [ _T36 _T37 _T38 _T39 _T40 _T41 _T42 _T43 _T44 ]
  liveUse = [ _T16 _T17 ]
  liveIn  = [ _T16 _T17 ]
  liveOut = [ ]
    _T37 =  call _QueueItem_New [ _T16 _T17 _T37 ]
    _T36 = _T37 [ _T16 _T17 _T36 ]
    _T38 = *(_T16 + 8) [ _T16 _T17 _T36 _T38 ]
    parm _T38 [ _T16 _T17 _T36 _T38 ]
    _T39 = *(_T38 + 0) [ _T16 _T17 _T36 _T39 ]
    _T40 = *(_T39 + 8) [ _T16 _T17 _T36 _T40 ]
    _T41 =  call _T40 [ _T16 _T17 _T36 _T41 ]
    _T42 = *(_T16 + 8) [ _T17 _T36 _T41 _T42 ]
    parm _T36 [ _T17 _T36 _T41 _T42 ]
    parm _T17 [ _T36 _T41 _T42 ]
    parm _T41 [ _T36 _T42 ]
    parm _T42 [ _T36 ]
    _T43 = *(_T36 + 0) [ _T43 ]
    _T44 = *(_T43 + 0) [ _T44 ]
    call _T44 [ ]
END BY RETURN, void result

FUNCTION _Queue.DeQueue : 
BASIC BLOCK 0 : 
  Def     = [ _T46 _T47 _T48 _T49 _T50 _T51 ]
  liveUse = [ _T18 ]
  liveIn  = [ _T18 ]
  liveOut = [ _T18 ]
    _T46 = *(_T18 + 8) [ _T18 _T46 ]
    parm _T46 [ _T18 _T46 ]
    _T47 = *(_T46 + 0) [ _T18 _T47 ]
    _T48 = *(_T47 + 12) [ _T18 _T48 ]
    _T49 =  call _T48 [ _T18 _T49 ]
    _T50 = *(_T18 + 8) [ _T18 _T49 _T50 ]
    _T51 = (_T49 == _T50) [ _T18 _T51 ]
END BY JZERO, if _T51 = 
    0 : goto 2; 1 : goto 1
BASIC BLOCK 1 : 
  Def     = [ _T52 _T53 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T52 = "Queue Is Empty" [ _T52 ]
    parm _T52 [ ]
    call _PrintString [ ]
    _T53 = 0 [ _T53 ]
END BY RETURN, result = _T53
BASIC BLOCK 2 : 
  Def     = [ _T45 _T54 _T55 _T56 _T57 _T58 _T59 _T60 _T61 _T62 _T63 _T64 _T65 _T66 _T67 _T68 _T69 _T70 _T71 _T72 _T73 _T74 _T75 _T76 _T77 ]
  liveUse = [ _T18 ]
  liveIn  = [ _T18 ]
  liveOut = [ _T45 ]
    _T55 = *(_T18 + 8) [ _T55 ]
    parm _T55 [ _T55 ]
    _T56 = *(_T55 + 0) [ _T56 ]
    _T57 = *(_T56 + 12) [ _T57 ]
    _T58 =  call _T57 [ _T58 ]
    _T54 = _T58 [ _T54 ]
    parm _T54 [ _T54 ]
    _T59 = *(_T54 + 0) [ _T54 _T59 ]
    _T60 = *(_T59 + 4) [ _T54 _T60 ]
    _T61 =  call _T60 [ _T54 _T61 ]
    _T45 = _T61 [ _T45 _T54 ]
    parm _T54 [ _T45 _T54 ]
    _T62 = *(_T54 + 0) [ _T45 _T54 _T62 ]
    _T63 = *(_T62 + 12) [ _T45 _T54 _T63 ]
    _T64 =  call _T63 [ _T45 _T54 _T64 ]
    parm _T54 [ _T45 _T54 _T64 ]
    _T65 = *(_T54 + 0) [ _T45 _T54 _T64 _T65 ]
    _T66 = *(_T65 + 8) [ _T45 _T54 _T64 _T66 ]
    _T67 =  call _T66 [ _T45 _T54 _T64 _T67 ]
    parm _T64 [ _T45 _T54 _T64 _T67 ]
    parm _T67 [ _T45 _T54 _T64 ]
    _T68 = *(_T64 + 0) [ _T45 _T54 _T68 ]
    _T69 = *(_T68 + 16) [ _T45 _T54 _T69 ]
    call _T69 [ _T45 _T54 ]
    parm _T54 [ _T45 _T54 ]
    _T70 = *(_T54 + 0) [ _T45 _T54 _T70 ]
    _T71 = *(_T70 + 8) [ _T45 _T54 _T71 ]
    _T72 =  call _T71 [ _T45 _T54 _T72 ]
    parm _T54 [ _T45 _T54 _T72 ]
    _T73 = *(_T54 + 0) [ _T45 _T72 _T73 ]
    _T74 = *(_T73 + 12) [ _T45 _T72 _T74 ]
    _T75 =  call _T74 [ _T45 _T72 _T75 ]
    parm _T72 [ _T45 _T72 _T75 ]
    parm _T75 [ _T45 _T72 ]
    _T76 = *(_T72 + 0) [ _T45 _T76 ]
    _T77 = *(_T76 + 20) [ _T45 _T77 ]
    call _T77 [ _T45 ]
END BY JUMP, goto 3
BASIC BLOCK 3 : 
  Def     = [ ]
  liveUse = [ _T45 ]
  liveIn  = [ _T45 ]
  liveOut = [ ]
END BY RETURN, result = _T45

FUNCTION main : 
BASIC BLOCK 0 : 
  Def     = [ _T78 _T79 _T80 _T81 _T82 _T83 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ _T78 _T79 ]
    _T80 =  call _Queue_New [ _T80 ]
    _T78 = _T80 [ _T78 ]
    parm _T78 [ _T78 ]
    _T81 = *(_T78 + 0) [ _T78 _T81 ]
    _T82 = *(_T81 + 0) [ _T78 _T82 ]
    call _T82 [ _T78 ]
    _T83 = 0 [ _T78 _T83 ]
    _T79 = _T83 [ _T78 _T79 ]
END BY JUMP, goto 2
BASIC BLOCK 1 : 
  Def     = [ _T79 _T84 _T85 ]
  liveUse = [ _T79 ]
  liveIn  = [ _T78 _T79 ]
  liveOut = [ _T78 _T79 ]
    _T84 = 1 [ _T78 _T79 _T84 ]
    _T85 = (_T79 + _T84) [ _T78 _T85 ]
    _T79 = _T85 [ _T78 _T79 ]
END BY JUMP, goto 2
BASIC BLOCK 2 : 
  Def     = [ _T86 _T87 ]
  liveUse = [ _T79 ]
  liveIn  = [ _T78 _T79 ]
  liveOut = [ _T78 _T79 ]
    _T86 = 10 [ _T78 _T79 _T86 ]
    _T87 = (_T79 < _T86) [ _T78 _T79 _T87 ]
END BY JZERO, if _T87 = 
    0 : goto 4; 1 : goto 3
BASIC BLOCK 3 : 
  Def     = [ _T88 _T89 ]
  liveUse = [ _T78 _T79 ]
  liveIn  = [ _T78 _T79 ]
  liveOut = [ _T78 _T79 ]
    parm _T78 [ _T78 _T79 ]
    parm _T79 [ _T78 _T79 ]
    _T88 = *(_T78 + 0) [ _T78 _T79 _T88 ]
    _T89 = *(_T88 + 4) [ _T78 _T79 _T89 ]
    call _T89 [ _T78 _T79 ]
END BY JUMP, goto 1
BASIC BLOCK 4 : 
  Def     = [ _T79 _T90 ]
  liveUse = [ ]
  liveIn  = [ _T78 ]
  liveOut = [ _T78 _T79 ]
    _T90 = 0 [ _T78 _T90 ]
    _T79 = _T90 [ _T78 _T79 ]
END BY JUMP, goto 6
BASIC BLOCK 5 : 
  Def     = [ _T79 _T91 _T92 ]
  liveUse = [ _T79 ]
  liveIn  = [ _T78 _T79 ]
  liveOut = [ _T78 _T79 ]
    _T91 = 1 [ _T78 _T79 _T91 ]
    _T92 = (_T79 + _T91) [ _T78 _T92 ]
    _T79 = _T92 [ _T78 _T79 ]
END BY JUMP, goto 6
BASIC BLOCK 6 : 
  Def     = [ _T93 _T94 ]
  liveUse = [ _T79 ]
  liveIn  = [ _T78 _T79 ]
  liveOut = [ _T78 _T79 ]
    _T93 = 4 [ _T78 _T79 _T93 ]
    _T94 = (_T79 < _T93) [ _T78 _T79 _T94 ]
END BY JZERO, if _T94 = 
    0 : goto 8; 1 : goto 7
BASIC BLOCK 7 : 
  Def     = [ _T95 _T96 _T97 _T98 ]
  liveUse = [ _T78 ]
  liveIn  = [ _T78 _T79 ]
  liveOut = [ _T78 _T79 ]
    parm _T78 [ _T78 _T79 ]
    _T95 = *(_T78 + 0) [ _T78 _T79 _T95 ]
    _T96 = *(_T95 + 8) [ _T78 _T79 _T96 ]
    _T97 =  call _T96 [ _T78 _T79 _T97 ]
    parm _T97 [ _T78 _T79 ]
    call _PrintInt [ _T78 _T79 ]
    _T98 = " " [ _T78 _T79 _T98 ]
    parm _T98 [ _T78 _T79 ]
    call _PrintString [ _T78 _T79 ]
END BY JUMP, goto 5
BASIC BLOCK 8 : 
  Def     = [ _T79 _T99 _T100 ]
  liveUse = [ ]
  liveIn  = [ _T78 ]
  liveOut = [ _T78 _T79 ]
    _T99 = "\n" [ _T78 _T99 ]
    parm _T99 [ _T78 ]
    call _PrintString [ _T78 ]
    _T100 = 0 [ _T78 _T100 ]
    _T79 = _T100 [ _T78 _T79 ]
END BY JUMP, goto 10
BASIC BLOCK 9 : 
  Def     = [ _T79 _T101 _T102 ]
  liveUse = [ _T79 ]
  liveIn  = [ _T78 _T79 ]
  liveOut = [ _T78 _T79 ]
    _T101 = 1 [ _T78 _T79 _T101 ]
    _T102 = (_T79 + _T101) [ _T78 _T102 ]
    _T79 = _T102 [ _T78 _T79 ]
END BY JUMP, goto 10
BASIC BLOCK 10 : 
  Def     = [ _T103 _T104 ]
  liveUse = [ _T79 ]
  liveIn  = [ _T78 _T79 ]
  liveOut = [ _T78 _T79 ]
    _T103 = 10 [ _T78 _T79 _T103 ]
    _T104 = (_T79 < _T103) [ _T78 _T79 _T104 ]
END BY JZERO, if _T104 = 
    0 : goto 12; 1 : goto 11
BASIC BLOCK 11 : 
  Def     = [ _T105 _T106 ]
  liveUse = [ _T78 _T79 ]
  liveIn  = [ _T78 _T79 ]
  liveOut = [ _T78 _T79 ]
    parm _T78 [ _T78 _T79 ]
    parm _T79 [ _T78 _T79 ]
    _T105 = *(_T78 + 0) [ _T78 _T79 _T105 ]
    _T106 = *(_T105 + 4) [ _T78 _T79 _T106 ]
    call _T106 [ _T78 _T79 ]
END BY JUMP, goto 9
BASIC BLOCK 12 : 
  Def     = [ _T79 _T107 ]
  liveUse = [ ]
  liveIn  = [ _T78 ]
  liveOut = [ _T78 _T79 ]
    _T107 = 0 [ _T78 _T107 ]
    _T79 = _T107 [ _T78 _T79 ]
END BY JUMP, goto 14
BASIC BLOCK 13 : 
  Def     = [ _T79 _T108 _T109 ]
  liveUse = [ _T79 ]
  liveIn  = [ _T78 _T79 ]
  liveOut = [ _T78 _T79 ]
    _T108 = 1 [ _T78 _T79 _T108 ]
    _T109 = (_T79 + _T108) [ _T78 _T109 ]
    _T79 = _T109 [ _T78 _T79 ]
END BY JUMP, goto 14
BASIC BLOCK 14 : 
  Def     = [ _T110 _T111 ]
  liveUse = [ _T79 ]
  liveIn  = [ _T78 _T79 ]
  liveOut = [ _T78 _T79 ]
    _T110 = 17 [ _T78 _T79 _T110 ]
    _T111 = (_T79 < _T110) [ _T78 _T79 _T111 ]
END BY JZERO, if _T111 = 
    0 : goto 16; 1 : goto 15
BASIC BLOCK 15 : 
  Def     = [ _T112 _T113 _T114 _T115 ]
  liveUse = [ _T78 ]
  liveIn  = [ _T78 _T79 ]
  liveOut = [ _T78 _T79 ]
    parm _T78 [ _T78 _T79 ]
    _T112 = *(_T78 + 0) [ _T78 _T79 _T112 ]
    _T113 = *(_T112 + 8) [ _T78 _T79 _T113 ]
    _T114 =  call _T113 [ _T78 _T79 _T114 ]
    parm _T114 [ _T78 _T79 ]
    call _PrintInt [ _T78 _T79 ]
    _T115 = " " [ _T78 _T79 _T115 ]
    parm _T115 [ _T78 _T79 ]
    call _PrintString [ _T78 _T79 ]
END BY JUMP, goto 13
BASIC BLOCK 16 : 
  Def     = [ _T116 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T116 = "\n" [ _T116 ]
    parm _T116 [ ]
    call _PrintString [ ]
END BY RETURN, void result

