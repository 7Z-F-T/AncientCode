FUNCTION _rndModule_New : 
BASIC BLOCK 0 : 
  Def     = [ _T5 _T6 _T7 _T8 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T5 = 8 [ _T5 ]
    parm _T5 [ ]
    _T6 =  call _Alloc [ _T6 ]
    _T7 = 0 [ _T6 _T7 ]
    *(_T6 + 4) = _T7 [ _T6 ]
    _T8 = VTBL <_rndModule> [ _T6 _T8 ]
    *(_T6 + 0) = _T8 [ _T6 ]
END BY RETURN, result = _T6

FUNCTION _Deck_New : 
BASIC BLOCK 0 : 
  Def     = [ _T13 _T14 _T15 _T16 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T13 = 16 [ _T13 ]
    parm _T13 [ ]
    _T14 =  call _Alloc [ _T14 ]
    _T15 = 0 [ _T14 _T15 ]
    *(_T14 + 4) = _T15 [ _T14 _T15 ]
    *(_T14 + 8) = _T15 [ _T14 _T15 ]
    *(_T14 + 12) = _T15 [ _T14 ]
    _T16 = VTBL <_Deck> [ _T14 _T16 ]
    *(_T14 + 0) = _T16 [ _T14 ]
END BY RETURN, result = _T14

FUNCTION _BJDeck_New : 
BASIC BLOCK 0 : 
  Def     = [ _T22 _T23 _T24 _T25 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T22 = 16 [ _T22 ]
    parm _T22 [ ]
    _T23 =  call _Alloc [ _T23 ]
    _T24 = 0 [ _T23 _T24 ]
    *(_T23 + 4) = _T24 [ _T23 _T24 ]
    *(_T23 + 8) = _T24 [ _T23 _T24 ]
    *(_T23 + 12) = _T24 [ _T23 ]
    _T25 = VTBL <_BJDeck> [ _T23 _T25 ]
    *(_T23 + 0) = _T25 [ _T23 ]
END BY RETURN, result = _T23

FUNCTION _Player_New : 
BASIC BLOCK 0 : 
  Def     = [ _T42 _T43 _T44 _T45 _T46 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ _T42 _T44 _T45 _T46 ]
    _T42 = 28 [ _T42 ]
    parm _T42 [ _T42 ]
    _T43 =  call _Alloc [ _T42 _T43 ]
    _T44 = 0 [ _T42 _T43 _T44 ]
    _T45 = 4 [ _T42 _T43 _T44 _T45 ]
    _T46 = (_T43 + _T42) [ _T42 _T44 _T45 _T46 ]
END BY JUMP, goto 1
BASIC BLOCK 1 : 
  Def     = [ _T42 _T46 _T47 _T48 ]
  liveUse = [ _T42 _T45 _T46 ]
  liveIn  = [ _T42 _T44 _T45 _T46 ]
  liveOut = [ _T42 _T44 _T45 _T46 ]
    _T47 = (_T46 - _T45) [ _T42 _T44 _T45 _T47 ]
    _T46 = _T47 [ _T42 _T44 _T45 _T46 ]
    _T48 = (_T42 - _T45) [ _T44 _T45 _T46 _T48 ]
    _T42 = _T48 [ _T42 _T44 _T45 _T46 ]
END BY JZERO, if _T42 = 
    0 : goto 3; 1 : goto 2
BASIC BLOCK 2 : 
  Def     = [ ]
  liveUse = [ _T44 _T46 ]
  liveIn  = [ _T42 _T44 _T45 _T46 ]
  liveOut = [ _T42 _T44 _T45 _T46 ]
    *(_T46 + 0) = _T44 [ _T42 _T44 _T45 _T46 ]
END BY JUMP, goto 1
BASIC BLOCK 3 : 
  Def     = [ _T49 ]
  liveUse = [ _T46 ]
  liveIn  = [ _T46 ]
  liveOut = [ ]
    _T49 = VTBL <_Player> [ _T46 _T49 ]
    *(_T46 + 0) = _T49 [ _T46 ]
END BY RETURN, result = _T46

FUNCTION _Dealer_New : 
BASIC BLOCK 0 : 
  Def     = [ _T54 _T55 _T56 _T57 _T58 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ _T54 _T56 _T57 _T58 ]
    _T54 = 28 [ _T54 ]
    parm _T54 [ _T54 ]
    _T55 =  call _Alloc [ _T54 _T55 ]
    _T56 = 0 [ _T54 _T55 _T56 ]
    _T57 = 4 [ _T54 _T55 _T56 _T57 ]
    _T58 = (_T55 + _T54) [ _T54 _T56 _T57 _T58 ]
END BY JUMP, goto 1
BASIC BLOCK 1 : 
  Def     = [ _T54 _T58 _T59 _T60 ]
  liveUse = [ _T54 _T57 _T58 ]
  liveIn  = [ _T54 _T56 _T57 _T58 ]
  liveOut = [ _T54 _T56 _T57 _T58 ]
    _T59 = (_T58 - _T57) [ _T54 _T56 _T57 _T59 ]
    _T58 = _T59 [ _T54 _T56 _T57 _T58 ]
    _T60 = (_T54 - _T57) [ _T56 _T57 _T58 _T60 ]
    _T54 = _T60 [ _T54 _T56 _T57 _T58 ]
END BY JZERO, if _T54 = 
    0 : goto 3; 1 : goto 2
BASIC BLOCK 2 : 
  Def     = [ ]
  liveUse = [ _T56 _T58 ]
  liveIn  = [ _T54 _T56 _T57 _T58 ]
  liveOut = [ _T54 _T56 _T57 _T58 ]
    *(_T58 + 0) = _T56 [ _T54 _T56 _T57 _T58 ]
END BY JUMP, goto 1
BASIC BLOCK 3 : 
  Def     = [ _T61 ]
  liveUse = [ _T58 ]
  liveIn  = [ _T58 ]
  liveOut = [ ]
    _T61 = VTBL <_Dealer> [ _T58 _T61 ]
    *(_T58 + 0) = _T61 [ _T58 ]
END BY RETURN, result = _T58

FUNCTION _House_New : 
BASIC BLOCK 0 : 
  Def     = [ _T69 _T70 _T71 _T72 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T69 = 16 [ _T69 ]
    parm _T69 [ ]
    _T70 =  call _Alloc [ _T70 ]
    _T71 = 0 [ _T70 _T71 ]
    *(_T70 + 4) = _T71 [ _T70 _T71 ]
    *(_T70 + 8) = _T71 [ _T70 _T71 ]
    *(_T70 + 12) = _T71 [ _T70 ]
    _T72 = VTBL <_House> [ _T70 _T72 ]
    *(_T70 + 0) = _T72 [ _T70 ]
END BY RETURN, result = _T70

FUNCTION _Main_New : 
BASIC BLOCK 0 : 
  Def     = [ _T74 _T75 _T76 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T74 = 4 [ _T74 ]
    parm _T74 [ ]
    _T75 =  call _Alloc [ _T75 ]
    _T76 = VTBL <_Main> [ _T75 _T76 ]
    *(_T75 + 0) = _T76 [ _T75 ]
END BY RETURN, result = _T75

FUNCTION _rndModule.Init : 
BASIC BLOCK 0 : 
  Def     = [ ]
  liveUse = [ _T0 _T1 ]
  liveIn  = [ _T0 _T1 ]
  liveOut = [ ]
    *(_T0 + 4) = _T1 [ ]
END BY RETURN, void result

FUNCTION _rndModule.Random : 
BASIC BLOCK 0 : 
  Def     = [ _T77 _T78 _T79 _T80 _T81 _T82 _T83 _T84 _T85 _T86 ]
  liveUse = [ _T2 ]
  liveIn  = [ _T2 ]
  liveOut = [ ]
    _T77 = 15625 [ _T2 _T77 ]
    _T78 = *(_T2 + 4) [ _T2 _T77 _T78 ]
    _T79 = 10000 [ _T2 _T77 _T78 _T79 ]
    _T80 = (_T78 % _T79) [ _T2 _T77 _T80 ]
    _T81 = (_T77 * _T80) [ _T2 _T81 ]
    _T82 = 22221 [ _T2 _T81 _T82 ]
    _T83 = (_T81 + _T82) [ _T2 _T83 ]
    _T84 = 65536 [ _T2 _T83 _T84 ]
    _T85 = (_T83 % _T84) [ _T2 _T85 ]
    *(_T2 + 4) = _T85 [ _T2 ]
    _T86 = *(_T2 + 4) [ _T86 ]
END BY RETURN, result = _T86

FUNCTION _rndModule.RndInt : 
BASIC BLOCK 0 : 
  Def     = [ _T87 _T88 _T89 _T90 ]
  liveUse = [ _T3 _T4 ]
  liveIn  = [ _T3 _T4 ]
  liveOut = [ ]
    parm _T3 [ _T3 _T4 ]
    _T87 = *(_T3 + 0) [ _T4 _T87 ]
    _T88 = *(_T87 + 4) [ _T4 _T88 ]
    _T89 =  call _T88 [ _T4 _T89 ]
    _T90 = (_T89 % _T4) [ _T90 ]
END BY RETURN, result = _T90

FUNCTION _Deck.Init : 
BASIC BLOCK 0 : 
  Def     = [ _T91 _T92 _T93 ]
  liveUse = [ ]
  liveIn  = [ _T9 _T10 ]
  liveOut = [ _T9 _T10 _T91 ]
    _T91 = 52 [ _T9 _T10 _T91 ]
    _T92 = 0 [ _T9 _T10 _T91 _T92 ]
    _T93 = (_T91 < _T92) [ _T9 _T10 _T91 _T93 ]
END BY JZERO, if _T93 = 
    0 : goto 2; 1 : goto 1
BASIC BLOCK 1 : 
  Def     = [ _T94 ]
  liveUse = [ ]
  liveIn  = [ _T9 _T10 _T91 ]
  liveOut = [ _T9 _T10 _T91 ]
    _T94 = "Decaf runtime error: Cannot create negative-sized array\n" [ _T9 _T10 _T91 _T94 ]
    parm _T94 [ _T9 _T10 _T91 ]
    call _PrintString [ _T9 _T10 _T91 ]
    call _Halt [ _T9 _T10 _T91 ]
END BY JUMP, goto 2
BASIC BLOCK 2 : 
  Def     = [ _T95 _T96 _T97 _T98 _T99 ]
  liveUse = [ _T91 ]
  liveIn  = [ _T9 _T10 _T91 ]
  liveOut = [ _T9 _T10 _T95 _T97 _T98 _T99 ]
    _T95 = 4 [ _T9 _T10 _T91 _T95 ]
    _T96 = (_T95 * _T91) [ _T9 _T10 _T91 _T95 _T96 ]
    _T97 = (_T95 + _T96) [ _T9 _T10 _T91 _T95 _T97 ]
    parm _T97 [ _T9 _T10 _T91 _T95 _T97 ]
    _T98 =  call _Alloc [ _T9 _T10 _T91 _T95 _T97 _T98 ]
    *(_T98 + 0) = _T91 [ _T9 _T10 _T95 _T97 _T98 ]
    _T99 = 0 [ _T9 _T10 _T95 _T97 _T98 _T99 ]
    _T98 = (_T98 + _T97) [ _T9 _T10 _T95 _T97 _T98 _T99 ]
END BY JUMP, goto 3
BASIC BLOCK 3 : 
  Def     = [ _T97 ]
  liveUse = [ _T95 _T97 ]
  liveIn  = [ _T9 _T10 _T95 _T97 _T98 _T99 ]
  liveOut = [ _T9 _T10 _T95 _T97 _T98 _T99 ]
    _T97 = (_T97 - _T95) [ _T9 _T10 _T95 _T97 _T98 _T99 ]
END BY JZERO, if _T97 = 
    0 : goto 5; 1 : goto 4
BASIC BLOCK 4 : 
  Def     = [ _T98 ]
  liveUse = [ _T95 _T98 _T99 ]
  liveIn  = [ _T9 _T10 _T95 _T97 _T98 _T99 ]
  liveOut = [ _T9 _T10 _T95 _T97 _T98 _T99 ]
    _T98 = (_T98 - _T95) [ _T9 _T10 _T95 _T97 _T98 _T99 ]
    *(_T98 + 0) = _T99 [ _T9 _T10 _T95 _T97 _T98 _T99 ]
END BY JUMP, goto 3
BASIC BLOCK 5 : 
  Def     = [ ]
  liveUse = [ _T9 _T10 _T98 ]
  liveIn  = [ _T9 _T10 _T98 ]
  liveOut = [ ]
    *(_T9 + 8) = _T98 [ _T9 _T10 ]
    *(_T9 + 12) = _T10 [ ]
END BY RETURN, void result

FUNCTION _Deck.Shuffle : 
BASIC BLOCK 0 : 
  Def     = [ _T100 ]
  liveUse = [ _T11 ]
  liveIn  = [ _T11 ]
  liveOut = [ _T11 ]
    _T100 = 1 [ _T11 _T100 ]
    *(_T11 + 4) = _T100 [ _T11 ]
END BY JUMP, goto 2
BASIC BLOCK 1 : 
  Def     = [ _T101 _T102 _T103 ]
  liveUse = [ _T11 ]
  liveIn  = [ _T11 ]
  liveOut = [ _T11 ]
    _T101 = *(_T11 + 4) [ _T11 _T101 ]
    _T102 = 1 [ _T11 _T101 _T102 ]
    _T103 = (_T101 + _T102) [ _T11 _T103 ]
    *(_T11 + 4) = _T103 [ _T11 ]
END BY JUMP, goto 2
BASIC BLOCK 2 : 
  Def     = [ _T104 _T105 _T106 ]
  liveUse = [ _T11 ]
  liveIn  = [ _T11 ]
  liveOut = [ _T11 ]
    _T104 = *(_T11 + 4) [ _T11 _T104 ]
    _T105 = 52 [ _T11 _T104 _T105 ]
    _T106 = (_T104 <= _T105) [ _T11 _T106 ]
END BY JZERO, if _T106 = 
    0 : goto 7; 1 : goto 3
BASIC BLOCK 3 : 
  Def     = [ _T107 _T108 _T109 _T110 _T111 _T112 ]
  liveUse = [ _T11 ]
  liveIn  = [ _T11 ]
  liveOut = [ _T11 _T107 _T110 ]
    _T107 = *(_T11 + 8) [ _T11 _T107 ]
    _T108 = *(_T11 + 4) [ _T11 _T107 _T108 ]
    _T109 = 1 [ _T11 _T107 _T108 _T109 ]
    _T110 = (_T108 - _T109) [ _T11 _T107 _T110 ]
    _T111 = *(_T107 - 4) [ _T11 _T107 _T110 _T111 ]
    _T112 = (_T110 < _T111) [ _T11 _T107 _T110 _T112 ]
END BY JZERO, if _T112 = 
    0 : goto 5; 1 : goto 4
BASIC BLOCK 4 : 
  Def     = [ _T113 _T114 ]
  liveUse = [ _T110 ]
  liveIn  = [ _T11 _T107 _T110 ]
  liveOut = [ _T11 _T107 _T110 ]
    _T113 = 0 [ _T11 _T107 _T110 _T113 ]
    _T114 = (_T110 < _T113) [ _T11 _T107 _T110 _T114 ]
END BY JZERO, if _T114 = 
    0 : goto 6; 1 : goto 5
BASIC BLOCK 5 : 
  Def     = [ _T115 ]
  liveUse = [ ]
  liveIn  = [ _T11 _T107 _T110 ]
  liveOut = [ _T11 _T107 _T110 ]
    _T115 = "Decaf runtime error: Array subscript out of bounds\n" [ _T11 _T107 _T110 _T115 ]
    parm _T115 [ _T11 _T107 _T110 ]
    call _PrintString [ _T11 _T107 _T110 ]
    call _Halt [ _T11 _T107 _T110 ]
END BY JUMP, goto 6
BASIC BLOCK 6 : 
  Def     = [ _T116 _T117 _T118 _T119 _T120 _T121 ]
  liveUse = [ _T11 _T107 _T110 ]
  liveIn  = [ _T11 _T107 _T110 ]
  liveOut = [ _T11 ]
    _T116 = *(_T11 + 4) [ _T11 _T107 _T110 _T116 ]
    _T117 = 13 [ _T11 _T107 _T110 _T116 _T117 ]
    _T118 = (_T116 % _T117) [ _T11 _T107 _T110 _T118 ]
    _T119 = 4 [ _T11 _T107 _T110 _T118 _T119 ]
    _T120 = (_T110 * _T119) [ _T11 _T107 _T118 _T120 ]
    _T121 = (_T107 + _T120) [ _T11 _T118 _T121 ]
    *(_T121 + 0) = _T118 [ _T11 ]
END BY JUMP, goto 1
BASIC BLOCK 7 : 
  Def     = [ _T122 _T123 _T124 ]
  liveUse = [ _T11 ]
  liveIn  = [ _T11 ]
  liveOut = [ _T11 ]
    _T122 = *(_T11 + 4) [ _T11 _T122 ]
    _T123 = 1 [ _T11 _T122 _T123 ]
    _T124 = (_T122 - _T123) [ _T11 _T124 ]
    *(_T11 + 4) = _T124 [ _T11 ]
END BY JUMP, goto 8
BASIC BLOCK 8 : 
  Def     = [ _T125 _T126 _T127 ]
  liveUse = [ _T11 ]
  liveIn  = [ _T11 ]
  liveOut = [ _T11 ]
    _T125 = *(_T11 + 4) [ _T11 _T125 ]
    _T126 = 0 [ _T11 _T125 _T126 ]
    _T127 = (_T125 > _T126) [ _T11 _T127 ]
END BY JZERO, if _T127 = 
    0 : goto 22; 1 : goto 9
BASIC BLOCK 9 : 
  Def     = [ _T128 _T130 _T131 _T132 _T133 _T134 _T135 _T136 _T137 _T138 _T139 _T140 _T141 ]
  liveUse = [ _T11 ]
  liveIn  = [ _T11 ]
  liveOut = [ _T11 _T128 _T138 _T139 ]
    _T130 = *(_T11 + 12) [ _T11 _T130 ]
    _T131 = *(_T11 + 4) [ _T11 _T130 _T131 ]
    parm _T130 [ _T11 _T130 _T131 ]
    parm _T131 [ _T11 _T130 ]
    _T132 = *(_T130 + 0) [ _T11 _T132 ]
    _T133 = *(_T132 + 8) [ _T11 _T133 ]
    _T134 =  call _T133 [ _T11 _T134 ]
    _T128 = _T134 [ _T11 _T128 ]
    _T135 = *(_T11 + 4) [ _T11 _T128 _T135 ]
    _T136 = 1 [ _T11 _T128 _T135 _T136 ]
    _T137 = (_T135 - _T136) [ _T11 _T128 _T137 ]
    *(_T11 + 4) = _T137 [ _T11 _T128 ]
    _T138 = *(_T11 + 8) [ _T11 _T128 _T138 ]
    _T139 = *(_T11 + 4) [ _T11 _T128 _T138 _T139 ]
    _T140 = *(_T138 - 4) [ _T11 _T128 _T138 _T139 _T140 ]
    _T141 = (_T139 < _T140) [ _T11 _T128 _T138 _T139 _T141 ]
END BY JZERO, if _T141 = 
    0 : goto 11; 1 : goto 10
BASIC BLOCK 10 : 
  Def     = [ _T142 _T143 ]
  liveUse = [ _T139 ]
  liveIn  = [ _T11 _T128 _T138 _T139 ]
  liveOut = [ _T11 _T128 _T138 _T139 ]
    _T142 = 0 [ _T11 _T128 _T138 _T139 _T142 ]
    _T143 = (_T139 < _T142) [ _T11 _T128 _T138 _T139 _T143 ]
END BY JZERO, if _T143 = 
    0 : goto 12; 1 : goto 11
BASIC BLOCK 11 : 
  Def     = [ _T144 ]
  liveUse = [ ]
  liveIn  = [ _T11 _T128 _T138 _T139 ]
  liveOut = [ _T11 _T128 _T138 _T139 ]
    _T144 = "Decaf runtime error: Array subscript out of bounds\n" [ _T11 _T128 _T138 _T139 _T144 ]
    parm _T144 [ _T11 _T128 _T138 _T139 ]
    call _PrintString [ _T11 _T128 _T138 _T139 ]
    call _Halt [ _T11 _T128 _T138 _T139 ]
END BY JUMP, goto 12
BASIC BLOCK 12 : 
  Def     = [ _T129 _T145 _T146 _T147 _T148 _T149 _T150 _T151 _T152 ]
  liveUse = [ _T11 _T138 _T139 ]
  liveIn  = [ _T11 _T128 _T138 _T139 ]
  liveOut = [ _T11 _T128 _T129 _T149 _T150 ]
    _T145 = 4 [ _T11 _T128 _T138 _T139 _T145 ]
    _T146 = (_T139 * _T145) [ _T11 _T128 _T138 _T146 ]
    _T147 = (_T138 + _T146) [ _T11 _T128 _T147 ]
    _T148 = *(_T147 + 0) [ _T11 _T128 _T148 ]
    _T129 = _T148 [ _T11 _T128 _T129 ]
    _T149 = *(_T11 + 8) [ _T11 _T128 _T129 _T149 ]
    _T150 = *(_T11 + 4) [ _T11 _T128 _T129 _T149 _T150 ]
    _T151 = *(_T149 - 4) [ _T11 _T128 _T129 _T149 _T150 _T151 ]
    _T152 = (_T150 < _T151) [ _T11 _T128 _T129 _T149 _T150 _T152 ]
END BY JZERO, if _T152 = 
    0 : goto 14; 1 : goto 13
BASIC BLOCK 13 : 
  Def     = [ _T153 _T154 ]
  liveUse = [ _T150 ]
  liveIn  = [ _T11 _T128 _T129 _T149 _T150 ]
  liveOut = [ _T11 _T128 _T129 _T149 _T150 ]
    _T153 = 0 [ _T11 _T128 _T129 _T149 _T150 _T153 ]
    _T154 = (_T150 < _T153) [ _T11 _T128 _T129 _T149 _T150 _T154 ]
END BY JZERO, if _T154 = 
    0 : goto 15; 1 : goto 14
BASIC BLOCK 14 : 
  Def     = [ _T155 ]
  liveUse = [ ]
  liveIn  = [ _T11 _T128 _T129 _T149 _T150 ]
  liveOut = [ _T11 _T128 _T129 _T149 _T150 ]
    _T155 = "Decaf runtime error: Array subscript out of bounds\n" [ _T11 _T128 _T129 _T149 _T150 _T155 ]
    parm _T155 [ _T11 _T128 _T129 _T149 _T150 ]
    call _PrintString [ _T11 _T128 _T129 _T149 _T150 ]
    call _Halt [ _T11 _T128 _T129 _T149 _T150 ]
END BY JUMP, goto 15
BASIC BLOCK 15 : 
  Def     = [ _T156 _T157 _T158 ]
  liveUse = [ _T11 _T128 ]
  liveIn  = [ _T11 _T128 _T129 _T149 _T150 ]
  liveOut = [ _T11 _T128 _T129 _T149 _T150 _T156 ]
    _T156 = *(_T11 + 8) [ _T11 _T128 _T129 _T149 _T150 _T156 ]
    _T157 = *(_T156 - 4) [ _T11 _T128 _T129 _T149 _T150 _T156 _T157 ]
    _T158 = (_T128 < _T157) [ _T11 _T128 _T129 _T149 _T150 _T156 _T158 ]
END BY JZERO, if _T158 = 
    0 : goto 17; 1 : goto 16
BASIC BLOCK 16 : 
  Def     = [ _T159 _T160 ]
  liveUse = [ _T128 ]
  liveIn  = [ _T11 _T128 _T129 _T149 _T150 _T156 ]
  liveOut = [ _T11 _T128 _T129 _T149 _T150 _T156 ]
    _T159 = 0 [ _T11 _T128 _T129 _T149 _T150 _T156 _T159 ]
    _T160 = (_T128 < _T159) [ _T11 _T128 _T129 _T149 _T150 _T156 _T160 ]
END BY JZERO, if _T160 = 
    0 : goto 18; 1 : goto 17
BASIC BLOCK 17 : 
  Def     = [ _T161 ]
  liveUse = [ ]
  liveIn  = [ _T11 _T128 _T129 _T149 _T150 _T156 ]
  liveOut = [ _T11 _T128 _T129 _T149 _T150 _T156 ]
    _T161 = "Decaf runtime error: Array subscript out of bounds\n" [ _T11 _T128 _T129 _T149 _T150 _T156 _T161 ]
    parm _T161 [ _T11 _T128 _T129 _T149 _T150 _T156 ]
    call _PrintString [ _T11 _T128 _T129 _T149 _T150 _T156 ]
    call _Halt [ _T11 _T128 _T129 _T149 _T150 _T156 ]
END BY JUMP, goto 18
BASIC BLOCK 18 : 
  Def     = [ _T162 _T163 _T164 _T165 _T166 _T167 _T168 _T169 _T170 _T171 ]
  liveUse = [ _T11 _T128 _T149 _T150 _T156 ]
  liveIn  = [ _T11 _T128 _T129 _T149 _T150 _T156 ]
  liveOut = [ _T11 _T128 _T129 _T169 ]
    _T162 = 4 [ _T11 _T128 _T129 _T149 _T150 _T156 _T162 ]
    _T163 = (_T128 * _T162) [ _T11 _T128 _T129 _T149 _T150 _T156 _T163 ]
    _T164 = (_T156 + _T163) [ _T11 _T128 _T129 _T149 _T150 _T164 ]
    _T165 = *(_T164 + 0) [ _T11 _T128 _T129 _T149 _T150 _T165 ]
    _T166 = 4 [ _T11 _T128 _T129 _T149 _T150 _T165 _T166 ]
    _T167 = (_T150 * _T166) [ _T11 _T128 _T129 _T149 _T165 _T167 ]
    _T168 = (_T149 + _T167) [ _T11 _T128 _T129 _T165 _T168 ]
    *(_T168 + 0) = _T165 [ _T11 _T128 _T129 ]
    _T169 = *(_T11 + 8) [ _T11 _T128 _T129 _T169 ]
    _T170 = *(_T169 - 4) [ _T11 _T128 _T129 _T169 _T170 ]
    _T171 = (_T128 < _T170) [ _T11 _T128 _T129 _T169 _T171 ]
END BY JZERO, if _T171 = 
    0 : goto 20; 1 : goto 19
BASIC BLOCK 19 : 
  Def     = [ _T172 _T173 ]
  liveUse = [ _T128 ]
  liveIn  = [ _T11 _T128 _T129 _T169 ]
  liveOut = [ _T11 _T128 _T129 _T169 ]
    _T172 = 0 [ _T11 _T128 _T129 _T169 _T172 ]
    _T173 = (_T128 < _T172) [ _T11 _T128 _T129 _T169 _T173 ]
END BY JZERO, if _T173 = 
    0 : goto 21; 1 : goto 20
BASIC BLOCK 20 : 
  Def     = [ _T174 ]
  liveUse = [ ]
  liveIn  = [ _T11 _T128 _T129 _T169 ]
  liveOut = [ _T11 _T128 _T129 _T169 ]
    _T174 = "Decaf runtime error: Array subscript out of bounds\n" [ _T11 _T128 _T129 _T169 _T174 ]
    parm _T174 [ _T11 _T128 _T129 _T169 ]
    call _PrintString [ _T11 _T128 _T129 _T169 ]
    call _Halt [ _T11 _T128 _T129 _T169 ]
END BY JUMP, goto 21
BASIC BLOCK 21 : 
  Def     = [ _T175 _T176 _T177 ]
  liveUse = [ _T128 _T129 _T169 ]
  liveIn  = [ _T11 _T128 _T129 _T169 ]
  liveOut = [ _T11 ]
    _T175 = 4 [ _T11 _T128 _T129 _T169 _T175 ]
    _T176 = (_T128 * _T175) [ _T11 _T129 _T169 _T176 ]
    _T177 = (_T169 + _T176) [ _T11 _T129 _T177 ]
    *(_T177 + 0) = _T129 [ _T11 ]
END BY JUMP, goto 8
BASIC BLOCK 22 : 
  Def     = [ ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
END BY RETURN, void result

FUNCTION _Deck.GetCard : 
BASIC BLOCK 0 : 
  Def     = [ _T179 _T180 _T181 ]
  liveUse = [ _T12 ]
  liveIn  = [ _T12 ]
  liveOut = [ _T12 ]
    _T179 = *(_T12 + 4) [ _T12 _T179 ]
    _T180 = 52 [ _T12 _T179 _T180 ]
    _T181 = (_T179 >= _T180) [ _T12 _T181 ]
END BY JZERO, if _T181 = 
    0 : goto 2; 1 : goto 1
BASIC BLOCK 1 : 
  Def     = [ _T182 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T182 = 0 [ _T182 ]
END BY RETURN, result = _T182
BASIC BLOCK 2 : 
  Def     = [ _T183 _T184 _T185 _T186 ]
  liveUse = [ _T12 ]
  liveIn  = [ _T12 ]
  liveOut = [ _T12 _T183 _T184 ]
    _T183 = *(_T12 + 8) [ _T12 _T183 ]
    _T184 = *(_T12 + 4) [ _T12 _T183 _T184 ]
    _T185 = *(_T183 - 4) [ _T12 _T183 _T184 _T185 ]
    _T186 = (_T184 < _T185) [ _T12 _T183 _T184 _T186 ]
END BY JZERO, if _T186 = 
    0 : goto 4; 1 : goto 3
BASIC BLOCK 3 : 
  Def     = [ _T187 _T188 ]
  liveUse = [ _T184 ]
  liveIn  = [ _T12 _T183 _T184 ]
  liveOut = [ _T12 _T183 _T184 ]
    _T187 = 0 [ _T12 _T183 _T184 _T187 ]
    _T188 = (_T184 < _T187) [ _T12 _T183 _T184 _T188 ]
END BY JZERO, if _T188 = 
    0 : goto 5; 1 : goto 4
BASIC BLOCK 4 : 
  Def     = [ _T189 ]
  liveUse = [ ]
  liveIn  = [ _T12 _T183 _T184 ]
  liveOut = [ _T12 _T183 _T184 ]
    _T189 = "Decaf runtime error: Array subscript out of bounds\n" [ _T12 _T183 _T184 _T189 ]
    parm _T189 [ _T12 _T183 _T184 ]
    call _PrintString [ _T12 _T183 _T184 ]
    call _Halt [ _T12 _T183 _T184 ]
END BY JUMP, goto 5
BASIC BLOCK 5 : 
  Def     = [ _T178 _T190 _T191 _T192 _T193 _T194 _T195 _T196 ]
  liveUse = [ _T12 _T183 _T184 ]
  liveIn  = [ _T12 _T183 _T184 ]
  liveOut = [ ]
    _T190 = 4 [ _T12 _T183 _T184 _T190 ]
    _T191 = (_T184 * _T190) [ _T12 _T183 _T191 ]
    _T192 = (_T183 + _T191) [ _T12 _T192 ]
    _T193 = *(_T192 + 0) [ _T12 _T193 ]
    _T178 = _T193 [ _T12 _T178 ]
    _T194 = *(_T12 + 4) [ _T12 _T178 _T194 ]
    _T195 = 1 [ _T12 _T178 _T194 _T195 ]
    _T196 = (_T194 + _T195) [ _T12 _T178 _T196 ]
    *(_T12 + 4) = _T196 [ _T178 ]
END BY RETURN, result = _T178

FUNCTION _BJDeck.Init : 
BASIC BLOCK 0 : 
  Def     = [ _T198 _T199 _T200 ]
  liveUse = [ ]
  liveIn  = [ _T17 _T18 ]
  liveOut = [ _T17 _T18 _T198 ]
    _T198 = 8 [ _T17 _T18 _T198 ]
    _T199 = 0 [ _T17 _T18 _T198 _T199 ]
    _T200 = (_T198 < _T199) [ _T17 _T18 _T198 _T200 ]
END BY JZERO, if _T200 = 
    0 : goto 2; 1 : goto 1
BASIC BLOCK 1 : 
  Def     = [ _T201 ]
  liveUse = [ ]
  liveIn  = [ _T17 _T18 _T198 ]
  liveOut = [ _T17 _T18 _T198 ]
    _T201 = "Decaf runtime error: Cannot create negative-sized array\n" [ _T17 _T18 _T198 _T201 ]
    parm _T201 [ _T17 _T18 _T198 ]
    call _PrintString [ _T17 _T18 _T198 ]
    call _Halt [ _T17 _T18 _T198 ]
END BY JUMP, goto 2
BASIC BLOCK 2 : 
  Def     = [ _T202 _T203 _T204 _T205 _T206 ]
  liveUse = [ _T198 ]
  liveIn  = [ _T17 _T18 _T198 ]
  liveOut = [ _T17 _T18 _T202 _T204 _T205 _T206 ]
    _T202 = 4 [ _T17 _T18 _T198 _T202 ]
    _T203 = (_T202 * _T198) [ _T17 _T18 _T198 _T202 _T203 ]
    _T204 = (_T202 + _T203) [ _T17 _T18 _T198 _T202 _T204 ]
    parm _T204 [ _T17 _T18 _T198 _T202 _T204 ]
    _T205 =  call _Alloc [ _T17 _T18 _T198 _T202 _T204 _T205 ]
    *(_T205 + 0) = _T198 [ _T17 _T18 _T202 _T204 _T205 ]
    _T206 = 0 [ _T17 _T18 _T202 _T204 _T205 _T206 ]
    _T205 = (_T205 + _T204) [ _T17 _T18 _T202 _T204 _T205 _T206 ]
END BY JUMP, goto 3
BASIC BLOCK 3 : 
  Def     = [ _T204 ]
  liveUse = [ _T202 _T204 ]
  liveIn  = [ _T17 _T18 _T202 _T204 _T205 _T206 ]
  liveOut = [ _T17 _T18 _T202 _T204 _T205 _T206 ]
    _T204 = (_T204 - _T202) [ _T17 _T18 _T202 _T204 _T205 _T206 ]
END BY JZERO, if _T204 = 
    0 : goto 5; 1 : goto 4
BASIC BLOCK 4 : 
  Def     = [ _T205 ]
  liveUse = [ _T202 _T205 _T206 ]
  liveIn  = [ _T17 _T18 _T202 _T204 _T205 _T206 ]
  liveOut = [ _T17 _T18 _T202 _T204 _T205 _T206 ]
    _T205 = (_T205 - _T202) [ _T17 _T18 _T202 _T204 _T205 _T206 ]
    *(_T205 + 0) = _T206 [ _T17 _T18 _T202 _T204 _T205 _T206 ]
END BY JUMP, goto 3
BASIC BLOCK 5 : 
  Def     = [ _T197 _T207 ]
  liveUse = [ _T17 _T205 ]
  liveIn  = [ _T17 _T18 _T205 ]
  liveOut = [ _T17 _T18 _T197 ]
    *(_T17 + 4) = _T205 [ _T17 _T18 ]
    _T207 = 0 [ _T17 _T18 _T207 ]
    _T197 = _T207 [ _T17 _T18 _T197 ]
END BY JUMP, goto 7
BASIC BLOCK 6 : 
  Def     = [ _T197 _T208 _T209 ]
  liveUse = [ _T197 ]
  liveIn  = [ _T17 _T18 _T197 ]
  liveOut = [ _T17 _T18 _T197 ]
    _T208 = 1 [ _T17 _T18 _T197 _T208 ]
    _T209 = (_T197 + _T208) [ _T17 _T18 _T209 ]
    _T197 = _T209 [ _T17 _T18 _T197 ]
END BY JUMP, goto 7
BASIC BLOCK 7 : 
  Def     = [ _T210 _T211 ]
  liveUse = [ _T197 ]
  liveIn  = [ _T17 _T18 _T197 ]
  liveOut = [ _T17 _T18 _T197 ]
    _T210 = 8 [ _T17 _T18 _T197 _T210 ]
    _T211 = (_T197 < _T210) [ _T17 _T18 _T197 _T211 ]
END BY JZERO, if _T211 = 
    0 : goto 15; 1 : goto 8
BASIC BLOCK 8 : 
  Def     = [ _T212 _T213 _T214 ]
  liveUse = [ _T17 _T197 ]
  liveIn  = [ _T17 _T18 _T197 ]
  liveOut = [ _T17 _T18 _T197 _T212 ]
    _T212 = *(_T17 + 4) [ _T17 _T18 _T197 _T212 ]
    _T213 = *(_T212 - 4) [ _T17 _T18 _T197 _T212 _T213 ]
    _T214 = (_T197 < _T213) [ _T17 _T18 _T197 _T212 _T214 ]
END BY JZERO, if _T214 = 
    0 : goto 10; 1 : goto 9
BASIC BLOCK 9 : 
  Def     = [ _T215 _T216 ]
  liveUse = [ _T197 ]
  liveIn  = [ _T17 _T18 _T197 _T212 ]
  liveOut = [ _T17 _T18 _T197 _T212 ]
    _T215 = 0 [ _T17 _T18 _T197 _T212 _T215 ]
    _T216 = (_T197 < _T215) [ _T17 _T18 _T197 _T212 _T216 ]
END BY JZERO, if _T216 = 
    0 : goto 11; 1 : goto 10
BASIC BLOCK 10 : 
  Def     = [ _T217 ]
  liveUse = [ ]
  liveIn  = [ _T17 _T18 _T197 _T212 ]
  liveOut = [ _T17 _T18 _T197 _T212 ]
    _T217 = "Decaf runtime error: Array subscript out of bounds\n" [ _T17 _T18 _T197 _T212 _T217 ]
    parm _T217 [ _T17 _T18 _T197 _T212 ]
    call _PrintString [ _T17 _T18 _T197 _T212 ]
    call _Halt [ _T17 _T18 _T197 _T212 ]
END BY JUMP, goto 11
BASIC BLOCK 11 : 
  Def     = [ _T218 _T219 _T220 _T221 _T222 _T223 _T224 ]
  liveUse = [ _T17 _T197 _T212 ]
  liveIn  = [ _T17 _T18 _T197 _T212 ]
  liveOut = [ _T17 _T18 _T197 _T222 ]
    _T218 =  call _Deck_New [ _T17 _T18 _T197 _T212 _T218 ]
    _T219 = 4 [ _T17 _T18 _T197 _T212 _T218 _T219 ]
    _T220 = (_T197 * _T219) [ _T17 _T18 _T197 _T212 _T218 _T220 ]
    _T221 = (_T212 + _T220) [ _T17 _T18 _T197 _T218 _T221 ]
    *(_T221 + 0) = _T218 [ _T17 _T18 _T197 ]
    _T222 = *(_T17 + 4) [ _T17 _T18 _T197 _T222 ]
    _T223 = *(_T222 - 4) [ _T17 _T18 _T197 _T222 _T223 ]
    _T224 = (_T197 < _T223) [ _T17 _T18 _T197 _T222 _T224 ]
END BY JZERO, if _T224 = 
    0 : goto 13; 1 : goto 12
BASIC BLOCK 12 : 
  Def     = [ _T225 _T226 ]
  liveUse = [ _T197 ]
  liveIn  = [ _T17 _T18 _T197 _T222 ]
  liveOut = [ _T17 _T18 _T197 _T222 ]
    _T225 = 0 [ _T17 _T18 _T197 _T222 _T225 ]
    _T226 = (_T197 < _T225) [ _T17 _T18 _T197 _T222 _T226 ]
END BY JZERO, if _T226 = 
    0 : goto 14; 1 : goto 13
BASIC BLOCK 13 : 
  Def     = [ _T227 ]
  liveUse = [ ]
  liveIn  = [ _T17 _T18 _T197 _T222 ]
  liveOut = [ _T17 _T18 _T197 _T222 ]
    _T227 = "Decaf runtime error: Array subscript out of bounds\n" [ _T17 _T18 _T197 _T222 _T227 ]
    parm _T227 [ _T17 _T18 _T197 _T222 ]
    call _PrintString [ _T17 _T18 _T197 _T222 ]
    call _Halt [ _T17 _T18 _T197 _T222 ]
END BY JUMP, goto 14
BASIC BLOCK 14 : 
  Def     = [ _T228 _T229 _T230 _T231 _T232 _T233 ]
  liveUse = [ _T18 _T197 _T222 ]
  liveIn  = [ _T17 _T18 _T197 _T222 ]
  liveOut = [ _T17 _T18 _T197 ]
    _T228 = 4 [ _T17 _T18 _T197 _T222 _T228 ]
    _T229 = (_T197 * _T228) [ _T17 _T18 _T197 _T222 _T229 ]
    _T230 = (_T222 + _T229) [ _T17 _T18 _T197 _T230 ]
    _T231 = *(_T230 + 0) [ _T17 _T18 _T197 _T231 ]
    parm _T231 [ _T17 _T18 _T197 _T231 ]
    parm _T18 [ _T17 _T18 _T197 _T231 ]
    _T232 = *(_T231 + 0) [ _T17 _T18 _T197 _T232 ]
    _T233 = *(_T232 + 0) [ _T17 _T18 _T197 _T233 ]
    call _T233 [ _T17 _T18 _T197 ]
END BY JUMP, goto 6
BASIC BLOCK 15 : 
  Def     = [ ]
  liveUse = [ _T17 _T18 ]
  liveIn  = [ _T17 _T18 ]
  liveOut = [ ]
    *(_T17 + 12) = _T18 [ ]
END BY RETURN, void result

FUNCTION _BJDeck.DealCard : 
BASIC BLOCK 0 : 
  Def     = [ _T234 _T235 _T236 _T237 _T238 _T239 _T240 ]
  liveUse = [ _T19 ]
  liveIn  = [ _T19 ]
  liveOut = [ _T19 _T234 ]
    _T235 = 0 [ _T19 _T235 ]
    _T234 = _T235 [ _T19 _T234 ]
    _T236 = *(_T19 + 8) [ _T19 _T234 _T236 ]
    _T237 = 8 [ _T19 _T234 _T236 _T237 ]
    _T238 = 52 [ _T19 _T234 _T236 _T237 _T238 ]
    _T239 = (_T237 * _T238) [ _T19 _T234 _T236 _T239 ]
    _T240 = (_T236 >= _T239) [ _T19 _T234 _T240 ]
END BY JZERO, if _T240 = 
    0 : goto 2; 1 : goto 1
BASIC BLOCK 1 : 
  Def     = [ _T241 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T241 = 11 [ _T241 ]
END BY RETURN, result = _T241
BASIC BLOCK 2 : 
  Def     = [ _T242 _T243 ]
  liveUse = [ _T234 ]
  liveIn  = [ _T19 _T234 ]
  liveOut = [ _T19 _T234 ]
    _T242 = 0 [ _T19 _T234 _T242 ]
    _T243 = (_T234 == _T242) [ _T19 _T234 _T243 ]
END BY JZERO, if _T243 = 
    0 : goto 7; 1 : goto 3
BASIC BLOCK 3 : 
  Def     = [ _T244 _T245 _T246 _T247 _T248 _T249 _T250 _T251 _T252 ]
  liveUse = [ _T19 ]
  liveIn  = [ _T19 ]
  liveOut = [ _T19 _T244 _T250 ]
    _T245 = *(_T19 + 12) [ _T19 _T245 ]
    _T246 = 8 [ _T19 _T245 _T246 ]
    parm _T245 [ _T19 _T245 _T246 ]
    parm _T246 [ _T19 _T245 ]
    _T247 = *(_T245 + 0) [ _T19 _T247 ]
    _T248 = *(_T247 + 8) [ _T19 _T248 ]
    _T249 =  call _T248 [ _T19 _T249 ]
    _T244 = _T249 [ _T19 _T244 ]
    _T250 = *(_T19 + 4) [ _T19 _T244 _T250 ]
    _T251 = *(_T250 - 4) [ _T19 _T244 _T250 _T251 ]
    _T252 = (_T244 < _T251) [ _T19 _T244 _T250 _T252 ]
END BY JZERO, if _T252 = 
    0 : goto 5; 1 : goto 4
BASIC BLOCK 4 : 
  Def     = [ _T253 _T254 ]
  liveUse = [ _T244 ]
  liveIn  = [ _T19 _T244 _T250 ]
  liveOut = [ _T19 _T244 _T250 ]
    _T253 = 0 [ _T19 _T244 _T250 _T253 ]
    _T254 = (_T244 < _T253) [ _T19 _T244 _T250 _T254 ]
END BY JZERO, if _T254 = 
    0 : goto 6; 1 : goto 5
BASIC BLOCK 5 : 
  Def     = [ _T255 ]
  liveUse = [ ]
  liveIn  = [ _T19 _T244 _T250 ]
  liveOut = [ _T19 _T244 _T250 ]
    _T255 = "Decaf runtime error: Array subscript out of bounds\n" [ _T19 _T244 _T250 _T255 ]
    parm _T255 [ _T19 _T244 _T250 ]
    call _PrintString [ _T19 _T244 _T250 ]
    call _Halt [ _T19 _T244 _T250 ]
END BY JUMP, goto 6
BASIC BLOCK 6 : 
  Def     = [ _T234 _T256 _T257 _T258 _T259 _T260 _T261 _T262 ]
  liveUse = [ _T244 _T250 ]
  liveIn  = [ _T19 _T244 _T250 ]
  liveOut = [ _T19 _T234 ]
    _T256 = 4 [ _T19 _T244 _T250 _T256 ]
    _T257 = (_T244 * _T256) [ _T19 _T250 _T257 ]
    _T258 = (_T250 + _T257) [ _T19 _T258 ]
    _T259 = *(_T258 + 0) [ _T19 _T259 ]
    parm _T259 [ _T19 _T259 ]
    _T260 = *(_T259 + 0) [ _T19 _T260 ]
    _T261 = *(_T260 + 8) [ _T19 _T261 ]
    _T262 =  call _T261 [ _T19 _T262 ]
    _T234 = _T262 [ _T19 _T234 ]
END BY JUMP, goto 2
BASIC BLOCK 7 : 
  Def     = [ _T263 _T264 ]
  liveUse = [ _T234 ]
  liveIn  = [ _T19 _T234 ]
  liveOut = [ _T19 _T234 ]
    _T263 = 10 [ _T19 _T234 _T263 ]
    _T264 = (_T234 > _T263) [ _T19 _T234 _T264 ]
END BY JZERO, if _T264 = 
    0 : goto 9; 1 : goto 8
BASIC BLOCK 8 : 
  Def     = [ _T234 _T265 ]
  liveUse = [ ]
  liveIn  = [ _T19 ]
  liveOut = [ _T19 _T234 ]
    _T265 = 10 [ _T19 _T265 ]
    _T234 = _T265 [ _T19 _T234 ]
END BY JUMP, goto 11
BASIC BLOCK 9 : 
  Def     = [ _T266 _T267 ]
  liveUse = [ _T234 ]
  liveIn  = [ _T19 _T234 ]
  liveOut = [ _T19 _T234 ]
    _T266 = 1 [ _T19 _T234 _T266 ]
    _T267 = (_T234 == _T266) [ _T19 _T234 _T267 ]
END BY JZERO, if _T267 = 
    0 : goto 11; 1 : goto 10
BASIC BLOCK 10 : 
  Def     = [ _T234 _T268 ]
  liveUse = [ ]
  liveIn  = [ _T19 ]
  liveOut = [ _T19 _T234 ]
    _T268 = 11 [ _T19 _T268 ]
    _T234 = _T268 [ _T19 _T234 ]
END BY JUMP, goto 11
BASIC BLOCK 11 : 
  Def     = [ _T269 _T270 _T271 ]
  liveUse = [ _T19 _T234 ]
  liveIn  = [ _T19 _T234 ]
  liveOut = [ ]
    _T269 = *(_T19 + 8) [ _T19 _T234 _T269 ]
    _T270 = 1 [ _T19 _T234 _T269 _T270 ]
    _T271 = (_T269 + _T270) [ _T19 _T234 _T271 ]
    *(_T19 + 8) = _T271 [ _T234 ]
END BY RETURN, result = _T234

FUNCTION _BJDeck.Shuffle : 
BASIC BLOCK 0 : 
  Def     = [ _T272 _T273 _T274 ]
  liveUse = [ ]
  liveIn  = [ _T20 ]
  liveOut = [ _T20 _T272 ]
    _T273 = "Shuffling..." [ _T20 _T273 ]
    parm _T273 [ _T20 ]
    call _PrintString [ _T20 ]
    _T274 = 0 [ _T20 _T274 ]
    _T272 = _T274 [ _T20 _T272 ]
END BY JUMP, goto 2
BASIC BLOCK 1 : 
  Def     = [ _T272 _T275 _T276 ]
  liveUse = [ _T272 ]
  liveIn  = [ _T20 _T272 ]
  liveOut = [ _T20 _T272 ]
    _T275 = 1 [ _T20 _T272 _T275 ]
    _T276 = (_T272 + _T275) [ _T20 _T276 ]
    _T272 = _T276 [ _T20 _T272 ]
END BY JUMP, goto 2
BASIC BLOCK 2 : 
  Def     = [ _T277 _T278 ]
  liveUse = [ _T272 ]
  liveIn  = [ _T20 _T272 ]
  liveOut = [ _T20 _T272 ]
    _T277 = 8 [ _T20 _T272 _T277 ]
    _T278 = (_T272 < _T277) [ _T20 _T272 _T278 ]
END BY JZERO, if _T278 = 
    0 : goto 7; 1 : goto 3
BASIC BLOCK 3 : 
  Def     = [ _T279 _T280 _T281 ]
  liveUse = [ _T20 _T272 ]
  liveIn  = [ _T20 _T272 ]
  liveOut = [ _T20 _T272 _T279 ]
    _T279 = *(_T20 + 4) [ _T20 _T272 _T279 ]
    _T280 = *(_T279 - 4) [ _T20 _T272 _T279 _T280 ]
    _T281 = (_T272 < _T280) [ _T20 _T272 _T279 _T281 ]
END BY JZERO, if _T281 = 
    0 : goto 5; 1 : goto 4
BASIC BLOCK 4 : 
  Def     = [ _T282 _T283 ]
  liveUse = [ _T272 ]
  liveIn  = [ _T20 _T272 _T279 ]
  liveOut = [ _T20 _T272 _T279 ]
    _T282 = 0 [ _T20 _T272 _T279 _T282 ]
    _T283 = (_T272 < _T282) [ _T20 _T272 _T279 _T283 ]
END BY JZERO, if _T283 = 
    0 : goto 6; 1 : goto 5
BASIC BLOCK 5 : 
  Def     = [ _T284 ]
  liveUse = [ ]
  liveIn  = [ _T20 _T272 _T279 ]
  liveOut = [ _T20 _T272 _T279 ]
    _T284 = "Decaf runtime error: Array subscript out of bounds\n" [ _T20 _T272 _T279 _T284 ]
    parm _T284 [ _T20 _T272 _T279 ]
    call _PrintString [ _T20 _T272 _T279 ]
    call _Halt [ _T20 _T272 _T279 ]
END BY JUMP, goto 6
BASIC BLOCK 6 : 
  Def     = [ _T285 _T286 _T287 _T288 _T289 _T290 ]
  liveUse = [ _T272 _T279 ]
  liveIn  = [ _T20 _T272 _T279 ]
  liveOut = [ _T20 _T272 ]
    _T285 = 4 [ _T20 _T272 _T279 _T285 ]
    _T286 = (_T272 * _T285) [ _T20 _T272 _T279 _T286 ]
    _T287 = (_T279 + _T286) [ _T20 _T272 _T287 ]
    _T288 = *(_T287 + 0) [ _T20 _T272 _T288 ]
    parm _T288 [ _T20 _T272 _T288 ]
    _T289 = *(_T288 + 0) [ _T20 _T272 _T289 ]
    _T290 = *(_T289 + 4) [ _T20 _T272 _T290 ]
    call _T290 [ _T20 _T272 ]
END BY JUMP, goto 1
BASIC BLOCK 7 : 
  Def     = [ _T291 _T292 ]
  liveUse = [ _T20 ]
  liveIn  = [ _T20 ]
  liveOut = [ ]
    _T291 = 0 [ _T20 _T291 ]
    *(_T20 + 8) = _T291 [ ]
    _T292 = "done.\n" [ _T292 ]
    parm _T292 [ ]
    call _PrintString [ ]
END BY RETURN, void result

FUNCTION _BJDeck.NumCardsRemaining : 
BASIC BLOCK 0 : 
  Def     = [ _T293 _T294 _T295 _T296 _T297 ]
  liveUse = [ _T21 ]
  liveIn  = [ _T21 ]
  liveOut = [ ]
    _T293 = 8 [ _T21 _T293 ]
    _T294 = 52 [ _T21 _T293 _T294 ]
    _T295 = (_T293 * _T294) [ _T21 _T295 ]
    _T296 = *(_T21 + 8) [ _T295 _T296 ]
    _T297 = (_T295 - _T296) [ _T297 ]
END BY RETURN, result = _T297

FUNCTION _Player.Init : 
BASIC BLOCK 0 : 
  Def     = [ _T298 _T299 _T300 _T301 ]
  liveUse = [ _T26 _T27 ]
  liveIn  = [ _T26 _T27 ]
  liveOut = [ ]
    _T298 = 1000 [ _T26 _T27 _T298 ]
    *(_T26 + 20) = _T298 [ _T26 _T27 ]
    _T299 = "What is the name of player #" [ _T26 _T27 _T299 ]
    parm _T299 [ _T26 _T27 ]
    call _PrintString [ _T26 _T27 ]
    parm _T27 [ _T26 ]
    call _PrintInt [ _T26 ]
    _T300 = "? " [ _T26 _T300 ]
    parm _T300 [ _T26 ]
    call _PrintString [ _T26 ]
    _T301 =  call _ReadLine [ _T26 _T301 ]
    *(_T26 + 24) = _T301 [ ]
END BY RETURN, void result

FUNCTION _Player.Hit : 
BASIC BLOCK 0 : 
  Def     = [ _T302 _T303 _T304 _T305 _T306 _T307 _T308 _T309 _T310 _T311 _T312 _T313 _T314 _T315 ]
  liveUse = [ _T28 _T29 ]
  liveIn  = [ _T28 _T29 ]
  liveOut = [ _T28 ]
    parm _T29 [ _T28 _T29 ]
    _T303 = *(_T29 + 0) [ _T28 _T303 ]
    _T304 = *(_T303 + 4) [ _T28 _T304 ]
    _T305 =  call _T304 [ _T28 _T305 ]
    _T302 = _T305 [ _T28 _T302 ]
    _T306 = *(_T28 + 24) [ _T28 _T302 _T306 ]
    parm _T306 [ _T28 _T302 ]
    call _PrintString [ _T28 _T302 ]
    _T307 = " was dealt a " [ _T28 _T302 _T307 ]
    parm _T307 [ _T28 _T302 ]
    call _PrintString [ _T28 _T302 ]
    parm _T302 [ _T28 _T302 ]
    call _PrintInt [ _T28 _T302 ]
    _T308 = ".\n" [ _T28 _T302 _T308 ]
    parm _T308 [ _T28 _T302 ]
    call _PrintString [ _T28 _T302 ]
    _T309 = *(_T28 + 4) [ _T28 _T302 _T309 ]
    _T310 = (_T309 + _T302) [ _T28 _T302 _T310 ]
    *(_T28 + 4) = _T310 [ _T28 _T302 ]
    _T311 = *(_T28 + 12) [ _T28 _T302 _T311 ]
    _T312 = 1 [ _T28 _T302 _T311 _T312 ]
    _T313 = (_T311 + _T312) [ _T28 _T302 _T313 ]
    *(_T28 + 12) = _T313 [ _T28 _T302 ]
    _T314 = 11 [ _T28 _T302 _T314 ]
    _T315 = (_T302 == _T314) [ _T28 _T315 ]
END BY JZERO, if _T315 = 
    0 : goto 2; 1 : goto 1
BASIC BLOCK 1 : 
  Def     = [ _T316 _T317 _T318 ]
  liveUse = [ _T28 ]
  liveIn  = [ _T28 ]
  liveOut = [ _T28 ]
    _T316 = *(_T28 + 8) [ _T28 _T316 ]
    _T317 = 1 [ _T28 _T316 _T317 ]
    _T318 = (_T316 + _T317) [ _T28 _T318 ]
    *(_T28 + 8) = _T318 [ _T28 ]
END BY JUMP, goto 2
BASIC BLOCK 2 : 
  Def     = [ _T319 _T320 _T321 _T322 _T323 _T324 _T325 ]
  liveUse = [ _T28 ]
  liveIn  = [ _T28 ]
  liveOut = [ _T28 ]
    _T319 = *(_T28 + 4) [ _T28 _T319 ]
    _T320 = 21 [ _T28 _T319 _T320 ]
    _T321 = (_T319 > _T320) [ _T28 _T321 ]
    _T322 = *(_T28 + 8) [ _T28 _T321 _T322 ]
    _T323 = 0 [ _T28 _T321 _T322 _T323 ]
    _T324 = (_T322 > _T323) [ _T28 _T321 _T324 ]
    _T325 = (_T321 && _T324) [ _T28 _T325 ]
END BY JZERO, if _T325 = 
    0 : goto 4; 1 : goto 3
BASIC BLOCK 3 : 
  Def     = [ _T326 _T327 _T328 _T329 _T330 _T331 ]
  liveUse = [ _T28 ]
  liveIn  = [ _T28 ]
  liveOut = [ _T28 ]
    _T326 = *(_T28 + 4) [ _T28 _T326 ]
    _T327 = 10 [ _T28 _T326 _T327 ]
    _T328 = (_T326 - _T327) [ _T28 _T328 ]
    *(_T28 + 4) = _T328 [ _T28 ]
    _T329 = *(_T28 + 8) [ _T28 _T329 ]
    _T330 = 1 [ _T28 _T329 _T330 ]
    _T331 = (_T329 - _T330) [ _T28 _T331 ]
    *(_T28 + 8) = _T331 [ _T28 ]
END BY JUMP, goto 2
BASIC BLOCK 4 : 
  Def     = [ ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
END BY RETURN, void result

FUNCTION _Player.DoubleDown : 
BASIC BLOCK 0 : 
  Def     = [ _T333 _T334 _T335 _T336 _T337 _T338 _T339 ]
  liveUse = [ _T30 ]
  liveIn  = [ _T30 _T31 ]
  liveOut = [ _T30 _T31 ]
    _T333 = *(_T30 + 4) [ _T30 _T31 _T333 ]
    _T334 = 10 [ _T30 _T31 _T333 _T334 ]
    _T335 = (_T333 != _T334) [ _T30 _T31 _T335 ]
    _T336 = *(_T30 + 4) [ _T30 _T31 _T335 _T336 ]
    _T337 = 11 [ _T30 _T31 _T335 _T336 _T337 ]
    _T338 = (_T336 != _T337) [ _T30 _T31 _T335 _T338 ]
    _T339 = (_T335 && _T338) [ _T30 _T31 _T339 ]
END BY JZERO, if _T339 = 
    0 : goto 2; 1 : goto 1
BASIC BLOCK 1 : 
  Def     = [ _T340 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T340 = 0 [ _T340 ]
END BY RETURN, result = _T340
BASIC BLOCK 2 : 
  Def     = [ _T341 _T342 _T343 _T344 ]
  liveUse = [ _T30 ]
  liveIn  = [ _T30 _T31 ]
  liveOut = [ _T30 _T31 ]
    _T341 = "Would you like to double down?" [ _T30 _T31 _T341 ]
    parm _T30 [ _T30 _T31 _T341 ]
    parm _T341 [ _T30 _T31 ]
    _T342 = *(_T30 + 0) [ _T30 _T31 _T342 ]
    _T343 = *(_T342 + 36) [ _T30 _T31 _T343 ]
    _T344 =  call _T343 [ _T30 _T31 _T344 ]
END BY JZERO, if _T344 = 
    0 : goto 4; 1 : goto 3
BASIC BLOCK 3 : 
  Def     = [ _T345 _T346 _T347 _T348 _T349 _T350 _T351 _T352 _T353 _T354 ]
  liveUse = [ _T30 _T31 ]
  liveIn  = [ _T30 _T31 ]
  liveOut = [ ]
    _T345 = *(_T30 + 16) [ _T30 _T31 _T345 ]
    _T346 = 2 [ _T30 _T31 _T345 _T346 ]
    _T347 = (_T345 * _T346) [ _T30 _T31 _T347 ]
    *(_T30 + 16) = _T347 [ _T30 _T31 ]
    parm _T30 [ _T30 _T31 ]
    parm _T31 [ _T30 ]
    _T348 = *(_T30 + 0) [ _T30 _T348 ]
    _T349 = *(_T348 + 4) [ _T30 _T349 ]
    call _T349 [ _T30 ]
    _T350 = *(_T30 + 24) [ _T30 _T350 ]
    parm _T350 [ _T30 ]
    call _PrintString [ _T30 ]
    _T351 = ", your total is " [ _T30 _T351 ]
    parm _T351 [ _T30 ]
    call _PrintString [ _T30 ]
    _T352 = *(_T30 + 4) [ _T352 ]
    parm _T352 [ ]
    call _PrintInt [ ]
    _T353 = ".\n" [ _T353 ]
    parm _T353 [ ]
    call _PrintString [ ]
    _T354 = 1 [ _T354 ]
END BY RETURN, result = _T354
BASIC BLOCK 4 : 
  Def     = [ _T355 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T355 = 0 [ _T355 ]
END BY RETURN, result = _T355
BASIC BLOCK 5 : 
  Def     = [ ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
END BY RETURN, void result

FUNCTION _Player.TakeTurn : 
BASIC BLOCK 0 : 
  Def     = [ _T357 _T358 _T359 _T360 _T361 _T362 _T363 _T364 _T365 _T366 _T367 _T368 _T369 _T370 ]
  liveUse = [ _T32 _T33 ]
  liveIn  = [ _T32 _T33 ]
  liveOut = [ _T32 _T33 ]
    _T357 = "\n" [ _T32 _T33 _T357 ]
    parm _T357 [ _T32 _T33 ]
    call _PrintString [ _T32 _T33 ]
    _T358 = *(_T32 + 24) [ _T32 _T33 _T358 ]
    parm _T358 [ _T32 _T33 ]
    call _PrintString [ _T32 _T33 ]
    _T359 = "'s turn.\n" [ _T32 _T33 _T359 ]
    parm _T359 [ _T32 _T33 ]
    call _PrintString [ _T32 _T33 ]
    _T360 = 0 [ _T32 _T33 _T360 ]
    *(_T32 + 4) = _T360 [ _T32 _T33 ]
    _T361 = 0 [ _T32 _T33 _T361 ]
    *(_T32 + 8) = _T361 [ _T32 _T33 ]
    _T362 = 0 [ _T32 _T33 _T362 ]
    *(_T32 + 12) = _T362 [ _T32 _T33 ]
    parm _T32 [ _T32 _T33 ]
    parm _T33 [ _T32 _T33 ]
    _T363 = *(_T32 + 0) [ _T32 _T33 _T363 ]
    _T364 = *(_T363 + 4) [ _T32 _T33 _T364 ]
    call _T364 [ _T32 _T33 ]
    parm _T32 [ _T32 _T33 ]
    parm _T33 [ _T32 _T33 ]
    _T365 = *(_T32 + 0) [ _T32 _T33 _T365 ]
    _T366 = *(_T365 + 4) [ _T32 _T33 _T366 ]
    call _T366 [ _T32 _T33 ]
    parm _T32 [ _T32 _T33 ]
    parm _T33 [ _T32 _T33 ]
    _T367 = *(_T32 + 0) [ _T32 _T33 _T367 ]
    _T368 = *(_T367 + 8) [ _T32 _T33 _T368 ]
    _T369 =  call _T368 [ _T32 _T33 _T369 ]
    _T370 = ! _T369 [ _T32 _T33 _T370 ]
END BY JZERO, if _T370 = 
    0 : goto 5; 1 : goto 1
BASIC BLOCK 1 : 
  Def     = [ _T356 _T371 ]
  liveUse = [ ]
  liveIn  = [ _T32 _T33 ]
  liveOut = [ _T32 _T33 _T356 ]
    _T371 = 1 [ _T32 _T33 _T371 ]
    _T356 = _T371 [ _T32 _T33 _T356 ]
END BY JUMP, goto 2
BASIC BLOCK 2 : 
  Def     = [ _T372 _T373 _T374 _T375 ]
  liveUse = [ _T32 _T356 ]
  liveIn  = [ _T32 _T33 _T356 ]
  liveOut = [ _T32 _T33 ]
    _T372 = *(_T32 + 4) [ _T32 _T33 _T356 _T372 ]
    _T373 = 21 [ _T32 _T33 _T356 _T372 _T373 ]
    _T374 = (_T372 <= _T373) [ _T32 _T33 _T356 _T374 ]
    _T375 = (_T374 && _T356) [ _T32 _T33 _T375 ]
END BY JZERO, if _T375 = 
    0 : goto 5; 1 : goto 3
BASIC BLOCK 3 : 
  Def     = [ _T356 _T376 _T377 _T378 _T379 _T380 _T381 _T382 _T383 ]
  liveUse = [ _T32 ]
  liveIn  = [ _T32 _T33 ]
  liveOut = [ _T32 _T33 _T356 ]
    _T376 = *(_T32 + 24) [ _T32 _T33 _T376 ]
    parm _T376 [ _T32 _T33 ]
    call _PrintString [ _T32 _T33 ]
    _T377 = ", your total is " [ _T32 _T33 _T377 ]
    parm _T377 [ _T32 _T33 ]
    call _PrintString [ _T32 _T33 ]
    _T378 = *(_T32 + 4) [ _T32 _T33 _T378 ]
    parm _T378 [ _T32 _T33 ]
    call _PrintInt [ _T32 _T33 ]
    _T379 = ".\n" [ _T32 _T33 _T379 ]
    parm _T379 [ _T32 _T33 ]
    call _PrintString [ _T32 _T33 ]
    _T380 = "Would you like a hit?" [ _T32 _T33 _T380 ]
    parm _T32 [ _T32 _T33 _T380 ]
    parm _T380 [ _T32 _T33 ]
    _T381 = *(_T32 + 0) [ _T32 _T33 _T381 ]
    _T382 = *(_T381 + 36) [ _T32 _T33 _T382 ]
    _T383 =  call _T382 [ _T32 _T33 _T383 ]
    _T356 = _T383 [ _T32 _T33 _T356 ]
END BY JZERO, if _T356 = 
    0 : goto 2; 1 : goto 4
BASIC BLOCK 4 : 
  Def     = [ _T384 _T385 ]
  liveUse = [ _T32 _T33 ]
  liveIn  = [ _T32 _T33 _T356 ]
  liveOut = [ _T32 _T33 _T356 ]
    parm _T32 [ _T32 _T33 _T356 ]
    parm _T33 [ _T32 _T33 _T356 ]
    _T384 = *(_T32 + 0) [ _T32 _T33 _T356 _T384 ]
    _T385 = *(_T384 + 4) [ _T32 _T33 _T356 _T385 ]
    call _T385 [ _T32 _T33 _T356 ]
END BY JUMP, goto 2
BASIC BLOCK 5 : 
  Def     = [ _T386 _T387 _T388 ]
  liveUse = [ _T32 ]
  liveIn  = [ _T32 ]
  liveOut = [ _T32 ]
    _T386 = *(_T32 + 4) [ _T32 _T386 ]
    _T387 = 21 [ _T32 _T386 _T387 ]
    _T388 = (_T386 > _T387) [ _T32 _T388 ]
END BY JZERO, if _T388 = 
    0 : goto 7; 1 : goto 6
BASIC BLOCK 6 : 
  Def     = [ _T389 _T390 _T391 _T392 ]
  liveUse = [ _T32 ]
  liveIn  = [ _T32 ]
  liveOut = [ ]
    _T389 = *(_T32 + 24) [ _T32 _T389 ]
    parm _T389 [ _T32 ]
    call _PrintString [ _T32 ]
    _T390 = " busts with the big " [ _T32 _T390 ]
    parm _T390 [ _T32 ]
    call _PrintString [ _T32 ]
    _T391 = *(_T32 + 4) [ _T391 ]
    parm _T391 [ ]
    call _PrintInt [ ]
    _T392 = "!\n" [ _T392 ]
    parm _T392 [ ]
    call _PrintString [ ]
END BY JUMP, goto 8
BASIC BLOCK 7 : 
  Def     = [ _T393 _T394 _T395 _T396 ]
  liveUse = [ _T32 ]
  liveIn  = [ _T32 ]
  liveOut = [ ]
    _T393 = *(_T32 + 24) [ _T32 _T393 ]
    parm _T393 [ _T32 ]
    call _PrintString [ _T32 ]
    _T394 = " stays at " [ _T32 _T394 ]
    parm _T394 [ _T32 ]
    call _PrintString [ _T32 ]
    _T395 = *(_T32 + 4) [ _T395 ]
    parm _T395 [ ]
    call _PrintInt [ ]
    _T396 = ".\n" [ _T396 ]
    parm _T396 [ ]
    call _PrintString [ ]
END BY JUMP, goto 8
BASIC BLOCK 8 : 
  Def     = [ ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
END BY RETURN, void result

FUNCTION _Player.HasMoney : 
BASIC BLOCK 0 : 
  Def     = [ _T397 _T398 _T399 ]
  liveUse = [ _T34 ]
  liveIn  = [ _T34 ]
  liveOut = [ ]
    _T397 = *(_T34 + 20) [ _T397 ]
    _T398 = 0 [ _T397 _T398 ]
    _T399 = (_T397 > _T398) [ _T399 ]
END BY RETURN, result = _T399

FUNCTION _Player.PrintMoney : 
BASIC BLOCK 0 : 
  Def     = [ _T400 _T401 _T402 _T403 ]
  liveUse = [ _T35 ]
  liveIn  = [ _T35 ]
  liveOut = [ ]
    _T400 = *(_T35 + 24) [ _T35 _T400 ]
    parm _T400 [ _T35 ]
    call _PrintString [ _T35 ]
    _T401 = ", you have $" [ _T35 _T401 ]
    parm _T401 [ _T35 ]
    call _PrintString [ _T35 ]
    _T402 = *(_T35 + 20) [ _T402 ]
    parm _T402 [ ]
    call _PrintInt [ ]
    _T403 = ".\n" [ _T403 ]
    parm _T403 [ ]
    call _PrintString [ ]
END BY RETURN, void result

FUNCTION _Player.PlaceBet : 
BASIC BLOCK 0 : 
  Def     = [ _T404 _T405 _T406 ]
  liveUse = [ _T36 ]
  liveIn  = [ _T36 ]
  liveOut = [ _T36 ]
    _T404 = 0 [ _T36 _T404 ]
    *(_T36 + 16) = _T404 [ _T36 ]
    parm _T36 [ _T36 ]
    _T405 = *(_T36 + 0) [ _T36 _T405 ]
    _T406 = *(_T405 + 20) [ _T36 _T406 ]
    call _T406 [ _T36 ]
END BY JUMP, goto 1
BASIC BLOCK 1 : 
  Def     = [ _T407 _T408 _T409 _T410 _T411 _T412 _T413 ]
  liveUse = [ _T36 ]
  liveIn  = [ _T36 ]
  liveOut = [ _T36 ]
    _T407 = *(_T36 + 16) [ _T36 _T407 ]
    _T408 = 0 [ _T36 _T407 _T408 ]
    _T409 = (_T407 <= _T408) [ _T36 _T409 ]
    _T410 = *(_T36 + 16) [ _T36 _T409 _T410 ]
    _T411 = *(_T36 + 20) [ _T36 _T409 _T410 _T411 ]
    _T412 = (_T410 > _T411) [ _T36 _T409 _T412 ]
    _T413 = (_T409 || _T412) [ _T36 _T413 ]
END BY JZERO, if _T413 = 
    0 : goto 3; 1 : goto 2
BASIC BLOCK 2 : 
  Def     = [ _T414 _T415 ]
  liveUse = [ _T36 ]
  liveIn  = [ _T36 ]
  liveOut = [ _T36 ]
    _T414 = "How much would you like to bet? " [ _T36 _T414 ]
    parm _T414 [ _T36 ]
    call _PrintString [ _T36 ]
    _T415 =  call _ReadInteger [ _T36 _T415 ]
    *(_T36 + 16) = _T415 [ _T36 ]
END BY JUMP, goto 1
BASIC BLOCK 3 : 
  Def     = [ ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
END BY RETURN, void result

FUNCTION _Player.GetTotal : 
BASIC BLOCK 0 : 
  Def     = [ _T416 ]
  liveUse = [ _T37 ]
  liveIn  = [ _T37 ]
  liveOut = [ ]
    _T416 = *(_T37 + 4) [ _T416 ]
END BY RETURN, result = _T416

FUNCTION _Player.Resolve : 
BASIC BLOCK 0 : 
  Def     = [ _T417 _T418 _T419 _T420 _T421 _T422 _T423 _T424 _T425 _T426 _T427 ]
  liveUse = [ _T38 ]
  liveIn  = [ _T38 _T39 ]
  liveOut = [ _T38 _T39 _T417 _T418 ]
    _T419 = 0 [ _T38 _T39 _T419 ]
    _T417 = _T419 [ _T38 _T39 _T417 ]
    _T420 = 0 [ _T38 _T39 _T417 _T420 ]
    _T418 = _T420 [ _T38 _T39 _T417 _T418 ]
    _T421 = *(_T38 + 4) [ _T38 _T39 _T417 _T418 _T421 ]
    _T422 = 21 [ _T38 _T39 _T417 _T418 _T421 _T422 ]
    _T423 = (_T421 == _T422) [ _T38 _T39 _T417 _T418 _T423 ]
    _T424 = *(_T38 + 12) [ _T38 _T39 _T417 _T418 _T423 _T424 ]
    _T425 = 2 [ _T38 _T39 _T417 _T418 _T423 _T424 _T425 ]
    _T426 = (_T424 == _T425) [ _T38 _T39 _T417 _T418 _T423 _T426 ]
    _T427 = (_T423 && _T426) [ _T38 _T39 _T417 _T418 _T427 ]
END BY JZERO, if _T427 = 
    0 : goto 2; 1 : goto 1
BASIC BLOCK 1 : 
  Def     = [ _T417 _T428 ]
  liveUse = [ ]
  liveIn  = [ _T38 _T418 ]
  liveOut = [ _T38 _T417 _T418 ]
    _T428 = 2 [ _T38 _T418 _T428 ]
    _T417 = _T428 [ _T38 _T417 _T418 ]
END BY JUMP, goto 10
BASIC BLOCK 2 : 
  Def     = [ _T429 _T430 _T431 ]
  liveUse = [ _T38 ]
  liveIn  = [ _T38 _T39 _T417 _T418 ]
  liveOut = [ _T38 _T39 _T417 _T418 ]
    _T429 = *(_T38 + 4) [ _T38 _T39 _T417 _T418 _T429 ]
    _T430 = 21 [ _T38 _T39 _T417 _T418 _T429 _T430 ]
    _T431 = (_T429 > _T430) [ _T38 _T39 _T417 _T418 _T431 ]
END BY JZERO, if _T431 = 
    0 : goto 4; 1 : goto 3
BASIC BLOCK 3 : 
  Def     = [ _T418 _T432 ]
  liveUse = [ ]
  liveIn  = [ _T38 _T417 ]
  liveOut = [ _T38 _T417 _T418 ]
    _T432 = 1 [ _T38 _T417 _T432 ]
    _T418 = _T432 [ _T38 _T417 _T418 ]
END BY JUMP, goto 10
BASIC BLOCK 4 : 
  Def     = [ _T433 _T434 ]
  liveUse = [ _T39 ]
  liveIn  = [ _T38 _T39 _T417 _T418 ]
  liveOut = [ _T38 _T39 _T417 _T418 ]
    _T433 = 21 [ _T38 _T39 _T417 _T418 _T433 ]
    _T434 = (_T39 > _T433) [ _T38 _T39 _T417 _T418 _T434 ]
END BY JZERO, if _T434 = 
    0 : goto 6; 1 : goto 5
BASIC BLOCK 5 : 
  Def     = [ _T417 _T435 ]
  liveUse = [ ]
  liveIn  = [ _T38 _T418 ]
  liveOut = [ _T38 _T417 _T418 ]
    _T435 = 1 [ _T38 _T418 _T435 ]
    _T417 = _T435 [ _T38 _T417 _T418 ]
END BY JUMP, goto 10
BASIC BLOCK 6 : 
  Def     = [ _T436 _T437 ]
  liveUse = [ _T38 _T39 ]
  liveIn  = [ _T38 _T39 _T417 _T418 ]
  liveOut = [ _T38 _T39 _T417 _T418 ]
    _T436 = *(_T38 + 4) [ _T38 _T39 _T417 _T418 _T436 ]
    _T437 = (_T436 > _T39) [ _T38 _T39 _T417 _T418 _T437 ]
END BY JZERO, if _T437 = 
    0 : goto 8; 1 : goto 7
BASIC BLOCK 7 : 
  Def     = [ _T417 _T438 ]
  liveUse = [ ]
  liveIn  = [ _T38 _T418 ]
  liveOut = [ _T38 _T417 _T418 ]
    _T438 = 1 [ _T38 _T418 _T438 ]
    _T417 = _T438 [ _T38 _T417 _T418 ]
END BY JUMP, goto 10
BASIC BLOCK 8 : 
  Def     = [ _T439 _T440 ]
  liveUse = [ _T38 _T39 ]
  liveIn  = [ _T38 _T39 _T417 _T418 ]
  liveOut = [ _T38 _T417 _T418 ]
    _T439 = *(_T38 + 4) [ _T38 _T39 _T417 _T418 _T439 ]
    _T440 = (_T39 > _T439) [ _T38 _T417 _T418 _T440 ]
END BY JZERO, if _T440 = 
    0 : goto 10; 1 : goto 9
BASIC BLOCK 9 : 
  Def     = [ _T418 _T441 ]
  liveUse = [ ]
  liveIn  = [ _T38 _T417 ]
  liveOut = [ _T38 _T417 _T418 ]
    _T441 = 1 [ _T38 _T417 _T441 ]
    _T418 = _T441 [ _T38 _T417 _T418 ]
END BY JUMP, goto 10
BASIC BLOCK 10 : 
  Def     = [ _T442 _T443 ]
  liveUse = [ _T417 ]
  liveIn  = [ _T38 _T417 _T418 ]
  liveOut = [ _T38 _T417 _T418 ]
    _T442 = 1 [ _T38 _T417 _T418 _T442 ]
    _T443 = (_T417 >= _T442) [ _T38 _T417 _T418 _T443 ]
END BY JZERO, if _T443 = 
    0 : goto 12; 1 : goto 11
BASIC BLOCK 11 : 
  Def     = [ _T444 _T445 _T446 _T447 ]
  liveUse = [ _T38 ]
  liveIn  = [ _T38 _T417 _T418 ]
  liveOut = [ _T38 _T417 _T418 ]
    _T444 = *(_T38 + 24) [ _T38 _T417 _T418 _T444 ]
    parm _T444 [ _T38 _T417 _T418 ]
    call _PrintString [ _T38 _T417 _T418 ]
    _T445 = ", you won $" [ _T38 _T417 _T418 _T445 ]
    parm _T445 [ _T38 _T417 _T418 ]
    call _PrintString [ _T38 _T417 _T418 ]
    _T446 = *(_T38 + 16) [ _T38 _T417 _T418 _T446 ]
    parm _T446 [ _T38 _T417 _T418 ]
    call _PrintInt [ _T38 _T417 _T418 ]
    _T447 = ".\n" [ _T38 _T417 _T418 _T447 ]
    parm _T447 [ _T38 _T417 _T418 ]
    call _PrintString [ _T38 _T417 _T418 ]
END BY JUMP, goto 15
BASIC BLOCK 12 : 
  Def     = [ _T448 _T449 ]
  liveUse = [ _T418 ]
  liveIn  = [ _T38 _T417 _T418 ]
  liveOut = [ _T38 _T417 _T418 ]
    _T448 = 1 [ _T38 _T417 _T418 _T448 ]
    _T449 = (_T418 >= _T448) [ _T38 _T417 _T418 _T449 ]
END BY JZERO, if _T449 = 
    0 : goto 14; 1 : goto 13
BASIC BLOCK 13 : 
  Def     = [ _T450 _T451 _T452 _T453 ]
  liveUse = [ _T38 ]
  liveIn  = [ _T38 _T417 _T418 ]
  liveOut = [ _T38 _T417 _T418 ]
    _T450 = *(_T38 + 24) [ _T38 _T417 _T418 _T450 ]
    parm _T450 [ _T38 _T417 _T418 ]
    call _PrintString [ _T38 _T417 _T418 ]
    _T451 = ", you lost $" [ _T38 _T417 _T418 _T451 ]
    parm _T451 [ _T38 _T417 _T418 ]
    call _PrintString [ _T38 _T417 _T418 ]
    _T452 = *(_T38 + 16) [ _T38 _T417 _T418 _T452 ]
    parm _T452 [ _T38 _T417 _T418 ]
    call _PrintInt [ _T38 _T417 _T418 ]
    _T453 = ".\n" [ _T38 _T417 _T418 _T453 ]
    parm _T453 [ _T38 _T417 _T418 ]
    call _PrintString [ _T38 _T417 _T418 ]
END BY JUMP, goto 15
BASIC BLOCK 14 : 
  Def     = [ _T454 _T455 ]
  liveUse = [ _T38 ]
  liveIn  = [ _T38 _T417 _T418 ]
  liveOut = [ _T38 _T417 _T418 ]
    _T454 = *(_T38 + 24) [ _T38 _T417 _T418 _T454 ]
    parm _T454 [ _T38 _T417 _T418 ]
    call _PrintString [ _T38 _T417 _T418 ]
    _T455 = ", you push!\n" [ _T38 _T417 _T418 _T455 ]
    parm _T455 [ _T38 _T417 _T418 ]
    call _PrintString [ _T38 _T417 _T418 ]
END BY JUMP, goto 15
BASIC BLOCK 15 : 
  Def     = [ _T417 _T418 _T456 _T457 _T458 _T459 _T460 _T461 _T462 ]
  liveUse = [ _T38 _T417 _T418 ]
  liveIn  = [ _T38 _T417 _T418 ]
  liveOut = [ ]
    _T456 = *(_T38 + 16) [ _T38 _T417 _T418 _T456 ]
    _T457 = (_T417 * _T456) [ _T38 _T418 _T457 ]
    _T417 = _T457 [ _T38 _T417 _T418 ]
    _T458 = *(_T38 + 16) [ _T38 _T417 _T418 _T458 ]
    _T459 = (_T418 * _T458) [ _T38 _T417 _T459 ]
    _T418 = _T459 [ _T38 _T417 _T418 ]
    _T460 = *(_T38 + 20) [ _T38 _T417 _T418 _T460 ]
    _T461 = (_T460 + _T417) [ _T38 _T418 _T461 ]
    _T462 = (_T461 - _T418) [ _T38 _T462 ]
    *(_T38 + 20) = _T462 [ ]
END BY RETURN, void result

FUNCTION _Player.GetYesOrNo : 
BASIC BLOCK 0 : 
  Def     = [ _T463 _T464 _T465 _T466 ]
  liveUse = [ _T41 ]
  liveIn  = [ _T41 ]
  liveOut = [ ]
    parm _T41 [ ]
    call _PrintString [ ]
    _T463 = " (0=No/1=Yes) " [ _T463 ]
    parm _T463 [ ]
    call _PrintString [ ]
    _T464 =  call _ReadInteger [ _T464 ]
    _T465 = 0 [ _T464 _T465 ]
    _T466 = (_T464 != _T465) [ _T466 ]
END BY RETURN, result = _T466

FUNCTION _Dealer.Init : 
BASIC BLOCK 0 : 
  Def     = [ _T467 _T468 _T469 _T470 _T471 ]
  liveUse = [ _T50 ]
  liveIn  = [ _T50 ]
  liveOut = [ ]
    _T468 = 0 [ _T50 _T468 ]
    *(_T50 + 4) = _T468 [ _T50 ]
    _T469 = 0 [ _T50 _T469 ]
    *(_T50 + 8) = _T469 [ _T50 ]
    _T470 = 0 [ _T50 _T470 ]
    *(_T50 + 12) = _T470 [ _T50 ]
    _T471 = "Dealer" [ _T50 _T471 ]
    _T467 = _T471 [ _T50 _T467 ]
    *(_T50 + 24) = _T467 [ ]
END BY RETURN, void result

FUNCTION _Dealer.TakeTurn : 
BASIC BLOCK 0 : 
  Def     = [ _T472 _T473 _T474 ]
  liveUse = [ _T52 ]
  liveIn  = [ _T52 _T53 ]
  liveOut = [ _T52 _T53 ]
    _T472 = "\n" [ _T52 _T53 _T472 ]
    parm _T472 [ _T52 _T53 ]
    call _PrintString [ _T52 _T53 ]
    _T473 = *(_T52 + 24) [ _T52 _T53 _T473 ]
    parm _T473 [ _T52 _T53 ]
    call _PrintString [ _T52 _T53 ]
    _T474 = "'s turn.\n" [ _T52 _T53 _T474 ]
    parm _T474 [ _T52 _T53 ]
    call _PrintString [ _T52 _T53 ]
END BY JUMP, goto 1
BASIC BLOCK 1 : 
  Def     = [ _T475 _T476 _T477 ]
  liveUse = [ _T52 ]
  liveIn  = [ _T52 _T53 ]
  liveOut = [ _T52 _T53 ]
    _T475 = *(_T52 + 4) [ _T52 _T53 _T475 ]
    _T476 = 16 [ _T52 _T53 _T475 _T476 ]
    _T477 = (_T475 <= _T476) [ _T52 _T53 _T477 ]
END BY JZERO, if _T477 = 
    0 : goto 3; 1 : goto 2
BASIC BLOCK 2 : 
  Def     = [ _T478 _T479 ]
  liveUse = [ _T52 _T53 ]
  liveIn  = [ _T52 _T53 ]
  liveOut = [ _T52 _T53 ]
    parm _T52 [ _T52 _T53 ]
    parm _T53 [ _T52 _T53 ]
    _T478 = *(_T52 + 0) [ _T52 _T53 _T478 ]
    _T479 = *(_T478 + 4) [ _T52 _T53 _T479 ]
    call _T479 [ _T52 _T53 ]
END BY JUMP, goto 1
BASIC BLOCK 3 : 
  Def     = [ _T480 _T481 _T482 ]
  liveUse = [ _T52 ]
  liveIn  = [ _T52 ]
  liveOut = [ _T52 ]
    _T480 = *(_T52 + 4) [ _T52 _T480 ]
    _T481 = 21 [ _T52 _T480 _T481 ]
    _T482 = (_T480 > _T481) [ _T52 _T482 ]
END BY JZERO, if _T482 = 
    0 : goto 5; 1 : goto 4
BASIC BLOCK 4 : 
  Def     = [ _T483 _T484 _T485 _T486 ]
  liveUse = [ _T52 ]
  liveIn  = [ _T52 ]
  liveOut = [ ]
    _T483 = *(_T52 + 24) [ _T52 _T483 ]
    parm _T483 [ _T52 ]
    call _PrintString [ _T52 ]
    _T484 = " busts with the big " [ _T52 _T484 ]
    parm _T484 [ _T52 ]
    call _PrintString [ _T52 ]
    _T485 = *(_T52 + 4) [ _T485 ]
    parm _T485 [ ]
    call _PrintInt [ ]
    _T486 = "!\n" [ _T486 ]
    parm _T486 [ ]
    call _PrintString [ ]
END BY JUMP, goto 6
BASIC BLOCK 5 : 
  Def     = [ _T487 _T488 _T489 _T490 ]
  liveUse = [ _T52 ]
  liveIn  = [ _T52 ]
  liveOut = [ ]
    _T487 = *(_T52 + 24) [ _T52 _T487 ]
    parm _T487 [ _T52 ]
    call _PrintString [ _T52 ]
    _T488 = " stays at " [ _T52 _T488 ]
    parm _T488 [ _T52 ]
    call _PrintString [ _T52 ]
    _T489 = *(_T52 + 4) [ _T489 ]
    parm _T489 [ ]
    call _PrintInt [ ]
    _T490 = ".\n" [ _T490 ]
    parm _T490 [ ]
    call _PrintString [ ]
END BY JUMP, goto 6
BASIC BLOCK 6 : 
  Def     = [ ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
END BY RETURN, void result

FUNCTION _House.SetupGame : 
BASIC BLOCK 0 : 
  Def     = [ _T491 _T492 _T493 _T494 _T495 _T496 _T497 _T498 _T499 _T500 _T501 _T502 _T503 _T504 _T505 _T506 ]
  liveUse = [ _T62 ]
  liveIn  = [ _T62 ]
  liveOut = [ ]
    _T491 = "\nWelcome to CS143 BlackJack!\n" [ _T62 _T491 ]
    parm _T491 [ _T62 ]
    call _PrintString [ _T62 ]
    _T492 = "---------------------------\n" [ _T62 _T492 ]
    parm _T492 [ _T62 ]
    call _PrintString [ _T62 ]
    _T494 =  call _rndModule_New [ _T62 _T494 ]
    _T493 = _T494 [ _T62 _T493 ]
    _T495 = "Please enter a random number seed: " [ _T62 _T493 _T495 ]
    parm _T495 [ _T62 _T493 ]
    call _PrintString [ _T62 _T493 ]
    _T496 =  call _ReadInteger [ _T62 _T493 _T496 ]
    parm _T493 [ _T62 _T493 _T496 ]
    parm _T496 [ _T62 _T493 ]
    _T497 = *(_T493 + 0) [ _T62 _T493 _T497 ]
    _T498 = *(_T497 + 0) [ _T62 _T493 _T498 ]
    call _T498 [ _T62 _T493 ]
    _T499 =  call _BJDeck_New [ _T62 _T493 _T499 ]
    *(_T62 + 12) = _T499 [ _T62 _T493 ]
    _T500 =  call _Dealer_New [ _T62 _T493 _T500 ]
    *(_T62 + 8) = _T500 [ _T62 _T493 ]
    _T501 = *(_T62 + 12) [ _T62 _T493 _T501 ]
    parm _T501 [ _T62 _T493 _T501 ]
    parm _T493 [ _T62 _T501 ]
    _T502 = *(_T501 + 0) [ _T62 _T502 ]
    _T503 = *(_T502 + 0) [ _T62 _T503 ]
    call _T503 [ _T62 ]
    _T504 = *(_T62 + 12) [ _T504 ]
    parm _T504 [ _T504 ]
    _T505 = *(_T504 + 0) [ _T505 ]
    _T506 = *(_T505 + 8) [ _T506 ]
    call _T506 [ ]
END BY RETURN, void result

FUNCTION _House.SetupPlayers : 
BASIC BLOCK 0 : 
  Def     = [ _T508 _T509 _T510 _T511 _T512 ]
  liveUse = [ ]
  liveIn  = [ _T63 ]
  liveOut = [ _T63 _T508 ]
    _T509 = "How many players do we have today? " [ _T63 _T509 ]
    parm _T509 [ _T63 ]
    call _PrintString [ _T63 ]
    _T510 =  call _ReadInteger [ _T63 _T510 ]
    _T508 = _T510 [ _T63 _T508 ]
    _T511 = 0 [ _T63 _T508 _T511 ]
    _T512 = (_T508 < _T511) [ _T63 _T508 _T512 ]
END BY JZERO, if _T512 = 
    0 : goto 2; 1 : goto 1
BASIC BLOCK 1 : 
  Def     = [ _T513 ]
  liveUse = [ ]
  liveIn  = [ _T63 _T508 ]
  liveOut = [ _T63 _T508 ]
    _T513 = "Decaf runtime error: Cannot create negative-sized array\n" [ _T63 _T508 _T513 ]
    parm _T513 [ _T63 _T508 ]
    call _PrintString [ _T63 _T508 ]
    call _Halt [ _T63 _T508 ]
END BY JUMP, goto 2
BASIC BLOCK 2 : 
  Def     = [ _T514 _T515 _T516 _T517 _T518 ]
  liveUse = [ _T508 ]
  liveIn  = [ _T63 _T508 ]
  liveOut = [ _T63 _T514 _T516 _T517 _T518 ]
    _T514 = 4 [ _T63 _T508 _T514 ]
    _T515 = (_T514 * _T508) [ _T63 _T508 _T514 _T515 ]
    _T516 = (_T514 + _T515) [ _T63 _T508 _T514 _T516 ]
    parm _T516 [ _T63 _T508 _T514 _T516 ]
    _T517 =  call _Alloc [ _T63 _T508 _T514 _T516 _T517 ]
    *(_T517 + 0) = _T508 [ _T63 _T514 _T516 _T517 ]
    _T518 = 0 [ _T63 _T514 _T516 _T517 _T518 ]
    _T517 = (_T517 + _T516) [ _T63 _T514 _T516 _T517 _T518 ]
END BY JUMP, goto 3
BASIC BLOCK 3 : 
  Def     = [ _T516 ]
  liveUse = [ _T514 _T516 ]
  liveIn  = [ _T63 _T514 _T516 _T517 _T518 ]
  liveOut = [ _T63 _T514 _T516 _T517 _T518 ]
    _T516 = (_T516 - _T514) [ _T63 _T514 _T516 _T517 _T518 ]
END BY JZERO, if _T516 = 
    0 : goto 5; 1 : goto 4
BASIC BLOCK 4 : 
  Def     = [ _T517 ]
  liveUse = [ _T514 _T517 _T518 ]
  liveIn  = [ _T63 _T514 _T516 _T517 _T518 ]
  liveOut = [ _T63 _T514 _T516 _T517 _T518 ]
    _T517 = (_T517 - _T514) [ _T63 _T514 _T516 _T517 _T518 ]
    *(_T517 + 0) = _T518 [ _T63 _T514 _T516 _T517 _T518 ]
END BY JUMP, goto 3
BASIC BLOCK 5 : 
  Def     = [ _T507 _T519 ]
  liveUse = [ _T63 _T517 ]
  liveIn  = [ _T63 _T517 ]
  liveOut = [ _T63 _T507 ]
    *(_T63 + 4) = _T517 [ _T63 ]
    _T519 = 0 [ _T63 _T519 ]
    _T507 = _T519 [ _T63 _T507 ]
END BY JUMP, goto 7
BASIC BLOCK 6 : 
  Def     = [ _T507 _T520 _T521 ]
  liveUse = [ _T507 ]
  liveIn  = [ _T63 _T507 ]
  liveOut = [ _T63 _T507 ]
    _T520 = 1 [ _T63 _T507 _T520 ]
    _T521 = (_T507 + _T520) [ _T63 _T521 ]
    _T507 = _T521 [ _T63 _T507 ]
END BY JUMP, goto 7
BASIC BLOCK 7 : 
  Def     = [ _T522 _T523 _T524 ]
  liveUse = [ _T63 _T507 ]
  liveIn  = [ _T63 _T507 ]
  liveOut = [ _T63 _T507 ]
    _T522 = *(_T63 + 4) [ _T63 _T507 _T522 ]
    _T523 = *(_T522 - 4) [ _T63 _T507 _T523 ]
    _T524 = (_T507 < _T523) [ _T63 _T507 _T524 ]
END BY JZERO, if _T524 = 
    0 : goto 15; 1 : goto 8
BASIC BLOCK 8 : 
  Def     = [ _T525 _T526 _T527 ]
  liveUse = [ _T63 _T507 ]
  liveIn  = [ _T63 _T507 ]
  liveOut = [ _T63 _T507 _T525 ]
    _T525 = *(_T63 + 4) [ _T63 _T507 _T525 ]
    _T526 = *(_T525 - 4) [ _T63 _T507 _T525 _T526 ]
    _T527 = (_T507 < _T526) [ _T63 _T507 _T525 _T527 ]
END BY JZERO, if _T527 = 
    0 : goto 10; 1 : goto 9
BASIC BLOCK 9 : 
  Def     = [ _T528 _T529 ]
  liveUse = [ _T507 ]
  liveIn  = [ _T63 _T507 _T525 ]
  liveOut = [ _T63 _T507 _T525 ]
    _T528 = 0 [ _T63 _T507 _T525 _T528 ]
    _T529 = (_T507 < _T528) [ _T63 _T507 _T525 _T529 ]
END BY JZERO, if _T529 = 
    0 : goto 11; 1 : goto 10
BASIC BLOCK 10 : 
  Def     = [ _T530 ]
  liveUse = [ ]
  liveIn  = [ _T63 _T507 _T525 ]
  liveOut = [ _T63 _T507 _T525 ]
    _T530 = "Decaf runtime error: Array subscript out of bounds\n" [ _T63 _T507 _T525 _T530 ]
    parm _T530 [ _T63 _T507 _T525 ]
    call _PrintString [ _T63 _T507 _T525 ]
    call _Halt [ _T63 _T507 _T525 ]
END BY JUMP, goto 11
BASIC BLOCK 11 : 
  Def     = [ _T531 _T532 _T533 _T534 _T535 _T536 _T537 ]
  liveUse = [ _T63 _T507 _T525 ]
  liveIn  = [ _T63 _T507 _T525 ]
  liveOut = [ _T63 _T507 _T535 ]
    _T531 =  call _Player_New [ _T63 _T507 _T525 _T531 ]
    _T532 = 4 [ _T63 _T507 _T525 _T531 _T532 ]
    _T533 = (_T507 * _T532) [ _T63 _T507 _T525 _T531 _T533 ]
    _T534 = (_T525 + _T533) [ _T63 _T507 _T531 _T534 ]
    *(_T534 + 0) = _T531 [ _T63 _T507 ]
    _T535 = *(_T63 + 4) [ _T63 _T507 _T535 ]
    _T536 = *(_T535 - 4) [ _T63 _T507 _T535 _T536 ]
    _T537 = (_T507 < _T536) [ _T63 _T507 _T535 _T537 ]
END BY JZERO, if _T537 = 
    0 : goto 13; 1 : goto 12
BASIC BLOCK 12 : 
  Def     = [ _T538 _T539 ]
  liveUse = [ _T507 ]
  liveIn  = [ _T63 _T507 _T535 ]
  liveOut = [ _T63 _T507 _T535 ]
    _T538 = 0 [ _T63 _T507 _T535 _T538 ]
    _T539 = (_T507 < _T538) [ _T63 _T507 _T535 _T539 ]
END BY JZERO, if _T539 = 
    0 : goto 14; 1 : goto 13
BASIC BLOCK 13 : 
  Def     = [ _T540 ]
  liveUse = [ ]
  liveIn  = [ _T63 _T507 _T535 ]
  liveOut = [ _T63 _T507 _T535 ]
    _T540 = "Decaf runtime error: Array subscript out of bounds\n" [ _T63 _T507 _T535 _T540 ]
    parm _T540 [ _T63 _T507 _T535 ]
    call _PrintString [ _T63 _T507 _T535 ]
    call _Halt [ _T63 _T507 _T535 ]
END BY JUMP, goto 14
BASIC BLOCK 14 : 
  Def     = [ _T541 _T542 _T543 _T544 _T545 _T546 _T547 _T548 ]
  liveUse = [ _T507 _T535 ]
  liveIn  = [ _T63 _T507 _T535 ]
  liveOut = [ _T63 _T507 ]
    _T541 = 4 [ _T63 _T507 _T535 _T541 ]
    _T542 = (_T507 * _T541) [ _T63 _T507 _T535 _T542 ]
    _T543 = (_T535 + _T542) [ _T63 _T507 _T543 ]
    _T544 = *(_T543 + 0) [ _T63 _T507 _T544 ]
    _T545 = 1 [ _T63 _T507 _T544 _T545 ]
    _T546 = (_T507 + _T545) [ _T63 _T507 _T544 _T546 ]
    parm _T544 [ _T63 _T507 _T544 _T546 ]
    parm _T546 [ _T63 _T507 _T544 ]
    _T547 = *(_T544 + 0) [ _T63 _T507 _T547 ]
    _T548 = *(_T547 + 0) [ _T63 _T507 _T548 ]
    call _T548 [ _T63 _T507 ]
END BY JUMP, goto 6
BASIC BLOCK 15 : 
  Def     = [ ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
END BY RETURN, void result

FUNCTION _House.TakeAllBets : 
BASIC BLOCK 0 : 
  Def     = [ _T549 _T550 _T551 ]
  liveUse = [ ]
  liveIn  = [ _T64 ]
  liveOut = [ _T64 _T549 ]
    _T550 = "\nFirst, let's take bets.\n" [ _T64 _T550 ]
    parm _T550 [ _T64 ]
    call _PrintString [ _T64 ]
    _T551 = 0 [ _T64 _T551 ]
    _T549 = _T551 [ _T64 _T549 ]
END BY JUMP, goto 2
BASIC BLOCK 1 : 
  Def     = [ _T549 _T552 _T553 ]
  liveUse = [ _T549 ]
  liveIn  = [ _T64 _T549 ]
  liveOut = [ _T64 _T549 ]
    _T552 = 1 [ _T64 _T549 _T552 ]
    _T553 = (_T549 + _T552) [ _T64 _T553 ]
    _T549 = _T553 [ _T64 _T549 ]
END BY JUMP, goto 2
BASIC BLOCK 2 : 
  Def     = [ _T554 _T555 _T556 ]
  liveUse = [ _T64 _T549 ]
  liveIn  = [ _T64 _T549 ]
  liveOut = [ _T64 _T549 ]
    _T554 = *(_T64 + 4) [ _T64 _T549 _T554 ]
    _T555 = *(_T554 - 4) [ _T64 _T549 _T555 ]
    _T556 = (_T549 < _T555) [ _T64 _T549 _T556 ]
END BY JZERO, if _T556 = 
    0 : goto 11; 1 : goto 3
BASIC BLOCK 3 : 
  Def     = [ _T557 _T558 _T559 ]
  liveUse = [ _T64 _T549 ]
  liveIn  = [ _T64 _T549 ]
  liveOut = [ _T64 _T549 _T557 ]
    _T557 = *(_T64 + 4) [ _T64 _T549 _T557 ]
    _T558 = *(_T557 - 4) [ _T64 _T549 _T557 _T558 ]
    _T559 = (_T549 < _T558) [ _T64 _T549 _T557 _T559 ]
END BY JZERO, if _T559 = 
    0 : goto 5; 1 : goto 4
BASIC BLOCK 4 : 
  Def     = [ _T560 _T561 ]
  liveUse = [ _T549 ]
  liveIn  = [ _T64 _T549 _T557 ]
  liveOut = [ _T64 _T549 _T557 ]
    _T560 = 0 [ _T64 _T549 _T557 _T560 ]
    _T561 = (_T549 < _T560) [ _T64 _T549 _T557 _T561 ]
END BY JZERO, if _T561 = 
    0 : goto 6; 1 : goto 5
BASIC BLOCK 5 : 
  Def     = [ _T562 ]
  liveUse = [ ]
  liveIn  = [ _T64 _T549 _T557 ]
  liveOut = [ _T64 _T549 _T557 ]
    _T562 = "Decaf runtime error: Array subscript out of bounds\n" [ _T64 _T549 _T557 _T562 ]
    parm _T562 [ _T64 _T549 _T557 ]
    call _PrintString [ _T64 _T549 _T557 ]
    call _Halt [ _T64 _T549 _T557 ]
END BY JUMP, goto 6
BASIC BLOCK 6 : 
  Def     = [ _T563 _T564 _T565 _T566 _T567 _T568 _T569 ]
  liveUse = [ _T549 _T557 ]
  liveIn  = [ _T64 _T549 _T557 ]
  liveOut = [ _T64 _T549 ]
    _T563 = 4 [ _T64 _T549 _T557 _T563 ]
    _T564 = (_T549 * _T563) [ _T64 _T549 _T557 _T564 ]
    _T565 = (_T557 + _T564) [ _T64 _T549 _T565 ]
    _T566 = *(_T565 + 0) [ _T64 _T549 _T566 ]
    parm _T566 [ _T64 _T549 _T566 ]
    _T567 = *(_T566 + 0) [ _T64 _T549 _T567 ]
    _T568 = *(_T567 + 16) [ _T64 _T549 _T568 ]
    _T569 =  call _T568 [ _T64 _T549 _T569 ]
END BY JZERO, if _T569 = 
    0 : goto 1; 1 : goto 7
BASIC BLOCK 7 : 
  Def     = [ _T570 _T571 _T572 ]
  liveUse = [ _T64 _T549 ]
  liveIn  = [ _T64 _T549 ]
  liveOut = [ _T64 _T549 _T570 ]
    _T570 = *(_T64 + 4) [ _T64 _T549 _T570 ]
    _T571 = *(_T570 - 4) [ _T64 _T549 _T570 _T571 ]
    _T572 = (_T549 < _T571) [ _T64 _T549 _T570 _T572 ]
END BY JZERO, if _T572 = 
    0 : goto 9; 1 : goto 8
BASIC BLOCK 8 : 
  Def     = [ _T573 _T574 ]
  liveUse = [ _T549 ]
  liveIn  = [ _T64 _T549 _T570 ]
  liveOut = [ _T64 _T549 _T570 ]
    _T573 = 0 [ _T64 _T549 _T570 _T573 ]
    _T574 = (_T549 < _T573) [ _T64 _T549 _T570 _T574 ]
END BY JZERO, if _T574 = 
    0 : goto 10; 1 : goto 9
BASIC BLOCK 9 : 
  Def     = [ _T575 ]
  liveUse = [ ]
  liveIn  = [ _T64 _T549 _T570 ]
  liveOut = [ _T64 _T549 _T570 ]
    _T575 = "Decaf runtime error: Array subscript out of bounds\n" [ _T64 _T549 _T570 _T575 ]
    parm _T575 [ _T64 _T549 _T570 ]
    call _PrintString [ _T64 _T549 _T570 ]
    call _Halt [ _T64 _T549 _T570 ]
END BY JUMP, goto 10
BASIC BLOCK 10 : 
  Def     = [ _T576 _T577 _T578 _T579 _T580 _T581 ]
  liveUse = [ _T549 _T570 ]
  liveIn  = [ _T64 _T549 _T570 ]
  liveOut = [ _T64 _T549 ]
    _T576 = 4 [ _T64 _T549 _T570 _T576 ]
    _T577 = (_T549 * _T576) [ _T64 _T549 _T570 _T577 ]
    _T578 = (_T570 + _T577) [ _T64 _T549 _T578 ]
    _T579 = *(_T578 + 0) [ _T64 _T549 _T579 ]
    parm _T579 [ _T64 _T549 _T579 ]
    _T580 = *(_T579 + 0) [ _T64 _T549 _T580 ]
    _T581 = *(_T580 + 24) [ _T64 _T549 _T581 ]
    call _T581 [ _T64 _T549 ]
END BY JUMP, goto 1
BASIC BLOCK 11 : 
  Def     = [ ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
END BY RETURN, void result

FUNCTION _House.TakeAllTurns : 
BASIC BLOCK 0 : 
  Def     = [ _T582 _T583 ]
  liveUse = [ ]
  liveIn  = [ _T65 ]
  liveOut = [ _T65 _T582 ]
    _T583 = 0 [ _T65 _T583 ]
    _T582 = _T583 [ _T65 _T582 ]
END BY JUMP, goto 2
BASIC BLOCK 1 : 
  Def     = [ _T582 _T584 _T585 ]
  liveUse = [ _T582 ]
  liveIn  = [ _T65 _T582 ]
  liveOut = [ _T65 _T582 ]
    _T584 = 1 [ _T65 _T582 _T584 ]
    _T585 = (_T582 + _T584) [ _T65 _T585 ]
    _T582 = _T585 [ _T65 _T582 ]
END BY JUMP, goto 2
BASIC BLOCK 2 : 
  Def     = [ _T586 _T587 _T588 ]
  liveUse = [ _T65 _T582 ]
  liveIn  = [ _T65 _T582 ]
  liveOut = [ _T65 _T582 ]
    _T586 = *(_T65 + 4) [ _T65 _T582 _T586 ]
    _T587 = *(_T586 - 4) [ _T65 _T582 _T587 ]
    _T588 = (_T582 < _T587) [ _T65 _T582 _T588 ]
END BY JZERO, if _T588 = 
    0 : goto 11; 1 : goto 3
BASIC BLOCK 3 : 
  Def     = [ _T589 _T590 _T591 ]
  liveUse = [ _T65 _T582 ]
  liveIn  = [ _T65 _T582 ]
  liveOut = [ _T65 _T582 _T589 ]
    _T589 = *(_T65 + 4) [ _T65 _T582 _T589 ]
    _T590 = *(_T589 - 4) [ _T65 _T582 _T589 _T590 ]
    _T591 = (_T582 < _T590) [ _T65 _T582 _T589 _T591 ]
END BY JZERO, if _T591 = 
    0 : goto 5; 1 : goto 4
BASIC BLOCK 4 : 
  Def     = [ _T592 _T593 ]
  liveUse = [ _T582 ]
  liveIn  = [ _T65 _T582 _T589 ]
  liveOut = [ _T65 _T582 _T589 ]
    _T592 = 0 [ _T65 _T582 _T589 _T592 ]
    _T593 = (_T582 < _T592) [ _T65 _T582 _T589 _T593 ]
END BY JZERO, if _T593 = 
    0 : goto 6; 1 : goto 5
BASIC BLOCK 5 : 
  Def     = [ _T594 ]
  liveUse = [ ]
  liveIn  = [ _T65 _T582 _T589 ]
  liveOut = [ _T65 _T582 _T589 ]
    _T594 = "Decaf runtime error: Array subscript out of bounds\n" [ _T65 _T582 _T589 _T594 ]
    parm _T594 [ _T65 _T582 _T589 ]
    call _PrintString [ _T65 _T582 _T589 ]
    call _Halt [ _T65 _T582 _T589 ]
END BY JUMP, goto 6
BASIC BLOCK 6 : 
  Def     = [ _T595 _T596 _T597 _T598 _T599 _T600 _T601 ]
  liveUse = [ _T582 _T589 ]
  liveIn  = [ _T65 _T582 _T589 ]
  liveOut = [ _T65 _T582 ]
    _T595 = 4 [ _T65 _T582 _T589 _T595 ]
    _T596 = (_T582 * _T595) [ _T65 _T582 _T589 _T596 ]
    _T597 = (_T589 + _T596) [ _T65 _T582 _T597 ]
    _T598 = *(_T597 + 0) [ _T65 _T582 _T598 ]
    parm _T598 [ _T65 _T582 _T598 ]
    _T599 = *(_T598 + 0) [ _T65 _T582 _T599 ]
    _T600 = *(_T599 + 16) [ _T65 _T582 _T600 ]
    _T601 =  call _T600 [ _T65 _T582 _T601 ]
END BY JZERO, if _T601 = 
    0 : goto 1; 1 : goto 7
BASIC BLOCK 7 : 
  Def     = [ _T602 _T603 _T604 ]
  liveUse = [ _T65 _T582 ]
  liveIn  = [ _T65 _T582 ]
  liveOut = [ _T65 _T582 _T602 ]
    _T602 = *(_T65 + 4) [ _T65 _T582 _T602 ]
    _T603 = *(_T602 - 4) [ _T65 _T582 _T602 _T603 ]
    _T604 = (_T582 < _T603) [ _T65 _T582 _T602 _T604 ]
END BY JZERO, if _T604 = 
    0 : goto 9; 1 : goto 8
BASIC BLOCK 8 : 
  Def     = [ _T605 _T606 ]
  liveUse = [ _T582 ]
  liveIn  = [ _T65 _T582 _T602 ]
  liveOut = [ _T65 _T582 _T602 ]
    _T605 = 0 [ _T65 _T582 _T602 _T605 ]
    _T606 = (_T582 < _T605) [ _T65 _T582 _T602 _T606 ]
END BY JZERO, if _T606 = 
    0 : goto 10; 1 : goto 9
BASIC BLOCK 9 : 
  Def     = [ _T607 ]
  liveUse = [ ]
  liveIn  = [ _T65 _T582 _T602 ]
  liveOut = [ _T65 _T582 _T602 ]
    _T607 = "Decaf runtime error: Array subscript out of bounds\n" [ _T65 _T582 _T602 _T607 ]
    parm _T607 [ _T65 _T582 _T602 ]
    call _PrintString [ _T65 _T582 _T602 ]
    call _Halt [ _T65 _T582 _T602 ]
END BY JUMP, goto 10
BASIC BLOCK 10 : 
  Def     = [ _T608 _T609 _T610 _T611 _T612 _T613 _T614 ]
  liveUse = [ _T65 _T582 _T602 ]
  liveIn  = [ _T65 _T582 _T602 ]
  liveOut = [ _T65 _T582 ]
    _T608 = 4 [ _T65 _T582 _T602 _T608 ]
    _T609 = (_T582 * _T608) [ _T65 _T582 _T602 _T609 ]
    _T610 = (_T602 + _T609) [ _T65 _T582 _T610 ]
    _T611 = *(_T610 + 0) [ _T65 _T582 _T611 ]
    _T612 = *(_T65 + 12) [ _T65 _T582 _T611 _T612 ]
    parm _T611 [ _T65 _T582 _T611 _T612 ]
    parm _T612 [ _T65 _T582 _T611 ]
    _T613 = *(_T611 + 0) [ _T65 _T582 _T613 ]
    _T614 = *(_T613 + 12) [ _T65 _T582 _T614 ]
    call _T614 [ _T65 _T582 ]
END BY JUMP, goto 1
BASIC BLOCK 11 : 
  Def     = [ ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
END BY RETURN, void result

FUNCTION _House.ResolveAllPlayers : 
BASIC BLOCK 0 : 
  Def     = [ _T615 _T616 _T617 ]
  liveUse = [ ]
  liveIn  = [ _T66 ]
  liveOut = [ _T66 _T615 ]
    _T616 = "\nTime to resolve bets.\n" [ _T66 _T616 ]
    parm _T616 [ _T66 ]
    call _PrintString [ _T66 ]
    _T617 = 0 [ _T66 _T617 ]
    _T615 = _T617 [ _T66 _T615 ]
END BY JUMP, goto 2
BASIC BLOCK 1 : 
  Def     = [ _T615 _T618 _T619 ]
  liveUse = [ _T615 ]
  liveIn  = [ _T66 _T615 ]
  liveOut = [ _T66 _T615 ]
    _T618 = 1 [ _T66 _T615 _T618 ]
    _T619 = (_T615 + _T618) [ _T66 _T619 ]
    _T615 = _T619 [ _T66 _T615 ]
END BY JUMP, goto 2
BASIC BLOCK 2 : 
  Def     = [ _T620 _T621 _T622 ]
  liveUse = [ _T66 _T615 ]
  liveIn  = [ _T66 _T615 ]
  liveOut = [ _T66 _T615 ]
    _T620 = *(_T66 + 4) [ _T66 _T615 _T620 ]
    _T621 = *(_T620 - 4) [ _T66 _T615 _T621 ]
    _T622 = (_T615 < _T621) [ _T66 _T615 _T622 ]
END BY JZERO, if _T622 = 
    0 : goto 11; 1 : goto 3
BASIC BLOCK 3 : 
  Def     = [ _T623 _T624 _T625 ]
  liveUse = [ _T66 _T615 ]
  liveIn  = [ _T66 _T615 ]
  liveOut = [ _T66 _T615 _T623 ]
    _T623 = *(_T66 + 4) [ _T66 _T615 _T623 ]
    _T624 = *(_T623 - 4) [ _T66 _T615 _T623 _T624 ]
    _T625 = (_T615 < _T624) [ _T66 _T615 _T623 _T625 ]
END BY JZERO, if _T625 = 
    0 : goto 5; 1 : goto 4
BASIC BLOCK 4 : 
  Def     = [ _T626 _T627 ]
  liveUse = [ _T615 ]
  liveIn  = [ _T66 _T615 _T623 ]
  liveOut = [ _T66 _T615 _T623 ]
    _T626 = 0 [ _T66 _T615 _T623 _T626 ]
    _T627 = (_T615 < _T626) [ _T66 _T615 _T623 _T627 ]
END BY JZERO, if _T627 = 
    0 : goto 6; 1 : goto 5
BASIC BLOCK 5 : 
  Def     = [ _T628 ]
  liveUse = [ ]
  liveIn  = [ _T66 _T615 _T623 ]
  liveOut = [ _T66 _T615 _T623 ]
    _T628 = "Decaf runtime error: Array subscript out of bounds\n" [ _T66 _T615 _T623 _T628 ]
    parm _T628 [ _T66 _T615 _T623 ]
    call _PrintString [ _T66 _T615 _T623 ]
    call _Halt [ _T66 _T615 _T623 ]
END BY JUMP, goto 6
BASIC BLOCK 6 : 
  Def     = [ _T629 _T630 _T631 _T632 _T633 _T634 _T635 ]
  liveUse = [ _T615 _T623 ]
  liveIn  = [ _T66 _T615 _T623 ]
  liveOut = [ _T66 _T615 ]
    _T629 = 4 [ _T66 _T615 _T623 _T629 ]
    _T630 = (_T615 * _T629) [ _T66 _T615 _T623 _T630 ]
    _T631 = (_T623 + _T630) [ _T66 _T615 _T631 ]
    _T632 = *(_T631 + 0) [ _T66 _T615 _T632 ]
    parm _T632 [ _T66 _T615 _T632 ]
    _T633 = *(_T632 + 0) [ _T66 _T615 _T633 ]
    _T634 = *(_T633 + 16) [ _T66 _T615 _T634 ]
    _T635 =  call _T634 [ _T66 _T615 _T635 ]
END BY JZERO, if _T635 = 
    0 : goto 1; 1 : goto 7
BASIC BLOCK 7 : 
  Def     = [ _T636 _T637 _T638 ]
  liveUse = [ _T66 _T615 ]
  liveIn  = [ _T66 _T615 ]
  liveOut = [ _T66 _T615 _T636 ]
    _T636 = *(_T66 + 4) [ _T66 _T615 _T636 ]
    _T637 = *(_T636 - 4) [ _T66 _T615 _T636 _T637 ]
    _T638 = (_T615 < _T637) [ _T66 _T615 _T636 _T638 ]
END BY JZERO, if _T638 = 
    0 : goto 9; 1 : goto 8
BASIC BLOCK 8 : 
  Def     = [ _T639 _T640 ]
  liveUse = [ _T615 ]
  liveIn  = [ _T66 _T615 _T636 ]
  liveOut = [ _T66 _T615 _T636 ]
    _T639 = 0 [ _T66 _T615 _T636 _T639 ]
    _T640 = (_T615 < _T639) [ _T66 _T615 _T636 _T640 ]
END BY JZERO, if _T640 = 
    0 : goto 10; 1 : goto 9
BASIC BLOCK 9 : 
  Def     = [ _T641 ]
  liveUse = [ ]
  liveIn  = [ _T66 _T615 _T636 ]
  liveOut = [ _T66 _T615 _T636 ]
    _T641 = "Decaf runtime error: Array subscript out of bounds\n" [ _T66 _T615 _T636 _T641 ]
    parm _T641 [ _T66 _T615 _T636 ]
    call _PrintString [ _T66 _T615 _T636 ]
    call _Halt [ _T66 _T615 _T636 ]
END BY JUMP, goto 10
BASIC BLOCK 10 : 
  Def     = [ _T642 _T643 _T644 _T645 _T646 _T647 _T648 _T649 _T650 _T651 ]
  liveUse = [ _T66 _T615 _T636 ]
  liveIn  = [ _T66 _T615 _T636 ]
  liveOut = [ _T66 _T615 ]
    _T642 = 4 [ _T66 _T615 _T636 _T642 ]
    _T643 = (_T615 * _T642) [ _T66 _T615 _T636 _T643 ]
    _T644 = (_T636 + _T643) [ _T66 _T615 _T644 ]
    _T645 = *(_T644 + 0) [ _T66 _T615 _T645 ]
    _T646 = *(_T66 + 8) [ _T66 _T615 _T645 _T646 ]
    parm _T646 [ _T66 _T615 _T645 _T646 ]
    _T647 = *(_T646 + 0) [ _T66 _T615 _T645 _T647 ]
    _T648 = *(_T647 + 28) [ _T66 _T615 _T645 _T648 ]
    _T649 =  call _T648 [ _T66 _T615 _T645 _T649 ]
    parm _T645 [ _T66 _T615 _T645 _T649 ]
    parm _T649 [ _T66 _T615 _T645 ]
    _T650 = *(_T645 + 0) [ _T66 _T615 _T650 ]
    _T651 = *(_T650 + 32) [ _T66 _T615 _T651 ]
    call _T651 [ _T66 _T615 ]
END BY JUMP, goto 1
BASIC BLOCK 11 : 
  Def     = [ ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
END BY RETURN, void result

FUNCTION _House.PrintAllMoney : 
BASIC BLOCK 0 : 
  Def     = [ _T652 _T653 ]
  liveUse = [ ]
  liveIn  = [ _T67 ]
  liveOut = [ _T67 _T652 ]
    _T653 = 0 [ _T67 _T653 ]
    _T652 = _T653 [ _T67 _T652 ]
END BY JUMP, goto 2
BASIC BLOCK 1 : 
  Def     = [ _T652 _T654 _T655 ]
  liveUse = [ _T652 ]
  liveIn  = [ _T67 _T652 ]
  liveOut = [ _T67 _T652 ]
    _T654 = 1 [ _T67 _T652 _T654 ]
    _T655 = (_T652 + _T654) [ _T67 _T655 ]
    _T652 = _T655 [ _T67 _T652 ]
END BY JUMP, goto 2
BASIC BLOCK 2 : 
  Def     = [ _T656 _T657 _T658 ]
  liveUse = [ _T67 _T652 ]
  liveIn  = [ _T67 _T652 ]
  liveOut = [ _T67 _T652 ]
    _T656 = *(_T67 + 4) [ _T67 _T652 _T656 ]
    _T657 = *(_T656 - 4) [ _T67 _T652 _T657 ]
    _T658 = (_T652 < _T657) [ _T67 _T652 _T658 ]
END BY JZERO, if _T658 = 
    0 : goto 7; 1 : goto 3
BASIC BLOCK 3 : 
  Def     = [ _T659 _T660 _T661 ]
  liveUse = [ _T67 _T652 ]
  liveIn  = [ _T67 _T652 ]
  liveOut = [ _T67 _T652 _T659 ]
    _T659 = *(_T67 + 4) [ _T67 _T652 _T659 ]
    _T660 = *(_T659 - 4) [ _T67 _T652 _T659 _T660 ]
    _T661 = (_T652 < _T660) [ _T67 _T652 _T659 _T661 ]
END BY JZERO, if _T661 = 
    0 : goto 5; 1 : goto 4
BASIC BLOCK 4 : 
  Def     = [ _T662 _T663 ]
  liveUse = [ _T652 ]
  liveIn  = [ _T67 _T652 _T659 ]
  liveOut = [ _T67 _T652 _T659 ]
    _T662 = 0 [ _T67 _T652 _T659 _T662 ]
    _T663 = (_T652 < _T662) [ _T67 _T652 _T659 _T663 ]
END BY JZERO, if _T663 = 
    0 : goto 6; 1 : goto 5
BASIC BLOCK 5 : 
  Def     = [ _T664 ]
  liveUse = [ ]
  liveIn  = [ _T67 _T652 _T659 ]
  liveOut = [ _T67 _T652 _T659 ]
    _T664 = "Decaf runtime error: Array subscript out of bounds\n" [ _T67 _T652 _T659 _T664 ]
    parm _T664 [ _T67 _T652 _T659 ]
    call _PrintString [ _T67 _T652 _T659 ]
    call _Halt [ _T67 _T652 _T659 ]
END BY JUMP, goto 6
BASIC BLOCK 6 : 
  Def     = [ _T665 _T666 _T667 _T668 _T669 _T670 ]
  liveUse = [ _T652 _T659 ]
  liveIn  = [ _T67 _T652 _T659 ]
  liveOut = [ _T67 _T652 ]
    _T665 = 4 [ _T67 _T652 _T659 _T665 ]
    _T666 = (_T652 * _T665) [ _T67 _T652 _T659 _T666 ]
    _T667 = (_T659 + _T666) [ _T67 _T652 _T667 ]
    _T668 = *(_T667 + 0) [ _T67 _T652 _T668 ]
    parm _T668 [ _T67 _T652 _T668 ]
    _T669 = *(_T668 + 0) [ _T67 _T652 _T669 ]
    _T670 = *(_T669 + 20) [ _T67 _T652 _T670 ]
    call _T670 [ _T67 _T652 ]
END BY JUMP, goto 1
BASIC BLOCK 7 : 
  Def     = [ ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
END BY RETURN, void result

FUNCTION _House.PlayOneGame : 
BASIC BLOCK 0 : 
  Def     = [ _T671 _T672 _T673 _T674 _T675 _T676 ]
  liveUse = [ _T68 ]
  liveIn  = [ _T68 ]
  liveOut = [ _T68 ]
    _T671 = *(_T68 + 12) [ _T68 _T671 ]
    parm _T671 [ _T68 _T671 ]
    _T672 = *(_T671 + 0) [ _T68 _T672 ]
    _T673 = *(_T672 + 12) [ _T68 _T673 ]
    _T674 =  call _T673 [ _T68 _T674 ]
    _T675 = 26 [ _T68 _T674 _T675 ]
    _T676 = (_T674 < _T675) [ _T68 _T676 ]
END BY JZERO, if _T676 = 
    0 : goto 2; 1 : goto 1
BASIC BLOCK 1 : 
  Def     = [ _T677 _T678 _T679 ]
  liveUse = [ _T68 ]
  liveIn  = [ _T68 ]
  liveOut = [ _T68 ]
    _T677 = *(_T68 + 12) [ _T68 _T677 ]
    parm _T677 [ _T68 _T677 ]
    _T678 = *(_T677 + 0) [ _T68 _T678 ]
    _T679 = *(_T678 + 8) [ _T68 _T679 ]
    call _T679 [ _T68 ]
END BY JUMP, goto 2
BASIC BLOCK 2 : 
  Def     = [ _T680 _T681 _T682 _T683 _T684 _T685 _T686 _T687 _T688 _T689 _T690 _T691 _T692 _T693 _T694 _T695 _T696 _T697 _T698 ]
  liveUse = [ _T68 ]
  liveIn  = [ _T68 ]
  liveOut = [ ]
    parm _T68 [ _T68 ]
    _T680 = *(_T68 + 0) [ _T68 _T680 ]
    _T681 = *(_T680 + 8) [ _T68 _T681 ]
    call _T681 [ _T68 ]
    _T682 = "\nDealer starts. " [ _T68 _T682 ]
    parm _T682 [ _T68 ]
    call _PrintString [ _T68 ]
    _T683 = *(_T68 + 8) [ _T68 _T683 ]
    _T684 = 0 [ _T68 _T683 _T684 ]
    parm _T683 [ _T68 _T683 _T684 ]
    parm _T684 [ _T68 _T683 ]
    _T685 = *(_T683 + 0) [ _T68 _T685 ]
    _T686 = *(_T685 + 0) [ _T68 _T686 ]
    call _T686 [ _T68 ]
    _T687 = *(_T68 + 8) [ _T68 _T687 ]
    _T688 = *(_T68 + 12) [ _T68 _T687 _T688 ]
    parm _T687 [ _T68 _T687 _T688 ]
    parm _T688 [ _T68 _T687 ]
    _T689 = *(_T687 + 0) [ _T68 _T689 ]
    _T690 = *(_T689 + 4) [ _T68 _T690 ]
    call _T690 [ _T68 ]
    parm _T68 [ _T68 ]
    _T691 = *(_T68 + 0) [ _T68 _T691 ]
    _T692 = *(_T691 + 12) [ _T68 _T692 ]
    call _T692 [ _T68 ]
    _T693 = *(_T68 + 8) [ _T68 _T693 ]
    _T694 = *(_T68 + 12) [ _T68 _T693 _T694 ]
    parm _T693 [ _T68 _T693 _T694 ]
    parm _T694 [ _T68 _T693 ]
    _T695 = *(_T693 + 0) [ _T68 _T695 ]
    _T696 = *(_T695 + 12) [ _T68 _T696 ]
    call _T696 [ _T68 ]
    parm _T68 [ _T68 ]
    _T697 = *(_T68 + 0) [ _T697 ]
    _T698 = *(_T697 + 16) [ _T698 ]
    call _T698 [ ]
END BY RETURN, void result

FUNCTION main : 
BASIC BLOCK 0 : 
  Def     = [ _T699 _T700 _T701 _T702 _T703 _T704 _T705 _T706 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ _T699 _T701 ]
    _T700 = 1 [ _T700 ]
    _T699 = _T700 [ _T699 ]
    _T702 =  call _House_New [ _T699 _T702 ]
    _T701 = _T702 [ _T699 _T701 ]
    parm _T701 [ _T699 _T701 ]
    _T703 = *(_T701 + 0) [ _T699 _T701 _T703 ]
    _T704 = *(_T703 + 0) [ _T699 _T701 _T704 ]
    call _T704 [ _T699 _T701 ]
    parm _T701 [ _T699 _T701 ]
    _T705 = *(_T701 + 0) [ _T699 _T701 _T705 ]
    _T706 = *(_T705 + 4) [ _T699 _T701 _T706 ]
    call _T706 [ _T699 _T701 ]
END BY JUMP, goto 1
BASIC BLOCK 1 : 
  Def     = [ ]
  liveUse = [ _T699 ]
  liveIn  = [ _T699 _T701 ]
  liveOut = [ _T701 ]
END BY JZERO, if _T699 = 
    0 : goto 3; 1 : goto 2
BASIC BLOCK 2 : 
  Def     = [ _T699 _T707 _T708 _T709 _T710 ]
  liveUse = [ _T701 ]
  liveIn  = [ _T701 ]
  liveOut = [ _T699 _T701 ]
    parm _T701 [ _T701 ]
    _T707 = *(_T701 + 0) [ _T701 _T707 ]
    _T708 = *(_T707 + 24) [ _T701 _T708 ]
    call _T708 [ _T701 ]
    _T709 = "\nDo you want to play another hand?" [ _T701 _T709 ]
    parm _T709 [ _T701 ]
    _T710 =  call _Main.GetYesOrNo [ _T701 _T710 ]
    _T699 = _T710 [ _T699 _T701 ]
END BY JUMP, goto 1
BASIC BLOCK 3 : 
  Def     = [ _T711 _T712 _T713 _T714 _T715 ]
  liveUse = [ _T701 ]
  liveIn  = [ _T701 ]
  liveOut = [ ]
    parm _T701 [ _T701 ]
    _T711 = *(_T701 + 0) [ _T711 ]
    _T712 = *(_T711 + 20) [ _T712 ]
    call _T712 [ ]
    _T713 = "Thank you for playing...come again soon.\n" [ _T713 ]
    parm _T713 [ ]
    call _PrintString [ ]
    _T714 = "\nCS143 BlackJack Copyright (c) 1999 by Peter Mork.\n" [ _T714 ]
    parm _T714 [ ]
    call _PrintString [ ]
    _T715 = "(2001 mods by jdz)\n" [ _T715 ]
    parm _T715 [ ]
    call _PrintString [ ]
END BY RETURN, void result

FUNCTION _Main.GetYesOrNo : 
BASIC BLOCK 0 : 
  Def     = [ _T716 _T717 _T718 _T719 ]
  liveUse = [ _T73 ]
  liveIn  = [ _T73 ]
  liveOut = [ ]
    parm _T73 [ ]
    call _PrintString [ ]
    _T716 = " (0=No/1=Yes) " [ _T716 ]
    parm _T716 [ ]
    call _PrintString [ ]
    _T717 =  call _ReadInteger [ _T717 ]
    _T718 = 0 [ _T717 _T718 ]
    _T719 = (_T717 != _T718) [ _T719 ]
END BY RETURN, result = _T719

